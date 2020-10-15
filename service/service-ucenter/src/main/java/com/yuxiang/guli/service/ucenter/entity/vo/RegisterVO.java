package com.yuxiang.guli.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-27
 **/
@Data
public class RegisterVO implements Serializable {

    private static final long serialVersionUID = 3966986395282320372L;
    private String nickName;
    private String mobile;
    private String password;
    private String code;

}
