package com.reachtoday.client.application.rttemplate;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;





public class ReachtodayTabletModule extends AbstractGinModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(ReachtodayView.class).to(ReachtodayTabletView.class).in(Singleton.class);
	    bind(ReachtodayActivity.class);


	}



}