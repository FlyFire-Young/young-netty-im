package org.young.base.server.handle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.young.base.server.init.SimpleCoseClientInitializer;
import org.young.base.utils.YoungUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SimpleCoseClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCoseClientHandler.class);

    private SimpleCoseClientInitializer simpleCoseClientInitializer;

    public SimpleCoseClientHandler(SimpleCoseClientInitializer simpleCoseClientInitializer) {
        this.simpleCoseClientInitializer = simpleCoseClientInitializer;
    }

    /**
     * 接收服务端发来的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        LOGGER.info(msg);
    }

    /**
     * 接收服务端发来的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info((String) msg);
        cacheData(ctx, (String) msg);
        simpleCoseClientInitializer.setResponse(msg);
//        LOGGER.info("end-----");
    }

    private void cacheData(ChannelHandlerContext ctx, String msg) {
        Channel incomming = ctx.channel();
        Map<String, Channel> channels = YoungUtils.map;
        Map<String, List<String>> msgMap = YoungUtils.msgMap;
        channels.forEach((k, v) -> {
            if (v == incomming) {
                List<String> allMsg = msgMap.get(k);
                if (null != allMsg) {
                    allMsg.add(msg);
                    msgMap.put(k, allMsg);
                } else {
                    allMsg = new ArrayList<>();
                    allMsg.add(msg);
                    msgMap.put(k, allMsg);
                }
                return;
            }
        });

    }
}