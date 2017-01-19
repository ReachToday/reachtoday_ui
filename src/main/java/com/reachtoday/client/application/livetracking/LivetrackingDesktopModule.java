package com.reachtoday.client.application.livetracking;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class LivetrackingDesktopModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(LivetrackingView.class).to(LivetrackingDesktopView.class).in(Singleton.class);
	    bind(LivetrackingActivity.class);


	}



}