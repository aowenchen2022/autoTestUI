package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.nooshOne.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class ProjectMenu extends Page {

	 public ProjectMenu(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 @FindBy(linkText="Invite Suppliers...")
	 private WebElement inviteSuppliersLink;
	 
	 @FindBy(linkText="Invite Members...")
	 private WebElement inviteMembersLink;
	 
	 @FindBy(linkText="Teams")
	 private WebElement teamsLink;
	 
	 @FindBy(linkText="Specs")
	 private WebElement specsLink;
	 
	 @FindBy(linkText="Create...")
	 private WebElement specLink;
	 
	 @FindBy(xpath="projectMenu.advSpec")
	 private WebElement advSpec;
	 
	 @FindBy(id="continue")
	 private WebElement continueBT;
	 
	 @FindBy(id="leftnav_home_update")
	 private WebElement updateLink;
	 
	 @FindBy(linkText="Messages")
	 private WebElement messageLink;
	 
	 @FindBy(id="leftnav_message_post")
	 private WebElement messagePostLink;
	 
	 @FindBy(id="leftnav_message_home")
	 private WebElement messagesListLink;
	 
	 @FindBy(id="leftnav_home_attach")
	 private WebElement attachLink;
	 
	 @FindBy(id="leftnav_file_mainmenu")
	 private WebElement fileLink;
	 
	 @FindBy(linkText="Estimates")
	 private WebElement estimatesLink;
	 
	 @FindBy(linkText="Create RFE...")
	 private WebElement createRFELink;
	 
	 @FindBy(name="topnav_logout")
	 private WebElement logout;
	 
	 @FindBy(id="leftnav_home_mainmenu")
	 private WebElement homeMenu;
	 
	 @FindBy(xpath="newHomePage.projLnk")
	 private WebElement projLnk;
	 
	 public void clickCreateSpec() {
		 specLink.click();
	 }
	 
	 public void clickSpecsLink() {
		 specsLink.click();
	 }
	 
	 public void clickAdvSpec(NooshWebDriver driver, String projName) {
		 advSpec.click();
		// driver.findElementByXPath("projectMenu.advSpec", "0", projName).click();
	 }
	 
	 public void clickContinueBT() {
		 continueBT.click();
	 }
	 
	 public void clickUpdateLink() {
		 updateLink.click();
	 }
	 
	 public void clickMessageLink() {
		 messageLink.click();
	 }
	 
	 public void clickMessagePostLink () {
		 messagePostLink.click();
	 }
	 
	 public void clickMessageListLink() {
		 messagesListLink.click();
	 }
	 
	 public void clickFileLink() {
		 fileLink.click();
	 }
	 
	 public void clickEstimatesLink() {
		 estimatesLink.click();
	 }
	 
	 public void clickCreateRFELink() {
		 createRFELink.click();
	 }
	 
	 public void clickLogout() {
		 logout.click();
	 }
	 
	 public void openHomeMenu() {
		 homeMenu.click();
	 }
	 
	 public boolean checkHomeMenuOpen() {
		 return updateLink.isDisplayed();
	 }
	 
	 public boolean checkMessagesMenuOpen() {
		 return messagePostLink.isDisplayed();
	 }
	 
	 public boolean checkEstimatesMenu() {
		 return createRFELink.isDisplayed();
	 }
	 
	 public void clickProjLnk() {
		 projLnk.click();
	 }
	 
	 public void clickTeamsLnk() {
		 teamsLink.click();
	 }
	 
	 public void clickInviteSuppliersLnk() {
		 inviteSuppliersLink.click();
	 }
	 
	 public void clickInviteMembersLnk() {
		 inviteMembersLink.click();
	 }
}
