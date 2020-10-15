package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Yuxiang
 * @create: 2020-06-05 08:59
 **/
@Data
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 7886014576527996470L;

    private String id;
    private String title;
    private String teacherName;
    private String subjectParentTitle;
    private String subjectTitle;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;

}
