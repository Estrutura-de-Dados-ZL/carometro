package com.carometro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @SuppressWarnings("unused")
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JWTAuthEntryPoint authEntryPoint;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .exceptionHandling((eH) -> eH.authenticationEntryPoint(authEntryPoint))
        .sessionManagement((session) -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/aluno/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/comentario/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/curso/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/experiencia/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/turma/**").permitAll()
            .anyRequest().authenticated());

    http.addFilterBefore(jwtAuthenticationFilter(),
        UsernamePasswordAuthenticationFilter.class);

    http.addFilterBefore(emailVerificationFilter(),
        UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  JWTAuthenticationFilter jwtAuthenticationFilter() {
    return new JWTAuthenticationFilter();
  }

  @Bean
  EmailVerificationFilter emailVerificationFilter() {
    return new EmailVerificationFilter();
  }
}