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
	
	public static AccountLoginPostRes of(Integer statusCode, String message, String accessToken) {
		AccountLoginPostRes res = new AccountLoginPostRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setAccessToken(accessToken);
		return res;
	}

}
