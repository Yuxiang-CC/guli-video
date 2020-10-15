package com.yuxiang.guli.service.cms.feign;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.cms.feign.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Yuxiang
 * @create: 2020-06-23
 **/
@Service
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove-file")
    R removeFile(@RequestBody String url);
}