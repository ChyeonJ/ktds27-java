package com.ktdsuniversity.edu.movie.service;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.InsertMovieVO;
import com.ktdsuniversity.edu.movie.vo.request.UpdateMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchOneMovieVO;

public interface MovieService {

	SearchMovieVO findMovieList();

	boolean createMovieData(InsertMovieVO insertMovieVO);

	MovieVO findOneMovie(String movieId);

	boolean updateMovieById(UpdateMovieVO updateMovieVO);

	boolean deleteMovie(String id);

}
