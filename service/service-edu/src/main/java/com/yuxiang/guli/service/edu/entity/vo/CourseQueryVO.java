package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yuxiang
 * @create: 2020-06-05 08:55
 **/
@Data
public class CourseQueryVO implements Serializable {

    private String title;
    private String teacherId;
    private String subjectId;
    private String subjectParentId;
}
