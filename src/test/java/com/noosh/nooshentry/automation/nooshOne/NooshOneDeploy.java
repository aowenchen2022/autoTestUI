/***********************************************************************
* This class does NooshOne deployment
* @author Ken zhang
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Properties;

public class NooshOneDeploy extends BaseSeleniumTest {  
    @Test
    @Parameters({"url", "loginName", "loginPW"})
    public void ngeDeploy(String url, String loginName, String loginPW) throws InterruptedException {	   
    	 String buildVersion = null;
	     String gitBranch = null;
	     String domain = null;
	     try {
	         Properties prop = new Properties();
	         FileInputStream in = new FileInputStream("C:\\NooshOneBuild\\savedParameters.properties");
	         prop.load(in);
	         buildVersion = prop.getProperty("Build_Version");
	         gitBranch = prop.getProperty("Git_Branch");
	         domain = prop.getProperty("domain");
	         in.close();
	     } catch (IOException ioe) {
	    	 System.out.println("Properties file C:\\NooshOneBuild\\savedParameters.properties doesn't exist" );
	     }
         Thread.sleep(1500);         
         driver.get(url); 
         driver.manage().window().maximize();  
         WebElement userName = driver.findElement(By.id("user"));
         userName.clear();
         userName.sendKeys(loginName);
         Thread.sleep(1000);
         WebElement pw = driver.findElement(By.id("pass"));
         pw.clear();
         pw.sendKeys(loginPW);
         Thread.sleep(1000);
         driver.findElement(By.className("ui_submit")).click();
         Thread.sleep(3000);             
         driver.switchTo().frame(0);
         driver.findElement(By.className("linkwithicon")).findElement(By.tagName("a")).click();
         Thread.sleep(2000);
         List<WebElement> div1s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
         div1s.get(3).findElement(By.tagName("a")).click();
         Thread.sleep(2000);
         driver.switchTo().defaultContent();
         Thread.sleep(2000);
         driver.switchTo().frame(1);
         List<WebElement> gridrow1s = driver.findElements(By.className("ui_grid_row"));         
         WebElement submitBTN = gridrow1s.get(1).findElement(By.className("ui_submit"));
         System.out.println("btn text=" + submitBTN.getAttribute("value"));
         List<WebElement> boxes1 = gridrow1s.get(1).findElements(By.className("ui_textbox"));
         buildVersion = buildVersion.trim();  
         boxes1.get(0).clear();
         boxes1.get(0).sendKeys(buildVersion);
         Thread.sleep(1000);
         boxes1.get(1).clear();
         boxes1.get(1).sendKeys("y");
         Thread.sleep(1000);
         //submitBTN.click();
         Thread.sleep(180000);
         driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click(); 
         Thread.sleep(5000);       
         driver.switchTo().defaultContent();
         driver.switchTo().frame(0);
         List<WebElement> links = driver.findElement(By.tagName("form")).findElements(By.className("linkwithicon"));
         links.get(1).findElement(By.tagName("a")).click();
         Thread.sleep(3000); 
         driver.get("https://one.scd.noosh.com/version.html");
         Thread.sleep(2000);
         List<WebElement> lis = driver.findElement(By.tagName("body")).findElements(By.tagName("li"));
         String version = lis.get(0).getText();
         version = (version.substring(9));
         System.out.println("\nversion="+version);
         String branch = lis.get(1).getText();
         branch = (branch.substring(8));
         gitBranch = gitBranch.trim();
         System.out.println("\nbranch="+branch+"\n");
         Assert.assertEquals(version, buildVersion);
         Thread.sleep(1000);
         Assert.assertEquals(branch, gitBranch);
         Thread.sleep(5000);  
         driver.close();
   }
}
   