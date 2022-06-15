/***********************************************************************
* This class tests the functionality for Create Quote 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
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
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptLog;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NGEcreateQuote extends BaseSeleniumTest {
  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   //String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   static String baseUrl;
   String firstWindow;
   String secondWindow;
   static String parentWindowHandle;
   //static String loginUrl;
   static String spUrl;
   static String projectName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if(domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup"; 
		   spUrl = "https://seleniumquote." + companyName + ".com";
	   } else {
		   baseUrl = "https://demo." + domain + "qa2." + companyName + ".com/service/signup"; 
		   spUrl = "https://seleniumquote." + domain + "." + companyName + ".com";
	   }
   }

   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** ngeCreateQuoteTest *****\n\n");
   }
   
   @Test(description="SP login")
   @Parameters({"spLoginUserID", "passwordSP"})
   public void testSPLogin(String spLoginUserID, String passwordSP) throws InterruptedException {
     	 log.info("Start: testSPLogin");
         Thread.sleep(1500);
         driver.get(spUrl); 
         driver.manage().window().maximize();  
         CommonUtils commonUtils = new CommonUtils(driver);
         commonUtils.loginUser1(spLoginUserID);
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
  	   if(!browser.trim().equals("IE")) {
  	      	  errorIndex = jsErrorIndex;
  	     	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
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
   @Parameters({"clientuser", "clientFieldName"})
   public void testSPInputClient(String clientuser, String clientFieldName) {
	   log.info("Start: testSPInputClient");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   try {
		   Assert.assertTrue(Page.isTextPresent(clientFieldName, driver));
		   commonUtils.testInputClientUser(clientuser);
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
  	    if(!browser.trim().equals("IE")) {	
  	    errorIndex = jsErrorIndex;
  	    Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project ******");	
  	    } 
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
   
    @Test(description="SP go to Selling tab")
    public void testSPGoToSellingTab() throws InterruptedException {
    	log.info("Start: testSPGoToSellingTab");
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.goToSellingTab();
 	    log.info("End: testSPGoToSellingTab");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP Create Quote - step 1")
    public void testSPcreateQuoteBtn() throws InterruptedException {
    	log.info("Start: testSPcreateQuoteBtn");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.createQuoteBtn();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(3000);
    	log.info("End: testSPcreateQuoteBtn");
    	log.info("--------------------------------------------"); 
    }
    /*
    @Test(description="SP Create Quote - step 2")
    @Parameters({"unitcost1", "cost1", "markup", "unitcost2", "cost2", "markup2"})
    public void testSPcreateQuote(String unitcost1, String cost1, String markup, String unitcost2, String cost2, String markup2) throws InterruptedException {
    	log.info("Start: testSPcreateQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.createQuote(unitcost1, cost1, markup, unitcost2, cost2, markup2);
    	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	log.info("End: testSPcreateQuote");
    	log.info("--------------------------------------------"); 
    }
    */
    @Test(description="SP Create Quote - step 2")
    @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
    public void testSPcreateQuote(String unitcost1, String cost1, String markup, String markupfix1, 
    		          String unitcost2, String cost2, String markup2, String markupfix2, String tax, 
    		          String quoteFailure) throws InterruptedException, IOException {
    	log.info("Start: testSPcreateQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.createQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	log.info("End: testSPcreateQuote");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP Create Quote - step 3")
    public void testSPSendQuote() throws InterruptedException {
    	log.info("Start: testSPSendQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.sendQuote();
    	Thread.sleep(5000);
    	log.info("End: testSPSendQuote");
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
    @Parameters({"gmailLoginPW", "email3"})
    public void testLoginToGmail(String gmailLoginPW, String email3) throws InterruptedException {
    	log.info("Start: testLoginToGmail");
   	    driver.get("http://www.gmail.com");
   	    
   	    //testGmailLoginPreCon();  
   	    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
   	    Thread.sleep(500);
   	    /*
   	    WebElement staySignInCookie = null;
  	    try {
  	        staySignInCookie = driver.findElement(By.id("PersistentCookie"));
  	    } catch (Exception e) {
  		    //do nothing
  	    }
  	    if (staySignInCookie != null) {
  		    staySignInCookie.click();
  	        Thread.sleep(800);
  	    }
  	    */
   	    gmailLoginPage.enterEmail(email3);
   	    Thread.sleep(1500);
   	    gmailLoginPage.enterPassword(gmailLoginPW);
   	    Thread.sleep(3500);
  	    gmailLoginPage.signIn();
  	    Thread.sleep(2000);
   	    log.info("End: testLoginToGmail");
   	    log.info("--------------------------------------------");
    }
   
    @Test(description="client check the email")
    public void testClientCheckReviewQuoteEmail() throws InterruptedException {
 	   log.info("Start: testClientCheckReviewQuoteEmail");
 	   Thread.sleep(5000);
 	   List<WebElement> emails = driver.findElements(By.tagName("span"));
 	   for (WebElement email : emails) {
 	 	  if (email != null) {
 	 		  String emailTitle = email.getText();
 	 		 if (emailTitle.indexOf("New Quote received") >= 0) {
 	 			  email.click();
 	 			  break;
 	 		  }
 	 	  }
 	   }
 	   Thread.sleep(2500); 
 	   WebElement acceptInv = driver.findElement(By.linkText("Review Quote"));
  	   if(!acceptInv.isDisplayed()) {
  		   throw new IllegalStateException("The accept invitation link is missing!");
  	   } else {
  		   acceptInv.click();
  	   } 	   
 	   Thread.sleep(2500);  
  	   log.info("End: testClientCheckReviewQuoteEmail");
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
 		   Assert.assertTrue(Page.isTextPresent(projectName, driver));
 	   } catch(Throwable e) {
 	   	   System.out.println("The project created by sp does Not show on client site!\n");
 	   	   log.info("The project created by sp does Not show on client site!");
 	   }
 	   log.info("End: testClientVerifyProj");
 	   log.info("--------------------------------------------");
     }
    
    @Test(description="client review quote")
    public void testClientQuoteAwardOrder() throws InterruptedException {
    	log.info("Start: testClientQuoteAwardOrder");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.clickQuoteAwardOrder();
    	Thread.sleep(5000);
    	log.info("End: testClientQuoteAwardOrder");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="client confirm quote order")
    public void testClientConfirmQuoteOrder() throws InterruptedException {
    	log.info("Start: testClientConfirmQuoteOrder");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.confirmQuoteOrder();
    	Thread.sleep(5000);
    	log.info("End: testClientConfirmQuoteOrder");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="client logout")
    public void testClientLogout() throws InterruptedException {
    	log.info("Start: testClientLogout");
    	Thread.sleep(1000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
    	log.info("End: testClientLogout");
  	    log.info("--------------------------------------------");
    }
    
    /*
    @Test(description="SP Check award order notification")
    @Parameters({"gmailLoginPW"})
    public void testSPCheckNotification(String gmailLoginPW) throws InterruptedException {
    	testLoginToGmail(gmailLoginPW);
    }
    */
    
    @Test(description="Check SP award Quote Email")
    public void testSPawardQuoteEmail() throws InterruptedException {
    	log.info("Start: testSPawardQuoteEmail");
    	Thread.sleep(3000);
 	    driver.close(); 
 	    Thread.sleep(2000);
 	    driver.switchTo().window(parentWindowHandle); 
 	    Thread.sleep(1000);
 	    driver.get("https://mail.google.com/mail/u/0/#inbox");
 	    Thread.sleep(3000);
  	    List<WebElement> emails = driver.findElements(By.tagName("span"));
  	    for (WebElement email : emails) {
  	 	    if (email != null) {
  	 		    String emailTitle = email.getText();
  	 		    if (emailTitle.indexOf("has awarded order Order for Quote for Seleniu") >= 0) {
  	 			    email.click();
  	 			    break;
  	 		    }
  	 	    }
  	     }
  	     Thread.sleep(2000); 
  	     WebElement acceptInv = driver.findElement(By.linkText("Review Order and Update Status"));
   	     if(!acceptInv.isDisplayed()) {
   		     throw new IllegalStateException("The accept invitation link is missing!");
   	     } else {
   		     acceptInv.click();
   	     } 	   
  	     Thread.sleep(2500);  
   	     log.info("End: testSPawardQuoteEmail");
   	     log.info("--------------------------------------------");
    }
    
    @Test(description="SP login")
    public void testSPlogin() throws InterruptedException {
    	log.info("Start: testSPlogin");	
  	    parentWindowHandle = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
     	for (String window : windows) {
     	     if (!window.equals(parentWindowHandle)) {
     			  driver.switchTo().window(window);
     			  break;
     	     }
     	}
  	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.loginUser1();
  	    Thread.sleep(3500);
  	    log.info("End: testSPlogin");
  	    log.info("--------------------------------------------");
    }
    
    /* 7/24/14 comment out, will come back
    @Test(description="SP verify quote status")
    public void testSPverifyStatus() throws InterruptedException {
    	log.info("Start: testSPverifyStatus");
    	SPHomePage sPHomePage = new SPHomePage(driver);
   	    Thread.sleep(500);
   	    sPHomePage.checkQuoteStatus();
    	log.info("End: testSPverifyStatus");
  	    log.info("--------------------------------------------");
    }
    */
    @Test(description="SP request estimate")
    public void testSPRequestEstimate() throws InterruptedException {
 	   log.info("End: testSPRequestEstimate");
 	   Thread.sleep(2500);
 	   driver.findElement(By.className("procurement-action")).click();
 	   Thread.sleep(2500);
 	   log.info("End: testSPRequestEstimate");
 	   log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP adding a supplier")
    @Parameters({"supplierName2"})
    public void testSPAddSupplier(String supplierName2)
    		     throws InterruptedException {
  	   log.info("Start: testSPAddSupplier"); 
	   InvitePage invitePage = new InvitePage(driver);
 	   invitePage.inputSupplier(supplierName2);
	   Thread.sleep(2000); 
  	   log.info("End: testSPAddSupplier");
  	   log.info("--------------------------------------------"); 
    }    
    
    @Test(description="Existing SP adding estimate due date")
    public void testSPAddEstimateDueDate() throws InterruptedException {
    	log.info("Start: testSPAddEstimateDueDate");
    	SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);  
  	    supplierEstimatePopup.callCalendar();	               
  	    Thread.sleep(2000);  	     	   
    	log.info("End: testSPAddEstimateDueDate");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP request estimates button")
    public void testSPRequestEstimatesBtn() throws InterruptedException {
    	log.info("Start: testSPRequestEstimatesBtn");
    	SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver); 
    	supplierEstimatePopup.callRequestEstimatesBtn();
  	    Thread.sleep(6000); 
    	log.info("End: testSPRequestEstimatesBtn");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP logout")
    public void testSPlogout() throws InterruptedException {
    	testSPLogout();
    }
    
    @Test(description="Existing supplier login gmail")
    @Parameters({"gmailLoginPW", "validSPEmail"})
    public void testSupplierLoginToGmail(String gmailLoginPW, String validSPEmail) throws InterruptedException  {
  	 	  log.info("Start: testSupplierLoginToGmail");  
  	 	  Thread.sleep(3000);  	 	  
 	      driver.close(); 
 	      Thread.sleep(2000);
 	      driver.switchTo().window(parentWindowHandle); 
 	      Thread.sleep(1000);
  	 	  driver.get("http://www.gmail.com");
  	 	  Thread.sleep(1500);
  	 	  driver.get("https://mail.google.com/mail/?logout");
	 	  Thread.sleep(1500);
  	 	  CommonUtils commonUtils = new CommonUtils(driver);
  	 	  commonUtils.testGmailLoginPreCon();
  	 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
  	 	  Thread.sleep(1000);
  	 	  gmailLoginPage.enterEmail(validSPEmail);
  	 	  Thread.sleep(1000);
  	 	  gmailLoginPage.enterPassword(gmailLoginPW);
  	 	  Thread.sleep(1000);
  	 	  WebElement staySignInCookie = null;
  	 	  try {
  	 	      staySignInCookie = driver.findElement(By.id("PersistentCookie"));
  	 	  } catch (Exception e) {
  	 	  }
  	 	  if (staySignInCookie != null) {
  	 		  staySignInCookie.click();
  	 	      Thread.sleep(1000);
  	 	  }
  	 	  gmailLoginPage.signIn();
  	 	  Thread.sleep(1000);
  	 	  
  	 	  WebElement skipLink = null;
  		  try {
  			  skipLink = driver.findElement(By.id("send-code-cancel-button"));
  		  } catch (Exception e) {
  		  }
  		  if (skipLink != null) {
  			  skipLink.click();
  			  Thread.sleep(1000);
  		  }
  	 	  log.info("End: testSupplierLoginToGmail");
  	 	  log.info("--------------------------------------------");
    }
    
    @Test(description="Existing supplier check gmail")
    public void testSupplierCheckGmail() throws InterruptedException {
  	   log.info("Start: testSupplierCheckGmail");
  	 	  Thread.sleep(5000);
  	 	  List<WebElement> emails = driver.findElements(By.tagName("span"));
  	 	  for (WebElement email : emails) {
  	 		  if (email != null) {
  	 			  String emailTitle = email.getText();
  	 			 if (emailTitle.indexOf("has requested estimate for project") >= 0) {
  	 				  email.click();
  	 				  break;
  	 			  }
  	 		  }
  	 	  }
  	 	  Thread.sleep(2000);
  	 	  driver.findElement(By.linkText("Send Estimate")).click(); 
  	 	  Thread.sleep(2000);
  	 	  log.info("End: testSupplierCheckGmail");
  	 	  log.info("--------------------------------------------");
    }    
    
    @Test(description="Existing supplier login")
    @Parameters({"passwd"})
    public void testExistingSupplierLogin(String passwd) throws InterruptedException {
  	    log.info("Start: testExistingSupplierLogin");
  	    //parentWindowHandle = driver.getWindowHandle();
  	    Set<String> windows = driver.getWindowHandles();
  	    for (String window : windows) {
  	        if (!window.equals(parentWindowHandle)) {
  	 	        driver.switchTo().window(window);
  	 	  	    break;
  	 	    }
  	    }  	   
  	    SPLoginPage loginPage = new SPLoginPage(driver);
  	    Thread.sleep(1000);
  	    loginPage.spLoginUser1(passwd);
  	    log.info("End: testExistingSupplierLogin");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="Exsiting supplier create estimate")
    @Parameters({"supplier2Price1", "supplier2Price2", "supplier2Price3", "supplier2Price4", "shipping2EstPrice1",
                 "shipping2EstPrice2", "shipping2EstPrice3", "shipping2EstPrice4", "taxPrice2"})
    public void testExistingSupplierCreateEstimate(String supplier2Price1, String supplier2Price2, String supplier2Price3, 
    		       String supplier2Price4, String shipping2EstPrice1, String shipping2EstPrice2, String shipping2EstPrice3, 
    		       String shipping2EstPrice4, String taxPrice2) throws InterruptedException {
    	log.info("Start: testExistingSupplierCreateEstimate");
    	SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
    	EstimateRFEPage estimateRFEPage = new EstimateRFEPage(driver);
    	Thread.sleep(1500);
    	estimateRFEPage.clickCreateEstimateBtn1();
    	Thread.sleep(3000);
    	List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
    	List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
    	prices.get(0).clear();
    	prices.get(0).sendKeys(supplier2Price1);
    	Thread.sleep(2000);
    	shippingPrices.get(0).clear();
    	shippingPrices.get(0).sendKeys(shipping2EstPrice1);
    	Thread.sleep(2000);  	 	  
    	prices.get(1).clear();
    	prices.get(1).sendKeys(supplier2Price2);
    	Thread.sleep(2000);
    	shippingPrices.get(1).clear();
    	shippingPrices.get(1).sendKeys(shipping2EstPrice2);
    	Thread.sleep(2000);  		  
    	prices.get(2).clear();
    	prices.get(2).sendKeys(supplier2Price3);
    	Thread.sleep(2000);
    	shippingPrices.get(2).clear();
    	shippingPrices.get(2).sendKeys(shipping2EstPrice3);
    	Thread.sleep(2000);
    	//estimateRFEPage.inputEstimatedTaxField(taxPrice2);
    	//Thread.sleep(2000);  	 	  
    	estimateRFEPage.clickSendEstimateBtn();
    	Thread.sleep(3000);
    	String msg = driver.findElement(By.id("confirmPopupWindow")).getText();
    	  if((msg == null) || (msg.isEmpty())) {
    		  supplierMainMenuPage.verifyEstSent("Estimate Sent");
  	    } else {
  		     Assert.fail("Unexpected popup");
  	    }
    	     Thread.sleep(2000);
    	log.info("End: testExistingSupplierCreateEstimate");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Exsiting supplier logout")
    public void testExistingSupplierLogout() throws InterruptedException {
    	log.info("Start: testExistingSupplierLogout");
    	Thread.sleep(3000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.logoutUser();
    	Thread.sleep(3000);  	  
    	log.info("End: testExistingSupplierLogout");
    	log.info("--------------------------------------------");
    }    
    
    @Test(description="Go back to the gmail window again")
    public void test2ndGoBackToGmailPage() throws InterruptedException {
    	log.info("Start: test2ndGoBackToGmailPage");
    	Thread.sleep(3000);
    	driver.close();  
    	Thread.sleep(2000);
    	driver.switchTo().window(parentWindowHandle); 
    	Thread.sleep(2000);
    	driver.get("https://mail.google.com/mail/?logout"); 
    	Thread.sleep(2000);
    	log.info("End: test2ndGoBackToGmailPage");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="sp login email account")    
    @Parameters({"gmailLoginPW", "supplierName1"})
    public void testSPLoginGmail(String gmailLoginPW, String supplierName1) throws InterruptedException {
    	log.info("Start: testSPLoginGmail");
    	Thread.sleep(1000);
    	driver.get("http://www.gmail.com");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testGmailLoginPreCon();    	  
    	GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
    	Thread.sleep(10000);
    	gmailLoginPage.enterEmail(supplierName1+"@noosh.com");
    	Thread.sleep(1000);
    	gmailLoginPage.enterPassword(gmailLoginPW);
    	Thread.sleep(1000);
    	gmailLoginPage.signIn();
    	Thread.sleep(1000);
    	log.info("End: testSPLoginGmail");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="SP Check Gmail")
    public void testSPCheckGmail() throws InterruptedException {
  	    log.info("Start: testSPCheckGmail");
  	    Thread.sleep(5000);
  	    List<WebElement> emails = driver.findElements(By.tagName("span"));
  	    for (WebElement email : emails) {
  		    if (email != null) {
  			    String emailTitle = email.getText();
  			    if (emailTitle.indexOf("Estimates submitted by ") >= 0) {
  				    email.click();
  				    break;
  			    }
  		    }
  	    }
  	    Thread.sleep(3000);
	    CommonUtils commonUtils = new CommonUtils(driver);
        commonUtils.reviewEst();
  	    Thread.sleep(3000);  	 
  	    log.info("End: testSPCheckGmail");
  	    log.info("--------------------------------------------");
    }
        
    @Test(description="sp login")
    public void testSPLoginAgain() throws InterruptedException {
   	    log.info("Start: testSPLogin");
   	    parentWindowHandle = driver.getWindowHandle();
   	    Set<String> windows = driver.getWindowHandles();
   	    for(String window : windows) {
   	    	if(!window.equals(parentWindowHandle)) {
   	    		driver.switchTo().window(window);
   	    		break;
   	    	}
   	    }
   	    CommonUtils commonUtils = new CommonUtils(driver);
        commonUtils.loginUser1();
        Thread.sleep(3000);
   	    log.info("End: testSPLogin");
   	    log.info("--------------------------------------------");
     }   
    
    @Test(description="sp award order")
    @Parameters({""})
    public void testAwardOrderPage() throws InterruptedException {
  	    log.info("Start: testAwardOrderPage");
  	    SPAwardOrderPage spAwardOrderPage = new SPAwardOrderPage(driver);
  	    spAwardOrderPage.selectAEstimate();
  	    spAwardOrderPage.clickAwardBtn();
  	    log.info("End: testAwardOrderPage");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="sp confirm order")
    public void testSPConfirmOrderPage() throws InterruptedException {
  	    log.info("Start: testConfirmOrderPage");
  	    SPConfirmOrderPage spConfirmOrderPage = new SPConfirmOrderPage(driver);
  	    spConfirmOrderPage.clickConfirmOrderBtn();
  	    Thread.sleep(1500);
  	    log.info("End: testConfirmOrderPage");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="sp log out")
    public void testSPLogoutAgain() throws InterruptedException {
  	    log.info("Start: testSPLogoutAgain");
  	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.logoutUser(); 
  	    log.info("End: testSPLogoutAgain");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="Go back to the gmail window again")
    public void testSPGoBackToGmailPage() throws InterruptedException {
  	    log.info("Start: testSPGoBackToGmailPage");
  	    Thread.sleep(3000);
  	    driver.close();  
  	    Thread.sleep(2000);
  	    driver.switchTo().window(parentWindowHandle); 
  	    Thread.sleep(2000);
  	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.logoutGmailAccount();
  	    Thread.sleep(2000);
  	    log.info("End: testSPGoBackToGmailPage");
  	    log.info("--------------------------------------------");
    }    
    
    @Test(description="Supplier login gmail")
    @Parameters({"gmailLoginPW", "validSPEmail"})
    public void testSupplierLoginGmail(String gmailLoginPW, String validSPEmail) throws InterruptedException {
   	    log.info("Start: testSupplierLoginGmail");
   	    Thread.sleep(3000);
   	    driver.get("http://www.gmail.com");
   	    CommonUtils commonUtils = new CommonUtils(driver);
 	    commonUtils.testGmailLoginPreCon();
   	    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
   	    Thread.sleep(3000);
   	    gmailLoginPage.enterEmail(validSPEmail);
   	    Thread.sleep(1000);
   	    gmailLoginPage.enterPassword(gmailLoginPW);
   	    Thread.sleep(1000);
   	    gmailLoginPage.signIn();
   	    Thread.sleep(1000);
   	    log.info("End: testSupplierLoginGmail");
   	    log.info("--------------------------------------------");
    }

    @Test(description="Supplier checks confirmation")
    public void testSupplierConfirmation() throws InterruptedException {
    	log.info("Start: testSupplierConfirmation");
   	    Thread.sleep(5000);
        List<WebElement> emails = driver.findElements(By.tagName("span"));
   	    for (WebElement email : emails) {
   	        if (email != null) {
   		        String emailTitle = email.getText();
   			    if (emailTitle.indexOf("or Selenium") >= 0) {
   			        email.click();
   			        break;
   			   }
   		   }
   	   }
       Thread.sleep(5000);
       log.info("End: testSupplierConfirmation");
       log.info("--------------------------------------------");
   }

   @Test(description="Supplier logout email")
   @Parameters({"gmailLoginPW"})
   public void testSupplierLogoutGmail(String gmailLoginPW) throws InterruptedException {
   	   log.info("Start: testSupplierLogoutGmail");
   	   Thread.sleep(2000);
   	   driver.get("https://mail.google.com/mail/?logout"); 
   	   Thread.sleep(2000);
   	   log.info("End: testSupplierLogoutGmail");
   	   log.info("--------------------------------------------");
    }
   
   @Test
   @Parameters({"spLoginUserID", "passwordSP"})
   public void loginSPAgain(String spLoginUserID, String passwordSP) throws InterruptedException {
	   log.info("Start: loginSPAgain");
       Thread.sleep(1500);
       driver.get(spUrl); 
       CommonUtils commonUtils = new CommonUtils(driver);
       commonUtils.loginUser1(spLoginUserID);
       spSiteLoginPage = driver.getCurrentUrl();  	  
       log.info("End: loginSPAgain");
  	   log.info("--------------------------------------------");
   }
   
   @Test
   public void openProj() throws InterruptedException {
	   log.info("Start: openProj");
	   CommonUtils commonUtils = new CommonUtils(driver);
       commonUtils.testOpenProjectInWS(projectName);
       Thread.sleep(2000);
       log.info("End: openProj");
  	   log.info("--------------------------------------------");
   }
   
   @Test
   public void goToBuying() throws InterruptedException {
	   log.info("Start: goToBuying");
	   CommonUtils commonUtils = new CommonUtils(driver);
       //commonUtils.testOpenProjectInWS(projectName);
       //Thread.sleep(2000);
       SPHomePage spHomePage = new SPHomePage(driver);
       spHomePage.goToBuyingTab(); 
       commonUtils.showBuyingDetail();
       Thread.sleep(2500);
	   log.info("End: goToBuying");
  	   log.info("--------------------------------------------");
   }
   
   @Test
   public void cancelOrder() throws InterruptedException {
	   log.info("Start: cancelOrder");
	   SPHomePage sPHomePage = new SPHomePage(driver);
   	   sPHomePage.verifyRecallRFE();
   	   Thread.sleep(2500);
	   log.info("End: cancelOrder");
  	   log.info("--------------------------------------------");
   }
   
   @Test
   public void testCancelOrderPopup() throws InterruptedException {
	   log.info("Start: testCancelOrderPopup");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.cancelOrderpopup();
	   Thread.sleep(2500);     	
	   log.info("End: testCancelOrderPopup");
	   log.info("--------------------------------------------"); 
   }
   
   @Test
   public void testVerifyCancelOrderStatus() throws InterruptedException, IOException {
	   log.info("Start: testVerifyCancelOrderStatus");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.showBuyingDetail();
	   Thread.sleep(3000);
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.verifyBuyingCancellStatus("Order canceled", "cancelBuyingOrderstatus");
	   Thread.sleep(2500);
	   log.info("End: testVerifyCancelOrderStatus");
	   log.info("--------------------------------------------"); 
   } 
   
   @Test
   public void logoutSPafterCancelOrder() throws InterruptedException {
  	   log.info("Start: logoutSPafterCancelOrder");
  	   CommonUtils commonUtils = new CommonUtils(driver);
  	   commonUtils.spLogout();
  	   Thread.sleep(5000);
  	   log.info("End: logoutSPafterCancelOrder");
  	   log.info("--------------------------------------------"); 
   }
   
   @Test
   @Parameters({"gmailLoginPW", "validSPEmail"})
   public void checkSupEmail(String gmailLoginPW, String validSPEmail) throws InterruptedException {
	   log.info("Start: checkSupEmail");  
	   driver.get("http://www.gmail.com");
	  
	   testGmailLoginPreCon();  
	   GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	   Thread.sleep(500);
	   /*
	   WebElement staySignInCookie = null;
	   try {
		   staySignInCookie = driver.findElement(By.id("PersistentCookie"));
	   } catch (Exception e) {
		   //do nothing
	   }
	   if (staySignInCookie != null) {
		   staySignInCookie.click();
		   Thread.sleep(800);
	   }
	   */
	   gmailLoginPage.enterEmail(validSPEmail);
	   //gmailLoginPage.enterEmail("seleniumtest54321@noosh.com");
	   Thread.sleep(1500);
	   gmailLoginPage.enterPassword(gmailLoginPW);
	   Thread.sleep(3500);
	   gmailLoginPage.signIn();
	   Thread.sleep(2000);
	   log.info("End: checkSupEmail");
	   log.info("--------------------------------------------");	   
   }
   
   @Test
   public void checkSupNotification() throws InterruptedException {
	   log.info("Start: checkSupNotification");
	   CommonUtils commonUtils = new CommonUtils(driver);
  	   commonUtils.reviewOrder();
	   log.info("End: checkSupNotification");
	   log.info("--------------------------------------------");
    } 
   
   @Test(description="Existing supplier login")
   @Parameters({"passwd"})
   public void testSupLogin(String passwd) throws InterruptedException {
	   testExistingSupplierLogin(passwd);
   }
   
   @Test
   public void openSupProj() throws InterruptedException {
	   log.info("Start: openSupProj");
	   SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
	   supplierMainMenuPage.openProj();
	   log.info("End: openSupProj");
	   log.info("--------------------------------------------");
   }
   
   @Test
   public void verifySupStatus() throws InterruptedException {
	   log.info("Start: verifySupStatus");
	   SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
	   supplierMainMenuPage.checkStatus();
	   log.info("End: verifySupStatus");
	   log.info("--------------------------------------------");
   }
}


    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
