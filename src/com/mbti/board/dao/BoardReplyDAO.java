package com.mbti.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.board.vo.BoardReplyVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;

public class BoardReplyDAO {
	//필요한 객체 선언 - con(Connection), pstmt(PreparedStatement), rs(ResultSet) : 셋 다 임포트 해줘야 한다
	//필요한 객체를 선언한다
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//1. 댓글 리스트
	public List<BoardReplyVO> replyList(Long no, PageObject pageObject) throws Exception{
		
		// 넘어오는 데이터 확인
		System.out.println("BoardDAO.list().no/pageObject : " + no +"/" + pageObject);
		
		List<BoardReplyVO> list = null;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con=DBInfo.getConnection();
			System.out.println("BoardDAO.replyList().con : " + con);
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			System.out.println("BoardDAO.replyList().DBSQL.BOARD_REPLY_LIST : " + DBSQL.BOARD_REPLY_LIST);
			pstmt=con.prepareStatement(DBSQL.BOARD_REPLY_LIST);
			System.out.println("BoardDAO.replyList().pstmt : " + pstmt);
			pstmt.setLong(1, no); //게시판 글번호
			pstmt.setLong(2, pageObject.getStartRow()); //일단 강제 세팅
			pstmt.setLong(3, pageObject.getEndRow()); //일단 강제 세팅
			//5 실행
			rs = pstmt.executeQuery();
			System.out.println("BoardDAO.replyList().rs : " + rs);
			//6. 데이터 표시
			if(rs != null) { //rs가 null이 아니면
				while(rs.next()) { //다음 데이터가 있으면 반복처리 해라
					if(list==null) list = new ArrayList<>(); //list가 null이면 ArrayList를 생성
					BoardReplyVO vo = new BoardReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setRcontent(rs.getString("rcontent"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					list.add(vo);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류 처리
			throw new Exception("게시판 댓글을 리스트 실행 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("BoardDAO.replyList().list : " + list);
		return  list;
	}

	//2. 글번호에 맞는 댓글 데이터 수 구하기
	public long getReplyTotalRow(Long no) throws Exception{
		System.out.println("BoardDAO.getReplyTotalRow().no : " + no);
		
		long result=0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con=DBInfo.getConnection();
			System.out.println("BoardDAO.getReplyTotalRow().con : " + con);
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt=con.prepareStatement(DBSQL.BOARD_GET_REPLY_TOTALROW);
			System.out.println("BoardDAO.getReplyTotalRow().pstmt : " + pstmt);
			pstmt.setLong(1, no);
			//5
			// rs는 출력해 볼수 있다. 그러나 rs.next() 출력하면 데이터를 한개 넘기게 된다.
			rs=pstmt.executeQuery();
			System.out.println("BoardDAO.getReplyTotalRow().rs : " + rs);
			//6
			if(rs!=null && rs.next()) { //rs가 null이 아니고 다음 데이터가 있으면 ↓
				result=rs.getLong(1); //여기는 무조건 1(count해서 나오는 결과는 하나니까)
				System.out.println("BoardDAO.getReplyTotalRow().result : " + result);
			}
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("댓글 전체 데이터를 가져오는 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		return result;
	}
	
	//3. 게시판 댓글 등록
	public int replyWrite(BoardReplyVO vo) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con=DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt=con.prepareStatement(DBSQL.BOARD_REPLY_WRITE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getRcontent());
			pstmt.setString(3, vo.getId());
			//5
			result=pstmt.executeUpdate();
			//6. 데이터 표시 : 입력이 됐는지 여부만 확인하면 된다
			System.out.println("댓글 등록에 성공했습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("댓글 등록 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	//4. 게시판 댓글 수정
	public int replyUpdate(BoardReplyVO vo) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con=DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt=con.prepareStatement(DBSQL.BOARD_REPLY_UPDATE);
			pstmt.setString(1, vo.getRcontent());
			pstmt.setString(2, vo.getId());
			pstmt.setLong(3, vo.getRno());
			//5
			result=pstmt.executeUpdate();
			//6. 데이터 표시 : 수정이 됐는지 여부만 확인하면 된다
			System.out.println("댓글 수정이 완료되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
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
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con=DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt=con.prepareStatement(DBSQL.BOARD_REPLY_DELETE);
			pstmt.setLong(1, rno);
			//5
			result=pstmt.executeUpdate();
			//6. 데이터 표시 : 삭제가 됐는지 여부를 확인하면 된다
			System.out.println("댓글 삭제가 완료되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("댓글 삭제 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
}
