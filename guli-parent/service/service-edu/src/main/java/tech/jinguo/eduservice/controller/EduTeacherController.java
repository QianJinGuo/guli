package tech.jinguo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.jinguo.commonutils.GuliException;
import tech.jinguo.commonutils.Result;
import tech.jinguo.eduservice.entity.EduTeacher;
import tech.jinguo.eduservice.entity.vo.TeacherQuery;
import tech.jinguo.eduservice.service.EduTeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //演示自定义异常

        try{
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(20001,"出现自定义异常");
        }

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
        return Result.success().data("items", list);
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
        if (flag) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageList(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                           @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit) {
        Page<EduTeacher> pageParam = new Page<>(current, limit);
        teacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();//得到所有的记录数
        long total = pageParam.getTotal();//总记录数
        // return Result.success().data("total",total).data("rows",records);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total", total);
        pageMap.put("rows", records);
        return Result.success().data(pageMap);
    }

    /**
     * @method: pageTeacherCondition
     * @params: [current, limit, teacherQuery]
     * @returnType: tech.jinguo.commonutils.Result
     * @description: TeacherQuery 如果传入参数和实体类匹配，通过@RequestBody则会将参数自动封装，@ResponseBody会自动将结果分装为字符串或json格式字符串
     * RequestBody:
     * 使用Json传递数据，把Json数据封装到对应对象里面
     * 用RequestBody需要使用Post
     * RequestBody(required = false) TeacherQuery teacherQuery
     * 表明teacherQuery可以为null
     * <p>
     * ResponseBody:
     * 返回数据，一般返回json数据
     * @author: jinguo
     * @date: 2020/12/22 23:38
     */
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                                       @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条件组合查询，动态sql，mybatis中<where>
        //判断条件值是否为空，如果不为空则拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //重点！！！加的不是类中的属性名，而是表中的字段
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            //构建条件
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            //构建条件
            wrapper.ge("begin", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            //构建条件
            wrapper.le("end", end);
        }

        LambdaQueryWrapper<EduTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), EduTeacher::getName, teacherQuery.getName());

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        long total = pageTeacher.getTotal();//总记录数
        return Result.success().data("total", total).data("rows", records);


    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/addTeacher")
    public Result save(@ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher) {
        teacherService.save(teacher);
        return Result.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/query/{id}")
    public Result getById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return Result.success().data("item", teacher);
    }


    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("/update/{id}")
    public Result updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        teacher.setId(id);
        teacherService.updateById(teacher);
        return Result.success();
    }


}

