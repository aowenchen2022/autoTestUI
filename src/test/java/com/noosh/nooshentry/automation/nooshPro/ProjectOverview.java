package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class ProjectOverview extends Page{
	
	 public ProjectOverview(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }	
	 
	 private WebElement name;
	 private WebElement projectStatusId;
	 private WebElement save;
	 
	 @FindBy(id="attach_new_file")
	 private WebElement attachFileButton;
	 
	 @FindBy(xpath="projectOverview.projectStatus")
	 private WebElement projectStatus;
	 
	 @FindBy(xpath="projectOverview.file")
	 private WebElement file;
	 
	 @FindBy(xpath="projectOverview.fileOpen")
	 private WebElement fileOpenLink;
	 
	 @FindBy(xpath="projectOverview.fileInput")
	 private WebElement fileField;
	 
	 @FindBy(xpath="projectOverview.fileName")
	 private WebElement fileName;
	 
	 public void setNewProjectName(String newName) {
		 type(name, newName);
	 }
	 public void selectProjectStatus(NooshWebDriver driver, String jobStatus) throws InterruptedException {
		 projectStatusId.click();
    	 Thread.sleep(800);
		 driver.findElementByXPath("projectOverview.projectStatus", "0", jobStatus).click();    	 
	 }
	 public void clickSave() {
		 save.click(); 
	 }
     public void clickFileName(NooshWebDriver driver ,String fileName) {
		 driver.findElementByXPath("projectOverview.file", "0", fileName).click();   
     }
     public void clickAttachButton() {
    	 attachFileButton.click();
     }
     public void uploadImageFile(String fileName) {
    	 uploadFileRelative(fileField, fileName);
     }
     public void clickFileOpen(NooshWebDriver driver ,String fileName) {
		 driver.findElementByXPath("projectOverview.fileOpen", "0", fileName).click();   	 
     }
	 public String getFileNameInList(NooshWebDriver driver, String name) throws InterruptedException {
    	 Thread.sleep(800);
		 return driver.findElementByXPath("projectOverview.fileName", "0", name).getText();    	 
	 }
}
