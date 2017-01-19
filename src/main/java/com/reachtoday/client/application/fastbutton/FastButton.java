package com.reachtoday.client.application.fastbutton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.reachtoday.client.resource.TabletResources;

/**
 * This class is a custom widget which handles the button functionality in the project
 * @author software
 *
 */
public class FastButton extends FastPressElement implements HasFastValue {

	private static FastPressElementUiBinder uiBinder = GWT.create(FastPressElementUiBinder.class);

	interface FastPressElementUiBinder extends UiBinder<Widget, FastButton> {
	}

	interface fastbuttonStyle extends CssResource {
		String buttonNormalStyle();
		String buttonHoldPressStyle();
	}

	@UiField InlineLabel text;
	@UiField HTMLPanel panel;
	@UiField TabletResources resources;
	private String normalStyle;
	private String holdPressStyle;
	private String value = "";
	String id="";

	/**
	 * Constructor 1
	 */
	public FastButton() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Parameterized Constructor 1
	 * @param text
	 * 			Text to be set in the fast button
	 * @param normalStyle
	 * 			Style to be set when the button is not pressed
	 * @param normalStyle
	 * 			Style to be set when the button is pressed
	 * @param value
	 * 			value to be set
	 */
	public FastButton(String text, String normalStyle, String holdPressStyle,
			String value) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text.setText(text);
		if((normalStyle.equalsIgnoreCase("")||holdPressStyle.equalsIgnoreCase(""))){
			this.normalStyle = resources.fastButtonStyles().buttonNormalStyle();
			this.holdPressStyle = resources.fastButtonStyles().buttonHoldPressStyle();
			this.value = value;
			addStyleName(resources.fastButtonStyles().buttonNormalStyle());
		}else
		{
			
			this.normalStyle = normalStyle;
			this.holdPressStyle = holdPressStyle;
			this.value = value;
			addStyleName(normalStyle);
		}
		

	}

	/**
	 * Setter method to set the visibility(show or hide the button)
	 * @param visible
	 * 		   value to set the visibility(show or hide the button)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
	/**
	 * Setter method to set the style name
	 * @param style
	 * 			style name to be set 
	 * 			
	 */
	public void setHTMLPanelStyle(String style) {
		this.panel.addStyleName(style);
	}

	/**
	 * Setter method to set the text within the fast button
	 * @param text
	 * 			text to be set within the fast button
	 */
	public void setText(String text) {
		this.text.setText(text);

	}

	/**
	 * Getter method to get the text from the fast button
	 * @return {@link String}
	 * 			returns the text from the fast button
	 */
	public String getText() {
		return this.text.getText();
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
	 * 			background image to be set to the fast button
	 */
	public void setBackgroundImage(ImageResource image) {
		this.getElement()
				.getStyle()
				.setBackgroundImage(
						"url(" + image.getSafeUri().asString() + ")");
	}

	/**
	 * Setter method to set the styles when the fast button is not pressed
	 * @param normalStyle
	 * 			style name to be set when the fast button is not pressed
	 */
	public void setNormalStyle(String normalStyle) {
		this.normalStyle = normalStyle;
		addStyleName(normalStyle);
	}

	/**
	 * Setter method to set the styles when the fast button is pressed
	 *  @param holdPressStyle
	 * 			style name to be set when the fast button is pressed
	 */
	public void setHoldPressStyle(String holdPressStyle) {
		this.holdPressStyle = holdPressStyle;

	}

	/**
	 * Setter method to set the value
	 * @param value
	 * 			value to be set
	 */
	public void setValue(String value) {
		this.value = value;

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
	 * Getter method to get the title
	 */
	public String getTitle() {
		return this.text.getTitle();

	}

	/**
	 * Implements the handler to set the styles when the button is not released after pressing
	 * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressDownStyle()
	 */
	@Override
	public void onHoldPressDownStyle() {
		clearStyles();
		addStyleName(holdPressStyle);
	}

	/**
	 * Implements the handler to set the styles when the button is released after pressing
	 * @see com.reachtoday.client.application.fastbutton.FastPressElement#onHoldPressOffStyle()
	 */
	@Override
	public void onHoldPressOffStyle() {
		clearStyles();
		addStyleName(normalStyle);

	}

	@Override
	public void onDisablePressStyle() {

	}

	@Override
	public void onEnablePressStyle() {

	}

	/**
	 * Method to clear the styles
	 */
	private void clearStyles() {
		removeStyleName(normalStyle);
		removeStyleName(holdPressStyle);
	}

	/**
	 * Method to get the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
