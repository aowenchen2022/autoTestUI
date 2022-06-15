package com.noosh.nooshentry.automation.buyerSite;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class BuyerSitePage extends Page
{
   public BuyerSitePage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
   
   @CacheLookup
   @FindBy(xpath="buyerSitePage.brochureProject")
   private WebElement brochureProject;
  
   @FindBy(xpath="buyerSitePage.clickProject")
   private WebElement clickProject;
   
   @FindBy(className="bHead-menu-arrow-dropdown")
   private WebElement menuArrow;
   
   @FindBy(xpath="buyerSitePage.logout")
   private WebElement logout;
   
   @FindBy(id="bHead-user-image")
   private WebElement headImg;
   
   private WebElement userLogout;       //10/4
   
   @FindBy(xpath="buyerSitePage.createdProject2")
   private WebElement createdProject;
   
   @FindBy(className="bDash-projName")
   private WebElement createdBuyerProject;
   
   @FindBy(xpath="buyerSitePage.createdProjectAdvEd")
   private WebElement projectName;
   
   @FindBy(id="bHead-logo-image")
   private WebElement buyerSiteLogo;
   
   @FindBy(className="bHead-service-logo")
   private WebElement logoSP;
   
   @FindBy(xpath="buyerSitePage.projectStatus")
   private WebElement projectStatus;
   
   @FindBy(xpath="buyerSitePage.spProject")
   private WebElement spProject;
   
   @FindBy(xpath="buyerSitePage.createdProjectAdvEd")
   private WebElement createdProjectAdvEd;
   
   @FindBy(xpath="buyerSitePage.productionSpecs")
   private WebElement productionSpecsTab;
   
   @FindBy(xpath="buyerSitePage.message1")
   private WebElement message1;
   
   @FindBy(xpath="buyerSitePage.message11")
   private WebElement message11;
   
   @FindBy(xpath="buyerSitePage.message5")
   private WebElement message5;
   
   @FindBy(xpath="buyerSItePage.message61")
   private WebElement message61;
   
   @FindBy(xpath="buyerSitePage.inviteBT")
   private WebElement invateBT;
   
   @FindBy(xpath="buyerSitePage.elailInput")
   private WebElement emailInput;
   
   @FindBy(xpath="buyerSitePage.envelopeProject")
   private WebElement envelope;
   
   @FindBy(xpath="buyerSitePage.createdProject3")
   private WebElement project;
   
   private WebElement bDashTeamClose;
   private WebElement dashProductList;
   
   @FindBy(xpath="buyerSitePage.copyProject")
   private WebElement copyProject;
   
   @FindBy(xpath="buyerSitePage.copy")
   private WebElement copy;
   
   @FindBy(xpath="buyerSitePage.textBoxField")
   private WebElement textBoxField;
   
   @FindBy(xpath="buyerSitePage.projectNameDraft")
   private WebElement projectNameDraft;
      
   public void getNewBrochureProject() {
      brochureProject.click();
   }
   
   public void clickProject() {
      clickProject.click();
   }
   
   public void clickCreatedProject() {
	   projectName.click();
   }
   
   public void clickBuyerProject() {
	   createdProject.click();
   }
  
   public void clickLogout() throws InterruptedException {
	   Thread.sleep(2000);
	   clickSubMenuItem(headImg, userLogout);
	   Thread.sleep(2000);
   }

   public boolean checkBuyerSiteLogo() {
	   return buyerSiteLogo.isDisplayed();
   }
   
   public boolean checkSPLogo() {
	   return logoSP.isDisplayed();
   }
   
   public String getProjectStatus() {
	   return projectStatus.getText();
   }

   public void clickBuyerProjectIE() {
	   createdBuyerProject.click();
   }
   public void getNewSPProject(NooshWebDriver driver, String product) {
	   driver.findElementByXPath("buyerSitePage.spProject", "0", product).click();
   }
   public void clickCreatedProjectAdvEd() {
	   createdProjectAdvEd.click();
   }
   public void clickProductionSpecsTab() {
	   productionSpecsTab.click();
   }
   public String getMessage1Text() {
	   return message1.getText();
   }
   public String getMessage11Text() {
	   return message11.getText();
   }
   public String getMessage5Text() {
	   return message5.getText();
   }
   public String getMessage61Text() {
	   return message61.getText();
   }
   public void buyerInviteBT() {
	   invateBT.click();
   }
   public void setNewBuyerEmail(String email) {
	   type(emailInput, email);
	   emailInput.sendKeys(Keys.RETURN);
   }
   public void closeInviteCoworker() {
	   bDashTeamClose.click();
   }
   public void clickEnvelope(NooshWebDriver driver, String product) {
	   driver.findElementByXPath("buyerSitePage.envelopeProject", "0", product).click();
   }
   public void clickSpec(NooshWebDriver driver, String spec) {
	   driver.findElementByXPath("buyerSitePage.envelopeProject", "0", spec).click();
   }
   public void selectEnvelopeProductBuyerSite() throws InterruptedException {
   new Actions(driver).moveToElement(dashProductList).build().perform();
   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
			,brochureProject);
   Thread.sleep(500);
   brochureProject.click();
   }
   public void selectAdvProductBuyerSite(NooshWebDriver driver, String advProduct) throws InterruptedException {
   WebElement product = driver.findElementByXPath("buyerSitePage.spProject", "0", advProduct);	   
   new Actions(driver).moveToElement(dashProductList).build().perform();
   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
			,product);
   Thread.sleep(500);
   product.click();
   }
   public void clickGearCopyProject() {
	   copyProject.click();
   }
   public void copyCreatedProject() {
	   copy.click();
   }
   public String getTextBoxText(NooshWebDriver driver, String text) {
	   return driver.findElementByXPath("buyerSitePage.textBoxField", "0", text).getText();
   }
   public void clickDraftProject(NooshWebDriver driver, String text) {
	   driver.findElementByXPath("buyerSitePage.textBoxField", "0", text).click();
   }
   public String getProjectDraftName() {
	   return projectNameDraft.getText();
   }
}

