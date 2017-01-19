package com.reachtoday.client.util;

import com.google.gwt.core.client.Callback;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;

public class UserLocationDetails {
	@Inject
	ActionServletURL actionServletURL; 
	private String JSON_URL;
	@Inject
	public UserLocationDetails(ActionServletURL actionServletURL)
	{
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();

	}

	/*Save the current location details*/
	public void sendLocationDetails(boolean isLoggedin)
	{
		
		Timer timer=new Timer() {
			
			@Override
			public void run() {
				
				forwardLocationDeatils();
			}
		};
		
		forwardLocationDeatils();
		
		if(isLoggedin)
			timer.scheduleRepeating(30000);
		else
			timer.cancel();
			
	}
	
	
	private void forwardLocationDeatils(){
		
		Geolocation.getIfSupported().getCurrentPosition(new Callback<Position, PositionError>() {

			@Override
			public void onFailure(PositionError error) {

				String message = "";
				switch (error.getCode()) {
				case PositionError.UNKNOWN_ERROR:
					message = "Unknown Error";
					break;
				case PositionError.PERMISSION_DENIED:
					message = "Permission Denied";
					break;
				case PositionError.POSITION_UNAVAILABLE:
					message = "Position Unavailable";
					break;
				case PositionError.TIMEOUT:
					message = "Time-out";
					break;
				default:
					message = "Unknown error code.";
				}
				
				Window.alert("Current Position: "+message);
				
			}

			@Override
			public void onSuccess(Position result) {
				insertLocationDetails(Double.toString(result.getCoordinates().getLatitude()),Double.toString(result.getCoordinates().getLongitude()));

			}
		});
		
		/*Geolocation.getIfSupported().watchPosition(new Callback<Position, PositionError>() {

		@Override
		public void onFailure(PositionError error) {
			String message = "";
			switch (error.getCode()) {
			case PositionError.UNKNOWN_ERROR:
				message = "Unknown Error";
				break;
			case PositionError.PERMISSION_DENIED:
				message = "Permission Denied";
				break;
			case PositionError.POSITION_UNAVAILABLE:
				message = "Position Unavailable";
				break;
			case PositionError.TIMEOUT:
				message = "Time-out";
				break;
			default:
				message = "Unknown error code.";
			}
			Window.alert("Watch Position: "+message);
		}

		@Override
		public void onSuccess(Position result) {
			insertLocationDetails(Double.toString(result.getCoordinates().getLatitude()),Double.toString(result.getCoordinates().getLongitude()));
		}
	});*/

	
	}


	private void insertLocationDetails(String lat,String lng)
	{
		if(!MainMobileView.emailid.trim().equalsIgnoreCase("")){


			String url=JSON_URL+"/UserLocation?userlat="+lat+"&userlng="+lng+"&usermailid="+MainMobileView.emailid;
			
			if(MainMobileView.emailid==null||MainMobileView.emailid.trim().length()<=0){
				Window.alert("Your eamild is not valid. Please update your email");
				return;
			}

			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			try {
				builder.sendRequest(null, new RequestCallback() {

					@Override
					public void onResponseReceived(Request request,
							Response response) {
						if(response.getStatusCode()==200)
						{



						}

					}

					@Override
					public void onError(Request request, Throwable exception) {
						// TODO Auto-generated method stub

					}});
			} catch (RequestException e) {

			}
		}
	}

}
