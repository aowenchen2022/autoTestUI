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
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

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

public class TestSimpleFlow extends BaseSeleniumTest {
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
		log.info("\n    Completed: ***** ngeSmallFeaturesTest *****\n\n");
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

	@Test(description = "Existing  Buyer click global 'create project' - step 2")
	public void testExistingBuyerClickGlbCreateProj()
			throws InterruptedException {
		log.info("Start: testExistingSPClickGlbCreateProj");
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.clickGlbCreateProj();
		Thread.sleep(5000);
		log.info("End: testExistingSPClickGlbCreateProj");
		log.info("--------------------------------------------");
	}

	@Test(description = "Existing Buyer input project name ")
	@Parameters({ "productName1", "projectNameField" })
	public void testInputProjectName(String productName1,
			String projectNameField) throws InterruptedException {
		log.info("Start: testExistingSPInputProjectName");
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
		log.info("End: testExistingSPInputProjectName");
		log.info("--------------------------------------------");
	}

	@Test(description = "Existing Buyer Create Project - step 3")
	@Parameters({ "productName1", "projectNameField" })
	public void testExistingBuyerCreateProjectWizard(String productName1,
			String projectNameField) throws InterruptedException, AWTException {
		log.info("Start: testSPcreateProjectProjectWizard");
		testInputProjectName(productName1, projectNameField);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		Thread.sleep(1000);
		List<WebElement> Buttons = driver.findElements(By.id("create_project"));
		Buttons.get(1).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(10000);
		log.info("End: testExistingSPcreateProjectWizard");
		log.info("--------------------------------------------");
	}

	@Test(description = "Existing Buyer Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testExistingBuyerCreateSpecs(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickSpecsLink();
		Thread.sleep(1000);
		projectMenu.clickCreateSpec();
		Thread.sleep(1000);
		driver.findElement(By.id("typeId_5005768")).click();
		driver.findElement(By.id("continue")).click();
		Thread.sleep(5000);
		// driver.switchTo().frame("myProjectProductView-1");
		driver.switchTo().frame("mppv");
		List<WebElement> Inputs = driver.findElements(By.tagName("input"));
		Thread.sleep(5000);
		// driver.findElement(By.tagName("input"));
		// Inputs.get(7).sendKeys("100");
		driver.findElement(By.linkText("Save")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		log.info("End: testExistingSPcreateProjectWizard");
		log.info("--------------------------------------------");
	}

	@Test(description = "Existing Buyer Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testExistingBuyerCreateRFE(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateRFE");
		specName = unicID;
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickEstimatesLink();
		Thread.sleep(1000);
		projectMenu.clickCreateRFELink();
		Thread.sleep(1000);
		driver.findElement(By.id("rfe_0.title")).sendKeys("RFEtitle");
		List<WebElement> Inputs = driver.findElements(By.tagName("input"));
		Thread.sleep(5000);
		Inputs.get(14).sendKeys("100");
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
		suppliers.get(2).click();
		Thread.sleep(1000);
		driver.findElement(By.name("done")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.id("send_RFE")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);

		log.info("End: testExistingSPcreateRFE");
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwd" })
	public void testSupplierLogin(String supplierUserName, String passwd)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(supplierUserName, passwd);
		// spSiteLoginPage = driver.getCurrentUrl();
		log.info("End: testSupplierLogin");
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier create estimate - step 6")
	public void testSupplierEstCreate() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickRFEfromRecent();
		driver.findElement(By.name("createEstimate")).click();
		Thread.sleep(2000);
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input"))
				.sendKeys("100");
		Thread.sleep(1000);
		driver.findElement(By.name("est_item_-1.comments")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("SEND_ESTIMATE")).click();
		Thread.sleep(6000);
		homePage.clickMydesklnk();
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

	@Test(description = "Buyer create order - step 7")
	public void testBuyerOrderCreate() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickEstfromRecent();
		Thread.sleep(2000);
		List<WebElement> Forms = driver.findElements(By.name("FORM0"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", Forms.get(3));
		Thread.sleep(1000);
		List<WebElement> Inputs = Forms.get(3)
				.findElements(By.tagName("input"));
		Inputs.get(2).click();
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

	@Test(description = "Supplier accepts order - step 8")
	public void testSupplierOrderAcceptance() throws Exception {
		WebElement orderLnk = driver.findElement(By
				.className("glb-wdgt-orders-project-orders"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(orderLnk).perform();
		builder.moveToElement(orderLnk)
		.moveToElement(
				driver.findElement(By.className("glb-wdgt-actions"))
						.findElement(By.className("ng-scope"))).click()
		.build().perform();
		Thread.sleep(8000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier select order")
	public void testConfigAllOnOrderPanel() throws Exception {
		List<WebElement> panels = driver.findElements(By
				.className("fa-cog"));
		Actions builder = new Actions(driver);

		builder.moveToElement(panels.get(4)).click().perform();
		
		builder.moveToElement(
				driver.findElement(By.className("glb-wdgt-set-menu"))
						.findElement(By.tagName("li"))).click().build()
				.perform();
		Thread.sleep(5000);
		driver.findElement(By.className("glb-wdgt-orders-project-orders"))
				.findElement(By.className("ng-binding")).click();
		Thread.sleep(1000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier create change order - step 9")
	public void testSupplierChangeOrderCreate() throws InterruptedException {
		driver.findElement(By.id("create_change_order")).click();
		Thread.sleep(2000);
		WebElement changeCostField = driver.findElement(By
				.name("orderItem1.value"));
		changeCostField.clear();
		changeCostField.sendKeys("100");
		driver.findElement(By.id("order.comments")).sendKeys(
				"Change order reason.");
		;
		Thread.sleep(1000);
		driver.findElement(By.id("preview_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("confirm_change_order_creation")).click();
		Thread.sleep(7000);
		HomePage homePage = new HomePage(driver);
		homePage.clickMydesklnk();
		log.info("--------------------------------------------");
	}

	@Test(description = "Buyer login")
	@Parameters({ "BuyerLoginN", "BuyerPassword" })
	public void testBuyerLoginTwice(String BuyerLoginN, String BuyerPassword)
			throws InterruptedException {
		testBuyerLogin(BuyerLoginN, BuyerPassword);
	}

	@Test(description = "Buyer accepts change order - step 10")
	public void testBuyerChangeOrderAcceptance() throws Exception {
		WebElement orderLnk = driver.findElement(By
				.className("glb-wdgt-orders-project-orders"));
		Actions builder = new Actions(driver);
		builder.moveToElement(orderLnk)
				.moveToElement(
						driver.findElement(By.className("glb-wdgt-actions"))
								.findElement(By.className("ng-scope"))).click()
				.build().perform();
		Thread.sleep(8000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Buyer uploads picture on profile page - step 10-1")
	public void testUploadPictureProfile() throws Exception {

        
		WebElement binding = driver.findElement(By
				.className("glb-nmh-profile-default"));
		List<WebElement> links = driver.findElement(By.className("glb-nmh-settings-drop")).findElements(By.tagName("li"));
		WebElement profileLink = links.get(3);
		Actions builder = new Actions(driver);
		builder.clickAndHold(binding).click().perform();
		Thread.sleep(1000);
		//String script = "document.getElementsByClassName('glb-nmh-settings-drop').style.display = 'block');";
        WebElement element = driver.findElement(By.className("glb-nmh-settings-drop"));
        JavascriptExecutor js = (JavascriptExecutor) driver;	
        js.executeScript("document.getElementsByClassName('glb-nmh-settings-drop').style.display = 'block');");	
		//builder.clickAndHold(binding).moveToElement(profileLink).click().build().perform();
		builder.moveToElement(profileLink).click().build().perform();
		Thread.sleep(10000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Buyer create Message - step 11-1")
	public void testSelectProjFromRecent() throws Exception {
		List<WebElement> topPanels = driver.findElements(By.className("glb-ent-action-top"));
		List<WebElement> topButtons = topPanels.get(0).findElements(By.tagName("div"));
		topButtons.get(2).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rpmenu"))
				.findElement(By.className("ng-scope")).click();
		Thread.sleep(2000);

		log.info("--------------------------------------------");
	}

	@Test(description = "Buyer create Message - step 11")
	public void testBuyerCreateMSG() throws InterruptedException {
		ProjectMenu projectMenu = new ProjectMenu(driver);
		projectMenu.clickMessageLink();
		Thread.sleep(1000);
		driver.findElement(By.id("create_message")).click();
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

	@Test(description = "Supplier reply Message - step 11-2")
	public void testSupplierReplyMSG() throws Exception {
		WebElement MSGlnk = driver.findElement(By
				.className("glb-wdgt-msg-line-content"));
		Actions builder = new Actions(driver);
		Thread.sleep(1000);
		builder.moveToElement(MSGlnk).perform();
		builder.moveToElement(MSGlnk)
				.moveToElement(
						driver.findElement(By.className("message-actions"))
								.findElement(By.className("glb-button")))
				.click().perform();
		Thread.sleep(1000);
		builder.moveToElement(MSGlnk)
				.moveToElement(
						driver.findElement(By.className("message-actions"))
								.findElement(By.className("ng-pristine")))
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
				.className("glb-wdgt-msg-line-content"));
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

}
