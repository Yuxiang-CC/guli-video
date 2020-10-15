package com.yuxiang.guli.service.trade.feign.fallback;

import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.trade.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Yuxiang
 * @create: 2020-07-02
 **/
@Slf4j
@Component
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public MemberDTO getMemberDTOById(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
