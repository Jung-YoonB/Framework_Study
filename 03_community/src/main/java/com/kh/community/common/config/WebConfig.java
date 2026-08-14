package com.kh.community.common.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.community.common.interceptor.LoginInterceptor;

/*
 * [WebMvcConfigurer]
 * 	- Spring MVC의 공통 설정 인터페이스
 * 
 * 	* 업로드 된 이미지 매핑
 * 		- 업로드 된 이미지 파일은 src/main/resources/static 폴더가 아니라
 * 		  별도의 경로(uploads)로 저장 됨
 * 		  	--> 특정 주소(/uploads/**)로 요청 했을 때 실제 파일이 저장 된 경로로 연결
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		String uploadDir = "uploads";
		
		
		String absoluteDir = new File(uploadDir).getAbsolutePath();
		
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:" + absoluteDir + File.separator);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/member/mypage", "/member/withdraw");
	}
	
	
}
