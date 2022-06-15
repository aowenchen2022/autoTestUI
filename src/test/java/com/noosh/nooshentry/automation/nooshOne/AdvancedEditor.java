package com.noosh.nooshentry.automation.nooshOne;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.JavascriptExecutor;

public class AdvancedEditor extends Page
{
	 public AdvancedEditor(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 // Basic Information tab
	 private WebElement peFormNameInput; 
	 private WebElement peFormIconInput;
	 
	 // Customization tab - Text Box
	 @FindBy(xpath="advancedEditor.textBox")
	 private WebElement textBox;
	 
	 @FindBy(xpath="advancedEditor.textBoxArea")
	 private WebElement textBoxArea;
	 
	 @FindBy(xpath="advancedEditor.pencilTextBox")
	 private WebElement pencilTextBox;
	 
	 @FindBy(name="isRequired")
	 private WebElement isRequired; 
	 
	 // Customization Tab - Date
	 @FindBy(xpath="advancedEditor.date")
	 private WebElement date;
	 
	 @FindBy(xpath="advancedEditor.dateArea")
	 private WebElement dateArea;
	 
	 @FindBy(xpath="advancedEditor.pencilDate")
	 private WebElement pebcilDate;
	 
	 @FindBy(name="isHidden")
	 private WebElement isHidden;
	 
	 // Customization Tab - Number
	 @FindBy(xpath="advancedEditor.number")
	 private WebElement number;
	 
	 @FindBy(xpath="advancedEditor.numberArea")
	 private WebElement numberArea;
	 
	 @FindBy(xpath="advancedEditor.pencilNumber")
	 private WebElement pebcilNumber;
	 
	 @FindBy(name="defaultValue")
	 private WebElement defaultValue;
	 
	 @FindBy(name="isReadOnly")
     private WebElement isReadOnly;
	 	 
	 // Common elements 
	 private WebElement saveAttrWinButton;	 
	 
	 @FindBy(linkText="Customization")
	 private WebElement customizationTab;
	 
	 @FindBy(xpath="advancedEditor.drovableArea1")
	 private WebElement drovableArea1;
	 
	 @FindBy(xpath="advancedEditor.image")
	 private WebElement image;
	 
	 @FindBy(xpath="advancedEditor.dimensions")
	 private WebElement dimensions;
	 
	 private WebElement peAddProductTab;
	 
	 @FindBy(xpath="advancedEditor.pencilTab")
	 private WebElement pencilTab;
	 
	 @FindBy(className="com-form-editor-tab-title-text")
	 private WebElement tab;
	 
	 @FindBy(xpath="advancedEditor.tabNew")
	 private WebElement tabName;
	 
	 private WebElement saveTabAttributes;
	 
	 @FindBy(xpath="advancedEditor.lebelPosition")
	 private WebElement lebelPosition;	 
	 
	 // Customize number columns
	 @FindBy(xpath="advancedEditor.checkAll")
	 private WebElement checkAll;
	 
	 @FindBy(className="pe-tab-cells-per-row")
	 private WebElement arrowBottom;
	 
	 @FindBy(xpath="advancedEditor.oneColumn")
	 private WebElement oneColumn;
	 
	 private WebElement productEditorNextButton;
	 
	 /*
	  *  Basic Information tab
	  */
	
	 public void putProductName(String productName) {
		 peFormNameInput.sendKeys(productName);
	 }
	 
	 public void uploadProductImage(String imagePath) {
		 uploadFileRelative(peFormIconInput, imagePath); 
	 }
	 
	 public boolean getLebelLevel() {
		 String isSelectedValue = lebelPosition.getAttribute("selected");
		 if (isSelectedValue.trim().equals(""))
			 return true;
		 return false;
	 }
	 
	 /*
	  *  Customization Tab - Text Box
	  */
	 public void clickCustomizationTab() {
		 customizationTab.click();
	 }
	 
	 public void dragAndDropTextArea() {
		 dragAndDropElement(textBox, drovableArea1);
	 }
	 
	 public void dragAndDropTextAreaEI() {
         new Actions(driver).dragAndDropBy(textBox, 250, -15).build().perform();
	 }

	 public void clickPencilTextBox() throws InterruptedException {
		 clickSubMenuItem(textBoxArea, pencilTextBox);		 
	 }
	 
	 public void clickPencilTextBoxIE() {
		 textBoxArea.click();
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pencilTextBox);
	 }
	 
	 public void clickPencilDateIE() {
		 dateArea.click();
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pebcilDate);
	 }
	 
	 public void clickPencilNumberIE() {
		 numberArea.click();
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pebcilNumber);
	 }

	 public void selectRequired() {
		 isRequired.click();
	 }
	 
	 public void clickSaveBT() {
		 saveAttrWinButton.click();
	 }
	 
	 /*
	  * Customization Tab - Date
	  */
	 
	 public void dragAndDropDate() {
		 dragAndDropElement(date, drovableArea1);
	 }
	 
	 public void clickPencilDate() throws InterruptedException {
		 clickSubMenuItem(dateArea, pebcilDate);
	 }
	 
	 public void selectNotShownOnClientView() {
		 isHidden.click();
	 }
	 
	 /*
	  * Customization Tab - Number
	  */
	 
	 public void dragAndDropName() {
		 dragAndDropElement(number, drovableArea1);
	 }
	 
	 public void clickPencilNumber() throws InterruptedException {
		 clickSubMenuItem(numberArea, pebcilNumber);
	 }
	 
	 public void putDefaultValue(String defValue) {
		 defaultValue.sendKeys(defValue);
	 }
	 
	 public void selectReadOnly() {
		 isReadOnly.click();
	 }
	 
	 // Drop another fields
	 public void dragAndDropImage() {
		 dragAndDropElement(image, drovableArea1);
	 }
	 
	 public void dragAndDropDimentions() {
		 dragAndDropElement(dimensions, drovableArea1);
	 }
	 
	 /*
	  * Customize number columns
	  */
	 
	 public void selectAll() {
		 checkAll.click();
	 }
	 
	 public void selectOneColumn() throws InterruptedException {
		 arrowBottom.click();
		 Thread.sleep(800);
	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     oneColumn.click();
	 }
	 
	 public void submitProduct() {
		 productEditorNextButton.click();
	 }
	 
	 public void clickTab() {
		 tab.click();
	 }
	 
	 public void addAnotherTab() {
		 peAddProductTab.click();
	 }
	 
	 public void clickPencilTab() {
		 pencilTab.click();
	 }
	 
	 public void setTabName(String name) {
		 type(tabName, name);
	 }
	 
	 public void saveTabAttrib() {
		 saveTabAttributes.click();
	 }
}
