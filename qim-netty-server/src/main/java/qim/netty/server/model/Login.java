package qim.netty.server.model;

import qim.netty.sdk.model.Packet;

public class Login extends Packet {

    private String username;
    private String password;
    private String reqpwd;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReqpwd() {
        return reqpwd;
    }

    public void setReqpwd(String reqpwd) {
        this.reqpwd = reqpwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
