package com.noosh.nooshentry.automation.nooshOne;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.JavascriptExecutor;

public class RegisterNewSupplierSitePage extends Page
{
   
	public RegisterNewSupplierSitePage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }     

   private WebElement fullName;      
   private WebElement password;   
   //private WebElement phoneNumber; 
  
   //private WebElement companyList;
   private WebElement company;
   
    
   public void setSupplierInfo(String supplierFullName, String supplierPasswd, String supplierCompanyName)       
   {
	   type(fullName, supplierFullName);   
	   type(password, supplierPasswd);
	   type(company, supplierCompanyName);
	   /*
	   List<WebElement> options = companyList.findElements(By.tagName("option"));
	   for (WebElement option : options) {
		   if (option.getText().equals(supplierCompanyName)) {
			   option.click();
			   break;
		   }
	   }
	   */
   }
   
   public void clickSignupBT() {
	   driver.findElement(By.className("sp-simple-button")).click();
   }
}
