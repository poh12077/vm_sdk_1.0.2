package com.solbox.delivery.vm;

public class App {

	public static void main(String[] args) {
		try {
			/*
			 * while(true) { KTCloudOpenAPI.createServer("test", "test"); }
			 */
			
			KTCloudOpenAPI.createServer("test", "test");
			// KTCloudOpenAPI.init();
			//RestAPI.sendGet();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}