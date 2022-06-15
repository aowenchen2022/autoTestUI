package com.noosh.nooshentry.automation.buyerSite;


import java.io.File;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewProjectPage extends Page
{
   public NewProjectPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
      
   @FindBy(linkText="Files")
   private WebElement fileTab;
   
   @FindBy(name="file")
   private WebElement uploadFile;
   
   @FindBy(linkText="Messages")
   private WebElement messageTab;
   
   @FindBy(name="content")
   private WebElement textArea;
   
   @FindBy(xpath="newProjectPage.submitMessage")
   private WebElement submitMessage;
   
   @FindBy(linkText="Production Specs")
   private WebElement projectSpecsTab;
   
   @FindBy(xpath="newProjectPage.projectStatus")
   private WebElement projectStatus;
   
   @FindBy(xpath="newProjectPage.buyerSiteLink")
   private WebElement buyerSiteLink;	
   
   @FindBy(linkText="Overview")
   private WebElement overviewTab;
   
   @FindBy(xpath="newProjectPage.projectSpec")
   private WebElement projectSpec;
   
   public void clickFileTab() {
      fileTab.click();
   }   
   public void uloadNewFile1(String fileName) {
	   File file = new File(fileName);
	   uploadFile.sendKeys(file.getAbsolutePath());
   }   
   public void uploadNewFile(String fileName) {
      uploadFile.sendKeys(fileName);
   }  
   public void clickMessageTab() {
      messageTab.click();
   }  
   public void buyerMessage(String buyerNewMessage) {
      textArea.clear();
      textArea.sendKeys(buyerNewMessage);
   }
   public void sendMessage() {
      submitMessage.submit();
   } 
   public void clickProjectSpecsTab() {
      projectSpecsTab.click();
   }
   public void uploadProjectFile(String projectFile) {
	   uploadFileRelative(uploadFile, projectFile);
 //     File file = new File(projectFile);
//	  uploadFile.sendKeys(file.getAbsolutePath());
   } 
   public boolean checkProjectStatus() {
	   return projectStatus.isDisplayed();
   }
   public boolean checkProjectName(String projectName) {
	   WebElement nameProject = driver.findElement(By.xpath("//span[contains(text(), projectName)]")); 
	   return nameProject.isDisplayed();
   }
   public boolean getBuyerMassage(String buyerMessage) {
	   WebElement message = driver.findElement(By.xpath("//span[text()='" +
				 buyerMessage + "']")); 
	   return message.isDisplayed();
   }
   public void clickBuyerSiteLink() {
  	   buyerSiteLink.click();
   }
   public void clickOverviewTab() {
	   overviewTab.click();
   }
   public void clickSPFile(String spFile) {
	   WebElement uploadedFile = driver.findElement(By.linkText(spFile)); 
	   uploadedFile.click();
   }
   public void clickProjectSpec() {
	   projectSpec.click();
   }
   
}
