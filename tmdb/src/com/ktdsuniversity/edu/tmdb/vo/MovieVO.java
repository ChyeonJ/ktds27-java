package com.ktdsuniversity.edu.tmdb.vo;

import java.util.List;

/**
 * @TableName MOVIE
 * @TableComment null
 */
public class MovieVO {

    /**
     * @ColumnName MOVIE_ID
     * @ColumnType VARCHAR2(18)
     * @ColumnComment MV-YYYYMMDD일련번호(6자리)
     */
    private String movieId;

    /**
     * @ColumnName POSTER_URL
     * @ColumnType VARCHAR2(2048)
     * @ColumnComment 대표 포스터의 URL을 작성, 이미지의 크기는 100 X 300
     */
    private String posterUrl;

    /**
     * @ColumnName TITLE
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String title;

    /**
     * @ColumnName MOVIE_RATING
     * @ColumnType VARCHAR2(3)
     * @ColumnComment null
     */
    private String movieRating;

    /**
     * @ColumnName OPEN_DATE
     * @ColumnType DATE
     * @ColumnComment null
     */
    private String openDate;

    /**
     * @ColumnName OPEN_COUNTRY
     * @ColumnType VARCHAR2(2)
     * @ColumnComment null
     */
    private String openCountry;

    /**
     * @ColumnName RUNNING_TIME
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment null
     */
    private int runningTime;

    /**
     * @ColumnName INTRODUCE
     * @ColumnType VARCHAR2(60)
     * @ColumnComment null
     */
    private String introduce;

    /**
     * @ColumnName SYNOPIS
     * @ColumnType VARCHAR2(400)
     * @ColumnComment null
     */
    private String synopis;

    /**
     * @ColumnName ORIGINAL_TITLE
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String originalTitle;

    /**
     * @ColumnName STATE
     * @ColumnType VARCHAR2(5)
     * @ColumnComment null
     */
    private String state;

    /**
     * @ColumnName LANGUAGE
     * @ColumnType VARCHAR2(6)
     * @ColumnComment null
     */
    private String language;

    /**
     * @ColumnName BUDGET
     * @ColumnType NUMBER(11, 2)
     * @ColumnComment null
     */
    private float budget;

    /**
     * @ColumnName PROFIT
     * @ColumnType NUMBER(12, 2)
     * @ColumnComment null
     */
    private float profit;
    
    private List<CategoryVO> category;
	private List<MakeVO> make;
    private List<AppearanceVO> appearance;

    public List<CategoryVO> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryVO> category) {
		this.category = category;
	}

	public List<MakeVO> getMake() {
		return make;
	}

	public void setMake(List<MakeVO> make) {
		this.make = make;
	}

	public List<AppearanceVO> getAppearance() {
		return appearance;
	}

	public void setAppearance(List<AppearanceVO> appearance) {
		this.appearance = appearance;
	}

    public String getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getPosterUrl() {
        return this.posterUrl;
    }
    
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getMovieRating() {
        return this.movieRating;
    }
    
    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }
    
    public String getOpenDate() {
        return this.openDate;
    }
    
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
    
    public String getOpenCountry() {
        return this.openCountry;
    }
    
    public void setOpenCountry(String openCountry) {
        this.openCountry = openCountry;
    }
    
    public int getRunningTime() {
        return this.runningTime;
    }
    
    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
    
    public String getIntroduce() {
        return this.introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    public String getSynopis() {
        return this.synopis;
    }
    
    public void setSynopis(String synopis) {
        this.synopis = synopis;
    }
    
    public String getOriginalTitle() {
        return this.originalTitle;
    }
    
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public float getBudget() {
        return this.budget;
    }
    
    public void setBudget(float budget) {
        this.budget = budget;
    }
    
    public float getProfit() {
        return this.profit;
    }
    
    public void setProfit(float profit) {
        this.profit = profit;
    }

	@Override
	public String toString() {
		return "MovieVO [movieId=" + movieId + ", posterUrl=" + posterUrl + ", title=" + title + ", movieRating="
				+ movieRating + ", openDate=" + openDate + ", openCountry=" + openCountry + ", runningTime="
				+ runningTime + ", introduce=" + introduce + ", synopis=" + synopis + ", originalTitle=" + originalTitle
				+ ", state=" + state + ", language=" + language + ", budget=" + budget + ", profit=" + profit
				+ ", category=" + category + ", make=" + make + ", appearance=" + appearance + "]";
	}
    

}