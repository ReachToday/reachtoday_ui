package com.reachtoday.client.ioc;

import com.google.gwt.core.client.GWT;

import java.util.logging.Logger;

public class TabletGinjectorProvider implements GinjectorProvider {
    public final static Logger logger = Logger.getLogger(TabletGinjectorProvider.class.getName());

    @Override
    public ClientGinjector get() {
        logger.info("TabletGinjectorProvider get() TabletGinjector was used.");

        return GWT.create(TabletGinjector.class);
    }
}
