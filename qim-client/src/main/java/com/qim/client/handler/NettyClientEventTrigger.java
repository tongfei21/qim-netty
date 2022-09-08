package com.qim.client.handler;

import qim.netty.sdk.model.Packet;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Qingdeng
 * 读写超时机制
 */
public class NettyClientEventTrigger extends ChannelInboundHandlerAdapter {


}
