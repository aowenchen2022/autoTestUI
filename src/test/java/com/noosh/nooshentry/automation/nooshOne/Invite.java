package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Invite extends Page
{
	   public Invite(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   }
	   
	   @FindBy(xpath="invite.coworkersTab")
	   private WebElement inviteCoworkerTab;
	   
	   @FindBy(xpath="invite.customerTab")
	   private WebElement inviteCustomerTab;
	   
	   @FindBy(xpath="invite.selectSite")
	   private WebElement selectSite;
	   
	   @FindBy(xpath="invite.firstSiteName")
	   private WebElement firstSiteName;
	   
	   @FindBy(xpath="invite.firstInvited")
	   private WebElement firstInvited;
	   
	   @FindBy(xpath="invite.secondInvited")
	   private WebElement secondInvited;
	   
	   private WebElement inviteCoworkerEmails;
	   private WebElement inviteCustomerEmails;
	   private WebElement inviteCancel;
	   private WebElement submitGlbCustomerInvitation;
	   private WebElement submitGlbCoworkerInvitation;	
	   private WebElement inviteCustomerEmails4Site;
	   private WebElement submitGlbCustomerInvitation4Site;
	   private WebElement inviteCancel4Site;
	   private WebElement inviteCustomerResult4Site;
	   private WebElement glbCustomerInviteInputError4Site;
	   
	   @FindBy(className="buyer-email")
	   private WebElement buyerEmailInList;
	   
	   @FindBy(linkText="Team")
	   private WebElement teamLink;
	   
	   public void clickInviteCoworkerTab()
	   {
		   inviteCoworkerTab.click();
	   }
	   public void putCoworkerEmail(String coworkerEmail)
	   {
		   type(inviteCoworkerEmails, coworkerEmail);
	   }
	   public void clickSendInvitationBT()
	   {
		   submitGlbCoworkerInvitation.click();
	   }
       public void putCustomersEmail(String customerEmails)
       {
		   type(inviteCustomerEmails, customerEmails);
       }
       public void selectFirstSite() throws InterruptedException
       {
    	   firstSiteName.click();
       }
       public void clickCustomerSend()
       {
    	   submitGlbCustomerInvitation.click();
       }
       public void selectSite(String siteName)
       {
    	   selectSite.sendKeys(siteName);
       }
       public void clickCloseLink()
       {
    	   inviteCancel.click();
       }
       public void clickInviteCustomerTab()
       {
    	   inviteCustomerTab.click();
       }
       public void putClientEmail(String email) {
    	   type(inviteCustomerEmails4Site, email);
 //   	   inviteCustomerEmails4Site.sendKeys(email);
       }
       public void sendInvitationBT() {
    	   submitGlbCustomerInvitation4Site.click();
       }
       public void closeInviteClientPopup() {
    	   inviteCancel4Site.click();
       }
       public void getTeamTab() {
    	   teamLink.click();
       }
       public String getFistInvited() {
    	   return firstInvited.getText();
       }
       public String getSecondInvited() {
    	   return secondInvited.getText();
       }
       public String getFirstBuyerEmail() {
    	   return buyerEmailInList.getText();
       }
       public String checkConfirmationMessage() {
    	   return inviteCustomerResult4Site.getText();
       }
       public String getErrorInviteMessage() {
    	   return glbCustomerInviteInputError4Site.getText();
       }
}
