package qim.netty.sdk.config;

public class CodeConfig {

    public static final int MAX_FRAME_LENGTH = 1024 * 1024;
    public static final int LENGTH_FIELD_LENGTH = 1;
    public static final int LENGTH_FIELD_OFFSET = 4;
    public static final int LENGTH_ADJUSTMENT = 0;
    public static final int INITIAL_BYTES_TO_STRIP = 0;

    public static final int HEADER_SIZE = 5;

    public static final byte MSGTYPE_CHAT = 1;

    public final static int READER_IDLE_TIME_SECONDS = 20;//读操作空闲20秒
    public final static int WRITER_IDLE_TIME_SECONDS = 20;//写操作空闲20秒
    public final static int ALL_IDLE_TIME_SECONDS = 25;//读写全部空闲40秒

}
