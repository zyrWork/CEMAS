package com.cemas.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cemas.bean.User;
import com.cemas.utils.GlobalVariable;
import com.cemas.utils.HttpRequestImpl;
import com.cemas.utils.WebUtil;

import android.annotation.SuppressLint;



@SuppressLint("NewApi")
public class UserService {

	// 服务端的接口URL地址
	// 获取数据
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/UserServlet?method=getData";
	// 增加数据
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/UserServlet?method=insertData";
	// 修改数据
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/UserServlet?method=updateData";
	// 删除数据
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CEMAS_Server/UserServlet?method=deleteData";
  
	
	public static ArrayList<User> getAllUser( ) {
		// 获取数据
		String requestResult = HttpRequestImpl.doGet(getUrl);
		System.out.println("requestResult--" + requestResult);
		
		ArrayList<User> list = new ArrayList<User>();
		if(requestResult==null)
		{
			
		}else 
		{
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					User bean = new User();//不能定义在外面
					
					bean.setuAge( jsonObj.getString("uAge") );
					bean.setuEmail( jsonObj.getString("uEmail") );
					bean.setuGender( jsonObj.getString("uGender") );
					bean.setuID( jsonObj.getString("uID") );
					bean.setuIdentify( jsonObj.getString("uIdentify") );
					
					bean.setuName( jsonObj.getString("uName") );
					bean.setuPassword( jsonObj.getString("uPassword") );
					bean.setuSchoolNumber( jsonObj.getString("uSchoolNumber") );
					bean.setuScore( jsonObj.getString("uScore") );
					bean.setuTele( jsonObj.getString("uTele") );
		
					list.add(bean);
				}
			} catch (JSONException e) {
				System.out.println("Json 转换错误!");
				e.printStackTrace();
			}
		}
		
		return list;
	}
	

	public static void insertUser(User bean) {
		// 增加数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
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

			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			
			String postResult = WebUtil.setJsonData(postUrl, postData);
			
			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void updateUser(User bean) {
		// 更新数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
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

	public static void deleteUser(User bean) {
		// 删除数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
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
