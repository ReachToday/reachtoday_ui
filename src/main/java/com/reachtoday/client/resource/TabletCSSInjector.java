package com.reachtoday.client.resource;

import com.google.inject.Inject;

public class TabletCSSInjector implements CSSInjector {

	@Inject
	TabletResources resources;

	public void injectCSS() {
		resources.tabletstyles().ensureInjected();
		resources.fastButtonStyles().ensureInjected();
		resources.fastCheckboxStyles().ensureInjected();
		resources.fastToggleCheckboxStyles().ensureInjected();
		resources.searchControlStyles().ensureInjected();
		
	}
}
