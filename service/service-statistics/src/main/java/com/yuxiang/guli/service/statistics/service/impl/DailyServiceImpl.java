package com.yuxiang.guli.service.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.statistics.entity.Daily;
import com.yuxiang.guli.service.statistics.feign.UcenterMemberService;
import com.yuxiang.guli.service.statistics.mapper.DailyMapper;
import com.yuxiang.guli.service.statistics.service.DailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author yuxiang
 * @since 2020-07-07
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterMemberService ucenterMemberService;


    @Override
    public void createStatisticsByDay(String date) {

        // 如果当前日期的统计数据已经存在，则先删除
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", date);
        baseMapper.delete(queryWrapper);

        // 获取某一天中的注册人数
        R r = ucenterMemberService.countRegisterNumber(date);
        Integer registerNum = (Integer) r.getData().get("registerNum");
        // TODO 获取其他
        int loginNum = RandomUtils.nextInt(100, 300);
        int videoViewNum = RandomUtils.nextInt(100, 300);
        int courseNum = RandomUtils.nextInt(100, 300);

        Daily daily = new Daily();
        daily.setRegisterNum(registerNum);
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setDateCalculated(date);

        baseMapper.insert(daily);
    }
}
