package com.reachtoday.cucumber.application;



import javax.inject.Inject;

import org.openqa.selenium.TimeoutException;

import com.reachtoday.cucumber.util.TestParameters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.google.gwt.user.client.ui.UIObject.DEBUG_ID_PREFIX;

public class ApplicationPage extends BasePage {


  
  @Inject
  ApplicationPage(WebDriver webDriver) {
	  super(webDriver);
	
  }

  public Boolean waitUntilDomIsLoaded(String nameToken) {
      try {
          waitUntilPlaceIsLoaded(nameToken);
          waitUntilElementIsLoaded(DEBUG_ID_PREFIX + "dom");
          return true;
      } catch (TimeoutException e) {
          return false;
      }
  }
  
  public void navigateTo(String page) {
	  if(page.equalsIgnoreCase("login"))
          getUrl(TestParameters.BASE_URL);
	  else
		  getUrl(TestParameters.BASE_URL + "/" + page);
      
  }
 

  public Boolean doesDebugIdExist(String debugId) {
    try {

      return true;
    } catch (TimeoutException e) {
      throw e;
    }
  }

}