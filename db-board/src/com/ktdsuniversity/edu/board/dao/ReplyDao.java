package com.ktdsuniversity.edu.board.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.query.ReplyQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

/**
 * Dao : Data Access Object
 * - Java에서 DB로 데이터 생성, 수정, 삭제, 조회를 하기위한 클래스
 */
public class ReplyDao {
	
	//List<ReplyVO> 댓글 목록 조회(게시글 아이디)
	// 게시글에 등록된 모든 댓글 조회 (대댓글 포함) - 계층 조회!
	public static List<ReplyVO> replyListSelect(String id){
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			List<ReplyVO> resultO = new ArrayList<ReplyVO>();
			dah.preparedStatement(ReplyQuery.replyListSelct(), (stmt) -> {
				stmt.setString(1, id);
			});
			dah.executeQuery(SQLType.SELECT, (rs) -> {
				ReplyVO result = new ReplyVO();
				result.setId(rs.getString("ID"));
				result.setBoardId(rs.getString("BOARD_ID"));
				result.setTopId(rs.getString("TOP_ID"));
				result.setContent(rs.getString("CONTENT"));
				result.setWriteDate(rs.getString("WRITE_DATE"));
				resultO.add(result);
			});
			dah.commit();
			return resultO;
		} 
		catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		return null;
		
	}
	
	//ReplyVO 댓글 조회(댓글 아이디)
	public static ReplyVO selectOne (String id) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			
			ReplyVO result = new ReplyVO();
			dah.preparedStatement(ReplyQuery.selectOne(), (stmt) -> {
				stmt.setString(1, id);
			}); 
		    dah.executeQuery(SQLType.SELECT, (rs) -> {
		    	result.setId(rs.getString("ID"));
		    	result.setBoardId(rs.getString("BOARD_ID"));
		    	result.setTopId(rs.getString("TOP_ID"));
		    	result.setContent(rs.getString("CONTENT"));
		    	result.setWriteDate(rs.getString("WRITE_DATE"));
		    });
			dah.commit();
			return result;
		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		
		return null;
	}
	//List<ReplyVO> 대댓글 조회(댓글아이디)
	// 댓글에 등록된 모든 대댓글 조회 (대댓글 포함) - 계층 조회
	public static List<ReplyVO> replyTopTop(String id){
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			List<ReplyVO> resultO = new ArrayList<ReplyVO>();
			dah.preparedStatement(ReplyQuery.replyTopTop(), (stmt) -> {
				stmt.setString(1, id);
			});
			dah.executeQuery(SQLType.SELECT, (rs) -> {
				ReplyVO result = new ReplyVO();
				result.setId(rs.getString("ID"));
				result.setBoardId(rs.getString("BOARD_ID"));
				result.setTopId(rs.getString("TOP_ID"));
				result.setContent(rs.getString("CONTENT"));
				result.setWriteDate(rs.getString("WRITE_DATE"));
				resultO.add(result);
			});
			dah.commit();
			return resultO;
		} 
		catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		return null;
		
	}	
	// void 댓글 등록 (ReplyVO)
	public void insertReplyGo (ReplyVO replyVo) {
		
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		try {
			dah.preparedStatement(ReplyQuery.insertReply(), (pstmt) -> {
				pstmt.setString(1, replyVo.getBoardId());
				pstmt.setString(2, replyVo.getContent());
			});
			dah.executeQuery(SQLType.INSERT, null);
			dah.commit();
		}
		catch(RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		
	}
	
	// void 대댓글 등록 (ReplyVO)
	public void insertTopReply (ReplyVO replyVo) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			dah.preparedStatement(ReplyQuery.insertTopReply(), (pstmt) -> {
				pstmt.setString(1, replyVo.getBoardId());
				pstmt.setString(2, replyVo.getTopId());
				pstmt.setString(3, replyVo.getContent());
			});
			dah.executeQuery(SQLType.INSERT, null);
			dah.commit();
		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		
	}
	
	// void 댓글 수정 (ReplyVO)
	
	public void upedateReply(ReplyVO replyVo) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			
			dah.preparedStatement(ReplyQuery.updateReply(), (stmt) -> {
				stmt.setString(1, replyVo.getContent());
				stmt.setString(2, replyVo.getId());
			});
			dah.executeQuery(SQLType.UPDATE, null);
			dah.commit();
		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
		
	}
	
	// void 댓글 삭제 (댓글 아이디)
	
	public static void deleteReply(String id) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");
		
		try {
			dah.preparedStatement(ReplyQuery.deleteReply(), (stmt) -> {
				stmt.setString(1, id);
			});
			dah.executeQuery(SQLType.DELETE, null);
			dah.commit();
		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
		finally {
			dah.close();
		}
	}
	
}
