package com.noosh.nooshentry.automation.buyerSite;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Page {
   protected final WebDriver driver;
   public WebElement myElement;
   public Page(WebDriver driver) {
       this.driver=driver;
   }
   
   public String getTitle(WebDriver driver) {
      return driver.getTitle();
   }
   
   public boolean checkPageTitle(WebDriver driver, String title) {
      if (title.equals(driver.getTitle()))
         return true;
         throw new IllegalStateException("This is not " + driver.getTitle() +
               " page");
   }
   
   public static boolean isTextPresent(String what, WebDriver driver) {
       try {
               driver.findElement(By.tagName("body")).getText().contains(what);
               return true;
           } 
       catch (NoSuchElementException e) {
           System.out.println("Page does not contain text: " + what);    
         return false;
           }
   }       

   public void moveWebElement(WebElement sourceElement, int xMoving, int yMoving) {
      new Actions(driver).dragAndDropBy(sourceElement, xMoving, yMoving).build().perform();
   }
   
   public void takeScreenshort(WebDriver driver, String fileName) {
      File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
         FileUtils.copyFile(scrFile1, new File(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
   } 
}
