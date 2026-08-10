package com.kh.community.member.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MemberDTO {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String nickname;
	private String email;
	private String profile;
	private LocalDateTime createAt;
	
	// 화면 표시용 문자열 변수 (JSP 에서는 Date만 형식을 사용할 수 있음)
		// LocalDateTime을 사용하려고 하면 코드가 시저분해질 수 있음
	private String createAtStr;
	
}
