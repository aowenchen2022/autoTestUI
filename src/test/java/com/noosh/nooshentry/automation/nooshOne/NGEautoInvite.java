/***********************************************************************
* This class tests the functionality for auto invitation
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

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
import java.io.FileNotFoundException;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.RuleCreatePage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Hashtable;
import java.util.List;

public class NGEautoInvite extends BaseSeleniumTest {
	Page page = new Page(driver); 
	Hashtable<String, String> credentials = new Hashtable<String, String>();
	String currentTime = String.valueOf(System.currentTimeMillis());
	String profileNameSP;
	String spSiteLoginPage;
	static String baseUrl;
	String firstWindow;
	String secondWindow;
	static String parentWindowHandle;
	static String spUrl;
	static String projectName;
	static String clientUrl;
   
	@BeforeTest
	public static void setParameters(ITestContext context) throws FileNotFoundException {
		if(domain.trim().equals("spd")) {
			baseUrl = "https://demo.noosh.com/service/signup"; 
			spUrl = "https://seleniumautoinvite.noosh.com";
			clientUrl = "https://seleniumautoinvite.noosh.com/automation/login";
		} else {
			baseUrl = "https://demo." + domain + "qa2.noosh.com/service/signup"; 
			spUrl = "https://seleniumautoinvite." + domain + ".noosh.com";
			clientUrl = "https://seleniumautoinvite." + domain + ".noosh.com/automation/login";
		}
	}

	@AfterTest
	public static void cleanup() {
		driver.quit();
		log.info("\n    Completed: ***** Auto Invitation *****\n\n");
	}

	@Test(description="SP login")
	@Parameters({"spLoginUserID", "passwordSP"})
	public void testSPLogin(String spLoginUserID, String passwordSP) throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(spUrl); 
		driver.manage().window().maximize();  
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.loginUser1(spLoginUserID);
		spSiteLoginPage = driver.getCurrentUrl();  	  
		log.info("End: testSPLogin");
		log.info("--------------------------------------------");
	}

	@Test
	public void testAuto() throws InterruptedException {
		log.info("Start: testAuto");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.clickAdmin();
		commonUtils.setAcct();
		commonUtils.goToAuto();
		log.info("End: testAuto");
		log.info("--------------------------------------------");
	}

	@Test
	public void testAddRulebtn() throws InterruptedException {
		log.info("Start: testAddRulebtn");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.addRule();
		log.info("End: testAddRulebtn");
		log.info("--------------------------------------------");
	}

	@Test
	@Parameters({"rulename", "ws", "sf1", "sf2", "coworker", "autoclient"})
	public void testAddRule(String rulename, String ws, String sf1, String sf2, String coworker, String autoclient) throws InterruptedException {
		log.info("Start: testAddRulebtn");
		rulename = rulename + unicID; 
		RuleCreatePage ruleCreatePage = new RuleCreatePage(driver);
		ruleCreatePage.addfields(rulename, ws, sf1, sf2, coworker, autoclient);
		ruleCreatePage.create();
		log.info("End: testAddRulebtn");
		log.info("--------------------------------------------");
	}

	@Test
	public void testCreateProj() throws InterruptedException {
		log.info("Start: testCreateProj");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.goToSPHome();
		SPHomePage spHomePage = new SPHomePage(driver);
		spHomePage.clickGlbCreateProj();
		log.info("End: testCreateProj");
		log.info("--------------------------------------------");
	}

	@Test
	@Parameters({"projectName", "projectNameField"})
	public void testExistingSPInputProjectName(String projectName, String projectNameField) throws InterruptedException {
		log.info("Start: testExistingSPInputProjectName");
		projectName = projectName + unicID;  
		CreateNewProject newProject = new CreateNewProject(driver); 
		newProject.inputGlbProjectName(projectName);   
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

	@Test
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

	@Test
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
		if(spSiteLoginPage.contains("sdm") || spSiteLoginPage.contains("sqa"))
			img.get(0).click(); 
		else if(spSiteLoginPage.contains("scd"))
			img.get(1).click();
		else
			img.get(0).click();
		Thread.sleep(3000);
		log.info("End: testExistingSPSelectSmartForm");
		log.info("--------------------------------------------"); 
	}

	@Test
	@Parameters({"projectName", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
		"specDescrSP", "fileForProject11"})
	public void testExistingSPcreateProjectWizard(String projectName, String descriptionNameSP,
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
		int index = fileForProject11.lastIndexOf("/");
		String imgTitle = fileForProject11.substring(index+1);
		String imgName = driver.findElement(By.id("createProjectTabFiles")).findElement(By.className("editable-field")).findElement(By.tagName("pre")).getAttribute("title");
		imgName = imgName + ".png";
		Assert.assertEquals(imgName, imgTitle);
		brochurePopup.getReviewAndSubmitTab();
		Thread.sleep(3000); 
		brochurePopup.clickCreateAndEstimateProjectBT();
		Thread.sleep(10000);
		log.info("End: testExistingSPcreateProjectWizard");
		log.info("--------------------------------------------"); 
	}    

	@Test
	@Parameters({"event"})
	public void testActMsg(String event) throws InterruptedException {
		log.info("Start: testActMsg");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testOpenProjectActTabInWS();
		SPHomePage spHomePage = new SPHomePage(driver);
    	spHomePage.verifyEventMsg(event);
		log.info("End: testActMsg");
		log.info("--------------------------------------------"); 
	}
	
	@Test
	@Parameters({"autoclient", "coworker", "teamTab"})
	public void testTeamUser(String autoclient, String coworker, String teamTab) throws InterruptedException {
		log.info("Start: testTeamUser");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testOpenProjectTabInWS2(projectName, teamTab);		
		SPHomePage spHomePage = new SPHomePage(driver);
		spHomePage.verifyCoworker(coworker);
		spHomePage.verifyClient(autoclient);		
		log.info("End: testTeamUser");
		log.info("--------------------------------------------"); 
	}
	
	@Test
	@Parameters()
	public void testDelRule() throws InterruptedException {
		testAuto();
		log.info("Start: testDelRule");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.delRule();
		commonUtils.exePopup();
		log.info("End: testDelRule");
		log.info("--------------------------------------------"); 
	}
	
	@Test
	@Parameters({"rulename", "ws", "sf1", "sf2", "autoclient"})
	public void testAutoWithoutcoworker(String rulename, String ws, String sf1, String sf2, String autoclient) throws InterruptedException {		
		testAuto();
		testAddRulebtn();
		log.info("Start: testAutoWithoutcoworker");
		rulename = rulename + unicID; 
		RuleCreatePage ruleCreatePage = new RuleCreatePage(driver);
		ruleCreatePage.addfields(rulename, ws, sf1, sf2, autoclient);
		ruleCreatePage.create();
		log.info("End: testAutoWithoutcoworker");
		log.info("--------------------------------------------"); 
	}
	
	@Test
	@Parameters({"projectName", "projectNameField"})
	public void inputProjectName(String projectName, String projectNameField) throws InterruptedException {
		testCreateProj();
		testExistingSPInputProjectName(projectName, projectNameField);
	}
	
	@Test
	@Parameters({"clientOrWorkspace"})
	public void selectWorkspace(String clientOrWorkspace) throws InterruptedException {
		testExistingSPSelectWorkspace(clientOrWorkspace);
		testExistingSPSelectSmartForm(clientOrWorkspace);
	}
	
	@Test
	@Parameters({"projectName", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
		"specDescrSP", "fileForProject11"})
	public void createProjectWizard(String projectName, String descriptionNameSP,
			String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
			String fileForProject11) throws InterruptedException, AWTException {
		testExistingSPcreateProjectWizard(projectName, descriptionNameSP, sku, referenceNumber, quantSP, quantSP2, quantSP3, specDescrSP,
				fileForProject11);
	}

	@Test
	@Parameters({"autoclient", "coworker", "teamTab"})
	public void testTeamUserWithNoCowoker(String autoclient, String coworker, String teamTab) throws InterruptedException {
		log.info("Start: testTeamUserWithNoCowoker");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testOpenProjectTabInWS2(projectName, teamTab);		
		SPHomePage spHomePage = new SPHomePage(driver);
		spHomePage.verifyClient(autoclient);		
		log.info("End: testTeamUserWithNoCowoker");
		log.info("--------------------------------------------"); 
	}
	
	@Test
	public void delRule() throws InterruptedException {
		testAuto();
		testDelRule();
	}

	//constructing....	
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
}    




   
   
   
   
   
   
  