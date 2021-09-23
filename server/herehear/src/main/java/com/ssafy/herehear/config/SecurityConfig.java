package com.ssafy.herehear.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CorsFilter corsFilter;
	
	// Password ���ڵ� ��Ŀ� BCrypt ��ȣȭ ��� ���
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(corsFilter)
                .httpBasic().disable()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ��ū ��� �����̹Ƿ� ���� ��� ��������
                //.and()
                // .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService)) //HTTP ��û�� JWT ��ū ���� ���͸� ��ġ���� ���͸� �߰�
                .authorizeRequests()
                //.antMatchers("/api/v1/users/me").authenticated()       //������ �ʿ��� URL�� �ʿ����� ���� URL�� ���Ͽ� ����
    	        	    .anyRequest().permitAll()
                .and().cors();
    }
}
