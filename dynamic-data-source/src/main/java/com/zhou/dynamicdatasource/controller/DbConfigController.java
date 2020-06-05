package com.zhou.dynamicdatasource.controller;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.zhou.dynamicdatasource.entity.DbConfigEntity;
import com.zhou.dynamicdatasource.service.DbConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Set;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/1/3 3:01 下午
 */
@RestController
@RequestMapping("/dbConfig")
public class DbConfigController {

    @Resource
    private DbConfigService dbConfigService;

    @Resource
    private DataSource dataSource;

    @Resource
    private DynamicDataSourceCreator dataSourceCreator;

    @GetMapping("/dataSources")
    public Set<String> dataSources() {
        DynamicRoutingDataSource drd = (DynamicRoutingDataSource) this.dataSource;
        return drd.getCurrentDataSources().keySet();
    }

    @PostMapping("/addDataSource")
    public Set<String> addDataSource(@RequestBody DbConfigEntity dbConfigEntity) {
        DynamicRoutingDataSource drd = (DynamicRoutingDataSource) this.dataSource;
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUsername(dbConfigEntity.getUsername());
        dataSourceProperty.setPassword(dbConfigEntity.getPassword());
        dataSourceProperty.setUrl(dbConfigEntity.getUrl());
        dataSourceProperty.setDriverClassName(dbConfigEntity.getDriver());
        this.dbConfigService.save(dbConfigEntity);
        DataSource dataSource = this.dataSourceCreator.createDataSource(dataSourceProperty);
        drd.addDataSource(dbConfigEntity.getName(), dataSource);
        return drd.getCurrentDataSources().keySet();
    }
}
