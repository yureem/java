package com.yedam.app.emp;

import java.sql.Date;

public class EmpVO { // 캡슐화. 모두 프라이빗으로. *필드*
	private int empNo; // 카멜법으로작성
	private String birthDate; // sql로 가져옴.
	private String firstName;
	private String lastName;
	private String gender;
	private String hireDate;			//employees의 모든 필드 변수화함. 한 사람의 모든 정보임
	public int getEmpNo() {
		return empNo;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	@Override
	public String toString() {
		return "EmpVO [empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}
	
	
	
}
