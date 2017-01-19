package com.reachtoday.client.application.homepage;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeTabletView extends Composite implements HomeView{
	
	 private static HomeMobileViewUiBinder uiBinder = GWT
             .create(HomeMobileViewUiBinder.class);

	 interface HomeMobileViewUiBinder extends UiBinder<Widget, HomeTabletView> {
	  }
	 private Presenter presenter;
		public HomeTabletView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
