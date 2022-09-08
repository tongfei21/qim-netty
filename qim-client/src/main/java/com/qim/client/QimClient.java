package com.qim.client;

import com.qim.client.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import qim.netty.sdk.model.CoderDuplex;
import qim.netty.sdk.model.MsgType;
import qim.netty.sdk.model.Packet;
import qim.netty.sdk.model.WtsChat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author CBeann
 * @create 2019-09-20 19:44
 */
public class QimClient {

    public static void main(String[] args) {
        QimClient qimClient = new QimClient(8080, "127.0.0.1");
        qimClient.start();

    }

    private int port = 8080;
    private String host = "127.0.0.1";
    private Channel channel = null;
    private Bootstrap b = null;

    public QimClient(int port, String host) {
        this.host = host;
        this.port = port;
        init();
        if (channel != null){
            channel.writeAndFlush(getMessage());
        }

    }

    public void init() {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        b = new Bootstrap();

        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        socketChannel.pipeline().addLast(new IdleStateHandler(0, 19, 0, TimeUnit.SECONDS));//添加心跳机制
                        //编解码
                        socketChannel.pipeline().addLast("codec", new CoderDuplex());
                        socketChannel.pipeline().addLast(new NettyClientHandler(QimClient.this));
                    }
                });

    }

    public void start() {
        //发起异步连接操作
        ChannelFuture f = b.connect(this.host, this.port);

        //断线重连
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    final EventLoop loop = channelFuture.channel().eventLoop();
                    loop.schedule(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("not connect service");

                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            start();
                        }
                    }, 1L, TimeUnit.SECONDS);
                } else {
                    channel = channelFuture.channel();
                    if (channel != null){
                        channel.writeAndFlush(getMessage());
                    }
                }
            }
        });

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
