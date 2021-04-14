let index={
	init: function(){
		$("#btn-save").on("click", ()=>{ // this를 바인딩하기 위해 화살표함수 사용
			this.save();
		});
		$("#btn-login").on("click", ()=>{
			this.login ();
		});
	},
	
	save: function(){
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
			// joinForm 변수에 바인딩
		}
		
		// console.log(data);
		
		// .을 통한 연결: 회원가입 수행 요청-> 정상이면 done-> 실패하면 fail
		$.ajax({
			// object 들어와야 하니까 {} 사용
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), // json 문자열로
			contentType: "application/json; charset=utf-8", // body data type
			dataType: "json" // response type
			// 명시해두면 json타입으로 왔다면 javascript로 변환되어 들어옴
		}).done(function(response){
			console.log(response);
			alert("회원가입이 완료되었습니다.");
//			 location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax 이용 3개의 파라미터 json으로 변경 후 
	
	},
	
	login: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response){
			console.log(response);
			alert("로그인이 완료되었습니다.");
//			 location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	
	}
}

index.init();