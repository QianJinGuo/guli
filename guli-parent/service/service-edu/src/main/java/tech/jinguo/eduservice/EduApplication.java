/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.eduservice
 * @fileName EduApplication
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-19 01:40:38
 *
 */
package tech.jinguo.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jinguo
 * @fileName EduApplication
 * @description 启动类
 * @email felix@jinguo.tech
 * @date 2020-12-19 01:40:38
 */
@SpringBootApplication
@ComponentScan(basePackages = {"tech.jinguo"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}