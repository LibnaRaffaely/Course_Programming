package com.rocketseat.courses_programming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    private static final String[] COURSE_LIST = {
            "/course/",
            "/course/{id}",
            "/course/{id}/active"
    };

    @Autowired
    private SecurityFilterUser securityFilterUser;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(COURSE_LIST).permitAll()
                            .requestMatchers("/user/").permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll()
                            .requestMatchers("/user/auth").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilterUser, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
