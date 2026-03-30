package com.ktdsuniversity.edu.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.InsertMovieVO;
import com.ktdsuniversity.edu.movie.vo.request.UpdateMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchOneMovieVO;

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
	
	@Override
	public boolean createMovieData(InsertMovieVO insertMovieVO) {
		int resultCount = this.movieDao.insertMovieData(insertMovieVO);
		return resultCount == 1;
	}
	
	@Override
	public MovieVO findOneMovie(String movieId) {
		MovieVO result = this.movieDao.selectOneMovieById(movieId);
		return result;
	}
	
	@Override
	public boolean updateMovieById(UpdateMovieVO updateMovieVO) {
		int resultCount = this.movieDao.updateMovie(updateMovieVO);
		return resultCount == 1;
	}
	
	@Override
	public boolean deleteMovie(String id) {
		int resultCount = this.movieDao.deleteMovie(id);
		return resultCount == 1;
	}

}
