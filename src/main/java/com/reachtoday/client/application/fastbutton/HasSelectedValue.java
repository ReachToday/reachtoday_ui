package com.reachtoday.client.application.fastbutton;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
 
/**
 * MVP-friendly interface for use with any widget that can be populated
 * with a Collection of items, one of which may be selected
 *
 * 
 *
 * @param <T>
 */
public interface HasSelectedValue 
{
	
	/**
	   * Adds an item to the list box. This method has the same effect as
	   * 
	   * <pre>
	   * addItem(item, item)
	   * </pre>
	   * 
	   * @param item the text of the item to be added
	   */
	 public void addItem(String item);
	 /**
	  * Adds an item to the list box, specifying an initial value for the item.
	  *
	  * @param item the text of the item to be added
	  * @param value the item's value, to be submitted if it is part of a
	  *          {@link FormPanel}; cannot be <code>null</code>
	  */
	 public void addItem(String item, String value);
	 /**
	   * Removes all items from the list box.
	 */
	 public void clear();
	 /**
	   * Gets the number of items present in the list box.
	   * 
	   * @return the number of items
	   */
	 public int getItemCount();
	 /**
      * Gets the value associated with the item at a given index.
      *
      * @param index the index of the item to be retrieved
      * @return the item's associated value
      * @throws IndexOutOfBoundsException if the index is out of range
      */
     public String getValue(int index);
	 /**
	   * Gets the text associated with the item at the specified index.
	   * 
	   * @param index the index of the item whose text is to be retrieved
	   * @return the text associated with the item
	   * @throws IndexOutOfBoundsException if the index is out of range
	   */
	 public String getItemText(int index);
	 
	  /**
	   * Gets the currently-selected item. If multiple items are selected, this
	   * method will return the first selected item ({@link #isItemSelected(int)}
	   * can be used to query individual items).
	   * 
	   * @return the selected index, or <code>-1</code> if none is selected
	   */
	  public int getSelectedIndex();
	  /**
	   * Inserts an item into the list box. Has the same effect as
	   * 
	   * <pre>
	   * insertItem(item, item, index)
	   * </pre>
	   * 
	   * @param item the text of the item to be inserted
	   * @param index the index at which to insert it
	   */
	  public void insertItem(String item, int index);
	  
	  /**
	   * Removes the item at the specified index.
	   *
	   * @param index the index of the item to be removed
	   * @throws IndexOutOfBoundsException if the index is out of range
	   */
	  public void removeItem(int index);
	  /**
	   * Sets whether an individual list item is selected.
	   * 
	   * <p>
	   * Note that setting the selection programmatically does <em>not</em> cause
	   * the {@link ChangeHandler#onChange(ChangeEvent)} event to be fired.
	   * </p>
	   * 
	   * @param index the index of the item to be selected or unselected
	   * @param selected <code>true</code> to select the item
	   * @throws IndexOutOfBoundsException if the index is out of range
	   */
	  public void setItemSelected(int index, boolean selected);
	  /**
	   * Sets the currently selected index.
	   * 
	   * After calling this method, only the specified item in the list will remain
	   * selected. For a ListBox with multiple selection enabled, see
	   * {@link #setItemSelected(int, boolean)} to select multiple items at a time.
	   * 
	   * <p>
	   * Note that setting the selected index programmatically does <em>not</em>
	   * cause the {@link ChangeHandler#onChange(ChangeEvent)} event to be fired.
	   * </p>
	   * 
	   * @param index the index of the item to be selected
	   */
	  public void setSelectedIndex(int index);
	  
	  public HandlerRegistration addChangeHandler(ChangeHandler handler) ;
	 
}