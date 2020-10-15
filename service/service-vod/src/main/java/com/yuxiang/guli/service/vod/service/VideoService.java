package com.yuxiang.guli.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-16
 **/
public interface VideoService {

    String uploadVideo(InputStream inputStream, String fileName);

    void removeVideo(String videoIds);

    void removeVideoIds(List<String> vodIds);

    String getPlayAuth(String videoSourceId) throws ClientException;
}
