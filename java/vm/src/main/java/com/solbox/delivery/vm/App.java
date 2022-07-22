package com.solbox.delivery.vm;

public class App {

	public static void main(String[] args) {
		try {
			
			/*
			 * while(true) { KTCloudOpenAPI.createServer("test", "test"); }
			 */ 
			//KTCloudOpenAPI.createServer("test", "test");
			KTCloudOpenAPI.deleteServer("6533a4cd-0be8-42d9-a019-af2236833fbf");
			// KTCloudOpenAPI.init();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}