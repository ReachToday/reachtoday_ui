package com.reachtoday.client.application.homepage;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class HomeMobileModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(HomeView.class).to(HomeMobileView.class).in(Singleton.class);
	    bind(HomeActivity.class);


	}



}