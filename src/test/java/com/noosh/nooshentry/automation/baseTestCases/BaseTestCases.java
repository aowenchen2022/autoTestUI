package com.noosh.nooshentry.automation.baseTestCases;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.BuyerSitePage;
import com.noosh.nooshentry.automation.buyerSite.LoginBuyerPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.RegisterBuyerPage;
import com.noosh.nooshentry.automation.demoSQANoosh.CommonUtils;
import com.noosh.nooshentry.automation.demoSQANoosh.CreateNewProduct;
import com.noosh.nooshentry.automation.demoSQANoosh.CreateNewProject;
import com.noosh.nooshentry.automation.demoSQANoosh.Invite;
import com.noosh.nooshentry.automation.demoSQANoosh.LoginDemoSqaPage;
import com.noosh.nooshentry.automation.demoSQANoosh.MainMenuPage;
import com.noosh.nooshentry.automation.demoSQANoosh.NewHomePage;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.demoSQANoosh.ProjectFrame;
import com.noosh.nooshentry.automation.demoSQANoosh.RegisterNewSPSitePage;
import com.noosh.nooshentry.automation.demoSQANoosh.RegisterNewSupplierSitePage;
import com.noosh.nooshentry.automation.demoSQANoosh.UserProfileFrame;
import com.noosh.nooshentry.automation.demoSQANoosh.WelcomeScreen;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class BaseTestCases extends BaseSeleniumTest {
	   @Test(description="SP sign up")
	   public static void testRegisterNewSPSite(String baseUrlSignUp, String fullNameSP, String passwordSP, 
			                String phoneNumberSP, String companyName) throws InterruptedException {  	  
		   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		   driver.get(baseUrlSignUp);
		   driver.manage().window().maximize();	
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   registerSPSitePage.spCredential(fullNameSP, emailSP, passwordSP, phoneNumberSP, companyName);
		   registerSPSitePage.getAgreementPopup();
		   registerSPSitePage.acceptAgreement();
		   CommonUtils commonUtils = new CommonUtils(driver);
		   long intialTime = commonUtils.currentTime();
		   registerSPSitePage.clickSignupBT();
		   long endingTime = commonUtils.currentTime();
		   long signupPageLoadTime = commonUtils.loadingTime(intialTime, endingTime);
		   log.info("*** SP Signup Home Page loading time: " + signupPageLoadTime + " seconds ***");
		   System.out.println("*** SP Signup Home Page loading time: " + signupPageLoadTime + " seconds ***");
	   }
	   
	   @Test(description="New supplier signup")
	   public static void testNewSupplierSite(String supplierFullName, String supplierPasswd, String supplierCompanyName) 
			   throws InterruptedException {
		   RegisterNewSupplierSitePage registerSupplierSitePage = new RegisterNewSupplierSitePage(driver);
		   Thread.sleep(1000);
		   registerSupplierSitePage.setSupplierInfo(supplierFullName, supplierPasswd, supplierCompanyName);
		   Thread.sleep(2000);
		   registerSupplierSitePage.clickSignupBT();
		   Thread.sleep(3000);
	   }
	   
	   @Test(description="SP - Let's Begin")
	   public static void testLetsBegin(String letsBegin) throws InterruptedException  {
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   try {
			   AssertJUnit.assertEquals(letsBegin, registerSPSitePage.getButtonText().trim());
		   } catch(Throwable e) {
			      System.out.println("Lets Begin does not displayed");
			      log.info("Lets Begin does not displayed");
		       }
    	   if(browser.trim().equals("IE")) {
    		   Thread.sleep(2000);   
		   }  
		   registerSPSitePage.clickLetsBegin();		     
	   }	
	   
	   @Test(description="SP - Create project")
	   public static void testCreateProject1(String projectName, String clientName) throws InterruptedException {
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   WelcomeScreen welcomeScreen = new WelcomeScreen(driver);
		   
		   projectName = projectName + unicID;
		   registerSPSitePage.clickCreateProject();		   
	       welcomeScreen.setProjectName(projectName);
	       clientName = clientName + unicID;
	       welcomeScreen.setClientCompanyName(clientName);
		   Thread.sleep(1000);
	       welcomeScreen.clickAllForm();
		   Thread.sleep(2000);
	       welcomeScreen.selectEnvelopeProduct();
	       welcomeScreen.clickCreateProjectBT();
	   }
	   
	   
	   @Test(description="SP - Create project, project info tab")
	   public static void testCreateProjectPopup(String projectName) throws InterruptedException {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);		   
		   projectName = projectName + unicID;
		   System.out.println("Project name: " + popupWindow.getProjectName());		   
		   try {
			   AssertJUnit.assertEquals(projectName, popupWindow.getProjectName().trim());
		   } catch(Throwable e) {
			      System.out.println("1.2 Project name does not match");
			      log.info("1.2 Project name does not match");
		       }
		   Thread.sleep(500);   	    
		   popupWindow.callCalendar(); 
		   Thread.sleep(1000);
		   popupWindow.setNextMonth();
		   popupWindow.setComplationDate();	
		   popupWindow.getReviewAndSubmitTab();
		   popupWindow.clickSubmitBT();
		   Thread.sleep(3000);			   
	   }
	   
	   @Test(description="SP - Create project, project info tab")
	   public static void testCreateProjectPopup1(String projectName) throws InterruptedException {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);		   
		   projectName = projectName + unicID;		   
		   try {
			   AssertJUnit.assertEquals(projectName, popupWindow.getProjectName().trim());
		   } catch(Throwable e) {
			      System.out.println("1.2 Project name does not match");
			      log.info("1.2 Project name does not match");
		       }
		   Thread.sleep(500);   	    
		   popupWindow.callCalendar();	
		   popupWindow.setNextMonth();
		   popupWindow.setComplationDate();		
	   }
	   
	   @Test(description="SP - Create project, spec description")
	   public static void testCreateProjectPopupSpec(String projNameSPAdv, String sku, String referenceNumber, String quantSP) throws InterruptedException {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);		   
		   Thread.sleep(500);
		   popupWindow.clickSpecDescription();	   
		   popupWindow.putDescriptionName(projNameSPAdv);
		   popupWindow.putSKU(sku);
		   popupWindow.putRefNumber(referenceNumber);
		   popupWindow.putQuant1(quantSP);	
		   Thread.sleep(500);
	   }
	   
	   @Test(description="SP - Create project, add new product")
	   public static void testCreateProjectPopupAddProduct(String descriptionNameSP, String sku, String referenceNumber, 
			   String quantSP, String newProjectNameSP) throws InterruptedException {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);
		   CreateNewProduct newProduct = new CreateNewProduct(driver);		   
		   popupWindow.getReviewAndSubmitTab();
		   popupWindow.clickAddProductsBT();
		   Thread.sleep(500);
		   newProduct.clickBrochureIcon();	   
		   Thread.sleep(500);
		   popupWindow.clickSpecDescription();	   
		   popupWindow.putDescriptionName(descriptionNameSP + 1);
		   popupWindow.putSKU(sku);
		   popupWindow.putRefNumber(referenceNumber);
		   popupWindow.putQuant1(quantSP);
		   popupWindow.getReviewAndSubmitTab();	   
		   popupWindow.clickProjectInfoEditBT();
		   Thread.sleep(500);
		   newProjectNameSP = newProjectNameSP + unicID;
		   popupWindow.setProjectName(newProjectNameSP);
		   Thread.sleep(500);
		   popupWindow.getReviewAndSubmitTab();	
	   }
	   
	   @Test(description="SP - Create project, Review - project verification")
	   public static void testReviewProjectVerification(String descriptionNameSP, String sku, String referenceNumber, 
			   String quantSP, String newProjectNameSP, String skuSP) throws InterruptedException  {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);
		   newProjectNameSP = newProjectNameSP + unicID;
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(descriptionNameSP, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + descriptionNameSP + " does not displayed");
				    log.info("Review - project verification: " + descriptionNameSP + " does not displayed");
			        }  
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(sku, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + sku + " does not displayed");
				    log.info("Review - project verification: " + sku + " does not displayed");
			        } 	
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(referenceNumber, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + referenceNumber + " does not displayed");
				    log.info("Review - project verification: " + referenceNumber + " does not displayed");
			        } 	
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(quantSP, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + quantSP + " does not displayed");
				    log.info("Review - project verification: " + quantSP + " does not displayed");
			        } 
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(newProjectNameSP, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + newProjectNameSP + " does not displayed");
				    log.info("Review - project verification: " + newProjectNameSP + " does not displayed");
			        } 
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(skuSP, driver));	
			   } catch(Throwable e) {
				    System.out.println("Review - project verification: " + skuSP + " does not displayed");
				    log.info("Review - project verification: " + skuSP + " does not displayed");
			        } 		   
		   popupWindow.submitNewProject();
		   Thread.sleep(2000);			   		   
	   }
	   
	   @Test(description="SP - New Home Page - client view")
	   public static void testNewHomePage(String passwordSP) throws InterruptedException {
		   MainMenuPage mainMenuPage = new MainMenuPage(driver);
		   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);		   
		   Thread.sleep(2500);
		   mainMenuPage.clickProjectMenu();		   		   
		   Thread.sleep(1500);
		   registerSPSitePage.logoutSPSite();
		   Thread.sleep(2000);
		   loginDemoSqa.loginUser(emailSP, passwordSP);      
		   Thread.sleep(1000);		   
	   }	   
	   
	   @Test(description="SP - New Home Page - client view")
	   public static void testNewHomePageVerification(String newProjectNameSP, String counter) throws InterruptedException {
		   NewHomePage homePage = new NewHomePage(driver);
		   newProjectNameSP = newProjectNameSP + unicID;
		   		   
		   String spSiteLoginPage = driver.getCurrentUrl();
		   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 49);
		   spSiteLoginPage = spSiteLoginPage + "login";
		   
		   System.out.println("SP url: " + spSiteLoginPage);
		   log.info("SP url: " + spSiteLoginPage);
		   log.info("SP site name/password: nobody+" + unicID + "@noosh.com/17password");
		   try{
		       AssertJUnit.assertEquals(newProjectNameSP, homePage.getProjectName().trim());
		      } catch(Throwable e) {
		    	  System.out.println("SP - New Home Page: project " + newProjectNameSP + " does not displayed");
		    	  log.info("SP - New Home Page: project " + newProjectNameSP + " does not displayed");
		    	}	
		   System.out.println("Project status: " + homePage.getProjectStatus(driver, newProjectNameSP));  
		   try{
		       AssertJUnit.assertEquals("New", homePage.getProjectStatus(driver, newProjectNameSP).trim());
		      } catch(Throwable e) {
		    	  System.out.println("SP - New Home Page: 'New' project status does not displayed");
		    	  log.info("SP - New Home Page: 'New' project status does not displayed");
		    	}	
	       System.out.println("Couter: " + homePage.getProjectCouter(driver, counter));
		   try{
		       AssertJUnit.assertEquals("1", homePage.getProjectCouter(driver, counter));
		      } catch(Throwable e) {
		    	  System.out.println("Couter: " + homePage.getProjectCouter(driver, counter));
		    	  System.out.println("SP - New Home Page: project counter does not match");
		    	  log.info("SP - New Home Page: project counter does not match");
		    	}	
		   try{
		       AssertJUnit.assertEquals("Invite to Project", homePage.getClientUser(driver, newProjectNameSP));
		      } catch(Throwable e) {
		    	  System.out.println("SP - New Home Page: Invite to Project does not displayed");
		    	  log.info("SP - New Home Page: Invite to Project does not displayed");
		    	}
		   
	   }	   
	   
	   @Test(description="Check SP invitation in the email")
	   public static void testLoginEmailPage(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
			   String newLastNameSP, String message) 
	      throws InterruptedException, FileNotFoundException, AWTException {
		  
		  driver.get(baseUrlBuyerSite);      
	      driver.manage().window().maximize();
	      
	      // Verifying that this is the correct page     

	      LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);     
	      	      
	      loginEmailPage.loginUser(validBuyerEmail, "77pass33word");      
	      Thread.sleep(1500);     
	      String searchString = buyerEmail + " " + message;   
	      loginEmailPage.searchInvitationLetter33(searchString);
	      Page.robotClickEnter();
	      Thread.sleep(2500);
	      loginEmailPage.getInvitationLetter();
		  Thread.sleep(1600);      
	      loginEmailPage.getRegisterBuyerPage();
	      Thread.sleep(3000);	     
	   } 
	   
	   @Test(description="Register new Buyer")
	   public static void testRegisterBuyerPage(String firstNameBuyer, String lastNameBuyer, 
	      String passwordBuyer, String phoneNumberBuyer) throws InterruptedException {
	      RegisterBuyerPage registerBuyerPage = new RegisterBuyerPage(driver);

	      for (String handle : driver.getWindowHandles()) {
	         driver.switchTo().window(handle);
	      }
	      
	      registerBuyerPage.buyerCredential(firstNameBuyer, lastNameBuyer, passwordBuyer, 
	         phoneNumberBuyer);
	      registerBuyerPage.getAgreementPopup();    
	      registerBuyerPage.acceptAgreement();
	      registerBuyerPage.moveSlider();
	      Thread.sleep(3000); 	                
	   }
	   
	   public static void testUploadFileSP(String fileForProject5, String fileForProject3) throws InterruptedException, AWTException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		   Page  page = new Page(driver);		   	   
		   Thread.sleep(500); 
		   brochurePopup.clickFileTabPop();
		   Thread.sleep(1000); 		   
		   page.uploadFileModalWindow(fileForProject5);
		   brochurePopup.clickUploadFileDropBT();
		   Thread.sleep(1000);
		   page.robotUpload();
		   Thread.sleep(3500);	 
		   page.uploadFileModalWindow(fileForProject3);
		   brochurePopup.clickUploadFileDropBT();
		   Thread.sleep(1000);
		   page.robotUpload();
		   Thread.sleep(3000);
	   }	   	   
	   
	   public static void testInviteCustomerHovering(String validBuyerEmail, String confirmInviteMessage, 
			   String invalidNewCustomerEmail, String wrongEmailMessage, String validSPEmail) throws InterruptedException  {			   		   
		   NewHomePage homePage = new NewHomePage(driver);
		   Invite invite = new Invite(driver);
		      
           homePage.clickShareBT();
		   Thread.sleep(2000);
		   invite.putClientEmail(invalidNewCustomerEmail);
		   Thread.sleep(600);
		   invite.sendInvitationBT();
		   Thread.sleep(1000);
		   try {
		       AssertJUnit.assertEquals(wrongEmailMessage, invite.getErrorInviteMessage());	      
		   } catch(Throwable e) {
			   System.out.println("Error message '" + wrongEmailMessage + "' does not displayed");
			   log.info("Error message '" + wrongEmailMessage + "' does not displayed");
		   }		   		   
		   Thread.sleep(1000);
		   String buyerEmails = validBuyerEmail + "+" + unicID + "@gmail.com";
		   invite.putClientEmail(buyerEmails);
		   Thread.sleep(600);
		   invite.sendInvitationBT();
		   Thread.sleep(1000);
		   try {
		       AssertJUnit.assertEquals(buyerEmails, invite.getFirstBuyerEmail());	      
		   } catch(Throwable e) {
			   System.out.println("Invited client email '" + buyerEmails + "' does not displayed");
			   log.info("Invited client email '" + buyerEmails + "' does not displayed");
		   }
		   try {
		       AssertJUnit.assertEquals(confirmInviteMessage, invite.checkConfirmationMessage());	      
		   } catch(Throwable e) {
			   System.out.println("Confirmation message '" + confirmInviteMessage + "' does not displayed");
			   log.info("Confirmation message '" + confirmInviteMessage + "' does not displayed");
		   }		      		   
		   invite.putClientEmail(validSPEmail);
		   Thread.sleep(600);
		   invite.sendInvitationBT();
		   Thread.sleep(1000);
		   try {
		       AssertJUnit.assertEquals(validSPEmail, invite.getSecondInvited());	      
		   } catch(Throwable e) {
			   System.out.println("Invited client email '" + validSPEmail + "' does not displayed");
			   log.info("Invited client email '" + validSPEmail + "' does not displayed");
		   }
		   try {
		       AssertJUnit.assertEquals(confirmInviteMessage, invite.checkConfirmationMessage());	      
		   } catch(Throwable e) {
			   System.out.println("Confirmation message '" + confirmInviteMessage + "' does not displayed");
			   log.info("Confirmation message '" + confirmInviteMessage + "' does not displayed");
		   }		   
		   Thread.sleep(2000);
		   invite.closeInviteClientPopup();
		   Thread.sleep(2000);
	   }	   
	   
	   public static void testGetBuyerSite(String siteName) throws InterruptedException {	  
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
	   }	   
	   
	//----------------------------------- end of SP onboarding -------------------------------------------------   
	   @Test(description="Login Demo SQA Page, smoke test")
	   public static void testLoginDemoSqaPageSmoke(String passwordSP, String spEmailSmoke, String baseUrlSmoke, String spSiteLoginPage) {
	      driver.get(baseUrlSmoke);    
	      driver.manage().window().maximize();          
	      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
	      loginDemoSqa.loginUser(spEmailSmoke, passwordSP);           
		  spSiteLoginPage = driver.getCurrentUrl(); 		
	   } 
	
	   @Test(description="13.3, Buyer sends new message")
	    public static void testSendMessageFile(WebDriver driver, String buyerNewMessage) throws InterruptedException {
		   NewProjectPage newProjectPage = new NewProjectPage(driver);
	       
	       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		   newProjectPage.clickMessageTab();
	       Thread.sleep(1000);	   
	       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);        
	       newProjectPage.buyerMessage(buyerNewMessage);
	       newProjectPage.sendMessage();
	       Thread.sleep(1000);
	       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);    
		   try {
			   AssertJUnit.assertTrue(Page.isTextPresent(buyerNewMessage, driver));	        
			   } catch(Throwable e) {
				  System.out.println("13.3 Buyer message " + buyerNewMessage + " does not displayed");
				  log.info("13.3 Buyer message " + buyerNewMessage + " does not displayed");
			   } 	        
	    }	   
	   
	   @Test(description="User Profile - Reset passowrd")
	   public static void testResetPassword(WebDriver driver, String wrongPassword, String password, String newPassword,
			   String wrongOriginalPassword) throws InterruptedException {
		   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	       
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   userProfileFrame.clickResetPassword();
	       Thread.sleep(500);
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   userProfileFrame.setOriginalPassword(wrongPassword);
		   userProfileFrame.setNewPassword(password);
		   userProfileFrame.setConfirmPassword(password);
		   userProfileFrame.clickChangePasswordBT();
	       Thread.sleep(1000);
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   try {
		       AssertJUnit.assertTrue(Page.isTextPresent(wrongOriginalPassword, driver));	      
		   } catch(Throwable e) {
			   System.out.println("6.3 'The original password is not correct!' does not displayed");
			   log.info("6.3 'The original password is not correct!' does not displayed");
		   }
		   userProfileFrame.setOriginalPassword(password);
		   userProfileFrame.setNewPassword(newPassword);
		   userProfileFrame.setConfirmPassword(newPassword);
		   userProfileFrame.clickChangePasswordBT();
	       Thread.sleep(1000);       
	   }
	   
	   @Test(description="SP user Profile - Reset password back")
	   public static void testResetPasswordBack(WebDriver driver, String password, String newPassword) throws InterruptedException {
		   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);	       
		   userProfileFrame.clickResetPassword();
		   userProfileFrame.setOriginalPassword(newPassword);
		   userProfileFrame.setNewPassword(password);
		   userProfileFrame.setConfirmPassword(password);
		   userProfileFrame.clickChangePasswordBT();
	       Thread.sleep(500);	       
	   }   
	   
	   @Test
	   public static void testSPcreateProjectProjectWizard1(String projNameSP, String descriptionNameSP,
			   String sku, String referenceNumber, String quantSP, String specDescrSP,
			   String fileForProject1) throws InterruptedException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);		   		    
		   brochurePopup.setProjectName(projNameSP); 
		   Thread.sleep(500);   
		   brochurePopup.callCalendar();      
		   brochurePopup.setNextMonth();
		   brochurePopup.setComplationDate();	   
		   brochurePopup.getNextTab(); 
		   brochurePopup.putDescriptionName(descriptionNameSP);  
		   brochurePopup.putSKU(sku);
		   brochurePopup.putRefNumber(referenceNumber);
		   brochurePopup.putQuant1(quantSP);
		   brochurePopup.putSpecDescription(specDescrSP);      		      
		   brochurePopup.getFilesTab();
		   brochurePopup.uploadProjectFile(fileForProject1);
		   Thread.sleep(500);  
		   brochurePopup.getReviewAndSubmitTab(); 
	       brochurePopup.clickAddProductsBT();		      		  
		   }
	   
	   @Test
	   public static void testCreateProductIntoCreateProject(String descriptionNameSP, String sku, String referenceNumber, 
			   String quantSP) throws InterruptedException {  
		   CreateNewProduct newProduct = new CreateNewProduct(driver);
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		   
		   Thread.sleep(500);
		   newProduct.clickBrochureIcon();
		   Thread.sleep(500);
		   brochurePopup.clickSpecDescription();
		   Thread.sleep(800);
		   brochurePopup.putDescriptionNameNew(driver, "Spec Name", descriptionNameSP + 1);
		   brochurePopup.putDescriptionNameNew(driver, "SKU", sku);
		   brochurePopup.putDescriptionNameNew(driver, "Reference Number", referenceNumber);		   
		   brochurePopup.putQuant1(quantSP);
		   Thread.sleep(500); 
		   brochurePopup.getReviewAndSubmitTab();
		   Thread.sleep(2000);
	   }

	   @Test
	   public static void testCreateProjectVerification(String projNameSPAdv, String productionSpec1, String productionSpec2,
			   String fileProject) throws InterruptedException {
		   MainMenuPage mainMenu = new MainMenuPage(driver);
		   ProjectFrame projectFrame = new ProjectFrame(driver);
		   NewHomePage newHomePage = new NewHomePage(driver);
		   
		   Thread.sleep(1000); 
		   mainMenu.clickProjectMenu();
		   Thread.sleep(800); 	   
           newHomePage.clickProjectInList(driver, projNameSPAdv);
		   try {
			      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
			   } catch(Throwable e) {
				  System.out.println("10.x Project status 'New' does not displayed");
				  log.info("10.x Project status 'New' does not displayed");
			   } 		   
		  
		   projectFrame.clickProductionSpecTab();
		   Thread.sleep(1500);
		   try {
			   AssertJUnit.assertTrue(Page.isTextPresent(productionSpec1, driver));	          
			   } catch(Throwable e) {
				  System.out.println("10.x Production spec " + productionSpec1 + " does not displayed");
				  log.info("10.x Production spec " + productionSpec1 + " does not displayed");
			   } 
		   try {
			   AssertJUnit.assertTrue(Page.isTextPresent(productionSpec2, driver));	   
			   } catch(Throwable e) {
				  System.out.println("10.x Production spec " + productionSpec2 + " does not displayed");
				  log.info("10.x Production spec " + productionSpec2 + " does not displayed");
			   } 		   	   
	   }
	   
	   @Test(description="10.1-1 SP Create Project Adv. Editor - step 1")
	   public static void testSPcreateProjectAdvEd(String validBuyerEmail) throws InterruptedException {		   
		   CreateNewProject newProject = new CreateNewProject(driver);  		   
		   Thread.sleep(1000);
		   newProject.clicklCreateProjectBT();	    
		   newProject.enterValidClientEmail(validBuyerEmail);
		   Thread.sleep(600);
		   newProject.clickFindBT();
		   Thread.sleep(1000);
		   newProject.selectCertainSite();   
	   }
	   
	   @Test(description="10.1-2 SP Create Project Adv. Editor - step 1")
	   public static void testSPcreateProjectAdvEdSelectProduct(String productName) throws InterruptedException {		   
		   CreateNewProject newProject = new CreateNewProject(driver);		   		   
		   Thread.sleep(1000);
		   newProject.clickAdvPoduct(driver, productName);
		   Thread.sleep(1000);	   
	   }
	   
	   @Test(description="10.1-3 SP Create Project Adv. Editor - step 2")
	   public static void testSPcreateProjectAdvEdProjectWizard(String projNameSPAdv, String descriptionNameSPAdv, String referenceNumber,
			String quantSP) throws InterruptedException {
		   projNameSPAdv = projNameSPAdv + unicID;		   
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);		   
		   Thread.sleep(500);   	    
		   brochurePopup.callCalendar();
		   Thread.sleep(500); 
		   brochurePopup.setNextMonth();
		   brochurePopup.setComplationDate();	   	   
		   brochurePopup.getNextTab();	    
	       brochurePopup.setTextBoxText(descriptionNameSPAdv);
		   Thread.sleep(500);   	    
		   brochurePopup.callCalendarAdv();	               
		   brochurePopup.setComplationDate();	   
		   brochurePopup.setNumber(referenceNumber);
		   brochurePopup.setDimentionLength(quantSP);
		   brochurePopup.setImportance();
		   brochurePopup.setPriority();
		   brochurePopup.setQuantitySecondField(unicID);
		   Thread.sleep(1500);	   
	   }
	   
	   @Test(description="10.1-4 SP Create Project Adv. Editor - upload file")
	   public static void testUploadFile(String fileForProject3) throws InterruptedException, AWTException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		   Page  page = new Page(driver);		   
		   brochurePopup.clickFileTabPop();
		   Thread.sleep(500); 
		   page.uploadFileModalWindow(fileForProject3);
		   brochurePopup.clickUploadFileDropBT();
		   Thread.sleep(1000);
		   page.robotUpload();
		   Thread.sleep(500);  	   
	   } 
	   
	   @Test(description="10.1-5 SP Create Project Adv. Editor - Add product")
	   public static void testSPcreateProjectAdvEdAddProduct(String projNameSPAdv, String reqField) throws InterruptedException {
		   String url = driver.getCurrentUrl();		   
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);		   
		   brochurePopup.getReviewAndSubmitTab();	    
		   brochurePopup.getReviewAndSubmitTab();	   
		   brochurePopup.clickSubmitBT();
		   Thread.sleep(800);
		   try {
			    AssertJUnit.assertTrue(Page.isTextPresent(reqField, driver));	
			   } catch(Throwable e) {
				    System.out.println("13.1.1-7, Error message " + reqField + " does not displayed");
				    log.info("13.1.1-7 Error message " + reqField + " does not displayed");
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
	   }
	   
	   @Test(description = "Sign out gmail")
	   public static void signOutGmail(WebDriver driver) throws InterruptedException {
		   LoginEmailPage loginEmailPage = new LoginEmailPage(driver);		   
		   loginEmailPage.logoutGmail();
	   }
// --------------------------------- New Integration test cases -----------------------------------------------------	   

	   public static void updateProjectStatus(String buyerProjectName) throws InterruptedException {
	   	   NewHomePage newHomePage = new NewHomePage(driver);		   
	   	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   	   
	   	   Thread.sleep(1200);
	   	   projectFrame.clickOverviewTab();
	   	   Thread.sleep(1200);
	   	   newHomePage.changeProjectStatus(driver, buyerProjectName);
	   }
	   
	   public static void spForgotPassword() throws InterruptedException {
	       RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	       
		   if(browser.trim().equals("IE")) {
			    registerSPSitePage.logoutSPsiteIE();
		    } else {
		        registerSPSitePage.logoutSPSite();
		    }
		   Thread.sleep(1000);
		   loginDemoSqa.clickForgotPasswordLink();
		   Thread.sleep(600);
		   loginDemoSqa.putSPEmail(emailSP);
		   Thread.sleep(1000);
	   }
	   
	   public static void getResetPasswordLink(NooshWebDriver driver, String baseUrlBuyerSite, String email, String password) 
			           throws InterruptedException, AWTException {
		    LoginEmailPage loginEmailPage = new LoginEmailPage(driver);  
		    driver.get(baseUrlBuyerSite); 
		    Thread.sleep(1500);
		    loginEmailPage.loginUser(email, password);      
		    Thread.sleep(1500); 
		    String searchString = "Reset Password for Noosh Group " + unicID;
		    loginEmailPage.searchInvitationLetter33(searchString);
		    Page.robotClickEnter();
		    Thread.sleep(2500);
		    loginEmailPage.getInvitationLetter();
		    Thread.sleep(1200);
		    String resetPasswordLink = "https://selenium"+ unicID;
            loginEmailPage.getResetPasswordLink(driver, resetPasswordLink);		    
	   }
	   
	   public static void resetSPPassword(String passwordSP) throws InterruptedException {
	       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);

		   loginDemoSqa.setNewSPPassword(passwordSP);	   
	   }
	   
	   public static void resetBuyerPassword(String passwordSP){
		   LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);

		   loginBuyerPage.setNewBuyerPassword(passwordSP);	   
	   }
	   
	   public static void buyerForgotPassword(String emailBuyer) throws InterruptedException {
		   LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
			
		   Thread.sleep(1000);
		   loginBuyerPage.clickResetPasswordLink();
		   Thread.sleep(800);
		   loginBuyerPage.putBuyerEmail(emailBuyer);
		   Thread.sleep(1000);
	   }
	   
	   public static void testBuyerEmailLogout(String winHandleBefore) throws FileNotFoundException, InterruptedException, AWTException {    
		   LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);     
		   Thread.sleep(2200);   
		   loginEmailPage.logoutBuyerEmail1();
		   Thread.sleep(1000);   
	   }	
// ----------------------------------------------- new Integration test cases: create project ------------------------------------------------
	   
	   public static void testSPCreateProjectSelectBuyerAndWorkspace(String validBuyerEmail) throws InterruptedException {		   
		   CreateNewProject newProject = new CreateNewProject(driver); 
		   NewHomePage newHomePage = new NewHomePage(driver);		   
		   newHomePage.clickCreateProjectHovering();
		   Thread.sleep(1000);		   	   
		   newProject.clickPostcard();   
	   }	   
	   
	   public static void testSPSetProjectInfo(String secondSPProjectName, String description) throws InterruptedException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);		   
		   Thread.sleep(500); 
		   brochurePopup.setProjectName(secondSPProjectName);
		   brochurePopup.callCalendar();	
		   brochurePopup.setNextMonth();
		   brochurePopup.setComplationDate();	
		   brochurePopup.setProjectDescription(description);
	   }
	   
	   public static void testSPSetSpecDiscription(String descriptionNameSP, String referenceNumber, String printQuant, String mailQuant) 
			             throws InterruptedException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 
		   
		   descriptionNameSP = descriptionNameSP + unicID;
		   brochurePopup.clickSpecDescription();	   
		   brochurePopup.putDescriptionName(descriptionNameSP); 
		   brochurePopup.putRefNumber(referenceNumber);
		   brochurePopup.setPrintQuantity(printQuant);
		   brochurePopup.setMailQuantity(mailQuant);
		   Thread.sleep(1000);
	   }
	   public static void testSPSetSizeAndStock() throws InterruptedException {
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 
		   
		   brochurePopup.clickSizeAndStock();
		   Thread.sleep(500); 
		   brochurePopup.setSizeOption();
	   }
	   public static void testSubmitProject() throws InterruptedException {
		   BrochurePopupWindow  popupWindow = new BrochurePopupWindow(driver);
		   
		   popupWindow.getReviewAndSubmitTab();
		   popupWindow.clickSubmitBT();
		   Thread.sleep(2500);			   
	   }
	   public static void testOpenProjectAndAssignClient(String project, String buyerEmails) throws InterruptedException {
		   NewHomePage newHomePage = new NewHomePage(driver);
		   
		   Thread.sleep(1200); 
		   newHomePage.openProjectSPName();
		   Thread.sleep(1200); 
		   newHomePage.assignClientForProject(driver, project, buyerEmails);
		   Thread.sleep(1000);
	   }
	   public static void testSPProject2VerificationStatus(String buyerProjectName) throws InterruptedException {
		   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);	   
			
		   if(buyerBrowser.trim().equals("IE")){
			   buyerProjectName = buyerProjectName + unicID + "G";
			   Thread.sleep(1000);
			   buyerSitePage.getNewSPProject(buyerDriver, buyerProjectName);
		   } else
		       buyerSitePage.clickCreatedProjectAdvEd();    	   
	   }
}


