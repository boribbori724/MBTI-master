/**
 * DB정보 및 DB연결을 위한 Class
 * 작성자 : 궁금해조
 * 작성일 : 2021.03.21
 * ver_1.0
 */
package com.mbti.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInfo {

	// 드라이버 확인 : 정보 입력
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static boolean driverCheck = false;
	
	// 1-1 DB에 대한 접속 정보 ㄷ
//	private static final String URL = "jdbc:oracle:thin:@192.168.0.125:1521:xe"; 
//	// 집에서 하실때 이거 주석 풀고 위에꺼 주석처리해서 사용해주세요.
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	
	private static final String ID = "team0";
	 
	private static final String PW = "team0";
	
	// static Block을 이용한 초기화
	static {
		
		System.out.println("DBInfo.static{} =================== ");
		
		try {
			
			Class.forName(DRIVER);
			
			driverCheck = true;
			
			System.out.println("DBInfo.static{} : 드라이버 확인");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static Connection getConnection() throws Exception {		// 드라이버 연결을 위한 method
		
		if(!driverCheck) {
			
			throw new SQLException("드라이버가 존재하지 않습니다.");
			
		}
		
		return DriverManager.getConnection(URL, ID, PW);
		
	}
	
	public static void close(Connection con, PreparedStatement pstmt) throws Exception {		// 연결된 Connection과 PreparedStatement의 연결 해제
		
		if(con != null) {
			
			con.close();
			
		}
		
		if(pstmt != null) {
			
			pstmt.close();
		}
		
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws Exception {	// 연결된 Connection과 PreparedStatement, ResultSet의 연결 해체
		
		close(con, pstmt);
		
		if(rs != null) {
			
			rs.close();
			
		}
		
	}
	
	
}
