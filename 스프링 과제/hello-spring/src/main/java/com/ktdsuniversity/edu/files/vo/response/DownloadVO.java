package com.ktdsuniversity.edu.files.vo.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public class DownloadVO {
	
	
	// 사용자가 다운받으려는 파일의 크기를 알아야함
	// 사용자가 어떤 형태의 파일을 받고 있는가
	// 사용자가 다운로드하고하자는 파일의 경로가 필요
	private String displayName;
	private String extendName;
	private long fileLength;
	private String filePath;
	
	// 사용자에게 전달해줄 파일 객체 filePath를 가지고 파일을 만들어줌
	private File file;
	
	// 브라우저에게 전달하기 위한 파일 객체
	private Resource resource;

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		// Java기반 애플리케이션에서 파일을 다운로드할 때
		// 영어를 제외한 글자들이 사라지는 현상이 발생
		// ==> 사라지지 않도록 다국어 지원을 하게 만듦
		this.displayName = displayName;
		try {
			this.displayName = URLEncoder.encode(displayName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
	}

	public String getExtendName() {
		return this.extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public long getFileLength() {
		return this.fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		// this.file 생성 다운로드 하려는 파일이 생성됨
		this.file = new File(this.filePath);
		// this.resource 생성. FileInputStream 인스턴스 메소드 호출
		try {
			FileInputStream fileStream = new FileInputStream(this.file);
			// InputStreamResource() => 다운로드를 위한 코드
			this.resource = new InputStreamResource(fileStream);
		} catch (FileNotFoundException fnfe) {
			// TODO 전용 예외 발생시켜 던지기
		}
	}

	public File getFile() {
		return this.file;
	}

	public Resource getResource() {
		return this.resource;
	}

	
	
}
