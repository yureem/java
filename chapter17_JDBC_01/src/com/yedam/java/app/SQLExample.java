package com.yedam.java.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLExample {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;			// try catch 안에 있어서 변수인식x , 그래서 적어줌.
		
		try {
			// 1. JDBC Driver 로딩하기
			Class.forName("org.sqlite.JDBC");
			
			// 2. DBMS(DB+컴파일할수있는프로그램. 걍 DB라생각하자) 서버와 접속하기
			String url = "jdbc:sqlite:/D:/dev/database/TestDataBase.db"; // 복붙으로 가져오기..
			conn = DriverManager.getConnection(url); // 아래 구문은 반복되지만 커넥션은 하나만 있어도 ok
			
			/***************** INSERT ***************************/
			
			// 3. Statement or PreparedStatement 객체 생성하기
			String insert = "INSERT INTO students(student_id, student_name) " // sql문 문자열로 분리시킴
							+ "VALUES (?,?)"; // 한 줄로 붙여도 됨. 
											// ? 변수 : 값을 넣고자 하는 부분. 물음표 개수에 따라 set 개수 사용해야함
			ps = conn.prepareStatement(insert);
			ps.setInt(1, 110); // student_id값 
			ps.setString(2, "윤달하"); // student_name / 첫번째 ? : 데이터 몇번째로 들어갈지 적어줌.
			//statement와의 차이점 다시 확인
			
			// 4. SQL실행하기
			int result = ps.executeUpdate(); // sql문이 실행되면서 어느 행이 달라졌는지 int 타입으로 반환됨.
			
			// 5. 결과출력하기
			System.out.println("insert 결과 : " + result);
			
			/***************** SELECT ***************************/ //statement로 많이 씀
			
			// 3. Statement or PreparedStatement 객체 생성하기
			stmt = conn.createStatement();
			
			// 4. SQL실행하기
			String select = "SELECT student_id, student_name, student_dept FROM Students";
			rs = stmt.executeQuery(select);
			//stmt는 완성된 sql문으로 줘야 함.
			
			// 5. 결과출력하기 - rs이 정확히 몇개의 값 들고있는지 모름. while문사용한다.
			while(rs.next()) { // 커서 움직일 수 있으면 값 가져온다(전부 다)
				int id = rs.getInt("student_id"); // 가지고 오려는 필드값 넣기
				String name = rs.getString("student_name");
				String dept = rs.getString("student_dept");
				
				System.out.printf("학번 : %d, 이름 : %s, 학년 : %s \n", id, name, dept);
			}
			
			
			/***************** UPDATE ***************************/
			
			// 3. Statement or PreparedStatement 객체 생성하기
			String update = "UPDATE students SET student_dept = ? WHERE student_id = ?";
			ps = conn.prepareStatement(update);
			ps.setInt(2, 110); // 두번째 물음표를 채운다. 110으로
			ps.setString(1, "3학년");
			
			// 4. SQL실행하기
			result = ps.executeUpdate();
			
			// 5. 결과출력하기
			System.out.println("update 결과 : " + result);
			
			/***************** DELETE ***************************/
			
			// 3. Statement or PreparedStatement 객체 생성하기
			stmt = conn.createStatement();
			
			// 4. SQL실행하기 - 실행할 수 있는 sql구문으로 적어줘야함
			String delete = "DELETE FROM students WHERE student_id = " + 110;
			//stmt는 ? 안 먹음. 저렇게 적어줘야 함.
			//DELETE FROM students WHERE student_id = 110; 이랑 같음. 
			result = stmt.executeUpdate(delete);
			
			// 5. 결과출력하기
			System.out.println("delete 결과 : " + result);
			
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC Driver Loding Fail");
		}catch(SQLException e) {
			System.out.println("SQL관련 예외 : " + e.getMessage()); // 오류 메시지 간단히 출력
		}catch(Exception e){ // 모든 예외의 부모격(기타 예외)
			e.printStackTrace(); // 오류 메시지 풀 출력
		}finally { // 자원 해제
			try {
			// 6. 자원해제하기(비정상적으로 종료되었더라도 정리하고 끝냄)
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			}catch(SQLException e) {
				System.out.println("정상적으로 자원이 해제되지 않았습니다.");
			}
		}

	}

}
