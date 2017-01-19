package com.reachtoday.client.application.friendfinder;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;
import com.reachtoday.client.resource.MobileResources;

public class FrienfinderMobileView extends Composite implements FrienfinderView{
	
	 private static FrienfinderMobileViewUiBinder uiBinder = GWT
             .create(FrienfinderMobileViewUiBinder.class);

	 interface FrienfinderMobileViewUiBinder extends UiBinder<Widget, FrienfinderMobileView> {
	  }
	 private Presenter presenter;
	 @UiField HorizontalPanel headerPanel;
	 @UiField Label headerLabel;
	 @UiField TextBox usersearchbox;
	 @UiField
		FlowPanel searchControlVPanel;
	 @UiField
	 static MobileResources resources;
	 @UiField
	 FlowPanel scrollpanelforfriendslist;
	 Image search=new Image("");
//	 Image searchclose=new Image("");
	@Inject
	ActionServletURL actionServletURL;
	HandlerRegistration searchClick;
	 
	private String JSON_URL="";
	 @Inject
		public FrienfinderMobileView(ActionServletURL actionServletURL)
		{
			initWidget(uiBinder.createAndBindUi(this));
			headerPanel.setCellVerticalAlignment(headerLabel, HasVerticalAlignment.ALIGN_MIDDLE);
			usersearchbox.setWidth("300px");
			usersearchbox.setText("");
			searchControlVPanel.getElement().getStyle().setMarginTop(10, Unit.PCT);
			searchControlVPanel.getElement().getStyle().setMarginLeft(30, Unit.PX);
			search.setResource(resources.searchGrey());
			search.setStyleName(resources.searchControlStyles().search());
			searchControlVPanel.add(search);
			scrollpanelforfriendslist.clear();
//			searchclose.setResource(resources.searchClose());
//			searchclose.setStyleName(resources.searchControlStyles().search());
//			searchControlVPanel.add(searchclose);
//			search.setVisible(true);
			searchhandlers();
			this.actionServletURL=actionServletURL;
			this.JSON_URL=actionServletURL.getBaseURL();
		}
		
		
		

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter=presenter;
			
		}
		String userflag="";
		public void searchhandlers()
		{
			userflag="";
			if(searchClick!=null)
				searchClick.removeHandler();
			searchClick=search.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					if(userflag!=usersearchbox.getText().trim())
					if(!(usersearchbox.getText().trim().equalsIgnoreCase("")||MainMobileView.loggedin==0)){
						scrollpanelforfriendslist.clear();
					String url=JSON_URL+"/searchuser?username="+usersearchbox.getText().trim()+"&currentloggedmail="+MainMobileView.emailid;
//					Window.alert(">>>>url>>>"+url);
					RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
					try {
						 builder.sendRequest(null, new RequestCallback() {

							@Override
							public void onResponseReceived(Request request,
									Response response) {
								if(response.getStatusCode()==200)
								{
									userflag=usersearchbox.getText().trim();
//									Window.alert(response.getText());
									JSONValue jsonValue = JSONParser.parseStrict(response.getText());
									JSONArray jsonarr = jsonValue.isArray();
//									Window.alert(">>>"+jsonarr.size());
									if(jsonarr.size()>0){
										for(int i=0;i<jsonarr.size();i++)
										{
									JSONObject jsonobj = jsonarr.get(i).isObject();
									searchControlVPanel.getElement().getStyle().setMarginTop(0, Unit.PCT);
									scrollpanelforfriendslist.setVisible(true);
									scrollpanelforfriendslist.add(new Friendcontainerpanel(searchControlVPanel,headerLabel,jsonobj.get("login_users_username").isString().stringValue(),jsonobj.get("login_users_mail_id").isString().stringValue(),MainMobileView.emailid,actionServletURL));
										}
//									Window.alert(">>>"+jsonobj);
									
										/*MainMobileView.username=jsonobj.get("login_users_username").isString().stringValue();
										MainMobileView.emailid=jsonobj.get("login_users_mail_id").isString().stringValue();*/
										
									}else
									{
										Window.alert("No users found with given criteria");
									}
									
									
								}else
								{
									
								}
								
							}

							@Override
							public void onError(Request request, Throwable exception) {
								// TODO Auto-generated method stub
								
							}});
					} catch (RequestException e) {
						
					}
					}else
					{
						Window.alert("please enter username/Login to proceed");
					}

					/*searchControlVPanel.getElement().getStyle().setMarginTop(0, Unit.PCT);
					scrollpanelforfriendslist.setVisible(true);
					scrollpanelforfriendslist.add(new Friendcontainerpanel());*/
				}
			});
		}

}
