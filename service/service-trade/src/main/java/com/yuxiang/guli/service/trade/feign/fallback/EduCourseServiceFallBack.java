package com.yuxiang.guli.service.trade.feign.fallback;

import com.yuxiang.guli.service.base.dto.CourseDTO;
import com.yuxiang.guli.service.trade.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Yuxiang
 * @create: 2020-07-02
 **/
@Component
@Slf4j
public class EduCourseServiceFallBack implements EduCourseService {
    @Override
    public CourseDTO getCourseDTOById(String courseId) {
        log.info("熔断保护");
        return null;
    }
}
