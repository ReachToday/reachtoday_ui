package com.reachtoday.client.application.livetracking;


import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.CustomRequest;
import com.reachtoday.client.application.main.CustomRequestBuilder;
import com.reachtoday.client.application.main.CustomRequestCallback;
import com.reachtoday.client.application.main.CustomResponse;
import com.reachtoday.client.application.searchcontrol.NavigationEvent;
import com.reachtoday.client.application.searchcontrol.NavigationEventHandler;
import com.reachtoday.client.application.searchcontrol.SearchEvent;
import com.reachtoday.client.application.searchcontrol.SearchEventHandler;
import com.reachtoday.client.application.searchcontrol.SearchResult;
import com.reachtoday.client.application.searchcontrol.SearchResultData;
import com.reachtoday.client.application.searchcontrol.SearchResultSelectionEvent;
import com.reachtoday.client.application.searchcontrol.SearchResultSelectionEventHandler;

public class LivetrackingActivity extends AbstractActivity implements LivetrackingView.Presenter{
	
	private LivetrackingView view; 
	private String token;
	private String JSON_URL;
	private PlaceController controller;
	private int offset = 1;
	private static int totalCount = 0;
	private static int totalPages = 0;
	String params="";
	String url = null;
	@Inject
	ActionServletURL actionServletURL; 
	
	@Inject
	public LivetrackingActivity(LivetrackingView view,PlaceController controller,ActionServletURL actionServletURL)
	{
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();
		this.view=view;
		this.view.setPresenter(this);
		this.controller = controller;
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		bind();
	}
	
	public LivetrackingActivity withPlace(LivetrackingPlace place) {
		  token = place.getToken();
		  return this;
	  }
	@Override
	public void bind() {

		
		view.getaddBusSearchControl(new SearchEventHandler() {
			
			@Override
			public void onSearch(SearchEvent event) {
				fireSearchEvent();
			}
		});
		view.getaddBusSearchControl(new NavigationEventHandler() {
			
			@Override
			public void onNavigation(NavigationEvent event) {
				fireNavigationEvent();
				
			}
		});
		view.getaddBusSearchControl(new SearchResultSelectionEventHandler() {
			
			@Override
			public void onSelect(SearchResultSelectionEvent event) {
				fireSearchResultSelectionEvent(event);
			}
		});
	
		
	}
	@Override
	public void searchPatient(String params, int offset, int limit) {
		
		
		String url=JSON_URL+"/ListBuses?tablename="+params+"&mylat=13.092632&mylng=80.204120";
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if(response.getStatusCode()==200)
					{
						if(response.getText()!=null)
						onsucces(response.getText());
					}
					
				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}});
		} catch (RequestException e) {
			
		}
		
	}
 ArrayList<SearchResult> list;
	
	public void onsucces(String json)
	{
//        Window.alert(">>>"+json);
         list = new ArrayList<SearchResult>();
        JSONValue value = JSONParser.parseStrict(json);
        
        	JSONArray arr = value.isArray();
                view.getPatientSearch().setTotalCount(20);
                for (int i = 0; i < arr.size(); i++) {
                	JSONObject jsonobj = arr.get(i).isObject();
        			JSONValue jsonValue1 = jsonobj.get("towards");
        			JSONValue jsonValue2 = jsonobj.get("at");
        			 String suggestion ="test114";
        			
        			if(jsonValue1.toString().trim()!="\"dontknow\""){
        				suggestion=  "test114, " + "at->" +jsonValue2.toString().split(",")[0]+", towards->"+jsonValue1;
        			}else
        			{
        				suggestion=  "test114," + "at->" +jsonValue2.toString().split(",")[0].replaceAll("\"", " ");
        			}
        			
                    SearchResultData processingData = new SearchResultData("IMEI,location", "9003115833,"+jsonValue2.toString().split(",")[0].replaceAll("\"", " "));
                    SearchResult processing = new SearchResult("", processingData, suggestion);
                    list.add(processing);
                }
                view.getPatientSearch().displayResult(list);

            
    
	}
	/**
	 * Start :Setters and Getter methods for the class level variables
	 */
	public int getOffset() {
		return offset;
	}
	public static int getTotalCount() {
		return totalCount;
	}
	public static int getTotalPages() {
		return totalPages;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public static void setTotalCount(int totalCount) {
		LivetrackingActivity.totalCount = totalCount;
	}
	public static void setTotalPages(int totalPages) {
		LivetrackingActivity.totalPages = totalPages;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * Executed when the Search Event is fired 
	 */
	protected void fireSearchEvent()
	{
		setOffset(1);
		setTotalCount(0);
		setParams(view.getPatientSearch().getSearchKeyWord());
		if (!getParams().equals("")){	
			searchPatient(getParams(), 1, view.getPatientSearch().getLimit());
		}
	}
	
	/**
	 * Executed when the Navigation Event is fired
	 */
	protected void fireNavigationEvent()
	{/*
		setParams(view.getPatientSearch().getSearchKeyWord());
		offset = offset + 1;
		if (offset <= totalPages) {
			searchPatient(params, offset, view.getPatientSearch().getLimit());
		}
	*/}
	
	/**
	 * Executed when the SearchResultSelection Event is fired
	 */
	protected void fireSearchResultSelectionEvent(SearchResultSelectionEvent event)
	{
		Window.alert("Please switch on Location to see Maps");
		String IMEIandlocation = event.getSelectedValue().getValue();
		view.displaymap(IMEIandlocation.split(",")[0],IMEIandlocation.split(",")[1],getParams());
		
	}
	
}
