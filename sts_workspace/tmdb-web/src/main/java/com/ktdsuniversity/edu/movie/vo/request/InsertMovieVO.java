package com.ktdsuniversity.edu.movie.vo.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class InsertMovieVO {
	
	@NotEmpty(message="포스터Url을 입력하세요")
	private String posterUrl;
	@NotEmpty(message="제목을 입력하세요")
	private String title;
	private String movieRating;
	private String openDate; 
	private String openCountry;
	private int runningTime; 
	private String introduce;
	@NotEmpty(message="개요를 입력하세요")
	private String synopsis;
	private String originalTitle;
	@NotEmpty(message="개봉상태를 입력하세요")
	private String state;
	@NotEmpty(message="원어를 입력하세요")
	private String language;
	private double budget; 
	private double profit;
	
	private MultipartFile readyFile;
	private String id;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MultipartFile getReadyFile() {
		return this.readyFile;
	}
	public void setReadyFile(MultipartFile readyFile) {
		this.readyFile = readyFile;
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
	public String getSynopsis() {
		return this.synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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
	public double getBudget() {
		return this.budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getProfit() {
		return this.profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	

}
