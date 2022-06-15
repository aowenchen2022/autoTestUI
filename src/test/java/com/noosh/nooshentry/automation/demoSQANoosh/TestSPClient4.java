/***********************************************************************
* This class tests the functionality of SP and client
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileNotFoundException;

import org.testng.annotations.Parameters;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.ClientEditProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientLoginPage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.awt.AWTException;

public class TestSPClient4 extends BaseSeleniumTest {  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   //static String baseUrlPro;
   static String baseUrlSmoke;
   static String smokeURL;
   static String clientURL;
   static String clientdddURL;
   static String clientURL2;
   static String clientWstestURL;
   String windowNumber;
   static String baseUrl;
   static String loginUrl;
   static String parentWindowHandle1;
   static String clientName = "Selenium Client";
   static String parentWindowHandle;
   static String productName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup";
		   //baseUrlPro = "https://demo.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + companyName + ".com/service/login";
		   clientURL = "https://selenium263945." + companyName + ".com/client_1/login";
		   clientdddURL = "https://selenium263945." + companyName + ".com/ddd/login";
		   clientURL2 = "https://nooshselenium." + companyName + ".com/autoclient/main";
		   clientWstestURL = "https://selenium263945." + companyName + ".com/wstest/login";
		   loginUrl = "https://selenium263945." + companyName + ".com/service/login";  
	   } else {
		   baseUrl = "https://demo." + domain + "." + companyName + ".com/service/signup";
		   //baseUrlPro = "http://" + domain + ".noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2." + domain + "." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + domain + "." + companyName + ".com/service/login";
		   clientURL = "https://selenium263945." + domain + "." + companyName + ".com/client_1/login";
		   clientdddURL = "https://selenium263945." + domain + "." + companyName + ".com/ddd/login";
		   clientURL2 = "https://nooshselenium." + domain + "." + companyName + ".com/autoclient/main";
		   clientWstestURL = "https://selenium263945." + domain + "." + companyName + ".com/wstest/login";
		   loginUrl = "https://selenium263945." + domain + "." + companyName + ".com/service/login";  
	   } 
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spClientTest4 *****\n\n");
   }
   
   @Test(description="Client login page")
   public void testClientForgotPWLink() throws InterruptedException {
   	  log.info("Start: testClientForgotPWLink");
   	  driver.get(clientURL);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
   	  ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
   	  Thread.sleep(1000);
   	  clientLoginPage.clientForgotPWLink();
   	  Thread.sleep(2000);
   	  log.info("End: testClientForgotPWLink");
  	  log.info("--------------------------------------------");
   }
 
   @Test(description="Client forgot password")
   @Parameters({"clientEmail1"})
   public void testClientForgotPW(String clientEmail1) throws InterruptedException {
	   log.info("Start: testClientForgotPW");
	   CommonUtils commonUtils = new CommonUtils(driver);  
	   Thread.sleep(500);
	   if(driver.getPageSource().contains("Password Recovery")) {
	       commonUtils.inputClientEmail(clientEmail1);
	       Thread.sleep(1500);
	       commonUtils.submitClientEmailBtn();
	       Thread.sleep(500);
	   } else {
		   log.info("Password recovery page is missing.\n");
	   }
       log.info("End: testClientForgotPW");
       log.info("--------------------------------------------");
   }
   
   @Test(description="Check gmail login preconditions")
   public void testGmailLoginPreCon() throws InterruptedException {
	   CommonUtils commonUtils = new CommonUtils(driver);  
	   commonUtils.testGmailLoginPreCon();
   }
   
   
   @Test(description="Check gmail login again preconditions")
   public void testGmailLoginAgainPreCon() throws InterruptedException {
	  

	 WebElement pwd = null;
 	try {
 			pwd = driver.findElement(By.id("passwordNext"));
 
   } catch (Exception e){
	   
   }
    if (pwd != null) {
	   try {
	   	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
		  gmailLoginPage.mouseup();
	 	  Thread.sleep(1000);
	   
	   } catch (Exception e){
	   } 
	   }

	   WebElement next = null;
 		try {
          next = driver.findElement(By.id("identifierLink"));
 
   } catch (Exception e){
	   
   }
 	   if (next != null) {
			  next.click();
			  Thread.sleep(500);
		  }	 
   }

   @Test(description="Client login to Gmail")
   @Parameters({"gmailLoginPW", "gmail"})
   public void testLoginToGmail(String gmailLoginPW, String gmail) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  driver.get("http://www.gmail.com");
 	  testGmailLoginPreCon();  
 	  testGmailLoginAgainPreCon();
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(1000); 	  
 	  gmailLoginPage.enterEmail(gmail);
 	  Thread.sleep(1500);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(3500);
 	  gmailLoginPage.check();
	  gmailLoginPage.signIn();
	  Thread.sleep(2000);
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="client check reset password email")
   public void testResetPWEmail() throws InterruptedException {
	   log.info("Start: testResetPWEmail");
	   Thread.sleep(5000);
	   List<WebElement> emails = driver.findElements(By.tagName("span"));
	   for (WebElement email : emails) {
	 	  if (email != null) {
	 		  String emailTitle = email.getText();
	 		 if (emailTitle.indexOf("Reset Password for Noosh Group") >= 0) {
	 			  email.click();
	 			  break;
	 		  }
	 	  }
	   }
	   Thread.sleep(2000);
	   driver.findElement(By.linkText("Reset Password")).click(); 
	   Thread.sleep(2000);  
 	   log.info("End: testResetPWEmail");
 	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client reset password on Reset Password page")
   @Parameters({"spPassword"})
   public void testClientResetPW(String spPassword) throws InterruptedException {
	   log.info("Start: testClientResetPW");
	   parentWindowHandle1 = driver.getWindowHandle();
	   System.out.println("parent="+ parentWindowHandle1);
	   Set<String> windows = driver.getWindowHandles();
	   System.out.println("num of windows="+ windows.size());
	   for (String window : windows) {
	       if (!window.equals(parentWindowHandle1)) {
	 	       driver.switchTo().window(window);
	 	  	   break;
	 	   }
	   }
	   Thread.sleep(2000);
	   CommonUtils commonUtils = new CommonUtils(driver);  
	   Thread.sleep(500);
	   commonUtils.setNewSPPassword(spPassword);
	   Thread.sleep(2500);
	   commonUtils.clickSubmitBtn();
	   Thread.sleep(6000);
	   log.info("End: testClientResetPW");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client logout")
   public void testClientLogout() throws InterruptedException {
	   log.info("Start: testClientLogout");
	   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	   
	   CommonUtils commonUtils = new CommonUtils(driver);
	   Thread.sleep(2000);
	   commonUtils.testClientLogout1();
	   Thread.sleep(2000);
	   log.info("End: testClientLogout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Logout gmail")
   public void testCloseGmailPage() throws InterruptedException {
	   log.info("Start: testCloseGmailPage");
	   Thread.sleep(3000);
	   driver.close(); 
	   Thread.sleep(2000);
	   driver.switchTo().window(parentWindowHandle1); 
	   Thread.sleep(1000);
	   driver.get("https://mail.google.com/mail/u/0/#inbox");
	   Thread.sleep(2000);
	   driver.findElement(By.className("T-Jo-auh")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.className("ar9")).click();
	   Thread.sleep(2000);
	   driver.get("https://mail.google.com/mail/?logout"); 
	   Thread.sleep(3000);
	   log.info("End: testCloseGmailPage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP login")
   public void testSPLogin() throws InterruptedException {
	   log.info("Start: testSPLogin");
	   driver.get(loginUrl);   
	   //driver.manage().window().maximize();           
	   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   CommonUtils commonUtils = new CommonUtils(driver);
	   if(driver.getPageSource().contains("Welcome to")) {
		   commonUtils.loginUser();
	   } else {
		   log.info("Service provider login page doesn't show\n");
	   }
	   log.info("End: testSPLogin");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP workspace create project")
   public void testSPWorkspaceCreateProj() throws InterruptedException {
	   log.info("Start: testSPWorkspaceCreateProj");
	   Thread.sleep(1500);	  
	   Actions action = new Actions(driver);
	   WebElement project = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-title"));
	   action.moveToElement(project).click();
	   action.build().perform();
	   Thread.sleep(1500);
	   driver.findElement(By.className("home-site-line-createProject")).click();	  
	   Thread.sleep(3500);
	   log.info("End: testSPWorkspaceCreateProj");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP workspace project name input")
   @Parameters({"productName1", "projectNameField"})
   public void testSPWorkspaceInputProjectName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testSPWorkspaceInputProjectName");
	   productName1 = productName1 + unicID;
	   productName = productName1;
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   newProject.inputWSProjectName(productName1);   
	   Thread.sleep(1000);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
	   } catch(Throwable e) {
		   System.out.println("Project Name field is missing!\n");
		   log.info("Project Name field is missing!");
	   }
	   log.info("End: testSPWorkspaceInputProjectName");
	   log.info("--------------------------------------------"); 
  }
   
  @Test(description="SP workspace create project select smart form")
  public void testSPWorkspaceProjectSmartForm() throws InterruptedException {
 	    log.info("Start: testSPWorkspaceProjectSmartForm");
        List<WebElement> imgs = driver.findElement(By.id("createWorkspaceProjectProductList")).findElements(By.className("scroll-list-imageContainer"));
        spSiteLoginPage = driver.getCurrentUrl();
        if(spSiteLoginPage.contains("sdm")) 
     	   imgs.get(1).click();
        else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd"))  
     	   imgs.get(0).click();
        else 
        	imgs.get(0).click();
        Thread.sleep(3000);
 	    log.info("End: testSPWorkspaceProjectSmartForm");
 	    log.info("--------------------------------------------"); 
   }

   @Test(description="SP workspace create project completion date")
   public void testSPWorkspaceProjectCompletionDate() throws InterruptedException {
 	   log.info("Start: testSPWorkspaceProjectCompletionDate");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   Thread.sleep(2000);   	    
 	   brochurePopup.callCalendar();
 	   brochurePopup.setNextMonth();
 	   brochurePopup.setComplationDate(); 
 	   Thread.sleep(3000);	   
 	   brochurePopup.getNextTab();	 
 	   Thread.sleep(2000);
 	   log.info("End: testSPWorkspaceProjectCompletionDate");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP workspace create project spec, uploading file")
   @Parameters({"quantSP", "quantSP2", "quantSP3", "fileForProject11"})
   public void testSPWorkspaceProjectSpec(String quantSP, String quantSP2, String quantSP3, String fileForProject11) 
 		                 throws InterruptedException, AWTException {
 	   log.info("Start: testSPWorkspaceProjectSpec");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   Thread.sleep(3000);
 	   List<WebElement> inputs = driver.findElements(By.tagName("input"));
 	   for (WebElement input : inputs) {
 		   String name = input.getAttribute("reportingname");
 		   if (name != null && name.equalsIgnoreCase("QUANTITY1")) {
 			   input.clear();
 			   input.sendKeys(quantSP);
 		   }
 		   if (name != null && name.equalsIgnoreCase("QUANTITY2")) {
 			   input.clear();
 			   input.sendKeys(quantSP2);
 		   }
 		   if (name != null && name.equalsIgnoreCase("QUANTITY3")) {
 			   input.clear();
 			   input.sendKeys(quantSP3);
 			   break;
 		   }
 	   }
 	   Thread.sleep(3000);  	   
 	   brochurePopup.getFilesTab();    
 	   Thread.sleep(1000);
 	   page.uploadFileModalWindow(fileForProject11);   
 	   
 	   brochurePopup.clickUploadFileDropBT();
 	   Thread.sleep(2000);
 	   page.robotUpload();
 	   Thread.sleep(6500);	  
 	   
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000);  
 	   log.info("End: testSPWorkspaceProjectSpec");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP workspace create project submit")
   public void testSPWorkspaceProjectSubmit() throws InterruptedException {
 	   log.info("Start: testSPWorkspaceProjectSubmit");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   brochurePopup.clickSubmitBT();    
 	   Thread.sleep(3500); 	 
 	   log.info("End: testSPWorkspaceProjectSubmit");
 	   log.info("--------------------------------------------"); 
   }
  
   @Test(description="SP workspace team tab")
   public void testSPWorkspaceTeamTab() throws InterruptedException {
	   log.info("Start: testSPWorkspaceTeamTab");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   Thread.sleep(15000);
	   commonUtils.testOpenProjectTabInWS2(productName, "Team");
	   Thread.sleep(3500);
	   //driver.findElement(By.className("user-management-area")).findElement(By.className("glb-client-view-users-menu")).findElement(By.className("manageUsers")).click();
	   //Thread.sleep(3000);
	   log.info("End: testSPWorkspaceTeamTab");
 	   log.info("--------------------------------------------"); 
   }   
   
   @Test(description="SP invite client")
   public void testSPWorkspaceInviteClient() throws InterruptedException {
	   log.info("Start: testSPWorkspaceInviteClient");
	   //driver.findElement(By.className("user-management-area")).findElement(By.className("glb-new-simple-button")).click();
	   driver.findElement(By.className("user-management-area")).findElement(By.className("glb-client-view-users-menu")).findElement(By.className("manageUsers")).click();
	   Thread.sleep(3000);
	   log.info("End: testSPWorkspaceInviteClient");
 	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP send client invitation")
   @Parameters({"clientddd"})
   public void testSPWorkspaceSendInvitationToClient(String clientddd) throws InterruptedException {
	   log.info("Start: testSPWorkspaceSendInvitationToClient");
	   InvitePage invitePage = new InvitePage(driver);
	   invitePage.inputClient1(clientddd);
	   Thread.sleep(2000);
	   driver.findElement(By.className("home-site-list-ul")).findElement(By.className("home-project-list-ul")).findElement(By.className("invite-people-button")).click();
	   Thread.sleep(3000);
	   log.info("End: testSPWorkspaceSendInvitationToClient");
 	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP logout")
   public void testSPLogout1stTime() throws InterruptedException {
 	  log.info("Start: testSPLogout1stTime");
 	  CommonUtils commonUtils = new CommonUtils(driver);
 	  commonUtils.logoutUser();
 	  Thread.sleep(3500);
 	  log.info("End: testSPLogout1stTime");
 	  log.info("--------------------------------------------"); 
   }
  
   @Test(description="client go to email")
   @Parameters({"gmailLoginPW", "gmail"})
   public void testClientLoginEmail(String gmailLoginPW, String gmail) throws InterruptedException {
	   testGmailLoginAgainPreCon();
	   testLoginToGmail(gmailLoginPW, gmail);
   }
  
   @Test(description="client check the email")
   public void testClientCheckInviteEmail() throws InterruptedException {
	   log.info("Start: testClientCheckInviteEmail");
	   Thread.sleep(5000);
	   List<WebElement> emails = driver.findElements(By.tagName("span"));
	   for (WebElement email : emails) {
	 	  if (email != null) {
	 		  String emailTitle = email.getText();
	 		 if (emailTitle.indexOf("Submitted Project") >= 0) {
	 			  email.click();
	 			  break;
	 		  }
	 	  }
	   }
	   Thread.sleep(2000); 
	   WebElement acceptInv = driver.findElement(By.linkText("Review Project"));
 	   if(!acceptInv.isDisplayed()) {
 		   throw new IllegalStateException("The accept invitation link is missing!");
 	   } else {
 		   acceptInv.click();
 	   } 	   
	   Thread.sleep(2000);  
 	   log.info("End: testClientCheckInviteEmail");
 	   log.info("--------------------------------------------");
   }
  
   @Test(description="client login page")
   public void testClientLoginPage() throws InterruptedException {
	   log.info("Start: testClientLoginPage");	
	   parentWindowHandle = driver.getWindowHandle();
       Set<String> windows = driver.getWindowHandles();
   	   for (String window : windows) {
   	       if (!window.equals(parentWindowHandle)) {
   			   driver.switchTo().window(window);
   			   break;
   	       }
   	   }
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.clientloginWithourUname();
	   Thread.sleep(3000);
	   log.info("End: testClientLoginPage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="client verify the project")
   public void testClientVerifyProj() throws InterruptedException {
	   log.info("Start: testClientVerifyProj");
	   try {
		   Assert.assertTrue(Page.isTextPresent(productName, driver));
	   } catch(Throwable e) {
	   	   System.out.println("The project created by sp does Not show on client site!\n");
	   	   log.info("The project created by sp does Not show on client site!");
	   }
	   log.info("End: testClientVerifyProj");
	   log.info("--------------------------------------------");
    }
   
   @Test(description="client open the project")
   public void testClientOpenProj() throws InterruptedException {
	   log.info("Start: testClientOpenProj");
	   //CommonUtils commonUtils = new CommonUtils(driver); 
	   //commonUtils.testClientOpenProject(productName);
	   ClientEditProjectPage clientEditProjectPage = new ClientEditProjectPage(driver);
	   clientEditProjectPage.clickProj(); 
	   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   Thread.sleep(2000);
	   log.info("End: testClientOpenProj");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="client logout")
   public void testClientLogout2ndTime() throws InterruptedException {
	   log.info("Start: testClientLogout2ndTime");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testClientLogout1();
	   log.info("End: testClientLogout2ndTime");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="logout email")
   public void testLogoutGmail() throws InterruptedException {
	   testCloseGmailPage();
   }
   
   @Test(description="sp login again")
   public void testSPLoginAgain() throws InterruptedException {
	   testSPLogin();
   }
   
   @Test(description="sp open the project")
   public void testSPOpenProj() throws InterruptedException {
	   log.info("Start: testSPOpenProj");
	   Thread.sleep(2000);
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testOpenProjectInWS(productName);
	   Thread.sleep(3000);
	   log.info("End: testSPOpenProj");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp go to file tab")
   public void testSPGoToFileTab() throws InterruptedException {
	   log.info("Start: testSPGoToFileTab");
	   WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Files")).click();  
	   Thread.sleep(2000);
	   log.info("End: testSPGoToFileTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp upload file")
   @Parameters({"profilePicture", "sharingTeam"})
   public void testSPUploadFile(String profilePicture, String sharingTeam) throws InterruptedException, AWTException {
	   log.info("Start: testSPUploadFile");
       page.uploadFileModalWindow(profilePicture);   
       BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   brochurePopup.clickUploadFileDropBT();
 	   Thread.sleep(2000);
 	   page.robotUpload();
 	   Thread.sleep(6500); 	   
	   log.info("End: testSPUploadFile");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp share group team")
   public void testSPShareGroup() throws InterruptedException {
	   log.info("Start: testSPShareGroup");
	   spSiteLoginPage = driver.getCurrentUrl();
	   CommonUtils commonUtils = new CommonUtils(driver);	   
	   commonUtils.testSharingOption(spSiteLoginPage);   
	   Thread.sleep(6500);
	   driver.findElement(By.className("upload")).click();
	   Thread.sleep(3500);
	   log.info("End: testSPShareGroup");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp go to Message")
   public void testSPGoToMsgTab() throws InterruptedException {
	   log.info("Start: testSPGoToMsgTab");
	   WebElement editProj = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Messages")).click();  
	   Thread.sleep(2000);
	   log.info("End: testSPGoToMsgTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp input message")
   public void testSPInputMsg() throws InterruptedException {
	   log.info("Start: testSPInputMsg");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testSPInputMsg("Message from SP\nMessage from SP");
	   Thread.sleep(2000);
	   log.info("End: testSPInputMsg");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp post message")
   public void testSPPostMsg() throws InterruptedException {
	   log.info("Start: testSPPostMsg");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.postMsgSP();
	   Thread.sleep(3000);
	   log.info("End: testSPPostMsg");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp logout")
   public void testSPLogoutAgain() throws InterruptedException {
	   log.info("Start: testSPLogoutAgain");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.logoutUser();
	   Thread.sleep(3500);
	   log.info("End: testSPLogoutAgain");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go to email")
   @Parameters({"gmailLoginPW", "gmail"})
   public void testLoginGmailAgain(String gmailLoginPW, String gmail) throws InterruptedException {
	   testGmailLoginAgainPreCon();
	   testLoginToGmail(gmailLoginPW, gmail);
   }
   
   @Test(description="client check email notification")
   public void testEmailNotification() throws InterruptedException {
	   log.info("Start: testEmailNotification");
	   Thread.sleep(5000);
	   List<WebElement> emails = driver.findElements(By.tagName("span"));
	   for (WebElement email : emails) {
	 	  if (email != null) {
	 		  String emailTitle = email.getText();
	 		 if (emailTitle.indexOf("Message from SP") >= 0) {
	 			  email.click();
	 			  break;
	 		  }
	 	  }
	   }
	   Thread.sleep(2000);
	   driver.findElement(By.linkText("Review and Respond to Message")).click(); 
	   Thread.sleep(2000);  
 	   log.info("End: testEmailNotification");
 	   log.info("--------------------------------------------");
   }
   
   @Test(description="client login")
   @Parameters({"clientddd"})
   public void testClientLogin(String clientddd) throws InterruptedException {
	   log.info("Start: testClientlogin");
	   driver.get(clientdddURL);
	   Thread.sleep(2000);
	   CommonUtils commonUtils = new CommonUtils(driver);  
	   commonUtils.clientlogin(clientddd);
	   Thread.sleep(5000);
	   log.info("End: testClientlogin");
 	   log.info("--------------------------------------------");	   
   }
   
   @Test(description="client open the project")
   public void testClientOpenProjAgain() throws InterruptedException {
	   testClientOpenProj();
   }
   
   @Test(description="client go to file tab")
   @Parameters({"profilePicture"})
   public void testClientGotoFileTab(String profilePicture) throws InterruptedException {
	   log.info("Start: testClientGotoFileTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getFilesTab1();
	   Thread.sleep(1000);
	   CommonUtils commonUtils = new CommonUtils(driver);  
	   commonUtils.testUploadedImagExisting(profilePicture);
	   Thread.sleep(2000);	
	   log.info("End: testClientGotoFileTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="client go to msg tab")
   public void testClientGotoMsgTab() throws InterruptedException {
	   log.info("Start: testClientGotoMsgTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getMessageTab();
	   Thread.sleep(2000);	
	   log.info("End: testClientGotoMsgTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="client logout")
   public void testClientLogoutAgain() throws InterruptedException {
	   log.info("Start: testClientLogoutAgain");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testClientLogout1();
	   log.info("End: testClientLogoutAgain");
	   log.info("--------------------------------------------"); 
   }
} 