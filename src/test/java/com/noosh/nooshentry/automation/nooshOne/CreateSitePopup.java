package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class CreateSitePopup extends MainMenuPage
{
      public CreateSitePopup(WebDriver driver)
      {
          super(driver);
          PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
      }
   
      @FindBy(id="ui-dialog-title-createPagePopup")      
      private WebElement createWindowPopupTitle;
      
      @FindBy(css="input[value='5000001']")
      private WebElement printers;
      
      private WebElement createPagePageName;
      
      private WebElement createPageSubmit;
      
      public String getPopupTitle()
      {
         return createWindowPopupTitle.getText();
      }
      public void setPageName(String pageName)
      {
         createPagePageName.clear();
         createPagePageName.sendKeys(pageName);
      }      
      public void createSite()
      {
         createPageSubmit.click();
      }      
      public void selectPrintersCategory()
      {
         printers.click();
      }
 	  public void selectCategory(String category)
 	  {
 		 WebElement categoryName = driver.findElement(By.xpath("//input[@value='" +
 				   category + "']")); 
 		 categoryName.click();
 	  }      
}
