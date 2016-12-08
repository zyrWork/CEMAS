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

import com.cemas.bean.Admin;
import com.cemas.dao.AdminDao;
import com.cemas.utils.HttpRequestImpl;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
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
				Admin bean = new Admin();
				bean.setaID( jsonArray.getJSONObject(0).getString("aID") );
				bean.setaPassword( jsonArray.getJSONObject(0).getString("aPassword") );
				bean.setaName( jsonArray.getJSONObject(0).getString("aName") );
		
				AdminDao.insertAdmin(bean);
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

					AdminDao.deleteAdminByID(jsonArray.getJSONObject(0).getString("aID"));
					
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
				
				Admin bean = new Admin();
				bean.setaID( jsonArray.getJSONObject(0).getString("aID") );
				bean.setaPassword( jsonArray.getJSONObject(0).getString("aPassword") );
				bean.setaName( jsonArray.getJSONObject(0).getString("aName") );
		
				AdminDao.updateAdmin(bean);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			
			System.out.println("getData -- " + method);
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			ArrayList<Admin> beanList = new ArrayList<Admin>();

			try {
				beanList = AdminDao.searchAllAdmin();
				for (Admin bean : beanList) {
					// JSONת�� �ṩ���ƶ��˵���
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("aID", bean.getaID());
					jsonObject.put("aPassword", bean.getaPassword());
					jsonObject.put("aName", bean.getaName());
					
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

