package com.reachtoday.client.mvp;





import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.reachtoday.client.application.rttemplate.ReachtodayActivity;
import com.reachtoday.client.application.rttemplate.ReachtodayPlace;


public class DesktopMainActivityMapper implements ActivityMapper {

	Provider<ReachtodayActivity> avpActivityProvider;
  
  @Inject
  public DesktopMainActivityMapper(Provider<ReachtodayActivity> avpActivityProvider
		  ){
	 super();
	 this.avpActivityProvider = avpActivityProvider;
	
  }


  @Override
  public Activity getActivity(Place place) {
	  
	  if (place instanceof ReachtodayPlace) {
			return avpActivityProvider.get();
	    } 
	
	return null;
    
  }

}
