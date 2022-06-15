package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ClientLoginPage extends Page {
   public ClientLoginPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(id="username")
   private WebElement clientUsername;
   
   @FindBy(id="password")
   private WebElement clientPassword;
   
   @FindBy(id="loginButton")
   private WebElement clientSubmitbtn;
   
   //private WebElement toResetPassword;
  
   //-----------------------------------
     
   public void clientLoginUser(String userName, String uPswd) throws InterruptedException {
	   clientUsername.clear();
	   clientUsername.sendKeys(userName);
	   Thread.sleep(2000);
	   clientPassword.clear();
	   clientPassword.sendKeys(uPswd);
	   Thread.sleep(2000);
	   clientSubmitbtn.click();
	   Thread.sleep(3000);	        
	   }
   
   public void clientLoginUser1(String uPswd) throws InterruptedException {	   
	   clientPassword.sendKeys(uPswd);
	   Thread.sleep(2000);
	   clientSubmitbtn.click();
	   Thread.sleep(3000);	        
	   }
   
   public void clientForgotPWLink() throws InterruptedException {
	   List<WebElement> as = driver.findElement(By.className("bLog-forgot-usernamePassword-wrapper")).findElements(By.tagName("a"));
	   WebElement toResetPassword = as.get(0);
	   Thread.sleep(1000);
	   if(toResetPassword.isDisplayed()) {
	       toResetPassword.click();
	   } else {
		   System.out.println("'Forgot Password' link is missing.\n");
	   }
	   
   }
}