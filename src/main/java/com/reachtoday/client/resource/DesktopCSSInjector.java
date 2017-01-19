package com.reachtoday.client.resource;

import com.google.inject.Inject;

public class DesktopCSSInjector implements CSSInjector {

	@Inject
	DesktopResources resources;

	public void injectCSS() {
		resources.desktopstyles().ensureInjected();
	}
	
}
