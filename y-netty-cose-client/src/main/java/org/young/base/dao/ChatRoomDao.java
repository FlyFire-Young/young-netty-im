package org.young.base.dao;

import org.springframework.stereotype.Repository;
import org.young.base.entity.ChatRoom;
import org.young.base.entity.User;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class ChatRoomDao extends AbstractDao<ChatRoom> {

    public ChatRoom getByUsername(User user) {
        return get("where user = ?", user);
    }
}
