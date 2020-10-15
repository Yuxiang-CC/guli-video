package com.yuxiang.guli.service.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.sms.config.SmsProperties;
import com.yuxiang.guli.service.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: Yuxiang
 * @create: 2020-06-26
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void sendCode(String phone, String code) throws Exception {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        CommonResponse response = client.getCommonResponse(request);

        // 解析响应结果
        String data = response.getData();
        Gson gson = new Gson();
        HashMap hashMap = gson.fromJson(data, HashMap.class);
        Object resultCode = hashMap.get("Code");
        Object resultMessage = hashMap.get("Message");

        if ("isv.DAY_LIMIT_CONTROL".equals(resultCode)) {
            log.error("短信发送失败,code:" + resultCode + ",message:" + resultMessage);
            throw new GuliException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        if (!"OK".equals(resultCode)) {
            log.error("短信发送失败,code:" + resultCode + ",message:" + resultMessage);
            throw new GuliException(ResultCodeEnum.SMS_SEND_ERROR);
        }

    }

}
