package com.yuxiang.guli.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: yuxiang
 * @create: 2020-07-07
 * @Description:
 **/
@SpringBootApplication
@ComponentScan("com.yuxiang.guli")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class SpringStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringStatisticsApplication.class, args);
    }
}
