package com.yuxiang.guli.service.cms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-23
 **/
@Data
public class AdVO implements Serializable {

    private static final long serialVersionUID = 2395045117531148517L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}
