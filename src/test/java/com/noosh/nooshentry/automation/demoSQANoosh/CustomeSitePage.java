package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CustomeSitePage extends Page
{
   public CustomeSitePage(WebDriver driver)
      {
          super(driver);
          PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
      }
  
   @FindBy(css="div.bDash-items-prodText")
   private WebElement envelope;

   @FindBy(css="li[title='Brochure'] > div.bDash-items-prodText")
   private WebElement brochure;

   @FindBy(css="li[title='Trade Show / Exhibit Design'] > div.bDash-items-prodText")
   private WebElement tradeShow;

   @FindBy(css="li[title='Printed Product - Request'] > div.bDash-items-prodText")
   private WebElement printedProductRequest;

   @FindBy(css="li[title='Postcard'] > div.bDash-items-prodText")
   private WebElement postcard;

   @FindBy(css="li[title='Printed Product - Full'] > div.bDash-items-prodText")
   private WebElement printedProductFull;

   @FindBy(css="li[title='Retail Display'] > div.bDash-items-prodText")
   private WebElement retailDisplay;

   @FindBy(css="li[title='Poster'] > div.bDash-items-prodText")
   private WebElement poster;

   @FindBy(css="li[title='Business Form - Unit Set'] > div.bDash-items-prodText")
   private WebElement businessForm;
   
   @FindBy(id="addOne")
   private WebElement expensionFrame;
   
   @FindBy(className="site-preview-head" )
   private WebElement rollUp;
   
   @FindBy(className="site-preview-slider-head")
   private WebElement turnOff;
   
   @FindBy(id="productTabClick")
   private WebElement productTab;
   
   @FindBy(linkText="Team")
   private WebElement customersLink;

   @FindBy(xpath="customeSitePage.inviteNewCustomer")
   private WebElement inviteNewCustomer;
   
   @FindBy(className="invite-page-link")
   private WebElement inviteNew;
   
   @FindBy(id="bHead-logo-image")
   private WebElement buyerLogo;
   
   private WebElement inviteCustomerWrapperEmail;
   
   private WebElement inviteCustomerWrapperAdd;
   
   private WebElement sendInvitation;
   
   private WebElement loginForm;
   
   @FindBy(className="site-preview-head")
   private WebElement sitePreviewHead;

   @FindBy(linkText="Overview")
   private WebElement clickOverviewTab;
   
   @FindBy(xpath="customeSitePage.uriSPSite")
   private WebElement uriSPSite;
   
   // Edit buyer site by SP
   @FindBy(xpath="customeSitePage.editSiteBT")
   private WebElement editSiteBT;
   
   @FindBy(className="createSiteName")
   private WebElement inputSiteName;
   
   @FindBy(name="file")
   private WebElement uploadBuyerLogo;
   
   @FindBy(xpath="customeSitePage.saveBT")
   private WebElement saveBT;
   
   @FindBy(xpath="customeSitePage.buyerSiteNameField")
   private WebElement buyerSiteNameField;
   
   @FindBy(className="site-overview-companyLogo-preview")
   private WebElement logoOverview;
   
   @FindBy(xpath="customeSitePage.logoPreview")
   private WebElement logoPreview;
   
   @FindBy(className="site-overview-companyLogo-preview")
   private WebElement buyerSiteLogo;
   
   public String getEnvelopeTitle()
   {
      return envelope.getText();
   }      
   public String getBrochureTitle()
   {
      return brochure.getText();
   }      
   public String getTradeShowTitle()
   {
      return tradeShow.getText();
   } 
   public String getPrintedProductRequestTitle()
   {
      return printedProductRequest.getText();
   }     
   public String getPostcardRequestTitle()
   {
      return postcard.getText();
   }     
   public String getPrintedProductFullTitle()
   {
      return printedProductFull.getText();
   }        
   public String getRetailDisplayFullTitle()
   {
      return retailDisplay.getText();
   }     
   public String getPosterTitle()
   {
      return poster.getText();
   }   
   public String getBusinessFormTitle()
   {
      return businessForm.getText();
   }    
   public void getOverviewTab()
   {
	   clickOverviewTab.click();
   }  
   public void expFrame()
   {
      expensionFrame.click();
   }
   public void rollUpSiteFrame()
   {
      rollUp.click();
   }  
   public void turnOffSiteFrame()
   {
	   turnOff.click();
   }
   public void getProductTab()
   {
      productTab.click();
   } 
   public void getCustomersTab()
   {
      customersLink.click();
   }
   public void inviteNewCuctomers()
   {
      inviteNewCustomer.click(); 
   }   
   public void news()
   {
	   inviteNew.click();
   }
   public void putNewCustomerEmail(String customerEmail)
   {
      inviteCustomerWrapperEmail.clear();
      inviteCustomerWrapperEmail.sendKeys(customerEmail);
   }   
   public void clickAddBT()
   {
      inviteCustomerWrapperAdd.click();
   }   
   public void sendInvitationEmail()
   {
      sendInvitation.click();
   }  
   public boolean checkLoginFormDisplayed()
   {
	   return isElementPresent(loginForm);
   }   
   public boolean checkLogoBuyerSiteDisplayed()
   {
	   return isElementPresent(buyerLogo);
   }   
   public String getUriSPSite()
   {
	   return uriSPSite.getText();
   }
   
   /*
    * Edit buyer site by SP
    */
   public void clickEditSiteBT()
   {
	   editSiteBT.click();
   }
   public void setNewBuyerSiteName(String newSiteName)
   {
	   type(inputSiteName, newSiteName);	   
   }   
   public void uploadBuyerSiteLogo(String buyerSiteLogo)
   {
	   uploadFileRelative(uploadBuyerLogo, buyerSiteLogo);
   }
   public void clickSaveBT()
   {
	   saveBT.click();
   }
   public String getBuyerSiteName()
   {
	   return buyerSiteNameField.getText();
   }
   public String getOverviewLogoImageAttribute()
   {
   	   return logoOverview.getAttribute("src");
   }
   public String getPreviewLogoImageAttribute()
   {
	   return logoPreview.getAttribute("src");
   }
   public boolean checkBuyerSiteLogo()
   {
	   return buyerSiteLogo.isDisplayed();
   }
   public boolean checkOverviewTabVisable()
   {
	   return clickOverviewTab.isEnabled();
   }
   public void clickSaveBTIE() {
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBT);
   }
   
   /*
    * Check workspace tabs available 
    */
   
   public boolean checkWorkspaceTabsAvailable() {
	   return isElementPresent(driver, "Overview");
   }
   public boolean checkPreviewTabAvailable() {
	   return rollUp.isDisplayed();
   }
}
   