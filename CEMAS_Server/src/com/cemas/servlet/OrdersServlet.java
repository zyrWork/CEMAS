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
		// ���������ַ���,�Ա�������ȡ����
		request.setCharacterEncoding("UTF-8");
		// ������Ӧ�ַ���,��������ĵ������������ʾ
		response.setContentType("text/json;charset=UTF-8");

		String method = request.getParameter("method");

		if ("insertData".equals(method)) {
			// ��������
			System.out.println("insert -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			// ��������
			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				// �����ݿ�
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
			// ɾ��
			System.out.println("delete -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				OrdersDao.deleteOrdersByID(jsonArray.getJSONObject(0).getString("oID"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("updateData".equals(method)) {
			System.out.println("update -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			// ��������
			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				// ���±�

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
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();

			ArrayList<Orders> beanList = new ArrayList<Orders>();

			try {
				beanList = OrdersDao.searchAllOrders();
				for (Orders bean : beanList) {
					// JSONת�� �ṩ���ƶ��˵���
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
				System.out.println("���--" + jsonArray.toString());
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
