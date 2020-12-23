/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName tech.jinguo.eduservice.entity.vo
 * @fileName TeacherQuery
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2020-12-22 23:29:22
 *
 */
package tech.jinguo.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jinguo
 * @fileName TeacherQuery
 * @description 分页条件查询
 * @email felix@jinguo.tech
 * @date 2020-12-22 23:29:22
 */
@Data
@ApiModel(value = "TeacherQuery对象", description = "分页查询参数对象")
public class TeacherQuery {
    @ApiModelProperty(value = "教师姓名，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2020-12-22 23:30:00")
    private String begin;

    @ApiModelProperty(value = "查询借书时间",example = "2020-12-22 23:30:00")
    private String end;
}