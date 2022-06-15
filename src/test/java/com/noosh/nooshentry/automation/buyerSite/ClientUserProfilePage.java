package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ClientUserProfilePage extends Page {
	public ClientUserProfilePage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	} 
	@FindBy(id="originalPassword")
	private WebElement originalPasswordField;
	
	@FindBy(id="passwordOnce")
	private WebElement passwordOnceField;
	
	@FindBy(id="passwordTwice")
	private WebElement passwordTwiceField;
	
	@FindBy(id="changePasswordSubmit")
	private WebElement changePasswordSubmitBtn;
	
	@FindBy(id="profileUpdate-close")
	private WebElement clientProfileCloseBtn;
	
	@FindBy(id="changePasswordCancel")
	private WebElement clientChangePasswordCancelBtn;
	
	public void clickResetPassword() {
		driver.findElement(By.id("changePasswordBtn")).click();
	}

	public void cancelChangePassword() {
		clientChangePasswordCancelBtn.click();
	}
	
	public void inputCurrentPasswordField(String currentPW) {
		originalPasswordField.clear();
		originalPasswordField.sendKeys(currentPW);
	}
	
	public void inputNewPasswordField(String newPW) {
		passwordOnceField.clear();
		passwordOnceField.sendKeys(newPW);
	}
	
	public void inputConfirmPasswordField(String confirmPW) {		
		passwordTwiceField.clear();
		passwordTwiceField.sendKeys(confirmPW);
	}
	
	public void clickChangePasswordBtn() {
		changePasswordSubmitBtn.click();
	}
	
	public void clickProfileCloseBtn() {
		clientProfileCloseBtn.click();
	}
}
