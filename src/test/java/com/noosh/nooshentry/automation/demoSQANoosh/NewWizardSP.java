package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewWizardSP extends Page
{
   
	public NewWizardSP(WebDriver driver)
    {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }  
	
	@FindBy(xpath="newWizardSP.clientCompanyName")
	private WebElement clientCompayName;
	
	private WebElement webpageCreateBtn;
	
	@FindBy(xpath="newWizardSP.uploadBuyerLogo")
	private WebElement uploadBuyerLogo;
	
	@FindBy(xpath="newWizardSP.printersCategory")
	private WebElement printers;
	
	public void setClientCompanyName(String companyName){
		type(clientCompayName, companyName);
	}
	
    public void uploadBuyerLogo(String buyerSiteLogo) {
    	uploadFileRelative(uploadBuyerLogo, buyerSiteLogo);
    }
    
    public void selectPrinters() {
    	printers.click();
    }
    
    public void clickWebPageCreateBT() {
    	webpageCreateBtn.click();
    }
}
