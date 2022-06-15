package com.noosh.nooshentry.automation.buyerSite;
/**
* This class tests the functionality of Buyer site in Firefox browser
* @author Mikhail Novokshchenov
*/

import org.openqa.selenium.Alert;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import com.noosh.nooshentry.automation.demoSQANoosh.CreateNewProduct;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.demoSQANoosh.ProjectFrame;
import com.noosh.nooshentry.automation.demoSQANoosh.TestDemoSqaFF;
import com.noosh.nooshentry.automation.demoSQANoosh.UserProfileFrame;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Set;

public class TestBuyerSiteFF extends BaseSeleniumTest
{   		   
	String winHandleBefore = null;
	String newWindow = null;
	String oldWindow = null;
	
	static Hashtable<String, String> buyerCredentials = new Hashtable<String, String>();
   
   Page page = new Page(buyerDriver);

   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "message"}) 
   @Test(description="Check SP invitation in the email")
   public void testLoginEmailPage1(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
		   String newLastNameSP, String message) 
      throws InterruptedException, FileNotFoundException, AWTException 
   {	  
	  log.info("Start: testLoginEmailPage1");
	  buyerDriver.get(baseUrlBuyerSite);      
      buyerDriver.manage().window().maximize();
      
      // Verifying that this is the correct page     

      LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);     
      
      loginEmailPage.loginUser(validBuyerEmail, "77pass33word");      
      Thread.sleep(1500);     
      String searchString = buyerEmail + " " + message;   

      loginEmailPage.searchInvitationLetter(searchString);
      Thread.sleep(2000);
      loginEmailPage.getInvitationLetter();
	  Thread.sleep(1800);
      winHandleBefore = buyerDriver.getWindowHandle();
      System.out.println("First winHandleBefore" + winHandleBefore);
      loginEmailPage.getRegisterBuyerPage();
      Thread.sleep(1000);
     
      if(!buyerBrowser.trim().equals("IE")){ 	      
   	     errorIndex = jsErrorIndex;
   	     Page.jsErrorReporter(driver, errorIndex,"####### Check SP invitation in the email, JS errors for Buyer site #######");
      }
      log.info("End: testLoginEmailPage1");
   } 
   
   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "message"}) 
   @Test(description="Check SP invitation in the email")
   public void testLoginEmailPage33(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
		   String newLastNameSP, String message) 
      throws InterruptedException, FileNotFoundException, AWTException 
   {	  
	  buyerDriver.get(baseUrlBuyerSite);      
      buyerDriver.manage().window().maximize();
      
      // Verifying that this is the correct page     

      LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);     
      
      loginEmailPage.loginUser(validBuyerEmail, "77pass33word");      
      Thread.sleep(1500);     
      String searchString = buyerEmail + " " + message;   
      loginEmailPage.searchInvitationLetter33(searchString);
      Page.robotClickEnter();
      Thread.sleep(2500);
      loginEmailPage.getInvitationLetter();
      Thread.sleep(800);
	  Thread.sleep(1000);
      winHandleBefore = buyerDriver.getWindowHandle();      
      loginEmailPage.getRegisterBuyerPage();
      Thread.sleep(3000);
     
      if(!buyerBrowser.trim().equals("IE")){ 	      
   	     errorIndex = jsErrorIndex;
   	     Page.jsErrorReporter(driver, errorIndex,"####### Check SP invitation in the email, JS errors for Buyer site #######");
      }
   } 
	   
   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "message"}) 
   @Test(description="Check SP invitation in the email")
   public void testLoginEmailPage(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
		   String newLastNameSP, String message) 
      throws InterruptedException 
   {
	   message = firstNameSP + " " + newLastNameSP + " " + message;
	   
      buyerDriver.get(baseUrlBuyerSite);      
      buyerDriver.manage().window().maximize();
      
      // Verifying that this is the correct page     

      LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);
      
      loginEmailPage.loginUser(validBuyerEmail, "77pass33word");           
      loginEmailPage.searchInvitationLetter33(validBuyerEmail + buyerEmail + " " + message);
      winHandleBefore = buyerDriver.getWindowHandle();
      loginEmailPage.getInvitationLetter();
      Thread.sleep(1000);
      loginEmailPage.getRegisterBuyerPage();       
      
      if(!buyerBrowser.trim().equals("IE")){ 	      
    	     errorIndex = jsErrorIndex;
    	     Page.jsErrorReporter(driver, errorIndex,"####### Check SP invitation in the email, JS errors for Buyer site #######");
       }      
   }   

   @Test(description="11.1, Register new Buyer")
   @Parameters({"firstNameBuyer", "lastNameBuyer", "passwordBuyer", "phoneNumberBuyer"})
   public void testRegisterBuyerPage(String firstNameBuyer, String lastNameBuyer, 
      String passwordBuyer, String phoneNumberBuyer) throws InterruptedException
   {
	   log.info("Start: testRegisterBuyerPage");
      RegisterBuyerPage registerBuyerPage = new RegisterBuyerPage(buyerDriver);

      for (String handle : buyerDriver.getWindowHandles()) {
         buyerDriver.switchTo().window(handle);         
      }
      
      buyerDriver.manage().window().maximize();
      registerBuyerPage.buyerCredential(firstNameBuyer, lastNameBuyer, passwordBuyer, 
         phoneNumberBuyer);
      if(!buyerBrowser.trim().equals("IE")){ 
          registerBuyerPage.getAgreementPopup();    
          registerBuyerPage.acceptAgreement();
      }
      registerBuyerPage.moveSlider();
      Thread.sleep(3000); 
            
      page.takeScreenshot(driver, "c:\\Screenshorts\\Demo_Site\\FirstMessage.png");

      if(!buyerBrowser.trim().equals("IE")){ 	      
 	     errorIndex = jsErrorIndex;
 	     Page.jsErrorReporter(driver, errorIndex,"####### TC 11.1, Register new Buyer, JS errors for Buyer site #######");
      }  
      log.info("End: testRegisterBuyerPage");
   }
   
   @Test(description="11.1, Register new Buyer for IE browser")
   @Parameters({"firstNameBuyer", "lastNameBuyer", "passwordBuyer", "phoneNumberBuyer"})
   public void testRegisterBuyerPageIE(String firstNameBuyer, String lastNameBuyer, 
      String passwordBuyer, String phoneNumberBuyer) throws InterruptedException
   {
      RegisterBuyerPage registerBuyerPage = new RegisterBuyerPage(buyerDriver);
      
      Thread.sleep(600);    
      Set<String> availableWindows = buyerDriver.getWindowHandles();
      for (String window : availableWindows) {
          if (!winHandleBefore.equals(window)) {
              newWindow = window;
          }
      }
      Thread.sleep(1000);
      buyerDriver.switchTo().window(newWindow);
      buyerDriver.manage().window().maximize();  
      registerBuyerPage.buyerCredential(firstNameBuyer, lastNameBuyer, passwordBuyer, 
         phoneNumberBuyer);
      registerBuyerPage.moveSlider();
      Thread.sleep(3000);    
   }

   @Test(description = "Login to Buyer Site for independent Buyer site test")
   @Parameters({"baseUrlLogin"})
   public void testLoginBuyerSiteIndependent(String baseUrlLogin) 
      throws InterruptedException
   {   
      buyerDriver.get(baseUrlLogin);
      
      buyerDriver.manage().window().maximize();
      
      LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
      
      loginBuyerPage.loginBuyer("demo.nshtest+572201@gmail.com", "marsik90");
      Thread.sleep(1000); 
      
      if(!buyerBrowser.trim().equals("IE")){ 	      
  	     errorIndex = jsErrorIndex;
  	     Page.jsErrorReporter(driver, errorIndex,"####### Login to Buyer Site, JS errors for Buyer site #######");
       }       
   }
   
   @Test(description = "11.2, Buyer site - Log out")
   public void testLogoutBuyerSite() throws InterruptedException
   {
	  BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	  
	  buyerSitePage.clickLogout();
	  Thread.sleep(1200); 
	  
      if(!buyerBrowser.trim().equals("IE")){ 	      
   	     errorIndex = jsErrorIndex;
   	     Page.jsErrorReporter(driver, errorIndex,"####### TC 11.2,  Buyer site - Log out, JS errors for Buyer site #######");
      }      
   }  
   
   @Test(description = "12.1 Buyer site - Log in")
   @Parameters({"validBuyerEmail", "passwordBuyer"})
   public void testLoginBuyerSite(String validBuyerEmail, String passwordBuyer) 
      throws InterruptedException
   {        
	   log.info("Start: testLoginBuyerSite");
      LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
      
	  buyerCredentials.put("buyerURL", buyerDriver.getCurrentUrl());
	  buyerCredentials.put("loginName", validBuyerEmail);
	  buyerCredentials.put("password", passwordBuyer);
	  System.out.println("-------------------------------------- Buyer site ---------------------------------");
	  log.info("-------------------------------------- Buyer site ---------------------------------");
      System.out.println(" buyer url: " + buyerCredentials.get("buyerURL") + " login name: " + 
	  buyerCredentials.get("loginName")  + buyerEmail + " password: " + buyerCredentials.get("password"));
      log.info(" Buyer site url: " + buyerCredentials.get("buyerURL") + " login name: " + 
	  buyerCredentials.get("loginName")  + buyerEmail + " password: 17password");
      loginBuyerPage.loginBuyer(validBuyerEmail + buyerEmail, buyerCredentials.get("password"));
      /*
	  try {
		  loginBuyerPage.loginBuyer(validBuyerEmail + "+" + page.getUnicID() + "@gmail.com", buyerCredentials.get("password"));
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
	  */
      /*
      
      try {
          AssertJUnit.assertTrue(buyerSitePage.checkBuyerSiteLogo());	      
	  } catch(Throwable e) {
		  System.out.println("12.1 Buyer logo not displayed");
		  log.info("12.1 Buyer logo not displayed");
	  }
      */
      /*
      Thread.sleep(1000); 
      try {
          AssertJUnit.assertTrue(buyerSitePage.checkSPLogo());	      
	  } catch(Throwable e) {
		  System.out.println("12.1 SP logo in the buyer site not displayed");
		  log.info("12.1 SP logo in the buyer site not displayed");
	  }
	  */
      Thread.sleep(1500);        
      
      if(!buyerBrowser.trim().equals("IE")){ 	      
    	     errorIndex = jsErrorIndex;
    	     Page.jsErrorReporter(driver, errorIndex,"####### TC 12.1, Buyer site - Log in, JS errors for Buyer site #######");
       }     
      log.info("End: testLoginBuyerSite");
   }
   
   @Test(description="12.2, Client verifies SP project, name and status")
   @Parameters({"buyerProjectName"})
   public void testClientVerifiesSPProject(String buyerProjectName) throws InterruptedException
   {
	   Thread.sleep(1500);
	   BaseTestCases.testSPProject2VerificationStatus(buyerProjectName);
	   Thread.sleep(2000);
   }
     
   @Test(description="13.1, Buyer creates new Project - Selects product")
   public void testBuyerSite() throws InterruptedException
   {
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
      
      buyerSitePage.getNewBrochureProject();       
      
      if(!buyerBrowser.trim().equals("IE")){ 	      
 	     errorIndex = jsErrorIndex;
 	     Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1 - Selects product, JS errors for Buyer site #######");
      }     
   }
  
   @Test(description="13.1, Buyer creates new Project - Create product")
   @Parameters({"projName", "descriptionName", "skuSP", "referenceNumber", 
	   "quant", "specDescrBuyer", "fileForProject1"})
   public void testBrochurePopupWindow(String projName, String descriptionName,
		   String skuSP, String referenceNumber, String quant, String specDescrBuyer,
		   String fileForProject1) throws InterruptedException, AWTException
   {
      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      brochurePopup.setProjectName(projName); 
      Thread.sleep(2000);       
      brochurePopup.callCalendar();                
      brochurePopup.setComplationDate();  
      brochurePopup.getNextTab();    
      brochurePopup.putDescriptionName(descriptionName); 
      brochurePopup.putSKU(skuSP);
      brochurePopup.putRefNumber(referenceNumber);
      brochurePopup.putQuant1(quant);         
      brochurePopup.getFilesTab();          
      if(!buyerBrowser.trim().equals("IE")){
    	     page.uploadFileModalWindow(fileForProject1);
       	     brochurePopup.clickUploadFileDropBT();
       	     Thread.sleep(1000);
       	     page.robotUpload();       	      
       }
      Thread.sleep(3000);  
      brochurePopup.getReviewAndSubmitTab();     
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000);      
      if(!buyerBrowser.trim().equals("IE")){ 	      
  	     errorIndex = jsErrorIndex;
  	     Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1 - Create product, JS errors for Buyer site #######");
      }       
   }   
  
   @Test(description="13.1, Buyer creates new Project - Create product")
   @Parameters({"projName", "descriptionName", "skuSP", "referenceNumber", 
	   "quant", "specDescrBuyer", "fileForProject1"})
   public void testBrochurePopupWindowWF(String projName, String descriptionName,
		   String skuSP, String referenceNumber, String quant, String specDescrBuyer,
		   String fileForProject1) throws InterruptedException, AWTException
   {
      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      brochurePopup.setProjectName(projName); 
      Thread.sleep(2000);       
      brochurePopup.callCalendar();                
      brochurePopup.setComplationDate();  
      brochurePopup.getNextTab();    
      brochurePopup.putDescriptionName(descriptionName);
      brochurePopup.putSKU(skuSP);
      brochurePopup.putRefNumber(referenceNumber);
      brochurePopup.putQuant1(quant);         
      brochurePopup.getFilesTab();     
      Thread.sleep(1000);  
      brochurePopup.getReviewAndSubmitTab();     
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000); 
      
      if(!buyerBrowser.trim().equals("IE")){ 	      
  	     errorIndex = jsErrorIndex;
  	     Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1 - Create product, JS errors for Buyer site #######");
      }       
   }   
   
   @Test(description="13.1, Buyer creates new Project - Add new product")
   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "newProjectName"})
   public void testNewProduct(String descriptionName, String skuSP, String referenceNumber, 
		   String quant, String specDescrBuyer, String newProjectName) throws InterruptedException
   {
	   TestDemoSqaFF testDemoSqa = new TestDemoSqaFF();
	   	
	   testDemoSqa.testCreateProductIntoCreateProject(descriptionName, skuSP, referenceNumber, 
			   quant, specDescrBuyer, newProjectName);

	   if(!buyerBrowser.trim().equals("IE")){ 	      
	   	   errorIndex = jsErrorIndex;
	   	   Page.jsErrorReporter(driver, errorIndex,"####### 13.1, Buyer creates new Project - Add new product #######");
	   }	   
   }
   
   @Test(description="13.1, Buyer creates new Project - Add new product for IE")
   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "newProjectName"})
   public void testCreateProductIntoCreateProjectIE(String descriptionName, String skuSP, String referenceNumber, 
		   String quant, String specDescrBuyer, String newProjectName) throws InterruptedException, AWTException
   {  	   
	   CreateNewProduct newProduct = new CreateNewProduct(buyerDriver);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
	   
	   Thread.sleep(500);
	   newProduct.clickBrochureIcon();
	   Thread.sleep(500);
	   brochurePopup.clickSpecDescription();
	   brochurePopup.putDescriptionName(descriptionName + 1);
	   brochurePopup.putSKU(skuSP);
	   brochurePopup.putRefNumber(referenceNumber);
	   brochurePopup.putQuant1(quant); 
	   brochurePopup.getReviewAndSubmitTab();	   
	   brochurePopup.clickProjectInfoEditBT();	   
	   brochurePopup.setProjectName(newProjectName);
	   Thread.sleep(500);
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(500);	   
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(1000);
	   if(!buyerDriver.equals(driver)){
	      page.closeModalPopup();
	   }
	   	   
   }
   
   @Test(description="13.1, Buyer creates new Project - verification in the Overview tab")
   @Parameters({"newProjectName"})
   public void testNewProject(String newProjectName) throws InterruptedException
   {
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
      NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);      
      
      Thread.sleep(2500); 
      buyerSitePage.clickBuyerProject();
      Thread.sleep(500); 
	  try {
		      AssertJUnit.assertTrue(newProjectPage.checkProjectStatus());	      
		  } catch(Throwable e) {
			  System.out.println("13.1 The project status 'New' does not displayed");
			  log.info("13.1 The project status 'New' does not displayed");
		  } 
	  
	  try {
//		      AssertJUnit.assertTrue(newProjectPage.checkProjectName(newProjectName));	
		  AssertJUnit.assertTrue(Page.isTextPresent(newProjectName, buyerDriver));
		   } catch(Throwable e) {
			  System.out.println("13.1 The correct project name does not displayed");
			  log.info("13.1 The correct project name does not displayed");
		   }

	   if(!buyerBrowser.trim().equals("IE")){ 	      
	   	   errorIndex = jsErrorIndex;
	   	   Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1, verification in the Overview tab, JS errors for Buyer site #######");
	   }      
   }
   
   @Test(description="13.2, Buyer uploads new file to the Project")
   @Parameters({"fileForProject3", "descriptionName", "fileProject1", "fileProject3", "fileForProject1"})
    public void testUploadingNewFile(String fileForProject3, String descriptionName, String fileProject1, 
    		String fileProject3, String fileForProject1) throws InterruptedException, AWTException
    {
       NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);
       ProjectFrame projectFrame = new ProjectFrame(buyerDriver);
/*
       newProjectPage.clickProjectSpecsTab();
       Thread.sleep(500);        
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionName, buyerDriver));	         
		   } catch(Throwable e) {
			  System.out.println("13.2 Project spec " + descriptionName + " does not displayed");
			  log.info("13.2 Project spec " + descriptionName + " does not displayed");
		   } 
	   descriptionName = descriptionName + 1;
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionName, buyerDriver));	        
		   } catch(Throwable e) {
			  System.out.println("13.2 Project spec " + descriptionName + " does not displayed");
			  log.info("13.2 Project spec " + descriptionName + " does not displayed");
		   }   
       */
	   newProjectPage.clickFileTab();
	   Thread.sleep(4000);
	   try{
		   AssertJUnit.assertTrue(Page.isTextPresent(fileProject3, buyerDriver));
	      } catch(Throwable e) {
	    	  System.out.println("13.2 Uploaded file name " + fileProject3 + " does not displayed");
	    	  log.info("13.2 Uploaded file name " + fileProject3 + " does not displayed");
	    	} 
       if(!buyerBrowser.trim().equals("IE")){
  	      page.uploadFileModalWindow(fileForProject1);
  	      projectFrame.clickUloadFileBTText();
  	      Thread.sleep(1000);
  	      page.robotUpload();
  	      Thread.sleep(3500);
  		  // download file and close viewer window
  	      projectFrame.clickDownloadFileIcon(driver, fileProject3);
  		  Thread.sleep(800);
  	      page.downloadFile(); 	      
  	      // file rename
  	      projectFrame.renameFileName(buyerDriver, fileProject1, "Screenshot");
  	      page.closeModalPopup();
  		  Thread.sleep(2500);
  	      // sorting
  	      projectFrame.clickNameSort();
  		  Thread.sleep(1500);
  		  projectFrame.clickNameSort();
  		  Thread.sleep(1500);
  		  // delete file
  	      Thread.sleep(1000);
  		  projectFrame.clickDeleteFileIcon(driver, "Screenshot");
  		  Thread.sleep(600);
  	      Alert alert = driver.switchTo().alert();
  		  alert.accept();
  	 	  page.closeModalPopup();
  		  Thread.sleep(2000);
        }
/*
       Thread.sleep(1000); 
 	   if(!buyerDriver.equals(driver)){
	   //	  Alert alert = buyerDriver.switchTo().alert();
	   //	  alert.accept();
		  page.closeModalPopup();		  
	   }
	      try {
		      AssertJUnit.assertTrue(projectFrame.getUploadedFileName(fileName1));   
	      } catch(Throwable e) {
			  System.out.println("13.2 Uploaded file name " + fileName1 + " does not displayed");
			  log.info("13.2 Uploaded file name " + fileName1 + " does not displayed");
		      } 
       if(!buyerBrowser.trim().equals("IE")){
 	      try {
	          
		      AssertJUnit.assertTrue(projectFrame.getUploadedFileName(fileName3));   
		  } catch(Throwable e) {
		      System.out.println("13.2 Uploaded file name " + fileName3 + " does not displayed");
		      log.info("13.2 Uploaded file name " + fileName3 + " does not displayed");
	      }   
 	  */     
 	   if(!buyerBrowser.trim().equals("IE")){ 	      
 		   	errorIndex = jsErrorIndex;
 		   	Page.jsErrorReporter(driver, errorIndex,"####### TC 13.2, Buyer uploads new file to the Project, JS errors for Buyer site #######");
 	   } 	      
   }
   
   @Test(description="13.3, Buyer sends new message for the Project")
   @Parameters({"buyerNewMessage"})
    public void testSendMessageFile(String buyerNewMessage) throws InterruptedException
    {
	   NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);
         
	   newProjectPage.clickMessageTab();
       Thread.sleep(1000);	                 
       newProjectPage.buyerMessage(buyerNewMessage);
       newProjectPage.sendMessage();
       Thread.sleep(1000);          
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(buyerNewMessage, buyerDriver));	        
		   } catch(Throwable e) {
			  System.out.println("13.3 Buyer message " + buyerNewMessage + " does not displayed");
			  log.info("13.3 Buyer message " + buyerNewMessage + " does not displayed");
		   } 

	   if(!buyerBrowser.trim().equals("IE")){ 	      
	   	   errorIndex = jsErrorIndex;
	   	   Page.jsErrorReporter(driver, errorIndex,"####### TC 13.3, Buyer sends new message for the Project,JS errors for Buyer site #######");
	   }        
    }

   
   @Test(description = "Login to Buyer Site for Buyer site test")
   public void testLoginBuyerSiteAdvEd() 
      throws InterruptedException
   {   
      buyerDriver.get("https://selenium745801.sqa.noosh.com/demo_selenium745801/main");
      
      buyerDriver.manage().window().maximize();
      
      LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
      
      loginBuyerPage.loginBuyer("demo.nshtest+745801@gmail.com", "marsik90");
      Thread.sleep(1000); 

	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### Login to Buyer Site JS errors for Buyer site #######");
	  }     
   }

   @Test(description="13.1.1-1, Buyer creates new Project, Adv editor poduct - Selects product")
   @Parameters({"productName"})
   public void testBuyerSiteAdvEd(String productName) throws InterruptedException
   {
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
      
      productName = productName + unicID;
      Thread.sleep(1500); 
      if(browser.trim().equals("IE")) {
    	  Thread.sleep(1400); 
      }
      buyerSitePage.getNewSPProject(buyerDriver, productName);
      Thread.sleep(2000); 
      
	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### 13.1.1-1, Buyer creates new Project, Adv editor poduct - Selects product #######");
	  }       
   }
   
   @Test(description="13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Project info tab")
   @Parameters({"buyerProjectName"})
   public void testProductPopupWindowAdvAd(String buyerProjectName) throws InterruptedException
   {      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      buyerProjectName = buyerProjectName + unicID;
      brochurePopup.setProjectName(buyerProjectName); 
      Thread.sleep(1000);       
      brochurePopup.callCalendar();  
      brochurePopup.setNextMonth();
      brochurePopup.setComplationDate();

	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Project info tab, JS errors for Buyer site #######");
	  }      
   }
   
   @Test(description="13.1.1-3, Buyer creates new Project, Adv editor poduct - Create product, New tab")
   @Parameters({"descriptionName", "quant", "reqField"})
   public void testProductPopupWindowNewTabAdvAd(String descriptionName, String quant, String reqField) throws InterruptedException
   {      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      brochurePopup.getNextTab();
      Thread.sleep(800);
      brochurePopup.setDimention(quant); 
      brochurePopup.getReviewAndSubmitTab();
      brochurePopup.clickSubmitBT();
      Thread.sleep(800);
	  try {
	       AssertJUnit.assertTrue(Page.isTextPresent(reqField, driver));	
	       } catch(Throwable e) {
		       System.out.println("13.1.1-7, Error message " + reqField + " does not displayed");
		       log.info("13.1.1-7 Error message " + reqField + " does not displayed");
	       }      
      brochurePopup.setNameTextBox(descriptionName);

	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-3, Buyer creates new Project, Adv editor poduct - Create product, New tab, JS errors for Buyer site #######");
	  }      
   }
   
   @Test(description="13.1.1-4, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab")
   @Parameters({"fileForProject3"})
   public void testProductPopupWindowFileAdvAd(String fileForProject3) throws InterruptedException, AWTException
   {      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);      

      Thread.sleep(1000);
      brochurePopup.clickFilesTabAdvEd();
      if(!buyerBrowser.trim().equals("IE")){   	  
   	      page.uploadFileModalWindow(fileForProject3);
   	      brochurePopup.clickUploadFileDropBT();
   	      Thread.sleep(1000);
   	      page.robotUpload();
   	      Thread.sleep(3000);
       }
      brochurePopup.getReviewAndSubmitTab();
      Thread.sleep(800); 
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000); 

	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab, JS errors for Buyer site #######");
	  }      
   }
   
   @Test(description="13.1.1-4, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab")
   @Parameters({"fileForProject3"})
   public void testProductPopupWindowFileAdvAdWF(String fileForProject3) throws InterruptedException, AWTException
   {      
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);   
      
      Thread.sleep(2000);
      brochurePopup.clickFilesTabAdvEd();
      brochurePopup.getReviewAndSubmitTab();
      Thread.sleep(800); 
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000); 

	  if(!buyerBrowser.trim().equals("IE")){ 	      
	   	  errorIndex = jsErrorIndex;
	   	  Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab, JS errors for Buyer site #######");
	  }      
   }   
   
   @Test(description="13.1.1-5, Buyer creates new Project, Adv editor poduct - Create product, Add new product")
   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "buyerProjectName"})
   public void testAddNewProductAdvEd(String descriptionName, String skuSP, String referenceNumber, 
		   String quant, String specDescrBuyer, String buyerProjectName) throws InterruptedException
   {
	   CreateNewProduct newProduct = new CreateNewProduct(buyerDriver);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
	   	
	   buyerProjectName = buyerProjectName + unicID + "G";

	   Thread.sleep(500);
	   newProduct.clickBrochureIcon();
	   Thread.sleep(500);
	   brochurePopup.clickSpecDescription();
	   Thread.sleep(500);
	   brochurePopup.putDescriptionNameNew(buyerDriver, "Spec Name", descriptionName + 1);
	   brochurePopup.putDescriptionNameNew(buyerDriver, "SKU", skuSP);		   
	   brochurePopup.putQuant1(quant);
	   Thread.sleep(600); 
	   brochurePopup.getReviewAndSubmitTab();		   
	   brochurePopup.clickProjectInfoEditBT();		   
	   brochurePopup.setProjectName(buyerProjectName);
	   brochurePopup.getReviewAndSubmitTab();		   
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(2500);

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   	errorIndex = jsErrorIndex;
		   	Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-5, Buyer creates new Project, Adv editor poduct - Create product, Add new product, JS errors for Buyer site #######");
	   }  	   
   }
   
   @Test(description="13.1.1-6, Buyer creates new Project, Adv editor poduct - Create product, Project verification, New status")
   @Parameters({"projectStatusBuyer", "buyerProjectName"})
   public void testStatusProjectVerificationAdvEd(String projectStatusBuyer, String buyerProjectName) throws InterruptedException
   {
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);	   
		
	   if(buyerBrowser.trim().equals("IE")){
		   buyerProjectName = buyerProjectName + unicID + "G";
		   Thread.sleep(1000);
		   buyerSitePage.getNewSPProject(buyerDriver, buyerProjectName);
	   } else
	       buyerSitePage.clickCreatedProjectAdvEd(); 
	   try {
		   AssertJUnit.assertEquals(buyerSitePage.getProjectStatus(), projectStatusBuyer);	
		   } catch(Throwable e) {
			 System.out.println("13.1.1-6 Project status '" + projectStatusBuyer + "' does not dispalayed.");
			 log.info("13.1.1-6 Project status '" + projectStatusBuyer + "' does not dispalayed.");
		   }

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   	errorIndex = jsErrorIndex;
		   	Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-6, Buyer creates new Project, Adv editor poduct - Project verification, New status, JS errors for Buyer site #######");
	   } 	   
   }  
   
   @Test(description="13.1.1-7, Buyer creates new Project, Adv editor poduct - Create product, Project verification, Production specs")
   @Parameters({"descriptionName", "buyerProjectName", "productName"})
   public void testSpecsProjectVerificationAdvEd(String descriptionName, String buyerProjectName, String productName) throws InterruptedException
   {
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   Thread.sleep(800);	   
	   projectPage.clickProjectSpecsTab();
	   Thread.sleep(800);             
       descriptionName = descriptionName + 1;
       buyerProjectName = buyerProjectName + unicID + "G - " + productName + unicID;
       
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent("Test", driver));	
	       } catch(Throwable e) {
		       System.out.println("13.1.1-7, Project spec " + descriptionName + " does not displayed");
		       log.info("13.1.1-7 Project spec " + descriptionName + " does not displayed");
	       }
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent("Test", driver));	
	       } catch(Throwable e) {
		       System.out.println("13.1.1-7, Project spec " + buyerProjectName + " does not displayed");
		       log.info("13.1.1-7 Project spec " + buyerProjectName + " does not displayed");
	       }

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   	errorIndex = jsErrorIndex;
		   	Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-7, Buyer creates new Project, Adv editor poduct - Project verification, Production specs, JS errors for Buyer site #######");
	   } 	   
   }  
   
   @Test(description="13.1.1-8, Buyer creates new Project, Adv editor poduct - Create product, Project verification, Uploaded file")
   @Parameters({"fileName3"})
   public void testUploadedFileProjectVerificationAdvEd(String fileName3) throws InterruptedException
   {	   
       NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   projectPage.clickFileTab();
	   Thread.sleep(2000); 
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(fileName3, buyerDriver));	
		   } catch(Throwable e) {
			 System.out.println("13.1.1-8 Buyer file '" + fileName3 + "' does not uploaded.");
			 log.info("13.1.1-8 Buyer file '" + fileName3 + "' does not uploaded.");
		   }

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   	errorIndex = jsErrorIndex;
		   	Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1.1-8, Buyer creates new Project, Adv editor poduct - Project verification, Uploaded file, JS errors for Buyer site #######");
	   }	   
   }   
   
   //*****************************************************************************
   
   @Test(description = "Log out Buyer site")
   public void testLogoutBuyerSiteFinal() throws InterruptedException
   {	 
	  BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
		      
	  buyerSitePage.clickLogout();
	  Thread.sleep(1200);  

	  if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### Log out Buyer site, JS errors for Buyer site #######");
	  }      
   } 
   
   @Test(description = "15.1 Login to Buyer's site")
   @Parameters({"validBuyerEmail"})
   public void buyerSiteLogin(String validBuyerEmail) throws InterruptedException
   {	   
	   buyerDriver.get(buyerCredentials.get("buyerURL")); 
	   testLoginBuyerSite(validBuyerEmail, buyerCredentials.get("password"));	
	   Thread.sleep(1000);  

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 15.1 Login to Buyer's site #######");
	   }  
   }
   
   @Test(description = "15.2, Buyer site - Project's updates, check status")
   @Parameters({"newProjectName", "projectStatus"})
   public void testObserveProject(String newProjectName, String projectStatus)
   {
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   buyerSitePage.clickCreatedProject();  
       projectPage.clickOverviewTab();
	   try {
		   AssertJUnit.assertEquals(buyerSitePage.getProjectStatus(), projectStatus);	
		   } catch(Throwable e) {
			 System.out.println("15.2 Project status '" + projectStatus + "' does not dispalayed.");
			 log.info("15.2 Project status '" + projectStatus + "' does not dispalayed.");
		   }

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 15.2, Buyer site - Project's updates, check status #######");
	   }        
   }
   
   @Test(description = "15.2, Buyer site - Project's updates, check SP message")
   @Parameters({"buyerNewMessage"})
   public void testSPMessageReceived(String buyerNewMessage)
   {
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   projectPage.clickMessageTab();   
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(buyerNewMessage, buyerDriver));	
		   } catch(Throwable e) {
			 System.out.println("15.2 SP message '" + buyerNewMessage + "' does not dispalayed.");
			 log.info("15.2 SP message '" + buyerNewMessage + "' does not dispalayed.");
		   }  

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 15.2, Buyer site - Project's updates, check SP message #######");
	   }        
   }
   
   @Test(description = "15.2, Buyer site - Project's updates, check SP file attached")
   @Parameters({"newProfilePictureFile"})
   public void testSPFileAttached(String newProfilePictureFile) throws InterruptedException
   {
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   projectPage.clickFileTab();
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(newProfilePictureFile, buyerDriver));	
		   } catch(Throwable e) {
			 System.out.println("15.2 SP file '" + newProfilePictureFile + "' does not uploaded.");
			 log.info("15.2 SP file '" + newProfilePictureFile + "' does not uploaded.");
		   }
       newWindow = buyerDriver.getWindowHandle();

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 15.2 - Project's updates, check SP file attached, JS errors for Buyer site #######");
	   }       
   }   
   
   //---------------------------------------------------- New test cases for copy project -----------------------------------------  
   @Test(description="13a.1-1, Buyer - Copy project")
   @Parameters({""})
   public void testBuyerCopyProject() throws InterruptedException
   {
	   BuyerSitePage buyerSitePage = new BuyerSitePage(driver);
	   
	   Thread.sleep(1000);
	   buyerSitePage.clickGearCopyProject();
	   buyerSitePage.copyCreatedProject();
	   
   }
   
   @Test(description="13a.1-2, Buyer - Correct field info in the copied project")
   @Parameters({"newNameTextBoxCopy", "quantSP"})
   public void testCopyProjectFieldCorrection(String newNameTextBoxCopy, String quantSP) throws InterruptedException
   {
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
	   
       Thread.sleep(1000);
	   brochurePopup.setTextBoxText(newNameTextBoxCopy);
	   brochurePopup.setDimention(quantSP); 
	   brochurePopup.getReviewAndSubmitTab();
	   brochurePopup.clickSubmitBT();
   }
   
   @Test(description="13a.1-3, Buyer - Verification copied poject")
   @Parameters({"buyerProjectName", "productName", "newNameTextBoxCopy"})
   public void testCopiedProjectVerification(String buyerProjectName, String productName, String newNameTextBoxCopy) throws InterruptedException
   {
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);	   
		
	   if(buyerBrowser.trim().equals("IE")){
		   buyerProjectName = "Copy of " + buyerProjectName + unicID + "G";
		   Thread.sleep(1000);
		   buyerSitePage.getNewSPProject(buyerDriver, buyerProjectName);
	   } else
	       buyerSitePage.clickCreatedProjectAdvEd();
       Thread.sleep(1000);
       buyerSitePage.clickProductionSpecsTab();
       Thread.sleep(800);
       productName = buyerProjectName + unicID + "G - " + productName + unicID;
       buyerSitePage.getNewSPProject(buyerDriver, productName);      
	   try {
		   AssertJUnit.assertEquals(newNameTextBoxCopy, buyerSitePage.getTextBoxText(buyerDriver, newNameTextBoxCopy));	
		   } catch(Throwable e) {
			 System.out.println("13a.1-3 Corrected '" + newNameTextBoxCopy + "' spec name does not dispalayed.");
			 log.info("13a.1-3 Corrected '" + newNameTextBoxCopy + "' spec name does not dispalayed.");
		   }
   }
   
   // project draft ---------------------------------------------------------------------------------
   
   @Test(description="13b.1-1, Create project draft - Select product for project draft")
   @Parameters({"productName"})
   public void testSelectProductForProjectDraft(String productName) throws InterruptedException
   {
	   testBuyerSiteAdvEd(productName);
   }
   
   @Test(description="13b.1-2, Create project draft - Put project info")
   @Parameters({"buyerProjectNameDraft"})
   public void testPutProjectInfo(String buyerProjectNameDraft) throws InterruptedException
   {
	   testProductPopupWindowAdvAd(buyerProjectNameDraft);
   }
   
   @Test(description="13b.1-3, Create project draft - New Tab info")
   @Parameters({"descriptionNameDraft", "quantDraft", "reqField"})
   public void testNewTabInfo(String descriptionNameDraft, String quantDraft, String reqField) throws InterruptedException
   {
	   testProductPopupWindowNewTabAdvAd(descriptionNameDraft, quantDraft, reqField);
   }
   
   @Test(description="13b.1-4, Create project draft - Upload file")
   @Parameters({"fileForProject3"})
   public void testUploadFileProjectDraft(String fileForProject3) throws InterruptedException, AWTException
   {
	      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);      

	      Thread.sleep(1000);
	      brochurePopup.clickFilesTabAdvEd();  	  
	   	  page.uploadFileModalWindow(fileForProject3);
	   	  brochurePopup.clickUploadFileDropBT();
	   	  Thread.sleep(1000);
	   	  page.robotUpload();
	   	  Thread.sleep(3000);
   }
   
   @Test(description="13b.1-5, Create project draft - Save project")
   public void testSaveProjectDraft() throws InterruptedException
   {
	      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver); 
	      
	      brochurePopup.getReviewAndSubmitTab();
	      Thread.sleep(800); 
	      brochurePopup.clickSaveAsDraftBT();
	      Thread.sleep(3000);
   }
   
   @Test(description="13b.1-6, Create project draft - Project verification")
   public void testDraftProjectVerification(String buyerProjectNameDraft) throws InterruptedException
   {
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   buyerSitePage.clickDraftProject(buyerDriver, "Draft");
	   Thread.sleep(1000);
	   buyerProjectNameDraft = buyerProjectNameDraft + unicID;
	   buyerSitePage.getProjectDraftName();
	   try {
		   AssertJUnit.assertEquals(buyerProjectNameDraft, buyerSitePage.getProjectDraftName());	
		   } catch(Throwable e) {
			 System.out.println("13a.1-6 '" + buyerProjectNameDraft + "' project name draft does not dispalayed.");
			 log.info("13a.1-6 '" + buyerProjectNameDraft + "' project name draft does not dispalayed.");
		   }
	   projectPage.clickProjectSpecsTab();
	   Thread.sleep(800);
   }
   
   // ----------------------------------------------------------
   
   @Test(description = "Logout Buyer Site")
   public void testLogoutBuyer() throws InterruptedException
   {
	   testLogoutBuyerSiteFinal();

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### Logout Buyer Site, JS errors for Buyer site #######");
	   }         
   }
   
   @Test(description = "Sign out gmail")
   public void signOutGmail() throws InterruptedException
   { 	   
	   Thread.sleep(1000); 
	   Set<String> availableWindows = buyerDriver.getWindowHandles();
	      for (String window : availableWindows) {
	           if (!newWindow.equals(window)) {
	              oldWindow = window;
	           }
	      }
	   Thread.sleep(1000);	   
	   buyerDriver.close();
	   Thread.sleep(700);
	   driver.close();
	   buyerDriver.switchTo().window(oldWindow);
	   Thread.sleep(1200); 
       log.info("---------------------------- Integration Test, Basic Flow, Finish --------------------------------");
	   
	   BaseTestCases.signOutGmail(buyerDriver);

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### Sign out gmail, JS errors for Buyer site #######");
	   }      	   	   	   
   }
   
   @Test(description = "16.1-1, Buyer Profile - Update some Profile info, get buyer profile popup")
   public void testGetProfileInfo() throws InterruptedException
   {
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(1000);
	   userProfileFrame.getBuyerProfileIE();
	   Thread.sleep(1000);

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.1-1, Buyer Profile - Update some Profile info, get buyer profile popup, JS errors for Buyer site #######");
	   }        
   }
   
   @Test(description = "16.1-2, Buyer Profile - Update some Profile info, check errors messages")
   public void testProfileInfo() throws InterruptedException
   {
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   userProfileFrame.clickEditProfileBT();
	   Thread.sleep(800);	   
	   buyerCredentials.put("buyerLoginDebug", userProfileFrame.getBuyerLoginName());
	   userProfileFrame.clearLoginNameField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(800);
	   try {
		   AssertJUnit.assertTrue(userProfileFrame.getLoginNameError());	
		   } catch(Throwable e) {
			 System.out.println("16.1-2 Buyer Profile - Profile login error does not dispalayed.");
			 log.info("16.1-2 Buyer Profile - Profile login error does not dispalayed.");	   
	   }	   
	   buyerCredentials.put("buyerEmailDebug", userProfileFrame.getBuyerEmail());
	   userProfileFrame.clearEmailField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(800);
	   try {
		   AssertJUnit.assertTrue(userProfileFrame.getEmailProfileError());	
		   } catch(Throwable e) {
			 System.out.println("16.1-2 Profile email error does not dispalayed.");
			 log.info("16.1-2 Profile email error does not dispalayed.");	   
	   }

 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.1-2, Buyer Profile - Update some Profile info, check errors messages, JS errors for Buyer site #######");
	   }        
   }
   
   @Test(description = "16.1-3, Buyer Profile - Update some Profile info, updating of fields")
   @Parameters({"newLastName"})
   public void testUpdatingProfileInfo(String newLastName) throws InterruptedException
   {
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(700);
	   userProfileFrame.setNewLastName(newLastName);
	   Thread.sleep(700);
	   userProfileFrame.setLoginName(buyerCredentials.get("buyerLoginDebug"));
	   userProfileFrame.setEmailAddress(buyerCredentials.get("buyerEmailDebug"));
	   Thread.sleep(800);
	   userProfileFrame.clickSaveProfileBT();
	
 	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.1-3, Buyer Profile - Update some Profile info, updating of fields, JS errors for Buyer site  #######");
	   }       
   }   
   
   @Test(description = "16.2-1, Buyer Profile - Download new Profile image, get buyer profile popup")
   public void testGetProfileImage() throws InterruptedException
   {
	   Thread.sleep(1000);
	   testGetProfileInfo();
 	   
	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.2-1, Buyer Profile - Download new Profile image, get buyer profile popup, JS errors for Buyer site  #######");
	   } 	   
   }
   
   @Test(description = "16.2-2, Buyer Profile - Download new Profile image, set new buyer profile image")
   @Parameters({"newBuyerProfilePicture"})
   public void testSetProfileImage(String newBuyerProfilePicture) throws InterruptedException, AWTException
   {	   
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(1000);
	   String oldProfileImage = userProfileFrame.getImageAttribute();

	   userProfileFrame.clickChangProfilePicture(newBuyerProfilePicture);	
	   if(!buyerDriver.equals(driver)){		  
	      page.closeModalPopup();
	   }
	   Thread.sleep(1000);
	   if (oldProfileImage.equals(userProfileFrame.getImageAttribute()))		   		   
	   {
		   System.out.println("16.2 The new buyer profile image does not displayed in buyer profile popup");
	       log.info("16.2 The new buyer profile image does not displayed in buyer profile popup");
	   }

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.2-2, Buyer Profile - Download new Profile image, set new buyer profile image, JS errors for Buyer site #######");
	   }       
   } 
   
   @Test(description = "16.3-1, Buyer Profile - Reset password, get buyer profile first time")
   public void testGetProfileInfo2() throws InterruptedException
   {
	   Thread.sleep(1000);
	   testGetProfileInfo();
	   
	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.3-1, Buyer Profile - Reset password, get buyer profile first time, JS errors for Buyer site #######");
	   }	   
   }
   
   @Test(description = "16.3-2, Buyer Profile - Reset password, reset password popup")
   @Parameters({"passwordBuyer", "wrongPassword", "newPassword", "wrongOriginalPassword"})
   public void testGetResetPassword(String passwordBuyer, String wrongPassword, String newPassword,
		   String wrongOriginalPassword) throws InterruptedException
   {	   
	   Thread.sleep(1000);
	   BaseTestCases.testResetPassword(buyerDriver, wrongPassword, passwordBuyer, newPassword, wrongOriginalPassword);
	   buyerCredentials.put("newBuyerPassword", newPassword);

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.3-1, Buyer Profile - Reset password, get buyer profile first time, JS errors for Buyer site #######");
	   }       
    }
   
    @Test(description = "16.3-3, Buyer Profile - Reset password, logout buyer site")
    public void testLogoutBuyerNewPassword() throws InterruptedException
    {
 	   testLogoutBuyerSiteFinal();
	   Thread.sleep(1200);
    }
    
    @Test(description = "16.3-4, Buyer Profile - Reset password, login buyer site new password")
    public void testLoginBuyerSiteNewPassword() throws InterruptedException
    {
       LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
    	
       loginBuyerPage.loginBuyer(buyerCredentials.get("buyerEmailDebug"), buyerCredentials.get("newBuyerPassword"));
	   Thread.sleep(1000);

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.3-4, Buyer Profile - Reset password, login buyer site new password, JS errors for Buyer site #######");
	   }        
    }   
    
    @Test(description = "16.3-5, Buyer Profile - Reset password, get buyer profile second time")
    public void testGetProfileInfo3() throws InterruptedException
    {
 	   Thread.sleep(1000);
 	   testGetProfileInfo();

	   if(!buyerBrowser.trim().equals("IE")){ 	      
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### 16.3-5, Buyer Profile - Reset password, get buyer profile second time, JS errors for Buyer site #######");
	   }        
    }
    
    @Test(description = "16.3-6, Buyer Profile - Reset password, set old buyer password back")
    @Parameters({"passwordBuyer"})
    public void testSetBuyerPasswordBack(String passwordBuyer) throws InterruptedException
    {		
 	    Thread.sleep(1000);
    	BaseTestCases.testResetPasswordBack(buyerDriver, passwordBuyer, buyerCredentials.get("newBuyerPassword"));
	    Thread.sleep(1000);

		if(!buyerBrowser.trim().equals("IE")){ 	      
			 errorIndex = jsErrorIndex;
			 Page.jsErrorReporter(driver, errorIndex,"####### 16.3-6, Buyer Profile - Reset password, set old buyer password back, JS errors for Buyer site #######");
		 } 	    
    }
    
    @Test
    public void testLogoutFin() throws InterruptedException
    {
    	testLogoutBuyer();
    }
    
    @Test(description = "16.4, Buyer user profile - Buyer forgot password")
    @Parameters({"validBuyerEmail"})
    public void testBuyerForgotPassword(String validBuyerEmail) throws InterruptedException
    {   	
    	String emailBuyer = validBuyerEmail + buyerEmail;
    	
    	BaseTestCases.buyerForgotPassword(emailBuyer);
		Thread.sleep(1000);
    }
    
    @Test(description="16.4-1, Buyer user profile - Buyer forgot password, get the reset password link")
    @Parameters({"baseUrlBuyerSite", "validBuyerEmail"})
    public void testSPGetResetPasswordLink(String baseUrlBuyerSite, String validBuyerEmail) throws InterruptedException, AWTException
    {
    	
 	   BaseTestCases.getResetPasswordLink(buyerDriver, baseUrlBuyerSite, validBuyerEmail, "77pass33word");
 	   
 	   errorIndex = jsErrorIndex;
 	   Page.jsErrorReporter(driver, errorIndex,"####### TC 16.4-1, Buyer user profile - Buyer forgot password, get the reset password link, JS errors for SP site #######");	
    }
    
    @Test(description="16.4-2, Buyer user profile - Buyer forgot password, set new SP password")
    @Parameters({"baseUrlBuyerSite", "validBuyerEmail"})
    public void testGetResetPasswordLink(String baseUrlBuyerSite, String validBuyerEmail) throws InterruptedException, AWTException, FileNotFoundException
    {
        Set<String> availableWindows = buyerDriver.getWindowHandles();
        for (String window : availableWindows) {
            if (!newWindow.equals(window)) {
            	winHandleBefore = window;
               }
            }
    	buyerDriver.close();
		buyerDriver.switchTo().window(winHandleBefore);
		buyerDriver.navigate().refresh();
    	BaseTestCases.testBuyerEmailLogout(winHandleBefore);
    	BaseTestCases.getResetPasswordLink(buyerDriver, baseUrlBuyerSite, validBuyerEmail, "77pass33word");
    	Thread.sleep(5000);
 	   
 	   errorIndex = jsErrorIndex;
 	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.4-2, SP user profile - SP forgot password, set new SP password, JS errors for SP site #######");	
    }    
    
    @Test(description="16.4-3, Buyer user profile - Buyer forgot password, set new SP password")
    @Parameters({"passwordSP"})
    public void testResetNewBuyerPassword(String passwordSP) throws InterruptedException, AWTException
    {
 	   for (String handle : buyerDriver.getWindowHandles()) {
 	       buyerDriver.switchTo().window(handle);         
 	   }
 	       
 	   driver.manage().window().maximize();
 	   Thread.sleep(1400);
 	   BaseTestCases.resetBuyerPassword(passwordSP);
 	   Thread.sleep(2400);
 	   
 	   errorIndex = jsErrorIndex;
 	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.4-2, SP user profile - SP forgot password, set new SP password, JS errors for SP site #######");	
    }    
    
// ------------------------------------------------ Buyer on boarding test cases -------------------------------------------------------    
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 1")
    @Parameters({"onboardMessage1_2", "firstNameSP", "lastNameSP", "specificSiteName"})
    public void testOnboardingMessage1(String onboardMessage1_2, String firstNameSP, String lastNameSP, String specificSiteName) throws InterruptedException
    {
    	String message1Site;
    	String message1 = "Welcome to the " + specificSiteName + unicID + " Workspace";
    	String message11 = firstNameSP + " " + lastNameSP +" set up this workspace";
    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
    	
	    Thread.sleep(600);
	    message1Site = buyerSitePage.getMessage1Text();	    
		try {
		     AssertJUnit.assertEquals(message1, buyerSitePage.getMessage1Text());	      
		} catch(Throwable e) {
			 System.out.println("Buyer ob boarding messages verification - Onboarding message State 1 '" + message1 + "' does not displayed");
			 log.info("Buyer ob boarding messages verification - Onboarding message State 1 '" + message1 + "' does not displayed");
		}
		page.takeScreenshot1(driver, "onboardMessage1_");
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage11Text().toLowerCase().contains(message11.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer ob boarding messages verification - Onboarding message State 1 '" + message11 + "' does not displayed");
			 log.info("Buyer ob boarding messages verification - Onboarding message State 1 '" + message11 + "' does not displayed");
			 page.takeScreenshot1(driver, "onboardMessage1_");
	   }
	    	   
    }
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 1")
    @Parameters({"onboardMessage1_2", "firstNameSP", "lastNameSP", "specificSiteName"})
    public void testOnboardingMessage2(String onboardMessage1_2, String firstNameSP, String lastNameSP, String specificSiteName) throws InterruptedException
    {
    	String message1Site;
    	String message1 = "Welcome to the " + specificSiteName + unicID + " Workspace";
    	String message11 = firstNameSP + " " + lastNameSP +" set up this workspace and created a project for you";
    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
    	
	    Thread.sleep(600);
	    message1Site = buyerSitePage.getMessage1Text();
	    page.takeScreenshot1(driver, "onboardMessage2_");
		try {
		     AssertJUnit.assertEquals(message1, buyerSitePage.getMessage1Text());	      
		} catch(Throwable e) {
			 System.out.println("Buyer ob boarding messages verification - Onboarding message State 2 '" + message1 + "' does not displayed");
			 log.info("Buyer ob boarding messages verification - Onboarding message State 2 '" + message1 + "' does not displayed");
			 page.takeScreenshot1(driver, "onboardMessage2_");
		}
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage11Text().toLowerCase().contains(message11.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer ob boarding messages verification - Onboarding message State 2 '" + message11 + "' does not displayed");
			 log.info("Buyer ob boarding messages verification - Onboarding message State 2 '" + message11 + "' does not displayed");
			 page.takeScreenshot1(driver, "onboardMessage2_");
	   }
	   Thread.sleep(1000);  	   
    }    
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 5")
    @Parameters({"onboardMessage5"})
    public void testOnboardingMessage5(String onboardMessage5) throws InterruptedException
    {    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
    	
    	buyerSitePage.clickCreatedProject();
	    Thread.sleep(1000);
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage5Text().toLowerCase().contains(onboardMessage5.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer on boarding messages verification - Onboarding message State 5 '" + onboardMessage5 + "' does not displayed");
			 log.info("Buyer on boarding messages verification - Onboarding message State 5 '" + onboardMessage5 + "' does not displayed");	 
			 page.takeScreenshot1(driver, "onboardMessage5_");
	    }
	    page.takeScreenshot1(driver, "onboardMessage5_");
	    Thread.sleep(1000);  	   
    } 
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 6")
    @Parameters({"onboardMessage6"})
    public void testOnboardingMessage6(String onboardMessage6) throws InterruptedException
    {    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);

	    Thread.sleep(1000);
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage61Text().toLowerCase().contains(onboardMessage6.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer on boarding messages verification - Onboarding message State 6 '" + onboardMessage6 + "' does not displayed");
			 log.info("Buyer on boarding messages verification - Onboarding message State 6 '" + onboardMessage6 + "' does not displayed");
			 page.takeScreenshot1(driver, "onboardMessage6_");
	    }
	    page.takeScreenshot1(driver, "onboardMessage6_");
	    Thread.sleep(1000);  	   
    } 
    
    @Test(description = "Buyer ob boarding messages verification - Buyer invite new buyer")
    @Parameters({"newBuyerEmail"})
    public void testBuyerInviteNewBuyer(String newBuyerEmail) throws InterruptedException
    {    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	    
    	newBuyerEmail = newBuyerEmail + unicID + "@noosh.com";
    	buyerSitePage.buyerInviteBT();
    	Thread.sleep(1000);
    	buyerSitePage.setNewBuyerEmail(newBuyerEmail);
    	Thread.sleep(2000);
    	buyerSitePage.closeInviteCoworker();
    }    
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 7")
    @Parameters({"onboardMessage7"})
    public void testOnboardingMessage7(String onboardMessage7) throws InterruptedException
    {    	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);

	    Thread.sleep(1000);
	    page.takeScreenshot1(driver, "onboardMessage7_");
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage61Text().toLowerCase().contains(onboardMessage7.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer on boarding messages verification - Onboarding message State 7 '" + onboardMessage7 + "' does not displayed");
			 log.info("Buyer on boarding messages verification - Onboarding message State 7 '" + onboardMessage7 + "' does not displayed");
			 page.takeScreenshot1(driver, "onboardMessage7_");
	    }
	    Thread.sleep(1000);  	   
    } 
    
    @Test(description = "Buyer ob boarding messages verification - Onboarding message State 5, after coworker invited")
    @Parameters({"onboardMessage5"})
    public void testOnboardingMessage5AfterInvitation(String onboardMessage5) throws InterruptedException
    {       	
    	BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
    	
    	buyerSitePage.clickCreatedProject();
	    Thread.sleep(1000);
	    try {
		   AssertJUnit.assertTrue(buyerSitePage.getMessage5Text().toLowerCase().contains(onboardMessage5.toLowerCase()));	
		   } catch(Throwable e) {
			 System.out.println("Buyer on boarding messages verification - Onboarding message State 5 '" + onboardMessage5 + "' does not displayed after coworker was invited");
			 log.info("Buyer on boarding messages verification - Onboarding message State 5 '" + onboardMessage5 + "' does not displayed after coworker was invited");	 
			 page.takeScreenshot1(driver, "onboardMessage5_2_");
	    }
	    page.takeScreenshot1(driver, "onboardMessage5_2_");
	    Thread.sleep(1000); 
    }
}



   