package com.reachtoday.client.mvp;





import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.reachtoday.client.application.friendfinder.FrienfinderActivity;
import com.reachtoday.client.application.friendfinder.FrienfinderPlace;
import com.reachtoday.client.application.homepage.HomeActivity;
import com.reachtoday.client.application.homepage.HomePlace;
import com.reachtoday.client.application.livetracking.LivetrackingActivity;
import com.reachtoday.client.application.livetracking.LivetrackingPlace;
import com.reachtoday.client.application.login.LoginActivity;
import com.reachtoday.client.application.login.LoginPlace;
import com.reachtoday.client.application.mapdisplay.MapdisplayActivity;
import com.reachtoday.client.application.mapdisplay.MapdisplayPlace;
import com.reachtoday.client.application.rttemplate.ReachtodayActivity;
import com.reachtoday.client.application.rttemplate.ReachtodayPlace;


public class MobileMainActivityMapper implements ActivityMapper {

	Provider<ReachtodayActivity> avpActivityProvider;
	Provider<LivetrackingActivity> livetrackingActivityProvider;
	Provider<MapdisplayActivity> mapdisplayActivityProvider;
	Provider<HomeActivity> homeActivityProvider;
	Provider<FrienfinderActivity> frienfinderActivity;
	Provider<LoginActivity>  loginActivityProvider;
  @Inject
  public MobileMainActivityMapper(Provider<ReachtodayActivity> avpActivityProvider,
		  						  Provider<LivetrackingActivity> livetrackingActivityProvider,
		  						Provider<MapdisplayActivity> mapdisplayActivityProvider,
		  						Provider<HomeActivity> homeActivityProvider,
		  						Provider<FrienfinderActivity> frienfinderActivity,
		  						Provider<LoginActivity>  loginActivityProvider
		  ){
	 super();
	 this.avpActivityProvider = avpActivityProvider;
	 this.livetrackingActivityProvider=livetrackingActivityProvider;
	 this.mapdisplayActivityProvider=mapdisplayActivityProvider;
	 this.homeActivityProvider=homeActivityProvider;
	 this.frienfinderActivity=frienfinderActivity;
	 this.loginActivityProvider=loginActivityProvider;
  }


  @Override
  public Activity getActivity(Place place) {
	  
	  if (place instanceof ReachtodayPlace) {
			return avpActivityProvider.get().withPlace((ReachtodayPlace) place);
	    } 
	  else if(place instanceof LivetrackingPlace)
	  {
		  return livetrackingActivityProvider.get().withPlace((LivetrackingPlace) place);
	  }
	  else if(place instanceof MapdisplayPlace)
	  {
		  return mapdisplayActivityProvider.get().withPlace((MapdisplayPlace) place);
	  }
	  else if(place instanceof HomePlace)
	  {
		  return homeActivityProvider.get().withPlace((HomePlace) place);
	  }
	  else if(place instanceof FrienfinderPlace)
	  {
		  return frienfinderActivity.get().withPlace((FrienfinderPlace) place);
	  }
	  else if(place instanceof LoginPlace)
	  {
		  return loginActivityProvider.get().withPlace((LoginPlace) place);
	  }
	return null;
    
  }

}
