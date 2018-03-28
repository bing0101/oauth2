package com.bing.resource.service;

import com.bing.oauth2.mapper.UserMapper;
import com.bing.oauth2.model.User;
import com.bing.oauth2.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bing on 2018/3/23.
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> getAll() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }
}
