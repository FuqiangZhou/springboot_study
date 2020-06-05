package com.zhou.springbootnetty;

import com.zhou.springbootnetty.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringbootNettyApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootNettyApplication.class);

    @Resource
    private NettyServer server;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 8899);
        LOGGER.info("run ... ... {}", "0.0.0.0");
        server.start(address);
    }
}
