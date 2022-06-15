package com.noosh.nooshentry.automation.nooshOne;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ManageProductsPage extends MainMenuPage
{
   public ManageProductsPage(WebDriver driver)
   {
      super(driver);
      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);;
   }
   
//   @FindBy(xpath="//div[@pid='5000020']")
   @FindBy(xpath="manageProductsPage.brochureCustomize")
   private WebElement brochureCustomize;
   
   @FindBy(xpath="manageProductsPage.brochureCustomizeQA2")
   private WebElement brochureCustomizeQA2;
   
   @FindBy(xpath="manageProductsPage.brochureCustomizeSDM")
   private WebElement brochureCustomizeSDM;
   
   @FindBy(xpath="manageProductsPage.brochureCustomizeSPD")
   private WebElement brochureCustomizeSPD;

//   @FindBy(xpath="//td[text()='']")
   @FindBy(xpath="manageProductsPage.customizeProductBT")
   private WebElement customizeProductBT;
 
   @FindBy(xpath="testDemoSqaFF.customizeBT2")
   private WebElement customizeBT;
   
   @FindBy(xpath="testDemoSqaFF.customizeBT3")
   private WebElement customizeBTSmoke;
   
   @FindBy(xpath="manageProductsPage.emptyProduct")
   private WebElement emptyProduct;
   
   @FindBy(xpath="testDemoSqaFF.customizeEmptyBT")
   private WebElement customizeEmptyProduct;

   private WebElement createNewProductTop;
   private WebElement serviceSpecsTable;
      
   public void getCustomizeProductPopup() throws InterruptedException {
       //brochureCustomize.click();
       List<WebElement> trs = serviceSpecsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	   Thread.sleep(500);
	   List<WebElement> tds = trs.get(0).findElements(By.tagName("td"));
	   Thread.sleep(500);
	   List<WebElement> divs = tds.get(1).findElements(By.tagName("div"));
	   Thread.sleep(500);
	   divs.get(0).click();
	   Thread.sleep(2000);
   }
   
   public void getCustomizeProductPopupQA2(){
	   brochureCustomizeQA2.click();
   }
   
   public void getCustomizeProductPopupSDM() {
	   brochureCustomizeSDM.click();
   }
   
   public void getCustommizeProductPopupSPD() throws InterruptedException {
	   //brochureCustomizeSPD.click();
	   List<WebElement> trs = serviceSpecsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	   Thread.sleep(500);
	   List<WebElement> tds = trs.get(0).findElements(By.tagName("td"));
	   Thread.sleep(500);
	   List<WebElement> divs = tds.get(1).findElements(By.tagName("div"));
	   Thread.sleep(500);
	   divs.get(0).click();
	   Thread.sleep(2000);
   }
   
   public void clickCreateNewProductBT(){
	   createNewProductTop.click();
   }
   
   public void clickProductCustomizeBT(WebElement productName){
       productName.click();
   }

   public void clickCustomizeBT(){
	   customizeBT.click();
   }
   
   public void clickCustomizeBTSmoke() {
	   customizeBTSmoke.click();
   }

   public void clickCustomize(String elementXpath){
       driver.findElement(By.xpath(elementXpath)).click();
   }
   public void clickEmptyProduct() {
	   List<WebElement> products = driver.findElements(By.className("scroll-list-img"));
	   for (WebElement product : products) {
		   if (product.getAttribute("alt").equals("Empty Smart Form")) {  
		  // if (product.getAttribute("alt").equals("Empty Request Form")) {   //10/17 change
			   product.click();
			   break;
		   }
	   }
	   //emptyProduct.click();
   }
   
   public void clickCustomizeEmptyProduct() {
	   customizeEmptyProduct.click();
   }
}