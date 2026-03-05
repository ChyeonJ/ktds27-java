package com.ktdsuniversity.edu.tmdb.dao.query;

public class CategoryQuery {

	public static String makeSelectQuery() {
		StringBuffer query = new StringBuffer();
	    query.append(" 	SELECT CATEGORY_ID                                                     ");
	    query.append("       , CATEGORY_NAME                                                   ");
	    query.append("    FROM CATEGORY                                                        ");
	    query.append("   WHERE CATEGORY_ID IN (SELECT CATEGORY_ID                              ");
	    query.append("                           FROM GENRE                                    ");
	    query.append("                          WHERE MOVIE_ID = ?)         ");
		return query.toString();
	}

}
