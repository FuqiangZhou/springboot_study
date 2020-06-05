package com.zhou.springbootwithshiro.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootwithshiro.entity.Role;
import com.zhou.springbootwithshiro.entity.User;
import com.zhou.springbootwithshiro.entity.UserRole;
import com.zhou.springbootwithshiro.mapper.RoleMapper;
import com.zhou.springbootwithshiro.mapper.UserMapper;
import com.zhou.springbootwithshiro.mapper.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 11:03
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("========>权限认证<========");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = this.userMapper.selectOne(new QueryWrapper<User>().eq("name", username));
        Set<String> roleIds = new HashSet<>();
        List<UserRole> userRoles = this.userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        for (UserRole userRole : userRoles){
            roleIds.add(userRole.getRoleId());
        }
        Set<String> roleNames = new HashSet<>();
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().in("id", roleIds));
        for (Role role : roles){
            roleNames.add(role.getName());
        }
        info.setRoles(roleNames);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("========>身份认证<========");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = this.userMapper.selectOne(new QueryWrapper<User>().eq("name", token.getUsername()));
        if (user != null){
            String password = user.getPassword();
            if (!password.equals(new String((char []) token.getCredentials()))){
                throw new AccountException("密码不正确");
            }
            return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
        }else {
            throw new AccountException("用户不存在");
        }
    }
}
