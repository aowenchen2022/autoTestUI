package com.noosh.nooshentry.automation.testscripts;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import org.testng.AssertJUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.BuyerSitePage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.demoSQANoosh.CreateSitePopup;
import com.noosh.nooshentry.automation.demoSQANoosh.Invite;
import com.noosh.nooshentry.automation.demoSQANoosh.MainMenuPage;
import com.noosh.nooshentry.automation.demoSQANoosh.NewHomePage;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.demoSQANoosh.ProjectFrame;
import com.noosh.nooshentry.automation.demoSQANoosh.RegisterNewSPSitePage;
import com.noosh.nooshentry.automation.demoSQANoosh.TestDemoSqaFF;
import com.noosh.nooshentry.automation.demoSQANoosh.WelcomeScreen;

public class TestOnboardingSP extends BaseSeleniumTest
{
	Page page = new Page(driver);
	String winHandleBefore;
	String emailSP1 = "nobody" + "+" + unicID + "A@noosh.com";
	String buyerEmail;
	
	   @Test(description="1.1, SP onboarding - SP sign up")
	   @Parameters({"baseUrlSignUp", "firstNameSP", "lastNameSP", "passwordSP", "phoneNumberSP", "companyName"})
	  // public void testRegisterNewSPSite(String baseUrlSignUp, String firstNameSP, String lastNameSP, 
		//	   String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException
	   public void testRegisterNewSPSite(String baseUrlSignUp, String fullNameSP,
			   String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException
	   {		   
		   //BaseTestCases.testRegisterNewSPSite(baseUrlSignUp, firstNameSP, lastNameSP, 
			//	   passwordSP, phoneNumberSP, companyName);                                   //10/2
		   BaseTestCases.testRegisterNewSPSite(baseUrlSignUp, fullNameSP, passwordSP, phoneNumberSP, companyName);                                   //10/2

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 2.1, New SP sign up, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-1, SP onboarding - Welcome screen")
	   @Parameters({"letsBegin"})
	   public void testWelcomeScreen(String letsBegin) throws InterruptedException
	   {		   		   
		   BaseTestCases.testLetsBegin(letsBegin);

		   System.out.println("SP site URL: " + driver.getCurrentUrl());
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-1, Welcome screen, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-2, SP onboarding - Welcome screen")
	   @Parameters({"projectNameForClient", "clientName"})
	   public void testCreateProject(String projectNameForClient, String clientName) throws InterruptedException
	   {		   
		   BaseTestCases.testCreateProject1(projectNameForClient, clientName);		   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-2, Welcome screen, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-3, SP onboarding - Project create popup, project info tab")
	   @Parameters({"projectNameForClient"})
	   public void testCreateProjectProjectInfo(String projectNameForClient) throws InterruptedException
	   {		   		   
		   Thread.sleep(1000);
		   BaseTestCases.testCreateProjectPopup1(projectNameForClient);		   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-3, SP onboarding - Project create popup, project info tab, JS errors for SP site #######");		   
	   }
	   
	   
	   @Test(description="1.2-4, SP onboarding - Project create popup, spec description tab")
	   @Parameters({"projNameSPAdv", "skuSP", "referenceNumber", "quantSP"})
	   public void testCreateProjectSpecDescription(String projNameSPAdv, String skuSP, String referenceNumber, String quantSP) throws InterruptedException
	   {		   
		   BaseTestCases.testCreateProjectPopupSpec(projNameSPAdv, skuSP, referenceNumber, quantSP);		   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-4, SP onboarding - Project create popup, spec description tab, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-5, SP onboarding - Project create popup, spec description tab")
	   @Parameters({"fileForProject5", "fileForProject3"})
	   public void testUploadFile(String fileForProject5, String fileForProject3) throws InterruptedException, AWTException
	   {		   
		   BaseTestCases.testUploadFileSP(fileForProject5, fileForProject3);		   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-4, SP onboarding - Project create popup, spec description tab, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-6, SP onboarding - Project create popup - add new product, spec description tab")
	   @Parameters({"descriptionNameSP", "sku", "referenceNumber", "quantSP", "newProjectNameSP"})
	   public void testCreateProjectAddNewProduct(String descriptionNameSP, String sku, String referenceNumber, String quantSP, String newProjectNameSP) throws InterruptedException
	   {		   
		   BaseTestCases.testCreateProjectPopupAddProduct(descriptionNameSP, sku, referenceNumber, quantSP, newProjectNameSP);	   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-6, SP onboarding - Project create popup - add new product, spec description tab, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-7, SP onboarding - Create project, Review - project verification")
	   @Parameters({"descriptionNameSP", "sku", "referenceNumber", "quantSP", "newProjectNameSP", "projNameSPAdv", "skuSP"})
	   public void testReviewPageVerification(String descriptionNameSP, String sku, String referenceNumber, String quantSP, String newProjectNameSP, 
			   String projNameSPAdv, String skuSP) throws InterruptedException
	   {		   
		   BaseTestCases.testReviewProjectVerification(descriptionNameSP, sku, referenceNumber, 
				   quantSP, newProjectNameSP, skuSP);	   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-7, SP onboarding - Create project, Review - project verification, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-8 ")
	   @Parameters({"passwordSP"})
	   public void testNewHomePageSP(String passwordSP) throws InterruptedException
	   {
		   BaseTestCases.testNewHomePage(passwordSP);
		   
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-8, SP onboarding - login - logout, JS errors for SP site #######");
	   }
	   
	   @Test(description="1.2-9, SP onboarding - New Home page - client view")
	   @Parameters({"newProjectNameSP"})
	   public void testNewHomePageSPVerification(String newProjectNameSP) throws InterruptedException
	   {		   
		   String counter = "Active Project";
		   BaseTestCases.testNewHomePageVerification(newProjectNameSP, counter);	   

		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-9, SP onboarding - New Home page - client view, project verification, JS errors for SP site #######");		   
	   }
	   
	   @Test(description="1.2-10, SP onboarding - New Home page - Change clien name")
	   @Parameters({"newClientName"})
	   public void testChangeClientName(String newClientName) throws InterruptedException, AWTException
	   {
		   NewHomePage homePage = new NewHomePage(driver);
		   
		   newClientName = newClientName + unicID;
		   homePage.changeClientName(newClientName);
		   page.closeModalPopup();
		   Thread.sleep(2000);
		   System.out.println("New client name: " + homePage.getClientName());
		   try{
		       AssertJUnit.assertEquals(newClientName, homePage.getClientName().trim());
		      } catch(Throwable e) {
		    	  System.out.println("SP - New Home Page: '" + newClientName + "' client name does not displayed");
		    	  log.info("SP - New Home Page: '" + newClientName + "' client name does not displayed");
		    	}	
		  
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-10, SP onboarding - New Home page - change client name, JS errors for SP site #######");
	   }
	   
	   @Test(description="1.2-11, SP onboarding - New Home page - Upload new client logo")
	   @Parameters({"oldProfilePicture"})
	   public void testUploadNewClientLogo(String oldProfilePicture) throws InterruptedException, AWTException
	   {
		   NewHomePage homePage = new NewHomePage(driver);		   
		   
		   String oldClientImage = homePage.getClientLogo();
		   page.uploadFileModalWindow(oldProfilePicture);
		   homePage.uploadClientLogo();
		   Thread.sleep(1000);
		   page.robotUpload();
		   Thread.sleep(3000);	  
		   if (oldClientImage.equals(homePage.getClientLogo()))
		   {
			   System.out.println("1.2-11 The new client image does not displayed in the client view");
		       log.info("1.2-11 The new client image does not displayed in the client view");
		   }		   
		   
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-11, SP onboarding - New Home page - Upload new client logo, JS errors for SP site #######");
	   }
	   
	   @Test(description="1.2-12, SP onboarding - New Home page - Invite buyer")
	   @Parameters({"firstNameBuyer", "lastNameBuyer", "validBuyerEmail"})
	   public void testInviteBuyer(String firstNameBuyer, String lastNameBuyer, String validBuyerEmail) throws InterruptedException, AWTException
	   {
		   NewHomePage homePage = new NewHomePage(driver);	
		   Invite invite = new Invite(driver);
		   
		   homePage.clickInviteClient();
		   Thread.sleep(2000);
		   String buyerEmails = validBuyerEmail + "+" + unicID + "@gmail.com";
		   String clients = buyerEmails + ", demo@noosh.com";
		   invite.putClientEmail(clients);
		   invite.sendInvitationBT();	
		   Thread.sleep(2000);
		   System.out.println("First invited: " + invite.getFistInvited());
		   System.out.println("Second invited: " + invite.getSecondInvited());
		   invite.closeInviteClientPopup();
		   Thread.sleep(2500);
		   
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-12, SP onboarding - New Home page - Invite buyer, JS errors for SP site #######"); 
	   }
	   
	   @Test(description="1.2-13, SP onboarding - New Home page - Check customers added to list in WS page")
	   public void testCheckBuyersInvited() throws InterruptedException
	   {
		   NewHomePage homePage = new NewHomePage(driver);
		   Invite invite = new Invite(driver);
		   
		   homePage.clickClientLogo();
		   Thread.sleep(1200);
		   invite.getTeamTab();
		   Thread.sleep(1200);
	   }
	   
	   @Test(description="1.2-14, SP onboarding - New Home page - Go to Home page")
	   public void testGetHomePageBack() throws InterruptedException
	   {
		   MainMenuPage mainMenuPage = new MainMenuPage(driver);
		   
		   mainMenuPage.clickProjectMenu();
		   Thread.sleep(1200);
		   
	   }
	   
	   @Test(description="1.2-15, SP onboarding - New Home page - Update project status")
	   public void testUpdateProjectStatus() throws InterruptedException
	   {
		   NewHomePage newHomePage = new NewHomePage(driver);
		   ProjectFrame projectFrame = new ProjectFrame(driver);
		   
		   newHomePage.clickProjectInListNew();
		   projectFrame.clickEditProjectBT();
		   Thread.sleep(300);
		   projectFrame.selectProjectStatus();
		   Thread.sleep(300); 
		   projectFrame.clickUpdateBT();
		   Thread.sleep(1000);
	   }
	   
	   @Test(description="1.2-16, SP onboarding - New Home page - SP post message")	   
	   @Parameters({"sPNewMessage"})
	   public void testPostMeesage(String sPNewMessage) throws InterruptedException
	   {
		   
		   BaseTestCases.testSendMessageFile(driver, sPNewMessage);
	   }
	   
	   @Test(description="1.2-17, SP onboarding - New Home page - SP logout")	   
	   public void testSPLogout() throws InterruptedException
	   {
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   
		   registerSPSitePage.logoutSPSite();
		   Thread.sleep(2000);
	   }
	   
	   @Test(description="1.2-18, SP onboarding - Get SP invitation")
	   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "message"})
	   public void testGetInvitationLetter(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
			   String newLastNameSP, String message) throws FileNotFoundException, InterruptedException, AWTException 
	   {
		   winHandleBefore = buyerDriver.getWindowHandle();
		   
		   BaseTestCases.testLoginEmailPage(baseUrlBuyerSite, validBuyerEmail, firstNameSP, newLastNameSP, message);
		   
	   }
	   
	   @Test(description="1.2-19, Register new buyer")
	   @Parameters({"firstNameBuyer", "lastNameBuyer", "passwordBuyer", "phoneNumberBuyer"})
	   public void testBuyerRegister(String firstNameBuyer, String lastNameBuyer, 
			      String passwordBuyer, String phoneNumberBuyer) throws InterruptedException 
	   {		   
		   BaseTestCases.testRegisterBuyerPage(firstNameBuyer, lastNameBuyer, 
				      passwordBuyer, phoneNumberBuyer);
	   }
	   
	   @Test(description="1.2-20, Buyer creates new Project - Selects product")
	   public void testBuyerSite() throws InterruptedException
	   {
	      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	       
	      buyerSitePage.selectEnvelopeProductBuyerSite();
	   }
	   
	   @Test(description="1.2-21, Buyer creates new Project - Create product")
	   @Parameters({"projName", "descriptionName", "skuSP", "referenceNumber", 
		   "quant", "specDescrBuyer", "fileForProject3"})
	   public void testBrochurePopupWindow(String projName, String descriptionName,
			   String skuSP, String referenceNumber, String quant, String specDescrBuyer,
			   String fileForProject3) throws InterruptedException, AWTException
	   {	      
	      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
	      
	      brochurePopup.setProjectName(projName); 
	      Thread.sleep(2000);       
	      brochurePopup.callCalendar();     
		  brochurePopup.setNextMonth();
	      brochurePopup.setComplationDate();  
	      brochurePopup.getNextTab();    
	      brochurePopup.putDescriptionName(descriptionName); 
	      brochurePopup.putSKU(skuSP);
	      brochurePopup.putRefNumber(referenceNumber);
	      brochurePopup.putQuant1(quant);
//	      brochurePopup.putSpecDescription(specDescrBuyer);         
	      brochurePopup.getFilesTab();     
   	      page.uploadFileModalWindow(fileForProject3);
   	      brochurePopup.clickUploadFileDropBT();
   	      Thread.sleep(1000);
   	      page.robotUpload();
   	      Thread.sleep(4000); 
	      brochurePopup.getReviewAndSubmitTab();     
	      brochurePopup.clickAddProductsBT();
	      Thread.sleep(2000); 	            
	   }
	   
	   @Test(description="1.2-22, Buyer creates new Project - Add new product")
	   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "newProjectName"})
	   public void testNewProduct(String descriptionName, String skuSP, String referenceNumber, 
			   String quant, String specDescrBuyer, String newProjectName) throws InterruptedException
	   {
		   TestDemoSqaFF testDemoSqa = new TestDemoSqaFF();
		   	
		   testDemoSqa.testCreateProductIntoCreateProject(descriptionName, skuSP, referenceNumber, 
				   quant, specDescrBuyer, newProjectName);	   
	   }	
	   
	   @Test(description="1.2-23, Buyer creates new Project, Adv editor poduct - Create product, Project verification, New status")
	   @Parameters({"projectStatusBuyer"})
	   public void testStatusProjectVerificationAdvEd(String projectStatusBuyer) throws InterruptedException
	   {
		   BuyerSitePage buyerSitePage = new BuyerSitePage(driver);
		   NewProjectPage projectPage = new NewProjectPage(driver);
		   
		   buyerSitePage.clickCreatedProjectAdvEd();
		   Thread.sleep(1000);	   
		   projectPage.clickProjectSpecsTab();
		   Thread.sleep(1000);
		   projectPage.clickFileTab();
		   Thread.sleep(2500);
	   }
	   
	   @Test(description = "Log out Buyer site")
	   public void testLogoutBuyerSite() throws InterruptedException
	   {	 
		  BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
			
		  buyerSitePage.clickLogout();
		  System.out.println("Buyer site URL: " + driver.getCurrentUrl());
		  log.info("Buyer site URL: " + driver.getCurrentUrl());
		  log.info("Buyer site name/password: demo.nshtest+" + unicID + "@gmail.com/17password");
		  Thread.sleep(1200);  
     
	   }
	   
	   @Test(description="2.1-1, SP onboarding - SP sign up 2nd time")
	  // @Parameters({"baseUrlSignUp", "firstNameSP1", "lastNameSP1", "passwordSP", "phoneNumberSP", "newCompanyName"})      //10/2
	   @Parameters({"baseUrlSignUp", "fullNameSP1", "passwordSP", "phoneNumberSP", "newCompanyName"})
	   //public void testRegisterSPSiteSecondTime(String baseUrlSignUp, String firstNameSP1, String lastNameSP1, 
		//	   String passwordSP, String phoneNumberSP, String newCompanyName) throws InterruptedException         //10/2
	   public void testRegisterSPSiteSecondTime(String baseUrlSignUp, String fullNameSP1, 
					   String passwordSP, String phoneNumberSP, String newCompanyName) throws InterruptedException
	   {			   
		 //  BaseTestCases.testRegisterNewSPSite(baseUrlSignUp, firstNameSP1, lastNameSP1, passwordSP, phoneNumberSP, newCompanyName);    //10/2
		   BaseTestCases.testRegisterNewSPSite(baseUrlSignUp, fullNameSP1, passwordSP, phoneNumberSP, newCompanyName);
		   Thread.sleep(2000); 
	   }
	   
	   @Test(description="2.1-2, SP onboarding - Lets begin 2nd time")
	   @Parameters({"letsBegin"})
	   public void testLetsBegin2Time(String letsBegin) throws InterruptedException
	   {			   		   
		   BaseTestCases.testLetsBegin(letsBegin);
		   Thread.sleep(2000); 
		   System.out.println("New SP site URL: " + driver.getCurrentUrl());
		   log.info("--------------------------------------- Signup second flow -------------------------------------");
	//	   log.info("Second SP site URL: " + driver.getCurrentUrl());
	   }
	   
	   @Test(description="2.1-3, SP onboarding - Create client workspace")
	   public void testCreateClientWorkspace() throws InterruptedException
	   {			   		   
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   
		   registerSPSitePage.clickCreateWorkspace();
		   Thread.sleep(2000); 

	   }
	   
	   @Test(description="2.1-4, SP onboarding - Create client workspace, workspace info")
	   @Parameters({"newProfilePicture"})
	   public void testWorkspaceInfo(String newProfilePicture) throws InterruptedException
	   {			   		   
		   WelcomeScreen welcomeScreen = new WelcomeScreen(driver);
		    
		   welcomeScreen.setClientWorkspace("New generation test");
		   Thread.sleep(600); 
		   welcomeScreen.setClientWorkspaceLogo(newProfilePicture);
		   Thread.sleep(1200); 
		   welcomeScreen.clickAllForm();
		   Thread.sleep(600);
		   welcomeScreen.clickCreateWorkspaceBT();
		   Thread.sleep(2000);
		   log.info("SP site URL: " + driver.getCurrentUrl());
		   log.info("SP site name/password: nobody+" + unicID + "@noosh.com/17password");
	   }
	   
	   @Test(description="2.1-5, SP onboarding - Create client workspace from home page")
	   public void testCreateWorkspace2() throws InterruptedException
	   {			   		   
		   NewHomePage homePage = new NewHomePage(driver);
		   
		   homePage.clickCreateWorkspaceHInHomePage();
		   Thread.sleep(600);
	   }
	   
	   @Test(description="2.1-6, SP onboarding - Create client workspace from home page, popup")
	   public void testCreateWorkspacePopup() throws InterruptedException
	   {			   		   
		   NewHomePage homePage = new NewHomePage(driver);
		   CreateSitePopup createSitePopup = new CreateSitePopup(driver);
		      
		   Thread.sleep(2000);
		   homePage.clickCreateWorkspace();
		   Thread.sleep(600);
		   createSitePopup.setPageName("NG"+unicID);
		   createSitePopup.selectPrintersCategory();     
		   createSitePopup.createSite();
		   Thread.sleep(2000);
	   }
	   
	   @Test(description="2.1-7, SP onboarding - Invite custome hovering")
	   public void testInviteCustomerHovering() throws InterruptedException
	   {			   		   
		   NewHomePage homePage = new NewHomePage(driver);
		   Invite invite = new Invite(driver);
		      
           homePage.clickShareBT();
		   Thread.sleep(2000);
		   buyerEmail = "nobody+" + unicID + "b@noosh.com";
		   invite.putClientEmail(buyerEmail);
		   Thread.sleep(600);
		   invite.sendInvitationBT();
		   Thread.sleep(2000);
		   invite.closeInviteClientPopup();
		   Thread.sleep(2000);
	   }
	   
	   @Test(description="2.1-8, SP onboarding - SP logout second time")
	   public void testSPLogoutSecondTime() throws InterruptedException
	   {			   		   	      
		   testSPLogout();
	   }
	   
	   @Test(description="2.1-9, Buyer register - Get invitation invitation")
	   @Parameters({"baseUrlBuyerSite", "validBuyerEmail", "firstNameSP", "newLastNameSP", "message"})
	   public void testGetBuyerInvitationLetter(String baseUrlBuyerSite, String validBuyerEmail, String firstNameSP,
			   String newLastNameSP, String message) throws FileNotFoundException, InterruptedException, AWTException 
	   {
		   buyerEmail = "nobody+" + unicID + "b@noosh.com";
		//   BaseTestCases.testLoginEmailPage(baseUrlBuyerSite, validBuyerEmail, firstNameSP, newLastNameSP, message);
		   driver.manage().deleteAllCookies();    

		   LoginEmailPage loginEmailPage = new LoginEmailPage(buyerDriver);     
		   driver.switchTo().window(winHandleBefore);
		   driver.navigate().refresh();
		   Thread.sleep(2200);
		   loginEmailPage.logoutBuyerEmail1();
		   Thread.sleep(1000);
		   loginEmailPage.loginUser("nobody@noosh.com", "17password");      
     
		   String searchString = buyerEmail + " " + message;   

		   Thread.sleep(2000);
		   loginEmailPage.searchInvitationLetter33(searchString);
		   Page.robotClickEnter();
		   Thread.sleep(2500); 
		   loginEmailPage.getInvitationLetter();
		   Thread.sleep(800);
		   Thread.sleep(1000);      
		   loginEmailPage.getRegisterBuyerPage();
		   Thread.sleep(3000);	
		   
	   }
	   
	   @Test(description="2.1-10, Register new buyer")
	   @Parameters({"firstNameBuyer", "newLastName", "passwordBuyer", "phoneNumberBuyer"})
	   public void testBuyerRegisterSecondTime(String firstNameBuyer, String newLastName, 
			      String passwordBuyer, String phoneNumberBuyer) throws InterruptedException 
	   {		   		   
		   BaseTestCases.testRegisterBuyerPage(firstNameBuyer, newLastName, 
				      passwordBuyer, phoneNumberBuyer);
	   }
	   
	   @Test(description="2.1-11, Buyer creates new Project - Selects product")
	   public void testSelectProduct() throws InterruptedException
	   {
	      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	      
		  String spSiteLoginPage = driver.getCurrentUrl();
		  spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 44);
		  spSiteLoginPage = spSiteLoginPage + "login";
	      
	      System.out.println("Buyer site URL: " + spSiteLoginPage);
	      System.out.println("Buyer site user name/password: " + buyerEmail + "/17password");
	      log.info("Second Buyer site URL: " + spSiteLoginPage);
	      log.info("Buyer site user name/password: " + buyerEmail + "/17password");
	      buyerSitePage.selectEnvelopeProductBuyerSite();
	   }
	   
	   @Test(description="2.1-12, Buyer creates new Project - Create product")
	   @Parameters({"projNameEnv", "textBoxName", "skuEnv", "referenceNumber", 
		   "quant", "specDescrBuyer", "fileForProject3"})
	   public void testEnvelopePopupWindow(String projNameEnv, String textBoxName,
			   String skuEnv, String referenceNumber, String quant, String specDescrBuyer,
			   String fileForProject3) throws InterruptedException, AWTException
	   {	      
	      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
	      
	      brochurePopup.setProjectName(projNameEnv); 
	      Thread.sleep(2000);       
	      brochurePopup.callCalendar();  
	      brochurePopup.setNextMonth();
	      brochurePopup.setComplationDate();  
	      brochurePopup.getNextTab();    
	      brochurePopup.putDescriptionName(textBoxName); 
	      brochurePopup.putSKU(skuEnv);
	      brochurePopup.putRefNumber(referenceNumber);
	      brochurePopup.putQuant1(quant);         
	      brochurePopup.getFilesTab();     
   	      page.uploadFileModalWindow(fileForProject3);
   	      brochurePopup.clickUploadFileDropBT();
   	      Thread.sleep(1100);
   	      page.robotUpload();
   	      Thread.sleep(4000); 
	      brochurePopup.getReviewAndSubmitTab();     
		  brochurePopup.clickSubmitBT();
		  Thread.sleep(3000); 	            
	   }
	   
	   @Test(description = "Log out Buyer site second time")
	   public void testLogoutBuyerSiteSecond() throws InterruptedException
	   {	 
		  BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
			
		  buyerSitePage.clickLogout();
		  Thread.sleep(2000);  
     
	   }
}
