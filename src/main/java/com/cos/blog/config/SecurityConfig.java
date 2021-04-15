package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // Bean 등록
@EnableWebSecurity // Filtering (security라는 필터를 추가) = 활성화 되어있는 시큐리티에 대한 설정을 이 파일에서 할 것을 명시
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정주소로 접근 시 권한 및 인증 미리 체크할 것
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() // request 들어오면
				.antMatchers("/auth/**")
				.permitAll()
				.anyRequest() // 이게 아닌 다른 모든 요청은
				.authenticated()
			.and()
				.formLogin() // 로그인 필요한 페이지는 폼으로 연결
				.loginPage("/auth/loginForm");
	}

}
