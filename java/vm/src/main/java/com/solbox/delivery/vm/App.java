package com.solbox.delivery.vm;


public class App {

	public static void main(String[] args) {
		try {

			/*
			 * while(true) { KTCloudOpenAPI.createServer("test", "test"); }
			 */
			 //ServerInformation serverInformation = KTCloudOpenAPI.createServer("test", "test");
			String vmId="93bc13c5-5c68-49e4-a23f-5d050dd3062b";
			KTCloudOpenAPI.deleteServer(vmId);
			// KTCloudOpenAPI.init();
			
			//test
			// ServerInformation serverInformation = new ServerInformation("aa", "bb", "", "", "");
			//Database.write(serverInformation);	

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}