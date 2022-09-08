package qim.netty.server;

import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import qim.netty.sdk.config.CodeConfig;
import qim.netty.sdk.model.CoderDuplex;
import qim.netty.server.handler.NettyServerHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author qingdeng
 * @create 2021-07-08
 */
public class QimServer {


    public static void main(String[] args) throws Exception {
        int port = 8080;
        //配置服务器端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new IdleStateHandler(CodeConfig.READER_IDLE_TIME_SECONDS,0, 0, TimeUnit.SECONDS));//添加心跳机制
                            socketChannel.pipeline().addLast("codec", new CoderDuplex());
                            //socketChannel.pipeline().addLast(new NettyServerEventTrigger());//添加心跳机制监听器
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定端口
            ChannelFuture f = b.bind(port).sync();

            System.out.println("--------------------start--------------------------");
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();


        } catch (Exception e) {

        } finally {
            //优雅关闭，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}