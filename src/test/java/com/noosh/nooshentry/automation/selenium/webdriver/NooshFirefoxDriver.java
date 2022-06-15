package com.noosh.nooshentry.automation.selenium.webdriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.noosh.nooshentry.automation.util.AppConfig;

public class NooshFirefoxDriver extends FirefoxDriver implements NooshWebDriver{

	public NooshFirefoxDriver() {		
	}
	
	public NooshFirefoxDriver(FirefoxProfile profile) {
		    super(new FirefoxBinary(), profile);
    }
	
	public WebElement findElementByXPath(String xpathKey) {
		String xpath = AppConfig.getXpath(xpathKey);
		return findElement("xpath", xpath);
	}

	public List<WebElement> findElementsByXPath(String xpathKey) {
		String xpath = AppConfig.getXpath(xpathKey);
		return findElements("xpath", xpath);
	}
	
	public WebElement findElementByXPath(String xpathKey, String index, String replace) {
		String xpath = AppConfig.getXpath(xpathKey, index, replace);
		return findElement("xpath", xpath);
	}

}
