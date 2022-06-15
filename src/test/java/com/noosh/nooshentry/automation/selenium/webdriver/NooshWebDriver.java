package com.noosh.nooshentry.automation.selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface NooshWebDriver extends WebDriver {
	
	public WebElement findElementByXPath(String xpathKey, String index, String replace);

}
