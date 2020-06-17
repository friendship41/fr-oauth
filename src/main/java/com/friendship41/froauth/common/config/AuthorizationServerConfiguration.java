package com.friendship41.froauth.common.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private DataSource dataSource;

  @Override
  public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
    // OAuth2 인증서버 자체의  보안 정보를 설정하는 부분
    security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    // Client 에 대한 정보를  설정하는 부분
    clients.jdbc(dataSource);
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // OAuth2 서버가 작동하기 위한 Endpoint에 대한 정보를 설정
    endpoints
        .tokenStore(JdbcTokenStore(dataSource))
        .authenticationManager(authenticationManager);
  }

  @Bean
  public TokenStore JdbcTokenStore(DataSource dataSource) {
    return new JdbcTokenStore(dataSource);
  }
}
