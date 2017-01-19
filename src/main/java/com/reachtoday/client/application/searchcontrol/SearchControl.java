package com.reachtoday.client.application.searchcontrol;

import java.util.ArrayList;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.fastbutton.PressHandler;
import com.reachtoday.client.resource.CellListStyle;
import com.reachtoday.client.resource.TabletResources;


/**
 *  @author software 
 *  
 *  
 *  <ul>
 *  <li>
 *  	<b>Module</b>: Search control widget <br/>
 *  </li>
 *  <li>
 *  	<b>Purpose:</b> This widget can be used as a search component to lookup certain items in a database based on the keyword.
 *  </li>
 *  </ul>
 */

public class SearchControl extends Composite implements HasHandlers,SearchControlIf {
	private static SearchControlUiBinder uiBinder = GWT.create(SearchControlUiBinder.class);

	Timer t = null;
	
	@UiField
	 static TabletResources resources;
	@UiField 
	CellListStyle cellListResources;
	@UiField HTMLPanel mainPanel;
	HorizontalPanel searchControlPanel=new HorizontalPanel();
	HorizontalPanel search=new HorizontalPanel();
	HorizontalPanel searchImagePanel=new HorizontalPanel();
	HorizontalPanel closeImagePanel=new HorizontalPanel();
	private TextBox searchBox = new TextBox();
	ShowMorePagerPanel pagerPanel;
	CellList<SearchResult> cellList;
	
	Image searchImage=new Image();
	HTMLPanel noresult=new HTMLPanel("No results found...");
	FastButton close;
	
	@UiField SearchControlStyles style; 
	/**
	 * The default place holder text to display in the search box
	 */
	private String defaultPlaceHolder = new String("Enter text to search...");

	/**
	 * The place holder text to display in the search box
	 */
	private String searchPlaceHolder = defaultPlaceHolder;

	/**
	 * Number of records to be fetched during each navigation 
	 */
	private int limit = 20;

	/**
	 * Offset of the current search result
	 */
	private int offset=0;

	/**
	 * Total number of records based on the selected criteria
	 */
	private int totalCount=0;
	
	/**
	 * Height of the component in landscape mode. It will be represented in pixels
	 */
	private int height=300;
	
	/**
	 * Width of the component in percentage
	 */
	private int width=100;
	
	
	
	interface SearchControlUiBinder extends UiBinder<Widget, SearchControl> {
	}

	/**
	 *  Handler interface {@link SearchEvent} events
	 *
	 */
	
	private interface SearchHandler extends KeyUpHandler, HasHandlers{
	}
	
	/**
	 * Handler interface {@link SearchResultSelectionEvent} events
	 *
	 */
	
	private interface ResultActionHandler extends Handler, HasHandlers{
	}

	
	/**
	 * Class representing each entry in the results section.
	 * 
	 */
	
	
	
	
	static class SearchResultCell extends AbstractCell<SearchResult>{
		@Override
		public void render(Context context,SearchResult value, SafeHtmlBuilder sb) {
			if (value == null){
				return;
			}
			if(value.getValue().getKey().equalsIgnoreCase("proc")){
				Image processing = new Image();
				processing.setResource(resources.processing());
				sb.appendHtmlConstant(processing.getElement().getString());
			}else{
				if(!value.getDisplayHTML().trim().equals("") && value.getDisplayHTML()!=null){
					sb.appendHtmlConstant(value.getDisplayHTML());
				}else{
					sb.appendHtmlConstant(value.getDescription());
				}
			}
		}
	}	 

	interface SearchControlStyles extends CssResource {

		
		String searchBoxStyle();
	}
	private HandlerManager handlerManager;

	@Inject
	public SearchControl(){
		initControl();
		closeImagePanel.ensureDebugId("closeImagePanel");
		searchImagePanel.ensureDebugId("searchImagePanel");
		search.ensureDebugId("search");
		searchControlPanel.ensureDebugId("searchControlPanel");
		mainPanel.ensureDebugId("mainpanel");
		cellList.ensureDebugId("cellList");
		pagerPanel.ensureDebugId("pagerpanel");
	}
	
	/*public SearchControl(TabletResources resources,CellListStyle cellListResources){
		this.resources=resources;
		this.cellListResources=cellListResources;
		initControl();
		closeImagePanel.ensureDebugId("closeImagePanel");
		searchImagePanel.ensureDebugId("searchImagePanel");
		search.ensureDebugId("search");
		searchControlPanel.ensureDebugId("searchControlPanel");
		mainPanel.ensureDebugId("mainpanel");
		cellList.ensureDebugId("cellList");
		pagerPanel.ensureDebugId("pagerpanel");
	}*/
	
	public SearchControl(int height, int width){
		this.setHeight(height);
		this.setWidth(width);
		initControl();
		closeImagePanel.ensureDebugId("closeImagePanel");
		searchImagePanel.ensureDebugId("searchImagePanel");
		search.ensureDebugId("search");
		searchControlPanel.ensureDebugId("searchControlPanel");
		mainPanel.ensureDebugId("mainpanel");
		cellList.ensureDebugId("cellList");
		pagerPanel.ensureDebugId("pagerpanel");
	}

	
	/**
	 * This function will initiate this control with the default values.
	 */
	
	private void initControl(){
		initWidget(uiBinder.createAndBindUi(this));
		cellListResources.cellliststyle().ensureInjected();
		StyleInjector.injectAtEnd("@media screen and (orientation:portrait){.pager{height:"+(this.getHeight() + (this.getHeight()*0.25))+"px;z-index:200;}}");
		StyleInjector.injectAtEnd("@media screen and (orientation:landscape){.pager{height:"+this.getHeight()+"px;z-index:200;}}");
		pagerPanel = new ShowMorePagerPanel(this.getLimit());
		handlerManager = new HandlerManager(this);
	/*	searchBox.getElement().setAttribute("type", "search");*/
		searchBox.getElement().setAttribute("placeholder", this.getSearchPlaceHolder());
		searchBox.setHeight("30px");
		close=new FastButton("",resources.searchControlStyles().searchClose(), resources.searchControlStyles().searchClosePress(),"");
		close.setBackgroundImage(resources.searchClose());
		closeImagePanel.add(close);
		
		searchImage.setResource(resources.searchGrey());
		searchImage.setStyleName(resources.searchControlStyles().search());
		searchImagePanel.add(searchImage);
		searchImagePanel.getElement().setAttribute("style", "float:right");
		closeImagePanel.getElement().setAttribute("style","float:right");
		
				
		search.getElement().setAttribute("style", "font-size:15px !important;display:block");
		searchControlPanel.getElement().setAttribute("style", "font-size:15px !important;width:100%;border:1px solid #CDCDCD;background-color:#FFFFFF;");
		
		searchBox.addKeyUpHandler(new SearchHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				//Clearing the previous search results list
				cellList.setRowCount(0);
				
				offset=0;
				mainPanel.remove(noresult);
				pagerPanel.scrollToTop();

				//Firing the search event
				String searchString = ((TextBox)event.getSource()).getValue();
				if(searchString.trim().equals("")){
					SearchEvent searchEvent = new SearchEvent(searchString);
					fireEvent(searchEvent);
					hideList();
				}else{
					
					final SearchEvent searchEvent = new SearchEvent(searchString);
					if (t!=null)
						t.cancel();
					t = new Timer() {
					      @Override
					      public void run() {
					    	  
					    	  fireEvent(searchEvent);
					    	  offset=0;
					    	  
					      }
					    };
					 
				        
					t.schedule(1000);
				}
			
			}

			@Override
			public void fireEvent(GwtEvent<?> event) {
				handlerManager.fireEvent(event);
			}
		});
		//pagerPanel.addStyleName(resources.tabletstyles().soapPopupPanelGlass());
		pagerPanel.setWidth(this.getWidth()+"%");
		pagerPanel.setHeight(this.getHeight()+"%");
		pagerPanel.addStyleName("pager");
		
		SearchResultCell cell = new SearchResultCell();
		
		cellList = new CellList<SearchResult>(cell,SearchResult.KEY_PROVIDER);
		cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		
		cellList.addRangeChangeHandler(new RangeChangeEvent.Handler() {
			
			@Override
			public void onRangeChange(RangeChangeEvent event) {
				searchBox.setFocus(false);
			}
		});

		cellList.getElement().setAttribute("style", "color:#FFFFFF;");
		
		final SingleSelectionModel<SearchResult> selectionModel = new SingleSelectionModel<SearchResult>(SearchResult.KEY_PROVIDER);
		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new ResultActionHandler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				SearchResultSelectionEvent actionEvent = new SearchResultSelectionEvent(selectionModel.getSelectedObject().getValue());
				fireEvent(actionEvent);
			}

			@Override
			public void fireEvent(GwtEvent<?> event) {
				handlerManager.fireEvent(event);
			}
		});

		pagerPanel.setDisplay(cellList);
		pagerPanel.setVisible(false);
		search.add(searchBox);
		searchBox.getElement().setAttribute("style", "font-size:15px !important;border:1px solid #FFFFFF;vertical-align:middle;width:100%;height:24px;");
		searchBox.setStyleName(style.searchBoxStyle());
		searchBox.getElement().getParentElement().setAttribute("style", "display: block;");
		searchBox.getElement().getParentElement().getParentElement().setAttribute("style", "display: block;");
		searchBox.getElement().getParentElement().getParentElement().getParentElement().setAttribute("style", "display: block;");
		searchControlPanel.add(search);
		searchControlPanel.add(searchImagePanel);
		mainPanel.add(searchControlPanel);
		mainPanel.add(pagerPanel);
		searchControlPanel.setCellWidth(searchImagePanel, "30px");
		searchImagePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		closeImagePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		final String searchBoxStyle=searchControlPanel.getElement().getAttribute("style");
		
		searchBox.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				searchControlPanel.getElement().setAttribute("style", searchBoxStyle+"border:1px solid #119DC9 !important");
				searchControlPanel.remove(searchImagePanel);
				searchControlPanel.add(closeImagePanel);
				searchControlPanel.setCellWidth(closeImagePanel, "30px");
				searchControlPanel.setCellVerticalAlignment(closeImagePanel, HasVerticalAlignment.ALIGN_MIDDLE);
				
			}
		});
		searchBox.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				searchControlPanel.getElement().setAttribute("style", searchBoxStyle+"border:1px solid #CDCDCD !important");
				if(searchBox.getText().equals("")){
				
						searchControlPanel.remove(closeImagePanel);
						searchControlPanel.add(searchImagePanel);
						searchControlPanel.setCellWidth(searchImagePanel, "30px");
						
			}
				
			}
		});
		
	close.addPressHandler(new PressHandler() {
		
		@Override
		public void onPress(PressEvent event) {
		searchControlPanel.getElement().setAttribute("style", searchBoxStyle+"border:1px solid #CDCDCD !important");
		searchBox.setText("");
		searchBox.getElement().blur();
		pagerPanel.setVisible(false);
		searchControlPanel.remove(closeImagePanel);
		
		searchControlPanel.add(searchImagePanel);
		searchControlPanel.setCellWidth(searchImagePanel, "30px");
		mainPanel.remove(noresult);
		}
	});
		
		
	}

	/**
	 * This function will display the results in the component.
	 * 
	 * @param searchResult list of result entries.
	 */
	public void displayResult(ArrayList<SearchResult> searchResult){
		showList();
		mainPanel.remove(noresult);
		if(searchResult.isEmpty()&& offset==0){
			pagerPanel.setVisible(false);
			mainPanel.add(noresult);
		}
		cellList.setRowData(offset,searchResult);
		int newSize = offset + searchResult.size();	
		pagerPanel.setLoadingIndicator(false);
		if(newSize>=totalCount){
		
			offset=totalCount;			
		}else{
			
			offset=newSize;
			
		}
		
	}

	/**
	 * Get the current offset of the search instance.
	 * 
	 * @return
	 */
	
	public int getOffset() {
		return offset;
	}

	/**
	 * Get the total number of records for a search instance.
	 * 
	 * @return total number of records
	 */
	
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Set the total number of records for a search instance
	 * 
	 * @param total number of records
	 */
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		cellList.setRowCount(totalCount);
	}
	
	/**
	 * Displays the processing indicator at the bottom of the list.
	 */
	
	public void displayProcessingIndicator(){
		SearchResultData resultdata = new SearchResultData("proc", null);
		SearchResult processing = new SearchResult("processing", resultdata, null);
	//	SearchResult processing = new SearchResult("processing", "proc", null);
		ArrayList<SearchResult> list = new ArrayList<SearchResult>();
		list.add(processing);
		pagerPanel.setLoadingIndicator(true);
		cellList.setRowData(offset,list);
	}

	/**
	 * Get the currently entered keyword in the search box.
	 * 
	 * @return search keyword
	 */
	
	public String getSearchKeyWord(){
		return searchBox.getValue();
	}

	/**
	 * Get the number of rows by which the range is increased when the scrollbar
	 * reaches the bottom.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Set the place holder text for the search box component.
	 * 
	 * @param searchPlaceHolder place holder text.
	 */
	public void setSearchPlaceHolder(String searchPlaceHolder){
		searchBox.getElement().setAttribute("placeholder", searchPlaceHolder);
	}

	/**
	 * Get the place holder text of the search box component
	 * 
	 * @return place holder text
	 */
	public String getSearchPlaceHolder(){
		return searchPlaceHolder;
	}

	/**
	 * Adds a {@link SearchEvent} handler
	 * 
	 * @param handler the search event handler
	 * @return {@link HandlerRegistration}
	 */
	
	public HandlerRegistration addSearchEventHandler(SearchEventHandler handler){
		return handlerManager.addHandler(SearchEvent.TYPE, handler);
	}

	/**
	 * Adds a {@link NavigationEvent} handler
	 * 
	 * @param handler the navigation event handler
	 * @return {@link HandlerRegistration}
	 */
	
	public HandlerRegistration addNavigationEventHandler(NavigationEventHandler handler){
		return pagerPanel.addNavigationEventHandler(handler);
	}
	
	/**
	 * Adds a {@link SearchResultSelectionEvent} handler
	 * 
	 * @param handler the search result action event handler
	 * @return {@link HandlerRegistration}
	 */
	public HandlerRegistration addSearchResultSelectionEventHandler(SearchResultSelectionEventHandler handler){
		return handlerManager.addHandler(SearchResultSelectionEvent.TYPE, handler);
	}
	
	/**
	 * Hides the results list of the widget.
	 */
	public void hideList(){
		pagerPanel.setVisible(false);
	}

	/**
	 * Displays the results list of the widget.
	 */	
	public void showList(){
		pagerPanel.setVisible(true);
	}


	/**
	 * @return the height of the component in pixels. 
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height sets the height of the component in pixels.
	 */
	public void setHeight(int height) {
		this.height = height;
		pagerPanel.setHeight(height + "%");
	}

	/**
	 * @return the height of the component in percentage.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width sets the width of the component in percentage.
	 */
	public void setWidth(int width) {
		this.width = width;
		pagerPanel.setWidth(width + "%");
	}

	/**
	 * @param selectedValue sets the value of the search box widget
	 */
	public void setSearchBoxValue(String selectedValue) {
		searchBox.setValue(selectedValue);
	}
	
	public TextBox getTextBox(){
		
		return searchBox;
	}
	
	public void makeOffsetZero(){
		
		offset =0;
	}
	public ShowMorePagerPanel getPagerPanel(){
		return pagerPanel;
	}
	public CellList<SearchResult> getCellList()
	{
		return cellList;
	}
	public HorizontalPanel getSearchPanel(){
		return searchControlPanel;
	}
	
	public HorizontalPanel getSearchImagePanel(){
		return searchImagePanel;
	}
	
	public HorizontalPanel getCloseImagePanel(){
		return closeImagePanel;
	}
	
	
	public Image getSearchImage(){
		return searchImage;
	}
	public FastButton getSearchClose(){
		return close;
	}
	
	public HTMLPanel getMainPanel(){
		return mainPanel;
	}
	public HTMLPanel getNoresultPanel(){
		return noresult;
	}
	
	public FastButton getCloseButton(){
		return close;
	}

	public HandlerRegistration addEventHandler(EventHandler eventHandler) {
		if(eventHandler instanceof SearchResultSelectionEventHandler)
		return addSearchResultSelectionEventHandler((SearchResultSelectionEventHandler) eventHandler);
		else if(eventHandler instanceof NavigationEventHandler)
			return addNavigationEventHandler((NavigationEventHandler)eventHandler);
		else if(eventHandler instanceof SearchEventHandler)
			return addSearchEventHandler((SearchEventHandler)eventHandler);
			else
				return null;
	}
}
