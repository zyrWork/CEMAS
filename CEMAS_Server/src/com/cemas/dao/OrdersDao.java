package com.cemas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cemas.bean.Orders;
import com.cemas.connectDB.DBConnection;

public class OrdersDao {

	public static void insertOrders(Orders bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Orders(oID,oMakeOrderID,oTakeOrderID,oGoodsDir,oGoodsCompany,oCoodsPhone,oGoodsNumber,oOrderMessage,oOrderType,oOrderMoney,oSendAddress,oOrderPersonName,oOrderIsTake) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, bean.getoID() );
		ps.setString(2, bean.getoMakeOrderID());
		ps.setString(3, bean.getoTakeOrderID());
		ps.setString(4, bean.getoGoodsDir());
		ps.setString(5, bean.getoGoodsCompany());
		
		ps.setString(6, bean.getoCoodsPhone());
		ps.setString(7, bean.getoGoodsNumber());
		ps.setString(8, bean.getoOrderMessage());
		ps.setString(9, bean.getoOrderType());
		ps.setString(10, bean.getoOrderMoney());
		
		ps.setString(11, bean.getoSendAddress());
		ps.setString(12, bean.getoOrderPersonName() );
		ps.setBoolean(13, bean.isoOrderIsTake() );

		ps.executeUpdate();
		System.out.println("插入订单成功!");
		DBConnection.close(ps, conn);
	}


	public static void deleteOrdersByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Orders where oID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除订单成功："+ID);
		DBConnection.close(ps, conn);
	}


	public static void updateOrders(Orders bean) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Orders set  oMakeOrderID=?,oTakeOrderID=?,oGoodsDir=?,oGoodsCompany=?,oCoodsPhone=?,oGoodsNumber=?,oOrderMessage=?,oOrderType=?,oOrderMoney=?,oSendAddress=?,oOrderPersonName=?,oOrderIsTake=?  where oID=?");
		
		ps.setString(1, bean.getoMakeOrderID());
		ps.setString(2, bean.getoTakeOrderID());
		ps.setString(3, bean.getoGoodsDir());
		ps.setString(4, bean.getoGoodsCompany());
		
		ps.setString(5, bean.getoCoodsPhone());
		ps.setString(6, bean.getoGoodsNumber());
		ps.setString(7, bean.getoOrderMessage());
		ps.setString(8, bean.getoOrderType());
		ps.setString(9, bean.getoOrderMoney());
		
		ps.setString(10, bean.getoSendAddress());
		ps.setString(11, bean.getoOrderPersonName() );
		ps.setBoolean(12, bean.isoOrderIsTake() );
		ps.setString(13, bean.getoID() );
		
		ps.executeUpdate();
		System.out.println("更新订单成功!"+ bean.getoID());
		DBConnection.close(ps, conn);
	}

	public static Orders searchOrdersByID(String ID) throws Exception {
		Orders bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Orders where oID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Orders();
			bean.setoID(rs.getString("oID"));
			bean.setoCoodsPhone(rs.getString("oCoodsPhone"));
			bean.setoGoodsCompany(rs.getString("oGoodsCompany"));
		    bean.setoGoodsDir(rs.getString("oGoodsDir"));
		    bean.setoGoodsNumber(rs.getString("oGoodsNumber"));

		    bean.setoMakeOrderID(rs.getString("oMakeOrderID"));
		    bean.setoOrderIsTake(rs.getBoolean("oOrderIsTake"));
		    bean.setoOrderMessage(rs.getString("oOrderMessage"));
		    bean.setoOrderMoney(rs.getString("oOrderMoney"));
		    bean.setoOrderPersonName(rs.getString("oOrderPersonName"));
		    
		    bean.setoOrderType(rs.getString("oOrderType"));
		    bean.setoSendAddress(rs.getString("oSendAddress"));
		    bean.setoTakeOrderID(rs.getString("oTakeOrderID")); 
		}

		System.out.println("查找订单成功:"+ bean.getoID());
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	public static ArrayList<Orders> searchAllOrders() throws Exception {
		ArrayList<Orders> list = new ArrayList<Orders>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Orders");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Orders bean = new Orders();
			bean.setoID(rs.getString("oID"));
			bean.setoCoodsPhone(rs.getString("oCoodsPhone"));
			bean.setoGoodsCompany(rs.getString("oGoodsCompany"));
		    bean.setoGoodsDir(rs.getString("oGoodsDir"));
		    bean.setoGoodsNumber(rs.getString("oGoodsNumber"));

		    bean.setoMakeOrderID(rs.getString("oMakeOrderID"));
		    bean.setoOrderIsTake(rs.getBoolean("oOrderIsTake"));
		    bean.setoOrderMessage(rs.getString("oOrderMessage"));
		    bean.setoOrderMoney(rs.getString("oOrderMoney"));
		    bean.setoOrderPersonName(rs.getString("oOrderPersonName"));
		    
		    bean.setoOrderType(rs.getString("oOrderType"));
		    bean.setoSendAddress(rs.getString("oSendAddress"));
		    bean.setoTakeOrderID(rs.getString("oTakeOrderID"));
		
			list.add(bean);
		}
		System.out.println("查找所有订单成功！");
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
	
	public static void main(String [] args) throws Exception
	{
		Orders bean=new Orders();
		
		bean.setoID("oID");
		bean.setoCoodsPhone("oCoodsPhone");
		bean.setoGoodsCompany("oGoodsCompany");
	    bean.setoGoodsDir("oGoodsDir");
	    bean.setoGoodsNumber("oGoodsNumber");

	    bean.setoMakeOrderID("oMakeOrderID");
	    bean.setoOrderIsTake(false);
	    bean.setoOrderMessage("oOrderMessage");
	    bean.setoOrderMoney("oOrderMoney");
	    bean.setoOrderPersonName("oOrderPersonName");
	    
	    bean.setoOrderType("oOrderType");
	    bean.setoSendAddress("oSendAddress");
	    bean.setoTakeOrderID("oTakeOrderID");

		OrdersDao.insertOrders(bean);
		
		OrdersDao.deleteOrdersByID(bean.getoID());
		
		bean.setoGoodsCompany("zyr");
		
		OrdersDao.insertOrders(bean);
		
		OrdersDao.searchOrdersByID(bean.getoID());
		OrdersDao.searchAllOrders();
	
	}
}
