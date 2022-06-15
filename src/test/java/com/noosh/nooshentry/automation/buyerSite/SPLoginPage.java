package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SPLoginPage extends Page {
   public SPLoginPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(id="username")
   private WebElement spUsername;
   
   @FindBy(id="password")
   private WebElement spPassword;
   
   @FindBy(id="submitbutton")
   private WebElement spSubmitbtn;
  
   //-----------------------------------
     
   public void spLoginUser (String userName, String uPswd) throws InterruptedException {
	   spUsername.clear();
	  // spUsername.sendKeys(emailSP);
	   spUsername.sendKeys(userName);
	   Thread.sleep(2000);
	   spPassword.clear();
	   spPassword.sendKeys(uPswd);
	   Thread.sleep(2000);
	   spSubmitbtn.click();
	   Thread.sleep(3000);
	        
	   }
   
   public void spLoginUser1(String uPswd) throws InterruptedException {
	   //spUsername.clear();
	   //spUsername.sendKeys(emailSP);
	   //spUsername.sendKeys(userName);
	   //Thread.sleep(2000);
	   spPassword.clear();
	   spPassword.sendKeys(uPswd);
	   Thread.sleep(2000);
	   spSubmitbtn.click();
	   Thread.sleep(3000);
	        
	   }
}