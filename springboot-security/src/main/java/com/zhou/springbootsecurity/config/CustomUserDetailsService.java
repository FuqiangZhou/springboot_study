package com.zhou.springbootsecurity.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootsecurity.entity.SysRole;
import com.zhou.springbootsecurity.entity.SysUser;
import com.zhou.springbootsecurity.entity.SysUserRole;
import com.zhou.springbootsecurity.service.SysRoleService;
import com.zhou.springbootsecurity.service.SysUserRoleService;
import com.zhou.springbootsecurity.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/2/12 5:57 下午
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //取出用户信息
        SysUser user = this.sysUserService.getOne(new QueryWrapper<SysUser>().eq("name", username));
        //判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //添加权限
        List<SysUserRole> sysUserRoles = this.sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id", user.getId()));
        for (SysUserRole sysUserRole : sysUserRoles) {
            SysRole sysRole = this.sysRoleService.getById(sysUserRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
