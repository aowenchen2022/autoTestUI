package com.noosh.nooshentry.automation.buyerSite;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class AssignSmartFormPage extends Page {
	public AssignSmartFormPage(WebDriver driver) {
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
	
	@FindBy(id="productTabClick")
	private WebElement smartFormTB;
	  
	@FindBy(id="assignProductsToSite")
	private WebElement smartFormBtn;
	
	@FindBy(id="productsPopupUl")
	private WebElement smartForm;
	
	@FindBy(id="serviceProductListTable")
	private WebElement smartFormList;
	
	@FindBy(id="addProductsToSite")
	private WebElement smartFormAssignBtn;
	
	public void clickSmartFormTB() throws InterruptedException {
		smartFormTB.click();
		Thread.sleep(1000);
	}
	
	public void clickAssignSmartForm() throws InterruptedException {
		//smartFormBtn.click(); //5/14 comment out
        driver.findElement(By.className("create-new-product")).click();
		Thread.sleep(2000);
	}
	
	public void assignSmartForm() throws InterruptedException{
		//smartFormList.findElement(By.tagName("input")).click();		   
		List<WebElement> smartForms = smartFormList.findElements(By.tagName("input"));
		smartForms.get(2).click();
		Thread.sleep(500);
		smartForms.get(3).click();
		Thread.sleep(1000);
	}
	
	public void clickSmartFormBtn() throws InterruptedException {
		smartFormAssignBtn.findElement(By.className("sp-simple-button")).click();
	    Thread.sleep(5000);
	}
	
	public void clickSmartForm() throws InterruptedException {
		//WebElement startElement = driver.findElement(By.id("createProjectProductUl"));
		WebElement img = smartForm.findElement((By.tagName("img")));
		img.click();
		Thread.sleep(3000);	
	}	
}