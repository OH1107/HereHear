package com.ssafy.herehear.db.entity;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * ���� �� ����.
 */
//Entity�� ���� Ŭ������ JPA�� �����ϴ� Ŭ�����̰�, ���̺�� ������ ���̺� �ش� ������̼��� ���δ�.
@Entity
@Getter
@Setter
public class Accounts extends BaseEntity {
	private String username;

	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
}