package com.ssafy.herehear.db.entity;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ���� �� ����.
 */
//Entity�� ���� Ŭ������ JPA�� �����ϴ� Ŭ�����̰�, ���̺�� ������ ���̺� �ش� ������̼��� ���δ�.
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {
	
	private String username;

	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Enumerated(EnumType.STRING)
    private Authority authority;
	
	@Builder
    public Account (String username, String password, Authority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
	
	public enum Authority {
	    ROLE_USER, ROLE_ADMIN
	}
	
}