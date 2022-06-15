package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class EditProductPopup extends MainMenuPage
{
   public EditProductPopup(WebDriver driver)
      {
          super(driver);
          PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
      }
      
   private WebElement productNameInput;   
   private WebElement productIconInput;  
   private WebElement nextProductTab;   
   private WebElement prevProductTab;
//   private WebElement newTabTitle6;
   private WebElement fieldSaveButton;
   private WebElement productIconPreview;
   private WebElement peFormNameInput;
   private WebElement peFormIconInput;
   private WebElement peAddProductTab;
   
   @FindBy(xpath="editProductPopup.inputTab")
   private WebElement newTabTitleInput;
   
   @FindBy(xpath="editProductPopup.newTabTitle")
   private WebElement newTabTitle;
   
   @FindBy(id="ui-dialog-title-productPopup")   
   private WebElement editProductPopupTitle;
   
   @FindBy(xpath="editProductPopup.save")
   private WebElement save;
   
   @FindBy(xpath="editProductPopup.saveLower")
   private WebElement saveLower;
   
   @FindBy(css="li[form='textarea']")
   private WebElement textAreaBT;

   @FindBy(xpath="editProductPopup.textBox")
   private WebElement textBox;
   
   @FindBy(xpath="editProductPopup.dataElement")
   private WebElement dataElement;
   
   @FindBy(xpath="editProductPopup.targetEnvelope")
   private WebElement targetEnvelope;
   
   @FindBy(xpath="editProductPopup.targetBrochure")
   private WebElement targetBrochure;
   
   @FindBy(xpath="editProductPopup.target")
   private WebElement target;
   
   @FindBy(linkText="Customization")
   private WebElement customizationTab;
   
   @FindBy(linkText="Basic Information")
   private WebElement basicInfo;
   
   // Add new tab BT (+) 
 //  private WebElement addFormEditorTab;
   
   @FindBy(xpath="editProductPopup.textName")
   private WebElement textName;
      
   @FindBy(xpath="editProductPopup.textNameField")
   private WebElement textNameField;
   
   @FindBy(xpath="editProductPopup.textArea")
   private WebElement textArea;
   
   @FindBy(xpath="editProductPopup.editDateBT")
   private WebElement editDateBT;
   
   @FindBy(id="comFieldTitle")
   private WebElement dateNameField;
   
   @FindBy(xpath="editProductPopup.calcImg")
   private WebElement calcImg;
   
   @FindBy(linkText="15")
   private WebElement certDate;
    
   @FindBy(xpath="editProductPopup.isRequired")
   private WebElement isRequired; 
   
   @FindBy(xpath="editProductPopup.closeBT")
   private WebElement closeBT;
   
   @FindBy(xpath="editProduct.customizedTab2")
   private WebElement userTab;
   
   // Drag and drop elements
   @FindBy(xpath="editProductPopup.dropArea")
   private WebElement newDropable1;
   
   @FindBy(xpath="editProductPopup.nameTextBox")
   private WebElement textBoxName;   
   
   @FindBy(xpath="editProductPopup.nameTextBoxInput")
   private WebElement nameTextBoxInput;
   
   @FindBy(xpath="editProductPopup.textNameField")
   private WebElement textBoxField;
   
   @FindBy(xpath="editProductPopup.calculator")
   private WebElement calculator;
   
   private WebElement productEditorNextButton;
   
      public String getPopupTitle() {
         return editProductPopupTitle.getText();
      }     
      public void setProductName(String prName) {
    	  peFormNameInput.clear();
    	  peFormNameInput.sendKeys(prName);
      }
      public void setProductNameInPopup(String prName) {
    	  peFormNameInput.clear();
    	  peFormNameInput.sendKeys(prName);
      }
      public void ulpoadProductIconInPopup(String fileName) {
    	  uploadFileRelative(peFormIconInput, fileName); 
      }
      public void uploadProductIcon(String fileName) {
    	  uploadFileRelative(peFormIconInput, fileName);  
      }      
      public void getCustomization() {
         nextProductTab.click();
      }   
      public void getBasicInfoTab() {
    	  basicInfo.click();
      }
      public void saveCustomization() throws InterruptedException {
         //save.click();
    	 productEditorNextButton.click();
         Thread.sleep(4000);         
      }  
      public void getNewTab()  {
    	  peAddProductTab.click();
      }
      public void getNewTabInPopup() {
    	  peAddProductTab.click();
      }
      public void dragAndDropTextBox()  {
    	  dragAndDropElement(textBox, newDropable1);    	  
      }      
      public void dragAndDropDataBox() {
    	  this.dragAndDropElement(dataElement, newDropable1);
      }
      public void dragAndDropTextBoxIE() {
    	  new Actions(driver).dragAndDropBy(textBox, 200, 0).build().perform();
      }  
      public void dragAndDropDataBoxIE() {
    	  new Actions(driver).dragAndDropBy(dataElement, 200, 0).build().perform();
      }
      public void getPrevTab() {
        prevProductTab.click();
      }      
      public void getCustomizationTab() {
         customizationTab.click();
      }
      public void setTextFieldName(String name) {
    	  textName.click();
    	  type(textNameField, name);
      }
      public void setTextAreaText(String text) {
    	  textArea.click();
    	  type(textArea, text);
      }
      public void setNewNameTextBox(String newTextBoxName) throws InterruptedException {
    	  textBoxName.click();
    	  Thread.sleep(800);
    	  type(nameTextBoxInput, newTextBoxName);
      }
      public void setTextBoxText(String text) {
    	  type(textBoxField, text);
      }
      public void clickCalculator() {
    	  calculator.click();
      }            
      public void clickEditDateBT() {
    	  editDateBT.click();
      }
      public void setDateFieldName(String name) {
    	  type(dateNameField, name);
      }
      public void clickCalcImg() {
    	  calcImg.click();
      }
      public void setDate() {
    	  certDate.click();
      }
      public void clickSaveDateBT() {
    	  fieldSaveButton.click();
      }
      public void setRequired() {
    	  isRequired.click();
      }
      public void setNewTabName(String newTabName) {
    	  doubleClickElement(newTabTitle);
    	  type(newTabTitleInput, newTabName);
      }
      public void clickCustomizedTab(WebElement element) {
    	  element.click();
      }
      public boolean checkIconDisplayed() {
   	      return productIconPreview.isDisplayed();
      }
      public void clickCloseBT() {
    	  closeBT.click();
      }
      public void clickUserTab() {
    	  userTab.click();
      }
}

