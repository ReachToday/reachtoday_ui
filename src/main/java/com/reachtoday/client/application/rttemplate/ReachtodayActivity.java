package com.reachtoday.client.application.rttemplate;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ReachtodayActivity extends AbstractActivity implements ReachtodayView.Presenter{
	
	private ReachtodayView view; 
	private String token;
	
	@Inject
	public ReachtodayActivity(ReachtodayView view)
	{
		this.view=view;
		this.view.setPresenter(this);
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub
		panel.setWidget(view.asWidget());
		
	}
	
	public ReachtodayActivity withPlace(ReachtodayPlace place) {
		  token = place.getToken();
		  return this;
	  }
	

	
}
