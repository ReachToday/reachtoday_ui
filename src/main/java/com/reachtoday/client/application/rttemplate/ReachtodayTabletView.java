package com.reachtoday.client.application.rttemplate;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReachtodayTabletView extends Composite implements ReachtodayView{
	
	 private static AVPTabletViewUiBinder uiBinder = GWT
             .create(AVPTabletViewUiBinder.class);

	 interface AVPTabletViewUiBinder extends UiBinder<Widget, ReachtodayTabletView> {
	  }
	 private Presenter presenter;
		public ReachtodayTabletView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
