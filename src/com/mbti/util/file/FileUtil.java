package com.mbti.util.file;

import java.io.File;

public class FileUtil {

	//파일이 존재하는지 확인하는 메소드
	public static boolean exist(File file) throws Exception {
		return file.exists();
	}
	
	//파일이 존재하는지 확인하는 메소드
	public static boolean exist(String image) throws Exception {
		return toFile(image).exists();
	}
	
	// 문자열을 파일 객체로 만들어 주는 메소드
	public static File toFile(String image) throws Exception {
		return new File(image);
	}
	
	//파일 지우기
	public static boolean delete(File file) throws Exception {
		return file.delete();
	}

	// 파일의 정보를 문자열로 넣어주면 지워주는 메소드
	// 파일은 realPath 정보의 파일명을 넘겨야 한다.
	public static boolean remove(String image) throws Exception{
		// 1. 문자열을 파일 객체로 만든다. 2. 있는지 확인한다. 3. 삭제한다. 4.결과 리턴
		File file = toFile(image);
		// 파일이 존재하지 않는 경우 예외 발생
		if(!exist(file)) throw new Exception("삭제하려는 파일이 존재하지 않습니다.");
		// 파일이 존재하는 경우 처리
		if(!delete(file)) throw new Exception("이전 파일이 삭제되지 않았습니다.");
		System.out.println("파일이 삭제되었습니다.");
		return true;
	}
}

	