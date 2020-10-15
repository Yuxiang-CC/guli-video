package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Yuxiang
 * @create: 2020-06-07 08:44
 **/
@Data
public class CoursePublishVO implements Serializable {

    private static final long serialVersionUID = -9103325713540872921L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private BigDecimal price;
}
