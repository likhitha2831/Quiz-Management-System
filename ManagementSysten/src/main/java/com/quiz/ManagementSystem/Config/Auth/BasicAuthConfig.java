package com.quiz.ManagementSystem.Config.Auth;

import com.quiz.ManagementSystem.User.User;
import com.quiz.ManagementSystem.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicAuthConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    private static String ADMIN_ROLE = "ADMIN";
    private static String USER_ROLE = "USER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/createUser").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/users/admin").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/userDetails").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/accounts/createAccount").permitAll()
                .antMatchers(HttpMethod.PUT, "/accounts/credit").permitAll()
                .antMatchers(HttpMethod.PUT, "/accounts/debit").permitAll()
                .antMatchers(HttpMethod.POST, "/question").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .cors().and()
                .csrf().disable();
    }
}
