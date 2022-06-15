package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ClientHomePage extends Page {
	public ClientHomePage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	} 
	
	@FindBy(id="dashItemList")
	private WebElement smartForm;
	
	@FindBy(id="bHead-user-image")
	private WebElement topMenuUserProfile;	
	private WebElement confirmOrder;
	private WebElement topMenuProfile;
	private WebElement rejectQuote;
	
	public void clickSmartForm() {
	    List<WebElement> smartForms = smartForm.findElements(By.className("product-list-as-icon"));
	    smartForms.get(2).click();	    
	}
	
	public void clickUserProfileImage() throws InterruptedException {
		Actions builder = new Actions(driver);
		topMenuProfile.click();
		Thread.sleep(1000);
		builder.moveToElement(topMenuProfile).perform();
		Thread.sleep(3000);
		driver.findElement(By.id("topMenuUserProfile")).click();
	}
	
	public void reviewQClient() throws InterruptedException{
     	driver.findElement(By.className("line-project-name-cot")).click();
     	Thread.sleep(3000);
     	driver.findElement(By.className("procurement-action")).findElement(By.tagName("a")).click();
		//driver.findElement(By.className("procurement-action")).click();
	}
	
	public void clickQuoteAwardOrder() {
		driver.findElement(By.className("award-order-btn")).click();
	}
	
	public void confirmQuoteOrder() {
		confirmOrder.click();
	}
	
	public void clickQuoteReject() {
		driver.findElement(By.className("reject-order-btn")).click();
	}

	public void confirmQuoteOrderReject() {
		rejectQuote.click();
	}
	
	public void getSFTab() {
		WebElement sfTab = driver.findElement(By.id("jobTabInfo")).findElement(By.linkText("Specs"));
		if(sfTab.isDisplayed()) {
			sfTab.click();
		}
	} 
	
	public void rejectReason() {
		new Select(driver.findElement(By.className("reject-reason"))).selectByVisibleText("Quote too high");
		
	}
	
	public void verifyDelete(String d) {
		WebElement sf = driver.findElement(By.className("job-jobSpecs-div-li"));
		String del = sf.findElement(By.tagName("a")).getText();
		Assert.assertEquals(del, d);
	}
	
	public void deleteSF() throws InterruptedException {
		WebElement sf = driver.findElement(By.className("job-jobSpecs-div-li"));
		sf.findElement(By.tagName("a")).click();
		Thread.sleep(2500);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2500);
	}
	
	public void verifyDelEve(String delMsg) throws InterruptedException {
		WebElement eve = driver.findElement(By.className("project-detail-container")).findElement(By.tagName("table")).findElement(By.tagName("tbody"));
		String delEve = eve.findElement(By.tagName("td")).getText();
		//System.out.println("client delEve = " + delEve+"\n");
		Thread.sleep(2000);
		Assert.assertEquals(delMsg, delEve);
		Thread.sleep(2000);
	}
	
	public void openDetail() throws InterruptedException {
		driver.findElement(By.className("rfe-list-cot")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
	}
	
	public void cancelOrder() throws InterruptedException {
		Actions builder = new Actions(driver);
		WebElement gear = driver.findElement(By.className("detail-cancel-order"));
		gear.click();
		builder.moveToElement(gear).perform();
		Thread.sleep(1500);
		//gear.findElement(By.className("retract")).click();
		//Thread.sleep(1500);
		//driver.findElement(By.className("confirm-button")).click();
		//Thread.sleep(2500);
	}
	
	public void verifyClientStatus(String orderStat, String quoteStat) throws InterruptedException {
		String order = driver.findElement(By.className("selling-order")).getText();
		Thread.sleep(1000);
		Assert.assertEquals(orderStat, order);		
		Thread.sleep(2000);
		String quote = driver.findElement(By.className("quote-list")).findElement(By.className("ellipsis")).getText();
		Thread.sleep(1000);
		Assert.assertEquals(quoteStat, quote);
	}
	
	public void open() throws InterruptedException {
		driver.findElement(By.className("line-project-name")).click();
		Thread.sleep(1000);
	}
	
	public void gotoBuying() throws InterruptedException {
		driver.findElement(By.className("project-detail-container")).findElement(By.linkText("Buying")).click();
		Thread.sleep(1000);
	}
	
	public void verifyQStatus() throws InterruptedException {
		String status = driver.findElement(By.cssSelector("span.quote-status")).getText();
		System.out.println("quote status="+status);
		Assert.assertEquals("Pending Client Acceptance", status);
		Thread.sleep(2000);
	}
}