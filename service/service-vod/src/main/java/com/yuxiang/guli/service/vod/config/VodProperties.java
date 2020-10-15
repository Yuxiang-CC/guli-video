package com.yuxiang.guli.service.vod.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Yuxiang
 * @create: 2020-06-16
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String templateGroupId;
    private String workflowId;
}
