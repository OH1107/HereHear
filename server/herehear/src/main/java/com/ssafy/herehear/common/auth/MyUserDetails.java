package com.ssafy.herehear.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.herehear.db.entity.Account;

/**
 * 
 * ���� ������ ��ū���κ��� ������ ������ �ΰ� ������(Ȱ��ȭ ����, ����, �� ��) ����
 *
 */
@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails {
	
	@Autowired
	Account account;
	boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    List<GrantedAuthority> roles = new ArrayList<>();
    
    public MyUserDetails(Account account) {
		super();
		this.account = account;
    }
    
    public Account getUser() {
		return this.account;
    }
    
    @Override
	public String getPassword() {
		return this.account.getPassword();
	}
    
	@Override
	public String getUsername() {
		return this.account.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
}
