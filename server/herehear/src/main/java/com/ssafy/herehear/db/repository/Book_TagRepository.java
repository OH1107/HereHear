package com.ssafy.herehear.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.herehear.db.entity.Book_Tag;

@Repository
public interface Book_TagRepository extends JpaRepository<Book_Tag, Long> {

}