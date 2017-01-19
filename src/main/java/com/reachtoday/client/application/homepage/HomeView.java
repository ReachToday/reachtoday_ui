package com.reachtoday.client.application.homepage;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;


public interface HomeView  {
	
	Widget asWidget();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		void goTo(Place place);
    }
	
	
}
