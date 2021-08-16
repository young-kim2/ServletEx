package com.bit.servlet.dao;

import java.util.List;

public class EmailDaoTest {

	public static void main(String[] args) {
		EmailDao dao=new EmailDaoOrclImpl();
		List<EmailVo> list=dao.getList();

	}

}
