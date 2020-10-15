package com.yuxiang.guli.service.sms.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author: Yuxiang
 * @create: 2020-06-26
 **/
public interface SmsService {

    void sendCode(String phone, String code) throws ClientException, Exception;
}
