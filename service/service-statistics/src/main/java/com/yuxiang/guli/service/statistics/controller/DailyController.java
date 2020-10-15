package com.yuxiang.guli.service.statistics.controller;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author yuxiang
 * @since 2020-07-07
 */
@Api(description = "统计控制器")
@RestController
@RequestMapping("/admin/statistics/daily")
@Slf4j
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation("根据日期成生统计信息")
    @GetMapping("create/{day}")
    public R createStatisticsByDay(@ApiParam("统计日期") @PathVariable("day") String day) {

        dailyService.createStatisticsByDay(day);
        return R.ok().message("统计信息生成成功");
    }

}

