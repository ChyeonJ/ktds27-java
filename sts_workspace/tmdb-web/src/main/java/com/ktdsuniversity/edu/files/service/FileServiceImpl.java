package com.ktdsuniversity.edu.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.files.dao.FileDao;
import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileDao fileDao;
	
	@Override
	public DownloadVO findAttachFile(SearchFileVO searchFileVO) {
		DownloadVO result = this.fileDao.selectFilesByFileGroupIdAndFileNum(searchFileVO);
		return result;
	}

}
