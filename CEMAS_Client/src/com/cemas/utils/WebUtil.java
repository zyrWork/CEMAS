package com.cemas.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

public class WebUtil {
	/**
	 * 从服务端获取所有数据
	 * 
	 * @param strUrl
	 * @return
	 */
	
	
	
	public static String getAllData(String strUrl) {
		String result = "";
		URL url = null;
		HttpURLConnection connection = null;
		InputStream in = null;
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(false);
			connection.setConnectTimeout(20000);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();
			connection.setDoInput(true);
			connection.setDoOutput(true);

			in = connection.getInputStream();
			if (in == null) {
				result = null;
			} else {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(in));
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/**
	 * 从服务端根据条件查询数据
	 * 
	 * @param methodName
	 * @param params
	 * @return
	 */
	public static JSONArray getDataByPara(String methodName, JSONArray params) {

		String returnValue = "";
		JSONArray result = null;
		HttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("charset", "UTF-8");
		HttpClient hc = new DefaultHttpClient(httpParams);
		HttpPost hp = new HttpPost(methodName);
		try {
			hp.setEntity(new StringEntity(params.toString(), "UTF-8"));
			HttpResponse hr = hc.execute(hp);
			if (hr.getStatusLine().getStatusCode() == 200) {
				returnValue = EntityUtils.toString(hr.getEntity(), "UTF-8");
				result = new JSONArray(returnValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hc != null) {
			hc.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * 发送数据到服务端
	 * 
	 * @param methodName
	 * @param params
	 * @return
	 */
	public static String setJsonData(String postUrl, String postData) {

		String returnValue = "";
		HttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("charset", "UTF-8");
		HttpClient hc = new DefaultHttpClient(httpParams);
		HttpPost hp = new HttpPost(postUrl);
		try {
			hp.setEntity(new StringEntity(postData, "UTF-8"));
			HttpResponse hr = hc.execute(hp);
			if (hr.getStatusLine().getStatusCode() == 200) {
				returnValue = "操作成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hc != null) {
			hc.getConnectionManager().shutdown();
		}
		return returnValue;
	}

	

	public static InputStream getImageViewInputStream(String N_Picture)
			throws IOException {
		InputStream inputStream = null;
		URL url = new URL(GlobalVariable.imageBasePath + N_Picture); // 服务器地址
		if (url != null) {
			// 打开连接
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setConnectTimeout(3000);// 设置网络连接超时的时间为3秒
			httpURLConnection.setRequestMethod("GET"); // 设置请求方法为GET
			httpURLConnection.setDoInput(true); // 打开输入流
			int responseCode = httpURLConnection.getResponseCode(); // 获取服务器响应值
			if (responseCode == HttpURLConnection.HTTP_OK) { // 正常连接
				inputStream = httpURLConnection.getInputStream(); // 获取输入流
			}
		}
		return inputStream;
	}

}
