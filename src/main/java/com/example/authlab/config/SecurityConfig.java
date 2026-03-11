package com.example.authlab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.authlab.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //CSRF 비활성화
            .csrf(AbstractHttpConfigurer::disable)
            //Security 기본 로그인 비활성화
            .formLogin(AbstractHttpConfigurer::disable)
            //브라우저 팝업 인증창 비활성화
            .httpBasic(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                //H2 콘솔을 위한 설정
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //요청 Filter
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            ;
        
        return http.build();
    } 
}
