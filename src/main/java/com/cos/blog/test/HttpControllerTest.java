package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 사용자가 요청->응답(Data를 응답)
public class HttpControllerTest {
	
	// http://localhost:8080/http/get
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.(postman으로 요청해봄)
	
	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m1 = Member.builder().username("yesol").password("1234").email("yesol.naver.com").build();
	
		System.out.println(TAG+"getter: "+m1.getId());
		m1.setId(5000);
		System.out.println(TAG+"setter: "+ m1.getId());
		return "lombok test 완료";
		
		
	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청: "+ m.getId() + "/" + m.getUsername() +"/" + m.getPassword() + "/" + m.getEmail();
	}
	
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { 
		return "post 요청: "+ m.getId() + "/" + m.getUsername() +"/" + m.getPassword() + "/" + m.getEmail();
	}
	
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" +  m.getId() + "/" + m.getUsername() +"/" + m.getPassword() + "/" + m.getEmail();
	}
	
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
