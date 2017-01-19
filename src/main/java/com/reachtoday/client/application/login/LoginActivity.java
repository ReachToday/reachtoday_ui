package com.reachtoday.client.application.login;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainView;

public class LoginActivity extends AbstractActivity implements LoginView.Presenter {
	private LoginView view; 
	private String token;
	private String JSON_URL;
	private PlaceController placeController;
	private EventBus eventBus;
	MainView mainPresenterObj;
	@Inject
	ActionServletURL actionServletURL;
	@Inject
	public LoginActivity(LoginView view,PlaceController placeController,EventBus eventBus,ActionServletURL actionServletURL)
	{	
		this.JSON_URL=actionServletURL.getBaseURL();
		this.actionServletURL=actionServletURL;
		    this.eventBus = eventBus;
 		    this.placeController=placeController;
		    this.view = view;
		    this.view.setPresenter(this);

	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
	}

	public Activity withPlace(LoginPlace place) {
		token = place.getToken();
		mainPresenterObj=place.getmainMobileView();
		return this;
	}
	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}
	/*
	 * For setting the email address in cookie whenever we login into it
	 */
	public static native String setCookie(String email) /*-{
	document.cookie="email"+"="+email;
	}-*/;

	public MainView getmainMobileView()
	{
		return mainPresenterObj;
	}
	

}
