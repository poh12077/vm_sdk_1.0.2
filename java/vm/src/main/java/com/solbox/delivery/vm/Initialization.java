package com.solbox.delivery.vm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Initialization {

	static void deleteAllVM(String list, String token) throws Exception {
		
		JSONObject fianlJsonObject = new JSONObject(list);
		JSONArray servers = fianlJsonObject.getJSONArray("servers");
		
		for(int i=0; i<servers.length();i++ ) {
			JSONObject VM = servers.getJSONObject(i);
			String VM_ID = VM.getString("id");
			Utils.requestForceDeleteVM(App.forceDeleteVM_URL, App.POST, token, App.USER_AGENT, App.CONTENT_TYPE, RequestBody.forceDeleteVM(), VM_ID);
		}			
	}
	
static void closeAllFirewall(String list, String token) throws Exception {
		
		JSONObject fianlJsonObject = new JSONObject(list);
		JSONObject nc_listfirewallrulesresponse = fianlJsonObject.getJSONObject("nc_listfirewallrulesresponse");
		JSONArray firewallrules = nc_listfirewallrulesresponse.getJSONArray("firewallrules");
		
		for(int i=0; i< firewallrules.length();i++ ) {
			JSONObject jsonObject0 = firewallrules.getJSONObject(i);
			JSONArray acls = jsonObject0.getJSONArray("acls");
			for(int j=0; j< acls.length(); j++ ) {
				JSONObject firewalls = acls.getJSONObject(j);
				int firewall_ID = firewalls.getInt("id");
				Utils.request(App.closeFirewall_URL, App.DELETE, token, Integer.toString(firewall_ID) );
			}
		}
					
	}

}