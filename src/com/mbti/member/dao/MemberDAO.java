package com.mbti.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.member.vo.LoginVO;
import com.mbti.member.vo.MemberVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;

public class MemberDAO {
	
	// 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 로그인 처리를 위한 메서드
	
	public LoginVO login(LoginVO vo) throws Exception{
		LoginVO loginVO = null;
		
		try {
			// 드라이버 확인 + 연결객체
			con = DBInfo.getConnection();
			// sql + 실행객체
			pstmt = con.prepareStatement(DBSQL.MEMBER_LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			// 실행
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next()) {
				loginVO = new LoginVO();
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
			
		}
		return loginVO;
	}
	
	
	// 2. 회원가입
	public int write(MemberVO vo) throws Exception{
		int result = 0;
		try {
			// 드라이버 확인 + 연결객체
			con = DBInfo.getConnection();
			// sql + 실행객체
			pstmt = con.prepareStatement(DBSQL.MEMBER_WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			// 실행
			result = pstmt.executeUpdate();
			System.out.println("회원가입 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원가입 처리 중 DB 오류 발생");
		} finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	// 2-1. 아이디 중복 체크
	public String checkId(String id) throws Exception{
		String result = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(DBSQL.MEMBER_CHECK_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next())
				result = rs.getString("id");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("MemberDAO Error - 아이디 중복 체크 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("MemberDAO.write().result : " + result);
		return result;
	}

	// 3. 회원 정보 보기
	public MemberVO view(String id) throws Exception{
		MemberVO vo = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(DBSQL.MEMBER_VIEW);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보 보기 실행 중 DB 처리 오류 ");
		} finally {
			
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("MemberDAO.view().vo :" + vo);
		return vo;
	}
	
	// 4. 회원 리스트
	public List<MemberVO> list(PageObject pageObject) throws Exception{
		List<MemberVO> list = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(DBSQL.MEMBER_LIST);
			pstmt.setLong(1, pageObject.getStartRow()); //시작 번호
			pstmt.setLong(2, pageObject.getEndRow());
			rs = pstmt.executeQuery();
			// 표시
			if(rs !=null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setStatus(rs.getString("status"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 리스트 실행 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}
	
	public long getTotalRow() throws Exception{
		long result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.MEMBER_GET_TOTALROW);
			//확인 : 나중에 지우자
			//5. 
			rs = pstmt.executeQuery();
			//rs는 출력해 볼 수 있으나 rs.next()는 출력하면 안 된다. 출력하면 데이터를 한 개 넘기게 된다
			//6.
			if(rs != null && rs.next()) { //rs가 null이 아니고 다음 데이터가 있으면 ↓
				//데이터를 result에 담는다
				result = rs.getLong(1); //여기는 무조건 1(count해서 나오는 결과는 하나니까)
			}
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("멤버 리스트 전체 데이터를 가져오는 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}		
		return result;
	}
	
	
	
	// 5. 회원 등급 수정
	public int gradeModify(MemberVO vo) throws Exception{
		int result = 0;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(DBSQL.MEMBER_GRADE_MODIFY);
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			result = pstmt.executeUpdate();
			// 표시
			if(result == 1 || result == 4) {
				System.out.println("MemberDAO.gradeModify() - 회원등급 수정 완료....");
			} else {
				throw new Exception("조건에 맞는 데이터(id)가 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 등급 수정 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		System.out.println("MemberDAO.gradeModify().result : " + result);
		return result;
	}
	
	// 6. 회원 탈퇴 처리
	public int delete(String id) throws Exception {
		int result = 0;
		try {
			// 1. 확인 2. 연결
			con = DBInfo.getConnection();
			// 3. sql . 4.실행&데이터셋팅
			pstmt = con.prepareStatement(DBSQL.MEMBER_DELETE);
			pstmt.setString(1, id);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 출력
			if(result == 1)
				System.out.println("회원 탈퇴에 성공하셨습니다.");
			else
				System.out.println("탈퇴하려는 id 정보를 확인하세요.");
			
		}catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위한 예외출력(500) -> 콘솔
			e.printStackTrace();
			// 사용자를 위한 예외처리. -> jsp까지 전달된다.
			throw new Exception("회원 탈퇴 DB 처리 중 오류 발생.");
		} finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	// 7. 회원 정보 수정
	public int update(MemberVO vo) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.MEMBER_UPDATE);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getId());
			//확인 : 나중에 지우자
			System.out.println("MemberDAO.update().DBSQL.MEMBER_UPDATE : " + DBSQL.MEMBER_UPDATE);
			System.out.println("MemberDAO.update().pstmt : " + pstmt);
			//5
			result = pstmt.executeUpdate();
			//6. 데이터 표시 : 수정이 됐는지 여부만 확인하면 된다
			System.out.println("게시글 수정이 완료되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("게시글 수정중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	
}
