package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class WSPage extends Page {
	public WSPage(WebDriver driver) {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	private WebElement glbTopCreateSite;
	private WebElement createPageCancel;
	private WebElement createPageSubmit;
	private WebElement topMenuGear;
	
	@FindBy(id="createPagePageName")
	private WebElement client;
	public void clickGlbWS() {
		//glbTopCreateSite.click();
		WebElement createWS = driver.findElement(By.className("glb-body-central-main"));
		createWS.findElement(By.className("glbTopCreateSite")).click();
	}
	
	public void clickWS(NooshWebDriver driver, String item) throws InterruptedException {
	    String currentURL = driver.getCurrentUrl();
		int index = currentURL.indexOf("/service");
		driver.get(currentURL.substring(0, index) + item);
	    Thread.sleep(3000);
	}
	
	public void inpputClientName(String clientName) {
		client.clear();
		client.sendKeys(clientName);		
	}
	
	public void cancelWS() {
		createPageCancel.click();
	}
	
	public void submitWS() {
		createPageSubmit.click();
	}
}
