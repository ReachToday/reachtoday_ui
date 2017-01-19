package com.reachtoday.client.application.livetracking;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.reachtoday.client.application.searchcontrol.SearchControl;
import com.reachtoday.client.application.searchcontrol.SearchControlIf;


public interface LivetrackingView  {
	
	 IsWidget asWidget();
		void setPresenter(Presenter presenter);
		
		public interface Presenter
		{
			public void bind();
			public void searchPatient(String params, final int offset, int limit);	
	    }
		
		public SearchControl getBusSearchControl();
		SearchControl getaddBusSearchControl(EventHandler eventHandler);
		public SearchControlIf getPatientSearch();
		public void displaymap(String IMEI,String location,String busnumber);
	
	
}
