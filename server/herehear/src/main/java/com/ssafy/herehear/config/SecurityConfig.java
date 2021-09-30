package com.ssafy.herehear.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.ssafy.herehear.common.jwt.JwtAccessDeniedHandler;
import com.ssafy.herehear.common.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.herehear.common.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CorsFilter corsFilter;
	private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	// Password ���ڵ� ��Ŀ� BCrypt ��ȣȭ ��� ���
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // swagger �׽�Ʈ�� ��Ȱ�ϵ��� ���� API ���� ���� ����
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/v2/api-docs",
            			"/configuration/ui",		                   
            			"/swagger-resources/**",
	                    "/configuration/security",
	                    "/swagger-ui.html",
	                    "/webjars/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF ���� Disable
    	http.csrf().disable()
    	
    		// exception hanlding �Ҷ� ���� ���� Ŭ���� �߰�
    		.exceptionHandling()
    		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
    		.accessDeniedHandler(jwtAccessDeniedHandler)
    		
    		// ��ť��Ƽ�� �⺻������ ���� ���
    		// �ٵ� �츮�� ���� ����� ���Ұ��̹Ƿ� stateless�� ����
    		.and()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		
    		// �α���, ȸ������ API�� ��ū ��� ��û�� �����ؾ� ��
    		.and()
    		.authorizeRequests()
    		.antMatchers("/api/v1/auth/**").permitAll()
    		.antMatchers("/swagger-ui/**").permitAll()
    		.anyRequest().authenticated()	// ������ API�� ���� ���� �ʿ�
    		
    		// JwtFilter�� addFilterBefore�� ����ߴ� JwtSecurityConfig Ŭ���� ����
    		.and()
    		.apply(new JwtSecurityConfig(tokenProvider));
    }
}
