package com.reachtoday.client.application.mapdisplay;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class MapdisplayActivity extends AbstractActivity implements MapdisplayView.Presenter{
	
	private MapdisplayView view; 
	private String token;
	
	@Inject
	public MapdisplayActivity(MapdisplayView view)
	{
		this.view=view;
		this.view.setPresenter(this);
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub
		panel.setWidget(view.asWidget());
		
	}
	
	public MapdisplayActivity withPlace(MapdisplayPlace place) {
		  token = place.getToken();
		  return this;
	  }
	

	
}
