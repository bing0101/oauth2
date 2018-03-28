package com.bing.oauth2.config;

import com.bing.oauth2.service.SecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * Created by bing on 2018/3/9.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@DependsOn("securityUserDetailsService")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private SecurityUserDetailsService securityUserDetailsService;

    /**
     * 定义认证用户信息获取来源
     * 身份验证管理生成器
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService);
    }

    /**
     * 定义安全策略
     * http请求安全处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            //定义 "/"，"/hello" 请求不需要验证
            .antMatchers("/", "/hello", "/resources/**", "/doLogin").permitAll()

            //以 "/admin/" 开头的URL只能让拥有 "ROLE_ADMIN"角色的用户访问，hasRole 方法，没有使用 "ROLE_" 前缀
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
            .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA")

            .anyRequest().authenticated()//其余所有请求都需要验证
            .and()
                .logout()
                .logoutUrl("/logout")//指定登出url
                .logoutSuccessUrl("/index")//指定登出成功后跳转页面
                .permitAll()//定义logout不需要验证
            .and()
                .formLogin()//使用form表单登录
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")//指定登录url
                .defaultSuccessUrl("/index")//指定登录成功后跳转页面
                .failureUrl("/fail")//指定登录失败后跳转页面
                .permitAll();
    }

    /**
     * web安全
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}
