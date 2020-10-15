package com.yuxiang.guli.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuxiang.guli.service.edu.entity.Subject;
import com.yuxiang.guli.service.edu.entity.excel.ExcelSubjectData;
import com.yuxiang.guli.service.edu.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {


    private SubjectMapper subjectMapper;

    public ExcelSubjectListener() {
    }

    public ExcelSubjectListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    /**
     * 遍历每一行的记录
     * @param data
     * @param context
     */
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext context) {
        log.info("解析到一条数据,{}", data);

        // 处理读取出来的数据
        String levelOneTitle = data.getLevelOneTitle(); // 一级标题
        String levelTwoTitle = data.getLevelTwoTitle(); // 二级标题

        // 一级标题
        // 先判断数据是否存在
        String parentId = null;
        Subject subjectLevelOne = this.getSubjectOneTitle(levelOneTitle);
        if (subjectLevelOne == null) {
            // 组装一级类别
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            // 存入数据库
            subjectMapper.insert(subject);
            parentId = subject.getId();
        } else {
            parentId = subjectLevelOne.getId();
        }

        // 二级标题
        Subject subjectLevelTwo = this.getSubjectOneWithTwoTitle(levelTwoTitle, parentId);
        if (subjectLevelTwo == null) {
            // 组装二级类别
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            subjectMapper.insert(subject);
        }

    }

    /**
     * 数据的收尾工作
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("全部数据解析完成");
    }

    /**
     * 根据一级分类的名称查询该一级分类是否存在
     * @param title 一级分类名称
     * @return
     */
    private Subject getSubjectOneTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0"); // 一级分类

        return subjectMapper.selectOne(queryWrapper);
    }

    /**
     * 根据一级分类的名称和父类名称查询该二级分类是否存在
     * @param title 二级分类名称
     * @param parentId 一级分类名称
     * @return
     */
    private Subject getSubjectOneWithTwoTitle(String title, String parentId) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);

        return subjectMapper.selectOne(queryWrapper);
    }


}
