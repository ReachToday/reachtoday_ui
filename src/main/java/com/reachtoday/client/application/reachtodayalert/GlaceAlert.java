package com.reachtoday.client.application.reachtodayalert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.HasFastValue;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.fastbutton.PressHandler;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.reachtodayalert.GlaceAlert.GlaceAlertUiBinder;
import com.reachtoday.client.resource.MobileResources;
import com.reachtoday.client.resource.TabletResources;

/**
 *  @author Harikishore 
 *  @since June 9, 2014
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Custom Alert/ConfirmAlert widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> This widget can be used instead of Window.alert by just passing the message.
 *  </li>
 *  <li>
 *  	Also it can be used instead of Window.confirm.
 *  </li>
 *  </ul>
 */


public class GlaceAlert extends Composite implements HasText {

	private static GlaceAlertUiBinder uiBinder = GWT
			.create(GlaceAlertUiBinder.class);

	interface GlaceAlertUiBinder extends UiBinder<Widget, GlaceAlert> {
	}


	MobileResources resources = GWT.create(MobileResources.class);
	@UiField PopupPanel alertPopup;
	@UiField VerticalPanel popupHolder;
	@UiField Label messageLabel;
	@UiField HorizontalPanel confirmHz;
	@UiField Label solutionlabel;
	@UiField(provided=true) FastButton dismissButton=new FastButton("Dismiss",resources.glaceMobileAlertStyle().buttonStyle(),resources.glaceMobileAlertStyle().buttonHoldStyle(),"");
	@UiField(provided=true) FastButton okButton=new FastButton("Yes",resources.glaceMobileAlertStyle().buttonStyleOk(),resources.glaceMobileAlertStyle().buttonHoldStyleOk(),"");
	@UiField(provided=true) FastButton cancelButton=new FastButton("No",resources.glaceMobileAlertStyle().buttonStyle(),resources.glaceMobileAlertStyle().buttonHoldStyleCancel(),"");
	String alertMessage="",confirmMessage="", solutionmessage="";
	String message="";
	boolean isConfirm;
	boolean solutionflag = false;
	GlaceAlertResponse glaceAlertResponse;
	static int time = 0;


	/**
	 * Just an alert
	 * @param alertMessage Message to be shown to the user.
	 * 
	 */
	public GlaceAlert(String alertMessage) {
		initWidget(uiBinder.createAndBindUi(this));
		setText(alertMessage);
		this.alertMessage=alertMessage;
		initPopup();
	}

	/**
	 * Confirmation alert
	 * @param confirmMessage Message to be shown to the user.
	 * @param isConfirm It should be true
	 * @param glaceAlertResponse To ensure that the next action should only be done after user select any option E.g., Yes or No
	 */
	public GlaceAlert(String confirmMessage, boolean isConfirm, GlaceAlertResponse glaceAlertResponse) {
		initWidget(uiBinder.createAndBindUi(this));
		setText(confirmMessage);
		this.glaceAlertResponse=glaceAlertResponse;
		this.confirmMessage=confirmMessage;
		initConfirmPopup();
	}
	
	/**
	 * Confirmation alert
	 * @param confirmMessage Message to be shown to the user.
	 * @para, solution message to be shown if present
	 * @param isConfirm It should be true
	 * @param glaceAlertResponse To ensure that the next action should only be done after user select any option E.g., Yes or No
	 */
	public GlaceAlert(String confirmMessage,String solutionmessage, boolean isConfirm,GlaceAlertResponse glaceAlertResponse) {
		initWidget(uiBinder.createAndBindUi(this));
		setText(confirmMessage);
		solutionflag = true;
		solutionlabel.setVisible(true);
		this.glaceAlertResponse = glaceAlertResponse;
		this.confirmMessage = confirmMessage;
		this.solutionmessage = solutionmessage;
		initConfirmPopup();
	}
	
	
	/**
	 * Confirmation alert
	 * @param confirmMessage Message to be shown to the user.
	 * @para, solution message to be shown if present
	 * @param isConfirm It should be true
	 * @param glaceAlertResponse To ensure that the next action should only be done after user select any option E.g., Yes or No
	 */
	public GlaceAlert(String confirmMessage,int time,ActionServletURL actionServletUrl,boolean isConfirm, GlaceAlertResponse glaceAlertResponse) {
		initWidget(uiBinder.createAndBindUi(this));
		setText(confirmMessage);
		this.glaceAlertResponse = glaceAlertResponse;
		this.confirmMessage = confirmMessage;
		initConfirmPopup();
		timerforsessioncheck(time,actionServletUrl);
	}
	

	/**
	 * Initiates the Confirmation popup, it focuses the style for the popup.
	 */
	private void initConfirmPopup() {
		confirmHz.setVisible(true);
		dismissButton.setVisible(false);
		alertPopup.setSize("300px", "150px");
		messageLabel.setText(confirmMessage);
		alertPopup.setGlassEnabled(true);
		popupHolder.setStyleName(resources.glaceMobileAlertStyle().popupStyle());
		popupHolder.setCellHeight(messageLabel, "70%");
		popupHolder.setCellHeight(confirmHz, "30%");
		popupHolder.setCellVerticalAlignment(messageLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		popupHolder.setCellHorizontalAlignment(messageLabel, HasHorizontalAlignment.ALIGN_CENTER);
		confirmHz.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_CENTER);
		confirmHz.setCellHorizontalAlignment(cancelButton, HasHorizontalAlignment.ALIGN_CENTER);
		confirmHz.setCellWidth(okButton, "50%");
		confirmHz.setCellWidth(cancelButton, "50%");
		messageLabel.setStyleName(resources.glaceMobileAlertStyle().messageLabel());
		alertPopup.center();
		alertPopup.setModal(true);
		alertPopup.setAutoHideEnabled(false);
		alertPopup.show();	

		okButton.addPressHandler(new PressHandler() {

			@Override
			public void onPress(PressEvent event) {
				isConfirm=true;
				alertPopup.hide();
				glaceAlertResponse.onConfirm();
			}
		});

		cancelButton.addPressHandler(new PressHandler() {

			@Override
			public void onPress(PressEvent event) {
				isConfirm=false;
				alertPopup.hide();
				glaceAlertResponse.onConfirm();
			}
		});
	}


	/**
	 * Initiates the alert popup, it focuses the style for the popup.
	 */
	public void initPopup() {
		dismissButton.setVisible(true);
		confirmHz.setVisible(false);

		alertPopup.setSize("250px", "150px");
		messageLabel.setText(alertMessage);
		alertPopup.setGlassEnabled(true);
		popupHolder.setStyleName(resources.glaceMobileAlertStyle().popupStyle());
		popupHolder.setCellHeight(messageLabel, "70%");
		popupHolder.setCellHeight(dismissButton, "30%");
		popupHolder.setCellVerticalAlignment(messageLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		popupHolder.setCellHorizontalAlignment(messageLabel, HasHorizontalAlignment.ALIGN_CENTER);
		popupHolder.setCellVerticalAlignment(dismissButton, HasVerticalAlignment.ALIGN_MIDDLE);
		popupHolder.setCellHorizontalAlignment(dismissButton, HasHorizontalAlignment.ALIGN_CENTER);
		messageLabel.setStyleName(resources.glaceMobileAlertStyle().messageLabel());
		alertPopup.center();
		alertPopup.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				alertPopup.hide();
			}
		});		
	}


	/**
	 * Press handler for dismiss button
	 */
	@UiHandler("dismissButton")
	public void onPressDismiss(PressEvent event){
		alertPopup.hide();
	}

	/**
	 * @return Returns the confirmation (true/false) 
	 */
	public boolean isConfirm() {
		return isConfirm;
	}

	@Override
	public String getText() {
		return message;
	}

	@Override
	public void setText(String message) {
		this.message=message;
	}


	public void timerforsessioncheck(int time,ActionServletURL actionServletUrl) {
		testsession(time,actionServletUrl);
	}
	
	public void testsession( int time,final ActionServletURL actionServletURL) {
		
		
		Timer timer = new Timer() {

			@Override
			public void run() {
				sessionChecking(actionServletURL);
			}
		};
			timer.scheduleRepeating(1000);	
	}
	
	public void sessionChecking(final ActionServletURL actionServletURL)
	 {
		 String jsonurl=actionServletURL.getBaseURL();
		 jsonurl=jsonurl.replace("/GWT", "");
		 String url=jsonurl+"/jsp/chart/patientdetails/getSession?ajax=1&nocache=0.6523253819918445&fromMainPage=true&GlaceAjaxRequest=true";
		 url = URL.encode(url);
		
		 RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		 try {
			 builder.sendRequest(null, new RequestCallback() {
				 public void onError(Request request, Throwable exception) {

				 }

				 @Override
				 public void onResponseReceived(Request request, Response response) {
					 if (200 == response.getStatusCode()) {
						 JSONValue jsonValue = JSONParser.parseStrict(response.getText());
			        	 JSONObject jsonobj = jsonValue.isObject();
			        	  JSONValue jsonValue1 = jsonobj.get("sessionTime");
		                 String userid =jsonValue1.toString();
		                 time = Integer.parseInt(userid.replace('"', ' ').trim());
		                 messageLabel.setText("Your session will expire in " + time + " click Yes to remain logged in");
					 } else {

					 }
				 }


			 });
		 }
		 catch(Exception e){

		 }
	 }
	
	/**
	 * @return Returns the dismiss button
	 */
	public HasFastValue getDismissButton() {
		return dismissButton;
	}


	/**
	 * @return Returns the dismiss button
	 */
	public HasFastValue getOkButton() {
		return okButton;
	}


	/**
	 * @return Returns the dismiss button
	 */
	public HasFastValue getCancelButton() {
		return cancelButton;
	}
}