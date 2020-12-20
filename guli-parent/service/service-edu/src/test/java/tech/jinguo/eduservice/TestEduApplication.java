/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.eduservice
 * @fileName TestSpringBoot
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-20 13:56:17
 *
 */
package tech.jinguo.eduservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.jinguo.eduservice.entity.EduTeacher;
import tech.jinguo.eduservice.service.EduTeacherService;

/**
 * @author jinguo
 * @fileName TestSpringBoot
 * @description junit测试类
 * @email felix@jinguo.tech
 * @date 2020-12-20 13:56:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEduApplication {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Test
    public void testInsert(){
        eduTeacherService.save(new EduTeacher().setName("赵六").setIntro("副教授").setCareer("高级").setLevel(2));
    }
}