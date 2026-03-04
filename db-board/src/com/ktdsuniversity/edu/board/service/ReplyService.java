package com.ktdsuniversity.edu.board.service;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.ReplyDao;
import com.ktdsuniversity.edu.board.dao.query.ReplyQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

public class ReplyService {
	
	private DataAccessHelper dah;
	private ReplyDao replyDao;
	
	public ReplyService(DataAccessHelper dah) {
		this.dah = dah;
		this.replyDao = new ReplyDao(dah);
	}
	
	//List<ReplyVO> 댓글 목록 조회(게시글 아이디)
	// 게시글에 등록된 모든 댓글 조회 (대댓글 포함) - 계층 조회!
	public List<ReplyVO> replyListSelect(String id){
		List<ReplyVO> result = this.replyDao.replyListSelect(id);
		return result;
	}
	
	//ReplyVO 댓글 조회(댓글 아이디)
	public  ReplyVO selectOne (String id) {
		ReplyVO result = this.replyDao.selectOne(id);
		return result;
	}
	//List<ReplyVO> 대댓글 조회(댓글아이디)
	// 댓글에 등록된 모든 대댓글 조회 (대댓글 포함) - 계층 조회
	public List<ReplyVO> replyTopTop(String id){
		List<ReplyVO> result = this.replyDao.replyTopTop(id);
		return result;
		
	}	
	// void 댓글 등록 (ReplyVO)
	public void insertReplyGo (ReplyVO replyVo) {
		try {
			this.replyDao.insertReplyGo(replyVo);
			dah.commit();
		}
		catch (RuntimeException re) {
			dah.rollback();
		}
	}
	
	// void 대댓글 등록 (ReplyVO)
	public void insertTopReply (ReplyVO replyVo) {
		try {
			this.replyDao.insertTopReply(replyVo);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}
	
	// void 댓글 수정 (ReplyVO)
	
	public void upedateReply(ReplyVO replyVo) {
		try {
			this.replyDao.upedateReply(replyVo);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}
	
	// void 댓글 삭제 (댓글 아이디)
	
	public  void deleteReply(String id) {
		try {
			this.replyDao.deleteReply(id);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}
	
}
