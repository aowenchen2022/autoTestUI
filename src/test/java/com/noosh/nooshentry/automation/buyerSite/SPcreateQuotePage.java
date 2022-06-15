package com.noosh.nooshentry.automation.buyerSite;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.CommonUtils;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class SPcreateQuotePage extends Page {
	public SPcreateQuotePage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	} 
	/*
	@FindBy(id="tabs-quote-matrix-view")
	private WebElement quoteTabs;
    */
	
	private WebElement sendQuote;
	
	public void createQuote(String unitcost1, String cost1, String markup) throws InterruptedException {
		WebElement spec = driver.findElement(By.className("ui-dialog"));
		WebElement specField = spec.findElement(By.className("quotes")).findElement(By.className("quote-spec")).findElement(By.className("spec-qty"));
		List<WebElement> fields = specField.findElements(By.tagName("input"));
		fields.get(1).clear();
		fields.get(1).sendKeys(unitcost1);
		Thread.sleep(2500);
		fields.get(2).clear();
		fields.get(2).sendKeys(cost1);
		Thread.sleep(2500);
		fields.get(3).clear();
		fields.get(3).sendKeys(markup);		
		Thread.sleep(2000);
		spec.findElement(By.className("ui-dialog-buttonset")).findElement(By.className("previewQuote")).click();
		Thread.sleep(3500);
	}
	
	public void getGear() throws InterruptedException {
		WebElement g = driver.findElement(By.cssSelector("div.ui-icon-settings2.glb-settings-icon"));
		if(!g.isDisplayed()) {
			throw new IllegalStateException("Gear missing.");
		} else {
			g.click();
			Thread.sleep(2000);
		}		
	}
	
	public void reviseQ() throws InterruptedException {
		driver.findElement(By.cssSelector("li.revise")).click();
		Thread.sleep(3000);
	}
	
	public void reviseQFields() throws InterruptedException {
		WebElement top = driver.findElement(By.className("ui-dialog")).findElement(By.className("popup"));		
		WebElement qTitle = top.findElement(By.tagName("input"));
		String t = qTitle.getAttribute("value");
		System.out.println("title="+t);
		qTitle.clear();
		qTitle.sendKeys(t + " edited");
		Thread.sleep(1000);
		WebElement des = top.findElement(By.tagName("textarea"));
		des.clear();
		des.sendKeys("Edit quote.");
		Thread.sleep(1000);
		WebElement q = driver.findElement(By.className("ui-dialog")).findElement(By.className("quotes")).findElement(By.className("quote-spec")).findElement(By.className("spec-qty"));
		List<WebElement> quantity = driver.findElements(By.className("item-quantity"));
		quantity.get(3).clear();
		Thread.sleep(1000);
		quantity.get(3).sendKeys("500");
		Thread.sleep(1000);
	}
	
	public void preview() throws InterruptedException {
		driver.findElement(By.cssSelector("div.sp-simple-button.previewQuote")).click();
		Thread.sleep(3000);
	}
	
	public void createQuote(String unitcost1, String cost1, String markup, String markupfix1, String unitcost2, 
			          String cost2, String markup2, String markupfix2, String tax, String filename) throws InterruptedException, IOException {
		try {
			WebElement etax = driver.findElement(By.className("tax-rate"));
			WebElement spec = driver.findElement(By.className("ui-dialog"));
			WebElement specField = spec.findElement(By.className("quotes")).findElement(By.className("quote-spec")).findElement(By.className("spec-qty"));
			List<WebElement> units = specField.findElements(By.className("item-unit"));
			List<WebElement> costs = specField.findElements(By.className("item-cost"));
			List<WebElement> markups = specField.findElements(By.className("item-markup"));
			List<WebElement> markupfixes = specField.findElements(By.className("item-markup_fixed"));
			units.get(0).clear();
			units.get(0).sendKeys(unitcost1);
			Thread.sleep(2500);
			costs.get(0).clear();
			costs.get(0).sendKeys(cost1);
			Thread.sleep(2500);
			markups.get(0).clear();
			Thread.sleep(1000);
			markups.get(0).sendKeys(markup);		
			Thread.sleep(2500);
			markupfixes.get(0).clear();
			Thread.sleep(1000);
			markupfixes.get(0).sendKeys(markupfix1);		
			Thread.sleep(2500);
			units.get(1).clear();
			units.get(1).sendKeys(unitcost2);
			Thread.sleep(2500);
			costs.get(1).clear();
			costs.get(1).sendKeys(cost2);
			Thread.sleep(2500);
			markups.get(1).clear();
			Thread.sleep(1000);
			markups.get(1).sendKeys(markup2);		
			Thread.sleep(2000);
			markupfixes.get(1).clear();
			Thread.sleep(1000);
			markupfixes.get(1).sendKeys(markupfix2);		
			Thread.sleep(2500);
			spec.findElement(By.className("ui-dialog-buttonset")).findElement(By.className("previewQuote")).click();
			Thread.sleep(5000);
		} catch(Exception e) {
			CommonUtils commonutils = new CommonUtils(driver);
			commonutils.getScreenShot(filename);
		}
	}
	
	public void sendQuote() throws InterruptedException {
		if(!sendQuote.isDisplayed()) {
  		     throw new IllegalStateException("The Send Quote button is missing!");
  	     } else {
  	    	sendQuote.click();
  	    	Thread.sleep(5000);
  	     } 	   
	}
}

