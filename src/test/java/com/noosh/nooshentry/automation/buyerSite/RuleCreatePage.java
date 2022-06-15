package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RuleCreatePage extends Page {
   public RuleCreatePage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(id="selMCS_chzn")
   private WebElement sfField;
  
   public void addfields(String name, String ws, String sf1, String sf2, String coworker, String client) throws InterruptedException {
	   List<WebElement> fieldSet = driver.findElement(By.className("spec-automation-popup-container")).findElements(By.tagName("fieldset"));
	   WebElement rule = fieldSet.get(0).findElement(By.tagName("input"));
	   rule.clear();
	   rule.sendKeys(name);
	   Thread.sleep(2000);
	   WebElement workspace = fieldSet.get(1).findElement(By.tagName("input"));
	   workspace.sendKeys(client);
	   workspace.sendKeys(Keys.RETURN);
	   Thread.sleep(2000);	   
	   List<WebElement> inputs = fieldSet.get(1).findElements(By.tagName("input"));
	   inputs.get(2).sendKeys(sf1);
	   inputs.get(2).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
	   inputs.get(2).sendKeys(sf2);
	   inputs.get(2).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
	   WebElement coW = fieldSet.get(3).findElement(By.tagName("input"));
	   coW.clear();
	   coW.sendKeys(coworker);
	   coW.sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
	   List<WebElement> actions = fieldSet.get(3).findElements(By.tagName("input"));
	   actions.get(1).clear();
	   actions.get(1).sendKeys(client);
	   actions.get(1).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
   }
   
   public void create() throws InterruptedException {
	   WebElement btn = driver.findElement(By.className("submitAddSpecAutomation"));
	   if(btn.isDisplayed()) {
		   btn.click();
	   } else {
		   Assert.fail();
	   }
	   Thread.sleep(3500);
   }
   
   public void addfields(String name, String ws, String sf1, String sf2, String client) throws InterruptedException {
	   List<WebElement> fieldSet = driver.findElement(By.className("spec-automation-popup-container")).findElements(By.tagName("fieldset"));
	   WebElement rule = fieldSet.get(0).findElement(By.tagName("input"));
	   rule.clear();
	   rule.sendKeys(name);
	   Thread.sleep(2000);
	   WebElement workspace = fieldSet.get(1).findElement(By.tagName("input"));
	   workspace.sendKeys(client);
	   workspace.sendKeys(Keys.RETURN);
	   Thread.sleep(2000);	   
	   List<WebElement> inputs = fieldSet.get(1).findElements(By.tagName("input"));
	   inputs.get(2).sendKeys(sf1);
	   inputs.get(2).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
	   inputs.get(2).sendKeys(sf2);
	   inputs.get(2).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
	   List<WebElement> actions = fieldSet.get(3).findElements(By.tagName("input"));
	   actions.get(1).clear();
	   actions.get(1).sendKeys(client);
	   actions.get(1).sendKeys(Keys.RETURN);
	   Thread.sleep(2000);
   }
}