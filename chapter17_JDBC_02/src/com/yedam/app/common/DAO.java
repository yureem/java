package com.yedam.app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO { // data access object : 데이터베이스에 접근(연결)할 때 사용하는 객체
	//DataBase 연결정보 - 외부에서 접근 못하게 프라이빗
	private String jdbcDriver = "org.sqlite.JDBC";
	private String jdbcUrl = "jdbc:sqlite:/D:/dev/database/YedamDataBase.db";
	
	//각 메소드에서 공통적으로 사용하는 변수 -> 필드이름으로! - 상속이용할거라서 프로텍티드
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	//연결
	// 1. JDBC DRIVER LOADING
	// 2. CONNECTION
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl);
			
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC DRIVER LOADING FAIL");
		}catch(SQLException e) {
			System.out.println("DATABASE CONNECTION FAIL");
		}
	}
	
	//연결해제
	// 6. 자원해제
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("정상적으로 자원이 해제되지 않았습니다.");
		}
	}
	

}
