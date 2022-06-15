package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ClientSignupPage extends Page {
	public ClientSignupPage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	} 
	
	@FindBy(id="fullName")
	private WebElement clientFullName;
	
	@FindBy(id="emailAddress")
	private WebElement clientEmailAddress;
	
	@FindBy(id="password")
	private WebElement clientPassword;
	
	@FindBy(id="signup_b")
	private WebElement clientSignupBtn;
	
	public void clickSignupLink() {
		driver.findElement(By.className("bLog-sublinks"));
	}
	
	public void inputFullName(String name) {
		clientFullName.clear();
		clientFullName.sendKeys(name);
	}
	
	public void inputEmail(String email) {
		clientEmailAddress.clear();
		clientEmailAddress.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		clientPassword.clear();
		clientPassword.sendKeys(password);
	}
	
	public void clickSignupBtn() {
		clientSignupBtn.click();
	}
}
