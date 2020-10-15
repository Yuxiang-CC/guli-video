package com.yuxiang.guli.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Yuxiang
 * @create: 2020-06-27
 **/
@SpringBootApplication
@ComponentScan("com.yuxiang.guli")
@EnableDiscoveryClient
@EnableFeignClients
public class SpringUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringUcenterApplication.class, args);
    }
}
