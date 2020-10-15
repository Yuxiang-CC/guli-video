package com.yuxiang.guli.service.edu.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.ExceptionUtils;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.edu.entity.vo.SubjectVO;
import com.yuxiang.guli.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Slf4j
@Api(description = "课程分类管理控制器")
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("import")
    public R batchImport(@ApiParam(value = "Excel文件流", required = true) @RequestParam("file") MultipartFile file) {
        try {
            subjectService.batchImport(file.getInputStream());
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
        return R.ok().message("批量导入成功");
    }

    @ApiOperation("获取课程分类信息")
    @GetMapping("nested-list")
    public R nestedList() {
        List<SubjectVO> list = subjectService.nestedList();
        return R.ok().data("items", list);
    }
}

