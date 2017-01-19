package com.reachtoday.client.application.main;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.reachtoday.client.reachtoday;
import com.reachtoday.client.application.main.events.HideProgressEvent;
import com.reachtoday.client.application.main.events.ShowProgressEvent;

/**
 * Wrapper class for the RequestBuilder
 * 
 * @author Debasmita
 */
public class CustomRequestBuilder extends RequestBuilder {

	private String displayMessage = "Loading";
	private String displayMessage1;
	private String displayMessage2;
	private String displayMessage3;
	private String displayMessage4;
	private int timeOut = 60*1000;
	private CustomResponse customResponse = new CustomResponse();
	private CustomRequest request1;
	private CustomRequestCallback callback1;
	private int flag;
	private int isBackgroundTask = 0;
	private EventBus eventBus = reachtoday.ginjector.getEventBus();
	static int requestCount=0; 
	static String commonMessage="Loading";
	
	/**
	 * Parameterized Constructor 1
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 */
	public CustomRequestBuilder(Method httpMethod, String url) {
		super(httpMethod, url);
		this.setTimeoutMillis(timeOut);
	}
	

	/**
	 * Parameterized Constructor 2
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 */

	public CustomRequestBuilder(String httpMethod, String url) {
		super(httpMethod, url);
		this.setTimeoutMillis(timeOut);
	}
	

	/**
	 * Parameterized Constructor 3
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param message
	 *            Message that has to be displayed instead of the default label
	 */

	public CustomRequestBuilder(Method httpMethod, String url, String message) {
		super(httpMethod, url);
		this.displayMessage = message;
		this.setTimeoutMillis(timeOut);
	}

	
	/**
	 * Parameterized Constructor 4
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param message
	 *            Message that has to be displayed instead of the default label
	 */
	public CustomRequestBuilder(String httpMethod, String url, String message) {
		super(httpMethod, url);
		this.displayMessage = message;
		this.setTimeoutMillis(timeOut);
	}

	
	/**
	 * Parameterized Constructor 5
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param isBackgroundTask
	 *            Specified as true for background task AJAX request
	 */
	public CustomRequestBuilder(Method httpMethod, String url,
			int isBackgroundTask) {
		super(httpMethod, url);
		this.isBackgroundTask = isBackgroundTask;
		this.setTimeoutMillis(timeOut);
	}

	
	/**
	 * Parameterized Constructor 6
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param isBackgroundTask
	 *            Specified as true for background task AJAX request
	 */

	public CustomRequestBuilder(String httpMethod, String url,
			int isBackgroundTask) {
		super(httpMethod, url);
		this.isBackgroundTask = isBackgroundTask;
		this.setTimeoutMillis(timeOut);
	}

	
	/**
	 * Parameterized Constructor 7
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param message
	 *            Message that has to be displayed instead of the default label
	 * @param isBackgroundTask
	 *            Specified as true for background task AJAX request
	 */

	public CustomRequestBuilder(Method httpMethod, String url, String message,
			int isBackgroundTask) {
		super(httpMethod, url);
		this.displayMessage = message;
		this.isBackgroundTask = isBackgroundTask;
		this.setTimeoutMillis(timeOut);
	}

	
	/**
	 * Parameterized Constructor 8
	 * 
	 * @param httpMethod
	 *            HTTP method to use for the request
	 * @param url
	 *            URL that has already been encoded
	 * @param message
	 *            Message that has to be displayed instead of the default label
	 * @param isBackgroundTask
	 *            Specified as true for background task AJAX request
	 */
	public CustomRequestBuilder(String httpMethod, String url, String message,
			int isBackgroundTask) {
		super(httpMethod, url);
		this.displayMessage = message;
		this.isBackgroundTask = isBackgroundTask;
		this.setTimeoutMillis(timeOut);
	}
	

	/**
	 * setTimeoutMillis() method is overridden to allow the developers to set
	 * the timeOut externally
	 */
	@Override
	public void setTimeoutMillis(int timeoutMillis) {
		super.setTimeoutMillis(timeoutMillis);
	}

	
	/**
	 * sendRequest() method is overridden to add extra functionalities to the
	 * predefined sendRequest() method
	 */
	public Request sendRequest(String requestData,
			final CustomRequestCallback callback) throws RequestException {
		/**
		 * ShowProgressEvent constructor has two parameters
		 * 
		 * @param displayMessage
		 *            --> refers to the Label to be displayed
		 * @param flag
		 *            Sets the flag based on whose value the functions are carried out
		 */
		flag = 0;
		if (isBackgroundTask==0||isBackgroundTask==2)
		{
			if(isBackgroundTask==2){
				flag=2;
			}
			eventBus.fireEvent(new ShowProgressEvent(displayMessage, flag));
		}	
		if(isBackgroundTask==3){
			flag=0;
			this.commonMessage=displayMessage;
			requestCount++;
			if(requestCount>0)
				eventBus.fireEvent(new ShowProgressEvent(commonMessage, flag));
		}
		return super.sendRequest(requestData, new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				customResponse.setResponse(response);
				request = new CustomRequest();
				request1 = (CustomRequest) request;
				callback1 = callback;
				if(isBackgroundTask==3)
					requestCount--;
				checkResponseCode();
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				/**
				 * This onError() is called when session is timedOut
				 */
				if(isBackgroundTask==3)
					requestCount--;
				displayMessage2 = "ERROR:  " + exception.getMessage();
				flag = 1;
				eventBus.fireEvent(new ShowProgressEvent(displayMessage2, flag));
				callback.onError(request1, exception);
			}
		});
	}

	
	public void checkResponseCode() {
		if (customResponse.getStatusCode() >= 200
				&& customResponse.getStatusCode() < 300) {
			/**
			 * If response status code lies between 200 and 300 it is considered as
			 * a successful response and value of "Login" key is checked
			 */
			checkLoginValue();
		}
		else {
			/**
			 * Else the onError() method is called
			 * 
			 * This onError() is called when HTTP errors occur.
			 */
			
			if(customResponse.getStatusCode()==0)
			{
				checkLoginValue();
			}
			else{
			displayMessage1 = "ERROR: " + customResponse.getStatusCode() + ":"
					+ customResponse.getStatusText();
			flag = 1;
			eventBus.fireEvent(new ShowProgressEvent(displayMessage1, flag));
			Exception e = null;
			callback1.onError(request1, e);
			}
		}
	}
	

	public void checkLoginValue() {
		if (customResponse.getLoginValue()) {
			/**
			 * If the value of the "Login" key is "True" then the value of "Success"
			 * key is checked
			 */
			checkSuccessValue();
		}
		
		else {
			/**
			 * Else it is redirected to the login.html page prompting the user to
			 * login again (if session doesnot exist it will be redirected to login page
			 */
			Window.Location.replace("/login.html?sx=1");
		}
	}
	

	public void checkSuccessValue() {
		
		if (customResponse.getSuccessValue()) {
			/**
			 * If the value of the "Success" key is "True" then the value of "isAuthorizationPresent"
			 * key is checked
			 */
			checkIsAuthorizationPresentValue();
		}
		
		else {
			/**
			 * Else the onError() is called and the corresponding error label is displayed
			 */
			displayMessage3 = "ERROR: The application error with the errorcode "
					+ customResponse.getErrorCode().toString() + " has occured.";
			flag = 1;
			eventBus.fireEvent(new ShowProgressEvent(displayMessage3, flag));
			Exception e = null;
			callback1.onError(request1, e);

		}
	}
	
	
	public void checkIsAuthorizationPresentValue()
	{
		if(customResponse.getIsAuthorizationPresent())
		{
			/**
			 * If the value of the "isAuthorizationPresent" key is "True" then the value of "canUserAccess"
			 * key is checked
			 */
			checkCanUserAccessValue();
		}
		else
		{
			/**
			 * Else the onResponseReceived() is executed and the "Loading" label is hidden
			 */
			if(isBackgroundTask==0||isBackgroundTask==2)
			{
				if(requestCount!=0){
					flag=0;
					eventBus.fireEvent(new ShowProgressEvent(commonMessage, flag));
				}
			eventBus.fireEvent(new HideProgressEvent());
			}
			if(isBackgroundTask==3&&requestCount==0)
				eventBus.fireEvent(new HideProgressEvent());
			callback1.onResponseReceived(request1, customResponse);
			
		}
	}
	
	
	public void checkCanUserAccessValue()
	{
		if(customResponse.getCanUserAccess())
		{
			/**
			 * If the value of the "canUserAccess" key is "True" then
			 * the "Loading" label is hidden and the onResponseReceived() is executed
			 */
			if(isBackgroundTask==0||isBackgroundTask==2)
			{
				if(requestCount!=0){
					flag=0;
					eventBus.fireEvent(new ShowProgressEvent(commonMessage, flag));
				}
			eventBus.fireEvent(new HideProgressEvent());
			}
			if(isBackgroundTask==3&&requestCount==0)
				eventBus.fireEvent(new HideProgressEvent());
			callback1.onResponseReceived(request1, customResponse);
			
			
		}
		else
		{
			/**
			 * Else the onError() is called and the corresponding error label is displayed
			 */
			displayMessage4 = "Sorry, you aren't authorized to access this page.";
			flag = 1;
			eventBus.fireEvent(new ShowProgressEvent(displayMessage4, flag));
		}
	}
}
