/***********************************************************************
 * This class tests the functionality for NooshOne Simple Flow test 
 * @author kenz
 ***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

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

import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
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

public class TestCommunisisMSG extends BaseSeleniumTest {
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
		} else {
			baseUrl = "https://" + domain + ".noosh.com/noosh/home/login";
			smallFeaturesUrl = "https://seleniumnge." + domain + "."
					+ companyName + ".com";
			clientUrl = "https://seleniumnge." + domain + "." + companyName
					+ ".com/my_client/login";
		}
	}

	@AfterTest
	public static void cleanup() {
		driver.quit();
		log.info("\n    Completed: ***** SPEDUbat *****\n\n");
	}

	@Test(description = "Buyer login - step 1 ")
	@Parameters({ "BuyerLoginN", "BuyerPassword" })
	public void testBuyerLogin(String BuyerLoginN, String BuyerPassword)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(BuyerLoginN, BuyerPassword);
		spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSPLogin");
		log.info("--------------------------------------------");
	}

	@Test(description = "Outsourcer login - step 1 ")
	@Parameters({ "OutsourcerLoginN", "BuyerPassword" })
	public void testOutsourcerLogin(String OutsourcerLoginN, String BuyerPassword)
			throws InterruptedException {
		log.info("Start: testOutsourcerLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(OutsourcerLoginN, BuyerPassword);
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
		homePage.clickGlbCreateProj();
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
		testInputProjectName(productName1, projectNameField);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		Thread.sleep(1000);
		WebElement clientInput = driver.findElement(By.className("userInput")).findElement(By.tagName("input"));
		//testSelectContact("qa client", clientInput);
		Thread.sleep(1000);
		List<WebElement> Buttons = driver.findElements(By.id("create_project"));
		Buttons.get(1).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		projRefNumber = driver.findElement(By.id("ProjectNumberID")).findElement(By.tagName("b")).getText();
		//String clientValue = driver.findElement(By.id("ClientAccountID")).findElement(By.tagName("b")).getText();
		//Assert.assertEquals(clientValue, "QA Client 1");
		log.info("End: testCreateProjectWizard");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Invite member and supplier")
	@Parameters({ "productName1", "projectNameField" })
	public void testInviteMembers(String productName1,
		String projectNameField) throws Exception {
		log.info("Start: Invite member and supplier");
		driver.findElement(By.linkText("Teams")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("invite_member")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("personPickerTab")).findElement(By.linkText("Contacts")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		List <WebElement> checkboxes = driver.findElements(By.id("selectContacts"));
		checkboxes.get(0).click();
		checkboxes.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("done")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("continue")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("sendInvitation")).click();
		Thread.sleep(5000);
		List <WebElement> buttons = driver.findElements(By.tagName("button"));
		buttons.get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.className("personPickerTab")).findElement(By.linkText("Contacts")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		List <WebElement> suppliercheckboxes = driver.findElements(By.id("selectContacts"));
		suppliercheckboxes.get(7).click();
		suppliercheckboxes.get(9).click();
		Thread.sleep(1000);
		driver.findElement(By.id("done")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("inviteSupplier_body")).findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		//driver.findElement(By.id("sendInvitation")).click();
		//Thread.sleep(5000);
		log.info("End: Invite member and supplier");
		log.info("--------------------------------------------");
	}
	

	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateSpecs(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickSpecsLink();
		Thread.sleep(2000);
		projectMenu.clickCreateSpec();
		Thread.sleep(1000);
		driver.findElement(By.id("typeId_5000005")).click();
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		// driver.switchTo().frame("myProjectProductView-1");
		//driver.switchTo().frame("mppv");
		driver.findElement(By.id("name")).sendKeys("spec1");
		Thread.sleep(2000);
		// driver.findElement(By.tagName("input"));
		// Inputs.get(7).sendKeys("100");
		driver.findElement(By.id("quantity1")).sendKeys("100");
		driver.findElement(By.id("save_spec")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		List<WebElement> tables = driver.findElement(By.className("specSection")).findElements(By.tagName("table"));
		List<WebElement> tds = tables.get(2).findElements(By.tagName("td"));
		WebElement specName1 = tds.get(1);
		WebElement quantity = tds.get(14);
		String specName1Value = specName1.getText();
		String quantityValue = quantity.getText();
		Assert.assertEquals(specName1Value, "spec1");
		//Assert.assertEquals(quantityValue, "100");
		
		
		projectMenu.clickCreateSpec();
		Thread.sleep(1000);
		driver.findElement(By.id("typeId_5006633")).click();
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		driver.switchTo().frame("mppv");
		List<WebElement> Inputs = driver.findElements(By.tagName("input"));
		Thread.sleep(5000);
		// driver.findElement(By.tagName("input"));
		Inputs.get(2).sendKeys("spec2");
		//Inputs.get(10).sendKeys("200");
		driver.switchTo().defaultContent();
		driver.findElement(By.id("qty_1")).sendKeys("200");
		Thread.sleep(1000);
		driver.switchTo().frame("mppv");
		driver.findElement(By.linkText("Save")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		WebElement specName2 = driver.findElement(By.id("cENLi7JQ_c5"))
				.findElement(By.className("pe-dd-cellCard-body"))
				.findElement(By.tagName("span"));
		WebElement quantity1 = driver.findElement(By.id("KOrPNcAQ_c9"))
				.findElement(By.className("pe-dd-cellCard-body"))
				.findElement(By.tagName("span"));
		String specName2Value = specName2.getText();
		String quantity1Value = quantity1.getText();
		Assert.assertEquals(specName2Value, "spec2");
		//Assert.assertEquals(quantity1Value, "200");
		
		driver.switchTo().defaultContent();
		log.info("End: testCreateProjectWizard");
		log.info("--------------------------------------------");
	}

	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateRFE(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateRFE");
		specName = unicID;
		rfeName = projectName;
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickEstimatesLink();
		Thread.sleep(1000);
		projectMenu.clickCreateRFELink();
		Thread.sleep(1000);
		List<WebElement> checkBoxes = driver.findElements(By.id("specRefId"));
		checkBoxes.get(0).click();
		checkBoxes.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("rfe_0.title")).sendKeys(rfeName);
		/*List<WebElement> Inputs = driver.findElement(By.className("nplTable")).findElements(By.tagName("input"));
		Thread.sleep(5000);
		Inputs.get(2).sendKeys("100");
		Inputs.get(10).clear();
		Inputs.get(10).sendKeys("200");
		*/
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("input")).sendKeys("100");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		WebElement contactSelector = driver.findElement(
				By.className("personPickerTab")).findElement(By.tagName("a"));
		contactSelector.click();
		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		List<WebElement> suppliers = driver.findElements(By.name("selectContacts"));
		suppliers.get(0).click();
		Thread.sleep(1000);
		driver.findElement(By.name("done")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("send_RFE")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		rfeRefNumber = driver.findElement(By.name("rfeReference")).getText();
		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwd" })
	public void testSupplierLogin(String supplierUserName, String BuyerPassword)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(supplierUserName, BuyerPassword);
		// spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSupplierLogin");
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier create estimate - step 6")
	public void testSupplierEstCreate() throws InterruptedException {
		String estRefNo;
		projectName =  "Selenium990536";
		WebElement rfeLnk = driver.findElement(By.linkText(projectName));
		Actions builder = new Actions(driver);
		builder.moveToElement(rfeLnk).perform();
		Thread.sleep(1500);
		builder.moveToElement(rfeLnk)
		.moveToElement(
				driver.findElement(By.linkText("Create Estimate"))).click()
		.build().perform();

		Thread.sleep(2000);
		driver.findElement(By.name("createEstimate")).click();
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input"))
				.sendKeys("1000");
		Thread.sleep(1000);
		driver.findElement(By.name("est_item_-1.comments")).click();
		driver.findElement(By.name("est_item_-2.isEstimated")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("SEND_ESTIMATE")).click();
		Thread.sleep(6000);
		estRefNo = driver.findElement(By.className("alt1")).findElement(By.tagName("a")).getText();
		estRefNumber = String.valueOf(estRefNo).substring(9, 16);

		log.info("--------------------------------------------");
	}

	@Test
	public void logout() throws InterruptedException {
		testLogout();
	}

	@Test(description = "Buyer login")
	@Parameters({ "BuyerLoginN", "BuyerPassword" })
	public void testBuyerLoginagain(String BuyerLoginN, String BuyerPassword)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, BuyerPassword);
	}

	@Test(description = "Outsourcer create quote - step 7")
	public void testQuoteCreate() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickEstfromRecent();
		Thread.sleep(2000);
		List<WebElement> Forms = driver.findElements(By.name("FORM0"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", Forms.get(3));
		Thread.sleep(1000);
		List<WebElement> Inputs = Forms.get(3)
				.findElements(By.tagName("input"));
		Inputs.get(4).click();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("send_quote")).click();
		Thread.sleep(1000);
		//driver.findElement(By.id("confirm_order_creation")).click();
		Thread.sleep(6000);
		homePage.clickMydesklnk();
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create order - step 8")
	public void testOrderCreate() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickEstfromRecent();
		Thread.sleep(2000);
		List<WebElement> Forms = driver.findElements(By.name("FORM0"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", Forms.get(3));
		Thread.sleep(1000);
		List<WebElement> Inputs = Forms.get(3)
				.findElements(By.tagName("input"));
		Inputs.get(4).click();
		Thread.sleep(1000);
		driver.findElement(By.name("award_the_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("proceed_to_confirmation")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_order_creation")).click();
		Thread.sleep(6000);
		homePage.clickMydesklnk();
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwd" })
	public void testSupplierLoginAgain(String supplierUserName, String passwd)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, passwd);
		Thread.sleep(2000);
	}

	public static void mouseMove(WebElement e) throws Exception {
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(e).perform();
		Thread.sleep(1500);

	}

	@Test(description = "Supplier accepts order - step 9")
	public void testSupplierOrderAcceptance() throws Exception {
		WebElement orderLnk = driver.findElement(By
				.className("glb-wdgt-orders-project-orders"));
		Actions builder = new Actions(driver);
		builder.moveToElement(orderLnk).perform();
		Thread.sleep(1500);
		builder.moveToElement(orderLnk)
		.moveToElement(
				driver.findElement(By.className("glb-wdgt-actions"))
						.findElement(By.className("ng-scope"))).click()
		.build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("accept_order")).click();
		Thread.sleep(1000);
		log.info("Start: testSPLogout");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(2000);

		log.info("--------------------------------------------");
	}


	@Test(description = "Buyer create Message - step 11-1")
	public void testSelectProjFromRecent() throws Exception {
		List<WebElement> topPanels = driver.findElements(By.className("glb-ent-action-top"));
		List<WebElement> topButtons = topPanels.get(0).findElements(By.tagName("div"));
		topButtons.get(2).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rpmenu"))
				.findElement(By.tagName("a")).click();
		Thread.sleep(2000);

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
  	   Thread.sleep(3500);	
  	   commonUtils.verifyUploadedImg(fileForProject11);
  	   driver.switchTo().defaultContent();
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
	 	
		commonUtils.testUpdateSharePre();
	  	driver.switchTo().defaultContent();
		Thread.sleep(3000);
		log.info("End: testUpdateSharingPre");
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
		Thread.sleep(1000);
		icons.get(2).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
	  	driver.switchTo().defaultContent();
		log.info("End: testDownloadFile");
		log.info("--------------------------------------------");
	}

	@Test(description = "supplier Login to Gmail")
	@Parameters({ "gmailLoginPW" })
	public void testLoginToGmail1stTime(String gmailLoginPW)
			throws InterruptedException {
		log.info("Start: testLoginToGmail1stTime");
		Thread.sleep(1000);
		driver.get("http://www.gmail.com");
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testGmailLoginPreCon();
		GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
		Thread.sleep(10000);
		gmailLoginPage.enterEmail("seleniumtest12345@noosh.com");
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(1000);
		gmailLoginPage.enterPassword(gmailLoginPW);
		driver.findElement(By.id("passwordNext")).click();
		Thread.sleep(1000);
		//gmailLoginPage.check();
		//gmailLoginPage.signIn();
		Thread.sleep(3000);
		log.info("End: testLoginToGmail1stTime");
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
		driver.findElement(By.id("create_message")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("subject")).sendKeys("test Subject");
		driver.findElement(By.name("content")).sendKeys("test content");
		Thread.sleep(1000);
		List<WebElement> checkbox = driver.findElement(By.className("postitem")).findElements(By.tagName("input"));
		checkbox.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Create Message - step 12")
	public void testCreateSupplierMSG() throws InterruptedException {
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickMessageLink();
		Thread.sleep(1000);
		driver.findElement(By.id("create_message")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("subject")).sendKeys("test Subject");
		driver.findElement(By.name("content")).sendKeys("test content");
		Thread.sleep(1000);
		List<WebElement> checkbox = driver.findElement(By.className("postitem")).findElements(By.tagName("input"));
		checkbox.get(6).click();
		checkbox.get(9).click();
		Thread.sleep(1000);
		driver.findElement(By.id("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		log.info("--------------------------------------------");
	}
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "BuyerPassword" })
	public void testMSGSupplierLogin(String supplierUserName, String BuyerPassword)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, BuyerPassword);
	}
	
	
	@Test(description = "Supplier login")
	@Parameters({ "member1UserName", "BuyerPassword" })
	public void testMSGMem1Login(String member1UserName, String BuyerPassword)
			throws InterruptedException {
		testSupplierLogin(member1UserName, BuyerPassword);
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "member2UserName", "BuyerPassword" })
	public void testMSGMem2Login(String member2UserName, String BuyerPassword)
			throws InterruptedException {
		testSupplierLogin(member2UserName, BuyerPassword);
	}
	
	
	@Test(description = "Reply Message - step 11-2")
	public void testReplyMSG() throws Exception {
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
	
}
