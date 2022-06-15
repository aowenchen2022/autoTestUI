package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AccountFrame  extends Page {

	public AccountFrame(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this); 
	}
	
	//Update Account information
	@FindBy(id="custom-accountInfo-editButton")
	private WebElement editUpdateInfoBT;	
	
	@FindBy(id="cancelAccountInfo")
	private WebElement cancelUpdateAccountInfoBT;
	
	@FindBy(id="saveAccountInfo")
	private WebElement saveUpdateAccountInfoBT;
	
	@FindBy(id="sp-account-companyName-input")
	private WebElement updateCompanyName;
	
	@FindBy(id="sp-account-line1-input")
	private WebElement updateAddress;
	
	@FindBy(id="sp-account-city-input")
	private WebElement updateCity;
	
	@FindBy(id="sp-account-state-input")
	private WebElement updateState;
	
	@FindBy(id="sp-account-postal-input")
	private WebElement updatePostalZip;
	
	//@FindBy(id="WORKGROUP_OPTION_PROCUREMENT-dd")
	//private WebElement radioGroup;
	
	//-------------------------- 
	public void clickUpdatInfo() {
		editUpdateInfoBT.click();
	}
	
	public void clickCancelUpdateAccountInfoBT() {
		cancelUpdateAccountInfoBT.click();
	}
	
	public void clickSaveUpdateAccountInfoBT() {
		saveUpdateAccountInfoBT.click();
	}
	
	public void setCompanyName(String newCompanyName) throws InterruptedException {
		Thread.sleep(500);
		updateCompanyName.clear();
		updateCompanyName.sendKeys(newCompanyName);
	}
	
	public void setAddress(String newAddress) throws InterruptedException {
		Thread.sleep(500);
		updateAddress.clear();
		updateAddress.sendKeys(newAddress);
	}
	
	public void setCity(String newCity) throws InterruptedException {
		Thread.sleep(500);
		updateCity.clear();
		updateCity.sendKeys(newCity);
	}
	
	public void setState(String newState) throws InterruptedException {
		Thread.sleep(500);
		updateState.clear();
		updateState.sendKeys(newState);
	}
	
	public void setZip(String newZip) throws InterruptedException {
		Thread.sleep(500);
		updatePostalZip.clear();
		updatePostalZip.sendKeys(newZip);
	}
}
