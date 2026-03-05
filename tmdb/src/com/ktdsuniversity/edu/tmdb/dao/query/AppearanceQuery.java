package com.ktdsuniversity.edu.tmdb.dao.query;

public class AppearanceQuery {

	public static String appearanceSelectQuery() {
		StringBuffer query = new StringBuffer();          
		
	    query.append("	SELECT AP.APPEARANCE_ID                   ");
	    query.append("     , AP.ACTOR_ID                          ");
	    query.append("     , AP.MOVIE_ID                          ");
	    query.append("     , AP.\"CHARACTER\"                       ");
	    query.append("     , A.ACTOR_NAME                         ");
	    query.append("     , A.ACTOR_PROFILE_URL                  ");
	    query.append("  FROM APPEARANCE AP                        ");
	    query.append(" INNER JOIN ACTOR A                         ");
	    query.append("    ON AP.ACTOR_ID = A.ACTOR_ID             ");
	    query.append(" WHERE AP.MOVIE_ID = ?   ");
		return query.toString();
	}

}
