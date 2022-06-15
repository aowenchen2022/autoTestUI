package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SendFile extends Page {

   public SendFile(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
   
   @FindBy(xpath="sendEmail.emailLink")
   private WebElement emailLink;
   
   private WebElement username;
   
   private WebElement passwd;
   
   @FindBy(id=".save")
   private WebElement submit;
   
   @FindBy(linkText="Compose")
   private WebElement compose;
   
   @FindBy(id="to-field")
   private WebElement emailUser;
   
   @FindBy(id="subject-field")
   private WebElement subject;
   
   @FindBy(id=":5f")
   private WebElement bodyEmail;
   
   @FindBy(id="attachment-button-input")
   private WebElement attach;
   
   @FindBy(linkText="Send")
   private WebElement sendEmail;
   
   public void clickEmailLink()
   {
	   emailLink.click();
   }   
   public void loginUser(String userName, String userPass)   
   {
	   username.clear();
	   username.sendKeys(userName);
	   passwd.clear();
	   passwd.sendKeys(userPass);
	   submit.click();
   }   
   public void clickCompose()
   {
	   compose.click();
   }  
   public void putEmailUser(String userEmail)
   {
	   emailUser.click();
	   emailUser.sendKeys(userEmail);
   }   
   public void putSUbject(String userSubject)
   {
	   subject.click();
	   subject.sendKeys(userSubject);
   }    
   public void putBody(String bodyMess)
   {
	   bodyEmail.click();
	   bodyEmail.sendKeys(bodyMess);
   }  
   public void clickAttach()
   {
	   attach.click();
   }   
   public void sendEmail()
   {
	   sendEmail.click();
   }      
}
