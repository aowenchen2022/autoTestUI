package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SPConfirmOrderPage extends Page {
	 public SPConfirmOrderPage(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   }
	
	public void clickConfirmOrderBtn() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.className("awardOrderButton")).click();
		Thread.sleep(5000);
	}
}
