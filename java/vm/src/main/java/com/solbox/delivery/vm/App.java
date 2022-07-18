package com.solbox.delivery.vm;


public class App {
	
	public static void main(String[] args) {
		try {
			
			KTCloudOpenAPI.createServer("test", "test");
			
			//KTCloudOpenAPI.init();
			
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}