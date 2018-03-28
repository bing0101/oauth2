package com.bing.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by bing on 2018/3/23.
 */
@Component
public class MyClientDetailsService implements ClientDetailsService {
    @Autowired
    private DataSource dataSource;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        return jdbcClientDetailsService.loadClientByClientId(clientId);
    }
}
