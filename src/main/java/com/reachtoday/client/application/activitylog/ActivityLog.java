package com.reachtoday.client.application.activitylog;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.ActionServletURLImpl;
import com.reachtoday.client.application.main.CustomRequest;
import com.reachtoday.client.application.main.CustomRequestBuilder;
import com.reachtoday.client.application.main.CustomRequestCallback;
import com.reachtoday.client.application.main.CustomResponse;
public class ActivityLog {
	
	static String JSON_URL;
	@Inject
	public ActivityLog(ActionServletURL actionServletURL){
		this.JSON_URL=actionServletURL.getBaseURL();
	}
	/*Logs the event*/
	public  void logEvent(final int patientId,final int encounterId,final int moduleId,final String textMessage,final int entityId,final boolean status){
		
		String url=null;
		url=JSON_URL+"/LogGWTActivity.Action?patientId="+patientId+"&encounterId="+encounterId+"&moduleId="+moduleId+"&textMessage="+textMessage+"&entityId="+entityId+"&status="+status;
		url = URL.encode(url);
		CustomRequestBuilder builder = new CustomRequestBuilder(CustomRequestBuilder.GET, url,1);
		try {
			Request request = builder.sendRequest(null, new CustomRequestCallback() {

				@Override
				public void onResponseReceived(CustomRequest request, CustomResponse response) {

					if (200 == response.getStatusCode()) {
						
					}
					else {
						System.out.println("Request Failed!");
					}
				}

				@Override
				public void onError(CustomRequest request,
						Throwable exception) {
					System.out.println("on error");

				}});
		}catch (RequestException e) {
			
			System.out.println("Log event Failed");
		}
	}
}
