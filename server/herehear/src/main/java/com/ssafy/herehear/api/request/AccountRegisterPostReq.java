package com.ssafy.herehear.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ���� ȸ������ API ([POST] /api/v1/users) ��û�� �ʿ��� ������Ʈ �ٵ� ����.
 */
@Getter
@Setter
@ApiModel("AccountRegisterPostRequest")
public class AccountRegisterPostReq {
	
	@ApiModelProperty(name="Index", readOnly=true)
	private Long user_id;
	
	@ApiModelProperty(name="Username")
	private String username;

	@ApiModelProperty(name="Password")
	private String password;
	
}
