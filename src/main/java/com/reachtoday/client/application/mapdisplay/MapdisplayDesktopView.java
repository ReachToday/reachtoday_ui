package com.reachtoday.client.application.mapdisplay;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MapdisplayDesktopView extends Composite implements MapdisplayView{
	
	 private static AVPDesktopViewUiBinder uiBinder = GWT
             .create(AVPDesktopViewUiBinder.class);

	 interface AVPDesktopViewUiBinder extends UiBinder<Widget, MapdisplayDesktopView> {
	  }
	 private Presenter presenter;
		public MapdisplayDesktopView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

}
