package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class ProjectFrame extends Page 
{
	 public ProjectFrame(WebDriver driver)
	     {
	         super(driver);
	         PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	     }
	 
	 @FindBy(linkText="Overview")
	 private WebElement overviewTab;
	 
	 @FindBy(linkText="Production Specs")
	 private WebElement productionSpecTab;
	 
	 @FindBy(xpath="projectFrame.filesTab")
	 private WebElement filesTab;
	 
	 @FindBy(xpath="projectFrame.submittedStatus")
	 private WebElement submittedStatus;
	 
	 @FindBy(xpath="projectFrame.buyerSiteLink")
	 private WebElement buyerSiteLink;	 	
	 
	 @FindBy(xpath="projectFrame.reviewProjectBT2")
	 private WebElement reviewProjectBT;	   
	 
	 @FindBy(name="file")
	 private WebElement file;
	 
	 @FindBy(xpath="projectFrame.editProjectBT")
	 private WebElement editProjectBT;
	 
	 @FindBy(xpath="projectFrame.updateBT")
	 private WebElement updateBT;
	 
	 @FindBy(name="pmProjectStatusId")
	 private WebElement pmProjectStatusId;
	 
	 @FindBy(css="dd.site-projectOverview-report span")
	 private WebElement projectName;
	 
	 @FindBy(xpath="projectFrame.projectName")
	 private WebElement projectNameSP;
	 
	 @FindBy(xpath="projectFrame.spProject")
	 private WebElement spProject;
	 
	 @FindBy(xpath="projectFrame.projectProductName")
	 private WebElement projectProductName;
	 
	 @FindBy(linkText="Edit")
	 private WebElement editBT;
	 
	 @FindBy(xpath="projectFrame.newNumber")
	 private WebElement newNumber;
	 
	 @FindBy(xpath="projectFrame.newDimention")
	 private WebElement newDimention;
	 
	 @FindBy(xpath="projectFrame.newTextBox")
	 private WebElement newTextBox;
	 
	 @FindBy(linkText="Save")
	 private WebElement saveBT;
	 
	 @FindBy(linkText="Cancel")
	 private WebElement canselBT;
	 
	 @FindBy(xpath="projectFrame.message")
	 private WebElement message;
	 
	 @FindBy(xpath="projectFrame.fileName")
	 private WebElement fileName;
	 
	 @FindBy(xpath="projectFrame.fileNameDirect")
	 private WebElement fileNameDirect;
	 
	 @FindBy(xpath="projectFrame.fileNameInputDirect")
	 private WebElement fileNameInputDirect;
	 
	 @FindBy(xpath="projectFrame.fileNameInput")
	 private WebElement fileNameInput;
	 
	 @FindBy(xpath="projectFrame.secondFileName")
	 private WebElement secondFileName;
	 
	 @FindBy(xpath="projectFrame.uploadBT")
	 private WebElement uploadBT;
	 	 
//	 @FindBy(xpath="projectFrame.uploadFileSP")	 
//	 @FindBy(xpath="brochurePopupWindow.uploadDropButton")
	 @FindBy(id="upload-drop-button-1")
	 private WebElement uploadFileSP;

	 @FindBy(xpath="projectFrame.uploadFileSPPro")
	 private WebElement uploadFilePro;
	 
	 @FindBy(xpath="projectFrame.downloadIcon")
	 private WebElement downloadIcon;
	 
	 @FindBy(xpath="projectFrame.deleteFileIcon")
	 private WebElement deleteFileIcon;
	 
	 @FindBy(xpath="brochurePopupWindow.uploadDropButtonText")
	 private WebElement uploadDropButtonText;
	 
	 @FindBy(linkText="Name")
	 private WebElement nameCol;
	 
	 @FindBy(linkText="Type")
	 private WebElement typeCol;	
	 
	 @FindBy(className="glb-body")
	 private WebElement body;
	 
	 public void clickProductionSpecTab() {
		 productionSpecTab.click();
	 }
	 public void clickFilesTab() {
		 filesTab.click();
	 }
	 public boolean getProjectStatus() {
		 return submittedStatus.isDisplayed();
	 }
	 public String getProjectName()
	 {
         return projectName.getText();
		 /*
		 WebElement projectName = driver.findElement(By.cssSelector("span:contains('" + newProjectName 
				 + "')"));
		 return projectName.isDisplayed();
		 */
	 }

	 public boolean getUploadedFileName(String uploadedFileName)  {
		 WebElement fileName = driver.findElement(By.linkText(uploadedFileName));
		 return fileName.isDisplayed();
	 }
	 public String getUploadedFileName33()  {
		 return fileName.getText();
	 }
	 public String getUploadedSecondFileName() {
		 return secondFileName.getText();
	 }
	 public void clickReviewProjectBT() {
		 reviewProjectBT.click();
	 }
	 public void clickReviewProjectBTSmoke(NooshWebDriver driver, String project) {
		 driver.findElementByXPath("projectFrame.reviewProjectBT2", "0", project).click();
//		 reviewProjectBT.click();
	 }
	 public void renameFileName(NooshWebDriver driver, String oldFileName, String newFileName) {
		 driver.findElementByXPath("projectFrame.fileNameDirect", "0", oldFileName).click();
		 driver.findElementByXPath("projectFrame.fileNameInputDirect", "0", oldFileName).sendKeys(newFileName);
	 }
	 
     public void clickBuyerSiteLink() {
    	 buyerSiteLink.click();
     }
     public String getBuyerSiteName() {
    	 return buyerSiteLink.getText();
     }
     public void uploadFileSP(String fileSP) {
    	 uploadFile(file, fileSP);
     }
     public void clickEditProjectBT() {
    	 editProjectBT.click();
     }
     public void selectProjectStatus() {
    	 pmProjectStatusId.sendKeys("Project Reviewed");
     }     
     public void selectProjectStatusSmoke() {
    	 pmProjectStatusId.sendKeys("New Job Received");
     }
     public void clickUpdateBT() {
    	 updateBT.click();
     }
     public void clickOverviewTab() {
    	 overviewTab.click();
     }
     public String getProjectNumber() {
    	 return editProjectBT.getAttribute("projectid");
     }
     public void clickSPProject() {
    	 spProject.click();
     }
     public void selectProductSpec(NooshWebDriver driver, String productSpec) {
		 driver.findElementByXPath("projectFrame.projectProductName", "0", productSpec).click();    	 
     }
     public void clickEditBT() {
    	 editBT.click();
     }
     public void setNewNumber(String number) {
    	 type(newNumber, number);
     }
     public void setNewDemention(String number) {
    	 type(newDimention, number);
     }
     public void setTextTextBox(String text) {
    	 type(newTextBox, text);
     }
     public void clickSaveBT() {
    	 saveBT.click();
     }
     public void clickCancelBT() {
    	 canselBT.click();
     }
     public String getMessage() {
    	 return message.getText();
     }
     public String getSPProjectName() {
    	 return projectNameSP.getText();
     }
     public void clickUploadFileBT() {
    	 uploadBT.click();
     }
     public void clickFileUploadSP() {
    	 uploadFileSP.click();
     }
     public void clickuploadFilePro() {
    	 uploadFilePro.click();
     }
	 public void clickDownloadFileIcon(NooshWebDriver driver, String project) {
		 driver.findElementByXPath("projectFrame.downloadIcon", "0", project).click();
	 }
	 public void clickDeleteFileIcon(NooshWebDriver driver, String project) {
		 driver.findElementByXPath("projectFrame.deleteFileIcon", "0", project).click();
	 }	 
	 public void clickUloadFileBTText() {
		 uploadDropButtonText.click();
	 }
	 public void clickNameSort() {
		 nameCol.click();
	 }
	 public void clickTypeSort() {
		 typeCol.click();
	 }
	 public void fileRename(String newName) throws InterruptedException {
		 fileName.click();
		 fileNameInput.sendKeys(newName);
	 }
	 public void clickBody() {
		 body.click();
	 }
}
