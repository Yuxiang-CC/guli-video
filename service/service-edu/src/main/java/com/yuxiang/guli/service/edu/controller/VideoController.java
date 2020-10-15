package com.yuxiang.guli.service.edu.controller;


import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.Video;
import com.yuxiang.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Api(description = "课程视频管理控制类")
@RestController
@RequestMapping("/admin/edu/video")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping("save")
    public R save(@ApiParam(value = "课时对象", required = true)
                  @RequestBody Video video) {

        boolean result = videoService.save(video);
        if (result) {
            return R.ok().message("新增课时成功");
        }
        return R.error().message("新增课时失败");
    }

    @ApiOperation("删除课时")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "课时Id", required = true)
                  @PathVariable("id") String videoId) {

        // 删除视频：阿里VOD
        videoService.removeVodVideoById(videoId);

        boolean result = videoService.removeById(videoId);
        if (result) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation("更新课时")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "课时对象", required = true)
                  @RequestBody Video video) {

        boolean result = videoService.updateById(video);
        if (result) {
            return R.ok().message("更新课时成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation("根据Id查询课时")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "课时Id", required = true)
                        @PathVariable("id") String id) {

        Video video = videoService.getById(id);
        if (video != null) {
            return R.ok().data("item", video);
        }
        return R.error().message("没有该数据");
    }


}
