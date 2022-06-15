/***********************************************************************
* This class tests the functionality for Copy Project 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.ClientCreateProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientEditProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.ClientLoginPage;
import com.noosh.nooshentry.automation.buyerSite.EstimateRFEPage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPAwardOrderPage;
import com.noosh.nooshentry.automation.buyerSite.SPConfirmOrderPage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SPLoginPage;
import com.noosh.nooshentry.automation.buyerSite.SPReviewEstimatePage;
import com.noosh.nooshentry.automation.buyerSite.SPcreateQuotePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.buyerSite.SupplierInvitePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierLoginPage;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptLog;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NGEcopyProj extends BaseSeleniumTest {
  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String profileNameSP;
   String spSiteLoginPage;
   static String baseUrl;
   String firstWindow;
   String secondWindow;
   static String parentWindowHandle;
   static String spCopyProjUrl;
   static String projectName;
   static String projName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if(domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup"; 
		   spCopyProjUrl = "https://seleniumtest." + companyName + ".com";
	   } else {
		   baseUrl = "https://demo." + domain + "qa2." + companyName + ".com/service/signup"; 
		   spCopyProjUrl = "https://seleniumtest." + domain + "." + companyName + ".com";
	   }
   }

   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** ngeCopySpecTest *****\n\n");
   }
   
   @Test(description="SP login")
   @Parameters({"spLoginID", "passwordSP"})
   public void testSPLogin(String spLoginID, String passwordSP) throws InterruptedException {
     	 log.info("Start: testSPLogin");
         Thread.sleep(1500);
         driver.get(spCopyProjUrl); 
         driver.manage().window().maximize(); 
         Thread.sleep(2000);
         CommonUtils commonUtils = new CommonUtils(driver);
         commonUtils.loginUser1(spLoginID);
         spSiteLoginPage = driver.getCurrentUrl();  	  
         log.info("End: testSPLogin");
    	 log.info("--------------------------------------------");
   }
   
   @Test(description="Existing SP click global 'create project'")
   public void testExistingSPClickGlbCreateProj() throws InterruptedException {
  	   log.info("Start: testExistingSPClickGlbCreateProj");
  	   SPHomePage sPHomePage = new SPHomePage(driver);
  	   Thread.sleep(3000);
  	   sPHomePage.clickGlbCreateProj();    
  	   Thread.sleep(3000);
  	   log.info("End: testExistingSPClickGlbCreateProj");
  	   log.info("--------------------------------------------"); 
   }
  
   @Test(description="Existing SP input project name")
   @Parameters({"productName1", "projectNameField"})
   public void testExistingSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
  	   log.info("Start: testExistingSPInputProjectName");
  	   productName1 = productName1 + unicID;  
  	   projectName = productName1;
  	   CreateNewProject newProject = new CreateNewProject(driver); 
  	   newProject.inputGlbProjectName(productName1);   
  	   Thread.sleep(1000);
  	   try {
  	      AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
  	   } catch(Throwable e) {
  		  System.out.println("Project Name field is missing!\n");
  		  log.info("Project Name field is missing!");
  	   }
  	   log.info("End: testExistingSPInputProjectName");
  	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP select client or workspace")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
  	   log.info("Start: testExistingSPSelectWorkspace");
  	   CreateNewProject createNewProject = new CreateNewProject(driver);
  	   createNewProject.selectWorkSpace();
       Thread.sleep(1000);
       try {
    	   AssertJUnit.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
       } catch(Throwable e) {
    	   System.out.println("Client or Workspace field is missing!\n");
    	   log.info("Client or Workspace field is missing!");
       }
       log.info("End: testExistingSPSelectWorkspace");
  	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP input client user")
   @Parameters({"clientUser", "clientFieldName"})
   public void testSPInputClient(String clientUser, String clientFieldName) {
	   log.info("Start: testSPInputClient");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   try {
		   Assert.assertTrue(Page.isTextPresent(clientFieldName, driver));
		   commonUtils.testInputClientUser(clientUser);
	   } catch(Throwable e) {
    	   System.out.println("Invite client User field field is missing!\n");
    	   log.info("Invite client User field field is missing!");
       }
	   log.info("End: testSPInputClient");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP select a smart form")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
  	    log.info("Start: testExistingSPSelectSmartForm");
  	    Thread.sleep(1000);
  	    try {
  	    	AssertJUnit.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
  	    } catch(Throwable e) {
  	    	System.out.println("Client or Workspace field is missing!\n");
  	    	log.info("Client or Workspace field is missing!");
  	    }
  	    WebElement startElement = driver.findElement(By.id("createProjectProductList"));
  	    List<WebElement> img = startElement.findElements(By.tagName("img"));
  	    spSiteLoginPage = driver.getCurrentUrl(); 
  	    if(spSiteLoginPage.contains("sdm"))
  	 	    img.get(1).click(); 	         
  	    else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("qa2"))   
  		    img.get(0).click();   
  	    else
  	    	img.get(0).click();
  	    Thread.sleep(3000);
  	    log.info("End: testExistingSPSelectSmartForm");
  	    log.info("--------------------------------------------"); 
   }
     
    @Test(description="Existing SP Create Project - step 2")
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
  	   "specDescrSP", "fileForProject11"})
    public void testExistingSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
  		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
  		     String fileForProject11) throws InterruptedException, AWTException {    
    	log.info("Start: testSPcreateProjectProjectWizard");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
    			quantSP3, specDescrSP, fileForProject11);
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	    
    	brochurePopup.getFilesTab();    
    	Thread.sleep(1000);
    	page.uploadFileModalWindow(fileForProject11);   
    	Thread.sleep(2000);
    	brochurePopup.clickUploadFileDropBT();
    	Thread.sleep(2000);
    	page.robotUpload();
    	Thread.sleep(8000);	
    	commonUtils.verifyUploadedImg(fileForProject11);    
    	brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000);
    	brochurePopup.clickCreateBT();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);
    	/*
	   WebElement imgPreview = driver.findElement(By.className("uploaded-file-preview-image"));
	   if (imgPreview == null) {
			   System.out.println("Image uploading is failed!");
			   log.info("Image uploading is failed!"); 
	   } 
    	 */  
    	log.info("End: testExistingSPcreateProjectWizard");
    	log.info("--------------------------------------------"); 
    }
   
    @Test(description="Go to copy project")
    public void testCopyProj() throws InterruptedException {
    	log.info("Start: testCopyProj");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.clickCopy();
    	log.info("End: testCopyProj");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="Check original uploaded file")
    @Parameters({"copyFileText"})
    public void testCheckOriginalFile(String copyFileText) throws InterruptedException {
    	log.info("Start: testCheckOriginalFile");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	    
    	brochurePopup.getFilesTab();
    	Thread.sleep(2000);
    	commonUtils.verifyCopyUploadedFileText(copyFileText);
    	Thread.sleep(2000);
    	log.info("End: testCheckOriginalFile");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="Create copyproject")
    public void testCreateCopyProj() throws InterruptedException {
    	log.info("Start: testCreateCopyProj");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	//brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000);
  	    List<WebElement> createBT = driver.findElements(By.id("cpNextStep"));
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	//Thread.sleep(3000);
    	//Alert alert = driver.switchTo().alert();
    	//alert.accept();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);
    	log.info("End: testCreateCopyProj");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testVerifyProjInfo() throws InterruptedException {
    	log.info("Start: testVerifyProjInfo");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyCopyProjInfo(projectName);
    	Thread.sleep(2000);
    	log.info("End: testVerifyProjInfo");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testVerifySFInfo() throws InterruptedException {
    	log.info("Start: testVerifySFInfo");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testOpenProjectSFTabInWS("Copy of " + projectName, "Smart Forms");
    	Thread.sleep(3000);
    	Assert.assertTrue(true, projectName + " - Brochure");
    	Thread.sleep(2000);
    	log.info("End: testVerifySFInfo");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"fileForProject11"})
    public void testVerifyFileInfo(String fileForProject11) throws InterruptedException {
    	log.info("Start: testVerifyFileInfo");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testOpenProjectTabInWS1("Copy of " + projectName, "Files");
    	Thread.sleep(5000); 
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyUploadedImgSPHome(fileForProject11);
    	Thread.sleep(3000);
    	log.info("End: testVerifyFileInfo");
    	log.info("--------------------------------------------"); 
    }
   
    @Test(description="SP logout")
    public void testSPLogout() throws InterruptedException {
  	    log.info("Start: testSPLogout");
  	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.spLogout();
  	    Thread.sleep(3500);
  	    log.info("End: testSPLogout");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="Check gmail login preconditions")
    public void testGmailLoginPreCon() throws InterruptedException {
 	   CommonUtils commonUtils = new CommonUtils(driver);  
 	   commonUtils.testGmailLoginPreCon();
    }
    
    @Test(description="Check notification")
    @Parameters({"gmailLoginPW", "emailAcct3"})
    public void testLoginToGmail(String gmailLoginPW, String emailAcct3) throws InterruptedException {
    	log.info("Start: testLoginToGmail");
   	    driver.get("http://www.gmail.com");
   	    testGmailLoginPreCon();  
   	    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
   	    gmailLoginPage.enterEmail(emailAcct3);
   	    Thread.sleep(1500);
   	    gmailLoginPage.enterPassword(gmailLoginPW);
   	    Thread.sleep(3500);
   	    gmailLoginPage.check();
  	    gmailLoginPage.signIn();
  	    Thread.sleep(3000);
   	    log.info("End: testLoginToGmail");
   	    log.info("--------------------------------------------");
    }
    
    @Test(description="client check the email")
    public void testClientNotificationEmail() throws InterruptedException {
 	   log.info("Start: testClientNotificationEmail");
 	   Thread.sleep(5500);
 	   List<WebElement> emails = driver.findElements(By.tagName("span"));
 	   for (WebElement email : emails) {
 	 	  if (email != null) {
 	 		  String emailTitle = email.getText();
 	 		  //if (emailTitle.indexOf("selenium test Submitted Project Copy of") >= 0) {
 	 		  if (emailTitle.indexOf("selenium test Submitted Project Copy of Selenium") >= 0) {
 	 			  email.click();
 	 			  break;
 	 		  }
 	 	  }
 	   }
 	   Thread.sleep(2500); 
 	  // WebElement acceptInv = driver.findElement(By.linkText("Login"));
 	   WebElement acceptInv = driver.findElement(By.linkText("Review Project"));
  	   if(!acceptInv.isDisplayed()) {
  		   throw new IllegalStateException("The accept invitation link is missing!");
  	   } else {
  		   acceptInv.click();
  	   } 	   
 	   Thread.sleep(2500);  
  	   log.info("End: testClientNotificationEmail");
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
 		   Assert.assertTrue(Page.isTextPresent("Copy of " + projectName, driver));
 	   } catch(Throwable e) {
 	   	   System.out.println("The project created by sp does Not show on client site!\n");
 	   	   log.info("The project created by sp does Not show on client site!");
 	   }
 	   Thread.sleep(5000);
 	   log.info("End: testClientVerifyProj");
 	   log.info("--------------------------------------------");
     }
    
    @Test(description="Client create project - step 1")
    public void testClientCreateProj1() throws InterruptedException {
 	   log.info("Start: testClientCreateProj1");	
 	   ClientHomePage clientHomePage = new ClientHomePage(driver);
 	   clientHomePage.clickSmartForm();
 	   Thread.sleep(4000);
 	   log.info("End: testClientCreateProj1");
 	   log.info("--------------------------------------------");
    }
    
    @Test(description="Client create project - step 2")
    @Parameters({"productName1", "projectNameField"})
    public void testClientCreateProjName(String productName1, String projectNameField) throws InterruptedException {
 	   log.info("Start: testClientCreateProjName");	
 	   CreateNewProject newProject = new CreateNewProject(driver); 
 	   projName = productName1 + unicID + " client";
 	   System.out.println("productName="+projName);
 	   newProject.inputClientProjectName(projName);   
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
    public void testClientProjReviewSubmitTab() throws InterruptedException {
 	   log.info("Start: testClientProjReviewSubmitTab");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   brochurePopup.getReviewAndSubmitTab1();
 	   Thread.sleep(3000);
 	   log.info("End: testClientProjReviewSubmitTab");
 	   log.info("--------------------------------------------");
    }
    
    @Test(description="Client create project - step 5")
    public void testClientProjAddSmartFormBtn() throws InterruptedException {
 	   log.info("Start: testClientProjAddSmartFormBtn");
 	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
 	   clientCreateProjectPage.addSmartFormBtn();
 	   Thread.sleep(3000);
 	   log.info("End: testClientProjAddSmartFormBtn");
 	   log.info("--------------------------------------------");
    }
    
    @Test(description="Client create project - step 6")
    public void testClientProjAddSmartForm2() throws InterruptedException {
 	   log.info("Start: testClientProjAddSmartForm2");
 	   WebElement startElement = driver.findElement(By.id("productsPopupUl"));
 	   List<WebElement> imgs = startElement.findElements(By.tagName("img"));
 	   spSiteLoginPage = driver.getCurrentUrl();
 	   if(spSiteLoginPage.contains("sqa")) {
 		   imgs.get(1).click();
 	   } else if(spSiteLoginPage.contains("sdm") || spSiteLoginPage.contains("scd")) {
 		   imgs.get(2).click();
 	   } else 
 		   imgs.get(1).click();
 	   Thread.sleep(5000);
 	   log.info("End: testClientProjAddSmartForm2");
 	   log.info("--------------------------------------------");
    }
    
    @Test
    public void reviewSubmitTab() throws InterruptedException {
    	testClientProjReviewSubmitTab();
    }
    
    @Test(description="Client create project - step 7")
    public void testClientProjCreateBtn() throws InterruptedException {
 	   //testClientProjReviewSubmitTab();
    	log.info("Start: testClientProjCreateBtn");
    	ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
    	clientCreateProjectPage.clickCreateProjBtn();
    	Thread.sleep(2000);
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	Thread.sleep(8000);
    	log.info("End: testClientProjCreateBtn");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="client logout")
    public void testClientLogout() throws InterruptedException {
    	log.info("Start: testClientLogout");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
    	log.info("End: testClientLogout");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void gmailLogout() throws InterruptedException {    	
    	log.info("Start: gmailLogout");
    	Thread.sleep(3000);
    	driver.close();  
    	Thread.sleep(2000);
    	driver.switchTo().window(parentWindowHandle); 
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.logoutGmailAccount();
    	log.info("End: gmailLogout");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="sp login")
    @Parameters({"spLoginID", "passwordSP"})
    public void testSPLoginAgain(String spLoginID, String passwordSP) throws InterruptedException {
    	log.info("Start: testSPLoginAgain");
    	driver.get(spCopyProjUrl); 
        Thread.sleep(2000);
        CommonUtils commonUtils = new CommonUtils(driver);
        commonUtils.loginUser1(spLoginID);
        log.info("End: testSPLoginAgain");
    	log.info("--------------------------------------------");
    }
   
    @Test(description="copy project created by client")
    public void testCopyProjCreatedByClient() throws InterruptedException {
    	log.info("Start: testCopyProjCreatedByClient");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.clickCopy();
    	log.info("End: testCopyProjCreatedByClient");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testGotoReviewTab() throws InterruptedException {
    	log.info("Start: testGotoReviewTab");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.goToReviewTab(); 
    	Thread.sleep(2500);
    	log.info("End: testGotoReviewTab");
  	    log.info("--------------------------------------------");
    }
    
    @Test 
    public void testVerifySF() throws InterruptedException {
    	log.info("Start: testVerifySF");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verifySF("Creative Brief");
    	Thread.sleep(2000);
    	log.info("End: testVerifySF");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testCreateCopyProjCreatedByClient() throws InterruptedException {
    	log.info("Start: testCreateCopyProjCreatedByClient");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	//Thread.sleep(1000);
    	//brochurePopup.clickCreateBT();
    	Thread.sleep(1000);
  	    List<WebElement> createBT = driver.findElements(By.id("cpNextStep"));
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	Thread.sleep(1000);
    	createBT.get(0).click();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);
    	log.info("End: testCreateCopyProjCreatedByClient");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testCopyProjInfo() throws InterruptedException {
    	log.info("Start: testCopyProjInfo");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyCopyClientProjInfo("Copy of " + projName);
    	Thread.sleep(2000);
    	log.info("End: testCopyProjInfo");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testCopyProjSF() throws InterruptedException {
    	log.info("Start: testCopyProjSF");
    	String sfName = projName + "- Brochure";
    	Assert.assertTrue(true, "Smart form name " + sfName + " is incorrect");
    	Thread.sleep(3000);
    	log.info("End: testCopyProjSF");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="sp copy project with two sf")
    public void testCreateProjWith2sf() throws InterruptedException {    	
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void testSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test    
    @Parameters({"clientOrWorkspace"})
    public void testSPSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void testSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
   	   "specDescrSP", "fileForProject11"})
    public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
   		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
   		     String fileForProject11) throws InterruptedException, AWTException {    
     	log.info("Start: testSPcreateProjectProjectWizard");
     	CommonUtils commonUtils = new CommonUtils(driver);
     	AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver);
     	commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
     			quantSP3, specDescrSP, fileForProject11);
     	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	    
     	brochurePopup.getFilesTab();    
     	Thread.sleep(1000);
     	page.uploadFileModalWindow(fileForProject11);   
     	Thread.sleep(2000);
     	brochurePopup.clickUploadFileDropBT();
     	Thread.sleep(2000);
     	page.robotUpload();
     	Thread.sleep(8000);	
     	commonUtils.verifyUploadedImg(fileForProject11);    
     	brochurePopup.getReviewAndSubmitTab();
     	Thread.sleep(3000);
     	addSmartFormPage.clickAddSmart();    
    	Thread.sleep(1000);    
    	List<WebElement> imgs = driver.findElement(By.id("productsPopupUl")).findElements((By.tagName("img")));
		imgs.get(2).click();
		Thread.sleep(3000);	
		brochurePopup.getReviewAndSubmitTab();
     	Thread.sleep(3000);
     	brochurePopup.clickCreateBT();
     	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     	Thread.sleep(10000);
     	log.info("End: testSPcreateProjectProjectWizard");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testCopyProjAgain() throws InterruptedException {
    	testCopyProj();
    	testCreateCopyProj();
    }
    
    @Test
    public void testVerifyCopyProjSF() throws InterruptedException {
    	log.info("Start: testVerifyCopyProjSF");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000);
    	
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	String sf1 = "Brochure";
    	String sf2 = "Creative Brief";
    	createNewProject.verifySFs(sf1, sf2);
    	createProj();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);
    	log.info("End: testVerifyCopyProjSF");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testVerifySFsInfo() throws InterruptedException {    	
    	log.info("Start: testVerifySFsInfo");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testOpenProjectSFTabInWS("Copy of " + projectName, "Smart Forms");
    	Thread.sleep(3000);
    	String sf1 = projectName + " - Brochure";
    	String sf2 = projectName + " - Creative Brief";
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.verifySFsSPHome(sf1, sf2);
    	Thread.sleep(2000);
    	log.info("End: testVerifySFsInfo");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void logoutSP() throws InterruptedException {
    	testSPLogout();
    }
    
    @Test
    @Parameters({"spLoginID", "passwordSP"})
    public void loginSP(String spLoginID, String passwordSP) throws InterruptedException {
    	testSPLogin(spLoginID, passwordSP);
    }
    
    @Test(description="copy project with added SF")
    public void sPClickGlbCreateProj() throws InterruptedException {
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void sPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void sPSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void sPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
   	   "specDescrSP", "fileForProject11"})
     public void sPcreateProjectWizard(String projNameSP, String descriptionNameSP,
   		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
   		     String fileForProject11) throws InterruptedException, AWTException {    
     	log.info("Start: sPcreateProjectWizard");
     	CommonUtils commonUtils = new CommonUtils(driver);
     	commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
     			quantSP3, specDescrSP, fileForProject11);
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
     	brochurePopup.getReviewAndSubmitTab();
     	Thread.sleep(3000);     	
     	brochurePopup.clickCreateBT();
     	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     	Thread.sleep(10000);
     	log.info("End: sPcreateProjectWizard");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void testAddSF() throws InterruptedException {
    	log.info("Start: testAddSF");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testOpenProjectSFTabInWS("Copy of " + projectName, "Specs");
    	Thread.sleep(3000);
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.clickAddSF();
    	Thread.sleep(3000);
    	List<WebElement> imgs = driver.findElement(By.className("spec-simple-cot")).findElements((By.tagName("img")));
		imgs.get(2).click();
		Thread.sleep(3000);	
		BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 
		brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(1000);
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.addSF();
    	Thread.sleep(6000);
    	log.info("End: testAddSF");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySFs() throws InterruptedException {
    	log.info("Start: verifySFs");
    	String sf1 = projectName + " - Brochure";
    	String sf2 = projectName + " - Creative Brief";
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.verifySFsSPHome(sf1, sf2);
    	Thread.sleep(2000);
    	log.info("End: verifySFs");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void copyProj() throws InterruptedException {
    	testCopyProj();
       	testCreateCopyProj();
    }
    
    @Test
    public void gotoReviewTab() throws InterruptedException {
    	testGotoReviewTab();
    }
    
    @Test
    public void testVerifySFs() throws InterruptedException {
    	log.info("Start: testVerifySFs");
    	CreateNewProject createNewProject = new CreateNewProject(driver);    	
    	String sf1 = "Brochure";
    	String sf2 = "Creative Brief";
    	createNewProject.verifyAddedSFs(sf1, sf2);
    	Thread.sleep(1000);
    	log.info("End: testVerifySFs");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="create project")
    public void createProj() throws InterruptedException {
    	log.info("Start: createProj");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 
    	brochurePopup.clickCreateBT();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);
    	log.info("End: createProj");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySFsAfterCreateProj() throws InterruptedException {
    	testVerifySFsInfo();
    }
}
    
   
    
   
    
    
    
    
    
   