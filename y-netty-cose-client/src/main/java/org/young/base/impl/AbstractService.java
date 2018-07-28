package org.young.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.young.base.dao.ChatRoomDao;
import org.young.base.dao.MessageDao;
import org.young.base.dao.UserDao;

/**
 * Created by Young on 2017/9/8.
 */
public abstract class AbstractService {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected MessageDao messageDao;

    @Autowired
    protected ChatRoomDao chatRoomDao;
}
