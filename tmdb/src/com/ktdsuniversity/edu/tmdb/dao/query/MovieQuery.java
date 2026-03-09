package com.ktdsuniversity.edu.tmdb.dao.query;

public class MovieQuery {
	
	public static String makeSelectQuery() {
		StringBuffer query = new StringBuffer();
	    query.append("	SELECT MOVIE_ID                                            ");
	    query.append("     , POSTER_URL                                            ");
	    query.append("     , TITLE                                                 ");
	    query.append("     , MOVIE_RATING                                          ");
	    query.append("     , TO_CHAR(OPEN_DATE, 'YYYY-MM-DD') AS OPEN_DATE         ");
	    query.append("     , OPEN_COUNTRY                                          ");
	    query.append("     , RUNNING_TIME                                          ");
	    query.append("     , INTRODUCE                                             ");
	    query.append("     , SYNOPIS                                               ");
	    query.append("     , ORIGINAL_TITLE                                        ");
	    query.append("     , STATE                                                 ");
	    query.append("     , \"LANGUAGE\"                                          ");
	    query.append("     , BUDGET                                                ");
	    query.append("     , PROFIT                                                ");
	    query.append("  FROM TMDB.MOVIE                                            ");
	    query.append(" WHERE MOVIE_ID = ?                      ");
	    return query.toString();
	}    
}
