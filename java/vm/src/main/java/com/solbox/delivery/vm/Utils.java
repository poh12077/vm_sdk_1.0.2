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
	
	static String VMCreateResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject server = fianlJsonObject.getJSONObject("server");
		String ID = server.getString("id");
		return ID;
	}
	
	static String volumeCreateResponseParser(String response) throws JSONException {
		JSONObject fianlJsonObject = new JSONObject(response);
		JSONObject volume = fianlJsonObject.getJSONObject("volume");
		String ID = volume.getString("id");
		return ID;
	}
	
}
