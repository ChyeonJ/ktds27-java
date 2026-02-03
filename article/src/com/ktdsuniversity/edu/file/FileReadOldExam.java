package com.ktdsuniversity.edu.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

public class FileReadOldExam {
	
	/**
	 * NIO
	 * Java version >= 1.8
	 */
	public static void readAndPrintFileDescriptionUseNIO(String parentPath, String file) {	//단점 메로리를 엄청 많이 잡아먹는다
		// 1. 읽으려는 파일을 특정.
		File target = new File(parentPath, file);
		
		// 2. 파일의 내용을 읽는다.
		// 3. 파일의 내용을 List에 할당한다.
		try {
			Files.lines(target.toPath()); // 이렇게하면 Chunking이 가능하다 하지만 함수를 배우지 않아서 지금은 적절하지 않다
			
			List<String> lines = Files.readAllLines(target.toPath());
			// 4. 파일의 내용을 출력한다.
			for (String line : lines) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * IO
	 * Java version < 1.8 
	 * @param parentPath
	 * @param file
	 */
	public static void readAndPrintFileDescriptionUseIO(String parentPath, String file) {		//위험한 코드
		
		// 1. 일으려는 파일을 특정. 
		File target = new File(parentPath, file);
		
		// 2. 파일이 존재하는지 확인.
		// 3. 읽으려는 대상이 진짜 파일이 맞는지 확인
		if(target.exists() && target.isFile()) { //파일이 아니거나, 파일이 아니면 
			// 4. 파일의 내용을 읽기 시작
			// 4-1. 파일의 바이트를 Chunking(자르다)해서 가져온다.
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			try {
				fileReader = new FileReader(target);	//------------------------------------------------------------------> Memory를 많이 사용한다. (JMC 설명)
				// 4-2. 파일의 내용을 String으로 변환한다.																		|		  Java는 FS(File System)한테 정보를 요청하고, 정보 전달을 하는 과정에서
				bufferedReader = new BufferedReader(fileReader);	// 파일의 내용을 한줄만 쫙 읽어옴----------------------------->		  Pipe가 생기는데 이것이 계속 쌓여서 GC가 생김
				
				
				String line = null; // 한줄씩 읽어올 내용들
				while(true) {
					
					line = bufferedReader.readLine(); //한줄을 읽어옴
					//EOF까지 갔느냐
					if (line ==null) {
						//buffer를 열린 순서 역순으로 pipe를 닫아줌 => 하지만 파이프는 끊기지 않는다. 그래서 파이프를 끊어준다.
						bufferedReader.close();	//pipe 해제
						fileReader.close();	//pipe 해제
						
						break;
					}
					else {
						// 4-3. 내용을 출력한다.
						System.out.println(line);
					}
					
				}
				
			} catch (IOException ioe) {	//내가 읽으려는 파일이 존재하지 않을떄 => 그치만 앞에서 null 체크를 했기 때문에 나올일 없음 그래도 해야함 FileNotFoundException) => BufferedReader에서 예외 에러 나서 둘다 부모 예외를 씀
				ioe.printStackTrace();
			} finally {	//그러면 pipe를 끊어라.
				if(bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {}
				}
				else if (fileReader != null) {
					try {
						fileReader.close();
					} catch (IOException e) {}
				}
				
			}
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
		//OS환경이 달라 파일의 저장소가 다르다면?
		// 운영체제 정보를 취득1 홈 디렉토리 경로
		Properties props = System.getProperties();
		String homePath = props.get("user.home").toString();
		System.out.println(homePath);
		
//		System.out.println(props.get("user.home"));
//		System.out.println(props);
//		
//		// 운영체제 정보를 취득	홈 디렉토리 경로
//		Map<String, String> env = System.getenv();
//		System.out.println(env.get("user.home"));

		
		// C:\Java Exam 폴더의 정보를 추출.
		// java.io.file
		File directory = new File(homePath + File.separator + "Java Exam");	//=> /, \\ 둘다 가능	=> File.separator는 운영체제 마다 다르게 슬래시 넣어
		// 1. 폴더의 이름 출력
		String directoryName = directory.getName();
		System.out.println(directoryName);
		
		// 2. 이 경로가 가리키는 것이 파일인지 폴더인지를 구분.
		boolean isFile = directory.isFile();
		System.out.println(isFile);
		//isFile의 값이 true이면 파일이다. False이면 파일이 아니다.
		
		
		boolean isDirectory = directory.isDirectory();
		System.out.println(isDirectory);
		//isDirectory가 true이면 폴더다. false이면 폴더가 아니다.
		
		
		// 3. 이 경로가 실제 존재하는 것인지.
		boolean exstits = directory.exists();
		System.out.println(exstits);
		//extits가 true이면 존재하는 폴더, false이면 존재하지 않는 폴더.
		
		// 4. 이 경로의 크기(폴더의 크기) 출력
		long bytes = directory.length();
		System.out.println(bytes + "byte");
		
		
		// C:\Java Exam\Java Exam.txt 파일의 정보를 추출 => 파일을 핸들링 한다 여기서부터 시작
		File textFile = new File(homePath + File.separator + "Java Exam", "Java Exam.txt");
		// 1. 파일의 이름을 출력
		String filename = textFile.getName();
		System.out.println(filename);
		
		// 2. 파일이 폴더인지 파일인지 구분해서 출력
		isDirectory = textFile.isDirectory();
		isFile = textFile.isFile();
		System.out.println(isDirectory);
		System.out.println(isFile);
		
		// 3. 이 파일이 실제 존재하는 것인 출력
		exstits = textFile.exists();
		System.out.println(exstits);
		
		// 4. 파일의 크기를 출력한다.
		bytes = textFile.length();
		System.out.println(bytes + "byte");
		
		// 5. 이 파일이 있는 부모의 경로를 출력한다.
		//	- 첫번째 방법
		String parentPath = textFile.getParent();	//많이씀 ❤❤
		System.out.println(parentPath);
		//	- 두번째 방법
		File parentFile = textFile.getParentFile();	//많이씀 ❤
		System.out.println(parentFile);
		
		// 6. 이 파일의 경로를 출력한다.
		
		String textFilePath = textFile.getAbsolutePath();
		System.out.println(textFilePath);
		
		while(true) {
			//readAndPrintFileDescriptionUseIO(homePath + File.separator + "Java Exam", "Java Exam.txt");
			readAndPrintFileDescriptionUseNIO(homePath + File.separator + "Java Exam", "Java Exam.txt");
		}
		
	}

}
