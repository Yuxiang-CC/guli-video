package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-21
 **/
@Data
public class WebCourseQueryVO implements Serializable {

    private static final long serialVersionUID = -2209731539816595745L;

    private String subjectId;
    private String subjectParentId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
    private Integer type;
}
