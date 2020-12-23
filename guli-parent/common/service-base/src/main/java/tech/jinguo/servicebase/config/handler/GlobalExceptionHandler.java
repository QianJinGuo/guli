/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.servicebase.config.handler
 * @fileName GlobalExceptionHandler
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-23 22:29:47
 *
 */
package tech.jinguo.servicebase.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.jinguo.commonutils.Result;

/**
 * @author jinguo
 * @fileName GlobalExceptionHandler
 * @description 统一异常处理
 * @email felix@jinguo.tech
 * @date 2020-12-23 22:29:47
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //指定出现什么异常执行此方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }
}