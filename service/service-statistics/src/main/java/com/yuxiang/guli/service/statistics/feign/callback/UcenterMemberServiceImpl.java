package com.yuxiang.guli.service.statistics.feign.callback;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: yuxiang
 * @create: 2020-07-07
 * @Description:
 **/
@Component
@Slf4j
public class UcenterMemberServiceImpl implements UcenterMemberService {
    @Override
    public R countRegisterNumber(String day) {
        log.error("统计注册人数远程服务熔断被调用");
        return R.ok().data("registerNum", 0);
    }
}
