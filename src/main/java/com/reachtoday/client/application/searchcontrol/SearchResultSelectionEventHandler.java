package com.reachtoday.client.application.searchcontrol;

import com.google.gwt.event.shared.EventHandler;

/**
 *  @author Ganeshram Sivashanmugam 
 *  
 *  
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Search control widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> Handler interface for {@link SearchResultSelectionEvent} events.
 *  </li>
 *  </ul>
 */
public interface SearchResultSelectionEventHandler extends EventHandler {
  /**
   * Fired while selecting an entry in the result entries.
   * 
   * @param event the {@link SearchResultSelectionEvent} that was fired
   */
	void onSelect(SearchResultSelectionEvent event);
}
