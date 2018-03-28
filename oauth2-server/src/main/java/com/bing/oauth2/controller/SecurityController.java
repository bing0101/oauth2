package com.bing.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by bing on 2018/3/9.
 */
@RestController
public class SecurityController {

    @RequestMapping("/security")
    public String security() {
        return "spring security";
    }

    @GetMapping("/index")
    public String hello() {
        return "index";
    }

    @GetMapping("/login")
    public Map<String, String[]> login(HttpServletRequest request) {
        return request.getParameterMap();
    }

    @GetMapping("/fail")
    public Map<String, String[]> fail(HttpServletRequest request) {
        return request.getParameterMap();
    }
}
