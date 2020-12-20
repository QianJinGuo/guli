package tech.jinguo.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tech.jinguo.eduservice.entity.EduTeacher;
import tech.jinguo.eduservice.service.EduTeacherService;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jinguo
 * @since 2020-12-19
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping(value = "/edu/teacher")
public class EduTeacherController {

    //访问地址:
    //把service注入http://localhost:8081/eduservice/teacher/findAll
    @Autowired
    private EduTeacherService teacherService;

    //1.查询讲师表所有数据
    //rest风格  Get Post Put Delete
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll") // findAll和/findAll都可以
    public List<EduTeacher> findAllTeacher() {
        return teacherService.list(null);
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/delete/{id}")
    public boolean removeById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        return teacherService.removeById(id);
    }

}

