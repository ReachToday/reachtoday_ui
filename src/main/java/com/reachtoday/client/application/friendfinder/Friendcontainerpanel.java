package com.reachtoday.client.application.friendfinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;
import com.reachtoday.client.resource.MobileResources;

public class Friendcontainerpanel extends Composite implements HasText {

	private static FriendcontainerpanelUiBinder uiBinder = GWT
			.create(FriendcontainerpanelUiBinder.class);

	interface FriendcontainerpanelUiBinder extends
			UiBinder<Widget, Friendcontainerpanel> {
	}
	@UiField HTMLPanel componentouterpanel;
	@UiField HorizontalPanel headerpanel;
	@UiField HorizontalPanel headingPanel;
	@UiField VerticalPanel componentinnerpanel,mapLoadingPanel,loadingimagevertical;
	@UiField HorizontalPanel componentData;
	@UiField Label heading,namevalue,emailvalue;   
	@UiField Button statusbutton,mapbutton;
//	@UiField VerticalPanel mapLoadingPanel;
//	@UiField VerticalPanel loadingimagevertical;
	@UiField MobileResources resources;
	@Inject
	ActionServletURL actionServletURL;
	private String JSON_URL="";
	@Inject
	public Friendcontainerpanel(FlowPanel searchControlVPanel, Label headerLabel, String username,String email,String currentemail,ActionServletURL actionServletURL) {
		initWidget(uiBinder.createAndBindUi(this));
		this.headerpanel.setWidth("100%");
		this.headingPanel.setWidth("100%");
		this.heading.setWidth("100%");
		headingPanel.setCellWidth(this.heading, "100%");
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();
		statusbutton.setText("");
		heading.setText(username);
		namevalue.setText(username);
		emailvalue.setText(email);
		mapbutton.setVisible(false);
		mapLoadingPanel.setVisible(false);
		loadingimagevertical.setVisible(false);
		checkfriends(email,currentemail);
		statusbuttonhandler(email,currentemail);
		viewButonHandler(searchControlVPanel,headerLabel);
	}
	
	
	public Friendcontainerpanel(String username,String mail,ActionServletURL actionServletURL) {
		initWidget(uiBinder.createAndBindUi(this));
		this.headerpanel.setWidth("100%");
		this.headingPanel.setWidth("100%");
		this.heading.setWidth("100%");
		headingPanel.setCellWidth(this.heading, "100%");
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();
		statusbutton.setText("Acceptrequest");
		heading.setText(username);
		namevalue.setText(username);
		emailvalue.setText(mail);
		mapbutton.setVisible(false);
		mapLoadingPanel.setVisible(false);
		loadingimagevertical.setVisible(false);
		acceptbuttonhandler(mail);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkfriends(String email,String currentemail)
	{

		

		if(!(email.trim().equalsIgnoreCase("")||currentemail.trim().equalsIgnoreCase(""))){
		
		String url=JSON_URL+"/searchfriend?email="+email.trim()+"&currentloggedmail="+MainMobileView.emailid;
//		Window.alert(">>>>url>>>"+url);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if(response.getStatusCode()==200)
					{
//						Window.alert(response.getText());
						JSONValue jsonValue = JSONParser.parseStrict(response.getText());
						JSONArray jsonarr = jsonValue.isArray();
//						Window.alert(">>>"+jsonarr.size());
						if(jsonarr.size()>0){
							
						JSONObject jsonobj = jsonarr.get(0).isObject();
							if(jsonobj.get("user_friends_isactive").isString().stringValue().trim().equalsIgnoreCase("true"))
							{
								statusbutton.setText("Friends");
								mapbutton.setVisible(true);
							}else
							{
								statusbutton.setText("Requestpending");
							}
//						Window.alert(">>>"+jsonobj);
						
							/*MainMobileView.username=jsonobj.get("login_users_username").isString().stringValue();
							MainMobileView.emailid=jsonobj.get("login_users_mail_id").isString().stringValue();*/
							
						}else
						{
							statusbutton.setText("Sendrequest");
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
			Window.alert("Error: Contact ReachToday in friend search");
		}

		/*searchControlVPanel.getElement().getStyle().setMarginTop(0, Unit.PCT);
		scrollpanelforfriendslist.setVisible(true);
		scrollpanelforfriendslist.add(new Friendcontainerpanel());*/
	
	}
	/*
	 * Status button for sending request
	 */
	public void statusbuttonhandler(final String email,final String currentemail)
	{
		statusbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(statusbutton.getText().trim().equalsIgnoreCase("")||statusbutton.getText().trim().equalsIgnoreCase("Requestpending")||statusbutton.getText().trim().equalsIgnoreCase("friends"))
				{
					
				}else
				{

					
					String url=JSON_URL+"/sendfriendrequest?email="+email.trim()+"&currentloggedmail="+MainMobileView.emailid.trim()+"&accept=false";;
//					Window.alert(">>>>url>>>"+url);
					RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
					try {
						 builder.sendRequest(null, new RequestCallback() {

							@Override
							public void onResponseReceived(Request request,
									Response response) {
								if(response.getStatusCode()==200)
								{
									statusbutton.setText("Requestpending");
									Window.alert("Request sent");
									
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
					
				}
				
			}
		});
		
	}
	
	/*
	 * Status button for accepting the request
	 */

	public void acceptbuttonhandler(final String mail)
	{
		statusbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(statusbutton.getText().trim().equalsIgnoreCase("")||statusbutton.getText().trim().equalsIgnoreCase("friends"))
				{
					
				}else
				{

					
					String url=JSON_URL+"/sendfriendrequest?email="+MainMobileView.emailid.trim()+"&currentloggedmail="+mail.trim()+"&accept=true";
//					Window.alert(">>>>url>>>"+url);
					RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
					try {
						 builder.sendRequest(null, new RequestCallback() {

							@Override
							public void onResponseReceived(Request request,
									Response response) {
								if(response.getStatusCode()==200)
								{
									statusbutton.setText("Accepted");
									Window.alert("Request sent");
									
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
					
				}
				
			}
		});
		
	}
	public void viewButonHandler(final FlowPanel searchControlVPanel,final Label headerLabel) {
		mapbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				headerLabel.setText(heading.getText());
				searchControlVPanel.setVisible(false);
				componentinnerpanel.setVisible(false);
				loadingimagevertical.setVisible(true);
				sendingRequestToLoad();
			}
		});
	}

	public void sendingRequestToLoad() {
		loadingimagevertical.setVisible(false);
		mapLoadingPanel.setVisible(true);
		mapLoadingPanel.clear();
		final Frame frame = new Frame(JSON_URL+"/TrackUser?userId="+emailvalue.getText().toString());
		frame.getElement().setAttribute("target", "_parent");
		frame.setWidth("100%");
		frame.setHeight((Window.getClientHeight()-160)+"px");
		mapLoadingPanel.add(frame);
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				frame.setHeight((Window.getClientHeight()-160)+"px");
			}
		});
		/*String url=JSON_URL+"/TrackUser?userId="+emailvalue.getText().toString();
//		Window.alert(">>>>url>>>"+url);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,	Response response) {
					if(response.getStatusCode()==200)
					{
						loadingimagevertical.setVisible(false);
						mapLoadingPanel.setVisible(true);
						loadMapInIFRAME(response.getText().toString());
					}else
					{
					}
				}
				@Override
				public void onError(Request request, Throwable exception) {
					
				}});
		} catch (RequestException e) {
			e.printStackTrace();
		}*/
	}


	public void loadMapInIFRAME(String responseText) {
		mapLoadingPanel.clear();
		final Frame frame = new Frame(JSON_URL+"/TrackUser?userId="+emailvalue.getText().toString());
		frame.getElement().setAttribute("target", "_parent");
//		frame.getElement().setInnerHTML(responseText);
		mapLoadingPanel.add(frame);
	}
}
