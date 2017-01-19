package com.reachtoday.client.application.livetracking;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.fastbutton.PressHandler;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.searchcontrol.SearchControl;
import com.reachtoday.client.application.searchcontrol.SearchControlIf;
import com.reachtoday.client.resource.CellListStyle;
import com.reachtoday.client.resource.MobileResources;

public class LivetrackingMobileView extends Composite implements LivetrackingView{
	
	 private static LivetrackingMobileViewUiBinder uiBinder = GWT
             .create(LivetrackingMobileViewUiBinder.class);

	 interface LivetrackingMobileViewUiBinder extends UiBinder<Widget, LivetrackingMobileView> {
	  }
	 private Presenter presenter;
		
		interface LivetrackingStyles extends CssResource {
			String busListText();
		}
		
		@UiField
		MobileResources resources;
		@UiField 
		CellListStyle cellListResources;
		@UiField
		LivetrackingStyles style;
		@UiField
		SearchControl busSearchControl;
		@UiField
		HorizontalPanel headerPanel;
		@UiField 
		Label headerLabel;
		@UiField
		FlowPanel searchControlVPanel;
		
		@UiField
		FastButton mapbackbtn;
		@UiField
		FastButton businmap;
		@UiField
		FlowPanel scrollpanelformap;
		
		@UiField
		Frame mapFrame;
		
		@Inject
		ActionServletURL actionServletURL;
		
		String JSON_URL="";
		
		@Inject
		public LivetrackingMobileView(ActionServletURL actionServletURL) {
			initWidget(uiBinder.createAndBindUi(this));
			this.actionServletURL=actionServletURL;
			this.JSON_URL=actionServletURL.getBaseURL();
			busSearchControl.setSize("300px", "100px");
			busSearchControl.setSearchPlaceHolder("Enter bus number to search...");
			busSearchControl.getTextBox().ensureDebugId("busLookUpSearchControl");
			busSearchControl.getCloseButton().getElement().setAttribute("style", "background-color: #FFFFFF;cursor: pointer;font-size: 25px !important;margin-left: -28px;width: 25px !important;font-family: icons;color:#aaa;");
			busSearchControl.getCloseButton().setBackgroundImage(resources.searchClose());
			busSearchControl.getSearchImage().getElement().getStyle().setProperty("fontFamily", "icons");
			busSearchControl.getSearchImage().getElement().getStyle().setProperty("color", "#aaa");
			headerPanel.setCellVerticalAlignment(headerLabel, HasVerticalAlignment.ALIGN_MIDDLE);
			searchControlVPanel.getElement().getStyle().setMarginTop(10, Unit.PCT);
			searchControlVPanel.getElement().getStyle().setMarginLeft(30, Unit.PX);
			/*busSearchControl.getTextBox().addFocusHandler(new FocusHandler() {
				
				@Override
				public void onFocus(FocusEvent event) {
					   searchControlVPanel.getElement().getStyle().setMarginTop(10, Unit.PCT);
				}
			});*/
			mapbackbtn.setVisible(false);
			businmap.setVisible(false);
			scrollpanelformap.setVisible(false);
		}
		
		@Override
		public Widget asWidget() {
			return this;
		}

		@Override
		public void setPresenter(Presenter presenter) {
			this.presenter = presenter;
		}

		@Override
		protected void onDetach() {
			super.onDetach();
			busSearchControl.getTextBox().setText("");
			busSearchControl.getPagerPanel().setVisible(false);
		}
		
		@Override
		public SearchControl getBusSearchControl() {
			return busSearchControl;
		}

		@Override
		public SearchControl getaddBusSearchControl(EventHandler eventHandler) {
			busSearchControl.addEventHandler(eventHandler); 
			return busSearchControl;
		}

		@Override
		public SearchControlIf getPatientSearch() {
			return busSearchControl;
		}
		
		public void displaymap(String IMEI,String location,String busnumber)
		{
			
			mapbackbtn.setVisible(true);
			businmap.setVisible(true);
			scrollpanelformap.setVisible(true);
			searchControlVPanel.setVisible(false);
			headerLabel.setVisible(false);
			businmap.addStyleName(resources.mobilestyles().ellipsis());
			businmap.setText(busnumber+" At "+location);
			businmap.addPressHandler(new PressHandler() {
				
				@Override
				public void onPress(PressEvent event) {
					mapbackbtn.setVisible(false);
					businmap.setVisible(false);
					scrollpanelformap.setVisible(false);
					searchControlVPanel.setVisible(true);
					headerLabel.setVisible(true);		
				}
			});
			mapbackbtn.addPressHandler(new PressHandler() {
				
				@Override
				public void onPress(PressEvent event) {
					mapbackbtn.setVisible(false);
					businmap.setVisible(false);
					scrollpanelformap.setVisible(false);
					searchControlVPanel.setVisible(true);
					headerLabel.setVisible(true);		
				}
			});
			scrollpanelformap.clear();
			mapFrame.ensureDebugId("iframe");
			scrollpanelformap.ensureDebugId("scrooll panel");
			scrollpanelformap.setStyleName(resources.livetrackingstyles().scrollPanel());
			
			String url=JSON_URL+"/SendNumber?busNumber="+busnumber+"&IMEI="+IMEI;
//			String url="127.0.01/ReachToday/SendNumber?busNumber=test114&IMEI=9003115833";
			mapFrame.setUrl(url);
			mapFrame.setStyleName(resources.livetrackingstyles().iframeStyleXMLTab());
			scrollpanelformap.add(mapFrame);
		}
		
}
