package com.yuxiang.guli.service.edu.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.Chapter;
import com.yuxiang.guli.service.edu.entity.vo.ChapterVO;
import com.yuxiang.guli.service.edu.service.ChapterService;
import com.yuxiang.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@CrossOrigin
@Api(description = "课程章节管理控制器")
@RestController
@RequestMapping("/admin/edu/chapter")
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    @ApiOperation("新增章节")
    @PostMapping("save")
    public R save(@ApiParam(value = "章节对象", required = true)
                  @RequestBody Chapter chapter) {

        boolean result = chapterService.save(chapter);

        if (result) {
            return R.ok().message("添加章节成功");
        } else {
            return R.error().message("添加章节失败");
        }
    }

    @ApiOperation("根据Id查询章节")
    @GetMapping("get/{id}")
    public R getById(
                    @ApiParam(value = "章节Id", required = true)
                    @PathVariable("id") String id) {

        Chapter chapter = chapterService.getById(id);

        if (chapter != null) {
            return R.ok().data("item", chapter);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据Id更新章节")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "章节对象", required = true)
                        @RequestBody Chapter chapter) {

        boolean result = chapterService.updateById(chapter);

        if (result) {
            return R.ok().message("更新章节成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据Id删除章节")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "章节Id")
                        @PathVariable("id") String id) {

        // 删除课程视频 远程调用
        videoService.removeVodVideoByChapterId(id);

        // 删除章节
        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("嵌套章节列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListById(
                        @ApiParam(value = "课程Id", required = true)
                        @PathVariable("courseId") String courseId) {

        List<ChapterVO> chapterVOList = chapterService.nestedListById(courseId);

        return R.ok().data("items", chapterVOList);

    }

}
