/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.commonutils
 * @fileName ResultCode
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-20 21:55:58
 *
 */
package tech.jinguo.commonutils;

/**
 * @author jinguo
 * @fileName ResultCode
 * @description
 *      返回状态码，也可用枚举进行定义
 *      Interface中默认public static final 所以可以不加
 * @email felix@jinguo.tech
 * @date 2020-12-20 21:55:58
 */
public interface ResultCode {
    Integer SUCCESS=20000;  //成功

    Integer ERROR=20001;    //失败
}