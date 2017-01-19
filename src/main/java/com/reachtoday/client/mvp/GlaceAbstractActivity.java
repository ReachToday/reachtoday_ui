package com.reachtoday.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.reachtoday.client.application.main.events.WindowResizeEvent;
import com.reachtoday.client.application.main.events.WindowResizeEventHandler;

/**
 * Wrapper for AbstractActivity to handle the resizing of Winodw.
 * @author software
 *
 */
public abstract class GlaceAbstractActivity extends AbstractActivity {
	private EventBus eventBus;

	@Inject
	public GlaceAbstractActivity(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
		eventBus.addHandler(WindowResizeEvent.TYPE, new WindowResizeEventHandler() {

			@Override
			public void onResize(final WindowResizeEvent windowResizeEvent) {
				windowResizeEvent.getPanel().getElement().getStyle().setHeight(0.93 * ((double) windowResizeEvent.getHeight()), Unit.PX);
				windowResizeEvent.getPanel().getElement().getStyle().setWidth((double) windowResizeEvent.getWidth(), Unit.PX);
			}
		});
	}

	/**
	 * Getter for mainDisplayPanel height.
	 * @return
	 */
	public static int getHeight() {
		int height = Window.getClientHeight() - 43;
		return height;
	}

}
