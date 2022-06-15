/***
* This class tests the functionality for Buying with Firefox browser
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

public class TestDemoSqaFF extends BaseSeleniumTest
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
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException
   {	
	   if(domain.trim().equals("qa2")) {
		         baseUrl = "https://demo.qa2.noosh.com/service/signup";
		       }else{		  
			       baseUrl = "https://demo.sqa.noosh.com/service/signup";
	   } 
   }
   
   @Test(description="SP onboarding - SP sign up")
   @Parameters({"fullNameSP", "passwordSP", "phoneNumberSP", "companyName"}) 
     public void testRegisterNewSPSite(String fullNameSP, String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException {		   
	   log.info("Start: testRegisterNewSPSite");
	   companyName = companyName + unicID;   
	   BaseTestCases.testRegisterNewSPSite(baseUrl, fullNameSP, passwordSP, phoneNumberSP, companyName);  
	   Thread.sleep(1200); 
	   
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 2.1, New SP sign up, JS errors for SP site #######");	
	   }
	   log.info("End: testRegisterNewSPSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Let's Begin")
   public void testLetsBegin() throws InterruptedException
   {
	   log.info("Start: testLetsBegin");
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   spUser = driver.getCurrentUrl();
	   int index = spUser.indexOf("/service");
	   spUser = spUser.substring(0, index);
	   System.out.println("spUser"+spUser);
	   
	   Thread.sleep(1500);
	   registerSPSitePage.clickLetsBegin();
	   Thread.sleep(1500);
	   /* 10/2
	   spSiteLoginPage = driver.getCurrentUrl();
	   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 13);
	   spSiteLoginPage = spSiteLoginPage + "login";
	   driver.get(spSiteLoginPage);
	   Thread.sleep(2000);
	   */
	   
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 2.2, Log out from SP Site, JS errors for SP site  #######");
	   }
	   log.info("End: testLetsBegin");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Invoke SP Wizard")
   public void testCreateBuyerSiteOB()
   {
 	  log.info("Start: testCreateBuyerSiteOB");
 	  SitesListTab sitesListTab = new SitesListTab(driver);
 	  
 	  sitesListTab.getSitesList();
 	  log.info("End: testCreateBuyerSiteOB");
 	  log.info("--------------------------------------------");
 	  
   }
   
   @Test(description="SP Create Project")
  // @Parameters({"validDemoUserEmail", "productName1"})
   @Parameters({"productName1"})
   public void testSPcreateProject(String productName1) throws InterruptedException
   {
	   log.info("Start: testSPcreateProject");
	   productName1 = productName1 + unicID +"2";
	   
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   
	   //newProject.clicklCreateProjectBT();	    //10/2
	   //newProject.enterValidClientEmail(validDemoUserEmail);  //10/3
	   newProject.inputProjectName(productName1);    //10/3
	   Thread.sleep(2000);
	   //newProject.clickFindBT();                //10/3
	   //newProject.selectCustomer();	          //10/3 
	   //Thread.sleep(2000);
	   
	   //newProject.selectCertainSite();
	   List<WebElement> imgs = driver.findElements(By.tagName("img"));
	   for (WebElement img : imgs) {
		   String title = img.getAttribute("alt");
		   //if (title != null && title.equalsIgnoreCase("Brochure")) {
		   if (title != null && title.equalsIgnoreCase("Envelope")) {
			   img.click();
			   break;
		   }
	   }
	   Thread.sleep(2000);
	   //newProject.clickPostcard();  10/3
	   newProject.clickCreateAndEstimateProjectBT();
	   Thread.sleep(2000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-1, SP Create Project - step 1, JS errors for SP site #######");	 
	   log.info("End: testSPcreateProject");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP Create Project - product 1")
   /* 10/3
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", 
	   "specDescrSP", "fileForProject1"})
   10/3  */
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
	   "specDescrSP", "fileForProject1"})
   /* 10/3
   public void testSPcreateProjectProjectWizard(String projNameSP, String descriptionNameSP,
		   String sku, String referenceNumber, String quantSP, String specDescrSP,
		   String fileForProject1) throws InterruptedException, AWTException
	10/3  */
   public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
		   String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
		   String fileForProject1) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPcreateProjectProjectWizard");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   	    
	   brochurePopup.setProjectName(projNameSP); 
	   Thread.sleep(2000);   	    
	   brochurePopup.callCalendar();	                
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
	   //brochurePopup.putQuant1(quantSP);
	   //brochurePopup.putQuant2(quantSP2);    //10/3
	   //brochurePopup.putQuant3(quantSP3);    //10/3
	   //brochurePopup.putSpecDescription(specDescrSP);    
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
	   
	   //testAddSmartFormPage();   //10/8 comment out
	   
	   brochurePopup.clickCreateAndEstimateProjectBT();    //10/3
	   Thread.sleep(3500);         //10/3
       
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"#######  SP Create Project - JS errors for SP site #######");	 
	   log.info("End: testSPcreateProjectProjectWizard");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="add smart form")
   @Parameters({})
   public void testAddSmartFormPage() throws InterruptedException {
	   log.info("Start: testAddSmartFormPage");
	   AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver);
	   addSmartFormPage.clickAddSmart();
	   addSmartFormPage.clickSmartForm();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"#######  add smart form - JS errors for SP site #######");	
	   log.info("End: testAddSmartFormPage");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Request Estimate from Suppliers")
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
	   supplierEstimatePopup.callCalendar();	               
	   Thread.sleep(2000);
	   supplierEstimatePopup.callRequestEstimatesBtn();
	   Thread.sleep(5000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### Request Estimate from Suppliers - product 1, JS errors for SP site #######");	  
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
   
   @Test(description="logout")
   public void testLogout() throws InterruptedException {
 	  log.info("Start: testLogout");
 	  Thread.sleep(8000);
 	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
 	  Thread.sleep(2000);
 	  supplierMainMenuPage.clickLogout();
	  Thread.sleep(2000);
 	  
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### logout - JS errors for SP site #######");	
 	  log.info("End: testLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Login to Gmail")
   @Parameters({"gmailLoginPW"})
   public void testLoginToGmail(String gmailLoginPW) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  Thread.sleep(1500);
 	  driver.get("http://www.gmail.com");
 	  Thread.sleep(2000);
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterEmail("seleniumtest12345@gmail.com");
 	 // gmailLoginPage.enterEmail("seleniumtest54321@gmail.com");
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(1000);
 	  gmailLoginPage.signIn();
 	  Thread.sleep(1000);
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Check Gmail")
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
 	  log.info("End: testCheckGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier signup")
   @Parameters({"newSupplierFullName", "newSupplierPasswd", "newSupplierCompanyName"})
   public void testNewSupplierSignup(String newSupplierFullName, String newSupplierPasswd, 
		                           String newSupplierCompanyName) throws InterruptedException { 	 
      log.info("Start: testSupplier1Signup");
      newSupplierCompanyName = newSupplierCompanyName + unicID;
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
 	  
 	  if(!browser.trim().equals("IE")) {
 		   errorIndex = jsErrorIndex;
 		   Page.jsErrorReporter(driver, errorIndex,"####### TC 2.1, New SP sign up, JS errors for SP site #######");	
 	  }
 	  
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
 	  log.info("End: testNewSupplierLogin");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier - Let's Begin")
   public void testNewSupplierLetsBegin() throws InterruptedException {
 	  log.info("Start: testNewSupplierLetsBegin");
 	 // testLetsBegin();
 	  String currentUrl = driver.getCurrentUrl();
 	  supplier1LoginUrl = currentUrl.substring(0, currentUrl.indexOf("/service")) + "/service/login";
 	  
 	  WebElement div = driver.findElement(By.id("glbLandingLaunchStart"));
 	  div.findElement(By.tagName("a")).click();
 	  Thread.sleep(500);
 	  log.info("End: testNewSupplierLetsBegin");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Estimate RFE")
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
 	  System.out.println("Found " + prices.size() + "prices\n");
 	  System.out.println("Found " + shippingPrices.size() + "shippingPrices\n");
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
 	  
 	  log.info("End: testNewSupplierCreateEstimate");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier logout")
   public void testSupplierLogout() throws InterruptedException {
 	  log.info("Start: testSupplierLogout");
 	  Thread.sleep(3000);
 	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
 	  Thread.sleep(5000);
 	  supplierMainMenuPage.clickLogout();
 	  Thread.sleep(3000);
 	  log.info("End: testSupplierLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Existing supplier login gmail")
   @Parameters({"gmailLoginPW"})
   public void testSupplier2LoginToGmail(String gmailLoginPW) throws InterruptedException  {
	 	  log.info("Start: testSupplier2LoginToGmail");
	 	  
	 	  Thread.sleep(1500);
	 	  driver.get("http://www.gmail.com");
	 	  Thread.sleep(2000);
	 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	 	  Thread.sleep(1000);
	 	  gmailLoginPage.enterEmail("seleniumtest54321@gmail.com");
	 	  Thread.sleep(1000);
	 	  gmailLoginPage.enterPassword(gmailLoginPW);
	 	  Thread.sleep(1000);
	 	  gmailLoginPage.signIn();
	 	  Thread.sleep(1000);
	 	  log.info("End: testSupplier2LoginToGmail");
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
	 			  //if (linkHref.endsWith("signupType=rfe")) {
	 			 if (linkHref.endsWith("selenium1.sqa.noosh.com/service/home")) {
	 				 // System.out.println("link=" + linkHref);
	 				  link.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(2000);
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
	 	  
	 	  if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.1 Log in to Noosh Pro, JS errors for SP site  #######");   
	 	  } 
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
	 	  System.out.println("Found " + prices.size() + " prices\n");
	 	  System.out.println("Found " + shippingPrices.size() + " shippingPrices\n");
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
	   log.info("End: testExistingSupplier2CreateEstimate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="supplier2 logout")
   public void testExistingSupplier2Logout() throws InterruptedException {
	   log.info("Start: testExistingSupplier2Logout");
	   Thread.sleep(3000);
	   //driver.get("http://selenium1.sqa.noosh.com/service/j_spring_security_logout");
	   ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
	   Thread.sleep(3000);
	   log.info("End: testExistingSupplier2Logout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp login")
   @Parameters({"spLoginURL", "spPassword"})
   public void testSPLogin(String spLoginURL,  String spPassword) throws InterruptedException {
 	  log.info("Start: testSPLogin");
 	  
 	  spLoginURL = spUser + spLoginURL;   
 	  System.out.println("sp url: " + spLoginURL + "\n");
 			  
 	  driver.get(spLoginURL);
 	  // driver.manage().window().maximize();
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(emailSP, spPassword);
 	  
 	  if(!browser.trim().equals("IE")) {
 		  errorIndex = jsErrorIndex;
 		  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.1 Log in to Noosh Pro, JS errors for SP site  #######");   
 	  }
 	  log.info("End: testSPLogin");
 	  log.info("--------------------------------------------");
   }   
   
   @Test(description="sp review estimates ")
   public void testSPReviewEstimateBtn() throws InterruptedException {
 	  log.info("Start: testSPReviewEstimate");
 	  SPReviewEstimatePage spReviewEstimatePage = new SPReviewEstimatePage(driver);
 	  spReviewEstimatePage.clickReviewEstimates();
 	  Thread.sleep(1000);
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
 	  log.info("End: testAwardOrderPage");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp confirm order")
   public void testSPConfirmOrderPage() throws InterruptedException {
 	  log.info("Start: testConfirmOrderPage");
 	  SPConfirmOrderPage spConfirmOrderPage = new SPConfirmOrderPage(driver);
 	  spConfirmOrderPage.clickConfirmOrderBtn();
 	  Thread.sleep(1000);
 	  
 	 if(!browser.trim().equals("IE")) {
		  errorIndex = jsErrorIndex;
		  Page.jsErrorReporter(driver, errorIndex,"####### JS errors for SP confirm order  #######");   
	  }
 	  log.info("End: testConfirmOrderPage");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp log out")
   public void testSPLogout() throws InterruptedException {
	   log.info("Start: testSPLogout");
	   testLogout();
	   
	   if(!browser.trim().equals("IE")) {
			  errorIndex = jsErrorIndex;
			  Page.jsErrorReporter(driver, errorIndex,"#######  errors for SP log out  #######");   
	   }
	   log.info("End: testSPLogout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="supplier login")
   @Parameters({"passwordSP"})
   public void testSupplier1Login(String passwordSP) throws InterruptedException {
 	  log.info("Start: testSupplier1Login");
 	  
 	 // supplierLoginURL = supplierName1 + "sqa.noosh.com" + supplierLoginURL;
 			  
 	  driver.get(supplier1LoginUrl);
 	 // driver.manage().window().maximize();
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(supplierUser1, passwordSP);
 	 System.out.println("supplier1LoginUrl="+supplier1LoginUrl);
 	  System.out.println("supplierUser1="+supplierUser1);
 	 System.out.println("passwordSP="+passwordSP);
 	  
 	  if(!browser.trim().equals("IE")) {
 		  errorIndex = jsErrorIndex;
 		  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.1 Log in to Noosh Pro, JS errors for SP site  #######");   
 	  } 
 	  log.info("End: testSupplier1Login");
 	  log.info("--------------------------------------------");
   }   
   
   @Test(description="check supplier confirmation")
   public void testSupplier1Confirmation() {
	   log.info("Start: testSupplier1Confirmation");
	   Assert.assertTrue(driver.getPageSource().contains("Congratulations on receiving your first Order!"));
	    
	   log.info("End: testSupplier1Confirmation");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="supplier1 logout")
   public void testSupplier1Logout() throws InterruptedException {
	   log.info("Start: testSupplier1Logout");
	   testLogout();
	   
	   log.info("End: testSupplier1Logout");
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
   }
   
   @Test(description="1.2-1, SP onboarding - Welcome screen")
   @Parameters({"letsBegin"})
   public void testWelcomeScreen(String letsBegin) throws InterruptedException
   {
	   log.info("Start: testWelcomeScreen");
	   Thread.sleep(1200); 
	   BaseTestCases.testLetsBegin(letsBegin);

	   System.out.println("SP site URL: " + driver.getCurrentUrl());
	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex; 
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-1, Welcome screen, JS errors for SP site #######");	
	   }
	   log.info("End: testWelcomeScreen");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="1.2-2, SP onboarding - Welcome screen")
   @Parameters({"projectNameForClient", "clientName"})
   public void testCreateProject(String projectNameForClient, String clientName) throws InterruptedException
   {		   
	   log.info("Start: testCreateProject");
	   BaseTestCases.testCreateProject1(projectNameForClient, clientName);		   

	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-2, Welcome screen, JS errors for SP site #######");	
	   }
	   log.info("End: testCreateProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="1.2-3, SP onboarding - Project create popup, project info tab")
   @Parameters({"projectNameForClient"})
   public void testCreateProjectProjectInfo(String projectNameForClient) throws InterruptedException
   {		   
	   log.info("Start: testCreateProjectProjectInfo");
	   BaseTestCases.testCreateProjectPopup(projectNameForClient);		   

	   if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-3, SP onboarding - Project create popup, project info tab, JS errors for SP site #######");
	   }
	   log.info("End: testCreateProjectProjectInfo");
	   log.info("--------------------------------------------");
   }
   
   
   
   @Test(description="1.2, SSO-1, Go to Noosh Entry through sites menu")
   @Parameters({"myDeskPage", "profileTitlePro"}) 
   public void testMyDeskPage(String myDeskPage, String profileTitlePro) throws InterruptedException
   {     
	  log.info("Start: testMyDeskPage");
      MyDeskPage myNooshDeskPage = new MyDeskPage(driver); 
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, myDeskPage)); 
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Noosh > My Desk > Summary' page title");
		  log.info("1.2 This is not 'Noosh > My Desk > Summary' page title");
	  }	
      try{
      AssertJUnit.assertEquals(profileTitlePro,myNooshDeskPage.getProfileTitle());
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Demo User' profile title");
		  log.info("1.2 This is not 'Demo User' profile title");
	  }	
	  */
	  Thread.sleep(1000);
	  if(browser.trim().equals("IE")) {
		  myNooshDeskPage.getSitesListIE();
	  } else {
          myNooshDeskPage.getSitesList();
	  }
      
	  if(!browser.trim().equals("IE")) {
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-1, Go to Noosh Entry through sites menu, JS errors for SP site #######");   
	  }
	  log.info("End: testMyDeskPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description = "1.2, SSO-2, Go back to Noosh Pro through My desk")
   @Parameters({"siteListPageTitle", "profileTitlePro"}) 
   public void testSiteListPage(String siteListPageTitle, String profileTitlePro) 
      throws InterruptedException
   {
	  log.info("Start: testSiteListPage");
      SiteListPage siteListPage = new SiteListPage(driver);
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, siteListPageTitle));
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Site List - MillerZell - Noosh' page title");
		  log.info("1.2 This is not 'Site List - MillerZell - Noosh' page title");
	  }	 
	  try{
      AssertJUnit.assertEquals(profileTitlePro, siteListPage.getProfileTitle());
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Demo User' profile title");
		  System.out.println("1.2 This is " + siteListPage.getProfileTitle() + " profile title");
		  log.info("1.2 This is not 'Demo User' profile title");
		  log.info("1.2 This is " + siteListPage.getProfileTitle() + " profile title");
	  }	
	  */
	  Thread.sleep(1000);  
	  if(browser.trim().equals("IE")) {
		  System.out.println("Browser: " + browser);
//		  siteListPage.getMyDeskPageBackIE();
		  driver.get("https://sqa.noosh.com/noosh/home/myDesk?loginSpecificWorkgroupId=5087123&isUpdateNooshProLocaleCookie=1&locale=en_US&mdu=Group-SP-MainMenu");
	  } else {
	      siteListPage.getMyDeskPageBack();
	  }
      
	  if(!browser.trim().equals("IE")) {
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-2, Go back to Noosh Pro through My desk, JS errors for SP site #######"); 
	  }
	  log.info("End: testSiteListPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="1.2, SSO-3, Go to my dashboard")
   public void testMyDeskPageBack() throws InterruptedException
   {
	  log.info("Start: testMyDeskPageBack");
      MyDeskPage myDeskPageNoo = new MyDeskPage(driver);      
       
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, myDeskPage));
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Noosh > My Desk > Summary' page title");
		  System.out.println("1.2 This is " + page.getTitle(driver) + " page title");
		  log.info("1.2 This is not 'Noosh > My Desk > Summary' page title");
		  log.info("1.2 This is " + page.getTitle(driver) + " page title");
	  }	
	  */
	  Thread.sleep(1000);            
      myDeskPageNoo.getDashboard(); 
      
	  if(!browser.trim().equals("IE")) {
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-3, Go to my dashboard, JS errors for SP site #######");
	  }
	  log.info("End: testMyDeskPageBack");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="1.3-1, Log out")
   @Parameters({"dashboardPageTitle"}) 
   public void testPageDashboard(String dashboardPageTitle) throws InterruptedException 
   {
	  log.info("Start: testPageDashboard");
      RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
       
      /*
      try{
          AssertJUnit.assertTrue(page.checkPageTitle(driver, dashboardPageTitle));
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Dashboard - Noosh Demo - Noosh' page title");
		  log.info("1.2 This is not 'Dashboard - Noosh Demo - Noosh' page title");
	  }	
	  */ 
	  registerSPSitePage.logoutSPSite();
	  Thread.sleep(1400);
//      dashboard.logoutNoosh();
	  if(!browser.trim().equals("IE")) {
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.3-1, Log out, JS errors for SP site #######"); 
	  }
	  log.info("End: testPageDashboard");
	  log.info("--------------------------------------------");
   }
   
   
   @Test(description="Test new logout SP site")
   @Parameters({"passwordSP"})
   public void testNewLogoutSPSite(String passwordSP) throws InterruptedException
   {
	   log.info("Start: testNewLogoutSPSite");
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   
//      Alert alert = driver.switchTo().alert();
//	   alert.accept();
//	   driver.navigate().refresh();
	   Thread.sleep(3000);
	   /*
	   if(browser.trim().equals("IE")) {
	      registerSPSitePage.logoutSPsiteIE();
	   } else {
	      registerSPSitePage.logoutSPsiteIE();
	   }
	   */
	   registerSPSitePage.logoutSPSite();
	   Thread.sleep(2000);
	   credentials.put("spURL", driver.getCurrentUrl());
	   credentials.put("loginName", emailSP);
	   credentials.put("password", passwordSP);
	   
	  if(!browser.trim().equals("IE")) {
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### Test new logout SP site #######");
	  }
	  log.info("End: testNewLogoutSPSite");
	  log.info("--------------------------------------------");
   }       
   
   // Testing the functionality of Login page
   @Test(description="3.1, Login to SP site")
   @Parameters({"baseUrl", "loginNooshDemoTitle", "passwordSP"}) 
   public void testLoginDemoSqaPageBW(String baseUrl, String loginNooshDemoTitle, String passwordSP) throws InterruptedException 
   {      
	   log.info("Start: testLoginDemoSqaPageBW");
      // Verifying that this is the correct page     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);

      /*
	  try {
          AssertJUnit.assertTrue(page.checkPageTitle(driver, loginNooshDemoTitle + unicID +" - Noosh"));
	  }
	  catch(Throwable e) {
		  System.out.println("3.1 This not selenium{UnicID} - Noosh page title");
		  log.info("3.1 This not selenium{UnicID} - Noosh page title");
		  System.out.println("3.1 This is '" + page.getTitle(driver) + "' page title");
		  log.info("3.1 This is '" + page.getTitle(driver) + "' page title");
	  }
	  */
      loginDemoSqa.loginUser(emailSP, passwordSP);      
	  Thread.sleep(1000);  
	  spSiteLoginPage = driver.getCurrentUrl();
	   
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 3.1, Login to SP site, JS errors for SP site #######");		   
	  log.info("End: testLoginDemoSqaPageBW");
	  log.info("--------------------------------------------");
   }              
  
   @Test(description="SP customization - Download Login page/ Header images")
   @Parameters({"headerLogo", "loginContentImage", "imageLogoFile", "imageLoginFile"})
   public void testDownloadLoginPageImages(String headerLogo, String loginContentImage, 
		   String imageLogoFile, String imageLoginFile) 
		   throws InterruptedException
   {
	   log.info("Start: testDownloadLoginPageImages");
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(1500);
//	   if(browser.trim().equals("IE")) {
	   spSiteLoginPage = driver.getCurrentUrl();
	   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 4);
	   System.out.println("Home page url:" + spSiteLoginPage);
	   driver.get(spSiteLoginPage + "home/custom");
//	   } else
//	   newHomePage.clickBrandingSubmenu(); or
//	   newHomePage.clickGearSubmenuItem(driver, "Branding");   
	   Thread.sleep(1000);	    
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageMain(imageLogoFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Image Customization tab");
			  log.info("5.1 Logo image not displayed in Image Customization tab");
	   }	  
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImagePreview(imageLogoFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Login Preview Image Customization tab");
			  log.info("5.1 Logo image not displayed in Login Preview Image Customization tab");
	   }
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImage(imageLogoFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in main SP page, in the left top corner");
			  log.info("5.1 Logo image not displayed in main SP page, in the left top corner");
	   }	  
	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginContentImage(imageLoginFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Login Content image not displayed in Image Customization tab");
			  log.info("5.1 Login Content image not displayed in Image Customization tab");
	   }
	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginContantImagePreview(imageLoginFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Login Content image not displayed in Login Preview Image Customization tab");
			  log.info("5.1 Login Content image not displayed in Login Preview Image Customization tab");
	   }
	   adminCustomization.clickDashboardPreviewLink();
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageDashboardPreview(imageLogoFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Dashboard Preview Image Customization tab");
			  log.info("5.1 Logo image not displayed in Dashboard Preview Image Customization tab");
	   }
	   try {
	          AssertJUnit.assertTrue(mainMenuPage.checkLogoImageSPSite(imageLogoFile));	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in SP site");
			  log.info("5.1 Logo image not displayed in SP site");
	   }
	   adminCustomization.clickUploadImagesBT();	    
	   Thread.sleep(1000);
	   // headerLogo 
	   adminCustomization.uploadHeaderLogo(headerLogo);
	   Thread.sleep(1000);
	   adminCustomization.uploadLoginContentImage(loginContentImage);    
	   Thread.sleep(1000);
	   adminCustomization.clickSaveImagesBT();	    
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.1, SP customization - Download Login page/ Header images, JS errors for SP site #######");	   
	   log.info("End: testDownloadLoginPageImages");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="5.2, SP customization - Login page customization")
   @Parameters({"loginBoxColor", "loginBoxTextColor"})
   public void testLoginPageCustomization(String loginBoxColor, String loginBoxTextColor) throws InterruptedException
   {
	   log.info("Start: testLoginPageCustomization");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   
	   adminCustomization.clickLoginCustomizationTab();	    
	   adminCustomization.clickEditColorsBT();	    
	   adminCustomization.setLoginBoxColor(loginBoxColor);
	   adminCustomization.setLoginBoxTextColor(loginBoxTextColor);	    
	   adminCustomization.clickSaveLoginColorsBT();
	   Thread.sleep(1500);
	   try{
	       AssertJUnit.assertEquals(loginBoxColor, adminCustomization.getColorLoginBox().trim());
	      } catch(Throwable e) {
			   System.out.println("5.2 New color doesn't displayed in Login customization tab");
	           log.info("5.2 New color doesn't displayed in Login customization tab");
	    	}
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(loginBoxColor, driver));	      
	   } catch(Throwable e) {
		   System.out.println("5.2 New Login box color doesn't displayed in Login customization tab");
           log.info("5.2 New Login box color doesn't displayed in Login customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(loginBoxTextColor, driver));     
	   } catch(Throwable e) {
		   System.out.println("5.2 New Login box Text color doesn't displayed in Login customization tab");
		   log.info("5.2 New Login box Text color doesn't displayed in Login customization tab");
	   }
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.2, SP customization - Login page customization, JS errors for SP site #######");
	   log.info("End: testLoginPageCustomization");
	   log.info("--------------------------------------------");
   }
  
   @Test(description="5.3 SP customization - Header customization")
   @Parameters({"headerBackgroundColor", "headerTextColor", "menuBackgoundColor", "menuTextColor"})
   public void testHeaderCustomization(String headerBackgroundColor, String headerTextColor, 
		   String menuBackgoundColor, String menuTextColor) throws InterruptedException
   {
	   log.info("Start: testHeaderCustomization");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   	   
	   adminCustomization.clickHeaderCustomizationTab();	   	   
	   Thread.sleep(1000);
	   adminCustomization.clickEditBT();	   
	   adminCustomization.setHeaderBGColor(headerBackgroundColor);
	   adminCustomization.setHeaderTextColor(headerTextColor);
	   adminCustomization.setMenuBGColor(menuBackgoundColor);
	   adminCustomization.setMenuTextColor(menuTextColor);
	   Thread.sleep(700);
	   adminCustomization.clickSaveHeaderColors();
	   Thread.sleep(1500);		   
	   try{
	       AssertJUnit.assertEquals(headerBackgroundColor, adminCustomization.getHeaderBGColor().trim());
	      } catch(Throwable e) {
		   System.out.println("5.3 New colors doesn't set in Header customization tab");
		   log.info("5.3 New colors doesn't set in Header customization tab");
	    	}	   	   
	   
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(headerBackgroundColor, driver));
//		   AssertJUnit.assertEquals(headerBackgroundColor, adminCustomization.getHeaderBGColor());	      
	   } catch(Throwable e) {
		   System.out.println("5.3 New Header Background color doesn't set in Header customization tab");
		   log.info("5.3 New Header Background color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(headerTextColor, driver));
//	       AssertJUnit.assertEquals(headerTextColor, adminCustomization.getHeaderTextColor());	      
	   } catch(Throwable e) {
		   System.out.println("5.3 New Header Text color doesn't set in Header customization tab");
		   log.info("5.3 New Header Text color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(menuBackgoundColor, driver));
//		   AssertJUnit.assertEquals(menuBackgoundColor, adminCustomization.getMenuBGColor());	      
	   } catch(Throwable e) {
		   System.out.println("5.3 New Menu Background color doesn't set in Header customization tab");
		   log.info("5.3 New Menu Background color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(menuTextColor, driver));
//	       AssertJUnit.assertEquals(menuTextColor, adminCustomization.getMenuTextColor());	      
	   } catch(Throwable e) {
		   System.out.println("5.3 New Menu Text color doesn't set in Header customization tab");
		   log.info("5.3 New Menu Text color doesn't set in Header customization tab");
	   }	

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.3, SP customization - Header customization, JS errors for SP site #######");
	   log.info("End: testHeaderCustomization");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="6.1, SP user Profile - Update some Profile info")
   @Parameters({"loginNameErrorMessage", "emailErrorMessage", "firstNameSP", "newLastNameSP"})
   public void testProfileInfoUpdate(String loginNameErrorMessage, String emailErrorMessage, String firstNameSP, 
		   String newLastNameSP) throws InterruptedException
   {	
	   log.info("Start: testProfileInfoUpdate");
	   String fullName = firstNameSP + " " + newLastNameSP;
	   
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   	   	
	   userProfileFrame.clickProfileUserName();	   
	   userProfileFrame.clickEditProfileBT();	   
	   userProfileFrame.clearLoginNameField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(200);	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(loginNameErrorMessage, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.1 'Please input Login Name' message not displayed");
		   log.info("6.1 'Please input Login Name' message not displayed");
	   }	
	   userProfileFrame.clearEmailField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(200);
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(emailErrorMessage, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.1 'Please input Email address' message not displayed");
		   log.info("6.1 'Please input Email address' message not displayed");
	   }
	   userProfileFrame.setLoginName(emailSP);	   
	   userProfileFrame.setEmailAddress(emailSP);	   
	   userProfileFrame.setNewLastName(newLastNameSP);
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(700);	   	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(fullName, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.1 User profile name does not updated");
		   log.info("6.1 User profile name does not updated");
	   }

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.1, SP user Profile - Update some Profile info, JS errors for SP site #######");	
	   log.info("End: testProfileInfoUpdate");
	   log.info("--------------------------------------------");
   }
   
   
   @Test(description="6.2 SP iser Profile - Download new Profile image")
   @Parameters({"newProfilePicture"})
   public void testDownloadNewProfileImage(String newProfilePicture) throws InterruptedException, AWTException
   {
	   log.info("Start: testDownloadNewProfileImage");
	   String oldProfileImage;
	   
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   	   
	   userProfileFrame.getImageAttribute();
	   Thread.sleep(1000);
	   oldProfileImage = userProfileFrame.getHeadProfileImageAttribute();	   	   
	   userProfileFrame.clickChangProfilePicture(newProfilePicture);
       Thread.sleep(1000);   
	   if (oldProfileImage.equals(userProfileFrame.getHeadProfileImageAttribute()))
	   {
		   System.out.println("6.2 The new profile image does not displayed in the head user profile");
	       log.info("6.2 The new profile image does not displayed in the head user profile");
	   }
	       Thread.sleep(500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.2, SP iser Profile - Download new Profile image, JS errors for SP site #######");		
	   log.info("End: testDownloadNewProfileImage");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="6.3-1, SP user Profile - Reset passowrd")
   @Parameters({"wrongPasswordSP", "passwordSP", "newPasswordSP", "wrongOriginalPassword"})
   public void testResetPassword(String wrongPasswordSP, String passwordSP, String newPasswordSP,
		   String wrongOriginalPassword) throws InterruptedException
   {
	   log.info("Start: testResetPassword");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
       RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
       
	   userProfileFrame.clickProfileUserName();
	   userProfileFrame.clickResetPassword();
       Thread.sleep(500);
	   userProfileFrame.setOriginalPassword(wrongPasswordSP);
	   userProfileFrame.setNewPassword(passwordSP);
	   userProfileFrame.setConfirmPassword(passwordSP);
	   userProfileFrame.clickChangePasswordBT();
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(wrongOriginalPassword, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.3 'The original password is not correct!' does not displayed");
		   log.info("6.3 'The original password is not correct!' does not displayed");
	   }
	   userProfileFrame.setOriginalPassword(passwordSP);
	   userProfileFrame.setNewPassword(newPasswordSP);
	   userProfileFrame.setConfirmPassword(newPasswordSP);
	   userProfileFrame.clickChangePasswordBT();
       Thread.sleep(500);
       
 // old UI      mainMenuPage.logoutSite();
	   if(browser.trim().equals("IE")) {
          registerSPSitePage.logoutSPsiteIE();
	   } else {
	      registerSPSitePage.logoutSPSite();
	   }
	   Thread.sleep(1000);
		
	    errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3-1, SP user Profile - Reset passowrd, JS errors for SP site #######");		
		log.info("End: testResetPassword");
		log.info("--------------------------------------------");
   }
   
   @Test(description="6.3-2, SP user Profile - Reset passowrd, login with new password, integration test")
   @Parameters({"newPasswordSP"})
   public void testLoginBackNewPassword(String newPasswordSP) throws InterruptedException
   {
	   log.info("Start: testLoginBackNewPassword");
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   	   
	   loginDemoSqa.loginUser(emailSP, newPasswordSP);
       Thread.sleep(1000); 

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3-2, SP user Profile - Reset passowrd, JS errors for SP site #######");		   
	   log.info("End: testLoginBackNewPassword");
   }    
   
// it temporary for debug   
   @Test(description="Login Demo SQA Page, ABAT")
   public void testLoginDemoSqaPage() 
   {
	   log.info("Start: testLoginDemoSqaPage");
//      driver.get("http://sel.sqa.noosh.com/service/login");
	   driver.get("https://selenium640806.sqa.noosh.com/service/login/");
      driver.manage().window().maximize();
      
      // Verifying that this is the correct page     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
      
      loginDemoSqa.loginUser("nobody+1@noosh.com", "17password");           

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######");		
	  log.info("End: testLoginDemoSqaPage");
	  log.info("--------------------------------------------");
   }     
   
   @Test(description="6.3, SP user Profile - Reset password back")
   @Parameters({"passwordSP", "newPasswordSP"})
   public void testResetPasswordBack(String passwordSP, String newPasswordSP) throws InterruptedException
   {
	   log.info("Start: testResetPasswordBack");
//	   String wrongOriginalPassword = "The original password is not correct!";
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
       
	   userProfileFrame.clickProfileUserName();
	   userProfileFrame.clickResetPassword();
	   userProfileFrame.setOriginalPassword(newPasswordSP);
	   userProfileFrame.setNewPassword(passwordSP);
	   userProfileFrame.setConfirmPassword(passwordSP);
	   userProfileFrame.clickChangePasswordBT();
       Thread.sleep(500);
		
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.2, SP user Profile - Reset password back, JS errors for SP site #######");		
	   log.info("End: testResetPasswordBack");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="6.3, SP user profile - Login back with old password")
   @Parameters({"passwordSP"})
   public void testLoginBackOldPassword(String passwordSP) throws InterruptedException
   {
	   log.info("Start: testLoginBackOldPassword");
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);

	   loginDemoSqa.loginUser(emailSP, passwordSP);
       System.out.println("----------------------------------------- SP---------------------------------");
       System.out.println("SP url: " + credentials.get("spURL") + "/ login name: " + credentials.get("loginName") + " password: " + credentials.get("password"));
       log.info("SP url: " + credentials.get("spURL") + "/ login name: " + credentials.get("loginName") + " password: " + credentials.get("password"));
       Thread.sleep(1000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3, SP user Profile - Reset password back, JS errors for SP site #######");		
	   log.info("End: testLoginBackOldPassword");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="6.4, SP user profile - SP forgot password")
   public void testSPForgotPassword() throws InterruptedException
   {
	   log.info("Start: testSPForgotPassword");
	   BaseTestCases.spForgotPassword();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.4, SP user profile - SP forgot password, JS errors for SP site #######");	
	   log.info("End: testSPForgotPassword");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.4-1, SP user profile - SP forgot password, get the reset password link")
   @Parameters({"baseUrlBuyerSite", "passwordSP"})
   public void testSPGetResetPasswordLink(String baseUrlBuyerSite, String passwordSP) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPGetResetPasswordLink");
	   BaseTestCases.getResetPasswordLink(driver, baseUrlBuyerSite, "demo.nshauto", "88word22pass");
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.4-1, SP user profile - SP forgot password, get the reset password link, JS errors for SP site #######");	
	   log.info("End: testSPGetResetPasswordLink");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.4-2, SP user profile - SP forgot password, set new SP password")
   @Parameters({"passwordSP"})
   public void testResetNewSPPassword(String passwordSP) throws InterruptedException, AWTException
   {
	   log.info("Start: testResetNewSPPassword");
	   firstWindow = driver.getWindowHandle();
	   for (String handle : buyerDriver.getWindowHandles()) {
	       buyerDriver.switchTo().window(handle);         
	   }
	       
	   driver.manage().window().maximize();
	   Thread.sleep(1400);
	   BaseTestCases.resetSPPassword(passwordSP);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.4-2, SP user profile - SP forgot password, set new SP password, JS errors for SP site #######");	
	   log.info("End: testResetNewSPPassword");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="7.1-1, Customize default product - Go to specs")
   @Parameters({"dashboardDemoTitle", "profileTitle", "passwordSP"}) 
   public void testDashboardDemoPage(String dashboardDemoTitle, String profileTitle, String passwordSP) 
      throws InterruptedException
   {
	   log.info("Start: testDashboardDemoPage");
      NewHomePage newHomePage = new NewHomePage(driver);

      Thread.sleep(800);
	   // new UI
	  if(browser.trim().equals("IE")) {
		 newHomePage.clickGearSubmenuItemIE();
	  } else {
	     newHomePage.clickGearSubmenuItem(driver, "Request Forms");
	  }
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-1, Customize default product - Go to specs, JS errors for SP site #######");	  
	  log.info("End: testDashboardDemoPage");
	  log.info("--------------------------------------------");
   }

   @Test(description = "7.1-2, Customize default product - Select customer product")
   @Parameters({"manageProductDemoTitle"})
   public void testManageProducts(String manageProductDemoTitle) 
      throws InterruptedException
   {
	  log.info("Start: testManageProducts");
      ManageProductsPage manageProducts = new ManageProductsPage(driver);

	  if(spSiteLoginPage.contains("qa2")) 
		  manageProducts.getCustomizeProductPopupQA2();
	  else
		  manageProducts.getCustomizeProductPopup();
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-2, Customize default product - Select customer product, JS errors for SP site #######");	  
	  log.info("End: testManageProducts");
	  log.info("--------------------------------------------");
   }
  
   @Test(description="7.1-3, Customize default product - Enter product info")
   @Parameters({"editProductTitle", "productName1", "fileImageName", "textBoxName",
	   "textBoxText", "dateFieldText", "newTabName"})
   public void testEditProductPopup(String editProductTitle, String productName1,String fileImageName, 
		   String textBoxName, String textBoxText, String dateFieldText, String newTabName) throws InterruptedException
   {

	     
	   log.info("Start: testEditProductPopup");
      EditProductPopup editProduct = new EditProductPopup(driver);
 //     AssertJUnit.assertEquals(editProductTitle, editProduct.getPopupTitle());
      
	  Thread.sleep(1000);	
      editProduct.setProductName(productName1+unicID); 
	  Thread.sleep(800);	
      editProduct.uploadProductIcon(fileImageName);	  
      editProduct.getCustomizationTab();
      editProduct.getNewTab();      
      editProduct.setNewTabName(newTabName);     
      editProduct.saveCustomization();  
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-3, Customize default product - Enter product info, JS errors for SP site #######");	  
	  log.info("End: testEditProductPopup");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="7.1-4, Customize default product - Product verification")
   @Parameters({"productName1", "newTabName", "textBoxName", "dateFieldText"})
   public void testProductVerification(String productName1, String newTabName, String textBoxName,
		   String dateFieldText) throws InterruptedException
   {
	   log.info("Start: testProductVerification");
	   productName1 = productName1 + unicID;
	   
	   EditProductPopup editProduct = new EditProductPopup(driver);	   
       ManageProductsPage manageProducts = new ManageProductsPage(driver);  

	   manageProducts.clickCustomizeBTSmoke();
	   Thread.sleep(1000);
	   try {
	       AssertJUnit.assertTrue(editProduct.checkIconDisplayed());	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Product icon does not displayed");
		   log.info("7.1 Product icon does not displayed");
		  
	   }
	   editProduct.getCustomization();  	   
	   editProduct.clickUserTab();	   
	   /*
	    * Temporary switched off 3/20
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(textBoxName, driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Customizad text area title not displayed");
		   log.info("7.1 Customizad text area title not displayed");
	   }
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(dateFieldText, driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Customizad date title not displayed");
		   log.info("7.1 Customizad date title not displayed");
	   }
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent("*", driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Required mark (*) does not displayed");
		   log.info("7.1 Required mark (*) does not displayed");
	   }
	   */	   
	   editProduct.clickCloseBT();  
	   Thread.sleep(1000);	 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-4, Customize default product - Product verification, JS errors for SP site #######");	  
	   log.info("End: testProductVerification");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.2-1, Creat new product based on default product - Select product")
   public void testCreateNewProduct() throws InterruptedException
   {
	   log.info("Start: testCreateNewProduct");
	   ManageProductsPage manageProducts = new ManageProductsPage(driver);
	   CreateNewProduct createNewProduct = new CreateNewProduct(driver);
	   
	   Thread.sleep(1000);	 
	   manageProducts.clickCreateNewProductBT();
	   Thread.sleep(800);
	   createNewProduct.clickEnvelopeIcon();	 

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.2-1, Creat new product based on default product - Select product, JS errors for SP site #######");
	   log.info("End: testCreateNewProduct");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.2-2, Create new product based on default product - Enter product info")
   @Parameters({"editProductTitle", "productName1", "fileImageName"})
   public void testCreateProductPopup(String editProductTitle, String productName1,
      String fileImageName) throws InterruptedException
   {
	   log.info("Start: testCreateProductPopup");
      EditProductPopup editProduct = new EditProductPopup(driver);
 //     AssertJUnit.assertEquals(editProductTitle, editProduct.getPopupTitle());      
	  editProduct.setProductNameInPopup(productName1 + unicID + "1");
	  editProduct.ulpoadProductIconInPopup(fileImageName);
      editProduct.getCustomizationTab();
      editProduct.getNewTabInPopup();
	  Thread.sleep(800); 
      editProduct.getBasicInfoTab();
      editProduct.getCustomizationTab();
      editProduct.saveCustomization();  
	  Thread.sleep(1200);
	  
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.2-2, Create new product based on default product - Enter product info, JS errors for SP site #######");	  
	   log.info("End: testCreateProductPopup");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.3-1, Creat new product based on empty product - Select product")
   public void testCreateNewProductOnBaseEmpty() throws InterruptedException
   {
	   log.info("Start: testCreateNewProductOnBaseEmpty");
	   ManageProductsPage manageProducts = new ManageProductsPage(driver);
	   CreateNewProduct createNewProduct = new CreateNewProduct(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   newHomePage.clickGearSubmenuItem(driver, "Request Forms");
	   Thread.sleep(600);	   	   
	   manageProducts.clickCreateNewProductBT();
	   Thread.sleep(800);
	   createNewProduct.clickEmptyIcon();	   		   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.3-1, Creat new product based on empty product - Select product, JS errors for SP site #######");	   
	   log.info("End: testCreateNewProductOnBaseEmpty");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.3-2, Creat new product based on empty product - Enter product info")
   @Parameters({"emptyProductName", "fileImageName"})
   public void testCreateProductOnBaseEmptyPopup(String emptyProductName, String fileImageName) throws InterruptedException
   {
	   log.info("Start: testCreateProductOnBaseEmptyPopup");
	   EditProductPopup editProduct = new EditProductPopup(driver);
	     
	   Thread.sleep(300);	   	   
	   editProduct.setProductName(emptyProductName + unicID);	
	   editProduct.uploadProductIcon(fileImageName);	
	   Thread.sleep(2000);	
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.3-2, Creat new product based on empty product - Enter product info, JS errors for SP site #######");	   
	   log.info("End: testCreateProductOnBaseEmptyPopup");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.3-3, Creat new product based on empty product - Customization tab")
   @Parameters({"newNameTextBox", "textTextBox"})
   public void testCreateProductOnBaseEmptyCustomization(String newNameTextBox, String textTextBox) throws InterruptedException
   {
	   log.info("Start: testCreateProductOnBaseEmptyCustomization");
	   EditProductPopup editProduct = new EditProductPopup(driver);	
	   	
	   Thread.sleep(1000);	
	   editProduct.getCustomization();  
	   Thread.sleep(1000);	  
	   if(browser.trim().equals("IE")) {
		   editProduct.dragAndDropTextBoxIE();
		   Thread.sleep(800);
		   editProduct.dragAndDropDataBoxIE();
	   } else {
		   editProduct.dragAndDropTextBox();
		   Thread.sleep(800);
		   editProduct.dragAndDropDataBox();
	   }	       
	   Thread.sleep(800);	   
	   editProduct.setNewNameTextBox(newNameTextBox);
	   Thread.sleep(1000);	   
	   editProduct.setTextBoxText(textTextBox);
	   Thread.sleep(700);
	   editProduct.clickCalculator();	   
	   editProduct.setDate();
	   Thread.sleep(800);
	   editProduct.saveCustomization();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.3-3, Creat new product based on empty product - Customization tab, JS errors for SP site #######");	   
	   log.info("End: testCreateProductOnBaseEmptyCustomization");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.3-4, Creat new product based on empty product - Product verification")
   @Parameters({})
   public void testProductOnBaseEmptyVerification() throws InterruptedException
   {	   
	   log.info("Start: testProductOnBaseEmptyVerification");
	   EditProductPopup editProduct = new EditProductPopup(driver);	   
       ManageProductsPage manageProducts = new ManageProductsPage(driver);  

       manageProducts.clickCustomizeEmptyProduct();
	   Thread.sleep(1000);
	   try {
	       AssertJUnit.assertTrue(editProduct.checkIconDisplayed());	      
	   } catch(Throwable e) {
		   System.out.println("7.3-4 Product icon does not displayed - Creat new product based on empty product");
		   log.info("7.3-4 Product icon does not displayed - Creat new product based on empty product");
	   }
	   editProduct.getCustomization();    
	   /*
	    * Temporary switched off 3/20
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(textBoxName, driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Customizad text area title not displayed");
		   log.info("7.1 Customizad text area title not displayed");
	   }
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(dateFieldText, driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Customizad date title not displayed");
		   log.info("7.1 Customizad date title not displayed");
	   }
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent("*", driver));	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Required mark (*) does not displayed");
		   log.info("7.1 Required mark (*) does not displayed");
	   }
	   */	   
	   editProduct.clickCloseBT();  
	   Thread.sleep(1000);	 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.3-4, Creat new product based on empty product - Product verification, JS errors for SP site #######");	   
	   log.info("End: testProductOnBaseEmptyVerification");
	   log.info("--------------------------------------------");
   }      
   
   @Test(description="7.5-1, Create New Product in Advanced Product Editor - get Empty product")
   public void testGetNewProductAdvancedEditor() throws InterruptedException
   {
	   log.info("Start: testGetNewProductAdvancedEditor");
	   ManageProductsPage managerProductsPage = new ManageProductsPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);	   
	   
	   Thread.sleep(1500);
	   if(browser.trim().equals("IE")) {
		   newHomePage.clickGearSubmenuItemIE();
	   } else {
	       newHomePage.clickGearSubmenuItem(driver, "Request Forms");
	   }
	   Thread.sleep(1600);	   
	   managerProductsPage.clickCreateNewProductBT();
	   Thread.sleep(2000);
	   managerProductsPage.clickEmptyProduct();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-1, Create New Product in ADvanced Product Editor - get Empty product, JS errors for SP site #######");	   
	   log.info("End: testGetNewProductAdvancedEditor");
	   log.info("--------------------------------------------");
   }

   @Test(description="7.5-2, Create New Product in Advanced Product Editor - Basic Information")
   @Parameters({"productName", "productImage"})
   public void testAdvancedEditorBasicInfo(String productName, String productImage) throws InterruptedException
   {
	   log.info("Start: testAdvancedEditorBasicInfo");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   
	   advancedEditor.putProductName(productName + unicID);
	   Thread.sleep(800);
	   advancedEditor.uploadProductImage(productImage);
	   Thread.sleep(1000);	
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-2, Create New Product in Advanced Product Editor - Basic Information, JS errors for SP site #######");	   
	   log.info("End: testAdvancedEditorBasicInfo");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-3, Create New Product in Advanced Product Editor - Customization Tab - Text Box")
   public void testCustomTabAdvancEditorTextBox() throws InterruptedException
   {
	   log.info("Start: testCustomTabAdvancEditorTextBox");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   	   
	   advancedEditor.clickCustomizationTab();
	   Thread.sleep(1000);	  
	   /*
	   try {
	       AssertJUnit.assertTrue(advancedEditor.getLebelLevel());	      
	   } catch(Throwable e) {
		   System.out.println("7.5-3, Required mark (*) does not displayed");
		   log.info("7.1 Required mark (*) does not displayed");
	   }
	   */	  	
	   if(browser.trim().equals("IE")) {
		   advancedEditor.dragAndDropTextAreaEI();
		   Thread.sleep(1200);
		   advancedEditor.clickPencilTextBoxIE();
	   } else {
			   advancedEditor.dragAndDropTextArea();
			   Thread.sleep(1200);
			   advancedEditor.clickPencilTextBox();
	   }
	   Thread.sleep(1000);	   
	   advancedEditor.selectRequired();
	   Thread.sleep(600);
	   advancedEditor.clickSaveBT();
	
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-3, Create New Product in Advanced Product Editor - Customization Tab - Text box, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorTextBox");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-4, Create New Product in Advanced Product Editor - Customization Tab - Date")
   public void testCustomTabAdvancEditorDate() throws InterruptedException
   {
	   log.info("Start: testCustomTabAdvancEditorDate");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   
	   advancedEditor.dragAndDropDate();
	   Thread.sleep(1200);	  	   
	   if(browser.trim().equals("IE")) {
		   advancedEditor.clickPencilDateIE();
	   } else
	       advancedEditor.clickPencilDate();
	   Thread.sleep(1200);	   
	   advancedEditor.selectNotShownOnClientView();
	   Thread.sleep(600);
	   advancedEditor.clickSaveBT();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-4, Create New Product in Advanced Product Editor - Customization Tab - Date, JS errors for SP site #######");	   	   
	   log.info("End: testCustomTabAdvancEditorDate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-5, Create New Product in Advanced Product Editor - Customization Tab - Number")
   @Parameters({"defaultValue"}) 
   public void testCustomTabAdvancEditorNumber(String defaultValue) throws InterruptedException
   {
	   log.info("Start: testCustomTabAdvancEditorNumber");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   
	   advancedEditor.dragAndDropName();
	   Thread.sleep(1200);	   
	   if(browser.trim().equals("IE")) {
		   advancedEditor.clickPencilNumberIE();
	   } else
	       advancedEditor.clickPencilNumber();
	   Thread.sleep(1000);	   
	   advancedEditor.putDefaultValue(defaultValue);
	   Thread.sleep(600);
	   advancedEditor.selectReadOnly();
	   Thread.sleep(600);
	   advancedEditor.clickSaveBT();
	   Thread.sleep(800);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-5, Create New Product in Advanced Product Editor - Customization Tab - Number, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorNumber");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-6, Create New Product in Advanced Product Editor - Customization Tab - Image and Dimentions")
   public void testCustomTabAdvancEditorAnotherElements() throws InterruptedException
   {
	   log.info("Start: testCustomTabAdvancEditorAnotherElements");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   
	   advancedEditor.dragAndDropImage();
	   Thread.sleep(1000);	   
	   advancedEditor.dragAndDropDimentions();
	   Thread.sleep(1000);	  
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-6, Create New Product in Advanced Product Editor - Customization Tab - Image and Dimentions, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorAnotherElements");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="7.5-7, Create New Product in Advanced Product Editor - Customizing number columns")
   public void testCustomTabAdvancEditorNumberCol() throws InterruptedException, AWTException
   {
	   log.info("Start: testCustomTabAdvancEditorNumberCol");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);	
	   
	   Thread.sleep(800);	   
	   advancedEditor.selectAll();
	   Thread.sleep(800);
	   try {	       	   
	       advancedEditor.selectOneColumn();	       
	       Alert alert = driver.switchTo().alert();
    	   alert.accept();
           page.closeModalPopup();
	   } catch(Throwable e) {
//		   e.printStackTrace();
	   }
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-7, Create New Product in Advanced Product Editor - Customizing number columns, JS errors for SP site #######");		   
	   log.info("End: testCustomTabAdvancEditorNumberCol");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="7.5-8, Create New Product in Advanced Product Editor - Add another tab")
   @Parameters({"tabName"})
   public void testCustomTabAdvancEditorAnotherTab(String tabName) throws InterruptedException, AWTException
   {
	   log.info("Start: testCustomTabAdvancEditorAnotherTab");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   
	   advancedEditor.addAnotherTab();
	   Thread.sleep(1000);	   
	   advancedEditor.clickPencilTab();
	   Thread.sleep(800);	   
	   advancedEditor.setTabName(tabName);	   
	   advancedEditor.selectNotShownOnClientView();
	   advancedEditor.saveTabAttrib();
	   Thread.sleep(1000);	   
	   advancedEditor.submitProduct();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-8, Create New Product in Advanced Product Editor - Add another tab, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorAnotherTab");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="7.5-9, Create New Product in Advanced Product Editor - Assigning created product to the buyer")
   @Parameters({"productName", "newBuyerSiteName"})
   public void testCustomTabAdvancEditorAssigningProductBuyer(String productName, String newBuyerSiteName) throws InterruptedException, AWTException
   {
	   log.info("Start: testCustomTabAdvancEditorAssigningProductBuyer");
	   SitesListTab sitesListTab = new SitesListTab(driver);
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   
	   Thread.sleep(800);
	   mainMenuPage.clickPoductsTab();
	   Thread.sleep(800);
	   mainMenuPage.assignProductToBuyerSite();
	   productName = productName + unicID;
	   Thread.sleep(1000);
	   if(browser.trim().equals("IE")) 
		   sitesListTab.assignProductToBuyerScroll(driver, productName);
	   else		      
	       sitesListTab.assignPoductToBuyer(driver, productName);	   	   	   
	   Thread.sleep(1400);
	   sitesListTab.clickAssignBT();
	   Thread.sleep(1500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-9, Create New Product in Advanced Product Editor - Assigning created product to the buyer, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorAssigningProductBuyer");
	   log.info("--------------------------------------------"); 
   }

   @Test(description="8.1-1, Create new Buyer site - Get buyer site popup")
   public void callCreateSitePopup() throws InterruptedException
   {
	   log.info("Start: callCreateSitePopup");
      NewHomePage newHomePage = new NewHomePage(driver);
  
      Thread.sleep(2000);
      newHomePage.clickCreateWorkspace();   
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 8.1-1, Create new Buyer site - Get buyer site popup, JS errors for SP site #######");		  
	  log.info("End: callCreateSitePopup");
	  log.info("--------------------------------------------"); 
   }
 
   @Test(description="8.1-2, Create new Buyer site - Enter site info")
   @Parameters({"createSitePopupTitle", "siteName"})
   public void testCreateSite(String createSitePopupTitle, String siteName) 
      throws InterruptedException
   {
	   log.info("Start: testCreateSite");
      CreateSitePopup createSitePopup = new CreateSitePopup(driver);
      
	  if(browser.trim().equals("IE")) {
		  Thread.sleep(1400);  
	  }
	  
      try{
         AssertJUnit.assertEquals(createSitePopupTitle, createSitePopup.getPopupTitle());
      } catch(Throwable e) {
	     System.out.println("8.1-2, Create new Buyer site: '" + createSitePopupTitle + "' popup name does not displayed");
	     log.info("8.1-2, Create new Buyer site: '" + createSitePopupTitle + "' popup name does not displayed");
      }
      createSitePopup.setPageName(siteName+unicID);
      createSitePopup.selectPrintersCategory();     
      createSitePopup.createSite(); 
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 8.1-2, Create new Buyer site - Enter site info, JS errors for SP site #######");	  
	  log.info("End: testCreateSite");
	  log.info("--------------------------------------------"); 
   }
   
   @Test(description="Go to Buyer site tabs")
   @Parameters({"siteName"})
   public void testGetBuyerSite(String siteName) throws InterruptedException
   {	  
	   BaseTestCases.testGetBuyerSite(siteName);
   }   

   @Test(description="8.2, Invite customers")
   @Parameters({"invalidNewCustomerEmail", "validDemoUserEmail", "validBuyerEmail", "invalidEmailMessage", "siteName"})
   public void testInviteCustomersPage(String invalidNewCustomerEmail,
      String validDemoUserEmail, String validBuyerEmail, String invalidEmailMessage, String siteName) throws InterruptedException
   {	
	   log.info("Start: testInviteCustomersPage");
      CustomeSitePage customeSitePage = new CustomeSitePage(driver);
      NewHomePage newHomePage = new NewHomePage(driver);
      
	  Thread.sleep(2400);   
	  if(browser.trim().equals("IE")) {
		  Thread.sleep(1400);
		  newHomePage.clickGearWorkspacesIE();
		  Thread.sleep(1400);
	  } else {
	      newHomePage.clickGearSubmenuItem(driver, "Workspaces");
	  }
	  newHomePage.clickPojectSPList(driver, siteName);
	  Thread.sleep(800);
      customeSitePage.getCustomersTab(); 
	  if(browser.trim().equals("IE")) 
		  Thread.sleep(1500);
      customeSitePage.inviteNewCuctomers();      
      customeSitePage.putNewCustomerEmail(invalidNewCustomerEmail);
      customeSitePage.clickAddBT();      
	  try {
	       AssertJUnit.assertTrue(Page.isTextPresent(invalidEmailMessage, driver));	      
	  } catch(Throwable e) {
		   System.out.println("8.2 Message about invalid email does not displayed");
		   log.info("8.2 Message about invalid email does not displayed");
	  }
      customeSitePage.putNewCustomerEmail(validDemoUserEmail);
      customeSitePage.clickAddBT();        
      customeSitePage.putNewCustomerEmail(validBuyerEmail+buyerEmail);
      Thread.sleep(500); 
      customeSitePage.clickAddBT();   
      Thread.sleep(500);
      try {
	       AssertJUnit.assertTrue(Page.isTextPresent(validDemoUserEmail, driver));	      
	  } catch(Throwable e) {
		   System.out.println("8.2 Already invited buyer email address does not displayed 1");
		   log.info("8.2 Already invited buyer email address does not displayed 1");
	  }
	  try {
	       AssertJUnit.assertTrue(Page.isTextPresent(validBuyerEmail+buyerEmail, driver));	      
	  } catch(Throwable e) {
		   System.out.println("8.2 New buyer email address does not displayed 1");
		   log.info("8.2 New buyer email address does not displayed 1");
	  }
      customeSitePage.sendInvitationEmail();       
      try {
	       AssertJUnit.assertTrue(Page.isTextPresent(validDemoUserEmail, driver));	      
	  } catch(Throwable e) {
		   System.out.println("8.2 Already invited buyer email address does not displayed 2");
		   log.info("8.2 Already invited buyer email address does not displayed 2");
	  }
	  try {
	       AssertJUnit.assertTrue(Page.isTextPresent(validBuyerEmail+buyerEmail, driver));	      
	  } catch(Throwable e) {
		   System.out.println("8.2 New buyer email address does not displayed 2");
		   log.info("8.2 New buyer email address does not displayed 2");
	  }
	  Thread.sleep(2000);	  
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 8.2, Invite customers, JS errors for SP site #######");
	  log.info("End: testInviteCustomersPage");
	  log.info("--------------------------------------------"); 
   }
   
   @Test(description="8.3 Edit buyer site")
   @Parameters({"newBuyerSiteName", "newBuyerSiteLogo", "siteName"})
   public void testEditBuyerSiteSP(String newBuyerSiteName, String newBuyerSiteLogo, String siteName) throws InterruptedException
   {
	   log.info("Start: testEditBuyerSiteSP");
	   String oldBuyerSiteName;
	   
	   newBuyerSiteName = newBuyerSiteName + unicID;
	   CustomeSitePage customeSitePage = new CustomeSitePage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);

	   newHomePage.clickCustomizeBuyerSiteHovering();
	   Thread.sleep(1000);  	     
	   oldBuyerSiteName = customeSitePage.getUriSPSite();
	   oldBuyerSiteURI = customeSitePage.getOverviewLogoImageAttribute();
	   customeSitePage.clickEditSiteBT();
	   Thread.sleep(500);  
	   customeSitePage.setNewBuyerSiteName(newBuyerSiteName);
	   Thread.sleep(500);  
	   customeSitePage.uploadBuyerSiteLogo(newBuyerSiteLogo);
	   if(browser.trim().equals("IE")) {
		  Thread.sleep(2000);
	      customeSitePage.clickSaveBTIE();
	   } else {
	      Thread.sleep(300);	   
	      customeSitePage.clickSaveBT();
	   }
	   Thread.sleep(3000);
	   try {
	       AssertJUnit.assertEquals(oldBuyerSiteName, customeSitePage.getUriSPSite());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 URI: Old buyer site name " + oldBuyerSiteName + " displayed");
		   log.info("8.3 URI: Old buyer site name " + oldBuyerSiteName + " displayed");
	   }
	   if (oldBuyerSiteURI.equals(customeSitePage.getOverviewLogoImageAttribute())){
		   System.out.println("8.3 The new logo image does not displayed in the Overview page");
		   log.info("8.3 The new logo image does not displayed in the Overview page");	          
	   }

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.3, Edit buyer site, JS errors for SP site #######");	
	   log.info("End: testEditBuyerSiteSP");
	   log.info("--------------------------------------------"); 
   }

   @Test(description="8.4, Preview buyer site first time")
   @Parameters({"newBuyerSiteName"})
   public void testPreviewSite(String newBuyerSiteName) throws InterruptedException
   { 
      MainMenuPage menuPage = new MainMenuPage(driver);
      NewHomePage newHomePage = new NewHomePage(driver);

	  menuPage.clickProjectMenu();
	  Thread.sleep(1400);
 	  newHomePage.clickClientViewBTHovering();
 	  Thread.sleep(1000);

      errorIndex = jsErrorIndex;
 	  Page.jsErrorReporter(driver, errorIndex,"####### TC 8.4, Preview buyer site first time, JS errors for SP site #######");
   }
 
   @Test(description="8.4, Preview buyer site second time")
   @Parameters({"newBuyerSiteName"})
   public void testCustomeSitePage(String newBuyerSiteName) throws InterruptedException
   {     
      CustomeSitePage customeSitePage = new CustomeSitePage(driver);
      SitesListTab sitesListTab = new SitesListTab(driver);      
  
     newBuyerSiteName = newBuyerSiteName + unicID;
     customeSitePage.rollUpSiteFrame();     
	 Thread.sleep(1200);
     if(browser.trim().equals("IE")) {
    	 Thread.sleep(1200);
     }
	 customeSitePage.turnOffSiteFrame();
	 Thread.sleep(2500);
     sitesListTab.getSitePreviewPopup(); 
     customeSitePage.rollUpSiteFrame();    
	 Thread.sleep(2000);
	 customeSitePage.turnOffSiteFrame();
	 Thread.sleep(2500);
	 
     errorIndex = jsErrorIndex;
 	 Page.jsErrorReporter(driver, errorIndex,"####### TC 8.4 Preview buyer site second time, JS errors for SP site #######");
 	 System.out.println("jsErrorIndex index = " + jsErrorIndex);
   }
   
   
   @Test(description="8.5, Create new Project in Preview mode")
   @Parameters({"projNameSP"})
   public void testCreateNewProjectPreviewMode(String projNameSP) throws InterruptedException
   {
	   projNameSP = projNameSP + unicID;
	   
	   SitesListTab sitesListTab = new SitesListTab(driver);
	   CreateProjectPreviewMode createProject = new CreateProjectPreviewMode(driver);
	   
	   Thread.sleep(1000);	    
	   sitesListTab.getSitePreviewPopup();
	   Thread.sleep(1000);   
	   createProject.clickBrochureLink();	   
	   createProject.setProjectName(projNameSP);
	   createProject.callCalendar();	   
	   createProject.setComplationDate();
	   Thread.sleep(1000);		   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.5, Create new Project in Preview mode, JS errors for SP site #######");
   }
   
   @Test(description="9.1, Global invite - Invite co-workers")
   @Parameters({"invalidNewCustomerEmail", "validDemoUserEmail", "validSPEmail", "wrongEmailMessage", "confirmInviteMessage"})
   public void testInviteCoworkers(String invalidNewCustomerEmail, String validDemoUserEmail,
		   String validSPEmail, String wrongEmailMessage, String confirmInviteMessage) throws InterruptedException
   {
	   String coworkerEmail = validDemoUserEmail + "," + validSPEmail;
	   
	   Map<String, String> coworkersEmailM = new HashMap<String, String>();
	   coworkersEmailM.put("one", validSPEmail);
	   coworkersEmailM.put("two", coworkerEmail);
	   
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   Invite invite = new Invite(driver);
	   
	   mainMenuPage.clickInviteBT();
	   invite.clickInviteCoworkerTab();
	   
	   try{
		   Iterator iter = coworkersEmailM.entrySet().iterator();
		   while(iter.hasNext()){
			   Map.Entry mEntry = (Map.Entry)iter.next();
			   invite.putCoworkerEmail((String) mEntry.getValue());
			   invite.clickSendInvitationBT();
			   Thread.sleep(800);
			   try {
			       AssertJUnit.assertTrue(Page.isTextPresent(confirmInviteMessage, driver));	      
			   } catch(Throwable e) {
				   System.out.println("9.1 'Invitation sent successfully!' message not displayed");
				   log.info("9.1 'Invitation sent successfully!' message not displayed");
			   }    
		     }		   
		   }catch(Throwable e) {
               System.out.println(e.toString());
		   }
	   
	   // invalid email
	   invite.putCoworkerEmail(invalidNewCustomerEmail);
	   invite.clickSendInvitationBT();
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(wrongEmailMessage, driver));	      
		   } catch(Throwable e) {
			  System.out.println("9.2 '" + wrongEmailMessage + "." + "' message not displayed");
			  log.info("9.2 '" + wrongEmailMessage + "." + "' message not displayed");
		   }
  
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 9.1, Global invite - Invite co-workers, JS errors for SP site #######");	   
   }
   
   @Test(description="9.2, Global invite - Invite customers")
   @Parameters({"invalidNewCustomerEmail", "validDemoUserEmail", "wrongEmailMessage", "confirmInviteMessage", 
	   "validBuyerEmail", "selectSiteMessage", "defaultSiteMessage", "emailMessage"})
   public void testInviteCustomers(String invalidNewCustomerEmail, String validDemoUserEmail,
		   String wrongEmailMessage, String confirmInviteMessage, String validBuyerEmail, 
		   String selectSiteMessage, String defaultSiteMessage, String emailMessage) throws InterruptedException
   {
	   String buyerEmails = "Mike Nipon " + validBuyerEmail + "@gmail.com, " + validDemoUserEmail;
	   
	   Invite invite = new Invite(driver);
	   
	   // Customer first name, last name, new email and already used email	   
	   invite.clickInviteCustomerTab();
       invite.putCustomersEmail(buyerEmails);
       invite.selectFirstSite();
	   Thread.sleep(300);
	   invite.clickCustomerSend();	   
	   Thread.sleep(300);
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(confirmInviteMessage, driver));	      
		   } catch(Throwable e) {
			  System.out.println("9.2 'Invitation sent successfully!' message not displayed");
			  log.info("9.2 'Invitation sent successfully!' message not displayed");
		   }    	   
	   // Valid customer email, do not selected site
       invite.putCustomersEmail(validDemoUserEmail);
	   invite.selectSite(defaultSiteMessage);
	   Thread.sleep(300);
	   invite.clickCustomerSend();	   
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(selectSiteMessage, driver));	      
		   } catch(Throwable e) {
			  System.out.println("9.2 'Please select a site then invite!' message not displayed");
			  log.info("9.2 'Please select a site then invite!' message not displayed");
		   }  
	   Thread.sleep(300);	   
	   // Invalid customer email, selected site
       invite.putCustomersEmail(invalidNewCustomerEmail);
       invite.selectFirstSite();
	   invite.clickCustomerSend();	   
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(wrongEmailMessage, driver));	      
		   } catch(Throwable e) {
			  System.out.println("9.2 '" + wrongEmailMessage + "." + "' message not displayed");
			  log.info("9.2 '" + wrongEmailMessage + "." + "' message not displayed");
		   } 	   	   
	   // No email, selected site
	   invite.putCustomersEmail("");
	   invite.selectFirstSite();
	   invite.clickCustomerSend();
	   
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(emailMessage, driver));	      
		   } catch(Throwable e) {
			  System.out.println("9.2 '" + emailMessage + "' message not displayed");
			  log.info("9.2 '" + emailMessage + "' message not displayed");
		   } 
	   invite.clickCloseLink();	    
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 9.2, Global invite - Invite customers, JS errors for SP site #######");	   
   }
   
   @Test(description="SP Create Project - product 1")
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", 
	   "specDescrSP", "fileForProject1"})
   public void testSPcreateProjectProjectWizardWF(String projNameSP, String descriptionNameSP,
		   String sku, String referenceNumber, String quantSP, String specDescrSP,
		   String fileForProject1) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPcreateProjectProjectWizardWF");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   	    
	   brochurePopup.setProjectName(projNameSP); 
	   Thread.sleep(500);   	    
	   brochurePopup.callCalendar();	                
	   brochurePopup.setComplationDate();   
	   brochurePopup.getNextTab();	    
	   brochurePopup.putDescriptionName(descriptionNameSP); 
	   brochurePopup.putSKU(sku);
	   brochurePopup.putRefNumber(referenceNumber);
	   brochurePopup.putQuant1(quantSP);
	   brochurePopup.putSpecDescription(specDescrSP);    
	   brochurePopup.getFilesTab();
	   brochurePopup.getReviewAndSubmitTab();	    
       brochurePopup.clickAddProductsBT();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-2, SP Create Project - product 1, JS errors for SP site #######");	
	   log.info("End: testSPcreateProjectProjectWizardWF");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP Create Project - product 2")
   @Parameters({"descriptionNameSP", "sku", "referenceNumber", "quantSP", "specDescrSP", "newProjectNameSP"})
   public void testCreateProductIntoCreateProject(String descriptionNameSP, String sku, String referenceNumber, 
		   String quantSP, String specDescrSP, String newProjectNameSP) throws InterruptedException
   {  
	   log.info("Start: testCreateProductIntoCreateProject");
	   CreateNewProduct newProduct = new CreateNewProduct(driver);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   Thread.sleep(500);
	   newProduct.clickBrochureIcon();	   
	   Thread.sleep(500);
	   brochurePopup.clickSpecDescription();	   
	   brochurePopup.putDescriptionName(descriptionNameSP + 1); 
	   brochurePopup.putSKU(sku);
	   brochurePopup.putRefNumber(referenceNumber);
	   brochurePopup.putQuant1(quantSP);
	   brochurePopup.getReviewAndSubmitTab();	    
	   brochurePopup.clickProjectInfoEditBT();	    
	   brochurePopup.setProjectName(newProjectNameSP);
	   brochurePopup.getReviewAndSubmitTab();	    
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(2500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-3, Create Project - product 2, JS errors for SP site #######");	
	   log.info("End: testCreateProductIntoCreateProject");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP Create Project - Project verification" )
   @Parameters({"newProjectNameSP", "descriptionNameSP", "fileProject1", "fileName1"})
   public void testCreateProjectVerification(String newProjectNameSP, String descriptionNameSP,
		   String fileProject1, String fileName1) throws InterruptedException, IOException, AWTException
   {
	   log.info("Start: testCreateProjectVerification");
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   Page page = new Page(driver);
	   
	   Thread.sleep(2000); 
	   
	   mainMenu.clickProjectMenu();
	   Thread.sleep(3000); 	   
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project status 'New' does not displayed");
			  log.info("10.0 Project status 'New' does not displayed");
		   } 
	   try {
		      AssertJUnit.assertTrue(newProjectNameSP.trim().equals(projectFrame.getProjectName()));	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Correct project name does not displayed");
			  log.info("10.0 Correct project name does not displayed");
		   } 
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1000);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSP, driver));	    
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project spec " + descriptionNameSP + " does not displayed");
			  log.info("10.0 Project spec " + descriptionNameSP + " does not displayed");
		   } 
	   descriptionNameSP = descriptionNameSP + 1;
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSP, driver));	   
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project spec " + descriptionNameSP + " does not displayed");
			  log.info("10.0 Project spec " + descriptionNameSP + " does not displayed");
		   } 	   	   
	   projectFrame.clickFilesTab();
	   Thread.sleep(1000);	   
//	   page.openFileExplorer();	   	
//	   System.out.println("1st uploaded file name is: " + projectFrame.getUploadedFileName33());
	   try{
	       AssertJUnit.assertEquals(fileProject1, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.0 Uploaded file name " + fileProject1 + " does not displayed");
	    	  log.info("10.0 Uploaded file name " + fileProject1 + " does not displayed");
	    	}	   
//	   page.mouseMove();
	   /* Temporary switched off 6/18/13  +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	   try {
		      AssertJUnit.assertTrue(projectFrame.getUploadedFileName(fileProject1));   
		   } catch(Throwable e) {
			  System.out.println("10.0 Uploaded file name " + fileProject1 + " does not displayed");
			  log.info("10.0 Uploaded file name " + fileProject1 + " does not displayed");
		   } 	
	   */
	   projectFrame.clickDownloadFileIcon(driver, fileProject1);
	   Thread.sleep(800);
	   page.downloadFile();
	   // file delete	   
       Thread.sleep(1500);
	   projectFrame.clickDeleteFileIcon(driver, fileName1);
	   Thread.sleep(600);	   
       Alert alert = buyerDriver.switchTo().alert();
	   alert.accept();
 	   page.closeModalPopup();
	   Thread.sleep(3000);	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0, SP Project verification, JS errors for SP site #######");	
	   log.info("End: testCreateProductIntoCreateProject");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP Create Project - Project verification" )
   @Parameters({"newProjectNameSP", "descriptionNameSP", "fileProject1", "fileName1"})
   public void testCreateProjectVerificationWF(String newProjectNameSP, String descriptionNameSP,
		   String fileProject1, String fileName1) throws InterruptedException, IOException, AWTException
   {
	   log.info("Start: testCreateProjectVerificationWF");
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(2000); 
	   
	   mainMenu.clickProjectMenu();
	   Thread.sleep(3000); 	   
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project status 'New' does not displayed");
			  log.info("10.0 Project status 'New' does not displayed");
		   } 
	   try {
		      AssertJUnit.assertTrue(newProjectNameSP.trim().equals(projectFrame.getProjectName()));	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Correct project name does not displayed");
			  log.info("10.0 Correct project name does not displayed");
		   } 
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1000);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSP, driver));	    
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project spec " + descriptionNameSP + " does not displayed");
			  log.info("10.0 Project spec " + descriptionNameSP + " does not displayed");
		   } 
	   descriptionNameSP = descriptionNameSP + 1;
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSP, driver));	   
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project spec " + descriptionNameSP + " does not displayed");
			  log.info("10.0 Project spec " + descriptionNameSP + " does not displayed");
		   } 	   	   
	   projectFrame.clickFilesTab();
	   Thread.sleep(1000);	     

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0, SP Project verification, JS errors for SP site #######");		
	   log.info("End: testCreateProjectVerificationWF");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-1 SP Create Project Adv. Editor - step 1")
   @Parameters({"validBuyerEmail"})
   public void testSPcreateProjectAdvEd(String validBuyerEmail) throws InterruptedException
   {	   
	   log.info("Start: testSPcreateProjectAdvEd");
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   
	   Thread.sleep(1000);
	   validBuyerEmail = validBuyerEmail + "+" + unicID + "@gmail.com";
	   	   
	   newProject.clicklCreateProjectBT();	    
	   newProject.enterValidClientEmail(validBuyerEmail);
	   Thread.sleep(400);
	   newProject.clickFindBT(); 
	   Thread.sleep(400);
	   newProject.selectCertainSite();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-1, SP Create Project - step 1, JS errors for SP site #######");	
	   log.info("End: testSPcreateProjectAdvEd");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-2 SP Create Project Adv. Editor - step 1")
   @Parameters({"productName"})
   public void testSPcreateProjectAdvEdSelectProduct(String productName) throws InterruptedException
   {
	   log.info("Start: testSPcreateProjectAdvEdSelectProduct");
	   CreateNewProject newProject = new CreateNewProject(driver);
	   
	   //resize frame
	   Thread.sleep(1000);
	   productName = productName + unicID;
	   Thread.sleep(1000);
	   newProject.selectAdvForm(driver, productName);
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-2 SP Create Project Adv. Editor - step 1 #######");	
	   log.info("End: testSPcreateProjectAdvEdSelectProduct");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-3 SP Create Project Adv. Editor - step 2")
   @Parameters({"projNameSPAdv", "descriptionNameSPAdv", "referenceNumber", "quantSP"})
   public void testSPcreateProjectAdvEdProjectWizard(String projNameSPAdv, String descriptionNameSPAdv, String referenceNumber,
		String quantSP) throws InterruptedException
   {
	   log.info("Start: testSPcreateProjectAdvEdProjectWizard");
	   projNameSPAdv = projNameSPAdv + unicID;
	   
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   Thread.sleep(500);   	    
	   brochurePopup.callCalendar();	
	   brochurePopup.setNextMonth();
	   brochurePopup.setComplationDate();	   	   
	   brochurePopup.getNextTab();	    
       brochurePopup.setTextBoxText(descriptionNameSPAdv);
	   Thread.sleep(500);   	    
	   brochurePopup.callCalendarAdv();	               
	   brochurePopup.setComplationDate();	   
	   brochurePopup.setNewNumber(referenceNumber);	   
	   brochurePopup.setNewDimention(quantSP);
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-3 SP Create Project Adv. Editor - step 2 #######");	
	   log.info("End: testSPcreateProjectAdvEdProjectWizard");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-4 SP Create Project Adv. Editor - upload file")
   @Parameters({"fileForProject3", "fileForProject5"})
   public void testUploadFile(String fileForProject3, String fileForProject5) throws InterruptedException, AWTException
   {
	   log.info("Start: testUploadFile");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   	   
	   brochurePopup.clickFilesTabAdvEd();
	   Thread.sleep(1000); 
	   /* Working for WD 2.33.0               */
	   page.uploadFileModalWindow(fileForProject5);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(1000);
	   page.robotUpload();
	   Thread.sleep(3000);	   
	   page.uploadFileModalWindow(fileForProject3);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(1000);
	   page.robotUpload();
	   Thread.sleep(3000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-4 SP Create Project Adv. Editor - upload file #######");	
	   log.info("End: testUploadFile");
	   log.info("--------------------------------------------"); 
   }  
   
   @Test(description="10.0-5 SP Create Project Adv. Editor - Add product")
   @Parameters({"projNameSPAdv", "reqField"})
   public void testSPcreateProjectAdvEdAddProduct(String projNameSPAdv, String reqField) throws InterruptedException
   {
	   log.info("Start: testSPcreateProjectAdvEdAddProduct");
	   String url = driver.getCurrentUrl();
	   
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   brochurePopup.getReviewAndSubmitTab();	    
	   brochurePopup.getReviewAndSubmitTab();	   
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(800);
	   try {
		    AssertJUnit.assertTrue(Page.isTextPresent(reqField, driver));	
		   } catch(Throwable e) {
			    System.out.println("10.0-5, Error message " + reqField + " does not displayed");
			    log.info("10.0-5 Error message " + reqField + " does not displayed");
		        }      
	   brochurePopup.setProjectName(projNameSPAdv);
	   brochurePopup.getReviewAndSubmitTab();
	   if(url.toLowerCase().contains(unicID)) {
           brochurePopup.clickAddProductsBT();	
	       Thread.sleep(1500);
	   } else {
    	   brochurePopup.clickSubmitBT();
    	   Thread.sleep(2000);
	   }		       
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-5 SP Create Project Adv. Editor - Add product #######");	  
	   log.info("End: testSPcreateProjectAdvEdAddProduct");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-6 SP Create Project Adv. Editor - Add product, fill out product fields ")
   @Parameters({"descriptionNameSP", "sku", "referenceNumber", "quantSP"})
   public void testCreateProductIntoCreateProjectAdvEd(String descriptionNameSP, String sku, String referenceNumber, 
		   String quantSP) throws InterruptedException
   {
	  log.info("Start: testCreateProductIntoCreateProjectAdvEd");
      BaseTestCases.testCreateProductIntoCreateProject(descriptionNameSP, sku, referenceNumber, 
   		   quantSP);
	   
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-6, SP Create Project Adv. Editor - Add product, fill out product fields, JS errors for SP site #######");		   
	  log.info("End: testCreateProductIntoCreateProjectAdvEd");
	  log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-7 SP Create Project Adv. Editor - Edit product spec and submit project ")
   public void testSPcreateProjectProductSpecEdit() throws InterruptedException 
   {	   
	   log.info("Start: testSPcreateProjectProductSpecEdit");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   Thread.sleep(500);	    
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(2000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-7 SP Create Project Adv. Editor - Edit product spec and submit project #######");
	   log.info("End: testSPcreateProjectProductSpecEdit");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.0-8 SP Create Project Adv. Editor - Project verification")
   @Parameters({"projNameSPAdv", "productName", "descriptionNameSPAdv", "fileProject3", "fileForProject4", "fileName5", "newFileName"})
   public void testSPProjectVerificationAdvEd(String projNameSPAdv, String productName, String descriptionNameSPAdv,
		   String fileProject3, String fileForProject4, String fileName5, String newFileName) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPProjectVerificationAdvEd");
	   String productionSpec1 = projNameSPAdv + " - " + productName + unicID;
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(1000);
	   BaseTestCases.testCreateProjectVerification(projNameSPAdv, productionSpec1, descriptionNameSPAdv, fileProject3);
	   Thread.sleep(500);
	   projectFrame.clickFilesTab();
	   Thread.sleep(4000);		   	   
	   try{
	       AssertJUnit.assertEquals(fileName5, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.x Uploaded file name " + fileName5 + " does not displayed");
	    	  log.info("10.x Uploaded file name " + fileName5 + " does not displayed");
	    	}   
	   try{
	       AssertJUnit.assertEquals(fileProject3, projectFrame.getUploadedSecondFileName().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.x Uploaded file name " + fileProject3 + " does not displayed");
	    	  log.info("10.x Uploaded file name " + fileProject3 + " does not displayed");
	    	}	   
       // upload file
	   page.uploadFileModalWindow(fileForProject4);
	   projectFrame.clickFileUploadSP();
	   Thread.sleep(1000);
	   page.robotUpload();
       Thread.sleep(4000);
       if(!browser.trim().equals("IE")) {
	      // download file and close viewer window
          projectFrame.clickDownloadFileIcon(driver, fileProject3);
	      Thread.sleep(800);
          page.downloadFile();
       }
       // file rename
       projectFrame.fileRename(newFileName);
       if(browser.trim().equals("IE")) {
          projectFrame.clickBody();
       } else {
          page.closeModalPopup();
       }
	   Thread.sleep(2500);
       // sorting
       projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   // delete file
	   projectFrame.clickDeleteFileIcon(driver, fileProject3);
	   Thread.sleep(600);
       Alert alert = driver.switchTo().alert();
	   alert.accept();
 	   page.closeModalPopup();
	   Thread.sleep(2000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-7, SP Create Project Adv. Editor - Project verification, JS errors for SP site #######");	   
	   log.info("End: testSPProjectVerificationAdvEd");
	   log.info("--------------------------------------------"); 
   }  
   
   @Test(description="10.0-8 SP Create Project Adv. Editor - Project verification")
   @Parameters({"projNameSPAdv", "productName", "descriptionNameSPAdv", "fileProject3", "fileForProject4", "fileName5", "newFileName"})
   public void testSPProjectVerificationAdvEdOB(String projNameSPAdv, String productName, String descriptionNameSPAdv,
		   String fileProject3, String fileForProject4, String fileName5, String newFileName) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPProjectVerificationAdvEdOB");
	   String productionSpec1 = projNameSPAdv + " - " + productName + unicID;
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(2000);   
	   mainMenu.clickProjectMenu();
	   Thread.sleep(1400); 	   
	   newHomePage.clickProjectInListNew();
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("10.x Project status 'New' does not displayed");
			  log.info("10.x Project status 'New' does not displayed");
		   } 	   	   
	   /*
	   try {
		      AssertJUnit.assertTrue(projNameSPAdv.trim().equals(projectFrame.getProjectName()));	      
		   } catch(Throwable e) {
			  System.out.println("10.x Correct project name does not displayed");
			  log.info("10.x Correct project name does not displayed");
		   } 
		   */
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1500);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(productionSpec1, driver));	    
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.x Production spec " + productionSpec1 + " does not displayed");
			  log.info("10.x Production spec " + productionSpec1 + " does not displayed");
		   } 
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSPAdv, driver));	   
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.x Production spec " + descriptionNameSPAdv + " does not displayed");
			  log.info("10.x Production spec " + descriptionNameSPAdv + " does not displayed");
		   } 
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		   
	   Thread.sleep(500);
	   projectFrame.clickFilesTab();
	   Thread.sleep(4000);		   	   
	   try{
	       AssertJUnit.assertEquals(fileName5, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.x Uploaded file name " + fileName5 + " does not displayed");
	    	  log.info("10.x Uploaded file name " + fileName5 + " does not displayed");
	    	}   
	   try{
	       AssertJUnit.assertEquals(fileProject3, projectFrame.getUploadedSecondFileName().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.x Uploaded file name " + fileProject3 + " does not displayed");
	    	  log.info("10.x Uploaded file name " + fileProject3 + " does not displayed");
	    	}	   
       // upload file
	   page.uploadFileModalWindow(fileForProject4);
	   projectFrame.clickFileUploadSP();
	   Thread.sleep(1000);
	   page.robotUpload();
       Thread.sleep(4000);
       if(!browser.trim().equals("IE")) {
	      // download file and close viewer window       
          projectFrame.clickDownloadFileIcon(driver, fileProject3);
	      Thread.sleep(800);
          page.downloadFile();
       }
       // file rename
       projectFrame.fileRename(newFileName);
       if(browser.trim().equals("IE")) {
           projectFrame.clickBody();
       } else {
           page.closeModalPopup();
       }
	   Thread.sleep(2500);
       // sorting
       projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   // delete file
	   projectFrame.clickDeleteFileIcon(driver, fileProject3);
	   Thread.sleep(600);
       Alert alert = driver.switchTo().alert();
	   alert.accept();
 	   page.closeModalPopup();
	   Thread.sleep(2000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-8, SP Create Project Adv. Editor - Project verification, JS errors for SP site #######");	   
	   log.info("End: testSPProjectVerificationAdvEdOB");
	   log.info("--------------------------------------------"); 
   }     
   
   @Test(description="10.0-8 SP Create Project Adv. Editor - Project verification")
   @Parameters({"projNameSPAdv", "productName", "descriptionNameSPAdv", "fileProject3", "fileForProject4", "fileName5", "newFileName"})
   public void testSPProjectVerificationAdvEdWF(String projNameSPAdv, String productName, String descriptionNameSPAdv,
		   String fileProject3, String fileForProject4, String fileName5, String newFileName) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPProjectVerificationAdvEdWF");
	   String productionSpec1 = projNameSPAdv + " - " + productName + unicID;
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);	   
	   Thread.sleep(1000);
	   /*
	   BaseTestCases.testCreateProjectVerification(projNameSPAdv, productionSpec1, descriptionNameSPAdv, fileProject3);
	   */
	   mainMenu.clickProjectMenu();
	   Thread.sleep(800); 
	   	   
       newHomePage.clickProjectInList(driver, projNameSPAdv);
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("10.x Project status 'New' does not displayed");
			  log.info("10.x Project status 'New' does not displayed");
		   } 	   	   
	   /*
	   try {
		      AssertJUnit.assertTrue(projNameSPAdv.trim().equals(projectFrame.getProjectName()));	      
		   } catch(Throwable e) {
			  System.out.println("10.x Correct project name does not displayed");
			  log.info("10.x Correct project name does not displayed");
		   } 
		   */
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1500);
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(productionSpec1, driver));	    
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.x Production spec " + productionSpec1 + " does not displayed");
			  log.info("10.x Production spec " + productionSpec1 + " does not displayed");
		   } 
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSPAdv, driver));	   
//		   AssertJUnit.assertTrue(projectFrame.getProjectSpecName(descriptionNameSP));      
		   } catch(Throwable e) {
			  System.out.println("10.x Production spec " + descriptionNameSPAdv + " does not displayed");
			  log.info("10.x Production spec " + descriptionNameSPAdv + " does not displayed");
		   } 
	   projectFrame.clickFilesTab();
	   Thread.sleep(1000);	  	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-8, SP Create Project Adv. Editor - Project verification, JS errors for SP site #######");	   
	   log.info("End: testSPProjectVerificationAdvEdWF");
	   log.info("--------------------------------------------"); 
   }  
   
   @Test(description="Go to home page second time")
   public void testGetHomePage2() throws InterruptedException
   {
	   log.info("Start: testGetHomePage");
	   MainMenuPage menuPage = new MainMenuPage(driver);
	   
	   menuPage.clickProjectMenu();
	   Thread.sleep(1400);
	   log.info("End: testGetHomePage");
	   log.info("--------------------------------------------"); 
   } 
   
   @Test(description="10.1-1 SP Create Project Hovering in home page - step 1, client selecting")
   @Parameters({"validBuyerEmail"})
   public void testSPCreateProjectSelectClient(String validBuyerEmail) throws InterruptedException
   {	   
	   log.info("Start: testSPCreateProjectSelectClient");
	   validBuyerEmail = validBuyerEmail + "+" + unicID + "@gmail.com";
	   	
	   BaseTestCases.testSPCreateProjectSelectBuyerAndWorkspace(validBuyerEmail); 

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-1, SP Create Project - step 1, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectSelectClient");
	   log.info("--------------------------------------------"); 
   }  
   
   @Test(description="10.1-2 SP Create Project Hovering in home page - step 2, filling out Project info tab")
   @Parameters({"secondSPprojectName", "specDescrSPAdv"})
   public void testSPCreateProjectSetProjectInfo(String secondSPprojectName, String specDescrSPAdv) throws InterruptedException
   {	   
	   log.info("Start: testSPCreateProjectSetProjectInfo");	
	   BaseTestCases.testSPSetProjectInfo(secondSPprojectName, specDescrSPAdv); 

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.1-2 SP Create Project Hovering in home page - step 2, filling out Project info tab, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectSetProjectInfo");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.1-3 SP Create Project Hovering in home page - step 3, project spec")
   @Parameters({"newSpecName", "referenceNumberSpec2", "printQuant", "mailQuant"})
   public void testSPCreateProjectSpec(String newSpecName, String referenceNumberSpec2, String printQuant, String mailQuant) throws InterruptedException
   {	   
	   log.info("Start: testSPCreateProjectSpec");
	   BaseTestCases.testSPSetSpecDiscription(newSpecName, referenceNumberSpec2, printQuant, mailQuant);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-3 SP Create Project Hovering in home page - step 3, project spec, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectSpec");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.1-4 SP Create Project Hovering in home page - step 4, select size")
   public void testSPCreateProjectSelectSize() throws InterruptedException
   {	  
	   log.info("Start: testSPCreateProjectSelectSize");
	   BaseTestCases.testSPSetSizeAndStock();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-4 SP Create Project Hovering in home page - step 4, select size, JS errors for SP site #######");	   
       log.info("End: testSPCreateProjectSelectSize");
       log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.1-5 SP Create Project Hovering in home page - step 5, upload file")
   @Parameters({"jpgFile"})
   public void testSPCreateProjectUploadFile(String jpgFile) throws InterruptedException, AWTException
   {	
	   log.info("Start: testSPCreateProjectUploadFile");
	   BaseTestCases.testUploadFile(jpgFile);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-5 SP Create Project Hovering in home page - step 5, upload file, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectUploadFile");
       log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.1-6 SP Create Project Hovering in home page - step 6, submit project")
   public void testSPCreateProjectSubmitProject() throws InterruptedException, AWTException
   {	  
	   log.info("Start: testSPCreateProjectSubmitProject");
	   BaseTestCases.testSubmitProject();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-5 SP Create Project Hovering in home page - step 5, submit project, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectSubmitProject");
       log.info("--------------------------------------------"); 
   }
   
   @Test(description="10.1-7 SP Create Project Hovering in home page - step 7, open project and assign client")
   @Parameters({"secondSPprojectName", "validBuyerEmail"})
   public void testSPCreateProjectOpenProjectAndAssignClient(String secondSPprojectName, String validBuyerEmail) throws InterruptedException, AWTException
   {	  
	   log.info("Start: testSPCreateProjectOpenProjectAndAssignClient");
	   String buyerEmails = validBuyerEmail + "+" + unicID + "@gmail.com";
	   buyerEmails = buyerEmails + " " + buyerEmails;
	   BaseTestCases.testOpenProjectAndAssignClient(secondSPprojectName, buyerEmails);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-7 SP Create Project Hovering in home page - step 7, open project and assign client, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectOpenProjectAndAssignClient");
       log.info("--------------------------------------------"); 
   }
   
   @Test(description="Go to home page")
   public void testGetHomePage() throws InterruptedException
   {
	   log.info("Start: testGetHomePage");
	   MainMenuPage menuPage = new MainMenuPage(driver);
	   
	   menuPage.clickProjectMenu();
	   Thread.sleep(1400);
	   log.info("End: testGetHomePage");
       log.info("--------------------------------------------"); 
   }   
   
   @Test(description="11, Invite client through hovering menu")
   @Parameters({"validBuyerEmail", "confirmInviteMessage", "invalidNewCustomerEmail", "wrongEmailMessage", "validSPEmail"})
   public void testClientsInviteHovering(String validBuyerEmail, String confirmInviteMessage, 
		   String invalidNewCustomerEmail, String wrongEmailMessage, String validSPEmail) throws InterruptedException 
   {
	   log.info("Start: testClientsInviteHovering");
	   BaseTestCases.testInviteCustomerHovering(validBuyerEmail, confirmInviteMessage, 
			   invalidNewCustomerEmail, wrongEmailMessage, validSPEmail);
	   log.info("End: testClientsInviteHovering");
       log.info("--------------------------------------------"); 
   }
   
   @Test(description="Logout SP site") 
   public void testLogoutDemoSite() throws InterruptedException
   {
	   log.info("Start: testLogoutDemoSite");
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   
	   Thread.sleep(1000);
	   if(browser.trim().equals("IE")) {
		    registerSPSitePage.logoutSPsiteIE();
	   } else {
	        registerSPSitePage.logoutSPSite();
	   }
	   Thread.sleep(1000); 
	   secondWindow = driver.getWindowHandle();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC Log out SP site, JS errors for SP site #######");	
	   log.info("End: testLogoutDemoSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Close SP email")
   public void testCloseSPEmail() throws InterruptedException
   {
	   log.info("Start: testCloseSPEmail");
	   LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
	   
	   driver.switchTo().window(firstWindow);
	   driver.navigate().refresh();
	   Thread.sleep(2200);
	   loginEmailPage.logoutSPEmail();
	   Thread.sleep(1000);
	   driver.close();
	   Thread.sleep(1000);
	   driver.switchTo().window(secondWindow);
	   driver.navigate().refresh();
	   Thread.sleep(2200);
	   log.info("End: testCloseSPEmail");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.1, Login to SP site")
   public void testLoginSPSite() throws InterruptedException 
   {   
	   log.info("Start: testLoginSPSite");
	   driver.get(credentials.get("spURL"));
	      
	    // Verifying that this is the correct page     
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);

	   loginDemoSqa.loginUser(credentials.get("loginName"), credentials.get("password"));      	   
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.1, Login to SP site, JS errors for SP site #######");	  
	   log.info("End: testLoginSPSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Login SP site")
   @Parameters({"passwordSP"})
   public void testLoginDemoPage(String passwordSP) throws InterruptedException 
   {	  
	   log.info("Start: testLoginDemoPage");
	   Thread.sleep(500);	
	   if(domain.trim().equals("qa2"))
		   driver.get("https://selenium" + unicID + ".qa2.noosh.com/service/login");
	   else 
	       driver.get("https://selenium" + unicID + ".sqa.noosh.com/service/login");
	   
       driver.manage().window().maximize();
         
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
       
	   Thread.sleep(500);   
       loginDemoSqa.loginUser("demo.nshauto+" + unicID + "@gmail.com", passwordSP);
       Thread.sleep(500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### Login SP site, JS errors for SP site #######");	  
	   log.info("End: testLoginDemoPage");
	   log.info("--------------------------------------------");
   } 
   
   @Test(description="Login SP site")
   @Parameters({"passwordSP"})
   public void testLoginDemoPageDebug(String passwordSP) throws InterruptedException 
   {	   
	   log.info("Start: testLoginDemoPageDebug");
	   Thread.sleep(500);	   
	   driver.get("https://selenium640806.sqa.noosh.com/service/login/");	   
       driver.manage().window().maximize();      
       // Verifying that this is the correct page     
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   Thread.sleep(500);   
       loginDemoSqa.loginUser("nobody+1@noosh.com", passwordSP);
       Thread.sleep(500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### Login SP site, JS errors for SP site #######");	  
	   log.info("End: testLoginDemoPageDebug");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="14.2, SP Review new project")
   @Parameters({"newProjectNameSP", "buyerProjectName"})
   public void testReviewNewProject(String newProjectNameSP, String buyerProjectName) throws InterruptedException
   {
	   log.info("Start:  testReviewNewProject");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   com.noosh.nooshentry.automation.buyerSite.NewProjectPage projectPage = new NewProjectPage(driver);
	        
	   Thread.sleep(1200); 
	   buyerProjectName = buyerProjectName + unicID + "G";	   
	   newHomePage.clickProjectInListNew();	   
	   Thread.sleep(800); 
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("14.2 Project status 'New' does not displayed");
			  log.info("14.2 Project status 'New' does not displayed");
		   }
	   projectPage.clickProjectSpecsTab();
	   Thread.sleep(800);	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent("Test", driver));	
	       } catch(Throwable e) {
		       System.out.println("14.2 Project spec " + newProjectNameSP + " does not displayed");
		       log.info("14.2 Project spec " + newProjectNameSP + " does not displayed");
	       }
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.2, SP Review new project,  JS errors for SP site #######");
	   log.info("End:  testReviewNewProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.3, SP upload new file to project")
   @Parameters({"profilePicture", "fileName1", "fileName3", "profilePictureFile", "newProfilePictureFile"})
   public void testUploadNewFile(String profilePicture, String fileName1, String fileName3, String profilePictureFile,
		   String newProfilePictureFile) throws InterruptedException, AWTException
   {
	   log.info("Start:  testUploadNewFile");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   NewProjectPage projectPage = new com.noosh.nooshentry.automation.buyerSite.NewProjectPage(driver);
	   
	   projectPage.clickFileTab();
	   Thread.sleep(400);	
	   try{
//	       AssertJUnit.assertEquals(fileName1, projectFrame.getUploadedFileName33().trim());
		   AssertJUnit.assertTrue(Page.isTextPresent(fileName3, driver));
	      } catch(Throwable e) {
	    	  System.out.println("14.3 Uploaded file name " + fileName3 + " does not displayed");
	    	  log.info("14.3 Uploaded file name " + fileName3 + " does not displayed");
	    	}
	   Thread.sleep(400);
	   page.uploadFileModalWindow(profilePicture);
	   projectFrame.clickFileUploadSP();
	   Thread.sleep(1000);
	   page.robotUpload();	 	   
	   Thread.sleep(3000);
	   if(!browser.trim().equals("IE")) {
          projectFrame.clickDownloadFileIcon(driver, fileName3);
	      Thread.sleep(800);
          page.downloadFile();
	   }
	   // file rename	   
       if(browser.trim().equals("IE")) {
    	   projectFrame.fileRename(newProfilePictureFile);
           projectFrame.clickBody();
       } else {
    	   projectFrame.renameFileName(buyerDriver, profilePictureFile, newProfilePictureFile);
           page.closeModalPopup();
       }
	   Thread.sleep(2500);
	   // sorting
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.3, SP upload new file to project, JS errors for SP site #######");	   
	   log.info("End:  testUploadNewFile");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.4, SP send message to buyer")
   @Parameters({"buyerNewMessage"})
   public void testSendMessage(String buyerNewMessage) throws InterruptedException
   {
	   log.info("Start:  testSendMessage");
	   BaseTestCases.testSendMessageFile(driver, buyerNewMessage);	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.4 SP send message to buyer, JS errors for SP site #######");	   
	   log.info("End:  testSendMessage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.5, SP update project info")
   public void testUpadateProjectInfo() throws InterruptedException
   {
	   log.info("Start:  testUpadateProjectInfo");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(300); 
	   projectFrame.clickOverviewTab();
	   Thread.sleep(300);
	   System.out.println("Project number: "+ projectFrame.getProjectNumber());
	   projectNumber = projectFrame.getProjectNumber();
	   credentials.put("ProjectNumber", projectFrame.getProjectNumber());	   	
	   projectFrame.clickEditProjectBT();
	   Thread.sleep(300);
	   projectFrame.selectProjectStatus();
	   Thread.sleep(300); 
	   projectFrame.clickUpdateBT();
	   Thread.sleep(400);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.5 SP update project info, JS errors for SP site #######");	
	   log.info("End:  testUpadateProjectInfo");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.5, SP update buyer project status")
   @Parameters({"buyerProjectName"})   
   public void testUpdateProjectStatus(String buyerProjectName) throws InterruptedException
   {
	   log.info("Start:  testUpdateProjectStatus");
	   buyerProjectName = buyerProjectName + unicID + "G";
	   Thread.sleep(600);
	   BaseTestCases.updateProjectStatus(buyerProjectName);
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.5 SP update buyer project status, JS errors for SP site #######");
	   log.info("End:  testUpdateProjectStatus");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.6-1, SP edit spec in-line, invalid data")
   @Parameters({"buyerProjectName", "productNameAdv", "descriptionNameSPAdv", "wrongOriginalPassword", 
	   "requiredFieldErrorMessage", "numericFieldErrorMessage"})
   public void testSPEditSpecInlineInvalidData(String buyerProjectName, String productNameAdv, 
		   String descriptionNameSPAdv, String wrongOriginalPassword, String requiredFieldErrorMessage,
		   String numericFieldErrorMessage) throws InterruptedException
   {
	   log.info("Start:  testSPEditSpecInlineInvalidData");
	   String specName;
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(600);
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1000);	   	
	   specName = buyerProjectName + unicID + "G" + " - " + productNameAdv + unicID;
	   projectFrame.selectProductSpec(driver, specName);
	   Thread.sleep(300);	   
	   projectFrame.clickEditBT();
	   Thread.sleep(1000);
	   projectFrame.setNewNumber(descriptionNameSPAdv);	 	   
	   projectFrame.setNewDemention(wrongOriginalPassword);
	   projectFrame.setTextTextBox("");
	   Thread.sleep(2000);
	   projectFrame.clickSaveBT();
	   Thread.sleep(400);
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(requiredFieldErrorMessage, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-1 Error message " + requiredFieldErrorMessage + " does not displayed after save button");
			  log.info("14.6-1 Error message " + requiredFieldErrorMessage + " does not displayed after save button");
		   }
	   /*
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(numericFieldErrorMessage, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-1 Error message " + numericFieldErrorMessage + " does not displayed after save button");
			  log.info("14.6-1 Error message " + numericFieldErrorMessage + " does not displayed after cancel button");
		   }
	   */
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.6-1 SP edit spec in-line, invalid data, JS errors for SP site #######");	   
	   log.info("End:  testSPEditSpecInlineInvalidData");
	   log.info("--------------------------------------------");
   }	   
   
   @Test(description="14.6-2, SP edit spec in-line, cancel button")
   @Parameters({"skuSP", "skuAdv"})
   public void testSPEditSpecInline(String skuSP, String skuAdv) throws InterruptedException
   {
	   log.info("Start:  testSPEditSpecInline");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(1000);	   
	   projectFrame.setNewNumber(skuSP);	 	   
	   projectFrame.setNewDemention(skuAdv);
	   projectFrame.setTextTextBox("United");	   
	   projectFrame.clickCancelBT();
	   Thread.sleep(1000);
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(skuSP, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-2 New number " + skuSP + " does not displayed after cancel button");
			  log.info("14.6-2 New number " + skuSP + " does not displayed after cancel button");
		   }
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(skuAdv, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-2 New dimention " + skuSP + " does not displayed after cancel button");
			  log.info("14.6-2 New dimention " + skuSP + " does not displayed after cancel button");
		   }
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent("United", driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-2 New text in text box United does not displayed after cancel button");
			  log.info("14.6-2 New dimention United does not displayed after cancel button");
		   }	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.6-2, SP edit spec in-line, cancel button, JS errors for SP site #######");
	   log.info("End:  testSPEditSpecInline");
	   log.info("--------------------------------------------");
   }

	   
   @Test(description="14.6-3, SP edit spec in-line, valid data")
   @Parameters({"skuSP", "skuAdv"})
   public void testSPEditSpecInlineValidData(String skuSP, String skuAdv) throws InterruptedException
   {
	   log.info("Start:  testSPEditSpecInlineValidData");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   projectFrame.clickEditBT();
	   Thread.sleep(1000);	   
	   projectFrame.setNewNumber(skuSP);	 	   
	   projectFrame.setNewDemention(skuAdv);
	   projectFrame.setTextTextBox("United");	   
	   projectFrame.clickSaveBT();
	   Thread.sleep(1000);
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(skuSP, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-3 New number " + skuSP + " does not displayed after save button");
			  log.info("14.6-3 New number " + skuSP + " does not displayed after save button");
		   }
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(skuAdv, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-3 New dimention " + skuSP + " does not displayed after save button");
			  log.info("14.6-3 New dimention " + skuSP + " does not displayed after save button");
		   }
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent("United", driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-3 New text in text box United does not displayed after save button");
			  log.info("14.6-3 New dimention United does not displayed after save button");
		   }
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.6-3 SP edit spec in-line, valid data, JS errors for SP site #######");	
	   log.info("End:  testSPEditSpecInlineValidData");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Logout SP site")
   public void logoutSPSite() throws InterruptedException
   {
	   log.info("Start:  logoutSPSite");
	   testLogoutDemoSite();	
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### Logout SP site, JS errors for SP site #######");	
	   log.info("End:  logoutSPSite");
	   log.info("--------------------------------------------");
   }
 
   @Test(description="14.5, Check buyer notification")
   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "profilePictureFile"})
   public void testBuyerNotifications(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
		   String newLastNameSP, String profilePictureFile) 
      throws InterruptedException, AWTException 
   {
	   log.info("Start:  testBuyerNotifications");
      String statusNote, messageNote, spFileNote;   
	   
	  driver.get(baseUrlBuyerSite);      
      
      // Verifying that this is the correct page     
      LoginEmailPage loginEmailPage = new LoginEmailPage(driver);      
                
      loginEmailPage.searchInvitationLetter(validBuyerEmail+buyerEmail);
      Page.robotClickEnter();
	  Thread.sleep(500);           
      statusNote = "The status of project Test presentation (" + projectNumber + 
    		  ") has been changed to New Job Received";
//      log.info("statusNote: " + statusNote); 
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(statusNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Changing status notification does not received");
		      log.info("14.5 Changing status notification does not received");
	      }	  	  	
	  messageNote = "Message posted regarding Test presentation (" + projectNumber + ")";
//	  log.info("messageNote: " + messageNote);
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(messageNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Buyr does not received SP message notification");
		      log.info("14.5 Buyr does not received SP message notification");
	      }

	  spFileNote = firstNameSP + " " + newLastNameSP + " has added File " + profilePictureFile;
//	  log.info("spFileNote: " + spFileNote);
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(spFileNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Buyr does not received New file added SP notification");
		      log.info("14.5 Buyr does not received New file added SP notification");
	      }
	  Thread.sleep(300);	   
	  loginEmailPage.logoutBuyerEmail1();
	  Thread.sleep(1000);
	   
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 14.5 - Check buyer notification, JS errors for SP site #######");	   
	  log.info("End:  testBuyerNotifications");
	  log.info("--------------------------------------------");
   }   
   
   @Test(description="14.3 - 14.5, Check SP notification")
   @Parameters({"passwordSP", "firstNameSP", "newLastNameSP", "profilePictureFile"})
   public void testSPNotifications(String passwordSP, String firstNameSP, String newLastNameSP, 
		   String profilePictureFile) throws InterruptedException, AWTException 
   {
	   log.info("Start: testSPNotifications");
	  String messageNote, spFileNote; 	  
	  
	  LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
	  
	  loginEmailPage.loginUser("nobody@noosh.com", passwordSP);  
	  loginEmailPage.searchInvitationLetter(unicID);
	  Page.robotClickEnter();
	  Thread.sleep(800);
	  	
	  messageNote = "Message posted regarding Test presentation (" + projectNumber + ")";
      try {
		     AssertJUnit.assertTrue(Page.isTextPresent(messageNote, driver));	
		  } catch(Throwable e) {
		     System.out.println("14.5 SP message notification does not received");
		     log.info("14.5 SP message notification does not received");
		  }
		  	  
	  spFileNote = firstNameSP + " " + newLastNameSP + " has added File " + profilePictureFile;
	  try {
		      AssertJUnit.assertTrue(Page.isTextPresent(spFileNote, driver));	
		  } catch(Throwable e) {
			 System.out.println("14.5 New file added SP notification does not received");
			 log.info("14.5 New file added SP notification does not received");
		  }
	  loginEmailPage.logoutSPEmail();
	  Thread.sleep(1000);
	   
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 14.3 - 14.5, JS errors for SP site #######");		  
	  log.info("End: testSPNotifications");
	  log.info("--------------------------------------------");
   }  
   
   @Test(description="14.3 - 14.5, Check SP notification, check file link")
   public void testSPFileNotifications(String profilePictureFile) throws InterruptedException 
   {
	  String messageNote, spFileNote;
	      
	  LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 14.3 - 14.5, JS errors for SP site #######");		   
   }  
   
   @Test(description="Logger JSErrors")
   public void logger() throws Exception {
		JavaScriptLog.logJSError(driver, buyerDriver);
	}
   
  
  @Test(description="Invite customers onboarding messages")
  @Parameters({"validBuyerEmail"})
  public void testInviteCustomersPageOB(String validBuyerEmail) throws InterruptedException
  {
	  log.info("Start: testInviteCustomersPageOB");
	   Invite invite = new Invite(driver);
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   String buyerEmails = validBuyerEmail + "+" + unicID + "@gmail.com";

	   mainMenuPage.clickInviteBT();
	   Thread.sleep(800);
	   invite.clickInviteCustomerTab();
       invite.putCustomersEmail(buyerEmails);
       invite.selectFirstSite();
	   Thread.sleep(300);
	   invite.clickCustomerSend();
	   Thread.sleep(300);
	   invite.clickCloseLink();
	   Thread.sleep(2000);
 
	 errorIndex = jsErrorIndex;
	 Page.jsErrorReporter(driver, errorIndex,"####### TC 8.2, Invite customers, JS errors for SP site #######");	  
	 log.info("End: testInviteCustomersPageOB");
	 log.info("--------------------------------------------");
  }  
  
 /* 
  @Test(description="1.3-2, Log out verification")
  @Parameters({"loginPageTitle"})
  public void testLogout(String loginPageTitle)
  {
	   log.info("Start: testLogout");
     try{
	      AssertJUnit.assertTrue(page.checkPageTitle(driver, loginPageTitle));
     } catch(Throwable e) {
         System.out.println("1.3 This is not '" + loginPageTitle + "' page title");  
         log.info("1.3 This is not '" + loginPageTitle + "' page title");
     }

	  if(!browser.trim().equals("IE")) {
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.3, Log out verification, JS errors for SP site #######"); 
	  }
	  log.info("End: testLogout");
	  log.info("--------------------------------------------");
  }
  
  
*/  
 
}
