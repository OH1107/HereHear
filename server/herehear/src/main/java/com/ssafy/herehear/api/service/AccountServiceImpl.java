package com.ssafy.herehear.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.herehear.api.request.AccountRegisterPostReq;
import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.repository.AccountRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
		
	@Override
	public Account createAccount(AccountRegisterPostReq registerInfo) {
		Account account = new Account();
		account.setUsername(registerInfo.getUsername());
		// ������ ���ؼ� ���� �н����� ��ȣȭ �Ͽ� ��� ����.
		account.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		
		// dsl ���
		return accountRepository.save(account);
	}
	
	@Override
	public Account getAccount(String username) {
		// DB�� ���̵� �������� ���� ���� ��ȸ
		Account account = accountRepository.findByUsername(username).get();
		return account;
	}
	
	@Override
	public Account findByAccountId(Long id) {
		Account account = accountRepository.findById(id).get();
		return account;
	}
}
