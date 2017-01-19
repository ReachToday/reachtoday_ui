package com.reachtoday.client.application.reachtodayalert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.HasFastValue;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.fastbutton.PressHandler;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;
import com.reachtoday.client.application.main.MainPresenter;
import com.reachtoday.client.application.main.MainView;
import com.reachtoday.client.resource.MobileResources;

/**
 *  @author Hari kishore
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Mobile Custom Alert/ConfirmAlert widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> Please donot touch this widget .
 *  </li>
 *  <li>
 *  	Also it can be used instead of Window.confirm.
 *  </li>
 *  </ul>
 */


public class MobileAlert extends Composite implements HasText {

	private static GlaceMobileAlertUiBinder uiBinder = GWT
			.create(GlaceMobileAlertUiBinder.class);

	interface GlaceMobileAlertUiBinder extends UiBinder<Widget, MobileAlert> {
	}


	MobileResources resources = GWT.create(MobileResources.class);
	@UiField PopupPanel alertPopup;
	@UiField VerticalPanel popupHolder;
	@UiField TextBox username,password,email;
	@UiField HorizontalPanel confirmHz;
	@UiField FastButton okButton=new FastButton("Login",resources.glaceMobileAlertStyle().buttonStyleOk(),resources.glaceMobileAlertStyle().buttonHoldStyleOk(),"");
	@UiField FastButton regButton=new FastButton("Register",resources.glaceMobileAlertStyle().buttonStyleOk(),resources.glaceMobileAlertStyle().buttonHoldStyleOk(),"");
	@UiField Label newuser;
	@Inject
	ActionServletURL actionServletURL; 
	private String JSON_URL;
	MainView mainPresenterObj;
	/**
	 * Just an alert
	 * @param alertMessage Message to be shown to the user.
	 * 
	 */
	@Inject
	public MobileAlert(String logreg,ActionServletURL actionServletURL,MainView  mainPresenterObj) {
		initWidget(uiBinder.createAndBindUi(this));
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();
		this.mainPresenterObj=mainPresenterObj;
		okButton.setVisible(true);
		username.setVisible(true);
		regButton.setVisible(true);
		newuser.setVisible(true);
		if(logreg.equalsIgnoreCase("register")){
			okButton.setVisible(false);	
			newuser.setVisible(false);
		}
		else{
			username.setVisible(false);
			regButton.setVisible(false);
		}
		
		newuser.getElement().getStyle().setColor("blue");
		newuser.getElement().getStyle().setTextDecoration(TextDecoration.UNDERLINE);
		newuser.getElement().getStyle().setMarginTop(5, Unit.PX);
		newuser.getElement().getStyle().setMarginLeft(80, Unit.PX);
//		newuser.getElement().getStyle().setFontSize(14, Unit.PX);
		email.getElement().setAttribute("placeholder", "Enter your email");
		email.addStyleName(resources.glaceMobileAlertStyle().optionsPopup());
		email.getElement().getStyle().setMarginLeft(10, Unit.PX);
		email.getElement().getStyle().setMarginTop(10, Unit.PX);
		
		username.getElement().setAttribute("placeholder", "Username");
		username.addStyleName(resources.glaceMobileAlertStyle().optionsPopup());
		username.getElement().getStyle().setMarginLeft(10, Unit.PX);
		username.getElement().getStyle().setMarginTop(10, Unit.PX);
		
		password.getElement().setAttribute("placeholder", "Password");
		password.addStyleName(resources.glaceMobileAlertStyle().optionsPopup());
		password.getElement().getStyle().setMarginLeft(10, Unit.PX);
		password.getElement().getStyle().setMarginTop(15, Unit.PX);
		regButton.getElement().getStyle().setMarginTop(10, Unit.PX);
		regButton.getElement().getStyle().setMarginLeft(80, Unit.PX);
		regButton.getElement().getStyle().setHeight(60, Unit.PX);
		okButton.getElement().getStyle().setMarginTop(10, Unit.PX);
		okButton.getElement().getStyle().setMarginLeft(80, Unit.PX);
		okButton.getElement().getStyle().setHeight(60, Unit.PX);
//		okButton.getElement().getStyle().setFontSize(18, Unit.PX);
		initPopup();
	}

	

	

	/**
	 * Initiates the alert popup, it focuses the style for the popup.
	 */
	public void initPopup() {
		confirmHz.setVisible(true);
		alertPopup.setSize("250px", "150px");
		alertPopup.setGlassEnabled(true);
		alertPopup.getElement().getStyle().setBackgroundColor("#fff");
//		popupHolder.setStyleName(resources.glaceMobileAlertStyle().popupStyle());
//		alertPopup.addStyleName(resources.glaceMobileAlertStyle().popOverStyle1());
		alertPopup.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		alertPopup.getElement().getStyle().setBorderColor("#f3f3f3");
		alertPopup.getElement().getStyle().setBorderWidth(1, Unit.PX);
		alertPopup.getElement().getStyle().setProperty("border-radius", "8px");
		//		alertPopup.addStyleName(resources.glaceMobileAlertStyle().optionsPopup());
//		popupHolder.setSize("250px", "150px");
		popupHolder.setCellHeight(username, "35%");
		popupHolder.setCellHeight(email, "35%");
		popupHolder.setCellHeight(password, "35%");
		popupHolder.setCellHeight(okButton, "30%");
		popupHolder.setCellHeight(regButton, "30%");
		alertPopup.center();
		alertPopup.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
//				alertPopup.hide();
			}
		});		
	}

	




	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}


@UiHandler("newuser")
public void pressnewuser(ClickEvent event)
{
	alertPopup.hide();
	new MobileAlert("register",this.actionServletURL,this.mainPresenterObj);
}

public static native String setCookie(String email) /*-{
document.cookie="email"+"="+email;
}-*/;
@UiHandler("okButton")
public void presslogin(PressEvent event)
{
	if(!(email.getText().trim().equalsIgnoreCase("")||password.getText().trim().equalsIgnoreCase(""))){
	setCookie(email.getText().trim());
	String url=JSON_URL+"/login?mailid="+email.getText().trim()+"&password="+password.getText().trim();
	RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
	try {
		 builder.sendRequest(null, new RequestCallback() {

			@Override
			public void onResponseReceived(Request request,
					Response response) {
				if(response.getStatusCode()==200)
				{
//					Window.alert(response.getText());
					JSONValue jsonValue = JSONParser.parseStrict(response.getText());
					JSONArray jsonarr = jsonValue.isArray();
//					Window.alert(">>>"+jsonarr.size());
					if(jsonarr.size()>0){
					JSONObject jsonobj = jsonarr.get(0).isObject();
//					Window.alert(">>>"+jsonobj);
					JSONValue jsonValue1 = jsonobj.get("login_users_isactive");
					String isloggedin = jsonValue1.toString();
						MainMobileView.loggedin=1;
						MainMobileView.username=jsonobj.get("login_users_username").isString().stringValue();
						MainMobileView.emailid=jsonobj.get("login_users_mail_id").isString().stringValue();
						mainPresenterObj.displayUsername("",MainMobileView.username);
//						Window.alert(">>>"+jsonobj.get("login_users_username").isString().stringValue());
						email.setText("");
						alertPopup.hide();
					}else
					{
						Window.alert("username/password not matched");
					}
					
					
					password.setText("");
				}else
				{
					MainMobileView.loggedin=0;
					MainMobileView.username="";
					MainMobileView.emailid="";
					mainPresenterObj.displayUsername("","");
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
		Window.alert("please enter all the fields");
	}
}

@UiHandler("regButton")
public void pressregis(PressEvent event)
{
	if(!(email.getText().trim().equalsIgnoreCase("")||username.getText().trim().equalsIgnoreCase("")||password.getText().trim().equalsIgnoreCase(""))){
	if(MainMobileView.loggedin==0){
	setCookie(email.getText().trim());
	String url=JSON_URL+"/register?mailid="+email.getText().trim()+"&password="+password.getText().trim()+"&username="+username.getText().trim();
//	Window.alert(">>>>url>>>"+url);
	RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
	try {
		 builder.sendRequest(null, new RequestCallback() {

			@Override
			public void onResponseReceived(Request request,
					Response response) {
				if(response.getStatusCode()==200)
				{
//					Window.alert(response.getText());
					JSONValue jsonValue = JSONParser.parseStrict(response.getText());
					JSONArray jsonarr = jsonValue.isArray();
//					Window.alert(">>>"+jsonarr.size());
					if(jsonarr.size()>0){
					Window.alert("UserId  already in use");	
					email.setText("");
					}else
					{
						Window.alert("Successfully registered");
						MainMobileView.loggedin=1;
						MainMobileView.username=username.getText().trim();
						MainMobileView.emailid=email.getText().trim();
						mainPresenterObj.displayUsername("",username.getText().trim());
						email.setText("");
						password.setText("");
						username.setText("");
						alertPopup.hide();
					}
				
					
				}else
				{
					MainMobileView.loggedin=0;
					MainMobileView.username="";
					MainMobileView.emailid="";
					mainPresenterObj.displayUsername("","");
				}
				
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO Auto-generated method stub
				
			}});
	} catch (RequestException e) {
		
	}
	}
	else
	{
		Window.alert("logout the current user to login");
	}
	}else
	{
		Window.alert("please enter all the fields");
	}
	
}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

}