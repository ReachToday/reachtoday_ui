package com.reachtoday.client.application.login;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class LoginDesktopModule extends AbstractGinModule{
	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(LoginView.class).to(LoginDesktopView.class).in(Singleton.class);
	    bind(LoginActivity.class);


	}
}
