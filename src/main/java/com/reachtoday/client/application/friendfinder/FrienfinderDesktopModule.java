package com.reachtoday.client.application.friendfinder;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class FrienfinderDesktopModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(FrienfinderView.class).to(FrienfinderDesktopView.class).in(Singleton.class);
	    bind(FrienfinderActivity.class);


	}



}