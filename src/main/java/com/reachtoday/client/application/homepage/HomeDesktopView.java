package com.reachtoday.client.application.homepage;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeDesktopView extends Composite implements HomeView{
	
	 private static HomeDesktopViewUiBinder uiBinder = GWT
             .create(HomeDesktopViewUiBinder.class);

	 interface HomeDesktopViewUiBinder extends UiBinder<Widget, HomeDesktopView> {
	  }
	 private Presenter presenter;
		public HomeDesktopView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
