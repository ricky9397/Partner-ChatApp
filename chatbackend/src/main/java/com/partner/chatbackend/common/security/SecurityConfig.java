package com.partner.chatbackend.common.security;

import com.partner.chatbackend.common.config.CorsConfig;
import com.partner.chatbackend.user.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;
    private final CorsConfig corsConfig;

    @Bean // bean 은 해당 메서드의 리턴되는 오브젝트를 Ioc 로 등록해준다.
    public BCryptPasswordEncoder encoderPwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(encoderPwd());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTLoginFilter loginFilter = new JWTLoginFilter(authenticationManager(), userSecurityService);
        JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager(), userSecurityService);
        http
                .addFilter(corsConfig.corsFilter()) // 시큐리티 cors
                .csrf().ignoringAntMatchers("/oauth2/login/**") // /oauth2/login/** 구글,네이버등 로그인 제외
                .disable() // csrf 보안 설정을 비활성화한다.
                .cors() // 화면 cors
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 시큐리티 세션을 사용하지 않음.
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class) // 로그인처리필터
                .addFilterAt(checkFilter, BasicAuthenticationFilter.class) // 토큰검증필터
                .authorizeRequests(config -> config
                        .antMatchers("/**").permitAll()
                        .antMatchers("/auth/**").permitAll()
                        .antMatchers("/admin/**").hasAuthority("ROLE_USER")
                        .antMatchers("/oauth2/**").permitAll()
                );
    }

}
