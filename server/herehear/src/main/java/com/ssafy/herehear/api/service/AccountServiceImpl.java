package com.ssafy.herehear.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.herehear.api.response.AccountRes;
import com.ssafy.herehear.common.util.SecurityUtil;
import com.ssafy.herehear.db.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service("accountService")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional(readOnly=true)
	public AccountRes getAccountInfo(String username) {
		return accountRepository.findByUsername(username)
				.map(AccountRes::of)
				.orElseThrow(() -> new RuntimeException("���� ������ �����ϴ�."));
	}
	
	// ���� SecurityContext �� �ִ� ���� ���� ��������
	@Transactional(readOnly = true)
    public AccountRes getMyInfo() {
        return accountRepository.findById(SecurityUtil.getCurrentAccountId())
                .map(AccountRes::of)
                .orElseThrow(() -> new RuntimeException("�α��� ���� ������ �����ϴ�."));
    }
	
	
}
