package com.reachtoday.client.application.rttemplate;

import com.google.gwt.user.client.ui.Widget;


public interface ReachtodayView  {
	
	Widget asWidget();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		
    }
	
	
}
