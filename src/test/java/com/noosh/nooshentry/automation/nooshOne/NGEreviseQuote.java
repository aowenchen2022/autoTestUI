/***********************************************************************
* This class tests the functionality for Create Quote 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

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

public class NGEreviseQuote extends BaseSeleniumTest {
  
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
   static String clientUrl;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if(domain.trim().equals("spd")) {
		   baseUrl = "https://demo.noosh.com/service/signup"; 
		   spUrl = "https://seleniumquote.noosh.com";
		   clientUrl = "https://seleniumquote.noosh.com/selclient/login";
	   } else {
		   baseUrl = "https://demo." + domain + "qa2.noosh.com/service/signup"; 
		   spUrl = "https://seleniumquote." + domain + ".noosh.com";
		   clientUrl = "https://seleniumquote." + domain + ".noosh.com/selclient/login";
	   }
   }

   @AfterTest
   public static void cleanup() {
	   //driver.quit();
	   log.info("\n    Completed: ***** ngeReviseQuoteTest_2.x_07072014.bat *****\n\n");
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
   
   @Test
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
     
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
  	   "specDescrSP", "fileForProject11"})
    public void testExistingSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
  		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
  		     String fileForProject11) throws InterruptedException, AWTException {    
  	   log.info("Start: testExistingSPcreateProjectWizard");
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
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(3000);
    	log.info("End: testSPcreateQuoteBtn");
    	log.info("--------------------------------------------"); 
    }
   
    @Test
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
    
    @Test
    public void testSPSendQuote() throws InterruptedException {
    	log.info("Start: testSPSendQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.sendQuote();
    	Thread.sleep(5000);
    	log.info("End: testSPSendQuote");
    	log.info("--------------------------------------------"); 
    }
    
    @Test(description="Check gmail login preconditions")
    public void testGmailLoginPreCon() throws InterruptedException {
 	   CommonUtils commonUtils = new CommonUtils(driver);  
 	   commonUtils.testGmailLoginPreCon();
    }
    
    @Test
    public void testRevise() throws InterruptedException {
    	log.info("Start: testRevise");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.getGear();
    	sPcreateQuotePage.reviseQ();
    	log.info("End: testRevise");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testReviseFields() throws InterruptedException {
    	log.info("Start: testReviseFields");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.reviseQFields();
    	log.info("End: testReviseFields");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testSendQuote() throws InterruptedException {
    	log.info("Start: testSendQuote");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	sPcreateQuotePage.preview();
    	sPcreateQuotePage.sendQuote();
    	CommonUtils commonUtils = new CommonUtils(driver);
  	    ///commonUtils.spLogout();
  	    Thread.sleep(3500);
    	log.info("End: testSendQuote");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"gmailLoginPW", "email3"})
    public void testClientNotification(String gmailLoginPW, String email3) throws InterruptedException {
    	log.info("Start: testClientNotification");
    	driver.get("http://www.gmail.com");
   	    //testGmailLoginPreCon();  
   	    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
   	    Thread.sleep(500);
   	    gmailLoginPage.enterEmail(email3);
   	    Thread.sleep(2500);
   	    gmailLoginPage.enterPassword(gmailLoginPW);
   	    Thread.sleep(3500);
   	    gmailLoginPage.check();
  	    gmailLoginPage.signIn();
  	    Thread.sleep(2000);
    	log.info("End: testClientNotification");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void testClientCheckReviseQuoteEmail() throws InterruptedException {
    	log.info("Start: testClientCheckReviseQuoteEmail");
    	Thread.sleep(5000);
    	List<WebElement> emails = driver.findElements(By.tagName("span"));
    	for (WebElement email : emails) {
    		if (email != null) {
    			String emailTitle = email.getText();
    			if (emailTitle.indexOf("Quote revised") >= 0) {
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
    	log.info("End: testClientCheckReviseQuoteEmail");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void testClientLogin() throws InterruptedException {
 	   log.info("Start: testClientLogin");	
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
 	   log.info("End: testClientLogin");
 	   log.info("--------------------------------------------");
    }
    
    @Test
    public void verifyProj() throws InterruptedException {
 	   log.info("Start: verifyProj");
 	   try {
 		   Assert.assertTrue(Page.isTextPresent(projectName, driver));
 	   } catch(Throwable e) {
 	   	   System.out.println("The project created by sp does Not show on client site!\n");
 	   	   log.info("The project created by sp does Not show on client site!");
 	   }
 	   log.info("End: verifyProj");
 	   log.info("--------------------------------------------");
     }
    
    @Test
    public void testQuoteStatus() throws InterruptedException {
    	log.info("Start: testQuoteStatus");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.verifyQStatus();
    	log.info("End: testQuoteStatus");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    public void testClientLogout() throws InterruptedException {
    	log.info("Start: testClientLogout");
    	Thread.sleep(1000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
    	driver.close();  
   	    Thread.sleep(2000);
   	    driver.switchTo().window(parentWindowHandle); 
   	    Thread.sleep(2000);
   	    driver.get("https://mail.google.com/mail/?logout"); 
   	    Thread.sleep(2000);
    	log.info("End: testClientLogout");
  	    log.info("--------------------------------------------");
    }
    
    @Test
    @Parameters({"spLoginUserID", "passwordSP"})
    public void loginsp(String spLoginUserID, String passwordSP) throws InterruptedException {
    	testSPLogin(spLoginUserID, passwordSP);
    }
    
    @Test
    public void createProj() throws InterruptedException {
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void inputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectWorkspace(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
    
    @Test(description="SP input client user")
    @Parameters({"clientuser", "clientFieldName"})
    public void inputClient(String clientuser, String clientFieldName) {
    	testSPInputClient(clientuser, clientFieldName);
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
    	testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP, fileForProject11);
    }
    
    @Test
    public void createQuoteBtn() throws InterruptedException {
    	log.info("Start: createQuoteBtn");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.goToSellingTab();
    	sPHomePage.createQuoteBtn();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(3000);
    	log.info("End: createQuoteBtn");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
    public void createQuote(String unitcost1, String cost1, String markup, String markupfix1, 
    		          String unitcost2, String cost2, String markup2, String markupfix2, String tax, 
    		          String quoteFailure) throws InterruptedException, IOException {
    	testSPcreateQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
    }
    
    @Test
    public void sendQuote() throws InterruptedException {
    	testSPSendQuote();
    }
    
    @Test
    public void addSpec() throws InterruptedException {
    	log.info("Start: addSpec");
    	CommonUtils commonUtils = new CommonUtils(driver);
 	    commonUtils.testOpenProjectSFTabInWS(projectName, "Specs");
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
    	log.info("End: addSpec");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testSpecListNotUpdate() throws InterruptedException {
    	log.info("Start: testSpecListNotUpdate");
    	SPcreateQuotePage sPcreateQuotePage = new SPcreateQuotePage(driver);
    	CommonUtils commonUtils = new CommonUtils(driver);
 	    commonUtils.testOpenProjectSFTabInWS();
    	sPcreateQuotePage.getGear();
    	sPcreateQuotePage.reviseQ();
    	log.info("End: testSpecListNotUpdate");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testUpdatedSpecNotinPopup() throws InterruptedException { 
    	log.info("Start: testUpdatedSpecNotinPopup");
    	String text = driver.findElement(By.tagName("body")).getText();
    	if (!text.contains("Creative Brief")) {
    		Assert.fail("New spec should not add in revise quote!");
    	} 
    	Thread.sleep(1000);
    	driver.findElement(By.className("ui-dialog-titlebar-close")).findElement(By.tagName("span")).click();
    	Thread.sleep(3000);
    	log.info("End: testUpdatedSpecNotinPopup");
    	log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"spLoginUserID"})
    public void logoutin(String spLoginUserID) throws InterruptedException {
    	log.info("Start: logoutin");
    	CommonUtils commonUtils = new CommonUtils(driver);
  	    //commonUtils.spLogout();
  	    Thread.sleep(3500);
  	    commonUtils.loginUser1(spLoginUserID);
  	    log.info("End: logoutin");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void glbcreateProj() throws InterruptedException {    	
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void inputProjName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectWS(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }
    

    @Test
    @Parameters({"clientuser", "clientFieldName"})
    public void inputClientAgain(String clientuser, String clientFieldName) {
    	testSPInputClient(clientuser, clientFieldName);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectSpec(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
    
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
  	   "specDescrSP", "fileForProject11"})
    public void createProjwizard(String projNameSP, String descriptionNameSP, String sku, String referenceNumber, String quantSP, String quantSP2, 
    		String quantSP3, String specDescrSP, String fileForProject11) throws InterruptedException, AWTException {    
    	testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP, fileForProject11);
    }
    
    @Test
    public void goToSellingTab() throws InterruptedException {
    	testSPGoToSellingTab();
    }
    
    @Test
    public void createquote() throws InterruptedException {
    	testSPcreateQuoteBtn();
    }
    
    @Test
    @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
    public void createSPQuote(String unitcost1, String cost1, String markup, String markupfix1, String unitcost2, String cost2, String markup2, 
    		      String markupfix2, String tax, String quoteFailure) throws InterruptedException, IOException {
    	testSPcreateQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
    }
    
    @Test
    public void sendQ() throws InterruptedException {
    	testSPSendQuote();
    }
    
    @Test
    @Parameters({"clientuser"})
    public void logoutspLoginclient(String clientuser) throws InterruptedException {
    	log.info("Start: logoutspLoginclient");
    	CommonUtils commonUtils = new CommonUtils(driver);
  	    //commonUtils.spLogout();
  	    Thread.sleep(3500);
  	    driver.get(clientUrl);
  	    Thread.sleep(3000);
  	    commonUtils.clientlogin(clientuser);
  	    Thread.sleep(4500);
  	    log.info("End: logoutspLoginclient");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testClientReviewQuote() throws InterruptedException {
    	log.info("Start: testClientReviewQuote");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.reviewQClient();
    	Thread.sleep(3000);
    	clientHomePage.clickQuoteAwardOrder();
    	Thread.sleep(5000);
    	clientHomePage.confirmQuoteOrder();
    	Thread.sleep(6000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
    	log.info("End: testClientReviewQuote");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"spLoginUserID", "quoteAccptFailure"})
    public void testSPReviewQ(String spLoginUserID, String quoteAccptFailure) throws InterruptedException, IOException {
    	log.info("Start: testSPReviewQ");
    	driver.get(spUrl); 
    	Thread.sleep(3000);
    	CommonUtils commonUtils = new CommonUtils(driver);    	
    	commonUtils.loginUser1(spLoginUserID);
    	commonUtils.testOpenProjectInWS(projectName);    	
    	commonUtils.testOpenProjectSFTabInWS();
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.verifyRetractStatus("Accepted", quoteAccptFailure);
    	log.info("End: testSPReviewQ");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    @Parameters({"spLoginUserID"})
    public void logoutinAgain(String spLoginUserID) throws InterruptedException {
    	logoutin(spLoginUserID);
    }
    
    @Test
    public void glbcreatProj() throws InterruptedException {    	
    	testExistingSPClickGlbCreateProj();
    }
    
    @Test
    @Parameters({"productName1", "projectNameField"})
    public void inputprojName(String productName1, String projectNameField) throws InterruptedException {
    	testExistingSPInputProjectName(productName1, projectNameField);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectWs(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectWorkspace(clientOrWorkspace);
    }

    @Test
    @Parameters({"clientuser", "clientFieldName"})
    public void inputclient(String clientuser, String clientFieldName) {
    	testSPInputClient(clientuser, clientFieldName);
    }
    
    @Test
    @Parameters({"clientOrWorkspace"})
    public void selectspec(String clientOrWorkspace) throws InterruptedException {
    	testExistingSPSelectSmartForm(clientOrWorkspace);
    }
   
    @Test
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
  	   "specDescrSP", "fileForProject11"})
    public void createprojwizard(String projNameSP, String descriptionNameSP, String sku, String referenceNumber, String quantSP, String quantSP2, 
    		String quantSP3, String specDescrSP, String fileForProject11) throws InterruptedException, AWTException {    
    	testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP, fileForProject11);
    }
    
    @Test
    public void goTosellingTab() throws InterruptedException {
    	testSPGoToSellingTab();
    }
    
    @Test
    public void creatQ() throws InterruptedException {
    	testSPcreateQuoteBtn();
    }
    
    @Test
    @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
    public void createSPQ(String unitcost1, String cost1, String markup, String markupfix1, String unitcost2, String cost2, String markup2, 
    		      String markupfix2, String tax, String quoteFailure) throws InterruptedException, IOException {
    	testSPcreateQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
    }
    
    @Test
    public void sendquote() throws InterruptedException {
    	testSPSendQuote();
    }
    
    @Test
    @Parameters({"clientuser"})
    public void logoutspLoginclientagain(String clientuser) throws InterruptedException {
    	log.info("Start: logoutspLoginclientagain");
    	CommonUtils commonUtils = new CommonUtils(driver);
  	    //commonUtils.spLogout();
  	    Thread.sleep(3500);
  	    driver.get(clientUrl);
	    Thread.sleep(3000);
  	    commonUtils.clientlogin(clientuser);
  	    Thread.sleep(3500);
  	    log.info("End: logoutspLoginclientagain");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void testClientRejectQuote() throws InterruptedException {
    	log.info("Start: testClientRejectQuote");
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.reviewQClient();
    	Thread.sleep(3000);
    	clientHomePage.clickQuoteReject();
    	Thread.sleep(3000);
    	clientHomePage.rejectReason();
    	Thread.sleep(1500);
    	clientHomePage.confirmQuoteOrderReject();
    	Thread.sleep(5000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
    	log.info("End: testClientRejectQuote");
  	    log.info("--------------------------------------------"); 
    } 
    
    @Test
    @Parameters({"spLoginUserID"})
    public void testSPRejectQuoteStatus(String spLoginUserID) throws InterruptedException {
    	log.info("Start: testSPRejectQuoteStatus");
    	driver.get(spUrl);
    	Thread.sleep(3000);
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.loginUser1(spLoginUserID);
    	commonUtils.testOpenProjectInWS(projectName);
    	commonUtils.testOpenProjectSFTabInWS();
    	commonUtils.verifySPQStatus("Rejected");
    	log.info("End: testSPRejectQuoteStatus");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void revise() throws InterruptedException {
    	testRevise();
    }
   
    @Test
    public void reviseFields() throws InterruptedException {
    	testReviseFields(); 
    }
    
    @Test
    public void sendquot() throws InterruptedException {
    	testSendQuote();
    }
    
    @Test
    @Parameters({"clientuser"})
    public void checkClientStatus(String clientuser) throws InterruptedException {
    	log.info("Start: checkClientStatus");
    	driver.get(clientUrl);
	    Thread.sleep(3000);
	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.clientlogin(clientuser);
  	    Thread.sleep(3500);
    	ClientHomePage clientHomePage = new ClientHomePage(driver);
    	clientHomePage.open();
    	clientHomePage.gotoBuying();
    	clientHomePage.verifyQStatus();
    	log.info("End: checkClientStatus");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test
    public void logoutClient() throws InterruptedException {
    	log.info("Start: logoutC");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
   	    Thread.sleep(2000);
   	    log.info("End: logoutC");
   	    log.info("--------------------------------------------"); 
    }
    
}
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
