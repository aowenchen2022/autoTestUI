package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends Page {
   public LoginPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(id="LOGIN_NAME")
   private WebElement loginName;
      
   @FindBy(id="DISPLAY_PASSWORD")
   private WebElement password;
   
   @FindBy(id="log_in")
   private WebElement submitbtn;  
  
   //-----------------------------------
   public MyDeskPage loginUser (String uname, String uPswd) throws InterruptedException {
	  loginName.clear();
	  loginName.sendKeys(uname);
      Thread.sleep(1000);
      password.clear();
      password.sendKeys(uPswd);
      Thread.sleep(1000);
      submitbtn.click();
      Thread.sleep(3000);
      return new MyDeskPage(driver);      
   }
}
   
 

