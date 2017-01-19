package com.reachtoday.client.application.friendfinder;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FrienfinderTabletView extends Composite implements FrienfinderView{
	
	 private static FrienfinderMobileViewUiBinder uiBinder = GWT
             .create(FrienfinderMobileViewUiBinder.class);

	 interface FrienfinderMobileViewUiBinder extends UiBinder<Widget, FrienfinderTabletView> {
	  }
	 private Presenter presenter;
		public FrienfinderTabletView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
