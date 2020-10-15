package com.yuxiang.guli.service.edu.feign.fallback;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        log.info("远程调用服务失败，触发熔断保护");
        return R.error().message("远程调用服务失败，触发熔断保护");
    }

    @Override
    public R removeFile(String url) {
        log.info("远程删除文件服务，触发熔断保护");
        return R.error().message("删除失败");
    }
}
