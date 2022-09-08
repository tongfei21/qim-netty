package qim.netty.server.model;

import qim.netty.sdk.model.Packet;

import java.time.LocalDateTime;

public class WtsUser extends Packet {
    private Integer id;

    /**
    * 用户名
    */
    private String username;

    private String password;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 头像照
    */
    private String headImg;

    /**
    * 头像完整照
    */
    private String headFullImg;

    /**
    * 姓别
    */
    private Boolean sex;

    /**
    * 生日
    */
    private LocalDateTime birthday;

    /**
    * 简介
    */
    private String brief;

    /**
    * 1:用户，2：咨询师
    */
    private byte type;

    /**
    * 0：账户正常，1：停用，2：封号
    */
    private byte status;

    /**
    * 添加时间
    */
    private LocalDateTime addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHeadFullImg() {
        return headFullImg;
    }

    public void setHeadFullImg(String headFullImg) {
        this.headFullImg = headFullImg;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public LocalDateTime getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime addtime) {
        this.addtime = addtime;
    }
}