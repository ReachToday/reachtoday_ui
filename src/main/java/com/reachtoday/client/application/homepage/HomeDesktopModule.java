package com.reachtoday.client.application.homepage;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class HomeDesktopModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(HomeView.class).to(HomeDesktopView.class).in(Singleton.class);
	    bind(HomeActivity.class);


	}



}