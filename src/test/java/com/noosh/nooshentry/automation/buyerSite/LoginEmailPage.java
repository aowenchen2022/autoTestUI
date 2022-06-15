package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class LoginEmailPage extends Page
{
   public LoginEmailPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
   
   @FindBy(id="Email")
   private WebElement userName;
      
   @FindBy(id="Passwd")
   private WebElement password;
   
   @FindBy(id="signIn")
   private WebElement submitBT;
   
   @FindBy(xpath="loginEmailPage.searchField")
   private WebElement searchField;
   
   @FindBy(id="gs_taif0")
   private WebElement searchFieldInput;
   
   @FindBy(xpath="loginEmailPage.searchBT")
   private WebElement submitSearch;
   
   @FindBy(id="gs_taif0")
   private WebElement secondSearch;
   
   @FindBy(css="div.au div.av")
   private WebElement invitationLink;
   
   @FindBy(linkText="Register")
   private WebElement registerLink;
   
   @FindBy(xpath="loginEmailPage.profileName")
   private WebElement profileName;
   
   @FindBy(className="gbmai")
   private WebElement arroyToBottom;
   
   @FindBy(xpath="loginEmailPage.spProfileLink")
   private WebElement spEmailName;
   
   @FindBy(id="gbg4")
   private WebElement userImage;
   
   @FindBy(xpath="loginEmailPage.logout")
   private WebElement signOut;
   
   @FindBy(name="Noosh Notification")
   private WebElement customerSiteNotification;  
   
   @FindBy(xpath="loginEmailPage.fileNotification")
   public WebElement fileNotification;
   
   @FindBy(linkText="click here")
   public WebElement fileLink;
   
   @FindBy(xpath="loginEmailPage.back")
   public WebElement backBT;
   
   @FindBy(xpath="loginEmailPage.resetPasswordLink")
   public WebElement resetPasswordLink;
   
   public void loginUser (String name, String userPasswd){
      userName.clear();
      userName.sendKeys(name);
      password.clear();
      password.sendKeys(userPasswd);
      submitBT.click();
   }   
   public void searchInvitationLetter(String invitationTitle) throws InterruptedException{
      searchField.sendKeys(invitationTitle);
	  Thread.sleep(1000);		  
//	  searchField.sendKeys(Keys.RETURN);
      submitSearch.click();
   } 
   public void clearSearchInvitationLetter() {
	   searchField.clear();
   }
   public void searchInvitationLetter33(String invitationTitle) throws InterruptedException{
	      searchField.sendKeys(invitationTitle);
		  Thread.sleep(1000);		  
//		  secondSearch.sendKeys(Keys.RETURN);
//	      submitSearch.click();
	   } 
   public void getInvitationLetter(){
//	   driver.findElementByXPath("loginEmailPage.invitationLink", "0", message).click();     
      invitationLink.click();
   }   
   
   public void getRegisterBuyerPage(){
      registerLink.click();
   }
   
   public void logoutBuyerEmail1() throws InterruptedException {
	  profileName.click(); 
	  Thread.sleep(500);
	  signOut.click();
   }
   
   public void logoutSPEmail()  throws InterruptedException {
	   spEmailName.click();
	   Thread.sleep(500);
	   signOut.click();
   }
   
   public void logoutGmail() throws InterruptedException{
	   userImage.click();
	   Thread.sleep(500);
	   signOut.click();
   }
   
   public void logoutBuyerEmail(String userName) throws InterruptedException {
	  WebElement profileName = driver.findElement(By.xpath("//span[text()='" +
				   userName + "']")); 
	  profileName.click(); 
	  Thread.sleep(500);
	  signOut.click();
   }   
   
   public void clickCustomerSiteBT(){
	   customerSiteNotification.click();
   }
   
   public void clickSPFileNotification() {
	   fileNotification.click();
   }
   
   public void clickFileLink() {
	   fileLink.click();
   }
   
   public void getBackSearch() {
	   backBT.click();
   }
   
   public void putTextInSearshEmail(String text) {
	   searchFieldInput.sendKeys(text);
   }
   
   public void getResetPasswordLink(NooshWebDriver driver, String text) {
	   driver.findElementByXPath("loginEmailPage.resetPasswordLink", "0", text).click();	   	   
   }
}
