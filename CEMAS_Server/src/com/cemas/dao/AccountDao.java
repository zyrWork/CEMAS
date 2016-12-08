package com.cemas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cemas.bean.Account;
import com.cemas.connectDB.DBConnection;

public class AccountDao {
	public static void insertAccount(Account bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Account(aID,aType,uID,aDefaultType) values(?,?,?,?)");
		ps.setString(1, bean.getaID() );
		ps.setString(2, bean.getaType());
		ps.setString(3, bean.getuID());
		ps.setString(4, bean.getaDefaultType() );

		ps.executeUpdate();
		System.out.println("插入账户成功!");
		DBConnection.close(ps, conn);
	}


	public static void deleteAccountByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Account where aID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除账户成功："+ID);
		DBConnection.close(ps, conn);
	}


	public static void updateAccount(Account bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Account set uID=?,aType=?,aDefaultType=? where aID=?");
	
		ps.setString(1, bean.getuID());	
		ps.setString(2, bean.getaType());	
		ps.setString(3, bean.getaDefaultType() );
		ps.setString(4, bean.getaID() );
		
		ps.executeUpdate();
		System.out.println("更新账户成功!"+ bean.getaID());
		DBConnection.close(ps, conn);
	}

	public static Account searchAccountByID(String ID) throws Exception {
		Account bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Account where aID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Account();
			bean.setaID(rs.getString("aID"));
			bean.setaType(rs.getString("aType"));
			bean.setuID(rs.getString("uID"));
			bean.setaDefaultType(rs.getString("aDefaultType"));
		}
		System.out.println("查找帐户成功:"+ bean.getaID());
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	public static ArrayList<Account> searchAllAccount() throws Exception {
		ArrayList<Account> list = new ArrayList<Account>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Account");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Account bean = new Account();
			bean.setaID(rs.getString("aID"));
			bean.setaType(rs.getString("aType"));
			bean.setuID(rs.getString("uID"));
			bean.setaDefaultType(rs.getString("aDefaultType"));
		
			list.add(bean);
		}
		System.out.println("查找所有帐户成功！");
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
	
	
//	public static void main(String [] args) throws Exception
//	{
//		Account bean=new Account();
//		
//		bean.setaID("aID");
//		bean.setaType("aType");
//		bean.setuID("uID");
//		bean.setaDefaultType("aDefaultType");
//		
//		AccountDao.deleteAccountByID(bean.getaID());
//		bean.setaDefaultType("zyr");
//		AccountDao.insertAccount(bean);
//		
//		AccountDao.searchAllAccount();
//		AccountDao.searchAccountByID(bean.getaID());
//	}
}
