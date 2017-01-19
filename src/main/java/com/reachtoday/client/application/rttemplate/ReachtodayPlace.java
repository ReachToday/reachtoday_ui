package com.reachtoday.client.application.rttemplate;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ReachtodayPlace extends Place{
	 private String token;

	    public ReachtodayPlace(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public static class Tokenizer implements PlaceTokenizer<ReachtodayPlace> {
	        @Override
	        public String getToken(ReachtodayPlace place) {
	            return place.getToken();
	        }

	        @Override
	        public ReachtodayPlace getPlace(String token) {
	            return new ReachtodayPlace(token);
	        }
	    }

}
