package org.young.base.server.handle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
@Sharable
public class SimpleCoseServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * NettyServerListener 日志控制器
     */
    private static final Logger logger = LoggerFactory.getLogger(SimpleCoseServerHandler.class);

    /**
     * 默认所有通道
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 每当服务端收到新的客户端连接时,客户端的channel存入ChannelGroup列表中,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        Channel incomming = ctx.channel();
        channels.add(ctx.channel());
        //通知所有已经连接到服务器的客户端，有一个新的通道加入
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incomming.remoteAddress() + "加入\n");
        }
    }

    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        Channel incomming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incomming.remoteAddress() + "离开\n");
        }
        //从服务端的channelGroup中移除当前离开的客户端
        channels.remove(ctx.channel());
    }

    /**
     * 每当从服务端读到客户端写入信息时,将信息转发给其他客户端的Channel.
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incomming = ctx.channel();
//        将收到的信息转发给全部的客户端channel
        for (Channel channel : channels) {
            if (channel != incomming) {
                channel.writeAndFlush("[" + incomming.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[You]" + msg + "\n");
            }
        }
    }

    /**
     * 服务端监听到客户端活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端上线通知
        Channel incoming = ctx.channel();
        logger.info("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    /**
     * 服务端监听到客户端不活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端掉线通知
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    /**
     * 当服务端的IO 抛出异常时被调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        //异常出现就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
