package com.yuxiang.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.Course;
import com.yuxiang.guli.service.edu.entity.Teacher;
import com.yuxiang.guli.service.edu.entity.vo.TeacherQueryVO;
import com.yuxiang.guli.service.edu.feign.OssFileService;
import com.yuxiang.guli.service.edu.mapper.CourseMapper;
import com.yuxiang.guli.service.edu.mapper.TeacherMapper;
import com.yuxiang.guli.service.edu.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Autowired
    private OssFileService ossFileService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVO teacherQueryVO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
//        1、排序：按照sort字段升序排序
        queryWrapper.orderByAsc("sort");

//        2、分页查询
        if (teacherQueryVO == null) {
            return baseMapper.selectPage(pageParam, queryWrapper);
        }
//        3、条件查询
        String name = teacherQueryVO.getName();
        Integer level = teacherQueryVO.getLevel();
        String joinDateBegin = teacherQueryVO.getJoinDateBegin();
        String joinDateEnd = teacherQueryVO.getJoinDateEnd();

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.likeRight("name", name);
        }

        if (level != null) {
            queryWrapper.eq("level", level);
        }

        if (StringUtils.isNotBlank(joinDateBegin)) {
            queryWrapper.ge("join_date", joinDateBegin);
        }

        if (StringUtils.isNotBlank(joinDateEnd)) {
            queryWrapper.le("join_date", joinDateEnd);
        }

        System.out.println(queryWrapper);

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String nameKey) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name").likeRight("name", nameKey);
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return maps;
    }

    @Override
    public boolean removeAvatarById(String id) {
        // 根据id 获取讲师图像rul
        Teacher teacher = baseMapper.selectById(id);
        if (teacher == null) {
            return false;
        }
        String avatar = teacher.getAvatar();
        if (StringUtils.isNotBlank(avatar)) {
            R r = ossFileService.removeFile(avatar);
            System.out.println(r.getMessage());
            return r.getSuccess();
        }
        return false;
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(final String id) {
        Teacher teacher = baseMapper.selectById(id);

        // 查询讲师发表的课程
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", id);
        List<Course> courses = courseMapper.selectList(queryWrapper);

        Map<String, Object> maps = new HashMap<>();
        maps.put("teacher", teacher);
        maps.put("courseList", courses);

        return maps;
    }

    @Cacheable(value = "hot", key = "'teacher'")
    @Override
    public List<Teacher> selectHotTeacher() {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        queryWrapper.last("limit 4");

        return baseMapper.selectList(queryWrapper);
    }
}
