package com.ktdsuniversity.edu.board.vo;

public class ReplyVO {
	private String id;
	private String boardId;
	private String topId;
	private String content;
	private String writeDate;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoardId() {
		return this.boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTopId() {
		return this.topId;
	}
	public void setTopId(String topId) {
		this.topId = topId;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return this.writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	@Override
	public String toString() {
		return id + boardId + topId + content + writeDate;
	}
	
}
