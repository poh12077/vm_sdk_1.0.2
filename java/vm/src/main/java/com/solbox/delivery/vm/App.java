package com.solbox.delivery.vm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
	private static final String getTokenURL = "https://api.ucloudbiz.olleh.com/d1/identity/auth/tokens";
	private static final String getVMURL = "https://api.ucloudbiz.olleh.com/d1/server/servers";
	private static final String forceDeleteVMURL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";
	private static final String getVolumeURL = "https://api.ucloudbiz.olleh.com/d1/volume/c3d6ced17b1d45d98af60cbeff8f4bbd/volumes";
	private static final String connectVMAndVolumeURL = "https://api.ucloudbiz.olleh.com/d1/server/servers/8afe175e-73dd-4f89-a02e-128559e670a4/os-volume_attachments";
	private static final String getIP = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress";
	private static final String deleteIP = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress/5c50c8f9-03a5-4e15-a4ec-d2b3202927b9";
	private static final String setStaticNAT = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	private static final String openFirewall = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	private static final String firewall_List = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	private static final String closeFirewall = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall/30";
	
	private static final String staticNATList = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	
	private static final String GET = "GET";
	private static final String DELETE = "DELETE";
	private static final String POST = "POST";
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String CONTENT_TYPE = "application/json";
	private static String requestBody;
	
	public static void main(String[] args) {
		try {
			String token = Utils.getToken(getTokenURL, POST, USER_AGENT, CONTENT_TYPE, RequestBody.getToken());

			//requestBody = RequestBody.getVM("nanasolrtmp2","f2905719-e1b8-4f0e-9c9d-8260184dda9f");
			//Utils.request(getVMURL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			
			 String VM_ID = "b275cf23-6cce-4fc3-bafd-389c32f00cd7";
			 Utils.request(forceDeleteVMURL, POST, token, USER_AGENT, CONTENT_TYPE, RequestBody.forceDeleteVM(), VM_ID);
			
			//requestBody = RequestBody.getVolume("0325", "e95dc0cc-260a-468b-939e-52cfb6cc4f7b");
		//	Utils.request(getVolumeURL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
		 
			//requestBody= RequestBody.connectVMAndVolume("f054f378-7e61-4f56-bac0-88e7123607f2");
			//Utils.request(connectVMAndVolumeURL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody );
			
			//Utils.request(getIP, POST, token);
			
			//requestBody = RequestBody.setStaticNat("172.25.1.167", "71655962-3e67-42d6-a17d-6ab61a435dfe", "5c50c8f9-03a5-4e15-a4ec-d2b3202927b9");
			//Utils.request(setStaticNAT, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			
			//Utils.request(staticNATList, GET, token);
			//Utils.request(firewall_List, GET, token);
			//Utils.request(closeFirewall, DELETE, token);
			//Utils.request(deleteIP, DELETE, token, USER_AGENT, CONTENT_TYPE);
			
			/*
			   String staticNAT_ID="c0ba66f7-f86c-4864-9436-3ee093665405";
			requestBody =  RequestBody.openFirewall("0", "65535", staticNAT_ID, "6b812762-c6bc-4a6d-affb-c469af1b4342", 
					"172.25.1.1/24", "TCP", "71655962-3e67-42d6-a17d-6ab61a435dfe");
			Utils.request(openFirewall, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			*/
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}