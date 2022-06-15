package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class MyDesk extends Page
{
	 public MyDesk(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 @FindBy(id="MTbl1")
	 private WebElement workspaceMenu;
	 
	 @FindBy(id="lnk11")
	 private WebElement orderMenu;
	 
	 @FindBy(name="mM1")
	 private WebElement summary;
	 
	 @FindBy(id="MTbl0")
	 private WebElement myDeskMenu;
	 
	 @FindBy(id="lnk10")
	 private WebElement projectsMenu;
	 
	 @FindBy(linkText="Save")
	 private WebElement save;
	 
	 @FindBy(id="cancel")
	 private WebElement canselBT;
	 
	 @FindBy(xpath="myDesk.projectName")
	 private WebElement projectNamePro;
	 
	 @FindBy(xpath="myDesk.specOne")
	 private WebElement specOne;
	 
	 @FindBy(xpath="myDesk.textBoxText")
	 private WebElement textBoxText;
	 
	 @FindBy(xpath="myDesk.date")
	 private WebElement date;
	 
	 @FindBy(xpath="myDesk.newNumber")
	 private WebElement newNumber;
	 
	 @FindBy(xpath="myDesk.newDimentionsLength")
	 private WebElement newDimentionLength;
	 
	 @FindBy(xpath="myDesk.editBT")
	 private WebElement editBt;
	 
	 @FindBy(xpath="myDesk.textBoxTextInput")
	 private WebElement textBoxInput;
	 
	 @FindBy(xpath="myDesk.newNumberInput")
	 private WebElement newNumberInput;
	 
	 @FindBy(xpath="myDesk.newDimentionsLengthInput")
	 private WebElement newDimentionsLengthInput;
	 
	 @FindBy(xpath="myDesk.saveBT")
	 private WebElement saveBT;
	 
	 @FindBy(xpath="myDesk.quantity2")
	 private WebElement quantity2;
	 
	 @FindBy(xpath="myDesk.week")
	 private WebElement weekDay;
	 
	 @FindBy(xpath="myDesk.weekClick")
	 private WebElement weekClick;
	 
	 @FindBy(xpath="myDesk.weekDaySelect")
	 private WebElement weekDaySelect;
	 
	 @FindBy(xpath="myDesk.priority")
	 private WebElement priority;
	 
	 @FindBy(xpath="myDesk.important")
	 private WebElement important;
	 
	 @FindBy(xpath="myDesk.quantity2Input")
	 private WebElement quantity2Input;
	 
	 @FindBy(xpath="myDesk.quantity1")
	 private WebElement quantity1Input;
	 
	 @FindBy(xpath="myDesk.weekInput")
	 private WebElement weekInput;
	 
	 @FindBy(xpath="myDesk.priorityInput")
	 private WebElement priorityInput;
	 
	 @FindBy(xpath="myDesk.label")
	 private WebElement specName;
	 
	 @FindBy(name="topnav_ngDashboard")
	 private WebElement nooshGroupEdition;
	 
	 @FindBy(xpath="myDesk.specCheckBox")
	 private WebElement specCheckBox;
	 
	 @FindBy(id="continue")
	 private WebElement continueBT;
	 
	 @FindBy(xpath="myDesk.createEstimate")
	 private WebElement createEstimate;	 
	 
	 private WebElement searchString;
	 
	 @FindBy(xpath="myDesk.estimateTitle")
	 private WebElement estimateTitle;
	 
	 @FindBy(id="return")
	 private WebElement returnBT;
	 
	 public void clickProjectsMenuItem() throws InterruptedException {
		 clickSubMenuItem(workspaceMenu, projectsMenu);
	 }
	 
	 public void clickSummarySubmenu() throws InterruptedException {
		 clickSubMenuItem(myDeskMenu, summary);
	 }
	 
     public void selectProject(NooshWebDriver driver, String projName) {
		 driver.findElementByXPath("myDesk.projectName", "0", projName).click();    	 
     }  
     
     public void selectProductSpec(NooshWebDriver driver, String specName) {
		 driver.findElementByXPath("myDesk.projectName", "0", specName).click();    	 
     }   
     
     public String getTextBoxText() {
    	 return textBoxText.getText();
     }
     
     public String getDate() {
    	 return date.getText();
     }
     
     public String getNewNumber() {
    	 return newNumber.getText();
     }
     
     public String getNewDimentionLength() {
    	 return newDimentionLength.getText();
     }
     
     public void clickEditBT() {
    	 editBt.click();
     }
     
     public void setTextInTextBox(String text) {
    	 type(textBoxInput, text);
     }
     
     public void setNewNumber(String newNumber) {
    	 type(newNumberInput, newNumber);
     }
     
     public void setDimentionLength(String dimentionLength) {
    	 type(newDimentionsLengthInput, dimentionLength);
     }
     
     public void setQuantity2Field(String number) {
    	 type(quantity2Input, number);
     }
     
     public void setWeekDay(String weekDay) {
    	 weekInput.sendKeys(weekDay);
    	 weekInput.sendKeys(Keys.RETURN);
     }
     
     public void clickSaveBT() {
    	 saveBT.click();
     }
     
     public void setSearchProject(String projName) {
    	 searchString.sendKeys(projName);
    	 searchString.sendKeys(Keys.RETURN);
     }
     
     public void selectProjectInList(NooshWebDriver driver, String project) {
    	 driver.findElementByXPath("myDesk.projectName1", "0", project).click(); 
     }
     
     public String getQuantity() {
    	 return quantity2.getText();
     }
     
     public String getWeekDay() {
    	 return weekDay.getText();
     }
     
     public String getPriority() {
    	 return priority.getText();
     }
     
     public String getImportanceStatus() {
    	 return important.getText();
     }
     
     public void setNewWeekDay() throws InterruptedException {
    	 weekClick.click();
    	 Thread.sleep(800);
    	 weekDaySelect.click();
     }
     
     public void setPriority() {
    	 priorityInput.click();
     }
     
     public void clickSave() {
    	 save.click();
     }
     
     public void clickCanselBT() {
    	 canselBT.click();
     }
     
     public String getSpecName() {
    	 return specName.getText();
     }
     
     public void clickNGE() {
    	 nooshGroupEdition.click();
     }
     
     public void selectSpecCheckBox() {
    	 specCheckBox.click();
     }
     
     public void clickContinueBT() {
    	 continueBT.click();
     }
     
     public void setQuantity1(String quantity) {
    	 quantity1Input.sendKeys(quantity);
     }
     
     public void clickMyDeskMenuItem() {
    	 myDeskMenu.click();
     }
     
     public void clickCreateEstimateProject(NooshWebDriver driver, String estimate) {
    	 driver.findElementByXPath("myDesk.createEstimate", "0", estimate).click();
     }
     
     public void clickReternBT() {
    	 returnBT.click();
     }
     
     public void selectEstimate(NooshWebDriver driver, String estimateName) {
		 driver.findElementByXPath("myDesk.estimateTitle", "0", estimateName).click();    	 
     }
     
	 public void clickOrdersMenuItem() throws InterruptedException {
		 clickSubMenuItem(workspaceMenu, orderMenu);
	 }
}	 
