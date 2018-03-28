package com.bing.oauth2.service;

import com.bing.oauth2.bo.SecurityUser;
import com.bing.oauth2.model.Role;
import com.bing.oauth2.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bing on 2018/3/14.
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        if (user == null) {
            String msg = String.format("username[%s] not foud", username);
            throw new UsernameNotFoundException(msg);
        }
        List<Role> roles = userService.getRolesByUsername(username);
        return new SecurityUser(user, roles);
    }
}
