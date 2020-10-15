package com.yuxiang.guli.service.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Yuxiang
 * @create: 2020-06-26
 **/
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties {

    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateCode;
    private String signName;
}
