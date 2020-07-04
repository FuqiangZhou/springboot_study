package com.zhou.dynamicdatasource.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/8/28 6:11 下午
 */
@Data
public class JavaClassInfo {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 类名
     */
    private String className;

    /**
     * 描述
     */
    private String comment;

    /**
     * 属性集合
     */
    private List<JavaColumnInfo> javaColumnInfos;

    /**
     * 是否导入java.util.Date
     */
    private Boolean importDate;

    /**
     * 是否导入java.math.BigDecimal
     */
    private Boolean importBigDecimal;

    /**
     * 是否包含主键
     */
    private Boolean includePk;

}
