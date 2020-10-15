package com.yuxiang.guli.service.statistics.service;

import com.yuxiang.guli.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author yuxiang
 * @since 2020-07-07
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String date);

}
