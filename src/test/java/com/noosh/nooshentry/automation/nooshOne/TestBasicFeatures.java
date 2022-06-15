/***********************************************************************
* This class tests the basic functionalities for Buying with IE, Firefox browser
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.buyerSite.SupplierMainMenuPage;

public class TestBasicFeatures extends BaseSeleniumTest {  
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
	static String loginUrl;
	static String parentWindowHandle1;
	static String clientName = "Selenium Client";
	static String parentWindowHandle;
	static String projectName;
	   
	@BeforeTest
	public static void setParameters(ITestContext context) throws FileNotFoundException {
	    if (domain.trim().equals("spd")) {
			   baseUrl = "https://demo.spd.noosh.com/service/signup";
			   baseUrlPro = "https://spd.noosh.com/noosh/home/login";
			   baseUrlSmoke = "https://nooshselenium2.noosh.com/service/login";
			   smokeURL = "https://nooshselenium.spd.noosh.com/service/login";
			   clientURL = "https://nooshselenium.spd.noosh.com/autoclient/main";
			   loginUrl = "https://selenium263945.spd.noosh.com/service/login";  
		} else if (domain.trim().equals("sdm")) {
			   baseUrl = "https://demo.sdm.noosh.com/service/signup";
			   baseUrlPro = "http://sdm.noosh.com/noosh/home/login";
			   baseUrlSmoke = "http://nooshselenium2.sdm.noosh.com/service/login";
			   smokeURL = "https://nooshselenium.sdm.noosh.com/service/login";
			   clientURL = "https://nooshselenium.sdm.noosh.com/autoclient/main";
			   loginUrl = "https://selenium263945.sdm.noosh.com/service/login";  
		} else if (domain.trim().equals("qa2")) {
			   baseUrl = "https://demo.qa2.noosh.com/service/signup";
			   baseUrlPro = "https://qa2.noosh.com/noosh/home/login";
			   baseUrlSmoke = "https://nooshselenium.qa2.noosh.com/service/login";
			   smokeURL = "https://nooshselenium.qa2.noosh.com/service/login";
			   clientURL = "https://nooshselenium.qa2.noosh.com/autoclient/main";
			   loginUrl = "https://selenium263945.qa2.noosh.com/service/login";  
		} else if (domain.trim().equals("scd")) { 
			   baseUrl = "https://demo.scd.noosh.com/service/signup";
			   baseUrlPro = "https://scd.noosh.com/noosh/home/login";                
			   baseUrlSmoke = "https://nooshselenium.scd.noosh.com/service/login";   
			   smokeURL = "https://nooshselenium.scd.noosh.com/service/login";
			   clientURL = "https://nooshselenium.scd.noosh.com/autoclient/main";
			   loginUrl = "https://selenium263945.scd.noosh.com/service/login";  
		} else {		  
			   baseUrl = "https://demo.sqa.noosh.com/service/signup";
			   baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
			   baseUrlSmoke = "https://nooshselenium2.sqa.noosh.com/service/login";  
			   smokeURL = "https://nooshselenium.sqa.noosh.com/service/login";
			   clientURL = "https://nooshselenium.sqa.noosh.com/autoclient/main";
			   loginUrl = "https://selenium263945.sqa.noosh.com/service/login";  
		} 
	}
	   
	@Test(description="Exsiting user login")
	@Parameters({"passwordSP", "spEmailSmoke"})
	//@Parameters({"email", "passwordSP"})
	public void testLoginDemoSqaPageSmoke(String passwordSP, String spEmailSmoke) throws InterruptedException {
	    log.info("Start: testLoginDemoSqaPageSmoke");
		Thread.sleep(1500);
	    driver.get(baseUrlSmoke);
	    driver.manage().window().maximize();   
	    Thread.sleep(2000);  
	    LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
	    loginDemoSqa.loginUser(spEmailSmoke, passwordSP); 
	    Thread.sleep(3500);
		spSiteLoginPage = driver.getCurrentUrl();
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######");      		
		log.info("End: testLoginDemoSqaPageSmoke");
		log.info("--------------------------------------------");
	} 
	
	@Test(description="Existing SP click global create project")
	public void testExistingSPClickGlbCreateProj() throws InterruptedException {
		log.info("Start: testExistingSPClickGlbCreateProj");
		SPHomePage sPHomePage = new SPHomePage(driver);
		sPHomePage.clickGlbCreateProj();    
		Thread.sleep(3000);
		if(!browser.trim().equals("IE")) {
	        errorIndex = jsErrorIndex;
	     	Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
	    } 
		log.info("End: testExistingSPClickGlbCreateProj");
	 	log.info("--------------------------------------------"); 
	}
	   
	@Test(description="Existing SP input project name")
	@Parameters({"productName1", "projectNameField"})
	public void testExistingSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
	    log.info("Start: testExistingSPInputProjectName");
		productName1 = productName1 + unicID + "2";  
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
		 else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd"))   
		    img.get(1).click();   
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
	    
	 	   log.info("Start: testSPcreateProjectProjectWizard");
	 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
	 	   	    
	 	   //brochurePopup.setProjectName(projNameSP); 
	 	   Thread.sleep(2000);   	    
	 	   brochurePopup.callCalendar();
	 	   brochurePopup.setNextMonth();
	 	   brochurePopup.setComplationDate(); 
	 	   Thread.sleep(3000);
	 	   /*below added 10/29 
	 	   List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
	 	   for (WebElement textarea : textareas) {
	 		   String projDesName = textarea.getAttribute("name");
	 		   if (projDesName != null && projDesName.equalsIgnoreCase("stD9TcfE_project_description"));
	 		       textarea.clear();
	 		       textarea.sendKeys("This is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\n\n   END");
	 		       break;
	 	   }
	 	   Thread.sleep(2000);
	 	   above added 10/29 */
	 	   brochurePopup.getNextTab();	 
	 	   Thread.sleep(2000);
	 	   //brochurePopup.putDescriptionName(descriptionNameSP); //10/3
	 	   driver.findElement(By.id("cpPreviousStep")).click();  //10/30
	 	   Thread.sleep(3000);
	 	   brochurePopup.getNextTab();            //10/30
	 	   Thread.sleep(3000);
	 	   //brochurePopup.putSpecName("Selenium");  //11/15
	 	   //Thread.sleep(1000);
	 	   //brochurePopup.putSKU(sku);    //11/15
	 	   //Thread.sleep(1000);
	 	   //brochurePopup.putRefNumber(referenceNumber);  //11/15
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
	 	   
	 	   brochurePopup.getFilesTab();  // tmp comment out 11/20
	 	   Thread.sleep(1000);
	 	   page.uploadFileModalWindow(fileForProject11);   // tmp comment out 11/20
	 	  // Thread.sleep(2000);
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
	 	   brochurePopup.getReviewAndSubmitTab();
	 	   Thread.sleep(3000);
	 	   testExistingSPAddSmartFormPage(quantSP, quantSP2);   
	 	   Thread.sleep(2000);                                  
	 	   // brochurePopup.getReviewAndSubmitTab();  //10/21
	 	   // WebElement reviewAndSubmit = driver.findElement(By.linkText("Review and Submit"));
	 	   // reviewAndSubmit.click();
	 	   // Thread.sleep(5000);
	       brochurePopup.clickCreateAndEstimateGlbProjectBT();    
	       Thread.sleep(3000);
	 	   log.info("End: testSPcreateProjectProjectWizard");
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
	 	   if(!browser.trim().equals("IE")) {
	 	      errorIndex = jsErrorIndex;
	 	      Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Add Smart Form  ******");	
	 	   }
	 	   log.info("End: testAddSmartFormPage");
	 	   log.info("--------------------------------------------"); 
	    }
	    
	    @Test(description="Existing SP Request Estimate from Suppliers")
	    @Parameters({"supplierName1", "supplierName2"})
	    public void testExistingSPRequestEstimateSupplier(String supplierName1, String supplierName2)  
	    		  throws InterruptedException {
	 	   log.info("Start: testRequestEstimateSupplier");
	 	   supplierUser1 = supplierName1 + "+" + "supplier" + unicID + "@gmail.com";
	 	   
	 	   Thread.sleep(2000);
	 	   SupplierEstimatePopup supplierEstimatePopup = new SupplierEstimatePopup(driver); 
	 	   Thread.sleep(2000);
	 	   supplierEstimatePopup.setSupplierName(supplierUser1, supplierName2);
	 	   Thread.sleep(2000); 
	 	   WebElement smartFormCheckbox = driver.findElement(By.className("forms-list"));  
	 	   smartFormCheckbox.findElement(By.tagName("input")).click();                     
	 	   Thread.sleep(1000); 
	 	   supplierEstimatePopup.callCalendar();	               
	 	   Thread.sleep(2000);
	 	   supplierEstimatePopup.callRequestEstimatesBtn();
	 	   Thread.sleep(6000);
	 	   
	 	   if(!browser.trim().equals("IE")) {
	 	      errorIndex = jsErrorIndex;
	 	      Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Request Estimate from Suppliers  ******");	  
	 	   }
	 	   log.info("End: testRequestEstimateSupplier");
	 	   log.info("--------------------------------------------"); 
	    }
	
	@Test(description="Exsiting user logout") 
	    public void testLogoutDemoSite() throws InterruptedException {
	 	log.info("Start: testLogoutDemoSite");
	 	Thread.sleep(5000);
	    SupplierMainMenuPage supplierMainMenuPage = new SupplierMainMenuPage(driver);
	    Thread.sleep(2000);
	    driver.get("https://nooshselenium2.sqa.noosh.com/service/j_spring_security_logout"); 
	 	Thread.sleep(3500);
	 	if(!browser.trim().equals("IE")) {
	  	    errorIndex = jsErrorIndex;
	 	    Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP logout ******");	
	 	    } 
	 	log.info("End: testLogoutDemoSite");
	 	log.info("--------------------------------------------");
	} 
	
    //New sp signup
	@Test(description="SP onboarding - SP sign up")
	   @Parameters({"fullNameSP", "passwordSP", "phoneNumberSP", "companyName"}) 
	     public void testRegisterNewSPSite(String fullNameSP, String passwordSP, String phoneNumberSP, String companyName) throws InterruptedException {		   
		   log.info("Start: testRegisterNewSPSite");
		   companyName = companyName + unicID;   
		   BaseTestCases.testRegisterNewSPSite(baseUrl, fullNameSP, passwordSP, phoneNumberSP, companyName);  
		   //System.out.println("sp baseURL= " + baseUrl);
		   Thread.sleep(3000); 
		   
		   if(!browser.trim().equals("IE")) {
		       errorIndex = jsErrorIndex;
		       Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP sign up ******");	
		   } 
		   log.info("End: testRegisterNewSPSite");
		   log.info("--------------------------------------------");
	   }
	   
	   @Test(description="SP Let's Begin")
	   public void testLetsBegin() throws InterruptedException
	   {
		   log.info("Start: testLetsBegin");
		   RegisterNewSPSitePage registerSPSitePage = new RegisterNewSPSitePage(driver);
		   spUser = driver.getCurrentUrl();
		   int index = spUser.indexOf("/service");
		   spUser = spUser.substring(0, index);
		   //System.out.println("spUser"+spUser);
		   
		   Thread.sleep(1500);
		   registerSPSitePage.clickLetsBegin();
		   Thread.sleep(1500);
		  
		   if(!browser.trim().equals("IE")) {
		       errorIndex = jsErrorIndex;
		       Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Let's Begin  ******");
		   } 
		   log.info("End: testLetsBegin");
		   log.info("--------------------------------------------");
	   }
	   
	   @Test(description="SP Buying Site")
	   public void testCreateBuyerSiteOB () throws InterruptedException 
	   {
	 	  log.info("Start: testCreateBuyerSiteOB");
	 	  SitesListTab sitesListTab = new SitesListTab(driver); 
	 	  sitesListTab.getSitesList();
	 	  Thread.sleep(1500);
	 	  /*
	 	  if(!browser.trim().equals("IE")) {
	 		   errorIndex = jsErrorIndex;
	 		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Buying Site******");	
	 	  } */
	 	  log.info("End: testCreateBuyerSiteOB");
	 	  log.info("--------------------------------------------");
	 	  
	   }
	   
	   @Test(description="SP Create Project - step 1")
	  // @Parameters({"validDemoUserEmail", "productName1"})
	   @Parameters({"productName1"})
	   public void testSPcreateProject(String productName1) throws InterruptedException {
		   log.info("Start: testSPcreateProject");
		   productName1 = productName1 + unicID +"3";
		   
		   CreateNewProject newProject = new CreateNewProject(driver); 
		   driver.findElement(By.id("goback")).click();   //11/7
		   Thread.sleep(2000);
		   testLetsBegin();   //11/7
		   newProject.inputProjectName(productName1);   
		   Thread.sleep(2000);
		   //newProject.selectCertainSite();
		   WebElement startElement = driver.findElement(By.id("createProjectProductUl"));
		   List<WebElement> img = startElement.findElements(By.tagName("img"));  
		   spSiteLoginPage = driver.getCurrentUrl(); 
		   if(spSiteLoginPage.contains("sdm"))
			   img.get(1).click();    
		   else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd"))   
			   img.get(1).click();    
		   Thread.sleep(3000);
		   //newProject.clickPostcard(); 
	 
		   newProject.clickCreateAndEstimateProjectBT();
		   Thread.sleep(2000);
		   //driver.findElement(By.className("sp-createProject-cancel"));    
		   //Thread.sleep(3000);
		   //img.click();                                           
		   //Thread.sleep(2000);
		   //newProject.clickCreateAndEstimateProjectBT();      
		   //Thread.sleep(3000);
		   /*
		   if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project ******");	
		   } */
		   log.info("End: testSPcreateProject");
		   log.info("--------------------------------------------"); 
	   }
	   
	   @Test(description="SP Create Project - step 2")
	   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
		   "specDescrSP", "fileForProject1"})
	   public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
			         String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
			         String fileForProject1) throws InterruptedException, AWTException {
		   log.info("Start: testSPcreateProjectProjectWizard");
		   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		   	    
		   //brochurePopup.setProjectName(projNameSP); 
		   Thread.sleep(2000);   	    
		   brochurePopup.callCalendar();
		   brochurePopup.setNextMonth();
		   brochurePopup.setComplationDate(); 
		   Thread.sleep(1000);
	 	   List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
	 	   for (WebElement textarea : textareas) {
	 		   String projDesName = textarea.getAttribute("name");
	 		   if (projDesName != null && projDesName.equalsIgnoreCase("stD9TcfE_project_description")) {
	 		       textarea.clear();
	 		       textarea.sendKeys("This is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\n\n   END");
	 		       break;
	 		   }
	 	   }
	 	   Thread.sleep(1500);
		   brochurePopup.getNextTab();	 
		   Thread.sleep(2000);
		   //brochurePopup.putDescriptionName(descriptionNameSP);
		   //brochurePopup.putSKU(sku);
		   //brochurePopup.putRefNumber(referenceNumber);
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
		    
		   brochurePopup.getFilesTab();
		   page.uploadFileModalWindow(fileForProject1);
		   Thread.sleep(2000);
		   //WebElement uploadBtn = driver.findElement(By.id("upload-drop-button-5000"));
		   //uploadBtn.click();
		   brochurePopup.clickUploadFileDropBT();
		   Thread.sleep(2000);
		   page.robotUpload();
		   Thread.sleep(8000);	   
		   brochurePopup.getReviewAndSubmitTab();
		   Thread.sleep(3000);
	       //brochurePopup.clickAddProductsBT();	
		   /*
		   WebElement imgPreview = driver.findElement(By.className("uploaded-file-preview-image"));
		   if (imgPreview == null) {
				   System.out.println("Image uploading is failed!");
				   log.info("Image uploading is failed!"); 
		   } 
		   */
		//   testAddSmartFormPage(quantSP, quantSP2);   
		   Thread.sleep(1500);
		  // brochurePopup.getReviewAndSubmitTab();  
		  // WebElement reviewAndSubmit = driver.findElement(By.linkText("Review and Submit"));
		  // reviewAndSubmit.click();
		  // Thread.sleep(5000);
		   brochurePopup.clickCreateAndEstimateProjectBT();   
		   Thread.sleep(3500);  
		   if(!browser.trim().equals("IE")) {
		   errorIndex = jsErrorIndex;
		   Page.jsErrorReporter(driver, errorIndex,"****** JS errors for SP Create Project - step 2  ******");	 
		   }
		   log.info("End: testSPcreateProjectProjectWizard");
		   log.info("--------------------------------------------"); 
	   }
	   
	   @Test(description="add smart form")
	   @Parameters({"quantSP", "quantSP2"})
	   public void testAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
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
		   Thread.sleep(2000);
		   
		   addSmartFormPage.clickSmartForm();
		   addSmartFormPage.inputOneQuantity(quantSP);
		  
		   Thread.sleep(2000);
		   brochurePopup.getReviewAndSubmitTab();
		   Thread.sleep(5000);
	 
		   /*
		   if(!browser.trim().equals("IE")) {
		      errorIndex = jsErrorIndex;
		      Page.jsErrorReporter(driver, errorIndex,"******  JS errors for SP Add Smart Form  ******");	
		   }*/
		   log.info("End: testAddSmartFormPage");
		   log.info("--------------------------------------------"); 
	   }

}
