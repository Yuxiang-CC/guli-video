package com.yuxiang.guli.service.edu.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.base.dto.CourseDTO;
import com.yuxiang.guli.service.edu.entity.Course;
import com.yuxiang.guli.service.edu.entity.vo.ChapterVO;
import com.yuxiang.guli.service.edu.entity.vo.WebCourseQueryVO;
import com.yuxiang.guli.service.edu.entity.vo.WebCourseVO;
import com.yuxiang.guli.service.edu.service.ChapterService;
import com.yuxiang.guli.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-21
 **/
@Api(description = "前台课程管理控制器")
@RestController
@RequestMapping("/api/edu/course")
@CrossOrigin
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("课程列表")
    @GetMapping("list")
    public R pageList(
            @ApiParam(value = "查询对象", required = true)
            WebCourseQueryVO webCourseQueryVO
    ) {

        List<Course> courseList = courseService.webSelectList(webCourseQueryVO);

        return R.ok().data("courseList", courseList);
    }

    @ApiOperation("课程信息")
    @GetMapping("get/{id}")
    public R courseInfo(
            @ApiParam(value = "查询对象", required = true)
            @PathVariable("id") String id
    ) {

        WebCourseVO webCourseVOBy = courseService.getWebCourseVOById(id);

        // 获取视频章节列表
        List<ChapterVO> chapterVOList = chapterService.nestedListById(id);

        return R.ok().data("course", webCourseVOBy).data("chapterVoList", chapterVOList);
    }

    @ApiOperation("根据课程ID查询课程信息")
    @GetMapping("inner/get-course-dto/{courseId}")
    public CourseDTO getCourseDTOById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable("courseId") String courseId
    ) {

        return courseService.getCourseDTOById(courseId);

    }


}
