package com.noosh.nooshentry.automation.apiTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class Hackst extends Page{

	 public Hackst(WebDriver driver)
	 {
		  super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }		 	 

     @FindBy(name="url")
     private WebElement requestField;
     
     @FindBy(linkText="Execute")
     private WebElement execute;
     
     @FindBy(xpath="hackst.firstWorkgroup")
     private WebElement firstWorkgroup;
     
     @FindBy(xpath="hackst.firstWorkgroupID")
     private WebElement firstWorkgroupID;
     
     @FindBy(xpath="hackst.secondWorkgroup")
     private WebElement secondWorkgroup;
     
     @FindBy(xpath="hackst.secondWorkgroupID")
     private WebElement secondWorkgroupID;
     
     @FindBy(xpath="hackst.statusCode")
     private WebElement statusCode;
     
     @FindBy(xpath="hackst.default2")
     private WebElement default2;
     
     @FindBy(xpath="hackst.default1")
     private WebElement default1;
     
     @FindBy(xpath="hackst.editBT")
     private WebElement editBT;
     
     // Web elements for projects
     
     @FindBy(xpath="hackst.projectAccount")
     private WebElement projectAccount;
     
     @FindBy(xpath="hackst.projectID")
     private WebElement projectID;
     
     @FindBy(xpath="hackst.projectName")
     private WebElement projectName;
     
     public void setRequestURI(String request) {
    	 type(requestField, request);
     }     
     public void clickExecuteBT() {
    	 execute.click();
     }
     public String getFirstWorkgroup() {
    	 return firstWorkgroup.getText();    	 
     }
     public String getFirstGroupID() {
    	 return firstWorkgroupID.getText();
     }
     public String getSecondWorkgroup() {
    	 return secondWorkgroup.getText();
     }
     public String getSecondGroupID() {
    	 return secondWorkgroupID.getText();
     }
     public String getStatusCode() {
    	 return statusCode.getText();
     }
     public String getDefault2() {
    	 return default2.getText();
     }
     public String getDefault1() {
    	 return default1.getText();
     }
     public void clickEditBT() {
    	 editBT.click();
     }
     
     // methods for project
     public String getProjectAccountName() {
    	 return projectAccount.getText();
     }
     public String getProjectID() {
    	 return projectID.getText();
     }
     public String getProjectName() {
    	 return projectName.getText();
     }
}
