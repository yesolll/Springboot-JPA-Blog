package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
// @Data  // (getter, setter 동시에)
// @AllArgsConstructor 생성자
// @ RequiredArgsConstructor // final 붙은 애들에 대한 생성자

@Data
// @AllArgsConstructor 생성자 직접 작성해봄
@NoArgsConstructor // bean 생성자

public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	// 원래 builder 패턴을 만들어야 하는데 어노테이션 하나로 가능.
	// 순서 지키지 않아도 상관 없음; 생성자를 통해 넣을 때는 순서 지켜야!~
	
	
}
