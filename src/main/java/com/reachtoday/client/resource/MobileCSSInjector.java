package com.reachtoday.client.resource;

import com.google.inject.Inject;

public class MobileCSSInjector implements CSSInjector {

	@Inject
	MobileResources resources;

	public void injectCSS() {
		resources.mobilestyles().ensureInjected();
		resources.livetrackingstyles().ensureInjected();
		resources.searchControlStyles().ensureInjected();
		resources.loginMobileStyles().ensureInjected();
	}
	
}
