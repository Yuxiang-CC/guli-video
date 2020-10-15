package com.yuxiang.guli.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.ExceptionUtils;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.vod.config.VodProperties;
import com.yuxiang.guli.service.vod.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-16
 **/
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;


    @Override
    public String uploadVideo(InputStream inputStream, String originalFileName) {

        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        UploadStreamRequest request =
                new UploadStreamRequest(vodProperties.getAccessKeyId(), vodProperties.getAccessKeySecret(), title, originalFileName, inputStream);

        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();

        if (StringUtils.isBlank(videoId)) {
            log.error("阿里云上传视频失败,code:" + response.getCode() + ",message:" + response.getMessage());
            throw new GuliException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

        return videoId;
    }

    @Override
    public void removeVideo(String videoIds) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", vodProperties.getAccessKeyId(), vodProperties.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);

        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoIds);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
        }
        log.info("remove RequestId = " + response.getRequestId());
    }

    @Override
    public void removeVideoIds(List<String> vodIds) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", vodProperties.getAccessKeyId(), vodProperties.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);

        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            StringBuffer idsStr = new StringBuffer(); // 组装好的字符串
            int idsSize = vodIds.size(); // vodVideoId数量

            for (int i = 0; i < idsSize; i++) {
                idsStr.append(vodIds.get(i));
                // 如果是最后一个id，则直接发送删除请求
                if (i == idsSize - 1 || i % 20 == 19) {
                    log.info("vodVideoIds:" + idsStr.toString());
                    // 删除请求
                    request.setVideoIds(idsStr.toString());
                    response = client.getAcsResponse(request);
                    // 清空idsStr
                    idsStr = new StringBuffer();
                } else if (i % 20 < 19) {
                    idsStr.append(",");
                }
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        log.info("remove RequestId = " + response.getRequestId());
    }

    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", vodProperties.getAccessKeyId(), vodProperties.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();//创建请求对象
        request.setVideoId(videoSourceId);//设置请求参数

        GetVideoPlayAuthResponse response = client.getAcsResponse(request);//发送请求得到响应

        return response.getPlayAuth();
    }
}
