package com.reachtoday.client.application.main;


import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface Presenter {
	   void go(final HasWidgets container);
	   void bind();
	   Widget getWidget();
	   void stop();
	   
	   /* 
	    String mayStop();
	    
	    void start(EventBus eventBus);

	    void stop();*/
	
	}
