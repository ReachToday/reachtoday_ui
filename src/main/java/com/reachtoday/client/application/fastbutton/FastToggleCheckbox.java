package com.reachtoday.client.application.fastbutton;



import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.resource.TabletResources;

/**
 * This class is a custom widget which handles the toggle checkbox functionality in the project
 * @author software
 *
 */
public class FastToggleCheckbox extends FastTogglePressElement implements HasCheckValue {

  private static FastPressElementUiBinder uiBinder = GWT.create(FastPressElementUiBinder.class);

  interface FastPressElementUiBinder extends UiBinder<Widget, FastToggleCheckbox> {
  }
  
  @UiField HTMLPanel checkbox;
  @UiField Label text;
  @UiField TabletResources resources;
  
  private String value;
  private ImageResource checkedimg;
  private ImageResource uncheckedimg;

  /**
 	 * Constructor 1
 	 */
  public FastToggleCheckbox(){
    initWidget(uiBinder.createAndBindUi(this));
    checkedimg=resources.checkedImage();
    uncheckedimg=resources.unCheckedImage();
  
    checkbox.getElement().setAttribute("checked", "no");
    checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
   }
  
  /**
	 * Parameterized Constructor 1
	 * @param text
	 * 			Text to be set for the toggle checkbox
	 * @param value
	 * 			value to be set
	 * @param isToggle
	 * 			sets the isToggle attribute for the toggle checkbox
	 */
  public FastToggleCheckbox(String text,String value,String isToggle){
	  initWidget(uiBinder.createAndBindUi(this));
	    checkedimg=resources.checkedImage();
	    uncheckedimg=resources.unCheckedImage();
	    checkbox.getElement().setAttribute("isToggle", isToggle);
	    checkbox.getElement().setAttribute("checked", "no");
	    checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
	    this.text.setText(text);
	    this.value=value;
	  }
  
  /**
   * Setter to set the text for the toggle checkbox
   * @param text 
   * 		text to be set for the toggle checkbox
   */
  public void setText(String text) {
	  this.text.setText(text);
	}
  
  /**
   * Setter to set the value for the toggle heckbox
   * @param value
   * 		value to be set for the toggle checkbox
   */
  public void setValue(String value) {
	  this.value=value;
	}
  
  /**
   * Getter to get the value of the toggle checkbox
   * @return {@link String}
   * 		returns the value of the toggle checkbox
   */
  public  String getValue() {
	  return value;
	}
  
  /**
   * Getter to get the text of the toggle checkbox
   * @return {@link String}
   * 		returns the text of the toggle checkbox
   */
  public  String getText() {
	  return this.text.getText();
	}
  
  /**
   * Getter to get the value of the toggle checkbox
   * @return {@link String}
   * 		returns the value of the toggle checkbox
   */
  public String getValue(String text) {
	  return this.text.getText();
	}
  
  /**
   * Setter method to set the title of the toggle checkbox
   * @param title
   * 		title to be set for the toggle checkbox
   */
  public void setTitle(String title) {
	  this.checkbox.setTitle(title);
 }
  
  /**
   * Method to verify whether the toggle checkbox is checked or not
   * @return {@link Boolean}
   * 			returns whether the toggle checkbox is checked or not 
   */
  public Boolean isChecked() {
	  
	  if (checkbox.getElement().getAttribute("checked").equals("yes"))
		  return true;
	  else
		  return false;
	}
  
  /**
   * Method to check or uncheck the toggle checkbox
   * @param checked
   * 		"yes" is passed if the toggle checkbox is to be checked
   */
  public void setChecked(String checked) {
	  
	  if(checked.equals("yes")){
		    checkbox.getElement().setAttribute("checked", "yes");
			checkbox.getElement().getStyle().setBackgroundImage("url(" +checkedimg.getSafeUri().asString() + ")");
	  }		
		else{
			checkbox.getElement().setAttribute("checked", "no");
			checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
		}
	  
  }
  
  /**
   * Method to set the attribute "isToggle"
   * @param isToggle
   * 		value of the attribute "isToggle"
   */
  public void setIsToggle(String isToggle) {
	  checkbox.getElement().setAttribute("isToggle", isToggle);
	}

/**
 * To perform toggle function on fasttogglecheckbox
 * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressDownStyle()
 */
@Override
  public void onHoldPressDownStyle() {	
		if(checkbox.getElement().getAttribute("checked").equalsIgnoreCase("no")){	
		checkbox.getElement().getStyle().setBackgroundImage("url(" +checkedimg.getSafeUri().asString() + ")");
		checkbox.getElement().setAttribute("checked", "yes");
		NodeList divElements=this.getElement().getParentNode().getParentElement().getParentElement().getParentElement().getElementsByTagName("div");
		  for(int i=0;i<divElements.getLength(); i++){
			  Element divElement = (Element)divElements.getItem(i);
			  if(divElement !=checkbox.getElement() && divElement.getAttribute("isToggle").equals("yes")){
				  divElement.getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
				  divElement.setAttribute("checked", "no");				  
			  }
			}				 
		}
		else if(checkbox.getElement().getAttribute("checked").equalsIgnoreCase("yes"))
		{
			checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
			checkbox.getElement().setAttribute("checked", "no");
		}		
	}

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

  private void clearStyles(){
	    // TODO Auto-generated method stub
  }


}
