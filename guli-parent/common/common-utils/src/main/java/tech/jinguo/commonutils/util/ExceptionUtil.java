/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.commonutils.util
 * @fileName ExceptionUtil
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-24 01:08:43
 *
 */
package tech.jinguo.commonutils.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author jinguo
 * @fileName ExceptionUtil
 * @description 日志堆栈信息工具类
 * @email felix@jinguo.tech
 * @date 2020-12-24 01:08:43
 */
public class ExceptionUtil {
    public static String getMessage(Exception e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try{
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的堆栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
        return sw.toString();
    }
}