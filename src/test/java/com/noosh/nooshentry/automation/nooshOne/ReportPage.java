package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ReportPage extends Page
{
   public ReportPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }

   private WebElement glbReports;
   
 //  @FindBy(id="btn_standardReports")
   @FindBy(id="btn_standardReports")
   private WebElement reportsTab;
   
   @FindBy(xpath="reportPage.accountActivity")
   private WebElement accountActivity;
   
   @FindBy(name="reportPage.buyingActivity")
   private WebElement buyingActivity;
      
   @FindBy(name="finish")
   private WebElement finishBT;
   
   @FindBy(linkText="Standard Reports")
   private WebElement standardReport;
   
   @FindBy(xpath="reportPage.projectStatus")
   private WebElement projectStatus;
   
   private WebElement NavigationBar;
   
   public void clickReportsBT() {
	   glbReports.click();
   }
   
   public void clickReportsTab() {
	   reportsTab.click();
   }
   
   public void clickAccountActivity() {
	   accountActivity.click();
   }
   
   public void clickBuyingActivity() {
	   buyingActivity.click();
   }
   
   public void clickFinishBT() {
	   finishBT.click();
   }
   
   public void clickStandardReport() {
	   standardReport.click();
   }
   
   public void clickProjectStatus() {
	   projectStatus.click();
   }
   
   public String getAttributNavigationBar() {
	   return NavigationBar.getAttribute("opt");
   }
   
   public double getLoadTime() {
	   final long startLoading = System.currentTimeMillis();
	   while(!NavigationBar.isDisplayed()) {		   
	   }
	   final long finisgLoading = System.currentTimeMillis();
	   return (finisgLoading - startLoading);
   }
}

