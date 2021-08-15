package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class EmailDaoOrclImpl implements EmailDao {
    //커넥션 메서드
	private Connection getConnection() throws SQLException{
		Connection conn=null;
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl="jdbc:oracle:this:@localhost:1521:xe";
			//드라이버 매니저->접속 얻어오기
			conn=DriverManager.getConnection(dburl, "C##KIMY", "1234");
		}catch
		(ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public List<EmailVo> getList(){
		//TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insert(EmailVo vo) {
		//TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int delete(Long no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
