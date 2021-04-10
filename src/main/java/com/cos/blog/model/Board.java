package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob
	private String content; // 섬머노트 라이브러리-> html 섞여서 디자인 되어 용량 커져서 Lob으로

	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne (fetch=FetchType.EAGER)// 연관관계 설정. Board(Many), User(One)
	// 한 명의 유저는 여러 개의 게시글 작성 가능하다. (OneToOne 등 도 있음)
	// 자동으로 외래키 설정된다고 보면 됩니다
	// EAGER 그냥 들고 와
	@JoinColumn(name="userId")
	private User user;
	// ORM에서는 외래키 사용이 아니라 User 타입 사용 (오브젝트 저장 가능하므로)
	// 테이블 어떻게 인식하나? 어노테이션 사용해야
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER) // 연관관계의 주인x = 외래키 x = db에 컬럼 생성하지마
	// 참조키라는 뜻인가?....
	// LAZY 맞으면 가져오고 아니면 말아 (댓글 펼치기면 이렇게 해도 됨)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
