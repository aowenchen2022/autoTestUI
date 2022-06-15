package com.noosh.nooshentry.automation.buyerSite;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SupplierEstimatePopup extends Page {
   public SupplierEstimatePopup(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
      
   private WebElement addASupplier;
   
   @FindBy(id="createRfeSearchSuppliers")                
   private WebElement createRfeSearchSuppliersBtn;
   
   private WebElement requestEstimatesBtn;
   
   @FindBy(linkText="20")
   private WebElement date1;
   
   @FindBy(linkText="25")
   private WebElement date2;
   
   @FindBy(xpath="brochurePopupWindow.nextMonth")
   private WebElement nextMonth;
  
   public void setSupplierName(String supplierName1, String supplierName2) throws InterruptedException  {
	   addASupplier = driver.findElement(By.className("ui-autocomplete-input"));
	   addASupplier.clear();
	   addASupplier.sendKeys(supplierName1);
	   Thread.sleep(2500);
	   createRfeSearchSuppliersBtn.click();
	   Thread.sleep(2500);
	   addASupplier.clear();
	   addASupplier.sendKeys(supplierName2);
	   Thread.sleep(2500);
	   createRfeSearchSuppliersBtn.click();
	   Thread.sleep(3000);
   }
   
   public void callCalendar() throws InterruptedException {
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);       
	   WebElement info = driver.findElement(By.className("rfe-additional-info")); 
	   List<WebElement> calendars = info.findElements(By.className("ui-datepicker-trigger"));
	   calendars.get(0).click();
	   Thread.sleep(2000);
	   //nextMonth.click();
	   //date1.click();
	   brochurePopup.setNextMonth();
	   Thread.sleep(1000);
       brochurePopup.setComplationDate(); 
	   Thread.sleep(3000);
	   calendars.get(1).click();
	   Thread.sleep(2000);
	   //nextMonth.click();
	   //date2.click();
	   brochurePopup.setNextMonth();
	   Thread.sleep(1000);
       brochurePopup.setComplationDate(); 
	   Thread.sleep(3000);
   }
	  
   public void callRequestEstimatesBtn() {
	   requestEstimatesBtn = driver.findElement(By.className("submitSendRfe"));
	   requestEstimatesBtn.click();
   }
   
}
