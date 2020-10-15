package com.yuxiang.guli.service.edu.feign;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.feign.fallback.VodFileServiceFallBack;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-18
 **/
@FeignClient(value = "service-vod", fallback = VodFileServiceFallBack.class)
public interface VodFileService {


    @ApiOperation("远程调用删除视频")
    @DeleteMapping("/admin/vod/file/remove/{vodId}")
    R remove(@PathVariable("vodId") String vodId);

    @ApiOperation("批量删除视频")
    @DeleteMapping("/admin/vod/file/remove")
    R remove(@RequestBody List<String> vodIds);

}
