package com.reachtoday.client.application.main;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
/**
 * Wrapper class for the RequestCallback
 * 
 * @author Debasmita
 */
public abstract class CustomRequestCallback implements RequestCallback{
	
	public void onResponseReceived(Request request, Response response){	}
	public abstract void onResponseReceived(CustomRequest request, CustomResponse response);
	public void onError(Request request, Throwable exception){}
	public abstract void onError(CustomRequest request, Throwable exception);
	
}
