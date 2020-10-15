package com.yuxiang.guli.service.statistics.task;

import com.yuxiang.guli.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: yuxiang
 * @create: 2020-07-08
 * @Description:
 **/
@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    @Scheduled(cron = "1/3 * * * * * *")
    public void test1() {

        log.info("task1 被执行");
    }

    @Scheduled(cron = "0 0 1 * * ? *")
    public void createStatistics() {

        log.info("成生统计信息");
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);

    }
}
