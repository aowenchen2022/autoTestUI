package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class ProjectMenu extends Page {

	 public ProjectMenu(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 @FindBy(id="leftnav_spec_create")
	 private WebElement specLink;
	 
	 @FindBy(xpath="projectMenu.advSpec")
	 private WebElement advSpec;
	 
	 @FindBy(id="continue")
	 private WebElement continueBT;
	 
	 @FindBy(id="leftnav_home_update")
	 private WebElement updateLink;
	 
	 @FindBy(id="leftnav_message_mainmenu")
	 private WebElement messageLink;
	 
	 @FindBy(id="leftnav_message_post")
	 private WebElement messagePostLink;
	 
	 @FindBy(id="leftnav_message_home")
	 private WebElement messagesListLink;
	 
	 @FindBy(id="leftnav_home_attach")
	 private WebElement attachLink;
	 
	 @FindBy(id="leftnav_file_mainmenu")
	 private WebElement fileLink;
	 
	 @FindBy(id="leftnav_estimate_mainmenu")
	 private WebElement estimatesLink;
	 
	 @FindBy(id="leftnav_estimate_create_rfe")
	 private WebElement createRFELink;
	 
	 @FindBy(name="topnav_logout")
	 private WebElement logout;
	 
	 @FindBy(id="leftnav_home_mainmenu")
	 private WebElement homeMenu;
	 
	 public void clickSpecLink() {
		 specLink.click();
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
}
