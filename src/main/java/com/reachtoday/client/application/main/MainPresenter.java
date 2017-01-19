package com.reachtoday.client.application.main;

import java.util.HashMap;



import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;


/**
 * @author software
 *
 */
public class MainPresenter implements Presenter , MainView.Presenter{
	

	public static  MainView view;
    private PlaceController placeController;
	private EventBus eventBus;
	private String JSON_URL;
	private int offset = 1;
	private static int totalCount = 0;
	private static int totalPages = 0;
	@Inject
	ActionServletURL actionServletURL;
	@Inject
	public MainPresenter(MainView view,PlaceController placeController,EventBus eventBus,ActionServletURL actionServletURL) {
		this.JSON_URL=actionServletURL.getBaseURL();
		this.actionServletURL=actionServletURL;
		    this.eventBus = eventBus;
 		    this.placeController=placeController;
		    this.view = view;
		    this.view.setPresenter(this);
		  }


	@Override
	public void go(HasWidgets container) {
		 container.clear();
		 container.add(view.asWidget());
		 bind();
		 
	}
	
	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}
	
	
	
	@Override
	public void bind() {

		 
	}
	
	 
	@Override
	public Widget getWidget() {
		return null;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	public void drawheader(String username)
	{
		view.displayUsername("",username);
	}

	 /**
	   * If can't get JSON, display error message.
	   * @param error
	   */
	  private void displayError(String error) {
	   
	  }
	
	public void drawHeader(){
		
		String url = JSON_URL+"mainaction?mode=1";
		url = URL.encode(url);
		
	    // Send request to server and catch any errors.
	    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

	    try {
	      Request request = builder.sendRequest(null, new RequestCallback() {
	        public void onError(Request request, Throwable exception) {
	          displayError("Couldn't retrieve JSON");
	        }

	        public void onResponseReceived(Request request, Response response) {
	          if (200 == response.getStatusCode()) {
	        	Window.alert("response.gettext()"+response.getText());
	      	  
	          } else {
	            displayError("Couldn't retrieve JSON (" + response.getStatusText()
	                + ")");
	          }
	        }
	      });
	    } catch (RequestException e) {
	      displayError("Couldn't retrieve JSON");
	    }
		
	 }
	
}
	
	 
	 
	

