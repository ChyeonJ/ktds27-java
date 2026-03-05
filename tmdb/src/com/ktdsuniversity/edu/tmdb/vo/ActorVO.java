package com.ktdsuniversity.edu.tmdb.vo;

/**
 * @TableName ACTOR
 * @TableComment null
 */
public class ActorVO {

    /**
     * @ColumnName ACTOR_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment AC-YYYYMMDD일련번호(6자리)
     */
    private String actorId;

    /**
     * @ColumnName ACTOR_NAME
     * @ColumnType VARCHAR2(25)
     * @ColumnComment null
     */
    private String actorName;

    /**
     * @ColumnName ACTOR_PROFILE_URL
     * @ColumnType VARCHAR2(2048)
     * @ColumnComment null
     */
    private String actorProfileUrl;

    public String getActorId() {
        return this.actorId;
    }
    
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    
    public String getActorName() {
        return this.actorName;
    }
    
    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
    
    public String getActorProfileUrl() {
        return this.actorProfileUrl;
    }
    
    public void setActorProfileUrl(String actorProfileUrl) {
        this.actorProfileUrl = actorProfileUrl;
    }

	@Override
	public String toString() {
		return "ActorVO [actorId=" + actorId + ", actorName=" + actorName + ", actorProfileUrl=" + actorProfileUrl
				+ "]";
	}
    
    
}