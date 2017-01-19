package com.reachtoday.client.application.main;



import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;

public interface MainView {
	
	public interface Presenter {

		void goTo(Place place);
		void drawHeader();
		
	}
	  Widget asWidget();
	  void setPresenter(Presenter presenter);
	 public void showhidemenuleft();
	 public void displayUsername(String practicename,String username);
	 
}
