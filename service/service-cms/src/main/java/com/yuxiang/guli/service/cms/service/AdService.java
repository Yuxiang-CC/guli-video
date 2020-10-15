package com.yuxiang.guli.service.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuxiang.guli.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuxiang.guli.service.cms.entity.vo.AdVO;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-06-23
 */
public interface AdService extends IService<Ad> {

    IPage<AdVO> selectPage(Long page, Long limit);

    boolean removeAdImageById(String id);

    List<Ad> selectByAdTypeId(String adTypeId);
}
