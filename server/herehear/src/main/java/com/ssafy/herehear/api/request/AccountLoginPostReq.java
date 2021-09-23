package com.ssafy.herehear.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/v1/auth/login) ��û�� �ʿ��� ������Ʈ �ٵ� ����.
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
