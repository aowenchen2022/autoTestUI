/***********************************************************************
* This class tests the functionality for Buying 
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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestspBasic extends BaseSeleniumTest {
  
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
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo.noosh.com/service/signup";
		   loginUrl = "https://selenium263945.noosh.com/service/login";
	   } else {
		   baseUrl = "https://demo." + domain + ".noosh.com/service/signup";
		   loginUrl = "https://selenium263945." + domain + ".noosh.com/service/login";
	   }
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spSupplierTest_2.x_10182013.bat *****\n\n");
   }
   
   @Test(description="SP login")
     @Parameters({"loginUserID", "passwordSP"})
     public void testSPLogin1stTime(String loginUserID, String passwordSP) throws InterruptedException, BiffException, IOException {
     	 log.info("Start: testSPLogin1stTime");
     	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);     	
     	 /*
     	 long beforeLoadLogin = System.currentTimeMillis();
         driver.get(loginUrl); 
         long afterLoadLogin = System.currentTimeMillis();
         long loadLoginPageTime = beforeLoadLogin - afterLoadLogin;
         log.info("Login loading time: " + loadLoginPageTime);
         */
     	 Workbook wb = Workbook.getWorkbook(new File("C:/jennifer"));
     	 Sheet st =  wb.getSheet(0);
     	 Cell cell = st.getCell(0,1);
     	 String url = cell.getContents();
     	 System.out.println("url="+url);
     	 driver.get(url);
         driver.manage().window().maximize();
         Thread.sleep(3000);    
         LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
         loginDemoSqa.loginUser(loginUserID, passwordSP); 
         Thread.sleep(3000);
         spSiteLoginPage = driver.getCurrentUrl();  
         log.info("End: testSPLogin1stTime");
    	 log.info("--------------------------------------------");
     }
   
   @Test(description="Existing SP click global 'create project'")
   public void testExistingSPClickGlbCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPClickGlbCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickGlbCreateProj();    
	   Thread.sleep(3000);
	   log.info("End: testExistingSPClickGlbCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP cancel global create project")
   public void testExistingSPCancelCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPCancelCreateProj");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject. cancelGlbProj();   
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
	   productName1 = productName1 + unicID + "55";  
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   newProject.inputGlbProjectName(productName1);   
	   Thread.sleep(1000);
	   try {
	      AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
	   } catch(Throwable e) {
		  System.out.println("Project Name field is missing!\n");
		  log.info("Project Name field is missing!");
	   }
	   if(!browser.trim().equals("IE")) {
	      	  errorIndex = jsErrorIndex;
	     	  Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
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
   
   @Test(description="Existing SP select a smart form")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
	    log.info("Start: testExistingSPSelectSmartForm");
	    driver.findElement(By.className("radio-input")).click();   
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
	    else if(spSiteLoginPage.contains("sqa"))   
		    img.get(1).click();   
	    else if(spSiteLoginPage.contains("scd"))
	    	img.get(0).click();
	    Thread.sleep(3000);
 	    if(!browser.trim().equals("IE")) {	
 	    errorIndex = jsErrorIndex;
 	    Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project ******");	
 	    } 
	    log.info("End: testExistingSPSelectSmartForm");
	    log.info("--------------------------------------------"); 
   }
     
    @Test(description="Existing SP Create Project - step 2")
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
 	   "specDescrSP", "fileForProject11"})
    public void testExistingSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
 		   String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
 		   String fileForProject11) throws InterruptedException, AWTException {
    
 	   log.info("Start: testExistingSPcreateProjectWizard");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   Thread.sleep(2000);   	    
 	   brochurePopup.callCalendar();
 	   brochurePopup.setNextMonth();
 	   brochurePopup.setComplationDate(); 
 	   Thread.sleep(3000);
 	   brochurePopup.getNextTab();	 
 	   Thread.sleep(2000);
 	   driver.findElement(By.id("cpPreviousStep")).click();  
 	   Thread.sleep(3000);
 	   brochurePopup.getNextTab();           
 	   Thread.sleep(3000);
 	   Thread.sleep(1000);
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
 	   driver.findElement(By.id("cpNextStep"));
 	   Thread.sleep(3000);
 	   
 	   brochurePopup.getFilesTab();  
 	   Thread.sleep(1000);
 	   page.uploadFileModalWindow(fileForProject11);   
 	   brochurePopup.clickUploadFileDropBT();
 	   Thread.sleep(2000);
 	   page.robotUpload();
 	   Thread.sleep(6500);	   
 	   int index = fileForProject11.indexOf("/");
 	   String imgTitle = fileForProject11.substring(index + 1);
 	   System.out.println("img title = " + imgTitle);
 	   String imgName = driver.findElement(By.id("createProjectTabFiles")).findElement(By.className("editable-field active")).getAttribute("title");
 	   System.out.println("img name = " + imgName);
 	   Assert.assertEquals(imgName, imgTitle);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000); 	   
 	   testExistingSPAddSmartFormPage(quantSP, quantSP2);   
 	   Thread.sleep(2000); 
       brochurePopup.clickCreateAndEstimateGlbProjectBT();    
       Thread.sleep(3000);
 	   log.info("End: testExistingSPcreateProjectWizard");
 	   log.info("--------------------------------------------"); 
    }    
    
    @Test(description="add smart form")
    @Parameters({"quantSP", "quantSP2"})
    public void testExistingSPAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
 	   log.info("Start: testAddSmartFormPage");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver); 	   
 	   addSmartFormPage.clickAddSmart();    
 	   Thread.sleep(1000); 	  
 	   addSmartFormPage.clickSmartForm();
 	   Thread.sleep(1000);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000);
 	   addSmartFormPage.clickAddSmart();
 	   Alert alert = driver.switchTo().alert();
 	   alert.accept();
 	   Thread.sleep(6000); 	   
 	   addSmartFormPage.clickSmartForm();
 	   addSmartFormPage.inputOneQuantity(quantSP); 	  
 	   Thread.sleep(2000);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000);	  
 	   log.info("End: testAddSmartFormPage");
 	   log.info("--------------------------------------------"); 
    }
    
    @Test(description="Existing SP adding two suppliers")
    @Parameters({"supplierName1", "supplierName2"})
    public void testExistingSPAddSuppliers(String supplierName1, String supplierName2)
    		     throws InterruptedException {
  	   log.info("Start: testSPAddSuppliers");
  	   supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@gmail.com";	   
	   Thread.sleep(2000);
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
    	if(!browser.trim().equals("IE")) {
  	        errorIndex = jsErrorIndex;
  	        Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Add Estimate Due Date  ******");	
  	    }
    	log.info("End: testSPSelectSF");
 	    log.info("--------------------------------------------"); 
    }   
    
    @Test(description="Existing SP adding estimate due date")
    public void testExistingSPAddEstimateDueDate() throws InterruptedException {
    	log.info("Start: testSPAddEstimateDueDate");
    	SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver);  
  	    supplierEstimatePopup.callCalendar();	               
  	    Thread.sleep(2000);  	     	   
  	    if(!browser.trim().equals("IE")) {
  	        errorIndex = jsErrorIndex;
  	        Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Add Estimate Due Date  ******");	
  	    }
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
  	  log.info("Start: testExistingSPLogout");
  	  Thread.sleep(7000);
  	  CommonUtils commonUtils = new CommonUtils(driver);
  	  Thread.sleep(2000);
  	  commonUtils.spLogout();
 	  Thread.sleep(2000);  	  
  	  log.info("End: testExistingSPLogout");
  	  log.info("--------------------------------------------");
    } 
}

