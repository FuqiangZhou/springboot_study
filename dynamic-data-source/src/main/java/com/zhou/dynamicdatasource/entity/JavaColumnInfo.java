package com.zhou.dynamicdatasource.entity;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/28 6:16 下午
 */
@Data
public class JavaColumnInfo {

    /**
     * 属性
     */
    private String javaProperties;

    /**
     * 类型
     */
    private String javaType;

    /**
     * 描述
     */
    private String comment;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 是否主键
     */
    private Boolean isPk;
}
