package com.mbti.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.notice.vo.NoticeReplyVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;

public class NoticeReplyDAO {
	
	//필요한 객체를 선언한다
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//1. 댓글 리스트
	public List<NoticeReplyVO> replyList(Long no, PageObject pageObject) throws Exception{
		
		// 넘어오는 데이터 확인
		System.out.println("NoticeDAO.list().no/pageObject : " + no +"/" + pageObject);
		
		List<NoticeReplyVO> list = null;
		
		try {
			//1+2
			con=DBInfo.getConnection();
			System.out.println("NoticeDAO.replyList().con : " + con);
			//3+4
			System.out.println("NoticeDAO.replyList().DBSQL.NOTICE_REPLY_LIST : " + DBSQL.NOTICE_REPLY_LIST);
			pstmt=con.prepareStatement(DBSQL.NOTICE_REPLY_LIST);
			System.out.println("NoticeDAO.replyList().pstmt : " + pstmt);
			pstmt.setLong(1, no); //게시판 글번호
			pstmt.setLong(2, pageObject.getStartRow()); //일단 강제 세팅
			pstmt.setLong(3, pageObject.getEndRow()); //일단 강제 세팅
			//5
			rs = pstmt.executeQuery();
			System.out.println("NoticeDAO.replyList().rs : " + rs);
			//6
			if(rs != null) {
				while(rs.next()) {
					if(list==null) list = new ArrayList<>();
					NoticeReplyVO vo = new NoticeReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setNcontent(rs.getString("ncontent"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					list.add(vo);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("게시판 댓글을 리스트 실행 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("NoticeDAO.replyList().list : " + list);
		return  list;
	}

	//2. 글번호에 맞는 댓글 데이터 수 구하기
	public long getReplyTotalRow(Long no) throws Exception{
		System.out.println("NoticeDAO.getReplyTotalRow().no : " + no);
		
		long result=0;
		
		try {
			//1+2
			con=DBInfo.getConnection();
			System.out.println("NoticeDAO.getReplyTotalRow().con : " + con);
			//3+4
			pstmt=con.prepareStatement(DBSQL.NOTICE_GET_REPLY_TOTALROW);
			System.out.println("NoticeDAO.getReplyTotalRow().pstmt : " + pstmt);
			pstmt.setLong(1, no);
			//5
			// rs는 출력해 볼수 있다. 그러나 rs.next() 출력하면 데이터를 한개 넘기게 된다.
			rs=pstmt.executeQuery();
			System.out.println("NoticeDAO.getReplyTotalRow().rs : " + rs);
			//6
			if(rs!=null && rs.next()) {
				result=rs.getLong(1);
				System.out.println("NoticeDAO.getReplyTotalRow().result : " + result);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("댓글 전체 데이터를 가져오는 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		return result;
	}
	
	//3. 게시판 댓글 등록
	public int replyWrite(NoticeReplyVO vo) throws Exception{
		int result = 0;
		
		try {
			//1+2
			con=DBInfo.getConnection();
			//3+4
			pstmt=con.prepareStatement(DBSQL.NOTICE_REPLY_WRITE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getId());
			//5
			result=pstmt.executeUpdate();
			//6. 표시만 해주면 된다
			System.out.println("댓글 등록에 성공했습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("댓글 등록 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	//4. 게시판 댓글 수정
	public int replyUpdate(NoticeReplyVO vo) throws Exception{
		int result = 0;
		
		try {
			//1+2
			con=DBInfo.getConnection();
			//3+4
			pstmt=con.prepareStatement(DBSQL.NOTICE_REPLY_UPDATE);
			pstmt.setString(1, vo.getNcontent());
			pstmt.setString(2, vo.getId());
			pstmt.setLong(3, vo.getRno());
			//5
			result=pstmt.executeUpdate();
			//6 표시만 하면 된다
			System.out.println("댓글 수정이 완료되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("댓글 수정 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}

	//5. 게시판 댓글 삭제
	public int replyDelete(Long rno) throws Exception{
		int result = 0;
		
		try {
			//1+2
			con=DBInfo.getConnection();
			//3+4
			pstmt=con.prepareStatement(DBSQL.NOTICE_REPLY_DELETE);
			pstmt.setLong(1, rno);
			//5
			result=pstmt.executeUpdate();
			//6 표시만 하면 된다
			System.out.println("댓글 삭제가 완료되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("댓글 삭제 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
}
