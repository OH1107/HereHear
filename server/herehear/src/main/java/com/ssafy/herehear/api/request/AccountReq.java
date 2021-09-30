package com.ssafy.herehear.api.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.entity.Account.Authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/v1/auth/login) ��û�� �ʿ��� ������Ʈ �ٵ� ����.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("AccountLoginPostRequest")
public class AccountReq {
	
	@ApiModelProperty(name="Username")
	String username;
	
	@ApiModelProperty(name="Password")
	String password;
	
	public Account toAccount(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
