package com.ktdsuniversity.edu.board.vo;

public class BoardVO {
	private String id;
	private String title;
	private String content;
	private int viewCount;
	private String writeDate;
	private String latestModifyDate;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return this.viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getWriteDate() {
		return this.writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getLatestModifyDate() {
		return this.latestModifyDate;
	}
	public void setLatestModifyDate(String latestModifyDate) {
		this.latestModifyDate = latestModifyDate;
	}
	
	@Override
	public String toString() {
		return id + title + content + viewCount + writeDate + latestModifyDate
;
	}
	
}
