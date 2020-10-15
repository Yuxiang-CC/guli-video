package com.yuxiang.guli.service.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.form.CourseInfoForm;
import com.yuxiang.guli.service.edu.entity.vo.CoursePublishVO;
import com.yuxiang.guli.service.edu.entity.vo.CourseQueryVO;
import com.yuxiang.guli.service.edu.entity.vo.CourseVO;
import com.yuxiang.guli.service.edu.service.CourseService;
import com.yuxiang.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@CrossOrigin
@Api(description = "课程管理控制器")
@RestController
@RequestMapping("/admin/edu/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;


    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId", courseId).message("保存成功");
    }

    @ApiOperation("根据ID查询课程信息")
    @GetMapping("course-info/{courseId}")
    public R getCourseInfoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable("courseId") String courseId) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(courseId);
        if (courseInfoForm != null)
            return R.ok().data("courseInfo", courseInfoForm);

        return R.error().message("数据不存在:" + courseId);
    }

    @ApiOperation("根据ID更新课程信息")
    @PutMapping("update-course-info")
    public R updateCourseInfoById(
            @ApiParam(value = "课程信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {
        courseService.updateCourseInfoById(courseInfoForm);

        return R.ok().message("更新成功");
    }

    @ApiOperation(value = "分页获取课程列表")
    @GetMapping("/list/{page}/{size}")
    public R getListPage(@ApiParam(value = "当前页码", required = true) @PathVariable("page") Long page,
                      @ApiParam(value = "页面大小", required = true) @PathVariable("size") Long size,
                      @ApiParam("课程列表查询对象") CourseQueryVO courseQueryVO) {

        Page<CourseVO> pageModelList = courseService.selectPage(page, size, courseQueryVO);

        return R.ok().data("items", pageModelList.getRecords())
                .data("total", pageModelList.getTotal());
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("/remove/{id}")
    public R removeCourseById(@ApiParam("课程ID") @PathVariable("id") String id) {

        // 删除课程视频
        videoService.removeVodVideoByCourseId(id);

        // 删除课程封面
        courseService.removeCourseCoverById(id);

        boolean result = courseService.removeCourseById(id);

        if (result)
            return R.ok().message("删除成功");

        return R.error().message("删除失败");
    }

    @ApiOperation(value = "根据ID获取课程的发布信息")
    @GetMapping("course-publish/{id}")
    public R getCoursePublishId(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable("id") String id) {

        CoursePublishVO coursePublishVO = courseService.getCoursePublishById(id);
        if (coursePublishVO != null) {
            return R.ok().data("item", coursePublishVO);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation(value = "根据ID发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable("id") String id) {

        boolean result = courseService.publishCourseById(id);
        if (result) {
            return R.ok().message("发布成功");
        }
        return R.error().message("发布失败");
    }

}
