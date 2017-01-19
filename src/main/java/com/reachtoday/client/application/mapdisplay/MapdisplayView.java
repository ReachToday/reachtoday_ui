package com.reachtoday.client.application.mapdisplay;

import com.google.gwt.user.client.ui.Widget;


public interface MapdisplayView  {
	
	Widget asWidget();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		
    }
	
	
}
