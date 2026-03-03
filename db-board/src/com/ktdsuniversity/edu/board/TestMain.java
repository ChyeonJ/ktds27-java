package com.ktdsuniversity.edu.board;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.vo.BoardVO;

public class TestMain {
	
	public static void main(String[] args) {
		//게시글 작성 (DB 게시글 생성)
		BoardVO newArticle = new BoardVO();
		newArticle.setTitle("새로운 게시글입니다.");
		newArticle.setContent("새로운 게시글의 내용입니다.");
		
		BoardDao boardDao = new BoardDao();
		boardDao.createNewArticle2(newArticle);
		System.out.println("");
		
		//Update
		BoardVO modifyArticle = new BoardVO();
		modifyArticle.setId("BO-20260303-000023");
		modifyArticle.setTitle("제목이 수정되었습니다");
		modifyArticle.setContent("내용이 수정 되었슴돠");
		
		boardDao.modifyArticle(modifyArticle);
		
		//delete
		boardDao.deleteArticle("BO-20260303-000003");
		
		//게시글 조회 BO-20260303-000031
		BoardVO article = boardDao.readArticle("BO-20260303-000031");
		System.out.println(article);
		
	}

}
