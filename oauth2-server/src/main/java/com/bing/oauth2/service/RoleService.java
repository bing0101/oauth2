package com.bing.oauth2.service;

import com.bing.oauth2.mapper.RoleMapper;
import com.bing.oauth2.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bing on 2018/2/24.
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public Role getById(long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

}
