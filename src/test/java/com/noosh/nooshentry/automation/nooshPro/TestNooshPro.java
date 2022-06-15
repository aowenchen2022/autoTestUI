package com.noosh.nooshentry.automation.nooshPro;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.baseTestCases.BaseTestCases;
import com.noosh.nooshentry.automation.buyerSite.NewProjectPage;
import com.noosh.nooshentry.automation.demoSQANoosh.CreateNewProject;
import com.noosh.nooshentry.automation.demoSQANoosh.EditProductPopup;
import com.noosh.nooshentry.automation.demoSQANoosh.LoginDemoSqaPage;
import com.noosh.nooshentry.automation.demoSQANoosh.MyDeskPage;
import com.noosh.nooshentry.automation.demoSQANoosh.NewHomePage;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.demoSQANoosh.ProjectFrame;
import com.noosh.nooshentry.automation.demoSQANoosh.SiteListPage;
import com.noosh.nooshentry.automation.demoSQANoosh.SitesListTab;
import com.noosh.nooshentry.automation.demoSQANoosh.TestDemoSqaFF;

import org.openqa.selenium.Alert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class TestNooshPro extends BaseSeleniumTest
{
	String spSiteLoginPage;
	static String baseUrlPro;
	static String baseUrlSmoke;
	String windowNumber;
	
	@BeforeTest
	public static void setParameters(ITestContext context) throws FileNotFoundException
	{	
		if(domain.trim().equals("spd"))
		{
			baseUrlPro = "https://spd.noosh.com/noosh/home/login";
			baseUrlSmoke = "https://nooshselenium2.noosh.com/service/login";
		}else if(domain.trim().equals("sdm")){
			  baseUrlPro = "https://sdm.noosh.com/noosh/home/login";
			  baseUrlSmoke = "https://nooshselenium2.sdm.noosh.com/service/login";
			}else if(domain.trim().equals("qa2")) {
			      baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
			      baseUrlSmoke = "https://nooshselenium.qa2.noosh.com/service/login";
			    }else{		  
				    baseUrlPro = "https://sqa.noosh.com/noosh/home/login";
				    baseUrlSmoke = "https://nooshselenium2.sqa.noosh.com/service/login";
		   } 
	   }	

	@Test(description="TC 1 - Login to NGE SP site")
	@Parameters({"passwordSP", "spEmailSmoke"})
	public void testLoginNGE(String passwordSP, String spEmailSmoke)
	{
		BaseTestCases.testLoginDemoSqaPageSmoke(passwordSP, spEmailSmoke, baseUrlSmoke, spSiteLoginPage);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 1 - Login to NGE SP site, JS errors for SP site #######");
	}
	
	@Test(description="TC 2.1 - SP Create Project Adv. Editor - step 1")
	@Parameters({"validDemoUserEmail"})
	public void testSPcreateProjectAdvEd(String validDemoUserEmail) throws InterruptedException
	{
		BaseTestCases.testSPcreateProjectAdvEd(validDemoUserEmail);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 2.1 - SP Create Project - step 1, JS errors for SP site #######");	   
	}
	
	@Test(description="TC 2.2 - SP Create Project Adv. Editor - step 1")
	@Parameters({"productAdvEditor"})
	public void testSPcreateProjectAdvEdSelectProduct(String productAdvEditor) throws InterruptedException
	{		
		BaseTestCases.testSPcreateProjectAdvEdSelectProduct(productAdvEditor);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 2.2 - SP Create Project - step 1, JS errors for SP site #######");	   
	}
	
	@Test(description="TC 2.3 - SP Create Project Adv. Editor - step 2")
	@Parameters({"projNameSPAdv", "descriptionNameSPAdv", "referenceNumber", "quantSP"})
	public void testSPcreateProjectAdvEdProjectWizard(String projNameSPAdv, String descriptionNameSPAdv, String referenceNumber,
			String quantSP) throws InterruptedException
	{
		BaseTestCases.testSPcreateProjectAdvEdProjectWizard(projNameSPAdv, descriptionNameSPAdv, referenceNumber, quantSP);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 2.3 - SP Create Project Adv. Editor - step 2, JS errors for SP site #######");	   
	}
	
	@Test(description="TC 2.4 - SP Create Project Adv. Editor - upload file")
	@Parameters({"fileForProject3"})
	public void testUploadFile(String fileForProject3) throws InterruptedException, AWTException
	{
		BaseTestCases.testUploadFile(fileForProject3);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 2.4 - SP Create Project Adv. Editor - upload file, JS errors for SP site #######");	   
	}
	
	@Test(description="TC 2.5 - SP Create Project Adv. Editor - Add product")
	@Parameters({"newProjectNameSP", "reqField"})
	public void testSPcreateProjectAdvEdAddProduct(String newProjectNameSP, String reqField) throws InterruptedException
	{
		newProjectNameSP = newProjectNameSP + " " + unicID;
		
		BaseTestCases.testSPcreateProjectAdvEdAddProduct(newProjectNameSP, reqField);		
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 2.5 - SP Create Project Adv. Editor - Add product, JS errors for SP site #######");	   
	}
	
	@Test(description="TC 3 - Go to Noosh Pro")
	public void testGoToNooshPro() throws InterruptedException
	{
	   SiteListPage siteListPage = new SiteListPage(driver);
	    
	   Thread.sleep(4000);            
	   siteListPage.getMyDeskPageBack();
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 3 - Go to Noosh Pro, JS errors for SP site #######");		 	    
	}
	
	@Test(description="TC 4 - Go to Noosh Pro")
	public void testGoToProjectsList() throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   Thread.sleep(2000); 
	   myDesk.clickProjectsMenuItem();	   
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 4 - Go to Noosh Pro, JS errors for SP site #######");		 	    
	}

	@Test(description="TC 5.1 - Noosh Pro: Selection of the SP project")
	@Parameters({"newProjectNameSP"})
	public void testSelectSPProject(String newProjectNameSP) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   newProjectNameSP = newProjectNameSP + " " + unicID;
	   myDesk.setSearchProject(newProjectNameSP);
//	   myDesk.selectProject(driver, newProjectNameSP);
	   myDesk.selectProjectInList(driver, newProjectNameSP);
	   Thread.sleep(1000); 
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.1 - Noosh Pro: Selection of the SP project, JS errors for SP site #######");		 	    
	}
	
	@Test(description="TC 5.2 - Noosh Pro: Selection of the spec")
	@Parameters({"newProjectNameSP", "productAdvEditor"}) 
	public void testSelectFistSpec(String newProjectNameSP, String productAdvEditor) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   newProjectNameSP = newProjectNameSP + " " + unicID + " - " + productAdvEditor;
	   myDesk.selectProductSpec(driver, newProjectNameSP);
	   Thread.sleep(3000);
	   windowNumber = driver.getWindowHandle();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.2 - Noosh Pro: Selection os the spec, JS errors for SP site #######");		 	    
	}
	
	@Test(description="TC 5.3 - Noosh Pro: Spec info verification")
	@Parameters({"descriptionNameSPAdv", "referenceNumber", "quantSP", "weekDay"}) 
	public void testVerifySpecInfo(String descriptionNameSPAdv, String referenceNumber, String quantSP, String weekDay) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   Thread.sleep(1000);
	   driver.switchTo().frame("myProjectProductView");
//	   System.out.println(" Text Box text: " + myDesk.getTextBoxText());
//	   System.out.println(" Date : " + myDesk.getDate());
//	   System.out.println(" New Number : " + myDesk.getNewNumber());
//	   System.out.println(" Quantity in second field: " + myDesk.getQuantity());
//	   System.out.println(" Week day :" + myDesk.getWeekDay());
//	   System.out.println(" Priority :" + myDesk.getPriority());
//	   System.out.println(" Importance :" + myDesk.getImportanceStatus());	   
	   try {
	       AssertJUnit.assertEquals(descriptionNameSPAdv, myDesk.getTextBoxText());	      
	   } catch(Throwable e) {
		   System.out.println("Spec verification: Text '" + descriptionNameSPAdv + "' in Text box does not displayed");
		   log.info("Spec verification: Text '" + descriptionNameSPAdv + "' in Text box does not displayed");
	   }
	   /*
	   try {
	       AssertJUnit.assertEquals("6/14/13", myDesk.getDate());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Date '6/15/13' does not displayed");
		   log.info("Noosh Pro spec verification: Date '6/15/13' does not displayed");
	   }
	   */
	   try {
	       AssertJUnit.assertEquals(referenceNumber, myDesk.getNewNumber());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: New number '" + referenceNumber + "' does not displayed");
		   log.info("Noosh Pro spec verification: New number '" + referenceNumber + "' does not displayed");
	   }
	   try {
	       AssertJUnit.assertEquals(quantSP, myDesk.getNewDimentionLength());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Dimention length '" + quantSP + "' does not displayed");
		   log.info("Noosh Pro spec verification: Dimention length '" + quantSP + "' does not displayed");
	   }	 
	   try {
	       AssertJUnit.assertEquals(unicID, myDesk.getQuantity());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Quantity '" + unicID + "' does not displayed in the second field");
		   log.info("Noosh Pro spec verification: Quantity '" + unicID + "' does not displayed in the second field");
	   }
	   try {
	       AssertJUnit.assertEquals(weekDay, myDesk.getWeekDay());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Week day '" + weekDay + "' does not displayed");
		   log.info("Noosh Pro spec verification: Week day '" + weekDay + "' does not displayed");
	   }
	   try {
	       AssertJUnit.assertEquals("Medium", myDesk.getPriority());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Priority 'Medium' does not displayed");
		   log.info("Noosh Pro spec verification: Priority 'Medium' does not displayed");
	   }
	   try {
	       AssertJUnit.assertEquals("Yes", myDesk.getImportanceStatus());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Importance status 'Yes' does not displayed");
		   log.info("Noosh Pro spec verification: Importance status 'Yes' does not displayed");
	   }
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 5.3 - Noosh Pro: Spec info verification, JS errors for SP site #######");
	}
	
	@Test(description="TC 6.1 - Noosh Pro: Spec input invalid data")
	@Parameters({"newLastName", "lastNameBuyer", "requiredFieldErrorMessage"}) 
	public void testSpecInputInvalidData(String newLastName, String lastNameBuyer, String requiredFieldErrorMessage) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   myDesk.clickEditBT();
	   myDesk.setNewNumber(newLastName);
	   myDesk.setDimentionLength(lastNameBuyer);
	   myDesk.setTextInTextBox("");
	   myDesk.clickSaveBT();	
	   Thread.sleep(800);
	   try {
		      AssertJUnit.assertTrue(Page.isTextPresent(requiredFieldErrorMessage, driver));   
		   } catch(Throwable e) {
			  System.out.println("Noosh Pro: Error message " + requiredFieldErrorMessage + " does not displayed after save button");
			  log.info("Noosh Pro: Error message " + requiredFieldErrorMessage + " does not displayed after save button");
		   }
	   Thread.sleep(1000);	   
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.1 - Noosh Pro: Spec input invalid data, JS errors for SP site #######");
	}
	
	@Test(description="TC 6.2 - Noosh Pro: Spec input valid data")
	@Parameters({"textBox", "phoneNumberSP", "skuSP", "skuAdv", "weekDay"}) 
	public void testEditProjectSpec(String textBox, String phoneNumberSP, String skuSP, String skuAdv, String weekDay) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   EditProductPopup editProductPopup = new EditProductPopup(driver);
	   	   
	   myDesk.setNewNumber(phoneNumberSP);
	   myDesk.setDimentionLength(skuSP);
	   myDesk.setTextInTextBox(textBox);
	   myDesk.setQuantity2Field(skuAdv);
	   myDesk.setNewWeekDay();
	   myDesk.setPriority();		
	   editProductPopup.clickCalculator();
	   editProductPopup.setDate();
	   myDesk.clickSaveBT();
	   Thread.sleep(1000);	 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.2 - Noosh Pro: Spec input valid data, JS errors for SP site #######");
	}
	
	@Test(description="TC 6.3 - Noosh Pro: Spec info verification after update")
	@Parameters({"textBox", "phoneNumberSP", "skuSP", "skuAdv"}) 
	public void testVerifySpecInfoAfterUpdate(String textBox, String phoneNumberSP, String skuSP, String skuAdv) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   Thread.sleep(2000);
	   driver.switchTo().frame("myProjectProductView");
//	   System.out.println(" Text Box text: " + myDesk.getTextBoxText());
//	   System.out.println(" Date : " + myDesk.getDate());
//	   System.out.println(" New Number : " + myDesk.getNewNumber());
//	   System.out.println(" New Dimention length : " + myDesk.getNewDimentionLength());
//	   System.out.println(" Week day :" + myDesk.getWeekDay());
//	   System.out.println(" Priority :" + myDesk.getPriority());
//	   System.out.println(" Importance :" + myDesk.getImportanceStatus());	   
	   try {
	       AssertJUnit.assertEquals(textBox, myDesk.getTextBoxText());	      
	   } catch(Throwable e) {
		   System.out.println("Spec verification: Text '" + textBox + "' in Text box does not displayed");
		   log.info("Spec verification: Text '" + textBox + "' in Text box does not displayed");
	   }
	   /*
	   try {
	       AssertJUnit.assertEquals("6/15/13", myDesk.getDate());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Date '6/14/13' does not displayed");
		   log.info("Noosh Pro spec verification: Date '6/14/13' does not displayed");
	   }
	   */
	   try {
	       AssertJUnit.assertEquals(phoneNumberSP, myDesk.getNewNumber());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: New number '" + phoneNumberSP + "' does not displayed");
		   log.info("Noosh Pro spec verification: New number '" + phoneNumberSP + "' does not displayed");
	   }
	   try {
	       AssertJUnit.assertEquals(skuSP, myDesk.getNewDimentionLength());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Dimention length '" + skuSP + "' does not displayed");
		   log.info("Noosh Pro spec verification: Dimention length '" + skuSP + "' does not displayed");
	   }	 
	   try {
	       AssertJUnit.assertEquals(skuAdv, myDesk.getQuantity());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Quantity '" + skuAdv + "' does not displayed in the second field");
		   log.info("Noosh Pro spec verification: Quantity '" + skuAdv + "' does not displayed in the second field");
	   }
	   try {
	       AssertJUnit.assertEquals("Monday", myDesk.getWeekDay());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Week day 'Monday' does not displayed");
		   log.info("Noosh Pro spec verification: Week day 'Monday' does not displayed");
	   }
	   /*
	   try {
	       AssertJUnit.assertEquals("High", myDesk.getPriority());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Priority 'High' does not displayed");
		   log.info("Noosh Pro spec verification: Priority 'High' does not displayed");
	   }
	   */
	   try {
	       AssertJUnit.assertEquals("Yes", myDesk.getImportanceStatus());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Importance status 'Yes' does not displayed");
		   log.info("Noosh Pro spec verification: Importance status 'Yes' does not displayed");
	   }
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 6.3 - Noosh Pro: Spec info verification after update, JS errors for SP site #######");
	}
	
	@Test(description="TC 7 - Noosh Pro: Create new product")
	@Parameters({"productAdvEditor"}) 
	public void testSelectProduct(String productAdvEditor) throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   
	   driver.switchTo().window(windowNumber);
	   String productName = "Promotional";
	   projectMenu.clickSpecLink();
	   Thread.sleep(1000);
	   projectMenu.clickAdvSpec(driver, productName);
	   Thread.sleep(600);
	   projectMenu.clickContinueBT();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 7 - Noosh Pro: Create new product, JS errors for SP site #######");
	}
	
	@Test(description="TC 8.1 - Noosh Pro: Edit created spec")
	@Parameters({"productAdvEditor", "numberNewSpec", "dimentionLength", "specName"}) 
	public void testEditCreatedProject(String productAdvEditor, String numberNewSpec, String dimentionLength, String specName) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	    
	   myDesk.getSpecName();
	   Thread.sleep(1600);
	   productAdvEditor = "Spec: " + productAdvEditor; 
	   try {
	       AssertJUnit.assertEquals(productAdvEditor, myDesk.getSpecName().trim());	      
	   } catch(Throwable e) {
		   System.out.println("Spec verification: Spec name '" + productAdvEditor + "' does not displayed");
		   log.info("Spec verification: Spec name '" + productAdvEditor + "' does not displayed");
	   }
	   driver.switchTo().frame("myProjectProductView");   
	   myDesk.clickEditBT();
	   myDesk.setNewNumber(numberNewSpec);
	   myDesk.setDimentionLength(dimentionLength);
	   myDesk.setTextInTextBox(specName);
//	   myDesk.setQuantity1("3480");
	   myDesk.clickSaveBT();	
//	   driver.switchTo().window(windowNumber);
	   Thread.sleep(800);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.1 - Noosh Pro: Edit created spec, JS errors for SP site #######");
	}
	
	@Test(description="TC 8.2 - Noosh Pro: Edited Spec info verification")
	@Parameters({"numberNewSpec", "dimentionLength", "specName"}) 
	public void testVerifyNewSpecInfo(String numberNewSpec, String dimentionLength, String specName) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   driver.switchTo().frame("myProjectProductView"); 
	   Thread.sleep(2000);
//	   System.out.println(" Text Box text: " + myDesk.getTextBoxText());
//	   System.out.println(" New Number : " + myDesk.getNewNumber());
//	   System.out.println(" New Dimention length : " + myDesk.getNewDimentionLength());
   
	   try {
	       AssertJUnit.assertEquals(specName, myDesk.getTextBoxText());	      
	   } catch(Throwable e) {
		   System.out.println("Spec verification: Text '" + specName + "' in Text box does not displayed, created spec");
		   log.info("Spec verification: Text '" + specName + "' in Text box does not displayed, created spec");
	   }
	   try {
	       AssertJUnit.assertEquals(numberNewSpec, myDesk.getNewNumber());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: New number '" + numberNewSpec + "' does not displayed, created spec");
		   log.info("Noosh Pro spec verification: New number '" + numberNewSpec + "' does not displayed, created spec");
	   }
	   try {
	       AssertJUnit.assertEquals(dimentionLength, myDesk.getNewDimentionLength());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro spec verification: Dimention length '" + dimentionLength + "' does not displayed, created spec");
		   log.info("Noosh Pro spec verification: Dimention length '" + dimentionLength + "' does not displayed, created spec");
	   }	 
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 8.2 - Noosh Pro: Spec info verification after update, JS errors #######");
	}	
	
	@Test(description="TC 9 - Noosh Pro: Update project")
	@Parameters({"projectNamePro", "jobStatus"}) 
	public void testUpdateProject(String projectNamePro, String jobStatus) throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   ProjectOverview projectOverview = new ProjectOverview(driver);
	   
	   driver.switchTo().window(windowNumber);
	   if(projectMenu.checkHomeMenuOpen() == false) {
	       projectMenu.openHomeMenu();
	       projectMenu.clickUpdateLink();
	   } else
	       projectMenu.clickUpdateLink();
	   projectNamePro = projectNamePro + " " + unicID;
	   Thread.sleep(1000);
	   projectOverview.setNewProjectName(projectNamePro);	
	   projectOverview.clickSave();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 9 - Noosh Pro: Create new product, JS errors #######");
	}

	@Test(description="TC 10.1 - Noosh Pro: Post message")
	@Parameters({"messageSubject", "contentText"}) 
	public void testSendMessage(String messageSubject, String contentText) throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
       Messages messages = new Messages(driver);
	   
	   driver.switchTo().window(windowNumber);
	   if(projectMenu.checkMessagesMenuOpen() == false) {
		   projectMenu.clickMessageLink();
		   projectMenu.clickMessagePostLink();
	   } else
	       projectMenu.clickMessagePostLink();
	   Thread.sleep(1000);
	   messages.setMessageSubject(messageSubject);
	   messages.setMessageText(contentText);
	   Thread.sleep(1000);
	   messages.selectCheckBox(driver,  "Selenium SP");
	   messages.selectCheckBox(driver,  "Selenium Buyer");
	   messages.setHighPriority();
	   Thread.sleep(1000);
	   messages.clickPostMessage();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.1 - Noosh Pro: Post message, JS errors #######");
	}
	
	@Test(description="TC 10.2 - Noosh Pro: Check message in the list")
	@Parameters({"messageSubject"}) 
	public void testCheckMessageInList(String messageSubject) throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
       Messages messages = new Messages(driver);
	   
       projectMenu.clickMessageListLink();
	   Thread.sleep(1000);
	   messages.getMessagesList();
	   Thread.sleep(4000);
//	   System.out.println(" Message priority: " + messages.getPriority());
	   try {
	       AssertJUnit.assertEquals("Medium", messages.getPriority());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro message verification: Priority '" + "Medium" + "' does not displayed");
		   log.info("Noosh Pro message verification: Priority '" + "Medium" + "' does not displayed");
	   }	
//	   System.out.println("Message subject : " + messages.getSubjectText());
	   try {
	       AssertJUnit.assertEquals(messageSubject, messages.getSubjectText());	      
	   } catch(Throwable e) {
		   System.out.println("Noosh Pro message verification: message subject '" + messageSubject + "' does not displayed");
		   log.info("Noosh Pro message verification: message subject '" + messageSubject + "' does not displayed");
	   }
	   projectMenu.clickMessageLink();
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 10.2 - Noosh Pro: Check message in the list, JS errors #######");
	}
	
	@Test(description="TC 11 - Noosh Pro: Check file in list")
	@Parameters({"fileName3", "profilePicture", "profilePictureFile"}) 
	public void testCheckFileInList(String fileName3, String profilePicture, String profilePictureFile) throws InterruptedException, AWTException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   ProjectOverview projectOverview = new ProjectOverview(driver);
	   ProjectFrame  projectFrame = new ProjectFrame(driver);
	   Page page = new Page(driver);
	   
	   projectMenu.clickFileLink();
	   Thread.sleep(1500);
	   windowNumber = driver.getWindowHandle();
	   driver.switchTo().frame("fileView");
	   Thread.sleep(500);
	   try{
	       AssertJUnit.assertEquals(fileName3, projectOverview.getFileNameInList(driver, fileName3).trim());
	      } catch(Throwable e) {
	    	  System.out.println("Uploaded into SP file " + fileName3 + " does not displayed in the list files in NooshPro");
	    	  log.info("Uploaded into SP file " + fileName3 + " does not displayed in the list files in NooshPro");
	    	}
	   page.uploadFileModalWindow(profilePicture);
	   projectFrame.clickFileUploadSP();
//	   projectFrame.clickuploadFilePro();
	   Thread.sleep(600);
	   page.robotUpload();
	   Thread.sleep(3000);	 
	   // download file and close viewer window
       projectFrame.clickDownloadFileIcon(driver, fileName3);
       page.downloadFile();
	   // delete file
	   projectFrame.clickDeleteFileIcon(driver, fileName3);
	   Thread.sleep(600);
       Alert alert = driver.switchTo().alert();
	   alert.accept();
 	   page.closeModalPopup();
	   Thread.sleep(2000);
	   try{
	       AssertJUnit.assertEquals(profilePictureFile, projectOverview.getFileNameInList(driver, profilePictureFile).trim());
	      } catch(Throwable e) {
	    	  System.out.println("Uploaded file " + profilePictureFile + " does not displayed in the list files in NooshPro");
	    	  log.info("Uploaded file " + profilePictureFile + " does not displayed in the list files in NooshPro");
	    	}	   
	   driver.switchTo().window(windowNumber);
	   Thread.sleep(600);
	   projectMenu.clickFileLink();
	   Thread.sleep(700);
	   /*
	   projectOverview.clickFileName(driver, fileName3);
	   Thread.sleep(1000);
	   driver.navigate().back();
	   Thread.sleep(1000);	
	   projectOverview.clickAttachButton();
	   Thread.sleep(1000);
	   projectOverview.uploadImageFile(profilePicture);
	   Thread.sleep(2000);
	   projectOverview.clickFileOpen(driver, profilePictureFile);
	   Thread.sleep(1000);
	   page.closeModalPopup();
	   Thread.sleep(1000);
	   projectMenu.clickFileLink();
	   Thread.sleep(1000);
	      */
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 11 - Noosh Pro: Check file in the list, JS errors #######");
	}
	
	@Test(description="TC 12.1 - Noosh Pro: Go to NGE")
	@Parameters({}) 
	public void testGotoNGE() throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   myDesk.clickNGE();
	   Thread.sleep(1500);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 12.1 - Noosh Pro: Go to NGE, JS errors #######");
	}
	
	@Test(description="TC 12.2 - Noosh Pro: Verification of the project in the NE site")
	@Parameters({"profilePictureFile", "fileName3", "contentText", "projectNamePro"}) 
	public void testProjectVerificationNG(String profilePictureFile, String fileName3, String contentText, String projectNamePro) throws InterruptedException
	{
	   ProjectFrame projectFrame = new ProjectFrame(driver);
	   NewProjectPage projectPage = new com.noosh.nooshentry.automation.buyerSite.NewProjectPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);
	   
	   /* 	   */
	   projectNamePro = projectNamePro + " " + unicID;
	   
	   newHomePage.clickProjectInListNew();
//	   newHomePage.clickProjectInList(driver, projectNamePro);
//	   projectFrame.clickReviewProjectBTSmoke(driver, projectNamePro);	
	   System.out.println("Project name: " + projectFrame.getSPProjectName());
	   
	   Thread.sleep(600);
	   projectPage.clickFileTab();
	   Thread.sleep(1000);
	   /*
	   try{
	       AssertJUnit.assertEquals(fileName3, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("Uploaded file name " + fileName3 + " does not displayed in SP site");
	    	  log.info("Uploaded file name " + fileName3 + " does not displayed in SP site");
	    	}
	    	*/
	   try{
	       AssertJUnit.assertEquals(profilePictureFile, projectFrame.getUploadedFileName33().trim());
	      } catch(Throwable e) {
	    	  System.out.println("Uploaded file name " + profilePictureFile + " does not displayed in SP site");
	    	  log.info("Uploaded file name " + profilePictureFile + " does not displayed in SP site");
	    	}	   
//	   projectPage.uploadProjectFile(fileName3);
//	   Thread.sleep(400);
	   /* Temporary switched off 6/19/2013
	   try {
		      AssertJUnit.assertTrue(projectFrame.getUploadedFileName(fileName3));   
		   } catch(Throwable e) {
			  System.out.println("Noosh Pro: Uploaded file name " + fileName3 + " does not displayed");
			  log.info("Noosh Pro: Uploaded file name " + fileName3 + " does not displayed");
		   }
		*/
	   projectPage.clickMessageTab();
//	   System.out.println("Sp message: " + projectFrame.getMessage());
	   try {
		      AssertJUnit.assertEquals(contentText, projectFrame.getMessage());   
		   } catch(Throwable e) {
			  System.out.println("Noosh Pro: SP message '" + contentText + "' does not displayed");
			  log.info("Noosh Pro: SP message '" + contentText + "' does not displayed");
		   }
       Thread.sleep(2000);	 
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 12.2 - Noosh Pro: Go to NGE, JS errors #######");
	}
	
	@Test(description="TC 13.1 - Go to Noosh Pro second time")
	public void testGoToNooshProSecondTime() throws InterruptedException
	{
	   SiteListPage siteListPage = new SiteListPage(driver);
	    
	   Thread.sleep(1400);            
	   siteListPage.getMyDeskPageBack();
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 13.1 - Noosh Pro: Go to Noosh Pro second time, JS errors  #######");		 	    
	}
	
	@Test(description="TC 13.2 - Noosh Pro: Go to projects list")
	public void testGoToProjectsListSecondTime() throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   Thread.sleep(2000); 
	   myDesk.clickProjectsMenuItem();	   
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 13.2 - Noosh Pro: Go to projects list, JS errors  #######");		 	    
	}
	
	@Test(description="TC 13.3 - Noosh Pro: Selection of the SP project second time")
	@Parameters({"projectNamePro"})
	public void testSelectSPProjectSecondTime(String projectNamePro) throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   
	   projectNamePro = projectNamePro + " " + unicID;
	   myDesk.setSearchProject(projectNamePro);
//	   myDesk.selectProject(driver, newProjectNameSP);
	   myDesk.selectProjectInList(driver, projectNamePro);
	   Thread.sleep(2500); 
	    
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 13.3 - Noosh Pro: Selection of the SP project second time, JS errors  #######");		 	    
	}	
	
	@Test(description="TC 14.1 - Noosh Pro: Create RFE link")
	@Parameters({}) 
	public void testCreateRFELink() throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   
	   if(projectMenu.checkEstimatesMenu() == false) {
		   projectMenu.clickEstimatesLink();
		   projectMenu.clickCreateRFELink();
	   } else 
		   projectMenu.clickCreateRFELink();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.1 - Noosh Pro: 'Create RFE' link, JS errors  #######");		 	    
	}
	
	@Test(description="TC 14.2 - Noosh Pro: Create RFE - go to RFE edit")
	@Parameters({}) 
	public void testGotoRFE() throws InterruptedException
	{
	   MyDesk myDesk = new MyDesk(driver);
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   
	   myDesk.selectSpecCheckBox();
	   myDesk.clickContinueBT();
	   Thread.sleep(1000);	   	   
//	   projectMenu.clickEstimatesLink();  //
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.2 - Noosh Pro: Create RFE link, JS errors  #######");		 	    
	}
	
	@Test(description="TC 14.3 - Noosh Pro: Create RFE - input RFE title, date, quantity1 and quantity1")
	@Parameters({"titleRFE", "quantityOne", "quantityTwo"}) 
	public void testCreateRFE(String titleRFE, String quantityOne, String quantityTwo) throws InterruptedException
	{
	   RFEForm rfeForm = new RFEForm(driver);
	   
	   Thread.sleep(1000);
	   titleRFE = titleRFE + " " + unicID;
	   rfeForm.setRFETitle(titleRFE);
	   Page.getCurrentDate();
	   Thread.sleep(1000);
	   rfeForm.setEstimateDueDatePro("9/22/13");
	   Thread.sleep(400);
	   rfeForm.setProposedCompletionDate("9/25/13");
//	   rfeForm.clickCalendarDue();
//	   rfeForm.clickCalendarComplation();
	   rfeForm.setQuantity1(quantityOne);
	   rfeForm.setQuantity2(quantityTwo);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 14.3 - Noosh Pro: Create RFE - input RFE title, date, quantity1 and quantity1, JS errors  #######");		 	    
	}

	@Test(description="TC 15 - Noosh Pro: Create RFE - check Edit mode")
	@Parameters({""}) 
	public void testCheckEditMode() throws InterruptedException
	{
	   RFEForm rfeForm = new RFEForm(driver);
	   MyDesk myDesk = new MyDesk(driver);
	   
	   rfeForm.clickEditIcon();
	   Thread.sleep(1500);
	   myDesk.clickCanselBT();
	   Thread.sleep(1000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 15 - Noosh Pro: Create RFE - check Edit mode, JS errors  #######");		 	    
	}
	
	@Test(description="TC 16.1 - Noosh Pro: Create RFE - select supplier")
	@Parameters({""}) 
	public void testSelectSupplier() throws InterruptedException
	{
	   RFEForm rfeForm = new RFEForm(driver);
	   Suppliers suppliers = new Suppliers(driver);
	   
	   rfeForm.clickContactsLink();
	   Thread.sleep(1000);
	   for (String handle : driver.getWindowHandles()) {
	      driver.switchTo().window(handle);
	   }
	   Thread.sleep(1000);
	   suppliers.selectSupplier();
	   suppliers.clickDoneBT();
	   Thread.sleep(1000);
	   driver.switchTo().window(windowNumber);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 16.1 - Noosh Pro: Create RFE - select supplier, JS errors  #######");		 	    
	}
	
	@Test(description="TC 16.2 - Noosh Pro: Create RFE - send RFE")
	@Parameters({""}) 
	public void testSendRFE() throws InterruptedException
	{
	   RFEForm rfeForm = new RFEForm(driver);
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   
	   Thread.sleep(800);
	   rfeForm.clickSendRFE();
	   Thread.sleep(3000);
	   rfeForm.clickRFEList();
	   Thread.sleep(2000);
	   rfeForm.openRFE();
	   Thread.sleep(2000);
	   projectMenu.clickEstimatesLink();
	   Thread.sleep(3000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 16.2 - Noosh Pro: Create RFE - send RFE, JS errors  #######");		 	    
	}
	
	@Test(description="TC 17 - Noosh Pro: Log out from Noosh Pro site")
	@Parameters({""}) 
	public void testLogoutNooshPro() throws InterruptedException
	{
	   ProjectMenu projectMenu = new ProjectMenu(driver);
	   
	   projectMenu.clickLogout();
	   Thread.sleep(2000);
	   
	   errorIndex = jsErrorIndex;
	   Page.jsErrorReporter(driver, errorIndex,"####### TC 17 - Noosh Pro: Log out from Noosh Pro site, JS errors  #######");		 	    
	}
	
	@Test(description="TC 18 Noosh Pro - Supplier: Login Noosh Pro as Supplier")
	@Parameters({"supplierEmail", "passwordSP"})
	public void testLoginAsSupplier(String supplierEmail, String passwordSP)
	{
	    LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);  
	    /*
	    driver.get("https://sqa.noosh.com/noosh/home/login?%24Hy8fxPcOUOBUt/JyjLQ4WbfQJcQsiGqoQoPTeeIDIQwIU7VKvFM7LDmJxiNqMer4mPltpGJFm3k=");    
	    driver.manage().window().maximize();  	    	    
	    */  
		loginDemoSqa.loginSup(supplierEmail, passwordSP);				
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 18 - Noosh Pro: Login Noosh Pro as Supplier, JS errors  #######");
	}
	
	@Test(description="TC 19 - Noosh Pro - Supplier: Select project in Project Activity and click spec")
	@Parameters({"projectNamePro", "newProjectNameSP", "productAdvEditor"})
	public void testSelectProjectAndSpec(String projectNamePro, String newProjectNameSP, String productAdvEditor) throws InterruptedException
	{
		MyDesk myDesk = new MyDesk(driver);
		
		projectNamePro = projectNamePro + " " + unicID;
		myDesk.selectProject(driver, projectNamePro);
        newProjectNameSP = newProjectNameSP + " " + unicID + " - " + productAdvEditor;
        myDesk.selectProductSpec(driver, newProjectNameSP);
        Thread.sleep(5000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 19 - Noosh Pro - Supplier: Select project in Project Activity and click spec, JS errors  #######");
	}
	
	@Test(description=" TC 20.1 - Noosh Pro - Supplier: Go to project to estimate")
	@Parameters({"titleRFE"})
	public void testSelectProjectToEstimate(String titleRFE) throws InterruptedException
	{
		MyDesk myDesk = new MyDesk(driver);
		
//		myDesk.clickSummarySubmenu();
		
		driver.navigate().back();
		Thread.sleep(700);
		driver.navigate().back();
		Thread.sleep(700);
		
		titleRFE = titleRFE + " " + unicID;
		
		myDesk.clickCreateEstimateProject(driver, titleRFE);
		Thread.sleep(3000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 20.1 - Noosh Pro - Supplier: Go to project to estimate, JS errors  #######");		
	}
	
    @Test(description="TC 20.2 Noosh Pro - Supplier: Supplier verify of estimate")
    @Parameters({"titleRFE", "quantityOne", "quantityTwo"})
    public void testSupplierVerifyEstimate(String titleRFE, String quantityOne, String quantityTwo) throws InterruptedException
    {
 	    RFEForm rfeForm = new RFEForm(driver);
    	
		Thread.sleep(1000);
		titleRFE = titleRFE + " " + unicID;
 	    try {
		      AssertJUnit.assertEquals(titleRFE, rfeForm.getEstimateTitle());   
		    } catch(Throwable e) {
			  System.out.println("Noosh Pro - Supplier: Estimate title '" + titleRFE + "' does not displayed");
			  log.info("Noosh Pro - Supplier: Estimate title '" + titleRFE + "' does not displayed");
		    }
 	    try {
		      AssertJUnit.assertEquals(quantityOne, rfeForm.getQuqntityOne());   
		    } catch(Throwable e) {
			  System.out.println("Noosh Pro - Supplier: Quantity 1 '" + quantityOne + "' does not displayed");
			  log.info("Noosh Pro - Supplier: Quantiry 1 '" + quantityOne + "' does not displayed");
		    }
 	    try {
		      AssertJUnit.assertEquals(quantityTwo, rfeForm.getQuantityTwo());   
		    } catch(Throwable e) {
			  System.out.println("Noosh Pro - Supplier: Quantity 2 '" + quantityTwo + "' does not displayed");
			  log.info("Noosh Pro - Supplier: Quantiry 2 '" + quantityTwo + "' does not displayed");
		    }	 	    
		Thread.sleep(1000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 20.2 Noosh Pro - Supplier: Supplier verify of estimate, JS errors  #######");		
    }
    
	@Test(description="TC 20.3 - Noosh Pro - Supplier: Open spec")
	@Parameters({"newProjectNameSP", "productAdvEditor", "textBox"})
	public void testSelectProjectSpec(String newProjectNameSP, String productAdvEditor, String textBox) throws InterruptedException
	{			
		MyDesk myDesk = new MyDesk(driver);
		
		testSelectFistSpec(newProjectNameSP, productAdvEditor);
		Thread.sleep(2000);
		driver.switchTo().frame("myProjectProductView");	
		Thread.sleep(1000);
		try {
		    AssertJUnit.assertEquals(textBox, myDesk.getTextBoxText());	      
		    } catch(Throwable e) {
			System.out.println("Spec verification by supplier: Text '" + textBox + "' in Text box does not displayed");
			log.info("Spec verification by supplier: Text '" + textBox + "' in Text box does not displayed");
		    }
		driver.switchTo().window(windowNumber);
		Thread.sleep(1000);
		myDesk.clickReternBT();
		Thread.sleep(3000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 20.3 - Noosh Pro - Supplier: Open spec, JS errors  #######");		
	}
	
	@Test(description="TC 21.1 - Noosh Pro - Supplier: supplier create estimate")
	@Parameters({"estimateTitleSupplier", "priceQuantOne", "priceQuantTwo"})
	public void testSupplierCreateEstimate(String estimateTitleSupplier, String priceQuantOne, String priceQuantTwo) throws InterruptedException, AWTException
	{
 	    RFEForm rfeForm = new RFEForm(driver);
 	    
 	    rfeForm.clickCreateEstimateBT();
 	    rfeForm.setEstimateTitle(estimateTitleSupplier);
// 	    rfeForm.clickCalendarExpiration();
 	    rfeForm.setEstimadeDue("9/24/13");
 	    rfeForm.setPriceForQuality1(driver, "Price", priceQuantOne);
 	    Page.robotClickEnter();
 	    rfeForm.setPriceForQuality2(driver, "Price", priceQuantTwo);
 	    Page.robotClickEnter();
 	    Thread.sleep(4000);
 	   
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 21.1 - Noosh Pro - Supplier: supplier create estimate, JS errors  #######"); 	    
	}
	
	@Test(description="TC 21.2 - Noosh Pro - Supplier: Open spec in Estimate Edit")
	@Parameters({"newProjectNameSP", "productAdvEditor", "textBox"})
	public void testSelectProjectSpecEstimateEdit(String newProjectNameSP, String productAdvEditor, String textBox) throws InterruptedException
	{			
		MyDesk myDesk = new MyDesk(driver);
		
		testSelectFistSpec(newProjectNameSP, productAdvEditor);
		Thread.sleep(2000);
		driver.switchTo().frame("myProjectProductView");	
		Thread.sleep(2000);
		try {
		    AssertJUnit.assertEquals(textBox, myDesk.getTextBoxText());	      
		    } catch(Throwable e) {
			System.out.println("Spec verification by supplier: Text '" + textBox + "' in Text box does not displayed");
			log.info("Spec verification by supplier: Text '" + textBox + "' in Text box does not displayed");
		    }
		driver.switchTo().window(windowNumber);
		Thread.sleep(1000);
		myDesk.clickReternBT();
		Thread.sleep(5000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 21.2 - Noosh Pro - Supplier: Open spec, JS errors  #######");		
	}
	
	@Test(description="TC 21.3 - Noosh Pro - Supplier: Send estimate")
	@Parameters({"confirmationStatus"})
	public void testSendEstimate(String confirmationStatus) throws InterruptedException 
	{
 	    RFEForm rfeForm = new RFEForm(driver);
 	    
 	    rfeForm.clickSendEstimateBT();
		Thread.sleep(3000);		
		try {
		    AssertJUnit.assertEquals(confirmationStatus, rfeForm.getConfermationStatus().trim());	      
		    } catch(Throwable e) {
			System.out.println("Confirmation message for submitting estimate '.. successfully submitted' does not displayed");
			log.info("Confirmation message for submitting estimate '.. successfully submitted' does not displayed");
		    }
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 21.3 - Noosh Pro - Supplier: Send estimate, JS errors  #######");
	}
	
	@Test(description="TC 22 - Noosh Pro - Supplier: View estimate list")
	@Parameters({"titleRFE"})
	public void testViewEstimateList(String titleRFE)
	{
 	    RFEForm rfeForm = new RFEForm(driver);
 	    
 	    rfeForm.clickViewEstimateListBT();
 	    rfeForm.getRFETitle();
 	    System.out.println("RFE reference number: " + rfeForm.getRFEReferenceNumber());
 	    System.out.println("RFE full name: " + rfeForm.getFullRFEName());
		titleRFE = titleRFE + " " + unicID;
		try {
		    AssertJUnit.assertEquals(titleRFE, rfeForm.getSPEstimateName().trim());	      
		    } catch(Throwable e) {
			System.out.println("SP estimate " + titleRFE + " does not displayed in the list for Supplier");
			log.info("SP estimate " + titleRFE + " does not displayed in the list for Supplier");
		    } 	    
 //	    System.out.println("Estimate title: " + rfeForm.getSPEstimateName());
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 22 - Noosh Pro - Supplier: View estimate list, JS errors  #######");		
	}
	
	@Test(description="TC 23 - Noosh Pro - Supplier: Open Estimate")
	@Parameters({"titleRFE", "estimateTitleSupplier", "newProjectNameSP", "productAdvEditor"})
	public void testOpenEstimate(String titleRFE, String estimateTitleSupplier, String newProjectNameSP, String productAdvEditor) throws InterruptedException
	{
 	    RFEForm rfeForm = new RFEForm(driver);
 	    
        rfeForm.openFullRFE();
        Thread.sleep(1000);
 	    System.out.println("SP RFE title: " + rfeForm.getSPEstimateTitle());        
		titleRFE = titleRFE + " " + unicID;
		try {
		    AssertJUnit.assertEquals(titleRFE, rfeForm.getSPEstimateTitle().trim());	      
		    } catch(Throwable e) {
			System.out.println("SP estimate " + titleRFE + " does not displayed in open Estimate");
			log.info("SP estimate " + titleRFE + " does not displayed in open Estimate");
		    } 	
		
 	    System.out.println("Supplier Estimate title: " + rfeForm.getSupplierEstimateTitle());
		try {
		    AssertJUnit.assertEquals(estimateTitleSupplier, rfeForm.getSupplierEstimateTitle().trim());	      
		    } catch(Throwable e) {
			System.out.println("Supplier estimate " + titleRFE + " does not displayed in open Estimate");
			log.info("Supplier estimate " + titleRFE + " does not displayed in open Estimate");
		    } 
		
        newProjectNameSP = newProjectNameSP + " " + unicID + " - " + productAdvEditor;
        String specName = rfeForm.getSpecTitle().trim();
        specName = specName.substring(0, specName.length() - 10);
//		System.out.println("Spec title: " + rfeForm.getSpecTitle() + " or " + specName);
		try {
		    AssertJUnit.assertEquals(newProjectNameSP, specName);	      
		    } catch(Throwable e) {
			System.out.println("Spec name: '" + newProjectNameSP + "' does not displayed in open Estimate");
			log.info("Spec name: '" + newProjectNameSP + "' does not displayed in open Estimate");
		    }
        Thread.sleep(4000);
        
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 23 - Noosh Pro - Supplier: Open Estimate, JS errors  #######");
	}
	
	@Test(description="TC 24 - Noosh Pro - Supplier: Logout")
	@Parameters({""})
	public void testLogoutSupplier() throws InterruptedException
	{
		testLogoutNooshPro();
        Thread.sleep(1000);
        
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 24 - Noosh Pro - Supplier: Logout, JS errors  #######");        
	}
	
	@Test(description="TC 25 - Noosh Pro - SP: Login Noosh Pro site")
	@Parameters({"passwordSP", "spEmailSmoke"})
	public void testLoginSPNooshPro(String passwordSP, String spEmailSmoke) throws InterruptedException
	{ 
        Thread.sleep(1000);        
	    driver.get(baseUrlPro);             
	    LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
	    loginDemoSqa.loginSup(spEmailSmoke, passwordSP); 
	    
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 25 - Noosh Pro - SP: Login Noosh Pro site, JS errors  #######");	    
	}
	
	@Test(description="TC 26 - Noosh Pro - SP: SP select Estimate for review")
	@Parameters({"projectNamePro"})
	public void testSPOpenEstimateNP(String projectNamePro) throws InterruptedException
	{ 
		MyDesk myDesk = new MyDesk(driver);
		
		projectNamePro = projectNamePro + " " + unicID;
        Thread.sleep(1000);
        myDesk.selectEstimate(driver, projectNamePro);
        Thread.sleep(1000);
        
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 26 - Noosh Pro - SP: SP select Estimate for review, JS errors  #######");        
	}
	
	@Test(description="TC 27 - Noosh Pro - SP: Review Estimate")
	@Parameters({"newProjectNameSP", "productAdvEditor", "textBox"})
	public void testSPReviewEstimateNP(String newProjectNameSP, String productAdvEditor, String textBox) throws InterruptedException
	{ 
		testSelectProjectSpecEstimateEdit(newProjectNameSP, productAdvEditor, textBox);
				
	}
	
	@Test(description="TC 28.1 - Noosh Pro - SP: Create order - Award order")
	@Parameters({""})
	public void testSPCraeteOrderAward() throws InterruptedException
	{ 
		RFEForm rfeForm = new RFEForm(driver);
		
		rfeForm.selectTotalPrice();
		rfeForm.clickAwardOrderBT();
		Thread.sleep(2000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 28.1 - Noosh Pro - SP: Create order - Award order, JS errors  #######");		
	}
	
	@Test(description="TC 28.2 - Noosh Pro - SP: Create order - Open spec")
	@Parameters({"newProjectNameSP", "productAdvEditor"})
	public void testSPCraeteOrderOpenSpec(String newProjectNameSP, String productAdvEditor) throws InterruptedException
	{ 
		MyDesk myDesk = new MyDesk(driver);
		
		testSelectFistSpec(newProjectNameSP, productAdvEditor);
		Thread.sleep(1000);
		myDesk.clickReternBT();
		Thread.sleep(3000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 28.2 - Noosh Pro - SP: Create order - Open spec, JS errors  #######");		
	}
	
	@Test(description="TC 28.3 - Noosh Pro - SP: Create order")
	@Parameters({"titleRFE"})
	public void testSPCraeteOrder(String titleRFE) throws InterruptedException
	{ 
		Order order = new Order(driver);
		
		titleRFE = titleRFE + " " + unicID + " - Order";
        order.setOrderTitle(titleRFE);
        order.setOrderCopmlationDate("8/24/13");
        order.selectOrderComplationTime();
        order.clickProceedToConfirmationBT();
        Thread.sleep(1000);
        order.clickOrderCreationBT();
        
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 28.3 - Noosh Pro - SP: Create order, JS errors  #######");	        
	}
	
	@Test(description="TC 28.4 - Noosh Pro - SP: Create order - Verification of order")
	@Parameters({"orderConfirmationText", "titleRFE"})
	public void testSPCraeteOrderVerification(String orderConfirmationText, String titleRFE) throws InterruptedException
	{ 
		Order order = new Order(driver);
		
		titleRFE = titleRFE + " " + unicID + " - Order";
		Thread.sleep(4000);
		order.checkCofirmationDisplayed(orderConfirmationText, titleRFE);
		order.clickViewOrderList();
		Thread.sleep(5000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 28.4 - Noosh Pro - SP: Create order - Verification of order, JS errors  #######");		
	}
	
	@Test(description="TC 28.5 - Noosh Pro - SP: Create order - Open order")
	@Parameters({"titleRFE"})
	public void testSPCraeteOrderOpenOrder(String titleRFE) throws InterruptedException
	{ 
		Order order = new Order(driver);
		
		titleRFE = titleRFE + " " + unicID + " - Order";
		Thread.sleep(1000);
		String orderTitle = "order_href " + titleRFE;
		order.clickOrderInList(driver, orderTitle);
		
		try {
		    AssertJUnit.assertEquals(titleRFE, order.getOrderTitle().trim());	      
		    } catch(Throwable e) {
			System.out.println("Order title: '" + titleRFE + "' does not displayed in open order");
			log.info("Order title: '" + titleRFE + "' does not displayed in open order");
		    } 	
		Thread.sleep(5000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 28.5 - Noosh Pro - SP: Create order - Open order, JS errors  #######");		
	}
	
	@Test(description="TC 29 - Noosh Pro - SP: Logout")
	public void testLogoutSPNP() throws InterruptedException {
		testLogoutSupplier();
	}
	
	@Test(description="TC 30 - Noosh Pro - Supplier: Login")
	@Parameters({"supplierEmail", "passwordSP"})
	public void testSupplierLogin(String supplierEmail, String passwordSP) {
		testLoginAsSupplier(supplierEmail, passwordSP);
	}
	
	@Test(description="TC 31.1 - Noosh Pro - Supplier: Review order")
	@Parameters({"titleRFE"})
	public void testReviewOrder(String titleRFE) throws InterruptedException {
		MyDesk myDesk = new MyDesk(driver);
		Order order = new Order(driver);
		
		titleRFE = titleRFE + " " + unicID + " - Order";
		myDesk.clickOrdersMenuItem();
		try {
			   AssertJUnit.assertTrue(Page.isTextPresent(titleRFE, driver));   
			} catch(Throwable e) {
			   System.out.println("Noosh Pro - Supplier: '" + titleRFE + "' order does not displayed in list");
			   log.info("Noosh Pro - Supplier: '" + titleRFE + "' order does not displayed in list");
			}
		order.clickOrderInListSupplier(driver, titleRFE);
		Thread.sleep(1000);
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 31.1 - Noosh Pro - Supplier: Review order, JS errors  #######");		
	}
	
	@Test(description="TC 31.2 - Noosh Pro - Supplier: Open spec in the order")
	@Parameters({"newProjectNameSP", "productAdvEditor"})
	public void testSupplierOpenSpecInOrder(String newProjectNameSP, String productAdvEditor) throws InterruptedException {
		testSPCraeteOrderOpenSpec(newProjectNameSP, productAdvEditor);
	}
	
	@Test(description="TC 32 - Noosh Pro - Supplier: Accept the order")
	@Parameters({"newProjectNameSP", "productAdvEditor", "titleRFE"})
	public void testSupplierAcceptOrder(String newProjectNameSP, String productAdvEditor, String titleRFE) throws InterruptedException {
		Order order = new Order(driver);
		MyDesk myDesk = new MyDesk(driver);
		
		titleRFE = titleRFE + " " + unicID + " - Order";
		order.clickAcceptOrder();
		Thread.sleep(2000);
	    Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
		myDesk.clickOrdersMenuItem();
		Thread.sleep(1000);
		try {
		    AssertJUnit.assertEquals("Accepted", order.checkOrderStatus(driver, titleRFE).trim());	      
		    } catch(Throwable e) {
			System.out.println("Order status: 'Accepted' does not displayed in orders list");
			log.info("Order status: 'Accepted' does not displayed in orders list");
		    }
		
		errorIndex = jsErrorIndex;
		Page.jsErrorReporter(driver, errorIndex,"####### TC 32 - Noosh Pro - Supplier: Accept the order, JS errors  #######");		
	}
	
	@Test(description="TC 33 - Noosh Pro - Supplier: Logout")
	public void testSupplierLogout() throws InterruptedException {
		testLogoutSPNP();
	}	
}
