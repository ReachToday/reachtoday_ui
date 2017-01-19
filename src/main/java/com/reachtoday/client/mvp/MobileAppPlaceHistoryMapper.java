package com.reachtoday.client.mvp;



import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.reachtoday.client.application.friendfinder.FrienfinderPlace;
import com.reachtoday.client.application.homepage.HomePlace;
import com.reachtoday.client.application.livetracking.LivetrackingPlace;
import com.reachtoday.client.application.login.LoginPlace;
import com.reachtoday.client.application.mapdisplay.MapdisplayPlace;
import com.reachtoday.client.application.rttemplate.ReachtodayPlace;



/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( {  ReachtodayPlace.Tokenizer.class,
					LivetrackingPlace.Tokenizer.class,
					HomePlace.Tokenizer.class,
					MapdisplayPlace.Tokenizer.class,
					FrienfinderPlace.Tokenizer.class,
					LoginPlace.Tokenizer.class
				   										})
public interface MobileAppPlaceHistoryMapper extends PlaceHistoryMapper {
}
