package com.ktdsuniversity.edu.tmdb.dao.query;

public class MakeQuery {

	public static String makeSelectQuery() {
		StringBuffer query = new StringBuffer();
		
	    query.append("	SELECT M.MAKE_ID                            ");
	    query.append("     , M.PRODUCER_ID                          ");
	    query.append("     , M.MOVIE_ID                             ");
	    query.append("     , M.ROLE                                 ");
	    query.append("     , M.PART                                 ");
	    query.append("     , P.PRODUCER_NAME                        ");
	    query.append("  FROM MAKE M                                 ");
	    query.append(" INNER JOIN PRODUCER P                        ");
	    query.append("    ON P.PRODUCER_ID = M.PRODUCER_ID          ");
	    query.append(" WHERE M.MOVIE_ID = ?     ");
		return query.toString();
	}
	
	
                                                                   
}
