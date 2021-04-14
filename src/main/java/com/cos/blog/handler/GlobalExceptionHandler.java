package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // 어디든 e 발생하면 이 클래스로 매핑
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	// Illegal만 들어오게
//	public String handleIllegalArgumentException(IllegalArgumentException e) {
//		return "<h1>" + e.getMessage() + "</h1>";
//	}
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
