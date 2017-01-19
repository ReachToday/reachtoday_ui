package com.reachtoday.client.application.livetracking;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LivetrackingPlace extends Place{
	 private String token;

	    public LivetrackingPlace(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public static class Tokenizer implements PlaceTokenizer<LivetrackingPlace> {
	        @Override
	        public String getToken(LivetrackingPlace place) {
	            return place.getToken();
	        }

	        @Override
	        public LivetrackingPlace getPlace(String token) {
	            return new LivetrackingPlace(token);
	        }
	    }

}
