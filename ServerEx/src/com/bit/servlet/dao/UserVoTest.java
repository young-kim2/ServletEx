package com.bit.servlet.dao;

public class UserVoTest {

	public static void main(String[] args) {
		UserDao dao=new UserDaoOrclImpl();
		UserVo vo=dao.getUserByEmailAndPassword("admin@example.com","1234");
		
		System.out.println(vo);

	}

}
