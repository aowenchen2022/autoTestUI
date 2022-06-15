package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class Messages extends Page {

	 public Messages(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 private WebElement subject;
	 private WebElement content;
	 private WebElement post_message;
	 private WebElement messageStatusFilter;
	 
	 
	 @FindBy(xpath="messages.checkBox")
	 private WebElement checkBox;
	 
	 @FindBy(xpath="messages.allValue")
	 private WebElement allValue;
	 
	 @FindBy(xpath="messages.priority")
	 private WebElement priority;
	 
	 @FindBy(xpath="messages.subject")
	 private WebElement subjectText;
	 
	 private WebElement priorityStrId;
	 
	 @FindBy(xpath="messages.priorityHigh")
	 private WebElement priorityHigh;
	 
	 public void setMessageSubject(String text) {
		 subject.sendKeys(text);
	 }
	 
	 public void setMessageText(String text) {
		 content.sendKeys(text);
	 }
	 
	 public void selectCheckBox(NooshWebDriver driver, String recipient) {
		 driver.findElementByXPath("messages.checkBox", "0", recipient).click();		 
	 }
	 
	 public void clickPostMessage() {
		 post_message.click();
	 }
	 
	 public void getMessagesList() throws InterruptedException {
		 messageStatusFilter.click();
    	 Thread.sleep(600);
		 allValue.click();
	 }
	 
	 public String getPriority() {
		 return priority.getText();
	 }
	 
	 public String getSubjectText() {
		 return subjectText.getText();
	 }
	 
	 public void setHighPriority() throws InterruptedException {
		 priorityStrId.click();
		 Thread.sleep(1000);
		 priorityHigh.click();
	 }
}
