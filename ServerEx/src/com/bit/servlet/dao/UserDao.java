package com.bit.servlet.dao;

public interface UserDao {
	public int insert(UserVo vo);
	//가입 처리
	
	public UserVo getUserByEmailAndPassword(String email, String password);

}
