package com.ssafy.herehear.db.repository;

// import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.herehear.db.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ���� �� ���� ��� ���� ������ ���� JPA Query Method �������̽� ����.
 */
//JpaRepository ��ӹ����鼭 ���׸��� ����� entity�� IDŸ���� �� �� �ִ� Ŭ���� 
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	// �Ʒ��� ����, Query Method �������̽�(��ȯ��, �޼ҵ��, ����) ���Ǹ� �ϸ� �ڵ����� Query Method ������.
	// �ʵ� �˻��� ���ؼ� �޼��� �̸����� ���� ���� ���� ���ξ�� findBy�� ����ؾ���
	
	public List<Account> findById(String id);


}