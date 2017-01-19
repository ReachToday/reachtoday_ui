package com.reachtoday.client.application.main;



import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.google.inject.Singleton;





public class MainMobileModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(MainView.class).to(MainMobileView.class).in(Singleton.class);
		
	   
	}
	
	 

}
