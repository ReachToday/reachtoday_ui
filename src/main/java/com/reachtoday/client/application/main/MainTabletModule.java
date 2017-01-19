package com.reachtoday.client.application.main;


import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class MainTabletModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(MainView.class).to(MainTabletView.class).in(Singleton.class);
		
	}
	

}
