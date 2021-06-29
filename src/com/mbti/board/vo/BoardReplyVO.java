package com.mbti.board.vo;

public class BoardReplyVO {
	
	private long rno, no;
	private String rcontent, id, writeDate;
	public long getRno() {
		return rno;
	}
	public void setRno(long rno) {
		this.rno = rno;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	@Override
	public String toString() {
		return "BoardReplyVO [rno=" + rno + ", no=" + no + ", rcontent=" + rcontent + ", id=" + id + ", writeDate="
				+ writeDate + "]";
	}
}
