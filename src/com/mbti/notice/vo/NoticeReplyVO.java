package com.mbti.notice.vo;

public class NoticeReplyVO {
	private long rno, no;
	private String ncontent, id, writeDate;
	
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
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
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
		return "NoticeReplyVO [rno=" + rno + ", no=" + no + ", ncontent=" + ncontent + ", id=" + id + ", writeDate="
				+ writeDate + "]";
	}
	
}
