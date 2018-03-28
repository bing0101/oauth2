package com.bing.oauth2.bo;

import com.bing.oauth2.model.Role;
import com.bing.oauth2.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bing on 2018/3/14.
 */
public class SecurityUser extends User implements UserDetails {
    private List<Role> roles;

    public SecurityUser(User user, List<Role> roles) {
        super.setUsername(user.getUsername());
        super.setPassword(user.getPassword());
        this.roles = roles == null ? new ArrayList<>() : roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(it -> authorities.add(new SimpleGrantedAuthority(it.getRoleCode())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
