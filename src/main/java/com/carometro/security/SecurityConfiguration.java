package com.carometro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
  public WebSecurityCustomizer webSecurityCustomizer() {
    // Ignore Swagger and API docs
    return (web) -> web.ignoring().requestMatchers(
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/v3/api-docs/**",
        "/webjars/**" // Additional paths for Swagger resources
    );
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .exceptionHandling((eH) -> eH.authenticationEntryPoint(authEntryPoint))
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/auth/**").permitAll() // Public endpoint for authentication

            // Uncomment the lines below to enable authentication for all requests except
            // GET requests.
            // .requestMatchers(HttpMethod.GET, "/aluno/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/comentario/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/curso/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/experiencia/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/turma/**").permitAll()

            // Uncomment the lines below to disable authentication.
            .requestMatchers("/aluno/**").permitAll()
            .requestMatchers("/comentario/**").permitAll()
            .requestMatchers("/curso/**").permitAll()
            .requestMatchers("/experiencia/**").permitAll()
            .requestMatchers("/turma/**").permitAll()

            .anyRequest().authenticated()); // Protect all other endpoints

    // Add custom filters before UsernamePasswordAuthenticationFilter
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
