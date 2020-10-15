package com.yuxiang.guli.service.cms.feign.fallback;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.cms.feign.OssFileService;
import org.springframework.stereotype.Service;

/**
 * @author: Yuxiang
 * @create: 2020-06-23
 **/
@Service
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R removeFile(String url) {
        return R.error().message("调用Oss失败，触发熔断");
    }
}
