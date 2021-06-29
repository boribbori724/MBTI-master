package com.mbti.feedback.vo;

public class FeedbackVO {
	
	private long no;
	private String title; 
	private String content;
	private String id;
	private String writeDate;
	private String sender;
	private String accepter;
	private long refNo;
	private long ordNo;
	private long levNo;
	private long parentNo;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public long getRefNo() {
		return refNo;
	}
	public void setRefNo(long refNo) {
		this.refNo = refNo;
	}
	public long getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(long ordNo) {
		this.ordNo = ordNo;
	}
	public long getLevNo() {
		return levNo;
	}
	public void setLevNo(long levNo) {
		this.levNo = levNo;
	}
	public long getParentNo() {
		return parentNo;
	}
	public void setParentNo(long parentNo) {
		this.parentNo = parentNo;
	}
	
	@Override
	public String toString() {
		return "FeedbackVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", writeDate="
				+ writeDate + ", sender=" + sender + ", accepter=" + accepter + ", refNo=" + refNo + ", ordNo=" + ordNo
				+ ", levNo=" + levNo + ", parentNo=" + parentNo + "]";
	}

}
