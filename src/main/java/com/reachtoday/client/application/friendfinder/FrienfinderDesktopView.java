package com.reachtoday.client.application.friendfinder;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FrienfinderDesktopView extends Composite implements FrienfinderView{
	
	 private static FrienfinderDesktopViewUiBinder uiBinder = GWT
             .create(FrienfinderDesktopViewUiBinder.class);

	 interface FrienfinderDesktopViewUiBinder extends UiBinder<Widget, FrienfinderDesktopView> {
	  }
	 private Presenter presenter;
		public FrienfinderDesktopView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
