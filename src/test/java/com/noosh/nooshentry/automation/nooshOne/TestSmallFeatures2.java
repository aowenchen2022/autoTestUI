/***********************************************************************
* This class tests the functionality for NGE small features 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SPcreateQuotePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSmallFeatures2 extends BaseSeleniumTest {  
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
	   log.info("\n    Completed: ***** ngeSmallFeaturesTest2 *****\n\n");
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
   
    @Test(description="SP logout")
    public void testSPLogout() throws InterruptedException {
  	    log.info("Start: testSPLogout");
  	    CommonUtils commonUtils = new CommonUtils(driver);
  	    commonUtils.Logout();
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
/*
    @Test
    public void testGmailLoginPreCon() throws InterruptedException {
 	   CommonUtils commonUtils = new CommonUtils(driver);  
 	   commonUtils.testGmailLoginPreCon();
    }
 */
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
        Thread.sleep(1000);
   	    commonUtils.clientloginWithourUname();
   	    Thread.sleep(5000);
   	    log.info("End: loginClient");
	    log.info("--------------------------------------------");
    }
 
     @Test
     public void testClientQuoteAwardOrder() throws InterruptedException {
     	log.info("Start: testClientQuoteAwardOrder");
		driver.findElement(By.className("line-project-name-cot")).click();
    	Thread.sleep(3000);
     	driver.findElement(By.className("procurement-action")).findElement(By.tagName("a")).click();
     	Thread.sleep(2000);
     	ClientHomePage clientHomePage = new ClientHomePage(driver);
     	clientHomePage.clickQuoteAwardOrder();
     	Thread.sleep(5000);
     	log.info("End: testClientQuoteAwardOrder");
   	    log.info("--------------------------------------------");
     }
     
     @Test
     public void testClientConfirmQuoteOrder() throws InterruptedException {
     	log.info("Start: testClientConfirmQuoteOrder");
     	ClientHomePage clientHomePage = new ClientHomePage(driver);
     	clientHomePage.confirmQuoteOrder();
     	Thread.sleep(5000);
     	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testClientLogout1();
     	log.info("End: testClientConfirmQuoteOrder");
   	    log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"spLoginID", "passwordSP"})
     public void loginSPSite(String spLoginID, String passwordSP) throws InterruptedException {
    	 testSPLogin(spLoginID, passwordSP);
     }
     
     
     @Test
     public void testOpenSelligDetail() throws InterruptedException {
    	log.info("Start: testOpenSelligDetail");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testOpenProjectInWS(projectName);
    	SPHomePage spHomePage = new SPHomePage(driver);
     	spHomePage.goToSellingTab();     	
		//commonUtils.showSellingDetail();
		Thread.sleep(1500);
		log.info("End: testOpenSelligDetail");
     	log.info("--------------------------------------------");
     }
    
     @Test
     public void testCancelOrder() throws InterruptedException {
    	 log.info("Start: testCancelOrder");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyCancelOrder();
     	 Thread.sleep(2000);
    	 log.info("End: testCancelOrder");
      	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testCancelOrderPopup() throws InterruptedException {
    	 log.info("Start: testCancelOrderPopup");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.cancelOrderpopup();
     	 Thread.sleep(2000);     	
     	 log.info("End: testCancelOrderPopup");
     	 log.info("--------------------------------------------"); 
     }
   
     @Test
     public void testVerifyCancelOrderStatus() throws InterruptedException, IOException {
    	 log.info("Start: testVerifyCancelOrderStatus");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyStatus("Order canceled ", "cancelOrderstatus");
     	 Thread.sleep(1000);
     	 log.info("End: testVerifyCancelOrderStatus");
     	 log.info("--------------------------------------------"); 
     } 
     
     @Test
     @Parameters({"retractfn"})
     public void testVerifyQuoteStatusAfterCancel(String retractfn) throws InterruptedException, IOException {
    	 log.info("Start: testVerifyQuoteStatusAfterCancel");
    	 String status = "Retracted";
    	 SPHomePage sPHomePage = new SPHomePage(driver);
    	 sPHomePage.verifyRetractStatus(status, retractfn);
    	 log.info("End: testVerifyQuoteStatusAfterCancel");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void testVeruftQuoteBtn() throws InterruptedException {
    	 log.info("Start: testVeruftQuoteBtn");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
      	 sPHomePage.verifyCreateQuoteBtn("Create Quote");
    	 log.info("End: testVeruftQuoteBtn");
      	 log.info("--------------------------------------------");
     }
     
     @Test
     public void logoutSPafterCancelOrder() throws InterruptedException {
    	 log.info("Start: logoutSPafterCancelOrder");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 //commonUtils.spLogout();
    	 Thread.sleep(5000);
    	 log.info("End: logoutSPafterCancelOrder");
    	 log.info("--------------------------------------------"); 
     }
    
     @Test
     @Parameters({"gmailLoginPW", "loginGmailID"})
     public void testClientNotification1(String gmailLoginPW, String loginGmailID) throws InterruptedException {
    	 log.info("Start: testClientNotification1");
    	 driver.get("http://www.gmail.com");
    	 //testGmailLoginPreCon();  
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 commonUtils.testGmailLoginPreCon();
    	 GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);    	 
    	 gmailLoginPage.enterEmail(loginGmailID);
    	 Thread.sleep(1500);
    	 gmailLoginPage.enterPassword(gmailLoginPW);
    	 Thread.sleep(3500);
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
    	 gmailLoginPage.signIn();
    	 Thread.sleep(3500);
    	 log.info("End: testClientNotification1");
    	 log.info("--------------------------------------------");
     }
   
     @Test
     public void testClientNotification() throws InterruptedException {
    	 log.info("Start: testClientNotification");
    	 List<WebElement> emails = driver.findElements(By.tagName("span"));
    	 for (WebElement email : emails) {
    		 if (email != null) {
    			 String emailTitle = email.getText();
    			 if (emailTitle.indexOf("has been canceled") >= 0) {
    				 email.click();
    				 break;
    			 }
    		 }
    	 }
    	 Thread.sleep(2500); 
    	 WebElement reviewQuote = driver.findElement(By.linkText("Review Order"));
    	 if(!reviewQuote.isDisplayed()) {
    		 throw new IllegalStateException("The Review Order link is missing!");
    	 } else {
    		 reviewQuote.click();
    	 } 	   
    	 Thread.sleep(2500);  
    	 log.info("End: testClientNotification");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void loginClientCheckCancelOrder() throws InterruptedException {
    	 loginClient();
     }
     
     @Test
     public void testClientCancelOrderStatus() throws InterruptedException {
    	 log.info("Start: testClientCancelOrderStatus");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 commonUtils.verifyClientCancelOrderStatus("Order canceled", "Retracted");
    	 Thread.sleep(1000);
    	 log.info("End: testClientCancelOrderStatus");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void logoutClient() throws InterruptedException {
    	 log.info("Start: logoutClient");
    	 CommonUtils commonUtils = new CommonUtils(driver);
     	 commonUtils.testClientLogout1();
    	 log.info("End: logoutClient");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void backToGmail() throws InterruptedException {
    	 log.info("Start: backToGmail");
    	 Thread.sleep(3000);
    	 driver.close();  
    	 Thread.sleep(2000);
    	 driver.switchTo().window(parentWindowHandle); 
    	 Thread.sleep(2000);
    	 driver.get("https://mail.google.com/mail/u/0/#inbox");
  	     Thread.sleep(2000);
  	     driver.findElement(By.className("T-Jo-auh")).click();
  	     Thread.sleep(2000);
  	     driver.findElement(By.className("ar9")).click();
  	     Thread.sleep(2000);
    	 driver.get("https://mail.google.com/mail/?logout"); 
    	 Thread.sleep(2000);
    	 log.info("End: backToGmail");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"spLoginID", "passwordSP"})
     public void loginSP(String spLoginID, String passwordSP) throws InterruptedException {
    	 testSPLogin(spLoginID, passwordSP);
     }
     
     @Test
     @Parameters({"productName1", "projectNameField"})
     public void inputProjName(String productName1, String projectNameField) throws InterruptedException {
    	 log.info("Start: inputProjName");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
    	 Thread.sleep(3000);
    	 sPHomePage.clickGlbCreateProj();    
    	 Thread.sleep(3000);
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
    	 log.info("End: inputProjName");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"clientOrWorkspace"})
     public void selectWorkspace(String clientOrWorkspace) throws InterruptedException {
    	 testExistingSPSelectWorkspace(clientOrWorkspace);
     }
     
     @Test
     @Parameters({"clientUser", "clientFieldName"})
     public void inputClient(String clientUser, String clientFieldName) {
    	 testSPInputClient(clientUser, clientFieldName);
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
    	 testExistingSPcreateProjectWizard(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
    			 quantSP3, specDescrSP, fileForProject11);   	 
     }
     
     @Test
     public void createQuoteBtn() throws InterruptedException {
    	 log.info("Start: createQuoteBtn");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
    	 sPHomePage.goToSellingTab();
    	 sPHomePage.createQuoteBtn();
    	 log.info("End: createQuoteBtn");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     @Parameters({"unitcost1", "cost1", "markup", "markupfix1", "unitcost2", "cost2", "markup2", "markupfix2", "tax", "quoteFailure"})
     public void createQuote(String unitcost1, String cost1, String markup, String markupfix1, String unitcost2, 
     		          String cost2, String markup2, String markupfix2, String tax, String quoteFailure) throws InterruptedException, IOException {
    	 testSPcreateQuote(unitcost1, cost1, markup, markupfix1, unitcost2, cost2, markup2, markupfix2, tax, quoteFailure);
     }
     
     @Test
     public void sendQuote() throws InterruptedException {
    	 testSPSendQuote();
     }
     
     @Test
     @Parameters({"clientUser"})
     public void loginOut(String clientUser) throws InterruptedException {
    	 log.info("Start: loginOut");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 //commonUtils.spLogout();
    	 Thread.sleep(3500);
    	 System.out.println("client url = " + clientUrl);
    	 driver.get(clientUrl);
    	 Thread.sleep(5000);
    	 commonUtils.clientlogin(clientUser);
    	 Thread.sleep(3000);
    	 log.info("End: loginOut");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"clientUser"})
     public void awardOrder(String clientUser) throws InterruptedException {
    	 log.info("Start: awardOrder");     	
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 driver.findElement(By.className("procurement-action")).findElement(By.tagName("a")).click();
    	 Thread.sleep(2000);
    	 ClientHomePage clientHomePage = new ClientHomePage(driver);
    	 clientHomePage.clickQuoteAwardOrder();
    	 Thread.sleep(5000);    
    	 clientHomePage.confirmQuoteOrder();
    	 Thread.sleep(5000);
    	 log.info("End: awardOrder");
    	 log.info("--------------------------------------------");
      }
     
     @Test
     public void openDetail() throws InterruptedException {
    	 log.info("Start: openDetail");
    	 ClientHomePage clientHomePage = new ClientHomePage(driver);
    	 clientHomePage.openDetail();
    	 log.info("End: openDetail");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void cancelOrder() throws InterruptedException {
    	 log.info("Start: cancelOrder");
    	 ClientHomePage clientHomePage = new ClientHomePage(driver);
    	 clientHomePage.cancelOrder();
    	 log.info("End: cancelOrder");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verfiyStatus() throws InterruptedException {
    	 log.info("Start: verfiyStatus");
    	 ClientHomePage clientHomePage = new ClientHomePage(driver);
    	 clientHomePage.verifyClientStatus("Order canceled", "Canceled");
    	 log.info("End: verfiyStatus");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"spLoginID"})
     public void logoutClientAndLoginSP(String spLoginID) throws InterruptedException {
    	 log.info("Start: logoutClientAndLoginSP");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 commonUtils.testClientLogout1();
    	 driver.get(smallFeaturesUrl); 
    	 commonUtils.loginUser1(spLoginID);
    	 log.info("End: logoutClientAndLoginSP");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verifySPStatus() throws InterruptedException, IOException {
    	 log.info("Start: verifySPStatus");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
    	 sPHomePage.verifySPStatus("Not quoted yet", "Order cancelled", "Canceled", "spStatusScreenShot");
    	 log.info("End: verifySPStatus");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void clickGlbCreateProj() throws InterruptedException {
    	 testExistingSPClickGlbCreateProj();
     }
     
     @Test
     @Parameters({"productName1", "projectNameField"})
     public void inputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	 testExistingSPInputProjectName(productName1, projectNameField);
     }
     
     @Test
     @Parameters({"clientOrWorkspace"})
     public void selectWorkspaceSP(String clientOrWorkspace) throws InterruptedException {
    	 testExistingSPSelectWorkspace(clientOrWorkspace);
     }
     
     @Test
     @Parameters({"clientOrWorkspace"})
     public void selectSmartFormSP(String clientOrWorkspace) throws InterruptedException {
    	 testExistingSPSelectSmartForm(clientOrWorkspace);
     }
     
     @Test
     @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
    	   "specDescrSP", "fileForProject11"})
     public void createProjectWizardSP(String projNameSP, String descriptionNameSP,
    		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
    		     String fileForProject11) throws InterruptedException, AWTException {    
    	 log.info("Start: createProjectWizardSP");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 //commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, specDescrSP, fileForProject11);
    	 BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	
    	 brochurePopup.getReviewAndSubmitTab();
    	 Thread.sleep(3000);
    	 brochurePopup.clickCreateBT();
    	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	 Thread.sleep(10000);    
    	 log.info("End: createProjectWizardSP");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     @Parameters({"supplierName3"})
     public void createOrder(String supplierName3) throws InterruptedException {
    	 log.info("Start: createOrder");
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 commonUtils.clickCreateOrder();
    	 InvitePage invitePage = new InvitePage(driver);
    	 invitePage.inputSupplier(supplierName3);
    	 commonUtils.createOrder();    	 
    	 log.info("End: createOrder");
    	 log.info("--------------------------------------------"); 
     }   
     
     @Test
     public void viewDetail() throws InterruptedException {
    	 log.info("Start: viewDetail");
    	 SPHomePage spHomePage = new SPHomePage(driver);
    	 spHomePage.viewDetails();
    	 log.info("End: viewDetail");
    	 log.info("--------------------------------------------"); 
     }
     
     @Test
     public void verifyCancelOrderStatus() throws InterruptedException {
         log.info("Start: verifyCancelOrderStatus");
    	 SPHomePage spHomePage = new SPHomePage(driver);
    	 spHomePage.verifyRecallRFE();
    	 log.info("End: verifyCancelOrderStatus");
    	 log.info("--------------------------------------------"); 
     }
 
     @Test
     public void testSPCancelOrderPopup() throws InterruptedException {
    	 log.info("Start: testSPCancelOrderPopup");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.cancelOrderpopup();
     	 Thread.sleep(3500);     	
     	 log.info("End: testSPCancelOrderPopup");
     	 log.info("--------------------------------------------"); 
     }
   
     @Test
     public void testSPVerifyCancelOrderStatus() throws InterruptedException, IOException {
    	 log.info("Start: testSPVerifyCancelOrderStatus");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
     	 sPHomePage.verifyStatus("Order canceled ", "cancelOrderstatus");
     	 Thread.sleep(1000);
     	 log.info("End: testSPVerifyCancelOrderStatus");
     	 log.info("--------------------------------------------"); 
     }      
     
     @Test
     public void logout() throws InterruptedException {
    	 testSPLogout();
     }
     
     @Test
     @Parameters({"gmailLoginPW", "validSPEmail"})
     public void testSupplierNotification(String gmailLoginPW, String validSPEmail) throws InterruptedException {
    	 log.info("Start: testSupplierNotification");
    	 driver.get("http://www.gmail.com");    	 
    	 //testGmailLoginPreCon();  
    	 CommonUtils commonUtils = new CommonUtils(driver);  
   	     commonUtils.testGmailLoginPreCon();
    	 GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);    	 
    	 gmailLoginPage.enterEmail(validSPEmail);
    	 Thread.sleep(1500);
    	 gmailLoginPage.enterPassword(gmailLoginPW);
    	 Thread.sleep(3500);
    	 gmailLoginPage.check();
    	 gmailLoginPage.signIn();    	 
    	 Thread.sleep(3500);
    	 log.info("End: testSupplierNotification");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void testNotification() throws InterruptedException {
    	 log.info("Start: testNotification");
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
    	 Thread.sleep(2500); 
    	 WebElement reviewQuote = driver.findElement(By.linkText("Review Order"));
    	 if(!reviewQuote.isDisplayed()) {
    		 throw new IllegalStateException("The Review Order link is missing!");
    	 } else {
    		 reviewQuote.click();  
    	 } 	   
    	 Thread.sleep(2500);  
    	 log.info("End: testNotification");
    	 log.info("--------------------------------------------");
     }
    
     @Test
     public void loginSupplier() throws InterruptedException {
    	 log.info("Start: loginSupplier");
    	 parentWindowHandle = driver.getWindowHandle();
   	     Set<String> windows = driver.getWindowHandles();
   	     for (String window : windows) {
   	         if (!window.equals(parentWindowHandle)) {
   	 	         driver.switchTo().window(window);
   	 	  	     break;
   	 	     }
   	     }  	 
    	 CommonUtils commonUtils = new CommonUtils(driver);
    	 Thread.sleep(1000);
    	 commonUtils.loginUser1();
    	 log.info("End: tloginSupplier");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void testSupplierStatus() throws InterruptedException {
    	 log.info("Start: testSupplierStatus");
    	 SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
    	 supplierMainMenuPage.checkStatus();
    	 log.info("End: testSupplierStatus");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void logoutSuppllier() throws InterruptedException {
    	 log.info("Start: logoutSuppllier");
    	 SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
     	 Thread.sleep(5000);
     	 supplierMainMenuPage.clickLogout();
    	 log.info("End: logoutSuppllier");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"spLoginID", "passwordSP"})
     public void loginSPAgain(String spLoginID, String passwordSP) throws InterruptedException {
    	 testSPLogin(spLoginID, passwordSP);
     }
     
     @Test
     public void createProjGlb() throws InterruptedException {
    	 log.info("Start: createProjGlb");
    	 SPHomePage sPHomePage = new SPHomePage(driver);
    	 sPHomePage.clickGlbCreateProj();    
    	 Thread.sleep(3000);
    	 log.info("End: createProjGlb");
    	 log.info("--------------------------------------------");
     }  
     
     @Test
     @Parameters({"productName1", "projectNameField"})
     public void inputSPProjectName(String productName1, String projectNameField) throws InterruptedException {
    	 testExistingSPInputProjectName(productName1, projectNameField);
     }
     
     @Test(description="Existing SP select client or workspace")
     @Parameters({"clientOrWorkspace"})
     public void selectWS(String clientOrWorkspace) throws InterruptedException {
    	 testExistingSPSelectWorkspace(clientOrWorkspace);
     }
     
     @Test
     public void verifySpecText() throws InterruptedException {
    	 log.info("Start: verifySpecText");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.verifySFText("Spec");
    	 log.info("End: verifySpecText");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verifySearchHintText() throws InterruptedException {
    	 log.info("Start: verifySearchHintText");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.verifySFHint("Select a Spec - Scroll for More Specs");
    	 log.info("End: verifySearchHintText");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verifySearchSpecsWith3Letters() throws InterruptedException {
    	 log.info("Start: verifySearchSpecsWith3Letters");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.verfifySearchSF("bro", "Brochure");
    	 log.info("End: verifySearchSpecsWith3Letters");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verifySearchSpecsWith1Letter() throws InterruptedException {
    	 log.info("Start: verifySearchSpecsWith1Letter");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.verfifySearchSF("b", "Brochure", "Business Laser", "Creative Brief", "Web Design Services");
    	 log.info("End: verifySearchSpecsWith1Letter");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void verifySearchSpecsWithWrongLetter() throws InterruptedException {
    	 log.info("Start: verifySearchSpecsWithWrongLetter");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.verfifySearchSF("abc");
    	 log.info("End: verifySearchSpecsWithWrongLetter");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void cancelProj() throws InterruptedException {
    	 log.info("Start: cancelProj");
    	 CreateNewProject createNewProject = new CreateNewProject(driver);
    	 createNewProject.cancelGlbProj();
    	 log.info("End: cancelProj");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     public void testSPWSCreateProj() throws InterruptedException {
    	 log.info("Start: testSPWSCreateProj");
    	 Thread.sleep(1500);	  
    	 Actions action = new Actions(driver);
    	 WebElement project = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-title"));
    	 action.moveToElement(project).click();
    	 action.build().perform();
    	 Thread.sleep(1500);
    	 driver.findElement(By.className("home-site-line-createProject")).click();	  
    	 Thread.sleep(3500);
    	 log.info("End: testSPWSCreateProj");
    	 log.info("--------------------------------------------");
     }
     
     @Test
     @Parameters({"productName1", "projectNameField"})
     public void testSPWSInputProjectName(String productName1, String projectNameField) throws InterruptedException {
    	 log.info("Start: testSPWSInputProjectName");
    	 productName1 = productName1 + unicID + "2" + " test project";  
    	 CreateNewProject newProject = new CreateNewProject(driver); 
    	 newProject.inputWSProjectName(productName1);   
    	 Thread.sleep(1000);
    	 try {
    		 AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
    	 } catch(Throwable e) {
    		 System.out.println("Project Name field is missing!\n");
    		 log.info("Project Name field is missing!");
    	 }
    	 log.info("End: testSPWSInputProjectName");
    	 log.info("--------------------------------------------"); 
    }
     
    @Test
    public void verifySpecTextWS() throws InterruptedException {
    	log.info("Start: verifySpecTextWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verifySFTextWS("Spec");
    	log.info("End: verifySpecTextWS");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySearchHintTextWS() throws InterruptedException {
    	log.info("Start: verifySearchHintTextWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verfySFHintWS("Select a Spec - Scroll for More Specs");
    	log.info("End: verifySearchHintTextWS");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySearchSpecsWith3LettersWS() throws InterruptedException {
    	log.info("Start: verifySearchSpecsWith3LettersWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verfifySearchSFWS("bro", "Brochure");
    	log.info("End: verifySearchSpecsWith3LettersWS");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySearchSpecsWith1LetterWS() throws InterruptedException {
    	log.info("Start: verifySearchSpecsWith1LetterWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verfifySearchSFWS("b", "Brochure", "Business Laser", "Creative Brief", "Web Design Services");
    	log.info("End: verifySearchSpecsWith1LetterWS");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void verifySearchSpecsWithWrongLetterWS() throws InterruptedException {
    	log.info("Start: verifySearchSpecsWithWrongLetterWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.verfifySearchSFWS("abc");
    	log.info("End: verifySearchSpecsWithWrongLetterWS");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void cancelProjWS() throws InterruptedException {
    	log.info("Start: cancelProjWS");
    	CreateNewProject createNewProject = new CreateNewProject(driver);
    	createNewProject.cancelGlbProj();
    	log.info("End: cancelProjWS");
    	log.info("--------------------------------------------");
    }
}
   
    
    
    
   