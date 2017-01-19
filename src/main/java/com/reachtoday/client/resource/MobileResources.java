package com.reachtoday.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.reachtoday.client.resource.TabletResources.SearchControlStyles;

public interface MobileResources extends ClientBundle {
	

public interface Styles extends CssResource {
	
	String pageloadFadeIn();
	
	String leftMenuPanel();
	
	String leftMenuPanelHold();
	
	String homeMenuPanel();
	
	String homeMenuPanelHold();
	
	String leftMenuButtonBlue();
	
	String leftMenuButtonBlueHold();
	
	String showhideLeftMenuNormal();
	
	String showhideLeftMenuHoldPress();
	
	String showhideRightMenuNormal();
	
	String showhideRightMenuHoldPress();
	
	String headerButtonNormal();
	
	String headerButtonHold();
	
	String userSelectNone();
	
	String makePanelTransparent();
	
	String reachTodayIcons();
	
	String Companysymbol();
	
	String headerStyles();
	
	String headerLabelStyles();
	
	String navigationArrow();
	
	String navigationArrowHold();
	
	String navigationText();
	
	String navigationTextHold();
	
	String ellipsis();
	String textoverflow();
	String textoverflowhide();
	String triangleup();
	String blackLabel();
	String textBox();
	String loadingImage();
	String roundIcons();
	String fastbuttonBlue();
	String fastbuttonBlueHold();
	
}
    @Source("../css/mobilestyles.css")
    Styles mobilestyles();
    
    /*
     * Live traking module related styles 
     */
    public interface LivetrackingStyles extends CssResource{
		
    	String components();
    	
    	String chartComponents();
    	
    	String iframeStyleXMLTab();
    	
       String scrollPanel();
       
       String container();
	}
	@Source("../application/livetracking/Livetracking.css")
	LivetrackingStyles livetrackingstyles();   
    
	public interface GlaceMobileAlertStyle extends CssResource {
		String popupStyle();		
		String popupHolder();
		String messageLabel();
		String buttonStyle();		
		String buttonHoldStyle();
		String buttonHoldStyleOk();
		String buttonHoldStyleCancel();
		String buttonStyleOk();
		String popOverStyle1();
		String optionsPopup();
	}
	
	@Source("../application/reachtodayalert/GlaceMobileAlert.css")
	GlaceMobileAlertStyle glaceMobileAlertStyle();
    
	public interface SearchControlStyles extends CssResource {
		String searchClose();
		String searchClosePress();
		String search();
	}
	@Source("../application/searchcontrol/searchcontrolstyles.css")
	SearchControlStyles searchControlStyles();
	
	public interface LoginMobileStyles extends CssResource {
		String headerStyles();
		String loginScroller();
		String loginFlowPanel();
		String loginTextBox();
		String loginBtn();
		String showPassword();
		String loginLabel();
	}
	@Source("../application/login/LoginMobileStyles.css")
	LoginMobileStyles loginMobileStyles();
	
	/*
     * All Images
     *  */
    @Source("../images/menuicon.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource menuicon();
    
    @Source("../images/searchClose.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource searchClose();
    
    @Source("../images/searchGrey.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource searchGrey();

    @Source("../images/user.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource user();
    
	@Source("../images/message_notification.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource messagenotification();
	
	@Source("../images/ajax-loader.gif")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource ajaxLoading();

}
