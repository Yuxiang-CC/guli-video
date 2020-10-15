package com.yuxiang.guli.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Yuxiang
 * @create: 2020-07-02
 **/
@SpringBootApplication
@ComponentScan("com.yuxiang.guli")
@EnableDiscoveryClient
@EnableFeignClients
public class SpringTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTradeApplication.class, args);
    }
}
