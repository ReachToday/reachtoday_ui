package com.reachtoday.client.application.main.events;

import com.google.gwt.event.shared.GwtEvent;

public class HideProgressEvent extends GwtEvent<HideProgressEventHandler> {
	public static Type<HideProgressEventHandler> TYPE = new Type<HideProgressEventHandler>();
	
	@Override
	public Type<HideProgressEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HideProgressEventHandler handler) {
		handler.hideProgress(this);
	}
}
