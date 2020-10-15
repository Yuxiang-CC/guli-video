package com.yuxiang.guli.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.FormUtils;
import com.yuxiang.guli.common.base.util.JWTUtils;
import com.yuxiang.guli.common.base.util.MD5;
import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.ucenter.entity.Member;
import com.yuxiang.guli.service.ucenter.entity.vo.LoginVO;
import com.yuxiang.guli.service.ucenter.entity.vo.RegisterVO;
import com.yuxiang.guli.service.ucenter.mapper.MemberMapper;
import com.yuxiang.guli.service.ucenter.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-06-27
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void register(RegisterVO registerVO) {

        // 验证参数
        String nickName = registerVO.getNickName();
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();
        if (StringUtils.isBlank(mobile) || !FormUtils.isMobile(mobile)) {

            throw new GuliException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        if (StringUtils.isBlank(nickName)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(code)) {

            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        // 校验验证码
        String getCode = (String) redisTemplate.opsForValue().get("sms:" + registerVO.getMobile());
        if (!code.equals(getCode)) {

            throw new GuliException(ResultCodeEnum.CODE_ERROR);
        }

        // 判断用户是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer checkMember = baseMapper.selectCount(queryWrapper);
        if (checkMember > 0) throw new GuliException(ResultCodeEnum.REGISTER_MOBLE_ERROR);

        // 清除注册码
//        redisTemplate.delete("sms:" + registerVO.getMobile());

        // 注册
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickName);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("https://video-guli.oss-cn-beijing.aliyuncs.com/avatar/yuxin.png");
        member.setDisabled(false);
        baseMapper.insert(member);

    }

    @Override
    public String login(LoginVO loginVO) {
        if (loginVO == null) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        // 校验参数是否为空
        if (StringUtils.isBlank(mobile)
                || StringUtils.isBlank(password)
                || !FormUtils.isMobile(mobile)) {
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
        // 校验手机号是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new GuliException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        // 校验密码是否正确
        if (!member.getPassword().equals(MD5.encrypt(password))) {
            throw new GuliException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        // 校验用户是否被封禁
        if (member.getDisabled()) {
            throw new GuliException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        // 生成Token
        String jwtToken = JWTUtils.genJwt(member.getId(), member.getNickname(), member.getAvatar(), 1000 * 60 * 30);

        return jwtToken;
    }

    @Override
    public MemberDTO getMemberDTOById(String memberId) {
        Member member = baseMapper.selectById(memberId);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setMobile(member.getMobile());
        memberDTO.setNickname(member.getNickname());
        return memberDTO;
    }

    @Override
    public Integer getRegisterNumberByDate(String date) {
        return baseMapper.selectRegisterNumberByDate(date);
    }
}
