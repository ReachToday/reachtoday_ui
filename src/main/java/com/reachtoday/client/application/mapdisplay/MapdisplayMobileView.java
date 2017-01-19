package com.reachtoday.client.application.mapdisplay;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MapdisplayMobileView extends Composite implements MapdisplayView{
	
	 private static AVPMobileViewUiBinder uiBinder = GWT
             .create(AVPMobileViewUiBinder.class);

	 interface AVPMobileViewUiBinder extends UiBinder<Widget, MapdisplayMobileView> {
	  }
	 private Presenter presenter;
		public MapdisplayMobileView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
