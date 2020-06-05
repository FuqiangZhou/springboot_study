package com.zhou.springbootwithshiro.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootwithshiro.entity.Role;
import com.zhou.springbootwithshiro.entity.User;
import com.zhou.springbootwithshiro.entity.UserRole;
import com.zhou.springbootwithshiro.mapper.RoleMapper;
import com.zhou.springbootwithshiro.mapper.UserMapper;
import com.zhou.springbootwithshiro.mapper.UserRoleMapper;
import com.zhou.springbootwithshiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 13:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public User findUserByName(String username) {
        return this.userMapper.selectOne(new QueryWrapper<User>().eq("name", username));
    }

    @Override
    public Set<String> getRolesByName(String username) {
        User user = findUserByName(username);
        List<UserRole> userRoles = this.userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        Set<String> roleIds = new HashSet<>();
        for (UserRole userRole : userRoles){
            roleIds.add(userRole.getRoleId());
        }
        Set<String> roleNames = new HashSet<>();
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().in("id", roleIds));
        for (Role role : roles){
            roleNames.add(role.getName());
        }
        return roleNames;
    }
}
