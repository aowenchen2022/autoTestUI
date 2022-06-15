/***********************************************************************
* This class tests the functionality for Buying with Firefox browser
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
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
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.buyerSite.SupplierInvitePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSPSuppliers2 extends BaseSeleniumTest {
  
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
	   if(domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName +".com/service/signup"; 
		   loginUrl = "https://selenium263945." + companyName + ".com";
	   } else {
		   baseUrl = "https://demo." + domain + "qa2." + companyName + ".com/service/signup"; 
		   loginUrl = "https://selenium263945." + domain + "." + companyName + ".com";
	   }
   }

   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spSupplierTest2 *****\n\n");
   }
   
   @Test(description="SP login")
   @Parameters({"loginUserID", "passwordSP"})
   public void testSPLogin1stTime(String loginUserID, String passwordSP) throws InterruptedException {
     	 log.info("Start: testSPLogin1stTime");
         Thread.sleep(1500);
         driver.get(loginUrl); 
         CommonUtils commonUtils = new CommonUtils(driver);
         commonUtils.loginUser1(loginUserID);
         spSiteLoginPage = driver.getCurrentUrl(); 
         log.info("End: testSPLogin1stTime");
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
   
   @Test(description="Existing SP cancel global create project")
   public void testExistingSPCancelCreateProj() throws InterruptedException {
  	   log.info("Start: testExistingSPCancelCreateProj");
  	   CreateNewProject createNewProject = new CreateNewProject(driver);
  	   createNewProject.cancelGlbProj();   
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
  	   productName1 = productName1 + unicID + "33";  
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
   @Parameters({"clientOrWorkspace", "clientFieldFailure"})
   public void testExistingSPSelectWorkspace(String clientOrWorkspace, String clientFieldFailure) throws InterruptedException, IOException {
  	   log.info("Start: testExistingSPSelectWorkspace");
  	   CreateNewProject createNewProject = new CreateNewProject(driver);
  	   createNewProject.selectWorkSpace();
       Thread.sleep(1000);
       try {
    	   AssertJUnit.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
       } catch(Throwable e) {
    	   System.out.println("Client or Workspace field is missing!\n");
    	   log.info("Client or Workspace field is missing!");
    	   CommonUtils commonutils = new CommonUtils(driver);
		   commonutils.getScreenShot(clientFieldFailure);
       }
       log.info("End: testExistingSPSelectWorkspace");
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
  	   /* tmp comment out 11/15
  	   WebElement imgPreview = driver.findElement(By.className("uploaded-file-preview-image"));
  	   if (imgPreview == null) {
  			   System.out.println("Image uploading is failed!");
  			   log.info("Image uploading is failed!"); 
  	   } 
  	   */
  	   
  	   brochurePopup.clickUploadFileDropBT();
  	   Thread.sleep(2000);
  	   page.robotUpload();
  	   Thread.sleep(6500);	
  	   commonUtils.verifyUploadedImg(fileForProject11);
  	   
  	   brochurePopup.getReviewAndSubmitTab();
  	   Thread.sleep(3000);  	   
  	   testExistingSPAddSmartFormPage(quantSP, quantSP2);   
  	   Thread.sleep(2000); 
       brochurePopup.clickCreateBT();    
       Thread.sleep(7500);
       driver.findElement(By.linkText("Specs")).click();
  	   Thread.sleep(2000);
  	   driver.findElement(By.className("project-products-name")).click();
  	   Thread.sleep(500);
  	   driver.findElement(By.className("inline-edit-product-edit")).click();
  	   Thread.sleep(500);
  	   driver.findElement(By.className("expend-view")).findElement(By.tagName("textarea")).sendKeys("test desciption");
  	   driver.findElement(By.className("inline-edit-product-save")).click();
  	   Thread.sleep(5000);
  	   log.info("End: testSPcreateProjectProjectWizard");
  	   log.info("--------------------------------------------"); 
    }
    
    @Test(description="Existing SP request estimate")
    public void testExistngSPRequestEstimate() throws InterruptedException {
 	    log.info("Start: testExistngSPRequestEstimate");
 	    CommonUtils commonUtils = new CommonUtils(driver);
 	    Thread.sleep(4500);
 	    commonUtils.testSPRequestEstimate(projectName);
 	    Thread.sleep(2000);
 	    log.info("End: testExistngSPRequestEstimate");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="add smart form")
    @Parameters({"quantSP", "quantSP2"})
    public void testExistingSPAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
    	log.info("Start: testExistingSPAddSmartFormPage");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver);  	   
    	addSmartFormPage.clickAddSmart();    
    	Thread.sleep(1000);
    	if(spSiteLoginPage.contains("sdm"))
    		addSmartFormPage.clickSmartFormSdm();     	    
        else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("qa2"))  
        	addSmartFormPage.clickSmartForm2();    	    
        else 
        	addSmartFormPage.clickSmartForm2(); 
        Thread.sleep(3000);  	
    	brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000);
    	addSmartFormPage.clickAddSmart();
    	if(spSiteLoginPage.contains("sdm")) {
    	    Alert alert = driver.switchTo().alert();
    	    alert.accept();
    	    Thread.sleep(6000);
    	}
    	addSmartFormPage.clickSmartForm();
    	addSmartFormPage.inputOneQuantity(quantSP);  	  
    	Thread.sleep(2000);
    	brochurePopup.getReviewAndSubmitTab();
    	Thread.sleep(3000); 
    	log.info("End: testExistingSPAddSmartFormPage");
    	log.info("--------------------------------------------"); 
    }    
	   
    @Test(description="Existing SP adding two suppliers")
    @Parameters({"supplierName1", "supplierName2"})
    public void testExistingSPAddSuppliers(String supplierName1, String supplierName2)
    		     throws InterruptedException {
  	   log.info("Start: testSPAddSuppliers");  	   
  	   supplierUser1 = supplierName1 + "+" + "supplier" + unicID  + "@noosh.com";	   
	   Thread.sleep(5000);
	   InvitePage invitePage = new InvitePage(driver);
 	   invitePage.inputSupplier(supplierUser1);
 	   Thread.sleep(2000);
 	   invitePage.inputSupplier(supplierName2);
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
  	  log.info("Start: testLogout");
  	  Thread.sleep(6500);
  	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
  	  Thread.sleep(2000);
  	  supplierMainMenuPage.clickLogout();
  	  Thread.sleep(2000); 
  	  log.info("End: testLogout");
  	  log.info("--------------------------------------------");
    }  
    
    @Test(description="New supplier Login to Gmail")
    @Parameters({"gmailLoginPW"})
    public void testLoginToGmail1stTime(String gmailLoginPW) throws InterruptedException {
  	  log.info("Start: testLoginToGmail1stTime");
  	  Thread.sleep(1000);
  	  driver.get("http://www.gmail.com");
  	  CommonUtils commonUtils = new CommonUtils(driver);
      commonUtils.testGmailLoginPreCon();    	  
  	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
  	  Thread.sleep(10000);
  	  gmailLoginPage.enterEmail("seleniumtest12345@noosh.com");
  	  Thread.sleep(1000);
  	  gmailLoginPage.enterPassword(gmailLoginPW);
  	  Thread.sleep(1000);
  	  gmailLoginPage.check();
  	  gmailLoginPage.signIn();
  	  Thread.sleep(3000);
  	  log.info("End: testLoginToGmail1stTime");
  	  log.info("--------------------------------------------");
    }
   
    @Test(description="New Supplier Check Gmail")
    public void testCheckGmail() throws InterruptedException {
  	  log.info("Start: testCheckGmail");
  	  Thread.sleep(5000);
  	  //List<WebElement> emails = driver.findElements(By.tagName("span"));
  	  List<WebElement> emails = driver.findElement(By.className("AO")).findElements(By.className("bog"));
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
	   parentWindowHandle = driver.getWindowHandle();
  	   Set<String> windows = driver.getWindowHandles();
  	   for (String window : windows) {
  	       if (!window.equals(parentWindowHandle)) {
  		       driver.switchTo().window(window);
  	  	       break;
  		   }
  	  }
  	  WebElement acceptInv = driver.findElement(By.linkText("Accept Invitation and Send Estimate"));
	      if(!acceptInv.isDisplayed()) {
		      throw new IllegalStateException("The accept invitation link is missing!");
	      } else {
		      acceptInv.click();
	      } 	   
  	  Thread.sleep(2500);
  	  log.info("End: testCheckGmail");
  	  log.info("--------------------------------------------");
    }
    
    @Test(description="New supplier signup")
    @Parameters({"newSupplierFullName", "newSupplierPasswd", "newSupplierCompanyName"})
    public void testNewSupplierSignup(String newSupplierFullName, String newSupplierPasswd, 
  		                           String newSupplierCompanyName) throws InterruptedException { 	 
       log.info("Start: testSupplier1Signup");
       newSupplierCompanyName = newSupplierCompanyName + unicID + "55";
       newSupplierUserCompanyName = newSupplierCompanyName;     
       newSupplierUserCompanyName = newSupplierUserCompanyName.substring(7);  
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
  	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  	  Thread.sleep(6000);
  	  log.info("End: testSupplier1Signup");
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
    @Parameters({"supplierPrice1", "supplierPrice2", "supplierPrice3", "supplierPrice4", 
    	                           "shippingEstPrice1", "shippingEstPrice2", "shippingEstPrice3", 
    	                           "shippingEstPrice4", "taxPrice"})
    public void testNewSupplierCreateEstimate(String supplierPrice1, String supplierPrice2, 
    		  String supplierPrice3, String supplierPrice4, String shippingEstPrice1, 
    		  String shippingEstPrice2, String shippingEstPrice3, String shippingEstPrice4,
  		      String taxPrice) throws InterruptedException {
  	  log.info("Start: testNewSupplierCreateEstimate");
  	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
  	  EstimateRFEPage estimateRFEPage = new EstimateRFEPage(driver);
  	  estimateRFEPage.clickCreateEstimateBtn1();
  	  Thread.sleep(2000);
  	  List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
  	  List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
  	  prices.get(0).clear();
  	  prices.get(0).sendKeys(supplierPrice1);
  	  prices.get(0).sendKeys("{TAB}");
  	  Thread.sleep(2000);
  	  shippingPrices.get(0).clear();
  	  shippingPrices.get(0).sendKeys(shippingEstPrice1);
	  shippingPrices.get(0).sendKeys("{TAB}");
  	  Thread.sleep(2000);  	  
  	  prices.get(1).clear();
  	  prices.get(1).sendKeys(supplierPrice2);
  	  prices.get(1).sendKeys("{TAB}");
  	  Thread.sleep(2000);
  	  shippingPrices.get(1).clear();
  	  shippingPrices.get(1).sendKeys(shippingEstPrice2);
  	  shippingPrices.get(1).sendKeys("{TAB}");
  	  Thread.sleep(2000);  	  
  	  prices.get(2).clear();
  	  prices.get(2).sendKeys(supplierPrice3);
 	  prices.get(2).sendKeys("{TAB}");
    	  Thread.sleep(2000);
  	  shippingPrices.get(2).clear();
  	  shippingPrices.get(2).sendKeys(shippingEstPrice3);
  	  shippingPrices.get(2).sendKeys("{TAB}");
  	  Thread.sleep(3000);  	  
  	  prices.get(3).clear();
 	  prices.get(3).sendKeys(supplierPrice4);
 	  prices.get(3).sendKeys("{TAB}");
   	  Thread.sleep(2000);
 	  shippingPrices.get(3).clear();
 	  shippingPrices.get(3).sendKeys(shippingEstPrice4);
	  shippingPrices.get(3).sendKeys("{TAB}");
 	  Thread.sleep(3000); 	  
  	  estimateRFEPage.clickSendEstimateBtn();
  	  Thread.sleep(5000);
  	  
  	  String msg = driver.findElement(By.id("confirmPopupWindow")).getText();
  	  if((msg == null) || (msg.isEmpty())) {
  		   //supplierMainMenuPage.verifyEstSent("Estimate Sent\nRevise Estimate Retract Estimate");
  	      supplierMainMenuPage.verifyEstSent("Estimate Sent");
	  } else {
		   Assert.fail("Unexpected popup");
	  }
  	  Thread.sleep(3000);
  	  log.info("End: testNewSupplierCreateEstimate");
  	  log.info("--------------------------------------------");
    }     
    
    @Test(description="New Supplier logout")
    public void testNewSupplierLogout() throws InterruptedException {
  	  log.info("Start: testNewSupplierLogout");
  	  Thread.sleep(3000);
  	  SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
  	  Thread.sleep(5000);
  	  supplierMainMenuPage.clickLogout();
  	  Thread.sleep(3000);
  	  log.info("End: testNewSupplierLogout");
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
    
    @Test(description="Existing supplier login gmail")
    @Parameters({"gmailLoginPW"})
    public void testSupplier2LoginToGmail(String gmailLoginPW) throws InterruptedException  {
  	 	  log.info("Start: testSupplier2LoginToGmail");
  	 	  
  	 	  Thread.sleep(1500);
  	 	  driver.get("http://www.gmail.com");
  	 	  CommonUtils commonUtils = new CommonUtils(driver);
  	 	  commonUtils.testGmailLoginPreCon();
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
  	 	  //gmailLoginPage.mouseup();
	 	  Thread.sleep(1000);
 /* 	 	  WebElement anotherAcc = driver.findElement(By.id("identifierLink"));
  		  Actions action = new Actions(driver);
  		  //WebElement project = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-title"));
  		  //action.moveToElement(item.get(1)).click();
		  action.moveToElement(anotherAcc).click();
  		  action.build().perform();
  		  */
  	 	  gmailLoginPage.enterEmail("seleniumtest54321@noosh.com");
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
    @Parameters({"supplier2Price1", "supplier2Price2", "supplier2Price3", "supplier2Price4", "shipping2EstPrice1",
                 "shipping2EstPrice2", "shipping2EstPrice3", "shipping2EstPrice4", "taxPrice2", "supplierEstBtn"})
    public void testExistingSupplier2CreateEstimate(String supplier2Price1, String supplier2Price2, String supplier2Price3, 
    		       String supplier2Price4, String shipping2EstPrice1, String shipping2EstPrice2, String shipping2EstPrice3, 
    		       String shipping2EstPrice4, String taxPrice2, String supplierEstBtn) throws InterruptedException, IOException {
  	   log.info("Start: testExistingSupplier2CreateEstimate");
  	   SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
  	   EstimateRFEPage estimateRFEPage = new EstimateRFEPage(driver);
  	   try {
  	        estimateRFEPage.clickCreateEstimateBtn1();
  	 } catch(Exception e) {
		 CommonUtils commonutils = new CommonUtils(driver);
		 commonutils.getScreenShot(supplierEstBtn);
	 }
  	   Thread.sleep(5000);
  	   List<WebElement> prices = driver.findElements(By.className("estimate-price-only"));
  	   List<WebElement> shippingPrices = driver.findElements(By.className("estimate-shipping"));
  	   prices.get(0).clear();
  	   prices.get(0).sendKeys(supplier2Price1);
  	   prices.get(0).sendKeys("{TAB}");
  	   Thread.sleep(2000);
  	   shippingPrices.get(0).clear();
  	   shippingPrices.get(0).sendKeys(shipping2EstPrice1);
  	   shippingPrices.get(0).sendKeys("{TAB}");
  	   Thread.sleep(2000);  	 	  
  	   prices.get(1).clear();
  	   prices.get(1).sendKeys(supplier2Price2);
  	   prices.get(1).sendKeys("{TAB}");
  	   Thread.sleep(2000);
  	   shippingPrices.get(1).clear();
  	   shippingPrices.get(1).sendKeys(shipping2EstPrice2);
  	   shippingPrices.get(1).sendKeys("{TAB}");
  	   Thread.sleep(2000);  		  
  	   prices.get(2).clear();
  	   prices.get(2).sendKeys(supplier2Price3);
  	   prices.get(2).sendKeys("{TAB}");
  	   Thread.sleep(2000);
  	   shippingPrices.get(2).clear();
  	   shippingPrices.get(2).sendKeys(shipping2EstPrice3);
  	   prices.get(2).sendKeys("{TAB}");
  	   Thread.sleep(2000);  	   
  	   prices.get(3).clear();
	   prices.get(3).sendKeys(supplier2Price4);
	   Thread.sleep(2000);
	   shippingPrices.get(3).clear();
	   shippingPrices.get(3).sendKeys(shipping2EstPrice4);
	   Thread.sleep(2000);  	 	  
  	   estimateRFEPage.clickSendEstimateBtn();
  	   Thread.sleep(3000);
  	   /*
 	   String msg = driver.findElement(By.id("confirmPopupWindow")).getText();
 	   if((msg == null) || (msg.isEmpty())) {
 		   supplierMainMenuPage.verifyEstSent("Estimate Sent");
	   } else {
		   Assert.fail("Unexpected popup");
	   }
 	   Thread.sleep(2000);
 	   */
  	   log.info("End: testExistingSupplier2CreateEstimate");
  	   log.info("--------------------------------------------");
    }
    
    @Test(description="Exsiting supplier logout")
    public void testExistingSupplier2Logout() throws InterruptedException {
  	   log.info("Start: testExistingSupplier2Logout");
  	   Thread.sleep(3000);
       CommonUtils commonUtils = new CommonUtils(driver);
       commonUtils.logoutUser();
  	   Thread.sleep(3000);  	  
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
    
    @Test(description="sp login email account")    
    @Parameters({"gmailLoginPW"})
    public void testSPLoginGmail(String gmailLoginPW) throws InterruptedException {
  	  log.info("Start: testSPLoginGmail");
  	  Thread.sleep(1000);
  	  driver.get("http://www.gmail.com");
  	  CommonUtils commonUtils = new CommonUtils(driver);
      commonUtils.testGmailLoginPreCon();    
/*		try {
		    WebElement next = driver.findElement(By.id("identifierLink"));
		    next.click();
			Thread.sleep(1500);
		} catch (NoSuchElementException ne) {
		}
		*/
  	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
  	  Thread.sleep(10000);
  	  gmailLoginPage.enterEmail("seleniumtest12345@noosh.com");
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
	   parentWindowHandle = driver.getWindowHandle();
  	   Set<String> windows = driver.getWindowHandles();
  	   for (String window : windows) {
  	       if (!window.equals(parentWindowHandle)) {
  		       driver.switchTo().window(window);
  	  	       break;
  		   }
  	  }
	  CommonUtils commonUtils = new CommonUtils(driver);
      commonUtils.reviewEst();
  	  Thread.sleep(3000);  	  
  	  log.info("End: testSPCheckGmail");
  	  log.info("--------------------------------------------");
    }
        
    @Test(description="sp login")
    public void testSPLogin() throws InterruptedException {
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
        Thread.sleep(5000);
   	    log.info("End: testSPLogin");
   	    log.info("--------------------------------------------");
     }   
    
    @Test(description="sp award order")
    @Parameters({""})
    public void testAwardOrderPage() throws InterruptedException {
    	log.info("Start: testAwardOrderPage");
    	SPAwardOrderPage spAwardOrderPage = new SPAwardOrderPage(driver);
    	spAwardOrderPage.selectAEstimate();
    	spAwardOrderPage.selectAEstimate2();
    	spAwardOrderPage.clickAwardBtn();
    	log.info("End: testAwardOrderPage");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="sp confirm order")
    public void testSPConfirmOrderPage() throws InterruptedException {
    	log.info("Start: testConfirmOrderPage");
    	SPConfirmOrderPage spConfirmOrderPage = new SPConfirmOrderPage(driver);
    	spConfirmOrderPage.clickConfirmOrderBtn();
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
    
    @Test(description="Supplier1 login gmail") 
    @Parameters({"gmailLoginPW"})
    public void testSupplier1LoginGmail(String gmailLoginPW) throws InterruptedException {
    	log.info("Start: testSupplier1LoginGmail");
    	GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
    	Thread.sleep(3000);
    	driver.get("http://www.gmail.com"); 
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testGmailLoginPreCon();   
    	Thread.sleep(3000);
    	
   	 	try {
		    WebElement next = driver.findElement(By.id("identifierLink"));
		    next.click();
			Thread.sleep(1500);
		} catch (NoSuchElementException ne) {
		}
    	//gmailLoginPage.enterEmail("seleniumtest12345@noosh.com");
    	Thread.sleep(1000);
    	gmailLoginPage.enterPassword(gmailLoginPW);
    	Thread.sleep(1000);
    	gmailLoginPage.signIn();
    	Thread.sleep(1500);
    	log.info("End: testSupplier1LoginGmail");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Supplier1 check confirmation in the email ")
    public void testSupplier1EstimateNotAccept() throws InterruptedException {
    	log.info("Start: testSupplier1EstimateNotAccept");
    	Thread.sleep(5000);
    	List<WebElement> emails = driver.findElements(By.tagName("span"));
    	for (WebElement email : emails) {
    		if (email != null) {
    			String emailTitle = email.getText();
    			//if (emailTitle.indexOf(newSupplierUserCompanyName) >= 0) {
    			if (emailTitle.indexOf("or Selenium") >= 0) {
    				email.click();
    				break;
    			}
    		}
    	}
    	Thread.sleep(5000);  	 
    	log.info("End: testSupplier1EstimateNotAccept");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Supplier1 logout gmail")
    public void testSupplier1LogoutGmail() throws InterruptedException {
    	log.info("Start: testSupplier1LogoutGmail");
    	Thread.sleep(3000);
    	driver.get("https://mail.google.com/mail/?logout"); 
    	Thread.sleep(2000);  	  
    	log.info("End: testSupplier1LogoutGmail");
    	log.info("--------------------------------------------");
    }


   @Test(description="Supplier2 login gmail")
   @Parameters({"gmailLoginPW"})
   public void testSupplier2LoginGmail(String gmailLoginPW) throws InterruptedException {
	   log.info("Start: testSupplier2LoginGmail");
	   Thread.sleep(3000);
	   driver.get("http://www.gmail.com");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   //commonUtils.testGmailLoginPreCon();
	   
	   GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
	   Thread.sleep(3000);
		 try {
			 	List<WebElement> next = driver.findElements(By.id("identifierLink"));
			    next.get(1).click();
				Thread.sleep(1500);
			} catch (NoSuchElementException ne) {
			}
	   //gmailLoginPage.enterEmail("seleniumtest54321@noosh.com");
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

  @Test(description="Supplier2 logout email")
  @Parameters({"gmailLoginPW"})
  public void testSupplier2LogoutGmail(String gmailLoginPW) throws InterruptedException {
  	  log.info("Start: testSupplier2LogoutGmail");
  	  Thread.sleep(2000);
  	  driver.get("https://mail.google.com/mail/?logout"); 
  	  Thread.sleep(2000);
  	  log.info("End: testSupplier2LogoutGmail");
  	  log.info("--------------------------------------------");
  }
        
  @Test(description="SP login")
  @Parameters({"passwordSP"})
  public void testSPLoginAgain(String passwordSP) throws InterruptedException {
    	log.info("Start: testSPLoginAgain");
        Thread.sleep(1500);
        driver.get(loginUrl);
        driver.manage().window().maximize(); 
        Thread.sleep(2000);
        CommonUtils commonUtils = new CommonUtils(driver);
        commonUtils.loginUser();
        Thread.sleep(3500);
        spSiteLoginPage = driver.getCurrentUrl();  
        log.info("End: testSPLoginAgain");
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
	  productName1 = productName1 + unicID + "3" + " test project";  
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
       else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("qa2"))  
    	   imgs.get(0).click();
       else 
    	   imgs.get(1).click();
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
	   Thread.sleep(6000);
	   log.info("End: testSPWorkspaceProjectSubmit");
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
  
  @Test(description="Glb invite")
  public void testGlbInvite() throws InterruptedException {
	  log.info("Start: testGlbInvite");
	  WebElement glbDashBoard = driver.findElement(By.id("glbDashboard"));
	  if(!glbDashBoard.isDisplayed()) {
		  throw new IllegalStateException("The Home link is missing!");
	  } else {
		  glbDashBoard.click();
	  }
	  Thread.sleep(1500);
	  InvitePage invitePage = new InvitePage(driver);
	  invitePage.clickGlbInvite();
	  Thread.sleep(2500);
	  log.info("End: testGlbInvite");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="Glb invite supplier")
   public void testGlbInviteSupplierTab() throws InterruptedException {
   	log.info("Start: testGlbInviteSupplierTab");
   	InvitePage invitePage = new InvitePage(driver);
   	invitePage.clickSupplierTab();
   	Thread.sleep(2000);
   	log.info("End: testGlbInviteSupplierTab");
   	log.info("--------------------------------------------");
   }
 
   @Test(description="Glb invite: supplier field")
   @Parameters({"email3"})
   public void testGlbInviteSupplierField(String email3) throws InterruptedException {
   	log.info("Start: testGlbInviteSupplierField");
   	SupplierInvitePage supplierInvitePage = new SupplierInvitePage(driver);
   	supplierInvitePage.inputSupplier(email3);
   	Thread.sleep(1500);
   	log.info("End: testGlbInviteSupplierField");
   	log.info("--------------------------------------------");
   }
  
   @Test(description="Glb invite: send Invitation to supplier")
   public void testGlbInviteSupplierSendEnvitations() throws InterruptedException {
   	  log.info("Start: testGlbInviteSupplierSendEnvitations");
      InvitePage invitePage = new InvitePage(driver);
   	  invitePage.sendSupplierInvitation();
	  Thread.sleep(3000);
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"******* JS errors for Glb Invite 2 *******");	
   	  log.info("End: testGlbInviteSupplierSendEnvitations");
      log.info("--------------------------------------------");
   }
   
   @Test(description="Logout")
   public void testSPLogout() throws InterruptedException {
   	    log.info("Start: testLogout");
 	    String spDomain = driver.getCurrentUrl();
 	    int index = spDomain.indexOf("/service");
  	    spDomain = spDomain.substring(0, index);
 	    System.out.println("spDomain"+spDomain);
 	    Thread.sleep(2500);
 	    ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
 	    Thread.sleep(3000); 
   	    log.info("End: testLogout");
        log.info("--------------------------------------------");
   }  
   
   @Test(description="Login to Gmail")
   @Parameters({"gmailLoginPW", "email3"})
   public void testLoginToGmail(String gmailLoginPW, String email3) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  Thread.sleep(800);
 	  driver.get("http://www.gmail.com");
 	  CommonUtils commonUtils = new CommonUtils(driver);
      commonUtils.testGmailLoginPreCon();  
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(2000);
 	  WebElement staySignInCookie = null;
 	  WebElement skipPhoneNum = null;
	  try {
	      staySignInCookie = driver.findElement(By.id("PersistentCookie"));
	  } catch (Exception e) {
		  //do nothing
	  }
	  if (staySignInCookie != null) {
		  staySignInCookie.click();
	      Thread.sleep(1000);
	  }
 	  gmailLoginPage.enterEmail(email3);
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(1000);
	  gmailLoginPage.signIn();
	  Thread.sleep(1500);
	  try {
	      skipPhoneNum = driver.findElement(By.id("skip-link"));
	  } catch (Exception e) {
		  //do nothing
	  }
	  if (skipPhoneNum != null) {
	     skipPhoneNum.click();
	     Thread.sleep(1000);
	     Alert alert = driver.switchTo().alert();
		 //alert.accept();
	 	 alert.dismiss();
	 	 Thread.sleep(500);
	  }
 	  if(!browser.trim().equals("IE")) {
 	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for New Supplier Login Gmail ******");	
 	  } 
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier checks email")
   public void testSupplierCheckEmail() throws InterruptedException {
	   log.info("Start: testSupplierCheckEmail");
	   Thread.sleep(5000);
	   List<WebElement> emails = driver.findElements(By.tagName("span"));
	   for (WebElement email : emails) {
	 	  if (email != null) {
	 		  String emailTitle = email.getText();
	 		 if (emailTitle.indexOf("invites you to collaborate") >= 0) {
	 			  email.click();
	 			  break;
	 		  }
	 	  }
	   }
	   Thread.sleep(2000);
	   WebElement acceptInv = driver.findElement(By.linkText("Accept Invitation"));
	   if(!acceptInv.isDisplayed()) {
		   throw new IllegalStateException("The accept invitation link is missing!");
	   } else {
		   acceptInv.click();
	   } 	   
	   Thread.sleep(2000);  
 	   log.info("End: testSupplierCheckEmail");
 	   log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier login")
   @Parameters({"email3", "passwordBuyer"})
   public void testSupplierLogin(String email3, String passwordBuyer) throws InterruptedException {
   	log.info("Start: testClientLogin");
   	parentWindowHandle = driver.getWindowHandle();
   	Set<String> windows = driver.getWindowHandles();
   	for (String window : windows) {
   		if (!window.equals(parentWindowHandle)) {
   			driver.switchTo().window(window);
   			break;
   		}
   	}
   	ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
   	Thread.sleep(1000);
   	clientLoginPage.clientLoginUser(email3, passwordBuyer);
   	Thread.sleep(2000);
   	log.info("End: testSupplierLogin");
  	    log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier logout account")
   public void testSupplierLogout() throws InterruptedException {
   	  log.info("Start: testSupplierLogout");
   	  CommonUtils commonUtils = new CommonUtils(driver);
   	  commonUtils.logoutUser();
      log.info("End: testSupplierLogout");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="Supplier logout gmail")
   @Parameters({"gmailLoginPW"})
   public void testSupplierLogoutGmail(String gmailLoginPW) throws InterruptedException {
	   log.info("Start: testSupplierLogoutGmail");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.logoutGmailAccount();   	  
	   log.info("End:testSupplierLogoutGmail");
	   log.info("--------------------------------------------");
    }
   
   @Test(description="SP open project")
   public void testSPOpenProject() throws InterruptedException {
	   log.info("Start: testSPOpenProject");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.openProject();
	   Thread.sleep(6000);
       log.info("End: testSPOpenProject");
 	   log.info("--------------------------------------------");
   }     
   
	@Test(description = "Member2 login")
	@Parameters({ "Member2LoginN", "BuyerPassword" })
	public void testMember2Loginagain(String Member2LoginN, String BuyerPassword)
			throws InterruptedException {
		testSupplierLogin(Member2LoginN, BuyerPassword);
	}
}

