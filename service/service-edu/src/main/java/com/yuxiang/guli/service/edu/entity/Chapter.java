package com.yuxiang.guli.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yuxiang.guli.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_chapter")
@ApiModel(value="Chapter对象", description="课程")
public class Chapter extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;


}