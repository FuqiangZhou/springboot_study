package com.zhou.dynamicdatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.dynamicdatasource.entity.DbConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/30 11:25 上午
 */
@Mapper
public interface DbConfigMapper extends BaseMapper<DbConfigEntity> {

    @Select("SELECT * FROM db_config")
    List<DbConfigEntity> selectAllDbConfigEntities();

}
