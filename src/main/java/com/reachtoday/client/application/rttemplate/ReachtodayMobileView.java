package com.reachtoday.client.application.rttemplate;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReachtodayMobileView extends Composite implements ReachtodayView{
	
	 private static AVPMobileViewUiBinder uiBinder = GWT
             .create(AVPMobileViewUiBinder.class);

	 interface AVPMobileViewUiBinder extends UiBinder<Widget, ReachtodayMobileView> {
	  }
	 private Presenter presenter;
		public ReachtodayMobileView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
