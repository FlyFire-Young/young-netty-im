package org.young.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.young.base.lservice.ChatRoomService;
import org.young.base.lservice.MessageService;
import org.young.base.lservice.UserService;

/**
 * User: Young
 * Date: 2018/7/25 0025
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public abstract class AbstractController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected ChatRoomService chatRoomService;
}
