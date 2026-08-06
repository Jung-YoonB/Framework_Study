package com.kh.spring.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service	// @Component + 이 클래스가 비즈니스 로직 계층임을 나타냄
public class MemberService {
	
	//(DI, 의존성주입) MemberDAO -> 생성자 주입방식
	private final MemberDAO dao;
	
	public MemberService(MemberDAO dao) {
		this.dao = dao;
	}
	
	// 회원 목록 조회
	// -> DB에서 조회 된 결과(List)를 반환
	public List<MemberDTO> getMemberList() {
		return dao.findAll();
	}
	
	// 회원 정보 추가
	// -> 회원 정보(DTO)를 전달 받아 DB에 추가
	public void insertMember(MemberDTO member) {
		dao.insert(member);
	}
	
	// 회원 정보 삭제
	// -> 회원 번호(id)를 전달 받아 DB에서 삭제
	public void deleteMember(int id) {
		dao.delete(id);
	}
	
	// 회원 정보 수정
	public void updateMember(MemberDTO member) {
		dao.update(member);
	}
	
	// 회원 정보 조회
	//	-> 회원 번호를 전달 받아서 해당 회원 정보를 DB에서 조회 후 반환
	public MemberDTO getMember(int id) {
		return dao.findById(id);
	}
}
