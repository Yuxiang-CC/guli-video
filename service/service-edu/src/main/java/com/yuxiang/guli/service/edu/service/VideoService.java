package com.yuxiang.guli.service.edu.service;

import com.yuxiang.guli.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
public interface VideoService extends IService<Video> {


    void removeVodVideoByCourseId(String courseId);

    void removeVodVideoById(String videoId);

    void removeVodVideoByChapterId(String id);
}
