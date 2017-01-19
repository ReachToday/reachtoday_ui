package com.reachtoday.client.application.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginDesktopView extends Composite  implements LoginView{

	private static LoginDesktopViewUiBinder uiBinder = GWT.create(LoginDesktopViewUiBinder.class);

	interface LoginDesktopViewUiBinder extends UiBinder<Widget, LoginDesktopView> {
	}
	private Presenter presenter;
	public LoginDesktopView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}

}
