package com.yuxiang.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-28
 **/
@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 81675564181045596L;
    private String mobile;
    private String password;

}
