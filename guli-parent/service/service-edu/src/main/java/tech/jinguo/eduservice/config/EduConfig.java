/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.eduservice.config
 * @fileName EduConfig
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-19 01:44:54
 *
 */
package tech.jinguo.eduservice.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author jinguo
 * @fileName EduConfig
 * @description Mybatis-Plus配置
 * @email felix@jinguo.tech
 * @date 2020-12-19 01:44:54
 */
@Configuration
@MapperScan("tech.jinguo.eduservice.mapper")
//配置建议加载配置类上，比加在启动类上规范
public class EduConfig {
    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     */
    @Bean
    @Profile({"dev"})// 设置 dev环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);//ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}