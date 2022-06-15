package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;



public class LoginPage extends Page {
   public LoginPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
 /*  
   @FindBy(id="LOGIN_NAME")
   private WebElement loginName;
      
   @FindBy(id="DISPLAY_PASSWORD")
   private WebElement password;
   
   @FindBy(id="log_in")
   private WebElement submitbtn;  
  */
   //-----------------------------------
   public MyDeskPage loginUser (String uname, String uPswd) throws InterruptedException  {
	  if (domain.trim().equals("kmmsqa"))
	  {
	  driver.findElement(By.id("LOGIN_NAME")).clear();
	  driver.findElement(By.id("LOGIN_NAME")).sendKeys(uname);
      Thread.sleep(1000);
      driver.findElement(By.id("DISPLAY_PASSWORD")).clear();
      driver.findElement(By.id("DISPLAY_PASSWORD")).sendKeys(uPswd);
      Thread.sleep(1000);
      driver.findElement(By.id("log_in")).click();
      Thread.sleep(5000);
	  } else
	  {
		  driver.findElement(By.xpath("loginPage.usernameInput")).click();
	      Thread.sleep(1000);
		  driver.findElement(By.xpath("loginPage.usernameInput")).sendKeys(uname);
	      Thread.sleep(1000);
		  driver.findElement(By.xpath("loginPage.passwordInput")).click();
	      Thread.sleep(1000);
		  driver.findElement(By.xpath("loginPage.passwordInput")).sendKeys(uPswd);
	      Thread.sleep(1000);
	      driver.findElement(By.xpath("loginPage.loginButton")).click();
	      Thread.sleep(8000);

	  } 	
	   
      return new MyDeskPage(driver);   

   }
}
   
 

