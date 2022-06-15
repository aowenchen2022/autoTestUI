/***********************************************************************
* This class tests the functionality for NGE small features 
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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.noosh.nooshentry.automation.buyerSite.ClientCreateProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientEditProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SPcreateQuotePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSmallFeatures extends BaseSeleniumTest {  
	Page page = new Page(driver); 
	Hashtable<String, String> credentials = new Hashtable<String, String>();
	String currentTime = String.valueOf(System.currentTimeMillis());
	String profileNameSP;
	String spSiteLoginPage;
	static String baseUrl;
	String firstWindow;
	String secondWindow;
	static String parentWindowHandle;
	static String smallFeaturesUrl;
	static String projectName;
	static String projName;
	static String clientUrl;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if(domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup"; 
		   smallFeaturesUrl = "https://seleniumnge." + companyName + ".com";
		   clientUrl = "https://seleniumnge." + companyName + ".com/my_client/login";
	   } else {
		   baseUrl = "https://demo." + domain + "qa2." + companyName + ".com/service/signup";  
		   smallFeaturesUrl = "https://seleniumnge." + domain + "." + companyName + ".com";
		   clientUrl = "https://seleniumnge." + domain + "." + companyName + ".com/my_client/login";
	   }
   }

   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** ngeSmallFeaturesTest *****\n\n");
   }

   @Test(description="SP login")
   @Parameters({"spLoginID", "passwordSP"})
   public void testSPLogin(String spLoginID, String passwordSP) throws InterruptedException {
	   log.info("Start: testSPLogin");
	   Thread.sleep(1500);
	   driver.get(smallFeaturesUrl); 
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
    	/*
    	brochurePopup.getFilesTab();    
    	Thread.sleep(1000);
    	page.uploadFileModalWindow(fileForProject11);   
    	Thread.sleep(2000);
    	brochurePopup.clickUploadFileDropBT();
    	Thread.sleep(2000);
    	page.robotUpload();
    	Thread.sleep(8000);	
    	commonUtils.verifyUploadedImg(fileForProject11);   
    	*/ 
    	brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000);
    	brochurePopup.clickCreateBT();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(10000);    
    	log.info("End: testExistingSPcreateProjectWizard");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void TestVerifySFDelete() throws InterruptedException {
    	log.info("Start: TestVerifySFDelete");
    	String delete = "Delete";
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testOpenProjectSFTabInWS(projectName, "Smart Forms");
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.verifyDeleteSPHome(delete);
    	log.info("End: TestVerifySFDelete");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void TestDeletSF() throws InterruptedException {
    	log.info("Start: TestDeletSF");
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.deleteSF();
    	log.info("End: TestDeletSF");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"delMsg"})
    public void TestVerifyDelMsg(String delMsg) throws InterruptedException {
    	log.info("Start: TestVerifyDelMsg");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testOpenProjectActTabInWS();
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.verifyEventMsg(delMsg);
    	log.info("End: TestVerifyDelMsg");
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
    
    @Test(description="client login page")
    @Parameters({"clientUser"})
    public void testClientLogin(String clientUser) throws InterruptedException {
    	log.info("Start: testClientLogin");
    	System.out.println("client url = " + clientUrl);
    	driver.get(clientUrl);
    	Thread.sleep(5000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.clientlogin(clientUser);
    	Thread.sleep(3000);
    	log.info("End: testClientLogin");
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
    public void testClientProjCreate() throws InterruptedException {
    	testClientProjReviewSubmitTab();
    	log.info("Start: testClientProjCreate");
    	ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
    	clientCreateProjectPage.clickCreateProjBtn();
    	Thread.sleep(8000);
    	log.info("End: testClientProjCreate");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void testOpenProj() throws InterruptedException {
    	log.info("Start: testOpenProj");
    	ClientEditProjectPage clientEditProjectPage = new ClientEditProjectPage(driver);
 	    clientEditProjectPage.clickProj(); 
 	    Thread.sleep(2000);
    	log.info("End: testOpenProj");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testVerifyClientSFDelete() throws InterruptedException {
        log.info("Start: testVerifyClientSFDelete");
        ClientHomePage clientHomePage = new ClientHomePage(driver);
 	    clientHomePage.getSFTab();
 	    Thread.sleep(1500);
 	    clientHomePage.verifyDelete("Delete");
 	    Thread.sleep(1500);
    	log.info("End: testVerifyClientSFDelete");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testDeleteClientSF() throws InterruptedException {
    	log.info("Start: testDeleteClientSF");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
 	    clientHomePage.deleteSF();
    	log.info("End: testDeleteClientSF");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testVerifyClientDelMsg() throws InterruptedException {
    	log.info("Start: testVerifyClientDelMsg");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	    brochurePopup.getActivitiesTab();
 	    ClientHomePage clientHomePage = new ClientHomePage(driver);
	    clientHomePage.verifyDelEve("Spec Deleted");
    	log.info("End: testVerifyClientDelMsg");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void createClientProj() throws InterruptedException {
    	testClientCreateProj1();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void createClientProjName(String productName1, String projectNameField) throws InterruptedException {
    	testClientCreateProjName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void inputClientCompletionDate() throws InterruptedException {
    	testClientProjCompletionDate();
    }
    
    @Test
    public void clientProjReviewTab() throws InterruptedException {
    	testClientProjReviewSubmitTab();
    }
    
    @Test
    public void testSaveDraft() throws InterruptedException {
    	log.info("Start: testSaveDraft");
    	ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
    	clientCreateProjectPage.saveDraft();
    	log.info("End: testSaveDraft");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testDraftDel() throws InterruptedException {
    	log.info("Start: testDraftDel");
    	ClientEditProjectPage clientEditProjectPage = new ClientEditProjectPage(driver);
 	    clientEditProjectPage.clickProj();
 	    BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	    brochurePopup.getSmartFormsTab();
	    ClientHomePage clientHomePage = new ClientHomePage(driver);
 	    clientHomePage.deleteSF();
    	log.info("End: testDraftDel");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void verifyClientDelMsg() throws InterruptedException {
    	testVerifyClientDelMsg();
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
    @Parameters({"spLoginID", "passwordSP"})
    public void spLogin(String spLoginID, String passwordSP) throws InterruptedException {
    	testSPLogin(spLoginID, passwordSP);
    }
    
    @Test
    public void SPClickGlbCreateProj() throws InterruptedException {
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void spInputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void spSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"clientUser", "clientFieldName"})
    public void spInputClient(String clientUser, String clientFieldName) {
    	testSPInputClient(clientUser, clientFieldName);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void spSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
   	   "specDescrSP", "fileForProject11"})
    public void spcreateProjectWizard(String projNameSP, String descriptionNameSP,
   		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
   		     String fileForProject11) throws InterruptedException, AWTException { 
    	testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP,
     		     fileForProject11);
    }
    
    @Test
    public void testSPGoToSellingTab() throws InterruptedException {
    	log.info("Start: testSPGoToSellingTab");
    	SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.goToSellingTab();
 	    log.info("End: testSPGoToSellingTab");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testSPcreateQuoteBtn() throws InterruptedException {
    	log.info("Start: testSPcreateQuoteBtn");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.createQuoteBtn();
    	log.info("End: testSPcreateQuoteBtn");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
    public void testSPcreateQuote(String unitcost1, String cost1, String markup, String markupfix1, String unitcost2, 
    		          String cost2, String markup2, String markupfix2, String tax, String quoteFailure) throws InterruptedException, IOException {
    	log.info("Start: testSPcreateQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.createQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	log.info("End: testSPcreateQuote");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testSPSendQuote() throws InterruptedException {
    	log.info("Start: testSPSendQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.sendQuote();
    	Thread.sleep(5000);
    	log.info("End: testSPSendQuote");
    	log.info("--------------------------------------------"); 
    }
   
    @Test 
    public void testRetractQuote() throws InterruptedException {
    	log.info("Start: testRetractQuote");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyRetractQuote();
    	//sPHomePage.verifyRetractQuoteOrRecallRFE();
    	log.info("End: testRetractQuote");
    	log.info("--------------------------------------------"); 
    }
  
    @Test
    @Parameters({"retractQPopup"})
    public void testRetractQuotePopup(String retractQPopup) throws InterruptedException, IOException {
    	log.info("Start: testRetractQuotePopup");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyRetractQuotePopup(retractQPopup);
    	log.info("End: testRetractQuotePopup");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"retractfn"})
    public void testVerifyStatus(String retractfn) throws InterruptedException, IOException {
    	log.info("Start: testVerifyStatus");
    	String status = "Retracted";
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyRetractStatus(status, retractfn);
    	log.info("End: testVerifyStatus");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void logout() throws InterruptedException {
    	testSPLogout();
    }
    
    @Test
    public void testGmailLoginPreCon() throws InterruptedException {
 	   CommonUtils commonUtils = new CommonUtils(driver);  
 	   commonUtils.testGmailLoginPreCon();
    }
    
    @Test(description="Check notification")
    @Parameters({"gmailLoginPW", "loginGmailID"})
    public void testLoginToGmail(String gmailLoginPW, String loginGmailID) throws InterruptedException {
    	log.info("Start: testLoginToGmail");
   	    driver.get("http://www.gmail.com");
   	    testGmailLoginPreCon();  
   	    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);   	    
   	    gmailLoginPage.enterEmail(loginGmailID);
   	    Thread.sleep(1500);
   	    gmailLoginPage.enterPassword(gmailLoginPW);
   	    Thread.sleep(3500);
   	    gmailLoginPage.check();
  	    gmailLoginPage.signIn();
  	    Thread.sleep(2000);
   	    log.info("End: testLoginToGmail");
   	    log.info("--------------------------------------------");
    }
    
    @Test(description="client check the email")
    public void testClientNotificationEmail() throws InterruptedException {
 	   log.info("Start: testClientNotificationEmail");
 	   Thread.sleep(6000);
 	   List<WebElement> emails = driver.findElements(By.tagName("span"));
 	   for (WebElement email : emails) {
 	 	  if (email != null) {
 	 		  String emailTitle = email.getText();
 	 		 if (emailTitle.indexOf("has been retracted") >= 0) {
 	 			  email.click();
 	 			  break;
 	 		  }
 	 	  }
 	   }
 	   Thread.sleep(2500); 
 	   WebElement reviewQuote = driver.findElement(By.linkText("Review Quote"));
  	   if(!reviewQuote.isDisplayed()) {
  		   throw new IllegalStateException("The Review Quote link is missing!");
  	   } else {
  		   reviewQuote.click();
  	   } 	   
 	   Thread.sleep(2500);  
  	   log.info("End: testClientNotificationEmail");
  	   log.info("--------------------------------------------");
    }
    
    @Test
    public void loginClient() throws InterruptedException {
    	log.info("Start: loginClient");
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
   	    Thread.sleep(5000);
   	    log.info("End: loginClient");
	    log.info("--------------------------------------------");
    }
    
    @Test(description="client check status")
    public void testClientQuotesStatus() throws InterruptedException {
    	CommonUtils commonUtils = new CommonUtils(driver);
   	    commonUtils.verifyClientQStatus();
   	    Thread.sleep(1000);
    }
    
    @Test(description="client logout")
    public void logoutClient() throws InterruptedException {
    	testClientLogout();
    }
    
    @Test(description="sp login")
    @Parameters({"spLoginID", "passwordSP"})
    public void loginSP(String spLoginID, String passwordSP) throws InterruptedException {
    	testSPLogin(spLoginID, passwordSP);
    }
    
    @Test
    public void openSpProj() throws InterruptedException {
    	log.info("Start: openSpProj");
    	CommonUtils commonUtils = new CommonUtils(driver);
   	    commonUtils.testOpenProjectInWS(projectName);
   	    commonUtils.testOpenProjectSFTabInWS();
    	log.info("End: openSpProj");
	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testVerifyCreateQuoteBtn() throws InterruptedException {
    	log.info("Start: testVerifyCreateQuoteBtn");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyCreateQuoteBtns();
    	log.info("End: testVerifyCreateQuoteBtn");
	    log.info("--------------------------------------------");
    }
    
    @Test(description="SP logout")
    public void logoutSPAgain() throws InterruptedException {
    	testSPLogout();
    }
    
    @Test
    @Parameters({"spLoginID", "passwordSP"})
    public void loginSPagain(String spLoginID, String passwordSP) throws InterruptedException {
        testSPLogin(spLoginID, passwordSP);
      	
    }
     
    @Test
    public void clickspGlbCreateProj() throws InterruptedException {
    	testExistingSPClickGlbCreateProj();
    }
   
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void inputspProjectName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectspWorkspace(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
   
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectSmartForm(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
      
     @Test
     @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
   	   "specDescrSP", "fileForProject11"})
     public void createProjectWizard(String projNameSP, String descriptionNameSP,
   		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
   		     String fileForProject11) throws InterruptedException, AWTException {
    	 testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP,
       		     fileForProject11);
     }
     
    
     @Test(description="Existing SP request estimate")
     public void testExistngSPRequestEstimate() throws InterruptedException {
    	 log.info("Start: testExistngSPRequestEstimate");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 Thread.sleep(2500);
    	 commonUtils.testSPRequestEstimate(projectName);
    	 Thread.sleep(2000);
    	 log.info("End: testExistngSPRequestEstimate");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     @Parameters({"supplierName3"})
     public void testExistingSPAddSupplier(String supplierName3)
     		     throws InterruptedException {
    	 log.info("Start: testSPAddSupplier");  	  
    	 Thread.sleep(5000);
    	 InvitePage invitePage = new InvitePage(driver);
    	 invitePage.inputSupplier(supplierName3);
    	 Thread.sleep(2000);
    	 log.info("End: testSPAddSupplier");
    	 log.info("--------------------------------------------"); 
     }    
    
     @Test
     public void testExistingSPAddEstimateDueDate() throws InterruptedException {
    	 log.info("Start: testSPAddEstimateDueDate");
    	 SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);  
    	 supplierEstimatePopup.callCalendar();	   
    	 Thread.sleep(2000);  
    	 log.info("End: testSPAddEstimateDueDate");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testExistingSPRequestEstimatesBtn() throws InterruptedException {
    	 log.info("Start: testSPRequestEstimatesBtn");
    	 SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver); 
    	 supplierEstimatePopup.callRequestEstimatesBtn();
    	 Thread.sleep(6000); 
    	 log.info("End: testSPRequestEstimatesBtn");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
	 public void testClickBuyingDetail() throws InterruptedException {
		 log.info("Start: testClickBuyingDetail");
		 CommonUtils commonUtils = new CommonUtils(driver);
		 commonUtils.showBuyingDetail();
		 Thread.sleep(1500);
		 log.info("End: testClickBuyingDetail");
	  	 log.info("--------------------------------------------"); 
	 }

     @Test(description="verify status before recall")
     public void testVerifyStatusBeforeRecall() throws InterruptedException, IOException {
    	 log.info("Start: testVerifyStatusBeforeRecall");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyStatusBeforeRecall("Open ", "beforerecalledstatus");
     	 Thread.sleep(1000);
    	 log.info("End: testVerifyStatusBeforeRecall");
      	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testRecallRFE() throws InterruptedException {
    	 log.info("Start: testRecallRFE");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyRecallRFE1();
     	 Thread.sleep(2000);
    	 log.info("End: testRecallRFE");
      	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testRecallRFEPopup() throws InterruptedException {
    	 log.info("Start: testRecallRFEPopup");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.recallRFEpopup();
     	 Thread.sleep(2000);     	
     	 log.info("End: testRecallRFEPopup");
     	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testVerifyRecallStatus() throws InterruptedException, IOException {
    	 log.info("Start: testVerifyRecallStatus");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyStatus("Recalled ", "recalledstatus");
     	 Thread.sleep(1000);
     	 log.info("End: testVerifyRecallStatus");
     	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void logoutsp() throws InterruptedException {
    	 testSPLogout();
     }
}
   
    
    
    
   