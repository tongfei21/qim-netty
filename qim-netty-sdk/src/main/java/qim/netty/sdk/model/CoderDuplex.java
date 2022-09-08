package qim.netty.sdk.model;

import io.netty.channel.CombinedChannelDuplexHandler;

public class CoderDuplex extends CombinedChannelDuplexHandler<Decoder, Encoder> {
    public CoderDuplex() {
        super(new Decoder(),new Encoder());
    }
}
