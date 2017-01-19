package com.reachtoday.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.CustomLocalStorage;
import com.reachtoday.client.ioc.ClientGinjector;
import com.reachtoday.client.ioc.GinjectorProvider;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class reachtoday implements EntryPoint {
	String baseUrl=null;
	public static final ClientGinjector ginjector = ((GinjectorProvider) GWT.create(GinjectorProvider.class)).get();
	ActionServletURL actionServletURL;
	/**
	 * This is the entry point method.
	 * @return 
	 */

	/**
	 * This method is to hide the refresh message visible on page load 
	 * 
	 */
	public static native String afterLoad() /*-{
	$doc.getElementById("refreshMessage").style.zIndex="-50000";
	}-*/;
		
	
	
	public void onModuleLoad() {
		afterLoad();
		ginjector.getCSSInjector().injectCSS();
		CustomLocalStorage.patientprotalStorageMap.clear();
		actionServletURL=ginjector.getActionServletURL();

		//Setting the redirect servlet url for AJAX request
//		baseUrl="http://192.168.2.195/ReachToday";
//		baseUrl="http://192.168.2.244/ReachToday";
		baseUrl="http://localhost/ReachToday";
//		baseUrl="https://anhk-513workouts.rhcloud.com";
//		baseUrl = Window.Location.getProtocol()+"//"+Window.Location.getHost()+"/ReachToday";
		
		
		
			/*if(Window.Location.getParameter("contextPath") == "" || Window.Location.getParameter("contextPath") == null){
//				baseUrl="http://localhost";
				Window.Location.replace("/login.html");
			}else{
				
				baseUrl = Window.Location.getProtocol()+"//"+Window.Location.getHost()+"/"+Window.Location.getParameter("contextPath");
				Defaults.setServiceRoot(Window.Location.getProtocol()+"//"+Window.Location.getHost()+"/"+"template_backend");
			}*/
			
		
 			actionServletURL.setBaseURLGlace(baseUrl);
			actionServletURL.setBaseURL(baseUrl);			
			ginjector.getMainPresenter().go(RootLayoutPanel.get());

			// Goes to place represented on URL or default place
			ginjector.getPlaceHistoryHandler().handleCurrentHistory();
		
	}
}