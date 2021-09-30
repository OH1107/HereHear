package com.ssafy.herehear.api.response;

import com.ssafy.herehear.db.entity.Account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/v1/auth) ��û�� ���� ���䰪 ����.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("AccountLoginPostResponse")
public class AccountRes extends BaseResponseBody {
	
	@ApiModelProperty(name="Username", example="ssafy")
	String username;
	
	public static AccountRes of(Account account) {
        return new AccountRes(account.getUsername());
    }
}