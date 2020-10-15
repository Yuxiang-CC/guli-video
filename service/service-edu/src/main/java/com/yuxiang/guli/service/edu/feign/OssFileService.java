package com.yuxiang.guli.service.edu.feign;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.feign.fallback.OssFileServiceFallBack;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @ApiOperation("测试 调用OpenFegin")
    @GetMapping("/admin/oss/file/test")
    R test();

    @ApiOperation("删除讲师头像")
    @DeleteMapping("/admin/oss/file/remove-file")
    R removeFile(String url);
}
