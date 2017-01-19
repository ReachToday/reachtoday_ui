package com.reachtoday.client.application.main;

import com.google.gwt.core.client.JavaScriptObject;

public class Resource extends JavaScriptObject {

	  // Overlay types always have protected, zero argument constructors.
	  protected Resource() {}                                              // (2)

	public final native int getId() /*-{ return this.id; }-*/;
	public final native String getName() /*-{ return this.name; }-*/;

	public final String toGString(){
		return getId()+getName();
	}
	

}
