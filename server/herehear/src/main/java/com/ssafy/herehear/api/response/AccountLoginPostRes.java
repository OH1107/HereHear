package com.ssafy.herehear.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/v1/auth) ��û�� ���� ���䰪 ����.
 */
@Getter
@Setter
@ApiModel("AccountLoginPostResponse")
public class AccountLoginPostRes extends BaseResponseBody {
	
	@ApiModelProperty(name="JWT ���� ��ū", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;
	
	@ApiModelProperty(name="Refresh ��ū", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String refreshToken;
	
	@ApiModelProperty(name="User PK", example="1")
	Long userId;
	
	@ApiModelProperty(name="Username", example="test")
	String username;
	
	public static AccountLoginPostRes of(Integer statusCode, String message, Long userId, String username, String accessToken, String refreshToken) {
		AccountLoginPostRes res = new AccountLoginPostRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setAccessToken(accessToken);
		res.setRefreshToken(refreshToken);
		res.setUserId(userId);
		res.setUsername(username);
		return res;
	}

}