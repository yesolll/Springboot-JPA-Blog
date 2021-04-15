package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// Bean으로 등록이 되나요?(IoC, DI) -> 자동으로 등록 되어 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer>{
	// 해당 jpa rep는 user테이블 관리, 그 pk는 integer
	
}
