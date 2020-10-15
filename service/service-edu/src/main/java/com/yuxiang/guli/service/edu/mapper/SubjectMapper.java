package com.yuxiang.guli.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuxiang.guli.service.edu.entity.Subject;
import com.yuxiang.guli.service.edu.entity.vo.SubjectVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVO> selectNestedSByParentId(String parentId);
}
