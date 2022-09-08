package qim.netty.sdk.model;

import com.alibaba.fastjson.JSONObject;
import qim.netty.sdk.core.IPacket;

import java.io.Serializable;



/**
 * Message.
 */
public class Packet implements Serializable, IPacket {

	private static final long SessionID = -8468023053404602368L;

	/**
	 * 消息id，全局唯一
	 * UUIDSessionIdGenerator.instance.sessionId(null)
	 */
	private String uuid ;

	/**
	 * 消息cmd命令码
	 */
	private MsgType msgType ;

	private Object ObjMessage;

	/**
	 *  JSON对象格式如：{'扩展字段名称':'扩展字段value'}
	 */
	private JSONObject extras;

	public static long getSessionID() {
		return SessionID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String id) {
		this.uuid = uuid;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public Packet(){}

	public JSONObject getExtras() {
		return extras;
	}

	public void setExtras(JSONObject extras) {
		this.extras = extras;
	}

	public Object getObjMessage() {
		return ObjMessage;
	}

	public void setObjMessage(Object objBytes) {
		this.ObjMessage = objBytes;
	}

	public Packet(String id, MsgType msgType,Object ObjMessage){
		this.uuid = uuid;
		this.msgType = msgType;
		this.ObjMessage = ObjMessage;
	}

	public abstract static class Builder<T extends Packet, B extends Packet.Builder<T,B>>{

		private T obj;

		public abstract T getObj();

		public void setObj(T obj) {
			this.obj = obj;
		}

		/**
		 * 供子类实现的抽象构建对象
		 * @return
		 */
		public abstract byte[] build(T obj);
	}
}
