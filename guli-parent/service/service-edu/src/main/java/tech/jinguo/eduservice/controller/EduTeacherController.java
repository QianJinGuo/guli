package tech.jinguo.eduservice.controller;


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
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //访问地址:
    //把service注入http://localhost:8081/eduservice/teacher/findAll
    @Autowired
    private EduTeacherService teacherService;

    //1.查询讲师表所有数据
    //rest风格  Get Post Put Delete
    @GetMapping("findAll") // findAll和/findAll都可以
    public List<EduTeacher> findAllTeacher(){
        return teacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
       return teacherService.removeById(id);
    }
}

