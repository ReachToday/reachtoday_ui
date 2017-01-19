package com.reachtoday.client.application.friendfinder;

import com.google.gwt.user.client.ui.Widget;


public interface FrienfinderView  {
	
	Widget asWidget();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		
    }
	
	
}
