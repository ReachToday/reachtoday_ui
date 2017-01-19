package com.reachtoday.cucumber.application;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.inject.Inject;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;
import com.reachtoday.cucumber.util.ByDebugId;
import com.reachtoday.cucumber.util.TestParameters;

public class BasePage {

	@Inject
	protected WebDriver webDriver;

	@Inject
	private static ElementLocatorFactory elementLocatorFactory;

	protected BasePage() {
		PageFactory.initElements(elementLocatorFactory, this);
	}

	protected BasePage(WebDriver webDriver) {
		
		this.webDriver = webDriver; 
	}

	public void getUrl(String url) {
		webDriver.get(url);
	}

	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	public void waitUntilPlaceIsLoaded(final String nameToken) {
		webDriverWait().until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.getCurrentUrl().contains("#" + nameToken);
			}
		});
	}

	protected void chooseOkOnNextConfirm() {
		((JavascriptExecutor) webDriver).executeScript("window.confirm = function(msg){return true;};");
	}

	protected void chooseCancelOnNextConfirm() {
		((JavascriptExecutor) webDriver).executeScript("window.confirm = function(msg){return false;};");
	}

	protected WebElement waitUntilElementIsLoaded(String id) {
		return waitUntilElementIsLoaded(By.id(id));
	}

	protected WebElement waitUntilElementIsLoaded(By locator) {
		return waitUntilElementIsLoaded(webDriver, locator);
	}

	protected void waitUntilElementIsVisible(WebElement element) {
		webDriverWait().until(ExpectedConditions.visibilityOf(element));
	}

	protected WebElement waitUntilElementIsVisible(By locator) {
		return webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitUntilElementIsDetached(WebElement element) {
		webDriverWait().until(ExpectedConditions.stalenessOf(element));
	}

	protected WebElement waitUntilElementIsClickable(final WebElement parent,
			final By locator) {
		final WebElement childElement = webDriverWait().until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return parent.findElement(locator);
			}
		});

		moveToElement(childElement);

		webDriverWait().until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return childElement.isEnabled();
			}
		});

		return childElement;
	}

	protected WebElement waitUntilElementIsClickable(By locator) {
		moveToElementLocatedBy(locator);

		return webDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected WebElement waitUntilPresenceOfElementLocated(By locator) {
		return webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected WebElement waitUntilElementIsLoaded(final SearchContext parent, final By locator) {
		return webDriverWait().until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return parent.findElement(locator);
			}
		});
	}

	private void moveToElementLocatedBy(By by) {
		WebElement webElement = waitUntilPresenceOfElementLocated(by);
		moveToElement(webElement);
	}

	private void moveToElement(WebElement webElement) {
		Actions actions = new Actions(webDriver);
		actions.moveToElement(webElement);
		actions.perform();
	}

	private WebDriverWait webDriverWait() {
		return new WebDriverWait(webDriver, TestParameters.TIME_OUT_IN_SECONDS);
	}
	protected  WebElement getElement(String debugId) {
	    return waitUntilElementIsLoaded(ByDebugId.id(debugId));
	}
	
	protected  WebElement getElementById(String elementId) {
	    return waitUntilElementIsLoaded(ById.id(elementId));
	}

	public void logincredentials(){
//			  webDriver.findElement(By.id("button1")).click();
		webDriver.findElement(By.id("username")).clear();
		webDriver.findElement(By.id("username")).sendKeys("demodoctor");
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys("demopwd0");
		webDriver.findElement(By.id("accountid")).clear();
		webDriver.findElement(By.id("accountid")).sendKeys("local");
		webDriver.findElement(By.xpath("(//input[@type='button'])")).click();
		Alert javascriptprompt = webDriver.switchTo().alert();
		javascriptprompt.accept();
	}

	/* to check whether an alert is present or not*/
	public boolean isAlertPresent() { 
		try 
		{ 
			webDriver.switchTo().alert(); 
			return true; 
		} 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		} 
	}

	/*to check whether the given debugId exists in the page or not*/
	public boolean isDebugIdPresent(String debugId) {
		
		boolean present = false;
		try {
			getElement(debugId);
			present = true;
		}catch( NoSuchElementException e) {
			present = false;
		}
		
		return present;
	}
	
	
	/*to check whether the given elementId exists in the page or not*/
	public boolean isElementIdPresent(String elementId) {
		
		boolean present = false;
		try {
			getElementById(elementId);
			present = true;
		}catch( NoSuchElementException e) {
			present = false;
		}
		
		return present;
	}


}
