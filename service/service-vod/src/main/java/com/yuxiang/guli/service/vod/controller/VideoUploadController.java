package com.yuxiang.guli.service.vod.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.ExceptionUtils;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-16
 **/
@Api(description = "课时视频上传控制器")
@CrossOrigin
@RestController
@RequestMapping("/admin/vod/file")
@Slf4j
public class VideoUploadController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public R uploadStream(
            @ApiParam(value = "视频文件流", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().data("videoId", videoId);

        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }

    }

    @ApiOperation("删除视频")
    @DeleteMapping("/remove/{vodId}")
    public R remove(
            @ApiParam(value = "阿里云视频VODId", required = true)
            @PathVariable("vodId") String vodId) {

        try {
            videoService.removeVideo(vodId);
            return R.ok();
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }


    @ApiOperation("批量删除视频")
    @DeleteMapping("/remove")
    public R remove(
            @ApiParam(value = "阿里云视频VODId", required = true)
            @RequestBody List<String> vodIds) {

        try {
            videoService.removeVideoIds(vodIds);
            return R.ok();
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

}
