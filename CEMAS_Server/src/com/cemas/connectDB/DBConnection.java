package com.cemas.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库连接
public class DBConnection {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/cemas?useUnicode=true&characterEncoding=UTF-8";
		String dbname = "root";
		String dbpwd = "";
		Connection conn = DriverManager.getConnection(url,dbname,dbpwd);
		return conn;
	}

	public static void close(ResultSet rs, PreparedStatement stat, Connection conn){
		try {
			if(rs != null) {
				rs.close();
			}
			if(stat != null) {
				stat.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps, Connection conn) {
		try {
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}