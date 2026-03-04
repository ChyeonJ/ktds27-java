package com.ktdsuniversity.edu.board.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.dao.query.BoardQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.BoardVO;

/**
 * 트랜잭션 처리.
 */
public class BoardService {
	
	private DataAccessHelper dah;
	private BoardDao boardDao;
	
	public BoardService(DataAccessHelper dah) {
		this.dah = dah;
		this.boardDao = new BoardDao(this.dah);
	}
	
	public List<BoardVO> readAllArticles() {
		List<BoardVO> result = this.boardDao.readAllArticles();
		return result;
	}	

	public BoardVO readArticle(String articleId) {
		try {
			this.boardDao.updateViewCount(articleId);
			BoardVO result = this.boardDao.readArticle(articleId);
			this.dah.commit();
			return result;
		}
		catch(RuntimeException re){
			re.printStackTrace();
			this.dah.rollback();
		}
		return null;
	}
	
	public void deleteArticle(String articeId) {
		try {
			this.boardDao.deleteArticle(articeId);
			this.dah.commit();
		}
		catch(RuntimeException re){
			this.dah.rollback();
		}
	}
	
	public void modifyArticle(BoardVO modifyArticle) {
		try {
			this.boardDao.modifyArticle(modifyArticle);
			this.dah.commit();
		}
		catch(RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());
		}
	}
	
	public void createNewArticle2 (BoardVO newArticle) {
		try {
			this.boardDao.createNewArticle2(newArticle);
			this.dah.commit();
		}
		catch (RuntimeException re) {
			dah.rollback();
		}
	}
}
