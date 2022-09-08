package qim.netty.sdk.model;

public enum MsgType {
  
  /**
   * MSG_UNKNOW = 0;
   */
  MSG_UNKNOW(0),
  /**
   *握手请求，含http的websocket握手请求
   * MSG_HANDSHAKE_REQ = 1;
   */
  MSG_HANDSHAKE_REQ(1),
  /**
   *握手响应，含http的websocket握手响应
   * MSG_HANDSHAKE_RESP = 2;
   */
  MSG_HANDSHAKE_RESP(2),
  /**
   *鉴权请求
   * MSG_AUTH_REQ = 3;
   */
  MSG_AUTH_REQ(3),
  /**
   * 鉴权响应
   * MSG_AUTH_RESP = 4;
   */
  MSG_AUTH_RESP(4),
  /**
   *登录请求
   * MSG_LOGIN_REQ = 5;
   */
  MSG_LOGIN_REQ(5),
  /**
   *登录响应
   * MSG_LOGIN_RESP = 6;
   */
  MSG_LOGIN_RESP(6),
  /**
   *申请进入群组
   * MSG_JOIN_GROUP_REQ = 7;
   */
  MSG_JOIN_GROUP_REQ(7),
  /**
   *申请进入群组响应
   * MSG_JOIN_GROUP_RESP = 8;
   */
  MSG_JOIN_GROUP_RESP(8),
  /**
   *进入群组通知
   * MSG_JOIN_GROUP_NOTIFY_RESP = 9;
   */
  MSG_JOIN_GROUP_NOTIFY_RESP(9),
  /**
   *退出群组通知
   * MSG_EXIT_GROUP_NOTIFY_RESP = 10;
   */
  MSG_EXIT_GROUP_NOTIFY_RESP(10),
  /**
   *聊天请求
   * MSG_CHAT_REQ = 11;
   */
  MSG_CHAT_REQ(11),
  /**
   *聊天响应
   * MSG_CHAT_RESP = 12;
   */
  MSG_CHAT_RESP(12),
  /**
   *心跳请求
   * MSG_HEARTBEAT_REQ = 13;
   */
  MSG_HEARTBEAT_REQ(13),
  /**
   *关闭请求
   * MSG_CLOSE_REQ = 14;
   */
  MSG_CLOSE_REQ(14),
  /**
   *发出撤消消息指令(管理员可以撤消所有人的消息，自己可以撤消自己的消息)
   * MSG_CANCEL_MSG_REQ = 15;
   */
  MSG_CANCEL_MSG_REQ(15),
  /**
   *收到撤消消息指令
   * MSG_CANCEL_MSG_RESP = 16;
   */
  MSG_CANCEL_MSG_RESP(16),
  /**
   *获取用户信息;
   * MSG_GET_USER_REQ = 17;
   */
  MSG_GET_USER_REQ(17),
  /**
   *获取用户信息响应;
   * MSG_GET_USER_RESP = 18;
   */
  MSG_GET_USER_RESP(18),
  /**
   * 获取聊天消息;
   * MSG_GET_MESSAGE_REQ = 19;
   */
  MSG_GET_MESSAGE_REQ(19),
  /**
   * 获取聊天消息响应;
   * MSG_GET_MESSAGE_RESP = 20;
   */
  MSG_GET_MESSAGE_RESP(20),
  ;

  public final int getNumber() {
    return value;
  }

  public static MsgType valueOf(int value) {
    return forNumber(value);
  }

  public static MsgType forNumber(int value) {
	  for(MsgType msgType : MsgType.values()){
	   	   if(msgType.getNumber() == value){
	   		   return msgType;
	   	   }
      }
	  return null;
  }
  
  private final int value;

  private MsgType(int value) {
    this.value = value;
  }
}

