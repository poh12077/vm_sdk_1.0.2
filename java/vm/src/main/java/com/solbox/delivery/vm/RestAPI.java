package com.solbox.delivery.vm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class RestAPI {
	//token
	static String request(String URL, String method, String requestBody) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);
		connection.setRequestMethod(method);
		// connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setRequestProperty("Content-Type", "application/json");
		if (method == "POST" || method == "PUT") {
			connection.setDoOutput(true);
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(requestBody);
			outputStream.flush();
			outputStream.close();
		}
		int statusCode = connection.getResponseCode();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(statusCode + " " + response);

		String token = connection.getHeaderField("X-Subject-Token");
		JSONObject result = new JSONObject();
		result.put("statusCode", statusCode);
		result.put("response", token);
		return result.toString();
	}

	//general
	static String request(String URL, String method, String token, String requestBody) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		// connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("X-Auth-Token", token);
		if (method == "POST" || method == "PUT") {
			connection.setDoOutput(true);
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(requestBody);
			outputStream.flush();
			outputStream.close();
		}
		int statusCode = connection.getResponseCode();
		if(statusCode == 409) {
			JSONObject result = new JSONObject();
			result.put("statusCode", statusCode);
			result.put("response", "");
			return result.toString();
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(statusCode + " " + response);

		JSONObject result = new JSONObject();
		result.put("statusCode", statusCode);
		result.put("response", response);
		return result.toString();
	}
	
	 private static final String USER_AGENT = "Mozila/5.0";
	    private static final String GET_URL = "https://www.google.com";
	    
	    public static void sendGet() throws ClientProtocolException, IOException {
	        
	        //http client 생성
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        
	        //get 메서드와 URL 설정
	        HttpGet httpGet = new HttpGet(GET_URL);
	        
	        //agent 정보 설정
	        httpGet.addHeader("User-Agent", USER_AGENT);
	        httpGet.addHeader("Content-type", "application/json");
	        
	        //get 요청
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        
	        System.out.println("GET Response Status");
	        System.out.println(httpResponse.getStatusLine().getStatusCode());
	        String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	        
	        System.out.println(json);
	        
	        httpClient.close();
	    }
	    
	    public static void post(String URL, String token, String requestBody) throws ClientProtocolException, IOException {
	    		    CloseableHttpClient client = HttpClients.createDefault();
	    		    HttpPost httpPost = new HttpPost(URL);
	    		    StringEntity entity = new StringEntity(requestBody);
	    		    httpPost.setEntity(entity);
	    		    httpPost.setHeader("Accept", "application/json");
	    		    httpPost.setHeader("Content-type", "application/json");
	    		    httpPost.setHeader("X-Auth-Token", token);

	    		    CloseableHttpResponse response = client.execute(httpPost);
	    		   
	    		    System.out.println("GET Response Status");
	    	        System.out.println(response.getStatusLine().getStatusCode());
	    	        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
	    	        
	    	        System.out.println(responseBody);
	    	        
	    		    client.close();
	    		}
	  	    
}
