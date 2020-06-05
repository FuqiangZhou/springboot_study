package com.zhou.springbootsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootsecurity.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:44 下午
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
