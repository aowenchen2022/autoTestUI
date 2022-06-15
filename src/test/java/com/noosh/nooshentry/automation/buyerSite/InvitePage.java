package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class InvitePage extends Page {
	 public InvitePage(WebDriver driver) {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }

	 private WebElement glbInviteClientUsersTextArea;
	 private WebElement glbInviteCoworkersTextArea;
	 private WebElement glbInviteSuppliersTextArea;
	 
	 @FindBy(id="glbInviteClientUsers")
	 private WebElement glbInviteClientUsersTab;
	 
	 @FindBy(id="glbInviteClientUsersButton")
	 private WebElement glbInviteClientUsersBtn;
	 
	 @FindBy(id="clientUserToSite")
	 private WebElement glbClientUserToSite;
 
	 @FindBy(id="glbInviteCoworkers")
	 private WebElement glbInviteCoworkersTab;
	 
	 @FindBy(id="glbInviteCoworkersButton")
     private WebElement glbInviteCoworkersBtn;
	 
	 @FindBy(id="glbInviteSuppliers")
	 private WebElement glbInviteSuppliersTab;
	 
	 @FindBy(id="glbInviteSuppliersButton")
	 private WebElement glbInviteSuppliersBtn;
	 
	 private WebElement glbTopInviteButton2;
	 
	 public void clickGlbInvite() {
		 glbTopInviteButton2.click();
	 }
	 
	 private WebElement clientUserToSite;
	 
	 public void sendClientInvitation() {
		 WebElement send = driver.findElement(By.className("manage-client-users"));
		 send.findElement(By.className("invite-people-button")).click();
	 }
	 
	 public void sendSupplierInvitation() {
		 WebElement send = driver.findElement(By.className("manage-suppliers"));
		 send.findElement(By.className("invite-people-button")).click();
	 }
	 
	 public void selectWS(String clientUser) {
		 Select clickThis = new Select(clientUserToSite);
		 clickThis.selectByVisibleText(clientUser);
	 }
	 
	 //constructing... 1/17
	 public void selectClient(String client) {
		 
	 }
	 
	 public void inputSupplier(String supplier) throws InterruptedException {
		 WebElement client = driver.findElement(By.id("rfe-autoComplete-token-input"));   
		 client.clear();
		 client.sendKeys(supplier);
		 Thread.sleep(1000);
		 client.sendKeys(",");
		 Thread.sleep(2000);
	 }
	 
	 public void inputClient1(String clientUser) throws InterruptedException {
		 WebElement client = driver.findElement(By.id("client-user-token-input"));    
		 client.clear();
		 client.sendKeys(clientUser);
		 Thread.sleep(1000);
		 client.sendKeys(",");
	 }
	
	 public void clickClientUsersTab() {
		 driver.findElement(By.className("glb-client-view-users-menu-active")).click();
	 }
	 
	 public void clickSupplierTab() {		 
		 WebElement supplierTab = driver.findElement(By.className("manageSuppliers"));
		 if(!supplierTab.isDisplayed()) {
		   		throw new IllegalStateException("The Suppliers tab is missing!");
		 } else {
			 supplierTab.click();
		 }
	 }
	 
	 public void enterClientEmail(String emailAddress) {
    	 glbInviteClientUsersTextArea.clear();
    	 glbInviteClientUsersTextArea.sendKeys(emailAddress);
     }
	
	 //constructing....
	 public void selectWS() {
		// clientUserToSite
	 }
	 
	 public void clickClientInvitation() {
		 glbInviteClientUsersBtn.click();
	 }
 
}
