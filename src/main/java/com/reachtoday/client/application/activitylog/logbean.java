package com.reachtoday.client.application.activitylog;

public class logbean {
	
	public static int module=-1;
	public static int entity=-1;
	public static void clearmodule() {
		module=-1;
		entity=-1;
	}

	public static void logmoduleevent(int moduleID,int entityID) {
		module=moduleID;
		entity=entityID;
	}
	
	
}
