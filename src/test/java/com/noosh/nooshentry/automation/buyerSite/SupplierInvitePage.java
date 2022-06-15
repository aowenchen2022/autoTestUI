package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class SupplierInvitePage extends Page {
	 public SupplierInvitePage(WebDriver driver) {
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
	 
	 private WebElement glbTopInviteButton;
	 
	 public void clickGlbInvite() {
		 glbTopInviteButton.click();
	 }
	 
	 public void sendSupplierInvitation() {
		 WebElement send = driver.findElement(By.className("manage-suppliers"));
		 send.findElement(By.className("invite-people-button")).click();
	 }

	 public void inputSupplier(String supplier) {
		 WebElement supplierUser = driver.findElement(By.className("manage-suppliers"));
		 supplierUser.click();
		 supplierUser.findElement(By.id("token-input-"));
		 supplierUser.clear();
		 supplierUser.sendKeys(supplier);
		 supplierUser.sendKeys(",");
	 }
	 
	 public void clickSupplierTab() {		 
		 WebElement supplierTab = driver.findElement(By.className("manageSuppliers"));
		 if(!supplierTab.isDisplayed()) {
		   		throw new IllegalStateException("The Suppliers tab is missing!");
		 } else {
			 supplierTab.click();
		 }
	 }
}
