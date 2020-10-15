package com.yuxiang.guli.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yuxiang.guli.service.edu.entity.Subject;
import com.yuxiang.guli.service.edu.entity.excel.ExcelSubjectData;
import com.yuxiang.guli.service.edu.entity.vo.SubjectVO;
import com.yuxiang.guli.service.edu.listener.ExcelSubjectListener;
import com.yuxiang.guli.service.edu.mapper.SubjectMapper;
import com.yuxiang.guli.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVO> nestedList() {
        return baseMapper.selectNestedSByParentId("0");
    }
}
