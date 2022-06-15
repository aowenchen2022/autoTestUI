/***********************************************************************
* This class tests the functionality of SP and client 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;

import org.testng.annotations.Parameters;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.awt.AWTException;

public class TestSPClient1 extends BaseSeleniumTest {  
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
   static String baseUrl;
   static String parentWindowHandle1;
   static String projectName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup";
		   baseUrlPro = "https://spd." + companyName + ".com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium.spd." + companyName + ".com/service/login";
		   clientURL = "https://nooshselenium.spd." + companyName + ".com/autoclient/main";
	   } else {
		   baseUrl = "https://demo." + domain + "." + companyName + ".com/service/signup";
		   baseUrlPro = "http://" + domain + "." + companyName + ".com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2." + domain + "." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + domain + "." + companyName + ".com/service/login";
		   clientURL = "https://nooshselenium." + domain + "." + companyName + ".com/autoclient/main";
	   }
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spClientTest1 *****\n\n");
   }
   
   @Test(description="Log in to Noosh Pro") 
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
		  System.out.println("This is not 'Noosh > Login' page title");
		  log.info("This is not 'Noosh > Login' page title");
	  }	
      */
      // Login and call MyNooshPage 
      loginPage.loginUser("demo@noosh.com", passwordSP); 
      Thread.sleep(1000);
	  log.info("End: testLoginPage");
	  log.info("--------------------------------------------");
   }   
   
	@Test(description = "Precondition: check the Workgroup Preferences options ")
	@Parameters({ "AdminLoginN", "AdminPassword", "BuyberWorkGroupNmae", "SupplierWorkGroupNmae" })
	public void testCheckWGpreferences(String AdminLoginN, String AdminPassword, String BuyberWorkGroupNmae, String SupplierWorkGroupNmae)
			throws InterruptedException {
		log.info("Start: testAdminLogin");
		testBuyerLogin(AdminLoginN, AdminPassword);
		log.info("End: testAdminLogin");
		searchWG(BuyberWorkGroupNmae);
		Thread.sleep(2000);
		driver.findElement(By.linkText(BuyberWorkGroupNmae)).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Workgroup Preferences")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("edit")).click();
		Thread.sleep(1000);
		WebElement dcCheckBox = driver.findElement(By.name("WORKGROUP_OPTION_MULTIPLE_CURRENCY"));
		WebElement ssCheckBox = driver.findElement(By.name("WORKGROUP_OPTION_SELL_SIDE_MULTIPLE_CURRENCY"));
		clickCheckBox(dcCheckBox, true);
		clickCheckBox(ssCheckBox, true);
		driver.findElement(By.id("update")).click();
		Thread.sleep(1000);
		
		searchWG(SupplierWorkGroupNmae);
		List <WebElement> lnks = driver.findElements(By.linkText(SupplierWorkGroupNmae));
		lnks.get(1).click();
		driver.findElement(By.id("edit_workgroup")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("transactionalCurrencyId"))).selectByValue("5");
		Thread.sleep(1000);
		driver.findElement(By.id("update_workgroup")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Workgroup Preferences")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("edit")).click();
		Thread.sleep(1000);
		WebElement CheckBox = driver.findElement(By.name("WORKGROUP_OPTION_MULTIPLE_CURRENCY"));
		clickCheckBox(CheckBox, true);
		driver.findElement(By.id("update")).click();
		Thread.sleep(1000);
				       
		log.info("--------------------------------------------");
	}
	
	   public static void searchWG(String WGname) throws InterruptedException {
			driver.findElement(By.linkText("Internal")).click();
			Thread.sleep(1500);
			driver.findElement(By.id("workgroupSearchString")).clear();
			driver.findElement(By.id("workgroupSearchString")).sendKeys(WGname);
			new Select(driver.findElement(By.id("searchFilterStr"))).selectByValue("0");
			new Select(driver.findElement(By.id("group_type"))).selectByValue("1000000");
			new Select(driver.findElement(By.id("dateFilterStr"))).selectByValue("ACTIVATION_DATE");
			Thread.sleep(1000);
			driver.findElement(By.className("frame")).findElement(By.tagName("table")).findElement(By.tagName("button")).click();
			Thread.sleep(2000);
	    }
	   
	    public static void clickCheckBox(Object obj, boolean flag) throws InterruptedException {
	        
	        WebElement   checkBoxElement = (WebElement) obj;
	        boolean a = (checkBoxElement.getAttribute("checked") == null);
	        if (a == flag) {
	            JavascriptExecutor executor = (JavascriptExecutor) driver;
	            executor.executeScript("arguments[0].click();", checkBoxElement);
	            Thread.sleep(1000);
	        }
	    }
	@Test(description = "Buyer login - step 1 ")
	@Parameters({ "BuyerLoginN", "AdminPassword" })
	public void testBuyerLogin(String BuyerLoginN, String AdminPassword)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(BuyerLoginN, AdminPassword);
		spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSPLogin");
		log.info("--------------------------------------------");
	}

   
   @Test(description="SSO-1, Go to Noosh Entry through sites menu")
   @Parameters({"myDeskPage", "profileTitlePro"}) 
   public void testMyDeskPage(String myDeskPage, String profileTitlePro) throws InterruptedException {    
	  log.info("Start: testMyDeskPage");
      MyDeskPage myNooshDeskPage = new MyDeskPage(driver);       
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, myDeskPage)); 
	  } catch(Throwable e) {
		  System.out.println("This is not 'Noosh > My Desk > Summary' page title");
		  log.info("This is not 'Noosh > My Desk > Summary' page title");
	  }	
      try{
      AssertJUnit.assertEquals(profileTitlePro,myNooshDeskPage.getProfileTitle());
	  } catch(Throwable e) {
		  System.out.println("This is not 'Demo User' profile title");
		  log.info("This is not 'Demo User' profile title");
	  }	
	  */     
	  Thread.sleep(1500);      
      myNooshDeskPage.getSitesList();   
      Thread.sleep(5500); 
	  log.info("End: testMyDeskPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description = "SSO-2, Go back to Noosh Pro through My desk")
   @Parameters({"siteListPageTitle", "profileTitlePro"}) 
   public void testSiteListPage(String siteListPageTitle, String profileTitlePro) 
                                        throws InterruptedException {
	  log.info("Start: testSiteListPage");
      SiteListPage siteListPage = new SiteListPage(driver);
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, siteListPageTitle));
	  } catch(Throwable e) {
		  System.out.println("This is not 'Site List - MillerZell - Noosh' page title");
		  log.info("This is not 'Site List - MillerZell - Noosh' page title");
	  }	 

	  try{
      AssertJUnit.assertEquals(profileTitlePro, siteListPage.getProfileTitle());
	  } catch(Throwable e) {
		  System.out.println("This is not 'Demo User' profile title");
		  System.out.println("This is " + siteListPage.getProfileTitle() + " profile title");
		  log.info("This is not 'Demo User' profile title");
		  log.info("This is " + siteListPage.getProfileTitle() + " profile title");
	  }	
	  */
	  Thread.sleep(1500);      
      siteListPage.getMyDeskPageBack();    
      Thread.sleep(1500); 
      log.info("End: testSiteListPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="SSO-3, Go to my dashboard")
   @Parameters({"myDeskPage"}) 
   public void testMyDeskPageBack(String myDeskPage) throws InterruptedException {
	   log.info("Start: testMyDeskPageBack");
	   MyDeskPage myDeskPageNoo = new MyDeskPage(driver);        
      /*
      try{
      AssertJUnit.assertTrue(page.checkPageTitle(driver, myDeskPage));
	  } catch(Throwable e) {
		  System.out.println("This is not 'Noosh > My Desk > Summary' page title");
		  System.out.println("This is " + page.getTitle(driver) + " page title");
		  log.info("This is not 'Noosh > My Desk > Summary' page title");
		  log.info("This is " + page.getTitle(driver) + " page title");
	  }	
	  */
	  Thread.sleep(1500);          
      myDeskPageNoo.getDashboard();  
      Thread.sleep(1500);
      log.info("End: testMyDeskPageBack");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="Log out")
   public void testLogoutPage() throws InterruptedException {  
	  log.info("Start: testLogoutPage");
	  /*
      try{
          AssertJUnit.assertTrue(page.checkPageTitle(driver, dashboardPageTitle));
	  } catch(Throwable e) {
		  System.out.println("This is not 'Dashboard - Noosh Demo - Noosh' page title");
		  log.info("This is not 'Dashboard - Noosh Demo - Noosh' page title");
	  }	 
	  */
	  CommonUtils commonUtils = new CommonUtils(driver);
	  //commonUtils.spLogout();     
	  log.info("End: testLogoutPage");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="SP sign up")
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
   
   @Test(description="SP onboarding click 'create project' on Home page")
   public void testSPClickCreateProj() throws InterruptedException {
	   log.info("Start: testSPClickCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickHomeCreateProj();    
	   Thread.sleep(3000);
	   log.info("End: testSPClickCreateProj");
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
   public void testSPClickCreateProjAgain() throws InterruptedException {
	   testSPClickCreateProj();
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
	   brochurePopup.getFilesTab();
	   page.uploadFileModalWindow(fileForProject1);
	   Thread.sleep(2000);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	   
	   brochurePopup.getReviewAndSubmitTab();
	   Thread.sleep(3000);
       brochurePopup.clickCreateBT();
       Thread.sleep(3000);
	   log.info("End: testSPcreateProjectProjectWizard");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="New SP request estimate")
   public void testNewSPRequestEstimate() throws InterruptedException {
	   log.info("Start: testNewSPRequestEstimate");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   Thread.sleep(2500);
	   commonUtils.testNewSPRequestEstimate(projectName);
	   log.info("End: testNewSPRequestEstimate");
	   log.info("--------------------------------------------"); 
   } 
   
   @Test(description="SP Request Estimate from Suppliers")
   @Parameters({"supplierName1", "supplierName2"})
   public void testRequestEstimateSupplier(String supplierName1, String supplierName2)  throws InterruptedException {
	   log.info("Start: testRequestEstimateSupplier");
	   //supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@gmail.com";
	   supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@noosh.com";
	   
	   Thread.sleep(2000);
	   SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);
	   
	   Thread.sleep(2000);
	   supplierEstimatePopup.setSupplierName(supplierUser1, supplierName2);
	   Thread.sleep(2000);                   
	   Thread.sleep(500);
	   supplierEstimatePopup.callCalendar();	               
	   Thread.sleep(2000);
	   supplierEstimatePopup.callRequestEstimatesBtn();
	   Thread.sleep(5000);
	   log.info("End: testRequestEstimateSupplier");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP logout")
   public void testLogout() throws InterruptedException {
	   log.info("Start: testLogout");
	   Thread.sleep(8000);
	   SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
	   Thread.sleep(2000);
	   supplierMainMenuPage.clickLogout();
	   Thread.sleep(3000);
	   log.info("End: testLogout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Exsiting user login")
   @Parameters({"passwordSP", "spEmailSmoke"})
   public void testLoginDemoSqaPageSmoke(String passwordSP, String spEmailSmoke) throws InterruptedException {
	   log.info("Start: testLoginDemoSqaPageSmoke");
	   Thread.sleep(1500);
	   driver.get(baseUrlSmoke); 
	   Thread.sleep(1500);     
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
	   loginDemoSqa.loginUser(spEmailSmoke, passwordSP);   
	   Thread.sleep(2000);
	   spSiteLoginPage = driver.getCurrentUrl();	
	   log.info("End: testLoginDemoSqaPageSmoke");
	   log.info("--------------------------------------------");
   } 
 
   @Test(description="SP customization - Download Login page/ Header images")
   @Parameters({"headerLogo", "loginContentImage", "imageLogoFile", "imageLoginFile"})
   public void testDownloadLoginPageImages(String headerLogo, String loginContentImage, 
		               String imageLogoFile, String imageLoginFile) throws InterruptedException {
	   log.info("Start: testDownloadLoginPageImages");
	   String logoImageCustomTab, logoImagePreviewTab, logoImageSPTab, loginImageImageCustomTab, 
	          loginImagePreviewTab, logoImageDashboardPreviewTab;
	   CommonUtils commonUtils = new CommonUtils(driver);
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   Thread.sleep(1500);
	   spSiteLoginPage = driver.getCurrentUrl();
	   spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - 4);
	   System.out.println("Home page url:" + spSiteLoginPage);
	   driver.get(spSiteLoginPage + "admin");
	   Thread.sleep(3500);
	   commonUtils.testGoToSPAdminBrandingPage();	          
	   Thread.sleep(1500);	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageCustomTab());	
	   } catch(Throwable e) {
			  System.out.println("Logo image not displayed in Image Customization tab");
			  log.info("Logo image not displayed in Image Customization tab");
	   }	 
	   
	   logoImageCustomTab = adminCustomization.getLogoImageCustomTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImagePreviewTab());
	   } catch(Throwable e) {
			  System.out.println("Logo image not displayed in Login Preview Image Customization tab");
			  log.info("Logo image not displayed in Login Preview Image Customization tab");
	   }
	   
	   logoImagePreviewTab = adminCustomization.getLogoImagePreviewTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLogoImageSPSite());
	   } catch(Throwable e) {
			  System.out.println("Logo image not displayed in main SP page, in the left top corner");
			  log.info(" Logo image not displayed in main SP page, in the left top corner");
	   }
	   
	   logoImageSPTab = adminCustomization.getLogoImageSPSite();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginImageImageCustomTab());
	   } catch(Throwable e) {
			  System.out.println("Login Content image not displayed in Image Customization tab");
			  log.info("Login Content image not displayed in Image Customization tab");
	   }
	   
	   loginImageImageCustomTab = adminCustomization.getLoginImageImageCustomTab();	   
	   try {
	          AssertJUnit.assertTrue(adminCustomization.checkLoginImagePreviewTab());	      
	   } catch(Throwable e) {
			  System.out.println("Login Content image not displayed in Login Preview Image Customization tab");
			  log.info("Login Content image not displayed in Login Preview Image Customization tab");
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
			 log.info("New logo image not displayed in Image Customization tab");
	   }
	   if (logoImagePreviewTab.trim().equals(adminCustomization.getLogoImagePreviewTab()))
	   {
			 System.out.println("5.1 New logo image not displayed in Login Preview Image Customization tab");
			 log.info("New logo image not displayed in Login Preview Image Customization tab");
	   }
	   if (logoImageSPTab.trim().equals(adminCustomization.getLogoImageSPSite()))
	   {
			 System.out.println("5.1 New logo image not displayed in main SP page, in the left top corner");
			 log.info("New logo image not displayed in main SP page, in the left top corner");
	   }
	   if (loginImageImageCustomTab.trim().equals(adminCustomization.getLoginImageImageCustomTab()))
	   {
			 System.out.println("5.1 New login Content image not displayed in Image Customization tab");
			 log.info("New login Content image not displayed in Image Customization tab");
	   }	
	   log.info("End: testDownloadLoginPageImages");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP customization - Download Login page/ Header images, return images back")
   @Parameters({"headerLogo1", "loginContentImage1"})
   public void testDownloadLoginPageImagesBack(String headerLogo1, String loginContentImage1) throws InterruptedException {
	   log.info("Start: testDownloadLoginPageImagesBack");	   
	   Thread.sleep(1500);
//	   newHomePage.clickGearSubmenuItem(driver, "Branding");	   
	   spSiteLoginPage = driver.getCurrentUrl();
	   int idx = spSiteLoginPage.indexOf("/service/");
	   spSiteLoginPage = spSiteLoginPage.substring(0, idx);
	   System.out.println("Home page url:" + spSiteLoginPage);
	   
	   Thread.sleep(1500);
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   adminCustomization.clickLoginPreviewLink();
	   Thread.sleep(1000);
	   adminCustomization.clickDashboardPreviewLink();	   
	   Thread.sleep(1000);
	   adminCustomization.clickUploadImagesBT();	   
	   Thread.sleep(1500);
	   // headerLogo 
	   adminCustomization.uploadHeaderLogo(headerLogo1);
	   Thread.sleep(1500);
	   adminCustomization.uploadLoginContentImage(loginContentImage1);	   
	   Thread.sleep(1500);
	   adminCustomization.clickSaveImagesBT();	   
	   Thread.sleep(2000);
       log.info("End: testDownloadLoginPageImagesBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP customization - Login page customization")
   @Parameters({"loginBoxColor", "loginBoxTextColor"})
   public void testLoginPageCustomization(String loginBoxColor, String loginBoxTextColor) throws InterruptedException {
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
		   System.out.println("New Login box color doesn't displayed in Login customization tab");
           log.info("New Login box color doesn't displayed in Login customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(loginBoxTextColor, driver));     
	   } catch(Throwable e) {
		   System.out.println(" New Login box Text color doesn't displayed in Login customization tab");
		   log.info("New Login box Text color doesn't displayed in Login customization tab");
	   }
	   Thread.sleep(1500); 
	   log.info("End: testLoginPageCustomization");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP customization - Login page customization, colors back")
   @Parameters({"loginBoxColor1", "loginBoxTextColor1"})
   public void testLoginPageCustomizationBack(String loginBoxColor1, String loginBoxTextColor1) throws InterruptedException {
	   log.info("Start: testLoginPageCustomizationBack");
	   AdminCustomizationFrame adminCustomization = new AdminCustomizationFrame(driver);
	   adminCustomization.clickLoginCustomizationTab();	   
	   adminCustomization.clickEditColorsBT();	   
	   adminCustomization.setLoginBoxColor(loginBoxColor1);
	   adminCustomization.setLoginBoxTextColor(loginBoxTextColor1);	   
	   adminCustomization.clickSaveLoginColorsBT();
	   Thread.sleep(1500);
	   log.info("End: testLoginPageCustomizationBack");
	   log.info("--------------------------------------------");
   }
  
   @Test(description="SP customization - Header customization")
   @Parameters({"headerBackgroundColor", "headerTextColor", "menuBackgoundColor", "menuTextColor"})
   public void testHeaderCustomization(String headerBackgroundColor, String headerTextColor, 
		                       String menuBackgoundColor, String menuTextColor) throws InterruptedException {
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
	   } catch(Throwable e) {
		   System.out.println("New Header Background color doesn't set in Header customization tab");
		   log.info("New Header Background color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(headerTextColor, driver));     
	   } catch(Throwable e) {
		   System.out.println(" New Header Text color doesn't set in Header customization tab");
		   log.info("New Header Text color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(menuBackgoundColor, driver));      
	   } catch(Throwable e) {
		   System.out.println("New Menu Background color doesn't set in Header customization tab");
		   log.info("New Menu Background color doesn't set in Header customization tab");
	   }
	   try {
		   AssertJUnit.assertTrue(Page.isTextPresent(menuTextColor, driver));	      
	   } catch(Throwable e) {
		   System.out.println("New Menu Text color doesn't set in Header customization tab");
		   log.info("New Menu Text color doesn't set in Header customization tab");
	   }	   
	   log.info("End: testHeaderCustomization");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP customization - Header customization, color back")
   @Parameters({"headerBackgroundColor1", "headerTextColor1", "menuBackgoundColor1", "menuTextColor1"})
   public void testHeaderCustomizationBack(String headerBackgroundColor1, String headerTextColor1, 
		            String menuBackgoundColor1, String menuTextColor1) throws InterruptedException {
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
	   log.info("End: testHeaderCustomizationBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Update some Profile info")
   @Parameters({"loginNameErrorMessage", "emailErrorMessage", "firstNameSP", "newLastNameSP"})
   public void testProfileInfoUpdate(String loginNameErrorMessage, String emailErrorMessage, String firstNameSP, 
		                        String newLastNameSP) throws InterruptedException {   
	   log.info("Start: testProfileInfoUpdate");
	   String fullName = firstNameSP + " " + newLastNameSP;	   
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);	   	   	
	   userProfileFrame.clickProfileUserName();	  
	   Thread.sleep(6000);
	   userProfileFrame.clickEditProfileBT();  
	   Thread.sleep(1500);	  
	   userProfileFrame.clearLoginNameField();
	   Thread.sleep(500);
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(loginNameErrorMessage, driver));	      
	   } catch(Throwable e) {
		   System.out.println("'Please input Login Name' message not displayed");
		   log.info("'Please input Login Name' message not displayed");
	   }	
	   userProfileFrame.clearEmailField();
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(emailErrorMessage, driver));	      
	   } catch(Throwable e) {
		   System.out.println("'Please input Email address' message not displayed");
		   log.info("'Please input Email address' message not displayed");
	   }
	   userProfileFrame.setLoginName(emailSP);	
	   System.out.println("emailSP="+emailSP);
	   userProfileFrame.setEmailAddress(emailSP);
	   userProfileFrame.setNewLastName(newLastNameSP);
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(1000);	   	   
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(fullName, driver));	      
	   } catch(Throwable e) {
		   System.out.println("User profile name does not updated");
		   log.info("User profile name does not updated");
	   }  
	   log.info("End: testProfileInfoUpdate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Update some Profile info, set data back")
   @Parameters({"spEmailSmoke"})
   public void testProfileInfoUpdateBack(String spEmailSmoke) throws InterruptedException {   	   
	   log.info("Start: testProfileInfoUpdateBack");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);	   	   	
	   Thread.sleep(1000);
	   userProfileFrame.clickProfileUserName();
	   Thread.sleep(5000);
	   userProfileFrame.clickEditProfileBT();
	   Thread.sleep(3000);	   
	   userProfileFrame.setLoginName(spEmailSmoke);	   
	   userProfileFrame.setEmailAddress(spEmailSmoke);
	   userProfileFrame.setNewLastName("SP");
	   userProfileFrame.clickSaveProfileBT();
	   Thread.sleep(2000);
	   log.info("End: testProfileInfoUpdateBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Download new Profile image")
   @Parameters({"newProfilePicture"})
   public void testDownloadNewProfileImage(String newProfilePicture) throws InterruptedException, AWTException {
	   log.info("Start: testDownloadNewProfileImage");
	   Thread.sleep(1000);
	   page.uploadFileModalWindow(newProfilePicture);   
	   driver.findElement(By.id("profileChangePicture")).click();   
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(2000);	
	   log.info("End: testDownloadNewProfileImage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Download back old Profile image ")
   @Parameters({"oldProfilePicture"})
   public void testDownloadProfileImageBack(String oldProfilePicture) throws InterruptedException, AWTException {
	   log.info("Start: testDownloadProfileImageBack");
	   String oldProfileImage;
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   userProfileFrame.getImageAttribute();
	   Thread.sleep(1500);
	   oldProfileImage = userProfileFrame.getHeadProfileImageAttribute();	   	   
	   userProfileFrame.clickChangProfilePicture(oldProfilePicture);
       Thread.sleep(1000);	   
	   userProfileFrame.getHeadProfileImageAttribute();
	   if (oldProfileImage.equals(userProfileFrame.getHeadProfileImageAttribute())) {
		   System.out.println("The profile image does not displayed in the head user profile");
	       log.info("The profile image does not displayed in the head user profile");
	   }
	       Thread.sleep(1500);	   	
	   log.info("End: testDownloadProfileImageBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Reset passowrd")
   @Parameters({"wrongPasswordSP", "passwordSP", "newPasswordSP", "wrongOriginalPasswordMessage"})
   public void testResetPassword(String wrongPasswordSP, String passwordSP, String newPasswordSP,
		   String wrongOriginalPasswordMessage) throws InterruptedException {
	   log.info("Start: testResetPassword");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
	   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
	   
	   userProfileFrame.clickProfileUserName();
	   userProfileFrame.clickResetPassword();
	   userProfileFrame.setOriginalPassword(wrongPasswordSP);
	   userProfileFrame.setNewPassword(passwordSP);
	   userProfileFrame.setConfirmPassword(passwordSP);
	   userProfileFrame.clickChangePasswordBT();
	   Thread.sleep(2000);
	   try {
	       AssertJUnit.assertTrue(Page.isTextPresent(wrongOriginalPasswordMessage, driver));
	   } catch(Throwable e) {
		   System.out.println("'The original password is not correct!' does not displayed");
		   log.info("'The original password is not correct!' does not displayed");
	   }
	   userProfileFrame.setOriginalPassword(passwordSP);
	   userProfileFrame.setNewPassword(newPasswordSP);
	   userProfileFrame.setConfirmPassword(newPasswordSP);
	   userProfileFrame.clickChangePasswordBT();
       Thread.sleep(1500);	   
       registerSPSitePage.logoutSPSite();
	   log.info("End: testResetPassword");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Reset passowrd, login with new password")
   @Parameters({"spEmailSmoke", "newPasswordSP"})
   public void testLoginBackNewPasswordSmoke(String spEmailSmoke, String newPasswordSP) throws InterruptedException {
	   log.info("Start: testLoginBackNewPasswordSmoke");
       LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);   
       loginDemoSqa.loginUser(spEmailSmoke, newPasswordSP);  
       Thread.sleep(3000); 
	   log.info("End: testLoginBackNewPasswordSmoke");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP user Profile - Reset password back")
   @Parameters({"passwordSP", "newPasswordSP"})
   public void testResetPasswordBack(String passwordSP, String newPasswordSP) throws InterruptedException {
	   log.info("Start: testResetPasswordBack");
	   UserProfileFrame userProfileFrame = new UserProfileFrame(driver);
       RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);       
	   userProfileFrame.clickProfileUserName();
	   Thread.sleep(3000);
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
	   log.info("End: testResetPasswordBack");
	   log.info("--------------------------------------------");
   }  
   
   @Test(description="SP user profile - Login back with old password")
   @Parameters({"spEmailSmoke", "passwordSP"})
   public void testLoginBackOldPasswordSmoke(String spEmailSmoke, String passwordSP) throws InterruptedException {
	   log.info("Start: testLoginBackOldPasswordSmoke");
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
       Thread.sleep(1500);	   
       loginDemoSqa.loginUser(spEmailSmoke, passwordSP);	
	   log.info("End: testLoginBackOldPasswordSmoke");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp user profile - logout after user set back password")
   public void testSPLogout() throws InterruptedException {
	   log.info("Start: testSPLogout");
	   testLogout();
	   log.info("End: testSPLogout");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp user info - forgot password")
   @Parameters({"loginUserID"})
   public void testForgotPW(String loginUserID) throws InterruptedException {
	   log.info("Start: testForgotPW");
	   if (domain.equals("spd")) {
		   driver.get("http://selenium263945.noosh.com/service/login");
	   } else {
	       driver.get("http://selenium263945." + domain + ".noosh.com/service/login");
	   }
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   Thread.sleep(1000);
	   loginDemoSqa.clickForgotPasswordLink();
	   Thread.sleep(1000);
	   loginDemoSqa.putSPEmail(loginUserID);
	   Thread.sleep(5000);
       log.info("End: testForgotPW");
       log.info("--------------------------------------------");
   }
  /* 
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
*/
   @Test(description="sp user login to Gmail")
   @Parameters({"gmailLoginPW", "supplierName1"})
   public void testLoginToGmail(String gmailLoginPW, String supplierName1) throws InterruptedException {
 	  log.info("Start: testLoginToGmail");
 	  Thread.sleep(1000);
 	  driver.get("http://www.gmail.com");
 	  //CommonUtils commonUtils = new CommonUtils(driver);
 	  //commonUtils.testGmailLoginPreCon();
 	  //testGmailLoginPreCon();  
 	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
 	  Thread.sleep(2000);
 	  /*
 	  WebElement staySignInCookie = null;
	  try {
	      staySignInCookie = driver.findElement(By.id("PersistentCookie"));
	  } catch (Exception e) {
		  //do nothing
	  }
	  if (staySignInCookie != null) {
		  staySignInCookie.click();
	      Thread.sleep(1000);
	  }*/
 	  //gmailLoginPage.enterEmail(supplierName1+"@gmail.com");
 	  gmailLoginPage.enterEmail(supplierName1+"@noosh.com");
 	  Thread.sleep(1000);
 	  gmailLoginPage.enterPassword(gmailLoginPW);
 	  Thread.sleep(2000);
	  gmailLoginPage.signIn();
	  Thread.sleep(2000);
 	  log.info("End: testLoginToGmail");
 	  log.info("--------------------------------------------");
   }
   
   @Test(description="sp user check reset password email")
   public void testResetPWEmail() throws InterruptedException {
	   log.info("Start: testResetPWEmail");
	   Thread.sleep(5000);
	   List<WebElement> emails = driver.findElements(By.tagName("span"));
	   for (WebElement email : emails) {
		   if (email != null) {
			   String emailTitle = email.getText();
			   if (emailTitle.indexOf("Reset Password for Noosh Group") >= 0) {
				   email.click();
				   break;
			   }
		   }
	   }
	   Thread.sleep(2000);
	   driver.findElement(By.linkText("Reset Password")).click(); 
	   Thread.sleep(2500);  
	   log.info("End: testResetPWEmail");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="sp user reset password on Reset Password page")
   @Parameters({"spPassword"})
   public void testResetPW(String spPassword) throws InterruptedException {
	   log.info("Start: testResetPW");
	   parentWindowHandle1 = driver.getWindowHandle();
	   System.out.println("parent="+ parentWindowHandle1);
	   Set<String> windows = driver.getWindowHandles();
	   System.out.println("num of windows="+ windows.size());
	   for (String window : windows) {
	       if (!window.equals(parentWindowHandle1)) {
	 	       driver.switchTo().window(window);
	 	  	   break;
	 	   }
	   }
	   Thread.sleep(2500);
	   LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
	   Thread.sleep(2000);
	   loginDemoSqa.setNewSPPassword(spPassword);
	   Thread.sleep(2000);
	   log.info("End: testResetPW");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Existing SP login")
   @Parameters({"loginUserID"})
   public void loginTest(String loginUserID) throws InterruptedException {
	   log.info("Start: loginTest");
	   Thread.sleep(1000);
	   WebElement username = driver.findElement(By.id("username"));
	   username.clear();
	   username.sendKeys(loginUserID);
	   Thread.sleep(1000);
	   WebElement passwd = driver.findElement(By.id("password"));
	   passwd.clear();
	   passwd.sendKeys("17password");
	   Thread.sleep(1000);
	   WebElement loginBtn = driver.findElement(By.id("submitbutton"));
	   loginBtn.click();
	   Thread.sleep(3000);
	   log.info("End: loginTest");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP logout")
   public void logoutSPExsitingTest() throws InterruptedException {
	   log.info("Start: logoutSPExsiting");
	   testLogoutPage();
	   log.info("End: logoutSPExsiting");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Close gmail page")
   public void testCloseGmailPage() throws InterruptedException {
	   log.info("Start: testCloseGmailPage");
	   Thread.sleep(3000);
	   driver.close(); 
	   Thread.sleep(2000);
	   driver.switchTo().window(parentWindowHandle1); 
	   Thread.sleep(1000);
	   driver.get("https://mail.google.com/mail/?logout"); 
	   Thread.sleep(2000);
	   log.info("End: testCloseGmailPage");
	   log.info("--------------------------------------------");
   }
}
   
  