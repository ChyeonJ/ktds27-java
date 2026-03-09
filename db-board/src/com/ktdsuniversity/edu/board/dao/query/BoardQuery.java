package com.ktdsuniversity.edu.board.dao.query;

public class BoardQuery {
	
	public static String makeInsertQuery() {
		StringBuffer query = new StringBuffer();            
		query.append(" INSERT INTO BOARD.BOARD                                                            ");
		query.append("  (ID                                                                               ");
		query.append("  , TITLE                                                                           ");
		query.append("  , CONTENT                                                                         ");
		query.append("  , WRITE_DATE)                                                                     ");
		query.append(" VALUES                                                                             ");
		query.append("  ('BO-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_BOARD_PK.NEXTVAL, 6, '0')     ");
		query.append(" , ?                                                                             ");
		query.append(" , ?                                                                              ");
		query.append(" , SYSDATE)                                                                         ");
		
		return query.toString();
	
	}
	
	public static String makeUpdateQuery() {
		StringBuffer query = new StringBuffer();            
		query.append("UPDATE BOARD                            ");
		query.append("   SET TITLE = ?                     ");
		query.append("     , CONTENT = ?                   ");
		query.append("     , LATEST_MODIFY_DATE = SYSDATE     ");
		query.append(" WHERE ID = ?      ");
		
		return query.toString();
	}
	
	public static String makeDeleteQuery() {
		StringBuffer query = new StringBuffer();            
	query.append("	DELETE                                  ");
	query.append("	  FROM BOARD                            ");
	query.append("	 WHERE ID = ?        ");
	
	return query.toString();
	}
	
	public static String makeUpdateViewCountQuery() {
		StringBuffer query = new StringBuffer();      
		query.append(" UPDATE BOARD                             ");
		query.append("    SET VIEW_COUNT = VIEW_COUNT + 1       ");
		query.append("  WHERE ID = ?         ");
		
		return query.toString();
	}
	
	public static String makeSelectOneQuery() {
		StringBuffer query = new StringBuffer();      
		query.append(" SELECT ID                                                                            ");
	    query.append("      , TITLE                                                                         ");
	    query.append("      , CONTENT                                                                       ");
	    query.append("      , VIEW_COUNT                                                                    ");
	    query.append("      , TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE                    ");
	    query.append("      , TO_CHAR(LATEST_MODIFY_DATE, 'YYYY-MM-DD HH24:MI:SS') AS LATEST_MODIFY_DATE    ");
	    query.append("   FROM BOARD                                                                         ");
	    query.append("  WHERE ID = ?                                                     ");
	    return query.toString();
	}
	
	public static String makeSelectAllQuery() {
		StringBuffer query = new StringBuffer();      
		query.append(" SELECT ID                                                                            ");
	    query.append("      , TITLE                                                                         ");
	    query.append("      , CONTENT                                                                       ");
	    query.append("      , VIEW_COUNT                                                                    ");
	    query.append("      , TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE                    ");
	    query.append("      , TO_CHAR(LATEST_MODIFY_DATE, 'YYYY-MM-DD HH24:MI:SS') AS LATEST_MODIFY_DATE    ");
	    query.append("   FROM BOARD                                                                         ");
	    return query.toString();
	}
	
}
