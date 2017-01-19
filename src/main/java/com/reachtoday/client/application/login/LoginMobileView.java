package com.reachtoday.client.application.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.homepage.HomePlace;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainMobileView;
import com.reachtoday.client.resource.MobileResources;

public class LoginMobileView extends Composite implements LoginView {

	private static LoginMobileViewUiBinder uiBinder = GWT.create(LoginMobileViewUiBinder.class);

	interface LoginMobileViewUiBinder extends UiBinder<Widget, LoginMobileView> {
	}
	private Presenter presenter;
	@UiField HorizontalPanel headerPanel;
	@UiField FastButton loginBackBtn;
	@UiField Label headerLabel;
	@UiField ScrollPanel mainScrollPanel;
	@UiField FlowPanel loginMainPanel;
	@UiField Label mailIdLabel;
	@UiField TextBox mailIdTxtBox;
	@UiField Label passwordLabel;
	@UiField TextBox passwordTxtBox;
	@UiField HorizontalPanel loginPasswdPanel;
	@UiField FastButton loginInShowPswd;
//	@UiField Label showPswdLabel; 
//	@UiField HorizontalPanel actionBtnPanel;
	@UiField FastButton loginSubmitBtn;
	@UiField FastButton newUserBtn;
//	@UiField FastButton clearBtn;
//	@UiField FastButton forgotBtn;
	@UiField ScrollPanel signinScrollPanel;
	@UiField FlowPanel signinMainPanel;
	@UiField Label signinUserNameLabel;
	@UiField TextBox signinUserNameTxtBox;
	@UiField Label signinMailIdLabel;
	@UiField TextBox signinMailIdTxtBox;
	@UiField Label signinPasswordLabel;
	@UiField TextBox signinPasswordTxtBox;
	@UiField FastButton signInShowPswd;
	@UiField FastButton createUser;
	@UiField MobileResources resources;
	@Inject
	ActionServletURL actionServletURL;
	private String JSON_URL;
	@Inject
	public LoginMobileView(ActionServletURL actionServletURL) {
		initWidget(uiBinder.createAndBindUi(this));
		loadDefaultStyles();
		this.actionServletURL=actionServletURL;
		this.JSON_URL=actionServletURL.getBaseURL();
	}

	public void loadDefaultStyles() {
		headerPanel.setCellVerticalAlignment(headerLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		mailIdTxtBox.getElement().setAttribute("placeholder","Username");
		mailIdTxtBox.getElement().setAttribute("autocomplete", "off");
		mailIdTxtBox.getElement().setAttribute("autocorrect", "off");
		mailIdTxtBox.addStyleName(resources.loginMobileStyles().loginTextBox());
		passwordTxtBox.getElement().setAttribute("placeholder","Password");
		passwordTxtBox.getElement().setAttribute("autocomplete", "off");
		passwordTxtBox.getElement().setAttribute("autocorrect", "off");
		passwordTxtBox.getElement().setAttribute("type", "password");
		passwordTxtBox.addStyleName(resources.loginMobileStyles().loginTextBox());
		loginSubmitBtn.addStyleName(resources.loginMobileStyles().loginBtn());
		newUserBtn.addStyleName(resources.loginMobileStyles().loginBtn());
		loginSubmitBtn.getElement().getStyle().setFloat(Float.NONE);
		newUserBtn.getElement().getStyle().setFloat(Float.NONE);
		loginSubmitBtn.getElement().getStyle().setMargin(10, Unit.PCT);
		newUserBtn.getElement().getStyle().setMargin(10, Unit.PCT);
//		clearBtn.addStyleName(resources.loginMobileStyles().loginBtn());
		signinUserNameTxtBox.getElement().setAttribute("placeholder","Username");
		signinUserNameTxtBox.getElement().setAttribute("autocomplete", "off");
		signinUserNameTxtBox.getElement().setAttribute("autocorrect", "off");
		signinUserNameTxtBox.addStyleName(resources.loginMobileStyles().loginTextBox());
		signinMailIdTxtBox.getElement().setAttribute("placeholder","Email id");
		signinMailIdTxtBox.getElement().setAttribute("autocomplete", "off");
		signinMailIdTxtBox.getElement().setAttribute("autocorrect", "off");
		signinMailIdTxtBox.addStyleName(resources.loginMobileStyles().loginTextBox());
		signinPasswordTxtBox.getElement().setAttribute("placeholder","Password");
		signinPasswordTxtBox.getElement().setAttribute("autocomplete", "off");
		signinPasswordTxtBox.getElement().setAttribute("autocorrect", "off");
		signinPasswordTxtBox.getElement().setAttribute("type", "password");
		signinPasswordTxtBox.addStyleName(resources.loginMobileStyles().loginTextBox());
		createUser.addStyleName(resources.loginMobileStyles().loginBtn());
		createUser.getElement().getStyle().setFloat(Float.NONE);
		createUser.getElement().getStyle().setMargin(10, Unit.PCT);
		loginInShowPswd.addStyleName(resources.loginMobileStyles().showPassword());
		signInShowPswd.addStyleName(resources.loginMobileStyles().showPassword());
		loginInShowPswd.getElement().getStyle().setProperty("font-family", "icons");
		signInShowPswd.getElement().getStyle().setProperty("font-family", "icons");
		loginInShowPswd.getElement().getStyle().setFontSize(18, Unit.PX);
		signInShowPswd.getElement().getStyle().setFontSize(18, Unit.PX);
		mailIdLabel.addStyleName(resources.loginMobileStyles().loginLabel());
		passwordLabel.addStyleName(resources.loginMobileStyles().loginLabel());
		signinUserNameLabel.addStyleName(resources.loginMobileStyles().loginLabel());
		signinMailIdLabel.addStyleName(resources.loginMobileStyles().loginLabel());
		signinPasswordLabel.addStyleName(resources.loginMobileStyles().loginLabel());
//		loginInShowPswd.setText("\ue06e;");
//		signInShowPswd.setText("\ue06e;");
		mainScrollPanel.setHeight((Window.getClientHeight()-100)+"px");
		signinScrollPanel.setHeight((Window.getClientHeight()-100)+"px");
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				mainScrollPanel.setHeight((Window.getClientHeight()-100)+"px");
				signinScrollPanel.setHeight((Window.getClientHeight()-100)+"px");
			}
		});
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}
	
	@UiHandler("newUserBtn")
	public void newUserBtnPressHandler(PressEvent event){
		mainScrollPanel.setVisible(false);
		headerLabel.setText("New User");
		loginBackBtn.setVisible(true);
		signinScrollPanel.setVisible(true);
		signinUserNameTxtBox.setText("");
		signinMailIdTxtBox.setText("");
		signinPasswordTxtBox.setText("");
		
	}
	
	@UiHandler("loginBackBtn")
	public void loginBackBtnPressHandler(PressEvent event){
		signinScrollPanel.setVisible(false);
		loginBackBtn.setVisible(false);
		headerLabel.setText("Login");
		mainScrollPanel.setVisible(true);
		mailIdTxtBox.setText("");
		passwordTxtBox.setText("");
	}
	@UiHandler("loginInShowPswd")
	public void loginInShowPswdPressHandler(PressEvent event){
		if(passwordTxtBox.getElement().getAttribute("type").equalsIgnoreCase("password")){
			passwordTxtBox.getElement().setAttribute("type", "text");
		}else{
			passwordTxtBox.getElement().setAttribute("type", "password");			
		}
	}
	@UiHandler("signInShowPswd")
	public void signInShowPswdPressHandler(PressEvent event){
		if(signinPasswordTxtBox.getElement().getAttribute("type").equalsIgnoreCase("password")){
			signinPasswordTxtBox.getElement().setAttribute("type", "text");
		}else{
			signinPasswordTxtBox.getElement().setAttribute("type", "password");			
		}
	}
	
	public static native String setCookie(String email) /*-{
	document.cookie="email"+"="+email;
	}-*/;
	
	@UiHandler("loginSubmitBtn")
	public void pressLoginBtn(PressEvent event)
	{

		if(!(mailIdTxtBox.getText().trim().equalsIgnoreCase("")||passwordTxtBox.getText().trim().equalsIgnoreCase(""))){
		setCookie(mailIdTxtBox.getText().trim());
		String url=JSON_URL+"/login?mailid="+mailIdTxtBox.getText().trim()+"&password="+passwordTxtBox.getText().trim();
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
//						Window.alert(">>>"+jsonobj);
						JSONValue jsonValue1 = jsonobj.get("login_users_isactive");
						String isloggedin = jsonValue1.toString();
							MainMobileView.loggedin=1;
							MainMobileView.username=jsonobj.get("login_users_username").isString().stringValue();
							MainMobileView.emailid=jsonobj.get("login_users_mail_id").isString().stringValue();
							presenter.getmainMobileView().displayUsername("",MainMobileView.username);
//							Window.alert(">>>"+jsonobj.get("login_users_username").isString().stringValue());
							mailIdTxtBox.setText("");
							presenter.goTo(new HomePlace("hello"));

						}else
						{
							Window.alert("username/password not matched");
						}
						
						
						passwordTxtBox.setText("");
					}else
					{
						MainMobileView.loggedin=0;
						MainMobileView.username="";
						MainMobileView.emailid="";
						presenter.getmainMobileView().displayUsername("","");
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
	
	/*
	 * Registration of new user
	 */
	@UiHandler("createUser")
	public void createUserPressHandler(PressEvent event){
		if(!(signinMailIdTxtBox.getText().trim().equalsIgnoreCase("")||signinUserNameTxtBox.getText().trim().equalsIgnoreCase("")||signinPasswordTxtBox.getText().trim().equalsIgnoreCase(""))){
			if(MainMobileView.loggedin==0){
			setCookie(signinMailIdTxtBox.getText().trim());
			String url=JSON_URL+"/register?mailid="+signinMailIdTxtBox.getText().trim()+"&password="+signinPasswordTxtBox.getText().trim()+"&username="+signinUserNameTxtBox.getText().trim();
//			Window.alert(">>>>url>>>"+url);
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			try {
				 builder.sendRequest(null, new RequestCallback() {

					@Override
					public void onResponseReceived(Request request,
							Response response) {
						if(response.getStatusCode()==200)
						{
//							Window.alert(response.getText());
							JSONValue jsonValue = JSONParser.parseStrict(response.getText());
							JSONArray jsonarr = jsonValue.isArray();
//							Window.alert(">>>"+jsonarr.size());
							if(jsonarr.size()>0){
							Window.alert("UserId  already in use");	
							mailIdTxtBox.setText("");
							}else
							{
								Window.alert("Successfully registered");
								MainMobileView.loggedin=1;
								MainMobileView.username=signinUserNameTxtBox.getText().trim();
								MainMobileView.emailid=signinMailIdTxtBox.getText().trim();
								presenter.getmainMobileView().displayUsername("",signinUserNameTxtBox.getText().trim());
								signinMailIdTxtBox.setText("");
								signinPasswordTxtBox.setText("");
								signinUserNameTxtBox.setText("");
								presenter.goTo(new HomePlace("hello"));
							}
						
							
						}else
						{
							MainMobileView.loggedin=0;
							MainMobileView.username="";
							MainMobileView.emailid="";
							presenter.getmainMobileView().displayUsername("","");
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
}
