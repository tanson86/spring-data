package com.understanding.spring.data.spring_data.understanding.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager user(){
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").
                        password("{noop}password").
                        roles("ADMIN").build(),
                User.withUsername("user").
                        password("{noop}password").
                        roles("USER").build()
        );
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3 (OpenAPI)
            "/public/**",
            "/public"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.
                headers((headers) ->
                        headers.frameOptions(Customizer.withDefaults()).disable()).
                csrf(csrf->csrf.disable()).
                authorizeHttpRequests(auth->auth.
                        requestMatchers(AUTH_WHITELIST).permitAll().
                        anyRequest().authenticated()
                ).
                sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                httpBasic(Customizer.withDefaults()).
                build();

    }
}

