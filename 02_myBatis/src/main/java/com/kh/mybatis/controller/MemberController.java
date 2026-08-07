package com.kh.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mybatis.model.dto.MemberDTO;
import com.kh.mybatis.model.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor	// final 이 붙은 필드를 매개변수로 가지는 생성자 생성
public class MemberController {
	
	// MemberMapper 주입 (생성자 주입 방식)
	private final MemberMapper mapper;
	
	/* 직접 생성자를 정의하는 방법 (롬복 사용 X)
	public MemberController(MemberMapper mapper) {
		this.mapper = mapper;
	}
	*/
	/**
	 * 회원 목록 조회
	 * URL : [GET] /member/list
	 * Param : X
	 * 응답 : 회원 목록 페이지 포워딩 (WEB-INF/views/member/list.jsp)
	 */
	@GetMapping("/list")
	public String memberList(Model m) {
		// DB에서 조회 된 회원 목록을 request 영역에 저장 (k:memberList)
		List<MemberDTO> list = mapper.findAll();
		m.addAttribute("memberList", list);
		
//		m.addAttribute("memberList", mapper.findAll());
		
		return "member/list";
	}
	
	/**
	 * 회원 가입 페이지 응답
	 * URL : [GET] /member/insert
	 * Param : X
	 * 응답 : 회원 가입 페이지 포워딩 (WEB-INF/views/member/insertForm.jsp)
	 */
	@GetMapping("/insert")
	public String memberInsert() {
		return "member/insertForm";
	}
	
	/**
	 * 회원 추가
	 * URL : [Post] /member/insert
	 * Param : name(String), email(String), age(int)
	 * 응답 : 회원 목록 페이지로 리다이렉트
	 */
	@PostMapping("/insert")
//	public String insert(@RequestParam(Value="name", defaultvalue="name") String name)
//	public String insert(String name, String email, int age) {
	public String insert(@ModelAttribute MemberDTO member, HttpSession session) {	
		int result = mapper.insert(member);
		
		// 추가 성공 시 "회원 가입 성공했습니다" 메시지를 저장
		// 추가 실패 시 "회원 가입 실패했습니다" 메시지를 저장
			// => redirect 처리 시 재요청 되므로 session 영역에 저장
		if (result>0) session.setAttribute("message", "회원 가입 성공");
		else session.setAttribute("message", "회원 가입 실패");		
		
		return "redirect:/member/list";
	}
	
	/**
	 * 회원 삭제
	 * URL : [GET] /member/delete/{id}
	 * Param : id (int)
	 * 응답 : 회원 목록 페이지로 리다이렉트
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, HttpSession session) {
		int result = mapper.delete(id);
		
		if (result>0) session.setAttribute("message", "회원 삭제 성공");
		else session.setAttribute("message", "회원 삭제 실패");
		
		return "redirect:/member/list";
	}
	
	
	/**
	 * 회원 수정 페이지 응답
	 * URL : [GET] /member/update/{id}
	 * Param : id (int)
	 * 응답 : 회원 수정 페이지로 포워드 (WEB-INF/views/member/update.jsp)
	 */
	@GetMapping("/update/{id}")
	public String memberUpdate(@PathVariable int id, Model m) {
		MemberDTO member = mapper.findMember(id);
		
		m.addAttribute("member", member);
		
		return "member/update";
	}
	
	/**
	 * 회원 수정
	 * URL : [POST] /member/update
	 * Param : MemberDTO
	 * 응답 : 회원 목록 페이지로 리다이렉트
	 */
	@PostMapping("/update")
	public String update(MemberDTO member, HttpSession session) {
		int result = mapper.update(member);
		
		if (result>0) session.setAttribute("message", "회원 수정 성공");
		else session.setAttribute("message", "회원 수정 실패");
		
		return "redirect:/member/list";
	}
}
