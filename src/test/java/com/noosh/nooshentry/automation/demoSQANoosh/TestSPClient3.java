/***********************************************************************
* This class tests the functionality of SP and client 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.FileNotFoundException;
import org.testng.annotations.Parameters;
import java.util.Hashtable;
import java.util.List;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.ClientCreateProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientEditProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.ClientLoginPage;
import com.noosh.nooshentry.automation.buyerSite.ClientUserProfilePage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.awt.AWTException;

public class TestSPClient3 extends BaseSeleniumTest {  
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
		   loginUrl = "https://selenium263945." + companyName + ".com/service/login";  
	   } else {
		   baseUrl = "https://demo." + domain + "." + companyName + ".com/service/signup";
		   //baseUrlPro = "http://" + domain + ".noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2." + domain + "." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + domain + "." + companyName + ".com/service/login";
		   clientURL = "https://selenium263945." + domain + "." + companyName + ".com/client_1/login";
		   clientdddURL = "https://selenium263945." + domain + "." + companyName + ".com/ddd/login";
		   clientURL2 = "https://nooshselenium." + domain + "." + companyName + ".com/autoclient/main";
		   loginUrl = "https://selenium263945." + domain + "." + companyName + ".com/service/login";  
	   } 
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spClientTest3 *****\n\n");
   }
   
   @Test(description="Client login")
   @Parameters({"clientNameddd", "passwordBuyer"})
   public void testClientLogin(String clientNameddd, String passwordBuyer) throws InterruptedException {
   	  log.info("Start: testClientLogin");
   	  driver.get(clientdddURL);
	  driver.manage().window().maximize();
	  Thread.sleep(3000);
   	  ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
   	  Thread.sleep(1000);
   	  clientLoginPage.clientLoginUser(clientNameddd, passwordBuyer);
   	  Thread.sleep(3500);
   	  log.info("End: testClientLogin");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 1")
   public void testClientCreateProj1() throws InterruptedException {
	   log.info("Start: testClientCreateProj1");	
	   ClientHomePage clientHomePage = new ClientHomePage(driver);
	   clientHomePage.clickSmartForm();
	   Thread.sleep(3000);
	   log.info("End: testClientCreateProj1");
	   log.info("--------------------------------------------");
   }
   
  /* 12/16 temp comment out --------------
   @Test(description="Client cancel project")
   public void testClientCancelProj() throws InterruptedException {
	   log.info("Start: testClientCancelProj");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.cancelProj(); 
	   Thread.sleep(3000);	   
	   log.info("End: testClientCancelProj");
	   log.info("--------------------------------------------");
   }
   12/16 temp comment out -----------*/
   
   @Test(description="Client create project - step 2")
   @Parameters({"productName1", "projectNameField"})
   public void testClientCreateProjName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testClientCreateProjName");	
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   productName = productName1 + unicID + " client";
	   System.out.println("productName="+productName);
	   newProject.inputClientProjectName(productName);   
	   Thread.sleep(1000);
	   try {
	      AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
	   } catch(Throwable e) {
		  System.out.println("Project Name field is missing!\n");
		  log.info("Project Name field is missing!");
	   }
	   log.info("End: testClientCreateProjName");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 3")
   @Parameters({"productName1", "projectNameField"})
   public void testClientProjCompletionDate() throws InterruptedException {
	   log.info("Start: testClientProjCompletionDate");	
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   Thread.sleep(2000);   	    
	   brochurePopup.callCalendar();
	   brochurePopup.setNextMonth();
	   brochurePopup.setComplationDate(); 
	   Thread.sleep(3000);
	   log.info("End: testClientProjCompletionDate");
	   log.info("--------------------------------------------");
   }
	
   @Test(description="Client create project - step 4")
   public void testClientProjNextBtn() throws InterruptedException {
	   log.info("Start: testClientProjNextBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.clickNext();
	   Thread.sleep(1000);
	   log.info("End: testClientProjNextBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 5")
   @Parameters({"quantSP", "quantSP2", "quantSP3"})
   public void testClientProjSpecQuantity(String quantSP, String quantSP2, String quantSP3) throws InterruptedException {
	   log.info("Start: testClientProjSpecQuantity");
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
	   Thread.sleep(2000);
	   log.info("End: testClientProjSpecQuantity");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 6")
   public void testClientProjFilesTab() throws InterruptedException {
	   log.info("Start: testClientProjFilesTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getFilesTab();
	   Thread.sleep(1500);
	   log.info("End: testClientProjFilesTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 7")
   @Parameters({"fileForProject4"})
   public void testClientProjFileUpload(String fileForProject4) throws InterruptedException, AWTException {
	   log.info("Start: testClientProjFileUpload");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   page.uploadFileModalWindow(fileForProject4);
	   Thread.sleep(2000);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	
	   log.info("End: testClientProjFileUpload");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 8")
   public void testClientProjReviewSubmitTab() throws InterruptedException {
	   log.info("Start: testClientProjReviewSubmitTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getReviewAndSubmitTab1();
	   Thread.sleep(3000);
	   log.info("End: testClientProjReviewSubmitTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 9")
   public void testClientProjAddSmartFormBtn() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartFormBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.addSmartFormBtn();
	   Thread.sleep(3000);
	   log.info("End: testClientProjAddSmartFormBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 10")
   public void testClientProjAddSmartForm2() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartForm2");
	   WebElement startElement = driver.findElement(By.id("moreProductsPopup"));
	   //List<WebElement> imgs = startElement.findElements(By.tagName("img"));
	   List<WebElement> imgs = startElement.findElements(By.className("product-list-as-icon"));
	   spSiteLoginPage = driver.getCurrentUrl();
	   if(spSiteLoginPage.contains("sqa")) {
		   imgs.get(2).click();
	   } else if(spSiteLoginPage.contains("sdm") || spSiteLoginPage.contains("scd")) {
		   imgs.get(2).click();
	   } else 
		   imgs.get(2).click();	   
	   Thread.sleep(2500);
	   log.info("End: testClientProjAddSmartForm2");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 11")
   public void testClientProjAddSmartForm3() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartForm3");
	   testClientProjReviewSubmitTab();
	   testClientProjAddSmartFormBtn();
	   testClientProjAddSmartForm2();
	   testClientProjReviewSubmitTab();
	   Thread.sleep(2000);
	   log.info("End: testClientProjAddSmartForm3");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 12")
   public void testClientProjCreateBtn() throws InterruptedException {
	   log.info("Start: testClientProjCreateBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.clickCreateProjBtn();
	   if(spSiteLoginPage.contains("sdm")) {
		   Alert alert = driver.switchTo().alert();
		   alert.accept();
		   Thread.sleep(1000);
	   }
	   Thread.sleep(5000);
	   log.info("End: testClientProjCreateBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client logout")
   public void testClientLogout() throws InterruptedException {
   	  log.info("Start: testClientLogout");
      Thread.sleep(3500);
      CommonUtils commonUtils = new CommonUtils(driver);
	  commonUtils.testClientLogout1();
  	  Thread.sleep(3000);  	  
   	  log.info("End: testClientLogout");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="SP login")
   @Parameters({"loginUserID", "passwordSP"})
   public void testSPLogin(String loginUserID, String passwordSP) throws InterruptedException {
   	  log.info("Start: testSPLogin");
      Thread.sleep(1500);
      driver.get(loginUrl);  
      Thread.sleep(2000);    
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
      loginDemoSqa.loginUser(loginUserID, passwordSP); 
      Thread.sleep(2000);
      spSiteLoginPage = driver.getCurrentUrl();  
      log.info("End: testSPLogin");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="SP verify the new project created by client")
   @Parameters({"loginUserID", "passwordSP"})
   public void testSPVerifyProjCreatedByClient(String loginUserID, String passwordSP) throws InterruptedException {
   	  log.info("Start: testSPVerifyProjCreatedByClient");
   	  try {
   		  AssertJUnit.assertTrue(Page.isTextPresent(productName, driver));
   	  } catch(Throwable e) {
   		  System.out.println("The project created by client does Not show on SP site!\n");
   		  log.info("The project created by client does Not show on SP site!");
   	  }
      log.info("End: testSPVerifyProjCreatedByClient");
  	  log.info("--------------------------------------------");
   }

   @Test(description="SP open the project created by client")
   public void testSPOpenProjCreatedByClient() throws InterruptedException {
	   log.info("Start: testSPOpenProjCreatedByClient");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testOpenProjectInWS(productName);
	   log.info("End: testSPOpenProjCreatedByClient");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP logout")
   public void testSPLogout() throws InterruptedException {
	   	log.info("Start: testSPLogout");
	   	Thread.sleep(6000);
	   	CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.spLogout();
	 	Thread.sleep(3000);	  
	   	log.info("End: testSPLogout");
	  	log.info("--------------------------------------------");
   }
   
   @Test(description="Client login again")
   @Parameters({"clientNameddd", "passwordBuyer"})
   public void testClientLoginAgain(String clientNameddd, String passwordBuyer) throws InterruptedException {
	   log.info("Start: testClientLoginAgain");
	   testClientLogin(clientNameddd, passwordBuyer); 
	   log.info("End: testClientLoginAgain");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client open project")
   public void testOpenProject() throws InterruptedException {
	   log.info("Start: testOpenProject");
	   ClientEditProjectPage clientEditProjectPage = new ClientEditProjectPage(driver);
	   clientEditProjectPage.clickProj();
	   Thread.sleep(3000);  
	   log.info("End: testOpenProject");
	   log.info("--------------------------------------------");
   }
      
   @Test(description="Client go to Smart Forms tab")
   public void testClientExistingProjSmartFormTab() throws InterruptedException {
	   log.info("Start: testClientExistingProjSmartFormTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getSmartFormsTab();
	   Thread.sleep(2000);
	   log.info("End: testClientExistingProjSmartFormTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client go to File tab")
   public void testClientExistingProjFileTab() throws InterruptedException {
	   log.info("Start: testClientExistingProjFileTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getFilesTab1();
	   Thread.sleep(1000);
	   log.info("End: testClientExistingProjFileTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client uploading files")
   @Parameters({"fileForProject3"})
   public void testClientExistingProjFileUpload(String fileForProject3) throws InterruptedException, AWTException {
	   log.info("Start: testClientExistingProjFileUpload");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   page.uploadFileModalWindow(fileForProject3);
	   Thread.sleep(2000);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	
	   log.info("End: testClientExistingProjFileUpload");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client go to Messages tab")
   public void testClientExistingProjMsgTab() throws InterruptedException {
	   log.info("Start: testClientExistingProjMsgTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getMessageTab();
	   Thread.sleep(1000);
	   log.info("End: testClientExistingProjMsgTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client input message")
   @Parameters({"clientMsg"})
   public void testClientExistingProjInputMsg() throws InterruptedException {
	   log.info("Start: testClientExistingProjInputMsg");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.inputMsg();
	   Thread.sleep(1000);
	   log.info("End: testClientExistingProjInputMsg");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client post message")
   @Parameters({"clientMsg"})
   public void testClientExistingProjPostMsg() throws InterruptedException {
	   log.info("Start: testClientExistingProjPostMsg");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.postMsg();
	   Thread.sleep(3000);
	   log.info("End: testClientExistingProjPostMsg");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client go to Activities tab")
   public void testClientExistingProjActivitiesTab() throws InterruptedException {
	   log.info("Start: testClientExistingProjActivitiesTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getActivitiesTab();
	   Thread.sleep(3500);
	   log.info("End: testClientExistingProjActivitiesTab");
	   log.info("--------------------------------------------"); 
   }
   @Test(description="Client logout")
   public void testClientLogoutAfterPostMSG() throws InterruptedException {
	   testClientLogout();
   }
   
   @Test(description="SP login to check the msg client posted")
   @Parameters({"loginUserID", "passwordSP"})
   public void testSPLoginCheckMSG(String loginUserID, String passwordSP) throws InterruptedException {
       testSPLogin(loginUserID, passwordSP);
   }
   
   @Test(description="SP open the project again")
   public void testSPOpenProjCreatedByClientAgain() throws InterruptedException {
	   log.info("Start: testSPOpenProjCreatedByClientAgain");
	   CommonUtils commonUtils = new CommonUtils(driver);    
	   commonUtils.testOpenProjectInWS(productName);         
	   Thread.sleep(3000);
	   log.info("End: testSPOpenProjCreatedByClientAgain");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP go to Files tab and verify files uploaded by client")
   public void testSPVerifyUploadFiles() throws InterruptedException {
	   log.info("Start: testSPVerifyUploadFiles");	   
	   CommonUtils commonUtils = new CommonUtils(driver);   
	   commonUtils.testOpenProjectTabInWS1(productName, "Files"); 
	   //BrochurePopupWindow brochurePopupWindow = new BrochurePopupWindow(driver);
	   //brochurePopupWindow.getFilesTab();
	   Thread.sleep(2000);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(productName, driver));
	   } catch(Throwable e) {
		   System.out.println("The file uploaded by client is missing!\n");
		   log.info("The file uploaded by client is missing!\n");
	   }
	   log.info("End: testSPVerifyUploadFiles");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP go to Messages tab and verify messages posted by client")
   public void testSPVerifyMsg() throws InterruptedException {
	   log.info("Start: testSPVerifyMsg");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testOpenProjectTabInWS(productName, "Messages");
	   Thread.sleep(2000);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent("Test message from client 2.", driver));
	   } catch(Throwable e) {
		   System.out.println("The message posted by client is missing!\n");
		   log.info("The message posted by client is missing!\n");
	   }
	   log.info("End: testSPVerifyMsg");
	   log.info("--------------------------------------------"); 
   }
     
   @Test(description="SP logout")
   public void testSPLogoutAfterVerify() throws InterruptedException  {
	   testSPLogout();
   }
   
   @Test(description="Client login")
   @Parameters({"clientNameddd", "passwordBuyer"})
   public void testClientLoginToUpdateProfile(String clientNameddd, String passwordBuyer) throws InterruptedException {
	   testClientLogin(clientNameddd, passwordBuyer);
   } 
   
   @Test(description="Client go to user profile")
   public void testClientClickUserProfile() throws InterruptedException {
	   log.info("Start: testClientClickUserProfile");
	   ClientHomePage clientHomePage = new ClientHomePage(driver);
	   clientHomePage.clickUserProfileImage();
	   Thread.sleep(3000);
	   log.info("End: testClientClickUserProfile");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client change profile picture")
   @Parameters({"fileForProject3"})
   public void testClientChangeProfilePicture(String fileForProject3) throws InterruptedException, AWTException {
	   log.info("Start: testClientChangeProfilePicture");
	   driver.findElement(By.id("fileProfilePicture")).click();
	   page.uploadFileModalWindow(fileForProject3);
       Thread.sleep(1000);
       page.robotUpload();
       Thread.sleep(5000);
	   log.info("End: testClientChangeProfilePicture");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client reset password")
   public void testClientResetPasswordBtn() throws InterruptedException {
	   log.info("Start: testClientResetPasswordBtn");
       //ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
       //clientUserProfilePage.clickResetPassword();
	   driver.findElement(By.id("changePasswordBtn")).click();
       Thread.sleep(1000);
	   log.info("End: testClientResetPasswordBtn");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client cancel change password")
   public void testClientCancelChangePassword() throws InterruptedException {
	   log.info("Start: testClientCancelChangePassword");
	   ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
	   clientUserProfilePage.cancelChangePassword();
	   Thread.sleep(1500);
	   log.info("End: testClientCancelChangePassword");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client click Reset Password button 2nd time")
   public void testClientClickResetPasswordBtn2ndTime() throws InterruptedException {
	   log.info("Start: testClientClickResetPasswordBtn2ndTime");
	   testClientResetPasswordBtn();
	   log.info("End: testClientClickResetPasswordBtn2ndTime");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client change password inputs")
   @Parameters({"passwordSP", "newPasswordSP"}) 
   public void testClientChangePasswordInputs(String passwordSP, String newPasswordSP) throws InterruptedException {
	   log.info("Start: testClientChangePasswordInputs");
	   ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
	   clientUserProfilePage.inputCurrentPasswordField(passwordSP);
	   Thread.sleep(800);
	   clientUserProfilePage.inputNewPasswordField(newPasswordSP);
	   Thread.sleep(800);
	   clientUserProfilePage.inputConfirmPasswordField(newPasswordSP);
	   Thread.sleep(1000);
	   log.info("End: testClientChangePasswordInputs");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client click on change password button")
   public void testClientClickChangePasswordBtn() throws InterruptedException {
	   log.info("Start: testClientClickChangePasswordBtn");
	   ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
	   clientUserProfilePage.clickChangePasswordBtn();
	   Thread.sleep(2000);
	   log.info("End: testClientClickChangePasswordBtn");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client close user profile")
   public void testClientCloseUserProfile() throws InterruptedException {
	   log.info("Start: testClientCloseUserProfile");
	   ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
	   clientUserProfilePage.clickProfileCloseBtn();
	   Thread.sleep(1000);
	   log.info("End: testClientCloseUserProfile");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client logout after changing password")
   public void testClientLogoutAgain() throws InterruptedException {
	   testClientLogout();
   }
   
   @Test(description="Client login after changing password")
   @Parameters({"clientNameddd", "newPasswordSP"})
   public void testClientLoginWithNewPW(String clientNameddd, String newPasswordSP) throws InterruptedException {
	   log.info("Start: testClientLoginWithNewPW");
	   	  driver.get(clientdddURL);
		  Thread.sleep(3000);
	   	  ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
	   	  Thread.sleep(1000);
	   	  clientLoginPage.clientLoginUser(clientNameddd, newPasswordSP);
	   	  Thread.sleep(3500);
	   	  log.info("End: testClientLoginWithNewPW");
	  	  log.info("--------------------------------------------");
   }
   
   @Test(description="Client go to user profile")
   public void testClientGotoUserProfile() throws InterruptedException {
       testClientClickUserProfile();
   }
   
   @Test(description="Client reset back password")
   public void testClientClickResetPW() throws InterruptedException {
	   testClientResetPasswordBtn();
   }
   
   @Test(description="Client cancel changing password")
   public void testClientClickCancelChangePasswordBtn() throws InterruptedException {
	   testClientCancelChangePassword();
   }
   
   @Test(description="Client click on reset password button")
   public void testClientClickResetPasswordBtnAgain() throws InterruptedException {
	   testClientResetPasswordBtn();
   }
   
   @Test(description="Client change back password inputs")
   @Parameters({"newPasswordSP", "passwordSP"}) 
   public void testClientChangeBackPasswordInputs(String newPasswordSP, String passwordSP) throws InterruptedException {
	   log.info("Start: testClientChangeBackPasswordInputs");
	   ClientUserProfilePage clientUserProfilePage = new ClientUserProfilePage(driver);
	   Thread.sleep(1000);
	   clientUserProfilePage.inputCurrentPasswordField(newPasswordSP);
	   Thread.sleep(800);
	   clientUserProfilePage.inputNewPasswordField(passwordSP);
	   Thread.sleep(800);
	   clientUserProfilePage.inputConfirmPasswordField(passwordSP);
	   Thread.sleep(1000); 
	   log.info("End: testClientChangeBackPasswordInputs");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Client click change password button")
   public void testClientChangeBackPasswordBtn() throws InterruptedException {
	   testClientClickChangePasswordBtn();
	   driver.findElement(By.id("profileUpdate-close"));
	   Thread.sleep(5000);
   }
   
   @Test(description="Client close profile")
   public void testClientCloseUserProfileAgain() throws InterruptedException {
	   testClientCloseUserProfile();
   }
   
   @Test(description="Client logout after changing back password")
   public void testClientLogoutAfterChangeBackPW() throws InterruptedException {	   
	   testClientLogout();
	   Thread.sleep(3500);
   }
   
   @Test(description="Client login with original password")
   @Parameters({"clientNameddd", "passwordSP"})
   public void testClientLoginWithOriginalPW(String clientNameddd, String passwordSP) throws InterruptedException {
	   log.info("Start: testClientLoginWithOriginalPW");
	   driver.get(clientdddURL);
	   Thread.sleep(3000);
	   //ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
	   //clientLoginPage.clientLoginUser(clientNameddd, passwordSP);
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.clientlogin(clientNameddd);
	   Thread.sleep(3500);
	   try {
	   		  AssertJUnit.assertTrue(Page.isTextPresent("Username or password is invalid", driver));
 	   } catch(Throwable e) {
 	        System.out.println("Client password is invalid!\n");
 	    	log.info("Client password is invalid!");
 	   }
	   	log.info("End: testClientLoginWithOriginalPW");
	  	log.info("--------------------------------------------");
   }
   
   @Test
   public void clentCreateProj() throws InterruptedException {
	   testClientCreateProj1();
   }
   
   @Test
   @Parameters({"productName1", "projectNameField"})
   public void clentCreateProjName(String productName1, String projectNameField) throws InterruptedException {
	   testClientCreateProjName(productName1, projectNameField);
	  
   }
   
   @Test
   public void clientProjReviewSubmitTab() throws InterruptedException {
	   testClientProjReviewSubmitTab();
   }
   
   @Test
   public void clientSaveDraft() throws InterruptedException {
	   log.info("Start: clientSaveDraft");
	   ClientEditProjectPage clientEditProjectPage = new ClientEditProjectPage(driver);
	   clientEditProjectPage.saveDraft();
	   Thread.sleep(5000);
	   log.info("End: clientSaveDraft");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client logout")
   public void testClientLogoutAfterPWchangeBack() throws InterruptedException {	   
	   testClientLogout();
	   Thread.sleep(3500);
   }   
}  