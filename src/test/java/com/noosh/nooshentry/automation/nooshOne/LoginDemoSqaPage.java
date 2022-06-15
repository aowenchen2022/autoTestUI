package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginDemoSqaPage extends Page {
   public LoginDemoSqaPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   private WebElement username;
   private WebElement password;   
   private WebElement submitbutton;
   private WebElement serviceForgotPedLink;
   private WebElement forgotPwdName;
   private WebElement forgotPwdButton;
   private WebElement firstResetPwd;
   private WebElement secondResetPwd;
   private WebElement resetPwdButton;
   
  // Login Noosh Pro
   @FindBy(id="LOGIN_NAME" )
   private WebElement loginSup;
   
   @FindBy(id="DISPLAY_PASSWORD")
   private WebElement passwordSup;
   
   @FindBy(xpath="loginDemoPage.login")
   private WebElement login;
  
   public void loginUser (String loginName, String userPasswd) {
      username.clear();
      username.sendKeys(loginName);
      password.clear();
      password.sendKeys(userPasswd);
      if(!submitbutton.isDisplayed()) {
    	  throw new IllegalStateException("Login button is missing!");
      } else {
          submitbutton.click();
      }
   }
   
   public void loginSup (String loginName, String userPasswd) {
	  loginSup.clear();
	  loginSup.sendKeys(loginName);
	  passwordSup.clear();
	  passwordSup.sendKeys(userPasswd);
	  if(!login.isDisplayed()) {
		  throw new IllegalStateException("Login button is missing!");
	  } else {
	      login.click();
	  }
   }
   
   public void clickForgotPasswordLink() {
	   serviceForgotPedLink.click();
   }
   
   public void putSPEmail(String email) {
	   forgotPwdName.clear();
	   forgotPwdName.sendKeys(email);
	   forgotPwdButton.click();
   }
   
   public void setNewSPPassword(String newPassword) throws InterruptedException {
	   firstResetPwd.clear();
	   firstResetPwd.sendKeys(newPassword);
	   secondResetPwd.clear();
	   secondResetPwd.sendKeys(newPassword);
	   Thread.sleep(2000);
	   resetPwdButton.click();
   }
}