package com.zhou.springbootsecurity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.springbootsecurity.entity.SysUser;
import com.zhou.springbootsecurity.mapper.SysUserMapper;
import com.zhou.springbootsecurity.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:49 下午
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
