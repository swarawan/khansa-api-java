package com.swarawan.khansapos.security;

import com.swarawan.khansapos.exception.CustomAccessDeniedHandler;
import com.swarawan.khansapos.exception.CustomAuthEntryPoint;
import com.swarawan.khansapos.security.jwt.JwtConfigurer;
import com.swarawan.khansapos.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//                .and()
//                    .exceptionHandling().authenticationEntryPoint(customAuthEntryPoint)
//                .and()
//                    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)

                .and().authorizeRequests()
                    .antMatchers("/api/v1/credential/**").permitAll()
                    .anyRequest().authenticated()

                .and()
                .apply(new JwtConfigurer(tokenProvider));
    }
}
