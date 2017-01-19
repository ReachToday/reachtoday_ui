package com.reachtoday.client.application.homepage;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.friendfinder.FrienfinderPlace;
import com.reachtoday.client.application.livetracking.LivetrackingPlace;
import com.reachtoday.client.application.login.LoginPlace;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;
import com.reachtoday.client.application.reachtodayalert.MobileAlert;
import com.reachtoday.client.resource.MobileResources;

public class HomeMobileView extends Composite implements HomeView{
	
	 private static HomeMobileViewUiBinder uiBinder = GWT
             .create(HomeMobileViewUiBinder.class);

	 interface HomeMobileViewUiBinder extends UiBinder<Widget, HomeMobileView> {
	  }
	 private Presenter presenter;
	 
	@UiField VerticalPanel SearchWidgetCntr;
	@UiField HorizontalPanel BusNumberWdgtCntr,RouteWdgtCntr,FriendWdgtCntr,LoginWdgtCntr;
	@UiField FastButton bussymbol,routesymbol,friendsymbol,loginsymbol;
	@UiField Label loginName,friendName,routeName,busName;
	@UiField MobileResources resources;
	@Inject
	ActionServletURL actionServletURL;
	@Inject
	public HomeMobileView(ActionServletURL actionServletURL)
		{
			initWidget(uiBinder.createAndBindUi(this));
			this.actionServletURL=actionServletURL;
			styles();
			
		}
		
		
		public void styles()
		{
			SearchWidgetCntr.setWidth(Window.getClientWidth()+"px");
			int height =Window.getClientHeight()-45;
			int contrheight=(height-100)/4;
			BusNumberWdgtCntr.setWidth(Window.getClientWidth()-30+"px");
			BusNumberWdgtCntr.setHeight(contrheight+"px"); 
			BusNumberWdgtCntr.getElement().getStyle().setMarginTop(25, Unit.PX);
			RouteWdgtCntr.setWidth(Window.getClientWidth()-30+"px");
			RouteWdgtCntr.setHeight(contrheight+"px");
			FriendWdgtCntr.setWidth(Window.getClientWidth()-30+"px");
			FriendWdgtCntr.setHeight(contrheight+"px");
			LoginWdgtCntr.setWidth(Window.getClientWidth()-30+"px");
			LoginWdgtCntr.setHeight(contrheight+"px");
			BusNumberWdgtCntr.setCellVerticalAlignment(bussymbol, HasVerticalAlignment.ALIGN_MIDDLE);
			BusNumberWdgtCntr.setCellVerticalAlignment(busName, HasVerticalAlignment.ALIGN_MIDDLE);
			BusNumberWdgtCntr.setCellHorizontalAlignment(bussymbol, HasHorizontalAlignment.ALIGN_CENTER);
			BusNumberWdgtCntr.setCellHorizontalAlignment(busName, HasHorizontalAlignment.ALIGN_LEFT);
//			bussymbol.addStyleName(resources.mobilestyles().roundIcons());
			bussymbol.getElement().getStyle().setBackgroundColor("#9370DB");
			bussymbol.getElement().getStyle().setFontSize(29, Unit.PX);
			BusNumberWdgtCntr.setCellWidth(bussymbol,"100px");
//			bussymbol.getElement().getStyle().setPaddingLeft(20, Unit.PX);
//			bussymbol.getElement().getStyle().setColor("##383842");
			RouteWdgtCntr.setCellVerticalAlignment(routesymbol, HasVerticalAlignment.ALIGN_MIDDLE);
			RouteWdgtCntr.setCellVerticalAlignment(routeName, HasVerticalAlignment.ALIGN_MIDDLE);
			RouteWdgtCntr.setCellHorizontalAlignment(routesymbol, HasHorizontalAlignment.ALIGN_CENTER);
			RouteWdgtCntr.setCellHorizontalAlignment(routeName, HasHorizontalAlignment.ALIGN_LEFT);
//			routesymbol.addStyleName(resources.mobilestyles().roundIcons());
			routesymbol.getElement().getStyle().setBackgroundColor("#DC143C");
			RouteWdgtCntr.setCellWidth(routesymbol,"100px");
//			routesymbol.getElement().getStyle().setFontSize(40, Unit.PX);
//			routesymbol.getElement().getStyle().setPaddingLeft(20, Unit.PX);
//			routesymbol.getElement().getStyle().setColor("##383842");
			FriendWdgtCntr.setCellVerticalAlignment(friendsymbol, HasVerticalAlignment.ALIGN_MIDDLE);
			FriendWdgtCntr.setCellVerticalAlignment(friendName, HasVerticalAlignment.ALIGN_MIDDLE);
			FriendWdgtCntr.setCellHorizontalAlignment(friendsymbol, HasHorizontalAlignment.ALIGN_CENTER);
			FriendWdgtCntr.setCellHorizontalAlignment(friendName, HasHorizontalAlignment.ALIGN_LEFT);
//			friendsymbol.addStyleName(resources.mobilestyles().roundIcons());
			friendsymbol.getElement().getStyle().setBackgroundColor("#1E90FF");
			FriendWdgtCntr.setCellWidth(friendsymbol,"100px");
//			friendsymbol.getElement().getStyle().setFontSize(40, Unit.PX);
//			friendsymbol.getElement().getStyle().setPaddingLeft(20, Unit.PX);
//			friendsymbol.getElement().getStyle().setColor("##383842");
			LoginWdgtCntr.setCellVerticalAlignment(loginsymbol, HasVerticalAlignment.ALIGN_MIDDLE);
			LoginWdgtCntr.setCellVerticalAlignment(loginName, HasVerticalAlignment.ALIGN_MIDDLE);
			LoginWdgtCntr.setCellHorizontalAlignment(loginsymbol, HasHorizontalAlignment.ALIGN_CENTER);
			LoginWdgtCntr.setCellHorizontalAlignment(loginName, HasHorizontalAlignment.ALIGN_LEFT);
//			loginsymbol.addStyleName(resources.mobilestyles().roundIcons());
			loginsymbol.getElement().getStyle().setBackgroundColor("#da70d6");
//			LoginWdgtCntr.getElement().getStyle().setMarginBottom(25, Unit.PX);
			loginsymbol.getElement().getStyle().setFontSize(26, Unit.PX);
			LoginWdgtCntr.setCellWidth(loginsymbol,"100px");
//			loginsymbol.getElement().getStyle().setColor("##383842");
//			loginsymbol.getElement().getStyle().setPaddingLeft(20, Unit.PX);
//			RouteWdgtCntr.setCellHorizontalAlignment(messageLabel, HasHorizontalAlignment.ALIGN_CENTER);
		}

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}

		
		@UiHandler("bussymbol")
		public void pressBus(PressEvent event)
		{
			presenter.goTo(new LivetrackingPlace("Livetrack"));
		}
		@UiHandler("busName")
		public void pressBus(ClickEvent event)
		{
			presenter.goTo(new LivetrackingPlace("Livetrack"));
		}
		
		@UiHandler("friendsymbol")
		public void pressfriend(PressEvent event)
		{
			if(MainMobileView.loggedin==1){
				presenter.goTo(new FrienfinderPlace("FriendTrack"));
				}else
				{
					Window.alert("Please login to proceed");
				}
		}
		@UiHandler("friendName")
		public void pressfriend(ClickEvent event)
		{
			if(MainMobileView.loggedin==1){
			presenter.goTo(new FrienfinderPlace("FriendTrack"));
			}else
			{
				Window.alert("Please login to proceed");
			}
		}
		
		
		@UiHandler("loginsymbol")
		public void presslogin(PressEvent event)
		{
			if(MainMobileView.loggedin==0){
			//	MobileAlert test=new MobileAlert("login",this.actionServletURL,MainMobileView.var);
				presenter.goTo(new LoginPlace("login",MainMobileView.var));
			}
			else
				Window.alert("You are already logged");
		}
		@UiHandler("loginName")
		public void presslogin(ClickEvent event)
		{
			if(MainMobileView.loggedin==0){
				//MobileAlert test=new MobileAlert("login",this.actionServletURL,MainMobileView.var);
				presenter.goTo(new LoginPlace("login",MainMobileView.var));
			}
			else
				Window.alert("You are already logged");
		}
		
}
