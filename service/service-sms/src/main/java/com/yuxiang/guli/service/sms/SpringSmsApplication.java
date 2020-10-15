package com.yuxiang.guli.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Yuxiang
 * @create: 2020-06-26
 **/
// 取消数据源的自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.yuxiang.guli")
@EnableDiscoveryClient
public class SpringSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSmsApplication.class, args);
    }
}
