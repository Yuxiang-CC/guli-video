package com.yuxiang.guli.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxiang.guli.service.base.dto.CourseDTO;
import com.yuxiang.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuxiang.guli.service.edu.entity.vo.CoursePublishVO;
import com.yuxiang.guli.service.edu.entity.vo.CourseVO;
import com.yuxiang.guli.service.edu.entity.vo.WebCourseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVO> selectPageByCourseQueryVO(Page<CourseVO> pageParam,
                                             @Param(Constants.WRAPPER) QueryWrapper<Course> queryWrapper);

    CoursePublishVO selectCoursePublishById(@Param("id") String id);


    WebCourseVO selectWebCourseVOById(String id);

    CourseDTO selectCourseDTOById(String courseId);
}
