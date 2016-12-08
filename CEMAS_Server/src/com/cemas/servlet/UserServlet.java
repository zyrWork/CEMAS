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

					UserDao.deleteUserByID(jsonArray.getJSONObject(0).getString("uID"));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if ("updateData".equals(method)) {
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

				//���±�
				
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
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			ArrayList<User> beanList = new ArrayList<User>();

			try {
				beanList = UserDao.searchAllUser();
				for (User bean : beanList) {
					// JSONת�� �ṩ���ƶ��˵���
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
				System.out.println("���--" + jsonArray.toString());
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
				System.out.println("������:" + reqMessage);

				reqObject = new JSONArray(reqMessage);
	
				// ��ȡ�˺ź�����
				String account_number = reqObject.getJSONObject(0).getString("uID");
				String password = reqObject.getJSONObject(0).getString("uPassword");
 
				System.out.println("p--"+password);
				System.out.println("n--"+account_number);
				User bean = UserDao.searchUserByID(account_number);

				if(bean==null)
				{//����cuΪ�գ������ָ�����
					respObject=null;
				}
				else if (bean.getuPassword() != null && bean.getuPassword().equals(password)) {
					respObject = new JSONArray().put(new JSONObject().put("uID", bean.getuID()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				respMessage = respObject == null ? "" : respObject.toString();
				System.out.println("���ر���:" + respMessage);
				PrintWriter pw = response.getWriter();
				pw.write(respMessage);
				pw.flush();
				pw.close();
			}
		}else if ("register".equals(method)) {
			System.out.println("register -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			//ע��ı��ʾ��ǲ�������
			// ��������
			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				// �����ݿ�
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