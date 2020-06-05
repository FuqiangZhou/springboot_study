package com.zhou.springbootwithshiro.service;

import com.zhou.springbootwithshiro.entity.User;

import java.util.Set;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-12 13:12
 */
public interface UserService {

    User findUserByName(String username);

    Set<String> getRolesByName(String username);
}
