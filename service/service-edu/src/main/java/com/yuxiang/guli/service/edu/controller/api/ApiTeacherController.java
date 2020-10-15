package com.yuxiang.guli.service.edu.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.Teacher;
import com.yuxiang.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: Yuxiang
 * @create: 2020-06-20
 **/
@Api(description = "前台讲师管理控制器")
@RestController
@RequestMapping("/api/edu/teacher")
@CrossOrigin
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll() {

        List<Teacher> teacherList = teacherService.list();

        return R.ok().data("items", teacherList).message("获取所有讲师列表成功");
    }

    @ApiOperation("根据ID获取讲师信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam("讲师对象信息") @PathVariable("id") String id) {

        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        if (map != null) {
            return R.ok().data(map);
        }

        return R.error().message("数据不存在");
    }

}
