package com.reachtoday.client.mvp;



import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.reachtoday.client.application.rttemplate.ReachtodayPlace;



/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( {  ReachtodayPlace.Tokenizer.class,
				   })
public interface DesktopAppPlaceHistoryMapper extends PlaceHistoryMapper {
}
