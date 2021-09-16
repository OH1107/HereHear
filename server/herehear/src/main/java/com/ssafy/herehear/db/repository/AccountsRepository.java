package com.ssafy.herehear.db.repository;

// import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.herehear.db.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ���� �� ���� ��� ���� ������ ���� JPA Query Method �������̽� ����.
 */
//JpaRepository ��ӹ����鼭 ���׸��� ����� entity�� IDŸ���� �� �� �ִ� Ŭ���� 
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	// �Ʒ��� ����, Query Method �������̽�(��ȯ��, �޼ҵ��, ����) ���Ǹ� �ϸ� �ڵ����� Query Method ������.
	// �ʵ� �˻��� ���ؼ� �޼��� �̸����� ���� ���� ���� ���ξ�� findBy�� ����ؾ���
	
	public List<Accounts> findById(String id);


}