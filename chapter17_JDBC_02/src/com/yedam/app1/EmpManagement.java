package com.yedam.app1;

import java.util.List;
import java.util.Scanner;

import com.yedam.app.emp.EmpDAO;
import com.yedam.app.emp.EmpDAOImpl;
import com.yedam.app.emp.EmpVO;

public class EmpManagement { // 프로그램 역할
	Scanner sc = new Scanner(System.in);
	EmpDAO empDAO = EmpDAOImpl.getInstance();

	public EmpManagement() {
		while (true) {
			// 메뉴출력
			menuPrint(); // 함수로 만들기
			// 메뉴선택
			int menuNo = menuSelect();

			// 각 메뉴의 기능을 실행시킴
			if (menuNo == 1) { // 전체조회
				selectAll();
			} else if (menuNo == 2) { // 단건조회
				selectOne();
			} else if (menuNo == 3) { // 사원등록
				insertEmp();
			} else if (menuNo == 4) { // 사원수정
				updateEmp();
			} else if (menuNo == 5) { // 사원삭제
				deleteEmp();
			} else if (menuNo == 9) { // 종료
				end();
				break;
			} else { // 기타사항
				printErrorMessage();
			}
		}
	}

	private void printErrorMessage() {
		System.out.println("=======================");
		System.out.println(" 메뉴를 잘못 입력했습니다.");
		System.out.println(" 메뉴를 다시 한번 확인해주세요.");
		System.out.println("=======================");
	}

	private void end() {
		System.out.println("=======================");
		System.out.println("프로그램을 종료합니다.");
		System.out.println("=======================");
	}

	private void menuPrint() {
		System.out.println("========================================================");
		System.out.println(" 1. 전체사원 2. 사원조회 3. 사원등록 4. 사원수정 5. 사원삭제 9. 종료");
		System.out.println("========================================================");
	}

	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 구성되어 있습니다."); // if쓰지않고도 이렇게도 가능.
		}
		return menuNo;
	}

	private void selectAll() {
		List<EmpVO> list = empDAO.selectAll();
		if (list.isEmpty()) {
			System.out.println("정보가 존재하지 않습니다.");
			return; // return되면 for문 실행x
		}
		for (EmpVO empVO : list) {
			System.out.printf("%d: %s, %s \n", empVO.getEmpNo(), empVO.getFirstName(), empVO.getLastName());
		}
	}

	private void selectOne() {
		EmpVO findEmp = inputEmpInfo();
		EmpVO empVO = empDAO.selectOne(findEmp);
		if (empVO == null) {
			System.out.printf("%d 사원은 존재하지 않습니다. \n", findEmp.getEmpNo());
		} else {
			System.out.println("검색결과 > ");
			System.out.println(empVO);
		}
	}

	private void insertEmp() { // 값 받고 넘겨줌
		EmpVO empVO = inputEmpAll();
		empDAO.insert(empVO);
	}

	private void updateEmp() {
		EmpVO empVO = inputEmpInfo();
		empDAO.update(empVO);
	}

	private void deleteEmp() {
		int empNo = inputEmpNo();
		empDAO.delete(empNo);
	}

	private EmpVO inputEmpAll() { // 인서트메소드안에 있음
		EmpVO empVO = new EmpVO();
		System.out.println("사원번호 > ");
		empVO.setEmpNo(Integer.parseInt(sc.nextLine()));
		System.out.print("생년월일(yyyy-MM-dd) > ");
		empVO.setBirthDate(sc.nextLine());
		System.out.println("이름 > ");
		empVO.setFirstName(sc.nextLine());
		System.out.println("성 > ");
		empVO.setLastName(sc.nextLine());
		System.out.println("성별(M,F) > ");
		empVO.setGender(sc.nextLine());
		System.out.println("입사일(yyyy-MM-dd) > ");
		empVO.setHireDate(sc.nextLine());
		return empVO;
	}

	private EmpVO inputEmpInfo() { // 인서트메소드안에 있음
		EmpVO empVO = new EmpVO();
		System.out.println("사원번호 > ");
		empVO.setEmpNo(Integer.parseInt(sc.nextLine()));
		System.out.println("이름 > ");
		empVO.setFirstName(sc.nextLine());
		return empVO;
	}
	
	private int inputEmpNo() {
		int EmpNo = 0;
		try {
			EmpNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 구성되어 있습니다."); // if쓰지않고도 이렇게도 가능.
		}
		return EmpNo;
	}
	
	
	
	
}
