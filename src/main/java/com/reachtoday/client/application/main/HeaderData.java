package com.reachtoday.client.application.main;

import com.google.gwt.core.client.JavaScriptObject;

class HeaderData extends JavaScriptObject {                              // [1]
  // Overlay types always have protected, zero argument constructors.
  protected HeaderData() {}                                              // [2]

  // JSNI methods to get header data.
  
  public final native String getPracticeName() /*-{ return this.practicename; }-*/; // [3]
  public final native String getUsername() /*-{ return this.username; }-*/;
  public final native String getHelp() /*-{ return this.help; }-*/;
}
