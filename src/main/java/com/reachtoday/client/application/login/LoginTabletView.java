package com.reachtoday.client.application.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginTabletView extends Composite implements LoginView{

	private static LoginTabletViewUiBinder uiBinder = GWT.create(LoginTabletViewUiBinder.class);

	interface LoginTabletViewUiBinder extends UiBinder<Widget, LoginTabletView> {
	}
	private Presenter presenter;
	public LoginTabletView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}

}
