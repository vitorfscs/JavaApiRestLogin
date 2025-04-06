package com.accounting.Accounting.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desabilita proteção CSRF pra facilitar testes via Postman
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/usuario/cadastrar", "/usuario/login").permitAll() // libera esses
                                .anyRequest().authenticated() // o resto precisa de login
                )
                .httpBasic(Customizer.withDefaults()); // usa autenticação básica

        return http.build();
    }
}
