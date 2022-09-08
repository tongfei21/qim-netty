package com.qim.client.handler;

import com.alibaba.fastjson.JSONObject;
import com.qim.client.QimClient;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import qim.netty.sdk.model.MsgType;
import qim.netty.sdk.model.NettyHeart;
import qim.netty.sdk.model.Packet;
import qim.netty.sdk.model.WtsChat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  @author Qingdeng
 *  读写超时机制
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<Packet> {

    private QimClient qimClient;

    // 定义客户端没有收到服务端的pong消息的最大次数
    private static final int MAX_UN_REC_PONG_TIMES = 3;

    // 多长时间未请求后，发送心跳
    private static final int WRITE_WAIT_SECONDS = 5;

    // 隔N秒后重连
    private static final int RE_CONN_WAIT_SECONDS = 5;

    // 客户端连续N次没有收到服务端的pong消息  计数器
    private int unRecPongTimes = 0;

    private ScheduledExecutorService executorService;

    // 是否停止
    private boolean isStop = false;
    //读写超时并且添加IdleStateHandler时，会触发这个方法

    public NettyClientHandler(QimClient qimClient) {
        this.qimClient = qimClient;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //获取超时对象，读超时，写超时还是读写超时
//        IdleState state = ((IdleStateEvent) evt).state();
//        //如果是读写超时，我这里整的简单了
//        if (state.equals(IdleState.ALL_IDLE)) {
//            System.out.println("客户端发送数据包");
//
//            //给服务端端发送字符为（HeartBeat-req）的心跳请求
//            ctx.writeAndFlush(Unpooled.copiedBuffer("HeartBeat-req".getBytes()));
//        }

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                /*读超时*/
                System.out.println("===server-side===(READER_IDLE read timeout)");

            } else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/
                System.out.println("===server-side===(WRITER_IDLE the write timeout)");

                if (unRecPongTimes < MAX_UN_REC_PONG_TIMES) {
                    //判断连接正常
                    if (ctx.channel().isActive()) {
                        //byte[] infos = JsonUtil.toJsonBytes(this.getMessage());
                        unRecPongTimes = 0;
                        Packet packet = NettyHeart.getHeart();
                        System.out.println("print content:"+packet.getObjMessage()+"---------------");
                        ctx.channel().writeAndFlush(packet);
                    }
                    unRecPongTimes++;
                } else {
                    ctx.channel().close();
                }
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                System.out.println("===server-side===(ALL_IDLE timeout)");
            }
        }

    }

    //客户端读取服务器发送的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            //String body = new String(req, "UTF-8");
            Packet packet = (Packet)msg;
            if (packet instanceof Packet ){
                if (packet.getMsgType() == MsgType.MSG_CHAT_RESP){
                    WtsChat message = JSONObject.parseObject(packet.getObjMessage().toString(),WtsChat.class);
                    System.out.println(message.getContent());
                }else if (packet.getMsgType() == MsgType.MSG_HEARTBEAT_REQ){

                    System.out.println("----------"+ packet.getObjMessage() +"---------------");
                }

            }else{
                System.out.println("decode is error");
            }

            //如果是服务端回应的心跳website
//            if ("HeartBeat-resp".equals(body)) {
//                unRecPongTimes--;
//                System.out.println("服务端回应心跳请求");
//            } else {//如果是服务端回应的其它
//                System.out.println("Now is:" + body);
//            }
        } catch (Exception e) {

        } finally {

            //标配
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {

    }

    /***
     * 自动重连
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.printf("Connection times is:%s TimeClient broken link:%s \n",unRecPongTimes, ctx.channel().localAddress().toString());

        //使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();

        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                qimClient.start();
            }
        }, 2, TimeUnit.SECONDS);
        ctx.fireChannelInactive();
    }

    /***
     * 业务逻辑异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channelClose(ctx.channel());
        ctx.close();
    }

    /**
     * 关闭方法
     */
    public void channelClose(Channel channel) {
        if (channel != null) {
            try {
                //channel.pipeline().remove(NettyClientEventTrigger.class.getName());
                //channel.pipeline().remove(NettyClientHandler.class.getName());
                channel.close();
                channel.eventLoop().shutdownGracefully();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                channel = null;
            }
        }
    }

    private Packet getMessage(){
        long time = System.currentTimeMillis();
        WtsChat wc = new WtsChat();
        //格式化格式
        String format = "YYYY-MM-dd hh:mm:ss";
        // DateTimeFormatter.ofPattern方法根据指定的格式输出时间
        String formatDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));


        wc.setChatType((byte) 1);
        wc.setType((byte) 1);
        wc.setContent("this is request message !");
        wc.setToUid(1);
        wc.setFromUid(2);
        wc.setAddTime(LocalDateTime.now());

        return new Packet(String.valueOf(time),MsgType.MSG_CHAT_REQ,wc);
    }
}