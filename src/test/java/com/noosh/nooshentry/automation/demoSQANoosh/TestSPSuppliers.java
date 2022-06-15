/***********************************************************************
* This class tests the functionality for Buying 
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
import java.io.IOException;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
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
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.buyerSite.SupplierLoginPage;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSPSuppliers extends BaseSeleniumTest {
  
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
   static String projectName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup";
		   loginUrl = "https://selenium263945." + companyName + ".com/service/login";
	   } else {
		   baseUrl = "https://demo." + domain + "." + companyName + ".com/service/signup";
		   loginUrl = "https://selenium263945." + domain + "." + companyName + ".com/service/login";
	   }
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spSupplierTest *****\n\n");
   }
   
   @Test(description="SP login")
     @Parameters({"loginUserID", "passwordSP"})
     public void testSPLogin1stTime(String loginUserID, String passwordSP) throws InterruptedException {
     	 log.info("Start: testSPLogin1stTime");
     	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
     	 long beforeLoadLogin = System.currentTimeMillis();
         driver.get(loginUrl); 
         long afterLoadLogin = System.currentTimeMillis();
         long loadLoginPageTime = beforeLoadLogin - afterLoadLogin;
         log.info("Login loading time: " + loadLoginPageTime);
         driver.manage().window().maximize();
         Thread.sleep(3000);    
         LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
         loginDemoSqa.loginUser(loginUserID, passwordSP); 
         Thread.sleep(3000);
         spSiteLoginPage = driver.getCurrentUrl();  	  
         if(!browser.trim().equals("IE")) {
         	  errorIndex = jsErrorIndex;
        	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
         } 
         log.info("End: testSPLogin1stTime");
    	 log.info("--------------------------------------------");
     }
   
   @Test(description="Existing SP click global 'create project'")
   public void testExistingSPClickGlbCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPClickGlbCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickGlbCreateProj();    
	   Thread.sleep(3000);
	   log.info("End: testExistingSPClickGlbCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP cancel global create project")
   public void testExistingSPCancelCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPCancelCreateProj");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject. cancelGlbProj();   
	   Thread.sleep(3000);
	   log.info("End: testExistingSPCancelCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP click global create project again")
   public void testExistingSPClickGlbCreateProjAgain() throws InterruptedException {
	   testExistingSPClickGlbCreateProj();
   }
   
   @Test(description="Existing SP input project name")
   @Parameters({"productName1", "projectNameField"})
   public void testExistingSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testExistingSPInputProjectName");
	   productName1 = productName1 + unicID + "55";  
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
   
   @Test(description="Existing SP select a smart form")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
	    log.info("Start: testExistingSPSelectSmartForm");
	    driver.findElement(By.className("radio-input")).click();   
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
	    else if(spSiteLoginPage.contains("sqa"))   
		    img.get(1).click();   
	    else if(spSiteLoginPage.contains("scd"))
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
    
 	   log.info("Start: testExistingSPcreateProjectWizard");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   Thread.sleep(2000);   	    
 	   brochurePopup.callCalendar();
 	   brochurePopup.setNextMonth();
 	   brochurePopup.setComplationDate(); 
 	   Thread.sleep(3000);
 	   brochurePopup.getNextTab();	 
 	   Thread.sleep(2000);
 	   driver.findElement(By.id("cpPreviousStep")).click();  
 	   Thread.sleep(3000);
 	   brochurePopup.getNextTab();           
 	   Thread.sleep(3000);
 	   Thread.sleep(1000);
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
 	   driver.findElement(By.id("cpNextStep"));
 	   Thread.sleep(3000);
 	   
 	   brochurePopup.getFilesTab();  
 	   Thread.sleep(1000);
 	   page.uploadFileModalWindow(fileForProject11);   
 	   brochurePopup.clickUploadFileDropBT();
 	   Thread.sleep(2000);
 	   page.robotUpload();
 	   Thread.sleep(6500);	   
 	   int index = fileForProject11.indexOf("/");
 	   String imgTitle = fileForProject11.substring(index + 1);
 	   String imgName = driver.findElement(By.id("createProjectTabFiles")).findElement(By.className("editable-field active")).getAttribute("title");
 	   Assert.assertEquals(imgName, imgTitle);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000); 	   
 	   testExistingSPAddSmartFormPage(quantSP, quantSP2);   
 	   Thread.sleep(2000); 
       brochurePopup.clickCreateAndEstimateGlbProjectBT();    
       Thread.sleep(3000);
 	   log.info("End: testExistingSPcreateProjectWizard");
 	   log.info("--------------------------------------------"); 
    }    
    
    @Test(description="add smart form")
    @Parameters({"quantSP", "quantSP2"})
    public void testExistingSPAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
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
 	   Thread.sleep(6000); 	   
 	   addSmartFormPage.clickSmartForm();
 	   addSmartFormPage.inputOneQuantity(quantSP); 	  
 	   Thread.sleep(2000);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000);	  
 	   log.info("End: testAddSmartFormPage");
 	   log.info("--------------------------------------------"); 
    }
    
    @Test(description="Existing SP adding two suppliers")
    @Parameters({"supplierName1", "supplierNameold"})
    public void testExistingSPAddSuppliers(String supplierName1, String supplierNameold)
    		     throws InterruptedException {
    	log.info("Start: testSPAddSuppliers");
    	supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@noosh.com";	   
    	Thread.sleep(2000);
    	InvitePage invitePage = new InvitePage(driver);
    	invitePage.inputSupplier(supplierUser1);
    	Thread.sleep(2000);
    	//invitePage.inputSupplier2(supplierNameold);
    	invitePage.inputSupplier(supplierNameold);
    	Thread.sleep(2000); 
    	log.info("End: testSPAddSuppliers");
    	log.info("--------------------------------------------"); 
    }    
   
    @Test(description="Existing SP selecting a smart form")
    public void testExistingSPSelectSF() throws InterruptedException {
    	log.info("Start: testSPSelectSF");
    	WebElement smartFormCheckbox = driver.findElement(By.className("forms-list"));  
  	    smartFormCheckbox.findElement(By.tagName("input")).click(); 
  	    Thread.sleep(1000);
    	log.info("End: testSPSelectSF");
 	    log.info("--------------------------------------------"); 
    }   
    
    @Test(description="Existing SP adding estimate due date")
    public void testExistingSPAddEstimateDueDate() throws InterruptedException {
    	log.info("Start: testSPAddEstimateDueDate");
    	SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);  
  	    supplierEstimatePopup.callCalendar();	               
  	    Thread.sleep(2000);  
    	log.info("End: testSPAddEstimateDueDate");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="Existing SP request estimates button")
    public void testExistingSPRequestEstimatesBtn() throws InterruptedException {
    	log.info("Start: testSPRequestEstimatesBtn");
    	SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver); 
    	supplierEstimatePopup.callRequestEstimatesBtn();
  	    Thread.sleep(6000); 
    	log.info("End: testSPRequestEstimatesBtn");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="Existing SP logout")
    public void testExistingSPLogout() throws InterruptedException {
    	log.info("Start: testExistingSPLogout");
    	Thread.sleep(7000);
    	//SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
    	//Thread.sleep(2000);
    	//supplierMainMenuPage.clickLogout();
    	CommonUtils commonUtils = new CommonUtils(driver);
    	Thread.sleep(2000);
    	commonUtils.spLogout();
    	Thread.sleep(2000);  	  
    	log.info("End: testExistingSPLogout");
    	log.info("--------------------------------------------");
    } 
   
   @Test(description="SP onboarding - SP sign up")
   @Parameters({"fullNameSP", "passwordSP", "phoneNumberSP", "companyName"}) 
   public void testRegisterNewSPSite(String fullNameSP, String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException {		   
	   log.info("Start: testRegisterNewSPSite");
	   companyName = companyName + unicID;   
	   BaseTestCases.testRegisterNewSPSite(baseUrl, fullNameSP, passwordSP, phoneNumberSP, companyName);  
	   Thread.sleep(3000); 	   
	   log.info("End: testRegisterNewSPSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Close onboarding intro page")
   public void testCloseOnBoardingIntro() throws InterruptedException {
	   log.info("Start: testCloseOnBoardingIntro");
	   driver.findElement(By.className("spOverlay-bigClose")).click();
	   Thread.sleep(2000);
	   log.info("End: testCloseOnBoardingIntro");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Enable selling")
   public void testEnableSelling() throws InterruptedException {
	   log.info("Start: testEnableSelling");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testInviteCustomers();
	   Thread.sleep(2000);
	   log.info("End: testEnableSelling");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Enable client btn")
   public void testEnableBtn() throws InterruptedException {
	   log.info("Start: testEnableBtn");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testEnableClientBtn();
	   Thread.sleep(3500);
	   log.info("End: testEnableBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test
   public void goToNE() throws InterruptedException {
	   log.info("Start: goToNE");
	   SiteListPage siteListPage = new SiteListPage(driver);
	   Thread.sleep(1000);      
       siteListPage.getMyDeskPageBack();
       Thread.sleep(3000);  
       //CommonUtils commonUtils = new CommonUtils(driver);
	   //commonUtils.verifyNEHome();
	   log.info("End: goToNE");
	   log.info("--------------------------------------------");
   }
   
   @Test
   public void backNP() throws InterruptedException {
	   log.info("Start: backNP");
	   MyDeskPage myDeskPageNoo = new MyDeskPage(driver);  	
   	   myDeskPageNoo.getDashboard(); 
   	   Thread.sleep(6000);
	   log.info("End: backNP");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Buying Site")
   public void testCreateBuyerSiteOB () throws InterruptedException {
 	   log.info("Start: testCreateBuyerSiteOB");
 	   SitesListTab sitesListTab = new SitesListTab(driver); 
 	   spUser = driver.getCurrentUrl();       
	   int index = spUser.indexOf("/service");     
	   spUser = spUser.substring(0, index);       
 	   sitesListTab.getSitesList();
 	   Thread.sleep(1500);
 	   log.info("End: testCreateBuyerSiteOB");
 	   log.info("--------------------------------------------");
  }
   
   @Test(description="SP onboarding click global 'create project'")
   public void testSPClickGlbCreateProj() throws InterruptedException {
	   log.info("Start: testSPClickGlbCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickGlbCreateProj();    
	   Thread.sleep(3500);
	   log.info("End: testSPClickGlbCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP onboarding cancel global create project")
   public void testSPCancelCreateProj() throws InterruptedException {
	   log.info("Start: testSPCancelCreateProj");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject.cancelGlbProj();   
	   Thread.sleep(3000);
	   log.info("End: testSPCancelCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP click global create project again")
   public void testSPClickGlbCreateProjAgain() throws InterruptedException {
	   testSPClickGlbCreateProj();
   }
   
   @Test(description="SP onboarding input project name")
   @Parameters({"productName1", "projectNameField"})
   public void testSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testSPInputProjectName");
	   productName1 = productName1 + unicID + "2"; 
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
	   log.info("End: testSPInputProjectName");
	   log.info("--------------------------------------------"); 
   }
     
   @Test(description="SP onboarding select a smart form")
   @Parameters({"clientOrWorkspace"})
   public void testSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
	    log.info("Start: testSPSelectSmartForm");
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
	    else if(spSiteLoginPage.contains("sqa"))   
		    img.get(1).click();   
	    else if(spSiteLoginPage.contains("scd"))
	    	img.get(0).click();
	    Thread.sleep(3000);
	    log.info("End: testSPSelectSmartForm");
	    log.info("--------------------------------------------"); 
   }
  
   @Test(description="SP Create Project - step 2")
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
	   "specDescrSP", "fileForProject1"})
   public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
		         String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
		         String fileForProject1) throws InterruptedException, AWTException {
	   log.info("Start: testSPcreateProjectProjectWizard");	   
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
			     quantSP3, specDescrSP, fileForProject1);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   /*
	   brochurePopup.getFilesTab();	   
	   page.uploadFileModalWindow(fileForProject1);
	   Thread.sleep(2000);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	
	   commonUtils.verifyUploadedImg(fileForProject1);
	   */
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(3000);
       brochurePopup.clickCreateBT();
       Thread.sleep(5000);
       /*
	   WebElement fileInput = driver.findElement(By.id("fileForProject1"));
	   fileInput.sendKeys(fileForProject1);
	   Thread.sleep(3000);
	   11/10/14 tmp comment out file uploading
	   */
	   log.info("End: testSPcreateProjectProjectWizard");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="New SP request estimate")
   public void testNewSPRequestEstimate() throws InterruptedException {
	   log.info("End: testNewSPRequestEstimate");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   Thread.sleep(2500);
	   commonUtils.testNewSPRequestEstimate(projectName);	   
	   log.info("End: testNewSPRequestEstimate");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="New SP adding two suppliers")
   @Parameters({"supplierName1", "supplierNameold"})
   public void testNewSPAddSuppliers(String supplierName1, String supplierNameold)
   		     throws InterruptedException {
	   testExistingSPAddSuppliers(supplierName1, supplierNameold); 	   
   }
   
   @Test(description="New SP adding estimate due date")
   public void testNewSPAddEstimateDueDate() throws InterruptedException {
	   testExistingSPAddEstimateDueDate();
   }
   
   @Test(description="New SP request estimates button")
   public void testNewSPRequestEstimatesBtn() throws InterruptedException {
	   testExistingSPRequestEstimatesBtn();
   }
   
   @Test(description="SP logout")
   public void testLogout() throws InterruptedException {
 	  log.info("Start: testLogout");
 	  Thread.sleep(7000); 	  
 	  spUser = driver.getCurrentUrl();
 	  int index = spUser.indexOf("/service");
 	  spUser = spUser.substring(0, index);
 	  Thread.sleep(2000);
	  CommonUtils commonUtils = new CommonUtils(driver);
  	  Thread.sleep(2000);
  	  commonUtils.spLogout();
 	  Thread.sleep(2000);  
 	  log.info("End: testLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier Login to Gmail")
   @Parameters({"gmailLoginPW", "validDemoUserEmailk"})
   public void testLoginToGmail(String gmailLoginPW, String validDemoUserEmailk) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  Thread.sleep(1000);
 	  driver.get("http://www.gmail.com");
 	  testGmailLoginPreCon();  
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(10000);
 	  gmailLoginPage.enterEmail(validDemoUserEmailk);
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(1000);
 	  gmailLoginPage.check();
 	  gmailLoginPage.signIn();
 	  Thread.sleep(1000);
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New Supplier Check Gmail")
   public void testCheckGmail() throws InterruptedException {
 	  log.info("Start: testCheckGmail");
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
 	 Thread.sleep(3000);
 	 WebElement acceptInv = driver.findElement(By.linkText("Accept Invitation and Send Estimate"));
	      if(!acceptInv.isDisplayed()) {
		      throw new IllegalStateException("The accept invitation link is missing!");
	      } else {
		      acceptInv.click();
	      } 	   
	      /*
 	  Thread.sleep(3000);
 	  List<WebElement> ps = driver.findElements(By.tagName("p"));
 	  for (WebElement p : ps) {
 		  String pText = p.getText();
 		  if (pText.startsWith("If the link above does not work click here")) {
 			  WebElement link = p.findElement(By.tagName("a"));
 			  String linkHref = link.getAttribute("href");
 			  System.out.println("link=" + linkHref);
 			  if (linkHref.startsWith("https://supplier.")) {
 				link.click();
 			  }
 			  Thread.sleep(1500);
 		      break;
 	      }
 	  }
 	  */
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
 	  log.info("End: testSupplier1Signup");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier login")
   @Parameters({"passwd"})
   public void testNewSupplierLogin(String passwd) throws InterruptedException {
 	  log.info("Start: testNewSupplierLogin");
 	  SupplierLoginPage supplierLoginPage = new SupplierLoginPage(driver);
 	  supplierLoginPage.loginUser1(passwd);
 	  Thread.sleep(2000);
 	  log.info("End: testNewSupplierLogin");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="New supplier Let's Begin")
   public void testNewSupplierLetsBegin() throws InterruptedException {
 	  log.info("Start: testNewSupplierLetsBegin");
 	  String currentUrl = driver.getCurrentUrl();
 	  supplier1LoginUrl = currentUrl.substring(0, currentUrl.indexOf("/service")) + "/service/login";  	  
 	  driver.findElement(By.id("glbLandingLaunchStart")).click();
 	  Thread.sleep(7000);
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
 	  estimateRFEPage.clickCreateEstimateBtn1();
 	  
 	  List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
 	  List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
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
 	  Thread.sleep(3000);
 	  //estimateRFEPage.inputEstimatedTaxField(taxPrice);
 	  //Thread.sleep(3000); 	  
 	  estimateRFEPage.clickSendEstimateBtn();
 	  Thread.sleep(3000); 	 
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
 	  log.info("End: testSupplierLogout");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Go back to the gmail window")
   public void testGoBackToGmailPage() throws InterruptedException {
	   log.info("Start: testGoBackToGmailPage");
	   Thread.sleep(3000);
	   driver.close(); 
	   Thread.sleep(3000);
	   driver.switchTo().window(parentWindowHandle); 
	   Thread.sleep(2000);
	   driver.get("https://mail.google.com/mail/?logout"); 
	   Thread.sleep(2000);
	   log.info("End: testGoBackToGmailPage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Check gmail login preconditions")
   public void testGmailLoginPreCon() throws InterruptedException {
	      Thread.sleep(2000);
	 	  WebElement gmailSignIn = null;
	 	  try {
	 	      gmailSignIn = driver.findElement(By.id("gmail-sign-in"));    
	 	  } catch (Exception e) {
	 		  // do nothing
	 	  }
		  if (gmailSignIn != null) {
			 gmailSignIn.click();
			 Thread.sleep(2000);
		  } 
		 
		  WebElement accountChooserLink = null;
		  try {
			  accountChooserLink = driver.findElement(By.id("account-chooser-link"));
		  } catch (Exception e) {
			  // do nothing
		  }
		  if (accountChooserLink != null) {
			  accountChooserLink.click();
			  Thread.sleep(2000);
		  }
		  
		  WebElement addAccountLink = null;
		  try {
			  addAccountLink = driver.findElement(By.id("account-chooser-add-account"));
		  } catch (Exception e) {
			  // do nothing
		  }
		  if (addAccountLink != null) {
			  addAccountLink.click();
			  Thread.sleep(2000);
		  }	 
   }
   
   @Test(description="Existing supplier login gmail")
   @Parameters({"gmailLoginPW", "validSPEmail"})
   public void testSupplier2LoginToGmail(String gmailLoginPW, String validSPEmail) throws InterruptedException  {
	 	  log.info("Start: testSupplier2LoginToGmail");
	 	  
	 	  Thread.sleep(1500);
	 	  driver.get("http://www.gmail.com");
	 	  testGmailLoginPreCon();
	 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	  	   	parentWindowHandle = driver.getWindowHandle();
	  	   	Set<String> windows = driver.getWindowHandles();
	  	   	for (String window : windows) {
	  	   		if (!window.equals(parentWindowHandle)) {
	  	   			driver.switchTo().window(window);
	  	   			break;
	  	   		}
	  	   	}
	  	 	  Thread.sleep(3000);
	  	 	  //List<WebElement> item = driver.findElements(By.tagName("svg"));
	  	 	  //driver.findElement(By.className("mUbCce")).findElement(By.tagName("span")).click();
	  	 	  gmailLoginPage.mouseup();
		 	  Thread.sleep(1000);
	  	 	  WebElement anotherAcc = driver.findElement(By.id("identifierLink"));
	  		  Actions action = new Actions(driver);
	  		  //WebElement project = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-title"));
	  		  //action.moveToElement(item.get(1)).click();
			  action.moveToElement(anotherAcc).click();
	  		  action.build().perform();
	  	 	  gmailLoginPage.enterEmail(validSPEmail);
	  	 	  Thread.sleep(1000);
	  	 	  gmailLoginPage.enterPassword(gmailLoginPW);
	  	 	  Thread.sleep(1000);
	 	   
	 	  WebElement staySignInCookie = null;
	 	  try {
	 	      staySignInCookie = driver.findElement(By.id("PersistentCookie"));
	 	  } catch (Exception e) {
	 		  //do nothing
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
			  //do nothing
		  }
		  if (skipLink != null) {
			  skipLink.click();
			  Thread.sleep(2000);
		  }
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
	 			 if (emailTitle.indexOf("has requested estimate for project") >= 0) {
	 				  email.click();
	 				  break;
	 			  }
	 		  }
	 	  }
	 	  Thread.sleep(2000);
	 	  driver.findElement(By.linkText("Send Estimate")).click(); 
	 	  Thread.sleep(2000);
	 	  log.info("End: testSupplier2CheckGmail");
	 	  log.info("--------------------------------------------");
   }
   
   
   @Test(description="Existing supplier login")
   @Parameters({"passwd"})
   public void testExistingSupplier2Login(String passwd) throws InterruptedException {
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
	   loginPage.spLoginUser1(passwd);
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
	   log.info("End: testExistingSupplier2CreateEstimate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Exsiting supplier logout")
   public void testExistingSupplier2Logout() throws InterruptedException {
	   log.info("Start: testExistingSupplier2Logout");
	   testSupplierLogout();
	   log.info("End: testExistingSupplier2Logout");
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
   
   @Test(description="sp login")
   @Parameters({"spLoginURL", "spPassword"})
   public void testSPLogin(String spLoginURL,  String spPassword) throws InterruptedException {
 	  log.info("Start: testSPLogin");
 	  spLoginURL = spUser + spLoginURL;  
 	  driver.get(spLoginURL);
	    WebElement next = driver.findElement(By.id("identifierLink"));
	    next.click();
		Thread.sleep(1500);
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(emailSP, spPassword);
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
 	  log.info("End: testConfirmOrderPage");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp log out")
   public void testSPLogout() throws InterruptedException {
	   testLogout();
   }
   
   @Test(description="Supplier1 login gmail") 
   @Parameters({"gmailLoginPW", "validDemoUserEmailk"})
   public void testSupplier1LoginGmail(String gmailLoginPW, String validDemoUserEmailk) throws InterruptedException {
	   log.info("Start: testSupplier1LoginGmail");
	   GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	   Thread.sleep(3000);
	   driver.get("http://www.gmail.com"); 
	   testGmailLoginPreCon();   
	   Thread.sleep(3000);
	   gmailLoginPage.enterEmail(validDemoUserEmailk);
	   Thread.sleep(1000);
	   gmailLoginPage.enterPassword(gmailLoginPW);
	   Thread.sleep(1000);
	   gmailLoginPage.signIn();
	   Thread.sleep(1000);
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
	   Thread.sleep(5000);
	   log.info("End: testSupplier1EstimateNotAccept");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier1 logout")
   public void testSupplier1LogoutGmail() throws InterruptedException {
	   log.info("Start: testSupplier1LogoutGmail");
	   Thread.sleep(3000);
	   driver.get("https://mail.google.com/mail/?logout"); 
	   Thread.sleep(2000);
	   log.info("End: testSupplier1LogoutGmail");
	   log.info("--------------------------------------------");
   }


@Test(description="Supplier2 login gmail")
@Parameters({"gmailLoginPW", "validSPEmail"})
public void testSupplier2LoginGmail(String gmailLoginPW, String validSPEmail) throws InterruptedException {
	  log.info("Start: testSupplier2LoginGmail");
	  Thread.sleep(3000);
 	  driver.get("http://www.gmail.com");
 	  testGmailLoginPreCon();
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(3000);
 	  gmailLoginPage.enterEmail(validSPEmail);
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(1000);
 	  gmailLoginPage.signIn();
 	  Thread.sleep(1000);
	  log.info("End: testSupplier2LoginGmail");
	  log.info("--------------------------------------------");
      }

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
    Thread.sleep(5000);
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
	  log.info("End: testSupplier2LogoutGmail");
	  log.info("--------------------------------------------");
      }

@Test(description="supplier1 login")
@Parameters({"passwordSP"})
public void testSupplier1Login(String passwordSP) throws InterruptedException {
 	  log.info("Start: testSupplier1Login");	  
 	  driver.get(supplier1LoginUrl);
 	 // driver.manage().window().maximize();
 	  SPLoginPage loginPage = new SPLoginPage(driver);
 	  loginPage.spLoginUser(supplierUser1, passwordSP);
 	  System.out.println("supplier1LoginUrl="+supplier1LoginUrl);
 	  System.out.println("supplierUser1="+supplierUser1);
 	  System.out.println("passwordSP="+passwordSP);
 	  
 	  log.info("End: testSupplier1Login");
 	  log.info("--------------------------------------------");
   }   
   
   @Test(description="check supplier confirmation")
   public void testSupplier1Confirmation() throws InterruptedException {
	   log.info("Start: testSupplier1Confirmation");
	   Thread.sleep(5000);
		List<WebElement> emails = driver.findElements(By.tagName("span"));
		for (WebElement email : emails) {
		    if (email != null) {
			    String emailTitle = email.getText();
				if (emailTitle.indexOf("for Selenium project") >= 0) {
				    email.click();
				    break;
				}
		    }
		}
				    
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
}

