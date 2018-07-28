package org.young.base;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.young.base.server.listener.SimpleCoseServer;

import javax.annotation.Resource;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SpringBootApplication
public class CoseServerApplication implements CommandLineRunner {

    @Resource
    private SimpleCoseServer simpleCoseServer;

    public static void main(String[] args) {
        SpringApplication.run(CoseServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        simpleCoseServer.start();
    }
}
