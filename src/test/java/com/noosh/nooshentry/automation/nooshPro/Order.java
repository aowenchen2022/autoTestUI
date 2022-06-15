package com.noosh.nooshentry.automation.nooshPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class Order extends Page {

	 public Order(WebDriver driver)
	 {
	      super(driver);
	      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	 }	
	 
	 @FindBy(id="order.title")
	 private WebElement orderTitle;
	 
	 @FindBy(id="order.completionDate")
	 private WebElement orderComplationDate;
	 
	 @FindBy(xpath="order.complationTime")
	 private WebElement complationTime;
	 
	 @FindBy(id="proceed_to_confirmation")
	 private WebElement proceedToConfirmationBT;
	 
	 @FindBy(id="confirm_order_creation")
	 private WebElement confirmOrderCreationBT;
	 
	 @FindBy(id="VIEW_ORDER_LIST")
	 private WebElement viewOrderList;
	 
	 @FindBy(id="orderAck_body")
	 private WebElement confirmationText;
	 
	 @FindBy(name="order_name")
	 private WebElement orderName;
	 
	 @FindBy(name="order.orderInList")
	 private WebElement orderInList;
	 
	 @FindBy(xpath="order.title")
	 private WebElement title;
	 
	 @FindBy(xpath="order.orderID")
	 private WebElement orderID;
	 
	 @FindBy(id="accept_order")
	 private WebElement acceptOrderBT;
	 
	 @FindBy(xpath="order.orderStatus")
	 private WebElement orderStatus;
	 
	 public void setOrderTitle(String title) {
		 type(orderTitle, title);
	 }
	 
	 public void setOrderCopmlationDate(String complationDate) {
		 type(orderComplationDate, complationDate);
	 }
	 
	 public void selectOrderComplationTime() {
		 complationTime.click();
	 }
	 
	 public void clickProceedToConfirmationBT() {
		 proceedToConfirmationBT.click();
	 }
	 
	 public void clickOrderCreationBT() {
		 confirmOrderCreationBT.click();
	 }
	 
	 public void clickViewOrderList() {
		 viewOrderList.click();
	 }
	 
	 public boolean checkCofirmationDisplayed(String commonText, String orderTitle) {
		 
		 boolean confirmText = (confirmationText.getText()).contains(commonText);
		 boolean order = (orderName.getText()).contains(orderTitle);
		 if (confirmText == true && order == true)
			 return true;
		 else
			 return false;
	 }
	 
	 public void clickOrderInList(NooshWebDriver driver, String title) {
		 driver.findElementByXPath("order.orderInList", "0", title).click();
	 }
	 
	 public String getOrderTitle() {
		 return title.getText();
	 }
	 
	 public void clickOrderInListSupplier(NooshWebDriver driver, String title) {
		 driver.findElementByXPath("order.orderID", "0", title).click();
	 }
	 
	 public void clickAcceptOrder() {
		 acceptOrderBT.click();
	 }
	 
	 public String checkOrderStatus(NooshWebDriver driver, String title) {
		 return driver.findElementByXPath("order.orderStatus", "0", title).getText();
	 }
}
