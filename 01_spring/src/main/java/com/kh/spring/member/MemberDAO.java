package com.kh.spring.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.spring.util.DBUtil;

// DAO : 실제 DB에 접근하는 객체
@Repository	// Component +DB 접근 계층임을 의미함
public class MemberDAO {
	
	// 회원 목록 조회
	// member 테이블 전체 목록을 조회 한 결과 반환 메소드
	public List<MemberDTO> findAll() {
		List<MemberDTO> list = new ArrayList<>();
		
		String sql = "SELECT ID, Name, Email, Age FROM member ORDER BY ID";
		
		// * JDBC 실행 순서
		//	0) 드라이버 로드 (환경에 따라 생략 가능)
		//	1) Connection 객체 생성
		// ------------ 여기까지는 DBUtil 에서 작성 함 ------------
		//	2) PreparedStatement 객체 생성
		//	3) 쿼리문 실행 후 결과 받기
		//		--> 추출 및 처리 작업
		//	4) 자원 반납 (try with resources 구문 사용으로 생략 가능)
		try (Connection c = DBUtil.getDBConn()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberDTO m = new MemberDTO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getInt("age")
						);
				
				list.add(m);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 회원 추가
	// 전달 된 회원 정보를 member 테이블에 추가하는 메소드
	public void insert(MemberDTO member) {
		// 전달 된 회원 정보 (이름, 이메일, 나이)
		// 	--> 회원 번호 (시퀀스)
		String sql = "INSERT INTO member (id, name, email, age) VALUES (seq_member_id.nextval, ?, ?, ?)";
		
		// Connection 객체 생성, PreparedStatement 객체 생성
		// 물음표 채우기
		try (Connection c = DBUtil.getDBConn()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setInt(3, member.getAge());
			
			int r = ps.executeUpdate();
			
			if (r>0) System.out.println("회원 추가 성공");
			else System.out.println("회원 추가 실패");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//회원 삭제
	// 전달 된 회원 번호를 기준으로 member 테이블에서 삭제하는 메소드
	public void delete(int id) {
		// member 테이블에서 회원 번호가 전달 된 값(id)과 일치하는 행을 삭제
		String sql = "DELETE FROM member WHERE id = ?";
		
		try (Connection c = DBUtil.getDBConn()) {
			PreparedStatement ps = c.prepareStatement(sql);
		
			ps.setInt(1, id);
			
			int r = ps.executeUpdate();
			
			if (r>0) System.out.println("회원 삭제 완료");
			else System.out.println("회원 삭제 실패");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
