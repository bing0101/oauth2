package com.bing.resource.controller;

import com.bing.oauth2.model.User;
import com.bing.resource.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bing on 2018/3/23.
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.getAll();
    }

}
