package com.reachtoday.client.application.livetracking;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.application.searchcontrol.SearchControl;
import com.reachtoday.client.application.searchcontrol.SearchControlIf;

public class LivetrackingDesktopView extends Composite implements LivetrackingView{
	
	 private static AVPDesktopViewUiBinder uiBinder = GWT
             .create(AVPDesktopViewUiBinder.class);

	 interface AVPDesktopViewUiBinder extends UiBinder<Widget, LivetrackingDesktopView> {
	  }
	 private Presenter presenter;
		public LivetrackingDesktopView()
		{
			initWidget(uiBinder.createAndBindUi(this));
		}
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}



		@Override
		public SearchControl getBusSearchControl() {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public SearchControl getaddBusSearchControl(EventHandler eventHandler) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public SearchControlIf getPatientSearch() {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public void displaymap(String IMEI, String location,String busnumber) {
			// TODO Auto-generated method stub
			
		}

}
