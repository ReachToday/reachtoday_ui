package com.reachtoday.client.application.homepage;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class HomeTabletModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(HomeView.class).to(HomeTabletView.class).in(Singleton.class);
	    bind(HomeActivity.class);


	}



}