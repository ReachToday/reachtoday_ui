package com.reachtoday.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface DesktopResources extends ClientBundle {
	

public interface Styles extends CssResource {
	
	String pageloadFadeIn();
	
}

    @Source("../css/desktopstyles.css")
    Styles desktopstyles();
    

}
