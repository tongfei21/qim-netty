package qim.netty.sdk.model;

import qim.netty.sdk.model.Packet;

import java.time.LocalDateTime;

public class WtsChat extends Packet {
    private Integer id;

    private Integer fromUid;

    private Integer toUid;

    private String content;

    /**
    * 0:text、1:image、2:voice、3:vedio、4:music、5:news
    */
    private byte type;

    /**
    * 0:未知,1:公聊,2:私聊
    */
    private byte chatType;

    /**
    * 群组id
    */
    private Integer groupId;

    private LocalDateTime addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getChatType() {
        return chatType;
    }

    public void setChatType(byte chatType) {
        this.chatType = chatType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}