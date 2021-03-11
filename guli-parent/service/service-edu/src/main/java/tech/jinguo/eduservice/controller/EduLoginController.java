/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.eduservice.controller
 * @fileName EduLoginController
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-03-11 22:19:45
 *
 */
package tech.jinguo.eduservice.controller;

import org.springframework.web.bind.annotation.*;
import tech.jinguo.commonutils.Result;

/**
 * @author jinguo
 * @fileName EduLoginController
 * @description 用户登录
 * @email felix@jinguo.tech
 * @date 2021-03-11 22:19:45
 */
@RestController
@RequestMapping("/eduservice/user")
/**
 * originPatterns = "*" 解决跨域问题
 */
@CrossOrigin(originPatterns = "*")
public class EduLoginController {
    /**
     * login
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login() {
        return Result.success().data("token","admin ");
    }

    /**
     * info
     * @return
     */
    @GetMapping("/info")
    @ResponseBody
    public Result info() {
        return Result.success().data("name","admin")
                .data("roles","[admin]")
                .data("avator","https://jinguo.tech/uploads/avatar.png");
    }
}