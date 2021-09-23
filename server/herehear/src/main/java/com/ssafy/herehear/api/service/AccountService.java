package com.ssafy.herehear.api.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.herehear.api.request.AccountRegisterPostReq;
import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.repository.AccountRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

/**
 *	���� ���� ����Ͻ� ���� ó���� ���� ���� �������̽� ����.
 */
public interface AccountService {
	
	Account createAccount(AccountRegisterPostReq registerInfo);
	Account getAccountByUsername(String username);
	
//	@Autowired
//	private AccountRepository AccountRepository;
//	public Optional<Account> findById(Long id) { 
//		Optional<Account> user = AccountRepository.findById(id); 
//		return user; 
//	}
}
