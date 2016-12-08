package com.cemas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cemas.bean.Admin;
import com.cemas.connectDB.DBConnection;

public class AdminDao {

	public static void insertAdmin(Admin bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Admin(aID,aPassword,aName) values(?,?,?)");
		ps.setString(1, bean.getaID() );
		ps.setString(2, bean.getaPassword());
		ps.setString(3, bean.getaName());
		ps.executeUpdate();
		System.out.println("插入管理员成功!");
		DBConnection.close(ps, conn);
	}


	public static void deleteAdminByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Admin where aID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除管理员成功："+ID);
		DBConnection.close(ps, conn);
	}


	public static void updateAdmin(Admin bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Admin set aPassword=?,aName=? where aID=?");
	
		ps.setString(1, bean.getaPassword());	
		ps.setString(2, bean.getaName());	
		ps.setString(3, bean.getaID());
		
		ps.executeUpdate();
		System.out.println("更新管理员成功!"+ bean.getaID());
		DBConnection.close(ps, conn);
	}

	public static Admin searchAdminByID(String ID) throws Exception {
		Admin bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Admin where aID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Admin();
			bean.setaID(rs.getString("aID"));
			bean.setaPassword(rs.getString("aPassword"));
			bean.setaName(rs.getString("aName"));
		}
		System.out.println("查找管理员成功:"+ bean.getaID());
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	public static ArrayList<Admin> searchAllAdmin() throws Exception {
		ArrayList<Admin> list = new ArrayList<Admin>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Admin");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Admin bean = new Admin();
			bean.setaID(rs.getString("aID"));
			bean.setaPassword(rs.getString("aPassword"));
			bean.setaName(rs.getString("aName"));
		
			list.add(bean);
		}
		System.out.println("查找所有管理员成功！");
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
	
	public static void main(String [] args) throws Exception
	{
		Admin bean=new Admin();
		
		bean.setaID("aID");
		bean.setaPassword("aPassword");
		bean.setaName("aName");
		
		AdminDao.insertAdmin(bean);
		
		AdminDao.deleteAdminByID(bean.getaID());
		
		bean.setaName("zyr");
		
		AdminDao.insertAdmin(bean);
		
		AdminDao.searchAdminByID(bean.getaID());
		AdminDao.searchAllAdmin();
	
	}
}
