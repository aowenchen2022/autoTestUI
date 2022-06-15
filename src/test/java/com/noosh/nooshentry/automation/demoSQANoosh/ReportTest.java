/**
* This class tests the functionality of SP and demo site in Firefox browser
* @author Mikhail Novokshchenov
*/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Parameters;

import java.util.Hashtable;
import java.util.List;

import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.BuyerSitePage;
import com.noosh.nooshentry.automation.buyerSite.LoginBuyerPage;
import com.noosh.nooshentry.automation.buyerSite.LoginEmailPage;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptLog;

import java.util.concurrent.TimeUnit;
import java.awt.AWTException;

public class ReportTest extends BaseSeleniumTest
{   
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   static String baseUrlPro;
   static String baseUrlSmoke;
   static String loginUrl;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException
   {	
		if (domain.trim().equals("spd")) {
			baseUrlPro = "https://spd.noosh.com/noosh/home/login";
			baseUrlSmoke = "https://nooshselenium.noosh.com/service/login";
		} else if (domain.trim().equals("sdm")) {
			baseUrlPro = "http://sdm.noosh.com/noosh/home/login";
			baseUrlSmoke = "http://nooshselenium.sdm.noosh.com/service/login";
		} else if (domain.trim().equals("qa2")) {
			baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
			baseUrlSmoke = "https://nooshselenium.qa2.noosh.com/service/login";
		} else if (domain.trim().equals("sqa")) {
			baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
			baseUrlSmoke = "https://selenium263945." + domain + "." + companyName + ".com/service/login";
			loginUrl = "https://selenium263945." + domain + "." + companyName + ".com";
		} else {
			baseUrlSmoke = "http://demo.birt.noosh.com:8080/service/login";
		}
   }
   
//   @Test(description="Reporting testing", threadPoolSize = 3, invocationCount = 1, timeOut = 100000)
   @Test(description="Reporting testing")
   @Parameters({"loginName", "password", "baseUrl", "domain"})
   public void testReport(String loginName, String password, String baseUrl, String domain) throws InterruptedException, IOException
   {    
	  String windowNumber;  
	  String outputFolder = System.getProperty("user.dir") + "/reportTestLog";
	  File outputDir = new File(outputFolder);
	  if(!outputDir.exists()){
		  outputDir.mkdirs();
	  }
	  String outputLog = outputFolder + "/" + domain + ".csv" ;
	  String logString = "";
	  String threadInfo = "Test-" + System.currentTimeMillis();
      driver.get(loginUrl);    
    
      driver.manage().window().maximize();          
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
      ReportPage reportPage = new ReportPage(driver);
    
    // Login and go to Reports tab
      loginDemoSqa.loginUser(loginName, password);           
	  spSiteLoginPage = driver.getCurrentUrl();
	  windowNumber = driver.getWindowHandle();
	  reportPage.clickReportsBT();
	  Thread.sleep(4000);
	  driver.switchTo().frame("dashboardFrame");
	  reportPage.clickReportsTab();
	  Thread.sleep(3000);
	  // Get the Buying activity
	  driver.switchTo().frame("allReportFrame");
	  driver.findElement(By.linkText("Buying Activity")).click();
	  //driver.switchTo().window(windowNumber);
	  driver.switchTo().defaultContent(); 
	  Thread.sleep(2000);
	  for (String handle : driver.getWindowHandles()) {
		  driver.switchTo().window(handle);
	  }
	  //reportPage.clickFinishBT();	
	  List<WebElement> startBtn = driver.findElements(By.className("rpt_middle_on"));
	  startBtn.get(1).click();
	  logString += "Buying activity," + reportPage.getLoadTime();
	  System.out.println("Buying activity for " + domain + " ==> " + reportPage.getLoadTime() + " ms");
	   Thread.sleep(2000);
	   WebElement checkReport = driver.findElement(By.className("birt-label-design"));
	   if(!checkReport.isDisplayed()) {
		   throw new IllegalStateException("The the Buying activity is missing!");
	   }

	  driver.close();
/*	
	  // Get the project status
	  driver.switchTo().window(windowNumber);
	  Thread.sleep(2000);
	  driver.switchTo().frame("allReportFrame");
	  reportPage.clickProjectStatus();
	  for (String handle : driver.getWindowHandles()) {
		 driver.switchTo().window(handle);
	  }
	  logString += ",Project status," + reportPage.getLoadTime() + "," + threadInfo + "\n";
	  System.out.println("Project status for " + domain + " ==> " + reportPage.getLoadTime() + " ms");
	  FileWriter fw = null;
	  try {
		  fw = new FileWriter(outputLog, true);
		  fw.write(logString);
		  fw.flush();
	  } catch (IOException e) {
	  }finally{
		  if(fw!=null){
			  fw.close();
		  }
	  }
	  */
//	  System.out.println("Navigation bar 2: " + reportPage.getAttributNavigationBar());

//	driver.switchTo().window(windowNumber);
	/*
	Thread.sleep(15000);
	driver.close();
	
	// Logout
	Thread.sleep(5000);
	mainMenuPage.logoutSite();

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######"); 
	  	 */      		
    }   
   
   @Test(description="Reporting testing, step 1")
   @Parameters({""})
   public void testReportStep1() throws InterruptedException
   {    
      driver.get(loginUrl);    
    
      driver.manage().window().maximize();          
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);
//    MainMenuPage mainMenuPage = new MainMenuPage(driver);
      ReportPage reportPage = new ReportPage(driver);
    
    // Login and go to Reports tab
      loginDemoSqa.loginUser("ken@noosh.com", "17password");           
	  spSiteLoginPage = driver.getCurrentUrl();
	  reportPage.clickReportsBT();
	  Thread.sleep(5000);
	  reportPage.clickReportsTab();
	  Thread.sleep(3000);
   }
   
   @Test(description="Reporting testing, step 2")
   @Parameters({""})
   public void testReportStep2() throws InterruptedException
   { 
	  String windowNumber;  
	  ReportPage reportPage = new ReportPage(driver);
	  
	  windowNumber = driver.getWindowHandle();
	  // Get the account activity
	  driver.switchTo().frame("allReportFrame");
	  reportPage.clickAccountActivity();
	  Thread.sleep(1000);
	  for (String handle : driver.getWindowHandles()) {
		  driver.switchTo().window(handle);
	  }
	  reportPage.clickFinishBT();	
	  System.out.println("Account activity popup uploading time, ms : " + reportPage.getLoadTime());
//	  System.out.println("Navigation bar: " + reportPage.getAttributNavigationBar());
	  driver.close();
	
	  // Get the project status
	  driver.switchTo().window(windowNumber);
	  Thread.sleep(1000);
	  driver.switchTo().frame("allReportFrame");
	  reportPage.clickProjectStatus();
	  for (String handle : driver.getWindowHandles()) {
		 driver.switchTo().window(handle);
	  }
	  System.out.println("Project status popup uploading time, ms : " + reportPage.getLoadTime());
	  driver.close();
	  driver.switchTo().window(windowNumber);
	  System.out.println("------------------------------------------------------------------------");
	  

	  errorIndex = jsErrorIndex;
	  Page.jsErrorReporter(driver, errorIndex,"####### TC Login Demo SQA Page, ABAT, Login SP site JS errors for SP site #######");      		
    }   
   
   @Test
   public void testReport2_2() throws InterruptedException {
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
	   Thread.sleep(1000);
	   testReportStep2();
   }
}
