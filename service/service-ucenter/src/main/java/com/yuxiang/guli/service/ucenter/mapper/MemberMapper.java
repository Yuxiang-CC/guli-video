package com.yuxiang.guli.service.ucenter.mapper;

import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Yuxiang
 * @since 2020-06-27
 */
public interface MemberMapper extends BaseMapper<Member> {

    MemberDTO selectMemberDTOById(String memberId);

    Integer selectRegisterNumberByDate(String date);
}
