package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // Bean 등록
@EnableWebSecurity // Filtering (security라는 필터를 추가) = 활성화 되어있는 시큐리티에 대한 설정을 이 파일에서 할 것을 명시
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정주소로 접근 시 권한 및 인증 미리 체크할 것
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // return값 스프링이 관리, 갖다 쓰면 됨(DI)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인 해줄 때 password 가로채기를 하는데 해당 비번이 해쉬화 어떻게 됐는지 알아야 db랑 비교 가능
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는 게 좋음)
			.authorizeRequests() // request 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest() // 이게 아닌 다른 모든
				.authenticated()
			.and()
				.formLogin() // 로그인 필요한 페이지는 폼으로 연결
				.loginPage("/auth/loginForm").loginProcessingUrl("/auth/loginProc") // 해당 주소로 오면 시큐리티가 로그인 가로채서 대신 로그인
				.defaultSuccessUrl("/") // 위에 있는 페이지가 아닌 auth처리 안 된 페이지는 모두 여기로
//				.failureUrl("/fail") //실패하면 여기로;
		;
	}

}
