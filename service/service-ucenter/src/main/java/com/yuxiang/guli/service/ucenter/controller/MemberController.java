package com.yuxiang.guli.service.ucenter.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuxiang
 * @create: 2020-07-06
 * @Description:
 **/
@Api(description = "会员管理控制类")
@RestController
@RequestMapping("/admin/ucenter/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("统计用户每天注册数")
    @GetMapping("count/{day}")
    public R countRegisterNumber(@ApiParam(value = "日期", format = "yyyy-MM-dd") @PathVariable("day") String day) {

        Integer registerNumberByDate = memberService.getRegisterNumberByDate(day);

        return R.ok().data("registerNum", registerNumberByDate);
    }
}
