package com.reachtoday.client.application.homepage;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;

public class HomeActivity extends AbstractActivity implements HomeView.Presenter{

	private HomeView view; 
	private String token;
	private PlaceController placeController;
	private EventBus eventBus;
	private String JSON_URL;

	@Inject
	ActionServletURL actionServletURL;
	@Inject
	public HomeActivity(HomeView view,PlaceController placeController,EventBus eventBus,ActionServletURL actionServletURL) {
		this.actionServletURL=actionServletURL;
		this.eventBus = eventBus;
		this.placeController=placeController;
		this.view = view;
		this.view.setPresenter(this);

	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub
		panel.setWidget(view.asWidget());

	}

	public HomeActivity withPlace(HomePlace place) {
		token = place.getToken();
		return this;
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

}
