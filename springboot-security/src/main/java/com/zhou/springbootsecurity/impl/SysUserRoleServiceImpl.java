package com.zhou.springbootsecurity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.springbootsecurity.entity.SysUserRole;
import com.zhou.springbootsecurity.mapper.SysUserRoleMapper;
import com.zhou.springbootsecurity.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:51 下午
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
