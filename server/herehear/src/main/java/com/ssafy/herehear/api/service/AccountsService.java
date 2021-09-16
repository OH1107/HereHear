package com.ssafy.herehear.api.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.herehear.db.entity.Accounts;
import com.ssafy.herehear.db.repository.AccountsRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

/**
 *	���� ���� ����Ͻ� ���� ó���� ���� ���� �������̽� ����.
 */
@Service
public class AccountsService {
	@Autowired
	private AccountsRepository AccountsRepository;
	public Optional<Accounts> findById(Long id) { 
		Optional<Accounts> user = AccountsRepository.findById(id); 
		return user; 
	}
}
