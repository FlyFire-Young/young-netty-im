package org.young.base.impl;

import org.springframework.stereotype.Service;
import org.young.base.entity.ChatRoom;
import org.young.base.entity.User;
import org.young.base.lservice.ChatRoomService;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class ChatRoomServiceImpl extends AbstractService implements ChatRoomService {

    @Override
    public ChatRoom getById(Long id) {
        return chatRoomDao.get(id);
    }

    @Override
    public ChatRoom checkRoom(User user) {
        chatRoomDao.getByUsername(user);
        return null;
    }
}
