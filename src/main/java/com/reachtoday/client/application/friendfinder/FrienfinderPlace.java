package com.reachtoday.client.application.friendfinder;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FrienfinderPlace extends Place{
	 private String token;

	    public FrienfinderPlace(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public static class Tokenizer implements PlaceTokenizer<FrienfinderPlace> {
	        @Override
	        public String getToken(FrienfinderPlace place) {
	            return place.getToken();
	        }

	        @Override
	        public FrienfinderPlace getPlace(String token) {
	            return new FrienfinderPlace(token);
	        }
	    }

}
