package qim.netty.sdk.core;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

public interface IPacket {

    final long serialVersionUID = 5275372187150637318L;
    Long  id = new AtomicLong().incrementAndGet();

    /**
     * 本packet在解码时，消耗的字节数
     */
    int	 byteCount = 0;
    Long respId = null;
    boolean isBlockSend	= false;

    /**
    * 消息是否是另外一台机器通过topic转过来的，如果是就不要死循环地再一次转发
    */
    boolean	isFromCluster = false;

    /**
    * 同步发送时，需要的同步序列号
    */
    Integer	syncSequence= 0;

    /**
    * 预编码过的bytebuffer，如果此值不为null，框架则会忽略原来的encode()而直接用此值
    */
    ByteBuffer preEncodedByteBuffer	= null;

    /**
    * 是否已经进行ssl加密过
    */
    boolean isSslEncrypted = false;

}
