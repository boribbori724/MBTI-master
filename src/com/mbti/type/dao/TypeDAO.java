package com.mbti.type.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.type.vo.TypeVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;

public class TypeDAO {

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//1. 이미지 유형 리스트
	public List<TypeVO> list(PageObject pageObject) throws Exception{
		
		System.out.println("TypeDAO.list().pageObject : " + pageObject);
		
		List<TypeVO> list = null;
		
		try {
			//1.드라이버 확인 2.연결
			con = DBInfo.getConnection();
			System.out.println("TypeDAO.list().DBSQL.TYPE_LIST :" + DBSQL.TYPE_LIST);
			//3.
			System.out.println("TypeDAO.list().pstmt : " + pstmt);
			pstmt=con.prepareStatement(DBSQL.TYPE_LIST);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			//5. 실행
			rs = pstmt.executeQuery();
			System.out.println("TypeDAO.list().rs : " + rs);
			//6. 표시 -> 데이터 담기
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list= new ArrayList<>();
					TypeVO vo = new TypeVO();
					vo.setNo(rs.getLong("no"));
					vo.setType(rs.getString("type"));
					vo.setImage(rs.getString("image"));
					vo.setUpdateDate(rs.getString("updateDate"));
					list.add(vo);
					System.out.println("TypeDAO.list().while.vo :" + vo);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//사용자를 위한 오류 처리
			throw new Exception("유형 관리 실행 중 DB처리 오류");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("TypeDAO.list().list : " + list);
		return list;
	}
	
	// 1-1. 유형이미지 전체 데이터 개수
	public long getTotalRow() throws Exception {
		System.out.println("TypeDAO.getTotalRow()");

		long result = 0;
		
		try {
			//1.2.
			con = DBInfo.getConnection();
			System.out.println("TypeDAO.getTotalRow().con : " + con);
			//3.4.
			//쿼리확인
			System.out.println("TypeDAO.getTotalRow().DBSQL.TYPE_GET_TOTALROW : " 
			+ DBSQL.TYPE_GET_TOTALROW);
			pstmt=con.prepareStatement(DBSQL.TYPE_GET_TOTALROW);
			System.out.println("TypeDAO.getTotalRow().pstmt : " + pstmt);
			// 5.
			rs = pstmt.executeQuery();
			System.out.println("TypeDAO.getTotalRow().rs : " + rs);
			//6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
				System.out.println("TypeDAO.getTotalRow().result : " + result);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("유형 이미지 데이터 전체 개수 실행중 DB 처리 오류 ");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("TypeDAO.getTotalRow().result :" + result);
		return result;
	}
	
	// 2. 유형관리 보기
	public TypeVO view(Long no) throws Exception{
		System.out.println("TypeDAO.view()");
		
		TypeVO vo = null;
		
		try {
			//1.2.
			con=DBInfo.getConnection();
			System.out.println("TypeDAO.view().con : " + con);
			
			//3.4.
			System.out.println("TypeDAO.view().DBSQL.TYPE_VIEW : " 
					+ DBSQL.TYPE_VIEW);
			pstmt=con.prepareStatement(DBSQL.TYPE_VIEW);
			pstmt.setLong(1, no);
			System.out.println("TypeDAO.view().pstmt : " + pstmt);
			
			//5.
			rs = pstmt.executeQuery();
			System.out.println("TypeDAO.view().rs : " + rs);
			
			//6.
			if(rs !=null && rs.next()) {
				vo = new TypeVO();
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setType(rs.getString("type"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setgType(rs.getString("gType"));
				vo.setgImage(rs.getString("gImage"));
				vo.setbType(rs.getString("bType"));
				vo.setbImage(rs.getString("bImage"));
				vo.setUpdateDate(rs.getString("updateDate"));
				System.out.println("TypeDAO.view().vo : " + vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("유형관리 이미지 보기 DB 처리 중 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
			
		}
		System.out.println("TypeDAO.view().vo : " + vo );
		return vo;
	}
	
	//3. 이미지 등록
	public int write(TypeVO vo ) throws Exception{
		int result = 0;
		
		try {
			
			System.out.println("write()");
			
			//1.2.
			con = DBInfo.getConnection();
			
			System.out.println(DBSQL.TYPE_WRITE);
			
			//3.4.
			pstmt = con.prepareStatement(DBSQL.TYPE_WRITE);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getType());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getImage());
			pstmt.setString(6, vo.getgType());
			pstmt.setString(7, vo.getgImage());
			pstmt.setString(8, vo.getbType());
			pstmt.setString(9, vo.getbImage());
			
			System.out.println(vo.getType());
			
			//5.
			result = pstmt.executeUpdate();
			
			System.out.println(result);
			
			//6.
			System.out.println("TypeDAO.write() - 유형을 등록했습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("유형 이미지 등록 DB처리 중 오류");
		}finally {
			DBInfo.close(con, pstmt);
			 
		}
		
		return result;
	}
	
	// 4-1 유형 이미지 파일 정보 수정 - 번호, 이미지파일
	public int updateFile(TypeVO vo) throws Exception {
		int result = 0;
		
		try {
			//1.2.
			con= DBInfo.getConnection();
			//3.4.
			pstmt = con.prepareStatement(DBSQL.TYPE_UPDATE_FILE);
			pstmt.setString(1, vo.getName()); 
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getType());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getImage());
			pstmt.setString(6, vo.getgType());
			pstmt.setString(7, vo.getgImage());
			pstmt.setString(8, vo.getbType());
			pstmt.setString(9, vo.getbImage());
			pstmt.setLong(10, vo.getNo());
			
			//5.
			result = pstmt.executeUpdate();
			
			//6.
			System.out.println("TypeDAO.updateFile()- 유형 이미지 수정 완료.");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("유형 이미지 수정 DB 처리 중 오류");
		}finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	// 5. 유형 이미지 게시판 삭제 
	public int delete(Long no) throws Exception {
		int result = 0;
		
		try {
			//1.2.
			con = DBInfo.getConnection();
			//3.4.
			pstmt = con.prepareStatement(DBSQL.TYPE_DELETE);
			pstmt.setLong(1, no);
			
			//5. 
			result = pstmt.executeUpdate();
			
			// 6.
			System.out.println("TypeDAO.delete() - 이미지 게시판 삭제 완료.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("유형 이미지 게시판 삭제 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	
	
}
