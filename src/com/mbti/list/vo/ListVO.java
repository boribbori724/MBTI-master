package com.mbti.list.vo;

public class ListVO {
	
	private long no, hit;
	
	private String title, image, url;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ListVO [no=" + no + ", hit=" + hit + ", title=" + title + ", image=" + image + ", url=" + url + "]";
	}
	
}
