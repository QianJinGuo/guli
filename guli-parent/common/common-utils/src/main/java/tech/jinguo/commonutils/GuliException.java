/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.commonutils
 * @fileName GuliException
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-23 23:14:58
 *
 */
package tech.jinguo.commonutils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinguo
 * @fileName GuliException
 * @description 自定义异常
 * @email felix@jinguo.tech
 * @date 2020-12-23 23:14:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "自定义异常类",description = "异常")
public class GuliException extends RuntimeException{
    @ApiModelProperty(value = "异常码")
    private Integer code;
    @ApiModelProperty(value = "异常消息")
    private String msg;
}