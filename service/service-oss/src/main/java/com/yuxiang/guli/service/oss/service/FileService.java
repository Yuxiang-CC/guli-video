package com.yuxiang.guli.service.oss.service;

import java.io.InputStream;

public interface FileService {
    /**
     *
     * @param inputStream 客户端文件流
     * @param model 上传到某个文件夹
     * @param originalFileName 文件名字
     * @return 文件访问路径
     */
    String upload(InputStream inputStream, String model, String originalFileName);

    /**
     * 阿里云文件删除
     * @param url 图片Url地址
     */
    void removeFile(String url);
}
