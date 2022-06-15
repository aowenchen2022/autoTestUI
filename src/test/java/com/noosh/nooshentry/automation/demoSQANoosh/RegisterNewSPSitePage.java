package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.JavascriptExecutor;

public class RegisterNewSPSitePage extends Page
{
   
	public RegisterNewSPSitePage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }     
   
   private WebElement firstName;   //10/2
   private WebElement lastName;    //10/2
   private WebElement fullName;      //10/2
   
   private WebElement emailAddress;   
   private WebElement password;   
   private WebElement phoneNumber;   
   private WebElement company;  
   private WebElement landingCreateProject;
   private WebElement landingCreatePage;
   
   //@FindBy(xpath="registerNewSPSitePage.signup")
   @FindBy(id="submitButtonWrapper")
   private WebElement submitButtonWrapper;
    
   //@FindBy(xpath="registerNewSPSitePage.agreement")
   @FindBy(id="signupTermsTrigger")
   private WebElement agreement;
   
   @FindBy(id="okayTos")
   private WebElement acceptBT;
   
   private WebElement sliderButton;
               
   @FindBy(id="glbLandingLaunchStart")
   private WebElement letsBeginBT;
   
   @FindBy(id="landingCreatePage")
   private WebElement createClientWebpage;
   
   @FindBy(id="landingCreateProject")
   private WebElement createProjectForClient;
   
   @FindBy(linkText="New Home Page")
   private WebElement newHomePage;
   
   private WebElement headProfileImage;
   
   @FindBy(xpath="registerNewSPSitePage.profileArrow")
   private WebElement profileArrow;
   
  // @FindBy(xpath="registerNewSPSitePage.logout")  
  // @FindBy(xpath="/html/body/div[5]/div/div/div[2]/div[2]/ul/li[3]/ul/li[2]/a/div")
  // @FindBy(linkText="Logout")
   @FindBy(id="topMenuLogout")    
   private WebElement logout;
    
   public void spCredential(String fullNameSP, String emailSP, String passwordSP, String phoneNumberSP, 
		       String companyName) throws InterruptedException {      
	   //type(firstName, firstNameSP); //10/2
	  // type(lastName, lastNameSP);   //10/2
	   type(fullName, fullNameSP);     
	   Thread.sleep(800);
	   type(emailAddress, emailSP);
	   Thread.sleep(800);
	   type(password, passwordSP);
	   Thread.sleep(800);
	   type(phoneNumber, phoneNumberSP);
	   Thread.sleep(800);
	   type(company, companyName);
	   Thread.sleep(1000);
   }
   
   public void getAgreementPopup() throws InterruptedException {
       agreement.click();
       Thread.sleep(1000);
   }
   
   public void acceptAgreement() throws InterruptedException {
       acceptBT.click();
       Thread.sleep(1000);
   }
   
   public void moveSlider() {
       new Actions(driver).dragAndDropBy(sliderButton, 180, 0).build().perform();
   }   
   
   public void clickLetsBegin() {
	   letsBeginBT.click();
   }
   public void clickLetsBeginIE() {
	   letsBeginBT.click();
   }
   
   public String getButtonText() {
	   return letsBeginBT.getText();
   }
   
   public void clickCreateClientPage() {
	   createClientWebpage.click();
   }
   
   public void clickNewHomePage() {
	   newHomePage.click();
   }
   
   public void logoutSPSite() throws InterruptedException {
       //clickSubMenuItem(headProfileImage, logout);    //10/1
	   Thread.sleep(10000);
	 //	String currentURL = driver.getCurrentUrl();
	 //   int index = currentURL.indexOf("/service");
	//	String logoutURL = currentURL.substring(0, index) + "/service/j_spring_security_logout";
	//	driver.get(logoutURL);
	   ((JavascriptExecutor) driver).executeScript("window.location='/service/j_spring_security_logout'");
		Thread.sleep(2000);  
   }
   
   
   public void logoutSPsiteIE() throws InterruptedException {
	   profileArrow.click();
	   Thread.sleep(4000);
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
   }
   
   public void clickCreateProject() {
	   landingCreateProject.click();
   }
   
   public void clickSignupBT() throws InterruptedException {
	   submitButtonWrapper.findElement(By.tagName("input")).click();
	   Thread.sleep(2000); 
   }
   
   public void clickCreateWorkspace() {
	   landingCreatePage.click();
   }
}
