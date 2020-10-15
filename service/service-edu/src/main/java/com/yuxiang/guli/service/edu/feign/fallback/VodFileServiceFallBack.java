package com.yuxiang.guli.service.edu.feign.fallback;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.feign.VodFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-18
 **/
@Service
@Slf4j
public class VodFileServiceFallBack implements VodFileService {
    @Override
    public R remove(String vodId) {
        log.info("远程调用服务失败，触发熔断保护");
        return R.error().message("远程调用服务失败，触发熔断保护");
    }

    @Override
    public R remove(List<String> vodIds) {
        log.info("远程调用服务失败，触发熔断保护");
        return R.error().message("远程调用服务失败，触发熔断保护");
    }
}
