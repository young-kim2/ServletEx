package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDaoOrclImpl implements EmailDao {
    //커넥션 메서드
	private Connection getConnection() throws SQLException{
		Connection conn=null;
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl="jdbc:oracle:thin:@localhost:1521:xe";
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
		List<EmailVo> list=new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			String sql="SELECT no, last_name, first_name, email, create_at "+
			           "FROM emaillist ORDER BY create_at DESC";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no=rs.getLong(1);
				String lastName=rs.getString(2);
				String firstName=rs.getString(3);
				String email=rs.getString(4);
				Date createAt=rs.getDate(5);
				//java.util.Date
				
				EmailVo vo=new EmailVo(no, lastName, firstName, email, createAt);
				//리스트에 추가
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		return list;
	}
	
	@Override
	public int insert(EmailVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		int insertedCount=0;
		
		try {
			conn=getConnection();
			String sql="INSERT INTO emaillist"+
			           "(no, last_name, first_name, email) "+
					   "VALUES(seq_emaillist_pk.NEXTVAL, ?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
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
	public int delete(Long no) {
	Connection conn=null;
	
	PreparedStatement pstmt=null;
	
	int deletedCount=0;
	
	try {
		conn=getConnection();
		String sql="DELETE FROM emaillist "+"WHERE no=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setLong(1, no);
		
		//쿼리 수행
		deletedCount=pstmt.executeUpdate();
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
	return deletedCount;
	}

}
