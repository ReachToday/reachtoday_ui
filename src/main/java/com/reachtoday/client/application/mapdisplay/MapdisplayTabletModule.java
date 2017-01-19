package com.reachtoday.client.application.mapdisplay;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class MapdisplayTabletModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(MapdisplayView.class).to(MapdisplayTabletView.class).in(Singleton.class);
	    bind(MapdisplayActivity.class);


	}



}