package com.cemas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cemas.bean.Orders;
import com.cemas.dao.OrdersDao;
import com.cemas.utils.HttpRequestImpl;

public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdersServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求字符集,以便从请求读取中文
		request.setCharacterEncoding("UTF-8");
		// 设置响应字符集,以输出中文到浏览器正常显示
		response.setContentType("text/json;charset=UTF-8");

		String method = request.getParameter("method");

		if ("insertData".equals(method)) {
			// 插入数据
			System.out.println("insert -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			// 插入数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				// 存数据库
				Orders bean = new Orders();
				bean.setoID(jsonArray.getJSONObject(0).getString("oID"));
				bean.setoCoodsPhone(jsonArray.getJSONObject(0).getString("oCoodsPhone"));
				bean.setoGoodsCompany(jsonArray.getJSONObject(0).getString("oGoodsCompany"));
				bean.setoGoodsDir(jsonArray.getJSONObject(0).getString("oGoodsDir"));
				bean.setoGoodsNumber(jsonArray.getJSONObject(0).getString("oGoodsNumber"));

				bean.setoMakeOrderID(jsonArray.getJSONObject(0).getString("oMakeOrderID"));
				bean.setoOrderIsTake(jsonArray.getJSONObject(0).getBoolean("oOrderIsTake"));
				bean.setoOrderMessage(jsonArray.getJSONObject(0).getString("oOrderMessage"));
				bean.setoOrderMoney(jsonArray.getJSONObject(0).getString("oOrderMoney"));
				bean.setoOrderPersonName(jsonArray.getJSONObject(0).getString("oOrderPersonName"));

				bean.setoOrderType(jsonArray.getJSONObject(0).getString("oOrderType"));
				bean.setoSendAddress(jsonArray.getJSONObject(0).getString("oSendAddress"));
				bean.setoTakeOrderID(jsonArray.getJSONObject(0).getString("oTakeOrderID"));

				OrdersDao.insertOrders(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("deleteData".equals(method)) {
			// 删除
			System.out.println("delete -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				OrdersDao.deleteOrdersByID(jsonArray.getJSONObject(0).getString("oID"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("updateData".equals(method)) {
			System.out.println("update -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			// 更新数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				// 更新表

				Orders bean = new Orders();
				bean.setoID(jsonArray.getJSONObject(0).getString("oID"));
				bean.setoCoodsPhone(jsonArray.getJSONObject(0).getString("oCoodsPhone"));
				bean.setoGoodsCompany(jsonArray.getJSONObject(0).getString("oGoodsCompany"));
				bean.setoGoodsDir(jsonArray.getJSONObject(0).getString("oGoodsDir"));
				bean.setoGoodsNumber(jsonArray.getJSONObject(0).getString("oGoodsNumber"));

				bean.setoMakeOrderID(jsonArray.getJSONObject(0).getString("oMakeOrderID"));
				bean.setoOrderIsTake(jsonArray.getJSONObject(0).getBoolean("oOrderIsTake"));
				bean.setoOrderMessage(jsonArray.getJSONObject(0).getString("oOrderMessage"));
				bean.setoOrderMoney(jsonArray.getJSONObject(0).getString("oOrderMoney"));
				bean.setoOrderPersonName(jsonArray.getJSONObject(0).getString("oOrderPersonName"));

				bean.setoOrderType(jsonArray.getJSONObject(0).getString("oOrderType"));
				bean.setoSendAddress(jsonArray.getJSONObject(0).getString("oSendAddress"));
				bean.setoTakeOrderID(jsonArray.getJSONObject(0).getString("oTakeOrderID"));

				OrdersDao.updateOrders(bean);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getData".equals(method)) {

			System.out.println("getData -- " + method);
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();

			ArrayList<Orders> beanList = new ArrayList<Orders>();

			try {
				beanList = OrdersDao.searchAllOrders();
				for (Orders bean : beanList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();

					jsonObject.put("oCoodsPhone", bean.getoCoodsPhone());
					jsonObject.put("oGoodsCompany", bean.getoGoodsCompany());
					jsonObject.put("oGoodsDir", bean.getoGoodsDir());
					jsonObject.put("oGoodsNumber", bean.getoGoodsNumber());
					jsonObject.put("oID", bean.getoID());

					jsonObject.put("oMakeOrderID", bean.getoMakeOrderID());
					jsonObject.put("oOrderMessage", bean.getoOrderMessage());
					jsonObject.put("oOrderMoney", bean.getoOrderMoney());
					jsonObject.put("oOrderPersonName", bean.getoOrderPersonName());
					jsonObject.put("oOrderType", bean.getoOrderType());

					jsonObject.put("oSendAddress", bean.getoSendAddress());
					jsonObject.put("oTakeOrderID", bean.getoTakeOrderID());
					jsonObject.put("oOrderIsTake", bean.isoOrderIsTake());

					jsonArray.put(jsonObject);
				}
				out.write(jsonArray.toString());
				out.flush();
				System.out.println("结果--" + jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
