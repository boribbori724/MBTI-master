package com.mbti.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.board.vo.BoardVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.DBSQL;
import com.mbti.util.page.PageObject;


public class BoardDAO {
	//필요한 객체 선언 - con(Connection), pstmt(PreparedStatement), rs(ResultSet) : 셋 다 임포트 해줘야 한다
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//1. 게시판 리스트
	public List<BoardVO> list(PageObject pageObject) throws Exception{
		//넘어오는 데이터 확인
		System.out.println("BoardDAO.list().pageObject : " + pageObject);
		
		//초기값은 null로 세팅한다
		List<BoardVO> list = null;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			System.out.println("BoardDAO.list().DBSQLBOARD_LIST"+DBSQL.BOARD_LIST);//확인용
			pstmt = con.prepareStatement(DBSQL.BOARD_LIST); //실행객체를 준비(재료)
			System.out.println("BoardDAO.list().pstmt : " + pstmt);
			pstmt.setLong(1, pageObject.getStartRow()); //시작 번호
			pstmt.setLong(2, pageObject.getEndRow());//끝 번호 --> 1부터 10까지 가져온다
			//5. 실행
			rs = pstmt.executeQuery();
			System.out.println("BoardDAO().list().rs : " + rs);
			//6. 데이터 표시
			if(rs != null) { //rs가 null이 아니면
				while(rs.next()) { //다음 데이터가 있으면 반복처리 해라
					if(list == null) list = new ArrayList<>(); //list가 null이면 ArrayList를 생성
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					list.add(vo);
					System.out.println("BoardDAO.list().while().vo : " + vo);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류 처리
			throw new Exception("게시판 리스트 처리중 오류가 발생했습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}		
		return list;
	}
	
	//1-2. 게시판 리스트 전체 데이터 가지고 오기
	public long getTotalRow() throws Exception{
		System.out.println("BoardDAO.getTotalRow");
		long result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_GET_TOTALROW);
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.getTotalRow().DBSQL.BOARD_GET_TOTALROW : " + DBSQL.BOARD_GET_TOTALROW);
			System.out.println("BoardDAO.getTotalRow().pstmt : " + pstmt);
			//5. 
			rs = pstmt.executeQuery();
			//rs는 출력해 볼 수 있으나 rs.next()는 출력하면 안 된다. 출력하면 데이터를 한 개 넘기게 된다
			System.out.println("BoardDAO.getTotalRow().rs : " + rs);
			//6.
			if(rs != null && rs.next()) { //rs가 null이 아니고 다음 데이터가 있으면 ↓
				//데이터를 result에 담는다
				result = rs.getLong(1); //여기는 무조건 1(count해서 나오는 결과는 하나니까)
				System.out.println("BoardDAO.getTotalRow().result : " + result);
			}
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("리스트 전체 데이터를 가져오는 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}		
		return result;
	}
	
	//2. 게시판 글보기
	public BoardVO view(long no) throws Exception{
		BoardVO vo = null;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_VIEW);
			pstmt.setLong(1, no);
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.view().DBSQL.BOARD_VIEW : " + DBSQL.BOARD_VIEW);
			System.out.println("BoardDAO.view().pstmt : " + pstmt);
			//5. 실행 : 데이터는 한 개만 나오니 반복문은 필요하지 않다
			rs = pstmt.executeQuery();
			//6.
			if(rs != null && rs.next()) {//rs가 null이 아니고 다음 데이터가 있으면 ↓
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("게시판 글보기 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		return vo;
	}
	
	//2-1. 조회수 1증가(리스트에서 글보기로 올 때만 증가한다)
	public int increase(long no) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			System.out.println();
			pstmt = con.prepareStatement(DBSQL.BOARD_INCREASE);
			pstmt.setLong(1, no);
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.increase().DBSQL.BOARD_INCREASE : " + DBSQL.BOARD_GET_TOTALROW);
			System.out.println("BoardDAO.increase().pstmt : " + pstmt);
			//5.
			result = pstmt.executeUpdate();
			//6
			System.out.println("조회수가 1 증가되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("조회수 1 증가 중 오류가 발생했습니다.");
		}finally {
			//7.닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	//3. 게시판 글쓰기
	public int write(BoardVO vo) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.write().DBSQL_Board.BOARD_WRITE : " + DBSQL.BOARD_WRITE);
			System.out.println("BoardDAO.write().pstmt : " + pstmt);
			//5
			result = pstmt.executeUpdate();
			//6. 데이터 표시 : 입력이 됐는지 여부만 확인하면 된다
			System.out.println("작성한 게시글이 등록됐습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("게시판 글쓰기 중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	//4. 게시판 글 수정
	public int update(BoardVO vo) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setLong(4, vo.getNo());
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.update().DBSQL.BOARD_UPDATE : " + DBSQL.BOARD_UPDATE);
			System.out.println("BoardDAO.update().pstmt : " + pstmt);
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
	
	//5. 게시판 글삭제
	public int delete(long no) throws Exception{
		int result = 0;
		
		try {
			//1. 드라이버 확인 (DBInfo) + 2. 연결
			con = DBInfo.getConnection();
			//3. sql - DBSQL + 4. 실행 객체 + 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_DELETE);
			pstmt.setLong(1, no);
			//확인 : 나중에 지우자
			System.out.println("BaordDAO.delete().DBSQL_Board.BOARD_DELETE : " + DBSQL.BOARD_DELETE);
			System.out.println("BoardDAO.delete().pstmt : " + pstmt);
			//5
			result = pstmt.executeUpdate();
			//6. 데이터 표시 : 삭제가 됐는지 여부를 확인하면 된다
			if(result == 1)
				System.out.println("게시글 삭제가 완료되었습니다.");
			else
				System.out.println("삭제할 게시글을 확인해주세요.");
		}catch (Exception e) {
			// TODO: handle exception
			//개발자를 위해서 발생하는 오류를 콘솔에 출력한다
			e.printStackTrace();
			//사용자를 위한 오류처리
			throw new Exception("게시글 삭제중 오류가 발생했습니다.");
		}finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		return result;
	}
}
