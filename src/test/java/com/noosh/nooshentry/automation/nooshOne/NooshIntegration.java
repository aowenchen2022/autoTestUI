/***********************************************************************
* This class tests NGE/NE integration functionality 
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
import java.io.FileNotFoundException;
import org.testng.annotations.Parameters;
import java.util.Hashtable;
import java.util.List;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.awt.AWTException;

public class NooshIntegration extends BaseSeleniumTest {  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   static String baseUrlPro;
   static String baseUrlSmoke;
   static String smokeURL;
   static String clientURL;
   String windowNumber;
   static String baseUrl;
   static String loginUrl;
   static String parentWindowHandle1;
   static String projectName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo.noosh.com/service/signup";
		   baseUrlPro = "https://noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2.noosh.com/service/login";
		   smokeURL = "https://nooshselenium.noosh.com/service/login";
		   clientURL = "https://nooshselenium.noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945.noosh.com/service/login";  
	   } else {
		   baseUrl = "https://demo." + domain + ".noosh.com/service/signup";
		   baseUrlPro = "http://" + domain + ".noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2." + domain + ".noosh.com/service/login";
		   smokeURL = "https://nooshselenium." + domain + ".noosh.com/service/login";
		   clientURL = "https://nooshselenium." + domain + ".noosh.com/autoclient/main";
		   loginUrl = "https://selenium263945." + domain + ".noosh.com/service/login";  
	   } 
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** nooshIntegrationTest_04162014.bat *****\n\n");
   }
   
   @Test(description="SP login")
   @Parameters({"spEmailSmoke", "passwordSP"})
   public void testSPLogin(String spEmailSmoke, String passwordSP) throws InterruptedException {
     	 log.info("Start: testSPLogin");
         Thread.sleep(1500);
         driver.get(smokeURL); 
         driver.manage().window().maximize();  
         CommonUtils commonUtils = new CommonUtils(driver);
         commonUtils.loginUser1(spEmailSmoke);
         log.info("End: testSPLogin");
    	 log.info("--------------------------------------------");
   }
   
   @Test(description="SP click 'create project' on header menu")
   public void testSPClickGlbCreateProj() throws InterruptedException {
	   log.info("Start: testSPClickGlbCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickGlbCreateProj();    
	   Thread.sleep(3000);
	   log.info("End: testSPClickGlbCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP input project name")
   @Parameters({"productNameIntegration", "projectNameField"})
   public void testSPInputProjectName(String productNameIntegration, String projectNameField) throws InterruptedException {
	   log.info("Start: testSPInputProjectName");
	   projectName = productNameIntegration + " " + unicID; 
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   newProject.inputGlbProjectName(projectName);   
	   Thread.sleep(1500);
	   try {
	      Assert.assertTrue(Page.isTextPresent(projectNameField, driver));	      
	   } catch(Throwable e) {
		  System.out.println("Project Name field is missing!\n");
		  log.info("Project Name field is missing!");
	   }
	   log.info("End: testSPInputProjectName");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP select workspace")
   @Parameters({"clientOrWorkspace"})
   public void testSPSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
	   log.info("Start: testSPSelectWorkspace");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject.selectWorkSpace();
       Thread.sleep(1000);
       try {
    	   Assert.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
       } catch(Throwable e) {
    	   System.out.println("Client or Workspace field is missing!\n");
    	   log.info("Client or Workspace field is missing!");
       }
       log.info("End: testSPSelectWorkspace");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP input client user")
   @Parameters({"clientUser1", "clientFieldName"})
   public void testSPInputClient(String clientUser1, String clientFieldName) {
	   log.info("Start: testSPInputClient");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   try {
		   Assert.assertTrue(Page.isTextPresent(clientFieldName, driver));
		   commonUtils.testInputClientUser(clientUser1);
	   } catch(Throwable e) {
    	   System.out.println("Invite client User field field is missing!\n");
    	   log.info("Invite client User field field is missing!");
       }
	   log.info("End: testSPInputClient");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="sp select a client from the list")
   @Parameters({"clientUser1"})
   public void testSPSelectClient(String clientUser1) throws InterruptedException {
	   log.info("Start: testSPSelectClient");
	   Thread.sleep(1000);
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testSelectClient(clientUser1);
	   Thread.sleep(2000);
	   log.info("End: testSPSelectClient");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP click Find btn")
   public void testSPClickFindBtn() throws InterruptedException {
	   log.info("Start: testSPClickFindBtn");
	   CommonUtils commonUtils = new CommonUtils(driver);	
	   Thread.sleep(1000);
	   commonUtils.testFindBtn();
	   Thread.sleep(5000);
	   log.info("End: testSPClickFindBtn");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="SP select a smart form")
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
	    String spSiteLoginPage = driver.getCurrentUrl(); 
	   // if(spSiteLoginPage.contains("sqa"))
	 	//    img.get(0).click(); 	           
	    if(spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("sdm"))
	    	img.get(2).click();
	    else 
	    	img.get(0).click();
	    Thread.sleep(3000); 	    
	    log.info("End: testSPSelectSmartForm");
	    log.info("--------------------------------------------"); 
   }
     
    @Test(description="Existing SP Create Project - step 2")
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
   	   "specDescrSP", "fileForProject11"})
    public void testSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
 		     String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
 		     String fileForProject11) throws InterruptedException, AWTException {    
 	   log.info("Start: testSPcreateProjectWizard");
 	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.testSPcreateProject(projNameSP, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, 
			     quantSP3, specDescrSP, fileForProject11);
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	
 	   brochurePopup.getFilesTab();    
 	   Thread.sleep(1000);
 	   page.uploadFileModalWindow(fileForProject11);   
 	   brochurePopup.clickUploadFileDropBT();
 	   Thread.sleep(2000);
 	   page.robotUpload();
 	   Thread.sleep(6500);	
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(6000);  
 	   log.info("End: testSPcreateProjectWizard");
 	   log.info("--------------------------------------------"); 
   }
    
    @Test(description="SP click 'Create Project' btn")
    public void testSPClickCreateProjBtn() throws InterruptedException {
        log.info("Start: testSPClickCreateProjBtn");
 	    CommonUtils commonUtils = new CommonUtils(driver);	   
 	    commonUtils.testCreateProjBtn();
 	    Thread.sleep(20000);
 	    log.info("End: testSPClickCreateProjBtn");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="Go to NE through My desk")
    @Parameters({"siteListPageTitle", "profileTitlePro"}) 
    public void testGotoNEPage(String siteListPageTitle, String profileTitlePro) throws InterruptedException {
 	    log.info("Start: testGotoNEPage");
        SiteListPage siteListPage = new SiteListPage(driver);
 	    Thread.sleep(3000);      
        siteListPage.getMyDeskPageBack();  //5/8/15 comment out
        //WebElement img = driver.findElement(By.className("glb-dropdown-control")).findElement(By.tagName("img"));
        //img.click();
        //img.findElement(By.id("glbMyDeskDropdown")).click();
        Thread.sleep(3000);  
 	    log.info("End: testGotoNEPage");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, select the project created in NGE")
    public void testSelectProjInNE() throws InterruptedException {
    	log.info("Start: testSelectProjInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	if(driver.getPageSource().contains("Project Activity")) {
  	        commonUtils.testSelectProjInNooshEnterprise(projectName);
  	        System.out.println("noosh pro page.\n");
    	} else {
    		log.info("Project Activity table is missing.\n");
    	}
  	    Thread.sleep(5000);
    	log.info("End: testSelectProjInNE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, project update")
    public void testUpdateProjInNE() throws InterruptedException {
    	log.info("Start: testUpdateProjInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testUpdateProjInNooshEnterprise();
    	Thread.sleep(5000);
    	log.info("End: testUpdateProjInNE");
 	    log.info("--------------------------------------------");
    }
   
    @Test(description="In NE, project name update")
    public void testUpdateProjNameInNE() throws InterruptedException {
    	log.info("Start: testUpdateProjNameInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testUpdateProjNameInNooshEnterprise(projectName);
    	Thread.sleep(4000);
    	log.info("End: testUpdateProjNameInNE");
 	    log.info("--------------------------------------------");
    }
   
    @Test(description="In NE, project completion date update")
    public void testUpdateProjCompletionDateInNE() throws InterruptedException {
    	log.info("Start: testUpdateProjCompletionDateInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	    	
    	commonUtils.testSetNECompletionDate();
    	Thread.sleep(3000);
    	log.info("End: testUpdateProjCompletionDateInNE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, project status update")
    public void testUpdateProjStatusInNE() throws InterruptedException {
    	log.info("Start: testUpdateProjStatusInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testUpdateProjStatusInNooshEnterprise();
    	Thread.sleep(2500);
    	log.info("End: testUpdateProjStatusInNE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, updated project save")
    public void testUpdateProjSaveInNE() throws InterruptedException {
    	log.info("Start: testUpdateProjSaveInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testUpdatedProjSaveInNooshEnterprise();
    	Thread.sleep(8000);
    	log.info("End: testUpdateProjSaveInNE");
 	    log.info("--------------------------------------------");
    }
   
    @Test(description="Go to NGE")
    public void testGotoNGE() throws InterruptedException {
    	log.info("Start: testGotoNGE");
    	MyDeskPage myDeskPageNoo = new MyDeskPage(driver);  	
    	myDeskPageNoo.getDashboard(); 
    	Thread.sleep(6000);
    	log.info("End: testGotoNGE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NGE, open the project")
    public void testOpenTheProjInfoInNGE() throws InterruptedException {
    	log.info("Start: testOpenTheProjInfoInNGE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testOpenProjectInWS(projectName + " edit");
    	Thread.sleep(6000);
    	log.info("End: testOpenTheProjInfoInNGE");
 	    log.info("--------------------------------------------");
    }
   
    @Test(description="Go back to NE")
    public void testGoBackToNE() throws InterruptedException {
    	log.info("Start: testGoBackToNE");
        SiteListPage siteListPage = new SiteListPage(driver);
        siteListPage.getMyDeskPageBack();
        Thread.sleep(3000);
    	log.info("End: testGoBackToNE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, open the project in NEG jobs")
    public void testSelectProjInNEGJobs() throws InterruptedException {
    	log.info("Start: testSelectProjInNEGJobs");
    	if(driver.getPageSource().contains("Noosh Group Jobs")) {
    		CommonUtils commonUtils = new CommonUtils(driver);	
        	commonUtils.testOpenProjInNEGJobs(projectName);        	
    	} else {
    		log.info("Noosh Group Jobs table is missing. \n");
    	}
    	Thread.sleep(5000);
    	log.info("End: testSelectProjInNEGJobs");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, select the SF")
    public void testSelectSFInNE() throws InterruptedException {
    	log.info("Start: testSelectSFInNE");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testSelectSFinNE();
    	Thread.sleep(5000);
    	log.info("End: testSelectSFInNE");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, edit SF field")
    @Parameters({"updatedSFName"})
    public void testEditSF(String updatedSFName) throws InterruptedException {
    	log.info("Start: testEditSF");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	String spSiteLoginPage = driver.getCurrentUrl();           
	    if(spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("sdm"))
	    	commonUtils.testUpdateSFinNE2(updatedSFName);
	    else 
	    	commonUtils.testUpdateSFinNE(updatedSFName);
    	Thread.sleep(6000);
    	commonUtils.testCreateSFinNE(updatedSFName);
    	log.info("End: testEditSF");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, click on cancel")
    public void testCancelBtn() throws InterruptedException {
    	log.info("Start: testCancelBtn");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testCancelBtn();
    	Thread.sleep(1000);
    	log.info("End: testCancelBtn");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, edit SF field")
    public void testEditSFAgain() throws InterruptedException {
    	log.info("Start: testEditSF");
    	driver.findElement(By.id("cFTlWiXf")).findElement(By.className("com-inlineEditor-value")).sendKeys("1234567");
    	Thread.sleep(2000);
    	log.info("End: testEditSF");
 	    log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, SF save")
    public void testSaveSF() throws InterruptedException {
    	log.info("Start: testSaveSF");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testSaveSF();
    	Thread.sleep(5000);
    	log.info("End: testSaveSF");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Go to NGE")
    public void testGoToNGEAgain() throws InterruptedException {
    	testGotoNGE();
    }
    
    @Test(description="Open SF tab")
    @Parameters({"updatedSFName"})
    public void testOpenSFTab(String updatedSFName) throws InterruptedException {
    	log.info("Start: testOpenSFTab");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	Thread.sleep(1500);
    	commonUtils.testOpenProjectInWS(projectName + " edit");
    	Thread.sleep(2500);
    	commonUtils.testOpenProjectSFTabInWS(projectName + " edit", "Specs");
    	Thread.sleep(3000);
    	Assert.assertTrue(true, "Updated smart form name " + updatedSFName + " updated in NE does NOT show in NGE.");
    	log.info("End: testOpenSFTab");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Go back to NE")
    public void testGoBackNE() throws InterruptedException {
    	testGoBackToNE();
    }
    
    @Test(description="In NE, select the project")
    public void testSelectProjInNEAgain() throws InterruptedException {
    	testSelectProjInNE();
    }
    
    @Test(description="In NE, create spec")
    public void testCreateSpec() throws InterruptedException {
    	log.info("Start: testCreateSpec");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testSelectCreateSpec();
    	Thread.sleep(3000);
    	log.info("End: testCreateSpec");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, select SF")
    public void testSelectSF() throws InterruptedException {
    	log.info("Start: testSelectSF");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testSelectaSF();
    	Thread.sleep(3000);
    	log.info("End: testSelectSF");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE")
    public void testContinueSF() throws InterruptedException {
    	log.info("Start: testContinueSF");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testContinueSFBtn();
    	Thread.sleep(3000);
    	log.info("End: testContinueSF");
    	log.info("--------------------------------------------");
    }
   
    @Test(description="In NE, edit spec")
    @Parameters({"specNameUpdate"})
    public void testEditSpec(String specNameUpdate) throws InterruptedException {
    	log.info("Start: testEditSpec");    	
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testCreateSFinNE(specNameUpdate);
    	Thread.sleep(3000);
    	log.info("End: testEditSpec");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Go to NGE")
    public void testGoToNGE3rdTimes() throws InterruptedException {
    	testGotoNGE();
    }
    
    @Test(description="In NGE, open SF tab")
    @Parameters({"specNameUpdate", "updatedSFName"})
    public void testOpenSFTabAgain(String specNameUpdate, String updatedSFName) throws InterruptedException {
    	testOpenSFTab(updatedSFName);
    	Assert.assertTrue(true, "The updated spec " + specNameUpdate + " does NOT show in NGE.");
    }
    
    @Test(description="In NE, msg post")
    public void testMsgPostLink() throws InterruptedException {
    	testGoBackToNE();
    	testSelectProjInNE();
    	log.info("Start: testMsgPostLink");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testPostMSGMenu();
    	commonUtils.testPostMSG();
    	Thread.sleep(2000);
    	log.info("End: testMsgPostLink");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, post subject")
    @Parameters({"postSubject"})
    public void testPostSubject(String postSubject) throws InterruptedException {
    	log.info("Start: testPostSubject");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testPostSubject(postSubject);
    	Thread.sleep(1500);
    	log.info("End: testPostSubject");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, content")
    @Parameters({"postContent"})
    public void testPostContent(String postContent) throws InterruptedException {
    	log.info("Start: testPostContent");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testPostContent(postContent);
    	Thread.sleep(1000);
    	log.info("End: testPostContent");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE post msg, check user from group")
    public void testCheckGroupUser() throws InterruptedException {
    	log.info("Start: testCheckGroupUser");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testCheckTeam();
    	Thread.sleep(1000);
    	log.info("End: testCheckGroupUser");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, post msg")
    public void testPostMsgbtn() throws InterruptedException { 
    	log.info("Start: testPostMsgbtn");
    	CommonUtils commonUtils = new CommonUtils(driver);	
    	commonUtils.testPostMsgbtn();
    	Thread.sleep(2000);
    	commonUtils.testPostMSGMenu();
    	Thread.sleep(2000);
    	log.info("End: testPostMsgbtn");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Go to NGE, verify the updated msg")
    @Parameters({"postContent"})
    public void testNEUpdatedMsgInNGE(String postContent) throws InterruptedException {
    	driver.switchTo().defaultContent();
    	Thread.sleep(2500);
    	testGotoNGE();
    	log.info("Start: testNEUpdatedMsgInNGE");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testOpenProjectInWS(projectName + " edit");
    	Thread.sleep(3000);
    	commonUtils.testOpenProjectSFTabInWS3(projectName + " edit", "Messages");
    	Thread.sleep(6000);
    	Assert.assertTrue(true, "Msg " + postContent + " posted in NE does NOT show in NGE.");
    	log.info("End: testNEUpdatedMsgInNGE");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, file list")
    public void testFileList() throws InterruptedException { 
    	testGoBackToNE();
    	testSelectProjInNE();
    	log.info("Start: testFileList");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	//commonUtils.testFileMenu();  //6/18/14 tmp comment out
    	commonUtils.testFile();
    	Thread.sleep(3000);
    	log.info("End: testFileList");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="In NE, load file")
    @Parameters({"fileForProject3"})
    public void testUploadFileNE(String fileForProject3) throws InterruptedException, AWTException {
    	log.info("Start: testUploadFileNE");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 
    	Thread.sleep(1000);
    	page.uploadFileModalWindow(fileForProject3);  
    	Thread.sleep(3000);
  	    brochurePopup.clickUploadFileDropBTNE();
  	    Thread.sleep(3000);
  	    page.robotUpload();
  	    Thread.sleep(20000);	
    	log.info("End: testUploadFileNE");
    	log.info("--------------------------------------------");
    }
   /* 
    @Test(description="In NE, select update sharing")
    public void testUpdateSharing() throws InterruptedException {
    	log.info("Start: testUpdateSharing");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testUpdateShare();
    	Thread.sleep(1500);
    	log.info("End: testUpdateSharing");
    	log.info("--------------------------------------------");
    }
    */
    @Test(description="In NE, update sharing preferences")
    public void testUpdateSharingPre() throws InterruptedException {
    	log.info("Start: testUpdateSharingPre");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testUpdateSharePre();
    	Thread.sleep(3000);
    	log.info("End: testUpdateSharingPre");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void backToNGE() throws InterruptedException {
    	driver.switchTo().defaultContent();
    	Thread.sleep(2500);
    	testGotoNGE();
    }
    
    @Test(description="Verify the updated file")
    @Parameters({"fileForProject3"})
    public void testNEUpdatedFileNGE(String fileForProject3) throws InterruptedException {
    	log.info("Start: testNEUpdatedFileNGE");
    	CommonUtils commonUtils = new CommonUtils(driver);
    	commonUtils.testOpenProjectInWS(projectName + " edit");
    	Thread.sleep(3000);
    	commonUtils.testOpenProjectTabInWS1(projectName + " edit", "Files");
    	Thread.sleep(5000); 
    	int index = fileForProject3.lastIndexOf("/");
    	String imagTitle = fileForProject3.substring(index + 1);
    	System.out.println("imag title = " + imagTitle);
    	String imgName = driver.findElement(By.className("uploaded-file-name")).getAttribute("title");
    	System.out.println("imag name = " + imgName);
    	Assert.assertEquals(imgName, imagTitle);
    	log.info("End: testNEUpdatedFileNGE");
    	log.info("--------------------------------------------");
    }
    
    @Test
    public void logout() throws InterruptedException {
    	CommonUtils commonUtils = new CommonUtils(driver);
    	//commonUtils.spLogout();
    }
    
    @Test(description="SP supplier Login to Gmail")
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
   
    @Test(description="SP Supplier Check Gmail")
    public void testCheckGmail() throws InterruptedException {
  	  log.info("Start: testCheckGmail");
  	  Thread.sleep(5000);
  	  List<WebElement> emails = driver.findElements(By.tagName("span"));
  	  for (WebElement email : emails) {
  		  if (email != null) {
  			  String emailTitle = email.getText();
  			  if (emailTitle.indexOf("has been uploaded") >= 0) {
  				  email.click();
  				  break;
  			  }
  		  }
  	  }
  	  Thread.sleep(3000);
  	  WebElement acceptInv = driver.findElement(By.linkText("View File"));
	      if(!acceptInv.isDisplayed()) {
		      throw new IllegalStateException("File sharing link is missing!");
	      } else {
		      acceptInv.click();
	      } 	   
  	  Thread.sleep(2500);
  	  log.info("End: testCheckGmail");
  	  log.info("--------------------------------------------");
    }
}
   
   
   
   