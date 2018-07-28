package org.young.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.young.base.server.SimpleCoseClient;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class ServerConfig {

    @Bean
    public NettyBeanScanner initNettyBeanScanner(@Value("${netty.basePackage}") String basePackage,
                                                 @Value("${netty.clientName}") String clientName) {
        return new NettyBeanScanner(basePackage, clientName);
    }

    @Bean("nettyClient")
    public SimpleCoseClient initNettyClient(@Value("${netty.url}") String url, @Value("${netty.port}") int port) {
        return new SimpleCoseClient(url, port);
    }
}
