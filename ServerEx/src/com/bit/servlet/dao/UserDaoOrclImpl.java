package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoOrclImpl implements UserDao {
	private Connection getConnection() throws SQLException{
		Connection conn=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(dburl, "C##KIMY", "1234");
		}catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		
		return conn;
	}
	
	@Override
	public int insert(UserVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		int insertedCount=0;
		
		try {
			conn=getConnection();
			String sql="INSERT INTO users"+
			           "(no, name, password, email, gender) "+
					   "VALUES(seq_users_pk.NEXTVAL, ?, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());
			
			insertedCount=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		   }
		return insertedCount;
	}
	@Override
	public UserVo getUserByEmailAndPassword(String email, String password) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserVo vo=null;
		
		try {
			conn = getConnection();
			String sql="SELECT no, name, password, email, gender, created_at FROM users "+
			            "WHERE email=? AND password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			//퀄리 수행
			rs=pstmt.executeQuery();
			
			//만약에 레코드가 있다면->사용자가 있다는 뜻
			if(rs.next()) {
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				String pwd=rs.getString(3);
				String eml=rs.getString(4);
				String gender=rs.getString(5);
				Date createdAt=rs.getDate(6);
				
				//사용자 객체 생성
				vo=new UserVo(no, name, pwd, eml, gender, createdAt);				
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		return vo;
	}

}
