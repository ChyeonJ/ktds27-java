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
	
	private DataAccessHelper dah;
	
	public ReplyDao(DataAccessHelper dah) {
		this.dah = dah;
	}
	
	//List<ReplyVO> 댓글 목록 조회(게시글 아이디)
	// 게시글에 등록된 모든 댓글 조회 (대댓글 포함) - 계층 조회!
	public List<ReplyVO> replyListSelect(String id){
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
			return resultO;
	}
	
	//ReplyVO 댓글 조회(댓글 아이디)
	public  ReplyVO selectOne (String id) {
			
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
			return result;
	}
	//List<ReplyVO> 대댓글 조회(댓글아이디)
	// 댓글에 등록된 모든 대댓글 조회 (대댓글 포함) - 계층 조회
	public List<ReplyVO> replyTopTop(String id){
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
			return resultO;
	}	
	// void 댓글 등록 (ReplyVO)
	public void insertReplyGo (ReplyVO replyVo) {
		
			dah.preparedStatement(ReplyQuery.insertReply(), (pstmt) -> {
				pstmt.setString(1, replyVo.getBoardId());
				pstmt.setString(2, replyVo.getContent());
			});
			dah.executeQuery(SQLType.INSERT, null);
	}
	
	// void 대댓글 등록 (ReplyVO)
	public void insertTopReply (ReplyVO replyVo) {
			dah.preparedStatement(ReplyQuery.insertTopReply(), (pstmt) -> {
				pstmt.setString(1, replyVo.getBoardId());
				pstmt.setString(2, replyVo.getTopId());
				pstmt.setString(3, replyVo.getContent());
			});
			dah.executeQuery(SQLType.INSERT, null);
	}
	
	// void 댓글 수정 (ReplyVO)
	
	public void upedateReply(ReplyVO replyVo) {
			dah.preparedStatement(ReplyQuery.updateReply(), (stmt) -> {
				stmt.setString(1, replyVo.getContent());
				stmt.setString(2, replyVo.getId());
			});
			dah.executeQuery(SQLType.UPDATE, null);
	}
	
	// void 댓글 삭제 (댓글 아이디)
	
	public  void deleteReply(String id) {
			dah.preparedStatement(ReplyQuery.deleteReply(), (stmt) -> {
				stmt.setString(1, id);
			});
			dah.executeQuery(SQLType.DELETE, null);
	}
}
