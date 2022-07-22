package com.solbox.delivery.vm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;

public class Utils {
	
	static String statusCodeParser(String result) throws JSONException {
		JSONObject jsonResult = new JSONObject(result);
		if( jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_CREATED 
				|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_OK 
				|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_ACCEPTED  ) 
		{
			return jsonResult.getString("response");
		}else {
			//fail logic should be here
			//System.out.println( jsonResult.getInt("statusCode") );
			return "";
		}
	}
	
	static String getProjectID(String result) throws JSONException {
		JSONObject jsonResult = new JSONObject(result);
		if( jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_CREATED 
				|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_OK 
				|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_ACCEPTED  ) 
		{
			return jsonResult.getString("projectID");
		}else {
			//fail logic should be here
			//System.out.println( jsonResult.getInt("statusCode") );
			return "";
		}
	}
	
	static String VMCreateResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject server = fianlJsonObject.getJSONObject("server");
		String ID = server.getString("id");
		return ID;
	}
	
	static String VMDetailResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject server = fianlJsonObject.getJSONObject("server");
		JSONObject addresses = server.getJSONObject("addresses");
		JSONArray Private = addresses.getJSONArray("Private");
		String privateIP="";
		for(int i=0;i<Private.length();i++) {
			JSONObject jsonObject = Private.getJSONObject(i);
			privateIP = jsonObject.getString("addr");
		}
		return privateIP;
	}
	
	
	static String volumeCreateResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject volume = fianlJsonObject.getJSONObject("volume");
		String ID = volume.getString("id");
		return ID;
	}
	
	static String IPCreateResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject nc_associateentpublicipresponse = fianlJsonObject.getJSONObject("nc_associateentpublicipresponse");
		String job_id = nc_associateentpublicipresponse.getString("job_id");
		return job_id;
	}
	
	static String jobIDLookupResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject nc_associateentpublicipresponse = fianlJsonObject.getJSONObject("nc_queryasyncjobresultresponse");
		JSONObject result = nc_associateentpublicipresponse.getJSONObject("result");
		String IP_id = result.getString("id");
		return IP_id;
	}
	
	static String staticNATSettingResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject nc_enablestaticnatresponse = fianlJsonObject.getJSONObject("nc_enablestaticnatresponse");
		String staticNAT_ID="";
		if(nc_enablestaticnatresponse.getBoolean("success")==true) {
			staticNAT_ID = nc_enablestaticnatresponse.getString("id");
			return staticNAT_ID;
		}else {
			return staticNAT_ID;
		}
	}
	
	static String projectIDParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject token = fianlJsonObject.getJSONObject("token");
		JSONObject project = token.getJSONObject("project");
		String ID= project.getString("id");
		return ID;
	}
	
	static void deleteVMOnly(String serverID, String token, int timeout) throws Exception {
			String requestBody=RequestBody.forceDeleteVM();
			String result = RestAPI.post(KTCloudOpenAPI.forceDeleteVM_URL+serverID+"/action", token, requestBody, timeout);
			JSONObject jsonResult = new JSONObject(result);
			if( jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_CREATED 
					|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_OK 
					|| jsonResult.getInt("statusCode") == HttpURLConnection.HTTP_ACCEPTED  ) 
			{
				System.out.println("Server deletion is done");
			}else {
				System.out.println("Server deletion failed");
			}
	}
	
	
}
