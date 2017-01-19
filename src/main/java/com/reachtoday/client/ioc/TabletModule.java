package com.reachtoday.client.ioc;





import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.reachtoday.client.application.main.ActionServletURL;
import com.reachtoday.client.application.main.ActionServletURLImpl;
import com.reachtoday.client.application.main.MainTabletModule;
import com.reachtoday.client.application.rttemplate.ReachtodayTabletModule;
import com.reachtoday.client.mvp.TabletMvpModule;
import com.reachtoday.client.resource.CSSInjector;
import com.reachtoday.client.resource.TabletCSSInjector;
import com.reachtoday.client.resource.TabletResources;




public class TabletModule extends AbstractGinModule{

	@Override
	protected void configure() {
		install(new TabletMvpModule());
		install(new MainTabletModule());
		install(new ReachtodayTabletModule());
		bind(TabletResources.class).in(Singleton.class);
		bind(CSSInjector.class).to(TabletCSSInjector.class).in(Singleton.class);
		 bind(ActionServletURL.class).to(ActionServletURLImpl.class).in(Singleton.class);
	}

}

