package com.reachtoday.client.application.mapdisplay;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class MapdisplayDesktopModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(MapdisplayView.class).to(MapdisplayDesktopView.class).in(Singleton.class);
	    bind(MapdisplayActivity.class);


	}



}