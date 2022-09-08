package qim.netty.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import qim.netty.sdk.model.MsgType;
import qim.netty.sdk.model.Packet;
import qim.netty.sdk.model.WtsChat;


/**
 * @author qingdeng
 * @create 2021-07-08
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //失败计数器：未收到client端发送的ping请求
    private int unRecPingTimes = 0 ;

    //每个chanel对应一个线程，此处用来存储对应于每个线程的一些基础数据，此处不一定要为KeepAliveMessage对象
    //ThreadLocal<KeepAliveMessage> localMsgInfo = new ThreadLocal<KeepAliveMessage>();

    // 定义客户端没有收到服务端的pong消息的最大次数
    private static final int MAX_UN_REC_PING_TIMES = 5;

    //当前超时次数
    private int timeoutNum = 0;

    //运行最大超时次数
    private int timeoutNumMax = 5;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //服务器读客户端发送来的数据
        //ByteBuf buf = (ByteBuf) msg;
        //byte[] req = new byte[buf.readableBytes()];
        //buf.readBytes(req);
        //String body = new String(req, "UTF-8");

        // ByteBuf转为Msg类型
        Packet packet = (Packet)msg; //JsonUtil.toBean(req,Packet.class);
        if (packet instanceof Packet ){

            if (packet.getMsgType() == MsgType.MSG_CHAT_REQ){
                WtsChat message = JSONObject.parseObject(packet.getObjMessage().toString(),WtsChat.class);
                NettyMessageHandler.MessageHandler(message);
                System.out.println(message.getContent());
                message.setContent("this message is response ~");
                packet.setObjMessage(message);
                packet.setMsgType(MsgType.MSG_CHAT_RESP);

                //信息存储



            }else if (packet.getMsgType() == MsgType.MSG_HEARTBEAT_REQ){

                NettyHeartHandler.HeartHandler(String.valueOf(packet.getObjMessage()));
                packet.setMsgType(MsgType.MSG_HEARTBEAT_REQ);
            }

        }else{
            System.out.println("decode is error");
        }
        //请求超时次数清零
        unRecPingTimes = 0;
        ctx.writeAndFlush(packet);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.printf("The connection times closed:%s TimeServer broken link: %s \n",unRecPingTimes,ctx.channel().localAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channelClose(ctx.channel());
        ctx.close();
    }

    //读写超时发送心跳
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                /*读超时*/
                //System.out.println("===server-side===(READER_IDLE Read timeout)");
                // 失败计数器次数大于等于3次的时候，关闭链接，等待client重连
                if(unRecPingTimes >= MAX_UN_REC_PING_TIMES){
                    // 连续超过N次未收到client的ping消息，那么关闭该通道，等待client重连
                    //System.out.println("The connection times closed:"+unRecPingTimes);
                    channelClose(ctx.channel());
                }else{
                    //失败计数器加1
                    unRecPingTimes++;
                }
            } else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/
                //System.out.println("===server-side===(WRITER_IDLE Write a timeout)");
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                //System.out.println("===server-side===(ALL_IDLE The total overtime)");
            }
        }
    }

    /**
     * 关闭方法
     */
    public void channelClose(Channel channel){
        if (channel != null){
            try{
                //channel.pipeline().remove(NettyServerHandler.class.getName());
                channel.close();
                channel.eventLoop().shutdownGracefully();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                channel = null;
            }
        }
    }
}