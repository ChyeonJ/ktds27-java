package com.ktdsuniversity.edu.board;

import java.util.List;

import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.service.ReplyService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

public class TestMain {
	
	public static void main(String[] args) {
		//게시글 작성 (DB 게시글 생성)
		BoardVO newArticle = new BoardVO();
		newArticle.setTitle("성능 향상 중이 새로운 게시글입니다.");
		newArticle.setContent("성능 새로운 게시글의 내용입니다.");
		
		DataAccessHelper dah = new DataAccessHelper("localhost",1521,"XE","BOARD", "BOARD");
		
		BoardService boardService = new BoardService(dah);
		boardService.createNewArticle2(newArticle);
		System.out.println("");
		
		//Update
		BoardVO modifyArticle = new BoardVO();
		modifyArticle.setId("BO-20260303-000023");
		modifyArticle.setTitle("제목이 수정되었습니다");
		modifyArticle.setContent("내용이 수정 되었슴돠");
		
		boardService.modifyArticle(modifyArticle);
		
		//delete
		boardService.deleteArticle("BO-20260303-000003");
		
		//게시글 조회 BO-20260303-000031
		BoardVO article = boardService.readArticle("BO-20260303-000031");
		System.out.println(article);
		
		//게시글 전체 조회
		List<BoardVO> articles = boardService.readAllArticles();
		System.out.println(articles + "\n");
		
		//ReplyDao
		ReplyService replyService = new ReplyService(dah);
		
		//댓글 등록
		ReplyVO replyNew = new ReplyVO();
		replyNew.setBoardId("BO-20260303-000023");
		replyNew.setContent("EKEKEKEK가");
		replyService.insertReplyGo(replyNew);
		
		//대댓글 등록
		ReplyVO replyTopNew = new ReplyVO();
		replyTopNew.setBoardId("BO-20260303-000022");
		replyTopNew.setTopId("RP-20260304-000017");
		replyTopNew.setContent("나나나나");
		replyService.insertTopReply(replyTopNew);
		
		//댓글 업데이트
		ReplyVO replyUpdate = new ReplyVO();
		replyUpdate.setContent("나 바뀌었지롱~");
		replyUpdate.setId("RP-20260304-000018");
		replyService.upedateReply(replyUpdate);
		
		//댓글 삭제
		replyService.deleteReply("RP-20260304-000019");
		
		//댓글 하나 조회
		ReplyVO result = replyService.selectOne("RP-20260304-000018");
		System.out.println(result);
		
		//게시글에 등록된 모든 댓글 조회
		List<ReplyVO> rr = replyService.replyListSelect("BO-20260303-000023");
		System.out.println(rr);
		
		List<ReplyVO> r2r = replyService.replyTopTop("RP-20260304-000041");
		System.out.println(r2r);
		
		
		// DB연결 종료
		dah.close();
		
	}

}
