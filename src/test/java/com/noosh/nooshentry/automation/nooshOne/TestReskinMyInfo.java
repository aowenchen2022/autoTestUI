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
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import java.awt.Robot ;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestReskinMyInfo extends BaseSeleniumTest {
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
	static String rfqName;

	static String projRefNumber;
	static String rfeRefNumber;
	static String estRefNumber;
	static String orderName;
	static String invoiceName;


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
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public void testProjectOver(String BuyberWorkGroupNmae) throws InterruptedException{
		 driver.findElement(By.className("glb-wdgt-activities-list")).findElement(By.tagName("a")).click();
		 Thread.sleep(1000);
		 List <WebElement> categorySelections = driver.findElements(By.className("common-filter-dropdown"));
		 categorySelections.get(1).click();
		 Thread.sleep(1000);
		 Actions builder = new Actions(driver);
		 builder.moveToElement(categorySelections.get(1)).perform();
		 Thread.sleep(1000);
		 List<WebElement> options = driver.findElements(By.className("dropdown-menu"));
		 builder.moveToElement(options.get(1).findElement(By.tagName("button"))).click().perform();
		 Thread.sleep(1500);
		 //builder.release();
		 driver.findElement(By.className("wdgt-none-edit-container")).click();
		 Thread.sleep(500);
		 driver.findElement(By.className("wdgt-none-edit-container")).findElement(By.tagName("i")).click();
		 
		 List <WebElement> projectStatus = driver.findElements(By.className("btn-group"));
		 //projectStatus.get(2).click();
		 Thread.sleep(1000);
		 //Actions builder1 = new Actions(driver);
		 builder.moveToElement(projectStatus.get(2)).perform();
		 Thread.sleep(1000);
		 List<WebElement> status = driver.findElements(By.className("dropdown-menu"));
		 builder.moveToElement(status.get(2).findElement(By.tagName("button"))).click().perform();
		 Thread.sleep(1500);
		 testLogout();
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public void testProjectOverviewPart2(String BuyberWorkGroupNmae) throws InterruptedException{
		 driver.findElement(By.className("glb-wdgt-activities-list")).findElement(By.tagName("a")).click();
		 Thread.sleep(1000);
		 List <WebElement> active = driver.findElements(By.className("common-filter-dropdown"));
		 active.get(2).click();
		 Thread.sleep(1000);
		 Actions builder = new Actions(driver);
		 builder.moveToElement(active.get(2)).perform();
		 Thread.sleep(1000);
		 List<WebElement> options = driver.findElements(By.className("dropdown-menu"));
		 builder.moveToElement(options.get(3).findElement(By.tagName("button"))).click().perform();
		 Thread.sleep(1500);
		 //builder.release();
		 builder.moveToElement(options.get(3).findElement(By.tagName("button"))).click().perform();
		 Thread.sleep(1500);
			}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public void testAttachProject() throws InterruptedException{
		
		 driver.findElement(By.linkText("Attach...")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("searchString")).sendKeys("Selenium");
		 Thread.sleep(1000);

		 driver.findElement(By.id("update_list_white")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("attachProjectId")).click();
		 Thread.sleep(500);
		 String attachedProjectName = driver.findElement(By.tagName("blockquote")).findElement(By.tagName("a")).getText();
		 driver.findElement(By.id("attach_project")).click();
		 Thread.sleep(1000);
		 Boolean proj = driver.findElement(By.linkText(attachedProjectName)).isDisplayed();
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

	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testSwitchWorkGroups(String BuyberWorkGroupNmae) throws InterruptedException{
		
		selectWorkGroups(BuyberWorkGroupNmae);
		Thread.sleep(2000);
		
	}

	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public  void testCheckMyInfoPage(String BuyberWorkGroupNmae) throws InterruptedException{
		Thread.sleep(1500);
		WebElement myInfoLink = driver.findElement(By.className("glb-nmh-profile"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(myInfoLink).perform();
		Thread.sleep(1500);
		builder.moveToElement(myInfoLink).moveToElement(driver.findElement(By.linkText("My Info"))).click().perform();
		Thread.sleep(6000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-outlinedPrimary"));
		buttons.get(1).click();
		Thread.sleep(2000);
		new Select(driver.findElement(By.tagName("select"))).selectByVisibleText("Set Integrative");;
		Thread.sleep(1000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(2000);

		List <WebElement> buttonsMore = driver.findElements(By.className("MuiButton-outlinedPrimary"));
		buttonsMore.get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.id("cancel")).click();
		Thread.sleep(2000);
		 driver.findElement(By.xpath("myDeskPage.myDeskLink")).click();
		 Thread.sleep(3500);
	 
	    testLogout();
		}

	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testUpdateInfo(String BuyberWorkGroupNmae) throws InterruptedException{
		
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-outlinedPrimary"));
		buttons.get(2).click();
		Thread.sleep(2000);
		driver.findElement(By.id("firstName")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("firstName")).sendKeys("Dgo1g1");
		Thread.sleep(500);
		driver.findElement(By.id("personalCompanyName")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("personalCompanyName")).sendKeys("AppleTalk");
		Thread.sleep(500);
		driver.findElement(By.id("title")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("title")).sendKeys("M");
		Thread.sleep(500);
		driver.findElement(By.id("phoneNumber")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("phoneNumber")).sendKeys("1");
		Thread.sleep(500);
		driver.findElement(By.id("faxNumber")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("faxNumber")).sendKeys("1234");
		Thread.sleep(500);
		driver.findElement(By.id("emailAddress")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("emailAddress")).sendKeys("mike@noosh.com");
		Thread.sleep(500);
		driver.findElement(By.id("pagerEmail")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("pagerEmail")).sendKeys("nooshqa@noosh.com");
		Thread.sleep(500);
		driver.findElement(By.id("line1")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("line1")).sendKeys("638 Ellis Street");
		Thread.sleep(500);
		driver.findElement(By.id("city")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("city")).sendKeys("Mountain View");
		Thread.sleep(500);
		driver.findElement(By.id("state")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("state")).sendKeys("ca");
		Thread.sleep(500);
		driver.findElement(By.id("postal")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("postal")).sendKeys("94043");
		Thread.sleep(500);
		driver.findElement(By.id("phoneNumber")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("phoneNumber")).sendKeys("1");
		Thread.sleep(500);
		new Select(driver.findElement(By.id("countryId"))).selectByValue("1000000");
		Thread.sleep(500);
		new Select(driver.findElement(By.id("localeId"))).selectByValue("1000000");
		Thread.sleep(500);
		new Select(driver.findElement(By.id("timeZoneId"))).selectByValue("1000004");
		Thread.sleep(500);
		driver.findElement(By.id("isPublic")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("save")).click();
		Thread.sleep(4000);
		}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public void testInLineEditWorkgroup(String BuyberWorkGroupNmae) throws InterruptedException{
		
		
		Actions builder = new Actions(driver);
		Thread.sleep(4000);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.defaultWorkgroupLink2"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(0);
		new Select(driver.findElement(By.tagName("select"))).selectByValue("5016917");
		Thread.sleep(1500);
		clickPencilIcon(0);
		driver.findElement(By.xpath("myDeskPage.myDeskLink")).click();
		Thread.sleep(3500);
	 
	    testLogout();
		}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineEditWorkgroupAgain(String BuyberWorkGroupNmae) throws InterruptedException{
		
		WebElement myInfoLink = driver.findElement(By.className("glb-nmh-profile"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(myInfoLink).perform();
		Thread.sleep(1500);
		builder.moveToElement(myInfoLink).moveToElement(driver.findElement(By.linkText("My Info"))).click().perform();
		Thread.sleep(4000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-outlinedPrimary"));
		buttons.get(1).click();
		new Select(driver.findElement(By.tagName("select"))).selectByVisibleText("AppleTalk");
		Thread.sleep(1500);
		driver.findElement(By.name("save")).click();
		Thread.sleep(4000);

		}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineEditName(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.nameLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(1);
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);
    	for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	    List <WebElement> pencilIcons = driver.findElements(By.className("MuiSvgIcon-root"));
	    builder.moveToElement(pencilIcons.get(1)).click().perform();
	    Thread.sleep(3000);

		}
		
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineCompanyName(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.companyNameLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(2);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("AppleTalk");;
		Thread.sleep(1000);	 
		clickPencilIcon(2);
		}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineTitle(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.titleLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(3);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("testTitle");;
		Thread.sleep(1000);	 
		clickPencilIcon(3);
	}
		

	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLinePhone(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.phoneLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(4);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("23233333");;
		Thread.sleep(1000);	 
		clickPencilIcon(4);
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineFax(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.faxLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(5);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("55667788");;
		Thread.sleep(1000);	 
		clickPencilIcon(5);
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineEmail(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.emailLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(6);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("nobody@noosh.com");;
		Thread.sleep(1000);	 
		clickPencilIcon(6);
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLinePaperEmail(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.paperEmailLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(7);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("test@noosh.com");;
		Thread.sleep(1000);	 
		clickPencilIcon(7);
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineAddress(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.addressLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(8);
		
	  	List <WebElement> inputs = driver.findElements(By.tagName("input"));
	  	inputs.get(2).clear();
		Thread.sleep(1000);	  
		inputs.get(2).sendKeys("638 Ellis Street");;
		Thread.sleep(1000);	 
		clickPencilIcon(8);
		Thread.sleep(1000);
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineEditTimeZone(String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.localeLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(10);
		new Select(driver.findElement(By.tagName("select"))).selectByValue("1000009");
		Thread.sleep(1500);
		clickPencilIcon(10);
		Thread.sleep(1000);
		}
	
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testInLineEditPublicly (String BuyberWorkGroupNmae) throws InterruptedException{
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("myInfoPage.PubliclyLink"))).perform();
		Thread.sleep(1000);
		clickPencilIcon(11);
		Thread.sleep(1500);
		clickPencilIcon(11);
		}
	
	
	
    public static void clickPencilIcon(int iconNum) throws InterruptedException{
    	for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
    	List <WebElement> pencilIcons = driver.findElements(By.className("MuiSvgIcon-root"));
    	pencilIcons.get(iconNum).click();
    	Thread.sleep(2000);
    	}
	
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testEventListPage(String BuyberWorkGroupNmae) throws InterruptedException{
		
		WebElement myDeskLink = driver.findElement(By.linkText("My Desk"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).perform();
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).moveToElement(driver.findElement(By.linkText("Events"))).click().perform();
		Thread.sleep(5500);
		driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).click();
		Thread.sleep(5000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.findElement(By.xpath("searchManagerPage.pageNum1")).click();
	
		Thread.sleep(3000);

		}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testTaskListPage(String BuyberWorkGroupNmae) throws InterruptedException{
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement myDeskLink = driver.findElement(By.className("MuiToolbar-regular")).findElement(By.tagName("a"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).perform();
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).moveToElement(driver.findElement(By.linkText("Tasks"))).click().perform();
		Thread.sleep(5500);
		List<WebElement> inputs = driver.findElements(By.className("MuiFormControl-fullWidth"));
		inputs.get(1).click();
		Thread.sleep(500);
		inputs.get(1).findElement(By.tagName("input")).sendKeys("test");;
		Thread.sleep(500);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(0).click();
		Thread.sleep(4000);
		
		List<WebElement> taskNums = driver.findElement(By.className("MuiTableBody-root")).findElements(By.tagName("a"));
		String taskNum = taskNums.get(0).getText();
		AssertJUnit.assertEquals(taskNum, "Review Order 5050824-1");
	
	}
	
	@Test(description = "switch workgroup ")
	@Parameters({ "BuyberWorkGroupNmae" })
    public static void testMessageListPage(String BuyberWorkGroupNmae) throws InterruptedException{
		
	
		WebElement myDeskLink = driver.findElement(By.className("MuiToolbar-regular")).findElement(By.tagName("a"));
		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).perform();
		Thread.sleep(1500);
		builder.moveToElement(myDeskLink).moveToElement(driver.findElement(By.xpath("myDeskPage.messageLink"))).click().perform();
		Thread.sleep(5500);
		/*
		List<WebElement> checkbox = driver.findElements(By.className("MuiIconButton-label"));
	
		boolean a = !driver.findElement(By.className("Mui-checked")).isDisplayed();
		System.out.print(a);

        if (a == true) {
        	checkbox.get(2).findElement(By.tagName("input")).click();
        }
    	for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}		*/
    	JavascriptExecutor executor = (JavascriptExecutor) driver;
 		executor.executeScript("document.getElementsByTagName('input')[3].value='8/11/20'");
 		//executor.executeScript("document.getElementsByTagName('input')[3].click()");
		//driver.findElement(By.className("MuiOutlinedInput-adornedEnd")).click();
		
		//driver.findElement(By.xpath("myDeskPage.dateInput")).click();
		Thread.sleep(4000);
		//driver.findElement(By.className("MuiOutlinedInput-inputAdornedEnd")).clear();
		Thread.sleep(500);
		//driver.findElement(By.className("MuiOutlinedInput-inputAdornedEnd")).sendKeys("8/22/20");
		Thread.sleep(500);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));
		svg.get(1).click();
		Thread.sleep(4000);
		
		List<WebElement> messageNames = driver.findElement(By.className("MuiTableBody-root")).findElements(By.tagName("a"));
		String messageName = messageNames.get(0).getText();
		AssertJUnit.assertEquals(messageName, "Test message 1");
	
		List<WebElement> checkbox2 = driver.findElements(By.className("MuiIconButton-label"));
		checkbox2.get(3).findElement(By.tagName("input")).click();
		List<WebElement> buttons = driver.findElements(By.className("MuiTouchRipple-root"));
		buttons.get(6).click();
		Thread.sleep(1500);
	}
	
	
	@Test(description = "Outsourcer login - step 1 ")
	@Parameters({ "OutsourcerLoginN", "AdminPassword" })
	public void testOutsourcerLogin(String OutsourcerLoginN, String AdminPassword)
			throws InterruptedException {
		log.info("Start: testOutsourcerLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(OutsourcerLoginN, AdminPassword);
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
		driver.findElement(By.linkText("Create Project")).click();
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
		Thread.sleep(3000);

		testInputProjectName(productName1, projectNameField);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.testChooseCalendar();
		Thread.sleep(1000);
		//new Select(driver.findElement(By.name("@Division_str"))).selectByIndex(1);
		WebElement clientInput = driver.findElement(By.className("contactSelectorContainer")).findElement(By.tagName("input"));
		testSelectContact("dgb1g1 mgr1", clientInput);
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(1000);
		List<WebElement> Buttons = driver.findElements(By.id("create_project"));
		Buttons.get(1).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);

		log.info("End: testCreateProjectWizard");
		log.info("--------------------------------------------");
	}

	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchFindProject(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");

		List<WebElement> search = driver.findElements(By.className("glb-search"));
		search.get(1).click();
		Thread.sleep(500);
		search.get(1).findElement(By.tagName("input")).sendKeys("superbowl");
		Thread.sleep(1000);
		driver.findElement(By.className("dropdown-menu")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		
		
	
		
		
		log.info("End: testCreateSpec");
		
		log.info("--------------------------------------------");
	}

	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testTeamLink(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		driver.findElement(By.linkText("Teams")).click();
		Thread.sleep(6000);

	
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testLinkOfNameRole(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
        driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.findElement(By.className("MuiTableBody-root")).findElement(By.linkText("Project Owner")).click();
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.className("MuiIconButton-edgeStart"))).click().perform();
		//driver.findElement(By.className("MuiDialog-root")).sendKeys(Keys.ESCAPE);	
		Thread.sleep(3000);

	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCogfMenu(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		List<WebElement> confMenus = driver.findElement(By.className("MuiTableBody-root")).findElements(By.tagName("svg"));

		Actions builder = new Actions(driver);
		builder.moveToElement(confMenus.get(1)).perform();
		Thread.sleep(2000);
		List<WebElement> papers = driver.findElements(By.className("MuiPaper-elevation1"));

		papers.get(1).isDisplayed();

	}
	
	
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCheckBox(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
		
		boolean result = false;
        try {
        	result = driver.findElement(By.className("Mui-checked")).isDisplayed();
        } catch (Exception e) {
            //e.printStackTrace();
        }
     
        if (result == false) {
        	driver.findElement(By.id("resultTable")).findElement(By.tagName("input")).click();
            Thread.sleep(1000);
        }
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.className("MuiTableRow-head")).findElement(By.tagName("button"))).perform();
		Thread.sleep(2000);
		List<WebElement> confMenus = driver.findElements(By.className("MuiPaper-rounded"));

		confMenus.get(1).findElement(By.tagName("li")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("cancel")).click();
		Thread.sleep(5000);

	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testPagination(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
		driver.findElement(By.xpath("searchManagerPage.pageNum1")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("MuiOutlinedInput-marginDense")).click();
		
		//driver.findElement(By.xpath("searchManagerPage.pageNum1")).click();
	

	}
	
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchProjectOwner(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
        JavascriptExecutor executor = (JavascriptExecutor) driver;

    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
		//executor.executeScript("arguments[0].click();", divs1.get(3));
    	divs1.get(6).click();
		Thread.sleep(2000);
    	driver.findElement(By.id("react-select-single")).sendKeys("Sunito3");
		Thread.sleep(3000);

	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchProjectStatus(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
        JavascriptExecutor executor = (JavascriptExecutor) driver;

    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
		//executor.executeScript("arguments[0].click();", divs1.get(3));
    	divs1.get(4).click();
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.className("MuiPaper-elevation8")).findElement(By.className("MuiList-root"));
		String status = checkbox.getAttribute("tabindex").toString();
		System.out.print("value:" + status);
		String flag = "0";
		boolean a = (status.equals(flag));
		System.out.print(a);

        if (a == true) {
            executor.executeScript("arguments[0].click();", checkbox.findElement(By.tagName("input")));
            Thread.sleep(1000);
        }
    	
		driver.findElement(By.className("MuiPaper-elevation8")).sendKeys(Keys.ESCAPE);
	
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(8).click();
		Thread.sleep(2000);

		List<WebElement> projectNums = driver.findElement(By.className("MuiTableBody-root")).findElements(By.tagName("span"));
		String projectNum = projectNums.get(3).getText();
		AssertJUnit.assertEquals(projectNum, " 5115970 ");
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchSpecType(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
        JavascriptExecutor executor = (JavascriptExecutor) driver;

    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
		//executor.executeScript("arguments[0].click();", divs1.get(3));
    	divs1.get(1).click();
		Thread.sleep(2000);
		List<WebElement> options = driver.findElement(By.className("MuiPaper-elevation8")).findElements(By.tagName("li"));
	
            executor.executeScript("arguments[0].click();", options.get(0));
            Thread.sleep(1000);
        
    	
        	List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

    		svg.get(4).click();
    		Thread.sleep(5000);
    		String projectNum = driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).getText();
    		Assert.assertEquals(projectNum, "Spec01 (Envelope)");
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchTransactionType(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
        JavascriptExecutor executor = (JavascriptExecutor) driver;

    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
		//executor.executeScript("arguments[0].click();", divs1.get(3));
    	divs1.get(5).click();
		Thread.sleep(2000);
		List<WebElement> options = driver.findElement(By.className("MuiPaper-elevation8")).findElements(By.tagName("li"));
	
        executor.executeScript("arguments[0].click();", options.get(0));
          
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(5).click();
		Thread.sleep(2000);

		List<WebElement> actualOrderNames = driver.findElement(By.className("MuiTableBody-root")).findElements(By.tagName("a"));
		String actualOrderName = actualOrderNames.get(0).getText();
		AssertJUnit.assertEquals(actualOrderName, orderName);
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchCreateDate(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	
		List<WebElement> divs = driver.findElements(By.className("MuiOutlinedInput-root"));
		executor.executeScript("arguments[0].click();", divs.get(2));
		Thread.sleep(2000);
		List<WebElement> radios = driver.findElements(By.name("radioGroup"));
		executor.executeScript("arguments[0].click();", radios.get(1));
		Thread.sleep(2000);
		driver.findElement(By.className("MuiFormGroup-root")).findElement(By.className("MuiSelect-root")).click();
		Thread.sleep(1000);
		List<WebElement> options = driver.findElement(By.id("menu-")).findElements(By.className("MuiTouchRipple-root"));
		executor.executeScript("arguments[0].click();", options.get(4));
	
		Thread.sleep(1000);
		driver.findElement(By.className("MuiPaper-elevation8")).sendKeys(Keys.ESCAPE);
	
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(4).click();
		Thread.sleep(2000);

		String projectNum = driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).getText();
		Assert.assertEquals(projectNum, "Spec01 (Envelope)");
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchLastUpdateDate(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	
		List<WebElement> divs = driver.findElements(By.className("MuiOutlinedInput-root"));
		executor.executeScript("arguments[0].click();", divs.get(3));
		Thread.sleep(2000);
		List<WebElement> radios = driver.findElements(By.name("radioGroup"));
		executor.executeScript("arguments[0].click();", radios.get(1));
		Thread.sleep(2000);
		driver.findElement(By.className("MuiFormGroup-root")).findElement(By.className("MuiSelect-root")).click();
		Thread.sleep(1000);
		List<WebElement> options = driver.findElement(By.id("menu-")).findElements(By.className("MuiTouchRipple-root"));
		executor.executeScript("arguments[0].click();", options.get(4));
	
		Thread.sleep(1000);
		driver.findElement(By.className("MuiPaper-elevation8")).sendKeys(Keys.ESCAPE);
	
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(4).click();
		Thread.sleep(4000);
		String projectNum = driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).getText();
		Assert.assertEquals(projectNum, "Spec01 (Envelope)");
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchCreateBy(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		//create date:
        JavascriptExecutor executor = (JavascriptExecutor) driver;

    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
	   	divs1.get(4).click();
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.className("MuiPaper-elevation8")).findElement(By.className("MuiList-root"));
		String status = checkbox.getAttribute("tabindex").toString();
		System.out.print("value:" + status);
		String flag = "0";
		boolean a = (status.equals(flag));
		System.out.print(a);

        if (a == true) {
            executor.executeScript("arguments[0].click();", checkbox.findElement(By.tagName("input")));
            Thread.sleep(1000);
        }
    	
		driver.findElement(By.className("MuiPaper-elevation8")).sendKeys(Keys.ESCAPE);
	
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(4).click();
		Thread.sleep(4000);
		String projectNum = driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).getText();
		Assert.assertEquals(projectNum, "Spec01 (Envelope)");
	}

	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testViewColumns(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
		driver.findElement(By.id("selectedAll")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("MuiTableSortLabel-iconDirectionDesc")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("MuiTableSortLabel-iconDirectionAsc")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("specListPage.jobIdLink")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("specListPage.jobIdLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.createdByLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.createdByLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.createdLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.createdLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.lastUpdatedLink")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("specListPage.lastUpdatedLink")).click();
		Thread.sleep(1000);
		
		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Search Find Box - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testInlineEdits(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		List<WebElement> svgs = driver.findElement(By.className("MuiTableBody-root")).findElements(By.className("MuiSvgIcon-root"));

		Actions builder = new Actions(driver);
		builder.moveToElement(svgs.get(0)).perform();
		Thread.sleep(1000);
		List<WebElement> options = driver.findElements(By.className("MuiMenuItem-gutters"));
		options.get(0).click();
		Thread.sleep(2000);

		driver.findElement(By.id("cancel")).click();
		Thread.sleep(4000);

		Thread.sleep(1000);
	//    JavascriptExecutor executor = (JavascriptExecutor) driver;
		List<WebElement> svgs2 = driver.findElement(By.className("MuiTableBody-root")).findElements(By.className("MuiSvgIcon-root"));

		Thread.sleep(2000);
		builder.moveToElement(svgs2.get(0)).perform();
		Thread.sleep(1000);
		
		List<WebElement> options2 = driver.findElements(By.className("MuiMenuItem-gutters"));
		options2.get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.className("MuiDialogActions-spacing")).findElement(By.tagName("button")).click();
	
		Thread.sleep(2000);
		}
	
	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateSpecs1(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;
		driver.findElement(By.linkText("Specs")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Create...")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("specPage.envelopeRadio")).click();
		Thread.sleep(1000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-text"));
		buttons.get(1).click();
		Thread.sleep(2000);

		driver.findElement(By.id("name")).sendKeys("Spec01");
	
		Thread.sleep(1000);
		driver.findElement(By.id("quantity1")).sendKeys("100");
		Thread.sleep(1000);
		driver.findElement(By.id("save_spec")).click();
		Thread.sleep(2000);
		
		Thread.sleep(4000);
		log.info("End: testCreateSpec");
		
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
		driver.findElement(By.linkText("Create...")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("specPage.directMailRadio")).click();
		Thread.sleep(1000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-text"));
		buttons.get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.id("name")).sendKeys("Spec02");
		
		Thread.sleep(1000);
		driver.findElement(By.id("save_spec")).click();
		Thread.sleep(2000);

	}
	
	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testSearchSpec(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
		driver.findElement(By.linkText("List")).click();
		Thread.sleep(4000);
	
    	List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
    	divs1.get(0).click();
		Thread.sleep(2000);
		divs1.get(0).findElement(By.tagName("input")).sendKeys("Spec01");
		Thread.sleep(1000);
		List<WebElement> svg = driver.findElements(By.className("MuiSvgIcon-root"));

		svg.get(4).click();
		Thread.sleep(5000);

		String projectNum = driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).getText();
		Assert.assertEquals(projectNum, "Spec01 (Envelope)");

		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateRFE(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
		specName = unicID;
		rfeName = projectName;
	
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.linkText("Estimates"))).perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElement(By.linkText("Estimates"))).moveToElement(driver.findElement(By.className("glb-flyout")).findElement(By.linkText("Create RFE..."))).click().perform();
		Thread.sleep(4000);
		driver.findElement(By.className("MuiOutlinedInput-inputMarginDense")).click();
		Thread.sleep(500);

		driver.findElement(By.className("MuiOutlinedInput-inputMarginDense")).sendKeys("Spec01");
		Thread.sleep(1000);
		driver.findElement(By.xpath("selectSpecPage.searchButton")).click();
		Thread.sleep(2000);

	 	driver.findElement(By.className("MuiSelect-selectMenu")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("MuiList-padding")).findElement(By.tagName("li")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("selectSpecPage.searchButton")).click();
		
   
	    JavascriptExecutor executor = (JavascriptExecutor) driver;

	    List<WebElement> divs1 = driver.findElements(By.className("MuiOutlinedInput-root"));
	    divs1.get(3).click();
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.className("MuiPaper-elevation8")).findElement(By.className("MuiList-root"));
		String status = checkbox.getAttribute("tabindex").toString();
		System.out.print("value:" + status);
		String flag = "0";
		boolean a = (status.equals(flag));
		System.out.print(a);

	        if (a == true) {
	            executor.executeScript("arguments[0].click();", checkbox.findElement(By.tagName("input")));
	            Thread.sleep(1000);
	        }
	   	driver.findElement(By.className("MuiPaper-elevation8")).sendKeys(Keys.ESCAPE);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("selectSpecPage.searchButton")).click();
		Thread.sleep(2000);
		
		
		List <WebElement> checkboxes = driver.findElements(By.className("MuiIconButton-label"));
		checkboxes.get(1).click();
		Thread.sleep(1000);

		List <WebElement> buttons = driver.findElements(By.className("MuiButton-label"));
		buttons.get(1).click();
		Thread.sleep(2000);
		List <WebElement> buttons2 = driver.findElements(By.className("MuiButton-sizeLarge"));
		buttons2.get(3).click();
		
		
		Thread.sleep(2000);
		//rfeRefNumber = driver.findElement(By.name("rfeReference")).getText();
		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}

	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testViewRFEDetail(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
	
		List<WebElement> buttons = driver.findElements(By.className("MuiTable-root"));
		buttons.get(0).findElement(By.tagName("a")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("fa-calendar-alt")).click();
		Thread.sleep(500); 
		driver.findElement(By.xpath("rfePage.notifySuppliersButton")).click();
		Thread.sleep(3000);
		/*driver.findElement(By.className("fa-times")).click();
		Thread.sleep(500); 
		driver.findElement(By.className("MuiOutlinedInput-multiline")).click();
		driver.findElement(By.className("MuiOutlinedInput-multiline")).sendKeys("Test reason");
		Thread.sleep(500); 

		WebElement dismiss = driver.findElement(By.className("MuiDialogActions-root")).findElement(By.tagName("span"));
		Actions builder = new Actions(driver);
	
		builder.moveToElement(dismiss).click().perform();
		Thread.sleep(5500);

	

		driver.findElement(By.xpath("rfePage.sendToAdditionalSuppliersButton")).click();
		Thread.sleep(500); 
		driver.findElement(By.xpath("rfePage.contactsLink")).click();
		
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		List<WebElement> checkboxes = driver.findElements(By.className("MuiCheckbox-root"));
		checkboxes.get(4).findElement(By.tagName("input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("rfePage.addSelectedButton")).click();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		driver.findElement(By.className("fa-check")).click();
		
	*/
		Thread.sleep(2000); 

		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create RFE - step 5")
	@Parameters({ "productName1", "projectNameField" })
	public void testSupplierViewRFEDetail(String specName, String Quantity)
			throws Exception, AWTException {
		log.info("Start: testSPcreateRFE");
		Thread.sleep(2000);

		//buttons.get(0).findElement(By.tagName("a")).click();
		driver.findElement(By.className("rfe-list-line")).findElement(By.tagName("a")).click();
		Thread.sleep(4000);
		List<WebElement> buttons = driver.findElements(By.className("MuiButton-label"));
		buttons.get(1).click();
		Thread.sleep(1000); 

		log.info("End: testCreateRFE");
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwordSP" })
	public void testSupplierLogin(String supplierUserName, String passwordSP)
			throws InterruptedException {
		log.info("Start: testSPLogin");
		Thread.sleep(1500);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(supplierUserName, passwordSP);
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
			
		List<WebElement> nobrs = driver.findElements(By.tagName("nobr"));
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input")).sendKeys("1000");
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input")).sendKeys("{Tab}");
		Thread.sleep(1000);
	

		Thread.sleep(1000);
		driver.findElement(By.id("SEND_ESTIMATE")).click();
		Thread.sleep(6000);


		log.info("--------------------------------------------");
	}

	@Test(description = "Supplier create estimate - step 6")
	public void testSupplier2EstCreate() throws InterruptedException, AWTException {
		String estRefNo;
		//projectName =  "Selenium198183";

		driver.findElement(By.className("rfe-list-line")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		//driver.findElement(By.linkText("Check All")).click();
		driver.findElement(By.id("selectedRfeItemId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("acceptRfeItem")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rejectComment")).clear();
		driver.findElement(By.id("rejectComment")).sendKeys("reject reason..");
		Thread.sleep(1000);
		driver.findElement(By.id("submitDeclineOrAcceptItemsButton")).click();
		Thread.sleep(1000);
		List<WebElement> nobrs = driver.findElements(By.tagName("nobr"));
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input"))
				.sendKeys("400");
		driver.findElement(By.tagName("nobr")).findElement(By.tagName("input")).sendKeys("{Tab}");
	
		Thread.sleep(1000);
		driver.findElement(By.id("SEND_ESTIMATE")).click();
		Thread.sleep(6000);
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
	@Parameters({ "OutsourcerLoginN", "AdminPassword" })
	public void testOutsourcerLogin2nd(String OutsourcerLoginN, String AdminPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, AdminPassword);
	}
	
	@Test(description = "Outsourcer login3")
	@Parameters({ "OutsourcerLoginN", "AdminPassword" })
	public void testOutsourcerLogin3rd(String OutsourcerLoginN, String AdminPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, AdminPassword);
	}
	
	@Test(description = "Outsourcer login4")
	@Parameters({ "OutsourcerLoginN", "AdminPassword" })
	public void testOutsourcerLogin4th (String OutsourcerLoginN, String AdminPassword) throws InterruptedException {
		testOutsourcerLogin(OutsourcerLoginN, AdminPassword);
	}
	
	@Test(description = "Outsourcer create quote - step 7")
	public void testCheckEst() throws Exception {
		//HomePage homePage = new HomePage(driver);
		//homePage.clickEstfromRecent();
		//driver.findElement(By.className("rfe-list-project ")).findElement(By.tagName("a")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("glb-wdgt-estimates-rfe-title")).findElement(By.tagName("a")).click();
		Thread.sleep(4000);
		
		String priceTotal = driver.findElement(By.xpath("estmatePage.totalPrice")).getText();
		//System.out.println(priceSupplier1);
		System.out.println(priceTotal);
		//AssertJUnit.assertEquals(priceSupplier1, "Declined");
		AssertJUnit.assertEquals(priceTotal, "$1,000.000");


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
	public void testCreateOrderFromQuote() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		driver.findElement(By.id("list")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("goto_accept_quote")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("create_order")).click();
		Thread.sleep(12000);

		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create order - step 8")
	public void testCreateBuyOrder() throws Exception {
		HomePage homePage = new HomePage(driver);
		//driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).click();
		//driver.findElement(By.linkText("Home")).click();
		List<WebElement> orderLnks = driver.findElements(By.className("fa-shopping-cart"));

		Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(orderLnks.get(1)).perform();
		Thread.sleep(1500);
		builder.moveToElement(orderLnks.get(1)).moveToElement(driver.findElement(By.className("glb-flyout")).findElement(By.linkText("Create..."))).click().perform();
		Thread.sleep(5500);

	//	CommonUtils commonUtils = new CommonUtils(driver);
	//	commonUtils.testChooseCalendar();
		Thread.sleep(1000); 

		
		WebElement supplierInput = driver.findElement(By.className("contactSelectorContainer")).findElement(By.tagName("input"));
		testSelectContact("DHs1g1 mgr1", supplierInput);
		Thread.sleep(1000);
		
		driver.findElement(By.id("orderItem1.unitPrice")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("orderItem1.unitPrice")).sendKeys("1000");

		Thread.sleep(1000);
		
		
		driver.findElement(By.id("proceed_to_confirmation")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_order_creation")).click();
		Thread.sleep(8000);

		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier accepts order - step 8")
	public void testSupplierOrderAcceptance() throws Exception {
		WebElement orderLnk = driver.findElement(By
				.className("glb-wdgt-orders-project-orders"));
		Actions builder = new Actions(driver);
		builder.moveToElement(orderLnk).perform();
		Thread.sleep(1500);
		builder.moveToElement(orderLnk.findElement(By.tagName("a"))).click()
		.build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("accept_order")).click();
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(2000);


		
		log.info("--------------------------------------------");
	}
	
	
	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateTask1(String specName, String Quantity)
			throws Exception {
		log.info("Start: testSPcreateSpecWizard");
		
		driver.findElement(By.linkText("Tasks")).click();
		Thread.sleep(4000);
		List<WebElement> buttons = driver.findElements(By.className("MuiButton-text"));
		buttons.get(1).click();
		Thread.sleep(5000);
		new Select(driver.findElement(By.id("taskTypeId"))).selectByValue("5016647");
		
		WebElement assignName = driver.findElement(By.className("contactSelectorContainer")).findElement(By.className("div"));
		testSelectContact("dgo1g1 mgr1", assignName);
		Thread.sleep(1000);
		driver.findElement(By.id("create_task")).click();
		Thread.sleep(4000);
	
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testPrint(String specName, String Quantity)
			throws Exception {
		log.info("Start: testSPcreateSpecWizard");
		driver.findElement(By.className("fa-print")).click();
		Thread.sleep(1000);
		driver.switchTo().frame("pdf-viewer");
		driver.findElement(By.id("zoom-toolbar")).sendKeys(Keys.ESCAPE);
	  	driver.switchTo().defaultContent();
		Thread.sleep(1000);
	  	
		
		log.info("--------------------------------------------");
	}
	

	@Test(description = "Filter Criteria - step 2")
	public  void testFilterTasks() throws Exception {
		driver.findElement(By.id("outlined-name")).sendKeys("task");
		Thread.sleep(1000);
		driver.findElement(By.className("MuiSvgIcon-root")).click();
		Thread.sleep(1000);

		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		List<WebElement> divs1 = driver.findElements(By.className("MuiInputAdornment-root"));
		executor.executeScript("arguments[0].click();", divs1.get(0));
		Thread.sleep(2000);
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement button = driver.findElement(By.xpath("searchManagerPage.calendarNum1"));
		  executor.executeScript("arguments[0].click();", button);
		Thread.sleep(2000);
				
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
        
		executor.executeScript("arguments[0].click();", divs1.get(1));
		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement button2 = driver.findElement(By.xpath("searchManagerPage.calendarNum"));
		  executor.executeScript("arguments[0].click();", button2);
		Thread.sleep(2000);
				
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		
		List<WebElement> buttons = driver.findElements(By.className("MuiOutlinedInput-inputMarginDense"));
		buttons.get(8).click();
		Thread.sleep(1000);
		List<WebElement> options = driver.findElement(By.className("MuiList-padding")).findElements(By.tagName("li"));
		options.get(1).click();
		Thread.sleep(1000);

		driver.findElement(By.className("MuiSvgIcon-root")).click();
		Thread.sleep(1000);
		
	
		boolean result = driver.findElement(By.linkText("task1")).isDisplayed();
		AssertJUnit.assertEquals(result, true);

	}
	
	@Test(description = "Filter Criteria - step 2")
	public  void testPagination2() throws Exception {
		driver.findElement(By.xpath("searchManagerPage.previous")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("searchManagerPage.next")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("searchManagerPage.pageNum1")).click();
		Thread.sleep(1000);

		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		List<WebElement> divs1 = driver.findElements(By.className("MuiInputAdornment-root"));
		executor.executeScript("arguments[0].click();", divs1.get(0));
		Thread.sleep(2000);
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement button = driver.findElement(By.xpath("searchManagerPage.calendarNum1"));
		  executor.executeScript("arguments[0].click();", button);
		Thread.sleep(2000);
				
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
        
		executor.executeScript("arguments[0].click();", divs1.get(1));
		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		WebElement button2 = driver.findElement(By.xpath("searchManagerPage.calendarNum"));
		  executor.executeScript("arguments[0].click();", button2);
		Thread.sleep(2000);
				
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		
		List<WebElement> buttons = driver.findElements(By.className("MuiOutlinedInput-inputMarginDense"));
		buttons.get(8).click();
		Thread.sleep(1000);
		List<WebElement> options = driver.findElement(By.className("MuiList-padding")).findElements(By.tagName("li"));
		options.get(1).click();
		Thread.sleep(1000);

		driver.findElement(By.className("MuiSvgIcon-root")).click();
		Thread.sleep(1000);
		
	
		boolean result = driver.findElement(By.linkText("task1")).isDisplayed();
		AssertJUnit.assertEquals(result, true);

	}
	
	@Test(description = "Create order - step 8")
	public void testBuyPaperOrderCreate() throws Exception {
		driver.findElement(By.linkText("Create Paper Order")).click();
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
		tables.get(4).findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("VIEW_ORDER_LIST")).click();
		Thread.sleep(3000);
		List<WebElement> trs = driver.findElement(By.id("orders")).findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(7).findElements(By.tagName("td"));
		String status = tds.get(3).getText();
		AssertJUnit.assertEquals(status, "Accepted");

		//AssertJUnit.assertTrue(driver.findElement(By.className("nplTable")).findElement(By.tagName("a")).isDisplayed());
		
		
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
	@Parameters({ "supplierUserName", "passwordSP" })
	public void testSupplierLoginAgain(String supplierUserName, String passwordSP)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, passwordSP);
		Thread.sleep(2000);
	}

	@Test(description = "Supplier login")
	@Parameters({ "supplier2UserName", "AdminPassword" })
	public void testSupplier2LoginAgain(String supplierUserName, String AdminPassword)
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

	@Test(description = "Filter Criteria - step 2")
	public  void testOutsourcerFilterCriteria() throws Exception {
		ManagerPage managerPage = new ManagerPage(driver);
		managerPage.FilterCriteria("RFQs");
		Thread.sleep(5000);

		//driver.findElement(By.linkText("Projects")).isDisplayed();
	}
	
	@Test(description = "Supplier accepts order - step 9")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testSupplierOrderAcceptance2(String SupplierWorkGroupNmae) throws Exception {
		//driver.findElement(By.className("glb-wdgt-orders-orderedProject")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("wdgt-overview-order-id")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Accept")).click();
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(5000);
	
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier accepts order - step 12")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testSupplierCloseOrderAcceptance(String SupplierWorkGroupNmae) throws Exception {
		driver.findElement(By.className("glb-wdgt-orders-project-orders")).findElement(By.tagName("a")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("orderListPage.acceptCloseButton")).click();
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(6000);
	

		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier accepts order - step 12")
	@Parameters({ "SupplierWorkGroupNmae"})
	public  void testPaperCloseOrderAcceptance(String SupplierWorkGroupNmae) throws Exception {
		driver.findElement(By.linkText("My Desk")).click();
		Thread.sleep(1500);
		List<WebElement>  links = driver.findElements(By.linkText("Review Closing Change Order"));
		links.get(1).click();
		Thread.sleep(1500);
		driver.findElement(By.id("route_change_order")).click();
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

		driver.findElement(By.id("create_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("order.title")).sendKeys("ChangeOrderName");
		driver.findElement(By.id("orderItem1.value")).clear();
		Thread.sleep(1000);
	
		driver.findElement(By.id("preview_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(5000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Outsourcer accepts order - step 9")
	@Parameters({ "BuyberWorkGroupNmae"})
	public void testOutsourcerChangeOrderAcceptance(String BuyberWorkGroupNmae) throws Exception {
		
		List<WebElement> orders = driver.findElements(By.className("glb-wdgt-orders-project-orders"));
		orders.get(0).findElement(By.tagName("a")).click();
		Thread.sleep(3000);
		List<WebElement> buttons = driver.findElements(By.className("MuiButton-label"));
		buttons.get(1).click();
		
		Thread.sleep(2000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);

		Thread.sleep(8000);

		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create close change order - step 14")
	public void testCloseChangeOrderCreate() throws Exception {
		
		List<WebElement> orderLinks = driver.findElements(By.linkText("Orders"));
		orderLinks.get(2).click();
		Thread.sleep(4000);
		Actions builder = new Actions(driver);
		List<WebElement> confMenus = driver.findElements(By.className("MuiSvgIcon-root"));
		builder.moveToElement(confMenus.get(4)).perform();
		Thread.sleep(2000);
		builder.moveToElement(driver.findElement(By.className("MuiListItem-root")).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(4000);
		driver.navigate().back();
		Thread.sleep(5000);
		List<WebElement> confMenus2 = driver.findElements(By.className("MuiSvgIcon-root"));

		builder.moveToElement(confMenus2.get(4)).perform();
		Thread.sleep(1000);

		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(1).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(5000);
		driver.navigate().back();
		Thread.sleep(5000);
		
		
		driver.findElement(By.className("MuiTableBody-root")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("orderListPage.closeButton")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("preview_closing_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(10000);
		
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
		Orderlnks.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("complete")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(3000);
		new Select(driver.findElement(By.id("@orderItem1.VAT_SELECTION_num"))).selectByIndex(1);
		driver.findElement(By.id("invoice-1.ownerReference")).sendKeys("NO123");
		driver.findElement(By.id("@invoice-1.SUBMIT_PDF_INVOICE_bool")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("preview_closing_change_order")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirm_change_order_creation")).click();
		Thread.sleep(10000);

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
		driver.findElement(By.className("glb-wdgt-invoice-entry")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("accept")).click();
		Thread.sleep(1000);
		CommonUtils commonUtils = new CommonUtils(driver);
		commonUtils.dealPotentialAlert(true);
		Thread.sleep(5000);

		log.info("--------------------------------------------");
	}
	

	@Test(description = "Create Spec - step 4")
	@Parameters({ "productName1", "projectNameField" })
	public void testCreateQuote(String specName, String Quantity)
			throws InterruptedException, AWTException {
		log.info("Start: testSPcreateSpecWizard");
		specName = unicID;

		Actions builder = new Actions(driver);		
		builder.moveToElement(driver.findElement(By.className("fa-sticky-note"))).perform();
		Thread.sleep(1500);
		builder.moveToElement(driver.findElement(By.xpath("newHomePage.createButton"))).click().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("quote.quoteItem1.quotedIndexId")).click();

		Thread.sleep(1000);

		driver.findElement(By.id("send_quote")).click();

		Thread.sleep(4000);
		log.info("End: testCreateSpec");
		
		log.info("--------------------------------------------");
	}
	
	
	@Test(description = "Create invoice - step 14")
	public void testSellInvoiceCreate() throws InterruptedException {

		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.className("fa-file-alt"))).perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElement(By.xpath("newHomePage.createButton"))).click().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("orderId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("create")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("invoiceItem-1_1.quantity")).sendKeys("100");
		Thread.sleep(1000);

		driver.findElement(By.id("send")).click();
		Thread.sleep(8000);
		
		log.info("--------------------------------------------");
	}

	@Test(description = "Create invoice - step 14")
	public void testViewBuyerOrder() throws InterruptedException {
		Thread.sleep(3000);

	//	List<WebElement> orderLinks = driver.findElements(By.linkText("Orders"));
	//	orderLinks.get(0).click();
		Thread.sleep(4000);
		List<WebElement> orderButtons = driver.findElements(By.className("MuiButton-label"));
		orderButtons.get(1).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(5000);
		driver.findElement(By.xpath("orderListPage.sellOrderList")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("orderListPage.markupSummary")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("orderListPage.datailedMarkupAnalysis")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("orderListPage.buyOrderList")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("orderListPage.lastChanged")).click();
		Thread.sleep(1000);

	//	classNames.get(2).findElement(By.tagName("a")).click();
	//	Thread.sleep(1000);
	//	driver.navigate().back();
	//	Thread.sleep(5000);
		
		List<WebElement> confMenus = driver.findElements(By.className("MuiSvgIcon-root"));
		Actions builder = new Actions(driver);
		builder.moveToElement(confMenus.get(3)).perform();
		Thread.sleep(2000);
		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(1).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(2).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(3).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(4).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElements(By.className("MuiListItem-root")).get(0).findElement(By.tagName("span"))).click().perform();
		Thread.sleep(1000);

		
	
		

		Thread.sleep(1000);
/*
		driver.findElement(By.className("fa-print")).click();
		Thread.sleep(5000);
		 Robot robot = null ;
	      try {
	          robot = new Robot();
	          } catch (AWTException e) {
	              e.printStackTrace();
	          }
	      robot.keyPress(KeyEvent.VK_ESCAPE) ;
	      robot.keyRelease(KeyEvent.VK_ESCAPE) ; */
		//driver.navigate().back();
		//Alert printDialog = driver.switchTo().alert();

		//printDialog.dismiss();
		Thread.sleep(2000);

		
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
		Thread.sleep(6000);
		/*List <WebElement> buttons = driver.findElements(By.tagName("button"));
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
		*/
		log.info("--------------------------------------------");
	}
	   
	@Test(description = "Invite member and supplier")
	@Parameters({ "productName1", "projectNameField" })
	public void testInviteSupplier(String productName1,
		String projectNameField) throws Exception {
		log.info("Start: Invite member and supplier");
		driver.findElement(By.linkText("Teams")).click();
		Thread.sleep(4000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButton-label"));
		buttons.get(2).click();
		Thread.sleep(2000);
		WebElement clientInput = driver.findElement(By.className("contactSelectorContainer")).findElement(By.tagName("input"));
		testSelectContact("Dgs1g1 mgr1", clientInput);
		Thread.sleep(1000);
		List <WebElement> myButtons = driver.findElements(By.tagName("button"));
		myButtons.get(2).click();

		Thread.sleep(6000);
	
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
		Thread.sleep(4000);
		List <WebElement> buttons = driver.findElements(By.className("MuiButtonBase-root"));
		buttons.get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.id("subject")).sendKeys("test Subject");
		driver.findElement(By.id("content")).sendKeys("test content");
		Thread.sleep(1000);
		driver.findElement(By.name("post_all")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
	
		Thread.sleep(2000);
	
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Create Message - step 12")
	public void testBuyerCreateMSG() throws InterruptedException {
		Thread.sleep(4000);

		List <WebElement> buttons = driver.findElements(By.className("MuiButtonBase-root"));
		buttons.get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.id("subject")).sendKeys("Message 2");
		driver.findElement(By.id("content")).sendKeys("content2");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("priorityStrId"))).selectByValue("1446");;
		driver.findElement(By.name("post_all")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
	
		Thread.sleep(2000);
	
		log.info("--------------------------------------------");
	}

	@Test(description = "Create Message - step 12")
	public void testSupplierCreateMSG() throws InterruptedException {
		Thread.sleep(4000);

		List <WebElement> buttons = driver.findElements(By.className("MuiButtonBase-root"));
		buttons.get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.id("subject")).sendKeys("Message 3");
		driver.findElement(By.id("content")).sendKeys("content3");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("priorityStrId"))).selectByValue("1448");;
		driver.findElement(By.name("post_all")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("post_message")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
	
		Thread.sleep(2000);
	
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Supplier login")
	@Parameters({ "supplierUserName", "passwd" })
	public void testMSGSupplierLogin(String supplierUserName, String passwd)
			throws InterruptedException {
		testSupplierLogin(supplierUserName, passwd);
	}

	@Test(description = "Reply Message - step 11-2")
	public void testBuyerReplyMSG() throws Exception {
		Thread.sleep(4000);
		WebElement MSGlnk = driver.findElement(By
				.className("glb-wdgt-msg-line-content"));
		Actions builder = new Actions(driver);
		Thread.sleep(1000);
		//builder.moveToElement(MSGlnk).perform();
		builder.moveToElement(MSGlnk)
				.perform();
		Thread.sleep(1000);
		builder.moveToElement(MSGlnk)
		.moveToElement(
				driver.findElement(By.className("fa-external-link-alt"))).doubleClick().perform();
		Thread.sleep(1000);

		//builder.moveToElement(MSGlnk).perform();
		driver.findElement(By.id("content")).sendKeys("Content 2");
		Thread.sleep(1000);

		driver.findElement(By.id("post_reply")).click();
		Thread.sleep(3000);
		log.info("--------------------------------------------");
	}

	@Test(description = "Reply Message - step 11-2")
	public void testSupplierReplyMSG() throws Exception {
		Thread.sleep(4000);
		WebElement MSGlnk = driver.findElement(By
				.className("glb-wdgt-msg-line-content"));
		Actions builder = new Actions(driver);
		Thread.sleep(1000);

		builder.moveToElement(MSGlnk)
		.perform();
		Thread.sleep(1000);
		builder.moveToElement(MSGlnk)
		.moveToElement(
				driver.findElement(By.className("fa-external-link-alt"))).click().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("content")).sendKeys("Content 3");
		Thread.sleep(1000);

		driver.findElement(By.id("post_reply")).click();
		Thread.sleep(3000);
		log.info("--------------------------------------------");
	}
	
	@Test(description = "Buyer check Message - step 11 -3")
	public void testBuyerCheckMSG() throws Exception {
		Thread.sleep(5000);

		driver.findElement(By.className("glb-wdgt-msg-line-content")).findElement(By.tagName("a")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("List")).click();
		Thread.sleep(5000);

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
		testSelectContact("dgo1g1 mgr1", userInput);
		
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
    	List <WebElement> icons = driver.findElements(By.className("fa-caret-down"));
    	Actions builder = new Actions(driver);
		Thread.sleep(1500);
		builder.moveToElement(icons.get(1)).perform();
		Thread.sleep(1500);
		builder.moveToElement(icons.get(1)).moveToElement(driver.findElement(By.xpath("myDeskPage.workGroupLink"))).click().perform();
		Thread.sleep(4000);
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
