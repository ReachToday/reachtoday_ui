package com.reachtoday.client.application.fastbutton;



import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
/**
 * This class is a custom widget which handles the toggle button functionality in the project
 * @author software
 *
 */
public class FastToggleButton extends FastTogglePressElement implements HasFastValue {

  private static FastPressElementUiBinder uiBinder = GWT.create(FastPressElementUiBinder.class);

  interface FastPressElementUiBinder extends UiBinder<Widget, FastToggleButton> {
  }
  

  @UiField InlineLabel text;
  @UiField HTMLPanel panel;
  private String normalStyle;
  private String holdPressStyle;
  private String isToggle;
  private String isClosable;
  private String value="";

  /**
	 * Constructor 1
	 */
  public FastToggleButton(){
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  /**
	 * Parameterized Constructor 1
	 * @param text
	 * 			Text to be set in the fast toggle button
	 * @param normalStyle
	 * 			Style to be set when the toggle button is not pressed
	 * @param normalStyle
	 * 			Style to be set when the toggle button is pressed
	 * @param value
	 * 			value to be set
	 */
  public FastToggleButton(String text,String normalStyle,String holdPressStyle,String isToggle,String isClosable,String value){
      initWidget(uiBinder.createAndBindUi(this));
	  this.text.setText(text);
	  this.normalStyle=normalStyle;
	  this.holdPressStyle=holdPressStyle;
	  this.isToggle=isToggle;
	  this.isClosable=isClosable;
	  this.value=value;
	  addStyleName(normalStyle);
	  this.addPressHandler(new PressHandler() {
		
		@Override
		public void onPress(PressEvent event) {
			
		}
	});
	   
	     }

  /**
	 * Setter method to set the text within the fast toggle button
	 * @param text
	 * 			text to be set within the fast toggle button
	 */
  public void setText(String text) {
	  this.text.setText(text);
	}
  
  /**
	 * Setter method to set the value
	 * @param value
	 * 			value to be set
	 */
  public void setValue(String value) {
	  this.value=value;
	}
  
  /**
	 * Getter method to get the value
	 * @return {@link String}
	 * 			returns the value which has been set
	 */
  public String getValue() {
	  return value;
	}
  
  /**
	 * Setter method to set the title
	 * @param title 
	 * 			title to be set
	 */
  public void setTitle(String title) {
	  this.text.setTitle(title);
	}
  
  /**
	 * Setter method to set the background image
	 * @param image
	 * 			background image to be set to the fast toggle button
	 */
  public void setBackgroundImage(ImageResource image) {
	  this.getElement().getStyle().setBackgroundImage("url(" +image.getSafeUri().asString() + ")");
	  
  }
  
  /**
	 * Setter method to set the styles when the fast toggle button is not pressed
	 * @param normalStyle
	 * 			style name to be set when the fast toggle button is not pressed
	 */
  public void setNormalStyle(String normalStyle) {
	  this.normalStyle=normalStyle;
	  addStyleName(normalStyle);
  }
  
  /**
	 * Setter method to set the styles when the fast toggle button is pressed
	 *  @param holdPressStyle
	 * 			style name to be set when the fast toggle button is pressed
	 */
  public void setHoldPressStyle(String holdPressStyle) {
	  this.holdPressStyle=holdPressStyle;
	  
  }
  
  /**
   * Method to set the attribute "isToggle"
   * @param isToggle
   * 		value of the attribute "isToggle"
   */
  public void setIsToggle(String isToggle) {
	  this.getElement().setAttribute("isToggle", isToggle);
	}
  
  /**
   * Method to set the attribute "isClosable"
   * @param isClosable
   * 		value of the attribute "isClosable"
   */
  public void setIsClosable(String isClosable) {
	  if(isClosable.equals("yes"))
		  this.getElement().setAttribute("isClosable", "no");
	}
  
  /**
   * Implements the handler to set the styles when the button is not released after pressing
   * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressDownStyle()
   */

@Override
  public void onHoldPressDownStyle() {
	 addStyleName(holdPressStyle);
	  NodeList divElements=this.getElement().getParentNode().getParentElement().getParentElement().getParentElement().getElementsByTagName("div");
	  for(int i=0;i<divElements.getLength(); i++){
		  Element divElement = (Element)divElements.getItem(i);
		  if(divElement !=this.getElement() && divElement.getAttribute("isToggle").equals("yes"))
			  divElement.replaceClassName(divElement.getClassName(), normalStyle);
		}
  }

/**
 * Implements the handler to set the styles when the button is released after pressing
 * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressOffStyle()
 */
  @Override
  public void onHoldPressOffStyle() {
	  
	  // TODO Auto-generated method stub
  }

  @Override
  public void onDisablePressStyle() {
    // TODO Auto-generated method stub

  }


  @Override
  public void onEnablePressStyle() {
    // TODO Auto-generated method stub

  }

  /**
	 * Method to clear the styles
	 */
  private void clearStyles(){
    removeStyleName(normalStyle);
    removeStyleName(holdPressStyle);
  }

  public String getText() {
	  return this.text.getText();
  }


}
