package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class MainMenuPage extends Page
{
   public MainMenuPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   private static WebElement profileTitleUserName;
   //private WebElement glbDashboard;   //jenn comment out. page source code change
   //@FindBy(className="glb-active-menu-item")  //new
   private WebElement glbDashboard; 
   private WebElement glbDashboardDropdown;
   private WebElement headProfileImage;
   
   @FindBy(id="glbSpecs")
   private WebElement specs;
   
   @FindBy(xpath="mainMenuPage.createSite")
   private WebElement createSite;  
   
   @FindBy(id="glbSites")
   private WebElement creatSiteMenuItem;
   
   @FindBy(xpath="mainMenuPage.siteNameItem")
   private WebElement siteNameItem;
   
   @FindBy(linkText="Admin")
   private WebElement adminMenuItem;
   
   @FindBy(id="glbCustomizeDropdown")
   private WebElement customizationMenuItem;
   
   @FindBy(id="glbTopCreateSite")
   private WebElement creatSiteBt;

   @FindBy(xpath="mainMenuPage.sitesList")
   private WebElement sitesList;
   
   @FindBy(xpath="mainMenuPage.createSiteItem")
   private WebElement createSiteItem;
   
   @FindBy(className="sp-header-simple-logout")
   private WebElement logout;
   
   @FindBy(xpath="mainMenuPage.closeWizard")
   private WebElement closeWizard;
   
   private WebElement headImgDom;
   
   @FindBy(id="glbTopInviteButton")
   private WebElement inviteBT;
   
   @FindBy(id="glbProjects")
   private WebElement projects;
   
   @FindBy(xpath="mainMenuPage.createdProject")
   private WebElement createdProject;
   
   @FindBy(id="glbProjectsDropdown")
   private WebElement goToProjects;
   
   private WebElement advancedEditor;

   @FindBy(xpath="manageProductsPage.emptyProduct")
   private WebElement emptyProduct;
   
   private WebElement productTabClick;
   
   @FindBy(xpath="mainMenuPage.assignProductBT")
   private WebElement assignProductBT;  
   
   private WebElement assignProductsToSite;
   
   @FindBy(xpath="mainMenuPage.printedProduct")
   private WebElement printedProduct;
  
   public String getProfileTitle() {
      return profileTitleUserName.getText();
   }
   public CreateSitePopup createSitePopup () {
      createSite.click();
      return new CreateSitePopup(driver);
   }   
   public void createSiteMenuItem() throws InterruptedException {
	   clickSubMenuItem(sitesList, createSiteItem);
   }  
   public void assignProductToBuyerSite() {
	   assignProductsToSite.click();
   }
   public void logoutSite() {
	   //logout.click();    //10/15
	   ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
   }
  // check, may be it double 
   public void closeWizardPopup()
   {
	   closeWizard.click();
   }   
   public String getSPName(String firstName, String lastName) {
	   return firstName + " " + lastName;
   }  
   public void clickSiteMenuItem() throws InterruptedException {
	   clickSubMenuItem(creatSiteMenuItem, siteNameItem);
   }  
   public void clickSiteItemMenu() {
	   creatSiteMenuItem.click();
   }
   
   // !!!!!!!! for EI
   public void clickCustomizationMenuItemIE() throws InterruptedException {
	   clickSubMenuItemIE(adminMenuItem, customizationMenuItem, "glbCustomizeDropdown");
   }
   // !!!!!!!!! for IE
   public void clcikCustomMenu(NooshWebDriver driver) {
	   ((JavascriptExecutor)driver).executeScript("$('div#').hover();");
   }   
   public boolean checkLogoImageSPSite(String imageFileName) {	   
	   return checkImageDisplayed(headImgDom, imageFileName);
   }
   public void clickInviteBT() {
	   inviteBT.click();
   }
   public void clickProjectMenuItem() {
	   projects.click();
   }  
   public void clickProjectMenu() throws InterruptedException {	   
      glbDashboard.click();
   }  
   public void clickCreatedProject() {
	   createdProject.click();
   }
   public void clickGoToDashboard() throws InterruptedException {
	   clickSubMenuItem(glbDashboard, glbDashboardDropdown);
   }
   public boolean checkUserProfileImageDisplayed() {
	   return headProfileImage.isDisplayed();
   } 
   public void clickAdvancedEditorBT() {
	   advancedEditor.click();
   }   
   public void clickEmptyProduct() {
	   emptyProduct.click();
   }
   public void clickPoductsTab() {
	   productTabClick.click();
   }
   public void clickAssignProduct() {
	   assignProductBT.click();
   }
   public void clickPrintedPdoductDelete() {
	   printedProduct.click();
   }
}

