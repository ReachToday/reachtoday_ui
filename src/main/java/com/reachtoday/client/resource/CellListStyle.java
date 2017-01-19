package com.reachtoday.client.resource;


import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.cellview.client.CellList.Style;

/**
 * 
 * @author Ganeshram Sivashanmugam
 * 
 * CSS Resource class for GWT cell list.
 *
 */
public interface CellListStyle extends ClientBundle{
	public interface Styles extends CssResource{
		/**
		 * Return the class for styling cell list widget 
		 * @return
		 */
		String cellListWidget();
		
		/**
		 * Return the class for styling even items in the cell list widget. 
		 * @return
		 */
		String cellListEvenItem();
		
		/**
		 * Return the class for styling odd items in the cell list widget. 
		 * @return
		 */		
		String cellListOddItem();
		
		/**
		 * Return the class for styling for selected item in the cell list widget. 
		 * @return
		 */		
		String cellListKeyboardSelectedItem();
	}
	
	/**
	 * Gets the source of the css style sheet
	 * @return
	 */
	@Source("../css/cellliststyle.css")
	Style cellliststyle();
}

