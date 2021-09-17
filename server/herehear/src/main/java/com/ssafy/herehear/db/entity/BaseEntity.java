package com.ssafy.herehear.db.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

// �� �� ������� ����
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	// ���� �Ӽ�
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	// Column
	private Long id;
}