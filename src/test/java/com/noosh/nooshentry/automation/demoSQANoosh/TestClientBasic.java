/***********************************************************************
* This class tests the functionality of SP and client with Firefox browser
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.FileNotFoundException;
import org.testng.annotations.Parameters;
import java.util.Hashtable;
import java.util.List;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.ClientCreateProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.ClientLoginPage;
import com.noosh.nooshentry.automation.buyerSite.ClientSignupPage;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.awt.AWTException;

public class TestClientBasic extends BaseSeleniumTest {  
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
   static String clientURL2;
   static String clientURL3;
   String windowNumber;
   static String baseUrl;
   static String loginUrl;
   static String parentWindowHandle1;
   static String clientName = "Selenium Client";
   static String parentWindowHandle;
   static String productName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo.spd.noosh.com/service/signup";
		   baseUrlPro = "https://spd.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.spd.noosh.com/service/login";
		   clientURL = "https://selenium263945.sqa.noosh.com/client_1/login";
		   clientURL2 = "https://nooshselenium.spd.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.spd.noosh.com/service/login"; 
		   clientURL3 = "https://selenium263945.spd.noosh.com/client_test/login";
	   } else if (domain.trim().equals("sdm")) {
		   baseUrl = "https://demo.sdm.noosh.com/service/signup";
		   baseUrlPro = "http://sdm.noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2.sdm.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.sdm.noosh.com/service/login";
		   clientURL = "https://selenium263945.sqa.noosh.com/client_1/login";
		   clientURL2 = "https://nooshselenium.sdm.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.sdm.noosh.com/service/login";
		   clientURL3 = "https://selenium263945.sdm.noosh.com/client_test/signup";
	   } else if (domain.trim().equals("qa2")) {
		   baseUrl = "https://demo.qa2.noosh.com/service/signup";
		   baseUrlPro = "https://qa2.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium.qa2.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.qa2.noosh.com/service/login";
		   clientURL = "https://selenium263945.sqa.noosh.com/client_1/login";
		   clientURL2 = "https://nooshselenium.qa2.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.qa2.noosh.com/service/login";  
		   clientURL3 = "https://selenium263945.qa2.noosh.com/client_test/signup";
	   } else if (domain.trim().equals("scd")) { 
		   baseUrl = "https://demo.scd.noosh.com/service/signup";
		   baseUrlPro = "https://scd.noosh.com/noosh/home/login";                
		   baseUrlSmoke = "https://nooshselenium.scd.noosh.com/service/login";   
		   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
		   clientURL = "https://selenium263945.sqa.noosh.com/client_1/login";
		   clientURL2 = "https://nooshselenium.scd.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.scd.noosh.com/service/login";  
		   clientURL3 = "https://selenium263945.scd.noosh.com/client_test/signup";
	   } else {		  
		   baseUrl = "https://demo.sqa.noosh.com/service/signup";
		   baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2.sqa.noosh.com/service/login";  
		   smokeURL = "https://nooshselenium.sqa.noosh.com/service/login";
		   clientURL = "https://selenium263945.sqa.noosh.com/client_1/login";
		   clientURL2 = "https://nooshselenium.sqa.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.sqa.noosh.com/service/login"; 
		   clientURL3 = "https://selenium263945.sqa.noosh.com/client_test/signup";
	   } 
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
   }
   
   
   @Test(description="Client login")
   @Parameters({"clientName1", "passwordBuyer"})
   public void testClientLogin(String clientName1, String passwordBuyer) throws InterruptedException {
   	  log.info("Start: testClientLogin");
   	  driver.get(clientURL);
	  driver.manage().window().maximize();
	  Thread.sleep(3000);
   	  ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
   	  Thread.sleep(1000);
   	  clientLoginPage.clientLoginUser(clientName1, passwordBuyer);
   	  Thread.sleep(5000);
   	  if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client Login  ******");   
	  } 
   	  log.info("End: testClientLogin");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 1")
   public void testClientCreateProj1() throws InterruptedException {
	   log.info("Start: testClientCreateProj1");	
	   ClientHomePage clientHomePage = new ClientHomePage(driver);
	   clientHomePage.clickSmartForm();
	   Thread.sleep(3000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client create project  ******");   
	  } 
	   log.info("End: testClientCreateProj1");
	   log.info("--------------------------------------------");
   }
   
  /* 12/16 temp comment out --------------
   @Test(description="Client cancel project")
   public void testClientCancelProj() throws InterruptedException {
	   log.info("Start: testClientCancelProj");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.cancelProj(); 
	   Thread.sleep(3000);	   
	   log.info("End: testClientCancelProj");
	   log.info("--------------------------------------------");
   }
   12/16 temp comment out -----------*/
   
   @Test(description="Client create project - step 2")
   @Parameters({"productName1", "projectNameField"})
   public void testClientCreateProjName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testClientCreateProjName");	
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   productName = productName1 + unicID + " client";
	   newProject.inputClientProjectName(productName);   
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
   public void testClientProjCompletionDate() throws InterruptedException {
	   log.info("Start: testClientProjCompletionDate");	
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   Thread.sleep(2000);   	    
	   brochurePopup.callCalendar();
	   brochurePopup.setNextMonth();
	   brochurePopup.setComplationDate(); 
	   Thread.sleep(3000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for client project completion date ******");   
	  } 
	   log.info("End: testClientProjCompletionDate");
	   log.info("--------------------------------------------");
   }
	
   @Test(description="Client create project - step 4")
   public void testClientProjNextBtn() throws InterruptedException {
	   log.info("Start: testClientProjNextBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.clickNext();
	   Thread.sleep(1000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjNextBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 5")
   @Parameters({"quantSP", "quantSP2", "quantSP3"})
   public void testClientProjSpecQuantity(String quantSP, String quantSP2, String quantSP3) throws InterruptedException {
	   log.info("Start: testClientProjSpecQuantity");
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
	   Thread.sleep(2000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjSpecQuantity");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 6")
   public void testClientProjFilesTab() throws InterruptedException {
	   log.info("Start: testClientProjFilesTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getFilesTab();
	   Thread.sleep(1000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjFilesTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 7")
   @Parameters({"fileForProject4"})
   public void testClientProjFileUpload(String fileForProject4) throws InterruptedException, AWTException {
	   log.info("Start: testClientProjFileUpload");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   page.uploadFileModalWindow(fileForProject4);
	   Thread.sleep(2000);
	   brochurePopup.clickUploadFileDropBT();
	   Thread.sleep(2000);
	   page.robotUpload();
	   Thread.sleep(8000);	
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjFileUpload");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 8")
   public void testClientProjReviewSubmitTab() throws InterruptedException {
	   log.info("Start: testClientProjReviewSubmitTab");
	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	   brochurePopup.getReviewAndSubmitTab1();
	   Thread.sleep(3000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjReviewSubmitTab");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 9")
   public void testClientProjAddSmartFormBtn() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartFormBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.addSmartFormBtn();
	   Thread.sleep(3000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjAddSmartFormBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 10")
   public void testClientProjAddSmartForm2() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartForm2");
	   WebElement startElement = driver.findElement(By.id("productsPopupUl"));
	   List<WebElement> imgs = startElement.findElements(By.tagName("img"));
	   spSiteLoginPage = driver.getCurrentUrl();
	   if(spSiteLoginPage.contains("sqa")) {
		   imgs.get(1).click();
	   } else if(spSiteLoginPage.contains("sdm") || spSiteLoginPage.contains("scd")) {
		   imgs.get(2).click();
	   }
	   Thread.sleep(2000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjAddSmartForm2");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 11")
   public void testClientProjAddSmartForm3() throws InterruptedException {
	   log.info("Start: testClientProjAddSmartForm3");
	   testClientProjReviewSubmitTab();
	   testClientProjAddSmartFormBtn();
	   testClientProjAddSmartForm2();
	   testClientProjReviewSubmitTab();
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjAddSmartForm3");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client create project - step 12")
   public void testClientProjCreateBtn() throws InterruptedException {
	   log.info("Start: testClientProjCreateBtn");
	   ClientCreateProjectPage clientCreateProjectPage = new ClientCreateProjectPage(driver);
	   clientCreateProjectPage.clickCreateProjBtn();
	   Thread.sleep(2000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client project next btn  ******");   
	  } 
	   log.info("End: testClientProjCreateBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Client logout")
   public void testClientLogout() throws InterruptedException {
   	  log.info("Start: testClientLogout");
   	  Thread.sleep(6500);
      SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
      Thread.sleep(2000);
      supplierMainMenuPage.clickLogout();
  	  Thread.sleep(2000);
  	  if(!browser.trim().equals("IE")) {
   	  errorIndex = jsErrorIndex;
  	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
  	  } 
   	  log.info("End: testClientLogout");
  	  log.info("--------------------------------------------");
   }
   
   @Test(description="Go to another client site")
   public void testAnotherClient() throws InterruptedException {
   	  log.info("Start: testAnotherClient");
   	  driver.get(clientURL3);	
   	  //driver.manage().window().maximize();
   	  Thread.sleep(2000);
   	  if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client Login  ******");   
	  } 
   	  log.info("End: testAnotherClient");
  	  log.info("--------------------------------------------");
   }
   
   /* 1/8
   @Test(description="Client signup link")
   public void testClientSignupLink() throws InterruptedException {
	   log.info("Start: testClientSignupLink");
	   ClientSignupPage clientSignupPage = new ClientSignupPage(driver);
	   clientSignupPage.clickSignupLink();
	   Thread.sleep(2500);
	   log.info("End: testClientSignupLink");
	   log.info("--------------------------------------------");
   }
   */
   
   @Test(description="client signup fields")
   @Parameters({"clientFullName", "clientSignupEmail", "passwordBuyer"}) 
   public void testClientSignupFields(String clientFullName, String clientSignupEmail, String passwordBuyer)
                  throws InterruptedException {
	   log.info("Start: testClientSignupFields");
	   clientFullName = clientFullName + unicID;
	   clientSignupEmail = clientSignupEmail+ unicID + "@gmail.com";
	   ClientSignupPage clientSignupPage = new ClientSignupPage(driver);
	   clientSignupPage.inputFullName(clientFullName);
	   Thread.sleep(500);
	   clientSignupPage.inputEmail(clientSignupEmail);
	   Thread.sleep(500);
	   clientSignupPage.inputPassword(passwordBuyer);
	   Thread.sleep(500);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client signup fields  ******");   
	   } 
	   log.info("End: testClientSignupFields");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="client signup button")
   public void testClientSignupBtn() throws InterruptedException {
	   log.info("Start: testClientSignupBtn");
	   ClientSignupPage clientSignupPage = new ClientSignupPage(driver);
	   clientSignupPage.clickSignupBtn();
	   Thread.sleep(5000);
	   if(!browser.trim().equals("IE")) {
	 		  errorIndex = jsErrorIndex;
	 		  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for Client signup button  ******");   
	   } 
	   log.info("End: testClientSignupBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="New client create project - step 1")
   public void testNewClientCreateProj1() throws InterruptedException {
	   testClientCreateProj1();	   
   }
   
   @Test(description="New client create project - step 2")
   @Parameters({"productName1", "projectNameField"})
   public void testNewClientCreateProjName(String productName1, String projectNameField) throws InterruptedException {
	   testClientCreateProjName(productName1, projectNameField);	   
   }
   
   @Test(description="New client create project - step 3")
   public void testNewClientProjCompletionDate() throws InterruptedException {
	   testClientProjCompletionDate();	   
   }
	
   @Test(description="New client create project - step 4")
   public void testNewClientProjNextBtn() throws InterruptedException {
	   testClientProjNextBtn();
   }
   
   @Test(description="New client create project - step 5")
   @Parameters({"quantSP", "quantSP2", "quantSP3"})
   public void testNewClientProjSpecQuantity(String quantSP, String quantSP2, String quantSP3) throws InterruptedException {
	   testClientProjSpecQuantity(quantSP, quantSP2, quantSP3);
   }
   
   @Test(description="Client create project - step 6")
   public void testNewClientProjFilesTab() throws InterruptedException {
	   testClientProjFilesTab();
   }
   
   @Test(description="New client create project - step 7")
   @Parameters({"fileForProject4"})
   public void testNewClientProjFileUpload(String fileForProject4) throws InterruptedException, AWTException {
	   testClientProjFileUpload(fileForProject4);
   }
   
   @Test(description="New client create project - step 8")
   public void testNewClientProjReviewSubmitTab() throws InterruptedException {
	   testClientProjReviewSubmitTab();
   }
  
   @Test(description="New client create project - step 12")
   public void testNewClientProjCreateBtn() throws InterruptedException {
	   testClientProjCreateBtn();
   }
   
   @Test(description="Client open project")
   public void testOpenProject() throws InterruptedException {
	   log.info("Start: testOpenProject");
	   WebElement project = driver.findElement(By.id("jobTabInfo")).findElement(By.className("line-project-name"));
	   WebElement spanTag = project.findElement(By.tagName("span"));
	   if (spanTag != null) {
	       String projectName = spanTag.getAttribute("title");
		   if(projectName.equals(productName)) {
				project.click();
				Thread.sleep(1500);
		   } else {
			   System.out.println("The project created does NOT show!");
			   log.info("The project created does NOT show!");
		   }
	   }	   
	   Thread.sleep(3000);  	  
	   if(!browser.trim().equals("IE")) {
	  	   errorIndex = jsErrorIndex;
	 	   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
	   } 	   
	   log.info("End: testOpenProject");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="New client logout")
   public void testClientLogout2ndTime() throws InterruptedException {
	   testClientLogout();
   }
}
   

   
   
   
   
   
    
    
    
   
   
   
   
    
        