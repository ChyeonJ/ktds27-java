package com.ktdsuniversity.edu.files.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;

@Mapper
public interface FileDao {

	int insertAttachFile(UploadVO uploadVO);

	DownloadVO selectFilesByFileGroupIdAndFileNum(SearchFileVO searchFileVO);

}
