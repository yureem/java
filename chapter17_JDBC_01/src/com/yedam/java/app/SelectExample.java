package com.yedam.java.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {

	public static void main(String[] args) {
		try { // try catch 구문 : 예외 발생하면 이렇게 처리하겠다.
		// 1. JDBC Driver 로딩하기.
		Class.forName("org.sqlite.JDBC"); // 메모리로 끌어올려서 동작하게 함. import와 다름. 오타주의!!!
		
		// 2. DB 서버 접속하기
		String url = "jdbc:sqlite:/D:/dev/database/TestDataBase.db"; // 절대경로로 불러오기.
		Connection conn = DriverManager.getConnection(url); // 연결통로 생김
		
		// 3. Statement or PreparedStatement 객체 생성하기 // 우편배달부 느낌
		Statement stmt = conn.createStatement(); // sql로 import. 우편배달부 생김.
		
		// 4. SQL 실행
		ResultSet rs = stmt.executeQuery("SELECT student_id, student_name, student_dept FROM Students"); // sql에서 요청해서 둘고옴. 세
		
		// 5. 결과값 출력
		while(rs.next()) { // rs의 모든 값을 들고 옴(next는 커서가 처음부터 마지막까지 쭉 이동하게)
			int sId = rs.getInt("student_id"); //rs에서 값 들고 오는데 그 값이 int.
			String sName = rs.getString("student_name");
			String sDept = rs.getString("student_dept");
			System.out.println("학번 : " + sId );
			System.out.println("이름 : " + sName);
			System.out.println("학년 : " + sDept);
		}
		}catch(ClassNotFoundException | SQLException e) { // 예외처리
			e.printStackTrace();							
		}finally {
		// 6. 자원해제하기 // 사용 끝나면 해제해줘야함. 순서 매우 중요
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
	}

}
