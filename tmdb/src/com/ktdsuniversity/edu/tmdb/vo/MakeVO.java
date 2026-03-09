package com.ktdsuniversity.edu.tmdb.vo;

/**
 * @TableName MAKE
 * @TableComment null
 */
public class MakeVO {

    /**
     * @ColumnName MAKE_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment MK-YYYYMMDD일련번호(6자리)
     */
    private String makeId;

    /**
     * @ColumnName PRODUCER_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment PD-YYYYMMDD일련번호(6자리)
     */
    private String producerId;

    /**
     * @ColumnName MOVIE_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment MV-YYYYMMDD일련번호(6자리)
     */
    private String movieId;

    /**
     * @ColumnName ROLE
     * @ColumnType VARCHAR2(25)
     * @ColumnComment null
     */
    private String role;

    /**
     * @ColumnName PART
     * @ColumnType VARCHAR2(10)
     * @ColumnComment null
     */
    private String part;
    
    private ProducerVO producer;

    public ProducerVO getProducer() {
		return producer;
	}

	public void setProducer(ProducerVO producer) {
		this.producer = producer;
	}

	public String getMakeId() {
        return this.makeId;
    }
    
    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }
    
    public String getProducerId() {
        return this.producerId;
    }
    
    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }
    
    public String getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPart() {
        return this.part;
    }
    
    public void setPart(String part) {
        this.part = part;
    }

	@Override
	public String toString() {
		return "MakeVO [makeId=" + makeId + ", producerId=" + producerId + ", movieId=" + movieId + ", role=" + role
				+ ", part=" + part + ", producer=" + producer + "]";
	}
    

}