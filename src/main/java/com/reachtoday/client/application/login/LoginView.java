package com.reachtoday.client.application.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.application.main.MainView;

public interface LoginView {

	void setPresenter(Presenter presenter);

	Widget asWidget();
	public interface Presenter {
		void goTo(Place place);
		public MainView getmainMobileView();
	}

}
