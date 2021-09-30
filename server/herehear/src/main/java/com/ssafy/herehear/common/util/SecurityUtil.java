package com.ssafy.herehear.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {
	
	private SecurityUtil() {}
	
	// SecurityContext �� ���� ������ ����Ǵ� ����
    // Request �� ���� �� JwtFilter �� doFilter ���� ����
	public static Long getCurrentAccountId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context �� ���� ������ �����ϴ�.");
        }

        return Long.parseLong(authentication.getName());
    }
}
