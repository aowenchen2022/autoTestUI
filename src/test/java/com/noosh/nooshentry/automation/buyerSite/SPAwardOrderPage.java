package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class SPAwardOrderPage extends Page
{
	public SPAwardOrderPage(WebDriver driver) {
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
	  
	//@FindBy(id="supplier-8123679-view41-seleniumtest12345@gmail.com")
	//WebElement selectEstimateRadioBtn;
	
	public void clickAwardBtn() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//WebElement proceedOrder = driver.findElement(By.className("award-order-btn"));
		WebElement proceedOrder = driver.findElement(By.className("award-order-btn"));
		if(proceedOrder != null) {
			proceedOrder.click();
		} else {
			System.out.println("'Proceed to Order Confirmation' button is missing or not clickable\n");
		}
		Thread.sleep(5000);
	}
	
	public void selectAEstimate() throws InterruptedException {
		//List<WebElement> estimateRadioBtns = driver.findElements(By.className("select-radio"));
		List<WebElement> estimateRadioBtns = driver.findElements(By.className("check-icon"));
		estimateRadioBtns.get(0).click();
		Thread.sleep(2000);
	}
	
	public void selectAEstimate2() throws InterruptedException {
		//List<WebElement> estimateRadioBtns = driver.findElements(By.className("select-radio"));
		List<WebElement> estimateRadioBtns = driver.findElements(By.className("check-icon"));
		estimateRadioBtns.get(3).click();
		Thread.sleep(1000);
	}
}