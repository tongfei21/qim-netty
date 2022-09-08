package qim.netty.server.redis;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * qingdeng
 * @date 2021年7月21日
 */
public class RedisConfiguration {
	
	private  int retryNum =100;
	private  int maxActive=100;
	private  int maxIdle=20;
	private  long maxWait=5000L;
	private  int timeout=2000;
	private  String auth;
	private  String host = "";
	private  int port= 0;
	
	public RedisConfiguration(){}
	
	public RedisConfiguration(Properties prop){
		this.retryNum = Integer.valueOf(prop.getProperty("redis.retrynum", "100"));
		this.maxActive = Integer.valueOf(prop.getProperty("redis.maxactive","100"));
		this.maxIdle =  Integer.valueOf(prop.getProperty("redis.maxidle", "20"));
		this.maxWait =  Long.valueOf(prop.getProperty("redis.maxwait","5000"));
		this.timeout =  Integer.valueOf(prop.getProperty("redis.timeout", "2000"));
		this.auth =  prop.getProperty("redis.auth",null);
		if(StringUtils.isEmpty(auth)) {
			this.auth = null;
		}
		this.host = prop.getProperty("redis.host","");
		this.port =  Integer.valueOf(prop.getProperty("redis.port","0"));
	}
	public int getRetryNum() {
		return retryNum;
	}
	public void setRetryNum(int retryNum) {
		this.retryNum = retryNum;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public long getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
}
