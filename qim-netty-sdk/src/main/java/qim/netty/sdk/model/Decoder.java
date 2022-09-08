package qim.netty.sdk.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import qim.netty.sdk.config.CodeConfig;
import qim.netty.sdk.util.JsonUtil;

public class Decoder extends LengthFieldBasedFrameDecoder {

    private Object pt;

    public Decoder() {
        super(CodeConfig.MAX_FRAME_LENGTH,CodeConfig.LENGTH_FIELD_OFFSET, CodeConfig.LENGTH_FIELD_LENGTH,
                CodeConfig.LENGTH_ADJUSTMENT, CodeConfig.INITIAL_BYTES_TO_STRIP);
    }

    @Override
    protected Packet decode(ChannelHandlerContext ctx, ByteBuf in2) throws Exception {
        ByteBuf in = (ByteBuf) super.decode(ctx, in2);
        if (in == null) {
            return null;
        }

        byte type = in.readByte();
        int len = in.readInt();
        ByteBuf buf = in.readBytes(len);
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        // 校验头长度
        if (type< CodeConfig.HEADER_SIZE) {
            return null;
        }
        // 校验消息体长度
        if (req.length < len) {
            return null;
        }
        //System.out.println("contention length："+in.readableBytes()+"The incoming length："+len+"\n");
        // ByteBuf转为Msg类型
        return JsonUtil.toBean(req,Packet.class);
    }
}

