package com.kh.community.member.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.community.member.model.dto.MemberDTO;
import com.kh.community.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/*
 * 회원 관련 화면 이동, 폼 처리 등을 담당 할 컨트롤러
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	// MemberService 를 DI 처리 (생성자 주입 방식)
	private final MemberService service;
	
	
	// ------ 화면 이동 요청 ------
	@GetMapping("/join")
	public String joinForm() {
		return "member/join";
	}
	
	@GetMapping("login")
	public String loginForm() {
		return "member/login";
	}
	
	// ------------------------
	
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDTO member, MultipartFile profileImage, RedirectAttributes redirectAttr){
		System.out.println(member);
		System.out.println(profileImage);
		
		try {
			
			service.join(member, profileImage);
			
		} catch (IOException e) {
			e.printStackTrace();
			// "회원 가입 실패" 메시지를 저장 ---> 클라이언트에서 사용
			// * 세션 영역에 저장 (HttpSession)
//			session.setAttribute("message", "회원 가입 실패");
			// * 리다이렉트 후 딱 한번 다음 요청에서만 사용 되는 데이터 => RedirectAttributes
			redirectAttr.addFlashAttribute("error", "회원 가입 실패");
			
			// 예외 발생 시 회원 가입 페이지로 리다이렉트
			return "redirect:/member/join";
		}
		
		// 회원 가입 성공 시 로그인 페이지로 리다이렉트
		redirectAttr.addFlashAttribute("joinSuccess", true);
		return "redirect:/member/login";
	}

}
