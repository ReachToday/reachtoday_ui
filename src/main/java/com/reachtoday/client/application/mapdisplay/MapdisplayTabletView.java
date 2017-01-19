package com.reachtoday.client.application.mapdisplay;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MapdisplayTabletView extends Composite implements MapdisplayView{
	
	 private static AVPTabletViewUiBinder uiBinder = GWT
             .create(AVPTabletViewUiBinder.class);

	 interface AVPTabletViewUiBinder extends UiBinder<Widget, MapdisplayTabletView> {
	  }
	 private Presenter presenter;
		public MapdisplayTabletView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
