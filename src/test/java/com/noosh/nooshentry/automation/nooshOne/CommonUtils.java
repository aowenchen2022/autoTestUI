package com.noosh.nooshentry.automation.nooshOne;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.BuyerSitePage;
import com.noosh.nooshentry.automation.buyerSite.Page;

import org.apache.commons.io.FileUtils;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class CommonUtils extends Page {	
	 public CommonUtils(WebDriver driver) {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 private WebElement username;
	 private WebElement password;  
	 private String loginName = "seleniumtest12345+263945@noosh.com";
	 private String passwd = "noosh123"; 
	 private WebElement submitbutton;
	 private WebElement glbDashboard;
	 private WebElement textArea;
	 private WebElement loginButton;
	 private WebElement tracking;
	 private WebElement leftnav_home_update;
	 private WebElement thisForm;
	 private WebElement projectStatusId;
	 private WebElement save;
	 private WebElement open_calendar;
	 private WebElement next_month;
	 private WebElement calendarContent;
	 private WebElement leftnav_spec_create;
	 private WebElement table;
	 private WebElement content;
	 private WebElement topMenuGear;
	 private WebElement projectProductContainer;
	 private WebElement createProjectTabFiles;
	 private WebElement topMenuProfile;
	 
	 @FindBy(id="settings-spec-automation-tab")
	 private WebElement rule;
	 
	 @FindBy(id="sp-adminSettings-container")
	 private WebElement settings;
	 
	 @FindBy(id="admin-main-page")
	 private WebElement adminSet;
	
	 @FindBy(id="siteListUl")
	 private WebElement ws;
	 
	 @FindBy(id="userName_pass_request")
	 private WebElement clientEmail;	   
	   
	 @FindBy(id="passwordOnce")
	 private WebElement newPwd;
	 
	 @FindBy(id="passwordTwice")
	 private WebElement pwdConfirm;
	 
	 @FindBy(id="bPassResetDiv")
	 private WebElement pwdSubmit;
	 
	 @FindBy(id="bPassRequestDiv")
	 private WebElement emailSubmit;
	 
	 @FindBy(id="topMenuUserLogout")
	 private WebElement clientLogout;
	 
	 @FindBy(id="admin-main-page")
	 private WebElement adminMain;
	 
	 @FindBy(id="createProjectCollaborateUser")
	 private WebElement clientInviteBtn;
	 
	 @FindBy(id="token-input-")
	 private WebElement clientUser;
	 
	 @FindBy(id="createProjectSearchUser")
	 private WebElement find;
	 
	 @FindBy(id="cpNextStep")
	 private WebElement projCreateBtn;
	 
	 @FindBy(id="createProjectCustomerList")
	 private WebElement clientList;
	 
	 //NE
	 @FindBy(id="MYDESK_NG_PROJECTS_body")
	 private WebElement negJobs;
	 
	 @FindBy(id="PM_HOME_DESKOID_STRUCTURE_body")
	 private WebElement projectStruc;
	 
	 @FindBy(id="continue")
	 private WebElement continueBtn;
	 
	 @FindBy(id="save_spec")
	 private WebElement specUpdateBtn;
	 
	 @FindBy(id="spec--1")
	 private WebElement specArea;
	 
	 @FindBy(id="leftnav_message_mainmenu")
	 private WebElement msgMenu;
	 
	 @FindBy(id="leftnav_message_post")
	 private WebElement postLk;
	 
	 @FindBy(id="subject")
	 private WebElement postSubj;
	 
	 @FindBy(id="post_message")
	 private WebElement postMsg;
	 
	 @FindBy(id="dummy")
	 private WebElement team;
	 
	 @FindBy(id="leftnav_file_mainmenu")
	 private WebElement fileMenu;
	 
	 @FindBy(id="leftnav_file_home")
	 private WebElement fileList;

	 @FindBy(id="projectFileForPro")
	 private WebElement updateShare;
	 
	 @FindBy(id="settings-spec-automation-tab")
	 private WebElement autoInvite;
	 
	 public void getScreenShot(String filename) throws IOException {
		 File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcFile, new File("C:\\jennifer\\autoscreenshot\\" + filename + ".png"));		 
	 }
	 
	 public void inputClientEmail(String email) {		 
		 clientEmail.clear();
		 clientEmail.sendKeys(email);
	 }
	 
	 public void submitClientEmailBtn() {
		 emailSubmit.findElement(By.className("bGlobal-button")).click();
	 }
	 
	 public void setNewSPPassword(String newPassword) throws InterruptedException {
		 Thread.sleep(1000);
		 newPwd.clear();
		 newPwd.sendKeys(newPassword);
		 Thread.sleep(1500);
		 pwdConfirm.clear();
		 pwdConfirm.sendKeys(newPassword);		   
	 }
	 
	 public void clickSubmitBtn() {
		 pwdSubmit.findElement(By.className("bGlobal-button")).click();
	 }
	 
	 public void clientlogin(String clientEmail5) {
		   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		   WebElement username = driver.findElement(By.id("username"));
		   if(username.isDisplayed()) {
		       username.clear();
		       username.sendKeys(clientEmail5);
		   } else {
			   System.out.println("Client username field is missing.\n");
		   }
		   WebElement passwd = driver.findElement(By.id("password"));
		   if(passwd.isDisplayed()){
		       passwd.clear();
		       passwd.sendKeys("17password");
		   } else {
			   System.out.println("Client password field is missing.\n");
		   }
		   WebElement loginBtn = driver.findElement(By.id("loginButton"));
		   loginBtn.click();
	 }
	 
	 public void clientloginWithourUname() throws InterruptedException {
		   Thread.sleep(1000);
		   password.sendKeys("17password");
		   Thread.sleep(1000);
		   loginButton.click();
	 }
	 
	 public void testClientOpenProject(String productName) throws InterruptedException {
		 boolean found = false;
		 List<WebElement> projectNames = driver.findElements(By.tagName("span"));
		 for(WebElement projectName : projectNames) {
		     if(projectName != null) {
				  String projectTitle = projectName.getText();
				  if(projectTitle.equalsIgnoreCase(productName)) {
					   projectName.click();
					   Thread.sleep(3000);
					   found = true;
					   break;
				  }						   
		     }
		 }
		 Assert.assertTrue(found, "The product " + productName + " is not found.");
	 }
	
	 public void testClientLogout() throws InterruptedException {
		 ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
		 Thread.sleep(2000);
	 }
	 
	 public void testClientLogout1() throws InterruptedException {
		   Actions builder = new Actions(driver);
		   topMenuProfile.click();
	       Thread.sleep(1500);
	       builder.moveToElement(topMenuProfile).build().perform();
	       Thread.sleep(2500);
	       driver.findElement(By.id("topMenuUserLogout")).click();
	       Thread.sleep(5000);
	 }
	 
	 public void goToSPHome() throws InterruptedException {
		 glbDashboard.click();
		 Thread.sleep(3000);
	 }
	 
	 public void goToClientView() throws InterruptedException {
		 WebElement workspace = ws.findElement(By.className("sites-site"));
		 WebElement ws = workspace.findElement(By.className("com-list-span-name"));
		 ws.click();
		 Thread.sleep(1000);
		 workspace.findElement(By.className("job-content")).findElement(By.className("site-preview")).findElement(By.className("site-preview-head")).click();
	 }
	 
	 public void goBackSPView() {
		 driver.findElement(By.className("site-preview-slider-head")).click();
	 }
	 
	 public void loginUser() throws InterruptedException {
		 if(username.isDisplayed()) {
		     Thread.sleep(3000);
	         username.clear();
	         username.sendKeys(loginName);
		 } else {
			 System.out.println("Username field is missing.\n");
		 }
		 if(password.isDisplayed()) {
	         password.clear();
	         password.sendKeys(passwd);
		 } else {
			 System.out.println("password field is missing. \n");
		 }
		 long startingTime = currentTime();
	     if(!submitbutton.isEnabled()) {
	         throw new IllegalStateException("Login button is missing!");
	     } else {
	         submitbutton.click();
	         long endingTime = currentTime();
	         long loginTime = loadingTime(startingTime, endingTime);
	         System.out.println("*** SP Home Page login loading time: " + loginTime + " seconds ***");
	     }
	     Thread.sleep(3000);
	 }
	 
	 public void loginUser1(String userName) throws InterruptedException {
		 if(username.isDisplayed()) {
		     Thread.sleep(3000);
	         username.clear();
	         username.sendKeys(userName);
		 } else {
			 System.out.println("Username field is missing.\n");
		 }
		 if(password.isDisplayed()) {
	         password.clear();
	         password.sendKeys(passwd);
		 } else {
			 System.out.println("password field is missing. \n");
		 }
		 long startingTime = currentTime();
	     if(!submitbutton.isDisplayed()) {
	         throw new IllegalStateException("Login button is missing!");
	     } else {
	         submitbutton.click();
	         long endingTime = currentTime();
	         long loginTime = loadingTime(startingTime, endingTime);
	         System.out.println("*** SP Home Page login loading time: " + loginTime + " seconds ***");
	     }
	     Thread.sleep(5000);
	 }
	 
	 public void loginUser1() throws InterruptedException {
		 Thread.sleep(3000);
	     password.clear();
	     password.sendKeys(passwd);
	     if(!submitbutton.isDisplayed()) {
	         throw new IllegalStateException("Login button is missing!");
	     } else {
	         submitbutton.click();
	     }
	     Thread.sleep(3000);
	 }
	 
	 public void logoutUser() throws InterruptedException {
	     Thread.sleep(3000);
		 ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
		 Thread.sleep(2000);
	 }
	 
	 public void Logout() throws InterruptedException {	 
/*		 if (driver.findElement(By.className("glb-nmh-profile")).isDisplayed() == false)
		 {
			 driver.findElement(By.xpath("myDeskPage.myDeskLink")).click();
			 Thread.sleep(3500);
		 }*/
		 WebElement topMenu = driver.findElement(By.className("glb-nmh-profile"));
		 topMenu.click();
		 Thread.sleep(1000);
		 Actions builder = new Actions(driver);
		 builder.moveToElement(topMenu).perform();
		 Thread.sleep(1000);
		 List<WebElement> links = driver.findElement(By.className("glb-nmh-settings-drop")).findElements(By.tagName("li"));
		 builder.moveToElement(links.get(7)).click().perform();
		 Thread.sleep(1000);

		 CommonUtils commonUtils = new CommonUtils(driver);
		 commonUtils.dealPotentialAlert(true);
		 Thread.sleep(3500);
	 }
	 
	 public void logoutGmailAccount() throws InterruptedException {
		 Thread.sleep(2000);
	   	 driver.get("https://mail.google.com/mail/?logout"); 
	   	 Thread.sleep(2000);
	 }
	 
	 public void testGmailLoginPreCon() throws InterruptedException {
	 	  WebElement gmailSignIn = null;
	 	  try {
	 	      gmailSignIn = driver.findElement(By.linkText("SIGN IN"));    
	 	  } catch (Exception e) {
	 		  // do nothing
	 	  }
		  if (gmailSignIn != null) {
			 gmailSignIn.click();
			 Thread.sleep(500);
		  } 
		 // add 10/25 
		  WebElement accountChooserLink = null;
		  try {
			  accountChooserLink = driver.findElement(By.id("account-chooser-link"));
		  } catch (Exception e) {
			  // do nothing
		  }
		  if (accountChooserLink != null) {
			  accountChooserLink.click();
			  Thread.sleep(500);
		  }
		  
		  WebElement addAccountLink = null;
		  try {
			  addAccountLink = driver.findElement(By.id("account-chooser-add-account"));
		  } catch (Exception e) {
			  // do nothing
		  }
		  if (addAccountLink != null) {
			  addAccountLink.click();
			  Thread.sleep(500);
		  }	 
     }
	
	 public void testInviteCustomers() {
		 driver.findElement(By.className("a-enable-selling")).click();
	 }
	 
	 public void testEnableClientBtn() {
		 driver.findElement(By.className("ui-dialog-buttonpane")).findElement(By.className("confirm-upgrade")).click();
	 }
	 
	 public void testExistingSPAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
	  	  BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	  	  AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver);	  	   
	  	  addSmartFormPage.clickAddSmart();    
	  	  Thread.sleep(1000);	  	  
	  	  addSmartFormPage.clickSmartForm();
	  	  Thread.sleep(1000);
	  	  brochurePopup.getReviewAndSubmitTab();
	  	  Thread.sleep(3000);
	  	  addSmartFormPage.clickAddSmart();
	  	  Alert alert = driver.switchTo().alert();
	  	  alert.accept();
	  	  Thread.sleep(6000);  	   
	  	  addSmartFormPage.clickSmartForm();
	  	  addSmartFormPage.inputOneQuantity(quantSP);  	  
	  	  Thread.sleep(2000);
	  	  brochurePopup.getReviewAndSubmitTab();
	  	  Thread.sleep(3000);
	 }
	 
	 public void testOpenProjectInWS(String productName) throws InterruptedException {
		 boolean found = false;
		 WebElement allProject = driver.findElement(By.id("homeSiteListView"));
		 List<WebElement> projectNames = driver.findElements(By.className("line-project-name-cot"));
		 for(WebElement projectName : projectNames) {
		     if(projectName != null) {
				  String projectTitle = projectName.getText();
				  if(projectTitle.equalsIgnoreCase(productName)) {
					   projectName.click();
					   Thread.sleep(3000);
					   found = true;
					   break;
				  }						   
		     }
		 }
		 Assert.assertTrue(found, "The product " + productName + " is not found.");
	 }
	 
	 public void testOpenProjectTabInWS(String productName, String tabName) throws InterruptedException {
		 System.out.println("product name = " + productName);
		 System.out.println("tab name = " + tabName);
		 boolean found = false;
		 WebElement allProject = driver.findElement(By.id("homeSiteListView"));
		 List<WebElement> bodies = driver.findElements(By.className("home-site-line-body"));
		 WebElement pBody = bodies.get(bodies.size()-1);
		 List<WebElement> pContainers = pBody.findElements(By.className("project-line-container"));
		 for (WebElement container: pContainers) {
			 WebElement projectLine = null;
			 try {
			     projectLine = container.findElement(By.className("project-line"));
			 } catch (NoSuchElementException e) {
				 continue;
			 }
			 WebElement lineProjectName = projectLine.findElement(By.className("line-project-name"));
		     if (lineProjectName != null) {
		    	  WebElement spanName = lineProjectName.findElement(By.tagName("span"));
				  String projectTitle = spanName.getText();
				  System.out.println("project title = " + projectTitle);
				  if(projectTitle.equalsIgnoreCase(productName)) {
					   spanName.click();
					   Thread.sleep(3000);
					   WebElement projectDetailContainer = container.findElement(By.className("project-detail-container"));
					   projectDetailContainer.findElement(By.className("ui-tabs-nav")).findElement(By.linkText(tabName)).click();
					   Thread.sleep(3000);
					   found = true;
					   break;
				  }						   
		     }
		 }
		 Assert.assertTrue(found, "The tab " + tabName + " is not found.");
	 }
	 
	 public void testOpenProjectTabInWS1(String productName, String tabName) throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Files")).click(); 
	 }
	 
	 public void testOpenProjectTabInWS2(String productName, String tabName) throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Team")).click(); 
	 }
	 
	 public void testOpenProjectSFTabInWS(String productName, String tabName) throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Specs")).click();
		 Thread.sleep(3500);
	 }
	 
	 public void testOpenProjectSFTabInWS() throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Selling")).click();
		 Thread.sleep(3500);
	 }
	 
	 public void testOpenProjectActTabInWS() throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Activities")).click();
		 Thread.sleep(3500);
	 }
	 
	 public void testOpenProjectSFTabInWS3(String productName, String tabName) throws InterruptedException {
		 WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
		 editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Messages")).click(); 
	 }	 
	 
	 public void testSPcreateProject(String projNameSP, String descriptionNameSP,
	               String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
	               String fileForProject1) throws InterruptedException, AWTException {
         BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
         Thread.sleep(2000);   	    
         brochurePopup.callCalendar();
         brochurePopup.setNextMonth();
         Thread.sleep(1000);
         brochurePopup.setComplationDate(); 
         Thread.sleep(1000);

         Thread.sleep(2000);
     }
	 
	 public void testChooseCalendar() throws InterruptedException, AWTException {
		 BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		 Thread.sleep(2000);   	    
		 brochurePopup.callCalendar();
		 Thread.sleep(2000); 
		 brochurePopup.setNextMonth();
		 Thread.sleep(3000); 
		 brochurePopup.setComplationDate(); 

}	 
	 
	 public void testSPRequestEstimate(String projectName) throws InterruptedException {
		 BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	     WebElement allProject = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-body")).findElement(By.className("home-project-list-ul"));   //5/21 comment out 	    
	     allProject.findElement(By.tagName("a")).click();
		 Thread.sleep(1000);
	     List<WebElement> projects = driver.findElements(By.tagName("a"));
	     for (WebElement project : projects) {
			 if (project != null) {
		 		 String projectEst = project.getAttribute("title");
		 		 if ((projectEst != null) && !projectEst.isEmpty()) {
		 		  	 if (projectEst.equals("Request Estimates")) {
		 		  		 project.click();
		 				 Thread.sleep(2500);
		 				 break;
		 		   	 }
		     	 }
		     }
		 }
	 }
	 
	 public void testNewSPRequestEstimate(String projectName) throws InterruptedException {
		 BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	     WebElement allProject = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-body")).findElement(By.className("home-project-list-ul"));  	     
	     Thread.sleep(1000);
	     List<WebElement> projects = allProject.findElements(By.tagName("a"));
	     Thread.sleep(1000);
	     for (WebElement project : projects) {
			 if (project != null) {
		 		 String projectEst = project.getAttribute("title");
		 		 if ((projectEst != null) && !projectEst.isEmpty()) {
		 		  	 if (projectEst.equals("Request Estimates")) {
		 		  		 System.out.println("title = " + projectEst);
		 		  		 System.out.println("project name = " + project);
		 		  		 project.click();
		 				 Thread.sleep(3500);
		 				 break;
		 		   	 }
		     	 }
		     }
		 }
	 }
	 
	 public void testSPInputMsg(String msg) {
		   textArea = driver.findElement(By.className("proj-view-message-fieldset")).findElement(By.tagName("textarea"));
		   String clientMsg = textArea.getAttribute("name");
		   if(clientMsg != null && clientMsg.equalsIgnoreCase("content")) {
			   textArea.clear();
			   textArea.sendKeys(msg);
		   }
	  }
	 
	 public void testGoToSPAdminBrandingPage() {
		 Actions actions = new Actions(driver);
		 WebElement admin = driver.findElement(By.id("admin-main-page"));
		 List<WebElement> adminTabs = admin.findElements(By.className("ui-state-default"));
		 WebElement branding = adminTabs.get(4);
		 actions.moveToElement(branding).build().perform();
		 branding.findElement(By.tagName("a")).click();
	 }
	 
	 public void testInviteClientBtn() {
		 if(clientInviteBtn != null) {
		     clientInviteBtn.click();
		 } else {
			 System.out.println("Client invite button is missing.\n");
		 }
	 }
	 
	 public void testInputClientUser(String client) throws InterruptedException {
		 clientUser.clear();
		 clientUser.sendKeys(client);
		 Thread.sleep(1000);
		 clientUser.sendKeys(",");
	 }
	 
	 public void testFindBtn() {
		 if(find != null) {
		     find.findElement(By.className("create-project-search-button")).click();
		 } else {
			 System.out.println("Client find button is missing.\n");
		 }
	 }
	 
	 public void testCreateProjBtn() {
		 if(projCreateBtn != null) {
			 projCreateBtn.click();
		 } else {
			 System.out.println("'Create Project' button is missing.\n");
		 }
	 }
	 
	 public void testSelectProjInNooshEnterprise(String projectName) {
		 tracking.findElement(By.partialLinkText(projectName)).click();		 
	 }
	 
	 public void testUpdateProjInNooshEnterprise() {
		 leftnav_home_update.click();
	 }
	 
	 public void testUpdateProjNameInNooshEnterprise(String projName) {
		 WebElement projNameField = thisForm.findElement(By.id("name"));
		 projNameField.clear();
		 projNameField.sendKeys(projName + " edit");
	 }
	 
	 public void testUpdateProjStatusInNooshEnterprise() {
		 projectStatusId.sendKeys("New Job Received");
	 }
	 
	 public void testUpdatedProjSaveInNooshEnterprise() {
		 save.click();
	 }
	 
	 public void testSharingOption(String spSiteLogin) {
		 WebElement editProj = driver.findElement(By.className("project-detail-container")).findElement(By.className("sharing-team-list"));
		 List<WebElement> sharingTeams = editProj.findElements(By.className("sharing-team-list-team"));
		 if(spSiteLogin.contains("sdm") || spSiteLogin.contains("sqa") || spSiteLogin.contains("scd"))  
			 sharingTeams.get(1).click();
	     else { 
	    	 for(int i=0; i<=1; i++) {
	             sharingTeams.get(i).click(); 
	    	 }
	     }
	 }
	 
	 public long currentTime() {
	     return System.currentTimeMillis();	 
	 }
	 
	 public long loadingTime(long begin, long ending) {
		 return (ending - begin)/1000;
	 }
	 
	 public void testSelectClient(String clientName) {
		 clientList.findElement(By.name("customerRadio")).click();
	 }
	 
	 public void testSetNECompletionDate() throws InterruptedException {
		 open_calendar.click();
		 next_month.click();
		 Thread.sleep(2000);
		 calendarContent.findElement(By.linkText("10")).click();
	 }
	 
	 public void testOpenExpandWS() {
		 ws.findElement(By.className("sites-site")).findElement(By.className("com-list-span-name")).click();
	 }
	 
	 public void testOpenProjInNEGJobs(String projName) {
		 negJobs.findElement(By.className("big")).findElement(By.partialLinkText(projName)).click();  
	 }
	 
	 public void testSelectSFinNE() {
		 List<WebElement> links = projectStruc.findElements(By.className("majorlabel"));
		 links.get(1).findElement(By.tagName("a")).click();
	 }
		 
	 public void testUpdateSFinNE(String newSFName) throws InterruptedException {
		 driver.switchTo().frame(1);
		 WebElement container = driver.findElement(By.id("specTemplateContainer"));
		 WebElement btn = container.findElement(By.className("inline-edit-buttons"));
		 btn.findElement(By.className("edit-btn")).click();
		 Thread.sleep(5000);
	 }
	 
	 public void testCreateSFinNE(String newSFName) throws InterruptedException {
		 driver.switchTo().frame(1);
		 WebElement btn = driver.findElement(By.className("inline-edit-buttons"));
		 
		 WebElement container = driver.findElement(By.className("main-container"));		 
		 WebElement sfname = container.findElement(By.className("pe-dd-cellCard-valueEdit"));
		 sfname.clear();
		 sfname.sendKeys(newSFName);
		 Thread.sleep(2000);
		 
		 btn.findElement(By.className("save-btn")).click();
		 Thread.sleep(5000);
	 }
	 
	 public void testUpdateSFinNE2(String newSFName) {
		 driver.switchTo().frame(1);
		 List<WebElement> sfName = projectProductContainer.findElement(By.className("job-jobSpecs-div-ul")).findElements(By.className("pe-dd-cellCard-body"));
		 WebElement input = sfName.get(1).findElement(By.tagName("input"));
		 input.clear();
		 input.sendKeys(newSFName);
	 }
	
	 public void testCancelBtn() {
		 driver.switchTo().frame("");
		 driver.findElement(By.className("inline-edit-product-cancel")).click();
	 }
	 
	 public void testSaveSF() {
		 driver.switchTo().frame("");
		 driver.findElement(By.className("inline-edit-product-save")).click();
	 }
	 
	 public void testSelectCreateSpec() {
		 leftnav_spec_create.click();
	 }
	 
	 public void testSelectaSF() {
		 List<WebElement> inputs = table.findElements(By.tagName("input"));
		 inputs.get(0).click();
	 }
	 
	 public void testContinueSFBtn() {
		 continueBtn.click();
	 }
	 
	 public void testUpdateSpecBtn() {
		 projectProductContainer.findElement(By.className("inline-edit-product-save")).click();
	 }
	 
	 public void testUpdateSpec(String specNameInput) throws InterruptedException {
		 driver.switchTo().frame(1);
		 Thread.sleep(2000);
		 projectProductContainer.findElement(By.className("job-jobSpecs-div-ul")).findElement(By.className("inline-edit-product-edit")).click();
	     Thread.sleep(2000);
	     WebElement field = projectProductContainer.findElement(By.className("com-inlineEditor-value")).findElement(By.tagName("input"));
	     field.clear();
	     field.sendKeys(specNameInput);
	 }
	 
	 public void testPostMSGMenu() throws InterruptedException {
		 msgMenu.click();
		 Thread.sleep(1000);
	 }
	 
	 public void testPostMSG() {		 
		 postLk.click();
	 }
	 
	 public void testPostSubject(String postSubject) {
		 postSubj.clear();
		 postSubj.sendKeys(postSubject);
	 }
	 
	 public void testPostContent(String postContent) {
		 content.clear();
		 content.sendKeys(postContent);
	 }
	 
	 public void testPostMsgbtn() {
		 postMsg.click();
	 }
	 
	 public void testCheckTeam() {
		 team.click();
	 }
	 
	 public void testFileMenu() throws InterruptedException {
		 fileMenu.click();
		 Thread.sleep(1000);
	 }
	 
	 public void testFile() {
		 fileList.click();
	 }
	 
	 public void testUpdateShare() {
		 updateShare.findElement(By.className("file-share-btn"));
	 }
	 
	 public void testUpdateSharePre() {		
		 updateShare.findElement(By.className("upload")).click();
	 }
	 
	 public void testUploadedImagExisting(String uploadedFile) {
		 int index = uploadedFile.lastIndexOf("/");
		 String imagTitle = uploadedFile.substring(index + 1);
		 String imgName = driver.findElement(By.className("uploaded-file-name")).getAttribute("title");
		 Assert.assertEquals(imgName, imagTitle);
	 }
	 
	 public void clickAdmin() throws InterruptedException {
		 Actions builder = new Actions(driver);
		 topMenuGear.click();
		 Thread.sleep(1000);
		 builder.moveToElement(topMenuGear).perform();
		 Thread.sleep(3000);
		 driver.findElement(By.id("topMenuSettingsMenuItemtopMenuAdmin")).click();
		 Thread.sleep(5000);
	 }
	 
	 public void clickSF() throws InterruptedException {
		 Actions builder = new Actions(driver);
		 topMenuGear.click();
		 Thread.sleep(1000);
		 builder.moveToElement(topMenuGear).perform();
		 Thread.sleep(3000);
		 driver.findElement(By.id("topMenuSettingsMenuItemtopMenuSmartForms")).click();
		 Thread.sleep(5000);
	 }
	 
	 public void verifyUploadedImg(String fileName) {
		 int index = fileName.lastIndexOf("/");
		 String imgTitle = fileName.substring(index + 1);
		 int index2 = imgTitle.indexOf(".");
		 imgTitle = imgTitle.substring(0, index2); 	   
		 String imgName = driver.findElement(By.className("editable-field")).findElement(By.tagName("pre")).getAttribute("title");
		 Assert.assertEquals(imgName, imgTitle);
	 }
	 
	 public void verifyCopyUploadedFileText(String text) {
		 List<WebElement> spans = createProjectTabFiles.findElements(By.tagName("span"));
		 String fileText = spans.get(1).getText();
		 System.out.println("text = " + text);
		 System.out.println("fileText = " + fileText);
		 Assert.assertEquals(fileText, text);
	 }
	 
	 public void verifyClientQStatus() throws InterruptedException {
		 driver.findElement(By.className("show-detail")).click();
		 String status = driver.findElement(By.className("quote-list")).findElement(By.className("ellipsis")).getText();
		 Thread.sleep(1000);
		 Assert.assertEquals(status, "Retracted");
	 }
	 
	 public void verifyClientCancelOrderStatus(String orderStatus, String quoteStatus) throws InterruptedException {
		 driver.findElement(By.className("show-detail")).click();
		 Thread.sleep(1000);
		 //String cancelStatus = driver.findElement(By.className("order-status")).getText();
		 //Assert.assertEquals(cancelStatus, orderStatus);
		 Thread.sleep(1000);
		 String status = driver.findElement(By.className("quote-list")).findElement(By.className("ellipsis")).getText();
		 Assert.assertEquals(status, quoteStatus);
	 }
	 
	 public void showBuyingDetail() {
		 driver.findElement(By.className("show-detail")).click();
	 }
	 
	 public void showSellingDetail() {
		 driver.findElement(By.className("toggle-detail")).click();
	 }
	 
	 public void clickCreateOrder() throws InterruptedException {
		 List<WebElement> projects = driver.findElements(By.tagName("a"));
		 for(WebElement project : projects) {
			 if(project != null) {
				 String co = project.getAttribute("title");
				 if((co != null) && !co.isEmpty()) {
					 if(co.equals("Create Order")) {
						 project.click();
						 Thread.sleep(3500);
						 break;
					 }
				 }
			 }
		 }
	 }
	 
	 public void createOrder() throws InterruptedException {
		 WebElement price = driver.findElement(By.className("form-price")).findElement(By.tagName("input"));
		 price.clear();
		 price.sendKeys("300");
		 Thread.sleep(1000);
		 driver.findElement(By.className("submitSendQuickOrder")).click();
		 Thread.sleep(5000);
	 }
	 
	 public void reviewEst() {
		 WebElement revEst = driver.findElement(By.linkText("Review Estimates"));
		 if(!revEst.isDisplayed()) {
			 throw new IllegalStateException("The accept invitation link is missing!");
		 } else {
			 revEst.click();
		 } 	   
	 }
	 
	 public void verifyNEHome() throws InterruptedException {
		 WebElement logo = driver.findElement(By.id("logobar"));
		 Assert.assertEquals(true, logo);	 
		 Thread.sleep(2000);
	 }
	 
	 public void verifySPQStatus(String s) throws InterruptedException {
		 String status = driver.findElement(By.className("quote-list")).findElement(By.className("ellipsis")).getText();
		 Assert.assertEquals(status, s);
		 Thread.sleep(1500);
	 }
	 
	 public void delRule() throws InterruptedException {
		 Thread.sleep(5000);
		 WebElement del = driver.findElement(By.className("ui-icon-bigtrash"));
	     if(del.isDisplayed()) {
	    	 del.click();
	     } else {
	    	 Assert.fail();
	     }
		 Thread.sleep(2500);
	 }
	 
	 public void exePopup() throws InterruptedException {
		 Alert alt = driver.switchTo().alert();
		 alt.accept();
		 Thread.sleep(1500);
	 }
	 
	 public void reviewOrder() throws InterruptedException {
		 Thread.sleep(2000);
		 List<WebElement> emails = driver.findElements(By.tagName("span"));
		 for (WebElement email : emails) {
		     if (email != null) {
			     String emailTitle = email.getText();
				 if (emailTitle.indexOf("has canceled the order") >= 0) {
				     email.click();
					 break;
				 }
			 }
		 }
		 Thread.sleep(2000);
		 driver.findElement(By.linkText("Review Order")).click(); 
		 Thread.sleep(2000);
	 }
	 
	 public void setAcct() throws InterruptedException {
		 Actions act = new Actions(driver);
		 List<WebElement> tabs = adminSet.findElements(By.className("ui-state-default"));
		 WebElement acct = tabs.get(1);
		 act.moveToElement(acct).build().perform();
		 acct.findElement(By.tagName("a")).click();
		 Thread.sleep(2000);
	 }
	 
	 public void goToAuto() throws InterruptedException {
		 Actions act = new Actions(driver);
		 List<WebElement> tops = settings.findElements(By.className("ui-corner-top"));
		 WebElement auto = tops.get(3);
		 act.moveToElement(auto).build().perform();
		 auto.findElement(By.tagName("a")).click();
		 Thread.sleep(2000);
	 }
	 
	 public void addRule() throws InterruptedException {
		 WebElement rulebtn = rule.findElement(By.className("sp-simple-button"));
		 if(rule.isDisplayed()) {
			 rulebtn.click();
		 } else {
			 Assert.fail();
		 }
		 Thread.sleep(2000);
	 }
	 
	    
	    //#############################Check Function##############################################################
	    public static boolean checkElement(WebElement e) throws Exception {
	        boolean result = false;
	        if(e == null){
	        	result = false;
	        } else{
	        	result = true;
	        }
	        return result;    	
	    }
	    
	    public static void checkIfElementIsDisplayed(WebElement e, String elementName) {        
	    	if (e == null){    		
	        	Assert.fail(elementName + " does not exist!");        	
	    	} else {
				try {
					Assert.assertTrue(checkElement(e), "elementName" + " is display!");
				} catch (Exception e1) {
					//String picName = elementName+DatetimeUtils.getDataTimeString();
					//SeleniumUtils.captureScreenshot(picName);
					//Assert.fail(elementName + " is not displayed! Please find the snapshot" +picName);
					e1.printStackTrace();
				}
	    	}	
	    }
	    

	    
	    public boolean dealPotentialAlert(boolean option) {
	        boolean flag = false;
	        try {
	            Alert alert = driver.switchTo().alert();
	            if (null == alert)
	                throw new NoAlertPresentException();
	            try {
	                if (option) {
	                    alert.accept();
	                    System.out.println("Accept the alert: " + alert.getText());
	                } else {
	                    alert.dismiss();
	                    System.out.println("Dismiss the alert: " + alert.getText());
	                }
	                flag = true;
	            } catch (WebDriverException ex) {
	                if (ex.getMessage().startsWith("Could not find"))
	                    System.out.println("There is no alert appear!");
	                else
	                    throw ex;
	            }
	        } catch (NoAlertPresentException e) {
	            System.out.println("There is no alert appear!");
	        }
	        return flag;
	    }
}
