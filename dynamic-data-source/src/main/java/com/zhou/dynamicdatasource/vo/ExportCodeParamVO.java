package com.zhou.dynamicdatasource.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/7/3 3:17 下午
 */
@Data
public class ExportCodeParamVO {

    /**
     * 主包名
     */
    private String packageName;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 表名集合
     */
    private List<String> tableNames;
}
