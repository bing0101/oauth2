package com.bing.oauth2.service;

import com.bing.oauth2.mapper.UserMapper;
import com.bing.oauth2.mapper.UserRoleMapper;
import com.bing.oauth2.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 2018/2/24.
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleService roleService;

    public List<User> getAll() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    public User getByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);

        if (users.size() == 0) {
            return null;
        }

        if (users.size() > 1) {
            logger.error("username:{}包含多条记录！", username);
        }

        return users.get(0);
    }

    public List<Role> getRolesByUsername(String username) {
        List<Role> roles = new ArrayList<>();

        User user = getByUsername(username);

        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        userRoles.forEach(it -> roles.add(roleService.getById(it.getId())));
        return roles;
    }

}
