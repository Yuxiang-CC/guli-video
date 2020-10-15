package com.yuxiang.guli.service.edu.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.vo.SubjectVO;
import com.yuxiang.guli.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-21
 **/
@Slf4j
@CrossOrigin
@Api(description = "课程分类管理控制器")
@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("课程类别")
    @GetMapping("nested-list")
    public R nestedList() {

        List<SubjectVO> list = subjectService.nestedList();
        return R.ok().data("items", list);
    }
}
