package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청 가로채서 진행 후 완료되면 UserDetails 타입의 오브젝트를 시큐리티 세션에 저장. 
// 그 때 이 PrincipalDetail 타입으로 저장할 것
public class PrincipalDetail implements UserDetails {

	private User user; // 컴포지션 : 객체를 품고 있음

	public PrincipalDetail(User user) {
		this.user = user;
	}
	// 계정 권한을 리턴 어떤 권한을 가졌는지
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>(); // AL 부모에 Collection 있음 = 컬렉션 타입임
//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole(); // 꼭 ROLE_이라고 prefix표기해야 인식이 된다!
//			}
//		});
		// 위 주석의 람다식표현
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collectors;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠기지 않았는지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비번 만료여부
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 사용가능 여부 (활성화 여부)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
