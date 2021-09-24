package com.ssafy.herehear.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyCorsFilter extends CorsFilter {
	
	//���Ͱ� ������ �� ���͸� ����
	public MyCorsFilter() {
		super(configurationSource());
	}

	//@Crossorigin���� ó���� �� ���� security�� ���� cors�� �н���Ŵ
	private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //�ڰ����� ��� 
        config.addAllowedOriginPattern("*"); //�ڰ������� ����� �� ��ó�� ����ϴ� �޼ҵ�
        config.addAllowedHeader("*"); //����� ��� ����
        config.addAllowedMethod("*"); //����� ��û (get post put delete ���)
        config.addExposedHeader("Authorization"); //������ ��� ���� ���� authorization�� ������ �ȵǼ� ��������� �����ؾ� ������
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); //corsconfiguration�� url��� �������� ����ϱ����� ����ü 
        source.registerCorsConfiguration("/**", config); //���� url ���� , cors���� ��ü
        return source;
    }

}
