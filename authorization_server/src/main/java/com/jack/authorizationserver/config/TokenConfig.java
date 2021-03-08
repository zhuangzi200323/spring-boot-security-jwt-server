package com.jack.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    private String SIGN_KEY = "uaa123";
    //配置如何把普通token转换成jwt token
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        //使用对称秘钥加密token,resource那边会用这个秘钥校验token
        converter.setSigningKey(SIGN_KEY);
        return converter;
    }

    //令牌存储策略
    @Bean
    public TokenStore tokenStore(){
        //把用户信息都存储在token当中,相当于存储在客户端,性能好很多
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
