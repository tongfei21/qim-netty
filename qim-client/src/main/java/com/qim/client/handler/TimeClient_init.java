package com.qim.client.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
没有封装成方法的启动诶，不能实现重连机制
 */
public class TimeClient_init {


    public static void main(String[] args) throws Exception {
        int port = 8080;
        String host = "127.0.0.1";
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group)
//                    .channel(NioSocketChannel.class)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            //心跳机制
//                            socketChannel.pipeline().addLast(new IdleStateHandler(5, 5, 5, TimeUnit.SECONDS));//添加心跳机制
//                            socketChannel.pipeline().addLast(new NettyClientEventTrigger());//添加心跳机制监听器
//                            //TimeClientHandler是自己定义的方法
//                            socketChannel.pipeline().addLast(new NettyClientHandler(null));
//                        }
//                    });
//            //发起异步连接操作
//            ChannelFuture f = b.connect(host, port).sync();
//
//
//            //发送数据
//            f.channel().writeAndFlush(Unpooled.copiedBuffer("您好1".getBytes()));
//            Thread.sleep(4000);
//            f.channel().writeAndFlush(Unpooled.copiedBuffer("您好2".getBytes()));
//
//
//            //等待客户端链路关闭
//            f.channel().closeFuture().sync();
//
//        } catch (Exception e) {
//
//        } finally {
//            //优雅关闭
//            group.shutdownGracefully();
//        }
    }
}