package org.young.base.entity;

import javax.persistence.*;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
public class ChatRoom extends AbstractEntity {

    /**
     * 聊天室名称
     */
    String chat_room_name;

    /**
     * 群昵称
     */
    String chat_room_nick;

    /**
     * 群组成员id【中文顿号分割】
     */
    String member_list;

    /**
     * 群成员昵称列表【中文顿号分割】
     */
    String display_name_list;

    /**
     * 群主
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 群成员昵称列表【中文顿号分割】（自定义昵称）
     */
    String self_display_name;

    public String getChat_room_name() {
        return chat_room_name;
    }

    public void setChat_room_name(String chat_room_name) {
        this.chat_room_name = chat_room_name;
    }

    public String getChat_room_nick() {
        return chat_room_nick;
    }

    public void setChat_room_nick(String chat_room_nick) {
        this.chat_room_nick = chat_room_nick;
    }

    public String getMember_list() {
        return member_list;
    }

    public void setMember_list(String member_list) {
        this.member_list = member_list;
    }

    public String getDisplay_name_list() {
        return display_name_list;
    }

    public void setDisplay_name_list(String display_name_list) {
        this.display_name_list = display_name_list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSelf_display_name() {
        return self_display_name;
    }

    public void setSelf_display_name(String self_display_name) {
        this.self_display_name = self_display_name;
    }
}
