package com.ssafy.herehear.api.service;

import com.ssafy.herehear.api.response.AccountRes;

/**
 *	���� ���� ����Ͻ� ���� ó���� ���� ���� �������̽� ����.
 */
public interface AccountService {
	
	AccountRes getAccountInfo(String username);
	AccountRes getMyInfo();
	
//	@Autowired
//	private AccountRepository AccountRepository;
//	public Optional<Account> findById(Long id) { 
//		Optional<Account> user = AccountRepository.findById(id); 
//		return user; 
//	}
}
