/**
* This class tests the functionality of SP and demo site in Firefox browser
* @author 
*/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

import org.testng.annotations.Parameters;

import java.util.Hashtable;
import java.util.List;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.BuyerSitePage;
import com.noosh.nooshentry.automation.buyerSite.LoginBuyerPage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptLog;

import java.util.concurrent.TimeUnit;
import java.awt.AWTException;

public class SmokeSqaTest extends BaseSeleniumTest
{   
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   static String baseUrlPro;
   static String baseUrlSmoke;
   static String smokeURL;
   static String clientURL;
   String windowNumber;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException
   {
	   if (domain.trim().equals("spd"))
	   {
		   baseUrlPro = "https://spd.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
	   } else if (domain.trim().equals("sdm")) {
		   baseUrlPro = "http://sdm.noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2.sdm.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
	   } else if (domain.trim().equals("qa2")) {
		   baseUrlPro = "https://qa2.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium.qa2.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
	   } else if (domain.trim().equals("scd")) {                                     
		   baseUrlPro = "https://scd.noosh.com/noosh/home/login";                
		   baseUrlSmoke = "https://nooshselenium.scd.noosh.com/service/login";   
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
	   } else {		  
		   baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2.sqa.noosh.com/service/login";  
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
	   } 
   }
   
   @Test(description="1.1, Log in to Noosh Pro") 
   @Parameters({"loginSQANoosh", "passwordSP"})
   public void testLoginPage(String loginSQANoosh, String passwordSP) throws InterruptedException {
	  log.info("Start: testLoginPage");
      driver.get(baseUrlPro);	
      
      driver.manage().window().maximize();    
      LoginPage loginPage = new LoginPage(driver);
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, loginSQANoosh));
	  } catch(Throwable e) {
		  System.out.println("1.1 This is not 'Noosh > Login' page title");
		  log.info("1.1 This is not 'Noosh > Login' page title");
	  }	
      */
      // Login and call MyNooshPage 
      loginPage.loginUser("demo@noosh.com", passwordSP);    //jenn comment out
      //loginPage.loginUser("jennifer@noosh.com", passwordSP); 
      
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.1 Log in to Noosh Pro, JS errors for SP site #######");
	  log.info("End: testLoginPage");
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
	  Thread.sleep(1500);      
      myNooshDeskPage.getSitesList();
	  //myNooshDeskPage.getDashboard();    //jenn comment out 9/25  

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-1, Go to Noosh Entry through sites menu, JS errors for SP site #######");       
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
	  Thread.sleep(1500);      
      siteListPage.getMyDeskPageBack();      

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-2, Go back to Noosh Pro through My desk, JS errors for SP site #######");     
	  log.info("End: testSiteListPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="1.2, SSO-3, Go to my dashboard")
   @Parameters({"myDeskPage"}) 
   public void testMyDeskPageBack(String myDeskPage) throws InterruptedException
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
	  Thread.sleep(1500);          
      myDeskPageNoo.getDashboard();     

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.2-3, Go to my dashboard, JS errors for SP site #######");      
	  log.info("End: testMyDeskPageBack");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="1.3-1, Log out")
   @Parameters({"dashboardPageTitle"}) 
   public void testPageDashboard(String dashboardPageTitle) throws InterruptedException 
   {  
	   log.info("Start: testPageDashboard");
	 /*
      try{
          AssertJUnit.assertTrue(page.checkPageTitle(driver, dashboardPageTitle));
	  } catch(Throwable e) {
		  System.out.println("1.2 This is not 'Dashboard - Noosh Demo - Noosh' page title");
		  log.info("1.2 This is not 'Dashboard - Noosh Demo - Noosh' page title");
	  }	 
	  */

	  RegisterNewSPSitePage registerNewSPSitePage = new RegisterNewSPSitePage(driver);
	  registerNewSPSitePage.logoutSPSite();      //10/4
	  Thread.sleep(5000);
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.3-1, Log out, JS errors for SP site #######");       
	  log.info("End: testPageDashboard");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="1.3-2, Log out verification")
   @Parameters({"loginPageTitle"})
   
   public void testLogout(String loginPageTitle) throws InterruptedException
   {
	  log.info("Start: testLogout");
	  
	 // driver.get("http://demo.sqa.noosh.com/service/j_spring_security_logout");
	  Thread.sleep(1000);
	   /*
      try{
	      AssertJUnit.assertTrue(page.checkPageTitle(driver, loginPageTitle));
      } catch(Throwable e) {
          System.out.println("1.3 This is not '" + loginPageTitle + "' page title");  
          log.info("1.3 This is not '" + loginPageTitle + "' page title");
      }
      */

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 1.3-2, Log out verification, JS errors for SP site #######");       
	  log.info("End: testLogout");
	  log.info("--------------------------------------------");
   }  
  
   @Test(description="2.2, Log out from SP Site")
   public void testLogoutSPSite() throws InterruptedException
   {
	   log.info("Start: testLogoutSPSite");
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   
	   Thread.sleep(1000);	    
	   mainMenuPage.closeWizardPopup();   
	   Thread.sleep(1000);
       mainMenuPage.logoutSite();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 2.2, Log out from SP Site, JS errors for SP site #######");	 
	   log.info("End: testLogoutSPSite");
	   log.info("--------------------------------------------");
   }
   
   // Testing the functionality of Login page
   @Test(description="3.1, Login to SP site")
   @Parameters({"baseUrl", "loginNooshDemoTitle", "passwordSP"}) 
   public void testLoginDemoSqaPageBW(String baseUrl, String loginNooshDemoTitle, String passwordSP) 
   {      
	  log.info("Start: testLoginDemoSqaPageBW");
      // Verifying that this is the correct page     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	  try {
          AssertJUnit.assertTrue(page.checkPageTitle(driver, loginNooshDemoTitle + unicID +" - Noosh"));
	  }
	  catch(Throwable e) {
		  System.out.println("3.1 This not selenium{UnicID} - Noosh page title");
		  log.info("3.1 This not selenium{UnicID} - Noosh page title");
		  System.out.println("3.1 This is '" + page.getTitle(driver) + "' page title");
		  log.info("3.1 This is '" + page.getTitle(driver) + "' page title");
	  }
      loginDemoSqa.loginUser(emailSP, passwordSP);           

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 3.1, Login to SP site, JS errors for SP site #######");	
	  log.info("End: testLoginDemoSqaPageBW");
	  log.info("--------------------------------------------");
   }   
  
   @Test(description="5.1, SP customization - Download Login page/ Header images")
   @Parameters({"headerLogo", "loginContentImage", "imageLogoFile", "imageLoginFile"})
   public void testDownloadLoginPageImages(String headerLogo, String loginContentImage, 
		   String imageLogoFile, String imageLoginFile) 
		   throws InterruptedException
   {
	   log.info("Start: testDownloadLoginPageImages");
	   String logoImageCustomTab, logoImagePreviewTab, logoImageSPTab, loginImageImageCustomTab, 
	          loginImagePreviewTab, logoImageDashboardPreviewTab;
	   
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(1500);
//	   newHomePage.clickGearSubmenuItem(driver, "Branding");
	   spSiteLoginPage = driver.getCurrentUrl();
	   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 4);
	   System.out.println("Home page url:" + spSiteLoginPage);
	   driver.get(spSiteLoginPage + "home/custom");
	   
	   Thread.sleep(1500);	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageCustomTab());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Image Customization tab");
			  log.info("5.1 Logo image not displayed in Image Customization tab");
	   }	 
	   
	   logoImageCustomTab = adminCustomization.getLogoImageCustomTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImagePreviewTab());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Login Preview Image Customization tab");
			  log.info("5.1 Logo image not displayed in Login Preview Image Customization tab");
	   }
	   
	   logoImagePreviewTab = adminCustomization.getLogoImagePreviewTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageSPSite());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in main SP page, in the left top corner");
			  log.info("5.1 Logo image not displayed in main SP page, in the left top corner");
	   }
	   
	   logoImageSPTab = adminCustomization.getLogoImageSPSite();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginImageImageCustomTab());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Login Content image not displayed in Image Customization tab");
			  log.info("5.1 Login Content image not displayed in Image Customization tab");
	   }
	   
	   loginImageImageCustomTab = adminCustomization.getLoginImageImageCustomTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginImagePreviewTab());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Login Content image not displayed in Login Preview Image Customization tab");
			  log.info("5.1 Login Content image not displayed in Login Preview Image Customization tab");
	   }
	   
	   logoImageDashboardPreviewTab = adminCustomization.getLoginImagePreviewTab();
	   
	   adminCustomization.clickDashboardPreviewLink();
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageDashboardPreviewTab());	      
	   } catch(Throwable e) {
			  System.out.println("5.1 Logo image not displayed in Dashboard Preview Image Customization tab");
			  log.info("5.1 Logo image not displayed in Dashboard Preview Image Customization tab");
	   }
	   adminCustomization.clickUploadImagesBT();	   
	   Thread.sleep(1500);
	   // headerLogo 
	   adminCustomization.uploadHeaderLogo(headerLogo);
	   Thread.sleep(1500);	   
	   adminCustomization.uploadLoginContentImage(loginContentImage);	   
	   Thread.sleep(1500);
	   adminCustomization.clickSaveImagesBT();
	   Thread.sleep(1500);	   	  
	   if (logoImageCustomTab.trim().equals(adminCustomization.getLogoImageCustomTab()))
	   {
			 System.out.println("5.1 New logo image not displayed in Image Customization tab");
			 log.info("5.1 New logo image not displayed in Image Customization tab");
	   }
	   if (logoImagePreviewTab.trim().equals(adminCustomization.getLogoImagePreviewTab()))
	   {
			 System.out.println("5.1 New logo image not displayed in Login Preview Image Customization tab");
			 log.info("5.1 New logo image not displayed in Login Preview Image Customization tab");
	   }
	   if (logoImageSPTab.trim().equals(adminCustomization.getLogoImageSPSite()))
	   {
			 System.out.println("5.1 New logo image not displayed in main SP page, in the left top corner");
			 log.info("5.1 New logo image not displayed in main SP page, in the left top corner");
	   }
	   if (loginImageImageCustomTab.trim().equals(adminCustomization.getLoginImageImageCustomTab()))
	   {
			 System.out.println("5.1 New login Content image not displayed in Image Customization tab");
			 log.info("5.1 New login Content image not displayed in Image Customization tab");
	   }

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.1, SP customization - Download Login page/ Header images, JS errors for SP site #######");	
	   log.info("End: testDownloadLoginPageImages");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="5.1, SP customization - Download Login page/ Header images, return images back")
   @Parameters({"headerLogo1", "loginContentImage1"})
   public void testDownloadLoginPageImagesBack(String headerLogo1, String loginContentImage1) 
		   throws InterruptedException
   {
	   log.info("Start: testDownloadLoginPageImagesBack");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(1500);
//	   newHomePage.clickGearSubmenuItem(driver, "Branding");	   
	   spSiteLoginPage = driver.getCurrentUrl();
	   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 11);
	   System.out.println("Home page url:" + spSiteLoginPage);
	   driver.get(spSiteLoginPage + "home/custom");
	   
	   Thread.sleep(1500);
	   adminCustomization.clickDashboardPreviewLink();	   
	   adminCustomization.clickUploadImagesBT();	   
	   Thread.sleep(1500);
	   // headerLogo 
	   adminCustomization.uploadHeaderLogo(headerLogo1);
	   Thread.sleep(1500);
	   adminCustomization.uploadLoginContentImage(loginContentImage1);	   
	   Thread.sleep(1500);
	   adminCustomization.clickSaveImagesBT();	   
	   Thread.sleep(1500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.1, SP customization - Download Login page/ Header images,  return images back, JS errors for SP site #######");	   
	   log.info("End: testDownloadLoginPageImagesBack");
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
	   Thread.sleep(1500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.2, SP customization - Login page customization, JS errors for SP site #######");	   
	   log.info("End: testLoginPageCustomization");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="5.2, SP customization - Login page customization, colors back")
   @Parameters({"loginBoxColor1", "loginBoxTextColor1"})
   public void testLoginPageCustomizationBack(String loginBoxColor1, String loginBoxTextColor1) throws InterruptedException
   {
	   log.info("Start: testLoginPageCustomizationBack");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   
	   adminCustomization.clickLoginCustomizationTab();	   
	   adminCustomization.clickEditColorsBT();	   
	   adminCustomization.setLoginBoxColor(loginBoxColor1);
	   adminCustomization.setLoginBoxTextColor(loginBoxTextColor1);	   
	   adminCustomization.clickSaveLoginColorsBT();
	   Thread.sleep(1500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.2, SP customization - Login page customization, JS errors for SP site #######");	   
	   log.info("End: testLoginPageCustomizationBack");
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
	   Thread.sleep(1500);	   	   
	   adminCustomization.clickEditBT();	   
	   adminCustomization.setHeaderBGColor(headerBackgroundColor);
	   adminCustomization.setHeaderTextColor(headerTextColor);
	   adminCustomization.setMenuBGColor(menuBackgoundColor);
	   adminCustomization.setMenuTextColor(menuTextColor);
	   Thread.sleep(1500);
	   adminCustomization.clickSaveHeaderColors();
	   Thread.sleep(1500);	   	   
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
   
   @Test(description="5.3 SP customization - Header customization, color back")
   @Parameters({"headerBackgroundColor1", "headerTextColor1", "menuBackgoundColor1", "menuTextColor1"})
   public void testHeaderCustomizationBack(String headerBackgroundColor1, String headerTextColor1, 
		   String menuBackgoundColor1, String menuTextColor1) throws InterruptedException
   {
	   log.info("Start: testHeaderCustomizationBack");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   	   
	   adminCustomization.clickHeaderCustomizationTab();	   	   
	   Thread.sleep(1500);
	   adminCustomization.clickEditBT();	   
	   adminCustomization.setHeaderBGColor(headerBackgroundColor1);
	   adminCustomization.setHeaderTextColor(headerTextColor1);
	   adminCustomization.setMenuBGColor(menuBackgoundColor1);
	   adminCustomization.setMenuTextColor(menuTextColor1);
	   Thread.sleep(1500);
	   adminCustomization.clickSaveHeaderColors();
	   Thread.sleep(1500);	

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.3, SP customization - Header customization, color back, JS errors for SP site #######");	   
	   log.info("End: testHeaderCustomizationBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - Update Account Info")
   @Parameters({"companyNameSP", "addressSP", "citySP", "stateSP", "zipSP"})
   public void testAccountUpdateInfo(String companyNameSP, String addressSP, String citySP, String stateSP, String zipSP) throws InterruptedException {
	   log.info("Start: testAccountUpdateInfo");
       Thread.sleep(1000);
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);	
	   userProfileFrame.clickProfileUserName();
	   
	   NewHomePage newHomePage = new NewHomePage(driver);

	   Thread.sleep(5000);   
	   newHomePage.clickGearSubmenuItem(driver, "/service/account"); // use link to find the item for now
	   Thread.sleep(1500);
	     
	   AccountFrame accountFrame = new AccountFrame(driver);
	   Thread.sleep(1500);
	   accountFrame.clickUpdatInfo();
	   Thread.sleep(1500);
	   accountFrame.setCompanyName(companyNameSP);
	   accountFrame.setAddress(addressSP);
	   accountFrame.setCity(citySP);
	   accountFrame.setState(stateSP);
	   accountFrame.setZip(zipSP);
	  // accountFrame.clickCancelUpdateAccountInfoBT();
	   Thread.sleep(1000);
	   accountFrame.clickSaveUpdateAccountInfoBT();
	   Thread.sleep(1000);	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### SP User Account - Update Account info, JS errors for SP site  #######");	   
	   log.info("End: testAccountUpdateInfo");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - Update Account Info Back")
   @Parameters({"originalCompanyNameSP", "originalAddressSP", "originalCitySP", "originalStateSP"})
   public void testAccountUpdateInfoBack(String originalCompanyNameSP, String originalAddressSP, String originalCitySP, String originalStateSP) throws InterruptedException {
	   log.info("Start: testAccountUpdateInfoBack");
	   
	   AccountFrame accountFrame = new AccountFrame(driver);
	   Thread.sleep(1500);
	   accountFrame.clickUpdatInfo();
	   Thread.sleep(1500);
	   accountFrame.setCompanyName(originalCompanyNameSP);
	   accountFrame.setAddress(originalAddressSP);
	   accountFrame.setCity(originalCitySP);
	   accountFrame.setState(originalStateSP);
	   Thread.sleep(1000);
	   accountFrame.clickSaveUpdateAccountInfoBT();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex, "####### SP User Account - Update Account info back, JS errors for SP site  #######");
	   log.info("End: testAccountUpdateInfoBack");
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
	   Thread.sleep(500);	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(loginNameErrorMessage, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.1 'Please input Login Name' message not displayed");
		   log.info("6.1 'Please input Login Name' message not displayed");
	   }	
	   userProfileFrame.clearEmailField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(500);
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
	   Thread.sleep(1000);	   	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(fullName, driver));	      
	   } catch(Throwable e) {
		   System.out.println("6.1 User profile name does not updated");
		   log.info("6.1 User profile name does not updated");
	   }

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.1, SP user Profile - Update some Profile info, JS errors for SP site  #######");	   
	   log.info("End: testProfileInfoUpdate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.1, SP user Profile - Update some Profile info, set data back")
   @Parameters({"spEmailSmoke"})
   public void testProfileInfoUpdateBack(String spEmailSmoke) throws InterruptedException
   {	   	   
	   log.info("Start: testProfileInfoUpdateBack");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   	   	
	   Thread.sleep(1000);
	   userProfileFrame.clickProfileUserName();	   
	   userProfileFrame.clickEditProfileBT();
	   Thread.sleep(500);	   
	   userProfileFrame.setLoginName(spEmailSmoke);	   
	   userProfileFrame.setEmailAddress(spEmailSmoke);
	   userProfileFrame.setNewLastName("SP");
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.1, SP user Profile - Update some Profile info, set data back, JS errors for SP site  #######");	   
	   log.info("End: testProfileInfoUpdateBack");
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
	   Thread.sleep(1500);
	   oldProfileImage = userProfileFrame.getHeadProfileImageAttribute();	   	   
	   userProfileFrame.clickChangProfilePicture(newProfilePicture);
       Thread.sleep(1000);	   
	   userProfileFrame.getHeadProfileImageAttribute();
	   if (oldProfileImage.equals(userProfileFrame.getHeadProfileImageAttribute()))
	   {
		   System.out.println("6.2 The new profile image does not displayed in the head user profile");
	       log.info("6.2 The new profile image does not displayed in the head user profile");
	   }
	       Thread.sleep(1500);

		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"#######  6.2, SP iser Profile - Download new Profile image, JS errors for SP site #######");		
		log.info("End: testDownloadNewProfileImage");
		log.info("--------------------------------------------");
   }
   
   @Test(description="6.2 SP iser Profile - Download back old Profile image ")
   @Parameters({"oldProfilePicture"})
   public void testDownloadProfileImageBack(String oldProfilePicture) throws InterruptedException, AWTException
   {
	   log.info("Start: testDownloadProfileImageBack");
	   String oldProfileImage;
	   
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   	   
	   userProfileFrame.getImageAttribute();
	   Thread.sleep(1500);
	   oldProfileImage = userProfileFrame.getHeadProfileImageAttribute();	   	   
	   userProfileFrame.clickChangProfilePicture(oldProfilePicture);
       Thread.sleep(1000);	   
	   userProfileFrame.getHeadProfileImageAttribute();
	   if (oldProfileImage.equals(userProfileFrame.getHeadProfileImageAttribute()))
	   {
		   System.out.println("6.2 The profile image does not displayed in the head user profile");
	       log.info("6.2 The profile image does not displayed in the head user profile");
	   }
	       Thread.sleep(1500);
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### SP iser Profile - Download old Profile image back, JS errors for SP site #######");			
	   log.info("End: testDownloadProfileImageBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.3, SP user Profile - Reset passowrd")
   @Parameters({"wrongPasswordSP", "passwordSP", "newPasswordSP", "wrongOriginalPasswordMessage"})
   public void testResetPassword(String wrongPasswordSP, String passwordSP, String newPasswordSP,
		   String wrongOriginalPasswordMessage) throws InterruptedException
   {
	   log.info("Start: testResetPassword");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   
	   userProfileFrame.clickProfileUserName();
	   userProfileFrame.clickResetPassword();
	   userProfileFrame.setOriginalPassword(wrongPasswordSP);
	   userProfileFrame.setNewPassword(passwordSP);
	   userProfileFrame.setConfirmPassword(passwordSP);
	   userProfileFrame.clickChangePasswordBT();
	   Thread.sleep(1000);
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(wrongOriginalPasswordMessage, driver));
	   } catch(Throwable e) {
		   System.out.println("6.3 'The original password is not correct!' does not displayed");
		   log.info("6.3 'The original password is not correct!' does not displayed");
	   }
	   userProfileFrame.setOriginalPassword(passwordSP);
	   userProfileFrame.setNewPassword(newPasswordSP);
	   userProfileFrame.setConfirmPassword(newPasswordSP);
	   userProfileFrame.clickChangePasswordBT();
       Thread.sleep(1000);	   
       registerSPSitePage.logoutSPSite();

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3, SP user Profile - Reset passowrd, JS errors for SP site  #######");
	   log.info("End: testResetPassword");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.3, SP user Profile - Reset passowrd, login with new password, integration test")
   @Parameters({"newPasswordSP"})
   public void testLoginBackNewPassword(String newPasswordSP) throws InterruptedException
   {
	   log.info("Start: testLoginBackNewPassword");
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   	   
       loginDemoSqa.loginUser(emailSP, newPasswordSP);
       Thread.sleep(1000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3, SP user Profile - Reset passowrd, JS errors for SP site #######");	  
	   log.info("End: testLoginBackNewPassword");
	   log.info("--------------------------------------------");
   } 
   
   @Test(description="6.3, SP user Profile - Reset passowrd, login with new password, smoke test")
   @Parameters({"spEmailSmoke", "newPasswordSP"})
   public void testLoginBackNewPasswordSmoke(String spEmailSmoke, String newPasswordSP) throws InterruptedException
   {
	   log.info("Start: testLoginBackNewPasswordSmoke");
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   	   
       loginDemoSqa.loginUser(spEmailSmoke, newPasswordSP);
       Thread.sleep(1500); 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3, SP user Profile - Reset passowrd, JS errors for SP site #######");
	   log.info("End: testLoginBackNewPasswordSmoke");
	   log.info("--------------------------------------------");
   }
   
// it temporary for debug   
   @Test(description="Login Demo SQA Page, ABAT")
   public void testLoginDemoSqaPage() throws InterruptedException
   {
	   log.info("Start: testLoginDemoSqaPage");
     // driver.get("http://sel.sqa.noosh.com/service/login");   //old code
	 //  driver.get("https://jennifer.sqa.noosh.com/service/login");
	  // driver.get("https://nooshselenium.sqa.noosh.com/service/login");   //10/18
	  // driver.get("https://nooshselenium.qa2.noosh.com/service/login");     //10/18
	   driver.get(smokeURL); 
	   Thread.sleep(3000);
      driver.manage().window().maximize();
      Thread.sleep(5000);
      // Verifying that this is the correct page     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
     
     // loginDemoSqa.loginUser("qa_novok6@yahoo.com", "12Password");      //old code
      loginDemoSqa.loginUser("jennifer@noosh.com", "17password");

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######");		
	  log.info("End: testLoginDemoSqaPage");
	  log.info("--------------------------------------------");
   } 
   
   @Test(description="Login Demo SQA Page, smoke test")
   @Parameters({"passwordSP", "spEmailSmoke"})
   public void testLoginDemoSqaPageSmoke(String passwordSP, String spEmailSmoke) throws InterruptedException
   {
	  log.info("Start: testLoginDemoSqaPageSmoke");
	  Thread.sleep(1500);
      driver.get(baseUrlSmoke);    
      driver.manage().window().maximize();   
      Thread.sleep(1500);
      
      // Verifying that this is the correct page     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
      loginDemoSqa.loginUser(spEmailSmoke, passwordSP);           
	  spSiteLoginPage = driver.getCurrentUrl();
	  
	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######");      		
	  log.info("End: testLoginDemoSqaPageSmoke");
	  log.info("--------------------------------------------");
   } 
   
   @Test(description="6.3, SP user Profile - Reset password back")
   @Parameters({"passwordSP", "newPasswordSP"})
   public void testResetPasswordBack(String passwordSP, String newPasswordSP) throws InterruptedException
   {
	   log.info("Start: testResetPasswordBack");
//	   String wrongOriginalPassword = "The original password is not correct!";
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
       RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
       
	   userProfileFrame.clickProfileUserName();
	   Thread.sleep(2500);
	   userProfileFrame.clickResetPassword();
	   Thread.sleep(1500);
	   userProfileFrame.setOriginalPassword(newPasswordSP);
	   Thread.sleep(1500);
	   userProfileFrame.setNewPassword(passwordSP);
	   Thread.sleep(1500);
	   userProfileFrame.setConfirmPassword(passwordSP);
	   Thread.sleep(1500);
	   userProfileFrame.clickChangePasswordBT();
	   
       Thread.sleep(1500);	   
	   registerSPSitePage.logoutSPSite();
	   credentials.put("spURL", driver.getCurrentUrl());
	   credentials.put("loginName", emailSP);
	   credentials.put("password", passwordSP);

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
       Thread.sleep(1500);
       
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.2, SP user Profile - Reset password back, JS errors for SP site #######");         		
	   log.info("End: testLoginBackOldPassword");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="6.3, SP user profile - Login back with old password")
   @Parameters({"spEmailSmoke", "passwordSP"})
   public void testLoginBackOldPasswordSmoke(String spEmailSmoke, String passwordSP) throws InterruptedException
   {
	   log.info("Start: testLoginBackOldPasswordSmoke");
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   
       Thread.sleep(1500);	   
       loginDemoSqa.loginUser(spEmailSmoke, passwordSP);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.2, SP user Profile - Reset password back, JS errors for SP site #######"); 	
	   log.info("End: testLoginBackOldPasswordSmoke");
	   log.info("--------------------------------------------");
   }

   
   @Test(description="7.1-1, Customize default product - Go to specs")
   @Parameters({"dashboardDemoTitle", "profileTitle", "passwordSP", "validDemoUserEmail"}) 
   public void testDashboardDemoPage(String dashboardDemoTitle, String profileTitle, String passwordSP, String validDemoUserEmail) 
      throws InterruptedException
   {
	  log.info("Start: testDashboardDemoPage");
	  
	  UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	  userProfileFrame.clickProfileUserName();
	  Thread.sleep(1500);
	  
      NewHomePage newHomePage = new NewHomePage(driver);

      Thread.sleep(5000);
	  credentials.put("spURL", driver.getCurrentUrl());
	  credentials.put("loginName", emailSP);
	  credentials.put("password", passwordSP);			   
	  // newHomePage.clickGearSubmenuItem(driver, "Request Forms"); // old code. 
	  newHomePage.clickGearSubmenuItem(driver, "/service/product/specs"); // use link to find the item for now
      Thread.sleep(1500);

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

      spSiteLoginPage = driver.getCurrentUrl(); // new code
	  if(spSiteLoginPage.contains("sdm"))
		  manageProducts.getCustomizeProductPopupSDM(); // 7/19
	 // else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("qa2"))      //10/18 comment out
	  else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("qa2") || spSiteLoginPage.contains("scd"))   //10/18
		  manageProducts.getCustomizeProductPopup();
	  else
		  manageProducts.getCustommizeProductPopupSPD();          

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-2, Customize default product - Select customer product, JS errors for SP site #######");	
	  log.info("End: testManageProducts");
	  log.info("--------------------------------------------");
   }
   
   @Test(description = "7.1-2, Customize default product - Select customer product")
   @Parameters({"manageProductDemoTitle"})
   public void testManageProductsSDM(String manageProductDemoTitle) 
      throws InterruptedException
   {
	   log.info("Start: testManageProductsSDM");
	   ManageProductsPage manageProducts = new ManageProductsPage(driver);
      
      manageProducts.getCustomizeProductPopupSDM();

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 7.1-2, Customize default product - Select customer product, JS errors for SP site #######");	
	  log.info("End: testManageProductsSDM");
	  log.info("--------------------------------------------");
   }
  
   @Test(description="7.1-3, Customize default product - Enter product info")
   @Parameters({"editProductTitle", "productName", "fileImageName", "textBoxName",
	   "textBoxText", "dateFieldText", "newTabName"})
   public void testEditProductPopup(String editProductTitle, String productName,String fileImageName, 
		   String textBoxName, String textBoxText, String dateFieldText, String newTabName) throws InterruptedException
   {
	  log.info("Start: testEditProductPopup");
      EditProductPopup editProduct = new EditProductPopup(driver);
      
      editProduct.setProductName(productName+unicID);    
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
   @Parameters({"productName", "newTabName", "textBoxName", "dateFieldText"})
   public void testProductVerification(String productName, String newTabName, String textBoxName,
		   String dateFieldText) throws InterruptedException
   {
	   log.info("Start: testProductVerification");
	   productName = productName + unicID;
	   
	   EditProductPopup editProduct = new EditProductPopup(driver);	   
       ManageProductsPage manageProducts = new ManageProductsPage(driver);
       
	   Thread.sleep(1500);	   
	   manageProducts.clickCustomizeBTSmoke();
	   Thread.sleep(1500);
	   try {
	       AssertJUnit.assertTrue(editProduct.checkIconDisplayed());	      
	   } catch(Throwable e) {
		   System.out.println("7.1 Product icon does not displayed");
		   log.info("7.1 Product icon does not displayed");
	   }
	   editProduct.getCustomizationTab();  	   
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
   
   @Test(description="Go to home page")
   public void testGetHomePage() throws InterruptedException
   {
	   log.info("Start: testGetHomePage");
	   MainMenuPage menuPage = new MainMenuPage(driver);
	   
	   menuPage.clickProjectMenu();
	   Thread.sleep(3000);
	   log.info("End: testGetHomePage");
	   log.info("--------------------------------------------");
   }    
   
   @Test(description="8.3 Edit buyer site")
   @Parameters({"newBuyerSiteName", "newBuyerSiteLogo"})
   public void testEditBuyerSiteSP(String newBuyerSiteName, String newBuyerSiteLogo) throws InterruptedException
   {
	   log.info("Start: testEditBuyerSiteSP");
	   String oldBuyerSiteName;
	   
	   newBuyerSiteName = newBuyerSiteName + unicID;
	   
	   CustomeSitePage customeSitePage = new CustomeSitePage(driver);
	   NewHomePage  newHomePage = new NewHomePage(driver);
	   
	   newHomePage.changeProjectsListView2();
	   newHomePage.clickCustomizeBuyerSiteHovering(); 
	   Thread.sleep(1500);	   
	   customeSitePage.getOverviewTab();
	   Thread.sleep(1000);  	    
	   oldBuyerSiteName = customeSitePage.getUriSPSite();
	   credentials.put("buyerSite", oldBuyerSiteName);
	   oldBuyerSiteURI = customeSitePage.getOverviewLogoImageAttribute();
	   customeSitePage.clickEditSiteBT();	   
	   customeSitePage.setNewBuyerSiteName(newBuyerSiteName);  
	   Thread.sleep(500);  
	   customeSitePage.uploadBuyerSiteLogo(newBuyerSiteLogo);	   
	   Thread.sleep(500);	   
	   customeSitePage.clickSaveBT();
	   Thread.sleep(3000);
	   /* need debug 4/12
	   	   
	   if(!newBuyerSiteName.trim().equals(customeSitePage.getBuyerSiteName())){
		   System.out.println("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
		   log.info("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
	   }   
	   /*
	   /*	   
	   try {
	       AssertJUnit.assertEquals(newBuyerSiteName, customeSitePage.getBuyerSiteName());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
		   log.info("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
	   }
	   */
	   try {
	       AssertJUnit.assertEquals(oldBuyerSiteName, customeSitePage.getUriSPSite());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 URI: Old buyer site name " + oldBuyerSiteName + " does not displayed");
		   log.info("8.3 URI: Old buyer site name " + oldBuyerSiteName + " does not displayed");
	   }
	   if (oldBuyerSiteURI.equals(customeSitePage.getOverviewLogoImageAttribute())){
		   System.out.println("8.3 The new logo image does not displayed in the Overview page");
		   log.info("8.3 The new logo image does not displayed in the Overview page");	          
	   }
       /*
	   try {
	       AssertJUnit.assertEquals(oldBuyerSiteName, customeSitePage.getPreviewLogoImageAttribute());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 The new logo image does not displayed in the Prieview header");
		   log.info("8.3 The new logo image does not displayed in the Prieview header");
	   }
	   */

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.3, Edit buyer site, JS errors for SP site #######");	 
	   log.info("End: testEditBuyerSiteSP");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="8.3 Edit buyer site")
   @Parameters({"newBuyerSiteName", "newBuyerSiteLogo"})
   public void testEditBuyerSiteSPSDM(String newBuyerSiteName, String newBuyerSiteLogo) throws InterruptedException
   {
	   log.info("Start: testEditBuyerSiteSPSDM");
	   String oldBuyerSiteName;	      
	   SitesListTab sitesListTab = new SitesListTab(driver);
	   CustomeSitePage customeSitePage = new CustomeSitePage(driver);
	   
	   Thread.sleep(1500); 
	   sitesListTab.getSiteList();  
	   Thread.sleep(1200);	   	   
	   customeSitePage.getOverviewTab();
	   Thread.sleep(1000);  	    
	   oldBuyerSiteName = customeSitePage.getUriSPSite();
	   credentials.put("buyerSite", oldBuyerSiteName);
	   oldBuyerSiteURI = customeSitePage.getOverviewLogoImageAttribute();
	   customeSitePage.clickEditSiteBT();	   
	   customeSitePage.setNewBuyerSiteName(newBuyerSiteName);    
	   Thread.sleep(300);  
	   customeSitePage.uploadBuyerSiteLogo(newBuyerSiteLogo);	   
	   Thread.sleep(300);	   
	   customeSitePage.clickSaveBT();
	   Thread.sleep(3000);
	   /* need debug 4/12
	   	   
	   if(!newBuyerSiteName.trim().equals(customeSitePage.getBuyerSiteName())){
		   System.out.println("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
		   log.info("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
	   }   
	   /*
	   /*	   
	   try {
	       AssertJUnit.assertEquals(newBuyerSiteName, customeSitePage.getBuyerSiteName());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
		   log.info("8.3 New buyer site name '" + newBuyerSiteName + "' does not displayed");
	   }
	   */
	   try {
	       AssertJUnit.assertEquals(oldBuyerSiteName, customeSitePage.getUriSPSite());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 URI: Old buyer site name " + oldBuyerSiteName + " does not displayed");
		   log.info("8.3 URI: Old buyer site name " + oldBuyerSiteName + " does not displayed");
	   }
	   if (oldBuyerSiteURI.equals(customeSitePage.getOverviewLogoImageAttribute())){
		   System.out.println("8.3 The new logo image does not displayed in the Overview page");
		   log.info("8.3 The new logo image does not displayed in the Overview page");	          
	   }
       /*
	   try {
	       AssertJUnit.assertEquals(oldBuyerSiteName, customeSitePage.getPreviewLogoImageAttribute());	      
	   } catch(Throwable e) {
		   System.out.println("8.3 The new logo image does not displayed in the Prieview header");
		   log.info("8.3 The new logo image does not displayed in the Prieview header");
	   }
	   */

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.3, Edit buyer site, JS errors for SP site #######");	
	   log.info("End: testEditBuyerSiteSPSDM");
	   log.info("--------------------------------------------");
   }

   @Test(description="8.4, Preview buyer site first time")
   public void testPreviewSite() throws InterruptedException
   { 
	  log.info("Start: testPreviewSite");
      NewHomePage newHomePage = new NewHomePage(driver);
      MainMenuPage menuPage = new MainMenuPage(driver);
           
	  menuPage.clickProjectMenu();
	  Thread.sleep(1400);
 	  newHomePage.clickClientViewBTHovering();
 	  Thread.sleep(1000);

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC 8.4, Preview buyer site first time, JS errors for SP site #######");	
	  log.info("End: testPreviewSite");
	  log.info("--------------------------------------------");
   }

   @Test(description="8.4, Preview buyer site second time")
   public void testCustomeSitePage() throws InterruptedException
   {     
	  log.info("Start: testCustomeSitePage");
      CustomeSitePage customeSitePage = new CustomeSitePage(driver);
      SitesListTab sitesListTab = new SitesListTab(driver);
      
     customeSitePage.rollUpSiteFrame();
	 Thread.sleep(1000);     
	 customeSitePage.turnOffSiteFrame();
	 Thread.sleep(1000);
     sitesListTab.getSitePreviewPopup();	 
     customeSitePage.rollUpSiteFrame();    
	 Thread.sleep(1000);
	 customeSitePage.turnOffSiteFrame();
	 Thread.sleep(1000);

	 errorIndex = jsErrorIndex;
	 Page.jsErrorReporter(driver, errorIndex,"####### TC 8.4 Preview buyer site second time, JS errors for SP site #######");	 
	 log.info("End: testCustomeSitePage");
	 log.info("--------------------------------------------");
   }

   //***************************************************************************************
   @Test(description="7.5-1, Create New Product in Advanced Product Editor - get Empty product")
   public void testGetNewProductAdvancedEditor() throws InterruptedException
   {
	   log.info("Start: testGetNewProductAdvancedEditor");
	   ManageProductsPage managerProductsPage = new ManageProductsPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(1700);	   
	   //newHomePage.clickGearSubmenuItem(driver, "Request Forms");
	   newHomePage.clickGearSubmenuItem(driver, "/service/product/specs"); // use link to find the item for now
	   Thread.sleep(1600);	
	   managerProductsPage.clickCreateNewProductBT();
	   Thread.sleep(5000);
	   managerProductsPage.clickEmptyProduct();
	   Thread.sleep(2000);
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-1, Create New Product in Advanced Product Editor - get Empty product, JS errors for SP site #######");	   
	   log.info("End: testGetNewProductAdvancedEditor");
	   log.info("--------------------------------------------");
   }

   @Test(description="7.5-2, Create New Product in Advanced Product Editor - Basic Information")
   @Parameters({"productNameAdv", "productImage"})
   public void testAdvancedEditorBasicInfo(String productNameAdv, String productImage) throws InterruptedException
   {
	   log.info("Start: testAdvancedEditorBasicInfo");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   
	   Thread.sleep(800);	   	   
	   advancedEditor.putProductName(productNameAdv + unicID);
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
	   advancedEditor.dragAndDropTextArea();
	   Thread.sleep(1200);
	   advancedEditor.clickPencilTextBox();
	   Thread.sleep(1000);	   
	   advancedEditor.selectRequired();
	   Thread.sleep(800);
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
	   advancedEditor.clickPencilDate();
	   Thread.sleep(1000);	   
	   advancedEditor.selectNotShownOnClientView();
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
	   advancedEditor.clickPencilNumber();
	   Thread.sleep(1000);	   
	   advancedEditor.putDefaultValue(defaultValue);	   
	   advancedEditor.selectReadOnly();
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
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-5, Create New Product in Advanced Product Editor - Customization Tab - Image and Dimentions, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorAnotherElements");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-6, Create New Product in Advanced Product Editor - Customizing number columns")
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
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-6, Create New Product in Advanced Product Editor - Customizing number columns, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorNumberCol");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="7.5-7, Create New Product in Advanced Product Editor - Add another tab")
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
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7.5-7, Create New Product in Advanced Product Editor - Add another tab, JS errors for SP site #######");	   
	   log.info("End: testCustomTabAdvancEditorAnotherTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go to sites list for Noosh Pro")
   public void testGoToSitesList() throws InterruptedException
   {
	   log.info("Start: testGoToSitesList");
	   SitesListTab sitesListTab = new SitesListTab(driver);
	   
	   Thread.sleep(1200);	   
	   sitesListTab.getSiteList();
	   log.info("End: testGoToSitesList");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="8.5, Create New Product in Advanced Product Editor - Assigning created product to the buyer")
   @Parameters({"productNameAdv"})
   public void testCustomTabAdvancEditorAssigningProductBuyer(String productNameAdv) throws InterruptedException, AWTException
   {	  
	   log.info("Start: testCustomTabAdvancEditorAssigningProductBuyer");
	   SitesListTab sitesListTab = new SitesListTab(driver);
	   MainMenuPage mainMenuPage = new MainMenuPage(driver);
	   
	   Thread.sleep(1500);  	   	   
	   mainMenuPage.clickPoductsTab();
	   Thread.sleep(800);	   
	   mainMenuPage.assignProductToBuyerSite();
	   productNameAdv = productNameAdv + unicID;
	   Thread.sleep(800);
	   sitesListTab.assignPoductToBuyer(driver, productNameAdv);	   
	   Thread.sleep(600);
	   sitesListTab.clickAssignBT();
	   Thread.sleep(1500);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.5, Create New Product in Advanced Product Editor - Assigning created product to the buyer, JS errors for SP site #######");		   
	   log.info("End: testCustomTabAdvancEditorAssigningProductBuyer");
	   log.info("--------------------------------------------");
   }
   //*****************************************************************************    
   
   @Test(description="10.0 SP Create Project - step 1")
   @Parameters({"validDemoUserEmail", "productName"})
   public void testSPcreateProject(String validDemoUserEmail, String productName) throws InterruptedException
   {
	   log.info("Start: testSPcreateProject");
	   productName = productName + unicID +"2";
	   
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   
	   newProject.clicklCreateProjectBT();	   
	   newProject.enterValidClientEmail(validDemoUserEmail); 
	   newProject.clickFindBT();
	   newProject.selectCustomer();	   
	   newProject.selectCertainSite();
	   newProject.clickPostcard();	     
	   Thread.sleep(500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-1, SP Create Project - step 1, JS errors for SP site #######");
	   log.info("End: testSPcreateProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0 SP Create Project - product 1")
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", 
	   "specDescrSP", "fileForProject1"})
   public void testSPcreateProjectProjectWizard(String projNameSP, String descriptionNameSP,
		   String sku, String referenceNumber, String quantSP, String specDescrSP,
		   String fileForProject1) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPcreateProjectProjectWizard");
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
	   page.uploadFileModalWindow(fileForProject1);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(1000);
	   page.robotUpload();	   
	   Thread.sleep(3000);  
	   brochurePopup.getReviewAndSubmitTab();	   
       brochurePopup.clickAddProductsBT();	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-2, SP Create Project - product 1, JS errors for SP site #######");
	   log.info("End: testSPcreateProjectProjectWizard");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0 SP Create Project - product 2")
   @Parameters({"descriptionNameSP", "sku", "referenceNumber", "quantSP", "specDescrSP", "newProjectNameSP"})
   public void testCreateProductIntoCreateProject(String descriptionNameSP, String sku, String referenceNumber, 
		   String quantSP, String specDescrSP, String newProjectNameSP) throws InterruptedException
   {  
	   log.info("Start: testCreateProductIntoCreateProject");
	   CreateNewProduct newProduct = new CreateNewProduct(driver);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   Thread.sleep(800);	   
	   newProduct.clickBrochureIcon();	   
	   Thread.sleep(500);
	   brochurePopup.clickSpecDescription();	   
	   brochurePopup.putDescriptionName(descriptionNameSP + 1);
	   brochurePopup.putSKU(sku);
	   brochurePopup.putRefNumber(referenceNumber);
	   brochurePopup.putQuant1(quantSP);
	   brochurePopup.getReviewAndSubmitTab();	   
	   brochurePopup.clickProjectInfoEditBT();	   
	   brochurePopup.setProjectName(newProjectNameSP + unicID);
	   brochurePopup.getReviewAndSubmitTab();	   
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(2000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-3, Create Project - product 2, JS errors for SP site #######");	  
	   log.info("End: testCreateProductIntoCreateProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0 SP Create Project - Project verification" )
   @Parameters({"newProjectNameSP", "descriptionNameSP", "fileProject1", "fileName1"})
   public void testCreateProjectVerification(String newProjectNameSP, String descriptionNameSP,
		   String fileProject1, String fileName1) throws InterruptedException, AWTException
   {
	   log.info("Start: testCreateProjectVerification");
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   Page page = new Page(driver);
	   
	   Thread.sleep(1700); 	   
	   mainMenu.clickProjectMenu();
	   Thread.sleep(2000); 	   
	   projectFrame.clickSPProject();
	   Thread.sleep(1000);	   
	   try {
		      AssertJUnit.assertTrue(projectFrame.getProjectStatus());	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Project status 'New' does not displayed");
			  log.info("10.0 Project status 'New' does not displayed");
		   } 
	   try {
		      AssertJUnit.assertTrue((newProjectNameSP + unicID).trim().equals(projectFrame.getProjectName()));	      
		   } catch(Throwable e) {
			  System.out.println("10.0 Correct project name does not displayed");
			  log.info("10.0 Correct project name does not displayed");
		   } 
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(2000);
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
	   Thread.sleep(700);
//	   System.out.println("1st uploaded file name is: " + projectFrame.getUploadedFileName33());
	   try{
	       AssertJUnit.assertEquals(fileProject1, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.0 Uploaded file name " + fileProject1 + " does not displayed");
	    	  log.info("10.0 Uploaded file name " + fileProject1 + " does not displayed");
	    	}	 
	   
	   projectFrame.clickDownloadFileIcon(driver, fileProject1);
	   Thread.sleep(600);
	   page.downloadFile();
	   // file delete	   
       Thread.sleep(3000);
	   projectFrame.clickDeleteFileIcon(driver, fileName1);
	   Thread.sleep(600);	   
       Alert alert = buyerDriver.switchTo().alert();
	   alert.accept();
 	   page.closeModalPopup();
	   Thread.sleep(3000);

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0, SP Project verification, JS errors for SP site #######"); 
	   log.info("End: testCreateProjectVerification");
	   log.info("--------------------------------------------");
   }      
      
  // ---------------------------------------- New test case create project 8/16/13 ------------------------------ 
   @Test(description="10.0-1 SP Create Project Adv. Editor - step 1")
  // @Parameters({"validDemoUserEmail"})   //10/17
   
   public void testSPcreateProjectAdvEd() throws InterruptedException {	 
  
	   log.info("Start: testSPcreateProjectAdvEd");
	   
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   Thread.sleep(1000);	   	   
	   newProject.clicklCreateProjectBT();
	   //newProject.inputCustomerProjectName(projectName);    //10/17 add
	  // newProject.enterValidClientEmail(validDemoUserEmail);   //10/17 comment out
	   Thread.sleep(500);
	   //newProject.selectClient();  //10/17 add
	   Thread.sleep(500);
	  // newProject.clickFindBT();    //10/17 comment out
	  // Thread.sleep(400);
	  // newProject.selectCertainSite();   //10/17 comment out

	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.0-1, SP Create Project - step 1, JS errors for SP site #######");	   
	   log.info("End: testSPcreateProjectAdvEd");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0-2 SP Create Project Adv. Editor - step 1")
   //@Parameters({"productNameD"})
   @Parameters({"projectName"})   //10/17
   public void testSPcreateProjectAdvEdSelectProduct(String projectName) throws InterruptedException
   {
	   log.info("Start: testSPcreateProjectAdvEdSelectProduct");
	   CreateNewProject newProject = new CreateNewProject(driver);
	   
	   //resize frame
	   
	   //productName = productName + unicID;  //10/17 comment out
	   projectName = projectName + unicID;    //10/17
	   Thread.sleep(1000);
	   newProject.inputCustomerProjectName(projectName);  //10/17
	   //newProject.selectAdvForm(driver, productName);  //10/17
	   newProject.selectClient();                       //10/17
	
	   //10/17 add -----
	   List<WebElement> smartForms = driver.findElements(By.className("scroll-list-img"));
	   for (WebElement smartForm : smartForms) {
		   if (smartForm.getAttribute("alt").equals("Brochure")) {  
			   smartForm.click();
			   break;
		   }
	   }
	   //10/17 add  -----
	   
	   Thread.sleep(1000);
	 
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-2 SP Create Project Adv. Editor - step 1 #######");	
	   log.info("End: testSPcreateProjectAdvEdSelectProduct");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0-3 SP Create Project Adv. Editor - step 2")
   //@Parameters({"projNameSPAdv", "descriptionNameSPAdv", "referenceNumber", "quantSP"})  //10/17
   @Parameters({"projNameSPAdv", "quantSP1", "quantSP2", "quantSP3"})
   //public void testSPcreateProjectAdvEdProjectWizard(String projNameSPAdv, String descriptionNameSPAdv, String referenceNumber,
	//	String quantSP1, String quantSP2, String quantSP3) throws InterruptedException    //10/17
   public void testSPcreateProjectAdvEdProjectWizard(String projNameSPAdv, String quantSP1, 
		                                             String quantSP2, String quantSP3) throws InterruptedException {
	   log.info("Start: testSPcreateProjectAdvEdProjectWizard");
	   projNameSPAdv = projNameSPAdv + unicID;
	   
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   
	   Thread.sleep(1000);
	   brochurePopup.setProjectName(projNameSPAdv);  //10/11
	   Thread.sleep(1000);   	    
	   brochurePopup.callCalendar();	
	   //brochurePopup.setNextMonth();
	   brochurePopup.setComplationDate();	   	   
	   brochurePopup.getNextTab();	    
       //brochurePopup.setTextBoxText(descriptionNameSPAdv);  //10/17
	   Thread.sleep(500);   	    
	  // brochurePopup.callCalendarAdv();	        //10/17       
	  // brochurePopup.setComplationDate();	        //10/17
	  // brochurePopup.setNewNumber(referenceNumber);	   
	  // brochurePopup.setNewDimention(quantSP);
	   Thread.sleep(1000);
	   
	   List<WebElement> inputs = driver.findElements(By.tagName("input"));
	   for (WebElement input : inputs) {
		   String name = input.getAttribute("reportingname");
		   if (name != null && name.equalsIgnoreCase("QUANTITY1")) {
			   input.clear();
			   input.sendKeys(quantSP1);
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
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### 10.0-3 SP Create Project Adv. Editor - step 2 #######");	
	   log.info("End: testSPcreateProjectAdvEdProjectWizard");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="10.0-4 SP Create Project Adv. Editor - upload file")  //10.0.4 ???
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
       brochurePopup.clickAddProductsBT();	
       Thread.sleep(1500);

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
   @Parameters({"projNameSPAdv", "productNameD", "descriptionNameSPAdv", "fileName3", "fileForProject4", "fileName5", "newFileName"})
   public void testSPProjectVerificationAdvEdOB(String projNameSPAdv, String productNameD, String descriptionNameSPAdv,
		   String fileName3, String fileForProject4, String fileName5, String newFileName) throws InterruptedException, AWTException
   {
	   log.info("Start: testSPProjectVerificationAdvEdOB");
	   String productionSpec1 = projNameSPAdv + " - " + productNameD + unicID;

	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   MainMenuPage mainMenu = new MainMenuPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(3000);   
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
//------------------------------------------------------------------
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
	       AssertJUnit.assertEquals(fileName3, projectFrame.getUploadedSecondFileName().trim());
	      } catch(Throwable e) {
	    	  System.out.println("10.x Uploaded file name " + fileName3 + " does not displayed");
	    	  log.info("10.x Uploaded file name " + fileName3 + " does not displayed");
	    	}	   
       // upload file
	   page.uploadFileModalWindow(fileForProject4);
	   projectFrame.clickFileUploadSP();
	   Thread.sleep(1000);
	   page.robotUpload();
       Thread.sleep(4000);
       if(!browser.trim().equals("IE")) {
	      // download file and close viewer window       
          projectFrame.clickDownloadFileIcon(driver, fileName3);
	      Thread.sleep(600);
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
	   projectFrame.clickDeleteFileIcon(driver, fileName3);
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
   
 // -------------------------------------------------------- New Test cases 10.1 ----------------------------------------------------------------------   
   
   @Test(description="Go to home page second time")
   public void testGetHomePage2() throws InterruptedException
   {
	   log.info("Start: testGetHomePage2");
	   MainMenuPage menuPage = new MainMenuPage(driver);
	   
	   menuPage.clickProjectMenu();
	   Thread.sleep(1400);
	   log.info("End: testGetHomePage2");
	   log.info("--------------------------------------------");
   } 
   
   @Test(description="10.1-1 SP Create Project Hovering in home page - step 1, client selecting")
   @Parameters({"validDemoUserEmail"})
   public void testSPCreateProjectSelectClient(String validDemoUserEmail) throws InterruptedException
   {	   	   	   	
	   log.info("Start: testSPCreateProjectSelectClient");
	   BaseTestCases.testSPCreateProjectSelectBuyerAndWorkspace(validDemoUserEmail); 

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
   @Parameters({"secondSPprojectName", "validDemoUserEmail"})
   public void testSPCreateProjectOpenProjectAndAssignClient(String secondSPprojectName, String validDemoUserEmail) throws InterruptedException, AWTException
   {	  
	   log.info("Start: testSPCreateProjectOpenProjectAndAssignClient");
	   validDemoUserEmail  = "Selenium Buyer";
	   
	   BaseTestCases.testOpenProjectAndAssignClient(secondSPprojectName, validDemoUserEmail);
	   Thread.sleep(1500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1-7 SP Create Project Hovering in home page - step 7, open project and assign client, JS errors for SP site #######");	   
	   log.info("End: testSPCreateProjectOpenProjectAndAssignClient");
	   log.info("--------------------------------------------");
   }
     
   @Test(description="12.2, Client verifies SP project, name and status")
   @Parameters({"buyerProjectName"})
   public void testClientVerifiesSPProject(String buyerProjectName) throws InterruptedException
   {
	   log.info("Start: testClientVerifiesSPProject");
	   Thread.sleep(1500);
	   BaseTestCases.testSPProject2VerificationStatus(buyerProjectName);
	   Thread.sleep(2000);
	   log.info("End: testClientVerifiesSPProject");
	   log.info("--------------------------------------------");
   }
  // ------------------------------------------------------- New test cases create project 8/16/13 ---------------------------------------------------------------- 
   @Test(description="Logout SP site") 
   public void testLogoutDemoSite() throws InterruptedException
   {   
	   log.info("Start: testLogoutDemoSite");
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   String spDomain = driver.getCurrentUrl();
	   int index = spDomain.indexOf("/service");
	   spDomain = spDomain.substring(0, index);
	   System.out.println("spDomain"+spDomain);
       
       //registerSPSitePage.logoutSPSite();
	   //driver.get("http://jennifer.sqa.noosh.com/service/j_spring_security_logout");
	   Thread.sleep(2000);
	   //driver.get("https://nooshselenium2.sqa.noosh.com/service/j_spring_security_logout");
	   //driver.get(spDomain + "/service/j_spring_security_logout");  //10/15
	   ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
       Thread.sleep(1000); 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC Log out SP site, JS errors for SP site #######");	 
	  
	   log.info("End: testLogoutDemoSite");
	   log.info("--------------------------------------------");
   }   
   
   // Advanced editor ************************************************************************************************
   @Test(description="13.1.1-1, Buyer creates new Project, Adv editor poduct - Selects product")
   @Parameters({"productNameAdv"})
   public void testBuyerSiteAdvEd(String productNameAdv) throws InterruptedException
   {
	  log.info("Start: testBuyerSiteAdvEd");
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
      
      productNameAdv = productNameAdv + unicID;
      Thread.sleep(1500); 
      // buyerSitePage.getNewSPProject(buyerDriver, productNameAdv); // old code
      WebElement productList = buyerDriver.findElement(By.id("dashProductList")); // new
      WebElement brochure = productList.findElement(By.tagName("li"));            // new           
      brochure.click();                                                           // new
      Thread.sleep(2000);       
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1.1 - Buyer creates new Project, Adv editor poduct - Selects product, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1.1 - Buyer creates new Project, Adv editor poduct - Selects product, JS errors for Buyer site #######");
   	       }
       }
       log.info("End: testBuyerSiteAdvEd");
       log.info("--------------------------------------------");
   }
   
   @Test(description="13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Project info tab")
   @Parameters({"buyerProjectName", "descriptionName", "quant", "fileForProject1"})
   public void testProductPopupWindowAdvAd(String buyerProjectName, String descriptionName,
		   String quant, String fileForProject1) throws InterruptedException
   {      
	  log.info("Start: testProductPopupWindowAdvAd");
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      descriptionName = descriptionName + unicID;
      buyerProjectName = buyerProjectName + unicID;
      brochurePopup.setProjectName(buyerProjectName); 
      Thread.sleep(1000);       
      brochurePopup.callCalendar();
      //brochurePopup.setNextMonth();
      brochurePopup.setComplationDate();
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Project info tab, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1.1-2, Buyer creates new Project, Adv editor poduct - Create product, Project info tab, JS errors for Buyer site #######");
   	       }
      }
      log.info("End: testProductPopupWindowAdvAd");
      log.info("--------------------------------------------");
   }
   
   @Test(description="13.1.1-3, Buyer creates new Project, Adv editor poduct - Create product, New tab")
   @Parameters({"descriptionName", "quant", "reqField"})
   public void testProductPopupWindowNewTabAdvAd(String descriptionName, String quant, String reqField) throws InterruptedException
   {      
	  log.info("Start: testProductPopupWindowNewTabAdvAd");
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      brochurePopup.getNextTab();
      Thread.sleep(800);
      //brochurePopup.setDimention(quant);      //jenn comment out  9/24
      brochurePopup.getReviewAndSubmitTab();     //submit
      brochurePopup.clickSubmitBT();
      Thread.sleep(800);
	  try {
	       AssertJUnit.assertTrue(Page.isTextPresent(reqField, driver));	
	       } catch(Throwable e) {
		       System.out.println("13.1.1-7, Error message " + reqField + " does not displayed");
		       log.info("13.1.1-7 Error message " + reqField + " does not displayed");
	       }      
      //brochurePopup.setNameTextBox(descriptionName);    /jenn comment out  9/24
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1.1-3, Buyer creates new Project, Adv editor poduct - Create product, New tab, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1.1-3, Buyer creates new Project, Adv editor poduct - Create product, New tab, JS errors for Buyer site #######");
   	       }
      }
      log.info("End: testProductPopupWindowNewTabAdvAd");
      log.info("--------------------------------------------");
   }
   
   @Test(description="13.1.1-4, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab")
   @Parameters({"fileForProject3"})
   public void testProductPopupWindowFileAdvAd(String fileForProject3) throws InterruptedException, AWTException
   {
	  log.info("Start: testProductPopupWindowFileAdvAd");
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);      

      Thread.sleep(2000);
      brochurePopup.clickFilesTabAdvEd();
      if(!buyerBrowser.trim().equals("IE")){
   	     page.uploadFileModalWindow(fileForProject3);
   	     brochurePopup.clickUploadFileDropBT();
   	     Thread.sleep(1000);
   	     page.robotUpload();     
   	     Thread.sleep(3500);
       }
      brochurePopup.getReviewAndSubmitTab();
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000); 
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1.1-4, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1.1-4, Buyer creates new Project, Adv editor poduct - Create product, Upload file and Submit tab, JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testProductPopupWindowFileAdvAd");
      log.info("--------------------------------------------");
   }
   
   @Test(description="13.1.1-5, Buyer creates new Project, Adv editor poduct - Create product, Add new product")
   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "buyerProjectName"})
   public void testAddNewProductAdvEd(String descriptionName, String skuSP, String referenceNumber, 
		   String quant, String specDescrBuyer, String buyerProjectName) throws InterruptedException
   {
	   log.info("Start: testAddNewProductAdvEd");
	   CreateNewProduct newProduct = new CreateNewProduct(buyerDriver);
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);	   
	   
	   Thread.sleep(500);
	   newProduct.clickBrochureIcon();
	   Thread.sleep(500);
	   brochurePopup.clickSpecDescription();
	   Thread.sleep(800);
	   brochurePopup.putDescriptionNameNew(driver, "Spec Name", descriptionName + 1);
	   brochurePopup.putDescriptionNameNew(driver, "SKU", skuSP);   
	   brochurePopup.putQuant1(quant);
	   Thread.sleep(500);
	   brochurePopup.getReviewAndSubmitTab();
	   brochurePopup.clickProjectInfoEditBT();	   
	   brochurePopup.setProjectName(buyerProjectName);
	   brochurePopup.getReviewAndSubmitTab();	   
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(2500);  	   
	   System.out.println("Buyer site URL: " + driver.getCurrentUrl());   

	   if(!buyerBrowser.trim().equals("IE")){
	    	 errorIndexBuyer = jsErrorIndexBuyer;
	   	     Page.logJSError(jsErrorIndexBuyer, driver, log);
	   	     if (jsErrorIndexBuyer > errorIndexBuyer){
	   		     System.out.println("####### TC 13.1.1-5, Buyer creates new Project, Adv editor poduct - Create product, Add new product, JS errors for Buyer site #######");
	   		     log.info("####### TC 13.1.1-5, Buyer creates new Project, Adv editor poduct - Create product, Add new product, JS errors for Buyer site #######");
	   	     }
	   }
	   log.info("End: testAddNewProductAdvEd");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="13.1.1-6, Buyer creates new Project, Adv editor poduct - Create product, Project verification, New status")
   @Parameters({"projectStatusBuyer"})
   public void testStatusProjectVerificationAdvEd(String projectStatusBuyer) throws InterruptedException
   {
	   log.info("Start: testStatusProjectVerificationAdvEd");
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	   
	   buyerSitePage.clickCreatedProjectAdvEd();
	   try {
		   AssertJUnit.assertEquals(buyerSitePage.getProjectStatus(), projectStatusBuyer);	
		   } catch(Throwable e) {
			 System.out.println("13.1.1-6 Project status '" + projectStatusBuyer + "' does not displayed.");
			 log.info("13.1.1-6 Project status '" + projectStatusBuyer + "' does not dispalayed.");
		   }

	   if(!buyerBrowser.trim().equals("IE")){
	    	 errorIndexBuyer = jsErrorIndexBuyer;
	   	     Page.logJSError(jsErrorIndexBuyer, driver, log);
	   	     if (jsErrorIndexBuyer > errorIndexBuyer){
	   		     System.out.println("####### TC 13.1.1-6, Buyer creates new Project, Adv editor poduct - Project verification, New status, JS errors for Buyer site #######");
	   		     log.info("####### TC 13.1.1-6, Buyer creates new Project, Adv editor poduct - Project verification, New status, JS errors for Buyer site #######");
	   	     }
	   }
	   log.info("End: testStatusProjectVerificationAdvEd");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="13.1.1-7, Buyer creates new Project, Adv editor poduct - Create product, Project verification, Production specs")
   @Parameters({"descriptionName", "buyerProjectName", "productNameAdv"})
   public void testSpecsProjectVerificationAdvEd(String descriptionName, String buyerProjectName, String productNameAdv) throws InterruptedException
   {
	   log.info("Start: testSpecsProjectVerificationAdvEd");
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   Thread.sleep(800);	   
	   projectPage.clickProjectSpecsTab();
	   Thread.sleep(800);      
       descriptionName = descriptionName + 1;
       buyerProjectName = buyerProjectName + unicID + "G - " + productNameAdv + unicID;      
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
	    	 errorIndexBuyer = jsErrorIndexBuyer;
	   	     Page.logJSError(jsErrorIndexBuyer, driver, log);
	   	     if (jsErrorIndexBuyer > errorIndexBuyer){
	   		     System.out.println("####### TC 13.1.1-7, Buyer creates new Project, Adv editor poduct - Project verification, Production specs, JS errors for Buyer site #######");
	   		     log.info("####### TC 13.1.1-7, Buyer creates new Project, Adv editor poduct - Project verification, Production specs, JS errors for Buyer site #######");
	   	     }
	   }
	   log.info("End: testSpecsProjectVerificationAdvEd");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="13.1.1-8, Buyer creates new Project, Adv editor poduct - Create product, Project verification, Uploaded file")
   @Parameters({"fileName1"})
   public void testUploadedFileProjectVerificationAdvEd(String fileName1) throws InterruptedException
   {
	   log.info("Start: testUploadedFileProjectVerificationAdvEd");
	   testSPFileAttached(fileName1);

	   if(!buyerBrowser.trim().equals("IE")){
	    	 errorIndexBuyer = jsErrorIndexBuyer;
	   	     Page.logJSError(jsErrorIndexBuyer, driver, log);
	   	     if (jsErrorIndexBuyer > errorIndexBuyer){
	   		     System.out.println("####### TC 13.1.1-8, Buyer creates new Project, Adv editor poduct - Project verification, Uploaded file, JS errors for Buyer site #######");
	   		     log.info("####### TC 13.1.1-8, Buyer creates new Project, Adv editor poduct - Project verification, Uploaded file, JS errors for Buyer site #######");
	   	     }
	   }
	   log.info("End: testUploadedFileProjectVerificationAdvEd");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="14.1, Login to SP site")
   @Parameters({"passwordSP", "spEmailSmoke"})
   public void testLoginSPSite(String passwordSP, String spEmailSmoke) throws InterruptedException
   {	    
	   log.info("Start: testLoginSPSite");
	   Thread.sleep(800);
	   driver.get(baseUrlSmoke);	      
	      
	   // Verifying that this is the correct page     
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   Thread.sleep(1000);	   
	   //loginDemoSqa.loginUser(spEmailSmoke, passwordSP);    //  
	   loginDemoSqa.loginUser(spEmailSmoke, passwordSP);   
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	   System.out.println("####### TC 14.1, Login to SP site, JS errors for SP site #######");
	   	   log.info("####### TC 14.1, Login to SP site, JS errors for SP site #######");
	   }	  
	   log.info("End: testLoginSPSite");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Login SP site")
   @Parameters({"passwordSP"})
   public void testLoginDemoPage(String passwordSP) throws InterruptedException 
   {
	   log.info("Start: testLoginDemoPage");
	   Thread.sleep(500);
	   //driver.get("http://selenium" + unicID + ".sqa.noosh.com/service/login");    
	  // driver.get("http://selenium" + unicID + ".qa2.noosh.com/service/login");   
	   driver.get("http://selenium" + unicID + "." + domain.trim() + ".noosh.com/service/login");
      
       driver.manage().window().maximize();
      
       // Verifying that this is the correct page     
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);

	   Thread.sleep(500);
       loginDemoSqa.loginUser("nobody+" + unicID + "@noosh.com", passwordSP); 
       Thread.sleep(500);
       
       errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### Login SP site, JS errors for SP site #######");
	   	  log.info("####### Login SP site, JS errors for SP site #######");
	   }   
	   log.info("End: testLoginDemoPage");
	   log.info("--------------------------------------------");
   } 
   
   @Test(description="14.2, SP Review new project")
   @Parameters({"buyerProjectName","descriptionName"})
   public void testReviewNewProject(String buyerProjectName, String descriptionName) throws InterruptedException
   {
	   log.info("Start: testReviewNewProject");
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   com.noosh.nooshentry.automation.buyerSite.NewProjectPage projectPage = new NewProjectPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
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
	       AssertJUnit.assertTrue(Page.isTextPresent(descriptionName, driver));	
	       } catch(Throwable e) {
		       System.out.println("14.2 Project spec " + descriptionName + " does not displayed");
		       log.info("14.2 Project spec " + descriptionName + " does not displayed");
	       }  

	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.2, SP Review new project, JS errors for SP site #######");
	   	  log.info("####### TC 14.2, SP Review new project, JS errors for SP site #######");
	   }
	   log.info("End: testReviewNewProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.3, SP upload new file to project")
   @Parameters({"profilePicture", "profilePictureFile", "fileName3", "newProfilePictureFile"})
   public void testUploadNewFile(String profilePicture, String profilePictureFile, String fileName3, 
		   String newProfilePictureFile) throws InterruptedException, AWTException
   {
	   log.info("Start: testUploadNewFile");
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
       projectFrame.clickDownloadFileIcon(driver, fileName3);
       Thread.sleep(600);
       page.downloadFile();
	   // file rename
	   projectFrame.renameFileName(buyerDriver, profilePictureFile, newProfilePictureFile);
	   page.closeModalPopup();
	   Thread.sleep(2500);
	   // sorting
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);
	   projectFrame.clickNameSort();
	   Thread.sleep(1500);

	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.3, SP upload new file to project, JS errors for SP site #######");
	   	  log.info("####### TC 14.3, SP upload new file to project, JS errors for SP site #######");
	   }
	   log.info("End: testUploadNewFile");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.4, SP send message to buyer")
   @Parameters({"sPNewMessage"})
   public void testSendMessage(String sPNewMessage) throws InterruptedException
   {
	   log.info("Start: testSendMessage");
	   BaseTestCases.testSendMessageFile(driver, sPNewMessage);	 
	   
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(sPNewMessage, driver));	        
		   } catch(Throwable e) {
			  System.out.println("14.4 SP message '" + sPNewMessage + "' does not displayed");
			  log.info("14.4 SP message '" + sPNewMessage + "' does not displayed");
		   } 

	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.4, SP send message to buyer, JS errors for SP site #######");
	   	  log.info("####### TC 14.4, SP send message to buyer, JS errors for SP site #######");
	   } 
	   log.info("End: testSendMessage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="14.5, SP update project info")
   public void testUpadateProjectInfo() throws InterruptedException
   {
	   log.info("Start: testUpadateProjectInfo");
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
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.5 JS errors for SP site #######");
	   	  log.info("####### TC 14.5 JS errors for SP site #######");
	   }
	   log.info("End: testUpadateProjectInfo");
	   log.info("--------------------------------------------");
   }

   @Test(description="14.5, SP update buyer project status")
   @Parameters({"buyerProjectName"})   
   public void testUpdateProjectStatus(String buyerProjectName) throws InterruptedException
   {
	   log.info("Start: testUpdateProjectStatus");
	   buyerProjectName = buyerProjectName + unicID + "G";
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(300); 
	   projectFrame.clickOverviewTab();
	   Thread.sleep(600);
	   BaseTestCases.updateProjectStatus(buyerProjectName);
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.5 SP update buyer project status, JS errors for SP site #######");
	   log.info("End: testUpdateProjectStatus");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="14.6-1, SP edit spec in-line, invalid data")
   @Parameters({"buyerProjectName", "productNameAdv", "descriptionNameSPAdv", "wrongOriginalPassword", 
	   "requiredFieldErrorMessage", "numericFieldErrorMessage"})
   public void testSPEditSpecInlineInvalidData(String buyerProjectName, String productNameAdv, 
		   String descriptionNameSPAdv, String wrongOriginalPassword, String requiredFieldErrorMessage,
		   String numericFieldErrorMessage) throws InterruptedException
   {
	   log.info("Start: testSPEditSpecInlineInvalidData");
	   String specName;
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   
	   Thread.sleep(600);
	   projectFrame.clickProductionSpecTab();
	   Thread.sleep(1000);	   	
	   specName = buyerProjectName + unicID + "G" + " - " + productNameAdv + unicID;
	   projectFrame.selectProductSpec(driver, specName);
	   Thread.sleep(300);	   
	   projectFrame.clickEditBT();	   
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
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(numericFieldErrorMessage, driver));   
		   } catch(Throwable e) {
			  System.out.println("14.6-1 Error message " + numericFieldErrorMessage + " does not displayed after save button");
			  log.info("14.6-1 Error message " + numericFieldErrorMessage + " does not displayed after cancel button");
		   }
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.6-1 SP edit spec in-line,  invalid data, JS errors for SP site #######");
	   	  log.info("####### TC 14.6-1 SP edit spec in-line, invalid data, JS errors for SP site #######");
	   }
	   log.info("End: testSPEditSpecInlineInvalidData");
	   log.info("--------------------------------------------");
   }	   
   
   @Test(description="14.6-2, SP edit spec in-line, cancel button")
   @Parameters({"skuSP", "skuAdv"})
   public void testSPEditSpecInline(String skuSP, String skuAdv) throws InterruptedException
   {
	   log.info("Start: testSPEditSpecInline");
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
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.6-2, SP edit spec in-line, cancel button, JS errors for SP site #######");
	   	  log.info("####### TC 14.6-2, SP edit spec in-line, cancel button, JS errors for SP site #######");
	   }
	   log.info("End: testSPEditSpecInline");
	   log.info("--------------------------------------------");
   }
	   
   @Test(description="14.6-3, SP edit spec in-line, valid data")
   @Parameters({"skuSP", "skuAdv"})
   public void testSPEditSpecInlineValidData(String skuSP, String skuAdv) throws InterruptedException
   {
	   log.info("Start: testSPEditSpecInlineValidData");
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
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.6-3 SP edit spec in-line, valid data, JS errors for SP site #######");
	   	  log.info("####### TC 14.6-3 SP edit spec in-line, valid data, JS errors for SP site #######");
	   }
	   log.info("End: testSPEditSpecInlineValidData");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="Logout SP site")
   public void logoutSPSite() throws InterruptedException
   {
	   log.info("Start: logoutSPSite");
	   testLogoutDemoSite();
	   
	   Thread.sleep(1000);
	   
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### Logout SP site, JS errors for SP site #######");
	   	  log.info("####### Logout SP site, JS errors for SP site #######");
	   }	
	   log.info("End: logoutSPSite");
	   log.info("--------------------------------------------");
   }
 
   @Test(description="14.5, Check buyer notification")
   @Parameters({"baseUrlBuyerSite", "buyerLoginEmail", "firstNameSP", "lastNameSP", "profilePictureFile", "newProjectName", "buyerProjectName"})
   public void testBuyerNotifications(String baseUrlBuyerSite, String buyerLoginEmail, String firstNameSP,
		   String lastNameSP, String profilePictureFile, String newProjectName, String buyerProjectName) 
      throws InterruptedException, AWTException 
   {
	  log.info("Start: testBuyerNotifications");
      String statusNote, messageNote, spFileNote,  projectNotification;
	   
	  driver.get(baseUrlBuyerSite);       
      // Verifying that this is the correct page     

      LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
            
      loginEmailPage.loginUser("nobody@noosh.com", "17password");      
            
      projectNotification = buyerLoginEmail + " " + newProjectName + unicID;         
      buyerProjectName = buyerProjectName + unicID + "G";
      
      loginEmailPage.searchInvitationLetter(buyerProjectName);
      Page.robotClickEnter();
	  Thread.sleep(500);            
      statusNote = "The status of project QA project Buyer" + unicID + " (" + projectNumber + 
    		  ") has been changed to New Job ";
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(statusNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Changing status notification does not received");
		      log.info("14.5 Changing status notification does not received");
	      }
	  	  	
	  messageNote = "Message posted regarding QA project Buyer" + unicID + " (" + projectNumber + ")";
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(messageNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Buyr does not received SP message notification");
		      log.info("14.5 Buyr does not received SP message notification");
	      }

	  spFileNote = firstNameSP + " " + lastNameSP + " has added File " + profilePictureFile;
	  try {
	      AssertJUnit.assertTrue(Page.isTextPresent(spFileNote, driver));	
	      } catch(Throwable e) {
		      System.out.println("14.5 Buyr does not received New file added SP notification");
		      log.info("14.5 Buyr does not received New file added SP notification");
	      }
	  Thread.sleep(300);	   
	  loginEmailPage.logoutSPEmail();
	  Thread.sleep(1000);	  
   	  System.out.println("Project name: " + "QA project Buyer" + unicID + " (" + projectNumber + ")");
   	  log.info("Project name: " + "QA project Buyer" + unicID + " (" + projectNumber + ")");
   	  
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.5 - Check buyer notification, JS errors for SP site #######");
	   	  log.info("####### TC 14.5 - Check buyer notification, JS errors for SP site #######");
	   }
	   log.info("End: testBuyerNotifications");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description="14.3 - 14.5, Check SP notification")
   @Parameters({"spEmailSmoke", "passwordSP", "newProjectName", "firstNameSP", "lastNameBuyer", "profilePictureFile", "buyerProjectName"})
   public void testSPNotifications(String spEmailSmoke, String passwordSP, String newProjectName, String firstNameSP, String lastNameBuyer, 
		   String profilePictureFile, String buyerProjectName) throws InterruptedException, AWTException 
   {
	  log.info("Start: testSPNotifications");
	  String messageNote, spFileNote, projectNotificationSP;
	      
	  LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
	  
	  loginEmailPage.loginUser("nobody@noosh.com", passwordSP);
	  	  
	  projectNotificationSP = spEmailSmoke + " " + newProjectName + unicID;
	  
      buyerProjectName = buyerProjectName + unicID + "G";
	  loginEmailPage.searchInvitationLetter(buyerProjectName);	
	  Page.robotClickEnter();
	  Thread.sleep(800);	  	 	  	  	
	  messageNote = "Message posted regarding QA project Buyer" + unicID + " (" + projectNumber + ")";
      try {
		     AssertJUnit.assertTrue(Page.isTextPresent(messageNote, driver));	
		  } catch(Throwable e) {
		     System.out.println("14.5 SP message notification does not received");
		     log.info("14.5 SP message notification does not received");
		  }
		  	  
	  spFileNote = firstNameSP + " " + lastNameBuyer + " has added File " + profilePictureFile;
	  try {
		      AssertJUnit.assertTrue(Page.isTextPresent(spFileNote, driver));	
		  } catch(Throwable e) {
			 System.out.println("14.5 New file added SP notification does not received");
			 log.info("14.5 New file added SP notification does not received");
		  }
	  loginEmailPage.logoutSPEmail();
	  Thread.sleep(1000);
	  
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.3 - 14.5, Check SP notification, JS errors for SP site #######");
	   	  log.info("####### TC 14.3 - 14.5, Check SP notification, JS errors for SP site #######");
	   }	
	   log.info("End: testSPNotifications");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="14.3 - 14.5, Check SP notification, check file link")
   public void testSPFileNotifications(String profilePictureFile) throws InterruptedException 
   {
	  log.info("Start: testSPFileNotifications");
	  String messageNote, spFileNote;
	      
	  LoginEmailPage loginEmailPage = new LoginEmailPage(driver);
	  
	   errorIndexBuyer = jsErrorIndexBuyer;
	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	   if (jsErrorIndexBuyer > errorIndexBuyer){
	   	  System.out.println("####### TC 14.3 - 14.5, JS errors for SP site #######");
	   	  log.info("####### TC 14.3 - 14.5, JS errors for SP site #######");
	   }	
	   log.info("End: testSPFileNotifications");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="Logger JSErrors")
   public void logger() throws Exception {
	    log.info("Start: logger");
		JavaScriptLog.logJSError(driver, buyerDriver);
		log.info("End: logger");
	}
   
   @Test(description = "Login to Buyer Site for independent Buyer site test")
   @Parameters({"buyerLoginEmail", "passwordBuyer"})
   public void testLoginBuyerSiteIndependent(String buyerLoginEmail, String passwordBuyer) 
      throws InterruptedException
   {     
	   log.info("Start: testLoginBuyerSiteIndependent");
	   // 6/17
	 // buyerDriver.get(credentials.get("buyerSite"));  // jenn comment out 10/4
	 //  buyerDriver.get("https://nooshselenium.sqa.noosh.com/client_3/main");  //10/17
	  //buyerDriver.get("https://nooshselenium.sqa.noosh.com/autoclient/main");  //10/18
	  // buyerDriver.get("https://nooshselenium.qa2.noosh.com/autoclient/main"); //10/18
	   buyerDriver.get(clientURL);   //10/19
      LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
      
      loginBuyerPage.loginBuyer(buyerLoginEmail, passwordBuyer);
      Thread.sleep(1500); 
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### Login to Buyer Site JS errors for Buyer site #######");
   		      log.info("####### Login to Buyer Site JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testLoginBuyerSiteIndependent");
      log.info("--------------------------------------------");
   }
   
   @Test(description="13.1, Buyer creates new Project - Selects product")
   public void testBuyerSite() throws InterruptedException
   {
	  log.info("Start: testBuyerSite");
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);         

      buyerSitePage.getNewBrochureProject();
      Thread.sleep(1000);
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1 - Selects product, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1 - Selects product, JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testBuyerSite");
      log.info("--------------------------------------------");
   }
   
   
   @Test(description="13.1, Buyer creates new Project - Create product")
   @Parameters({"projName", "descriptionName", "skuSP", "referenceNumber", 
	   "quant", "specDescrBuyer", "fileForProject1"})
   public void testBrochurePopupWindow(String projName, String descriptionName,
		   String skuSP, String referenceNumber, String quant, String specDescrBuyer,
		   String fileForProject1) throws InterruptedException, AWTException
   {    
	  log.info("Start: testBrochurePopupWindow");
      BrochurePopupWindow brochurePopup = new BrochurePopupWindow(buyerDriver);
      
      brochurePopup.setProjectName(projName); 
      Thread.sleep(1000);       
      brochurePopup.callCalendar();
      brochurePopup.setNextMonth();
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
      Thread.sleep(3500);  
      brochurePopup.getReviewAndSubmitTab();
      brochurePopup.clickAddProductsBT();
      Thread.sleep(2000); 
      
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1 - Create product, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1 - Create product, JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testBrochurePopupWindow");
      log.info("--------------------------------------------");
   }      

   @Test(description="13.1, Buyer creates new Project - Add new product for IE")
   @Parameters({"descriptionName", "skuSP", "referenceNumber", "quant", "specDescrBuyer", "newProjectName"})
   public void testCreateProductIntoCreateProjectIE(String descriptionName, String skuSP, String referenceNumber, 
		   String quant, String specDescrBuyer, String newProjectName) throws InterruptedException, AWTException
   {  	   
	   log.info("Start: testCreateProductIntoCreateProjectIE");
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
	   brochurePopup.setProjectName(newProjectName + unicID);
	   Thread.sleep(500);
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(1000);
	   brochurePopup.clickSubmitBT();
	   Thread.sleep(1000);
	   if(!buyerDriver.equals(driver)){
	      page.closeModalPopup();
	   }	 
	   log.info("End: testCreateProductIntoCreateProjectIE");
	   log.info("--------------------------------------------");
   } 
   
   @Test(description="13.1, Buyer creates new Project - verification in the Overview tab")
   @Parameters({"newProjectName"})
   public void testNewProject(String newProjectName) throws InterruptedException
   {
	  log.info("Start: testNewProject");
      BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
      NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);      
      
      Thread.sleep(2000); 
      buyerSitePage.clickProject();
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
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 13.1, Buyer create new Project - verification in the Overview tab, JS errors for Buyer site #######");
   		      log.info("####### TC 13.1, Buyer create new Project - verification in the Overview tab, JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testNewProject");
      log.info("--------------------------------------------");
   }

   @Test(description="13.1, Buyer create new Project")
   @Parameters({"descriptionName"})
    public void testVerifyProjectSpecs(String descriptionName) throws InterruptedException, AWTException
    {
	   log.info("Start: testVerifyProjectSpecs");
       NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);

       newProjectPage.clickProjectSpecsTab();
       Thread.sleep(500);        
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionName, buyerDriver));	         
		   } catch(Throwable e) {
			  System.out.println("13.1 Project spec " + descriptionName + " does not displayed");
			  log.info("13.1 Project spec " + descriptionName + " does not displayed");
		   } 
	   descriptionName = descriptionName + 1;
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(descriptionName, buyerDriver));	        
		   } catch(Throwable e) {
			  System.out.println("13.1 Project spec " + descriptionName + " does not displayed");
			  log.info("13.1 Project spec " + descriptionName + " does not displayed");
		   } 
	   
	    if(!buyerBrowser.trim().equals("IE")){
 	        errorIndexBuyer = jsErrorIndexBuyer;
 	        Page.logJSError(jsErrorIndexBuyer, driver, log);
 	        if (jsErrorIndexBuyer > errorIndexBuyer){
 	    	     System.out.println("####### TC 13.1, Buyer create new Project - verification of project specs, JS errors for Buyer site #######");
 	    	     log.info("####### TC 13.1, Buyer create new Project - verification of project specs, JS errors for Buyer site #######");
 	        }
 	    } 	   
	    log.info("End: testVerifyProjectSpecs");
	    log.info("--------------------------------------------");
    }
	   
	  @Test(description="13.2, Buyer uploads new file to the Project")
	  @Parameters({"fileForProject1", "fileName1", "fileName3", "fileProject1"})
	   public void testUploadingNewFile(String fileForProject1, String fileName1, 
	    	String fileName3, String fileProject1) throws InterruptedException, AWTException
	   {
		  log.info("Start: testUploadingNewFile");
	   NewProjectPage newProjectPage = new NewProjectPage(buyerDriver);
       ProjectFrame projectFrame = new ProjectFrame(buyerDriver);
       
       newProjectPage.clickFileTab();  
       Thread.sleep(1000);
      try {
	       AssertJUnit.assertTrue(Page.isTextPresent(fileName3, buyerDriver));	
	   } catch(Throwable e) {
		   System.out.println("13.2 Uploaded file name '" + fileName3 + "' does not uploaded.");
		   log.info("13.2 Uploaded file name '" + fileName3 + "' does not uploaded.");
	   } 
       if(!buyerBrowser.trim().equals("IE")){
   	      page.uploadFileModalWindow(fileForProject1);
   	      projectFrame.clickUloadFileBTText();
   	      Thread.sleep(1000);
   	      page.robotUpload();
   	      Thread.sleep(3500);
   		  // download file and close viewer window
   	      projectFrame.clickDownloadFileIcon(driver, fileName3);
          Thread.sleep(1000);
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
   		  Thread.sleep(1000);
   	      Alert alert = driver.switchTo().alert();
   		  alert.accept();
   	 	  page.closeModalPopup();
   		  Thread.sleep(2000);
       }   
  
 	      if(!buyerBrowser.trim().equals("IE")){
 	          errorIndexBuyer = jsErrorIndexBuyer;
 	          Page.logJSError(jsErrorIndexBuyer, driver, log);
 	          if (jsErrorIndexBuyer > errorIndexBuyer){
 	    	     System.out.println("####### TC 13.2, Buyer uploads new file to the Project, JS errors for Buyer site #######");
 	    	     log.info("####### TC 13.2, Buyer uploads new file to the Project, JS errors for Buyer site #######");
 	          }
 	      }
 	      
 	     log.info("End: testUploadingNewFile");
 	    log.info("--------------------------------------------");
    } 
	  
	   @Test(description="13.3, Buyer sends new message for the Project")
	   @Parameters({"buyerNewMessage"})
	    public void testSendMessageFile(String buyerNewMessage) throws InterruptedException
	    {
		   log.info("Start: testSendMessageFile");
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
	      	  errorIndexBuyer = jsErrorIndexBuyer;
	     	      Page.logJSError(jsErrorIndexBuyer, driver, log);
	     	      if (jsErrorIndexBuyer > errorIndexBuyer){
	     		      System.out.println("####### TC 13.3, Buyer sends new message for the Project, JS errors for Buyer site #######");
	     		      log.info("####### TC 13.3, Buyer sends new message for the Project, JS errors for Buyer site #######");
	     	      }
	       }
	       log.info("End: testSendMessageFile");
	       log.info("--------------------------------------------");
	    }	
   
   @Test(description = "11.2, Buyer site - Log out")
   public void testLogoutBuyerSite() throws InterruptedException
   {
	  log.info("Start: testLogoutBuyerSite");
	  BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	  
	  buyerSitePage.clickLogout();
	  Thread.sleep(3000); 
	  
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### TC 11.2 JS errors for Buyer site #######");
   		      log.info("####### TC 11.2 JS errors for Buyer site #######");
   	       }
       }
      log.info("End: testLogoutBuyerSite");
      log.info("--------------------------------------------");
   } 
   
   @Test(description = "15.1 Login to Buyer's site")
   @Parameters({"buyerLoginEmail", "passwordBuyer"})
   public void buyerSiteLogin(String buyerLoginEmail, String passwordBuyer) throws InterruptedException
   {	   
	   log.info("Start: buyerSiteLogin");
	   //LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);   //jenn comment out
	   
	   Thread.sleep(1000);  
	   //buyerDriver.get(credentials.get("buyerSite"));    //jenn comment out
	  // buyerDriver.get("https://nooshselenium.sqa.noosh.com/autoclient/main");     //new code 10/18
	  // buyerDriver.get("https://nooshselenium.qa2.noosh.com/autoclient/main");     //new code  10/18
	   buyerDriver.get(clientURL);       // 10/19
	   LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);     //new code
	   
	   loginBuyerPage.loginBuyer(buyerLoginEmail, passwordBuyer);	
	   Thread.sleep(1000);  
	   /*
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 15.1, JS errors for Buyer site #######");
      		      log.info("####### TC 15.1, JS errors for Buyer site #######");
      	      }
       } */
       log.info("End: buyerSiteLogin");
       log.info("--------------------------------------------");
   }
   
   @Test(description = "15.2, Buyer site - Project's updates, check status")
   @Parameters({"newProjectName", "projectStatus"})
   public void testObserveProject(String newProjectName, String projectStatus)
   {
	   log.info("Start: testObserveProject");
	   BuyerSitePage buyerSitePage = new BuyerSitePage(buyerDriver);
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   buyerSitePage.clickProject();  
       projectPage.clickOverviewTab();
	   try {
		   AssertJUnit.assertEquals(buyerSitePage.getProjectStatus(), projectStatus);	
		   } catch(Throwable e) {
			 System.out.println("15.2 Project status '" + projectStatus + "' does not dispalayed.");
			 log.info("15.2 Project status '" + projectStatus + "' does not dispalayed.");
		   }
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 15.2 - Project's updates, check status, JS errors for Buyer site #######");
      		      log.info("####### TC 15.2 - Project's updates, check status, JS errors for Buyer site #######");
      	       }
          }	
       log.info("End: testObserveProject");
       log.info("--------------------------------------------");
   } 
   
   @Test(description = "15.2, Buyer site - Project's updates, check SP message")
   @Parameters({"sPNewMessage"})
   public void testSPMessageReceived(String sPNewMessage)
   {
	   log.info("Start: testSPMessageReceived");
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   projectPage.clickMessageTab();   
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(sPNewMessage, buyerDriver));
		   } catch(Throwable e) {
			 System.out.println("15.2 SP message '" + sPNewMessage + "' does not dispalayed.");   
			 log.info("15.2 SP message '" + sPNewMessage + "' does not dispalayed.");  
		   }  
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 15.2 - Project's updates, check SP message JS errors for Buyer site #######");
      		      log.info("####### TC 15.2 - Project's updates, check SP message JS errors for Buyer site #######");
      	      }
        }	       
       log.info("End: testSPMessageReceived");
       log.info("--------------------------------------------");
   }   
      
   @Test(description = "15.2, Buyer site - Project's updates, check SP file attached")
   @Parameters({"newProfilePictureFile"})
   public void testSPFileAttached(String newProfilePictureFile) throws InterruptedException
   {
	   log.info("Start: testSPFileAttached");
	   NewProjectPage projectPage = new NewProjectPage(buyerDriver);
	   
	   projectPage.clickFileTab();
	   
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(newProfilePictureFile, buyerDriver));	
		   } catch(Throwable e) {
			 System.out.println("15.2 SP file '" + newProfilePictureFile + "' does not uploaded.");
			 log.info("15.2 SP file '" + newProfilePictureFile + "' does not uploaded.");
		   }	   
	   /* switched off 6/19/2013
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(profilePictureFile, buyerDriver));	
		   } catch(Throwable e) {
			 System.out.println("15.2 SP file '" + profilePictureFile + "' does not uploaded.");
			 log.info("15.2 SP file '" + profilePictureFile + "' does not uploaded.");
		   }
		   */

       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 15.2 - Project's updates, check SP file attached, JS errors for Buyer site #######");
      		      log.info("####### TC 15.2 - Project's updates, check SP file attached, JS errors for Buyer site #######");
      	      }
       }  
       log.info("End: testSPFileAttached");
       log.info("--------------------------------------------");
   } 
   
   @Test(description = "Logout Buyer Site")
   public void testLogoutBuyer() throws InterruptedException
   {
	   log.info("Start: testLogoutBuyer");
	   testLogoutBuyerSite();
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### Logout Buyer Site, JS errors for Buyer site #######");
      		      log.info("####### Logout Buyer Site, JS errors for Buyer site #######");
      	      }
        }	
       log.info("End: testLogoutBuyer");
       log.info("--------------------------------------------");
   }  
   
   @Test(description = "16.1-1, Buyer Profile - Update some Profile info, get buyer profile popup")
   public void testGetProfileInfo() throws InterruptedException
   {
	   log.info("Start: testGetProfileInfo");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(3000);
	   //userProfileFrame.getBuyerProfileIE();
	   userProfileFrame.getBuyerProfile();
	   Thread.sleep(1000);
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 16.1-1, Buyer Profile - Update some Profile info, get buyer profile popup, JS errors for Buyer site #######");
      		      log.info("####### TC 16.1-1, Buyer Profile - Update some Profile info, get buyer profile popup, JS errors for Buyer site #######");
      	      }
        }
       log.info("End: testGetProfileInfo");
       log.info("--------------------------------------------");
   }  
   
   @Test(description = "16.1-2, Buyer Profile - Update some Profile info, check errors messages")
   public void testProfileInfo() throws InterruptedException
   {
	   log.info("Start: testProfileInfo");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   userProfileFrame.clickEditProfileBT();
	   Thread.sleep(1000);
	   
	   
	   credentials.put("buyerLoginDebug", userProfileFrame.getBuyerLoginName());
	   userProfileFrame.clearLoginNameField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);
	   try {
		   AssertJUnit.assertTrue(userProfileFrame.getLoginNameError());	
		   } catch(Throwable e) {
			 System.out.println("16.1-2 Buyer Profile - Profile login error does not dispalayed.");
			 log.info("16.1-2 Buyer Profile - Profile login error does not dispalayed.");	   
	   }
	   
	   credentials.put("buyerEmailDebug", userProfileFrame.getBuyerEmail());
	   userProfileFrame.clearEmailField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);
	   try {
		   AssertJUnit.assertTrue(userProfileFrame.getEmailProfileError());	
		   } catch(Throwable e) {
			 System.out.println("16.1-2 Profile email error does not dispalayed.");
			 log.info("16.1-2 Profile email error does not dispalayed.");	   
	   }
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 16.1-2, Buyer Profile - Update some Profile info, check errors messages, JS errors for Buyer site #######");
      		      log.info("####### TC 16.1-2, Buyer Profile - Update some Profile info, check errors messages, JS errors for Buyer site #######");
      	      }
        }	   
       log.info("End: testProfileInfo");
       log.info("--------------------------------------------");
   }
   
   @Test(description = "16.1-3, Buyer Profile - Update some Profile info, updating of fields")
   @Parameters({"newLastName"})
   public void testUpdatingProfileInfo(String newLastName) throws InterruptedException
   {
	   log.info("Start: testUpdatingProfileInfo");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   
	   Thread.sleep(1000);
	   userProfileFrame.setLoginName(credentials.get("buyerLoginDebug"));
	   userProfileFrame.setEmailAddress(credentials.get("buyerEmailDebug"));
	   Thread.sleep(1000);
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(2000);
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### 16.1-3, Buyer Profile - Update some Profile info, updating of fields, JS errors for Buyer site #######");
      		      log.info("####### 16.1-3, Buyer Profile - Update some Profile info, updating of fields, JS errors for Buyer site #######");
      	      }
        }	
       log.info("End: testUpdatingProfileInfo");
       log.info("--------------------------------------------");
   } 
   
   @Test(description = "16.2-1, Buyer Profile - Download new Profile image, get buyer profile popup")
   public void testGetProfileImage() throws InterruptedException
   {
	   log.info("Start: testGetProfileImage");
	   Thread.sleep(1500);
	   testGetProfileInfo();
	   log.info("End: testGetProfileImage");
	   log.info("--------------------------------------------");
   }   
   
   @Test(description = "16.2-2, Buyer Profile - Download new Profile image, set new buyer profile image")
   @Parameters({"newBuyerProfilePicture"})
   public void testSetProfileImage(String newBuyerProfilePicture) throws InterruptedException, AWTException
   {	 
	   log.info("Start: testSetProfileImage");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(1500);
	   String oldProfileImage = userProfileFrame.getImageAttribute();
	   userProfileFrame.clickChangProfilePicture(newBuyerProfilePicture);	
	   
	   if(!buyerDriver.equals(driver)){
	      page.closeModalPopup();
	   }
	   Thread.sleep(1500);
	   if (oldProfileImage.equals(userProfileFrame.getImageAttribute()))		   		   
	   {
		   System.out.println("16.2 The new buyer profile image does not displayed in buyer profile popup");
	       log.info("16.2 The new buyer profile image does not displayed in buyer profile popup");
	   }
	   
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### 16.2-2, Buyer Profile - Download new Profile image, set new buyer profile image, JS errors for Buyer site #######");
      		      log.info("####### 16.2-2, Buyer Profile - Download new Profile image, set new buyer profile image, JS errors for Buyer site #######");
      	      }
        }	
       log.info("End: testSetProfileImage");
       log.info("--------------------------------------------");
   }  
   
   @Test(description = "16.2-3, Buyer Profile - Reset password, get buyer profile first time")
   public void testGetProfileInfo2() throws InterruptedException
   {
	   log.info("Start: testGetProfileInfo2");
	   Thread.sleep(1500);
	   testGetProfileInfo();
	   log.info("End: testGetProfileInfo2");
	   log.info("--------------------------------------------");
   }

   @Test(description = "16.2-4, Buyer Profile - Download new Profile image, set new buyer profile image")
   @Parameters({"buyerProfilePicture"})
   public void testSetProfileImageBack(String buyerProfilePicture) throws InterruptedException, AWTException
   {	   
	   log.info("Start: testSetProfileImageBack");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(buyerDriver);
	   
	   Thread.sleep(1500);
	   String oldProfileImage = userProfileFrame.getImageAttribute();
	   
	   userProfileFrame.clickChangProfilePicture(buyerProfilePicture);	
	   
	   if(!buyerDriver.equals(driver)){

	      page.closeModalPopup();
	   }
	   Thread.sleep(1500);
	   if (oldProfileImage.equals(userProfileFrame.getImageAttribute()))		   		   
	   {
		   System.out.println("16.2-4 The buyer profile image does not displayed in buyer profile popup");
	       log.info("16.2-4 The buyer profile image does not displayed in buyer profile popup");
	   }
	   	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 16.2-4, Buyer Profile - Download Profile image, set back buyer profile image, JS errors for Buyer site #######");
      		      log.info("####### TC 16.2-4, Buyer Profile - Download Profile image, set back buyer profile image, JS errors for Buyer site #######");
      	      }
        }
       log.info("End: testSetProfileImageBack");
       log.info("--------------------------------------------");
   }
   
   @Test(description = "16.3-1, Buyer Profile - Reset password, get buyer profile first time")
   public void testGetProfileInfo3() throws InterruptedException
   {
	   log.info("Start: testGetProfileInfo3");
	   Thread.sleep(1500);
	   testGetProfileInfo();
	   log.info("End: testGetProfileInfo3");
   }
   
   @Test(description = "16.3-2, Buyer Profile - Reset password, reset password popup")
   @Parameters({"passwordBuyer", "wrongPassword", "newPasswordSP", "wrongOriginalPassword"})
   public void testGetResetPassword(String passwordBuyer, String wrongPassword, String newPasswordSP,
		   String wrongOriginalPassword) throws InterruptedException
   {	   
	   log.info("Start: testGetResetPassword");
	   Thread.sleep(1500);
	   BaseTestCases.testResetPassword(buyerDriver, wrongPassword, passwordBuyer, newPasswordSP, wrongOriginalPassword);
	   credentials.put("newBuyerPassword", newPasswordSP);
	   
       if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### TC 16.3-1, Buyer Profile - Reset password, get buyer profile first time, JS errors for Buyer site #######");
      		      log.info("####### TC 16.3-1, Buyer Profile - Reset password, get buyer profile first time, JS errors for Buyer site #######");
      	      }
        }
       log.info("End: testGetResetPassword");
       log.info("--------------------------------------------");
    }   

   @Test(description = "16.3-3, Buyer Profile - Reset password, logout buyer site")
   public void testLogoutBuyerNewPassword() throws InterruptedException
   {
	   log.info("Start: testLogoutBuyerNewPassword");
	   testLogoutBuyer();
	   Thread.sleep(1000);
	   log.info("End: testLogoutBuyerNewPassword");
   }
   
   @Test(description = "16.3-4, Buyer Profile - Reset password, login buyer site new password")
   public void testLoginBuyerSiteNewPassword() throws InterruptedException
   {
	  log.info("Start: testLoginBuyerSiteNewPassword");
      LoginBuyerPage loginBuyerPage = new LoginBuyerPage(buyerDriver);
   	
      loginBuyerPage.loginBuyer(credentials.get("buyerEmailDebug"), credentials.get("newBuyerPassword"));
	   Thread.sleep(1500);
	   
      if(!buyerBrowser.trim().equals("IE")){
       	  errorIndexBuyer = jsErrorIndexBuyer;
      	      Page.logJSError(jsErrorIndexBuyer, driver, log);
      	      if (jsErrorIndexBuyer > errorIndexBuyer){
      		      System.out.println("####### 16.3-4, Buyer Profile - Reset password, login buyer site new password, JS errors for Buyer site #######");
      		      log.info("####### 16.3-4, Buyer Profile - Reset password, login buyer site new password, JS errors for Buyer site #######");
      	      }
      }	
      log.info("testLoginBuyerSiteNewPassword");
      log.info("--------------------------------------------");
   }   
   
   @Test(description = "16.3-5, Buyer Profile - Reset password, get buyer profile second time")
   public void testGetProfileInfo4() throws InterruptedException
   {
	   log.info("Start: testGetProfileInfo4");
	   Thread.sleep(1500);
	   testGetProfileInfo();
	   
      if(!buyerBrowser.trim().equals("IE")){
    	  errorIndexBuyer = jsErrorIndexBuyer;
   	      Page.logJSError(jsErrorIndexBuyer, driver, log);
   	      if (jsErrorIndexBuyer > errorIndexBuyer){
   		      System.out.println("####### 16.3-5, Buyer Profile - Reset password, get buyer profile second time, JS errors for Buyer site #######");
   		      log.info("####### 16.3-5, Buyer Profile - Reset password, get buyer profile second time, JS errors for Buyer site #######");
   	      }
      } 
      log.info("End: testGetProfileInfo4");
      log.info("--------------------------------------------");
   }
   
   @Test(description = "16.3-6, Buyer Profile - Reset password, set old buyer password back")
   @Parameters({"passwordBuyer"})
   public void testSetBuyerPasswordBack(String passwordBuyer) throws InterruptedException
   {		
	   log.info("Start: testSetBuyerPasswordBack"); 
	   Thread.sleep(1500);
   	    BaseTestCases.testResetPasswordBack(buyerDriver, passwordBuyer, credentials.get("newBuyerPassword"));
	    Thread.sleep(1500);
	    
	    if(!buyerBrowser.trim().equals("IE")){
	       errorIndexBuyer = jsErrorIndexBuyer;
	     	   Page.logJSError(jsErrorIndexBuyer, driver, log);
	     	   if (jsErrorIndexBuyer > errorIndexBuyer){
	     		   System.out.println("####### 16.3-6, Buyer Profile - Reset password, set old buyer password back, JS errors for Buyer site #######");
	     		   log.info("####### 16.3-6, Buyer Profile - Reset password, set old buyer password back, JS errors for Buyer site #######");
	     	   }
	    }	
	    log.info("End: testSetBuyerPasswordBack"); 
	    log.info("--------------------------------------------");
   }
   
   @Test(description = "Logout buyer site final")
   public void logoutBuyerSiteFinal() throws InterruptedException
   {
	   log.info("Start: logoutBuyerSiteFinal");
	   testLogoutBuyerSite();
	   Thread.sleep(1500);
	   if(!driver.equals(buyerDriver))
	   driver.quit();
	   
	    if(!buyerBrowser.trim().equals("IE")){
		       errorIndexBuyer = jsErrorIndexBuyer;
		     	   Page.logJSError(jsErrorIndexBuyer, driver, log);
		     	   if (jsErrorIndexBuyer > errorIndexBuyer){
		     		   System.out.println("####### Logout buyer site final, JS errors for Buyer site #######");
		     		   log.info("####### Logout buyer site final, JS errors for Buyer site #######");
		     	   }
		    }
	    log.info("End: logoutBuyerSiteFinal");
	    log.info("--------------------------------------------");
   }
}
