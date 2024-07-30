package com.beyond.board.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // spring security 설정을 customizing 하기 위함.
@EnableGlobalMethodSecurity(prePostEnabled = true) // pre : 사전, post : 사후 인증검사
public class SecurityConfig {
    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // csrf 공격에 대한 설정은 하지 않겠다라는 의미
                .csrf().disable()
                
                .build();
    }

}
