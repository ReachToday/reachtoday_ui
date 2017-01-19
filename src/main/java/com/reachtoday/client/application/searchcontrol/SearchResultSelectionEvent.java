package com.reachtoday.client.application.searchcontrol;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.GwtEvent;

/**
 *  @author Ganeshram Sivashanmugam 
 *  
 *  
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Search control widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> Represents a selection event in the search control results section.
 *  </li>
 *  </ul>
 */

public class SearchResultSelectionEvent extends GwtEvent<SearchResultSelectionEventHandler> {
	/**
	 * Event type for result selection events. Represents the meta-data associated with this
	 * event.
	 */	
	public static Type<SearchResultSelectionEventHandler> TYPE = new Type<SearchResultSelectionEventHandler>();

	/**
	 * Value of the selected entry in the results list.
	 */
    //private final String selectedValue;
	private final SearchResultData selectedValue;
    
    /**
     * Gets the value of the selected entry in the results list.
     * @return
     */
  /*  public String getSelectedValue() {
        return selectedValue;
    }*/
	 
	 public SearchResultData getSelectedValue() {
	        return selectedValue;
	    }


    /**
     * Protected constructor, use
     * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
     * to fire reslt selection events.
     */     
    /*protected SearchResultSelectionEvent(String selectedValue) {
        this.selectedValue = selectedValue;
    }*/
	 protected SearchResultSelectionEvent(SearchResultData selectedValue) {
	        this.selectedValue = selectedValue;
	    }



    @Override
    public Type<SearchResultSelectionEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SearchResultSelectionEventHandler handler) {
        handler.onSelect(this);
    }


}
