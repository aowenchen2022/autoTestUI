package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class AddSmartFormPage extends Page
{
	public AddSmartFormPage(WebDriver driver) {
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
	
	@FindBy(id="cpAddProducts")
	private WebElement addSmart;
	  
	@FindBy(id="productsPopupUl")
	private WebElement smartForm;
	
	@FindBy(id="createWorkspaceProjectProductList")
	private WebElement smartFormInWSCreateProj;
	
	public void clickAddSmart() throws InterruptedException {
		addSmart.click();
		Thread.sleep(1000);
	}
	
	public void clickSmartForm() throws InterruptedException {
		//WebElement startElement = driver.findElement(By.id("createProjectProductUl"));
		//WebElement img = smartForm.findElement((By.tagName("img")));
		List<WebElement> imgs = smartForm.findElements((By.tagName("img")));
		imgs.get(1).click();
		Thread.sleep(3000);	
	}
	
	public void clickSmartForm2() throws InterruptedException {
		List<WebElement> imgs = smartForm.findElements((By.tagName("img")));
		imgs.get(4).click();
		Thread.sleep(3000);	
	}
	
	public void clickSmartFormSdm() throws InterruptedException {
		List<WebElement> imgs = smartForm.findElements((By.tagName("img")));
		imgs.get(5).click();
		Thread.sleep(3000);	
	}
	
	public void selectSmartFormInWS() {
		smartFormInWSCreateProj.click();
	}
	
	public void inputOneQuantity(String quantitySP1) throws InterruptedException {
		 List<WebElement> inputs = driver.findElements(By.tagName("input"));
		 for (WebElement input : inputs) {
			 String name = input.getAttribute("reportingname");
			 if (name != null && name.equalsIgnoreCase("QUANTITY1")) {
				  input.clear();
				  input.sendKeys(quantitySP1);
				  break;
			 }
		 }
		 Thread.sleep(1000);
	}
	
}