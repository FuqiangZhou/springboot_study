package com.zhou.dynamicdatasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.dynamicdatasource.entity.TableInfo;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/10 5:22 下午
 */
public interface TableInfoService extends IService<TableInfo> {

    List<TableInfo> showAllTableInfo(String dataSourceName);

    List<TableInfo> showTableInfo(String dataSourceName, List<String> tableNames);
}
