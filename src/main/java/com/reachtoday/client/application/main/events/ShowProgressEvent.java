package com.reachtoday.client.application.main.events;

import com.google.gwt.event.shared.GwtEvent;

public class ShowProgressEvent extends GwtEvent<ShowProgressEventHandler> {
	public static Type<ShowProgressEventHandler> TYPE = new Type<ShowProgressEventHandler>();
	private final String message;
	private final int flag;

	public ShowProgressEvent(String message, int flag) {
	
		this.message = message;
		this.flag = flag;
	}

	
	public String getMessage() {
		return message;
	}
	
	public int getFlag() {
		return flag;
	}

	@Override
	public Type<ShowProgressEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowProgressEventHandler handler) {
		handler.showProgress(this);
	}
}
