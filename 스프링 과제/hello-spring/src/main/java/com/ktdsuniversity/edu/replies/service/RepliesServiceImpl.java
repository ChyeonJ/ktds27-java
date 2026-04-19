package com.ktdsuniversity.edu.replies.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.common.utils.ObjectUtils;
import com.ktdsuniversity.edu.common.utils.SessionUtils;
import com.ktdsuniversity.edu.exceptions.HelloSpringApiException;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;
import com.ktdsuniversity.edu.files.vo.request.SearchFileGroupVO;
import com.ktdsuniversity.edu.replies.dao.RepliesDao;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.request.DeleteVO;
import com.ktdsuniversity.edu.replies.vo.request.RecommendCntVO;
import com.ktdsuniversity.edu.replies.vo.request.UpdateVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.replies.vo.response.UpdateResultVO;

import jakarta.validation.Valid;

@Service
public class RepliesServiceImpl implements RepliesService {

	private static final Logger logger = LoggerFactory.getLogger(RepliesServiceImpl.class);

	@Autowired
	private RepliesDao repliesDao;

	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	private FilesDao filesDao;

	@Transactional
	@Override
	public UpdateResultVO updateReply(@Valid UpdateVO updateVO) {
		RepliesVO res = this.repliesDao.selectReplyByReplyId(updateVO.getReplyId());
		if (ObjectUtils.isNotNull(res)) {

			if (!SessionUtils.isMineResource(res.getEmail())) {
				throw new HelloSpringApiException("권한이 부족", HttpStatus.BAD_REQUEST.value(), "자신의 댓글이 아닙니다.");
			}
		}
		
		updateVO.setFileGroupId(res.getFileGroupId());

		// 선택한 파일 삭제
		if (updateVO.getDelFileNum() != null && updateVO.getDelFileNum().size() > 0) {
			SearchFileGroupVO searchFileGroupVO = new SearchFileGroupVO();
			searchFileGroupVO.setDeleteFileNum(updateVO.getDelFileNum());
			searchFileGroupVO.setFileGroupId(updateVO.getFileGroupId());
			// 선택한 파일들의 정보를 조회 --> 파일 경로 --> 실제 파일을 제거
			List<String> deleteTargets = this.filesDao
											.selectFilesPathbuFilesGroupIdAndFileNums(searchFileGroupVO);
			for (String target : deleteTargets) {
				new File(target).delete();
			}
			// 선택한 파일들을 Files 테이블에서 제거
			int deleteCount = this.filesDao
											.deleteFilesByFileGruopIdAndFileNums(searchFileGroupVO);
		}
		
		// 첨부 파일 업로드
		List<MultipartFile> file = updateVO.getNewAttachFile();
		
		String fileGroupId = updateVO.getFileGroupId();
		if (fileGroupId == null || fileGroupId.length() == 0) {
			// 첨부파일이 없다면 내부적으로 PK를 만들어서 주입해라
			fileGroupId = this.multipartFileHandler.upload(file);
		} else {
			// 첨부파일이 있다면 아이디를 따로 주는 형태로 호출
			this.multipartFileHandler.upload(file, fileGroupId);
		}
		
		int updateCount = this.repliesDao.updateReplyByReplyId(updateVO);
		UpdateResultVO result = new UpdateResultVO();
		result.setReplyId(updateVO.getReplyId());
		result.setUpdate(updateCount == 1);
		
		return result;
	}

	@Transactional
	@Override
	public DeleteVO deleteReplyByReplyId(String replyId) {

		RepliesVO res = this.repliesDao.selectReplyByReplyId(replyId);
		if (ObjectUtils.isNotNull(res)) {

			if (!SessionUtils.isMineResource(res.getEmail())) {
				throw new HelloSpringApiException("권한이 부족", HttpStatus.BAD_REQUEST.value(), "자신의 댓글이 아닙니다.");
			}
		}

		int resultCount = this.repliesDao.deleteReply(replyId);
		if (resultCount == 1) {
			DeleteVO asd = new DeleteVO();
			asd.setId(replyId);
			return asd;
		}

		return null;
	}

	@Transactional
	@Override
	public RepliesVO createNewReply(CreateVO createVO) {

		String fileGroupId = this.multipartFileHandler.upload(createVO.getAttachFile());
		createVO.setFileGroupId(fileGroupId);

		int insertCount = this.repliesDao.insertNewReply(createVO);
		if (insertCount == 1) {
			RepliesVO insertResult = this.repliesDao.selectReplyByReplyId(createVO.getId());
			return insertResult;
		}
		return null;
	}

	@Override
	public SearchResultVO findRepliesByArticleId(String articleId) {

		SearchResultVO searchResultVO = new SearchResultVO();

		int count = this.repliesDao.selectRepliesCountByArticleId(articleId);
		searchResultVO.setCount(count);

		if (count > 0) {
			List<RepliesVO> searchList = this.repliesDao.selectRepliesByArticleId(articleId);
			searchResultVO.setResult(searchList);
		}

		return searchResultVO;
	}

	@Transactional
	@Override
	public RecommendCntVO updateRecommendCntByArticleId(String articleId) {

		RepliesVO res = this.repliesDao.selectReplyByReplyId(articleId);
		if (ObjectUtils.isNotNull(res)) {

			if (SessionUtils.isMineResource(res.getEmail())) {
				throw new HelloSpringApiException("권한이 부족", HttpStatus.BAD_REQUEST.value(), "자신의 닷글은 추천할 수 없습니다.");
			}
		}

		RecommendCntVO s = new RecommendCntVO();
		s.setId(articleId);
		int result = this.repliesDao.updateRecommentCnt(articleId);
		if (result == 1) {
			RepliesVO re = this.repliesDao.selectReplyByReplyId(articleId);
			s.setRecommendCnt(re.getRecommendCnt());
		}

		return s;
	}

}
