package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CreateNewProduct extends Page
{
	 public CreateNewProduct(WebDriver driver)
	 {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	   
	 @FindBy(xpath="createNewProduct.envelopeIcon")
     private WebElement envelopeIcon;
	 
	 @FindBy(xpath="createNewProduct.brochureIcon")
	 private WebElement brochureIcon;
	 
	 @FindBy(xpath="createNewProduct.envelopLinkBuyer")
	 private WebElement envelopLinkBuyer;
	 
	 @FindBy(xpath="createNewProduct.emptyProduct")
	 private WebElement emptyProduct;
	 
	 public void clickEnvelopeIcon() {
		 envelopeIcon.click();
	 }
	 public void clickBrochureIcon() {
		 brochureIcon.click();
	 }	
	 public void clickEnvelopLink() {
		 envelopLinkBuyer.click();
	 }
	 public void clickEmptyIcon() {
		 emptyProduct.click();
	 }
}