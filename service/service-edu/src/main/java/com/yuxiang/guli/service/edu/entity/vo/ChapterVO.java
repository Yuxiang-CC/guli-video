package com.yuxiang.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-07 15:05
 **/
@Data
public class ChapterVO implements Serializable {

    private static final long serialVersionUID = -7373401113633844276L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVO> children = new ArrayList<>();
}
