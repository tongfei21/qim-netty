package qim.netty.sdk.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyHeart extends Packet{

    private static final long SessionID = -4403810563373662208L;
    // （1）心跳内容
    public static final String HEARTBEAT_PACKET = "Heartbeat";

    /**
     * 生成心跳包
     * @return
     */
    public static Packet getHeart(){
        long time = System.currentTimeMillis();
        return new Packet(String.valueOf(time),MsgType.MSG_HEARTBEAT_REQ,HEARTBEAT_PACKET);
    }
}
