package com.ssafy.herehear.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/v1/auth/login) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("AccountLoginPostRequest")
public class AccountLoginPostReq {
	
	@ApiModelProperty(name="Username")
	String username;
	
	@ApiModelProperty(name="Password")
	String password;

}
