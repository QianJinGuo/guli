/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.servicebase.config
 * @fileName SwaggerConfig
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-20 15:26:47
 *
 */
package tech.jinguo.servicebase.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jinguo
 * @fileName SwaggerConfig
 * @description Swagger配置类
 * @email felix@jinguo.tech
 * @date 2020-12-20 15:26:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * @method: webApiConfig
     * @params: []
     * @returnType: springfox.documentation.spring.web.plugins.Docket
     * @description: Docket  groupName(String var):设置栏目名
     * Docket  apiInfo(ApiInfo apiInfo):设置文档信息
     * Docket  pathMapping(String path):设置api根路径
     * Docket  protocols(Set<String> protocols):设置协议
     * <p>
     * ApiSelectorBuilder select():初始化并返回一个API选择构造器
     * ApiSelectorBuilder apis(Predicate<RequestHandler> selector):添加选择条件并返回添加后的ApiSelectorBuilder对象
     * ApiSelectorBuilder paths(Predicate<String> selector):设置路径筛选，该方法中含一句pathSelector = and(pathSelector, selector);表明条件为相与
     * <p>
     * PathSelectors类的方法：
     * Predicate<String> any():满足条件的路径，该断言总为true
     * Predicate<String> none():不满足条件的路径,该断言总为false
     * Predicate<String> regex(final String pathRegex):符合正则的路径
     * @author: jinguo
     * @date: 2020/12/20 19:03
     */
    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2).groupName("webApi")
                .apiInfo(webApiInfo()).select().paths(Predicates.not(PathSelectors.regex("/admin/.*")))  //不匹配此路径
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).build();//不匹配此路径
    }

    /**
     * @method: webApiInfo
     * @params: []
     * @returnType: springfox.documentation.service.ApiInfo
     * @description:
     * @author: jinguo
     * @date: 2020/12/20 19:05
     */
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder().title("网站-课程中心API文档").description("本文档描述了课程中心微服务接口定义")
                .version("1.0").contact(new Contact("jinguo", "https://jinguo.tech", "felix@jinguo.tech")).build();
    }
}