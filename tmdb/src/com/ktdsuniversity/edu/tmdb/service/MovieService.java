package com.ktdsuniversity.edu.tmdb.service;

import java.util.List;

import com.ktdsuniversity.edu.tmdb.dao.AppearanceDao;
import com.ktdsuniversity.edu.tmdb.dao.CategoryDao;
import com.ktdsuniversity.edu.tmdb.dao.MakeDao;
import com.ktdsuniversity.edu.tmdb.dao.MovieDao;
import com.ktdsuniversity.edu.tmdb.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.tmdb.vo.AppearanceVO;
import com.ktdsuniversity.edu.tmdb.vo.CategoryVO;
import com.ktdsuniversity.edu.tmdb.vo.MakeVO;
import com.ktdsuniversity.edu.tmdb.vo.MovieVO;

public class MovieService {
	
	private DataAccessHelper dah;
	private MovieDao moviedao;
	private CategoryDao categoryDao;
	private MakeDao makeDao;
	private AppearanceDao appearanceDao;
	
	/*
	 * private ActorDat actorDao;
	 * private MakeDao makeDao
	 */

	public MovieService(DataAccessHelper dah) {
		this.dah = dah;
		this.moviedao = new MovieDao(this.dah);
		this.categoryDao = new CategoryDao(this.dah);
		this.makeDao = new MakeDao(this.dah);
		this.appearanceDao = new AppearanceDao(this.dah);
	}

	public MovieVO readMovie(String movieId) {
		MovieVO movie = this.moviedao.selectMovie(movieId);
		
		List<CategoryVO> categories = this.categoryDao.selectCategoriesByMovieId(movieId);
		movie.setCategory(categories);
		
		List<MakeVO> make = this.makeDao.selectMakeByMovieId(movieId);
		movie.setMake(make);
		
		List<AppearanceVO> appearances = this.appearanceDao.selectAppearanceByMovieId(movieId);
		movie.setAppearance(appearances);
		
		return movie;
	}

}
