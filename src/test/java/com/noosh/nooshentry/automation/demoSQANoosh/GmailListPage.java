package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
	
public class GmailListPage extends Page {
	public GmailListPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
		
	@FindBy(id="gbqfb")
	private WebElement gmailSearchBtn;
		
	@FindBy(id="gbqfq")
	private WebElement gmailSearchBox;
		
	public void enterSearchPattern(String emailSearchPattern) {
		gmailSearchBox.clear();
		gmailSearchBox.sendKeys(emailSearchPattern);
	}
		
	public void searchGmail() {
		gmailSearchBtn.click();
	}

}
