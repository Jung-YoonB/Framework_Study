package com.kh.mybatis.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// 기본 생성자
@AllArgsConstructor		// 모든 필드 매개변수 생성자
@Getter					// getter
@Setter					// setter
@ToString				// toString
//@EqualsAndHashCode
public class MemberDTO {
	private int id;
	private String name;
	private String email;
	private int age;
}
