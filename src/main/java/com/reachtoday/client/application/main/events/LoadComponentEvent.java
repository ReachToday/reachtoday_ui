package com.reachtoday.client.application.main.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoadComponentEvent extends GwtEvent<LoadComponentEventHandler> {
	public static Type<LoadComponentEventHandler> TYPE = new Type<LoadComponentEventHandler>();

	private String componentName;
	private String componentId;
	private String baseComponent;

	public LoadComponentEvent(String componentName,String baseComponent) {
		this.componentName = componentName;
		this.baseComponent = baseComponent;
	}

	public LoadComponentEvent(String componentName, String componentId,String baseComponent) {
		this.componentName = componentName;
		this.componentId = componentId;
		this.baseComponent = baseComponent;
	}

	public String getComponentId() {
		return componentId;
	}
	
	public String getBaseComponent() {
		return baseComponent;
	}

	public String getComponentName() {
		return componentName.replaceAll(" ", "");
	}

	@Override
	public Type<LoadComponentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadComponentEventHandler handler) {
		handler.onLoadComponent(this);
	}
}
