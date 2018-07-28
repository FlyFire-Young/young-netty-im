package org.young.base.server.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.young.base.server.handle.SimpleCoseClientHandler;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SimpleCoseClientInitializer extends ChannelInitializer<SocketChannel> {

    private Object response;
    private int maxFrameLength;

    public SimpleCoseClientInitializer(int maxFrameLength){
        this.maxFrameLength = maxFrameLength;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(maxFrameLength, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new SimpleCoseClientHandler(this));
    }
}
