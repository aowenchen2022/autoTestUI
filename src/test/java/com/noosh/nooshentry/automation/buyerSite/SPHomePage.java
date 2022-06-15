package com.noosh.nooshentry.automation.buyerSite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.noosh.nooshentry.automation.demoSQANoosh.CommonUtils;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class SPHomePage extends Page {
	public SPHomePage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	} 
	
	@FindBy(id="glbTopCreateProject")
	private WebElement glbCreateProject;
	
	@FindBy(id="homeSwitcher")
	private WebElement homeSwitcherIcon;
	
	@FindBy(id="homeProjectListView")
	private WebElement projectListView;
	
	private WebElement homeSiteListView;
	private WebElement dropdownMenu1;
	private WebElement confirmPopupWindow;
	private WebElement homeProjectListView;
	
	public void clickGlbCreateProj() throws InterruptedException {
		if(glbCreateProject.isDisplayed()) {
		    glbCreateProject.click();
		} else {
			System.out.println("'Create Project' is missing");
		}
		Thread.sleep(2500);
	}
	
	//10/2/2014 temp comment out due to it doesn't work. will work back
	public void switchToClientView() throws InterruptedException {
		Actions builder = new Actions(driver);
		//WebElement clientSwitch = homeSwitcherIcon.findElement(By.className("home-switcher-switch-icon"));		
		WebElement clientSwitch = homeSwitcherIcon.findElement(By.className("ui-icon-viewChange-default-iconOnly"));
		clientSwitch.click();
		Thread.sleep(1000);	
		builder.moveToElement(clientSwitch).perform();
		Thread.sleep(5000);
		driver.findElement(By.className("ui-icon-viewChange-default")).click();	
		Thread.sleep(4000);
	}
	
	//10/2/2014 temp comment out due to it doesn't work. will work back
	public void switchToProjectsView() throws InterruptedException {
		/*
		Actions builder = new Actions(driver);
		WebElement switchMenu = driver.findElement(By.className("home-switcher-toggle-wrapper"));
		WebElement clientSwitch = switchMenu.findElement(By.id("dropdownMenu1"));
		clientSwitch.click();
		Thread.sleep(1000);		
		builder.moveToElement(clientSwitch).perform();
		Thread.sleep(5000);
		//driver.findElement(By.className("ui-icon-viewChange-project")).click();
		driver.findElement(By.id("homeListSwitch")).click();
		Thread.sleep(4000);
		*/
		WebElement switchMenu = driver.findElement(By.className("home-switcher-toggle-wrapper"));
		List<WebElement> switcher = switchMenu.findElements(By.className("homeListSwitch"));
		Actions builder = new Actions(driver);
		builder.click(switcher.get(0));
		Thread.sleep(1000);
		builder.click(switcher.get(1));
		//switcher.get(0).click();
		//Thread.sleep(1000);
		//switcher.get(1).click();
		Action switchToProjectsView = builder.build();
		switchToProjectsView.perform();
		Thread.sleep(4000);
	}
	
	
	public void clickHomeCreateProj() {
		homeSiteListView.findElement(By.className("create-pro-btn")).click();
	}
	
	public void createQuoteBtn() throws InterruptedException {
		driver.findElement(By.className("selling-status")).findElement(By.tagName("input")).click();
		Thread.sleep(3000);
		//WebElement allProject = projectListView.findElement(By.className("home-project-list-ul")).findElement(By.className("procurement-quote"));   //5/21 comment out 	
		/*
	    WebElement allProject = projectListView.findElement(By.className("procurement-quote"));
	    List<WebElement> projects = allProject.findElements(By.tagName("a"));
		for (WebElement project : projects) {
			 if (project != null) {
			 	 String projectEst = project.getAttribute("title");
			 	 if ((projectEst != null) && !projectEst.isEmpty()) {
			 	  	 if (projectEst.equals("Create Quote")) {
			 	  		 project.click();
			 			 Thread.sleep(2500);
			 			 break;
			 	   	 }			     	
			     }
			 }
		 } */
	}
	
	public void verifyCreateQuoteBtn(String btn) throws InterruptedException {
		String createQuote = driver.findElement(By.className("selling-status")).findElement(By.tagName("input")).getAttribute("value");
		Thread.sleep(1000);
		Assert.assertEquals(createQuote, btn);
		Thread.sleep(500);
	}
	
	public void verifyCreateQuoteBtns() throws InterruptedException {
		WebElement createQuote = driver.findElement(By.className("selling-status")).findElement(By.tagName("input"));
		String createQ = createQuote.getAttribute("value");
		System.out.println("createQ="+createQ);
		Thread.sleep(2000);
		Assert.assertEquals(createQ, "Create Quote");
		WebElement createQuoteGlb = driver.findElement(By.className("project-line-button-set")).findElement(By.tagName("a"));
		String createQglb = createQuoteGlb.getText();		
		Thread.sleep(2000);
		Assert.assertEquals(createQglb, "Create Quote");
	}
	
	public void createQuoteBtn1() {
		homeSiteListView.findElement(By.className("home-project-list-ul")).findElement(By.linkText("Create Quote")).click();
	}
	
	public void goToSellingTab() throws InterruptedException {
		Thread.sleep(7000);
    	WebElement editProj = homeSiteListView.findElement(By.className("home-project-list-ul"));
    	Thread.sleep(500);
 	    editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElement(By.linkText("Selling")).click(); 
 	    Thread.sleep(2000);
	}
	
	public void goToBuyingTab() throws InterruptedException {
		Thread.sleep(7000);
    	WebElement editProj = homeProjectListView.findElement(By.className("home-project-list-ul"));
    	Thread.sleep(500);
 	    List<WebElement> as = editProj.findElement(By.className("project-detail-container")).findElement(By.className("ui-tabs-nav")).findElements(By.tagName("a"));
    	as.get(3).click();
 	    Thread.sleep(2000);
	}		
	
	public void clickCopy() throws InterruptedException {
		Actions builder = new Actions(driver);
		Thread.sleep(1000);		
		WebElement projicon = homeSiteListView.findElement(By.className("line-message"));
		List<WebElement> divs = projicon.findElements(By.tagName("div"));
		WebElement div = divs.get(2);
		div.click();
		builder.moveToElement(div).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.className("glb-project-settings-sp-copy")).click();
		Thread.sleep(8000);		
	 }
	
	 public void verifyCopyProjInfo(String projectName) {
		 WebElement info = homeSiteListView.findElement(By.className("proj-view-fieldset")).findElement(By.tagName("span"));
		 String projName = info.getText();
		 int index = projName.lastIndexOf(" ");
		 projName = projName.substring(index + 1);
		 Assert.assertEquals(projName, projectName);
	 }
	 
	 public void verifyCopyClientProjInfo(String projectName) {
		 WebElement info = homeSiteListView.findElement(By.className("proj-view-fieldset")).findElement(By.tagName("span"));
		 String projName = info.getText();
		 System.out.println("projName = " + projName);
		 System.out.println("project Name = " + projectName);
		 Assert.assertEquals(projName, projectName);
	 }
	 
	 public void verifyUploadedImgSPHome(String fileName) {
		 int index = fileName.lastIndexOf("/");
		 String imgTitle = fileName.substring(index + 1);
		 System.out.println("imag title = " + imgTitle);
		 String imgName = driver.findElement(By.className("uploaded-file-name")).getAttribute("title");
		 System.out.println("imag name = " + imgName);
		 Assert.assertEquals(imgName, imgTitle);
	 }
	 
	 public void verifySFsSPHome(String s1, String s2) throws InterruptedException {
		 List<WebElement> sfs = driver.findElements(By.className("job-jobSpecs-div-li"));
		 String sf1 = sfs.get(0).findElement(By.tagName("b")).getText();		
		 Assert.assertEquals(sf1, s1);
		 Thread.sleep(1000);
		 String sf2 = sfs.get(1).findElement(By.tagName("b")).getText();
		 Assert.assertEquals(sf2, s2);
		 Thread.sleep(1000);
	 }
	 
	 public void clickAddSF() {
		 homeSiteListView.findElement(By.className("project-detail-container")).findElement(By.className("add-spec-btn")).click();
	 }
	 
	 public void verifyDeleteSPHome(String d) throws InterruptedException {
		 WebElement sf = driver.findElement(By.className("job-jobSpecs-div-li"));
		 String del = sf.findElement(By.tagName("a")).getText();
		 Assert.assertEquals(del, d);
		 Thread.sleep(1000);
	 }
	 
	 public void deleteSF() throws InterruptedException {
		 WebElement sf = driver.findElement(By.className("job-jobSpecs-div-li"));
		 sf.findElement(By.tagName("a")).click();
		 Thread.sleep(2000);
		 Alert alert = driver.switchTo().alert();
		 alert.accept();
		 Thread.sleep(2000);
	 }
	 
	 public void verifyEventMsg(String delMsg) throws InterruptedException {
		 WebElement eve = driver.findElement(By.className("project-detail-container")).findElement(By.tagName("table")).findElement(By.tagName("tbody"));
		 String delEve = eve.findElement(By.tagName("td")).getText();
		 Assert.assertEquals(delEve, delMsg);
		 Thread.sleep(3000);
	 }
	 
	 public void verifyRetractQuote() throws InterruptedException {
		 Actions builder = new Actions(driver);
		 WebElement gear = driver.findElement(By.className("glb-settings-icon"));
		 if(gear.isDisplayed()) {
		     gear.click();
		     Thread.sleep(1500);
		 } else {
			 System.out.println("Gear is missing!\n");
		 }
		 builder.moveToElement(gear).build().perform();
		 Thread.sleep(1000);
		 driver.findElement(By.className("retract")).click();
		 Thread.sleep(1000);
	 }
	 
	 public void verifyRetractQuotePopup(String fn) throws InterruptedException, IOException {
		 try {
			 //WebDriverWait wait = new WebDriverWait(driver, 2);
			// wait.until(ExpectedConditions.alertIsPresent());
			// Alert alt = driver.switchTo().alert();
			// alt.accept();
			 driver.findElement(By.className("ui-dialog-buttonpane")).findElement(By.className("confirm-button")).click();
			 Thread.sleep(5000);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(fn);
		 }
	 }
	 
	 public void verifyRetractStatus(String status, String quotestatusfn) throws InterruptedException, IOException {
		 try {
		     String retracted = driver.findElement(By.className("quote-list")).findElement(By.tagName("div")).getText();
		     Assert.assertEquals(retracted, status);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(quotestatusfn);
		 }
		 Thread.sleep(1000);
	 }
	 
	 public void verifyRecallRFE() throws InterruptedException {
		/*
		 Actions builder = new Actions(driver);
		 WebElement gear = driver.findElement(By.className("glb-settings-icon"));
		 if(gear.isDisplayed()) {
		     gear.click();
		     Thread.sleep(2000);
		 } else {
			 System.out.println("Gear is missing!\n");
		 }
		 builder.moveToElement(gear).build().perform();
		 Thread.sleep(1000);
		 driver.findElement(By.className("cancel-order")).click();
		 */
		 driver.findElement(By.className("detail-cancel-order")).click();
		 Thread.sleep(2000);
	 }
	 
	 public void verifyRecallRFE1() throws InterruptedException {
		 /*	 
		    Actions builder = new Actions(driver);
			 WebElement gear = driver.findElement(By.className("glb-settings-icon"));
			 if(gear.isDisplayed()) {
			     gear.click();
			     Thread.sleep(2000);
			 } else {
				 System.out.println("Gear is missing!\n");
			 }
			 builder.moveToElement(gear).build().perform();
			 Thread.sleep(1000);
			 driver.findElement(By.className("cancel-order")).click();
			 //driver.findElement(By.className("detail-cancel-order")).click();
			 Thread.sleep(2000);
		  */
		 driver.findElement(By.className("detail-recall-rfe")).click();
		 Thread.sleep(2000);
		 }
	 
	 public void verifyCancelOrder() throws InterruptedException {
		 Actions builder = new Actions(driver);
		 //WebElement gear = driver.findElement(By.className("glb-settings-icon"));
		 WebElement gear = driver.findElement(By.className("odd"));
		 if(gear.isDisplayed()) {
		     gear.click();
		     Thread.sleep(1500);
		 } else {
			 System.out.println("Gear is missing!\n");
		 }
		 builder.moveToElement(gear).build().perform();
		 Thread.sleep(1000);
		 driver.findElement(By.className("more-cancel-order")).click();
	 }
	 
	 public void recallRFEpopup() throws InterruptedException {
		 WebElement textarea = confirmPopupWindow.findElement(By.tagName("textarea"));
		 textarea.clear();
		 textarea.sendKeys("I don't like it.");
		 Thread.sleep(1000);
		 driver.findElement(By.className("ui-dialog-buttonpane")).findElement(By.className("confirm-button")).click();
	 }
	 
	 public void cancelOrderpopup() throws InterruptedException {
		 try {
		     driver.findElement(By.className("ui-dialog-buttonpane")).findElement(By.className("confirm-button")).click();
		     Thread.sleep(2000);
		 } catch(Exception e) {
			 System.out.println("Confirm button is missing.");
		 }
	 }
	 
	 public void verifyStatusBeforeRecall(String status, String rfestatusfn) throws IOException {
		 try {
			 String open = driver.findElement(By.className("rfe-status")).getText();
			 Assert.assertEquals(open, status);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(rfestatusfn);
		 }
	 }
	 
	 public void verifyStatus(String status, String rfestatusfn) throws IOException {
		 try {
			 String statusResult = driver.findElement(By.className("rfe-status")).getText();
			 Assert.assertEquals(statusResult, status);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(rfestatusfn);
		 }
	 }
	 
	 public void verifyClient(String client) throws InterruptedException {
		 driver.findElement(By.className("manageUsers")).click();
		 Thread.sleep(2000);
		 List<WebElement> lis = driver.findElement(By.className("client-user-list")).findElements(By.tagName("li"));
		 List<WebElement> divs = lis.get(1).findElements(By.tagName("div"));
		 String user = divs.get(4).getText();
		 Assert.assertEquals(user, client);
		 Thread.sleep(2000);
	 }
	 
	 public void verifyCoworker(String coworker) throws InterruptedException {
		 Thread.sleep(5000);
		 List<WebElement> lis = driver.findElement(By.className("coworker-list")).findElements(By.tagName("li"));
		 List<WebElement> divs = lis.get(2).findElements(By.tagName("div"));
		 String cw = divs.get(4).getText();
		 Assert.assertEquals(cw, coworker);
		 Thread.sleep(2000);
	 }
	 
	 public void verifyBuyingCancellStatus(String status, String rfestatusfn) throws IOException {
		 try {
			 String statusResult = driver.findElement(By.className("buying-order")).getText();
			 Assert.assertEquals(statusResult, status);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(rfestatusfn);
		 }
	 }
	 
	 public void verifySPStatus(String specOrderStatus, String orderStatus, String quoteStatus, String spStatusfn) throws InterruptedException, IOException {
		 try {
			 String specS = driver.findElement(By.className("selling-status")).findElement(By.className("spec-status")).getText();
			 Assert.assertEquals(specS, specOrderStatus);
			 Thread.sleep(1000);
			 String orderS = driver.findElement(By.className("selling-order")).getText();
			 Assert.assertEquals(orderS, orderStatus);
			 Thread.sleep(1000);
			 String quoteS = driver.findElement(By.className("quote-list")).findElement(By.className("ellipsis")).getText();
			 Assert.assertEquals(quoteS, quoteStatus);
			 Thread.sleep(1000);
		 } catch(Exception e) {
			 CommonUtils commonutils = new CommonUtils(driver);
			 commonutils.getScreenShot(spStatusfn);
		 }		 
	 }
	 
	 public void viewDetails() throws InterruptedException {
		 //driver.findElement(By.className("show-detail")).click();
		 //Thread.sleep(2000);
		 String status = driver.findElement(By.className("status-display-name")).getText();
		 Assert.assertEquals(status, "Order Accepted");
		 Thread.sleep(1500);
	 }
}

