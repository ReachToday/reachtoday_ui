package com.reachtoday.client.application.main;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.friendfinder.Friendcontainerpanel;
import com.reachtoday.client.application.homepage.HomePlace;
import com.reachtoday.client.application.login.LoginPlace;
import com.reachtoday.client.application.reachtodayalert.MobileAlert;
import com.reachtoday.client.mvp.MobileMainActivityMapper;
import com.reachtoday.client.resource.MobileResources;
import com.reachtoday.client.util.UserLocationDetails;

@Singleton
public class MainMobileView extends Composite implements MainView {

	private static MainMobileViewUiBinder uiBinder = GWT
			.create(MainMobileViewUiBinder.class);

	MobileResources resources = GWT.create(MobileResources.class);
	interface MainMobileViewUiBinder extends UiBinder<Widget, MainMobileView> {
	}

	@UiField SimplePanel mainDisplayPanel;
	@UiField HTMLPanel menu;
	@UiField HTMLPanel page;
	@UiField HTMLPanel menu1;
	@UiField Label CompanyName;
	@UiField FastButton City;
	@UiField FastButton home,Refresh,logout,login,messagenotification;
	@UiField FastButton homeClickable,refreshClickable;
	@UiField HorizontalPanel homePanel,RefreshPanel,Logout,Login,messageCountPanel;
	
	
	@UiField FastButton showhidemenuleft;
	@UiField FastButton companysymbol;


	FlowPanel patientInfo = new FlowPanel();
	FlowPanel menuInfo = new FlowPanel();
	static String check = "check";

	EventBus eventBus;
	private String JSON_URL; 
	public Boolean menuleftOpen=false;
	public PopupPanel loadingPopUp = new PopupPanel();

	private Presenter presenter;

	double startTouchStart;
	int startTouchStartX;
	int startTouchStartY;
	int actualTouchStartX;
	int actualTouchStartY;

	final int seuilSwipeHorizontalX=30;
	final int seuilSwipeHorizontalY=75;
	final int seuilSwipeVerticalX=75;
	final int seuilSwipeVerticalY=30;
	final int seuilSwipeDuration=1000;

	public static int loggedin=0;
	public static String username="";
	public static String emailid="";
	@Inject
	ActionServletURL actionServletURL;
	public static MainMobileView var;
	@Inject
	public MainMobileView(MobileMainActivityMapper mainActivityMapper, 
			ActivityManager mainActivityManager,EventBus eventBus,ActionServletURL actionServletURL ) {  

		initWidget(uiBinder.createAndBindUi(this));
		this.JSON_URL=actionServletURL.getBaseURL();
		this.eventBus=eventBus;
		mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
				menu.getElement().setAttribute("Height","100%");
			}
		});
		
		mainActivityManager.setDisplay(mainDisplayPanel);
	
		home.getElement().getStyle().setWidth(100, Unit.PCT);
		home.getElement().getStyle().setPaddingLeft(35, Unit.PX);
		Refresh.getElement().getStyle().setPaddingLeft(35, Unit.PX);
		logout.getElement().getStyle().setPaddingLeft(35, Unit.PX);
		login.getElement().getStyle().setPaddingLeft(35, Unit.PX);
		homeClickable.addStyleName(resources.mobilestyles().userSelectNone());
		
		refreshClickable.addStyleName(resources.mobilestyles().userSelectNone());
		companysymbol.addStyleName(resources.mobilestyles().Companysymbol());
		this.actionServletURL=actionServletURL;
		//to assgign the details in case logged in
		Logout.setVisible(false);
		Login.setVisible(true);
		getloggeddetails();
		var=this;
		messageCountPanel.clear();
	} 

	
	
	@UiHandler("showhidemenuleft")
	void onPresmenuleft(PressEvent event) {
		showhidemenuleft();
	}

	@UiHandler("homeClickable")
	void onPressHome(PressEvent event) {
		showhidemenuleft();
		presenter.goTo(new HomePlace("hello"));
	}

	
	@UiHandler("LoginBtn")
	void onPressLoginBtn(PressEvent event) {
		showhidemenuleft();

//		Window.alert("need to code");
		if(loggedin==0){
			presenter.goTo(new LoginPlace("login",this));
//			MobileAlert test=new MobileAlert("login",this.actionServletURL,this);
		}
		else
			Window.alert("You are already logged");
//		GlaceAlert test=new GlaceAlert("Welcome to glenwood service");
	}
	@UiHandler("refreshClickable")
	void onPressRefresh(PressEvent event){
		forceReload();	
//		testmethod();
	}


	/**
	 * @author software
	 *
	 */
	interface MainStyle extends CssResource{
		String foralerts();
		String foralerts1();
		String foralerts2();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}

	public static native void forceReload() /*-{
	    $wnd.location.reload(true);
	}-*/;

	public Widget asWidget() {
		return this;
	}

	


	
	@Override
	public void displayUsername(String practicename,String username) {
		City.setText("ReachToday");
		if(username.trim().equalsIgnoreCase("")){
		CompanyName.setText("ReachToday");
		Logout.setVisible(false);
		Login.setVisible(true);
		messageCountPanel.setVisible(false);
		messageCountPanel.clear();
		}
		else{
			
			Logout.setVisible(true);
			Login.setVisible(false);
			CompanyName.setText(username);
			checknotifications();
			new UserLocationDetails(actionServletURL).sendLocationDetails(true);
			
		}
	}
	


	

	

	public FastButton getCityName() {
		return City;
	}


	
	@Override
	public void showhidemenuleft() {
		if(!menuleftOpen){
			page.getElement().getStyle().setProperty("MozTransform", "translate3d(150px, 0 , 0)");
			page.getElement().getStyle().setProperty("transform", "translate3d(150px, 0 , 0)");
			page.getElement().getStyle().setProperty("WebkitTransform", "translate3d(150px, 0 , 0)");
			page.getElement().getStyle().setProperty("OTransform", "translate3d(150px, 0 , 0)");
			menuleftOpen=true;

		}
		else{
			menu1.removeStyleName(resources.mobilestyles().makePanelTransparent());
			menu.add(menu1);
			page.getElement().getStyle().setProperty("MozTransform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("transform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("WebkitTransform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("OTransform", "translate3d(0, 0 , 0)");
			menuleftOpen=false;
		}

	}

	public static native String getCookie(String c_name) /*-{


    var c_value = document.cookie;
   var c_start = c_value.indexOf(" " + c_name + "=");
   if (c_start == -1)
   {
   c_start = c_value.indexOf(c_name + "=");
   }
   if (c_start == -1)
   {
   c_value = null;
   }
   else
   {
   c_start = c_value.indexOf("=", c_start) + 1;
   var c_end = c_value.indexOf(";", c_start);
   if (c_end == -1)
{
c_end = c_value.length;
}
c_value = unescape(c_value.substring(c_start,c_end));
}
return c_value;
}-*/;
	
	 
	
	public void getloggeddetails()
	{

if(getCookie("email")!=null){
		String url=JSON_URL+"/login?mailid="+getCookie("email").trim()+"&password=logincheck";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if(response.getStatusCode()==200)
					{
						JSONValue jsonValue = JSONParser.parseStrict(response.getText());
						JSONArray jsonarr = jsonValue.isArray();
						if(jsonarr.size()>0){
						JSONObject jsonobj = jsonarr.get(0).isObject();
						JSONValue jsonValue1 = jsonobj.get("login_users_isactive");
						String isloggedin = jsonValue1.isString().stringValue();
						if(isloggedin.equalsIgnoreCase("true"))
						{
							loggedin=1;
							username=jsonobj.get("login_users_username").isString().stringValue();
							emailid=jsonobj.get("login_users_mail_id").isString().stringValue();
							displayUsername("", username);
							
							
						}else
						{
							loggedin=0;
							username="";
							emailid="";
							displayUsername("","");
						}
						}
					}else
					{
						loggedin=0;
						username="";
						emailid="";
						displayUsername("","");
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
	
	@UiHandler("LogoutBtn")
	void onPressLogoutBtn(PressEvent event) {
		showhidemenuleft();
		
		String url=JSON_URL+"/login?mailid="+getCookie("email").trim()+"&password=logout";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if(response.getStatusCode()==200)
					{
						loggedin=0;
						username="";
						emailid="";
						displayUsername("","");
					}else
					{
						loggedin=0;
						username="";
						emailid="";
						displayUsername("","");
					}
					new UserLocationDetails(actionServletURL).sendLocationDetails(false);
				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}});
		} catch (RequestException e) {
			
		}
		presenter.goTo(new HomePlace("hello"));
	}
	JSONArray notifications = new JSONArray();
	public void checknotifications()
	{
		String url=JSON_URL+"/friendnotification?currentloggedmail="+MainMobileView.emailid;
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			 builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if(response.getStatusCode()==200)
					{

						JSONValue jsonValue = JSONParser.parseStrict(response.getText());
						JSONArray jsonarr = jsonValue.isArray();
						if(jsonarr.size()>0){
							notifications=jsonarr;
							Label  messagealerts=new Label();
							messagealerts.setText(jsonarr.size()+"");
							messageCountPanel.setVisible(true);
							messageCountPanel.add(messagealerts);
						}else
						{
							messageCountPanel.setVisible(false);
							messageCountPanel.clear();
						}
					
					}
					
				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}});
		} catch (RequestException e) {
			
		}
	}
	
	@UiHandler("messagenotification")
	public void pressmessagecountpanel(PressEvent event)
	{
		if(notifications.size()>0){
		PopupPanel popup=new PopupPanel();
		Label arrow = new Label();
		arrow.ensureDebugId("arrow");
		arrow.setStyleName(resources.mobilestyles().triangleup());
		arrow.getElement().getStyle().setMarginLeft(messagenotification.getAbsoluteLeft()-50, Unit.PX);
		popup.setAnimationEnabled(true);
		popup.setAutoHideEnabled(true);
		popup.setGlassEnabled(true);
		
		popup.showRelativeTo(messagenotification);
//		popup.getElement().getStyle().setLeft(messagenotification.getAbsoluteLeft(), Unit.PX);
//		popup.getElement().getStyle().setFloat(Float.RIGHT);
		popup.getElement().getStyle().setLeft(50, Unit.PX);
		popup.setSize("300px", "330px");
		VerticalPanel outerPanel=new VerticalPanel();
		outerPanel.setSize("300px", "330px");
		outerPanel.add(arrow);
		outerPanel.getElement().getStyle().setBackgroundColor("transparent");
		ScrollPanel scrollpn=new ScrollPanel();
		scrollpn.ensureDebugId("scrollpanel");
		scrollpn.setSize("300px", "300px");
		VerticalPanel vPanel=new VerticalPanel();
		scrollpn.add(vPanel);
		vPanel.ensureDebugId("vPanel");
		vPanel.setSize("100%", "100%");
		scrollpn.getElement().getStyle().setMarginTop(-14, Unit.PX);
		scrollpn.getElement().getStyle().setBackgroundColor("white");
		outerPanel.add(scrollpn);
		popup.add(outerPanel);
		popup.show();
		
		for(int i=0;i<notifications.size();i++)
		vPanel.add(new Friendcontainerpanel(notifications.get(i).isObject().get("user_friends_login_users_username").isString().stringValue(), notifications.get(i).isObject().get("user_friends_login_users_mail_id").isString().stringValue(), actionServletURL));
		}
	}
	
	
}