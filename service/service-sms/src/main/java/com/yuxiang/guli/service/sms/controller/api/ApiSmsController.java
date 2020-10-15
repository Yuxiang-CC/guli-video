package com.yuxiang.guli.service.sms.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.FormUtils;
import com.yuxiang.guli.common.base.util.RandomUtils;
import com.yuxiang.guli.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author: Yuxiang
 * @create: 2020-06-26
 **/
@RestController
@Api("阿里云短信管理")
@RequestMapping("/api/sms")
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send-code/{phone}")
    public R getCode(@PathVariable("phone") String phone) throws Exception {

        // 1.校验手机号是否合法
        if (StringUtils.isBlank(phone) || !FormUtils.isMobile(phone)) {
            log.error("手机号不正确");
            return R.setResult(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        // 2.生成验证码
        String sixCode = RandomUtils.getSixBitRandom();

        // 3.发送验证码
//        smsService.sendCode(phone, sixCode);

        // 4.存入数据库
        redisTemplate.opsForValue().set("sms:" + phone, sixCode, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");

    }
}
