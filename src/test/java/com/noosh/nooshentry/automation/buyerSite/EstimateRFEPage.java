package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.CommonUtils;

public class EstimateRFEPage extends Page {
	 public EstimateRFEPage(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   }
	 
	 public void clickCreateEstimateBtn() throws InterruptedException {
		 Thread.sleep(2000);
		 List<WebElement> createEst = driver.findElements(By.className("simple-button"));
		 createEst.get(1).click();
		 //WebElement createEst = driver.findElement(By.className("glb-title pointer"));
		 //createEst.findElement(By.className("create-estimate")).click();
		 Thread.sleep(2000);
	 }
	 
	 public void clickCreateEstimateBtn1() throws InterruptedException {
		 Thread.sleep(2000);
		 driver.findElement(By.className("procurement-action")).click();
		 Thread.sleep(3500);
	 }
	 
	 public void inputEstimatedTaxField(String taxPrice) {
		 //WebElement taxField = driver.findElement(By.className("taxRate"));
		 WebElement taxField = driver.findElement(By.className("custom-select-comboized"));
		 taxField.clear();
		 taxField.sendKeys(taxPrice);
	 }
	 
	 public void clickSendEstimateBtn() throws InterruptedException {
		 driver.findElement(By.className("submitSendRfe")).click();
		 Thread.sleep(2000);
	 }

}
