package org.young.base.lservice;

import org.young.base.entity.User;
import org.young.base.enumeration.ChatType;
import org.young.base.enumeration.MsgType;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface MessageService {

    /**
     * 保存信息
     * @param content
     * @param msgType
     * @param chatType
     */
    void saveContent(User user,Long talkerId, String content, MsgType msgType, ChatType chatType);
}
