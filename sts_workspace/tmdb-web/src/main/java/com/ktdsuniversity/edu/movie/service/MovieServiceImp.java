package com.ktdsuniversity.edu.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;

@Service
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieDao movieDao;
	
	@Override
	public SearchMovieVO findMovieList() {
		
		SearchMovieVO result = new SearchMovieVO();
		
		int count = this.movieDao.selectMovieCount();
		result.setCount(count);
		
		List<MovieVO> list =  this.movieDao.selectMovieList();
		result.setResult(list);
		
		return result;
	}

}
