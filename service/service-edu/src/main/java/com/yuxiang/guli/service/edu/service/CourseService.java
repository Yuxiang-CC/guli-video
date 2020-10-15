package com.yuxiang.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuxiang.guli.service.base.dto.CourseDTO;
import com.yuxiang.guli.service.edu.entity.Course;
import com.yuxiang.guli.service.edu.entity.form.CourseInfoForm;
import com.yuxiang.guli.service.edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String courseId);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    Page<CourseVO> selectPage(Long page, Long size, CourseQueryVO courseQueryVO);

    void removeCourseCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVO getCoursePublishById(String id);

    /**
     * 发布课程
     * @param id 课程ID
     * @return true 发布成功，false 发布失败
     */
    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVO webCourseQueryVO);

    /**
     * 获取课程信息，并更新浏览数
     * @param id
     * @return
     */
    WebCourseVO getWebCourseVOById(String id);

    List<Course> selectHotCourse();

    CourseDTO getCourseDTOById(String courseId);
}
