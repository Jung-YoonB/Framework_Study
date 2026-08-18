package com.kh.community.board.model.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 자바스크립트에서 JSON 문자열로 전달 한 데이터를 컨트롤러에서 @RequestBody 로 받으면,
 * JSON 변환 라이브러리(Jackson)가 전달 된 키 값을 DTO의 필드로 변환 해 줌
 */
@Getter
@Setter
public class CommentRequest {
	private String content;
}
