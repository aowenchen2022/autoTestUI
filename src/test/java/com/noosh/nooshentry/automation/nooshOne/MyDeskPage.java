package com.noosh.nooshentry.automation.nooshOne;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyDeskPage extends Page {
   public MyDeskPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }

   @FindBy(id="topNavUserName")
   private WebElement profileTitleUserName;
 
   //@FindBy(id="lnk4")
   //@FindBy(id="lnk5")  //spd
   @FindBy(id="mmlink0")
   private WebElement sitesLink;
   
   @FindBy(name="topnav_ngDashboard")
   private WebElement nooshGroupEdition;

   public String getProfileTitle() {
      return profileTitleUserName.getText();
   }
   
   // click on Sites link and get Site List page
   public void getSitesList() {
      //sitesLink.click();	
	   //String domain = System.getProperty("selenium.domain");
	   //driver.get("https://demo." + domain + ".noosh.com/service/site/siteList?locale=en_US");
	   List<WebElement> links = driver.findElements(By.tagName("a"));
	   for (WebElement link : links) {
		   String name = link.getAttribute("name");
		   if ("topnav_ngDashboard".equals(name)) {
			   System.out.println("switch url=" + link.getAttribute("href"));
			   link.click();
		   }
	   }
   }
   public void getSitesListIE() {
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sitesLink);
   }
   
   
   public DashboardNoosh getDashboard() {
      nooshGroupEdition.click();
      return new DashboardNoosh(driver);
   }
	
}




