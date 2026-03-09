package com.ktdsuniversity.edu.board.dao.query;

public class ReplyQuery {
	
	public static String insertReply() {
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO BOARD.REPLY                                                          ");
		query.append("  (ID                                                                             ");
		query.append("  , BOARD_ID                                                                      ");
		query.append("  , TOP_ID                                                                        ");
		query.append("  , CONTENT                                                                       ");
		query.append("  , WRITE_DATE)                                                                   ");
		query.append(" VALUES                                                                           ");
		query.append("  ('RP-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_REPLY_PK.NEXTVAL, 6, '0')   ");
		query.append(" , ?                                                                         ");
		query.append(" , null                                                                       ");
		query.append(" , ?                                                                          ");
		query.append(" ,  SYSDATE )                                                                     ");
		return query.toString();
	}
	
	public static String insertTopReply() {
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO BOARD.REPLY                                                          ");
		query.append("  (ID                                                                             ");
		query.append("  , BOARD_ID                                                                      ");
		query.append("  , TOP_ID                                                                        ");
		query.append("  , CONTENT                                                                       ");
		query.append("  , WRITE_DATE)                                                                   ");
		query.append(" VALUES                                                                           ");
		query.append("  ('RP-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_REPLY_PK.NEXTVAL, 6, '0')   ");
		query.append(" , ?                                                                         ");
		query.append(" , ?                                                                      ");
		query.append(" , ?                                                                          ");
		query.append(" ,  SYSDATE )                                                                     ");
		return query.toString();
		
	}
	
	public static String updateReply() {
		StringBuffer query = new StringBuffer();
		
		query.append(" UPDATE BOARD.REPLY                 ");
		query.append("    SET CONTENT = ?             ");
		query.append("      , WRITE_DATE = SYSDATE        ");
		query.append("  WHERE ID= ?     ");
		return query.toString();
	}
	
	public static String deleteReply() {
		StringBuffer query = new StringBuffer();
		query.append(" DELETE FROM BOARD.REPLY ");
		query.append(" WHERE ID = ?             ");
		return query.toString();
	}                                       
	
	
	public static String selectOne() {
		StringBuffer query = new StringBuffer();
	    query.append( " 	SELECT ID          ");
	    query.append( "      , BOARD_ID        ");
	    query.append( "      , TOP_ID          ");
	    query.append( "      , CONTENT         ");
	    query.append( "      , TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE      ");
	    query.append( "  FROM BOARD.REPLY      ");
	    query.append( " WHERE ID = ?           ");
	    return query.toString();
	}
	
	public static String replyListSelct() {
		StringBuffer query = new StringBuffer();
	    query.append("	 SELECT ID                                                ");
	    query.append("      , BOARD_ID                                            ");
	    query.append("      , TOP_ID                                              ");
	    query.append("      , CONTENT                                             ");
	    query.append("      , TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE        ");
	    query.append("   FROM REPLY                                               ");
	    query.append("  START WITH BOARD_ID = ?             ");
	    query.append("    AND TOP_ID IS NULL                                      ");
	    query.append("CONNECT BY PRIOR ID = TOP_ID                                ");
	    return query.toString();
	}
	
	public static String replyTopTop() {
		StringBuffer query = new StringBuffer();
	    query.append(" 	SELECT ID                                                              ");
	    query.append("  , BOARD_ID                                                             ");
	    query.append("  , TOP_ID                                                               ");
	    query.append("  , CONTENT                                                              ");
	    query.append("  , TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE           ");
	    query.append("   FROM REPLY                                                            ");
	    query.append("  START WITH TOP_ID = ?                               ");
	    query.append(" CONNECT BY PRIOR ID = TOP_ID                                            ");
	    return query.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
