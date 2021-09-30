package com.ssafy.herehear.api.service;

import com.ssafy.herehear.api.request.AccountReq;
import com.ssafy.herehear.api.response.AccountRes;
import com.ssafy.herehear.common.jwt.TokenDto;
import com.ssafy.herehear.common.jwt.TokenReqDto;

/**
 *	���� ���� ����Ͻ� ���� ó���� ���� ���� �������̽� ����.
 */
public interface AuthService {
	
	AccountRes signup(AccountReq accountReq);
	TokenDto login(AccountReq accountReq);
	TokenDto reissue(TokenReqDto toeknReqDto);
}
