package org.young.base.server.listener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.young.base.config.ServerConfig;
import org.young.base.server.init.SimpleCoseServerInitializer;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class SimpleCoseServer {

    /**
     * NettyServerListener 日志控制器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCoseServer.class);

    /**
     * boss用来接收进来的连接
     */
    private EventLoopGroup boss = new NioEventLoopGroup();

    /**
     * //用来处理已经被接收的连接;
     */
    private EventLoopGroup work = new NioEventLoopGroup();

    /**
     * 是一个启动NIO服务的辅助启动类
     */
    private ServerBootstrap bootstrap = new ServerBootstrap();

    @Resource
    private SimpleCoseServerInitializer simpleCoseServerInitializer;

    /**
     * 配置文件
     */
    @Resource
    private ServerConfig serverConfig;

    /**
     * 关闭服务器方法
     */
    @PreDestroy
    public void close() {
        LOGGER.info("关闭服务器....");
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    public void start() throws Exception{
        try{
            int port = serverConfig.getPort();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(simpleCoseServerInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            LOGGER.info("SimpleChatServer 启动了");

            //绑定端口,开始接收进来的连接
            LOGGER.info("port ___________:{}", port);
            ChannelFuture future = bootstrap.bind(port).sync();

            //等待服务器socket关闭
            future.channel().closeFuture().sync();
        }  catch (InterruptedException e) {
            LOGGER.info("SimpleChatServer 关闭了");
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
