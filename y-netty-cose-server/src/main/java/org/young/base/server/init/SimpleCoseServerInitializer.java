package org.young.base.server.init;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;
import org.young.base.config.ServerConfig;
import org.young.base.server.handle.SimpleCoseServerHandler;

import javax.annotation.Resource;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
@ChannelHandler.Sharable
public class SimpleCoseServerInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private ServerConfig serverConfig;

    @Resource
    private SimpleCoseServerHandler simpleCoseServerHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(serverConfig.getMaxFrameLength(), Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", simpleCoseServerHandler);
        System.out.println("SimpleChatClient:" + ch.remoteAddress() + "连接上服务器");
    }
}
