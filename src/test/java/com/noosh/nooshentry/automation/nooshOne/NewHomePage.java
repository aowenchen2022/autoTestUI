package com.noosh.nooshentry.automation.nooshOne;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class NewHomePage extends Page {
	
	public NewHomePage(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   } 

   @FindBy(xpath="newHomePage.projectName")
   private WebElement projectName;
   
   @FindBy(xpath="newHomePage.project")
   private WebElement project;
   
   @FindBy(xpath="newHomePage.status")
   private WebElement status;
   
   @FindBy(xpath="newHomePage.couter")
   private WebElement couter;
   
   @FindBy(className="ui-icon-settings")
//   @FindBy(xpath="newHomePage.gear")
//   @FindBy(css="ul#glbHeadSettings > li[1] > div") 
   private WebElement gear;
   
   @FindBy(xpath="newHomePage.gearArrow")
   private WebElement gearArrow;
   
   @FindBy(xpath="newHomePage.submenuItem")
   private WebElement submenuItem;
   
   @FindBy(xpath="newHomePage.createWorkspace")
   private WebElement createWorkspace;
   
   @FindBy(xpath="newHomePage.clientUser")
   private WebElement clientUser;
   
   @FindBy(className="new-site-field-label")
   private WebElement clientName;
   
   @FindBy(xpath="newHomePage.clientNameInput")
   private WebElement clientNameInput;
   
   @FindBy(xpath="newHomePage.clientLogo")
   private WebElement clientLogo;
   
   @FindBy(xpath="newHomePage.fig")
   private WebElement fig;
   
   @FindBy(xpath="newHomePage.inviteClientBT")
   private WebElement inviteClientBT;
   
   @FindBy(xpath="newHomePage.createWorkspaceHomePage")
   private WebElement createWorkspaceBT;
   
   @FindBy(xpath="newHomePage.workspaceBar")
   private WebElement projectBar;
   
   private WebElement shareButton;
   
   @FindBy(xpath="newHomePage.requestForm")
   private WebElement requestForm;
   
   @FindBy(xpath="newHomePage.workspaces")
   private WebElement workspaces;
   
   @FindBy(xpath="newHomePage.projectInList")
   private WebElement projectInList;
   
   @FindBy(xpath="newHomePage.projectInListOpen")
   private WebElement openProjectSPname;
   
   @FindBy(xpath="newHomePage.projectN")
   private WebElement projectN;
   
   private WebElement customizeButton;
   
   @FindBy(xpath="newHomePage.currentProjectStatus")
   private WebElement currentProjectStatus;
   
   @FindBy(xpath="newHomePage.newProjectStatus")
   private WebElement newProjectStatus;
   
   @FindBy(xpath="newHomePage.inputNewProjectStatus")
   private WebElement inputNewProjectStatus;
   
   @FindBy(xpath="newProjectPage.projectStatus")
   private WebElement projectStatus;
   
   private WebElement clientCreateProject;
   
   @FindBy(className="ui-icon-viewChange-project")
   private WebElement changeViewProject;
   
   @FindBy(className="ui-icon-viewChange-default")
   private WebElement changeView;
   
   @FindBy(xpath="newHomePage.inviteToProject")
   private WebElement inviteToProject;
   
   @FindBy(xpath="newHomePage.client")
   private WebElement client;
   
   @FindBy(xpath="newHomePage.branding")
   private WebElement branding;
   
   @FindBy(id="glbHeadSettings")            //10/16
   private WebElement homeGearIcon;     //10/16
   
   @FindBy(id="topMenuSettingsMenuItemAccount")
   private WebElement accountLink;
   
   public WebElement previewCustomerViewButton;
	
   public String getProjectName() {
	   return projectName.getText();
   }
   public String getProjectStatus(NooshWebDriver driver, String project) {	   
	   return status.getText();
   }
   public String getProjectCouter(NooshWebDriver driver, String project) {
	   char counter = driver.findElementByXPath("newHomePage.couter", "0", project).getText().charAt(0);
	   return Character.toString(counter);
   }
   public void clickBrandingSubmenu() throws InterruptedException{
	   gear.click();
	   Thread.sleep(500);
//	   new Actions(driver).moveToElement(gear).build().perform();
	   branding.click();
   }
   public void clickGearSubmenuItem(NooshWebDriver driver, String item) throws InterruptedException {
	   Thread.sleep(3000);
	   String currentURL = driver.getCurrentUrl();
	   int index = currentURL.indexOf("/service");
	   driver.get(currentURL.substring(0, index) + item);
	   Thread.sleep(3000);
   }
   public void clickGearSubmenuItemIE() {	  
	   gear.click();
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", requestForm);
   }
   public void clickGearWorkspacesIE() {
	   gear.click();
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", workspaces);
   }
   public void clickProjectInList(NooshWebDriver driver, String project) {
	   driver.findElementByXPath("newHomePage.project", "0", project).click();	   
   }
   public void clickPojectSPList(NooshWebDriver driver, String project) {
	   driver.findElementByXPath("newHomePage.projectN", "0", project).click();
   }
  
   public void clickProjectInListNew() {
	   projectInList.click();	   
   }
   public void clickCreateWorkspace() {
	   createWorkspace.click();
   }
   public String getClientUser(NooshWebDriver driver, String text) {
	   return driver.findElementByXPath("newHomePage.clientUser", "0", text).getText();	
   }
   public void changeClientName(String newClientName) {
	   clientName.click();
	   type(clientNameInput, newClientName);	   
   }
   public String getClientName() {
	   return clientName.getText();
   }
   
   public void uploadClientLogo() {
	   new Actions(driver).moveToElement(clientLogo).build().perform();
	   fig.click();	   
   }
   public void clickInviteClient() {
	   inviteClientBT.click();
   }
   public void clickClientLogo() {
	   clientLogo.click();
   }
   public String getClientLogo() {
	   return clientLogo.getAttribute("src");
   }
   public void clickProject() {
	   projectName.click();
   }  	
   public void clickCreateWorkspaceHInHomePage() {
	   createWorkspaceBT.click();
   }
   public void clickShareBT() {
	   new Actions(driver).moveToElement(projectBar).build().perform();
	   shareButton.click();
   }
   public void clickCustomizeBuyerSiteHovering() {
	   new Actions(driver).moveToElement(projectBar).build().perform();
	   customizeButton.click();
   }
   public void changeProjectStatus(NooshWebDriver driver, String project) throws InterruptedException {
	   currentProjectStatus.click();
	   projectStatus.click();
	   newProjectStatus.click();
   }
   public void clickCreateProjectHovering() {
	   new Actions(driver).moveToElement(projectBar).build().perform();
	   clientCreateProject.click();
   }
   public void changeProjectsListView() {
	   changeView.click();
   }
   
   public void changeProjectsListView2() {
      changeViewProject.click();
   }
   public void openProjectSPName() {
	   openProjectSPname.click();
   }
   public void assignClientForProject(NooshWebDriver driver, String project, String clientName) {
	   driver.findElementByXPath("newHomePage.inviteToProject", "0", project).click();
	   driver.findElementByXPath("newHomePage.client", "0", clientName).click();   
   }
   public void clickClientViewBTHovering() {
	   new Actions(driver).moveToElement(projectBar).build().perform();
	   //previewCustomerViewButton.click();  // 11/21 comment out
	   driver.findElement(By.className("ui-icon-viewChange-project")).click();  //11/21 added
   }
}
