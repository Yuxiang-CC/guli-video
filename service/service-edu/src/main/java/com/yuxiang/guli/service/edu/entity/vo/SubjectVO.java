package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-05-30 09:01
 **/
@Data
public class SubjectVO implements Serializable {

    private static final long serialVersionUID = 7041135244031330543L;

    private String id;

    private String title;

    private Integer sort;

    private List<SubjectVO> children = new ArrayList<>();
}
