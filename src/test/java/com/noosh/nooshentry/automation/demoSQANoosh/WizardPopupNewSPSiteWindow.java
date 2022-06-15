package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class WizardPopupNewSPSiteWindow extends Page
{	
	public WizardPopupNewSPSiteWindow(WebDriver driver)
	{
	    super(driver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	@FindBy(id="ui-dialog-title-firstTimeWizard")
	private WebElement wizardTitle;
	
	@FindBy(id="profilePicture-uploader")
	private WebElement uploadPictureFile;
	
	private WebElement addressButton;
	
	@FindBy(id="wizardLine1")
	private WebElement address1;
	
	private WebElement wizardCity;
	
	private WebElement wizardState;
	
	private WebElement wizardPostal;
	
	private WebElement wizardCountry;
	
	@FindBy(id="firstTimeWizardNext")
	private WebElement nextBT;
	
	private WebElement wizardSiteName;
	
	@FindBy(id="glbOkayCheck")
	private WebElement okFlag;
	
	@FindBy(id="siteLogo-uploader")
	private WebElement siteLogo;
	
	private WebElement wizardSiteUrlBase;
	
	private WebElement wizardSiteUrlName;
	
//	@FindBy(xpath="//input[@pid='5000340']") 
	@FindBy(xpath="wizardPopupNewSPSiteWindow.envelope")
    private WebElement envelope;
	
//	@FindBy(xpath="//input[@pid='5000020']") 
	@FindBy(xpath="wizardPopupNewSPSiteWindow.brochure")
    private WebElement brochure;
	
//	@FindBy(xpath="//input[@pid='5000140']")
	@FindBy(xpath="wizardPopupNewSPSiteWindow.printedProduct")
	private WebElement printedProduct;
	
    public String getPopupTitle()
    {
       return wizardTitle.getText();
    }    

    public void uploadProfilePicture(String profilePicture)
    {
    	uploadFileRelative(uploadPictureFile, profilePicture);
    }
    
	public void clickAddressBT()
	{
		addressButton.click();
	}
	
    public void setAddressSP(String street, String city, String state, String zip)
    {
    	type(address1, street);
    	type(wizardCity, city);
    	type(wizardState, state);
    	type(wizardPostal, zip);
    }
    
    public void setCountryName(String country)
    {
    	wizardCountry.sendKeys(country);
    }
    
    public void clickNextBT()
    {
    	nextBT.click();
    }
    
    public void setBuyerSiteName(String specificSiteName)
    {
    	wizardSiteName.sendKeys(specificSiteName);
    }
    
    public boolean checkSiteNameOK()
    {
    	return isElementPresent(okFlag);
    }

	public void uploadBuyerSiteLogo(String siteLogoFile)
	{
		uploadFileRelative(siteLogo, siteLogoFile);
	}
	
    public String getSiteUrlBase()
    {
       return wizardSiteUrlBase.getText();
    }   
    
    public String getSiteUrlName()
    {
       return wizardSiteUrlName.getText();
    }   
    
    public void selectEnvelope()
    {
    	envelope.click();
    }
    
    public void selectBrochure()
    {
    	brochure.click();
    }
    
    public void selectPrintedProduct()
    {
        printedProduct.click();
    }
}