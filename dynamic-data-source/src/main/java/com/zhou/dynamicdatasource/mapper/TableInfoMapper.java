package com.zhou.dynamicdatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.dynamicdatasource.entity.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/10 4:51 下午
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfo> {

    List<TableInfo> selectTableInfo();

    List<TableInfo> selectTableInfoByNames(@Param("tableNames") List<String> tableNames);
}
