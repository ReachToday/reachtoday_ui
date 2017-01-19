package com.reachtoday.client.application.fastbutton;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * An object that implements this interface has a collection of event handlers
 * associated with it.
 */
public interface HasPressHandlers extends HasHandlers {
  HandlerRegistration addPressHandler(PressHandler handler);

}
