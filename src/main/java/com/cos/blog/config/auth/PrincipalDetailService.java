package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// 스프링이 로그인 요청 가로챌 때 username, password 변수 두 개를 가로 채는데
	// password 처리는 알아서. username이 db에 있는 지 확인 후 리턴
	// ㄴ 알아서-> config에 auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: " + username);
		});
		return new PrincipalDetail(principal);
	}

}
