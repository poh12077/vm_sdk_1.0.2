package com.solbox.delivery.vm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class App {
	 static final String getToken_URL = "https://api.ucloudbiz.olleh.com/d1/identity/auth/tokens";
	 static final String getVM_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers";
	 static final String forceDeleteVM_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";
	 static final String getVolume_URL = "https://api.ucloudbiz.olleh.com/d1/volume/c3d6ced17b1d45d98af60cbeff8f4bbd/volumes";
	 static final String connectVMAndVolume_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";
	 static final String getIP_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress";
	 static final String deleteIP_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress/";
	 static final String setStaticNAT_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	 static final String openFirewall_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	 static final String firewall_List_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	 static final String closeFirewall_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall/";
	 static final String VMList_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/detail";
	 static final String staticNATList_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	 static final String IPList_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress";
	 static final String DeleteStaticNAT_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat/";
			
	 static final String GET = "GET";
	 static final String DELETE = "DELETE";
	 static final String POST = "POST";
	 static final String USER_AGENT = "Mozilla/5.0";
	 static final String CONTENT_TYPE = "application/json";
	
	public static void main(String[] args) {
		try {
			String requestBody;
			String list;
			String result;
			String response;
			result = RestAPI.getToken(getToken_URL, POST, USER_AGENT, CONTENT_TYPE, RequestBody.getToken());
			String token = Utils.statusCodeParser(result);
			
			requestBody = RequestBody.getVM("t2","f2905719-e1b8-4f0e-9c9d-8260184dda9f");
			result =  RestAPI.request(getVM_URL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			response = Utils.statusCodeParser(result);
			String VM_ID = Utils.VMCreateResponseParser(response);
			
			
			requestBody = RequestBody.getVolume("t2", "69dfe9a7-75ee-41aa-ac3a-ab054006d49d");
			result = RestAPI.request(getVolume_URL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			response = Utils.statusCodeParser(result);
			String volumeID = Utils.volumeCreateResponseParser(response);
			
			result = RestAPI.request(getIP_URL, POST, token);
			response = Utils.statusCodeParser(result);
			
			requestBody= RequestBody.connectVMAndVolume(volumeID);
			result = RestAPI.request(connectVMAndVolume_URL+VM_ID+"/os-volume_attachments", POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			response = Utils.statusCodeParser(result);
			
			
			
			//requestBody = RequestBody.setStaticNat("172.25.1.167", "71655962-3e67-42d6-a17d-6ab61a435dfe", "5c50c8f9-03a5-4e15-a4ec-d2b3202927b9");
			//RestAPI.request(setStaticNAT, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			
			//RestAPI.request(staticNATList, GET, token);
			
			
			//RestAPI.request(closeFirewall, DELETE, token);
			//RestAPI.request(deleteIP, DELETE, token, USER_AGENT, CONTENT_TYPE);
			
			/*
			   String staticNAT_ID="c0ba66f7-f86c-4864-9436-3ee093665405";
			requestBody =  RequestBody.openFirewall("0", "65535", staticNAT_ID, "6b812762-c6bc-4a6d-affb-c469af1b4342", 
					"172.25.1.1/24", "TCP", "71655962-3e67-42d6-a17d-6ab61a435dfe");
			RestAPI.request(openFirewall, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			*/
			 
			/*
			 * delete
			 //String VM_ID = "b275cf23-6cce-4fc3-bafd-389c32f00cd7";
			 //RestAPI.request(forceDeleteVMURL, POST, token, USER_AGENT, CONTENT_TYPE, RequestBody.forceDeleteVM(), VM_ID);
			 */
			
			/*
			 // initialize 
			list = RestAPI.request(staticNATList_URL, GET, token);
			Initialization.deleteAllStaticNAT(list, token);
			
			list = RestAPI.request(IPList_URL, GET, token);
			Initialization.deleteAllIP(list, token);
			
		     //list = RestAPI.request(VMList_URL, GET, token);
			 //Initialization.deleteAllVM(list, token);
		     
			//list = RestAPI.request(firewall_List_URL, GET, token);
			//Initialization.closeAllFirewall(list, token);
			*/
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}