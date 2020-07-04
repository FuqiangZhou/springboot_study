package com.zhou.dynamicdatasource.entity;

import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/28 6:03 下午
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 是否主键
     */
    private Boolean columnKey;
}
