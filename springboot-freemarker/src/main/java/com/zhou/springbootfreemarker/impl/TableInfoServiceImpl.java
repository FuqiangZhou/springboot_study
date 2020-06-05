package com.zhou.springbootfreemarker.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.springbootfreemarker.entity.TableInfo;
import com.zhou.springbootfreemarker.mapper.TableInfoMapper;
import com.zhou.springbootfreemarker.service.TableInfoService;
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
    public List<TableInfo> showAllTableInfo() {
        return this.baseMapper.selectTableInfo();
    }
}
