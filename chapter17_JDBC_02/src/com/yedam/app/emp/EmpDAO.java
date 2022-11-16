package com.yedam.app.emp;

import java.util.List;

public interface EmpDAO { // 메소드 시그니처 정의
	//전체조회 : 리턴값이 복수 -> 리스트로!
	List<EmpVO> selectAll();
	
	//단건조회 : 뭘 조회할지 몰라서 객체로 넣어줌
	EmpVO selectOne(EmpVO empVO);
	
	//등록
	void insert(EmpVO empVO);
	
	//수정
	void update(EmpVO empVO);
	
	//삭제 : 삭제는 객체보다 직접 삭제가 낫다
	void delete(int empNo);
}
