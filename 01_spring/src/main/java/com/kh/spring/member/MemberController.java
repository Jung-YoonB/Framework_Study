package com.kh.spring.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller	// @Component + 컨트롤러 계층의 기능이 추가 된 어노테이션
			// -> 이 클래스의 메소드가 반환하는 문자열은 "View" 의 이름으로 해석 됨(포워드)
@RequestMapping("/member")	// 클래스 레벨의 공통 URL 지정
							// -> 내부 메소드들은 매핑 URL 앞에 "/member"가 붙음
public class MemberController {
	
	// MemberService 클래스를 주입(생상자 주입 방식)
	private final MemberService service;
	
	// @Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
	/**
	 * 회원 목록 조회
	 * URL : [GET] /member/list
	 */
	@GetMapping("/list")
	public String memberList(Model m) {
		List<MemberDTO> list = service.getMemberList();
		
		// 조회 된 결과(list)를 request 영역에 저장 (k: memberList)
		m.addAttribute("memberList", list);
		
		// 포워드 처리 됨
		return "/member/list";	// => /WEB-INF/views/member/list.jsp
	}
	
	
	/**
	 * 회원 등록
	 * URL : [POST] /member/insert
	 * Parameter : age, email, name
	 */
	@PostMapping("/insert")
	public String insert(@ModelAttribute MemberDTO member) {
		return "";
	}
	
	
	/**
	 * 회원 삭제
	 * URL : [GET] /member/delete/{id}
	 */
	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteMember(id);
		return "redirect:/member/list";
	}
	
}
