package com.yuxiang.guli.service.ucenter.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.JWTUtils;
import com.yuxiang.guli.common.base.util.JwtInfo;
import com.yuxiang.guli.service.base.dto.MemberDTO;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.ucenter.entity.vo.LoginVO;
import com.yuxiang.guli.service.ucenter.entity.vo.RegisterVO;
import com.yuxiang.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-06-27
 */
@Api(description = "会员管理控制类")
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO) {

        memberService.register(registerVO);
        return R.ok().message("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO) {

        String token = memberService.login(loginVO);
        return R.ok().data("token", token);
    }

    @ApiOperation("根据token获取登录信息")
    @GetMapping("/get-login-info")
    public R getLoginInfo(HttpServletRequest request) {

        try {
            JwtInfo infoFromJWT = JWTUtils.getInfoFromJWT(request);
            return R.ok().data("userInfo", infoFromJWT);
        } catch (Exception e) {
            log.error("解析用户信息失败," + e.getMessage());
            throw new GuliException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation("根据会员ID查询会员信息")
    @GetMapping("/inner/get-member-dto/{memberId}")
    public MemberDTO getMemberDTOById(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable("memberId") String memberId
    ) {
        return memberService.getMemberDTOById(memberId);
    }

}

