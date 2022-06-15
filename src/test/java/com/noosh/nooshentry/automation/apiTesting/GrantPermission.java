package com.noosh.nooshentry.automation.apiTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class GrantPermission extends Page{

	 public GrantPermission(WebDriver driver)
	 {
		  super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }		 
	 
	 private WebElement loginName;
	 private WebElement password;
	 
	 @FindBy(xpath="api.loginBT")
	 private WebElement loginBT;
	 
	 @FindBy(xpath="api.authorizeBT")
	 private WebElement authorizeBT;
	 
	 @FindBy(id="user_oauth_approval")
	 private WebElement grantPermission;
	 
	 @FindBy(xpath="api.getAuthorizationCodeForToken")
	 private WebElement getAuthorizationCodeForToken;
	 
	 @FindBy(id="accessToken")
	 private WebElement accessToken;
	 
	 @FindBy(id="refreshToken")
	 private WebElement refreshToken;	 
	 
	 public void clickAuthorizeBT() {
		 authorizeBT.click();
	 }
	 
	 public void login(String name, String passwordSP) {
		 loginName.sendKeys(name);
		 password.sendKeys(passwordSP);
		 loginBT.click();
	 }
	 
     public void clickGrantPermissionBT() {
    	 grantPermission.click();
     }
     
     public void clickGetAuthorizationCadeForToken() {
    	 getAuthorizationCodeForToken.click();
     }
     
     public String getAccessToken() {
    	 return accessToken.getText();
     }
     
     public String getRefreshToken() {
    	 return refreshToken.getText();
     }
}
