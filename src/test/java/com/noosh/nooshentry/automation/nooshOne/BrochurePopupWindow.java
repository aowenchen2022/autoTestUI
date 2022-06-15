package com.noosh.nooshentry.automation.nooshOne;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class BrochurePopupWindow extends Page
{
   public BrochurePopupWindow(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(xpath="brochurePopupWindow.projectName")
   private WebElement projectName;
   
   @FindBy(id="open_calendar")
   private WebElement complationDataCalendar;
   
   @FindBy(linkText="25")
   private WebElement complationDate;
   
   @FindBy(id="next_month")
   private WebElement nextMonth;
   
   @FindBy(xpath="brochurePopupWindow.complationData")
   private WebElement complationDateIE;
   
   @FindBy(partialLinkText="Files")
   private WebElement filesTab;
   
   @FindBy(linkText="Specs")
   private WebElement specsTab;
   
   @FindBy(linkText="Messages")       
   private WebElement messageTab;
   
   @FindBy(linkText="Team")
   private WebElement teamTab;
   
   @FindBy(id="createProjectFilesFile1")
   private WebElement uploadFile;
   
   @FindBy(linkText="Review and Submit")
   private WebElement reviewAndSubmitTab;
   
   @FindBy(id="createProjectTabReviewHref")
   private WebElement reviewSubmitTab;
   
   @FindBy(id="cpNextStep")
   private WebElement nextBT;
   
   @FindBy(xpath="brochurePopupWindow.selectSize")
   private WebElement selectSize;
   
   @FindBy(xpath="brochurePopupWindow.option")
   private WebElement option;
   
   @FindBy(xpath="brochurePopupWindow.descriptionName")
   private WebElement descriptionNameProject;
   
   @FindBy(xpath="brochurePopupWindow.descriptionNameOld")
   private WebElement descriptionNameProjectOld;
   
   @FindBy(xpath="brochurePopupWindow.sku")
   private WebElement skuPro;
   
   @FindBy(xpath="brochurePopupWindow.specName")  
   private WebElement specName;
   
   @FindBy(xpath="brochurePopupWindow.refNumber")   
   private WebElement refNumber;
   
   @FindBy(xpath="brochurePopupWindow.quantity1")
   private WebElement quant1;
   
   @FindBy(xpath="brochurePopupWindow.quantity2")       
   private WebElement quant2;                           
   
   @FindBy(xpath="brochurePopupWindow.quantity3")       
   private WebElement quant3;                           
   
   @FindBy(xpath="brochurePopupWindow.specDescription")
   private WebElement specDescription;   

   @FindBy(xpath="brochurePopupWindow.submitProjectBT")
   private WebElement submitProjectBT;
   
   @FindBy(xpath="brochurePopupWindow.projectInfoEditBT")
   private WebElement projectInfoEditBT;
   
   @FindBy(linkText="Spec Description")
   private WebElement specDiscriptionTab;
   
   @FindBy(linkText="Size and Stock")
   private WebElement sizeAndStock;
   
   @FindBy(linkText="Activities")
   private WebElement activitiesTab;
   
   @FindBy(id="next_month")
   private WebElement next;
   
   private WebElement cpNextStep;
   private WebElement cpAddProducts;
   private WebElement cpDraftButton;
   private WebElement homeSiteListView;
   private WebElement jobTabInfo;
   
   
   @FindBy(id="upload-drop-button-5000")
   private WebElement uploadDropButton;

   @FindBy(name="create_project")
   private WebElement createProjBTN;
   
   @FindBy(id="cpNextStep")   
   private WebElement projectCreateEstimatBtn; 
   
   @FindBy(id="cpNextStep-clone")  
   private WebElement projectGlbCreateEstimatBtn; 
   
   @FindBy(id="cpNextStep-cloneInvite")
   private WebElement projectGlbCreateInviteBtn;
   
   // Advanced Editor product
   @FindBy(xpath="brochurePopupWindow.textbox")
   private WebElement textBox;
   
   @FindBy(xpath="brochurePopupWindow.newNumber")
   private WebElement newNumber;
   
   @FindBy(xpath="brochurePopupWindow.callCalendar")
   private WebElement callCalendar;
   
   @FindBy(xpath="brochurePopupWindow.newDimention")
   private WebElement newDemention;
   
   @FindBy(xpath="brochurePopupWindow.newDimentionlength")
   private WebElement newDimentionlength;
   
   @FindBy(xpath="brochurePopupWindow.editProductSpec")
   private WebElement editPorductSpecBT;
   
   @FindBy(xpath="brochurePopupWindow.newTextBox")
   private WebElement newTextBox;
   
   @FindBy(xpath="brochurePopupWindow.dimention")
   private WebElement dimention;
   
   @FindBy(xpath="brochurePopupWindow.fileTab")
   private WebElement fileTab;
   
   @FindBy(xpath="brochurePopupWindow.fileTabPop")
   private WebElement fileTabPop;
   
   @FindBy(xpath="brochurePopupWindow.fileTabPro")
   private WebElement fileTabPro;
   
   @FindBy(xpath="brochurePopupWindow.numberField")
   private WebElement numberField;
   
   @FindBy(xpath="brochurePopupWindow.priority")
   private WebElement priority;
   
   @FindBy(xpath="brochurePopupWindow.important")
   private WebElement important;
   
   @FindBy(xpath="brochurePopupWindow.quantityTwo")
   private WebElement quantityTwo;
   
   @FindBy(xpath="brochurePopupWindow.projectDescription")
   private WebElement projectDescription;
   
   @FindBy(xpath="brochurePopupWindow.printQuanttity")
   private WebElement printQuanttity;
   
   @FindBy(xpath="brochurePopupWindow.mailQuanttity")
   private WebElement mailQuantity;
   
   @FindBy(linkText="Production Specs")
   private WebElement productionSpecTab;  
   
   @FindBy(id="createProjectTabs")
   private WebElement tabs;
   
   private WebElement textArea;
   private WebElement msgPost;
  
   public void inputMsg() {
	   textArea = driver.findElement(By.className("proj-view-message-static")).findElement(By.tagName("textarea"));
	   String clientMsg = textArea.getAttribute("name");
	   if(clientMsg != null && clientMsg.equalsIgnoreCase("content")) {
		   textArea.clear();
		   textArea.sendKeys("Test message from client 2.\nTest message from client 2.\nTest message from client 2.");
	   }
   }
   
   public void clickEditProjectBtn() {
	   WebElement editProj = homeSiteListView.findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("projectOverview-editButton")).click();	   
   }
   
   public void clickRequestEstimateBtn() {
	   WebElement editProj = homeSiteListView.findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("projectOverview-rfeButton")).click();	   
   }
   
   public void updateProjectName(String projectName) {
	   WebElement editProj = driver.findElement(By.className("home-project-list-ul"));
	   WebElement projName = editProj.findElement(By.className("site-projectOverview-controll"));
	   projName.clear();
	   projName.sendKeys(projectName + " edit");
   }
   
   public void updateProjectStatus() {
	   //WebElement editProj = driver.findElement(By.className("home-project-list-ul"));
	   //Select status = new Select(editProj.findElement(By.className("site-projectOverview-controll")));
	   //status.selectByVisibleText("Project Reviewed");
	   
	   WebElement editProj = homeSiteListView.findElement(By.className("home-project-list-ul")).findElement(By.tagName("form")).findElement(By.tagName("select"));
	   Select clickThis = new Select(editProj);
	   clickThis.selectByVisibleText("Project Reviewed");
   }
   
   public void clickUpdateProjectBtn() {
	   WebElement editProj = driver.findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("projectOverview-updateButton")).click();
   }
   
   public void clickCancelProjectBtn() {
	   WebElement editProj = driver.findElement(By.className("home-project-list-ul"));
	   editProj.findElement(By.className("glb-simple-cancel")).click();
   }
   
   public void postMsg() throws InterruptedException {
	   msgPost = driver.findElement(By.className("proj-view-message-fieldset")).findElement(By.className("sp-simple-button"));
	   List<WebElement> team = jobTabInfo.findElement(By.className("project-member-list")).findElements(By.tagName("input"));
	   if(!team.get(2).isSelected()) {
		   team.get(2).click();
		   Thread.sleep(2000);
	   }
	   if(msgPost.isDisplayed()){
		   msgPost.click();
		   Thread.sleep(3000);
	   } else {
		   System.out.println("Post Msg btn is missing!\n");
	   }
   }
  
   public void postMsgSP() throws InterruptedException {
	   msgPost = driver.findElement(By.className("proj-view-message-fieldset")).findElement(By.className("post-message-button")).findElement(By.className("sp-simple-button"));
	   WebElement msgSet = driver.findElement(By.className("proj-view-message-fieldset"));
	   List<WebElement> team = msgSet.findElement(By.className("project-member-list")).findElements(By.tagName("input"));
	   if(!team.get(2).isSelected()) {
		   team.get(2).click();
		   Thread.sleep(2000);
	   }
	   if(msgPost.isDisplayed()){
		   msgPost.click();
		   Thread.sleep(3000);
	   } else {
		   System.out.println("Post Msg btn is missing!\n");
	   }
   }
   
   public void openProject() throws InterruptedException {
	   WebElement projects = driver.findElement(By.id("homeSwitcher"));
	   projects.findElement(By.className("line-project-name")).click();	  
   }
   
   public void getActivitiesTab() throws InterruptedException {
	   activitiesTab.click();
	   Thread.sleep(1000);
	   Assert.assertNotNull(driver.findElement(By.id("jobTabInfo")).findElement(By.tagName("tbody")));
	   Thread.sleep(1000);
   }
   
   public void getSmartFormsTab() throws InterruptedException {
	   specsTab.click();
	   Thread.sleep(1500);
   }
   
   public void setProjectName(String projName) {
      projectName.clear();
      projectName.sendKeys(projName);
   }      
   public void callCalendar() {
      complationDataCalendar.click();
   }   
   public void setComplationDate() {
      //complationDate.click();
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();",complationDate);
   }   
   public void setNextMonth() {
	   //nextMonth.click();
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();",nextMonth);
   }
   public void getFilesTab() {
	   driver.findElement(By.id("createProjectTabs")).findElement(By.linkText("Files")).click();
	  
   }  
   public void getFilesTab1() {
	   WebElement fileTab = driver.findElement(By.id("jobTabInfo")).findElement(By.linkText("Files"));
	   if(fileTab.isDisplayed()) {
		   fileTab.click();
	   }
   }     
   public void getMessageTab() {   
	   messageTab.click();
   }
   public void getTeamTab() {   
	   teamTab.click();
   }
   public void uploadProjectFile(String projectFile)  {
	   uploadFileRelative(uploadFile, projectFile);
   }  
   // check for using in the script
   public void uploadFile() {
	   Actions action = new Actions(driver);
	   action.doubleClick(uploadFile);
	   action.perform();
   }   
   // check for using in the script
   public void uploadProjectFile1(String projectFile) {
      File file = new File(projectFile);
	  uploadFile.sendKeys(file.getAbsolutePath());
   }          
   public void getReviewAndSubmitTab() {
      reviewAndSubmitTab.click();
   }  
   public void getReviewAndSubmitTab1() {
	   reviewSubmitTab.click();
   }
   public void submitProject() throws InterruptedException
   {
	   Actions action = new Actions(driver);
	   action.moveToElement(submitProjectBT).build().perform();
	   action.clickAndHold(submitProjectBT).build().perform();
	   Thread.sleep(2000); 
	   action.release(submitProjectBT).build().perform(); 
   }   
   public void clickSubmitBT() {
	   String createProj = cpNextStep.getText();
	   if(cpNextStep.isDisplayed() && createProj.equals("Create Project")) {
	       cpNextStep.click();
	   } else {
		   System.out.println("'Create Project' button is missing.\n");
		   Assert.fail();
	   }
   }
   
   public void submitNewProject() {
	   submitProjectBT.click();
   }

   public void getNextTab() {
      nextBT.click();
   }
   
   public void putDescriptionName(String descriptionName) {   	
      descriptionNameProjectOld.clear();
      descriptionNameProjectOld.sendKeys(descriptionName);
   }
   public void putDescriptionNameNew(NooshWebDriver driver, String descriptionFieldName, String descriptionName) {
	   
	   driver.findElementByXPath("brochurePopupWindow.descriptionName", "0", descriptionFieldName).clear();
	   driver.findElementByXPath("brochurePopupWindow.descriptionName", "0", descriptionFieldName).sendKeys(descriptionName);	   	
   }
   
   public void putSKU(String sku) {
      skuPro.clear();
      skuPro.sendKeys(sku);
   }
   
   public void putRefNumber(String referenceNumber) {
      refNumber.clear();
      refNumber.sendKeys(referenceNumber);
   }
   
   public void putSpecName(String SName) {
	   specName.clear();
	   specName.sendKeys(SName);
    }
 
   public void putSpecDescription(String specDescrBuyer) {
      specDescription.clear();
      specDescription.sendKeys(specDescrBuyer);
   }

   public void putQuant1(String quant) {
      quant1.clear();
      quant1.sendKeys(quant);
   }
   
   public void putQuant2(String quant) {
	   quant2.clear();
	   quant2.sendKeys(quant);
   }
   
   public void putQuant3(String quant) {
	   quant3.clear();
	   quant3.sendKeys(quant);
   }
   
   public void clickAddProductsBT() {
	   cpAddProducts.click();
   }
  
   public void clickCreateAndEstimateProjectBT() {
	   projectCreateEstimatBtn.click();
   }
   public void clickCreateAndEstimateGlbProjectBT() {
	   projectGlbCreateEstimatBtn.click();
   }
   
   public void clickCreateAndInviteBT() throws InterruptedException {
	   projectGlbCreateInviteBtn.click();	   
	   Thread.sleep(1000);
   }
   
   public void clickCreateBT() {  
	   createProjBTN.click();
   }
   
   public void clickProjectInfoEditBT() {
	   projectInfoEditBT.click();
   }  
   
   public void clickSpecDescription() {
	   specDiscriptionTab.click();
   }
   
   public void setProjectDescription(String description) {
	   projectDescription.sendKeys(description);
   }
   
   public void setPrintQuantity(String quantity) {
	   printQuanttity.sendKeys(quantity);
   }
   
   public void setMailQuantity(String quantity) {	   
	   mailQuantity.sendKeys(quantity);
   }
  
   public void setSizeOption() {
	   selectSize.click();
	   option.click();
   }
   
   public void clickSaveAsDraftBT() {
	   cpDraftButton.click();
   }
   
   public void setTextBoxText(String text) {
	   type(textBox, text);
   }
   
   public void setNewNumber(String newNumberAdv) {
	   type(newNumber, newNumberAdv);
   }
   
   public void callCalendarAdv() {
	   callCalendar.click();
   }
   
   public void setNewDimention(String dimention) {
	   type(newDemention, dimention);
   }
   
   public void clickProductSpecEdit() {
	   editPorductSpecBT.click();
   }
   
   public void setNameTextBox(String name) {
	   type(textBox, name);
   }
   
   public void setDimention(String dimentionVal) {
	   type(dimention, dimentionVal);
   }
   
   public void clickFilesTabAdvEd() {
	   fileTab.click();
   }
   
   public void setNumber(String newNumber) {
	   numberField.sendKeys(newNumber);
   }
   
   public void setDimentionLength(String length) {
	   newDimentionlength.clear();
	   newDimentionlength.sendKeys(length);
   }
   public void setPriority() {
	   priority.click();
   }
   
   public void setImportance() {
	   important.click();
   }
   
   public void setQuantitySecondField(String number) {
	   quantityTwo.sendKeys(number);
   }
   
   public void clickFileTabPro() {
	   fileTabPro.click();
   }
   
   public void clickFileTabPop() {
	   fileTabPop.click();
   }
   
   public void clickUploadFileDropBT() {
	  driver.findElement(By.className("drop-upload-file-button")).click();
   }
   
   public void clickUploadFileDropBTNE() {
	   driver.switchTo().frame("fileView");	
	   driver.findElement(By.className("drop-upload-file-button")).click();
   }
   
   public String getProjectName() {
	   return projectName.getAttribute("value");
   }
   
   public void setComplationDateIE(String dateComplation) {
	   complationDateIE.sendKeys(dateComplation);
   }
   
   public void clickSizeAndStock() {
	   sizeAndStock.click();
   }
   
   public void clickProductionSpecTab() {
	   productionSpecTab.click();
   }
}

