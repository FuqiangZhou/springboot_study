package com.zhou.dynamicdatasource.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.dynamicdatasource.entity.TableInfo;
import com.zhou.dynamicdatasource.mapper.TableInfoMapper;
import com.zhou.dynamicdatasource.service.TableInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/10 5:22 下午
 */
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo> implements TableInfoService {

    @Override
    @DS("#dataSourceName")
    public List<TableInfo> showAllTableInfo(String dataSourceName) {
        return this.baseMapper.selectTableInfo();
    }

    @Override
    @DS("#dataSourceName")
    public List<TableInfo> showTableInfo(String dataSourceName, List<String> tableNames) {
        return this.baseMapper.selectTableInfoByNames(tableNames);
    }
}
