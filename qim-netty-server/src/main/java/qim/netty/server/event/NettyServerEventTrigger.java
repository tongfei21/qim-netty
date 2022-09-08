package qim.netty.server.event;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import qim.netty.sdk.model.Packet;
import qim.netty.server.handler.NettyServerHandler;

/**
 * @author qingdeng
 * @create 2021-07-08
 */
public class NettyServerEventTrigger extends ChannelInboundHandlerAdapter {

}