package com.reachtoday.client.application.friendfinder;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class FrienfinderActivity extends AbstractActivity implements FrienfinderView.Presenter{
	
	private FrienfinderView view; 
	private String token;
	
	@Inject
	public FrienfinderActivity(FrienfinderView view)
	{
		
		this.view=view;
		this.view.setPresenter(this);
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	
	public FrienfinderActivity withPlace(FrienfinderPlace place) {
		  token = place.getToken();
		  return this;
	  }
	

	
}
