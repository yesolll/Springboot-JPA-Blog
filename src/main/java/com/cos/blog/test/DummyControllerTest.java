package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired  // DI
	private UserRepository userRepository;
	
	// localhost:8000/blog/dummy/join (request)
	// http의 body에 username, password, email 데이터를 가지고 request -> 파라미터로
	@PostMapping("/dummy/join")
	public String join(User user) {
//	public String join(@RequestParam("username") un, String password, String email) {
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		
		System.out.println("id: " + user.getId());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
