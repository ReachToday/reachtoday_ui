package com.reachtoday.client.application.searchcontrol;

import com.google.gwt.event.shared.EventHandler;

/**
 *  @author software
 *  
 *  
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Search control widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> Handler interface for {@link NavigationEvent} events.
 *  </li>
 *  </ul>
 */

public interface NavigationEventHandler extends EventHandler {
  /**
   * Fired when current view of the results reached the end.
   * 
   * @param event the {@link NavigationEvent} that was fired
   */
	void onNavigation(NavigationEvent event);
}
