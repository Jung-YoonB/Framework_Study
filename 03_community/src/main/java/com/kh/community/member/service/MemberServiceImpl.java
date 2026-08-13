package com.kh.community.member.service;

import java.io.IOException;
import java.nio.channels.IllegalSelectorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.community.common.util.FileUploadUtil;
import com.kh.community.common.util.SavedFile;
import com.kh.community.member.model.dto.MemberDTO;
import com.kh.community.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	// FileUploadUtil 을 DI처리 (생성자 주입 방식, 롬복 사용)
	private final FileUploadUtil uploadUtil;
	// MemberMapper DI
	private final MemberMapper mapper;
	// PasswordEncoder DI
	private final PasswordEncoder passwordEncoder;
	
	@Value("file.upload-dir.profile")
	private String profileUploadDir;
	
	@Override
	public void join(MemberDTO member, MultipartFile profileImage) throws IOException {
		// 아이디 중복 검사
		if (isMemberIdCheck(member.getMemberId())) {
			throw new IllegalStateException("이미 사용 중인 아이디입니다.");
		}
				
		// 비밀번호 암호화 처리 -> BCryptPasswordEncoder => SecurityConfig 설정
		// * 암호화 : passwordEncoder.encode(입력받은_비밀번호_평문)
		String encodePwd = passwordEncoder.encode(member.getMemberPwd());
		member.setMemberPwd(encodePwd); // 비밀번호 필드를 암호화 된 값으로 변경
		
		// 프로필 이미지 파일을 서버에 저장 --> 공통 클래스로 분리
		SavedFile saved = uploadUtil.save(profileImage, profileUploadDir, "/uploads/profile");
		
		// 저장 값이 null 이 아니면 저장 된 경로를 dto 설정
		if (saved != null) member.setProfile(saved.getPath());
		
		// TB_MEMBER 테이블("DB")에 데이터 저장 --> Mapper
		mapper.insertMember(member);
	}

	@Override
	public boolean isMemberIdCheck(String memberId) {
		// 중복 된 아이디가 있을 경우 => 1개 일 것
		return mapper.countByMemberId(memberId) > 0;
		
	}

	@Override
	public MemberDTO login(String memberId, String memberPwd) throws IllegalStateException {
		// 아이디를 기준으로 회원 정보를 조회
		MemberDTO member = mapper.selectByMemberId(memberId);
		
		// 조회 된 정보 중 비밀번호(암호문)와 전달 된 비밀번호(평문)가 일치하는 지 확인
		// 암호화 된 비밀번호 => DB에서 조회 한 값 (member.getMemberPwd())
		// 평문 비밀번호 => 전달 된 값 (memberPwd)
		
		// passwordEncofr.matches(평문, 암호문) => 동일한 경우 true, 그렇지 않으면 false
		if (member == null || !passwordEncoder.matches(memberPwd, member.getMemberPwd())) {
			throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 회원 정보를 반환
		return member;
	}

	@Override
	public void withdraw(String memberId) {
		// TODO Auto-generated method stub
		
	}

}
