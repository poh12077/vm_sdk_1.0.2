package com.solbox.delivery.vm;


public class App {

	public static void main(String[] args) {
		try {

			 ServerInformation serverInformation = KTCloudOpenAPI.createServer("test", "test");
			KTCloudOpenAPI.deleteServer(serverInformation);
			// KTCloudOpenAPI.init();
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}