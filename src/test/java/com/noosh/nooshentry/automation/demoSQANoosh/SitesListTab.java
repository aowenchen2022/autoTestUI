package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class SitesListTab extends Page
{
   public SitesListTab(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }

   @FindBy(xpath="sitesListTab.selectSite")
   private WebElement selectSite;
   
   //@FindBy(id="glbSites")       //10/2
  // private WebElement sites;     //10/2
   @FindBy(id="landingBuying")
   private WebElement buying;
   
   @FindBy(id="landingSelling")   //10/22
   private WebElement selling;    //10/22
   
   @FindBy(id="glbSitesDropdown")
   private WebElement goToSites;
   
   @FindBy(linkText="Preview")
   private WebElement sitePreviewHead;
   
   @FindBy(xpath="sitesListTab.buyerSiteSDM")
   private WebElement buyerSiteSDM;
   
   @FindBy(xpath="sitesListTab.productForBuyer")
   private WebElement assignProductCheckBox;
   
   @FindBy(xpath="sitesListTab.assignBT")
   private WebElement assignBT;
   
   private WebElement serviceProductListPopup;
   
//   assign products and services
   
   public void getPreviewPage() {
      selectSite.click();
   }
   
   public void getSitesList() {
      //sites.click();     //10/2
	   buying.click();
   }   
   
   public void getSellingSitesList() {   //10/22
		   selling.click();              //10/22
   }   
   
   //depricated - delete
   public void getSiteList() throws InterruptedException  {
      clickSubMenuItem(buying, goToSites);
   } 
   
   public void getSitePreviewPopup() {
      sitePreviewHead.click();
   }
   
   public void getBuyerSite(NooshWebDriver driver, String site) {
	   driver.findElementByXPath("sitesListTab.buyerSiteSDM", "0", site).click();
   }
   
   public void assignPoductToBuyer(NooshWebDriver driver, String product) {
	   driver.findElementByXPath("sitesListTab.productForBuyer", "0", product).click();
   }
   
   public void clickAssignBT() {
	   assignBT.click();
   }
   
   public void assignProductToBuyerScroll(NooshWebDriver driver, String product) throws InterruptedException {
	   WebElement element = driver.findElementByXPath("sitesListTab.productForBuyer", "0", product);
	   selectEnvelopeProduct(serviceProductListPopup, element);
   }
}
