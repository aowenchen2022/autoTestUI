package com.noosh.nooshentry.automation.apiTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ChromeApiClient extends Page{

	 public ChromeApiClient(WebDriver driver)
	 {
		  super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }		 	 

	 @FindBy(xpath="chromeApiClient.uriLink")
	 private WebElement uriLink;
	 
	 @FindBy(className="RequestHeaders_Widget_rawInput")
	 private WebElement requestHeader;
	 
	 public void putNooshUriLink(String link) {
		 uriLink.sendKeys(link);
	 }
	 
	 public void setRequestHeader() {
		 requestHeader.click();
	 }
}
