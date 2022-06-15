package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SupplierLoginPage extends Page
{
   public SupplierLoginPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   private WebElement username;
   private WebElement password;
   
   @FindBy(id="multiSignUpBtn")
   private WebElement submitbtn;
   
//   public MyDeskPage loginUser (String login, String userPasswd)
   public void loginUser(String login, String userPasswd) throws InterruptedException{
      username.clear();
      username.sendKeys(login);
      Thread.sleep(500);
      password.clear();
      password.sendKeys(userPasswd);
      Thread.sleep(500);
      submitbtn.click();   
   }
   
   public void loginUser1(String userPasswd) throws InterruptedException{
	      //username.clear();
	      //username.sendKeys(login);
	      //Thread.sleep(500);
	      password.clear();
	      password.sendKeys(userPasswd);
	      Thread.sleep(500);
	      submitbtn.click();   
	   }
}

