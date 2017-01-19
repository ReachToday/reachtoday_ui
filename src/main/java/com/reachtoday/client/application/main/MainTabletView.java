package com.reachtoday.client.application.main;



import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.reachtoday.client.application.fastbutton.FastButton;
import com.reachtoday.client.application.fastbutton.PressEvent;
import com.reachtoday.client.application.fastbutton.PressHandler;
import com.reachtoday.client.application.searchcontrol.SearchControl;
import com.reachtoday.client.mvp.TabletMainActivityMapper;
import com.reachtoday.client.resource.TabletResources;


@Singleton
public class MainTabletView extends Composite implements MainView {

	private static MainTabletViewUiBinder uiBinder = GWT
			.create(MainTabletViewUiBinder.class);

	interface MainTabletViewUiBinder extends UiBinder<Widget, MainTabletView> {
	}

	/**
	 * The constants used in this Content Widget.
	 */
	public static interface CwConstants extends Constants {
		String cwSuggestBoxDescription();

		String cwSuggestBoxLabel();

		String cwSuggestBoxName();

		String[] cwSuggestBoxWords();

	}
	@UiField
	SearchControl control;
	@UiField
	TabletResources resources;
	@UiField HTMLPanel page;
	@UiField HTMLPanel menu1;
	@UiField HTMLPanel menu;
	public Boolean menuleftOpen=false;
	@UiField HorizontalPanel searchPanel;
	@UiField Label practiceName;
	private Presenter presenter;
	
	@UiField SimplePanel mainDisplayPanel;
	@UiField FastButton showhidemenuright;
	
	
	@Inject
	public MainTabletView(TabletMainActivityMapper mainActivityMapper, 
			ActivityManager mainActivityManager) { 
		
		initWidget(uiBinder.createAndBindUi(this));
		mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				mainDisplayPanel.getElement().getStyle().setHeight(Window.getClientHeight()-43, Unit.PX);
				control.getPagerPanel().setHeight(""+(Window.getClientHeight()-practiceName.getOffsetHeight()-searchPanel.getOffsetHeight()-50)+"px");
			}
		});

		showhidemenuright.setVisible(false);
		mainActivityManager.setDisplay(mainDisplayPanel);
		control.setSearchPlaceHolder("Search");
		control.setSize("230px","20px");
		control.getPagerPanel().setWidth("250px");
		control.getPagerPanel().setStyleName(resources.tabletstyles().leftPagerPanel());
		final String searchPanelstyle=control.getSearchPanel().getElement().getAttribute("style");
		final FastButton close=control.getSearchClose();

		final TextBox searchTextBox=control.getTextBox();

		searchTextBox.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				control.getSearchPanel().getElement().setAttribute("style",searchPanelstyle+"border:1px solid #119DC9 !important;");
				control.getPagerPanel().setHeight(""+(Window.getClientHeight()-practiceName.getOffsetHeight()-searchPanel.getOffsetHeight()-50)+"px");

				close.addPressHandler(new PressHandler() {
					@Override
					public void onPress(PressEvent event) {
						menu1.removeStyleName(resources.tabletstyles().makePanelTransparent());
						/*menu1.getElement().getStyle().setVisibility(Visibility.VISIBLE);*/
						menu.add(menu1);
						control.getSearchPanel().getElement().setAttribute("style",searchPanelstyle+"border:1px solid #CDCDCD !important;");

					}
				});
				if(control.getSearchKeyWord()==""){
					control.getPagerPanel().setVisible(false);
					menu1.setStyleName(resources.tabletstyles().makePanelTransparent());

				}
			}



		});


		searchTextBox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {

				menu1.removeStyleName(resources.tabletstyles().makePanelTransparent());
				control.getSearchPanel().getElement().setAttribute("style",searchPanelstyle+"border:1px solid #CDCDCD !important;");

				/*if(!control.getPagerPanel().isVisible()){
				menu.add(menu1);
			}*/

				if(control.getTextBox().getText().equals("")){
					menu.add(menu1);
				}

				if(control.getMainPanel().remove(control.getNoresultPanel())){
					control.getSearchPanel().add(control.getSearchImagePanel());
					control.getSearchPanel().setCellHeight(control.getSearchImagePanel(), "30px");
					control.getTextBox().setText("");
					control.getTextBox().getElement().blur();
					menu.add(menu1);
				}
			}
		});		


			
	} 

	
	
	interface MainStyle extends CssResource{
		String foralerts();
		String foralerts1();
		String foralerts2();
	}

	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}

	public Widget asWidget() {
		return this;
	}

	@UiHandler("showhidemenuleft")
	void onPresmenuleft(PressEvent event) {
		showhidemenuleft();
	}

@UiHandler("messagenotification")
void onpressmessgaes(PressEvent event)
{
	presenter.drawHeader();
}
	
	@Override
	public void showhidemenuleft(){


		if(!menuleftOpen){
			page.getElement().getStyle().setProperty("MozTransform", "translate3d(260px, 0 , 0)");
			page.getElement().getStyle().setProperty("transform", "translate3d(260px, 0 , 0)");
			page.getElement().getStyle().setProperty("WebkitTransform", "translate3d(260px, 0 , 0)");
			page.getElement().getStyle().setProperty("OTransform", "translate3d(260px, 0 , 0)");
			menuleftOpen=true;
		}
		else{
			control.getPagerPanel().setVisible(false);
			control.getTextBox().setText("");
			control.getTextBox().getElement().blur();
			menu1.removeStyleName(resources.tabletstyles().makePanelTransparent());
			/*menu1.getElement().getStyle().setVisibility(Visibility.VISIBLE);*/
			control.getSearchPanel().remove(control.getCloseImagePanel());
			control.getSearchPanel().add(control.getSearchImagePanel());
			control.getSearchPanel().setCellWidth(control.getSearchImagePanel(), "30px");
			control.getMainPanel().remove(control.getNoresultPanel());
			menu.add(menu1);
			page.getElement().getStyle().setProperty("MozTransform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("transform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("WebkitTransform", "translate3d(0, 0 , 0)");
			page.getElement().getStyle().setProperty("OTransform", "translate3d(0, 0 , 0)");
			menuleftOpen=false;
		}

	}

	@Override
	public void displayUsername(String practicename, String username) {
		// TODO Auto-generated method stub
		
	}

}