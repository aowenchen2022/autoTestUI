package com.noosh.nooshentry.automation.base;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptError;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshChromeDriver;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshFirefoxDriver;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshInternetExplorerDriver;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;
import com.noosh.nooshentry.automation.util.AppConfig;
import com.noosh.nooshentry.automation.util.UtilsTools;

public abstract class BaseSeleniumTest {

	// IE web driver
	public final static String IE_DRIVER_SERVER_PATH_32="webDriverServer/IEDriverServer.exe";
	public final static String IE_DRIVER_SERVER_PATH_64="webDriverServer/IEDriverServer_64.exe";
	// Chrome web driver
	public final static String CHROME_DRIVER_SERVER_PATH="webDriverServer/chromedriver.exe";

	public static final long unicIDDig = System.currentTimeMillis()/10000;
	public static final String unicID = String.valueOf(unicIDDig).substring(3, 9);
	public static final String buyerEmail = "+" + unicID + "@gmail.com";
	//public static final String emailSP = "demo.nshauto+" + unicID + "@gmail.com";
	public static final String emailSP = "seleniumtest12345+" + unicID + "@gmail.com";
	public static final String siteNameBuyer1 = "Buyer demo";
	public static final String siteNameBuyer2 = "Selemium demo";
	public static String projectNumber = "";
	public static String buyerBrowser;
	public static String domain;
	public static String companyName;
	public static int jsErrorIndex = 0;
	public static int jsErrorIndexBuyer = 0;
	public int errorIndex = 0;
	public int errorIndexBuyer = 0;
	public static String browser;
	public static String locale;
	public static String spUser;   // 10/10
	public static String spDomain;  //10/11
	public static String supplierUser1;  
	public static String supplier1LoginUrl;
	public static String newSupplierUserCompanyName;   //10/15
	
	protected static Logger log = Logger.getLogger(BaseSeleniumTest.class.getName());
	
	protected static NooshWebDriver driver; //service provider
	
	protected static NooshWebDriver buyerDriver;

	@BeforeSuite
//	@Parameters("browser")
	public static void setUp() throws Exception {
		browser = AppConfig.getBrowser();
		buyerBrowser = AppConfig.getBuyerBrowser();
		domain = AppConfig.getDomain();
		companyName = AppConfig.getCompanyName();
		locale = AppConfig.getLocale();
		//UtilsTools.deleteScreenshotFiles();
		
		driver = initBrowerDriver(browser);
		if(buyerBrowser != null){
			if(buyerBrowser.equals(browser)){
				buyerDriver = driver;
			}else{
				buyerDriver = initBrowerDriver(buyerBrowser);
			}
		}
		System.out.println("buyerBrowser: " + buyerBrowser);
		System.out.println("domain: " + domain);
		System.out.println("locale: " + locale);
		log.info("--------------------------- Integration test, Basic Flow, Start ---------------------------------");
	}

	private static NooshWebDriver initBrowerDriver(String browser) throws IOException {
		if("IE".equals(browser)){
			String driverServerPath = BaseSeleniumTest.class.getClassLoader().getResource(IE_DRIVER_SERVER_PATH_32).getPath();
	        System.setProperty("webdriver.ie.driver", "D:\\chromedriver\\chromedriver.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
//			ieCapabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
	        return new NooshInternetExplorerDriver(ieCapabilities);
		} else if("Chrome".equals(browser)){
			String driverServerPath = BaseSeleniumTest.class.getClassLoader().getResource(CHROME_DRIVER_SERVER_PATH).getPath();
	        System.setProperty("webdriver.chrome.driver", driverServerPath);	        	        
			return new NooshChromeDriver();
		} else{	        	
			   FirefoxProfile ffProfile = new FirefoxProfile();
			   JavaScriptError.addExtension(ffProfile);
			   ffProfile.setEnableNativeEvents(true);
	           return new NooshFirefoxDriver(ffProfile);
		}
	}

	@AfterSuite
	public static void tearDown() throws Exception {
		if ((driver == null) || driver.toString().contains("null")) {
			return;
		}
		if(driver.equals(buyerDriver)){
		   driver.quit();
		}else {
//		if(!driver.equals(buyerDriver)){
		   buyerDriver.manage().deleteAllCookies(); 
		   buyerDriver.quit();
		}
	}
	
}