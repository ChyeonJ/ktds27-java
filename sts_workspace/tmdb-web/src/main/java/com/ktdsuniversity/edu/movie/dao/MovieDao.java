package com.ktdsuniversity.edu.movie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.InsertMovieVO;
import com.ktdsuniversity.edu.movie.vo.request.UpdateMovieVO;

@Mapper
public interface MovieDao {

	int selectMovieCount();

	List<MovieVO> selectMovieList();

	int insertMovieData(InsertMovieVO insertMovieVO);

	MovieVO selectOneMovieById(String movieId);

	int updateMovie(UpdateMovieVO updateMovieVO);

	int deleteMovie(String id);

}
