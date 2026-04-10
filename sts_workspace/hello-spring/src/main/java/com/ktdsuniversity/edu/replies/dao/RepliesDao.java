package com.ktdsuniversity.edu.replies.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.request.RecommendCntVO;
import com.ktdsuniversity.edu.replies.vo.request.UpdateVO;

import jakarta.validation.Valid;

@Mapper
public interface RepliesDao {

	int insertNewReply(CreateVO createVO);

	RepliesVO selectReplyByReplyId(String id);

	List<RepliesVO> selectRepliesByArticleId(String articleId);

	int selectRepliesCountByArticleId(String articleId);

	int updateRecommentCnt(String articleId);

	int deleteReply(String replyId);

	int updateReplyByReplyId(@Valid UpdateVO updateVO);
	
	
	
}
