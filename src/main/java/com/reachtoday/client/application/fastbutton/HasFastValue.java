package com.reachtoday.client.application.fastbutton;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.reachtoday.client.application.fastbutton.PressHandler;

public interface HasFastValue {
	public void setText(String text);
	public String getText();
	public void setTitle(String title);
	public String getTitle();
	public void setBackgroundImage(ImageResource image);
	public void setNormalStyle(String normalStyle);
	public void setHoldPressStyle(String holdPressStyle);
	public void setValue(String value);
	public String getValue();
	public void onHoldPressDownStyle();
	public void onHoldPressOffStyle();
	public HandlerRegistration addPressHandler(PressHandler handler);
	public com.google.gwt.user.client.Element getElement();
	public void setVisible(boolean visible);

}
