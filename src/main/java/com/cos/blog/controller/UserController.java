package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//	인증이 안 된 사용자도 출입 가능ㅎ안 경로를 /auth/로 허용
//	그냥 주소가 / 이면 index.jsp 허용
//	static이하 폴더 허용
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
