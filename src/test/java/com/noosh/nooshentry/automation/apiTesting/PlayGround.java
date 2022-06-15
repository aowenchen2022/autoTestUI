package com.noosh.nooshentry.automation.apiTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class PlayGround extends Page
{

	 public PlayGround (WebDriver driver)
	 {
		  super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }	
	 
	 @FindBy(xpath="api.oauthSetting")
	 private WebElement oAuthSettingsTab;
	 
	 private WebElement clientId;
	 private WebElement clientSecret;
	 
	 @FindBy(name="step2")
	 private WebElement next;
	 
	 private WebElement loginName;
	 private WebElement password;
	 private WebElement requestURL;
	 
	 @FindBy(xpath="api.loginBT")
	 private WebElement loginBT;
	 
	 @FindBy(xpath="api.authorizeBT")
	 private WebElement authorizeBT;
	 
	 @FindBy(id="user_oauth_approval")
	 private WebElement grantPermission;
	 
	 @FindBy(xpath="api.getAuthorizationCodeForToken")
	 private WebElement getAuthorizationCodeForToken;
	 
	 @FindBy(id="accessTokenForTool")
	 private WebElement accessToken;
	 
	 @FindBy(id="refreshToken")
	 private WebElement refreshToken;
	 
	 @FindBy(xpath="api.requestTab")
	 private WebElement requestTab;
	 
	 @FindBy(xpath="api.fetchBT")
	 private WebElement fetchBT;
	 
	 private WebElement rawResponseInfo;
	 
	 public void openOauthSettingTab() {
		 oAuthSettingsTab.click();
	 }
	 
	 public void setClientID(String clientID) {
		 clientId.sendKeys(clientID);
	 }
	 
	 public void setClientSecret(String secret) {
		 clientSecret.sendKeys(secret);
	 }
	 
	 public void clickNextBT() {
		 next.click();
	 }
	 
	 public GrantPermission clickAuthorizeBT() {
		 authorizeBT.click();
		 return new GrantPermission(driver);
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
    	 return accessToken.getAttribute("value");
     }
     
     public String getRefreshToken() {
    	 return refreshToken.getText();
     }
     
     public void clickRequestTab() {
    	 requestTab.click();
     }
     
     public void setRequestURI(String request) {
    	 requestURL.sendKeys(request);
     }
     
     public void clickFetchBT() {
    	 fetchBT.click();
     }
     
     public String getReuqestInfo() {
    	 return rawResponseInfo.getText();
     }
}
