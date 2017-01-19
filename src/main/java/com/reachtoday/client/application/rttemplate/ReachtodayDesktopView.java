package com.reachtoday.client.application.rttemplate;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReachtodayDesktopView extends Composite implements ReachtodayView{
	
	 private static AVPDesktopViewUiBinder uiBinder = GWT
             .create(AVPDesktopViewUiBinder.class);

	 interface AVPDesktopViewUiBinder extends UiBinder<Widget, ReachtodayDesktopView> {
	  }
	 private Presenter presenter;
		public ReachtodayDesktopView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
