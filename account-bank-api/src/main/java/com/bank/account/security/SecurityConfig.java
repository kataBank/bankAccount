package com.bank.account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private UserDetailsService userDetaitlsService;
  @Autowired private BCryptPasswordEncoder bcrytPasswordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetaitlsService).passwordEncoder(bcrytPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers(
            "/login/**",
            "/static/**",
            "/resources/static/**",
            "/api/**",
            "/",
            "/**.ico",
            " /**.map",
            "/**.js",
            "/assets/logo/**.jpg",
            "/**",
            "/operation",
            "/client",
            "/index.*")
        .permitAll()
        .antMatchers("/h2-console/**")
        .permitAll();
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  }


 /* public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/account").setViewName("account");
    registry.addViewController("/aperation").setViewName("operation");
    registry.addViewController("/client").setViewName("client");
    registry.addViewController("/login").setViewName("login");
  }*/
}
