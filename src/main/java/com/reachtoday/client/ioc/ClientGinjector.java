package com.reachtoday.client.ioc;




import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.MainPresenter;
import com.reachtoday.client.resource.CSSInjector;

public interface ClientGinjector extends Ginjector {
	PlaceHistoryHandler getPlaceHistoryHandler();
	CSSInjector getCSSInjector();
	MainPresenter getMainPresenter();
	EventBus getEventBus();
	ActionServletURL getActionServletURL();
}
