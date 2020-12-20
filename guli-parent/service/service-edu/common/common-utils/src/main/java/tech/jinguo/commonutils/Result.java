/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.commonutils
 * @fileName Result
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-20 22:00:31
 *
 */
package tech.jinguo.commonutils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinguo
 * @fileName Result
 * @description 返回类
 * 格式：
 *     列表：
 *      {
 *          "success":true,
 *          "code":20000,
 *          "message":"成功"
 *          "data":{
 *              "items":[
 *                  "id":"1",
 *                  "name":"zhangsan",
 *                  "info":"毕业于苏州科技大学，喜欢编程，尤其是Java"
 *              ]
 *          }
 *      }
 *
 *      分页：
 *       {
 *          "success":true,
 *          "code":20000,
 *          "message":"成功"
 *          "data":{
 *              "total": 17,
 *              "rows":[
 *                  "id":"1",
 *                  "name":"zhangsan",
 *                  "info":"毕业于苏州科技大学，喜欢编程，尤其是Java"
 *              ]
 *          }
 *      }
 *
 *      没有返回数据:
 *      {
 *          "success": true,
 *          "code": 20000,
 *          "message": "成功",
 *          "data": {}
 *      }
 *
 *
 *      失败:
 *      {
 *          "success": false,
 *          "code": 20001,
 *          "message": "失败",
 *          "data": {}
 *      }
 *
 *      定义统一结果
 *      {
 *          "success": 布尔, //响应是否成功
 *          "code": 数字, //响应码
 *          "message": 字符串, //返回消息
 *          "data": HashMap //返回数据，放在键值对中
 *      }
 *
 * @email felix@jinguo.tech
 * @date 2020-12-20 22:00:31
 */
@Data
//@Accessors(chain = true)
public class Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data= new HashMap<>();

    //构造函数私有化，无法被外部实例化
    private Result(){

    }

    public static Result success(){
        //return new Result().setSuccess(true).setCode(ResultCode.SUCCESS).setMessage("成功");
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    public static Result error(){
        //return new Result().setSuccess(false).setCode(ResultCode.ERROR).setMessage("失败");
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    /**
     * 以下方法是为了实现链式调用
     * 比如Result.success().message().code().data()
     * 其实lombok已经实现了链式调用
     * 可以通过注解@Accessors(chain = true)来实现此效果
     */
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }


    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

}