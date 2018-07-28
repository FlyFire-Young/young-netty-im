package org.young.base.entity;

import org.young.base.enumeration.ChatType;
import org.young.base.enumeration.MsgType;

import javax.persistence.*;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
public class Message extends AbstractEntity {

    /**
     * 发送消息的用户
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 消息类型
     */
    @Enumerated(EnumType.STRING)
    private MsgType msgType;


    /**
     * 聊天类型
     */
    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    /**
     * 聊天对象为一对一时用户的id,聊天对象为群组时为群组id
     */
    private Long talkerId;

    /**
     * 消息内容
     */
    private String content;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }


    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public Long getTalkerId() {
        return talkerId;
    }

    public void setTalkerId(Long talkerId) {
        this.talkerId = talkerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
