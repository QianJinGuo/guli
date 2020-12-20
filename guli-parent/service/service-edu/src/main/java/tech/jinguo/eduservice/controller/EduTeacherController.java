package tech.jinguo.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tech.jinguo.commonutils.Result;
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

    @ApiOperation(value = "以统一结果来返回所有讲师列表")
    @GetMapping("/commonFindAll") // findAll和/findAll都可以
    public Result commonFindAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Result.success().data("items",list);
    }

    @ApiOperation("以统一结果来返回根据id删除讲师的影响记录")
    @DeleteMapping("/commonDelete/{id}")
    public Result commonRemoveById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);

        /*
           在使用mybatis-plus 3.0.5的removeById方法时:
           boolean flag = teacherService.removeById(id);
           removeById的底层是delBool
           public static boolean delBool(Integer result) {
             return null != result && result >= 0;
           }
           所以问题在于result=0的时候 返回的是true

           上述remove方法一直返回true的问题，在最新版本3.3.0已经修复，
           SqlHelper中已经移除delBool方法，save update remove全部都使用retBool方法判断逻辑。
           default boolean removeById(Serializable id) {
                return SqlHelper.retBool(this.getBaseMapper().deleteById(id));
                =>实际上判断的是return null != result && result >= 1;
           }
           在使用MP 的IService接口时，如果不熟悉或不太确定接口底层的实现逻辑是否与自己的预期一致，
           建议是先看一下源码使用单元测试验证一下逻辑是否正常，此问题就是本人在swapper中，
           故意输入错误值，查看返回错误结果时，永远返回的都是true
         */
        if(flag){
            return Result.success();
        }else {
            return Result.error();
        }
    }




}

