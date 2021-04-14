package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶여, 전체가 성공하면 commit, 실패하면 rollback 될 것 (짜야 됨)
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true) // select시 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성 유지): 여러 번 해도 같은 데이터 찾아짐
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
