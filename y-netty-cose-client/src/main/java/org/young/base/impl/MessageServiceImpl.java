package org.young.base.impl;

import org.springframework.stereotype.Service;
import org.young.base.entity.Message;
import org.young.base.entity.User;
import org.young.base.enumeration.ChatType;
import org.young.base.enumeration.MsgType;
import org.young.base.lservice.MessageService;

/**
 * User: Young
 * Date: 2018/7/27 0027
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class MessageServiceImpl extends AbstractService implements MessageService {

    @Override
    public void saveContent(User user, Long talkerId, String content, MsgType msgType, ChatType chatType) {
        Message message = new Message();
        message.setContent(content);
        message.setChatType(chatType);
        message.setMsgType(msgType);
        message.setUser(user);
        message.setTalkerId(talkerId);
        messageDao.save(message);
    }
}
