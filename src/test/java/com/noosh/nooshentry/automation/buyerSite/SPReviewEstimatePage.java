package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class SPReviewEstimatePage extends Page
{
	public SPReviewEstimatePage(WebDriver driver) {
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
	
	private WebElement reviewEstimates;
	  
	public void clickReviewEstimates() throws InterruptedException {
		Thread.sleep(1000);
		reviewEstimates = driver.findElement(By.className("procurement-action"));
		reviewEstimates.findElement(By.tagName("a")).click();	
		Thread.sleep(2000);
	}
}

