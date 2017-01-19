package com.reachtoday.client.application.fastbutton;



import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.resource.TabletResources;

/**
 * This class is a custom widget which handles the checkbox functionality in the project
 * @author software
 *
 */
public class FastCheckbox extends FastTogglePressElement implements HasCheckValue{

  private static FastPressElementUiBinder uiBinder = GWT.create(FastPressElementUiBinder.class);

  interface FastPressElementUiBinder extends UiBinder<Widget, FastCheckbox> {
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
  public FastCheckbox(){
    initWidget(uiBinder.createAndBindUi(this));
    checkedimg=resources.checkedImage();
    uncheckedimg=resources.unCheckedImage();
    checkbox.getElement().setAttribute("checked", "no");
    checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
   }
 
  /**
	 * Parameterized Constructor 1
	 * @param text
	 * 			Text to be set for the checkbox
	 * @param value
	 * 			value to be set
	 */
  public FastCheckbox(String text,String value){
      initWidget(uiBinder.createAndBindUi(this));
        checkedimg=resources.checkedImage();
        uncheckedimg=resources.unCheckedImage();
        checkbox.getElement().setAttribute("checked", "no");
        checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
        this.text.setText(text);
        this.value=value;
      }
  
  /**
 	 * Parameterized Constructor 2
 	 * @param checked
 	 * 			Sets whether the checkbox should be checked initially or not
 	 */
  public FastCheckbox(String checked){
	  if (checked.equals("yes")) {
		  initWidget(uiBinder.createAndBindUi(this));
	        checkedimg=resources.checkedImage();        
	        checkbox.getElement().getStyle().setBackgroundImage("url(" +checkedimg.getSafeUri().asString() + ")");	        
	  } else {
		  initWidget(uiBinder.createAndBindUi(this));	        
	      uncheckedimg=resources.unCheckedImage();
	      checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");	      
	  }      
   }
 
  /**
   * Setter to set the text for the checkbox
   * @param text 
   * 		text to be set for the checkbox
   */
  public void setText(String text) {
      this.text.setText(text);
    }
 
  /**
   * Setter to set the value for the checkbox
   * @param value
   * 		value to be set for the checkbox
   */
  public void setValue(String value) {
      this.value=value;
    }
 
  /**
   * Getter to get the value of the checkbox
   * @return {@link String}
   * 		returns the value of the checkbox
   */
  public  String getValue() {
      return value;
    }
 
  /**
   * Getter to get the text of the checkbox
   * @return {@link String}
   * 		returns the text of the checkbox
   */
  public  String getText() {
      return this.text.getText();
    }
  
  /**
   * Getter to get the value of the checkbox
   * @return {@link String}
   * 		returns the value of the checkbox
   */
  public String getValue(String text) {
      return this.text.getText();
    }
 
 /**
  * Setter method to set the title of the checkbox
  * @param title
  * 		title to be set for the checkbox
  */
  public void setTitle(String title) {
      this.checkbox.setTitle(title);
 }
 
/**
 * Method to verify whether the checkbox is checked or not
 * @return {@link Boolean}
 * 			returns whether the checkbox is checked or not 
 */
  public Boolean isChecked() {
      
      if (checkbox.getElement().getAttribute("checked").equals("yes"))
          return true;
      else
          return false;
    }
 
 /**
  * Method to check or uncheck the checkbox
  * @param checked
  * 		"yes" is passed if the checkbox is to be checked
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
      this.getElement().setAttribute("isToggle", isToggle);
    }
 
/**
 * Implements the handler to set the styles when the checkbox is not released after pressing
 * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressDownStyle()
 */
@Override
  public void onHoldPressDownStyle() {
	
    if(checkbox.getElement().getAttribute("checked").equals("yes")){
        checkbox.getElement().getStyle().setBackgroundImage("url(" +uncheckedimg.getSafeUri().asString() + ")");
        checkbox.getElement().setAttribute("checked", "no");
        
    }
    else{
        checkbox.getElement().getStyle().setBackgroundImage("url(" +checkedimg.getSafeUri().asString() + ")");
        checkbox.getElement().setAttribute("checked", "yes");
        
    }
        
    }

/**
 * Implements the handler to set the styles when the checkbox is released after pressing
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

  private void clearStyles(){
        // TODO Auto-generated method stub
  }
}