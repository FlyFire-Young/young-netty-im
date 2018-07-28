package org.young.base.lservice;

import org.young.base.entity.ChatRoom;
import org.young.base.entity.User;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface ChatRoomService {

    /**
     * 查询群组
     * @param id
     * @return
     */
    ChatRoom getById(Long id);

    ChatRoom checkRoom(User user);
}
