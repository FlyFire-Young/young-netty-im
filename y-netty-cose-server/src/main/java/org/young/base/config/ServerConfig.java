package org.young.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class ServerConfig {

    /**
     * 端口
     */
    private int port;
    /**
     * 最大线程数
     */
    private int maxThreads;
    /**
     * 最大数据包长度
     */
    private int maxFrameLength;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMaxFrameLength() {
        return maxFrameLength;
    }

    public void setMaxFrameLength(int maxFrameLength) {
        this.maxFrameLength = maxFrameLength;
    }
}
