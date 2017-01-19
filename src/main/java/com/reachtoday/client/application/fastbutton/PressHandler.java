package com.reachtoday.client.application.fastbutton;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for event handlers.
 */
public interface PressHandler extends EventHandler {
  void onPress(PressEvent event);

}
