package com.yuxiang.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxiang.guli.service.edu.entity.Video;
import com.yuxiang.guli.service.edu.feign.VodFileService;
import com.yuxiang.guli.service.edu.mapper.VideoMapper;
import com.yuxiang.guli.service.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Autowired
    private VodFileService vodFileService;

    @Override
    public void removeVodVideoByCourseId(String courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("video_source_id");
        queryWrapper.eq("course_id", courseId);
        List<Video> videos = baseMapper.selectList(queryWrapper);

        vodFileService.remove(getVideoIdsFromVideos(videos));
    }

    @Override
    public void removeVodVideoById(String videoId) {

        // 根据id找到视频对应的阿里vod视频id
        Video video = baseMapper.selectById(videoId);
        log.warn("VideoServiceImpl.removeVodVideo:" + video.getVideoSourceId());

        vodFileService.remove(video.getVideoSourceId());
    }

    @Override
    public void removeVodVideoByChapterId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("video_source_id");
        queryWrapper.eq("chapter_id", id);
        List<Video> videos = baseMapper.selectList(queryWrapper);

        vodFileService.remove(getVideoIdsFromVideos(videos));
    }

    // 将获取到的Video集合遍历拿出每一个的videoSourceId并返回
    private List<String> getVideoIdsFromVideos(List<Video> videos) {
        List<String> collect = videos.stream().map(Video::getVideoSourceId).collect(Collectors.toList());
        log.warn("VideoServiceImpl.removeVodVideoByChapterId:" + collect);
        return collect;
    }
}
