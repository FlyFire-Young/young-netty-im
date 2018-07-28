package org.young.base.service;

import io.netty.channel.Channel;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface CoseService {

    Channel start(String name);

    String send(String name, String message);

    String readResposeContent(Object msg);

}
