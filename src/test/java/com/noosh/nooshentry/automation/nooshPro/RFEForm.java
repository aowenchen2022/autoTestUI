package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class RFEForm extends Page {

	 public RFEForm(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }
	 
	 @FindBy(id="rfe_0.title")
	 private WebElement titleRFE;
	 
	 @FindBy(id="rfe_0.dueDate")
	 private WebElement estimateDueDate;
	 
	 @FindBy(id="rfe_0.proposedCompletionDate")
	 private WebElement proposedCompationDate;
	 
	 @FindBy(xpath="rfeform.dueDate")
	 private WebElement dueDate;
	 
	 @FindBy(xpath="rfeform.dateDue")
	 private WebElement dateDue;
	 
	 @FindBy(xpath="rfeform.complationDate")
	 private WebElement complationDate;
	 
	 @FindBy(xpath="rfeform.closeButton")
	 private WebElement closeButton;
	 
	 @FindBy(xpath="rfeform.dateComplation")
	 private WebElement dateComplation;
	 
	 @FindBy(xpath="rfeform.quantity1")
	 private WebElement quantity1;
	 
	 @FindBy(xpath="rfeform.quantity2")
	 private WebElement quantity2;
	 
	 @FindBy(id="edit_icon")
	 private WebElement editIcon;
	 
	 @FindBy(className="openPopup")
	 private WebElement contacts;
	 
	 @FindBy(id="send_RFE")
	 private WebElement sendRFE;
	 
	 @FindBy(id="view_rfe_list")
	 private WebElement viewRFEList;
	 
	 @FindBy(xpath="rfeform.refNumber")
	 private WebElement refNumber;
	 
	 @FindBy(xpath="rfeform.quantityOne")
	 private WebElement quantityOne;
	 
	 @FindBy(xpath="rfeform.quantityTwo")
	 private WebElement quantityTwo;
	 
	 @FindBy(xpath="rfeform.estimeTitle")
	 private WebElement estimeTitle;
	 
	 /*
	  * Create supplier estimate
	  */
	 
	 private WebElement createEstimate;
	 
	 @FindBy(id="est.title")
	 private WebElement estTitle;
	 
	 @FindBy(xpath="rfeform.expirationDate")
	 private WebElement expirationDate;
	 
	 @FindBy(xpath="rfeform.priceQuantity1")
	 private WebElement priceQuantity1;
	 
	 @FindBy(xpath="rfeform.priceQuantity2")
	 private WebElement priceQuantity2;
	 
	 @FindBy(id="SEND_ESTIMATE")
	 private WebElement sendEstimate;
	 
	 @FindBy(xpath="rfeform.confirmation")
	 private WebElement confirmationMessage;
	 
	 @FindBy(id="view_estimate_list")
	 private WebElement viewEstimateListBT;
	 
	 @FindBy(xpath="rfeform.rfeTitle")
	 private WebElement rfeTitle;
	 
	 @FindBy(xpath="rfeform.rfeRefNumber")
	 private WebElement rfeRefNumber;
	 
	 @FindBy(xpath="rfeform.fullRFEName")
	 private WebElement fillRFEName;
	 
	 @FindBy(id="rfe_0.dueDate")
	 private WebElement estimDueDate;
	 
	 @FindBy(id="rfe_0.proposedCompletionDate")
	 private WebElement proposOderDate;
	 
	 @FindBy(xpath="rfeform.estimateDue")
	 private WebElement estimateDue;
	 
	 @FindBy(xpath="rfeform.spEstimate")
	 private WebElement spEstimate;
	 
	 @FindBy(xpath="rfeform.spEstimateTitle")
	 private WebElement spEstimateTitle;
	 
	 @FindBy(xpath="freform.supplierEstimateTitle")
	 private WebElement supplierEstimateTitle;
	 
	 @FindBy(xpath="rfeform.specTitle")
	 private WebElement specTitle;
	 
	 /*
	  * SP create order
	  */
	 @FindBy(xpath="rfeform.totalPrice")
	 private WebElement totalPrice;
	 
	 @FindBy(id="award_the_order")
	 private WebElement awardOrderBT;
	 
	 public void setRFETitle(String title) {
		 type(titleRFE, title);
	 }
	 
	 public void setEstimateDueDate(String dueDate) {
		 type(estimateDueDate, dueDate);
	 }
	 
	 public void setProposedComplationDate(String complationDate) {
		 proposedCompationDate.clear();
		 type(proposedCompationDate, complationDate);
	 }
	 
	 public void clickCalendarDue() throws InterruptedException {
		 dueDate.click();
		 Thread.sleep(800);
		 dateDue.click();
//		 closeButton.click();
	 }
	 
	 public void clickCalendarComplation() throws InterruptedException {
		 complationDate.click();
		 Thread.sleep(800);
		 dateComplation.click();
//		 closeButton.click();
	 }
	 
	 public void clickEditIcon() {
		 editIcon.click();
	 }
	 
	 public void setQuantity1(String quantity) {
		 quantity1.sendKeys(quantity);
	 }
	 
	 public void setQuantity2(String quantity) {
		 quantity2.sendKeys(quantity);
	 }
	 
	 public void clickContactsLink() {
		 contacts.click();
	 }
	 
	 public void clickSendRFE() {
		 sendRFE.click();
	 }
	 
	 public void clickRFEList() {
	     viewRFEList.click();
	 }
	 
	 public void openRFE() {
		 refNumber.click();
	 }
	 
     public String getEstimateTitle() {
    	 return estimeTitle.getText();
     }
	 
	 public String getQuqntityOne() {
		 return quantityOne.getText();
	 }
	 
	 public String getQuantityTwo() {
		 return quantityTwo.getText();
	 }
	 
	 /*
	  * Create supplier estimate
	  */
	 
	 public void clickCreateEstimateBT() {
		 createEstimate.click();
	 }
	 
	 public void setEstimateTitle(String title) {
		 estTitle.clear();
		 type(estTitle, title);
	 }
	 
	 public void clickCalendarExpiration() throws InterruptedException {
		 expirationDate.click();
		 Thread.sleep(800);
		 dateComplation.click();
//		 closeButton.click();
	 }
	 
	 public void setPriceForQuality1(NooshWebDriver driver, String title, String price) {
		 driver.findElementByXPath("rfeform.priceQuantity1", "0", title).sendKeys(price);
	 }
	 
	 public void setPriceForQuality2(NooshWebDriver driver, String rowTitle, String price) {
		 driver.findElementByXPath("rfeform.priceQuantity2", "0", rowTitle).sendKeys(price);	 
	 }
	 
	 public void clickSendEstimateBT() {
		 sendEstimate.click();
	 }
	 
	 public String getConfermationStatus() {
		 return confirmationMessage.getText();
	 }
	 
	 public void clickViewEstimateListBT() {
		 viewEstimateListBT.click();
	 }
	 
	 public String getRFETitle() {
		 return rfeTitle.getText();
	 }
	 
	 public String getRFEReferenceNumber() {
		 return rfeRefNumber.getText();
	 }
	 
	 public String getFullRFEName() {
		 return fillRFEName.getText();
	 }
	 
	 public void openFullRFE() {
		 fillRFEName.click();
	 }
	 
	 public void setEstimateDueDatePro(String date) {
		 estimDueDate.sendKeys(date);
	 }
	 
	 public void setProposedCompletionDate(String date) {
		 proposOderDate.clear();
		 proposOderDate.sendKeys(date);
	 }
	 
	 public void setEstimadeDue(String due) {
		 estimateDue.sendKeys(due);
	 }
	 
	 public String getSPEstimateName() {
		 return spEstimate.getText();
	 }
	 
	 public String getSPEstimateTitle() {
		 return spEstimateTitle.getText();
	 }
	 
	 public String getSupplierEstimateTitle() {
		 return supplierEstimateTitle.getText();
	 }
	 
	 public String getSpecTitle() {
		 return specTitle.getText();
	 }
	 
	 /*
	  * SP create order
	  */
	 
	 public void selectTotalPrice() {
		 totalPrice.click();
	 }
	 
	 public void clickAwardOrderBT() {
		 awardOrderBT.click();
	 }
}
