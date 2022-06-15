/***
* This class tests the functionality for Selling with Firefox browser
* @author Jennifer Wu
***/
package com.noosh.nooshentry.automation.demoSQANoosh;

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
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.EstimateRFEPage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPAwardOrderPage;
import com.noosh.nooshentry.automation.buyerSite.SPConfirmOrderPage;
import com.noosh.nooshentry.automation.buyerSite.SPLoginPage;
import com.noosh.nooshentry.automation.buyerSite.SPReviewEstimatePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
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

public class TestSPSelling extends BaseSeleniumTest
{   
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   static String baseUrl;
   String firstWindow;
   String secondWindow;
   static String parentWindowHandle;
   static String loginUrl;
  // static String clientUserEmail;
   /*
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException
   {	
	   if (domain.trim().equals("qa2")) {
		   baseUrl = "https://demo.qa2.noosh.com/service/signup";
       } else if (domain.trim().equals("sqa")) {		  
		   baseUrl = "https://demo.sqa.noosh.com/service/signup";
	   } else if (domain.trim().equals("scd")) {
		   baseUrl = "https://demo.scd.noosh.com/service/signup";
	   } else if (domain.trim().equals("sdm")) {
		   baseUrl = "https://demo.sdm.noosh.com/service/signup";
	   } 
   }  */
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
   	
	   if (domain.trim().equals("qa2")) {
		   baseUrl = "https://demo.qa2.noosh.com/service/signup";
		   loginUrl = "https://selenium263945.qa2.noosh.com/service/login";  
       } else if (domain.trim().equals("sqa")) {		  
		   baseUrl = "https://demo.sqa.noosh.com/service/signup";
		   loginUrl = "https://selenium263945.sqa.noosh.com/service/login";  
	   } else if (domain.trim().equals("scd")) {
		   baseUrl = "https://demo.scd.noosh.com/service/signup";
		   loginUrl = "https://selenium263945.scd.noosh.com/service/login";  
	   } else if (domain.trim().equals("sdm")) {
		   baseUrl = "https://demo.sdm.noosh.com/service/signup";
		   loginUrl = "https://selenium263945.sdm.noosh.com/service/login"; 
	   } 
   }
   
   @Test(description="login - for debug test only")
   public void loginTest() throws InterruptedException {
	   driver.get("http://junit.scd.noosh.com/service/login");
	   driver.manage().window().maximize();
	   WebElement username = driver.findElement(By.id("username"));
	   username.clear();
	   username.sendKeys("Jennifer+sp5@noosh.com");
	   Thread.sleep(1000);
	   WebElement passwd = driver.findElement(By.id("password"));
	   passwd.clear();
	   passwd.sendKeys("17password");
	   Thread.sleep(1000);
	   WebElement loginBtn = driver.findElement(By.id("submitbutton"));
	   loginBtn.click();
	   Thread.sleep(3000);
   }
   
   @Test(description="SP onboarding - SP sign up")
   @Parameters({"fullNameSP", "passwordSP", "phoneNumberSP", "companyName"}) 
     public void testRegisterNewSPSite(String fullNameSP, String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException {		   
	   log.info("Start: testRegisterNewSPSite");
	   companyName = companyName + unicID;
	   System.out.println("companyName="+companyName);
	   BaseTestCases.testRegisterNewSPSite(baseUrl, fullNameSP, passwordSP, phoneNumberSP, companyName);  
	   //System.out.println("sp baseURL= " + baseUrl);
	   Thread.sleep(1200); 
	   /*
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP sign up ******");	
	   } */
	   log.info("End: testRegisterNewSPSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Let's Begin")
   public void testLetsBegin() throws InterruptedException
   {
	   log.info("Start: testLetsBegin");
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   spUser = driver.getCurrentUrl();
	   int index = spUser.indexOf("/service");
	   spUser = spUser.substring(0, index);
	   //System.out.println("spUser"+spUser);
	   
	   Thread.sleep(1500);
	   registerSPSitePage.clickLetsBegin();
	   Thread.sleep(1500);
	  
	   /*
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Let's Begin  ******");
	   } */
	   log.info("End: testLetsBegin");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Selling Site")
   public void testCreateSellingSiteOB()
   {
 	  log.info("Start: testCreateBuyerSiteOB");
 	  SitesListTab sitesListTab = new SitesListTab(driver); 
 	  sitesListTab.getSellingSitesList();
 	  /*
 	  if(!browser.trim().equals("IE")) {
 		   errorIndex = jsErrorIndex;
 		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Buying Site******");	
 	  } */
 	  log.info("End: testCreateBuyerSiteOB");
 	  log.info("--------------------------------------------");
 	  
   }
   
   @Test(description="SP Create Project - step 1")
  // @Parameters({"validDemoUserEmail", "productName1"})
   @Parameters({"productName1"})
   public void testSPcreateProject(String productName1) throws InterruptedException
   {
	   log.info("Start: testSPcreateProject");
	   productName1 = productName1 + unicID +"2";
	   
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   
	   driver.findElement(By.id("goback")).click();  //10/29
	   Thread.sleep(1000);
	   testCreateSellingSiteOB();     //10/29
	   
	   //newProject.clicklCreateProjectBT();	    //10/2
	   //newProject.enterValidClientEmail(validDemoUserEmail);  //10/3
	   newProject.inputProjectName(productName1);    //10/3
	   Thread.sleep(2000);
	   //newProject.clickFindBT();                //10/3
	   //newProject.selectCustomer();	          //10/3 
	   //Thread.sleep(2000);
	   
	   //newProject.selectCertainSite();
	   WebElement startElement = driver.findElement(By.id("createProjectProductUl"));
	   List<WebElement> img = startElement.findElements(By.tagName("img"));
	   img.get(1).click();
	   Thread.sleep(2000);
	   
	   newProject.clickCreateAndEstimateProjectBT();
	   Thread.sleep(2000);
	   /*
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project ******");	
	   } */
	   log.info("End: testSPcreateProject");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP Create Project - step 2")
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
	   "specDescrSP", "fileForProject1"})
   public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
		   String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
		   String fileForProject1) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPcreateProjectProjectWizard");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   	    
	   //brochurePopup.setProjectName(projNameSP); 
	   Thread.sleep(2000);   	    
	   brochurePopup.callCalendar();
 	   brochurePopup.setNextMonth();
 	   brochurePopup.setComplationDate(); 
	   Thread.sleep(2000);
	   brochurePopup.getNextTab();	 
	   Thread.sleep(2000);
	   //brochurePopup.putDescriptionName(descriptionNameSP); 10/3
	   //brochurePopup.putSKU(sku);
	   //brochurePopup.putRefNumber(referenceNumber);
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
	    
	   brochurePopup.getFilesTab();
	   page.uploadFileModalWindow(fileForProject1);
	   Thread.sleep(2000);
	   //WebElement uploadBtn = driver.findElement(By.id("upload-drop-button-5000"));
	   //uploadBtn.click();
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	   
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(3000);
       //brochurePopup.clickAddProductsBT();	  //10/3  
	   
	   testAddSmartFormPage(quantSP, quantSP2);   //10/21
	   Thread.sleep(1000);
	  // brochurePopup.getReviewAndSubmitTab();  //10/21
	  // WebElement reviewAndSubmit = driver.findElement(By.linkText("Review and Submit"));
	  // reviewAndSubmit.click();
	  // Thread.sleep(5000);
	   brochurePopup.clickCreateAndEstimateProjectBT();    //10/3
	   Thread.sleep(3500);         //10/3
       /*
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project - step 2  ******");	 
	   }*/
	   log.info("End: testSPcreateProjectProjectWizard");
	   log.info("--------------------------------------------"); 
   }
   
   
   @Test(description="add smart form")
   @Parameters({"quantSP", "quantSP2"})
   public void testAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
	   log.info("Start: testAddSmartFormPage");
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
	   Thread.sleep(2000);
	   
	   addSmartFormPage.clickSmartForm();
	   addSmartFormPage.inputOneQuantity(quantSP);
	  
	   Thread.sleep(2000);
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(3000);
 
	   /*
	   if(!browser.trim().equals("IE")) {
	      errorIndex = jsErrorIndex;
	      Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Add Smart Form  ******");	
	   }*/
	   log.info("End: testAddSmartFormPage");
	   log.info("--------------------------------------------"); 
   }
   
   
   @Test(description="SP - test message")
   @Parameters({"SPNewMessage", "clientEmail"})
   public void testMessageTeam(String SPNewMessage, String clientEmail) throws InterruptedException {
	   log.info("Start: testMessageTeam");
	   clientEmail = clientEmail + "+" + unicID + "@gmail.com";
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   Thread.sleep(2000);	    
	   brochurePopup.getMessageTab();
	   Thread.sleep(2000);
	   WebElement textArea = driver.findElement(By.name("content"));
	   textArea.clear();
	   textArea.sendKeys(SPNewMessage);
	   Thread.sleep(1000);
	   //WebElement msgSendBtn = driver.findElement(By.xpath("//div[text()='Post Message']"));
	   //WebElement msgSendBtn = driver.findElement(By.xpath("//div[@text='Post Message']"));
	   //WebElement msgSendBtn = driver.findElement(By.name("Post Message"));
	   //msgBtn.submit();
	   WebElement msgBtn = driver.findElement(By.className("post-message-button"));
	   msgBtn.findElement(By.className("sp-simple-button")).click();
	   Thread.sleep(1000);   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(SPNewMessage, driver));	        
	   } catch(Throwable e) {
				  System.out.println("13.3 Buyer message " + SPNewMessage + " does not displayed");
				  log.info("SP message " + SPNewMessage + " does not displayed");
			   } 
	   Thread.sleep(2000);
	   brochurePopup.getTeamTab();
	   Thread.sleep(2000);
	   WebElement inviteBtn = driver.findElement(By.className("manage-client-users"));  
	  //WebElement inviteBtn = driver.findElement(By.className("manage-coworkers"));  
	   inviteBtn.findElement(By.className("glb-userManage-invitation")).click();
	   Thread.sleep(1000);
	   
	  // WebElement text = driver.findElement(By.name("emailArea"));
      // WebElement text = driver.findElement(By.xpath("/html/body/div[6]/div/div[4]/div[2]/div[2]/ul/li/div[3]/div/ul/div[2]/div/div[7]/div/div/div[4]/div[2]/div[2]/div[2]/div[3]/div/textarea"));
	   //List<WebElement> text = driver.findElements(By.name("emailArea"));
	   //text.get(1).clear();
	   //text.get(1).sendKeys(clientEmail);
	   WebElement text = driver.findElement(By.name("emailArea"));
	   text.clear();
	   text.sendKeys(clientEmail);
	   Thread.sleep(2000);
	  //List<WebElement> sendBtn = driver.findElements(By.className("invite-people-button"));
	   //sendBtn.get(1).click();
	   driver.findElement(By.className("invite-people-button")).click();
	   Thread.sleep(2000);

	   log.info("Start: testMessageTeam");
	   log.info("--------------------------------------------"); 
   }   
   
   @Test(description="SP logout")
   public void testLogout() throws InterruptedException {
 	  log.info("Start: testLogout");
 	  Thread.sleep(8000);
 	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
 	  Thread.sleep(2000);
 	  supplierMainMenuPage.clickLogout();
	  Thread.sleep(2000);
 	  /*
	  if(!browser.trim().equals("IE")) {
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
	  } */
 	  log.info("End: testLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Client login gmail")
   @Parameters({"gmailLoginPW"})
   public void testClientLoginToGmail(String gmailLoginPW) throws InterruptedException  {
	 	  log.info("Start: testClientLoginToGmail");
	 	  
	 	  Thread.sleep(2000);
	 	  driver.get("http://www.gmail.com");
	 	  Thread.sleep(2000);
	 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	 	  Thread.sleep(3000);
	 	  gmailLoginPage.enterEmail("seleniumtest54321@gmail.com");
	 	  Thread.sleep(1000);
	 	  gmailLoginPage.enterPassword(gmailLoginPW);
	 	  Thread.sleep(1000);
	 	  gmailLoginPage.signIn();
	 	  Thread.sleep(1000);
	 	 /*
	 	  if(!browser.trim().equals("IE")) {
			   errorIndex = jsErrorIndex;
			   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Login Gmail  ******");	
		  }
		  */
	 	  log.info("End: testClientLoginToGmail");
	 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Client check gmail")
   public void testClientCheckGmail() throws InterruptedException {
	   log.info("Start: testClientCheckGmail");
	 	  Thread.sleep(5000);
	 	  List<WebElement> emails = driver.findElements(By.tagName("span"));
	 	  for (WebElement email : emails) {
	 		  if (email != null) {
	 			  String emailTitle = email.getText();
	 			 //if (emailTitle.indexOf("Submitted Project Selenium") >= 0) {
	 			 if (emailTitle.indexOf("Selenium") >= 0) {
	 				  email.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(3000);
	 	  List<WebElement> links = driver.findElements(By.tagName("a"));
	 	  Thread.sleep(2000);
	 	  for (WebElement link : links) {
	 		  if (link != null) {
	 			  String linkHref = link.getAttribute("href");
	 			 //if (linkHref.endsWith(".noosh.com/widget_company/main")) {
	 			 if (linkHref.endsWith("@gmail.com")) {
	 				  link.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(2000);
	 	 /*
	 	  if(!browser.trim().equals("IE")) {
			   errorIndex = jsErrorIndex;
			   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Check Gmail  ******");	
		  }
		  */
	 	  log.info("End: testClientCheckGmail");
	 	  log.info("--------------------------------------------");
   }
   
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   @Test(description="SP Request Estimate from Suppliers")
   @Parameters({"supplierName1", "supplierName2"})
   public void testRequestEstimateSupplier(String supplierName1, String supplierName2)  throws InterruptedException
   {
	   log.info("Start: testRequestEstimateSupplier");
	   supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@gmail.com";
	   
	   Thread.sleep(2000);
	   SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);
	   
	   Thread.sleep(2000);
	   supplierEstimatePopup.setSupplierName(supplierUser1, supplierName2);
	   Thread.sleep(2000); 
	   WebElement smartFormCheckbox = driver.findElement(By.className("forms-list"));  //10/21
	   smartFormCheckbox.findElement(By.tagName("input")).click();
	   Thread.sleep(1000); 
	   
	   //WebElement smartFormCheckboxes = driver.findElement(By.xpath("//html/body/div[12]/div[2]/div[2]/ul/li/div/div/div/input"));  //10/21
	   //smartFormCheckboxes.click();
	   //smartFormCheckbox.getAttribute("input");     //10/21
	   Thread.sleep(500);
	   supplierEstimatePopup.callCalendar();	               
	   Thread.sleep(2000);
	   supplierEstimatePopup.callRequestEstimatesBtn();
	   Thread.sleep(5000);
	   /*
	   if(!browser.trim().equals("IE")) {
	      errorIndex = jsErrorIndex;
	      Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Request Estimate from Suppliers  ******");	  
	   }*/
	   log.info("End: testRequestEstimateSupplier");
	   log.info("--------------------------------------------"); 
   }
   
   /*
   @Test(description="temp case for testing only")
   @Parameters({"loginN", "spPassword"})
   public void testLogin(String loginNL,  String spPassword) throws InterruptedException {
 	  log.info("Start: testSPLogin");
 	  
 	  	  driver.get("https://selenium159908.sqa.noosh.com/service/login");
 	 // driver.manage().window().maximize();
 	  Thread.sleep(3000);
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(emailSP, spPassword);
 	  
 	  if(!browser.trim().equals("IE")) {
 		  errorIndex = jsErrorIndex;
 		  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.1 Log in to Noosh Pro, JS errors for SP site  #######");   
 	  }
 	  log.info("End: testSPLogin");
 	  log.info("--------------------------------------------");
   }   
   */
   
  
   
   @Test(description="New supplier Login to Gmail")
   @Parameters({"gmailLoginPW"})
   public void testLoginToGmail(String gmailLoginPW) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  Thread.sleep(1000);
 	  driver.get("http://www.gmail.com");
 	  Thread.sleep(2000);
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	 Thread.sleep(15000);
 	  gmailLoginPage.enterEmail("seleniumtest12345@gmail.com");
 	 // gmailLoginPage.enterEmail("seleniumtest54321@gmail.com");
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(1000);
 	  gmailLoginPage.signIn();
 	  Thread.sleep(1000);
 	  /*
 	  if(!browser.trim().equals("IE")) {
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Login Gmail ******");	
 	  } */
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New Supplier Check Gmail")
   public void testCheckGmail() throws InterruptedException {
 	  log.info("Start: testCheckGmail");
 	  Thread.sleep(5000);
 	  /*
 	  GmailListPage gmailListPage = new GmailListPage(driver);
 	  Thread.sleep(5000);
 	  gmailListPage.enterSearchPattern("has invited you to join as a Supplier and provide Estimates");
 	  Thread.sleep(1000);
 	  gmailListPage.searchGmail();
 	  Thread.sleep(3000);
 	  */
 	  List<WebElement> emails = driver.findElements(By.tagName("span"));
 	  for (WebElement email : emails) {
 		  if (email != null) {
 			  String emailTitle = email.getText();
 			  //if (emailTitle.indexOf("has invited you to join as a Supplier and provide Estimates") >= 0) {
 			 if (emailTitle.indexOf("has requested estimate for project:") >= 0) {
 				  email.click();
 				  break;
 			  }
 		  }
 	  }
 	  Thread.sleep(3000);
 	  List<WebElement> links = driver.findElements(By.tagName("a"));
 	  Thread.sleep(2000);
 	  for (WebElement link : links) {
 		  if (link != null) {
 			  String linkHref = link.getAttribute("href");
 			  if (linkHref.endsWith("signupType=rfe")) {
 				 // System.out.println("link=" + linkHref);
 				  link.click();
 				  break;
 			  }
 		  }
 	  }
 	  Thread.sleep(2000);
 	  /*
 	  if(!browser.trim().equals("IE")) {
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Check Gmail ******");	
 	  } */
 	  log.info("End: testCheckGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier signup")
   @Parameters({"newSupplierFullName", "newSupplierPasswd", "newSupplierCompanyName"})
   public void testNewSupplierSignup(String newSupplierFullName, String newSupplierPasswd, 
		                           String newSupplierCompanyName) throws InterruptedException { 	 
      log.info("Start: testSupplier1Signup");
      newSupplierCompanyName = newSupplierCompanyName + unicID;
      newSupplierUserCompanyName = newSupplierCompanyName;      //10/15
      newSupplierUserCompanyName = newSupplierUserCompanyName.substring(7);   //10/15
 	  parentWindowHandle = driver.getWindowHandle();
 	  Set<String> windows = driver.getWindowHandles();
 	  for (String window : windows) {
 	      if (!window.equals(parentWindowHandle)) {
 		      driver.switchTo().window(window);
 	  	      break;
 		  }
 	  }
 	  RegisterNewSupplierSitePage registerSupplierSitePage = new RegisterNewSupplierSitePage(driver);
 	  Thread.sleep(1000);
 	  registerSupplierSitePage.setSupplierInfo(newSupplierFullName, newSupplierPasswd, newSupplierCompanyName);
 	  Thread.sleep(2000);
 	  registerSupplierSitePage.clickSignupBT();
 	  Thread.sleep(3000);
 	  //BaseTestCases.testNewSupplierSite(newSupplierFullName, newSupplierPasswd, newSupplierCompanyName);
 	  /*
 	  if(!browser.trim().equals("IE")) {
 		   errorIndex = jsErrorIndex;
 		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Signup  ******");	
 	  } */
 	  log.info("End: testSupplier1Signup");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier login")
   @Parameters({"supplierUserName", "passwd"})
   public void testNewSupplierLogin(String supplierUserName, String passwd) throws InterruptedException {
 	  log.info("Start: testNewSupplierLogin");
 	  SupplierLoginPage supplierLoginPage = new SupplierLoginPage(driver);
 	  supplierLoginPage.loginUser(supplierUserName, passwd);
 	  Thread.sleep(2000);
 	  
 	  if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Login  ******");	
	  }
 	  log.info("End: testNewSupplierLogin");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier Let's Begin")
   public void testNewSupplierLetsBegin() throws InterruptedException {
 	  log.info("Start: testNewSupplierLetsBegin");
 	 // testLetsBegin();
 	  String currentUrl = driver.getCurrentUrl();
 	  supplier1LoginUrl = currentUrl.substring(0, currentUrl.indexOf("/service")) + "/service/login";
 	  
 	  WebElement div = driver.findElement(By.id("glbLandingLaunchStart"));
 	  div.findElement(By.tagName("a")).click();
 	  Thread.sleep(500);
 	  /*
 	  if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Let's Begin  ******");	
	  }
	  */
 	  log.info("End: testNewSupplierLetsBegin");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New Supplier Estimate RFE")
   @Parameters({"supplierPrice1", "supplierPrice2", "supplierPrice3", "shippingEstPrice1",
                                  "shippingEstPrice2", "shippingEstPrice3", "taxPrice"})
   public void testNewSupplierCreateEstimate(String supplierPrice1, String supplierPrice2, String supplierPrice3, 
		                          String shippingEstPrice1, String shippingEstPrice2, String shippingEstPrice3, 
		                          String taxPrice) throws InterruptedException {
 	  log.info("Start: testNewSupplierCreateEstimate");
 	  EstimateRFEPage estimateRFEPage = new EstimateRFEPage(driver);
 	  estimateRFEPage.clickCreateEstimateBtn();
 	  
 	  List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
 	  List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
 	  //System.out.println("Found " + prices.size() + "prices\n");
 	  //System.out.println("Found " + shippingPrices.size() + "shippingPrices\n");
 	  prices.get(0).clear();
 	  prices.get(0).sendKeys(supplierPrice1);
 	  Thread.sleep(2000);
 	  shippingPrices.get(0).clear();
 	  shippingPrices.get(0).sendKeys(shippingEstPrice1);
 	  Thread.sleep(2000);
 	  
 	  prices.get(1).clear();
	  prices.get(1).sendKeys(supplierPrice2);
	  Thread.sleep(2000);
	  shippingPrices.get(1).clear();
	  shippingPrices.get(1).sendKeys(shippingEstPrice2);
	  Thread.sleep(2000);
	  
	  prices.get(2).clear();
 	  prices.get(2).sendKeys(supplierPrice3);
   	  Thread.sleep(2000);
 	  shippingPrices.get(2).clear();
 	  shippingPrices.get(2).sendKeys(shippingEstPrice3);
 	  Thread.sleep(2000);
 	  
 	  /*  10/10 comment out
 	  for (WebElement price : prices) {
 		  if (price != null) {
 			   price.clear();
 			   price.sendKeys(supplierPrice);
 		  }
 	  }
 	  
 	  List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
 	  for (WebElement shippingPrice : shippingPrices) {
 		  if (shippingPrice != null) {
 			  shippingPrice.clear();
 			  shippingPrice.sendKeys(shippingEstPrice);
 			  Thread.sleep(1000);
 		  }
 	  }
 	  */
 	  estimateRFEPage.inputEstimatedTaxField(taxPrice);
 	  Thread.sleep(2000);
 	  
 	  estimateRFEPage.clickSendEstimateBtn();
 	  Thread.sleep(3000);
 	 /* 
 	 if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Estimate RFE  ******");	
	  }
	  */
 	  log.info("End: testNewSupplierCreateEstimate");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New Supplier logout")
   public void testSupplierLogout() throws InterruptedException {
 	  log.info("Start: testSupplierLogout");
 	  Thread.sleep(3000);
 	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
 	  Thread.sleep(5000);
 	  supplierMainMenuPage.clickLogout();
 	  Thread.sleep(3000);
 	  /*
  	  if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Logout  ******");	
	  }
	  */
 	  log.info("End: testSupplierLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Go back to the gmail window")
   public void testGoBackToGmailPage() throws InterruptedException {
	   log.info("Start: testGoBackToGmailPage");
	   Thread.sleep(3000);
	   driver.close();  // close out the current window
	   Thread.sleep(3000);
	   driver.switchTo().window(parentWindowHandle); // switch back to the gmail window
	   Thread.sleep(2000);
	   driver.get("https://mail.google.com/mail/?logout"); // logout the current gmail session
	   Thread.sleep(2000);
	   log.info("End: testGoBackToGmailPage");
	   log.info("--------------------------------------------");
   }
   
  
   
   @Test(description="Existing supplier check gmail")
   public void testSupplier2CheckGmail() throws InterruptedException {
	   log.info("Start: testSupplier2CheckGmail");
	 	  Thread.sleep(5000);
	 	  List<WebElement> emails = driver.findElements(By.tagName("span"));
	 	  for (WebElement email : emails) {
	 		  if (email != null) {
	 			  String emailTitle = email.getText();
	 			 if (emailTitle.indexOf("has requested estimate for project:") >= 0) {
	 				  email.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(3000);
	 	  List<WebElement> links = driver.findElements(By.tagName("a"));
	 	  Thread.sleep(2000);
	 	  for (WebElement link : links) {
	 		  if (link != null) {
	 			  String linkHref = link.getAttribute("href");
	 			 if (linkHref.endsWith(".noosh.com/service/home")) {
	 				  link.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(2000);
	 	 /*
	 	  if(!browser.trim().equals("IE")) {
			   errorIndex = jsErrorIndex;
			   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Check Gmail  ******");	
		  }
		  */
	 	  log.info("End: testSupplier2CheckGmail");
	 	  log.info("--------------------------------------------");
   }
   
   
   @Test(description="Existing supplier login")
   @Parameters({"supplierName2", "passwd"})
   public void testExistingSupplier2Login(String supplierName2, String passwd) throws InterruptedException {
	   log.info("Start: testExistingSupplier2Login");
	   parentWindowHandle = driver.getWindowHandle();
	   Set<String> windows = driver.getWindowHandles();
	   for (String window : windows) {
	       if (!window.equals(parentWindowHandle)) {
	 	       driver.switchTo().window(window);
	 	  	   break;
	 	   }
	   }
	   
	   SPLoginPage loginPage = new SPLoginPage(driver);
	   Thread.sleep(1000);
	   loginPage.spLoginUser(supplierName2, passwd);
	   /*  
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Login  ******");   
	 	} 
	 	*/
	    log.info("End: testExistingSupplier2Login");
	    log.info("--------------------------------------------");
   }
   
   @Test(description="Exsiting supplier create estimate")
   @Parameters({"supplier2Price1", "supplier2Price2", "supplier2Price3", "shipping2EstPrice1",
       "shipping2EstPrice2", "shipping2EstPrice3", "taxPrice2"})
   public void testExistingSupplier2CreateEstimate(String supplier2Price1, String supplier2Price2, String supplier2Price3, 
       String shipping2EstPrice1, String shipping2EstPrice2, String shipping2EstPrice3, 
       String taxPrice2) throws InterruptedException {
	   log.info("Start: testExistingSupplier2CreateEstimate");
	   EstimateRFEPage estimateRFEPage = new EstimateRFEPage(driver);
	   estimateRFEPage.clickCreateEstimateBtn();
	   Thread.sleep(3000);
	   List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
	   List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
	   //System.out.println("Found " + prices.size() + " prices\n");
	   //System.out.println("Found " + shippingPrices.size() + " shippingPrices\n");
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
	
	   estimateRFEPage.inputEstimatedTaxField(taxPrice2);
	   Thread.sleep(2000);
	 	  
	   estimateRFEPage.clickSendEstimateBtn();
	   Thread.sleep(3000);
	   /*  
	   if(!browser.trim().equals("IE")) {
			errorIndex = jsErrorIndex;
			Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Create Estimate  ******");   
	   } */
	   log.info("End: testExistingSupplier2CreateEstimate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Exsiting supplier logout")
   public void testExistingSupplier2Logout() throws InterruptedException {
	   log.info("Start: testExistingSupplier2Logout");
	   Thread.sleep(3000);
	   //driver.get("http://selenium1.sqa.noosh.com/service/j_spring_security_logout");
	   ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
	   Thread.sleep(3000);
	   /*
	   if(!browser.trim().equals("IE")) {
			errorIndex = jsErrorIndex;
			Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Existing Supplier Logout  ******");   
	   } 
	   */
	   log.info("End: testExistingSupplier2Logout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go back to the gmail window again")
   public void test2ndGoBackToGmailPage() throws InterruptedException {
	   log.info("Start: test2ndGoBackToGmailPage");
	   Thread.sleep(3000);
	   driver.close();  // close out the current window
	   Thread.sleep(2000);
	   driver.switchTo().window(parentWindowHandle); // switch back to the gmail window
	   Thread.sleep(2000);
	   driver.get("https://mail.google.com/mail/?logout"); // logout the current gmail session
	   Thread.sleep(2000);
	   log.info("End: test2ndGoBackToGmailPage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp login")
   @Parameters({"spLoginURL", "spPassword"})
   public void testSPLogin(String spLoginURL,  String spPassword) throws InterruptedException {
 	  log.info("Start: testSPLogin");
 	  
 	  spLoginURL = spUser + spLoginURL;   
 	  //System.out.println("sp url: " + spLoginURL + "\n");
 			  
 	  driver.get(spLoginURL);
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(emailSP, spPassword);
 	  
 	 // if(!browser.trim().equals("IE")) {
 	//	  errorIndex = jsErrorIndex;
 	//	  Page.jsErrorReporter(driver, errorIndex,"####### TC JS errors for SP site  #######");   
 	//  }
 	 /*
 	  if(!browser.trim().equals("IE")) {
			errorIndex = jsErrorIndex;
			Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Login  ******");   
	  } 
	  */
 	  log.info("End: testSPLogin");
 	  log.info("--------------------------------------------");
   }   
   
   @Test(description="sp review estimates ")
   public void testSPReviewEstimate() throws InterruptedException {
 	  log.info("Start: testSPReviewEstimate");
 	  SPReviewEstimatePage spReviewEstimatePage = new SPReviewEstimatePage(driver);
   	  Thread.sleep(1000);
 	  spReviewEstimatePage.clickReviewEstimates(); 
 	  Thread.sleep(1000);
 	 /*
 	  if(!browser.trim().equals("IE")) {
			errorIndex = jsErrorIndex;
			Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Review Estimates  ******");   
	  } 
	  */
 	  log.info("End: testSPReviewEstimate");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp award order")
   @Parameters({""})
   public void testAwardOrderPage() throws InterruptedException {
 	  log.info("Start: testAwardOrderPage");
 	  SPAwardOrderPage spAwardOrderPage = new SPAwardOrderPage(driver);
 	  spAwardOrderPage.selectAEstimate();
 	  spAwardOrderPage.clickAwardBtn();
 	 /*
 	 if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Award Order  ******");   
	  }
	  */
 	  log.info("End: testAwardOrderPage");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp confirm order")
   public void testSPConfirmOrderPage() throws InterruptedException {
 	  log.info("Start: testConfirmOrderPage");
 	  SPConfirmOrderPage spConfirmOrderPage = new SPConfirmOrderPage(driver);
 	  spConfirmOrderPage.clickConfirmOrderBtn();
 	  Thread.sleep(1000);
 	 /*
 	 if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Confirm Order  ******");   
	  }
	  */
 	  log.info("End: testConfirmOrderPage");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp log out")
   public void testSPLogout() throws InterruptedException {
	   log.info("Start: testSPLogout");
	   testLogout();
	   /*
	   if(!browser.trim().equals("IE")) {
			  errorIndex = jsErrorIndex;
			  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Logout  ******");   
	   }
	   */
	   log.info("End: testSPLogout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier1 login gmail") 
   @Parameters({"gmailLoginPW"})
   public void testSupplier1LoginGmail(String gmailLoginPW) throws InterruptedException {
	   log.info("Start: testSupplier1LoginGmail");
	   Thread.sleep(1000);
	   testLoginToGmail(gmailLoginPW);
	   /*
	   if(!browser.trim().equals("IE")) {
			  errorIndex = jsErrorIndex;
			  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier1(new) Login Gmail  ******");   
	   }
	   */
	   log.info("End: testSupplier1LoginGmail");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier1 receives 'estimate not accept' email ")
   public void testSupplier1EstimateNotAccept() throws InterruptedException {
	   log.info("Start: testSupplier1EstimateNotAccept");
	 	  Thread.sleep(5000);
	 	  List<WebElement> emails = driver.findElements(By.tagName("span"));
	 	  for (WebElement email : emails) {
	 		  if (email != null) {
	 			  String emailTitle = email.getText();
	 			  if (emailTitle.indexOf(newSupplierUserCompanyName) >= 0) {
	 				  email.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	   Thread.sleep(3000);
	   /*
	   if(!browser.trim().equals("IE")) {
			  errorIndex = jsErrorIndex;
			  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier1(new) Estimate Not Accept  ******");   
	   }*/
	   log.info("End: testSupplier1EstimateNotAccept");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier1 logout")
   public void testSupplier1LogoutGmail() throws InterruptedException {
	   log.info("Start: testSupplier1LogoutGmail");
	   Thread.sleep(3000);
	   //driver.close();  // close out the current window
	   //Thread.sleep(3000);
	   //driver.switchTo().window(parentWindowHandle); // switch back to the gmail window
	   //Thread.sleep(2000);
	   driver.get("https://mail.google.com/mail/?logout"); // logout the current gmail session
	   Thread.sleep(2000);
	   /*
	   if(!browser.trim().equals("IE")) {
			  errorIndex = jsErrorIndex;
			  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier1(new) Logout Gmail  ******");   
	   }*/
	   log.info("End: testSupplier1LogoutGmail");
	   log.info("--------------------------------------------");
   }

/*
@Test(description="Supplier2 login gmail")
@Parameters({"gmailLoginPW"})
public void testSupplier2LoginGmail(String gmailLoginPW) throws InterruptedException {
	  log.info("Start: testSupplier2LoginGmail");
	  Thread.sleep(3000);
	 // driver.close();
	 // Thread.sleep(3000);
	 // driver.switchTo().window(parentWindowHandle); 
	 // Thread.sleep(3000);
	  testSupplier2LoginToGmail(gmailLoginPW);
	  /*
	  if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier2(Existing) Login Gmail  ******");   
      }*/
	/*  log.info("End: testSupplier2LoginGmail");
	  log.info("--------------------------------------------");
      }
*/
@Test(description="Supplier2 checks confirmation")
public void testSupplier2Confirmation() throws InterruptedException {
	log.info("Start: testSupplier2Confirmation");
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
    Thread.sleep(3000);
    /*
    if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier2(Existing) Check Confirmation  ******");   
    }*/
    log.info("End: testSupplier2Confirmation");
    log.info("--------------------------------------------");
}

@Test(description="Supplier2 logout")
@Parameters({"gmailLoginPW"})
public void testSupplier2LogoutGmail(String gmailLoginPW) throws InterruptedException {
	  log.info("Start: testSupplier2LogoutGmail");
	  Thread.sleep(2000);
	  driver.get("https://mail.google.com/mail/?logout"); 
	  Thread.sleep(2000);
	  /*
	  if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"******  JS errors for Supplier2(Existing) Logout Gmail  ******");   
      } */
	  log.info("End: testSupplier2LogoutGmail");
	  log.info("--------------------------------------------");
      }
}
