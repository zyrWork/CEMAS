package com.cemas.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cemas.bean.Account;
import com.cemas.utils.GlobalVariable;
import com.cemas.utils.HttpRequestImpl;
import com.cemas.utils.WebUtil;

import android.annotation.SuppressLint;



@SuppressLint("NewApi")
public class AccountService {

	// ����˵Ľӿ�URL��ַ
	// ��ȡ����
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=getData";
	// ��������
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=insertData";
	// �޸�����
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=updateData";
	// ɾ������
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=deleteData";
  
	
	public static ArrayList<Account> getAllAccount( ) {
		// ��ȡ����
		String requestResult = HttpRequestImpl.doGet(getUrl);
		System.out.println("requestResult--" + requestResult);
		
		ArrayList<Account> list = new ArrayList<Account>();
		if(requestResult==null)
		{
			
		}else 
		{
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Account bean = new Account();//���ܶ���������
					bean.setaID(jsonObj.getString("aID") );
					bean.setaType( jsonObj.getString("aType") );
					bean.setuID(jsonObj.getString("uID") );
					bean.setaDefaultType( jsonObj.getString("aDefaultType") );
		
					list.add(bean);
				}
			} catch (JSONException e) {
				System.out.println("Json ת������!");
				e.printStackTrace();
			}
		}
		
		return list;
	}
	

	public static void insertAccount(Account bean) {
		// ��������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("aID", bean.getaID());
			jsonObject.put("aType", bean.getaType());
			jsonObject.put("uID", bean.getuID());
			jsonObject.put("DefaultType", bean.getaDefaultType());	

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			
			String postResult = WebUtil.setJsonData(postUrl, postData);
			
			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void updateAccount(Account bean) {
		// ��������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
	
			jsonObject.put("aID", bean.getaID());
			jsonObject.put("aType", bean.getaType());
			jsonObject.put("uID", bean.getuID());
			jsonObject.put("DefaultType", bean.getaDefaultType());	

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//��Ҫһ��
			String postResult = WebUtil.setJsonData(updUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void deleteAccount(Account bean) {
		// ɾ������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("aID", bean.getaID());
			jsonObject.put("aType", bean.getaType());
			jsonObject.put("uID", bean.getuID());
			jsonObject.put("DefaultType", bean.getaDefaultType());	

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//��Ҫһ��
			String postResult = WebUtil.setJsonData(delUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
