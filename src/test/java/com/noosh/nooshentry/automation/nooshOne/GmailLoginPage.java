package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends Page {
	public GmailLoginPage(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   }
	
	@FindBy(id="identifierId")
	private WebElement email;
	
	@FindBy(name="password")
	private WebElement passwd;
	
	@FindBy(id="passwordNext")
	private WebElement signInBtn;
	
	@FindBy(xpath="GmailLoginPage.mouseup")
	private WebElement mouseup;
	
	
	public void enterEmail(String emailAddress) {
		email.clear();
		email.sendKeys(emailAddress);
	}
	
	public void enterPassword(String emailPasswd) throws InterruptedException {
		try {
		    WebElement next = driver.findElement(By.id("identifierNext"));
		    next.click();
			Thread.sleep(1500);
		} catch (NoSuchElementException ne) {
		}
		passwd.clear();
		passwd.sendKeys(emailPasswd);
	}
	
	public void signIn() {
		signInBtn.click();
	}
	
	public void accessGmail() throws InterruptedException {
	    Thread.sleep(3000);
	    driver.get("http://www.gmail.com");
	    Thread.sleep(2000);
	    WebElement gmailSignIn = null;
		try {
		    gmailSignIn = driver.findElement(By.id("gmail-sign-in"));    //10/24
		} catch (Exception e) {
			  // do nothing
		}
		if (gmailSignIn != null) {
			gmailSignIn.click();
			Thread.sleep(2000);
	    } 
	}
	
	public void chooseGmailAccount() throws InterruptedException {
		WebElement accountChooserLink = null;
		try {
		    accountChooserLink = driver.findElement(By.id("account-chooser-link"));
		} catch (Exception e) {
			  // do nothing
		}
		if (accountChooserLink != null) {
		    accountChooserLink.click();
			Thread.sleep(2000);
		  }
		WebElement addAccountLink = null;
		try {
		    addAccountLink = driver.findElement(By.id("account-chooser-add-account"));
		} catch (Exception e) {
			  // do nothing
		}
		if (addAccountLink != null) {
			addAccountLink.click();
			Thread.sleep(2000);
		}
	}
	
	public void selectGmailAccountTwo() throws InterruptedException {
		WebElement accountTwo = null;
		try {
			accountTwo = driver.findElement(By.id("choose-account-1"));
		} catch (Exception e) {
			  // do nothing
		}
		if (accountTwo != null) {
		    accountTwo.click();
			Thread.sleep(2000);
		}	
	}
	
	public void check() throws InterruptedException {
		WebElement staySignInCookie = null;
	    try {
	        staySignInCookie = driver.findElement(By.id("PersistentCookie"));
	    } catch (Exception e) {
		    //do nothing
	    }
	    if (staySignInCookie != null) {
		    staySignInCookie.click();
	        Thread.sleep(1000);
	    }
	}
	
	public void selectGmailAccountOne() throws InterruptedException {
		WebElement accountOne = null;
		try {
			accountOne = driver.findElement(By.id("choose-account-0"));
		} catch (Exception e) {
			  // do nothing
		}
		if (accountOne != null) {
			accountOne.click();
			Thread.sleep(2000);
		}
	}
}
