package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

//import junit.framework.Assert;  //4/19/2015 update
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class SupplierMainMenuPage extends Page
{
	public SupplierMainMenuPage(WebDriver driver) {
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
	
	//@FindBy(id="headProfileImage")
	//private WebElement logoutImage;
	
	//@FindBy(id="topMenuLogout")
	//private WebElement logout;
	
	//public void clickProfileUserName() {
	//	 logoutImage.click();
	//}    
	
	private WebElement homeProjectListView;
	 
	public void clickLogout() throws InterruptedException {
		Thread.sleep(3000);
	 	String currentURL = driver.getCurrentUrl();
	    int index = currentURL.indexOf("/home");
		String logoutURL = currentURL.substring(0, index+5) + "/service/j_spring_security_logout";
		System.out.println("Logout="+logoutURL);
		driver.get(logoutURL);
		Thread.sleep(2000);
	}
   
	public void supplierLogout() throws InterruptedException {
		WebElement topMenu = driver.findElement(By.id("headProfileImage"));	  
		Actions builder = new Actions(driver);
		builder.moveToElement(topMenu).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3500);
	 }
	
	public void checkStatus() throws InterruptedException {
		homeProjectListView.findElement(By.className("toggle-detail")).click();
		Thread.sleep(2000);
		String status = driver.findElement(By.className("order-list")).findElement(By.className("order-status")).getText();
		Assert.assertEquals("Order canceled", status);
		Thread.sleep(2000);
	}
	
	public void openProj() throws InterruptedException {
		driver.findElement(By.className("line-project-name-cot")).click();
		Thread.sleep(2500);
	}	
	
	public void verifyEstSent(String status) throws InterruptedException {
		WebElement tr = driver.findElement(By.className("supplier-rfe-table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
		List<WebElement> td = tr.findElements(By.tagName("td"));
		String est = td.get(4).getText();
		Assert.assertEquals(status, est);
		Thread.sleep(3500);
	}
}
