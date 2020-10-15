package com.yuxiang.guli.service.ucenter.service;

import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuxiang.guli.service.ucenter.entity.vo.LoginVO;
import com.yuxiang.guli.service.ucenter.entity.vo.RegisterVO;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-06-27
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVO registerVO);

    String login(LoginVO loginVO);

    MemberDTO getMemberDTOById(String memberId);

    Integer getRegisterNumberByDate(String date);
}
