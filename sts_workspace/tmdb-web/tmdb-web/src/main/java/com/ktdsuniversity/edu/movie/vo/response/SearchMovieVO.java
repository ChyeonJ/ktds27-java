package com.ktdsuniversity.edu.movie.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.movie.vo.MovieVO;

public class SearchMovieVO {
	
	private List<MovieVO> result;
	private int count;
	
	public List<MovieVO> getResult() {
		return this.result;
	}
	public void setResult(List<MovieVO> result) {
		this.result = result;
	}
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
