package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-07 15:04
 **/
@Data
public class VideoVO implements Serializable {

    private static final long serialVersionUID = -6872612444333869032L;

    private String id;
    private String title;
    private Boolean free; // 是否免费
    private Integer sort;

    private String videoSourceId; // 阿里云VOD唯一ID
}
