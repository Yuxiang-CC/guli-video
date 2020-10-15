package com.yuxiang.guli.service.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.service.edu.entity.Teacher;
import com.yuxiang.guli.service.edu.entity.vo.TeacherQueryVO;
import com.yuxiang.guli.service.edu.feign.OssFileService;
import com.yuxiang.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Yuxiang
 * @since 2020-05-19
 */
@Api(description = "讲师管理控制器")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll() {

        List<Teacher> teacherList = teacherService.list();

        return R.ok().data("items", teacherList);
    }

    @ApiOperation(value = "根据ID删除讲师", notes = "详细信息：逻辑删除")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam("讲师ID") @PathVariable("id") String id) {

        boolean removeAvatarResult = teacherService.removeAvatarById(id);

        boolean result = teacherService.removeById(id);
        if (result)
            return R.ok().message("删除成功-" + removeAvatarResult);

        return R.error().message("删除失败");
    }

    @ApiOperation(value = "分页获取讲师列表")
    @GetMapping("/list/{page}/{size}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable("page") Long page,
                      @ApiParam(value = "页面大小", required = true) @PathVariable("size") Long size,
                      @ApiParam("讲师列表查询对象") TeacherQueryVO teacherQueryVO) {

        Page<Teacher> pageParam = new Page<>(page, size);
        Page<Teacher> pageModelList = teacherService.selectPage(pageParam, teacherQueryVO);

        return R.ok().data("rows", pageModelList.getRecords())
                .data("total", pageModelList.getTotal());
    }

    @ApiOperation("新增讲师")
    @PostMapping("/add")
    public R add(@ApiParam("讲师对象信息") @RequestBody Teacher teacher) {
        boolean result = teacherService.save(teacher);
        if (result)
            return R.ok().message("新增成功");

        return R.error().message("新增失败");
    }

    @ApiOperation("更新讲师")
    @PutMapping("/update")
    public R update(@ApiParam("讲师对象信息") @RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if (result) return R.ok().message("更新成功");

        return R.error().message("数据不存在");
    }

    @ApiOperation("根据ID获取讲师信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam("讲师对象信息") @PathVariable("id") String id) {

        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("teacher", teacher);
        }

        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id批量删除讲师")
    @DeleteMapping("/batch-remove")
    public R removeByIds(@ApiParam("讲师对象ID列表") @RequestBody List<String> idList) {

        boolean result = teacherService.removeByIds(idList);
        if (result)
            return R.ok().message("操作成功");

        return R.error().message("操作失败");
    }


    @ApiOperation("根据关键字查询讲师名字")
    @GetMapping("/list-name/{nameKey}")
    public R listName(
            @ApiParam(value = "讲师名字关键字", required = true)
            @PathVariable("nameKey") String nameKey) {

        List<Map<String, Object>> teacherNameList = teacherService.selectNameList(nameKey);
        return R.ok().data("names", teacherNameList);
    }


    @GetMapping("/test-open-feign")
    @ApiOperation("测试 调用远程OSS服务")
    public R testOpenFeign() {

        R ossR = ossFileService.test();

        return ossR.data("edu", "edu");
    }


    @GetMapping("/test")
    @ApiOperation("压力测试")
    public R test() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

}

