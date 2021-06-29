package com.mbti.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.notice.vo.NoticeVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;

public class NoticeDAO {
	
	// 0) 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1) 공지 리스트
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
		// 생성 확인용
		System.out.println("NoticeDAO.list().pageObject : " + pageObject);
		List<NoticeVO> list = null;
		try {
			// 1. 2. -- 연결 확인
			con = DBInfo.getConnection();
			// 3. 4. -- 가져와야할 쿼리 및 오브젝트
			String period = pageObject.getPeriod();
			String sql = "";
			if(period.equals("pre")) sql = DBSQL.NOTICE_LIST;
			else if(period.equals("48gh9rc83")) sql = DBSQL.NOTICE_LIST_ADMINO;
			else if(period.equals("92grcvbq6")) sql = DBSQL.NOTICE_LIST_ADMINR;
			else if(period.equals("74vwnm5r0")) sql = DBSQL.NOTICE_LIST_ADMINALL;
			pstmt = con.prepareStatement(sql);
			// DBSQL에 연결하여 쿼리를 가져온다.
			System.out.println("NoticeDAO.list().DBSQL.NOTICE_LIST : " + DBSQL.NOTICE_LIST);
			System.out.println("NoticeDAO.list().DBSQL.NOTICE_LIST : " + DBSQL.NOTICE_LIST_ADMINO);
			System.out.println("NoticeDAO.list().DBSQL.NOTICE_LIST : " + DBSQL.NOTICE_LIST_ADMINR);
			System.out.println("NoticeDAO.list().DBSQL.NOTICE_LIST : " + DBSQL.NOTICE_LIST_ADMINALL);
			// 쿼리 문에 있는 ?는 가져와야 할 데이터의 갯수 이다. 
			// 
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			// 5.insert update delete 는 executeUpdate, select문 에는 executeQuery를 사용
			// rs에 실행된 결과 값을 넣는다.
			rs = pstmt.executeQuery();
			
			// 결과 값이 Null이 아닐 경우에 실행
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					// 저장할 객체 vo 생성및 값을 집어넣음
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setWriteDate(rs.getString("writeDate"));
					// vo에 넣어줌
					list.add(vo);
					// 잘 가져오고 있는지 콘솔창에 출력하여 확인
					System.out.println("NoticeDAO.list().vo : " + vo);
				}
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자용 오류출력
			e.printStackTrace();
			// 사용자용 오류출력
			throw new Exception("공지 리스트 DB 처리 중 오류 발생");
		}finally {
			// 실행완료후 연결 종료
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}
	// 2) 공지 전체 데이터 확인
	public long getTotalRow(PageObject pageObject) throws Exception{
		// 생성 확인용
		System.out.println("NoticeDAO.getTotalRow()");
		long result = 0;
		try {
		// 1. 2.
		con = DBInfo.getConnection();
		// 3. 4.
		pstmt = con.prepareStatement(DBSQL.NOTICE_GET_TOTALROW);
		// 5.
		rs = pstmt.executeQuery();
		// 6.
		if(rs != null && rs.next()) {
			result = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("DB 공지 전체 데이터 갯수 확인중 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return result;
	}
	// 3) 공지 글 보기
	public NoticeVO view(long no) throws Exception{
		NoticeVO vo = null;
		try {
		// 1. 2.
		con = DBInfo.getConnection();
		// 3. 4.
		pstmt = con.prepareStatement(DBSQL.NOTICE_VIEW);
		pstmt.setLong(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6.
		if(rs != null && rs.next()) {
			vo = new NoticeVO();
			vo.setNo(rs.getLong("no"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setStartDate(rs.getString("startDate"));
			vo.setEndDate(rs.getString("endDate"));
			vo.setWriteDate(rs.getString("writeDate"));
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지 글보기DB 처리중 오류 발생");
			} finally {
			DBInfo.close(con, pstmt, rs);
		}
		return vo;
	}
	// 4) 공지 글 작성
	public int write(NoticeVO vo) throws Exception{
		int result = 0;
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.NOTICE_WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			// 5.
			result = pstmt.executeUpdate();
			// 6. 확인
			System.out.println("공지 작성 완료");
				} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지 작성 DB처리중 오류");
			} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	// 5) 공지 글 삭제
	public int delete(long no) throws Exception{
		int result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.NOTICE_DELETE);
			pstmt.setLong(1, no);
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			if (result == 1) {
			System.out.println("공지 삭제 확인");
			}else 
				System.out.println("삭제되지 않았습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지 삭제 처리중 오류 발생");
		}finally {
			
		}
		
		return result;
	}
	
}
