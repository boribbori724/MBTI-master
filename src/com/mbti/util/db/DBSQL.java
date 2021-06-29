package com.mbti.util.db;

 
public class DBSQL {
	//========================================================================
	// 게시판 쿼리 작성 -----------------------------------------------------------
	// 1. 게시판 리스트
	public static final String BOARD_LIST
	= " select rnum, no, title, id, "
			+ " to_char(WriteDate, 'yyyy-mm-dd') writeDate, hit from ( "
			+ " select rownum rnum, no, title, id, writeDate, hit "
			+ " from ( select no, title, id, writeDate, hit from board "
			+ " order by no desc "
			+ " ) "
			+ " ) where rnum between ? and ? ";

	// 1-1 게시판 리스트 전체 데이터 보기 
	public static final String BOARD_GET_TOTALROW
	= " select count(*) from board ";
	
	// 2. 게시판 글보기
	public static final String BOARD_VIEW
	= " select no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, "
			+ " hit from board where no = ? ";
	
	// 2-1. 게시판 글보기 조회수 1 증가
	public static final String BOARD_INCREASE
	= " update board set hit = hit + 1 where no = ? ";
	
	// 3. 게시판 글쓰기
	public static final String BOARD_WRITE
	= " insert into board(no, title, content, id) "
			+ " values(board_seq.nextval, ?, ?, ?) ";
	
	// 4. 게시판 글수정
	public static final String BOARD_UPDATE
	= " update board set title = ?, content = ?, id = ? "
			+ " where no = ? ";
	
	// 5. 게시판 글 삭제
	public static final String BOARD_DELETE
	= " delete from board where no = ? ";
	
	//========================================================================
	// 게시판 쿼리 작성 -----------------------------------------------------------
	// 1. 게시판 댓글 리스트
	public static final String BOARD_REPLY_LIST 
	= "select rnum, rno, no, rcontent, id,"
			+ " to_char(writeDate, 'yyyy-MM-dd HH24:MI:SS') writeDate from( "
			+ " select rownum rnum, rno, no, rcontent, id, writeDate  from ("
				+ " select rno, no, rcontent, id, writeDate from board_reply "
				+ " where no = ? "
				+ " order by rno desc "
			+ " ) "
			+ ") where rnum between ? and ?  ";
	
	//2. 현재 게시판에 등록된 전체 댓글
	public static final String BOARD_GET_REPLY_TOTALROW
	= " select count(*) from board_reply where no = ? ";
	
	//3. 댓글 등록
	public static final String BOARD_REPLY_WRITE
	= " insert into board_reply(rno, no, rcontent, id) "
			+ " values(board_reply_seq.nextval, ?, ?, ?) ";
	
	//4. 댓글 수정
	public static final String BOARD_REPLY_UPDATE
	= " update board_reply set rcontent = ?, id = ? where rno = ? ";
	
	//5. 댓글 삭제
	public static final String BOARD_REPLY_DELETE
	= " delete from board_reply where rno = ? ";
	
	//========================================================================
	// 공지사항 쿼리 -------------------------------------------------------------
	// 1. 공지사항 리스트 - 보여줄 공지 (현 공지)
	public static final String NOTICE_LIST
	= " select rnum, no, title, "
			+ " to_char(writeDate, 'yyyy-mm-dd') writeDate, "
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate from ( "
				+ " select rownum rnum, no, title, writeDate, startDate, endDate from ( "
					+ " select no, title, writeDate, startDate, endDate from notice "
					+ " where startDate < sysdate and endDate >= trunc(sysdate) "
					+ " order by startDate desc "
				+ " ) "
			+ " ) where rnum between ? and ? ";
	// 1-1. 관리자용 공지 리스트1 - 삭제용 옛공지
	public static final String NOTICE_LIST_ADMINO
	= " select rnum, no, title, "
			+ " to_char(writeDate, 'yyyy-mm-dd') writeDate, "
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate from ( "
				+ " select rownum rnum, no, title, writeDate, startDate, endDate from ( "
					+ " select no, title, writeDate, startDate, endDate from notice "
					+ " where endDate < trunc(sysdate) "
					+ " order by startDate desc "
				+ " ) "
			+ " ) where rnum between ? and ? ";
	// 1-2. 관리자 용 공지 리스트2 - 삭제용 예정공지
	public static final String NOTICE_LIST_ADMINR
	= "select rnum, no, title, "
			+ "to_char(writeDate, 'yyyy-mm-dd') writeDate,"
			+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
			+ " to_char(endDate, 'yyyy.mm.dd') endDate from ( "
				+ " select rownum rnum, no, title, writeDate, startDate, endDate from ("
					+ " select no, title, writeDate, startDate, endDate from notice "
					+ " where startDate > sysdate "
					+ " order by startDate desc "
				+ " ) "
			+ ") where rnum between ? and ?  ";
		// 1-2. 관리자 용 공지 리스트3 - 삭제용 전체공지
	public static final String NOTICE_LIST_ADMINALL
	= "select rnum, no, title, "
			+ "to_char(writeDate, 'yyyy-mm-dd') writeDate,"
			+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
			+ " to_char(endDate, 'yyyy.mm.dd') endDate from ( "
			+ " select rownum rnum, no, title, writeDate, startDate, endDate from ("
			+ " select no, title, writeDate, startDate, endDate from notice "
			+ " order by startDate desc "
			+ " ) "
			+ ") where rnum between ? and ?  ";
	// 1-3. 공지사항 총 게시글 갯수
	public static final String NOTICE_GET_TOTALROW
	= " select count(*) from notice where startDate < sysdate and endDate >= trunc(sysdate) ";
	// 2. 공지사항 작성 글 보기
	public static final String NOTICE_VIEW
	= " select no, title, content, "
			+ " to_char(writeDate, 'yyyy-mm-dd') writeDate, "
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate from notice "
			+ " where no = ? ";
	// 3. 공지사항 작성하기
	public static final String NOTICE_WRITE
	= " insert into notice (no, title, content, startDate, endDate) "
			+ "values(notice_seq.nextval, ?, ?, ?, ?) ";
	// 4. 공지사항 삭제하기
	public static final String NOTICE_DELETE
	= " delete from notice where no = ? ";
	// 5. 공지 검색
	public static final String NOTICE_SEARCH
	= " select no, title, content, startDate, endDate from notice where content like ? or title like ? ";
	// 6. 댓글 리스트
	public static final String NOTICE_REPLY_LIST 
	= "select rnum, rno, no, ncontent, id,"
			+ " to_char(writeDate, 'yyyy-MM-dd HH24:MI:SS') writeDate from( "
			+ " select rownum rnum, rno, no, ncontent, id, writeDate  from ("
				+ " select rno, no, ncontent, id, writeDate from notice_reply "
				+ " where no = ? "
				+ " order by rno asc "
			+ " ) "
			+ ") where rnum between ? and ?  ";
	
	// 6-1. 현재 게시판에 등록된 전체 댓글
	public static final String NOTICE_GET_REPLY_TOTALROW
	= " select count(*) from notice_reply where no = ? ";
	
	// 7. 댓글 등록
	public static final String NOTICE_REPLY_WRITE
	= " insert into notice_reply(rno, no, ncontent, id) "
			+ " values(notice_reply_seq.nextval, ?, ?, ?) ";
	
	// 8. 댓글 수정
	public static final String NOTICE_REPLY_UPDATE
	= " update notice_reply set ncontent = ?, id = ? where rno = ? ";
	
	// 9. 댓글 삭제
	public static final String NOTICE_REPLY_DELETE
	= " delete from notice_reply where rno = ? ";
		
	//========================================================================
	// 유형 게시판 쿼리 -----------------------------------------------------------
	// 1. 리스트 - 번호, 유형, 이미지, 수정일
	public static final String TYPE_LIST
	
	= " select rnum, no,  name, title, type, image, "
		+ " to_char(updateDate, 'yyyy.mm.dd') updateDate from( "
			+ "select rownum rnum, no, title, type, image, name, updateDate from  ("
			+ " select no, title, type, image, name, updateDate from type"
			+ " order by no desc "
			+ " ) "
		+ " )  where rnum between ? and ? ";

	// 1-1. 전체 데이터 개수 - 페이지 처리: 리스트
	public static final String TYPE_GET_TOTALROW
	= " select count(*) from type ";
	
	// 2. 유형 이미지 보기 - 번호, 유형, 내용, 이미지, g유형, g이미지, b유형, b이미지, 수정일
	public static final String TYPE_VIEW
	= " select no, name, title, type, content, image, gType, gImage, bType, bImage, "
			+ " to_char(updateDate, 'yyyy.mm.dd') updateDate "
			+ " from type where no = ? ";
	
	// 3. 유형 이미지 등록 - 번호, 유형, 이미지, g유형, g이미지, b유형, b이미지
	public static final String TYPE_WRITE
	= " insert into type(no, name, title, type, content, image, gType, gImage, bType, bImage) "
			+ " values(type_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	// 4.유형 이미지 파일 정보 수정
	public static final String TYPE_UPDATE_FILE
	= " update type set name =?, title=?, type =?, content =?, image =?,  gType =?, gImage =?, bType =?, bImage =? "
			+ " where no = ? ";

	// 5.유형 이미지 삭제 .
	public static final String TYPE_DELETE
	= " delete from type where no = ? ";
	// 6.유형 결과페이지 출력
	public static final String TYPE_RESULT_VIEW
	= " select name, title, type, content, image, gType, gImage, bType, bImage from type where type = ? ";
	//========================================================================
	//========================================================================
	// 회원관리 쿼리 -------------------------------------------------------------
	// 1. 로그인 처리
	public static final String MEMBER_LOGIN
	= " select m.id, m.name, m.gradeNo, g.gradeName from member m, grade g "
	+ " where (m.id = ? and m.pw = ?) and (m.gradeNo = g.gradeNo) ";
	
	// 2. 회원가입 처리
	public static final String MEMBER_WRITE
	= " insert into member(id, pw, name, gender, birth, tel, email) "
	+ " values(?, ?, ?, ?, ?, ?, ?) ";
	
	// 2-1. 아이디 중복체크
	public static final String MEMBER_CHECK_ID
	= " select id from member where id = ?";
	
	// 3. 회원 정보 보기
	public static final String MEMBER_VIEW
	= " select m.id, m.name, m.gender, m.tel, m.email, "
	+ " to_char(m.birth, 'yyyy.mm.dd') birth, "
	+ " m.status, g.gradeNo, g.gradeName " 
	+ " from member m, grade g "
	+ " where id = ? and (m.gradeNo = g.gradeNo) ";
	
	// 4. 회원 리스트
	public static final String MEMBER_LIST
	= " select rnum, id, name, gender, "
	+ "to_char(birth, 'yyyy.mm.dd') birth, tel, status, gradeNo, gradeName from( "
		+ " select rownum rnum, id, name, gender, birth, tel, status, "
		+ " gradeNo, gradeName from( "
			+ " select m.id, m.name, m.gender, m.birth, m.tel, m.status, "
			+ " m.gradeNo, g.gradeName "
			+ " from member m, grade g "
			+ " where m.gradeNo = g.gradeNo "
			+ " order by id "
		+ " ) "
	+ " ) where rnum between ? and ? ";
	
	public static final String MEMBER_GET_TOTALROW
	= " select count(*) from member ";
	
	// 5. 회원 등급 수정
	public static final String MEMBER_GRADE_MODIFY
	= "update member set gradeNo = ? where id = ?";
	
	// 6. 회원 탈퇴 처리
	public static final String MEMBER_DELETE 
	= " delete from member where id = ? ";
	
	// 7. 회원 정보 수정
	public static final String MEMBER_UPDATE
	= " update member set pw = ?, tel = ?, email = ? "
			+ " where id = ? ";
	

	//========================================================================
	// 테스트 목록 쿼리 -----------------------------------------------------------
	public static final String LIST_LIST
	= " SELECT rnum, no, title, image, url, hit FROM ( "
			+ " SELECT rownum rnum, no, title, image, url, hit FROM ("
				+ " SELECT no, title, image, url, hit "
				+ " FROM list "
				+ " ORDER by hit DESC "
			+ " ) "
		+ ") "
	+ " WHERE rnum BETWEEN ? AND ?";
	
	public static final String LIST_GET_TOTALROW
	= " SELECT COUNT(*) FROM list ";
	
	public static final String LIST_INCREASE
	= " UPDATE list SET hit = hit + 1 WHERE no = ? ";
	
	public static final String LIST_REGISTER
	= " INSERT INTO list(no, title, image, url) "
	+ " VALUES(list_seq.NEXTVAL, ?, ?, ?) ";
	
	public static final String LIST_UPDATE
	= " UPDATE list SET title = ?, image = ?, url = ? "
	+ " WHERE no = ? ";
	
	public static final String LIST_DELETE
	= " DELETE FROM list WHERE no = ? ";
	
	public static final String LIST_VIEW
	= " select no, title, image, url, hit from list "
			+ " where no = ? ";
	//=======================================================================
	// 피드백 쿼리 --------------------------------------------------------------
	// 1. 피드백 리스트
	public static final String FEEDBACK_LIST 
	= " select rnum, no, title, "
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, levNo "
	+ " from ( "
		+ " select rownum rnum, no, title, writeDate, levNo from ( "
			+ " select no, title, writeDate, levNo from feedback "
			+ " where sender = ? "
			+ " order by refNo desc, ordNo "
		+ " ) "
	+ " ) where rnum between ? and ? ";
	
	// 1-1.피드백 관리자 리스트
	public static final String FEEDBACK_ADMIN_LIST 
	= " select rnum, no, title, "
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, levNo "
	+ " from ( "
		+ " select rownum rnum, no, title, writeDate, levNo from ( "
			+ " select no, title, writeDate, levNo from feedback "
			+ " where sender = 'admin' or accepter = 'admin' "
			+ " order by refNo desc, ordNo "
		+ " ) "
	+ " ) where rnum between ? and ? ";
	
	// 2. 피드백 전체 데이터 갯수
	public static final String FEEDBACK_GET_TOTALROW
	= " select count(*) from feedback where sender = ? ";
	// 2-1. 피드백 관리자용 전체 데이터 갯수
	public static final String FEEDBACK_ADMIN_GET_TOTALROW
	= " select count(*) from feedback where accepter = 'admin' ";
	
	// 3. 피드백 보기 
	public static final String FEEDBACK_VIEW
	= " select no, title, content, sender, accepter, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
			+ " refNo, ordNo, levNo "
			+ " from feedback "
			+ " where no = ? ";
	
	// 4. 피드백 작성
	public static final String FEEDBACK_WRITE
	= " insert into feedback(no, sender, title, content, accepter, refNo, ordNo, levNo, parentNo) "
		+ " values(feedback_seq.nextval, ?, ?, ?, 'admin', feedback_seq.nextval, 1, 0, feedback_seq.nextval) ";
	
	// 4-1. 피드백 답변 작성
	public static final String FEEDBACK_ANSWER
	= " insert into feedback(no, title, content, sender , accepter, refNo, ordNo, levNo, parentNo) "
			+ " values(feedback_seq.nextval, ?, ?, ?, 'admin', ?, ?, ?, ?) ";

	// 4-2. 피드백 관련번호 
	public static final String FEEDBACK_ANSWER_INCREASE_ORDNO
	= " update feedback set ordNo = ordNo + 1 where refNo = ? and ordNo >= ? ";

	// 5. 피드백 삭제
	public static final String FEEDBACK_DELETE
	= " delete from feedback where no = ? ";
	
} 




