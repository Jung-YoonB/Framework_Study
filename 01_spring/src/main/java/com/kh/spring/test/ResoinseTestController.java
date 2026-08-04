package com.kh.spring.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ResoinseTestController {
	// ============ 응답 방식 ============

		/*
		 * @ResponseBoby
		 * 	- 화면(view) 없이 결과를 바로 텍스트로 응답
		 */
		@ResponseBody
		@GetMapping		// => /test
		public String responseBodyTest() {
			return "결과를 텍스트로 응답";
		}
		
		// 기본적으로 forward 방식으로 응답 처리 됨
		@GetMapping("/index")	// => /test/index
		public String responseIndex() {
			
			// prefix, suffix
			// {prefix}리턴값{suffix}
			//	=> /WEB-INF/view/페이지경로.jsp 이 파일을 ViewResolver 가 매칭시켜 응답해줄 것
			// return "forward 처리할 페이지 경로";
			return "test/index"; // => /WEB-INF/view/test/index.jsp
		}
}
