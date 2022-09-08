package qim.netty.sdk.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import qim.netty.sdk.util.JsonUtil;

public class Encoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf out) throws Exception {
        if (packet == null | packet.getMsgType()== null) {
            throw new Exception("The encode message is null");
        }

        String ID = packet.getUuid();
        // 获取类型
        MsgType type = packet.getMsgType();

        // 获取消息体
        byte[] objBytes = JsonUtil.toJsonBytes(packet);
        // 计算消息体的长度
        int bodySize = objBytes.length;

        //System.out.printf("MyEncoder header: %s \n", type);

        //消息体，三段，1.id, 2.长度，3.内容
        out.writeByte(type.getNumber());
        out.writeInt(bodySize);
        out.writeBytes(objBytes);
    }

}
