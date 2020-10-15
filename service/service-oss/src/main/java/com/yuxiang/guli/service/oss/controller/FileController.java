package com.yuxiang.guli.service.oss.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.ExceptionUtils;
import com.yuxiang.guli.service.base.exception.GuliException;
import com.yuxiang.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api("阿里云文件管理")
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public R uploadImg(
            @ApiParam("文件流") @RequestParam("file") MultipartFile file,
            @ApiParam("文件夹") @RequestParam("module") String module) {

        String uploadUrl = null;
        try {
            uploadUrl = fileService.upload(file.getInputStream(), module, file.getOriginalFilename());
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }

    @ApiOperation("删除图像文件")
    @DeleteMapping("/remove-file")
    public R removeFile(
            @ApiParam(value = "图片url", required = true)
            @RequestBody String url) {

        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation("测试 OpenFegin")
    @GetMapping("/test")
    public R test() {

        System.out.println("test 被调用 ");
        return R.ok().message("测试 OpenFegin");
    }


}
