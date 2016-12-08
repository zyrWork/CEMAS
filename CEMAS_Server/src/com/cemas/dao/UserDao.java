package com.cemas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cemas.bean.User;
import com.cemas.connectDB.DBConnection;

public class UserDao {

	public static void insertUser(User u) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into User(uID,uPassword,uName,uAge,uGender,uSchoolNumber,uEmail,uIdentify,uTele,uScore ) values(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, u.getuID() );
		ps.setString(2, u.getuPassword() );
		ps.setString(3, u.getuName());
		ps.setString(4, u.getuAge() );
		ps.setString(5, u.getuGender());
		ps.setString(6, u.getuSchoolNumber());
		ps.setString(7, u.getuEmail() );
		ps.setString(8, u.getuIdentify() );
		ps.setString(9, u.getuTele());
		ps.setString(10, u.getuScore());
		ps.executeUpdate();
		System.out.println("插入用户成功!");
		DBConnection.close(ps, conn);
	}


	public static void deleteUserByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from User where uID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除用户成功："+ID);
		DBConnection.close(ps, conn);
	}


	public static void updateUser(User u) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update User set uPassword=?,uName=?,uAge=?,uGender=?,uSchoolNumber=?,uEmail=?,uIdentify=?,uTele=?,uScore=? where uID=?");
	
		ps.setString(1, u.getuPassword() );
		ps.setString(2, u.getuName());
		ps.setString(3, u.getuAge() );
		ps.setString(4, u.getuGender());
		ps.setString(5, u.getuSchoolNumber());
		ps.setString(6, u.getuEmail() );
		ps.setString(7, u.getuIdentify() );
		ps.setString(8, u.getuTele());
		ps.setString(9, u.getuScore());
		ps.setString(10, u.getuID() );
		ps.executeUpdate();
		System.out.println("更新用户成功!"+ u.getuID());
		DBConnection.close(ps, conn);
	}

	public static User searchUserByID(String ID) throws Exception {
		User bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from User where uID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new User();
			bean.setuAge(rs.getString("uAge"));
			bean.setuEmail(rs.getString("uEmail"));
			bean.setuGender(rs.getString("uGender"));
			bean.setuID(rs.getString("uID"));
			bean.setuIdentify(rs.getString("uIdentify"));
			
			bean.setuName(rs.getString("uName"));
			bean.setuPassword(rs.getString("uPassword"));
			bean.setuSchoolNumber(rs.getString("uSchoolNumber"));
			bean.setuScore(rs.getString("uScore"));
			bean.setuTele(rs.getString("uTele"));
		}
		System.out.println("查找用户成功:"+ bean.getuID());
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	public static ArrayList<User> searchAllUser() throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from User");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			User bean = new User();
			bean.setuAge(rs.getString("uAge"));
			bean.setuEmail(rs.getString("uEmail"));
			bean.setuGender(rs.getString("uGender"));
			bean.setuID(rs.getString("uID"));
			bean.setuIdentify(rs.getString("uIdentify"));
			
			bean.setuName(rs.getString("uName"));
			bean.setuPassword(rs.getString("uPassword"));
			bean.setuSchoolNumber(rs.getString("uSchoolNumber"));
			bean.setuScore(rs.getString("uScore"));
			bean.setuTele(rs.getString("uTele"));
			
			list.add(bean);
		}
		System.out.println("查找所有用户成功！");
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
//	public static void main(String [] args) throws Exception
//	{
//		User bean=new User();
//		
//		bean.setuAge("uAge");
//		bean.setuEmail("uEmail");
//		bean.setuGender("uGender");
//		bean.setuID("uID");
//		bean.setuIdentify("uIdentify");
//		
//		bean.setuName("uName");
//		bean.setuPassword("uPassword");
//		bean.setuSchoolNumber("uSchoolNumber");
//		bean.setuScore("uScore");
//		bean.setuTele("uTele");
//		
//		UserDao.deleteUserByID(bean.getuID());
//		//UserDao.insertUser(bean);
//		bean.setuAge("zyr");
//		UserDao.insertUser(bean);
//		
//		UserDao.searchAllUser();
//		UserDao.searchUserByID(bean.getuID());
//	}

}
