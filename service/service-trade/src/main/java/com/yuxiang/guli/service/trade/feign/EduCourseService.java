package com.yuxiang.guli.service.trade.feign;

import com.yuxiang.guli.service.base.dto.CourseDTO;
import com.yuxiang.guli.service.trade.feign.fallback.EduCourseServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Yuxiang
 * @create: 2020-07-02
 **/
@FeignClient(value = "service-edu", fallback = EduCourseServiceFallBack.class)
public interface EduCourseService {

    @GetMapping("/api/edu/course/inner/get-course-dto/{courseId}")
    public CourseDTO getCourseDTOById(@PathVariable("courseId") String courseId);
}
