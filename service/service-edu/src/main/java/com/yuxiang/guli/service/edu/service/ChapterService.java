package com.yuxiang.guli.service.edu.service;

import com.yuxiang.guli.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuxiang.guli.service.edu.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    /**
     * 组装章节列表
     * @param courseId 课程Id
     * @return 课程的所有章节
     */
    List<ChapterVO> nestedListById(String courseId);

}
