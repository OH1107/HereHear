package com.ssafy.herehear.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.herehear.api.request.AccountLoginPostReq;
import com.ssafy.herehear.api.response.AccountLoginPostRes;
import com.ssafy.herehear.api.response.BaseResponseBody;
import com.ssafy.herehear.api.service.AccountService;
import com.ssafy.herehear.common.util.CookieUtil;
import com.ssafy.herehear.common.util.JwtTokenUtil;
import com.ssafy.herehear.common.util.RedisUtil;
import com.ssafy.herehear.db.entity.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "ȸ�� ���� API", tags = {"Auth"})
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private CookieUtil cookieUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@PostMapping("/login")
	@ApiOperation(value = "�α���")
	public ResponseEntity<AccountLoginPostRes> login(
			@RequestBody @ApiParam(value="�α��� ����", required=true) AccountLoginPostReq loginInfo, HttpServletResponse res) {
		String username = loginInfo.getUsername();
		String password = loginInfo.getPassword();
		
		Account account = accountService.getAccount(username);
		
		// �α��� ��û�� �����κ��� ��ȿ�� �н��������� Ȯ��
		if (passwordEncoder.matches(password, account.getPassword())) {
			// ��ȿ�� �н������� ���, �α��� ����
			final String jwtToken = jwtTokenUtil.generateToken(account);
			final String refreshJwt = jwtTokenUtil.generateRefreshToken(account);
			
			// ���� ���� ��ū�� ��Ű�� ��ȯ
			Cookie accessToken = cookieUtil.createCookie(JwtTokenUtil.ACCESS_TOKEN_NAME, jwtToken);
            Cookie refreshToken = cookieUtil.createCookie(JwtTokenUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setData(account.getUsername(), refreshJwt);
            // ��Ű�� �߰� (�ȵ���̵忡�� �ʿ������ ���� ����)
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
            redisUtil.setDataExpire(refreshJwt, account.getUsername(), JwtTokenUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            // ���� �����ε� ��ū ���� jwtToken ���
			return ResponseEntity.ok(AccountLoginPostRes.of(200, "Success", jwtToken, refreshJwt));
		}
		
		// ��ȿ���� ���� ���, �α��� ����
		return ResponseEntity.status(401).body(AccountLoginPostRes.of(401, "Invalid Password", null, null));
	}
	
//	�α׾ƿ� ��� ���� �ʿ�
//	@PostMapping("/logout")
//	@ApiOperation(value = "�α׾ƿ�")
//	public ResponseEntity<?> logout(
//			@RequestBody HttpServletResponse res) {
//		
//	}
}
