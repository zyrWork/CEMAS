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

	// 服务端的接口URL地址
	// 获取数据
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=getData";
	// 增加数据
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=insertData";
	// 修改数据
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=updateData";
	// 删除数据
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/AccountServlet?method=deleteData";
  
	
	public static ArrayList<Account> getAllAccount( ) {
		// 获取数据
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
					Account bean = new Account();//不能定义在外面
					bean.setaID(jsonObj.getString("aID") );
					bean.setaType( jsonObj.getString("aType") );
					bean.setuID(jsonObj.getString("uID") );
					bean.setaDefaultType( jsonObj.getString("aDefaultType") );
		
					list.add(bean);
				}
			} catch (JSONException e) {
				System.out.println("Json 转换错误!");
				e.printStackTrace();
			}
		}
		
		return list;
	}
	

	public static void insertAccount(Account bean) {
		// 增加数据
		try {
			// 定义一个JSON，用于向服务器提交数据
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
		// 更新数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
	
			jsonObject.put("aID", bean.getaID());
			jsonObject.put("aType", bean.getaType());
			jsonObject.put("uID", bean.getuID());
			jsonObject.put("DefaultType", bean.getaDefaultType());	

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//重要一步
			String postResult = WebUtil.setJsonData(updUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void deleteAccount(Account bean) {
		// 删除数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("aID", bean.getaID());
			jsonObject.put("aType", bean.getaType());
			jsonObject.put("uID", bean.getuID());
			jsonObject.put("DefaultType", bean.getaDefaultType());	

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//重要一步
			String postResult = WebUtil.setJsonData(delUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
