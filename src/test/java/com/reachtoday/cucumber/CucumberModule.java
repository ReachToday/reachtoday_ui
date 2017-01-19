package com.reachtoday.cucumber;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.reachtoday.cucumber.application.BasePage;
import com.reachtoday.cucumber.util.GlaceElementLocatorFactory;

	public class CucumberModule extends AbstractModule {
	    @Override
	    protected void configure() {
	        bind(ElementLocatorFactory.class).to(GlaceElementLocatorFactory.class).in(Singleton.class);
	        requestStaticInjection(BasePage.class);
	    }
  
	    @Provides
	    @Singleton 
	    WebDriver getDefaultWebDriver() {
	    	FirefoxDriver firefoxdriver = new FirefoxDriver();   
	    	firefoxdriver.manage().timeouts().implicitlyWait(100L, TimeUnit.SECONDS);
	        return firefoxdriver; 
	    }
	}
    

