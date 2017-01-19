package com.reachtoday.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;


public interface TabletResources extends ClientBundle {

	public interface FastButtonStyles extends CssResource{
		/*
		 * Fast Button
		 */
		String buttonNormalStyle();
		String buttonHoldPressStyle();
	}
	@Source("../application/fastbutton/FastButtonStyles.css")
	FastButtonStyles fastButtonStyles();

	public interface FastCheckboxStyles extends CssResource{
		/*
		 * Fast Check Box
		 */
		String unchecked();
		String text();
		String container();
	}
	@Source("../application/fastbutton/FastCheckboxStyles.css")
	FastCheckboxStyles fastCheckboxStyles();

	public interface FastToggleCheckboxStyles extends CssResource{
		/*
		 * Fast Toggle Checkbox
		 */
		String unchecked();
		String text();
		String container();
	}
	@Source("../application/fastbutton/FastToggleCheckboxStyles.css")
	FastToggleCheckboxStyles fastToggleCheckboxStyles();



	public interface Styles extends CssResource {

		/* Main View */
		String pageloadFadeIn();
		String menuHoldPress();
		String showhideErrorCloseNormal();
		String showhideErrorCloseHoldPress();
		String menuNormal();
		String buttonNormal();
		String buttonHoldPress();
		String showhideLeftMenuNormal();
		String showhideLeftMenuHoldPress();
		String showhideRightMenuNormal();
		String showhideRightMenuHoldPress();
		String makePanelTransparent();
		String leftPagerPanel();

		String userSelectNone(); // To protect the user to select none of the
		// elements

		String smoothScrolling(); // For smooth scrolling in iPad

		String popOverStyle(); // PopupPanel styles for all pop overs
		// (notification number)

		String alertCount(); // Alert count styles

		String headerStyles(); // Header panel styles

		String headerLabelStyles(); // Header label styles

		String fastButtonBlue(); // Action button Blue normal styles

		String fastButtonBlueHold(); // Action button Blue hold press styles

		String fastButtonBlack(); // Action button Black normal styles

		String fastButtonBlackHold(); // Action button Black hold press styles

		String fastButtonGrey(); // Action button Grey normal styles

		String fastButtonGreyHold(); // Action button Grey hold press styles

		String blackLabel(); // Non clickable black label

		String redLabel(); // Non clickable red label

		String lightGreyLabel(); // Non clickable light grey label

		String darkGreyLabel(); // Non clickable dark grey label

		String navigationArrow(); // Navigation arrow >

		String navigationArrowHold(); // Navigation arrow > hold press styles

		String navigationText(); // Navigation arrow >

		String navigationTextHold(); // Navigation arrow > hold press styles

		String arrowToRight(); // Navigation arrow <

		String arrowToRightHold(); // Navigation arrow < hold press styles

		String arrowToLeft(); // Navigation arrow <

		String arrowToLeftHold(); // Navigation arrow < hold press styles

		String textArea(); // TextArea styles

		String selectionTickButton(); // Selection tick button styles
		// (non-active)

		String selectionTickButtonActive(); // Selection tick button styles
		// (active)

		String categoryTitle(); // Category title styles

		String borderBottom(); // To give border bottom for every list row

		String navigationPanel();

		String iconBlue();

		String iconBlueHold();

		String soapSubHeaderStyles();

		String soapHeaderLabelStyles();

		String loadingImage();

		String glaceIcons();
		String datePopupGlass();

	}
	@Source("../css/tabletstyles.css")
	Styles tabletstyles();

	public interface GlaceAlertStyle extends CssResource {
		String popupStyle();		
		String popupHolder();
		String messageLabel();
		String buttonStyle();		
		String buttonHoldStyle();
		String buttonHoldStyleOk();
		String buttonHoldStyleCancel();
		String buttonStyleOk();
		
	}

	@Source("../application/reachtodayalert/GlaceAlert.css")
	GlaceAlertStyle glaceAlertStyle();
	
	public interface SearchControlStyles extends CssResource {
		String searchClose();
		String searchClosePress();
		String search();
	}
	@Source("../application/searchcontrol/searchcontrolstyles.css")
	SearchControlStyles searchControlStyles();

	@Source("../images/check.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource unCheckedImage();

	@Source("../images/check-tic.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource checkedImage();

	@Source("../images/searchGrey.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource searchGrey();

	@Source("../images/searchClose.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource searchClose();

	@Source("../images/processing.gif")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource processing();

	@Source("../images/menuiconright.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource menuiconright();

	@Source("../images/home.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource home();

	@Source("../images/message_notification.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource messagenotification();

	@Source("../images/menuicon.png")
	@ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
	ImageResource menuicon();



}

