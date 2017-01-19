package com.reachtoday.client.application.main.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Panel;

public class WindowResizeEvent extends GwtEvent<WindowResizeEventHandler> {
	public static Type<WindowResizeEventHandler> TYPE = new Type<WindowResizeEventHandler>();

	private Panel panel;
	private int height;
	private int width;

	public WindowResizeEvent(Panel panel, int heigth,int width) {
		this.panel =panel;
		this.height = heigth;
		this.width = width;
	}

	public Panel getPanel(){
		return panel;
	}
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public Type<WindowResizeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(WindowResizeEventHandler handler) {
		handler.onResize(this);

	}

}
