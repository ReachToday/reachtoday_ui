package com.reachtoday.client.application.login;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class LoginMobileModule extends AbstractGinModule{
	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	    bind(LoginView.class).to(LoginMobileView.class).in(Singleton.class);
	    bind(LoginActivity.class);


	}
}
