package com.manu.GradeR.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

    @Autowired
    JpaUserDetailsService jpaUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .headers(headers -> headers.frameOptions().sameOrigin())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/register/**","/h2-console/**", "/error").permitAll()
                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated())
                    .csrf().disable()
                    .userDetailsService(jpaUserDetailsService)
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .successForwardUrl("/");

            return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
