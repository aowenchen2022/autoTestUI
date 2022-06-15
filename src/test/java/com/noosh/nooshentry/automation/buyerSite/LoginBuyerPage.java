package com.noosh.nooshentry.automation.buyerSite;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginBuyerPage extends Page
{
   public LoginBuyerPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
   
   @FindBy(css="input#username")
   private WebElement userName;
   
   @FindBy(css="input#password")
   private WebElement password;
   
   @FindBy(xpath="loginBuyerPage.loginButton")
   private WebElement loginButton;
   
   @FindBy(xpath="loginBuyerPage.submitBT")
   private WebElement submitBT;
   
   @FindBy(id="userName_pass_request")
   private WebElement buyerEmailAdd;
   
   @FindBy(xpath="loginBuyerPage.submitPasswordBT")
   private WebElement submitPasswordBT;
   
   private WebElement toResetPassword;
   private WebElement passwordOnce;
   private WebElement passwordTwice;
   
   
   public void loginBuyer(String buyerName, String buyerPassword) throws InterruptedException
   {
	  Thread.sleep(1000);
      userName.clear();
      userName.sendKeys(buyerName);
      Thread.sleep(1000);
      password.clear();
      password.sendKeys(buyerPassword);
      Thread.sleep(1000);
      loginButton.click();
      Thread.sleep(1000);
   }
   
   public void clickResetPasswordLink() {
	  toResetPassword.click();
   }
   
   public void putBuyerEmail(String buyerEmail) {
	   buyerEmailAdd.sendKeys(buyerEmail);
	   submitBT.click();
   }
   
   public void setNewBuyerPassword(String password) {
	   passwordOnce.sendKeys(password);
	   passwordTwice.sendKeys(password);
	   submitPasswordBT.click();
   }
}