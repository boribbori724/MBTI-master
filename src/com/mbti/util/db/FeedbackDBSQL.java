package com.mbti.util.db;

public class FeedbackDBSQL {
	
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
	
	public static final String FEEDBACK_GET_TOTALROW
	= " select count(*) from feedback where sender = ? ";

	public static final String FEEDBACK_ADMIN_GET_TOTALROW
	= " select count(*) from feedback where accepter = 'admin' ";
	
	public static final String FEEDBACK_VIEW
	= " select no, title, content, sender, accepter, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
			+ " refNo, ordNo, levNo "
			+ " from feedback "
			+ " where no = ? ";
	
	public static final String FEEDBACK_WRITE
	= " insert into feedback(no, sender, title, content, accepter, refNo, ordNo, levNo, parentNo) "
		+ " values(feedback_seq.nextval, ?, ?, ?, 'admin', feedback_seq.nextval, 1, 0, feedback_seq.nextval) ";
	
	public static final String FEEDBACK_ANSWER
	= " insert into feedback(no, title, content, sender , accepter, refNo, ordNo, levNo, parentNo) "
			+ " values(feedback_seq.nextval, ?, ?, ?, 'admin', ?, ?, ?, ?) ";

	public static final String FEEDBACK_ANSWER_INCREASE_ORDNO
	= " update feedback set ordNo = ordNo + 1 where refNo = ? and ordNo >= ? ";
	
	public static final String FEEDBACK_DELETE 
	= " delete from feedback where no = ? ";

}
