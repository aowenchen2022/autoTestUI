package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SiteListPage extends Page
{
   public SiteListPage(WebDriver driver)
      {
          super(driver);
          PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
      }
   
   private WebElement profileTitleUserName;  
   
   @FindBy(id="glbDashboard")  //new code
 //  @FindBy(id="glbHeadMenu")
  // @FindBy(xpath="siteListPage.home")  //old code
   private WebElement dashboard;
  
   @FindBy(xpath="siteListPage.homearrow")
   private WebElement arrowHome;
   
   @FindBy(id="glbMyDeskDropdownHref")  
   private WebElement myDesk;
   
   public String getProfileTitle() {
      return profileTitleUserName.getText();
   }     
   
   public void getMyDeskPageBack() throws InterruptedException {	  
	  //WebElement menu = driver.findElement(By.id("glbHeadMenu")); // new code
	  //dashboard = menu.findElement(By.tagName("a"));  // new code
	  //clickSubMenuItem(dashboard, myDesk);
	   driver.get(myDesk.getAttribute("href"));
   }  
   
   // does not work for IE
   public void getMyDeskPageBackIE() throws InterruptedException {
	   clickSubMenuItemIE(arrowHome, myDesk, "glbMyDeskDropdown"); 
   }
}
