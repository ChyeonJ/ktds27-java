package com.ktdsuniversity.edu.replies.service;

import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.request.DeleteVO;
import com.ktdsuniversity.edu.replies.vo.request.RecommendCntVO;
import com.ktdsuniversity.edu.replies.vo.request.UpdateVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.replies.vo.response.UpdateResultVO;

import jakarta.validation.Valid;

public interface RepliesService {

	RepliesVO createNewReply(@Valid CreateVO createVO);

	SearchResultVO findRepliesByArticleId(String articleId);

	RecommendCntVO updateRecommendCntByArticleId(String articleId);

	DeleteVO deleteReplyByReplyId(String replyId);

	UpdateResultVO updateReply(@Valid UpdateVO updateVO);



}
