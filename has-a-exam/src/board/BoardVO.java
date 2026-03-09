package board;

import java.util.List;

/**
 * @TableName BOARD
 * @TableComment 게시글의 목록
 */
public class BoardVO {

    /**
     * @ColumnName ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment 게시글의 아이디 (BO-YYYYMMDD-일련번호(6자리))
     */
    private String id;

    /**
     * @ColumnName TITLE
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 게시글의 제목
     */
    private String title;

    /**
     * @ColumnName CONTENT
     * @ColumnType CLOB
     * @ColumnComment 게시글의 내용
     */
    private String content;

    /**
     * @ColumnName VIEW_COUNT
     * @ColumnType NUMBER(8, 0)
     * @ColumnComment 게시글의 조회수
     */
    private int viewCount;

    /**
     * @ColumnName WRITE_DATE
     * @ColumnType DATE
     * @ColumnComment 게시글을 작성한 날짜와 시간
     */
    private String writeDate;

    /**
     * @ColumnName LATEST_MODIFY_DATE
     * @ColumnType DATE
     * @ColumnComment 게시글을 마지막으로 수정한 날짜와 시간
     */
    private String latestModifyDate;
    
    // has
    private List<ReplyVO> reply;

    public List<ReplyVO> getReply() {
		return reply;
	}

	public void setReply(List<ReplyVO> reply) {
		this.reply = reply;
	}

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
        return "BoardVO(id: " + id + ", title: " + title + ", content: " + content + ", viewCount: " + viewCount + ", writeDate: " + writeDate + ", latestModifyDate: " + latestModifyDate + ", )";
    }
}