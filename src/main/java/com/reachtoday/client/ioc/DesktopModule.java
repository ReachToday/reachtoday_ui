package com.reachtoday.client.ioc;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;
import com.reachtoday.client.application.login.LoginDesktopModule;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.ActionServletURLImpl;
import com.reachtoday.client.application.main.MainDesktopModule;
import com.reachtoday.client.application.rttemplate.ReachtodayDesktopModule;
import com.reachtoday.client.mvp.DesktopMvpModule;
import com.reachtoday.client.resource.CSSInjector;
import com.reachtoday.client.resource.DesktopCSSInjector;
import com.reachtoday.client.resource.DesktopResources;


public class DesktopModule extends AbstractGinModule{

	@Override
	protected void configure() {
		install(new DesktopMvpModule());
		install(new MainDesktopModule());
		install(new ReachtodayDesktopModule());
		install(new LoginDesktopModule());
		bind(DesktopResources.class).in(Singleton.class);
		bind(CSSInjector.class).to(DesktopCSSInjector.class).in(Singleton.class);
		bind(ActionServletURL.class).to(ActionServletURLImpl.class).in(Singleton.class);
	}
}

