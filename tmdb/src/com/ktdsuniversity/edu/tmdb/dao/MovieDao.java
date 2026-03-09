package com.ktdsuniversity.edu.tmdb.dao;

import com.ktdsuniversity.edu.tmdb.dao.query.MovieQuery;
import com.ktdsuniversity.edu.tmdb.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.tmdb.db.helper.SQLType;
import com.ktdsuniversity.edu.tmdb.vo.MovieVO;

public class MovieDao {
	
	private DataAccessHelper dah;
	
	public MovieDao(DataAccessHelper dah) {
		this.dah = dah;
	}

	public MovieVO selectMovie(String movieId) {
		
		MovieVO result = new MovieVO();
		
		this.dah.preparedStatement(MovieQuery.makeSelectQuery(), (pstmt) -> {
			pstmt.setString(1, movieId);
		});
		
		this.dah.executeQuery(SQLType.SELECT, (rs) -> {
			result.setMovieId(rs.getString("MOVIE_ID"));
			result.setPosterUrl(rs.getString("POSTER_URL"));
			result.setTitle(rs.getString("TITLE"));
			result.setMovieRating(rs.getString("MOVIE_RATING"));
			result.setOpenDate(rs.getString("OPEN_DATE"));
			result.setOpenCountry(rs.getString("OPEN_COUNTRY"));
			result.setRunningTime(rs.getInt("RUNNING_TIME"));
			result.setIntroduce(rs.getString("INTRODUCE"));
			result.setSynopis(rs.getString("SYNOPIS"));
			result.setOriginalTitle(rs.getString("ORIGINAL_TITLE"));
			result.setState(rs.getString("STATE"));
			result.setLanguage(rs.getString("LANGUAGE"));
			result.setBudget(rs.getFloat("BUDGET"));
			result.setProfit(rs.getFloat("PROFIT"));
		});
		
		return result;
	}

}
