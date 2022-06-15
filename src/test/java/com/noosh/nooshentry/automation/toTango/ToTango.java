package com.noosh.nooshentry.automation.toTango;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class ToTango extends Page {

	public ToTango(WebDriver driver)
	{
	   super(driver);
	   PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}	
		
	private WebElement emailField;
	private WebElement passwordField;
	private WebElement signinBtn;
	private WebElement globalAccountSearch;
	
	@FindBy(xpath="toTango.selectUser")
	private WebElement selectUser;
	
	@FindBy(xpath="toTango.timeStarted")
	private WebElement timeStarted;
	
	@FindBy(xpath="toTango.forTime")
	private WebElement forTime;
	
	@FindBy(xpath="toTango.userProfile")
	private WebElement userProfile;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	public void loginToTango(String email, String password) {
		type(emailField, email);
		type(passwordField, password);
		signinBtn.click();
	}
	
	public void searchText(String searchText) {
		globalAccountSearch.sendKeys(searchText);
		globalAccountSearch.sendKeys(Keys.RETURN);
		selectUser.click();
	}
	
	public String getTimeStarted() {
		return timeStarted.getText();
	}
	
	public String getForTime() {
		return forTime.getText();
	}
	
	public void logout(NooshWebDriver driver, String user) throws InterruptedException {
		driver.findElementByXPath("toTango.userProfile", "0", user).click();
		Thread.sleep(600);
		logout.click();		
	}
}
