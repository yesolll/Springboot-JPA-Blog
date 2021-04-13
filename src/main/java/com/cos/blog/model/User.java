package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 클래스를 테이블화 시키기 위한 어노테이션
// User 클래스가 MySQL에 테이블로 생성
// ***ORM이란????? JAVA(언어) -> 테이블로 매핑해주는 기술!!
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert 시 null인 필드 제외
public class User {
	
	@Id //primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// IDENTITY -> SEQUENCE, TABLE(테이블 번호), AUTO 등
	private int  id; // 오라클로 치면 시퀀스, mysql은 auto_increment
	
	@Column(nullable=false, length=30, unique=true)
	private String username; // 아이디
	
	@Column(nullable=false, length=100) // hash로 변경해서 암호화 할 것
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
//	@ColumnDefault("'user'")
	//Enum을 쓰는 게 좋다. String 쓰면 오타날 수도 있는데 Enum은 도메인 설정 가능(범위가 있다는 뜻)
	@Enumerated(EnumType.STRING)
	private RoleType role; // 권한(admin, user, manager)
	
	@CreationTimestamp
	private Timestamp createDate;
	
	// select_test 테스트
}
