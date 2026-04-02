package com.ktdsuniversity.edu.files.service;

import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;

public interface FileService {
	
	DownloadVO findAttachFile(SearchFileVO searchFileVO);
	
}
