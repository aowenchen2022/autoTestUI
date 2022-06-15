package com.noosh.nooshentry.automation.demoSQANoosh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DashboardNoosh extends Page
{
   public DashboardNoosh(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }
   
   @FindBy(className="sp-header-simple-logout")
   private WebElement logout;

   public LoginPage logoutNoosh()
   {
      logout.click();
      return new LoginPage(driver);
   }
}
