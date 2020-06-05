package com.zhou.dynamicdatasource.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.dynamicdatasource.entity.DbConfigEntity;
import com.zhou.dynamicdatasource.mapper.DbConfigMapper;
import com.zhou.dynamicdatasource.service.DbConfigService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/1/3 2:57 下午
 */
@Service
@DS("db_test")
public class DbConfigServiceImpl extends ServiceImpl<DbConfigMapper, DbConfigEntity> implements DbConfigService {

}
