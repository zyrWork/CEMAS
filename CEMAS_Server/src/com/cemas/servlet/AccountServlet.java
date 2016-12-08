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

import com.cemas.bean.Account;
import com.cemas.dao.AccountDao;
import com.cemas.utils.HttpRequestImpl;

public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
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
				Account bean = new Account();
				bean.setaID(jsonArray.getJSONObject(0).getString("aID") );
				bean.setaType( jsonArray.getJSONObject(0).getString("aType") );
				bean.setuID( jsonArray.getJSONObject(0).getString("uID") );
				bean.setaDefaultType( jsonArray.getJSONObject(0).getString("aDefaultType") );
	
				AccountDao.insertAccount(bean);
			} catch (Exception e) {	
				e.printStackTrace();
			}
		}else if ("deleteData".equals(method)) {
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

					AccountDao.deleteAccountByID(jsonArray.getJSONObject(0).getString("aID"));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if ("updateData".equals(method)) {
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

				//更新表
				
				Account bean = new Account();
				bean.setaID(jsonArray.getJSONObject(0).getString("aID") );
				bean.setaType( jsonArray.getJSONObject(0).getString("aType") );
				bean.setuID( jsonArray.getJSONObject(0).getString("uID") );
				bean.setaDefaultType( jsonArray.getJSONObject(0).getString("aDefaultType") );
	
				AccountDao.updateAccount(bean);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			
			System.out.println("getData -- " + method);
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			ArrayList<Account> beanList = new ArrayList<Account>();

			try {
				beanList = AccountDao.searchAllAccount();
				for (Account bean : beanList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("aID", bean.getaID());
					jsonObject.put("aType", bean.getaType());
					jsonObject.put("uID", bean.getuID());
					jsonObject.put("DefaultType", bean.getaDefaultType());	
			
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
