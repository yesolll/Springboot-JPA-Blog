package com.cos.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//{id} 주소로 파라미터를 전달받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { // 주소로 들어온 id를 받음
//		User user = userRepository.findById(id);
		 // CurdRepository의 findById() 리턴타입은 Optional임!
		// optional은 db에서 찾는 값 null이라던가 오류 날 때를 대비해 optional로 가져와 컨버팅 필요

//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
//			}
//		});
				//.get()은 무조건 가져오기(위험)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다. id: " + id);
		});
		
		return user;
	}

	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지 당 두 건의 데이터를 받아볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=1, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		List<User>users = pagingUsers.getContent();
		return users;
	}
	
	// email, password 수정해보기
	@Transactional
	@PutMapping("/dummy/user/{id}") // 주소 같아도 annotation 달라서 괜찮음
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id: " + id);
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email: " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// save 함수 사용 시 해당 id에 대한 데이터 있으면 insert 아닌 update 됨
		// userRepository.save(user);
		// ㄴ @Transactional 걸면이렇게 주석처리 해도 update가 된다 -> 더티 체킹
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
		userRepository.deleteById(id);
		}catch(Exception e) {
		return "삭제 실패."+ id + "은(는) 없는 아이디 입니다.";
		}
		return "삭제되었습니다. id:" + id;
	}
	
}
