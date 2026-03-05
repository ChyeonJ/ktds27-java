package com.ktdsuniversity.edu.tmdb.vo;

/**
 * @TableName APPEARANCE
 * @TableComment null
 */
public class AppearanceVO {

    /**
     * @ColumnName APPEARANCE_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment AP-YYYYMMDD일련번호(6자리)
     */
    private String appearanceId;

    /**
     * @ColumnName ACTOR_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment MV-YYYYMMDD일련번호(6자리)
     */
    private String actorId;

    /**
     * @ColumnName MOVIE_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment AC-YYYYMMDD일련번호(6자리)
     */
    private String movieId;

    /**
     * @ColumnName CHARACTER
     * @ColumnType VARCHAR2(40)
     * @ColumnComment null
     */
    private String character;
    
    private ActorVO actor;

    public ActorVO getActor() {
		return actor;
	}

	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	public String getAppearanceId() {
        return this.appearanceId;
    }
    
    public void setAppearanceId(String appearanceId) {
        this.appearanceId = appearanceId;
    }
    
    public String getActorId() {
        return this.actorId;
    }
    
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    
    public String getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getCharacter() {
        return this.character;
    }
    
    public void setCharacter(String character) {
        this.character = character;
    }
    public AppearanceVO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AppearanceVO [appearanceId=" + appearanceId + ", actorId=" + actorId + ", movieId=" + movieId
				+ ", character=" + character + ", actor=" + actor + "]";
	}
    
    
    
}