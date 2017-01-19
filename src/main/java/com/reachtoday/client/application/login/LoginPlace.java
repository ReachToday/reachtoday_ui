package com.reachtoday.client.application.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.reachtoday.client.application.main.MainMobileView;

public class LoginPlace  extends Place {
	private String token;

	public static MainMobileView mainMobileView;
    public LoginPlace(String token,MainMobileView mainMobileView) {
        this.token = token;
        this.mainMobileView=mainMobileView;
    }
    
    public MainMobileView getmainMobileView()
    {
    	return mainMobileView;
    }

    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<LoginPlace> {
        @Override
        public String getToken(LoginPlace place) {
            return place.getToken();
        }

        @Override
        public LoginPlace getPlace(String token) {
            return new LoginPlace(token,mainMobileView);
        }
    }

}
