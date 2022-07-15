package com.solbox.delivery.vm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RestAPI {
		
	static String getToken(String URL, String method, String USER_AGENT, String CONTENT_TYPE, String requestBody)
			throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);
		connection.setDoOutput(true);

		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(requestBody);
		outputStream.flush();
		outputStream.close();

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
		result.put("statusCode",statusCode);
		result.put("response",token);
		
		return result.toString();
	}
	

	static String request(String URL, String method, String token, String USER_AGENT, String CONTENT_TYPE, String requestBody)
			throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);
		connection.setRequestProperty("X-Auth-Token", token);
		connection.setDoOutput(true);
		
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(requestBody);
		outputStream.flush();
		outputStream.close();

		int statusCode = connection.getResponseCode();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;

		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println( statusCode + " " + response);

		JSONObject result = new JSONObject();
		result.put("statusCode",statusCode);
		result.put("response",response);
		
		return result.toString();
	}

	static String request(String URL, String method, String token) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
		connection.setRequestProperty("X-Auth-Token", token);
		//connection.setDoOutput(true);

		int statusCode = connection.getResponseCode();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(response);
		
		JSONObject result = new JSONObject();
		result.put("statusCode",statusCode);
		result.put("response",response);
		
		return result.toString();
	}

	

	static void request(String URL, String method, String token, String USER_AGENT, String CONTENT_TYPE)
			throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);
		connection.setRequestProperty("X-Auth-Token", token);
		//connection.connect();
		int statusCode = connection.getResponseCode();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(response);
	}
	
	static void requestForceDeleteVM(String URL, String method, String token, String USER_AGENT, String CONTENT_TYPE, String requestBody, String URLData) 
			throws Exception {
		URL = URL + URLData + "/action";
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);
		connection.setRequestProperty("X-Auth-Token", token);
		connection.setDoOutput(true);

		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(requestBody);
		outputStream.flush();
		outputStream.close();
		int statusCode = connection.getResponseCode();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(statusCode);
		System.out.println(response);
		
		if (statusCode == HttpURLConnection.HTTP_CREATED) {
		} else {
		}
	}

	static void request(String URL, String method, String token, String URLData) 
			throws Exception {
		URL = URL + URLData;
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
		connection.setRequestProperty("X-Auth-Token", token);

		int statusCode = connection.getResponseCode();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();
		String response = stringBuffer.toString();
		System.out.println(statusCode);
		System.out.println(response);
		
		if (statusCode == HttpURLConnection.HTTP_CREATED) {
		} else {
		}
	}
	
}