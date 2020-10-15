package com.yuxiang.guli.service.trade.feign;

import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.trade.feign.fallback.UcenterMemberServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Yuxiang
 * @create: 2020-07-02
 **/
@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBack.class)
public interface UcenterMemberService {

    @GetMapping("/api/ucenter/member/inner/get-member-dto/{memberId}")
    MemberDTO getMemberDTOById(@PathVariable("memberId") String memberId);
}
