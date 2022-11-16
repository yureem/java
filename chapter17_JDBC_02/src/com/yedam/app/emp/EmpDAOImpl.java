package com.yedam.app.emp;

import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

public class EmpDAOImpl extends DAO implements EmpDAO { // 구현 클래스임
	
	//싱글톤 : ..뭔데이거
	private static EmpDAO instance = null;
	
	public static EmpDAO getInstance() {
		if(instance == null)
			instance = new EmpDAOImpl();
		return instance;
	}

	@Override
	public List<EmpVO> selectAll() { // 전체조회 : 
		List<EmpVO> list = new ArrayList<>(); // 반환될것 가져옴
		try {
			connect(); 
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employees"; // 모든 필드 다 가져올때 아스타사용하면됨
			rs = stmt.executeQuery(sql); // sql문 실행
			int count = 0;
			while(rs.next()) {
				EmpVO empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("emp_no"));
				empVO.setBirthDate(rs.getString("birth_date"));
				empVO.setFirstName(rs.getString("first_name"));
				empVO.setLastName(rs.getString("last_name"));
				empVO.setGender(rs.getString("gender"));
				empVO.setHireDate(rs.getString("hire_date"));
				list.add(empVO);			// 데이터 다 들고와서 리스트에 넣음
				
				if(++count >= 20) break; // 원래는 불필요함. 데이터 너무많아서 20개만들고옴 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list; // 꼭 반환하기
	}

	@Override
	public EmpVO selectOne(EmpVO empVO) { // 단건조회
		EmpVO findVO = null; // 객체생성과 리턴을 먼저 잡고 trycatch문 작성하기. 커넥트디스커넥트넣고시작
		try {
			connect();
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM employees WHERE emp_no = " + empVO.getEmpNo();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				findVO = new EmpVO();
				findVO.setEmpNo(rs.getInt("emp_no"));
				findVO.setBirthDate(rs.getString("birth_date"));
				findVO.setFirstName(rs.getString("first_name"));
				findVO.setLastName(rs.getString("last_name"));
				findVO.setGender(rs.getString("gender"));
				findVO.setHireDate(rs.getString("hire_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return findVO;
	}

	@Override
	public void insert(EmpVO empVO) { // 따로 리턴값 없음 -> 객체 안 불러와도됨
		try {
			connect();
			String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empVO.getEmpNo());
			pstmt.setString(2, empVO.getBirthDate());
			pstmt.setString(3, empVO.getFirstName());
			pstmt.setString(4, empVO.getLastName());
			pstmt.setString(5, empVO.getGender());
			pstmt.setString(6, empVO.getHireDate());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			}else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}

	@Override
	public void update(EmpVO empVO) {
		try {
			connect();
			String sql = "UPDATE employees SET first_name = ? WHERE emp_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empVO.getFirstName());
			pstmt.setInt(2, empVO.getEmpNo());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}

	@Override
	public void delete(int empNo) {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "DELETE FROM employees WHERE emp_no = " + empNo;
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			}else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}

}
