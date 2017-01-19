package com.reachtoday.client.application.main;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;




@Singleton
public class MainDesktopView extends Composite implements MainView {

  private static MainDesktopViewUiBinder uiBinder = GWT
  .create(MainDesktopViewUiBinder.class);
  
  interface MainDesktopViewUiBinder extends UiBinder<Widget, MainDesktopView> {
  }
  

@UiField SimplePanel mainDisplayPanel;
  
private Presenter presenter;


  
  @Inject
  public MainDesktopView(ActivityMapper mainActivityMapper, 
                  ActivityManager mainActivityManager) {     
	  initWidget(uiBinder.createAndBindUi(this));
	  
	  mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
				
			}
		});
		mainActivityManager.setDisplay(mainDisplayPanel);
	  
  } 

@Override
public void setPresenter(Presenter presenter) {
	// TODO Auto-generated method stub
	this.presenter=presenter;
}

public Widget asWidget() {
    return this;
  }

@Override
public void showhidemenuleft() {
	// TODO Auto-generated method stub
	
}

@Override
public void displayUsername(String practicename, String username) {
	// TODO Auto-generated method stub
	
}



}