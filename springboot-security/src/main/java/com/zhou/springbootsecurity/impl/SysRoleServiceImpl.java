package com.zhou.springbootsecurity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.springbootsecurity.entity.SysRole;
import com.zhou.springbootsecurity.mapper.SysRoleMapper;
import com.zhou.springbootsecurity.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:50 下午
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
