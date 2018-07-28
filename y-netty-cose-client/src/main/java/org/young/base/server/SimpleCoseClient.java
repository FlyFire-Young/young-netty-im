package org.young.base.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.young.base.server.init.SimpleCoseClientInitializer;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SimpleCoseClient {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCoseClient.class);

    private Bootstrap bootstrap;
    private EventLoopGroup worker;
    private int port;
    private String url;
    private int maxFrameLength;
    private int MAX_RETRY_TIMES = 10;
    private Channel channel;

    public SimpleCoseClient(String url, int port) {
        this.url = url;
        this.port = port;
        this.maxFrameLength = 65535;
        bootstrap = new Bootstrap();
        worker = new NioEventLoopGroup();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class);
    }

    public void close() {
        LOGGER.info("关闭资源");
        worker.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void run(int retry) throws Exception {
        try {
            SimpleCoseClientInitializer customChannelInitializer = new SimpleCoseClientInitializer(this.maxFrameLength);
            bootstrap.handler(customChannelInitializer);
            ChannelFuture sync = bootstrap.connect(url, port).sync();
            this.channel = sync.channel();
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            while (true){
//                sync.channel().writeAndFlush(in.readLine()+"\r\n");
//            }
            return;
        } catch (InterruptedException e) {
            retry++;
            if (retry > MAX_RETRY_TIMES) {
                throw new RuntimeException("调用Wrong");
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                LOGGER.info("第{}次尝试....失败", retry);
            }
        }
    }
}
