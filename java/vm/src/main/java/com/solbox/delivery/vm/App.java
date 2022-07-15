package com.solbox.delivery.vm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
	 static final String getToken_URL = "https://api.ucloudbiz.olleh.com/d1/identity/auth/tokens";
	 static final String getVM_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers";
	 static final String forceDeleteVM_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";
	 static final String getVolume_URL = "https://api.ucloudbiz.olleh.com/d1/volume/c3d6ced17b1d45d98af60cbeff8f4bbd/volumes";
	 static final String connectVMAndVolume_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/8afe175e-73dd-4f89-a02e-128559e670a4/os-volume_attachments";
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
			String token = Utils.getToken(getToken_URL, POST, USER_AGENT, CONTENT_TYPE, RequestBody.getToken());

			//requestBody = RequestBody.getVM("solrtmp00","59a2e83b-a879-4528-958e-7c46e241040b");
			//Utils.request(getVM_URL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			
			list = Utils.request(staticNATList_URL, GET, token);
			Initialization.deleteAllStaticNAT(list, token);
		    
			
			list = Utils.request(IPList_URL, GET, token);
			Initialization.deleteAllIP(list, token);
		     
			
		     //list = Utils.request(VMList_URL, GET, token);
			 //Initialization.deleteAllVM(list, token);
		     
			 //String VM_ID = "b275cf23-6cce-4fc3-bafd-389c32f00cd7";
			 //Utils.request(forceDeleteVMURL, POST, token, USER_AGENT, CONTENT_TYPE, RequestBody.forceDeleteVM(), VM_ID);
			
			//requestBody = RequestBody.getVolume("0325", "e95dc0cc-260a-468b-939e-52cfb6cc4f7b");
			//Utils.request(getVolumeURL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
		 
			//requestBody= RequestBody.connectVMAndVolume("f054f378-7e61-4f56-bac0-88e7123607f2");
			//Utils.request(connectVMAndVolumeURL, POST, token, USER_AGENT, CONTENT_TYPE, requestBody );
			
			//Utils.request(getIPURL, POST, token);
			
			//requestBody = RequestBody.setStaticNat("172.25.1.167", "71655962-3e67-42d6-a17d-6ab61a435dfe", "5c50c8f9-03a5-4e15-a4ec-d2b3202927b9");
			//Utils.request(setStaticNAT, POST, token, USER_AGENT, CONTENT_TYPE, requestBody);
			
			//Utils.request(staticNATList, GET, token);
			
			//list = Utils.request(firewall_List_URL, GET, token);
			//Initialization.closeAllFirewall(list, token);
			
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