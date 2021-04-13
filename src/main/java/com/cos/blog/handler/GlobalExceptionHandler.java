package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디든 e 발생하면 이 클래스로 매핑
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class)
	// Illegal만 들어오게
	public String handleIllegalArgumentException(IllegalArgumentException e){
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
