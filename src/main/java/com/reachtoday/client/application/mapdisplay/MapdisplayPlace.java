package com.reachtoday.client.application.mapdisplay;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MapdisplayPlace extends Place{
	 private String token;

	    public MapdisplayPlace(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public static class Tokenizer implements PlaceTokenizer<MapdisplayPlace> {
	        @Override
	        public String getToken(MapdisplayPlace place) {
	            return place.getToken();
	        }

	        @Override
	        public MapdisplayPlace getPlace(String token) {
	            return new MapdisplayPlace(token);
	        }
	    }

}
