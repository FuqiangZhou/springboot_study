package com.zhou.springbootfreemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootfreemarker.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/12 12:39 下午
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
