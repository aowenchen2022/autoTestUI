package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class Suppliers extends Page {

	 public Suppliers(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }	
	 
	 private WebElement selectContacts;
	 private WebElement done;
	 
	 public void selectSupplier() {
		 selectContacts.click();
	 }
	 
	 public void clickDoneBT() {
		 done.click();
	 }
}
