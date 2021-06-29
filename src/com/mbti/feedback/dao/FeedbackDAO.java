package com.mbti.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.feedback.vo.FeedbackVO;
import com.mbti.util.page.PageObject;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;

public class FeedbackDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	
	public List<FeedbackVO> list(PageObject pageObject) throws Exception {
		List<FeedbackVO> list = null;
		
		try {
			
			con = DBInfo.getConnection();
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_LIST);
			pstmt.setString(1, pageObject.getAccepter());
//			pstmt.setString(2, pageObject.getAccepter());
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					FeedbackVO vo = new FeedbackVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setLevNo(rs.getLong("levNo"));
					list.add(vo);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("FEEDBACK 회원 글 보기  DB 처리 중 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}
	
	public List<FeedbackVO> adminList(PageObject pageObject) throws Exception {
		List<FeedbackVO> list = null;
		
		try {
			
			con = DBInfo.getConnection();
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_ADMIN_LIST);
//			pstmt.setString(1, pageObject.getAccepter());
//			pstmt.setString(2, pageObject.getAccepter());
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			rs = pstmt.executeQuery(); 
			
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					FeedbackVO vo = new FeedbackVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setLevNo(rs.getLong("levNo"));
					list.add(vo);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("FEEDBACK 관리자 글 보기  DB 처리 중 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}
	
	public long getTotalRow(PageObject pageObject) throws Exception{
		long result = 0;
		
		try {
			
			//1.2.
			con = DBInfo.getConnection();
			//3.4.
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_GET_TOTALROW);
			pstmt.setString(1, pageObject.getAccepter());
			//5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("FEEDBACK 전체 갯수 구하는 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return result;
	} // end of getTotalRow()

	public long adminGetTotalRow() throws Exception{ 
		long result = 0;
		
		try {
			
			//1.2.
			con = DBInfo.getConnection();
			//3.4.
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_ADMIN_GET_TOTALROW);
			//5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("AdminFEEDBACK 전체 갯수 구하는 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return result;
	} // end of getTotalRow()
	
	public FeedbackVO view(Long no) throws Exception{
		FeedbackVO vo = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_VIEW);
			pstmt.setLong(1, no);
			// 5. 
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				vo = new FeedbackVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setSender(rs.getString("sender"));
				vo.setAccepter(rs.getString("accepter"));
				vo.setWriteDate(rs.getString("writeDate"));
				// 글보기 할때는 관련글번호, 순서번호, 들여쓰기 정보를 필요로 하지 않아서 버린다.
				// 답변쓰기 할때는 이러한 정보가 필요하다. 그래서 일단 담아 놓는다. 
				vo.setRefNo(rs.getLong("refNo"));
				vo.setOrdNo(rs.getLong("ordNo"));
				vo.setLevNo(rs.getLong("levNo"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("FEEDBACK 글보기 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		return vo;
	}
	
	public int write(FeedbackVO vo) throws Exception{
		int result = 0;
		
		try {
			
			//1.2.
			con = DBInfo.getConnection();
			//3.4.
			System.out.println(DBSQL.FEEDBACK_WRITE);
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_WRITE);
			pstmt.setString(1, vo.getSender());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			//5.
			result = pstmt.executeUpdate();
			// 6.
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("피드백 작성 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	} // end of write()
	
	public int answer(FeedbackVO vo) throws Exception{
		int result = 0;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_ANSWER);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getSender());
			pstmt.setLong(4, vo.getRefNo());
			pstmt.setLong(5, vo.getOrdNo());
			pstmt.setLong(6, vo.getLevNo());
			pstmt.setLong(7, vo.getParentNo());
			// 5. 
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("QNA 답변 처리 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	public int increaseOrdNo(FeedbackVO vo) throws Exception{
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			pstmt = con.prepareStatement(DBSQL.FEEDBACK_ANSWER_INCREASE_ORDNO);
			pstmt.setLong(1, vo.getRefNo());
			pstmt.setLong(2, vo.getOrdNo());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("FEEDBACK OrdNo 증가 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}

	public int delete(long no) throws Exception {
		int result = 0;
		
		try {
		con = DBInfo.getConnection();
		pstmt = con.prepareStatement(DBSQL.FEEDBACK_DELETE);
		pstmt.setLong(1, no);
		
		result = pstmt.executeUpdate(); 
		
		if(result == 1)
			System.out.println("글을 삭제하였습니다.");
		else
			System.out.println("삭제하려는 글 정보를 확인하세요.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("FEEDBACK 글 삭제 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}

}
