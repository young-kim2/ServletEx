package com.bit.servlet.dao;

import java.util.Date;

public class UserVo {
	//필드
	private Long no;
	private String name;
	private String password;
	private String email;
	private String gender;
	private Date createdAt;
	
	//생성자
	public UserVo() {

}
	public UserVo(String name, String password, String email, String gender) {
		this.name=name;
		this.password=password;
		this.email=email;
		this.gender=gender;
	}
	
	public UserVo(Long no, String name, String password, String email, String gender, Date createdAt) {
		this(name, password, email, gender);
		this.no=no;
		this.createdAt=createdAt;
	}
	// getters/setters
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	//toString
	
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", password=" + password + ", email=" + email + ", gender="
				+ gender + ", createdAt=" + createdAt + "]";
	}
	
}