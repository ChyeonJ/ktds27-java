package com.ktdsuniversity.edu.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FIleWriteExam {
	
	public static void writerFileDescriptionUseNIO(File target,List<String> description) {
		
		try {
			Files.write(target.toPath(), description);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void writerFileDescriptionUseIO(File target,List<String> description) {
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
	try {
		//파일을 쓰기위한 Pipe 생성
		fileWriter = new FileWriter(target);
		bufferedWriter = new BufferedWriter(fileWriter);
		
		//파일쓰기
		for(String line : description) {
			bufferedWriter.write(line + "\n");
		}
	}
	catch (IOException ioe) {
		ioe.printStackTrace();
	}
	finally {
		if (bufferedWriter != null) {
			try {
				bufferedWriter.close();
			} catch (IOException ioe) {}
		}
		
		if (fileWriter != null) {
			try {
				fileWriter.close();
			} catch (IOException ioe) {}
		}
	}
	}
	
	public static void main(String[] args) {
		
		Properties props = System.getProperties();
		String homePath = props.get("user.home").toString();
		
		File file = new File(homePath + File.separator + "Java Exam", "test.txt");
		
		List<String> description = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			description.add("난수 입니다. ==>" + Math.random() * 100_000);
		}
		
		//writerFileDescriptionUseIO(file, description);
		writerFileDescriptionUseNIO(file, description);
		
	}

}
