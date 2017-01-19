package com.reachtoday.client.application.friendfinder;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class FrienfinderMobileModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(FrienfinderView.class).to(FrienfinderMobileView.class).in(Singleton.class);
	    bind(FrienfinderActivity.class);


	}



}