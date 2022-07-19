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

	//token
	static String request(String URL, String method, String requestBody) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
}
