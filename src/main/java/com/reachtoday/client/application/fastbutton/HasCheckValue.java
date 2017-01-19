package com.reachtoday.client.application.fastbutton;

import com.google.gwt.event.shared.HandlerRegistration;
import com.reachtoday.client.application.fastbutton.PressHandler;

public interface HasCheckValue {
	
	public void setText(String text);
	public void setValue(String value);
	public  String getValue();
	public  String getText();
	public String getValue(String text);
	public void setTitle(String title);
	public Boolean isChecked();
	public void setChecked(String checked);
	public void setIsToggle(String isToggle);
	public void onHoldPressDownStyle();
	public HandlerRegistration addPressHandler(PressHandler handler);
	public com.google.gwt.user.client.Element getElement() ;
}
