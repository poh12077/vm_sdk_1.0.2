package com.solbox.delivery.vm;

public class KTCloudOpenAPI {

	static final String getVm_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers";
	static final String forceDeleteVm_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";
	static final String VmList_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/detail";
	static final String VmDetail_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";

	static final String getVolume_URL = "https://api.ucloudbiz.olleh.com/d1/volume/";
	static final String connectVmAndVolume_URL = "https://api.ucloudbiz.olleh.com/d1/server/servers/";

	static final String getIP_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress";
	static final String deleteIP_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress/";
	static final String IPList_URL = "https://api.ucloudbiz.olleh.com/d1/nc/IpAddress";

	static final String setStaticNAT_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	static final String staticNATList_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat";
	static final String DeleteStaticNAT_URL = "https://api.ucloudbiz.olleh.com/d1/nc/StaticNat/";

	static final String openFirewall_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	static final String firewall_List_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall";
	static final String closeFirewall_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Firewall/";

	static final String getToken_URL = "https://api.ucloudbiz.olleh.com/d1/identity/auth/tokens";
	static final String jobID_URL = "https://api.ucloudbiz.olleh.com/d1/nc/Etc?command=queryAsyncJob&jobid=";

	static final String GET = "GET";
	static final String DELETE = "DELETE";
	static final String POST = "POST";
	static final int timeout = 10; //sec

	static ServerInformation createServer(String serverName, String volumeName) throws Exception {

		String requestBody;
		String result;
		String response;
		String VmImage_complete1 = "03a6328b-76c8-4d15-8e3f-d5cae5cf1156";
		String VmImage_nginx = "fab16e16-5d53-4e00-892f-bec4b10079bb";
		// token
		result = RestAPI.request(getToken_URL, POST, RequestBody.getToken());
		// result = RestAPI.post(getToken_URL, RequestBody.getToken(), 10);
		String token = Utils.statusCodeParser(result);
		String projectID = Utils.getProjectID(result);

		// get vm
		String VmimageID = VmImage_complete1;
		String specs = "61c68bc1-3a56-4827-9fd1-6a7929362bf6";
		requestBody = RequestBody.getVm(serverName, VmimageID, specs);
		// result = RestAPI.request(getVm_URL, POST, token, requestBody);
		result = RestAPI.post(getVm_URL, token, requestBody, 10);
		response = Utils.statusCodeParser(result);
		String VmId = Utils.VmCreateResponseParser(response);

		// get volume
		String volumeImageID = "556aacd2-de16-47fc-b230-3db3a55be50d";
		requestBody = RequestBody.getVolume(volumeName, volumeImageID);
		result = RestAPI.request(getVolume_URL + projectID + "/volumes", POST, token, requestBody);
		response = Utils.statusCodeParser(result);
		String volumeID = Utils.volumeCreateResponseParser(response);

		// get public ip
		result = RestAPI.request(getIP_URL, POST, token, "");
		response = Utils.statusCodeParser(result);
		String jobID = Utils.IPCreateResponseParser(response);

		// result = RestAPI.request(jobID_URL + jobID, GET, token, "");
		result = RestAPI.get(jobID_URL + jobID, token, 10);
		response = Utils.statusCodeParser(result);
		String publicIP_ID = Utils.jobIDLookupResponseParser(response);

		// connect vm and volume
		int count = 0;
		while (true) {
			requestBody = RequestBody.connectVmAndVolume(volumeID);
			result = RestAPI.request(connectVmAndVolume_URL + VmId + "/os-volume_attachments", POST, token, requestBody);
			response = Utils.statusCodeParser(result);
			if (response.length() > 0) {
				break;
			}
			Thread.sleep(1000);
			count++;
			System.out.println(count);
		}
		// System.out.println(count);

		// look up vm ip
		result = RestAPI.request(VmDetail_URL + VmId, GET, token, "");
		response = Utils.statusCodeParser(result);
		String privateIP = Utils.VmDetailResponseParser(response);

		// set static NAT
		String networkID = "71655962-3e67-42d6-a17d-6ab61a435dfe";
		requestBody = RequestBody.setStaticNat(privateIP, networkID, publicIP_ID);
		result = RestAPI.request(setStaticNAT_URL, POST, token, requestBody);
		response = Utils.statusCodeParser(result);
		String staticNAT_ID = Utils.staticNATSettingResponseParser(response);

		// open firewall
		requestBody = RequestBody.openFirewall("0", "65535", staticNAT_ID, "6b812762-c6bc-4a6d-affb-c469af1b4342",
				"172.25.1.1/24", "ALL", "71655962-3e67-42d6-a17d-6ab61a435dfe");
		result = RestAPI.request(openFirewall_URL, POST, token, requestBody);
		response = Utils.statusCodeParser(result);

		System.out.println("server creation is done");
		
		RestAPI.get("https://api.ucloudbiz.olleh.com/d1/server/servers/"+ VmId, token, 10);
		
		ServerInformation serverInformation = new ServerInformation(VmId, volumeID, publicIP_ID, staticNAT_ID, projectID);
		Database.write(serverInformation);
		return serverInformation;
		
	}

	static void deleteServer(String vmId) throws Exception {
		
		ServerInformation serverInformation = Database.read(vmId);
		
		String result="";
		// token
		result = RestAPI.post(getToken_URL, RequestBody.getToken(), 10);
		String token = Utils.statusCodeParser(result);

		Utils.deleteVmOnly(serverInformation.getVmId(), token, timeout);
		
		
	}

	static void init() throws Exception {
		String result;
		String response;
		result = RestAPI.request(getToken_URL, POST, RequestBody.getToken());
		String token = Utils.statusCodeParser(result);

		// close firewall
		result = RestAPI.request(firewall_List_URL, GET, token, "");
		response = Utils.statusCodeParser(result);
		Initialization.closeAllFirewall(response, token);

		// unlock static NAT
		result = RestAPI.request(staticNATList_URL, GET, token, "");
		response = Utils.statusCodeParser(result);
		Initialization.deleteAllStaticNAT(response, token);

		// delete ip
		result = RestAPI.request(IPList_URL, GET, token, "");
		response = Utils.statusCodeParser(result);
		Initialization.deleteAllIP(response, token);

		// delete vm
		result = RestAPI.request(VmList_URL, GET, token, "");
		response = Utils.statusCodeParser(result);
		Initialization.deleteAllVm(response, token);

		System.out.println("initialization is done");
	}

	static void lookup() throws Exception {
	}

}
