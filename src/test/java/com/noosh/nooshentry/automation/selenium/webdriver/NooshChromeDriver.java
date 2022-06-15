package com.noosh.nooshentry.automation.selenium.webdriver;

import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.noosh.nooshentry.automation.util.AppConfig;

public class NooshChromeDriver extends ChromeDriver implements NooshWebDriver{
	
	public NooshChromeDriver() {
		super();
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
