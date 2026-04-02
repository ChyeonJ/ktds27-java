package com.ktdsuniversity.edu.movie.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FileDao;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;
import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.InsertMovieVO;
import com.ktdsuniversity.edu.movie.vo.request.UpdateMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;

@Service
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private FileDao	 fileDao;
	
	@Override
	public SearchMovieVO findMovieList() {
		
		SearchMovieVO result = new SearchMovieVO();
		
		int count = this.movieDao.selectMovieCount();
		result.setCount(count);
		
		List<MovieVO> list =  this.movieDao.selectMovieList();
		result.setResult(list);
		
		return result;
	}
	
	@Override
	public boolean createMovieData(InsertMovieVO insertMovieVO) {
		int resultCount = this.movieDao.insertMovieData(insertMovieVO);
		
		//1장만 들어가니까 List화 할 필요가 있을까?
		MultipartFile file = insertMovieVO.getReadyFile();
		File exFile = new File("C:\\uploadFiles",file.getOriginalFilename());
		if (!exFile.getParentFile().exists()){
			exFile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(exFile);
			UploadVO uploadVO = new UploadVO();
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			uploadVO.setFileGroupId(insertMovieVO.getId());
			uploadVO.setObfuscateName(fileName);
			uploadVO.setDisplayName(fileName);
			uploadVO.setExtendName(ext);
			uploadVO.setFileLength(exFile.length());
			uploadVO.setFilePath(exFile.getAbsolutePath());
			this.fileDao.insertAttachFile(uploadVO);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		
		return resultCount == 1;
	}
	
	@Override
	public MovieVO findOneMovie(String movieId) {
		MovieVO result = this.movieDao.selectOneMovieById(movieId);
		return result;
	}
	
	@Override
	public boolean updateMovieById(UpdateMovieVO updateMovieVO) {
		int resultCount = this.movieDao.updateMovie(updateMovieVO);
		return resultCount == 1;
	}
	
	@Override
	public boolean deleteMovie(String id) {
		int resultCount = this.movieDao.deleteMovie(id);
		return resultCount == 1;
	}

}
