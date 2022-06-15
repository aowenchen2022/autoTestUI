/***********************************************************************
 * This class tests the functionality for NooshOne Simple Flow test 
 * @author kenz
 ***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.asserts.Assertion;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.noosh.nooshentry.automation.nooshOne.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.ClientCreateProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientEditProjectPage;
import com.noosh.nooshentry.automation.buyerSite.ClientHomePage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.SPcreateQuotePage;
import com.noosh.nooshentry.automation.buyerSite.SupplierEstimatePopup;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestKmmsPaper extends BaseSeleniumTest {
	private static final String String = null;
	Page page = new Page(driver);
	Hashtable<String, String> credentials = new Hashtable<String, String>();
	String currentTime = String.valueOf(System.currentTimeMillis());
	String profileNameSP;
	String spSiteLoginPage;
	static String baseUrl;
	String firstWindow;
	String secondWindow;
	static String parentWindowHandle;
	static String smallFeaturesUrl;
	static String projectName;
	static String projName;
	static String clientUrl;
	static String rfeName;
	static String projRefNumber;
	static String rfeRefNumber;
	static String estRefNumber;
	private String[] arrays;
	
	@BeforeTest
	public static void setParameters(ITestContext context)
			throws FileNotFoundException {
		if (domain.trim().equals("spd")) {
			baseUrl = "https://" + domain + ".noosh.com/noosh/home/login";
			smallFeaturesUrl = "https://seleniumnge." + companyName + ".com";
			clientUrl = "https://seleniumnge." + companyName
					+ ".com/my_client/login";
		} else if (domain.trim().equals("kmmsqa")) {
			baseUrl = "https://kmms-qa.noosh.com/noosh/home/login";
			
		} else 
		{
			baseUrl = "https://" + domain + ".noosh.com/noosh/home/login";
			smallFeaturesUrl = "https://communisisuk." + domain + ".noosh.com/service/login";
			clientUrl = "https://seleniumnge." + domain + "." + companyName
					+ ".com/my_client/login";
		}
	}

	@AfterTest
	public static void cleanup() {
		driver.quit();
		log.info("\n    Completed: ***** SPEDUbat *****\n\n");
	}

	@Test(description = "Precondition: check the Workgroup Preferences options ")
	@Parameters({ "AdminLoginN", "AdminPassword", "BuyberWorkGroupNmae", "SupplierWorkGroupNmae" })
	public void testCheckWGpreferences(String AdminLoginN, String AdminPassword, String BuyberWorkGroupNmae, String SupplierWorkGroupNmae)
			throws InterruptedException {
		log.info("Start: testAdminLogin");
		testOutsourcerLogin(AdminLoginN, AdminPassword);
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
	
	@Test(description = "Precondition: check the Workgroup Preferences options ")
	@Parameters({ "BuyerLoginN", "AdminPassword", "BuyberWorkGroupNmae", "SupplierWorkGroupNmae" })
	public void testSetupExchangeRates(String BuyerLoginN, String AdminPassword, String BuyberWorkGroupNmae, String SupplierWorkGroupNmae)
			throws InterruptedException {
		log.info("Start: testAdminLogin");
		testBuyerLogin(BuyerLoginN, AdminPassword);
		log.info("End: testAdminLogin");
		selectWorkGroups(BuyberWorkGroupNmae);
		//searchWG(BuyberWorkGroupNmae);
		Thread.sleep(2000);
		selectMyGroup();
		Thread.sleep(1000);
		driver.findElement(By.name("clients")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("edit_icon")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("transactionalCurrencyId"))).selectByValue("3");
		Thread.sleep(1000);
		driver.findElement(By.id("update")).click();
		Thread.sleep(1000);
		selectMyGroup();
		driver.findElement(By.name("multi_currency")).click();
		Thread.sleep(1000);
		WebElement Clients = driver.findElement(By.id("clientTable")).findElement(By.className("alt2")).findElement(By.tagName("td"));
		String clientContent = Clients.getText();
		System.out.print(clientContent);
	

		if (!clientContent.equals("FrieslandCampina Branded Netherlands/Belgium"))
		{
			driver.findElement(By.name("edit")).click();
			Thread.sleep(1000);
			List <WebElement> adds = driver.findElements(By.id("add"));
			adds.get(1).click();
			Thread.sleep(500);
			new Select(driver.findElement(By.id("buClientWorkgroupId"))).selectByIndex(1);
			Thread.sleep(500);
			driver.findElement(By.id("clientTable")).findElement(By.tagName("input")).clear();
			driver.findElement(By.id("clientTable")).findElement(By.tagName("input")).sendKeys("0.86000");
			Thread.sleep(500);
			BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
			Thread.sleep(2000);   	    
			brochurePopup.callCalendar();
			driver.findElement(By.id("clientTable")).findElement(By.id("open_calendar")).click();
			Thread.sleep(1000); 
			brochurePopup.setNextMonth();
			Thread.sleep(1000); 
			brochurePopup.setComplationDate(); 
			Thread.sleep(1000);
			driver.findElement(By.id("update")).click();
			Thread.sleep(1000);
			
		}
		
		
		log.info("--------------------------------------------");
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
		new Select(driver.findElement(By.name("locale"))).selectByValue(locale);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(BuyerLoginN, AdminPassword);
		spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSPLogin");
		log.info("--------------------------------------------");
	}

	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testSwitchWorkGroups(String BuyberWorkGroupNmae) throws InterruptedException{
		
		selectWorkGroups(BuyberWorkGroupNmae);
		Thread.sleep(2000);
		
	}

	
	@Test(description = "Outsourcer login - step 1 ")
	@Parameters({ "OutsourcerLoginN", "SmartFormPassword" })
	public void testOutsourcerLogin(String OutsourcerLoginN, String SmartFormPassword)
			throws InterruptedException {
		log.info("Start: testOutsourcerLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(OutsourcerLoginN, SmartFormPassword);
		spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testOutsourcerLogin");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "click global 'create project' - step 2.1")
	public void testClickGlbCreateProj()
			throws InterruptedException {
		log.info("Start: testClickGlbCreateProj");
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		driver.findElement(By.name("topnav_createproject")).click();
		Thread.sleep(5000);
		log.info("End: testClickGlbCreateProj");
		log.info("--------------------------------------------");
	}

	@Test(description = "Input project name - step 2.2")
	@Parameters({ "productName1", "projectNameField" })
	public void testInputProjectName(String productName1,
		String projectNameField) throws InterruptedException {
		log.info("Start: testInputProjectName");
		productName1 = productName1 + unicID;
		projectName = productName1;
		CreateNewProject newProject = new CreateNewProject(driver);
		newProject.inputProjectName1(productName1);
		Thread.sleep(1000);
		try {
			AssertJUnit
					.assertTrue(Page.isTextPresent(projectNameField, driver));
		} catch (Throwable e) {
			System.out.println("Project Name field is missing!\n");
			log.info("Project Name field is missing!");
		}
		log.info("End: testInputProjectName");
		log.info("--------------------------------------------");
	}

	@Test(description = "Outsourcer select Client - step 2.4 ")
	public static void testSelectContact(String username, WebElement e) throws Exception{
	     
	    e.sendKeys(username);	
	    Thread.sleep(2000);
	    WebElement menuItem = driver.findElement(By.className("ui-menu-item"));
	    if(menuItem.isDisplayed())
	        {
	    		menuItem.click();
	        }
	         else {
	     		CommonUtils commonUtils = new CommonUtils(driver);
	    		commonUtils.getScreenShot(username + "contactLink issue");
	        	        	
	         	}
	        	System.out.println("No user matches with your input!!");
	            }
	
	
	@Test(description = "Create Project - step 2.3")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateProjectWizard(String productName1,
			String projectNameField) throws Exception {
		log.info("Start: testCreateProjectProjectWizard");
		new Select(driver.findElement(By.name("clientId"))).selectByIndex(1);
		Thread.sleep(1000);

		testInputProjectName(productName1, projectNameField);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		Thread.sleep(1000);
		//new Select(driver.findElement(By.name("@Division_str"))).selectByIndex(1);
		new Select(driver.findElement(By.name("@Brand_str"))).selectByIndex(1);
		//new Select(driver.findElement(By.name("proceed_to"))).selectByValue("2");
		//WebElement clientInput = driver.findElement(By.className("userInput")).findElement(By.tagName("input"));
		driver.findElement(By.id("@Contact_Name_str")).sendKeys("Test");
		//testSelectContact("SUnitB2", clientInput);
		Thread.sleep(1000);
		List<WebElement> Buttons = driver.findElements(By.id("create_project"));
		Buttons.get(1).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		//projRefNumber = driver.findElement(By.id("ProjectNumberID")).findElement(By.tagName("b")).getText();
		//String clientValue = driver.findElement(By.id("ClientAccountID")).findElement(By.tagName("b")).getText();
		//Assert.assertEquals(clientValue, "QA Client 1");
		log.info("End: testCreateProjectWizard");
		log.info("--------------------------------------------");
	}

	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateSpecs1(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;
		//ProjectMenu projectMenu = new ProjectMenu(driver);
		//projectMenu.clickSpecsLink();
		Thread.sleep(2000);
		driver.findElement(By.name("leftnav_select_campaign_spec_type")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("template_5003253")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);

		//driver.findElement(By.id("name")).sendKeys("Spec01");
		// driver.findElement(By.tagName("input"));
		/*
		new Select(driver.findElement(By.id("productTypeId"))).selectByIndex(1);
		driver.findElement(By.id("quantity1")).sendKeys("100");
		Thread.sleep(1000);
		driver.findElement(By.id("save_spec")).click();
		Thread.sleep(2000);
		
		List<WebElement> copySpec = driver.findElements(By.id("copy_spec"));
		copySpec.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("update_spec")).click();
		log.info("End: testCreateSpec");
		*/
		log.info("--------------------------------------------");
	}


	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateSpecs2(String specName, String Quantity)
			throws Exception {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;
		ProjectMenu projectMenu = new ProjectMenu(driver);
		//projectMenu.clickSpecsLink();
		Thread.sleep(2000);
		driver.findElement(By.id("leftnav_spec_create")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.tagName("select"))).selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("update_spec")).click();
		Thread.sleep(1000);
		
	}
	
	@Test(description = "Edit project budget - step 5")
	@Parameters({ "productName1" })
	public void testProjBudget(String specName)
			throws InterruptedException, AWTException {
		log.info("Start: Edit project budget");
		driver.findElement(By.linkText("Home")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Budget...")).click();
		Thread.sleep(1000);
		WebElement input = driver.findElement(By.id("table")).findElement(By.tagName("input"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;   
        executor.executeScript("arguments[0].value ='"+"150000"+"';",input);
		driver.findElement(By.id("table")).findElement(By.tagName("input")).sendKeys("{TAB}");
		driver.findElement(By.id("update")).click();
		Thread.sleep(2000);
		List<WebElement> sums = driver.findElements(By.className("sum"));
		String budget = sums.get(2).findElement(By.tagName("b")).getText();
		AssertJUnit.assertEquals(budget, "£150,000.00");

		log.info("End: Edit project budget");
		log.info("--------------------------------------------");
	}

	
	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateRFE(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
		specName = unicID;
		rfeName = projectName;
		//for (String handle : driver.getWindowHandles()) {
		//	driver.switchTo().window(handle);
		//}
		//driver.findElement(By.linkText("Home")).click();
		Thread.sleep(1000);
		ProjectMenu projectMenu = new ProjectMenu(driver);
		driver.findElement(By.id("leftnav_estimate_create_rfe")).click();
		Thread.sleep(1000);
		//List<WebElement> checkBoxes = driver.findElements(By.id("specRefId"));
		//checkBoxes.get(0).click();
		//checkBoxes.get(1).click();
		//driver.findElement(By.linkText("Check All")).click();
		//Thread.sleep(1000);
		//driver.findElement(By.id("continue")).click();
		Thread.sleep(1000);
		//driver.findElement(By.id("continue")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.id("rfe_0")).sendKeys(rfeName);
		new Select(driver.findElement(By.id("@rfe_0.initial_rfe_str"))).selectByIndex(1);
		List<WebElement> Inputs = driver.findElement(By.className("nplTable")).findElements(By.tagName("input"));
		Inputs.get(3).clear();
		Thread.sleep(2000);
		Inputs.get(3).sendKeys("1000");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		//List<WebElement> calendars = driver.findElements(By.id("open_calendar"));
		//calendars.get(1).click();
		Thread.sleep(1000); 

		//BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
		//brochurePopup.setNextMonth();
		//Thread.sleep(2000); 
		//brochurePopup.setComplationDate(); 
		WebElement supplierInput = driver.findElement(By.className("contactSelectorContainer")).findElement(By.tagName("input"));
		testSelectContact("Andy", supplierInput);
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("send_RFE")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);

		//rfeRefNumber = driver.findElement(By.name("rfeReference")).getText();
		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "AdminPassword" })
	public void testSupplierLogin(String supplierUserName, String AdminPassword)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		new Select(driver.findElement(By.name("locale"))).selectByValue(locale);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(supplierUserName, AdminPassword);
		// spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSupplierLogin");
		log.info("--------------------------------------------");
	}

	@Test(description = "switch workgroup ")
	@Parameters({ "SupplierWorkGroupNmae" })
    public static void testSwitchSupplierWorkGroups(String SupplierWorkGroupNmae) throws InterruptedException{
		
		selectWorkGroups(SupplierWorkGroupNmae);
		Thread.sleep(3000);
		
	}
	
	@Test(description = "Supplier create estimate - step 6")
	public void testSupplierEstCreate() throws InterruptedException, AWTException {
		String estRefNo;
		//projectName =  "Selenium198183";
		HomePage homePage = new HomePage(driver);
		homePage.clickCreateEstFromRFE(locale);
		Thread.sleep(2000);
		driver.findElement(By.name("createEstimate")).click();
		Thread.sleep(1000);
		List<WebElement> nobrs = driver.findElements(By.tagName("nobr"));
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input"))
				.sendKeys("10000");
		Thread.sleep(1000);
		//nobrs.get(2).findElement(By.tagName("input")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		Thread.sleep(2000);
		List<WebElement> selects = driver.findElements(By.tagName("select"));
		new Select(selects.get(3)).selectByIndex(1);
		Thread.sleep(1000);
		new Select(selects.get(4)).selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.className("qty")).sendKeys("1000");
		Thread.sleep(1000);

		//nobrs.get(4).findElement(By.tagName("input")).sendKeys("50");
		/*List<WebElement> selects = driver.findElements(By.tagName("select"));
		List<WebElement> tables = driver.findElements(By.tagName("table"));
		List<WebElement> inputs = tables.get(17).findElements(By.tagName("input"));
		new Select(selects.get(4)).selectByIndex(1);
		new Select(selects.get(5)).selectByIndex(1);
		inputs.get(7).sendKeys("100");*/
		//driver.findElement(By.id("prTableView")).findElement(By.id("actualQty1")).sendKeys("100");
		Thread.sleep(1000);
		//String sizeId = driver.findElement(By.id("prTableView")).findElement(By.tagName("input")).getText();
		//Assert.assertEquals(sizeId, "PA0000006049");
		driver.findElement(By.id("SEND_ESTIMATE")).click();
		Thread.sleep(6000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		//estRefNo = driver.findElement(By.className("alt1")).findElement(By.tagName("a")).getText();
		//estRefNumber = String.valueOf(estRefNo).substring(9, 16);

		log.info("--------------------------------------------");
	}

	@Test
	public void logout() throws InterruptedException {
		testLogout();
	}

	@Test(description = "Buyer login2")
	@Parameters({ "BuyerLoginN", "AdminPassword", "BuyberWorkGroupNmae" })
	public void testBuyerLoginagain(String BuyerLoginN, String AdminPassword, String BuyberWorkGroupNmae)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, AdminPassword);
		selectWorkGroups(BuyberWorkGroupNmae);
	}
	
	@Test(description = "Buyer login3")
	@Parameters({ "BuyerLoginN", "AdminPassword", "BuyberWorkGroupNmae" })
	public void testBuyerLogin3rd(String BuyerLoginN, String AdminPassword, String BuyberWorkGroupNmae)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, AdminPassword);
		selectWorkGroups(BuyberWorkGroupNmae);
	}
	
	@Test(description = "Buyer login3")
	@Parameters({ "BuyerLoginN", "AdminPassword", "BuyberWorkGroupNmae" })
	public void testBuyerLogin4th(String BuyerLoginN, String AdminPassword, String BuyberWorkGroupNmae)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, AdminPassword);
		selectWorkGroups(BuyberWorkGroupNmae);
	}
	
	@Test(description = "Buyer login5")
	@Parameters({ "BuyerLoginN", "AdminPassword", "BuyberWorkGroupNmae" })
	public void testBuyerLogin5th(String BuyerLoginN, String AdminPassword, String BuyberWorkGroupNmae)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, AdminPassword);
		selectWorkGroups(BuyberWorkGroupNmae);
	}
	
	@Test(description = "Outsourcer login")
	@Parameters({ "OutsourcerLoginN", "BuyerPassword" })
	public void testOutsourcerLogin2nd(String OutsourcerLoginN, String BuyerPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, BuyerPassword);
	}
	
	@Test(description = "Outsourcer login3")
	@Parameters({ "OutsourcerLoginN", "BuyerPassword" })
	public void testOutsourcerLogin3rd(String OutsourcerLoginN, String BuyerPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, BuyerPassword);
	}
	
	@Test(description = "Outsourcer login4")
	@Parameters({ "OutsourcerLoginN", "BuyerPassword" })
	public void testOutsourcerLogin4th (String OutsourcerLoginN, String BuyerPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, BuyerPassword);
	}
	
	@Test(description = "Outsourcer create quote - step 7")
	public void testQuoteCreate() throws Exception {
		HomePage homePage = new HomePage(driver);
		//homePage.clickEstfromRecent();
		Thread.sleep(2000);
		homePage.clickReviewEst(locale);
		Thread.sleep(2000);
		List<WebElement> Tables = driver.findElements(By.className("nplTable"));
		List<WebElement> Inputs = Tables.get(1).findElements(By.tagName("input"));
		Inputs.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("@quote.SHIP_FROM_str"))).selectByIndex(1);
		new Select(driver.findElement(By.id("@quote.SHIP_TO_str"))).selectByIndex(1);

		driver.findElement(By.id("send_quote")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("quoteCreate_body")).findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("update_status")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("statusId"))).selectByValue("2000011");
		Thread.sleep(1000);
		driver.findElement(By.id("update_status")).click();
		Thread.sleep(2000);
		//homePage.clickMydesklnk();
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create order - step 8")
	public void testSellOrderCreate() throws InterruptedException {
		//HomePage homePage = new HomePage(driver);
		//homePage.clickEstfromRecent();
		driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("goto_accept_quote")).click();
		Thread.sleep(1000);
		//String totalPrice = driver.findElement(By.id("quote.grandTotal")).getText();
		//Assert.assertEquals(totalPrice, "141,451.95");
		driver.findElement(By.id("poNumber")).sendKeys("poNo");
		driver.findElement(By.id("create_order")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Create order - step 8")
	public void testBuyOrderCreate() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		//homePage.clickEstfromRecent();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("order.reasonId"))).selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.id("proceed_to_confirmation")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_order_creation")).click();
		Thread.sleep(15000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create order - step 8")
	public void testBuyPaperOrderCreate() throws Exception {
		HomePage homePage = new HomePage(driver);
		homePage.clickCreatePaperOrder(locale);
		Thread.sleep(1000);
		driver.findElement(By.id("createExternalOrderButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Check All")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("frame")).findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("order.paymentReference")).sendKeys("testNO");
		WebElement supplierInput = driver.findElement(By.className("userInput")).findElement(By.tagName("input"));
		testSelectContact("CHARTERHOUSE", supplierInput);
		Thread.sleep(1000);
		List<WebElement> tables = driver.findElements(By.className("nplTable"));
		tables.get(3).findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("VIEW_ORDER_LIST")).click();
		Thread.sleep(3000);
		List<WebElement> trs = driver.findElement(By.id("orders")).findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(9).findElements(By.tagName("td"));
		String status = tds.get(3).getText();
	
		switch(locale){
		case "en_US":
			Assert.assertEquals(status, "Accepted");	
			break;
		case "en_EU":
			Assert.assertEquals(status, "Accepted");	
			break;
		case "de_DE":
			Assert.assertEquals(status, "Angenommen");	
			break;
		case "es_LA":
			Assert.assertEquals(status, "Aceptado");	
			break;
	    }
		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create order - step 8")
	public void testBuyPaperOrderCreateAgain() throws Exception {
		driver.findElement(By.id("createExternalOrderButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Check All")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("frame")).findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("order.paymentReference")).sendKeys("testNO");
		WebElement supplierInput = driver.findElement(By.className("userInput")).findElement(By.tagName("input"));
		testSelectContact("CHARTERHOUSE", supplierInput);
		Thread.sleep(1000);
		driver.findElement(By.className("nplTable")).findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		/*driver.findElement(By.id("VIEW_ORDER_LIST")).click();
		Thread.sleep(1000);
		List<WebElement> trs = driver.findElement(By.id("orders")).findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(7).findElements(By.tagName("td"));
		String status = tds.get(3).getAttribute("value");
		Assert.assertEquals(status, "Accepted");
*/
		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "AdminPassword" })
	public void testSupplierLoginAgain(String supplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, AdminPassword);
		Thread.sleep(2000);
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "AdminPassword" })
	public void testSupplierLogin2nd(String supplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, AdminPassword);
		Thread.sleep(4000);
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "AdminPassword" })
	public void testSupplierLogin3rd(String supplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, AdminPassword);
		Thread.sleep(2000);
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "AdminPassword" })
	public void testSupplierLogin4th(String supplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, AdminPassword);
		Thread.sleep(2000);
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "paperSupplierUserName", "AdminPassword" })
	public void testPaperSupplierLogin(String paperSupplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(paperSupplierUserName, AdminPassword);
		Thread.sleep(3000);
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "paperSupplierUserName", "AdminPassword" })
	public void testPaperSupplierLoginAgain(String paperSupplierUserName, String AdminPassword)
			throws InterruptedException {
		testSupplierLogin(paperSupplierUserName, AdminPassword);
		Thread.sleep(2000);
	}
	
	public static void mouseMove(WebElement e) throws Exception {
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(e).perform();
		Thread.sleep(1500);

	}

	@Test(description = "Supplier accepts order - step 9")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testSupplierOrderAcceptance(String SupplierWorkGroupNmae) throws Exception {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickReviewOrder(locale);
		Thread.sleep(1500);
		driver.findElement(By.id("accept_order")).click();
		Thread.sleep(1000);
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(12000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier accepts order - step 12")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testSupplierCloseOrderAcceptance(String SupplierWorkGroupNmae) throws Exception {

		HomePage homePage = new HomePage(driver);
		homePage.clickReviewClosingChangeOrder(locale);
		Thread.sleep(1500);
		driver.findElement(By.id("accept_change_order")).click();
		Thread.sleep(1000);
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(6000);

		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier accepts order - step 12")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testPaperCloseOrderAcceptance(String SupplierWorkGroupNmae) throws Exception {
		driver.findElement(By.className("milonictable")).findElement(By.tagName("a")).click();
		Thread.sleep(1500);
		switch(locale){
		case "en_US":
			List<WebElement>  usLink = driver.findElements(By.linkText("Review Closing Change Order"));
			usLink.get(1).click();	
			break;
		case "en_EU":
			List<WebElement>  euLink = driver.findElements(By.linkText("Review Closing Change Order"));
			euLink.get(1).click();
			break;
		case "de_DE":
			List<WebElement>  deLink = driver.findElements(By.linkText("Abschlussänderung prüfen"));
			deLink.get(1).click();
			break;
		case "es_LA":
			List<WebElement>  laLink = driver.findElements(By.linkText("Revisar Pedido de cambio final"));
			laLink.get(1).click();
			break;
	    }
		//List<WebElement>  links = driver.findElements(By.linkText("Review Closing Change Order"));
		//links.get(1).click();
		Thread.sleep(1500);
		//driver.findElement(By.id("route_change_order")).click();
		Thread.sleep(3500);
		driver.findElement(By.id("accept_change_order")).click();
		Thread.sleep(1000);
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(6000);

		log.info("--------------------------------------------");
	}
	
	@Test(description = "Outsourcer accepts order - step 12")
	@Parameters({ "OutsourcerWorkGroupNmae"})
	public  void testOutsourcerCloseOrderAcceptance(String OutsourcerWorkGroupNmae) throws Exception {
	
		driver.findElement(By.linkText("Review Closing Change Order")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("accept_change_order")).click();
		Thread.sleep(1000);
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(12000);

		log.info("--------------------------------------------");
	}
	
	
	@Test(description = "Create change order - step 114")
	public void testChangeOrderCreate() throws Exception {
		Thread.sleep(2000);
		//HomePage homePage = new HomePage(driver);
		driver.findElement(By.id("MYDESK_PROJECT_ACTIVITY_body")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("orders")).findElement(By.tagName("a")).click();
		//homePage.clickEstfromRecent();
		Thread.sleep(1000);
		driver.findElement(By.id("create_change_order")).click();
		Thread.sleep(1000);
		List<WebElement>  tables = driver.findElements(By.className("nplTable"));
		tables.get(1).findElement(By.tagName("input")).sendKeys("ChangeOrderName");
		//driver.findElement(By.id("orderItem1.value")).clear();
		Thread.sleep(1000);
		//driver.findElement(By.id("orderItem1.value")).sendKeys("1000");
		Thread.sleep(1000);
		//driver.findElement(By.id("orderItem1.value")).sendKeys("{Tab}");
		Thread.sleep(1000);
		//String valueAfter = driver.findElement(By.id("orderItem1.valueAfter")).getAttribute("value");
		//Assert.assertEquals(valueAfter, "6,363.00");
		driver.findElement(By.id("preview_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(15000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Outsourcer accepts order - step 9")
	@Parameters({ "BuyberWorkGroupNmae"})
	public void testOutsourcerChangeOrderAcceptance(String BuyberWorkGroupNmae) throws Exception {
		

		HomePage homePage = new HomePage(driver);
		homePage.clickReviewChangeOrder(locale);
		Thread.sleep(2000);
		//driver.findElement(By.id("route_change_order")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("accept_change_order")).click();

		Thread.sleep(5000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create close change order - step 14")
	public void testCloseChangeOrderCreate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("MYDESK_PROJECT_ACTIVITY_body")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		//homePage.clickEstfromRecent();
		//driver.findElement(By.linkText("Order with Changes")).click();
		driver.findElement(By.className("sum")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("complete")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("@orderItem1.VAT_SELECTION_DD_str"))).selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.id("invoice-1.ownerReference")).sendKeys("NO123");
		//driver.findElement(By.id("@invoice-1.SUBMIT_PDF_INVOICE_bool")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("preview_closing_change_order")).click();
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(10000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create close change order - step 14")
	public void testPaperCloseChangeOrderCreate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("MYDESK_PROJECT_ACTIVITY_body")).findElement(By.tagName("a")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("leftnav_order_home")).click();
		Thread.sleep(1000);
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		//homePage.clickEstfromRecent();
		List<WebElement> Orderlnks = driver.findElement(By.id("orders")).findElements(By.tagName("a"));
		Orderlnks.get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.id("complete")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(3000);
		new Select(driver.findElement(By.id("@orderItem1.VAT_SELECTION_DD_str"))).selectByIndex(1);
		driver.findElement(By.id("invoice-1.ownerReference")).sendKeys("NO123");
	//	driver.findElement(By.id("@invoice-1.SUBMIT_PDF_INVOICE_bool")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("preview_closing_change_order")).click();
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(1000);
		
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(10000);
		driver.findElement(By.name("topnav_logout")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create invoice - step 14")
	@Parameters({"fileForProject11"})
	public void testBuyInvoiceCreate(String fileForProject11) throws InterruptedException, AWTException {
		HomePage homePage = new HomePage(driver);
		//testSelectProjFromRecent();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Invoices")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("orderId")).click();
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("INVOICE_NUMBER_ID")).findElement(By.tagName("input")).sendKeys("11112222");
		driver.findElement(By.id("REFERENCE_NUMBER_ID")).findElement(By.tagName("input")).sendKeys("Refer666");
		List<WebElement> Tables = driver.findElements(By.className("nplTable"));
		List<WebElement> Trs = Tables.get(2).findElements(By.tagName("tr"));
		WebElement TotalQty = Trs.get(6).findElement(By.tagName("input"));
		
        JavascriptExecutor executor = (JavascriptExecutor) driver;   
        executor.executeScript("arguments[0].value ='"+"200"+"';",TotalQty);
		driver.findElement(By.id("prorate")).click();
		
		//driver.findElement(By.id("PO_LINE_NO_str")).sendKeys("poNo");
		driver.findElement(By.id("send")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("update")).click();
		Thread.sleep(2000);
		AssertJUnit.assertTrue(driver.findElement(By.id("invoiceList")).findElement(By.tagName("a")).isDisplayed());	
		driver.findElement(By.className("alt1")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("attach_new_file")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("status_draft")).isDisplayed();
		driver.findElement(By.id("file")).click();
		testAttachFile(fileForProject11);
		Thread.sleep(3000);
		driver.findElement(By.id("download")).isDisplayed();
		driver.findElement(By.id("edit")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("send")).click();
		Thread.sleep(4000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Outsourcer accepts buy invoice - step 9")
	public void testOutsourcerInvoiceAcceptance() throws Exception {
		
		driver.findElement(By.id("quoteDeskoid")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("accept")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(5000);

		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create invoice - step 14")
	public void testSellInvoiceCreate() throws InterruptedException {
		driver.findElement(By.id("invoiceSection")).findElement(By.linkText("Selling")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("orderId")).click();
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("INVOICE_NUMBER_ID")).findElement(By.tagName("input")).sendKeys("11112222");
		driver.findElement(By.id("REFERENCE_NUMBER_ID")).findElement(By.tagName("input")).sendKeys("Refer666");
		List<WebElement> Tables = driver.findElements(By.className("nplTable"));
		List<WebElement> Trs = Tables.get(2).findElements(By.tagName("tr"));
		WebElement TotalQty = Trs.get(7).findElement(By.tagName("input"));
		
        JavascriptExecutor executor = (JavascriptExecutor) driver;   
        executor.executeScript("arguments[0].value ='"+"200"+"';",TotalQty);
		driver.findElement(By.id("prorate")).click();
		Trs.get(6).findElement(By.tagName("input")).sendKeys("PO333");
		driver.findElement(By.id("send")).click();
		Thread.sleep(6000);
		AssertJUnit.assertTrue(driver.findElement(By.id("invoiceList")).findElement(By.tagName("a")).isDisplayed());	
		log.info("--------------------------------------------");
	}

	
	@Test(description = "Buyer create Message - step 11-1")
	public void testSelectProjFromRecent() throws Exception {
		driver.findElement(By.id("topnav_recentprojects_control")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("rpmenu_iframe"))
				.findElement(By.tagName("span")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);

		log.info("--------------------------------------------");
	}

	   
    @Test
	public void testAttachFile(String fileForProject11) throws InterruptedException, AWTException {    
  	   log.info("Start: test upload file");
  
  	   Thread.sleep(3000);
  	    page.uploadFileModalWindow(fileForProject11);   
  	   //brochurePopup.clickUploadFileDropBT();
  	   Thread.sleep(2000);
  	   page.robotUpload();
  	   Thread.sleep(4500);	
  	   //commonUtils.verifyUploadedImg(fileForProject11);
  	
  	   log.info("End: testSPcreateProjectProjectWizard");
  	   log.info("--------------------------------------------"); 
    }
	
	@Test(description = "update sharing preferences")
	public void testUpdateSharingPre() throws InterruptedException {
		log.info("Start: testUpdateSharingPre");
		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.switchTo().frame("mppv");
		CommonUtils commonUtils = new CommonUtils(driver);
		Thread.sleep(3000);
	 	List <WebElement> CheckBoxes = driver.findElement(By.className("sharing-team-list")).findElements(By.className("sharing-team-li"));
	 	CheckBoxes.get(2).findElement(By.tagName("input")).click();
		commonUtils.testUpdateSharePre();
	  	driver.switchTo().defaultContent();
		Thread.sleep(3000);
		log.info("End: testUpdateSharingPre");
		log.info("--------------------------------------------");
	}
	
	@Test(description="upload file - step 11")
    @Parameters({"fileForProject11"})
    public void testUploadFile(String fileForProject11) throws InterruptedException, AWTException {    
  	   log.info("Start: test upload file");
  	   driver.findElement(By.linkText("Files")).click();
  	   CommonUtils commonUtils = new CommonUtils(driver);
  	   
  	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);  	    
  	     
  	   Thread.sleep(3000);
  	    page.uploadFileModalWindow(fileForProject11);   
  	   /* tmp comment out 11/15
  	   WebElement imgPreview = driver.findElement(By.className("uploaded-file-preview-image"));
  	   if (imgPreview == null) {
  			   System.out.println("Image uploading is failed!");
  			   log.info("Image uploading is failed!"); 
  	   } 
  	   */
  	   driver.switchTo().frame("mppv");

  	   brochurePopup.clickUploadFileDropBT();
  	   Thread.sleep(2000);
  	   page.robotUpload();
  	   Thread.sleep(4500);	
  	   commonUtils.verifyUploadedImg(fileForProject11);
  	   driver.switchTo().defaultContent();
  	   log.info("End: testSPcreateProjectProjectWizard");
  	   log.info("--------------------------------------------"); 
    }

	@Test(description = "Verify the updated file")
	@Parameters({ "fileForProject3" })
	public void testDownloadFile(String fileForProject3)
		throws InterruptedException {
		log.info("Start: testDownloadFile");
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	 	driver.switchTo().frame("mppv");
		List<WebElement> icons = driver.findElements(By.className("file-icons-padding"));
		icons.get(1).click();
		Thread.sleep(2000);
		icons.get(2).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
	  	driver.switchTo().defaultContent();
		log.info("End: testDownloadFile");
		log.info("--------------------------------------------");
	}

	

	@Test(description = "Supplier Check Gmail")
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
		if (!acceptInv.isDisplayed()) {
			throw new IllegalStateException("File sharing link is missing!");
		} else {
			acceptInv.click();
		}
		Thread.sleep(2500);
		log.info("End: testCheckGmail");
		log.info("--------------------------------------------");
	}

	@Test(description = "Create Message - step 12")
	public void testCreateMSG() throws InterruptedException {
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickMessageLink();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Post...")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("subject")).sendKeys("test Subject");
		driver.findElement(By.name("content")).sendKeys("test content");
		Thread.sleep(1000);
		driver.findElement(By.name("post_all")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.clickMydesklnk();
		Thread.sleep(2000);
		/*List<WebElement> panels = driver.findElements(By
				.className("glb-wdgt-set"));
		Actions builder = new Actions(driver);
		builder.moveToElement(panels.get(8)).clickAndHold().build();
		builder.clickAndHold(panels.get(8)).moveToElement(
				driver.findElement(By.className("glb-wdgt-set-menu"))
				.findElement(By.tagName("li"))).release().build().perform();
		driver.findElement(By.className("glb-wdgt-set-menu")).findElement(By.tagName("li")).click();
		Thread.sleep(10000);*/
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwd" })
	public void testMSGSupplierLogin(String supplierUserName, String passwd)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, passwd);
	}

	@Test(description = "Reply Message - step 11-2")
	public void testReplyMSG() throws Exception {
		Thread.sleep(2000);
		WebElement MSGlnk = driver.findElement(By
				.className("glb-wdgt-msg-line-content"));
		Actions builder = new Actions(driver);
		Thread.sleep(1000);
		//builder.moveToElement(MSGlnk).perform();
		builder.moveToElement(MSGlnk)
				.moveToElement(
						driver.findElement(By.className("message-actions"))
								.findElement(By.className("glb-button"))).click().perform();
		Thread.sleep(1000);
		//builder.moveToElement(MSGlnk).perform();
		builder.moveToElement(MSGlnk)
		.moveToElement(
				driver.findElement(By.className("message-actions"))
						.findElement(By.className("ng-pristine"))).click()
		.sendKeys("Rely from supplier").perform();
		Thread.sleep(1000);
		builder.moveToElement(MSGlnk)
				.moveToElement(
						driver.findElement(By.className("message-actions"))
								.findElement(By.className("glb-button")))
				.click().perform();
		Thread.sleep(3000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Buyer check Message - step 11 -3")
	public void testBuyerCheckMSG() throws Exception {
		WebElement MSGlnk = driver.findElement(By
				.className("glb-message-title"));
		Actions builder = new Actions(driver);
		Thread.sleep(10000);
		builder.moveToElement(MSGlnk).clickAndHold().perform();
		Thread.sleep(1000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "logout")
	public void testLogout() throws InterruptedException {
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.Logout();
		Thread.sleep(3500);
		log.info("End: testSPLogout");
		log.info("--------------------------------------------");
	}


	@Test(description = "create Task - step 12")
	public void testCreateTask() throws Exception {
		//HomePage homePage = new HomePage(driver);
		//homePage.clickRFEfromRecent();
		driver.findElement(By.linkText("Tasks")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Create...")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("taskTypeId"))).selectByIndex(1);
		
		Thread.sleep(1000);
		WebElement userInput = driver.findElement(By.className("userInput")).findElement(By.tagName("input"));
		testSelectContact("Qa Supplier1", userInput);
		
		driver.findElement(By.name("create_task")).click();
		Thread.sleep(1000);
		
		log.info("--------------------------------------------");
	}

	
	 public void MyGroup() throws InterruptedException {	 
		 WebElement topMenu = driver.findElement(By.className("ui-icon-settings"));
		 topMenu.click();
		 Thread.sleep(1000);
		 Actions builder = new Actions(driver);
		 builder.moveToElement(topMenu).perform();
		 Thread.sleep(1000);
		 WebElement adminLink = driver.findElement(By.name("topnav_admin"));
		 WebElement myGroupLink = driver.findElement(By.name("topnav_my_group"));
		 builder.moveToElement(adminLink).moveToElement(myGroupLink).click().perform();
		 Thread.sleep(3500);
	 }
	
	@Test(description = "The elastic search page testing")
	@Parameters({ "productName1", "projectNameField" })
	public void testElasticSearch(String productName1,
			String projectNameField) throws Exception {
		log.info("Start: testSPcreateProjectProjectWizard");
		List<WebElement> seachBtns = driver.findElements(By.className("fa-search"));
		seachBtns.get(1).click();
		//List<WebElement> dropDowns = driver.findElements(By.className("fa-caret-down"));
		//dropDowns.get(1).click();
		Thread.sleep(1000);
		System.out.println(projRefNumber);
		System.out.println(rfeRefNumber);
		System.out.println(estRefNumber);
		//String arrays[] = {"12542017","6896662","Estimate 8707671"};
		String arrays[] = new String[3];
		//arrays[0] = new String(projRefNumber);
		arrays[0] = new String("5101531");
		arrays[1] = new String("5031302");
		arrays[2] = new String("5031799");
		//arrays = null;


		   for (int i = 0; i <3; i++) {
               driver.findElement(By.id("searchQuery")).clear();
			   driver.findElement(By.id("searchQuery")).sendKeys(arrays[i]);
			   driver.findElement(By.className("search-button")).click();
			   Thread.sleep(5000);
			   checkResultExist(arrays[i]);
		   }
		
				log.info("End: The elastic search page testing");
		log.info("--------------------------------------------");
	}
	
    public static boolean checkResultExist(String refNumber) throws Exception {
        boolean result = false;
        try {
        	if (refNumber == driver.findElement(By.tagName("em")).getText())
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
    
    public static void selectWorkGroups(String workgroups) throws InterruptedException{
    	//Hover over My Desk>Summary> click on Switch workgroups
		List <WebElement> lnks = driver.findElement(By.className("small")).findElements(By.tagName("a"));
		lnks.get(1).click();
		Thread.sleep(2000);
    	
    	//Select from the drop down
    	//new Select(driver.findElement(By.id("uid"))).selectByValue("5477282");
     	new Select(driver.findElement(By.id("uid"))).selectByVisibleText(workgroups);;
		Thread.sleep(1000);
        //Click "Choose Group" button
    	driver.findElement(By.id("save")).click();
		Thread.sleep(3000);


    }
    
    public static void selectMyGroup() throws InterruptedException{
    	//Hover over My Desk>Summary> click on Switch workgroups
    	WebElement adminLnk = driver.findElement(By.linkText("Admin"));
    	
    	Actions builder = new Actions(driver);
    	builder.moveToElement(adminLnk).perform();
    	Thread.sleep(1500);
    	builder.moveToElement(adminLnk).moveToElement(driver.findElement(By.linkText("My Group"))).click()
    	.build().perform();
    	Thread.sleep(1000);

    }
    
    

}
