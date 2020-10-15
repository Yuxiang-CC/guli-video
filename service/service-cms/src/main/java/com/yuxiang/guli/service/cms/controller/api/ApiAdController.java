package com.yuxiang.guli.service.cms.controller.api;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.cms.entity.Ad;
import com.yuxiang.guli.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yuxiang
 * @create: 2020-06-24
 **/
@Api(description = "广告推荐控制器")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
public class ApiAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("根据前端传递的广告分类查询广告信息")
    @GetMapping("/list/{adTypeId}")
    public R getAdListByAdType(@ApiParam(value = "推荐位ID", required = true) @PathVariable("adTypeId") String adTypeId) {

        List<Ad> adList =  adService.selectByAdTypeId(adTypeId);

        return R.ok().data("items", adList);
    }











    @PostMapping("/save-test")
    public R saveTest(@RequestBody Ad ad) {

        redisTemplate.opsForValue().set("cms:ad:" + ad.getId() , ad);

        return R.ok();
    }
    @GetMapping("/get-test/{adId}")
    public R getTest(@PathVariable("adId") String adId) {

        Ad ad = (Ad) redisTemplate.opsForValue().get("cms:ad:" + adId);

        return R.ok().data("ad", ad);
    }
    @DeleteMapping("/delete-test/{adId}")
    public R deleteTest(@PathVariable("adId") String adId) {
        System.out.println("删除前，是否存在：" +redisTemplate.hasKey(adId));

        Boolean delete = redisTemplate.delete("cms:ad:"  + adId);

        System.out.println("删除后，是否存在：" +redisTemplate.hasKey(adId));

        return R.ok();
    }


}
