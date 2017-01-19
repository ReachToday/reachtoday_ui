package com.reachtoday.client.ioc;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;
import com.reachtoday.client.application.friendfinder.FrienfinderMobileModule;
import com.reachtoday.client.application.homepage.HomeMobileModule;
import com.reachtoday.client.application.livetracking.LivetrackingMobileModule;
import com.reachtoday.client.application.login.LoginMobileModule;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.ActionServletURLImpl;
import com.reachtoday.client.application.main.MainMobileModule;
import com.reachtoday.client.application.mapdisplay.MapdisplayMobileModule;
import com.reachtoday.client.application.rttemplate.ReachtodayMobileModule;
import com.reachtoday.client.mvp.MobileMvpModule;
import com.reachtoday.client.resource.CSSInjector;
import com.reachtoday.client.resource.MobileCSSInjector;
import com.reachtoday.client.resource.MobileResources;


public class MobileModule extends AbstractGinModule{

	@Override
	protected void configure() {
		install(new MobileMvpModule());
		install(new MainMobileModule());
		install(new ReachtodayMobileModule());
		install(new HomeMobileModule());
		install(new FrienfinderMobileModule());
		install(new LivetrackingMobileModule());
		install(new MapdisplayMobileModule());
		install(new LoginMobileModule());
		bind(MobileResources.class).in(Singleton.class);
		bind(CSSInjector.class).to(MobileCSSInjector.class).in(Singleton.class);
		bind(ActionServletURL.class).to(ActionServletURLImpl.class).in(Singleton.class);
	}
}

