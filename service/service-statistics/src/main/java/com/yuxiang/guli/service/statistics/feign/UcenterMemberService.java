package com.yuxiang.guli.service.statistics.feign;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.statistics.feign.callback.UcenterMemberServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Yuxiang
 * @create: 2020-07-07
 **/
@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceImpl.class)
public interface UcenterMemberService {

    @GetMapping("/admin/ucenter/member/count/{day}")
    public R countRegisterNumber( @PathVariable("day") String day);
}
