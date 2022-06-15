package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class InviteCoworkers extends Page
{
	   public InviteCoworkers(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   }
	   
	   @FindBy(linkText="Invite Coworker")
	   private WebElement inviteCoworkerTab;
	   
	   private WebElement inviteCoworkerEmails;
	   private WebElement submitGlbCoworkerInvitation;	   
	   
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
}	   