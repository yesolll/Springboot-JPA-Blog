package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne // 한 게시글에 여러 답변 가능
	@JoinColumn(name="boardId")
	private Board board;
	// Board 타입이기 때문에 알아서 그 테이블과 연결될 수 있는 것
	
	@ManyToOne // 한 유저가 여러 답변 가능
	@JoinColumn(name="userId")
	private User user;
	
	//boardId, userId int로 만들어질 것: 왜?  두 테이블의 id가 int기 때문
	
	@CreationTimestamp
	private Timestamp createDate;
}
