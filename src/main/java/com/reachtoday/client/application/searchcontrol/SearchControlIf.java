package com.reachtoday.client.application.searchcontrol;

import java.util.ArrayList;

/**
 * @author Harikishore
 * Used to Bind the Search Control in order to check the CallBack Properties
 */
public interface SearchControlIf {
	
	public String getSearchKeyWord();
	public int getLimit();
	public void displayResult(ArrayList<SearchResult> searchResult);
	public void setTotalCount(int totalCount);
}
