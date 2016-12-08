package com.cemas.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cemas.bean.User;
import com.cemas.dao.UserDao;
import com.cemas.utils.HttpRequestImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
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
				User bean = new User();
				bean.setuAge( jsonArray.getJSONObject(0).getString("uAge") );
				bean.setuEmail( jsonArray.getJSONObject(0).getString("uEmail") );
				bean.setuGender( jsonArray.getJSONObject(0).getString("uGender") );
				bean.setuID( jsonArray.getJSONObject(0).getString("uID") );
				bean.setuIdentify( jsonArray.getJSONObject(0).getString("uIdentify") );
				
				bean.setuName( jsonArray.getJSONObject(0).getString("uName") );
				bean.setuPassword( jsonArray.getJSONObject(0).getString("uPassword") );
				bean.setuSchoolNumber( jsonArray.getJSONObject(0).getString("uSchoolNumber") );
				bean.setuScore( jsonArray.getJSONObject(0).getString("uScore") );
				bean.setuTele( jsonArray.getJSONObject(0).getString("uTele") );
				
				UserDao.insertUser(bean);
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

					UserDao.deleteUserByID(jsonArray.getJSONObject(0).getString("uID"));
					
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
				
				User bean = new User();
				bean.setuAge( jsonArray.getJSONObject(0).getString("uAge") );
				bean.setuEmail( jsonArray.getJSONObject(0).getString("uEmail") );
				bean.setuGender( jsonArray.getJSONObject(0).getString("uGender") );
				bean.setuID( jsonArray.getJSONObject(0).getString("uID") );
				bean.setuIdentify( jsonArray.getJSONObject(0).getString("uIdentify") );
				
				bean.setuName( jsonArray.getJSONObject(0).getString("uName") );
				bean.setuPassword( jsonArray.getJSONObject(0).getString("uPassword") );
				bean.setuSchoolNumber( jsonArray.getJSONObject(0).getString("uSchoolNumber") );
				bean.setuScore( jsonArray.getJSONObject(0).getString("uScore") );
				bean.setuTele( jsonArray.getJSONObject(0).getString("uTele") );
		
				UserDao.updateUser(bean);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			
			System.out.println("getData -- " + method);
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			ArrayList<User> beanList = new ArrayList<User>();

			try {
				beanList = UserDao.searchAllUser();
				for (User bean : beanList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("uAge", bean.getuAge());
					jsonObject.put("uEmail", bean.getuEmail());
					jsonObject.put("uGender", bean.getuGender());
					jsonObject.put("uID", bean.getuID());
					jsonObject.put("uIdentify", bean.getuIdentify());
					
					jsonObject.put("uName", bean.getuName());
					jsonObject.put("uPassword", bean.getuPassword());
					jsonObject.put("uSchoolNumber", bean.getuSchoolNumber());
					jsonObject.put("uScore", bean.getuScore());
					jsonObject.put("uTele", bean.getuTele());
			
					jsonArray.put(jsonObject);
				}
				out.write(jsonArray.toString());
				out.flush();
				System.out.println("结果--" + jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("login".equals(method)) {
			System.out.println("login -- " + method);

			String reqMessage, respMessage;
			JSONArray reqObject = null;
			JSONArray respObject = null;

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer("");
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();

				reqMessage = sb.toString();
				System.out.println("请求报文:" + reqMessage);

				reqObject = new JSONArray(reqMessage);
	
				// 获取账号和密码
				String account_number = reqObject.getJSONObject(0).getString("uID");
				String password = reqObject.getJSONObject(0).getString("uPassword");
 
				System.out.println("p--"+password);
				System.out.println("n--"+account_number);
				User bean = UserDao.searchUserByID(account_number);

				if(bean==null)
				{//避免cu为空，引起空指针错误
					respObject=null;
				}
				else if (bean.getuPassword() != null && bean.getuPassword().equals(password)) {
					respObject = new JSONArray().put(new JSONObject().put("uID", bean.getuID()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				respMessage = respObject == null ? "" : respObject.toString();
				System.out.println("返回报文:" + respMessage);
				PrintWriter pw = response.getWriter();
				pw.write(respMessage);
				pw.flush();
				pw.close();
			}
		}else if ("register".equals(method)) {
			System.out.println("register -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			//注册的本质就是插入数据
			// 插入数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				// 存数据库
				User bean = new User();
				bean.setuAge( jsonArray.getJSONObject(0).getString("uAge") );
				bean.setuEmail( jsonArray.getJSONObject(0).getString("uEmail") );
				bean.setuGender( jsonArray.getJSONObject(0).getString("uGender") );
				bean.setuID( jsonArray.getJSONObject(0).getString("uID") );
				bean.setuIdentify( jsonArray.getJSONObject(0).getString("uIdentify") );
				
				bean.setuName( jsonArray.getJSONObject(0).getString("uName") );
				bean.setuPassword( jsonArray.getJSONObject(0).getString("uPassword") );
				bean.setuSchoolNumber( jsonArray.getJSONObject(0).getString("uSchoolNumber") );
				bean.setuScore( jsonArray.getJSONObject(0).getString("uScore") );
				bean.setuTele( jsonArray.getJSONObject(0).getString("uTele") );
				
				UserDao.insertUser(bean);
				
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